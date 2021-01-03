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
import org.tensorflow.op.core.ReduceSum;
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
   * @param <U> the data type ofr the labels.
   * @return the Operand for the Top K categorical accuracy value.
   */
  public static <T extends TNumber, U extends TNumber> Operand<T> topKCategoricalAccuracy(
      Ops tf, Operand<U> labels, Operand<T> predictions, long k) {
    Operand<TFloat32> fPredictions = CastHelper.cast(tf, predictions, TFloat32.class);
    return CastHelper.cast(
        tf,
        tf.nn.inTopK(fPredictions, tf.math.argMax(labels, tf.constant(-1)), tf.constant(k)),
        predictions.type());
  }

  /**
   * Computes the cosine similarity between labels and predictions.
   *
   * @param tf the TensorFlow Ops
   * @param labels The ground truth values.
   * @param predictions The prediction values.
   * @param axis The dimension along which the cosine similarity is computed.
   * @param <U> the data type for the labels
   * @param <T> the data type for the predictions and result
   * @return Cosine similarity value.
   */
  @SuppressWarnings("unchecked")
  public static <T extends TNumber, U extends TNumber> Operand<T> cosineProximity(
      Ops tf, Operand<U> labels, Operand<T> predictions, int[] axis) {
    Operand<T> labelsNorm;
    if (labels.type() != predictions.type())
      labelsNorm = CastHelper.cast(tf, labels, predictions.type());
    else labelsNorm = (Operand<T>) labels;
    labelsNorm = l2Normalize(tf, labelsNorm, axis);

    Operand<T> predictionsNorm = l2Normalize(tf, predictions, axis);
    Operand<T> mathMul = tf.math.mul(labelsNorm, predictionsNorm);
    return tf.reduceSum(mathMul, tf.constant(axis), ReduceSum.keepDims(Boolean.FALSE));
  }

  /**
   * Normalizes along dimension <code>axis</code> using an L2 norm with an epsilon of {@link
   * #L2_NORM_EPSILON}.
   *
   * <p>For a 1-D tensor with <code>axis = 0</code>, computes
   *
   * <pre>
   *       output = x / sqrt(max(sum(x**2), epsilon))
   * </pre>
   *
   * <p>For <code>x</code> with more dimensions, independently normalizes each 1-D slice along
   * dimension <code>axis</code>.
   *
   * @param tf The TensorFlow ops
   * @param x The operand to normalize
   * @param axes Dimension(s) along which to normalize.
   * @param <U> The data type for x.
   * @return the normalized values of x.
   */
  public static <U extends TNumber> Operand<U> l2Normalize(Ops tf, Operand<U> x, int[] axes) {
    return l2Normalize(tf, x, axes, L2_NORM_EPSILON);
  }

  /**
   * Normalizes along dimension <code>axis</code> using an L2 norm.
   *
   * <p>For a 1-D tensor with <code>axis = 0</code>, computes
   *
   * <pre>
   *       output = x / sqrt(max(sum(x**2), epsilon))
   * </pre>
   *
   * <p>For <code>x</code> with more dimensions, independently normalizes each 1-D slice along
   * dimension <code>axis</code>.
   *
   * @param tf The TensorFlow ops
   * @param x The operand to normalize
   * @param axes Dimension(s) along which to normalize.
   * @param epsilon A lower bound value for the norm. Will use <code>sqrt(epsilon)</code> as the divisor if
   *     <code>norm &lt; sqrt(epsilon)</code>.
   * @param <U> The data type for the values.
   * @return the normalized values of x.
   */
  public static <U extends TNumber> Operand<U> l2Normalize(
      Ops tf, Operand<U> x, int[] axes, float epsilon) {
    Operand<U> squareSum =
        tf.reduceSum(tf.math.square(x), tf.constant(axes), ReduceSum.keepDims(Boolean.TRUE));
    Operand<U> y =
        tf.math.rsqrt(
            tf.math.maximum(squareSum, CastHelper.cast(tf, tf.constant(epsilon), x.type())));
    return tf.math.mul(x, y);
  }
}
