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
package org.tensorflow.framework.metrics;

import java.util.Collections;
import java.util.List;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/** Base class for Metrics */
public abstract class BaseMetric implements Metric {

  /** The seed for random number generation */
  private final long seed;

  private String name;

  private boolean initialized;

  private Ops tf;

  /**
   * Creates a Metric with a name of {@link Class#getSimpleName()}
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   */
  protected BaseMetric(long seed) {
    this(null, seed);
  }

  /**
   * Creates a Metric
   *
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   */
  protected BaseMetric(String name, long seed) {

    this.seed = seed;
    this.name = name != null ? name : this.getClass().getSimpleName();
  }

  /**
   * Creates a List of Operations to update the metric state based on input values.
   *
   * <p>This is an empty implementation that should be overridden in a subclass, if needed.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to the values, may be null.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not have a Graph environment.
   * @return a List of Operations to update the metric state
   */
  @SuppressWarnings({"unchecked", "unused"})
  @Override
  public List<Op> updateStateList(
      Ops tf, Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights) {
    checkIsGraph(tf);
    return Collections.EMPTY_LIST;
  }

  /**
   * Creates a List of Operations to update the metric state based on labels and predictions.
   *
   * <p>This is an empty implementation that should be overridden in a subclass, if needed.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights sample weights to be applied to the metric values, may be null.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not have a Graph environment.
   * @return a List of Operations to update the metric state
   */
  @Override
  @SuppressWarnings({"unchecked", "unused"})
  public List<Op> updateStateList(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    checkIsGraph(tf);
    return Collections.EMPTY_LIST;
  }

  /**
   * Creates a NoOp Operation with control dependencies to update the metric state
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to the values, may be null.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not have a Graph environment.
   * @return the Operation to update the metric state
   */
  public final Op updateState(
      Ops tf, Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights) {
    checkIsGraph(tf);
    List<Op> controlOps = updateStateList(tf, values, sampleWeights);
    return tf.withSubScope("updateState").withControlDependencies(controlOps).noOp();
  }

  /**
   * Creates a NoOp Operation with control dependencies to update the metric state
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights sample weights to be applied to the metric values, may be null.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not have a Graph environment.
   * @return the Operation to update the metric state
   */
  public final Op updateState(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    List<Op> controlOps = updateStateList(tf, labels, predictions, sampleWeights);
    return tf.withSubScope("updateState").withControlDependencies(controlOps).noOp();
  }

  /**
   * Calls update state once, followed by a call to get the result
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to the values, may be null.
   * @param <T> The data type for the metric result
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not have a Graph environment.
   * @return the result, possibly with control dependencies
   */
  @Override
  public final <T extends TNumber> Operand<T> callOnce(
      Ops tf,
      Operand<? extends TNumber> values,
      Operand<? extends TNumber> sampleWeights,
      Class<T> type) {
    checkIsGraph(tf);
    List<Op> controlOps = updateStateList(tf, values, sampleWeights);
    Ops ltf = tf.withSubScope("callOnce").withControlDependencies(controlOps);
    return ltf.identity(result(ltf, type));
  }

  /**
   * Gets a formatted name for a variable, in the form {@link #name} + "_" + varName.
   *
   * @param varName the base name for the variable
   * @return the formatted variable name
   */
  protected String getVariableName(String varName) {
    return String.format("%s_%s", this.name, varName);
  }

  /**
   * The name for this metric. Defaults to {@link Class#getSimpleName()}.
   *
   * <p>Gets the name of this metric.
   *
   * @return the name of this metric
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the metric name
   *
   * @param name the metric name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the random number generator seed value
   *
   * @return the random number generator seed value
   */
  public long getSeed() {
    return seed;
  }

  /**
   * Initialize the TensorFlow Ops
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @throws IllegalArgumentException if the TensorFlow Ops does not have a Graph environment,
   */
  protected abstract void init(Ops tf);

  /**
   * Gets the TensorFlow Ops for this metric
   *
   * @return the TensorFlow Ops for this metric.
   */
  protected Ops getTF() {
    return tf;
  }

  /**
   * Sets the TensorFlow Ops for this metric.
   *
   * <p>This should be set from the {@link #init(Ops)} implementation.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not have a Graph environment.
   */
  protected void setTF(Ops tf) {
    checkIsGraph(tf);
    this.tf = tf;
  }

  /**
   * Checks whether the Metric is initialized or not.
   *
   * @return true if the Metric has been initialized.
   */
  public boolean isInitialized() {
    return initialized;
  }

  /**
   * Sets the initialized indicator
   *
   * @param initialized the initialized indicator
   */
  protected void setInitialized(boolean initialized) {
    this.initialized = initialized;
  }

  /**
   * Checks if the TensorFlow Ops encapsulates a {@link Graph} environment.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not encapsulate a Graph
   *     environment.
   */
  protected void checkIsGraph(Ops tf) {
    if (!tf.scope().env().isGraph()) {
      throw new IllegalArgumentException(
          "The Ops environment is not a Graph, Graph is required for metrics.");
    }
  }
}
