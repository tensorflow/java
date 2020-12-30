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
package org.tensorflow.framework.activations;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.op.math.Greater;
import org.tensorflow.op.nn.LeakyRelu;
import org.tensorflow.types.family.TNumber;

/**
 * Rectified Linear Unit(ReLU) activation.
 *
 * <p>With default values, this returns the standard ReLU activation: <code>max(x, 0)</code>, the
 * element-wise maximum of 0 and the input tensor.
 *
 * <p>Modifying default parameters allows you to use non-zero thresholds, change the max value of
 * the activation, and to use a non-zero multiple of the input for values below the threshold.
 *
 * <p>For example:
 *
 * <pre>
 *     Operand&lt;TFloat32&gt; input = tf.constant(
 *              new float[] {-10f, -5f, 0.0f, 5f, 10f});
 *
 *     // With default parameters
 *     ReLU&lt;TFloat32&gt; relu = new ReLU&lt;&gt;(tf);
 *     Operand&lt;TFloat32&gt; result = relu.call(input);
 *     // result is [0.f,  0.f,  0.f,  5.f, 10.f]
 *
 *     // With alpha = 0.5
 *     relu = new ReLU&lt;&gt;(tf, 0.5f, ReLU.MAX_VALUE_DEFAULT, ReLU.THRESHOLD_DEFAULT);
 *     result = relu.call(input);
 *     // result is [-5.f , -2.5f,  0.f ,  5.f , 10.f]
 *
 *     // With maxValue = 5
 *     relu = new ReLU&lt;&gt;(tf, ReLU.ALPHA_DEFAULT, 5f, ReLU.THRESHOLD_DEFAULT);
 *     result = relu.call(input);
 *     // result is [0.f, 0.f, 0.f, 5.f, 5.f]
 *
 *     // With threshold = 5
 *     relu = new ReLU&lt;&gt;(tf, ReLU.ALPHA_DEFAULT, ReLU.MAX_VALUE_DEFAULT, 5f);
 *     result = relu.call(input);
 *     // result is [-0.f, -0.f,  0.f,  0.f, 10.f]
 * </pre>
 *
 * @param <T> the data type of the result
 */
public class ReLU<T extends TNumber> extends Activation<T> {

  public static final float ALPHA_DEFAULT = 0.0f;
  public static final float MAX_VALUE_DEFAULT = Float.NaN;
  public static final float THRESHOLD_DEFAULT = 0.0f;

  private final float alpha;
  private final float maxValue;
  private final float threshold;

  /**
   * Creates a new ReLU with alpha={@link #ALPHA_DEFAULT}, maxValue={@link #MAX_VALUE_DEFAULT},
   * threshold={@link #THRESHOLD_DEFAULT},
   *
   * @param tf the TensorFlow Ops
   */
  public ReLU(Ops tf) {
    this(tf, ALPHA_DEFAULT, MAX_VALUE_DEFAULT, THRESHOLD_DEFAULT);
  }

  /**
   * Creates a new ReLU
   *
   * @param tf the TensorFlow Ops
   * @param alpha governs the slope for values lower than the threshold.
   * @param maxValue sets the saturation threshold (the largest value the function will return).
   * @param threshold the threshold value of the activation function below which values will be
   *     damped or set to zero.
   */
  public ReLU(Ops tf, float alpha, float maxValue, float threshold) {
    super(tf);
    this.alpha = alpha;
    this.maxValue = maxValue;
    this.threshold = threshold;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Operand<T> input) {
    Class<T> inputType = input.type();

    boolean clipMax = !Float.isNaN(maxValue);
    Operand<T> negativePart = null;
    if (alpha != 0) {
      if (Float.isNaN(maxValue) && threshold == 0) {
        return tf.nn.leakyRelu(input, LeakyRelu.alpha(alpha));
      }
      if (threshold != 0) {
        negativePart =
            tf.nn.relu(
                tf.math.add(tf.math.neg(input), tf.dtypes.cast(tf.constant(threshold), inputType)));
      } else {
        negativePart = tf.nn.relu(tf.math.neg(input));
      }
    }

    Operand<T> lInput;
    if (threshold != 0) {
      // computes input for input > threshold else 0
      Greater greater = tf.math.greater(input, tf.dtypes.cast(tf.constant(threshold), inputType));
      lInput = tf.math.mul(input, tf.dtypes.cast(greater, inputType));
    } else if (maxValue == 6) {
      // if no threshold, then can use nn.relu6 native TF op for performance
      lInput = tf.nn.relu6(input);
      clipMax = false;
    } else {
      lInput = tf.nn.relu(input);
    }
    if (clipMax) {
      Operand<T> lmaxValue = tf.dtypes.cast(tf.constant(maxValue), inputType);
      Operand<T> zero = tf.dtypes.cast(tf.constant(0), inputType);
      lInput = tf.clipByValue(lInput, zero, lmaxValue);
    }

    if (alpha != 0.) {
      lInput =
          tf.math.sub(
              lInput, tf.math.mul(tf.dtypes.cast(tf.constant(alpha), inputType), negativePart));
    }
    return lInput;
  }
}
