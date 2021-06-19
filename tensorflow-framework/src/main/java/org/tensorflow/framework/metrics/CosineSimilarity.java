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

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.Losses;
import org.tensorflow.framework.metrics.impl.LossMetric;
import org.tensorflow.framework.metrics.impl.MeanMetricWrapper;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * A metric that computes the cosine similarity metric between labels and predictions.
 *
 * <p>Note that it is a number between -1 and 1. When it is a negative number between -1 and 0, 0
 * indicates orthogonality and values closer to -1 indicate greater similarity. The values closer to
 * 1 indicate greater dissimilarity. This makes it usable as a loss function in a setting where you
 * try to maximize the proximity between predictions and targets. If either labels and predictions
 * is a zero vector, cosine similarity will be 0 regardless of the proximity between predictions and
 * targets.
 *
 * <pre>{@code loss = -sum(l2_norm(y_true) * l2_norm(y_pred))}</pre>
 *
 * @param <T> The data type for the metric result.
 * @see <a href="https://en.wikipedia.org/wiki/Cosine_similarity">Cosine Similarity</a>
 */
public class CosineSimilarity<T extends TNumber> extends MeanMetricWrapper<T>
    implements LossMetric<T> {

  /** The default axis, the last axis for calculating the cosime similarity */
  public static final int DEFAULT_AXIS = -1;

  private final int[] axis;

  /**
   * Creates a metric that computes the cosine similarity metric between labels and predictions with
   * a default axis, {@link #DEFAULT_AXIS} and using a name based on {@link Class#getSimpleName()}.
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CosineSimilarity(long seed, Class<T> type) {
    this(null, DEFAULT_AXIS, seed, type);
  }

  /**
   * Creates a metric that computes the cosine similarity metric between labels and predictions
   * using a name based on {@link Class#getSimpleName()}.
   *
   * @param axis The dimension along which the cosine similarity is computed.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CosineSimilarity(int axis, long seed, Class<T> type) {
    this(null, new int[] {axis}, seed, type);
  }
  /**
   * Creates a CosineSimilarity metric using a name based on {@link Class#getSimpleName()}.
   *
   * @param axis The dimension along which the cosine similarity is computed.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CosineSimilarity(int[] axis, long seed, Class<T> type) {
    this(null, axis, seed, type);
  }
  /**
   * Creates a metric that computes the cosine similarity metric between labels and predictions with
   * a default axis, {@link #DEFAULT_AXIS}
   *
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CosineSimilarity(String name, long seed, Class<T> type) {
    this(name, DEFAULT_AXIS, seed, type);
  }

  /**
   * Creates a metric that computes the cosine similarity metric between labels and predictions.
   *
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param axis The dimension along which the cosine similarity is computed.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CosineSimilarity(String name, int axis, long seed, Class<T> type) {
    this(name, new int[] {axis}, seed, type);
  }
  /**
   * Creates a CosineSimilarity metric
   *
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param axis The dimension along which the cosine similarity is computed.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public CosineSimilarity(String name, int[] axis, long seed, Class<T> type) {
    super(name, seed, type);
    this.axis = axis;
    setLoss(this);
  }

  /**
   * Computes the cosine similarity loss between labels and predictions.
   *
   * @param labels the truth values or labels
   * @param predictions the predictions
   * @return the cosine similarity loss
   */
  @Override
  public Operand<T> call(
      Ops tf, Operand<? extends TNumber> labels, Operand<? extends TNumber> predictions) {
    init(tf);
    // NOTE: metrics.CosineSimilarity is Losses.cosineSimilarity,
    // while losses.CosineSimilarity is the negative of Losses.cosineSimilarity
    Operand<T> tLabels = cast(getTF(), labels, getResultType());
    Operand<T> tPredictions = cast(getTF(), predictions, getResultType());
    return Losses.cosineSimilarity(getTF(), tLabels, tPredictions, axis);
  }
}
