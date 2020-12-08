/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow;

import static org.tensorflow.Graph.resolveOutputs;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_CloseSession;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteSession;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewSession;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SessionRun;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetConfig;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
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
import org.tensorflow.op.Op;
import org.tensorflow.proto.framework.ConfigProto;
import org.tensorflow.proto.framework.RunMetadata;
import org.tensorflow.proto.framework.RunOptions;
import org.tensorflow.proto.util.SaverDef;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

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
 * <p>Instances of a Session are thread-safe.
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
    graph = g;
    Graph.Reference r = g.ref();
    try {
      nativeHandle = allocate(r.nativeHandle(), null, config);
      graphRef = g.ref();
    } finally {
      r.close();
    }
  }

  /** Wrap an existing session with the associated {@link Graph}. */
  Session(Graph g, TF_Session nativeHandle) {
    graph = g;
    this.nativeHandle = nativeHandle;
    graphRef = g.ref();
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
   * Run {@link Operation}s and evaluate {@link Tensor Tensors}.
   *
   * <p>A Runner runs the necessary graph fragments to execute every {@link Operation} required to
   * evaluate the {@link Tensor Tensors} to fetch. The {@link #feed(String,int,Tensor)} call allows
   * callers to override the value of {@link Tensor Tensors} in the graph by substituting the
   * provided {@link Tensor Tensors} for the outputs of the operations provided to {@link
   * #feed(String,int,Tensor)}.
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
     */
    public Runner feed(String operation, Tensor t) {
      return feed(graph.getOutput(operation), t);
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
     */
    public Runner feed(String operation, int index, Tensor t) {
      Operation op = graph.operationOrError(operation);
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
      inputs.add(operand.asOutput());
      inputTensors.add(t);
      return this;
    }

    /**
     * Make {@link #run()} return the output of {@code operation}.
     *
     * @param operation Is either the string name of the operation, in which case this method is a
     *     shorthand for {@code fetch(operation, 0)}, or it is a string of the form
     *     <tt>operation_name:output_index</tt> , in which case this method acts like {@code
     *     fetch(operation_name, output_index)}. These colon-separated names are commonly used in
     *     the {@code SignatureDef} protocol buffer messages that are included in {@link
     *     SavedModelBundle#metaGraphDef()}.
     * @return this session runner
     * @see Graph#getOutput(String, int)
     */
    public Runner fetch(String operation) {
      return fetch(graph.getOutput(operation));
    }

    /**
     * Make {@link #run()} return the {@code index}-th output of {@code operation}.
     *
     * <p>Operations in a {@link Graph} can have multiple outputs, {@code index} identifies which
     * one to return.
     *
     * @param operation the string name of the operation
     * @return this session runner
     * @see Graph#getOutput(String, int)
     */
    public Runner fetch(String operation, int index) {
      Operation op = graph.operationOrError(operation);
      outputs.add(op.output(index));
      return this;
    }

    /**
     * Makes {@link #run()} return the Tensor referred to by {@code output}.
     *
     * @param output the node to fetch the tensor from
     * @return this session runner
     */
    public Runner fetch(Output<?> output) {
      outputs.add(output);
      return this;
    }

    /**
     * Makes {@link #run()} return the Tensor referred to by the output of {@code operand}.
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
     * @param operation the string name of the operation to execute
     * @return this session runner
     */
    public Runner addTarget(String operation) {
      GraphOperation op = graph.operationOrError(operation);
      targets.add(op);
      return this;
    }

    /**
     * Make {@link #run()} execute {@code operation}, but not return any evaluated {@link Tensor
     * Tensors}.
     *
     * @param operation the operation to execute
     * @return this session runner
     * @throws IllegalArgumentException if the operation is not a {@link GraphOperation}
     */
    public Runner addTarget(Operation operation) {
      if (!(operation instanceof GraphOperation)) {
        throw new IllegalArgumentException(
            "Operation of type "
                + operation.getClass().getName()
                + " is not supported in graph sessions");
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

    /**
     * The result of a run in a session.  Contains the fetched tensors and the outputs that were fetched.
     * <p>
     * Closing a {@code Result} object will close all of the tensors contained by it.
     */
    public final class Result implements AutoCloseable, Iterable<Tensor<?>>{
      private final List<Tensor<?>> results;
      private final List<Output<?>> fetches;
      private final LinkedHashMap<Output<?>, Integer> indexMap;

      private Result(List<Tensor<?>> results, List<Output<?>> fetches) {
        this.results = new ArrayList<>(results);
        this.fetches = new ArrayList<>(fetches);
        indexMap = new LinkedHashMap<>();
        for(int i = 0 ; i < fetches.size() ; i++){
          indexMap.put(fetches.get(i), i);
        }
      }

      /**
       * Get the result tensors.
       */
      public List<Tensor<?>> getResults() {
        return results;
      }

      /**
       * Get the outputs that were fetched.
       */
      public List<Output<?>> getFetches() {
        return fetches;
      }

      /**
       * Get the result at {@code index}.
       */
      public Tensor<?> get(int index){
        return results.get(index);
      }

      /**
       * Get the result for {@code output} or throw an {@code IllegalArgumentException} if it wasn't fetched.
       */
      @SuppressWarnings("unchecked")
      public <T extends TType> Tensor<T> get(Output<T> output){
        if(!indexMap.containsKey(output))
          throw new IllegalArgumentException("Did not fetch an output for " + output);
        return (Tensor<T>) results.get(indexMap.get(output));
      }

      /**
       * Get the result for {@code operand} or throw an {@code IllegalArgumentException} if it wasn't fetched.
       */
      public <T extends TType> Tensor<T> get(Operand<T> operand){
        return get(operand.asOutput());
      }

      /**
       * Get the result for the {@code index}-th output of {@code operation} or throw an {@code IllegalArgumentException} if it wasn't fetched.
       */
      public Tensor<?> get(String operation, int index){
        return get(graph.getOutput(operation, index));
      }


      /**
       * Get the result for the output specified by {@code output} or throw an {@code IllegalArgumentException} if it wasn't fetched.
       */
      public Tensor<?> get(String output){
        return get(graph.getOutput(output));
      }

      /**
       * Close all of the tensors contained by this {@code Result}.
       */
      @Override
      public void close() throws Exception {
        for(Tensor<?> t : this){
          t.close();
        }
      }

      @Override
      public Iterator<Tensor<?>> iterator() {
        return results.iterator();
      }

      @Override
      public void forEach(Consumer<? super Tensor<?>> action) {
        results.forEach(action);
      }

      @Override
      public Spliterator<Tensor<?>> spliterator() {
        return results.spliterator();
      }
    }

    /**
     * Execute the graph fragments necessary to compute all requested fetches.
     *
     * <p><b>WARNING:</b> The caller assumes ownership of all returned {@link Tensor Tensors}, i.e.,
     * the caller must call {@link Tensor#close} on all returned tensors or {@link Result#close()} to free up
     * resources.
     *
     * @return a {@link Result} containing tensors fetched by this session runner
     */
    public Result run() {
      return new Result(runHelper(false).outputs, outputs);
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
    public Run runAndFetchMetadata() {
      return runHelper(true);
    }

    private Run runHelper(boolean wantMetadata) {
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
      Run ret = new Run();
      ret.outputs = outputs;
      ret.metadata = metadata;
      return ret;
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

    private ArrayList<Output<?>> inputs = new ArrayList<>();
    private ArrayList<Tensor> inputTensors = new ArrayList<>();
    private ArrayList<Output<?>> outputs = new ArrayList<>();
    private ArrayList<GraphOperation> targets = new ArrayList<>();
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
    Operation operation = graph.operation(opName);
    if (operation == null) {
      throw new IllegalArgumentException(
          "Operation named '" + opName + "' cannot be found in the graph");
    }
    runner().addTarget(operation).run();
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
   * Saves the actual state of the variables of this session's graph.
   *
   * <p>{@code prefix} is a path where the files containing the variables state will be saved,
   * followed by a prefix for naming these files. For example, if {@code prefix} is set to
   * <i>mymodel/myvariables/variables</i>, then the generated files will be located under
   * <i>mymodel/myvariables</i> and named <i>variables.data-*-of-*</i>
   *
   * <p>Note that this method might alter the underlying graph if it is the first time that one
   * of its session is saved, see {@link Graph#saverDef()} for more details.
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
   * Output tensors and metadata obtained when executing a session.
   *
   * <p>See {@link Runner#runAndFetchMetadata()}
   */
  public static final class Run {
    /** Tensors from requested fetches. */
    public List<Tensor> outputs;

    /**
     * Metadata about the run.
     *
     * <p>A <a
     * href="https://www.tensorflow.org/code/tensorflow/core/protobuf/config.proto">RunMetadata
     * protocol buffer</a>.
     */
    public RunMetadata metadata;
  }

  Graph graph() {
    return graph;
  }

  private final Graph graph;
  private final Graph.Reference graphRef;

  private final Object nativeHandleLock = new Object();
  private TF_Session nativeHandle;
  private int numActiveRuns;

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

      TF_Session session = TF_NewSession(graphHandle, opts, status);
      status.throwExceptionIfNotOK();

      return session;
    }
  }

  private static void delete(TF_Session handle) {
    requireHandle(handle);

    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TF_CloseSession(handle, status);
      // Result of close is ignored, delete anyway.
      TF_DeleteSession(handle, status);
      status.throwExceptionIfNotOK();
    }
  }

  /**
   * Execute a session.
   *
   * <p>The author apologizes for the ugliness of the long argument list of this method. However,
   * take solace in the fact that this is a private method meant to cross the JNI boundary.
   *
   * @param handle to the C API TF_Session object (Session.nativeHandle)
   * @param runOptions A RunOptions protocol buffer, or null
   * @param inputOpHandles (see inputOpIndices)
   * @param inputOpIndices (see inputTensorHandles)
   * @param inputTensorHandles together with inputOpHandles and inputOpIndices specifies the values
   *     that are being "fed" (do not need to be computed) during graph execution.
   *     inputTensorHandles[i] (which corresponds to a Tensor.nativeHandle) is considered to be the
   *     inputOpIndices[i]-th output of the Operation inputOpHandles[i]. Thus, it is required that
   *     inputOpHandles.length == inputOpIndices.length == inputTensorHandles.length.
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
