/* Copyright 2019-2022 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================
*/
package org.tensorflow;

import static org.tensorflow.Graph.resolveOutputs;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrType;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SessionRun;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetConfig;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Graph;
import org.tensorflow.internal.c_api.TF_Operation;
import org.tensorflow.internal.c_api.TF_Output;
import org.tensorflow.internal.c_api.TF_Session;
import org.tensorflow.internal.c_api.TF_SessionOptions;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.internal.types.registry.TensorTypeRegistry;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.ReadVariableOp;
import org.tensorflow.proto.framework.ConfigProto;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.proto.framework.RunMetadata;
import org.tensorflow.proto.framework.RunOptions;
import org.tensorflow.proto.util.SaverDef;
import org.tensorflow.types.TString;

/**
 * Driver for {@link Graph} execution.
 *
 * <p>A {@code Session} instance encapsulates the environment in which {@link Operation}s in a
 * {@link Graph} are executed to compute {@link Tensor Tensors}. For example:
 *
 * <pre>{@code
 * // Let's say graph is an instance of the Graph class
 * // for the computation y = 3 * x
 *
 * try (Session s = new Session(graph)) {
 *   try (Tensor x = Tensor.create(2.0f);
 *       Tensor y = s.runner().feed("x", x).fetch("y").run().get(0)) {
 *       System.out.println(y.floatValue());  // Will print 6.0f
 *   }
 *   try (Tensor x = Tensor.create(1.1f);
 *       Tensor y = s.runner().feed("x", x).fetch("y").run().get(0)) {
 *       System.out.println(y.floatValue());  // Will print 3.3f
 *   }
 * }
 * }</pre>
 *
 * <p><b>WARNING:</b>A {@code Session} owns resources that <b>must</b> be explicitly freed by
 * invoking {@link #close()}.
 *
 * <p>Instances of a Session are thread-safe. Modifying a graph concurrently while other threads are
 * using sessions of that graph <b>is not safe</b>.
 */
public final class Session implements AutoCloseable {

  /**
   * Construct a new session with the associated {@link Graph}.
   *
   * @param g The {@link Graph} the created Session will operate on.
   */
  public Session(Graph g) {
    this(g, (ConfigProto) null);
  }

  /**
   * Construct a new session with the associated {@link Graph} and configuration options.
   *
   * @param g The {@link Graph} the created Session will operate on.
   * @param config Configuration parameters for the session specified as a <a
   *     href="https://www.tensorflow.org/code/tensorflow/core/protobuf/config.proto">ConfigProto</a>
   *     protocol buffer.
   * @throws IllegalArgumentException if the config is not a valid serialization of the ConfigProto
   *     protocol buffer.
   */
  public Session(Graph g, ConfigProto config) {
    this(g, true, config);
  }

  /**
   * Construct and optionally initialize a new session with the associated {@link Graph}.
   *
   * @param g The {@link Graph} the created Session will operate on.
   * @param autoInit Whether to initialize the session.
   */
  public Session(Graph g, boolean autoInit) {
    this(g, autoInit, null);
  }

  /**
   * Construct and optionally initialize a new session with the associated {@link Graph} and
   * configuration options.
   *
   * @param g The {@link Graph} the created Session will operate on.
   * @param autoInit Whether to initialize the session.
   * @param config Configuration parameters for the session specified as a <a
   *     href="https://www.tensorflow.org/code/tensorflow/core/protobuf/config.proto">ConfigProto</a>
   *     protocol buffer.
   * @throws IllegalArgumentException if the config is not a valid serialization of the ConfigProto
   *     protocol buffer.
   */
  public Session(Graph g, boolean autoInit, ConfigProto config) {
    graph = g;
    Graph.Reference r = g.ref();
    try {
      nativeHandle = allocate(r.nativeHandle(), null, config);
      graphRef = g.ref();
    } finally {
      r.close();
    }
    this.autoInit = autoInit;
  }

  /**
   * Wrap an existing session with the associated {@link Graph}.
   *
   * <p>Does not enable auto-init.
   */
  Session(Graph g, TF_Session nativeHandle) {
    graph = g;
    this.nativeHandle = nativeHandle;
    graphRef = g.ref();
    this.autoInit = false;
  }

  /**
   * Release resources associated with the Session.
   *
   * <p>Blocks until there are no active executions ({@link Session.Runner#run()} calls). A Session
   * is not usable after close returns.
   */
  @Override
  public void close() {
    graphRef.close();
    synchronized (nativeHandleLock) {
      if (nativeHandle == null || nativeHandle.isNull()) {
        return;
      }
      while (numActiveRuns > 0) {
        try {
          nativeHandleLock.wait();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          // Possible leak of the Session and Graph in this case?
          return;
        }
      }
      delete(nativeHandle);
      nativeHandle = null;
    }
  }

  /**
   * Execute any un-ran initializers. Will be done automatically unless disabled at session
   * creation.
   *
   * <p>This runs any ops that have been created with an init scope that have not already been ran.
   */
  public void initialize() {
    Runner runner = runner();
    graph.initializers().stream().filter((x) -> !ranInits.contains(x)).forEach(runner::addTarget);
    setInitialized();
    if (!runner.isEmpty()) {
      runner.runNoInit();
    }
  }

  /**
   * Set the ran initializers to all initializers in the graph, as if they had been run. <b>Does not
   * actually ensure they are ran.</b>
   */
  void setInitialized() {
    ranInits.clear();
    ranInits.addAll(graph.initializers());
  }

  /**
   * Execute the graph's initializers, regardless of whether the session has been initialized.
   *
   * <p>This runs any ops that have been created with an init scope.
   *
   * @return this
   */
  public Session forceInitialize() {
    Set<Operation> initializers = graph.initializers();
    if (!initializers.isEmpty()) {
      Runner runner = runner();
      initializers.forEach(runner::addTarget);
      runner.runNoInit();
    }
    ranInits.clear();
    ranInits.addAll(graph.initializers());
    return this;
  }

  /**
   * Run {@link Operation}s and evaluate {@link Tensor Tensors}.
   *
   * <p>A Runner runs the necessary graph fragments to execute every {@link Operation} required to
   * evaluate the {@link Tensor Tensors} to fetch. The {@link #feed(String, int, Tensor)} call
   * allows callers to override the value of {@link Tensor Tensors} in the graph by substituting the
   * provided {@link Tensor Tensors} for the outputs of the operations provided to {@link
   * #feed(String, int, Tensor)}.
   */
  public final class Runner {

    /**
     * Avoid evaluating {@code operation} and substitute {@code t} for the value it produces.
     *
     * @param operation Is either the string name of the operation, in which case this method is a
     *     shorthand for {@code feed(operation, 0)}, or it is a string of the form
     *     <tt>operation_name:output_index</tt> , in which case this method acts like {@code
     *     feed(operation_name, output_index)}. These colon-separated names are commonly used in the
     *     {@code SignatureDef} protocol buffer messages that are included in {@link
     *     SavedModelBundle#metaGraphDef()}.
     * @param t the tensor substituting the operation
     * @return this session runner
     * @throws IllegalArgumentException if no output exists with the provided name
     */
    public Runner feed(String operation, Tensor t) {
      return feed(graph.outputOrThrow(operation), t);
    }

    /**
     * Avoid evaluating the {@code index}-th output of {@code operation} by substituting {@code t}
     * for the value it produces.
     *
     * <p>Operations in a {@link Graph} can have multiple outputs, {@code index} identifies which
     * one {@code t} is being provided for.
     *
     * @param operation the string name of the operation
     * @param t the tensor substituting the operation
     * @return this session runner
     * @throws IllegalArgumentException if no operation exists with the provided name
     * @throws IndexOutOfBoundsException if the operation has no output with the given index
     */
    public Runner feed(String operation, int index, Tensor t) {
      Operation op = graph.operationOrThrow(operation);
      inputs.add(op.output(index));
      inputTensors.add(t);
      return this;
    }

    /**
     * Use {@code t} instead of the Tensor referred to by executing the operation referred to by
     * {@code operand}.
     *
     * @param operand the node in the graph representing the operation to substitute
     * @param t the tensor substituting the operation
     * @return this session runner
     */
    public Runner feed(Operand<?> operand, Tensor t) {
      if (operand.env() != graph) {
        throw new IllegalStateException(
            "Can't feed value for operand "
                + operand
                + ", it is from "
                + (operand.env().isEager() ? "an eager session" : "a different graph")
                + ".");
      }

      inputs.add(operand.asOutput());
      inputTensors.add(t);
      return this;
    }

    /**
     * Make {@link #run()} return the output of {@code operation}.
     *
     * <p>If the output is a resource variable, will fetch the value.
     *
     * @param operation Is either the string name of the operation, in which case this method is a
     *     shorthand for {@code fetch(operation, 0)}, or it is a string of the form
     *     <tt>operation_name:output_index</tt> , in which case this method acts like {@code
     *     fetch(operation_name, output_index)}. These colon-separated names are commonly used in
     *     the {@code SignatureDef} protocol buffer messages that are included in {@link
     *     SavedModelBundle#metaGraphDef()}.
     * @return this session runner
     * @throws IllegalArgumentException if no output exists with the provided name
     */
    public Runner fetch(String operation) {
      Runner r = fetch(graph.outputOrThrow(operation), false);
      outputNames.add(operation);
      return r;
    }

    /**
     * Make {@link #run()} return the {@code index}-th output of {@code operation}.
     *
     * <p>If the output is a resource variable, will fetch the value.
     *
     * <p>Operations in a {@link Graph} can have multiple outputs, {@code index} identifies which
     * one to return.
     *
     * @param operation the string name of the operation
     * @return this session runner
     * @throws IllegalArgumentException if no operation exists with the provided name
     * @throws IndexOutOfBoundsException if the operation has no output with the given index
     */
    public Runner fetch(String operation, int index) {
      Operation op = graph.operationOrThrow(operation);
      return fetch(op.output(index));
    }

    /**
     * Makes {@link #run()} return the Tensor referred to by {@code output}.
     *
     * <p>If {@code output} is a resource variable, will fetch the value.
     *
     * @param output the node to fetch the tensor from
     * @return this session runner
     */
    public Runner fetch(Output<?> output) {
      return fetch(output, true);
    }

    /**
     * Makes {@link #run()} return the Tensor referred to by {@code output}.
     *
     * <p>If {@code output} is a resource variable, will fetch the value.
     *
     * @param output the node to fetch the tensor from
     * @param recordName Records the output name. If false the output name must be recorded by the
     *     calling method as otherwise the result object will throw on construction.
     * @return this session runner
     */
    private Runner fetch(Output<?> output, boolean recordName) {
      if (output.env() != graph) {
        throw new IllegalStateException(
            "Can't fetch output "
                + output
                + ", it is from "
                + (output.env().isEager() ? "an eager session" : "a different graph")
                + ".");
      }

      if (output.dataType() == DataType.DT_RESOURCE) {
        int[] rawDt = new int[1];

        GraphOperation graphOp = (GraphOperation) output.op();

        try (PointerScope scope = new PointerScope()) {
          TF_Status status = TF_Status.newStatus();
          TF_OperationGetAttrType(graphOp.getUnsafeNativeHandle(), "dtype", rawDt, status);
          status.throwExceptionIfNotOK();
        }

        DataType valueDt = DataType.forNumber(rawDt[0]);

        Operand<?> read = null;
        for (GraphOperation op : graphOp.consumers()) {
          if (op.dtype(0) == valueDt && op.type().equals(ReadVariableOp.OP_NAME)) {
            read = op.output(0);
            break;
          }
        }

        if (read == null) {
          read =
              Ops.create(graph)
                  .withSubScope("session_reads")
                  .withName(output.op().name() + "_read")
                  .readVariableOp(output, TensorTypeRegistry.find(valueDt).type());
        }

        outputs.add(read.asOutput());
      } else {
        outputs.add(output);
      }
      if (recordName) {
        outputNames.add(output.name());
      }
      return this;
    }

    /**
     * Makes {@link #run()} return the Tensor referred to by the output of {@code operand}.
     *
     * <p>If {@code operand} is a resource variable, will fetch the value.
     *
     * @param operand the node to fetch the tensor from, as an operand
     * @return this session runner
     */
    public Runner fetch(Operand<?> operand) {
      return fetch(operand.asOutput());
    }

    /**
     * Make {@link #run()} execute {@code operation}, but not return any evaluated {@link Tensor
     * Tensors}.
     *
     * @param operation Is either the string name of the operation or it is a string of the form
     *     <tt>operation_name:output_index</tt>, where <tt>output_index</tt> will simply be ignored.
     * @return this session runner
     * @throws IllegalArgumentException if no operation exists with the provided name
     */
    public Runner addTarget(String operation) {
      return addTarget(graph.outputOrThrow(operation));
    }

    /**
     * Make {@link #run()} execute {@code operation}, but not return any evaluated {@link Tensor
     * Tensors}.
     *
     * @param operation the operation to execute
     * @return this session runner
     * @throws IllegalArgumentException if the operation is not a {@link GraphOperation}
     * @throws IllegalStateException if the operation is not from the session's graph.
     */
    public Runner addTarget(Operation operation) {
      if (operation.env() != graph) {
        throw new IllegalStateException(
            "Can't target operation "
                + operation
                + ", it is from "
                + (operation.env().isEager() ? "an eager session" : "a different graph")
                + ".");
      }
      targets.add((GraphOperation) operation);
      return this;
    }

    /**
     * Make {@link #run} execute {@code op}, but not return any evaluated {@link Tensor Tensors}.
     *
     * @param op the operation to execute, as an {@link Op}
     * @return this session runner
     */
    public Runner addTarget(Op op) {
      return addTarget(op.op());
    }

    /**
     * Set options (typically for debugging) for this run.
     *
     * <p>The options are presented as a <a
     * href="https://www.tensorflow.org/code/tensorflow/core/protobuf/config.proto">RunOptions
     * protocol buffer</a>.
     *
     * @param options a {@code RunOptions} proto
     * @return this session runner
     */
    public Runner setOptions(RunOptions options) {
      this.runOptions = options;
      return this;
    }

    /** True if there are no targets or fetches. */
    public boolean isEmpty() {
      return targets.isEmpty() && outputs.isEmpty();
    }

    private void doInit() {
      if (autoInit) {
        initialize();
      } else {
        graph
            .initializers()
            .forEach(
                x -> {
                  if (!ranInits.contains(x))
                    throw new IllegalStateException(
                        "Graph has un-ran initializers, but the session's autoInit is false.  Run Session.initialize() before calling run().");
                });
      }
    }

    /**
     * Execute the graph fragments necessary to compute all requested fetches.
     *
     * <p><b>WARNING:</b> The caller assumes ownership of all returned {@link Tensor Tensors}, i.e.,
     * the caller must call {@link Tensor#close} on all elements of the returned list to free up
     * resources.
     *
     * <p>TODO(ashankar): Reconsider the return type here. Two things in particular: (a) Make it
     * easier for the caller to cleanup (perhaps returning something like AutoCloseableList in
     * SessionTest.java), and (b) Evaluate whether the return value should be a list, or maybe a
     * {@code Map<Output, Tensor>}?
     *
     * <p>TODO(andrewmyers): It would also be good if whatever is returned here made it easier to
     * extract output tensors in a type-safe way.
     *
     * @return list of resulting tensors fetched by this session runner
     */
    public Result run() {
      doInit();
      return runNoInit();
    }

    Result runNoInit() {
      return runHelper(false);
    }

    /**
     * Execute graph fragments to compute requested fetches and return metadata about the run.
     *
     * <p>This is exactly like {@link #run()}, but in addition to the requested Tensors, also
     * returns metadata about the graph execution in the form of a <a
     * href="https://www.tensorflow.org/code/tensorflow/core/protobuf/config.proto">RunMetadata
     * protocol buffer</a>.
     *
     * @return list of resulting tensors fetched by this session runner, with execution metadata
     */
    public Result runAndFetchMetadata() {
      doInit();
      return runHelper(true);
    }

    private Result runHelper(boolean wantMetadata) {
      TF_Tensor[] inputTensorHandles = new TF_Tensor[inputTensors.size()];
      TF_Operation[] inputOpHandles = new TF_Operation[inputs.size()];
      int[] inputOpIndices = new int[inputs.size()];
      TF_Operation[] outputOpHandles = new TF_Operation[outputs.size()];
      int[] outputOpIndices = new int[outputs.size()];
      TF_Operation[] targetOpHandles = new TF_Operation[targets.size()];

      // It's okay to use Operation.getUnsafeNativeHandle() here since the safety depends on the
      // validity of the Graph and graphRef ensures that.
      int idx = 0;
      for (Tensor t : inputTensors) {
        inputTensorHandles[idx++] = t.asRawTensor().nativeHandle();
      }
      idx = 0;
      for (Output<?> o : inputs) {
        inputOpHandles[idx] = (TF_Operation) o.getUnsafeNativeHandle();
        inputOpIndices[idx] = o.index();
        idx++;
      }
      idx = 0;
      for (Output<?> o : outputs) {
        outputOpHandles[idx] = (TF_Operation) o.getUnsafeNativeHandle();
        outputOpIndices[idx] = o.index();
        idx++;
      }
      idx = 0;
      for (GraphOperation op : targets) {
        targetOpHandles[idx++] = op.getUnsafeNativeHandle();
      }
      Reference runRef = new Reference();
      RunMetadata metadata = null;
      List<Tensor> outputs = new ArrayList<>();
      try {
        metadata =
            Session.run(
                nativeHandle,
                runOptions,
                inputTensorHandles,
                inputOpHandles,
                inputOpIndices,
                outputOpHandles,
                outputOpIndices,
                targetOpHandles,
                wantMetadata,
                outputs);
      } catch (Exception e) {
        for (Tensor t : outputs) {
          t.close();
        }
        outputs.clear();
        throw e;
      } finally {
        runRef.close();
      }
      return new Result(outputNames, outputs, metadata);
    }

    private class Reference implements AutoCloseable {

      public Reference() {
        synchronized (nativeHandleLock) {
          if (nativeHandle == null || nativeHandle.isNull()) {
            throw new IllegalStateException("run() cannot be called on the Session after close()");
          }
          ++numActiveRuns;
        }
      }

      @Override
      public void close() {
        synchronized (nativeHandleLock) {
          if (nativeHandle == null || nativeHandle.isNull()) {
            return;
          }
          if (--numActiveRuns == 0) {
            nativeHandleLock.notifyAll();
          }
        }
      }
    }

    private final ArrayList<Output<?>> inputs = new ArrayList<>();
    private final ArrayList<Tensor> inputTensors = new ArrayList<>();
    private final ArrayList<Output<?>> outputs = new ArrayList<>();
    private final ArrayList<String> outputNames = new ArrayList<>();
    private final ArrayList<GraphOperation> targets = new ArrayList<>();
    private RunOptions runOptions = null;
  }

  /** Create a Runner to execute graph operations and evaluate Tensors. */
  public Runner runner() {
    return new Runner();
  }

  /**
   * Executes an operation in the graph with the given name.
   *
   * <p>This method is equivalent to {@code session.runner().addTarget(opName).run()}.
   *
   * @param opName name of the operation to run.
   * @throws IllegalArgumentException if no operation of that name can be found in the graph
   */
  public void run(String opName) {
    runner().addTarget(opName).run();
  }

  /**
   * Executes an operation in the graph.
   *
   * <p>This method is equivalent to {@code session.runner().addTarget(op).run()}.
   *
   * @param op the operation to run.
   */
  public void run(Op op) {
    runner().addTarget(op.op()).run();
  }

  /**
   * Create a new session function, backed by this session.
   *
   * @param signature the signature of the function.
   */
  public SessionFunction function(Signature signature) {
    return new SessionFunction(signature, this);
  }

  /**
   * Create and call a function, returning the results.
   *
   * @param signature the signature of the function
   * @param arguments the arguments to call with.
   * @return The results of the function call.
   */
  public Result run(Signature signature, Map<String, Tensor> arguments) {
    return function(signature).call(arguments);
  }

  /**
   * Saves the actual state of the variables of this session's graph.
   *
   * <p>{@code prefix} is a path where the files containing the variables state will be saved,
   * followed by a prefix for naming these files. For example, if {@code prefix} is set to
   * <i>mymodel/myvariables/variables</i>, then the generated files will be located under
   * <i>mymodel/myvariables</i> and named <i>variables.data-*-of-*</i>
   *
   * <p>Note that this method might alter the underlying graph if it is the first time that one of
   * its sessions is saved, see {@link Graph#saverDef()} for more details.
   *
   * @param prefix prefix to the variable files to save
   */
  public void save(String prefix) {
    SaverDef saverDef = graph.saverDef();
    runner()
        .addTarget(saverDef.getSaveTensorName())
        .feed(saverDef.getFilenameTensorName(), TString.scalarOf(prefix))
        .run();
  }

  /**
   * Restore the actual state of the variables of this session's graph. Counts as initialization,
   * but can be done after other initializations.
   *
   * <p>{@code prefix} is the path where the files containing the variables state live, followed by
   * the filename prefix. For example, if {@code prefix} is set to
   * <i>mymodel/myvariables/variables</i>, then the files are loaded from <i>mymodel/myvariables</i>
   * and named <i>variables.data-*-of-*</i>
   *
   * <p>Note that this method might alter the underlying graph if it is the first time that one of
   * its sessions is saved, see {@link Graph#saverDef()} for more details.
   *
   * @param prefix prefix to restore from
   */
  public void restore(String prefix) {
    SaverDef saverDef = graph.saverDef();
    runner()
        .addTarget(saverDef.getRestoreOpName())
        .feed(saverDef.getFilenameTensorName(), TString.scalarOf(prefix))
        .runNoInit();
    // TODO better way of doing this, only count as ran assignments to the restored variables.
    setInitialized();
  }

  Graph graph() {
    return graph;
  }

  private final Graph graph;
  private final Graph.Reference graphRef;

  private final Object nativeHandleLock = new Object();
  private TF_Session nativeHandle;
  private int numActiveRuns;

  private final boolean autoInit;
  private final Set<Operation> ranInits = Collections.synchronizedSet(new LinkedHashSet<>());

  private static void requireHandle(Pointer handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("close() has been called on the Session");
    }
  }

  private static void resolveHandles(String type, Pointer[] src, PointerPointer dst, int n) {
    if (src.length != n) {
      throw new IllegalArgumentException("expected " + n + ", got " + src.length + " " + type);
    }
    for (int i = 0; i < n; ++i) {
      if (src[i] == null || src[i].isNull()) {
        throw new IllegalStateException("invalid " + type + " (#" + i + " of " + n + ")");
      }
      dst.put(i, src[i]);
    }
  }

  private static TF_Session allocate(TF_Graph graphHandle, String target, ConfigProto config) {
    if (graphHandle == null || graphHandle.isNull()) {
      throw new IllegalStateException("Graph has been close()d");
    }
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TF_SessionOptions opts = TF_SessionOptions.newSessionOptions();
      if (config != null) {
        BytePointer configBytes = new BytePointer(config.toByteArray());
        TF_SetConfig(opts, configBytes, configBytes.capacity(), status);
        status.throwExceptionIfNotOK();
      }

      TF_Session session = TF_Session.newSession(graphHandle, opts, status);
      status.throwExceptionIfNotOK();

      return session.retainReference();
    }
  }

  private static void delete(TF_Session handle) {
    requireHandle(handle);
    handle.releaseReference();
  }

  /**
   * Execute a session.
   *
   * <p>The author apologizes for the ugliness of the long argument list of this method. However,
   * take solace in the fact that this is a private method meant to cross the JNI boundary.
   *
   * @param handle to the C API TF_Session object (Session.nativeHandle)
   * @param runOptions A RunOptions protocol buffer, or null
   * @param inputTensorHandles together with inputOpHandles and inputOpIndices specifies the values
   *     that are being "fed" (do not need to be computed) during graph execution.
   *     inputTensorHandles[i] (which corresponds to a Tensor.nativeHandle) is considered to be the
   *     inputOpIndices[i]-th output of the Operation inputOpHandles[i]. Thus, it is required that
   *     inputOpHandles.length == inputOpIndices.length == inputTensorHandles.length.
   * @param inputOpHandles (see inputOpIndices)
   * @param inputOpIndices (see inputTensorHandles)
   * @param outputOpHandles (see outputOpIndices)
   * @param outputOpIndices together with outputOpHandles identifies the set of values that should
   *     be computed. The outputOpIndices[i]-th output of the Operation outputOpHandles[i], It is
   *     required that outputOpHandles.length == outputOpIndices.length.
   * @param targetOpHandles is the set of Operations in the graph that are to be executed but whose
   *     output will not be returned
   * @param wantRunMetadata indicates whether metadata about this execution should be returned.
   * @param outputTensors will be filled in with tensors to the outputs requested. It is required
   *     that outputs.length == outputOpHandles.length.
   * @return if wantRunMetadata is true, a RunMetadata protocol buffer, false otherwise.
   */
  private static RunMetadata run(
      TF_Session handle,
      RunOptions runOptions,
      TF_Tensor[] inputTensorHandles,
      TF_Operation[] inputOpHandles,
      int[] inputOpIndices,
      TF_Operation[] outputOpHandles,
      int[] outputOpIndices,
      TF_Operation[] targetOpHandles,
      boolean wantRunMetadata,
      List<Tensor> outputTensors) {
    requireHandle(handle);

    int ninputs = inputTensorHandles.length;
    int noutputs = outputOpHandles.length;
    int ntargets = targetOpHandles.length;

    try (PointerScope scope = new PointerScope()) {
      TF_Output inputs = new TF_Output(ninputs);
      PointerPointer<TF_Tensor> inputValues = new PointerPointer<TF_Tensor>(ninputs);
      TF_Output outputs = new TF_Output(noutputs);
      PointerPointer<TF_Tensor> outputValues = new PointerPointer<TF_Tensor>(noutputs);
      PointerPointer<TF_Operation> targets = new PointerPointer<TF_Operation>(ntargets);
      TF_Buffer runMetadata = wantRunMetadata ? TF_Buffer.newBuffer() : null;

      resolveHandles("input Tensors", inputTensorHandles, inputValues, ninputs);
      resolveOutputs("input", inputOpHandles, inputOpIndices, inputs, ninputs);
      resolveOutputs("output", outputOpHandles, outputOpIndices, outputs, noutputs);
      resolveHandles("target Operations", targetOpHandles, targets, ntargets);

      TF_Status status = TF_Status.newStatus();
      TF_Buffer runOpts = TF_Buffer.newBufferFromString(runOptions);

      TF_SessionRun(
          handle,
          runOpts,
          inputs,
          inputValues,
          ninputs,
          outputs,
          outputValues,
          noutputs,
          targets,
          ntargets,
          runMetadata,
          status);
      status.throwExceptionIfNotOK();

      for (int i = 0; i < noutputs; ++i) {
        TF_Tensor h = outputValues.get(TF_Tensor.class, i).withDeallocator();
        outputTensors.add(RawTensor.fromHandle(h).asTypedTensor());
      }
      try {
        return runMetadata != null ? RunMetadata.parseFrom(runMetadata.dataAsByteBuffer()) : null;
      } catch (InvalidProtocolBufferException e) {
        throw new TensorFlowException("Cannot parse RunMetadata protocol buffer", e);
      }
    }
  }
}
