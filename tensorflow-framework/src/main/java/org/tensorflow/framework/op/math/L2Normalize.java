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
package org.tensorflow.framework.op.math;

import org.tensorflow.Operand;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.ReduceSum;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.math.Maximum;
import org.tensorflow.op.math.Mul;
import org.tensorflow.op.math.Rsqrt;
import org.tensorflow.op.math.Square;
import org.tensorflow.types.family.TNumber;

/** L2 Normalization Operations */
public class L2Normalize {

  /**
   * Normalizes along dimension axis using an L2 norm.
   *
   * @param scope The TensorFlow scope
   * @param x the input
   * @param axis Dimension along which to normalize.
   * @param <T> the data type for the input and the result
   * @return the normalized values based on L2 norm
   */
  public static <T extends TNumber> Operand<T> l2Normalize(Scope scope, Operand<T> x, int[] axis) {
    Operand<T> squareSum =
        ReduceSum.create(
            scope,
            Square.create(scope, x),
            Constant.vectorOf(scope, axis),
            ReduceSum.keepDims(Boolean.TRUE));
    Operand<T> invNorm =
        Rsqrt.create(
            scope,
            Maximum.create(
                scope, squareSum, Cast.create(scope, Constant.scalarOf(scope, 1e-12F), x.type())));
    return Mul.create(scope, x, invNorm);
  }
}
