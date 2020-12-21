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

import static org.tensorflow.internal.c_api.global.tensorflow.TF_AddGradientsWithPrefix;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteGraph;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_FinishWhile;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_GraphImportGraphDef;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_GraphNextOperation;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_GraphOperationByName;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_GraphToGraphDef;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_ImportGraphDefOptionsSetPrefix;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewGraph;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewWhile;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerScope;
import org.bytedeco.javacpp.SizeTPointer;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Graph;
import org.tensorflow.internal.c_api.TF_ImportGraphDefOptions;
import org.tensorflow.internal.c_api.TF_Operation;
import org.tensorflow.internal.c_api.TF_Output;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.internal.c_api.TF_WhileParams;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.NoOp;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.train.Restore;
import org.tensorflow.op.train.Save;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.proto.framework.GraphDef;
import org.tensorflow.proto.util.SaverDef;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;


/**
 * A data flow graph representing a TensorFlow computation.
 *
 * <p>Instances of a Graph are thread-safe.
 *
 * <p><b>WARNING:</b> Resources consumed by the Graph object must be explicitly freed by invoking
 * the {@link #close()} method then the Graph object is no longer needed.
 */
public final class Graph implements ExecutionEnvironment, AutoCloseable {

  /** Create an empty Graph. */
  public Graph() {
    nativeHandle = allocate();
  }

  /** Create a Graph from an existing handle (takes ownership). */
  Graph(TF_Graph nativeHandle) {
    this.nativeHandle = nativeHandle;
  }

  Graph(TF_Graph nativeHandle, SaverDef saverDef) {
    this(nativeHandle);
    this.saverDef = saverDef;
  }

  /**
   * Release resources associated with the Graph.
   *
   * <p>Blocks until there are no active {@link Session} instances referring to this Graph. A Graph
   * is not usable after close returns.
   */
  @Override
  public void close() {
    synchronized (nativeHandleLock) {
      if (nativeHandle == null || nativeHandle.isNull()) {
        return;
      }
      while (refcount > 0) {
        try {
          nativeHandleLock.wait();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          // Possible leak of the graph in this case?
          return;
        }
      }
      delete(nativeHandle);
      nativeHandle = null;
    }
  }

  /**
   * Returns the operation (node in the Graph) with the provided name.
   *
   * <p>Or {@code null} if no such operation exists in the Graph.
   *
   * @param name name of the operation to look for
   * @return operation in the graph with this name or null if not found
   */
  public GraphOperation operation(String name) {
    synchronized (nativeHandleLock) {
      TF_Operation oph = operation(nativeHandle, name);
      if (oph == null || oph.isNull()) {
        return null;
      }
      return new GraphOperation(this, oph);
    }
  }

  /**
   * Iterator over all the {@link Operation}s in the graph.
   *
   * <p>The order of iteration is unspecified. Consumers of the iterator will receive no
   * notification should the underlying graph change during iteration.
   */
  public Iterator<Operation> operations() {
    return new OperationIterator(this);
  }

  /**
   * Returns a builder to add {@link Operation}s to the Graph.
   *
   * @param type of the Operation (i.e., identifies the computation to be performed)
   * @param name to refer to the created Operation in the graph.
   * @return an {@link OperationBuilder}, which will add the Operation to the graph when {@link
   *     OperationBuilder#build()} is invoked. If {@link OperationBuilder#build()} is not invoked,
   *     then some resources may leak.
   */
  @Override
  public GraphOperationBuilder opBuilder(String type, String name) {
    return new GraphOperationBuilder(this, type, name);
  }

  @Override
  public Types environmentType() {
    return Types.GRAPH;
  }

  /**
   * Import a representation of a TensorFlow graph.
   *
   * <p>The representation of the graph, referred to as a {@code GraphDef}, can be
   * generated by {@link #toGraphDef()} and equivalents in other language APIs.
   *
   * @param graphDef {@code GraphDef} proto to import
   * @throws IllegalArgumentException if graphDef is not a recognized serialization of a graph.
   * @see #importGraphDef(GraphDef, String)
   */
  public void importGraphDef(GraphDef graphDef) throws IllegalArgumentException {
    importGraphDef(graphDef, "");
  }

  /**
   * Import a representation of a TensorFlow graph.
   *
   * @param graphDef the representation of a TensorFlow graph.
   * @param prefix a prefix that will be prepended to names in graphDef
   * @throws IllegalArgumentException if graphDef is not a recognized serialization of a graph.
   * @see #importGraphDef(GraphDef)
   */
  public void importGraphDef(GraphDef graphDef, String prefix) throws IllegalArgumentException {
    if (graphDef == null || prefix == null) {
      throw new IllegalArgumentException("graphDef and prefix cannot be null");
    }
    synchronized (nativeHandleLock) {
      importGraphDef(nativeHandle, graphDef, prefix);
    }
  }

  /**
   * Generate a representation of the Graph.
   *
   * @see #importGraphDef(GraphDef)
   * @see #importGraphDef(GraphDef, String)
   */
  public GraphDef toGraphDef() {
    synchronized (nativeHandleLock) {
      return toGraphDef(nativeHandle);
    }
  }

  /**
   * Adds an initializer to the graph initializer list.
   * @param initializer An initializer to add to the list.
   */
  public synchronized void addInitializer(Op initializer) {
    initializers.add(initializer);
  }

  /**
   * Returns all initializers added to the graph via {@link #addInitializer(Op)}
   */
  public List<Op> initializers() {
    return Collections.unmodifiableList(initializers);
  }

  /**
   * Adds operations to compute the partial derivatives of sum of {@code y}s w.r.t {@code x}s, i.e.,
   * {@code d(y_1 + y_2 + ...)/dx_1, d(y_1 + y_2 + ...)/dx_2...}
   *
   * <p>{@code dx} are used as initial gradients (which represent the symbolic partial derivatives
   * of some loss function {@code L} w.r.t. {@code y}). {@code dx} must be null or have size of
   * {@code y}.
   *
   * <p>If {@code dx} is null, the implementation will use dx of {@link
   * org.tensorflow.op.core.OnesLike OnesLike} for all shapes in {@code y}.
   *
   * <p>{@code prefix} is used as the name prefix applied to all nodes added to the graph to compute
   * gradients. It must be unique within the provided graph or the operation will fail.
   *
   * <p>If {@code prefix} is null, then one will be chosen automatically.
   *
   * @param prefix unique string prefix applied before the names of nodes added to the graph to
   *     compute gradients. If null, a default one will be chosen.
   * @param y output of the function to derive
   * @param x inputs of the function for which partial derivatives are computed
   * @param dx if not null, the partial derivatives of some loss function {@code L} w.r.t. {@code y}
   * @return the partial derivatives {@code dy} with the size of {@code x}
   */
  public Output<?>[] addGradients(String prefix, Output<?>[] y, Output<?>[] x, Output<?>[] dx) {
    Output<?>[] dy = new Output<?>[x.length];
    final TF_Operation[] yHandles = new TF_Operation[y.length];
    final int[] yIndices = new int[y.length];
    final TF_Operation[] xHandles = new TF_Operation[x.length];
    final int[] xIndices = new int[x.length];
    TF_Operation[] dxHandles = null;
    int[] dxIndices = null;

    try (Reference ref = ref()) {
      for (int i = 0; i < y.length; ++i) {
        yHandles[i] = (TF_Operation)y[i].getUnsafeNativeHandle();
        yIndices[i] = y[i].index();
      }
      for (int i = 0; i < x.length; ++i) {
        xHandles[i] = (TF_Operation)x[i].getUnsafeNativeHandle();
        xIndices[i] = x[i].index();
      }
      if (dx != null && dx.length > 0) {
        dxHandles = new TF_Operation[dx.length];
        dxIndices = new int[dx.length];

        for (int i = 0; i < dx.length; ++i) {
          dxHandles[i] = (TF_Operation)dx[i].getUnsafeNativeHandle();
          dxIndices[i] = dx[i].index();
        }
      }
      // Gradient outputs are returned in two continuous arrays concatenated into one. The first
      // holds the native handles of the gradient operations while the second holds the index of
      // their output e.g. given
      // xHandles = [x0Handle, x1Handle, ...] and xIndices = [x0Index, x1Index, ..], we obtain
      // dy = [dy0Handle, dy1Handle, ..., dy0Index, dy1Index, ...]
      Object[] dyHandlesAndIndices =
          addGradients(
              ref.nativeHandle(),
              prefix,
              yHandles,
              yIndices,
              xHandles,
              xIndices,
              dxHandles,
              dxIndices);
      int ndy = dyHandlesAndIndices.length >> 1;
      if (ndy != dy.length) {
        throw new IllegalStateException(String.valueOf(ndy) + " gradients were added to the graph when " + dy.length
            + " were expected");
      }
      for (int i = 0, j = ndy; i < ndy; ++i, ++j) {
        GraphOperation op = new GraphOperation(this, (TF_Operation)dyHandlesAndIndices[i]);
        dy[i] = new Output<>(op, (int) dyHandlesAndIndices[j]);
      }
    }
    return dy;
  }

  /**
   * Adds operations to compute the partial derivatives of sum of {@code y}s w.r.t {@code x}s,
   * i.e., {@code dy/dx_1, dy/dx_2...}
   * <p>
   * This is a simplified version of {@link #addGradients(String, Output[], Output[], Output[])}
   * where {@code y} is a single output, {@code dx} is null and {@code prefix} is null.
   *
   * @param y output of the function to derive
   * @param x inputs of the function for which partial derivatives are computed
   * @return the partial derivatives {@code dy} with the size of {@code x}
   */
  public Output<?>[] addGradients(Output<?> y, Output<?>[] x) {
    return addGradients(null, new Output<?>[] {y}, x, null);
  }

  /**
   * Used to instantiate an abstract class which overrides the buildSubgraph method to build a
   * conditional or body subgraph for a while loop. After Java 8, this can alternatively be used to
   * create a lambda for the same purpose.
   *
   * <p>To be used when calling {@link #whileLoop(Output[],
   * org.tensorflow.Graph.WhileSubgraphBuilder, org.tensorflow.Graph.WhileSubgraphBuilder, String)}
   *
   * <p>Example usage (prior to Java 8):
   *
   * <pre>
   * WhileSubgraphBuilder bodyGraphBuilder = new WhileSubgraphBuilder() {
   *   &#64;Override
   *   public void buildSubgraph(Graph bodyGraph, Output&lt;?&gt;[] bodyInputs, Output&lt;?&gt;[] bodyOutputs) { // build
   *     body subgraph
   *   }
   * };
   * </pre>
   * Example usage (after Java 8):
   * <pre>
   * WhileSubgraphBuilder bodyGraphBuilder = (bodyGraph, bodyInputs, bodyOutputs) -&gt; { //
   *   build body subgraph
   * };
   * </pre>
   */
  public interface WhileSubgraphBuilder {
    /**
     * To be overridden by user with code to build conditional or body subgraph for a while loop
     *
     * @param g the subgraph
     * @param inputs subgraph inputs
     * @param outputs subgraph outputs
     */
    public void buildSubgraph(Graph g, Output<?>[] inputs, Output<?>[] outputs);
  }

  // called by while loop code in graph_jni.cc to construct conditional/body subgraphs
  private static Object[] buildSubgraph(
      WhileSubgraphBuilder subgraphBuilder,
      TF_Graph subgraphHandle,
      TF_Operation[] inputHandles,
      int[] inputIndices,
      TF_Operation[] outputHandles,
      int[] outputIndices) {
    Graph subgraph = new Graph(subgraphHandle);

    int ninputs = inputHandles.length;
    int noutputs = outputHandles.length;
    Output<?>[] inputs = new Output<?>[ninputs];
    Output<?>[] outputs = new Output<?>[noutputs];
    Object[] outputHandlesAndIndices = new Object[noutputs * 2];

    synchronized (subgraph.nativeHandleLock) {
      try (Reference ref = subgraph.ref()) {

        for (int i = 0; i < ninputs; i++) {
          Operation op = new GraphOperation(subgraph, inputHandles[i]);
          inputs[i] = op.output(inputIndices[i]);
        }

        for (int i = 0; i < noutputs; i++) {
          Operation op = new GraphOperation(subgraph, outputHandles[i]);
          outputs[i] = op.output(outputIndices[i]);
        }

        subgraphBuilder.buildSubgraph(subgraph, inputs, outputs);

        for (int i = 0, j = noutputs; i < noutputs; i++, j++) {
          outputHandlesAndIndices[i] = outputs[i].getUnsafeNativeHandle();
          outputHandlesAndIndices[j] = (int) outputs[i].index();
        }
      }
      return outputHandlesAndIndices;
    }
  }

  /**
   * Builds a while loop.
   *
   * @param inputs the loop inputs
   * @param cgBuilder WhileSubgraphBuilder to build the conditional subgraph
   * @param bgBuilder WhileSubgraphBuilder to build the body subgraph
   * @param name name for the loop
   * @return list of loop outputs, of the same length as {@code inputs}
   */
  public Output<?>[] whileLoop(
      Output<?>[] inputs,
      WhileSubgraphBuilder cgBuilder,
      WhileSubgraphBuilder bgBuilder,
      String name) {
    int ninputs = inputs.length;
    TF_Operation[] inputHandles = new TF_Operation[ninputs];
    int[] inputIndices = new int[ninputs];
    Output<?>[] outputs = new Output<?>[ninputs];

    synchronized (nativeHandleLock) {
      try (Reference ref = ref()) {

        for (int i = 0; i < ninputs; i++) {
          inputHandles[i] = (TF_Operation)inputs[i].getUnsafeNativeHandle();
          inputIndices[i] = inputs[i].index();
        }

        Object[] outputHandlesAndIndices =
            whileLoop(nativeHandle, inputHandles, inputIndices, name, cgBuilder, bgBuilder);

        for (int i = 0, j = ninputs; i < ninputs; ++i, ++j) {
          Operation op = new GraphOperation(this, (TF_Operation)outputHandlesAndIndices[i]);
          outputs[i] = op.output((int) outputHandlesAndIndices[j]);
        }
      }
      return outputs;
    }
  }

  /**
   * Return the {@link SaverDef} instance used to save the state of all variables present in
   * this graph.
   *
   * <p/>On the first call of this method, all nodes necessary to save and restore the state of the
   * variables are added to the graph. Consequently, any variables that are added to the graph after
   * this call could not be saved nor restored using this {@link SaverDef}.
   *
   * @return a {@link SaverDef} instance
   */
  synchronized SaverDef saverDef() {
    if (saverDef == null) {
      saverDef = addVariableSaver(this);
    }
    return saverDef;
  }

  private final Object nativeHandleLock = new Object();
  private TF_Graph nativeHandle;
  private int refcount = 0;
  private SaverDef saverDef;

  private final List<Op> initializers = new ArrayList<>();

  // Related native objects (such as the TF_Operation object backing an Operation instance)
  // have a validity tied to that of the Graph. The handles to those native objects are not
  // valid after Graph.close() has been invoked.
  //
  // Instances of the Reference class should be used to ensure the Graph has not been closed
  // while dependent handles are in use.
  class Reference implements AutoCloseable {
    private Reference() {
      synchronized (Graph.this.nativeHandleLock) {
        active = Graph.this.nativeHandle != null && !Graph.this.nativeHandle.isNull();
        if (!active) {
          throw new IllegalStateException("close() has been called on the Graph");
        }
        active = true;
        Graph.this.refcount++;
      }
    }

    @Override
    public void close() {
      synchronized (Graph.this.nativeHandleLock) {
        if (!active) {
          return;
        }
        active = false;
        if (--Graph.this.refcount == 0) {
          Graph.this.nativeHandleLock.notifyAll();
        }
      }
    }

    public TF_Graph nativeHandle() {
      synchronized (Graph.this.nativeHandleLock) {
        return active ? Graph.this.nativeHandle : null;
      }
    }

    private boolean active;
  }

  Reference ref() {
    return new Reference();
  }

  private static final class OperationIterator implements Iterator<Operation> {

    OperationIterator(Graph g) {
      this.graph = g;
      this.operation = null;
      this.position = 0;
      this.advance();
    }

    private final void advance() {
      Graph.Reference reference = this.graph.ref();

      this.operation = null;

      try {
        Object[] nativeReturn = nextOperation(reference.nativeHandle(), this.position);

        if (nativeReturn != null && nativeReturn[0] != null && !((TF_Operation)nativeReturn[0]).isNull()) {
          this.operation = new GraphOperation(this.graph, (TF_Operation)nativeReturn[0]);
          this.position = (Integer)nativeReturn[1];
        }
      } finally {
        reference.close();
      }
    }

    @Override
    public boolean hasNext() {
      return (this.operation != null);
    }

    @Override
    public Operation next() {
      Operation rhett = this.operation;
      this.advance();
      return rhett;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("remove() is unsupported.");
    }

    private final Graph graph;
    private Operation operation;
    private int position;
  }

  private static TF_Graph allocate() {
      return TF_NewGraph();
  }

  private static void delete(TF_Graph handle) {
    if (handle == null || handle.isNull()) return;
    TF_DeleteGraph(handle);
  }

  private static void requireHandle(Pointer handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("close() has been called on the Graph");
    }
  }

  private static TF_Operation operation(TF_Graph handle, String name) {
    requireHandle(handle);
    return TF_GraphOperationByName(handle, name);
  }

  // This method returns the Operation native handle at index 0 and the new value for pos at index 1
  // (see TF_GraphNextOperation)
  private static Object[] nextOperation(TF_Graph handle, int position) {
    requireHandle(handle);

    try (PointerScope scope = new PointerScope()) {
      SizeTPointer pos = new SizeTPointer(1).put(position);
      TF_Operation operation = TF_GraphNextOperation(handle, pos);
      if (operation == null || operation.isNull()) return null;

      Object[] handleAndPosition = new Object[2];
      handleAndPosition[0] = operation;
      handleAndPosition[1] = (int)pos.get();
      return handleAndPosition;
    }
  }

  private static void importGraphDef(TF_Graph handle, GraphDef graphDef, String prefix)
      throws IllegalArgumentException {
    requireHandle(handle);

    // Continue cleaning up resources even if an exception was thrown.
    try (PointerScope scope = new PointerScope()) {
      TF_ImportGraphDefOptions opts = TF_ImportGraphDefOptions.newImportGraphDefOptions();

      TF_ImportGraphDefOptionsSetPrefix(opts, prefix);

      TF_Buffer buf = TF_Buffer.newBufferFromString(graphDef);
      TF_Status status = TF_Status.newStatus();

      TF_GraphImportGraphDef(handle, buf, opts, status);
      status.throwExceptionIfNotOK();
    }
  }

  private static GraphDef toGraphDef(TF_Graph handle) {
    requireHandle(handle);

    try (PointerScope scope = new PointerScope()) {
      TF_Buffer buf = TF_Buffer.newBuffer();
      TF_Status status = TF_Status.newStatus();
      TF_GraphToGraphDef(handle, buf, status);
      status.throwExceptionIfNotOK();
      try {
        return GraphDef.parseFrom(buf.dataAsByteBuffer());
      } catch (InvalidProtocolBufferException e) {
        throw new TensorFlowException("Cannot parse GraphDef protocol buffer", e);
      }
    }
  }

  static void resolveOutputs(String type, TF_Operation[] srcOps,
                             int[] srcIndices, TF_Output dst, int n) {
    if (srcOps.length != n) {
      throw new IllegalArgumentException("expected " + n + ", got " + srcOps.length + " " + type + " Operations");
    }
    if (srcIndices.length != n) {
      throw new IllegalArgumentException("expected " + n + ", got " + srcIndices.length + " " + type + " Operation output indices");
    }
    for (int i = 0; i < n; ++i) {
      if (srcOps[i] == null || srcOps[i].isNull()) {
        throw new IllegalStateException("invalid " + type + " (#" + i + " of " + n + ")");
      }
      dst.position(i).oper(srcOps[i]).index(srcIndices[i]);
    }
    dst.position(0);
  }

  private static Object[] addGradients(
      TF_Graph handle,
      String prefix,
      TF_Operation[] inputHandles,
      int[] inputIndices,
      TF_Operation[] outputHandles,
      int[] outputIndices,
      TF_Operation[] gradInputHandles,
      int[] gradInputIndices) {
    requireHandle(handle);

    try (PointerScope scope = new PointerScope()) {
      int ny = inputHandles.length;
      int nx = outputHandles.length;

      TF_Output y = new TF_Output(ny);
      TF_Output x = new TF_Output(nx);
      TF_Output dx = null;
      TF_Output dy = new TF_Output(nx);

      resolveOutputs("y", inputHandles, inputIndices, y, ny);
      resolveOutputs("x", outputHandles, outputIndices, x, nx);
      if (gradInputHandles != null) {
        if (gradInputHandles.length != ny) {
          throw new IllegalArgumentException("expected " + ny + ", got " + gradInputHandles.length + " handles");
        }
        dx = new TF_Output(ny);
        resolveOutputs("dx", gradInputHandles, gradInputIndices, dx, ny);
      }

      TF_Status status = TF_Status.newStatus();
      TF_AddGradientsWithPrefix(handle, prefix, y, ny, x, nx, dx, status, dy);
      status.throwExceptionIfNotOK();

      // returned array contains both op handles and output indices, in pair
      Object[] gradOutputHandlesAndIndices = new Object[nx * 2];
      for (int i = 0, j = nx; i < nx; ++i, ++j) {
        TF_Output gradOutput = dy.position(i);
        gradOutputHandlesAndIndices[i] = gradOutput.oper();
        gradOutputHandlesAndIndices[j] = gradOutput.index();
      }
      return gradOutputHandlesAndIndices;
    }
  }

  private static Object[] whileLoop(
      TF_Graph handle,
      TF_Operation[] inputHandles,
      int[] inputIndices,
      String name,
      WhileSubgraphBuilder condGraphBuilder,
      WhileSubgraphBuilder bodyGraphBuilder) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();

      int ninputs = inputHandles.length;

      TF_Output inputs = new TF_Output(ninputs);
      resolveOutputs("inputs", inputHandles, inputIndices, inputs, ninputs);

      // initialize while params
      TF_WhileParams params = TF_NewWhile(handle, inputs, ninputs, status);
      status.throwExceptionIfNotOK();

      // build conditional subgraph
      TF_Output condInputsOutput = params.cond_inputs();
      TF_Output condOutputOutput = params.cond_output();
      TF_Operation[] condInputHandles = new TF_Operation[ninputs];
      int[] condInputIndices = new int[ninputs];
      TF_Operation[] condOutputHandles = new TF_Operation[1];
      int[] condOutputIndices = new int[1];
      for (int i = 0; i < ninputs; i++) {
          condInputHandles[i] = condInputsOutput.position(i).oper();
          condInputIndices[i] = condInputsOutput.position(i).index();
      }
      condOutputHandles[0] = condOutputOutput.oper();
      condOutputIndices[0] = condOutputOutput.index();

      Object[] condOutputHandlesAndIndices =
          buildSubgraph(condGraphBuilder, params.cond_graph(),
                        condInputHandles, condInputIndices,
                        condOutputHandles, condOutputIndices);

      // build body subgraph
      TF_Output bodyInputsOutput = params.body_inputs();
      TF_Output bodyOutputsOutput = params.body_outputs();
      TF_Operation[] bodyInputHandles = new TF_Operation[ninputs];
      int[] bodyInputIndices = new int[ninputs];
      TF_Operation[] bodyOutputHandles = new TF_Operation[ninputs];
      int[] bodyOutputIndices = new int[ninputs];
      for (int i = 0; i < ninputs; i++) {
          bodyInputHandles[i] = bodyInputsOutput.position(i).oper();
          bodyInputIndices[i] = bodyInputsOutput.position(i).index();
          bodyOutputHandles[i] = bodyOutputsOutput.position(i).oper();
          bodyOutputIndices[i] = bodyOutputsOutput.position(i).index();
      }

      Object[] bodyOutputHandlesAndIndices =
          buildSubgraph(bodyGraphBuilder, params.body_graph(),
                        bodyInputHandles, bodyInputIndices,
                        bodyOutputHandles, bodyOutputIndices);

      if (condOutputHandlesAndIndices == null ||
          bodyOutputHandlesAndIndices == null)
        return null;

      // set cond_output param to output of the conditional subgraph
      condOutputOutput.oper((TF_Operation)condOutputHandlesAndIndices[0])
                      .index((Integer)condOutputHandlesAndIndices[1]);

      // set body_outputs param to outputs of the body subgraph
      for (int i = 0, j = ninputs; i < ninputs; ++i, ++j) {
        bodyOutputsOutput.position(i).oper((TF_Operation)bodyOutputHandlesAndIndices[i])
                                     .index((Integer)bodyOutputHandlesAndIndices[j]);
      }

      // set loop name param
      params.name(new BytePointer(name));

      // build the while loop, storing loop outputs in `outputs`
      TF_Output outputs = new TF_Output(ninputs);
      TF_FinishWhile(params, status, outputs);

      status.throwExceptionIfNotOK();

      // returned array contains both op handles and output indices, in pair
      Object[] outputHandlesAndIndices = new Object[ninputs * 2];
      for (int i = 0, j = ninputs; i < ninputs; ++i, ++j) {
        TF_Output output = outputs.position(i);
        outputHandlesAndIndices[i] = output.oper();
        outputHandlesAndIndices[j] = output.index();
      }

      return outputHandlesAndIndices;
    }
  }

  private static SaverDef addVariableSaver(Graph graph) {
    Ops tf = Ops.create(graph).withSubScope("save");

    List<String> varNames = new ArrayList<>();
    List<Operand<?>> varOutputs = new ArrayList<>();
    List<Class<? extends TType>> varTypes = new ArrayList<>();

    for (Iterator<Operation> iter = graph.operations(); iter.hasNext();) {
      Operation op = iter.next();
      if (op.type().equals("VariableV2")) {
        varNames.add(op.name());
        varOutputs.add(op.output(0));
        varTypes.add(op.output(0).type());
      }
    }

    // FIXME Need an easier way to initialize an NdArray from a list
    String[] tmp = new String[varNames.size()];
    Constant<TString> varNamesTensor = tf.constant(StdArrays.ndCopyOf(varNames.toArray(tmp)));
    Operand<TString> varSlices = tf.zerosLike(varNamesTensor);

    Placeholder<TString> saveFilename = tf.placeholder(TString.class);
    Save saveVariables = tf.train.save(
        saveFilename,
        varNamesTensor,
        varSlices,
        varOutputs
    );
    Restore restoreVariables = tf.train.restore(
        saveFilename,
        varNamesTensor,
        varSlices,
        varTypes
    );
    List<Op> restoreOps = new ArrayList<>(varOutputs.size());
    for (int i = 0; i < varOutputs.size(); ++i) {
      restoreOps.add(tf.assign(varOutputs.get(i), (Operand) restoreVariables.tensors().get(i)));
    }
    NoOp restoreAll = tf.withControlDependencies(restoreOps).noOp();

    return SaverDef.newBuilder()
        .setFilenameTensorName(saveFilename.op().name())
        .setSaveTensorName(saveVariables.op().name())
        .setRestoreOpName(restoreAll.op().name())
        .build();
  }

  static {
    TensorFlow.init();
  }
}
