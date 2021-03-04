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
import org.tensorflow.Session;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;

import java.util.Arrays;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Applies the Gaussian error linear unit (GELU) activation function.
 *
 * <p>Gaussian error linear unit (GELU) computes {@code x * P(X <= x)}, where {@code P(X) ~ N(0,
 * 1)}. The (GELU) nonlinearity weights inputs by their value, rather than gates inputs by their
 * sign as in ReLU. if <code>approximate</code> is <code>true</code> :
 *
 * <pre>
 *     0.5 * x * (1 + tanh(sqrt(2 / pi) * (x + 0.044715 * x^3)))
 * </pre>
 *
 * <p>or, if <code>approximate</code> is <code>false</code>.
 *
 * <pre>
 *     x * P(X <= x) = 0.5 * x * (1 + erf(x / sqrt(2))),
 * </pre>
 *
 * where <code>P(X) ~ N(0, 1)</code>.
 *
 * @see <a href="https://arxiv.org/abs/1606.08415">Hendrycks, Dan and Gimpel, Kevin, 2016-2020,
 *     Gaussian Error Linear Units (GELUs)</a>
 */
// TFloating
public class GeLU extends Activation {

  private final boolean approximate;

  /**
   * Creates a e Gaussian error linear unit (GELU) activation, with approximate set to false
   *
   * @param tf The TensorFlow ops
   */
  public GeLU(Ops tf) {
    this(tf, false);
  }

  /**
   * Creates a e Gaussian error linear unit (GELU) activation
   *
   * @param tf The TensorFlow ops
   * @param approximate indicator whether to enable approximation.
   */
  public GeLU(Ops tf, boolean approximate) {
    super(tf);
    this.approximate = approximate;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Operand<T> input) {
    if (!TFloating.class.isAssignableFrom(input.type())) {
      throw new IllegalArgumentException(
          "Tensor type must be numeric or boolean: " + input.type().getSimpleName());
    }
    if (approximate) {
      /*
              coeff = math_ops.cast(0.044715, features.dtype)
              return 0.5 * features * (
                    1.0 + math_ops.tanh(0.7978845608028654 *
                              (features + coeff * math_ops.pow(features, 3))))
      */
      Operand<T> coeff = cast(tf, tf.constant(0.044715), input.type());
      Operand<T> point5 = cast(tf, tf.constant(0.5), input.type());
      Operand<T> one = cast(tf, tf.constant(1.0), input.type());

      return tf.math.mul(
          point5,
          tf.math.mul(
              input,
              tf.math.add(
                  one,
                  tf.math.tanh(
                      tf.math.mul(
                          // sqrt(2.0 / PI)
                          cast(tf, tf.constant(0.7978845608028654), input.type()),
                          tf.math.add(
                              input,
                              tf.math.mul(
                                  coeff,
                                  tf.math.pow(input, cast(tf, tf.constant(3), input.type()))) // mul
                              ) // add
                          ) // mul
                      ) // tanh
                  ) // add
              ) // mul
          ); // mul

    } else {
      /*
      return 0.5 * features * (1.0 + math_ops.erf(
        features / math_ops.cast(1.4142135623730951, features.dtype)))
       */
      return tf.math.mul(
          cast(tf, tf.constant(0.5), input.type()),
          tf.math.mul(
              input,
              tf.math.add(
                  cast(tf, tf.constant(1), input.type()),
                  tf.math.erf(
                      tf.math.div(
                          input, cast(tf, tf.constant(1.4142135623730951), input.type()))))));
    }
  }

}
