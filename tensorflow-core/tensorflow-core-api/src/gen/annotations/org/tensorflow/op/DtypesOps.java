// Copyright 2020 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.dtypes.AsString;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.dtypes.Complex;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code dtypes} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class DtypesOps {
  private final Scope scope;

  private final Ops ops;

  DtypesOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Converts each entry in the given tensor to strings.
   *  <p>
   *  Supports many numeric types and boolean.
   *  <p>
   *  For Unicode, see the
   *  [https://www.tensorflow.org/tutorials/representation/unicode](Working with Unicode text)
   *  tutorial.
   *  <p>
   *  Examples:
   *  <p>
   *  >>> tf.strings.as_string([3, 2])
   *  <tf.Tensor: shape=(2,), dtype=string, numpy=array([b'3', b'2'], dtype=object)>
   *  >>> tf.strings.as_string([3.1415926, 2.71828], precision=2).numpy()
   *  array([b'3.14', b'2.72'], dtype=object)
   *
   * @param input
   * @param options carries optional attributes values
   * @return a new instance of AsString
   */
  public <T extends TType> AsString asString(Operand<T> input, AsString.Options... options) {
    return AsString.create(scope, input, options);
  }

  /**
   * Cast x of type SrcT to y of DstT.
   *
   * @param <U> data type for {@code y()} output
   * @param x
   * @param DstT
   * @param options carries optional attributes values
   * @return a new instance of Cast
   */
  public <U extends TType, T extends TType> Cast<U> cast(Operand<T> x, DataType<U> DstT,
      Cast.Options... options) {
    return Cast.create(scope, x, DstT, options);
  }

  /**
   * Converts two real numbers to a complex number.
   *  <p>
   *  Given a tensor `real` representing the real part of a complex number, and a
   *  tensor `imag` representing the imaginary part of a complex number, this
   *  operation returns complex numbers elementwise of the form \\(a + bj\\), where
   *  <i>a</i> represents the `real` part and <i>b</i> represents the `imag` part.
   *  <p>
   *  The input tensors `real` and `imag` must have the same shape.
   *  <p>
   *  For example:
   *  <pre>{@code
   *  # tensor 'real' is [2.25, 3.25]
   *  # tensor `imag` is [4.75, 5.75]
   *  tf.complex(real, imag) ==> [[2.25 + 4.75j], [3.25 + 5.75j]]
   *  }</pre>
   *
   * @param <U> data type for {@code out()} output
   * @param real
   * @param imag
   * @param Tout
   * @return a new instance of Complex
   */
  public <U extends TType, T extends TNumber> Complex<U> complex(Operand<T> real, Operand<T> imag,
      DataType<U> Tout) {
    return Complex.create(scope, real, imag, Tout);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
