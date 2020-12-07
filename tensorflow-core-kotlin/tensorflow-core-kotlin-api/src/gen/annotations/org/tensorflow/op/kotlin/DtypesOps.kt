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
package org.tensorflow.op.kotlin

import org.tensorflow.DataType
import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.dtypes.AsString
import org.tensorflow.op.dtypes.Cast
import org.tensorflow.op.dtypes.Complex
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building `dtypes` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class DtypesOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.DtypesOps = ops.java.dtypes

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Converts each entry in the given tensor to strings.
     *
     *  Supports many numeric types and boolean.
     *
     *  For Unicode, see the
     *  &#91;https://www.tensorflow.org/tutorials/representation/unicode](Working with Unicode
     * text)
     *  tutorial.
     *
     *  Examples:
     *
     *  >>> tf.strings.as_string(&#91;3, 2])
     *  <tf.Tensor: shape=(2,), dtype=string, numpy=array(&#91;b'3', b'2'], dtype=object)>
     *  >>> tf.strings.as_string(&#91;3.1415926, 2.71828], precision=2).numpy()
     *  array(&#91;b'3.14', b'2.72'], dtype=object)
     *
     * @param input
     * @param options carries optional attributes values
     * @return a new instance of AsString
     * @see org.tensorflow.op.DtypesOps.asString
     * @param precision The post-decimal precision to use for floating point numbers.
     *  Only used if precision > -1.
     * @param scientific Use scientific notation for floating point numbers.
     * @param shortest Use shortest representation (either scientific or standard) for
     *  floating point numbers.
     * @param width Pad pre-decimal numbers to this width.
     *  Applies to both floating point and integer numbers.
     *  Only used if width > -1.
     * @param fill The value to pad if width > -1.  If empty, pads with spaces.
     *  Another typical value is '0'.  String cannot be longer than 1 character.
     */
    public fun <T : TType> asString(
        input: Operand<T>,
        precision: Long? = null,
        scientific: Boolean? = null,
        shortest: Boolean? = null,
        width: Long? = null,
        fill: String? = null
    ): AsString = java.asString<T>(
        input,
        *listOfNotNull(
            precision?.let { org.tensorflow.op.dtypes.AsString.precision(it) },
            scientific?.let { org.tensorflow.op.dtypes.AsString.scientific(it) },
            shortest?.let { org.tensorflow.op.dtypes.AsString.shortest(it) },
            width?.let { org.tensorflow.op.dtypes.AsString.width(it) },
            fill?.let { org.tensorflow.op.dtypes.AsString.fill(it) }
        ).toTypedArray()
    )

    /**
     * Cast x of type SrcT to y of DstT.
     *
     * @param U data type for ` y()` output
     * @param x
     * @param DstT
     * @param options carries optional attributes values
     * @return a new instance of Cast
     * @see org.tensorflow.op.DtypesOps.cast
     * @param Truncate @param Truncate
     */
    public fun <U : TType, T : TType> cast(
        x: Operand<T>,
        DstT: DataType<U>,
        Truncate: Boolean? = null
    ): Cast<U> = java.cast<U, T>(
        x,
        DstT,
        *listOfNotNull(
            Truncate?.let { org.tensorflow.op.dtypes.Cast.Truncate(it) }
        ).toTypedArray()
    )

    /**
     * Converts two real numbers to a complex number.
     *
     *  Given a tensor `real` representing the real part of a complex number, and a
     *  tensor `imag` representing the imaginary part of a complex number, this
     *  operation returns complex numbers elementwise of the form \\(a + bj\\), where
     *  <i>a</i> represents the `real` part and <i>b</i> represents the `imag` part.
     *
     *  The input tensors `real` and `imag` must have the same shape.
     *
     *  For example:
     *  ```
     *  # tensor 'real' is [2.25, 3.25]
     *  # tensor `imag` is [4.75, 5.75]
     *  tf.complex(real, imag) ==> [[2.25 + 4.75j], [3.25 + 5.75j]]
     *  ```
     *
     *
     * @param U data type for ` out()` output
     * @param real
     * @param imag
     * @param Tout
     * @return a new instance of Complex
     * @see org.tensorflow.op.DtypesOps.complex
     */
    public fun <U : TType, T : TNumber> complex(
        real: Operand<T>,
        imag: Operand<T>,
        Tout: DataType<U>
    ): Complex<U> = java.complex<U, T>(
        real,
        imag,
        Tout
    )
}
