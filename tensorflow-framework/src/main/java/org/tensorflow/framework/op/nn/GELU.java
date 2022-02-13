/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.framework.op.nn;

import org.tensorflow.Operand;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.Div;
import org.tensorflow.op.math.Erf;
import org.tensorflow.op.math.Mul;
import org.tensorflow.op.math.Pow;
import org.tensorflow.op.math.Tanh;
import org.tensorflow.types.family.TNumber;

/**
 * The Gaussian Error Linear Unit (GELU) activation function.
 *
 * <p>Gaussian error linear unit (GELU) computes {@code x * P(X <= x)}, where {@code P(X) ~ N(0,
 * 1)}. The (GELU) nonlinearity weights inputs by their value, rather than gates inputs by their
 * sign as in ReLU.
 *
 * @see <a href="https://arxiv.org/abs/1606.08415">Gaussian Error Linear Units (GELUs)</a>
 */
@Operator(group = "nn")
public class GELU {

  /**
   * Compute the Gaussian Error Linear Unit (GELU) activation function without approximation.
   *
   * @param scope The TensorFlow scope
   * @param input the input
   * @param <T> the data type for the input and result
   * @return The Gaussian Error Linear Unit
   */
  @Endpoint(name = "gelu")
  public static <T extends TNumber> Operand<T> gelu(Scope scope, Operand<T> input) {
    return gelu(scope, input, false);
  }

  /**
   * Compute the Gaussian Error Linear Unit (GELU) activation function.
   *
   * @param scope The TensorFlow scope
   * @param input the input
   * @param approximate Whether to enable approximation.
   * @param <T> the data type for the input and result
   * @return The Gaussian Error Linear Unit computation
   */
  @Endpoint(name = "gelu")
  public static <T extends TNumber> Operand<T> gelu(
      Scope scope, Operand<T> input, boolean approximate) {
    Cast<T> point5 = Cast.create(scope, Constant.scalarOf(scope, 0.5), input.type());
    Cast<T> one = Cast.create(scope, Constant.scalarOf(scope, 1.0), input.type());
    Mul<T> inputMul = Mul.create(scope, point5, input);
    if (approximate) {
      Operand<T> coeff = Cast.create(scope, Constant.scalarOf(scope, 0.044715), input.type());
      Operand<T> tanhMul =
          Cast.create(scope, Constant.scalarOf(scope, 0.7978845608028654), input.type());
      Operand<T> three = Cast.create(scope, Constant.scalarOf(scope, 3), input.type());
      return Mul.create(
          scope,
          inputMul,
          Add.create(
              scope,
              one,
              Tanh.create(
                  scope,
                  Mul.create(
                      scope,
                      tanhMul,
                      Add.create(
                          scope,
                          input,
                          Mul.create(scope, coeff, Pow.create(scope, input, three)))))));

    } else {
      Operand<T> mulConstant =
          Cast.create(scope, Constant.scalarOf(scope, 1.4142135623730951), input.type());

      return Mul.create(
          scope,
          inputMul,
          Add.create(scope, one, Erf.create(scope, Div.create(scope, input, mulConstant))));
    }
  }
}
