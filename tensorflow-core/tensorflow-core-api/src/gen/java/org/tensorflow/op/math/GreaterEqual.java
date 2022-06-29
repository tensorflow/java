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

package org.tensorflow.op.math;

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
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;

/**
 * Returns the truth value of (x &gt;= y) element-wise.
 * <em>NOTE</em>: {@code math.GreaterEqual} supports broadcasting. More about broadcasting
 *  <a href="http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html">here</a> 
 * <p>Example:
 * <pre>
 * x = tf.constant([5, 4, 6, 7])
 * y = tf.constant([5, 2, 5, 10])
 * tf.math.greater_equal(x, y) ==&gt; [True, True, True, False]
 *
 * x = tf.constant([5, 4, 6, 7])
 * y = tf.constant([5])
 * tf.math.greater_equal(x, y) ==&gt; [True, False, True, True]
 * </pre>
 */
@OpMetadata(
    opType = GreaterEqual.OP_NAME,
    inputsClass = GreaterEqual.Inputs.class
)
@Operator(
    group = "math"
)
public final class GreaterEqual extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "GreaterEqual";

  private Output<TBool> z;

  public GreaterEqual(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GreaterEqual operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param y The y value
   * @param <T> data type for {@code GreaterEqual} output and operands
   * @return a new instance of GreaterEqual
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> GreaterEqual create(Scope scope, Operand<T> x, Operand<T> y) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "GreaterEqual");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    return new GreaterEqual(opBuilder.build());
  }

  /**
   * Gets z.
   *
   * @return z.
   */
  public Output<TBool> z() {
    return z;
  }

  @Override
  public Output<TBool> asOutput() {
    return z;
  }

  @OpInputsMetadata(
      outputsClass = GreaterEqual.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<GreaterEqual> {
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
      super(new GreaterEqual(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      y = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
