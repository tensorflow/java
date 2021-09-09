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

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.framework.metrics.impl.LossMetric;
import org.tensorflow.framework.metrics.impl.MeanBaseMetricWrapper;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.OneHot;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Metric that calculates how often predictions matches one-hot labels.
 *
 * <p>You can provide {@code logits} of classes as {@code predictions}, since argmax of {@code
 * logits} and probabilities are same.
 *
 * <p>This metric creates two local variables, {@code total} and {@code count} that are used to
 * compute the frequency with which {@code predictions} matches {@code labels}. This frequency is
 * ultimately returned as categorical accuracy: an idempotent operation that simply divides total by
 * count.
 *
 * <p>{@code predictions} and {@code labels} should be passed in as vectors of probabilities, rather
 * than as labels. If necessary, use {@link org.tensorflow.op.Ops#oneHot(Operand, Operand, Operand,
 * Operand, OneHot.Options...)} to expand {@code labels} as a vector.
 *
 * <p>If sample_weight is None, weights default to 1. Use sample_weight of 0 to mask values.
 *
 * @param <T> The data type for the metric result
 */
public class CategoricalAccuracy<T extends TNumber> extends MeanBaseMetricWrapper<T>
    implements LossMetric {

  /**
   * Creates a CategoricalAccuracy metric, using {@link Class#getSimpleName()} for the metric name
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public CategoricalAccuracy(long seed, Class<T> type) {
    this(null, seed, type);
  }

  /**
   * Creates a CategoricalAccuracy metric
   *
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public CategoricalAccuracy(String name, long seed, Class<T> type) {
    super(name, seed, type);
    super.setLoss(this);
  }

  /**
   * Computes the categorical crossentropy loss.
   *
   * <p>{@code predictions} and {@code labels} should be passed in as vectors of probabilities,
   * rather than as labels. If necessary, use {@link Ops#oneHot} to expand {@code labels} as a
   * vector.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param labels One-hot ground truth values.
   * @param predictions tThe prediction values.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not encapsulate a Graph
   *     environment.
   * @return Categorical accuracy values.
   */
  @Override
  public <U extends TNumber> Operand<U> call(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Class<U> resultType) {
    init(tf);
    Operand<TInt64> trueMax = tf.math.argMax(labels, tf.constant(-1));

    Operand<TInt64> predMax = tf.math.argMax(predictions, tf.constant(-1));
    return cast(tf, tf.math.equal(trueMax, predMax), resultType);
  }
}
