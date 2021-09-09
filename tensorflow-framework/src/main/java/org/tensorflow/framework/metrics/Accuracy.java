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
import org.tensorflow.framework.losses.impl.LossTuple;
import org.tensorflow.framework.metrics.impl.LossMetric;
import org.tensorflow.framework.metrics.impl.MeanBaseMetricWrapper;
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
public class Accuracy<T extends TNumber> extends MeanBaseMetricWrapper<T> implements LossMetric {

  /**
   * Creates an Accuracy Metric using {@link Class#getSimpleName()} for the metric name
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Accuracy(long seed, Class<T> type) {
    this(null, seed, type);
  }

  /**
   * Creates an Accuracy Metric
   *
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public Accuracy(String name, long seed, Class<T> type) {
    super(name, seed, type);
    setLoss(this);
  }

  /**
   * Calculates how often predictions equals labels. {@code labels} and {@code predictions} must
   * have compatible shapes, see {@link Shape @isCompatibleWith}.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param labels the truth values or labels
   * @param predictions the predictions
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not encapsulate a Graph
   *     environment.
   * @return the loss
   */
  @Override
  public <U extends TNumber> Operand<U> call(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Class<U> resultType) {
    init(tf);
    Operand<T> tLabels = cast(tf, labels, getInternalType());
    Operand<T> tPredictions = cast(tf, predictions, getInternalType());
    LossTuple<T> tuple =
        MetricsHelper.raggedAssertCompatibleAndGetFlatValues(tf, tLabels, tPredictions);
    tLabels = tuple.getLabels();
    tPredictions = tuple.getTarget();

    if (!predictions.shape().isCompatibleWith(labels.shape())) {
      throw new IllegalArgumentException(
          String.format(
              "Shapes %s and %s are incompatible",
              predictions.shape().toString(), labels.shape().toString()));
    }

    // cast TBool to result type
    return cast(tf, tf.math.equal(tLabels, tPredictions), resultType);
  }
}
