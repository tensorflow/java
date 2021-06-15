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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import org.tensorflow.Operand;
import org.tensorflow.framework.metrics.impl.Initializable;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Base class for Metrics
 *
 * @param <T> The data type for the metric result
 */
public abstract class Metric<T extends TNumber> implements Initializable {

  /** The seed for random number generation */
  private final long seed;
  /** The name for this metric. Defaults to {@link Class#getSimpleName()}. */
  private final String name;
  /** The TensorFlow Ops */
  protected Ops tf;

  protected List<Consumer<Ops>> onInits;

  /**
   * Creates a Metric with a name of {@link Class#getSimpleName()}
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   */
  protected Metric(long seed) {
    this((String) null, seed);
  }

  /**
   * Creates a Metric
   *
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   */
  protected Metric(String name, long seed) {
    this.seed = seed;
    this.name = name != null ? name : this.getClass().getSimpleName();
  }

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
    this(name, seed);
    init(tf);
  }

  /** {@inheritDoc} */
  @Override
  public Ops init(Ops tf) {

    if (this.tf == null) {
      setTensorFlowOps(tf);
      applyOnInit();
    }
    return this.tf;
  }

  /** {@inheritDoc} */
  @Override
  public void onInit(Consumer<Ops> initFunction) {
    if (onInits == null) {
      onInits = new ArrayList<>();
    }
    onInits.add(initFunction);
  }

  /**
   * Sets the TensorFlow Ops. This is for sub classes that override the {@link #init(Ops)} method.
   *
   * @param tf the TensorFlow Ops
   * @throws IllegalArgumentException if the TensorFlow environment is not in Graph mode.
   */
  protected Ops setTensorFlowOps(Ops tf) {
    if (!tf.scope().env().isGraph()) {
      throw new IllegalArgumentException("Metrics are required to execute in Graph mode.");
    }
    this.tf = this.tf = tf.withName(this.getClass().getSimpleName());
    return this.tf;
  }

  /**
   * Applies the registered onInit functions with the TensorFlow platform. This is for sub classes
   * that override the {@link #init(Ops)} method.
   *
   * @throws IllegalStateException if the TensorFlow Ops has not been assigned.
   */
  protected void applyOnInit() {
    if (onInits != null) {
      checkTF();
      onInits.forEach(c -> c.accept(this.tf));
    }
  }

  /**
   * Get the TensorFlow Ops for this metric.
   *
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     #init(Ops)}.
   * @return the TensorFlow Ops for this metric.
   */
  protected Ops checkTF() {
    if (tf == null) {
      throw new IllegalStateException(
          "The TensorFlow Platform has mot been initialized with this Metric. Call 'init(Ops)' first");
    }
    return tf;
  }

  /**
   * Creates a List of Operations to update the metric state based on input values.
   *
   * <p>This is an empty implementation that should be overridden in a subclass, if needed.
   *
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return a List of Operations to update the metric state
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     #init(Ops)}.
   */
  @SuppressWarnings({"unused"})
  public List<Op> updateStateList(
      Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights) {
    return Collections.emptyList();
  }

  /**
   * Creates a List of Operations to update the metric state based on labels and predictions.
   *
   * <p>This is an empty implementation that should be overridden in a sub class, if needed.
   *
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return a List of Operations to update the metric state
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     #init(Ops)}.
   */
  @SuppressWarnings({"unused"})
  public List<Op> updateStateList(
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    return Collections.emptyList();
  }

  /**
   * Creates a NoOp Operation with control dependencies to update the metric state
   *
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return the Operation to update the metric state
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     #init(Ops)}.
   */
  public final Op updateState(
      Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights) {
    List<Op> controlOps = updateStateList(values, sampleWeights);
    return checkTF().withSubScope("updateState").withControlDependencies(controlOps).noOp();
  }

  /**
   * Creates a NoOp Operation with control dependencies to update the metric state
   *
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return the Operation to update the metric state
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     #init(Ops)}.
   */
  public final Op updateState(
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    List<Op> controlOps = updateStateList(labels, predictions, sampleWeights);
    return checkTF().withSubScope("updateState").withControlDependencies(controlOps).noOp();
  }

  /**
   * Gets the current result of the metric
   *
   * @return the result, possibly with control dependencies
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     #init(Ops)}.
   */
  public abstract Operand<T> result();

  /**
   * Resets any state variables to their initial values
   *
   * @return the control operation for doing the reset
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     #init(Ops)}.
   */
  public abstract Op resetStates();

  /**
   * Calls update state once, followed by a call to get the result
   *
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return the result, possibly with control dependencies
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     #init(Ops)}.
   */
  public final Operand<T> callOnce(
      Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights) {
    List<Op> controlOps = updateStateList(values, sampleWeights);
    Ops ltf = checkTF().withSubScope("callOnce").withControlDependencies(controlOps);
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

  /**
   * Gets the random number generator seed value
   *
   * @return the random number generator seed value
   */
  public long getSeed() {
    return seed;
  }
}
