/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow.framework.metrics;

import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossTuple;
import org.tensorflow.framework.losses.impl.LossesHelper;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes root mean squared error metric between {@code labels} and {@code predictions} .
 *
 * @param <T> The data type for the metric result
 */
public class RootMeanSquaredError<T extends TNumber> extends Mean<T> {

  /**
   * Creates a RootMeanSquaredError metric with a name of {@link Class#getSimpleName()}
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public RootMeanSquaredError(long seed, Class<T> type) {
    this(null, seed, type);
  }

  /**
   * Creates a RootMeanSquaredError metric
   *
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public RootMeanSquaredError(String name, long seed, Class<T> type) {
    super(name, seed, type);
  }

  /**
   * Accumulates root mean squared error statistics.
   *
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights Optional weighting of each example. Defaults to 1. Rank is either 0, or *
   *     the same rank as labels, and must be broadcastable to labels.
   * @return a List of Operations to update the metric state.
   */
  @Override
  public List<Op> updateStateList(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    init(tf);
    Operand<T> tLabels = cast(tf, labels, getInternalType());
    Operand<T> tPredictions = cast(tf, predictions, getInternalType());
    Operand<T> tSampleWeights =
        sampleWeights != null ? cast(tf, sampleWeights, getInternalType()) : null;

    LossTuple<T> ops = LossesHelper.squeezeOrExpandDimensions(tf, tLabels, tPredictions);
    tPredictions = ops.getTarget();
    tLabels = ops.getLabels();

    Operand<T> errorSquared =
        cast(tf, tf.math.squaredDifference(tPredictions, tLabels), getInternalType());

    return super.updateStateList(tf, errorSquared, tSampleWeights);
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TNumber> Operand<U> result(Ops tf, Class<U> resultType) {
    init(tf);
    return cast(tf, tf.math.sqrt(tf.math.divNoNan(this.total, this.count)), resultType);
  }
}
