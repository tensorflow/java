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

/**
 * Elementwise computes the bitwise right-shift of `x` and `y`.
 * <p>
 * Performs a logical shift for unsigned integer types, and an arithmetic shift
 * for signed integer types.
 * <p>
 * If `y` is negative, or greater than or equal to than the width of `x` in bits
 * the result is implementation defined.
 * <p>
 * Example:
 * <pre>{@code
 * import tensorflow as tf
 * from tensorflow.python.ops import bitwise_ops
 * import numpy as np
 * dtype_list = [tf.int8, tf.int16, tf.int32, tf.int64]
 * 
 * for dtype in dtype_list:
 *   lhs = tf.constant([-1, -5, -3, -14], dtype=dtype)
 *   rhs = tf.constant([5, 0, 7, 11], dtype=dtype)
 * 
 *   right_shift_result = bitwise_ops.right_shift(lhs, rhs)
 * 
 *   print(right_shift_result)
 * 
 * # This will print:
 * # tf.Tensor([-1 -5 -1 -1], shape=(4,), dtype=int8)
 * # tf.Tensor([-1 -5 -1 -1], shape=(4,), dtype=int16)
 * # tf.Tensor([-1 -5 -1 -1], shape=(4,), dtype=int32)
 * # tf.Tensor([-1 -5 -1 -1], shape=(4,), dtype=int64)
 * 
 * lhs = np.array([-2, 64, 101, 32], dtype=np.int8)
 * rhs = np.array([-1, -5, -3, -14], dtype=np.int8)
 * bitwise_ops.right_shift(lhs, rhs)
 * # <tf.Tensor: shape=(4,), dtype=int8, numpy=array([ -2,  64, 101,  32], dtype=int8)>
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code z()} output
 */
@Operator(group = "bitwise")
public final class RightShift<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new RightShift operation.
   * 
   * @param scope current scope
   * @param x 
   * @param y 
   * @return a new instance of RightShift
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> RightShift<T> create(Scope scope, Operand<T> x, Operand<T> y) {
    OperationBuilder opBuilder = scope.env().opBuilder("RightShift", scope.makeOpName("RightShift"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new RightShift<T>(opBuilder.build());
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
  public static final String OP_NAME = "RightShift";
  
  private Output<T> z;
  
  private RightShift(Operation operation) {
    super(operation);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }
}
