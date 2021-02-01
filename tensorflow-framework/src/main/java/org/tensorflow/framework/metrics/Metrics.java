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
import org.tensorflow.framework.utils.CastHelper;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/** Helper class with built-in metrics functions. */
public class Metrics {

  public static final float L2_NORM_EPSILON = 1e-12f;

  /**
   * Computes how often targets are in the top K predictions.
   *
   * <p>Standalone usage:
   *
   * <pre>
   *     Operand&lt;TInt32&gt; labels = tf.constant(new int[][]
   *                                    {{0, 0, 1}, {0, 1, 0}});
   *     Operand&lt;TFloat32&gt; predictions = tf.constant(new float[][]
   *                                    {{0.1f, 0.9f, 0.8f}, {0.05f, 0.95f, 0f}});
   *     Operand&lt;TFloat32&gt; m = Metrics.topKCategoricalAccuracy(
   *                                    labels, predictions, 3)
   *     //m.shape().toString == "[2]"
   * </pre>
   *
   * @param tf the TensorFlow Ops.
   * @param labels the ground truth values.
   * @param predictions The prediction values.
   * @param k Number of top elements to look at for computing accuracy.
   * @param <T> the data type for the predictions and results
   * @return the Operand for the Top K categorical accuracy value.
   */
  public static <T extends TNumber> Operand<T> topKCategoricalAccuracy(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions, long k) {
    Operand<TFloat32> fPredictions = CastHelper.cast(tf, predictions, TFloat32.class);
    return CastHelper.cast(
        tf,
        tf.nn.inTopK(fPredictions, tf.math.argMax(labels, tf.constant(-1)), tf.constant(k)),
        predictions.type());
  }
}
