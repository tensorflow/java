/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TType;

/**
 * Computes the trignometric inverse sine of x element-wise.
 * The {@code tf.math.asin} operation returns the inverse of {@code tf.math.sin}, such that
 * if {@code y = tf.math.sin(x)} then, {@code x = tf.math.asin(y)}.
 * <p><strong>Note</strong>: The output of {@code tf.math.asin} will lie within the invertible range
 * of sine, i.e [-pi/2, pi/2].
 * <p>For example:
 * <pre>
 * # Note: [1.047, 0.785] ~= [(pi/3), (pi/4)]
 * x = tf.constant([1.047, 0.785])
 * y = tf.math.sin(x) # [0.8659266, 0.7068252]
 *
 * tf.math.asin(y) # [1.047, 0.785] = x
 * </pre>
 */
@OpMetadata(
    opType = Asin.OP_NAME,
    inputsClass = Asin.Inputs.class
)
@Operator(
    group = "math"
)
public final class Asin<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Asin";

  private Output<T> y;

  public Asin(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Asin operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param <T> data type for {@code Asin} output and operands
   * @return a new instance of Asin
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Asin<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Asin");
    opBuilder.addInput(x.asOutput());
    return new Asin<>(opBuilder.build());
  }

  /**
   * Gets y.
   *
   * @return y.
   */
  public Output<T> y() {
    return y;
  }

  @Override
  public Output<T> asOutput() {
    return y;
  }

  @OpInputsMetadata(
      outputsClass = Asin.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Asin<T>> {
    /**
     * The x input
     */
    public final Operand<T> x;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Asin<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
