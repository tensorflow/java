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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Elementwise computes the bitwise AND of {@code x} and {@code y}.
 * The result will have those bits set, that are set in both {@code x} and {@code y}. The
 * computation is performed on the underlying representations of {@code x} and {@code y}.
 * <p>For example:
 * <pre>
 * import tensorflow as tf
 * from tensorflow.python.ops import bitwise_ops
 * dtype_list = [tf.int8, tf.int16, tf.int32, tf.int64,
 *               tf.uint8, tf.uint16, tf.uint32, tf.uint64]
 *
 * for dtype in dtype_list:
 *   lhs = tf.constant([0, 5, 3, 14], dtype=dtype)
 *   rhs = tf.constant([5, 0, 7, 11], dtype=dtype)
 *   exp = tf.constant([0, 0, 3, 10], dtype=tf.float32)
 *
 *   res = bitwise_ops.bitwise_and(lhs, rhs)
 *   tf.assert_equal(tf.cast(res, tf.float32), exp) # TRUE
 * </pre>
 *
 * @param <T> data type for {@code z} output
 */
@OpMetadata(
    opType = BitwiseAnd.OP_NAME,
    inputsClass = BitwiseAnd.Inputs.class
)
@Operator(
    group = "bitwise"
)
public final class BitwiseAnd<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BitwiseAnd";

  private Output<T> z;

  public BitwiseAnd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BitwiseAnd operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param y The y value
   * @param <T> data type for {@code BitwiseAnd} output and operands
   * @return a new instance of BitwiseAnd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> BitwiseAnd<T> create(Scope scope, Operand<T> x, Operand<T> y) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BitwiseAnd");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    return new BitwiseAnd<>(opBuilder.build());
  }

  /**
   * Gets z.
   *
   * @return z.
   */
  public Output<T> z() {
    return z;
  }

  @Override
  public Output<T> asOutput() {
    return z;
  }

  @OpInputsMetadata(
      outputsClass = BitwiseAnd.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<BitwiseAnd<T>> {
    /**
     * The x input
     */
    public final Operand<T> x;

    /**
     * The y input
     */
    public final Operand<T> y;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new BitwiseAnd<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      y = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
