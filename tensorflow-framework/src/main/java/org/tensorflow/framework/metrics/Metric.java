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

import org.tensorflow.Operand;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

import java.util.Collections;
import java.util.List;

/**
 * Base class for Metrics
 *
 * @param <U> The data type for the metric values
 * @param <T> The data type for the metric result
 */
public abstract class Metric<U extends TNumber, T extends TNumber> {

  /** The TensorFlow Ops */
  private final Ops tf;

  private final long seed;

  /** The name for this metric. Defaults to {@link Class#getSimpleName()}. */
  private final String name;

  /**
   * Creates a Metric with a name of {@link Class#getSimpleName()}
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   */
  protected Metric(Ops tf, long seed) {
    this(tf, null, seed);
  }

  /**
   * Creates a Metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   */
  protected Metric(Ops tf, String name, long seed) {
    if (!tf.scope().env().isGraph()) {
      throw new IllegalArgumentException("Metrics are required to execute in Graph mode.");
    }
    this.seed = seed;
    this.name = name != null ? name : this.getClass().getSimpleName();
    this.tf = tf.withName(this.getClass().getSimpleName());
  }

  /**
   * Creates a List of Operations to update the metric state based on input values.
   *
   * <p>This is an empty implementation that should be overridden in a subclass, if needed.
   *
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return a List of Operations to update the metric state
   * @param <S> the data type for sampleWeights
   */
  @SuppressWarnings({"unchecked", "unused"})
  public <S extends TNumber> List<Op> updateStateList(Operand<U> values, Operand<S> sampleWeights) {
    return Collections.EMPTY_LIST;
  }

  /**
   * Creates a List of Operations to update the metric state based on labels and predictions.
   *
   * <p>This is an empty implementation that should be overridden in a sub class, if needed.
   *
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @param <V> the data type for the labels
   * @param <S> the data type for the sampleWeights
   * @return a List of Operations to update the metric state
   */
  @SuppressWarnings({"unchecked", "unused"})
  public <V extends TNumber, S extends TNumber> List<Op> updateStateList(
      Operand<V> labels, Operand<U> predictions, Operand<S> sampleWeights) {
    return Collections.EMPTY_LIST;
  }

  /**
   * Creates a NoOp Operation with control dependencies to update the metric state
   *
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @param <S> the data type for sampleWeights
   * @return the Operation to update the metric state
   */
  public final <S extends TNumber> Op updateState(Operand<U> values, Operand<S> sampleWeights) {
    List<Op> controlOps = updateStateList(values, sampleWeights);
    return tf.withSubScope("updateState").withControlDependencies(controlOps).noOp();
  }

  /**
   * Creates a NoOp Operation with control dependencies to update the metric state
   *
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @param <V> the data type for the labels
   * @param <S> the data type for the sampleWeights
   * @return the Operation to update the metric state
   */
  public final <V extends TNumber, S extends TNumber> Op updateState(
      Operand<V> labels, Operand<U> predictions, Operand<S> sampleWeights) {
    List<Op> controlOps = updateStateList(labels, predictions, sampleWeights);
    return tf.withSubScope("updateState").withControlDependencies(controlOps).noOp();
  }

  /**
   * Gets the current result of the metric
   *
   * @return the result, possibly with control dependencies
   */
  public abstract Operand<T> result();

  /**
   * Resets any state variables to their initial values
   *
   * @return the control operation for doing the reset
   */
  public abstract Op resetStates();

  /**
   * Calls update state once, followed by a call to get the result
   *
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return the result, possibly with control dependencies
   */
  public final Operand<T> callOnce(Operand<U> values, Operand<T> sampleWeights) {
    List<Op> controlOps = updateStateList(values, sampleWeights);
    Ops ltf = tf.withSubScope("callOnce").withControlDependencies(controlOps);
    return ltf.identity(result());
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
   * Gets the TensorFlow Ops
   *
   * @return the TensorFlow Ops
   */
  public Ops getTF() {
    return tf;
  }

  /**
   * Gets the name of this metric.
   *
   * @return the name of this metric
   */
  public String getName() {
    return name;
  }

  /** The random number generator seed value */
  public long getSeed() {
    return seed;
  }
}
