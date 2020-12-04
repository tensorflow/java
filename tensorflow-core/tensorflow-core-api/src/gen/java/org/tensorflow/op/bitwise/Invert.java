/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

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

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.bitwise;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Invert (flip) each bit of supported types; for example, type `uint8` value 01010101 becomes 10101010.
 * <p>
 * Flip each bit of supported types.  For example, type `int8` (decimal 2) binary 00000010 becomes (decimal -3) binary 11111101.
 * This operation is performed on each element of the tensor argument `x`.
 * <p>
 * Example:
 * <pre>{@code
 * import tensorflow as tf
 * from tensorflow.python.ops import bitwise_ops
 * 
 * # flip 2 (00000010) to -3 (11111101)
 * tf.assert_equal(-3, bitwise_ops.invert(2))
 * 
 * dtype_list = [dtypes.int8, dtypes.int16, dtypes.int32, dtypes.int64,
 *               dtypes.uint8, dtypes.uint16, dtypes.uint32, dtypes.uint64]
 * 
 * inputs = [0, 5, 3, 14]
 * for dtype in dtype_list:
 *   # Because of issues with negative numbers, let's test this indirectly.
 *   # 1. invert(a) and a = 0
 *   # 2. invert(a) or a = invert(0)
 *   input_tensor = tf.constant([0, 5, 3, 14], dtype=dtype)
 *   not_a_and_a, not_a_or_a, not_0 = [bitwise_ops.bitwise_and(
 *                                       input_tensor, bitwise_ops.invert(input_tensor)),
 *                                     bitwise_ops.bitwise_or(
 *                                       input_tensor, bitwise_ops.invert(input_tensor)),
 *                                     bitwise_ops.invert(
 *                                       tf.constant(0, dtype=dtype))]
 * 
 *   expected = tf.constant([0, 0, 0, 0], dtype=tf.float32)
 *   tf.assert_equal(tf.cast(not_a_and_a, tf.float32), expected)
 * 
 *   expected = tf.cast([not_0] * 4, tf.float32)
 *   tf.assert_equal(tf.cast(not_a_or_a, tf.float32), expected)
 * 
 *   # For unsigned dtypes let's also check the result directly.
 *   if dtype.is_unsigned:
 *     inverted = bitwise_ops.invert(input_tensor)
 *     expected = tf.constant([dtype.max - x for x in inputs], dtype=tf.float32)
 *     tf.assert_equal(tf.cast(inverted, tf.float32), tf.cast(expected, tf.float32))
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code y()} output
 */
@Operator(group = "bitwise")
public final class Invert<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Invert operation.
   * 
   * @param scope current scope
   * @param x 
   * @return a new instance of Invert
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> Invert<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.env().opBuilder("Invert", scope.makeOpName("Invert"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Invert<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> y() {
    return y;
  }
  
  @Override
  public Output<T> asOutput() {
    return y;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Invert";
  
  private Output<T> y;
  
  private Invert(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }
}
