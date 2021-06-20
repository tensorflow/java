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

import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.dtypes.AsString
import org.tensorflow.op.dtypes.Cast
import org.tensorflow.op.dtypes.Complex
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType
import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.jvm.JvmName

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
     *  Supports many numeric types and boolean.
     *
     * For Unicode, see the
     *  &#91;https://www.tensorflow.org/tutorials/representation/unicode&#93;(Working with Unicode
     * text)
     *  tutorial.
     *
     * Examples:
     *  ```
     *
     * tf.strings.as_string([3, 2])
     *  <tf.Tensor: shape=(2,), dtype=string, numpy=array([b'3', b'2'], dtype=object)>
     *  tf.strings.as_string([3.1415926, 2.71828], precision=2).numpy()
     *  array([b'3.14', b'2.72'], dtype=object)
     * ```
     *
     * @param input the input value
     * @param options carries optional attribute values
     * @return a new instance of AsString
     * @see org.tensorflow.op.DtypesOps.asString
     * @param precision Sets the precision option.
     *
     * @param precision The post-decimal precision to use for floating point numbers.
     *  Only used if precision > -1.
     * @return this Options instance.
     * @param scientific Sets the scientific option.
     *
     * @param scientific Use scientific notation for floating point numbers.
     * @return this Options instance.
     * @param shortest Sets the shortest option.
     *
     * @param shortest Use shortest representation (either scientific or standard) for
     *  floating point numbers.
     * @return this Options instance.
     * @param width Sets the width option.
     *
     * @param width Pad pre-decimal numbers to this width.
     *  Applies to both floating point and integer numbers.
     *  Only used if width > -1.
     * @return this Options instance.
     * @param fill Sets the fill option.
     *
     * @param fill The value to pad if width > -1.  If empty, pads with spaces.
     *  Another typical value is '0'.  String cannot be longer than 1 character.
     * @return this Options instance.
     */
    public fun asString(
        input: Operand<out TType>,
        precision: Long? = null,
        scientific: Boolean? = null,
        shortest: Boolean? = null,
        width: Long? = null,
        fill: String? = null
    ): AsString = java.asString(
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
     * @param <U> data type for `y` output
     * @param x the x value
     * @param DstT the value of the DstT property
     * @param options carries optional attribute values
     * @param <U> data type for `Cast` output and operands
     * @return a new instance of Cast
     * @see org.tensorflow.op.DtypesOps.cast
     * @param Truncate Sets the Truncate option.
     *
     * @param Truncate the Truncate option
     * @return this Options instance.
     */
    public fun <U : TType> cast(
        x: Operand<out TType>,
        DstT: Class<U>,
        Truncate: Boolean? = null
    ): Cast<U> = java.cast<U>(
        x,
        DstT,
        *listOfNotNull(
            Truncate?.let { org.tensorflow.op.dtypes.Cast.Truncate(it) }
        ).toTypedArray()
    )

    /**
     * Converts two real numbers to a complex number.
     *  Given a tensor `real` representing the real part of a complex number, and a
     *  tensor `imag` representing the imaginary part of a complex number, this
     *  operation returns complex numbers elementwise of the form `\(a + bj\)`, where
     *  _a_ represents the `real` part and _b_ represents the `imag` part.
     *
     * The input tensors `real` and `imag` must have the same shape.
     *
     * For example:
     *  ```
     * # tensor 'real' is [2.25, 3.25]
     *  # tensor `imag` is [4.75, 5.75]
     *  tf.complex(real, imag) ==> [[2.25 + 4.75j], [3.25 + 5.75j]]
     *
     * ```
     *
     * @param <U> data type for `out` output
     * @param real the real value
     * @param imag the imag value
     * @param Tout the value of the Tout property
     * @param <U> data type for `Complex` output and operands
     * @param <T> data type for `Complex` output and operands
     * @return a new instance of Complex
     * @see org.tensorflow.op.DtypesOps.complex
     */
    public fun <U : TType, T : TNumber> complex(
        real: Operand<T>,
        imag: Operand<T>,
        Tout: Class<U>
    ): Complex<U> = java.complex<U, T>(
        real,
        imag,
        Tout
    )

    /**
     * Cast x of type SrcT to y of DstT.
     *
     * @param <U> data type for `y` output
     * @param x the x value
     * @param DstT the value of the DstT property
     * @param options carries optional attribute values
     * @param <U> data type for `Cast` output and operands
     * @return a new instance of Cast
     * @see org.tensorflow.op.DtypesOps.cast
     * @param Truncate Sets the Truncate option.
     *
     * @param Truncate the Truncate option
     * @return this Options instance.
     */
    @JvmName("castReified")
    public inline fun <reified U : TType> cast(x: Operand<out TType>, Truncate: Boolean? = null):
        Cast<U> = cast<U>(x, U::class.java, Truncate)

    /**
     * Converts two real numbers to a complex number.
     *  Given a tensor `real` representing the real part of a complex number, and a
     *  tensor `imag` representing the imaginary part of a complex number, this
     *  operation returns complex numbers elementwise of the form `\(a + bj\)`, where
     *  _a_ represents the `real` part and _b_ represents the `imag` part.
     *
     * The input tensors `real` and `imag` must have the same shape.
     *
     * For example:
     *  ```
     * # tensor 'real' is [2.25, 3.25]
     *  # tensor `imag` is [4.75, 5.75]
     *  tf.complex(real, imag) ==> [[2.25 + 4.75j], [3.25 + 5.75j]]
     *
     * ```
     *
     * @param <U> data type for `out` output
     * @param real the real value
     * @param imag the imag value
     * @param Tout the value of the Tout property
     * @param <U> data type for `Complex` output and operands
     * @param <T> data type for `Complex` output and operands
     * @return a new instance of Complex
     * @see org.tensorflow.op.DtypesOps.complex
     */
    @JvmName("complexReified")
    public inline fun <reified U : TType, T : TNumber> complex(real: Operand<T>, imag: Operand<T>):
        Complex<U> = complex<U, T>(real, imag, U::class.java)
}
