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

import java.util.ArrayList;
import java.util.List;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Graph;
import org.tensorflow.internal.c_api.TF_Operation;
import org.tensorflow.internal.c_api.TF_Output;
import org.tensorflow.internal.c_api.TF_Session;
import org.tensorflow.internal.c_api.TF_SessionOptions;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.op.Op;
import org.tensorflow.op.core.Init;

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

  /** Construct a new session with the associated {@link Graph}. */
  public Session(Graph g) {
    this(g, (byte[])null);
  }

  /**
   * Construct a new session with the associated {@link Graph} and configuration options.
   *
   * @param g The {@link Graph} the created Session will operate on.
   * @param config Configuration parameters for the session specified as a serialized <a
   *     href="https://www.tensorflow.org/code/tensorflow/core/protobuf/config.proto">ConfigProto</a>
   *     protocol buffer.
   * @throws IllegalArgumentException if the config is not a valid serialization of the ConfigProto
   *     protocol buffer.
   */
  public Session(Graph g, byte[] config) {
    graph = g;
    Graph.Reference r = g.ref();
    try {
      nativeHandle =
          (config == null) ? allocate(r.nativeHandle()) : allocate2(r.nativeHandle(), null, config);
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
     */
    public Runner feed(String operation, Tensor<?> t) {
      return feed(parseOutput(operation), t);
    }

    /**
     * Avoid evaluating the {@code index}-th output of {@code operation} by substituting {@code t}
     * for the value it produces.
     *
     * <p>Operations in a {@link Graph} can have multiple outputs, {@code index} identifies which
     * one {@code t} is being provided for.
     */
    public Runner feed(String operation, int index, Tensor<?> t) {
      Operation op = operationByName(operation);
      if (op != null) {
        inputs.add(op.output(index));
        inputTensors.add(t);
      }
      return this;
    }

    /**
     * Use {@code t} instead of the Tensor referred to by executing the operation referred to by
     * {@code operand}.
     */
    public Runner feed(Operand<?> operand, Tensor<?> t) {
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
     */
    public Runner fetch(String operation) {
      return fetch(parseOutput(operation));
    }

    /**
     * Make {@link #run()} return the {@code index}-th output of {@code operation}.
     *
     * <p>Operations in a {@link Graph} can have multiple outputs, {@code index} identifies which
     * one to return.
     */
    public Runner fetch(String operation, int index) {
      Operation op = operationByName(operation);
      if (op != null) {
        outputs.add(op.output(index));
      }
      return this;
    }

    /** 
     * Makes {@link #run()} return the Tensor referred to by {@code output}. 
     */
    public Runner fetch(Output<?> output) {
      outputs.add(output);
      return this;
    }
    
    /**
     * Makes {@link #run()} return the Tensor referred to by the output of {@code operand}. 
     */
    public Runner fetch(Operand<?> operand) {
      return fetch(operand.asOutput());
    }

    /**
     * Make {@link #run()} execute {@code operation}, but not return any evaluated {@link Tensor
     * Tensors}.
     */
    public Runner addTarget(String operation) {
      GraphOperation op = operationByName(operation);
      if (op != null) {
        targets.add(op);
      }
      return this;
    }

    /**
     * Make {@link #run()} execute {@code operation}, but not return any evaluated {@link Tensor
     * Tensors}.
     *
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
     * Make {@link #run} execute {@code operand}, but not return any evaluated {@link Tensor
     * Tensors}.
     */
    public Runner addTarget(Operand<?> operand) {
      return addTarget(operand.asOutput().op());
    }

    /**
     * (Experimental method): set options (typically for debugging) for this run.
     *
     * <p>The options are presented as a serialized <a
     * href="https://www.tensorflow.org/code/tensorflow/core/protobuf/config.proto">RunOptions
     * protocol buffer</a>.
     *
     * <p>The org.tensorflow package is free of any protocol buffer dependencies in order to remain
     * friendly to resource constrained systems (where something like <a
     * href="https://github.com/google/protobuf/tree/master/javanano#nano-version">nanoproto</a> may
     * be more appropriate). A cost of that is this lack of type-safety in this API function. This
     * choice is under review and this function may be replaced by more type-safe equivalents at any
     * time.
     */
    public Runner setOptions(byte[] options) {
      this.runOptions = options;
      return this;
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
     */
    public List<Tensor<?>> run() {
      return runHelper(false).outputs;
    }

    /**
     * Execute graph fragments to compute requested fetches and return metadata about the run.
     *
     * <p>This is exactly like {@link #run()}, but in addition to the requested Tensors, also
     * returns metadata about the graph execution in the form of a serialized <a
     * href="https://www.tensorflow.org/code/tensorflow/core/protobuf/config.proto">RunMetadata
     * protocol buffer</a>.
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
      TF_Tensor[] outputTensorHandles = new TF_Tensor[outputs.size()];

      // It's okay to use Operation.getUnsafeNativeHandle() here since the safety depends on the
      // validity of the Graph and graphRef ensures that.
      int idx = 0;
      for (Tensor<?> t : inputTensors) {
        inputTensorHandles[idx++] = t.nativeHandle();
      }
      idx = 0;
      for (Output<?> o : inputs) {
        inputOpHandles[idx] = (TF_Operation)o.getUnsafeNativeHandle();
        inputOpIndices[idx] = o.index();
        idx++;
      }
      idx = 0;
      for (Output<?> o : outputs) {
        outputOpHandles[idx] = (TF_Operation)o.getUnsafeNativeHandle();
        outputOpIndices[idx] = o.index();
        idx++;
      }
      idx = 0;
      for (GraphOperation op : targets) {
        targetOpHandles[idx++] = op.getUnsafeNativeHandle();
      }
      Reference runRef = new Reference();
      byte[] metadata = null;
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
                outputTensorHandles);
      } finally {
        runRef.close();
      }
      List<Tensor<?>> outputs = new ArrayList<>();
      for (TF_Tensor h : outputTensorHandles) {
        try {
          outputs.add(Tensor.fromHandle(h));
        } catch (Exception e) {
          for (Tensor<?> t : outputs) {
            t.close();
          }
          outputs.clear();
          throw e;
        }
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

    private GraphOperation operationByName(String opName) {
      GraphOperation op = graph.operation(opName);
      if (op == null) {
        throw new IllegalArgumentException("No Operation named [" + opName + "] in the Graph");
      }
      return op;
    }

    @SuppressWarnings("rawtypes")
    private Output<?> parseOutput(String opName) {
      int colon = opName.lastIndexOf(':');
      if (colon == -1 || colon == opName.length() - 1) {
        return new Output(operationByName(opName), 0);
      }
      try {
        String op = opName.substring(0, colon);
        int index = Integer.parseInt(opName.substring(colon + 1));
        return new Output(operationByName(op), index);
      } catch (NumberFormatException e) {
        return new Output(operationByName(opName), 0);
      }
    }

    private ArrayList<Output<?>> inputs = new ArrayList<>();
    private ArrayList<Tensor<?>> inputTensors = new ArrayList<>();
    private ArrayList<Output<?>> outputs = new ArrayList<>();
    private ArrayList<GraphOperation> targets = new ArrayList<>();
    private byte[] runOptions = null;
  }

  /** Create a Runner to execute graph operations and evaluate Tensors. */
  public Runner runner() {
    return new Runner();
  }

  /**
   * Run all graph initializers.
   *
   * <p>Initializers must be executed once before running the graph in a training
   * loop using a session {@link Runner}.</p>
   *
   * <p>This method invokes {@link #runInit(String)} using the default name for the init
   * operation, which is {@link Init#DEFAULT_NAME}. For example:</p>
   * <pre>{@code
   * try (Graph g = new Graph()) {
   *   Ops tf = Ops.create(g);
   *
   *   Variable<TInt32> x = tf.variable(tf.constant(10));
   *   Variable<TInt32> y = tf.variable(tf.constant(20));
   *   Add<TInt32> z = tf.math.add(x, y);
   *   tf.init();
   *
   *   try (Session s = new Session(g)) {
   *     s.runInit();
   *
   *     try (Tensor<TInt32> t = s.runner().fetch(z).run().get(0).expect(TInt32.DTYPE)) {
   *       assertEquals(30, t.data().getInt());
   *     }
   *   }
   * }
   * }</pre>
   */
  public void runInit() {
    runInit(Init.DEFAULT_NAME);
  }

  /**
   * Run all graph initializers grouped under the {@code initOpName} operation.
   *
   * <p>Initializers must be executed once before running the graph in a training loop using a
   * session {@link Runner}.</p>
   *
   * <p>The {@code initOpName} is the name of a single operation already added to the
   * graph that executes all graph initializers at once. For example:</p>
   * <pre>{@code
   * try (Graph g = new Graph()) {
   *   Ops tf = Ops.create(g);
   *
   *   Variable<TInt32> x = tf.variable(tf.constant(10));
   *   Variable<TInt32> y = tf.variable(tf.constant(20));
   *   Add<TInt32> z = tf.math.add(x, y);
   *   tf.withName("initialize").init();
   *
   *   try (Session s = new Session(g)) {
   *     s.runInit("initialize");
   *
   *     try (Tensor<TInt32> t = s.runner().fetch(z).run().get(0).expect(TInt32.DTYPE)) {
   *       assertEquals(30, t.data().getInt());
   *     }
   *   }
   * }
   * }</pre>
   *
   * @param initOpName name of the initializer operation.
   */
  public void runInit(String initOpName) {
    Operation operation = graph.operation(initOpName);
    if (operation == null) {
      throw new IllegalArgumentException("Initializer operation named '"
          + initOpName + "' cannot be found in the graph");
    }
    runner().addTarget(operation).run();
  }

  /**
   * Run all graph initializers registered by the given {@code init} op.
   *
   * <p>Initializers must be executed once before running the graph in a training
   * loop using a session {@link Runner}.</p>
   *
   * <p>This method can be used if the graph is being built by the same program running the session.
   * For example:</p>
   * <pre>{@code
   * try (Graph g = new Graph()) {
   *   Ops tf = Ops.create(g);
   *
   *   Variable<TInt32> x = tf.variable(tf.constant(10));
   *   Variable<TInt32> y = tf.variable(tf.constant(20));
   *   Add<TInt32> z = tf.math.add(x, y);
   *   Init init = tf.withName("initialize").init();
   *
   *   try (Session s = new Session(g)) {
   *     s.runInit(init);
   *
   *     try (Tensor<TInt32> t = s.runner().fetch(z).run().get(0).expect(TInt32.DTYPE)) {
   *       assertEquals(30, t.data().getInt());
   *     }
   *   }
   * }
   * }</pre>
   */
  public void runInit(Init initOp) {
    runner().addTarget(initOp.op()).run();
  }

  /**
   * Output tensors and metadata obtained when executing a session.
   *
   * <p>See {@link Runner#runAndFetchMetadata()}
   */
  public static final class Run {
    /** Tensors from requested fetches. */
    public List<Tensor<?>> outputs;

    /**
     * (Experimental): Metadata about the run.
     *
     * <p>A serialized <a
     * href="https://www.tensorflow.org/code/tensorflow/core/protobuf/config.proto">RunMetadata
     * protocol buffer</a>. The org.tensorflow package is free of any protocol buffer dependencies
     * in order to remain friendly to resource constrained systems (where something like <a
     * href="https://github.com/google/protobuf/tree/master/javanano#nano-version">nanoproto</a> may
     * be more appropriate). A cost of that is this opaque blob. This choice is under review and
     * this field may be replaced by more type-safe equivalents at any time.
     */
    public byte[] metadata;
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

  // TODO(ashankar): Remove after TensorFlow 1.2 has been released with allocate2().
  private static TF_Session allocate(TF_Graph graphHandle) {
    return allocate2(graphHandle, null, null);
  }

  private static TF_Session allocate2(TF_Graph graphHandle, String target, byte[] config) {
    if (graphHandle == null || graphHandle.isNull()) {
      throw new IllegalStateException("Graph has been close()d");
    }

    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TF_SessionOptions opts = TF_SessionOptions.newSessionOptions();
      if (config != null && config.length > 0) {
        TF_SetConfig(opts, new BytePointer(config), config.length, status);
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
   * @param runOptions serialized representation of a RunOptions protocol buffer, or null
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
   * @param outputTensorHandles will be filled in with handles to the outputs requested. It is
   *     required that outputTensorHandles.length == outputOpHandles.length.
   * @return if wantRunMetadata is true, serialized representation of the RunMetadata protocol
   *     buffer, false otherwise.
   */
  private static byte[] run(
      TF_Session handle,
      byte[] runOptions,
      TF_Tensor[] inputTensorHandles,
      TF_Operation[] inputOpHandles,
      int[] inputOpIndices,
      TF_Operation[] outputOpHandles,
      int[] outputOpIndices,
      TF_Operation[] targetOpHandles,
      boolean wantRunMetadata,
      TF_Tensor[] outputTensorHandles) {
    requireHandle(handle);

    int ninputs = inputTensorHandles.length;
    int noutputs = outputTensorHandles.length;
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

      TF_SessionRun(handle, runOpts, inputs, inputValues, ninputs,
                    outputs, outputValues, noutputs, targets, ntargets,
                    runMetadata, status);
      status.throwExceptionIfNotOK();

      for (int i = 0; i < noutputs; ++i) {
        outputTensorHandles[i] = outputValues.get(TF_Tensor.class, i);
      }

      return runMetadata != null ? runMetadata.get() : null;
    }
  }
}
