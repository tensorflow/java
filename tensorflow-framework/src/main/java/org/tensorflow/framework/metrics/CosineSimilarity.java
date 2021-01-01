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
import org.tensorflow.framework.metrics.impl.LossInterface;
import org.tensorflow.framework.metrics.impl.MeanMetricWrapper;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/** Computes the cosine similarity metric between labels and predictions. */
// TODO: this is weird, the metric is called CosineSimilarity in Keras,
//  but it calls Metrics.cosineProximity instead of Losses.cosineSimilarity.
//  The metric is calculating the Euclidean distance using L2 norms, while the loss
//  is using the dot product proportional to the product of their magnitudes.
//  While the 2 concepts are similar, they are different.
//  Should we rename this metric to CosineProximity?
public class CosineSimilarity<U extends TNumber, T extends TNumber> extends MeanMetricWrapper<U,T>
    implements LossInterface<T> {
  public static final int[] DEFAULT_AXIS = {-1};
  private final int[] axis;

  /**
   * Creates a CosineSimilarity metric with a default axis, {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   */
  public CosineSimilarity(Ops tf, String name, long seed, Class<T> type) {
    this(tf, name, DEFAULT_AXIS, seed, type);
  }

  /**
   * Creates a CosineSimilarity metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param axis The dimension along which the cosine similarity is computed.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   */
  public CosineSimilarity(Ops tf, String name, int[] axis, long seed, Class<T> type) {
    super(tf, name, seed, type);
    this.axis = axis;
    setLoss(this);
  }

  /** {@inheritDoc} */
  @Override
  public <V extends TNumber> Operand<T> call(Operand<V> labels, Operand<T> predictions) {
    // NOTE: cosineProximity is a different algorithm than Losses.cosineSimilarity
    return Metrics.cosineProximity(getTF(), labels, predictions, axis);
  }
}
