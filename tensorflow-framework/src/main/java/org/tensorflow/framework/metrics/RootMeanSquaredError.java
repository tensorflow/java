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

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossTuple;
import org.tensorflow.framework.losses.impl.LossesHelper;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

import java.util.List;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Computes root mean squared error metric between {@code labels} and {@code predictions}
 * .
 *
 * @param <T> The data type for the metric result
 */
public class RootMeanSquaredError<T extends TNumber> extends Mean<T> {

  /**
   * Creates a RootMeanSquaredError metric with a name of {@link Class#getSimpleName()}
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public RootMeanSquaredError(Ops tf, long seed, Class<T> type) {
    this(tf, null, seed, type);
  }

  /**
   * Creates a RootMeanSquaredError metric
   *
   * @param tf the TensorFlow Ops
   * @param name name of the metric instance. If null, name defaults to {@link
   *     Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public RootMeanSquaredError(Ops tf, String name, long seed, Class<T> type) {
    super(tf, name, seed, type);
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
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {

    Operand<T> tLabels = cast(getTF(), labels, getResultType());
    Operand<T> tPredictions = cast(getTF(), predictions, getResultType());
    Operand<T> tSampleWeights =
        sampleWeights != null ? cast(getTF(), sampleWeights, getResultType()) : null;

    LossTuple<T> ops = LossesHelper.squeezeOrExpandDimensions(getTF(), tLabels, tPredictions);
    tPredictions = ops.getTarget();
    tLabels = ops.getLabels();

    Operand<T> errorSquared =
        cast(getTF(), getTF().math.squaredDifference(tPredictions, tLabels), getResultType());

    return super.updateStateList(errorSquared, tSampleWeights);
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> result() {
    return getTF().math.sqrt(getTF().math.divNoNan(this.total, this.count));
  }
}
