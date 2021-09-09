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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/** Static methods for computing metrics. */
public class Metrics {

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
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment..
   * @param labels the ground truth values.
   * @param predictions The prediction values.
   * @param k Number of top elements to look at for computing accuracy.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not encapsulate a Graph
   *     environment.
   * @param <T> the data type for the predictions and results
   * @return the Operand for the Top K categorical accuracy value.
   */
  public static <T extends TNumber> Operand<T> topKCategoricalAccuracy(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions, long k) {
    Operand<TFloat32> fPredictions = cast(tf, predictions, TFloat32.class);
    return cast(
        tf,
        tf.nn.inTopK(fPredictions, tf.math.argMax(labels, tf.constant(-1)), tf.constant(k)),
        predictions.type());
  }

  /**
   * Computes how often integer targets are in the top K predictions.
   *
   * <p>Standalone usage:
   *
   * <pre>
   *     Operand&lt;TInt32&gt; labels = tf.constant(new int[]{2, 1});
   *     Operand&lt;TFloat32&gt; predictions = tf.constant(new float[][]
   *                            {{0.1f, 0.9f, 0.f8}, {0.05f, 0.95f, 0f}});
   *     Operand&lt;TFloat32&gt; m = Metrics.topKCategoricalAccuracy(
   *                                    labels, predictions, 3)
   *     //m.shape().toString == "[2]"
   * </pre>
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment..
   * @param labels the ground truth values.
   * @param predictions The prediction values.
   * @param k Number of top elements to look at for computing accuracy.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not encapsulate a Graph
   *     environment.
   * @param <T> the data type for the predictions and results
   * @param <U> the data type ofr the labels.
   * @return the Operand for the Sparse top K categorical accuracy value.
   */
  public static <T extends TNumber, U extends TNumber> Operand<T> sparseTopKCategoricalAccuracy(
      Ops tf, Operand<U> labels, Operand<T> predictions, int k) {
    Operand<T> tLabels = cast(tf, labels, predictions.type());

    // Flatten predictions to (batch_size, num_samples) and labels to (num_samples,)

    int predictionsRank = predictions.shape().numDimensions();
    int labelsRank = tLabels.shape().numDimensions();

    Operand<TFloat32> castPredictions = cast(tf, predictions, TFloat32.class);
    if (predictionsRank != Shape.UNKNOWN_SIZE && labelsRank != Shape.UNKNOWN_SIZE) {
      if (predictionsRank > 2) {
        // y_pred = array_ops.reshape(y_pred, [-1, y_pred.shape[-1]])
        castPredictions =
            tf.reshape(
                castPredictions,
                tf.constant(castPredictions.shape().takeLast(1).prepend(Shape.UNKNOWN_SIZE)));
      }
      if (labelsRank > 1) {
        // y_true = array_ops.reshape(y_true, [-1])
        tLabels = tf.reshape(tLabels, tf.constant(Shape.of(Shape.UNKNOWN_SIZE)));
      }
    }
    return cast(
        tf,
        tf.nn.inTopK(castPredictions, cast(tf, tLabels, TInt32.class), tf.constant(k)),
        predictions.type());
  }
}
