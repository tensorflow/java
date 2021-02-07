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
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

import java.util.List;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Computes the mean relative error by normalizing with the given values.
 *
 * <p>This metric creates two local variables, <code>total</code> and <code>count</code> that are
 * used to compute the mean relative error. This is weighted by <code>sampleWeight</code>, and it is
 * ultimately returned as mean relative error: an idempotent operation that simply divides total by
 * count.
 *
 * <p>If <code>sampleWeight</code> is <code>null</code>, weights default to 1. Use sample_weight of
 * 0 to mask * values.
 *
 * @param <T> The data type for the metric result
 */
public class MeanRelativeError<T extends TNumber> extends Mean<T> {
  private Operand<T> normalizer;

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
    super(tf, name, seed, type);
    this.normalizer = normalizer;
  }

  /** {@inheritDoc} */
  @Override
  public List<Op> updateStateList(
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    Operand<T> tLabels = cast(getTF(), labels, getResultType());
    if (tLabels.shape().numDimensions() > 1) tLabels = getTF().shape.flatten(tLabels);

    Operand<T> tPredictions = cast(getTF(), predictions, getResultType());
    if (tPredictions.shape().numDimensions() > 1)
      tPredictions = getTF().shape.flatten(tPredictions);

    LossTuple<T> tuple = LossesHelper.squeezeOrExpandDimensions(getTF(), tLabels, tPredictions);
    tPredictions = tuple.getTarget();
    tLabels = tuple.getLabels();
    Operand<T> tSampleWeights =
        sampleWeights != null ? cast(getTF(), sampleWeights, getResultType()) : null;
    if (tSampleWeights != null && tSampleWeights.shape().numDimensions() > 1) {
      tSampleWeights = getTF().shape.flatten(tSampleWeights);
    }

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
