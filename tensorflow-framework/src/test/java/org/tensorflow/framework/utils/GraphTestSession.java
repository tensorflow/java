/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.utils;

import java.io.PrintWriter;
import java.util.Map;
import java.util.function.Predicate;
import org.tensorflow.EagerSession;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/** Graph Mode Test Session */
public class GraphTestSession extends TestSession {

  private final Graph graph;
  private final Session session;
  private final Ops tf;

  /** Create a Graph mode test session. */
  public GraphTestSession() {
    graph = new Graph();
    session = new Session(graph);
    tf = Ops.create(graph).withName("test");
  }

  /**
   * Create a Graph mode test session.
   *
   * @param graph the graph
   * @param session the session
   * @param tf the TensorFlow Ops
   */
  public GraphTestSession(Graph graph, Session session, Ops tf) {
    this.graph = graph;
    this.session = session;
    this.tf = tf;
  }

  /** {@inheritDoc} */
  @Override
  public Ops getTF() {
    return tf;
  }

  /** Get the Graph object that is represented by this Test Session */
  public Graph getGraph() {
    return graph;
  }

  /**
   * Get the TensorFlow Session instance
   *
   * @return the TensorFlow Session instance
   */
  public Session getSession() {
    return session;
  }

  /** {@inheritDoc} */
  @Override
  public void close() {
    session.close();
    graph.close();
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEager() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public Session getGraphSession() {
    return this.session;
  }

  /** {@inheritDoc} */
  @Override
  public EagerSession getEagerSession() {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public void initialize() {
    graph.initializers().forEach(initializer -> session.runner().addTarget(initializer).run());
  }

  /** {@inheritDoc} */
  @Override
  public void run(Op op, Map<Operand<? extends TType>, Tensor> feedMap) {
    createRunner(op, feedMap).run();
  }

  /**
   * Create a runner for the Operation
   *
   * @param feedMap the dictionary of values to use for the runner's feed operations. Required when
   *     placeholders are used.
   * @return the runner
   */
  public final Session.Runner createRunner(Map<Operand<? extends TType>, Tensor> feedMap) {
    return createRunner(null, feedMap);
  }

  /**
   * Create a runner for the Operation
   *
   * @param op the operation
   * @param feedMap the dictionary of values to use for the runner's feed operations. Required when
   *     placeholders are used.
   * @return the runner
   */
  public final Session.Runner createRunner(Op op, Map<Operand<? extends TType>, Tensor> feedMap) {
    Session.Runner runner = session.runner();
    if (op != null) runner.addTarget(op.op());
    if (feedMap != null) feedMap.forEach((operand, tensor) -> runner.feed(operand, tensor));

    return runner;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> void evaluate(
      double expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    try (Tensor tensor = createRunner(feedMap).fetch(input).run().get(0)) {
      super.evaluate(expected, tensor, input.type());
    }
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> void evaluate(
      Number[] expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {

    try (Tensor tensor = createRunner(feedMap).fetch(input).run().get(0)) {
      super.evaluate(expected, tensor, input.type());
    }
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void evaluate(
      FloatNdArray expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    try (Tensor tensor = createRunner(feedMap).fetch(input).run().get(0)) {
      super.evaluate(expected, tensor, input.type());
    }
  }

  /** {@inheritDoc} */
  @Override
  public void evaluateString(
      Operand<TString> input,
      Predicate<String> predicate,
      Map<Operand<? extends TType>, Tensor> feedMap) {
    try (TString tensor = (TString) createRunner(feedMap).fetch(input).run().get(0)) {
      super.evaluateString(tensor, predicate);
    }
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void evaluate(
      Operand<T> input,
      Predicate<Number> predicate,
      Map<Operand<? extends TType>, Tensor> feedMap) {
    try (Tensor tensor = createRunner(feedMap).fetch(input).run().get(0)) {
      super.evaluate(tensor, input.type(), predicate);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void evaluate(
      String[] expected, Operand<TString> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    try (TString tensor = (TString) createRunner(feedMap).fetch(input).run().get(0)) {
      super.evaluate(expected, tensor);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void evaluate(
      Boolean[] expected, Operand<TBool> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    try (TBool tensor = (TBool) createRunner(feedMap).fetch(input).run().get(0)) {
      super.evaluate(expected, tensor);
    }
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void evaluate(
      Operand<T> expected, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    try (Tensor tensor = createRunner(feedMap).fetch(input).run().get(0);
        Tensor expectedTensor = createRunner(feedMap).fetch(expected).run().get(0)) {
      super.evaluate(expectedTensor, tensor, input.type());
    }
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void print(
      PrintWriter writer, Operand<T> input, Map<Operand<? extends TType>, Tensor> feedMap) {
    try (Tensor tensor = createRunner(feedMap).fetch(input).run().get(0)) {
      super.print(writer, tensor, input.type());
    }
  }
}
