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
 * Elementwise computes the bitwise OR of `x` and `y`.
 * <p>
 * The result will have those bits set, that are set in `x`, `y` or both. The
 * computation is performed on the underlying representations of `x` and `y`.
 * <p>
 * For example:
 * <pre>{@code
 * import tensorflow as tf
 * from tensorflow.python.ops import bitwise_ops
 * dtype_list = [tf.int8, tf.int16, tf.int32, tf.int64,
 *               tf.uint8, tf.uint16, tf.uint32, tf.uint64]
 * 
 * for dtype in dtype_list:
 *   lhs = tf.constant([0, 5, 3, 14], dtype=dtype)
 *   rhs = tf.constant([5, 0, 7, 11], dtype=dtype)
 *   exp = tf.constant([5, 5, 7, 15], dtype=tf.float32)
 * 
 *   res = bitwise_ops.bitwise_or(lhs, rhs)
 *   tf.assert_equal(tf.cast(res,  tf.float32), exp)  # TRUE
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code z()} output
 */
@Operator(group = "bitwise")
public final class BitwiseOr<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new BitwiseOr operation.
   * 
   * @param scope current scope
   * @param x 
   * @param y 
   * @return a new instance of BitwiseOr
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> BitwiseOr<T> create(Scope scope, Operand<T> x, Operand<T> y) {
    OperationBuilder opBuilder = scope.env().opBuilder("BitwiseOr", scope.makeOpName("BitwiseOr"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new BitwiseOr<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> z() {
    return z;
  }
  
  @Override
  public Output<T> asOutput() {
    return z;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BitwiseOr";
  
  private Output<T> z;
  
  private BitwiseOr(Operation operation) {
    super(operation);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }
}
