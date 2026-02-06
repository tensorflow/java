// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
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

import org.tensorflow.Operand;
import org.tensorflow.op.dtypes.AsString;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.dtypes.Complex;
import org.tensorflow.op.dtypes.ToBool;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code dtypes} operations as {@link Op Op}s
 *
 * @see Ops
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
   *  Supports many numeric types and boolean.
   *  <p>For Unicode, see the
   *  [https://www.tensorflow.org/text/guide/unicode](Working with Unicode text)
   *  tutorial.
   *  <p>Examples:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>tf.strings.as_string([3, 2])
   *  &lt;tf.Tensor: shape=(2,), dtype=string, numpy=array([b'3', b'2'], dtype=object)&gt;
   *  tf.strings.as_string([3.1415926, 2.71828], precision=2).numpy()
   *  array([b'3.14', b'2.72'], dtype=object)
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param input The input value
   * @param options carries optional attribute values
   * @return a new instance of AsString
   */
  public AsString asString(Operand<? extends TType> input, AsString.Options... options) {
    return AsString.create(scope, input, options);
  }

  /**
   * Cast x of type SrcT to y of DstT.
   *
   * @param x The x value
   * @param DstT The value of the DstT attribute
   * @param options carries optional attribute values
   * @param <U> data type for {@code Cast} output and operands
   * @return a new instance of Cast
   */
  public <U extends TType> Cast<U> cast(Operand<? extends TType> x, Class<U> DstT,
      Cast.Options... options) {
    return Cast.create(scope, x, DstT, options);
  }

  /**
   * Converts two real numbers to a complex number.
   *  Given a tensor {@code real} representing the real part of a complex number, and a
   *  tensor {@code imag} representing the imaginary part of a complex number, this
   *  operation returns complex numbers elementwise of the form \(a + bj\), where
   *  <em>a</em> represents the {@code real} part and <em>b</em> represents the {@code imag} part.
   *  <p>The input tensors {@code real} and {@code imag} must have the same shape.
   *  <p>For example:
   *  <pre>
   *  # tensor 'real' is [2.25, 3.25]
   *  # tensor `imag` is [4.75, 5.75]
   *  tf.complex(real, imag) ==&gt; [[2.25 + 4.75j], [3.25 + 5.75j]]
   *  </pre>
   *
   * @param real The real value
   * @param imag The imag value
   * @param Tout The value of the Tout attribute
   * @param <U> data type for {@code Complex} output and operands
   * @param <T> data type for {@code Complex} output and operands
   * @return a new instance of Complex
   */
  public <U extends TType, T extends TNumber> Complex<U> complex(Operand<T> real, Operand<T> imag,
      Class<U> Tout) {
    return Complex.create(scope, real, imag, Tout);
  }

  /**
   * Converts a tensor to a scalar predicate.
   *  Converts a tensor to a scalar predicate with the following rules:
   *  <ul>
   *  <li>
   *  <p>For 0D tensors, truthiness is determined by comparing against a &quot;zero&quot;
   *  value. For numerical types it is the obvious zero. For strings it is the
   *  empty string.
   *  </li>
   *  <li>
   *  <p>For &gt;0D tensors, truthiness is determined by looking at the number of
   *  elements. If has zero elements, then the result is false. Otherwise the
   *  result is true.
   *  </li>
   *  </ul>
   *  <p>This matches the behavior of If and While for determining if a tensor counts
   *  as true/false for a branch condition.
   *
   * @param input The input value
   * @return a new instance of ToBool
   */
  public ToBool toBool(Operand<? extends TType> input) {
    return ToBool.create(scope, input);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
