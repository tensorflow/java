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
import org.tensorflow.framework.losses.impl.LossTuple;
import org.tensorflow.framework.metrics.impl.LossMetric;
import org.tensorflow.framework.metrics.impl.MeanMetricWrapper;
import org.tensorflow.framework.metrics.impl.MetricsHelper;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Metric that calculates how often predictions equals labels.
 *
 * <p>This metric creates two local variables, total and count that are used to compute the
 * frequency with which {@code predictions} matches {@code labels}. This frequency is ultimately
 * returned as binary accuracy: an idempotent operation that simply divides total by count.
 *
 * <p>If sampleWeights is {@code null}, weights default to 1. Use sampleWeights of 0 to mask values.
 *
 * @param <T> The data type for the metric result
 */
public class Accuracy<T extends TNumber> extends MeanMetricWrapper<T> implements LossMetric<T> {

  /**
   * Creates an Accuracy Metric using {@link Class#getSimpleName()} for the metric name
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Accuracy(Ops tf, long seed, Class<T> type) {
    this(tf, null, seed, type);
  }

  /**
   * Creates an Accuracy Metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Accuracy(Ops tf, String name, long seed, Class<T> type) {
    super(tf, name, seed, type);
    setLoss(this);
  }

  /**
   * Calculates how often predictions equals labels. {@code labels} and {@code predictions} must
   * have compatible shapes, see {@link Shape @isCompatibleWith}.
   *
   * @param labels the truth values or labels
   * @param predictions the predictions
   * @throws IllegalArgumentException if predictions and labels shapes are not compatible.
   * @return the loss
   */
  @Override
  public Operand<T> call(
      Operand<? extends TNumber> labels, Operand<? extends TNumber> predictions) {
    Operand<T> tLabels = cast(getTF(), labels, getResultType());
    Operand<T> tPredictions = cast(getTF(), predictions, getResultType());
    LossTuple<T> tuple =
        MetricsHelper.raggedAssertCompatibleAndGetFlatValues(getTF(), tLabels, tPredictions);
    tLabels = tuple.getLabels();
    tPredictions = tuple.getTarget();

    if (!predictions.shape().isCompatibleWith(labels.shape())) {
      throw new IllegalArgumentException(
          String.format(
              "Shapes %s and %s are incompatible",
              predictions.shape().toString(), labels.shape().toString()));
    }

    // cast TBool to result type
    return cast(getTF(), getTF().math.equal(tLabels, tPredictions), getResultType());
  }
}
