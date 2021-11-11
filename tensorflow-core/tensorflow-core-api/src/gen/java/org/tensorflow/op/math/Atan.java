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
import org.tensorflow.types.family.TType;

/**
 * Computes the trignometric inverse tangent of x element-wise.
 * The {@code tf.math.atan} operation returns the inverse of {@code tf.math.tan}, such that
 * if {@code y = tf.math.tan(x)} then, {@code x = tf.math.atan(y)}.
 * <p><strong>Note</strong>: The output of {@code tf.math.atan} will lie within the invertible range
 * of tan, i.e (-pi/2, pi/2).
 * <p>For example:
 * <pre>
 * # Note: [1.047, 0.785] ~= [(pi/3), (pi/4)]
 * x = tf.constant([1.047, 0.785])
 * y = tf.math.tan(x) # [1.731261, 0.99920404]
 *
 * tf.math.atan(y) # [1.047, 0.785] = x
 * </pre>
 *
 * @param <T> data type for {@code y} output
 */
@OpMetadata(
    opType = Atan.OP_NAME,
    inputsClass = Atan.Inputs.class
)
@Operator(
    group = "math"
)
public final class Atan<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Atan";

  private Output<T> y;

  public Atan(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Atan operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param <T> data type for {@code Atan} output and operands
   * @return a new instance of Atan
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Atan<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Atan");
    opBuilder.addInput(x.asOutput());
    return new Atan<>(opBuilder.build());
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
      outputsClass = Atan.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Atan<T>> {
    /**
     * The x input
     */
    public final Operand<T> x;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Atan<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
