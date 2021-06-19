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

import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import org.tensorflow.Operand;
import org.tensorflow.framework.metrics.impl.Initializable;
import org.tensorflow.framework.metrics.impl.MetricInterface;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Base class for Metrics
 *
 * @param <T> The data resultType for the metric result
 */
public abstract class Metric<T extends TNumber> implements Initializable, MetricInterface {

  /** The seed for random number generation */
  private final long seed;
  /** The name for this metric. Defaults to {@link Class#getSimpleName()}. */
  private final String name;

  private final Class<T> resultType;
  /** The TensorFlow Ops */
  protected Ops tf;

  /** Stores the list of registered functions to be invoked up initialization */
  protected List<Consumer<Ops>> onInits;

  /** indicator whether the metric's variables need to be initialized with an assing operation */
  protected boolean variablesNeedAssign;

  /**
   * Creates a Metric with a name of {@link Class#getSimpleName()}
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data resultType.
   * @param resultType the data type of the metric result
   */
  protected Metric(long seed, Class<T> resultType) {
    this(null, seed, resultType);
  }

  /**
   * Creates a Metric
   *
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data resultType.
   * @param resultType the data type of the metric result
   */
  protected Metric(String name, long seed, Class<T> resultType) {
    this.seed = seed;
    this.resultType = resultType;
    this.name = name != null ? name : this.getClass().getSimpleName();
  }

  /** {@inheritDoc} */
  @Override
  public Ops init(Ops tf) {
    if (this.tf == null) {
      if (this.tf == null) {
        setTensorFlowOps(tf);
        applyOnInit();
      }
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
    this.tf = tf.withName(this.getClass().getSimpleName());
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
   * Creates a List of Operations to update the metric state based on labels and predictions.
   *
   * <p>This is an empty implementation that should be overridden in a sub class, if needed.
   *
   * @param tf the TensorFlow Ops
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return a List of Operations to update the metric state
   */
  public List<Op> updateStateList(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    return Collections.emptyList();
  }

  /**
   * Creates a List of Operations to update the metric state based on input values.
   *
   * <p>This is an empty implementation that should be overridden in a subclass, if needed.
   *
   * @param tf the TensorFlow Ops
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return a List of Operations to update the metric state
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     Initializable#init(Ops)}.
   */
  public List<Op> updateStateList(
      Ops tf, Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights) {
    return Collections.emptyList();
  }

  /**
   * Creates a NoOp Operation with control dependencies to update the metric state
   *
   * @param tf the TensorFlow Ops
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return the Operation to update the metric state
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     #init(Ops)}.
   */
  public final Op updateState(
      Ops tf, Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights) {
    List<Op> controlOps = updateStateList(tf, values, sampleWeights);
    return checkTF().withSubScope("updateState").withControlDependencies(controlOps).noOp();
  }

  /**
   * Creates a NoOp Operation with control dependencies to update the metric state
   *
   * @param tf the TensorFlow Ops
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return the Operation to update the metric state
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     #init(Ops)}.
   */
  public final Op updateState(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {

    List<Op> controlOps = updateStateList(tf, labels, predictions, sampleWeights);
    return checkTF().withSubScope("updateState").withControlDependencies(controlOps).noOp();
  }

  /**
   * Calls update state once, followed by a call to get the result
   *
   * @param tf the TensorFlow Ops
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return the result, possibly with control dependencies
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     #init(Ops)}.
   */
  public final Operand<T> callOnce(
      Ops tf, Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights) {
    init(tf);
    List<Op> controlOps = updateStateList(tf, values, sampleWeights);
    Ops ltf = getTF().withSubScope("callOnce").withControlDependencies(controlOps);
    return ltf.identity(cast(getTF(), result(getTF()), getResultType()));
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

  /**
   * Gets the result type
   *
   * @return the result type
   */
  public Class<T> getResultType() {
    return resultType;
  }

  /**
   * Gets a result of zero, primarily for conditions where variables have not yet been assigned.
   *
   * @return a result of zero.
   */
  protected Operand<T> getResultZero() {
    return cast(tf, tf.constant(0), resultType);
  }
}
