/*
 * Copyright 2020 The TensorFlow Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.tensorflow.op.Ops;
import org.tensorflow.proto.framework.SignatureDef;
import org.tensorflow.proto.framework.TensorInfo;

/**
 * A graph that can be invoked as a single function, with an input and output signature.
 *
 * <p>A function can also invoke a
 * <a href="https://www.tensorflow.org/api_docs/python/tf/function">tf.function</a>
 * defined in a {@link SavedModelBundle}.
 *
 * <pre>{@code
 * ConcreteFunction myFunction = savedModelBundle.function("myFunctionSignatureName");
 * Map<String, Tensor> outputTensorMap = myFunction.call(inputTensorMap);
 * }</pre>
 */
public class ConcreteFunction implements AutoCloseable {

  /**
   * Creates a function by building a new graph.
   *
   * <p>The {@code functionBuilder} must initialize the function graph from the provided
   * {@link Ops} instance and return a valid signature that will be used to feed the input tensors
   * and fetch the output tensors on execution.
   *
   * <p>The function will be the owner of the new graph and its resulting session. Therefore,
   * the function must be enclosed properly with a try-with-resources block to guarantee that
   * all native resources will be freed once the function is discarded. For example:
   *
   * <pre>{@code
   * public class MyModel {
   *
   *   public static Signature addTwo(Ops tf) {
   *     Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
   *     Add<TFloat32> output = tf.math.add(input, tf.constant(2.0f));
   *     return Signature.builder("addTwo").input("x", input).output("y", output).build();
   *   }
   *
   *   public static void main(String args[]) {
   *     try (ConcreteFunction function = ConcreteFunction.create(MyModel::addTwo);
   *         TFloat32 x = TFloat32.scalarOf(2.0f)) {
   *       assertEquals(4.0f, ((TFloat32)function.call(x)).getFloat());
   *     }
   *   }
   * }
   * }</pre>
   *
   * @param functionBuilder function builder
   * @return the new function
   */
  public static ConcreteFunction create(Function<Ops, Signature> functionBuilder) {
    Graph graph = new Graph();
    try {
      Ops tf = Ops.create(graph);
      Signature signature = functionBuilder.apply(tf);
      return new ConcreteFunction(signature, graph, new Session(graph), Ownership.GRAPH_AND_SESSION);
    } catch (Exception e) {
      graph.close();
      throw e;
    }
  }

  /**
   * Create a function from a signature and an existing graph.
   *
   * <p>The function will keep the ownership of the session used to run the graph but not
   * the graph itself, meaning that the lifetime of the latter can extend beyond the scope
   * of the function. For example:
   *
   * <pre>{@code
   * try (Graph g = new Graph()) {
   *   Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
   *   Add<TFloat32> output = tf.math.add(input, tf.constant(2.0f));
   *   Signature signature = Signature.builder().input("x", input).output("y", output).build();
   *
   *   try (ConcreteFunction f = ConcreteFunction.create(signature, g);
   *       TFloat32 x = TFloat32.scalarOf(2.0f)) {
   *     assertEquals(4.0f, ((TFloat32)function.call(x)).getFloat());
   *   }
   *   // Graph g is still valid at this point
   * }
   * }</pre>
   *
   * @param signature signature of the function to create
   * @param graph a valid and initialized graph
   * @return a new function
   */
  public static ConcreteFunction create(Signature signature, Graph graph) {
    return new ConcreteFunction(signature, graph, new Session(graph), Ownership.SESSION_ONLY);
  }

  /**
   * Create a function from a signature and a valid graph session.
   *
   * <p>The function will not own the session nor its graph, meaning that their lifetime
   * can extend beyond the scope of the function. Therefore the function does not need to be
   * closed after its usage. For example:
   *
   * <pre>{@code
   * try (Graph g = new Graph()) {
   *   Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
   *   Add<TFloat32> output = tf.math.add(input, tf.constant(2.0f));
   *   Signature signature = Signature.builder().input("x", input).output("y", output).build();
   *
   *   try (Session s = new Session(g)) {
   *     // Auto-closing the function just as an example but this is not required since it has
   *     // no effect
   *     try (ConcreteFunction f = ConcreteFunction.create(signature, s);
   *         TFloat32 t = TFloat32.scalarOf(2.0f)) {
   *       assertEquals(4.0f, ((TFloat32)function.call(x)).getFloat());
   *     }
   *     // Session s is still valid at this point
   *   }
   *   // Graph g is still valid at this point
   * }
   * }</pre>
   *
   * @param signature signature of the function to create
   * @param session a valid session to an initialized graph
   * @return a new function
   */
  public static ConcreteFunction create(Signature signature, Session session) {
    return new ConcreteFunction(signature, session.graph(), session, Ownership.NONE);
  }

  /**
   * Returns the signature of this function
   */
  public Signature signature() {
    return signature;
  }

  /**
   * Invokes a function.
   *
   * <p>Caller is responsible for closing all Tensors.
   *
   * @param arguments list of tensors to pass in input to the function,
   *                  mapped by their signature name
   * @return output tensors resulting from the execution of the function,
   *         mapped by their signature name
   */
  public Map<String, Tensor> call(Map<String, Tensor> arguments)
      throws IllegalArgumentException {

    final SignatureDef signatureDef = signature.asSignatureDef();
    final Session.Runner runner = session.runner();

    signatureDef.getInputsMap().forEach((argName, t) -> {
      Tensor tensor = arguments.get(argName);
      if (tensor == null) {
        throw new IllegalArgumentException(String.format("Missing argument [%s]", argName));
      }
      runner.feed(t.getName(), tensor);
    });

    Map<String, TensorInfo> outputToNode = signatureDef.getOutputsMap();
    outputToNode.values().forEach(t -> runner.fetch(t.getName()));

    List<Tensor> resultTensors = runner.run();
    try {
      ListIterator<Tensor> resultTensorIter = resultTensors.listIterator();
      Map<String, Tensor> returnMap = new HashMap<String, Tensor>();

      // Use the output names as present in the signature definition
      for (String nodeName: outputToNode.keySet()) {
        returnMap.put(nodeName, resultTensorIter.next());
      }
      return returnMap;

    } catch (Exception e) {
      // Release tensors before throwing exception
      for (Tensor t : resultTensors) {
        t.close();
      }
      throw e;
    }
  }

  /**
   * Invokes a function with a single input and output.
   *
   * <p>Caller is responsible for closing all Tensors.
   *
   * @param tensor input tensor
   * @return output tensor
   * @throws IllegalArgumentException if there are multiple input or output parameters defined
   *                                  in the function
   */
  public Tensor call(Tensor tensor) throws IllegalArgumentException {
    final SignatureDef signatureDef = signature.asSignatureDef();

    if (signatureDef.getInputsCount() != 1) {
      throw new IllegalArgumentException(
        String.format("Function [%s] requires multiple inputs", signatureDef.getMethodName()));
    }
    String inputNodeName = signatureDef.getInputsMap().values().iterator().next().getName();

    if (signatureDef.getOutputsCount() != 1) {
      throw new IllegalArgumentException(
        String.format("Function [%s] has multiple outputs", signatureDef.getMethodName()));
    }
    String outputNodeName = signatureDef.getOutputsMap().values().iterator().next().getName();

    return session.runner().feed(inputNodeName, tensor).fetch(outputNodeName).run().get(0);
  }

  /**
   * Export this function as a saved model.
   *
   * <p>This method is convenient shortcut equivalent to
   * {@code SavedModel.exporter(exportDir).withFunction(this).export()}
   *
   * @param exportDir directory where to export the saved model
   * @throws IOException if saved model or variable state cannot be written on disk
   */
  public void save(String exportDir) throws IOException {
    SavedModelBundle.exporter(exportDir).withFunction(this).export();
  }

  /**
   * Returns the session used to execute the graph when calling this function
   *
   * <p>In general, a user does not need to handle directly the session of a function and rely
   * on {@link #call(Map)} to execute the graph instead. But in some cases, direct access to
   * the session might be necessary, as it allows more running options.
   *
   * @return the function session
   */
  public Session session() {
    return session;
  }

  /**
   * Returns the graph of this function
   */
  public Graph graph() {
    return graph;
  }

  @Override
  public void close() {
    if (ownership != Ownership.NONE) {
      session.close();
      if (ownership == Ownership.GRAPH_AND_SESSION) {
        graph.close();
      }
    }
  }

  @Override
  public String toString() {
    return signature.toString();
  }

  private enum Ownership {
    GRAPH_AND_SESSION, SESSION_ONLY, NONE;
  }

  private final Graph graph;
  private final Session session;
  private final Signature signature;
  private final Ownership ownership;

  ConcreteFunction(Signature signature, Graph graph, Session session, Ownership ownership) {
    this.graph = graph;
    this.session = session;
    this.signature = signature;
    this.ownership = ownership;
  }
}
