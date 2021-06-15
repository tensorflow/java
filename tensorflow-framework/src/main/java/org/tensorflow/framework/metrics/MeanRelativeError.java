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
import org.tensorflow.framework.losses.impl.LossTuple;
import org.tensorflow.framework.losses.impl.LossesHelper;
import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

import java.util.List;
import java.util.stream.IntStream;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Computes the mean relative error by normalizing with the given values.
 *
 * <p>This metric creates two local variables, {@code total} and {@code count} that are used to
 * compute the mean relative error. This is weighted by {@code sampleWeight}, and it is ultimately
 * returned as mean relative error: an idempotent operation that simply divides total by count.
 *
 * <p>If {@code sampleWeight} is {@code null}, weights default to 1. Use {@code sampleWeight} of 0
 * to mask values.
 *
 * @param <T> The data type for the metric result
 */
public class MeanRelativeError<T extends TNumber> extends Mean<T> {
  private Operand<T> normalizer;

  // holders for the init(Ops) method
  private DoubleNdArray normalizerArray;

  /**
   * Creates a MeanRelativeError metric using {@link Class#getSimpleName()} as the name
   *
   * @param normalizer The normalizer values with same shape as predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanRelativeError(float[] normalizer, long seed, Class<T> type) {
    this((String) null, normalizer, seed, type);
  }

  /**
   * Creates a MeanRelativeError metric
   *
   * @param name the name of the metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param normalizer The normalizer values with same shape as predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanRelativeError(String name, float[] normalizer, long seed, Class<T> type) {
    this(
        name,
        IntStream.range(0, normalizer.length).mapToDouble(i -> normalizer[i]).toArray(),
        seed,
        type);
  }

  /**
   * Creates a MeanRelativeError metric using {@link Class#getSimpleName()} as the name
   *
   * @param normalizer The normalizer values with same shape as predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanRelativeError(double[] normalizer, long seed, Class<T> type) {
    this((String) null, normalizer, seed, type);
  }

  /**
   * Creates a MeanRelativeError metric
   *
   * @param name the name of the metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param normalizer The normalizer values with same shape as predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanRelativeError(String name, double[] normalizer, long seed, Class<T> type) {
    this(name, StdArrays.ndCopyOf(normalizer), seed, type);
  }

  /**
   * Creates a MeanRelativeError metric
   *
   * @param name the name of the metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param normalizerArray The normalizer values with same shape as predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanRelativeError(
      String name, DoubleNdArray normalizerArray, long seed, Class<T> type) {
    super(name, seed, type);
    this.normalizerArray = normalizerArray;
  }

  /**
   * Creates a MeanRelativeError metric using {@link Class#getSimpleName()} as the name
   *
   * @param normalizer The normalizer values with same shape as predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanRelativeError(Operand<T> normalizer, long seed, Class<T> type) {
    this((String) null, normalizer, seed, type);
  }

  /**
   * Creates a MeanRelativeError metric
   *
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param normalizer The normalizer values with same shape as predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanRelativeError(String name, Operand<T> normalizer, long seed, Class<T> type) {
    super(name, seed, type);
    this.normalizer = normalizer;
  }

  /**
   * Creates a MeanRelativeError metric using {@link Class#getSimpleName()} as the name
   *
   * @param tf the TensorFlow Ops
   * @param normalizer The normalizer values with same shape as predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanRelativeError(Ops tf, float[] normalizer, long seed, Class<T> type) {
    this(tf, null, cast(tf, tf.constant(normalizer), type), seed, type);
  }

  /**
   * Creates a MeanRelativeError metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param normalizer The normalizer values with same shape as predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanRelativeError(Ops tf, String name, float[] normalizer, long seed, Class<T> type) {
    this(tf, name, cast(tf, tf.constant(normalizer), type), seed, type);
  }

  /**
   * Creates a MeanRelativeError metric using {@link Class#getSimpleName()} as the name
   *
   * @param tf the TensorFlow Ops
   * @param normalizer The normalizer values with same shape as predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanRelativeError(Ops tf, double[] normalizer, long seed, Class<T> type) {
    this(tf, null, cast(tf, tf.constant(normalizer), type), seed, type);
  }

  /**
   * Creates a MeanRelativeError metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param normalizer The normalizer values with same shape as predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanRelativeError(Ops tf, String name, double[] normalizer, long seed, Class<T> type) {
    this(tf, name, cast(tf, tf.constant(normalizer), type), seed, type);
  }

  /**
   * Creates a MeanRelativeError metric using {@link Class#getSimpleName()} as the name
   *
   * @param tf the TensorFlow Ops
   * @param normalizer The normalizer values with same shape as predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanRelativeError(Ops tf, Operand<T> normalizer, long seed, Class<T> type) {
    this(tf, null, normalizer, seed, type);
  }

  /**
   * Creates a MeanRelativeError metric
   *
   * @param tf the TensorFlow ops
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param normalizer The normalizer values with same shape as predictions.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected MeanRelativeError(
      Ops tf, String name, Operand<T> normalizer, long seed, Class<T> type) {
    this(name, normalizer, seed, type);
    init(tf);
  }

  /** {@inheritDoc} */
  @Override
  public Ops init(Ops tf) {
    super.init(tf);
    if (normalizer == null) {
      if (normalizerArray != null) {
        normalizer = cast(getTF(), getTF().constant(normalizerArray), getResultType());
      }
    }
    return getTF();
  }

  /**
   * Accumulates metric statistics.
   *
   * @param labels The ground truth values.
   * @param predictions The predicted values. Must be the same shape as the normalizer.
   * @param sampleWeights Optional weighting of each example. A null value defaults to 1. Can be an
   *     {@code Operand} whose rank is either 0, or the same rank as {@code labels}, and must be
   *     broadcastable to {@code labels}.
   * @return a List of Operations to update the metric state
   */
  @Override
  public List<Op> updateStateList(
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    Operand<T> tLabels = cast(getTF(), labels, getResultType());
    Operand<T> tPredictions = cast(getTF(), predictions, getResultType());
    Operand<T> tSampleWeights =
        sampleWeights != null ? cast(getTF(), sampleWeights, getResultType()) : null;

    LossTuple<T> tuple = LossesHelper.squeezeOrExpandDimensions(getTF(), tLabels, tPredictions);
    tPredictions = tuple.getTarget();
    tLabels = tuple.getLabels();

    tuple = LossesHelper.removeSqueezableDimensions(getTF(), normalizer, tPredictions);
    normalizer = tuple.getLabels();
    tPredictions = tuple.getTarget();

    if (!tPredictions.shape().isCompatibleWith(tLabels.shape()))
      throw new IllegalArgumentException(
          String.format(
              "Prediction shape %s is not compatible with labels shape %s",
              tPredictions.shape(), tLabels.shape()));

    Operand<T> relativeErrors =
        getTF()
            .math
            .divNoNan(
                getTF().math.abs(getTF().math.sub(tLabels, tPredictions)), this.getNormalizer());

    return super.updateStateList(relativeErrors, tSampleWeights);
  }

  /**
   * Gets the normalizer Operand
   *
   * @return the normalizer
   */
  public Operand<T> getNormalizer() {
    return normalizer;
  }
}
