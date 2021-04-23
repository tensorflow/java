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
import org.tensorflow.op.bitwise.BitwiseAnd
import org.tensorflow.op.bitwise.BitwiseOr
import org.tensorflow.op.bitwise.BitwiseXor
import org.tensorflow.op.bitwise.Invert
import org.tensorflow.op.bitwise.LeftShift
import org.tensorflow.op.bitwise.RightShift
import org.tensorflow.types.family.TNumber

/**
 * An API for building `bitwise` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class BitwiseOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.BitwiseOps = ops.java.bitwise

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Elementwise computes the bitwise AND of `x` and `y`.
     *  The result will have those bits set, that are set in both `x` and `y`. The
     *  computation is performed on the underlying representations of `x` and `y`.
     *
     * For example:
     *  ```
     * import tensorflow as tf
     *  from tensorflow.python.ops import bitwise_ops
     *  dtype_list = [tf.int8, tf.int16, tf.int32, tf.int64,
     *                tf.uint8, tf.uint16, tf.uint32, tf.uint64]
     *
     *  for dtype in dtype_list:
     *    lhs = tf.constant([0, 5, 3, 14], dtype=dtype)
     *    rhs = tf.constant([5, 0, 7, 11], dtype=dtype)
     *    exp = tf.constant([0, 0, 3, 10], dtype=tf.float32)
     *
     *    res = bitwise_ops.bitwise_and(lhs, rhs)
     *    tf.assert_equal(tf.cast(res, tf.float32), exp) # TRUE
     *
     * ```
     *
     * @param <T> data type for `z` output
     * @param x the x value
     * @param y the y value
     * @param <T> data type for `BitwiseAnd` output and operands
     * @return a new instance of BitwiseAnd
     * @see org.tensorflow.op.BitwiseOps.bitwiseAnd
     */
    public fun <T : TNumber> bitwiseAnd(x: Operand<T>, y: Operand<T>): BitwiseAnd<T> =
        java.bitwiseAnd<T>(
            x,
            y
        )

    /**
     * Elementwise computes the bitwise OR of `x` and `y`.
     *  The result will have those bits set, that are set in `x`, `y` or both. The
     *  computation is performed on the underlying representations of `x` and `y`.
     *
     * For example:
     *  ```
     * import tensorflow as tf
     *  from tensorflow.python.ops import bitwise_ops
     *  dtype_list = [tf.int8, tf.int16, tf.int32, tf.int64,
     *                tf.uint8, tf.uint16, tf.uint32, tf.uint64]
     *
     *  for dtype in dtype_list:
     *    lhs = tf.constant([0, 5, 3, 14], dtype=dtype)
     *    rhs = tf.constant([5, 0, 7, 11], dtype=dtype)
     *    exp = tf.constant([5, 5, 7, 15], dtype=tf.float32)
     *
     *    res = bitwise_ops.bitwise_or(lhs, rhs)
     *    tf.assert_equal(tf.cast(res,  tf.float32), exp)  # TRUE
     *
     * ```
     *
     * @param <T> data type for `z` output
     * @param x the x value
     * @param y the y value
     * @param <T> data type for `BitwiseOr` output and operands
     * @return a new instance of BitwiseOr
     * @see org.tensorflow.op.BitwiseOps.bitwiseOr
     */
    public fun <T : TNumber> bitwiseOr(x: Operand<T>, y: Operand<T>): BitwiseOr<T> =
        java.bitwiseOr<T>(
            x,
            y
        )

    /**
     * Elementwise computes the bitwise XOR of `x` and `y`.
     *  The result will have those bits set, that are different in `x` and `y`. The
     *  computation is performed on the underlying representations of `x` and `y`.
     *
     * For example:
     *  ```
     * import tensorflow as tf
     *  from tensorflow.python.ops import bitwise_ops
     *  dtype_list = [tf.int8, tf.int16, tf.int32, tf.int64,
     *                tf.uint8, tf.uint16, tf.uint32, tf.uint64]
     *
     *  for dtype in dtype_list:
     *    lhs = tf.constant([0, 5, 3, 14], dtype=dtype)
     *    rhs = tf.constant([5, 0, 7, 11], dtype=dtype)
     *    exp = tf.constant([5, 5, 4, 5],  dtype=tf.float32)
     *
     *    res = bitwise_ops.bitwise_xor(lhs, rhs)
     *    tf.assert_equal(tf.cast(res, tf.float32), exp) # TRUE
     *
     * ```
     *
     * @param <T> data type for `z` output
     * @param x the x value
     * @param y the y value
     * @param <T> data type for `BitwiseXor` output and operands
     * @return a new instance of BitwiseXor
     * @see org.tensorflow.op.BitwiseOps.bitwiseXor
     */
    public fun <T : TNumber> bitwiseXor(x: Operand<T>, y: Operand<T>): BitwiseXor<T> =
        java.bitwiseXor<T>(
            x,
            y
        )

    /**
     * Invert (flip) each bit of supported types; for example, type `uint8` value 01010101 becomes
     * 10101010.
     *  Flip each bit of supported types.  For example, type `int8` (decimal 2) binary 00000010
     * becomes (decimal -3) binary 11111101.
     *  This operation is performed on each element of the tensor argument `x`.
     *
     * Example:
     *  ```
     * import tensorflow as tf
     *  from tensorflow.python.ops import bitwise_ops
     *
     *  # flip 2 (00000010) to -3 (11111101)
     *  tf.assert_equal(-3, bitwise_ops.invert(2))
     *
     *  dtype_list = [dtypes.int8, dtypes.int16, dtypes.int32, dtypes.int64,
     *                dtypes.uint8, dtypes.uint16, dtypes.uint32, dtypes.uint64]
     *
     *  inputs = [0, 5, 3, 14]
     *  for dtype in dtype_list:
     *    # Because of issues with negative numbers, let's test this indirectly.
     *    # 1. invert(a) and a = 0
     *    # 2. invert(a) or a = invert(0)
     *    input_tensor = tf.constant([0, 5, 3, 14], dtype=dtype)
     *    not_a_and_a, not_a_or_a, not_0 = [bitwise_ops.bitwise_and(
     *                                        input_tensor, bitwise_ops.invert(input_tensor)),
     *                                      bitwise_ops.bitwise_or(
     *                                        input_tensor, bitwise_ops.invert(input_tensor)),
     *                                      bitwise_ops.invert(
     *                                        tf.constant(0, dtype=dtype))]
     *
     *    expected = tf.constant([0, 0, 0, 0], dtype=tf.float32)
     *    tf.assert_equal(tf.cast(not_a_and_a, tf.float32), expected)
     *
     *    expected = tf.cast([not_0] * 4, tf.float32)
     *    tf.assert_equal(tf.cast(not_a_or_a, tf.float32), expected)
     *
     *    # For unsigned dtypes let's also check the result directly.
     *    if dtype.is_unsigned:
     *      inverted = bitwise_ops.invert(input_tensor)
     *      expected = tf.constant([dtype.max - x for x in inputs], dtype=tf.float32)
     *      tf.assert_equal(tf.cast(inverted, tf.float32), tf.cast(expected, tf.float32))
     *
     * ```
     *
     * @param <T> data type for `y` output
     * @param x the x value
     * @param <T> data type for `Invert` output and operands
     * @return a new instance of Invert
     * @see org.tensorflow.op.BitwiseOps.invert
     */
    public fun <T : TNumber> invert(x: Operand<T>): Invert<T> = java.invert<T>(
        x
    )

    /**
     * Elementwise computes the bitwise left-shift of `x` and `y`.
     *  If `y` is negative, or greater than or equal to the width of `x` in bits the
     *  result is implementation defined.
     *
     * Example:
     *  ```
     * import tensorflow as tf
     *  from tensorflow.python.ops import bitwise_ops
     *  import numpy as np
     *  dtype_list = [tf.int8, tf.int16, tf.int32, tf.int64]
     *
     *  for dtype in dtype_list:
     *    lhs = tf.constant([-1, -5, -3, -14], dtype=dtype)
     *    rhs = tf.constant([5, 0, 7, 11], dtype=dtype)
     *
     *    left_shift_result = bitwise_ops.left_shift(lhs, rhs)
     *
     *    print(left_shift_result)
     *
     *  # This will print:
     *  # tf.Tensor([ -32   -5 -128    0], shape=(4,), dtype=int8)
     *  # tf.Tensor([   -32     -5   -384 -28672], shape=(4,), dtype=int16)
     *  # tf.Tensor([   -32     -5   -384 -28672], shape=(4,), dtype=int32)
     *  # tf.Tensor([   -32     -5   -384 -28672], shape=(4,), dtype=int64)
     *
     *  lhs = np.array([-2, 64, 101, 32], dtype=np.int8)
     *  rhs = np.array([-1, -5, -3, -14], dtype=np.int8)
     *  bitwise_ops.left_shift(lhs, rhs)
     *  # <tf.Tensor: shape=(4,), dtype=int8, numpy=array([ -2,  64, 101,  32], dtype=int8)>
     *
     * ```
     *
     * @param <T> data type for `z` output
     * @param x the x value
     * @param y the y value
     * @param <T> data type for `LeftShift` output and operands
     * @return a new instance of LeftShift
     * @see org.tensorflow.op.BitwiseOps.leftShift
     */
    public fun <T : TNumber> leftShift(x: Operand<T>, y: Operand<T>): LeftShift<T> =
        java.leftShift<T>(
            x,
            y
        )

    /**
     * Elementwise computes the bitwise right-shift of `x` and `y`.
     *  Performs a logical shift for unsigned integer types, and an arithmetic shift
     *  for signed integer types.
     *
     * If `y` is negative, or greater than or equal to than the width of `x` in bits
     *  the result is implementation defined.
     *
     * Example:
     *  ```
     * import tensorflow as tf
     *  from tensorflow.python.ops import bitwise_ops
     *  import numpy as np
     *  dtype_list = [tf.int8, tf.int16, tf.int32, tf.int64]
     *
     *  for dtype in dtype_list:
     *    lhs = tf.constant([-1, -5, -3, -14], dtype=dtype)
     *    rhs = tf.constant([5, 0, 7, 11], dtype=dtype)
     *
     *    right_shift_result = bitwise_ops.right_shift(lhs, rhs)
     *
     *    print(right_shift_result)
     *
     *  # This will print:
     *  # tf.Tensor([-1 -5 -1 -1], shape=(4,), dtype=int8)
     *  # tf.Tensor([-1 -5 -1 -1], shape=(4,), dtype=int16)
     *  # tf.Tensor([-1 -5 -1 -1], shape=(4,), dtype=int32)
     *  # tf.Tensor([-1 -5 -1 -1], shape=(4,), dtype=int64)
     *
     *  lhs = np.array([-2, 64, 101, 32], dtype=np.int8)
     *  rhs = np.array([-1, -5, -3, -14], dtype=np.int8)
     *  bitwise_ops.right_shift(lhs, rhs)
     *  # <tf.Tensor: shape=(4,), dtype=int8, numpy=array([ -2,  64, 101,  32], dtype=int8)>
     *
     * ```
     *
     * @param <T> data type for `z` output
     * @param x the x value
     * @param y the y value
     * @param <T> data type for `RightShift` output and operands
     * @return a new instance of RightShift
     * @see org.tensorflow.op.BitwiseOps.rightShift
     */
    public fun <T : TNumber> rightShift(x: Operand<T>, y: Operand<T>): RightShift<T> =
        java.rightShift<T>(
            x,
            y
        )
}
