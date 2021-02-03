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
import org.tensorflow.framework.metrics.impl.LossMetric;
import org.tensorflow.framework.metrics.impl.MeanMetricWrapper;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.OneHot;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Metric that calculates how often predictions matches one-hot labels.
 *
 * <p>You can provide <code>logits</code> of classes as <code>predictions</code>y_pred, since argmax
 * of <code>logits</code> and probabilities are same.
 *
 * <p>This metric creates two local variables, <code>total</code> and <code>count</code> that are
 * used to compute the frequency with which <code>predictions</code> matches <code>labels</code>.
 * This frequency is ultimately returned as categorical accuracy: an idempotent operation that
 * simply divides total by count.
 *
 * <p><code>predictions</code> and <code>labels</code> should be passed in as vectors of
 * probabilities, rather than as labels. If necessary, use {@link
 * org.tensorflow.op.Ops#oneHot(Operand, Operand, Operand, Operand, OneHot.Options...)} to expand
 * <code>labels</code> as a vector.
 *
 * <p>If sample_weight is None, weights default to 1. Use sample_weight of 0 to mask values.
 *
 * @param <T> The data type for the metric result
 */
public class CategoricalAccuracy<T extends TNumber> extends MeanMetricWrapper<T>
    implements LossMetric<T> {

  /**
   * Creates a CategoricalAccuracy metric, using {@link Class#getSimpleName()} for the metric name
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public CategoricalAccuracy(Ops tf, long seed, Class<T> type) {
    this(tf, null, seed, type);
  }

  /**
   * Creates a CategoricalAccuracy metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public CategoricalAccuracy(Ops tf, String name, long seed, Class<T> type) {
    super(tf, name, seed, type);
    super.setLoss(this);
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(
      Operand<? extends TNumber> labels, Operand<? extends TNumber> predictions) {
    Operand<TInt64> trueMax = getTF().math.argMax(labels, getTF().constant(-1));

    Operand<TInt64> predMax = getTF().math.argMax(predictions, getTF().constant(-1));
    return cast(getTF(), getTF().math.equal(trueMax, predMax), getResultType());
  }
}
