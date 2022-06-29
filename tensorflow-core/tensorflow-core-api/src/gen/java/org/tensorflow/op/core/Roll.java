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

package org.tensorflow.op.core;

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
import org.tensorflow.types.family.TType;

/**
 * Rolls the elements of a tensor along an axis.
 * The elements are shifted positively (towards larger indices) by the offset of
 * {@code shift} along the dimension of {@code axis}. Negative {@code shift} values will shift
 * elements in the opposite direction. Elements that roll passed the last position
 * will wrap around to the first and vice versa. Multiple shifts along multiple
 * axes may be specified.
 * <p>For example:
 * <pre>
 * # 't' is [0, 1, 2, 3, 4]
 * roll(t, shift=2, axis=0) ==&gt; [3, 4, 0, 1, 2]
 *
 * # shifting along multiple dimensions
 * # 't' is [[0, 1, 2, 3, 4], [5, 6, 7, 8, 9]]
 * roll(t, shift=[1, -2], axis=[0, 1]) ==&gt; [[7, 8, 9, 5, 6], [2, 3, 4, 0, 1]]
 *
 * # shifting along the same axis multiple times
 * # 't' is [[0, 1, 2, 3, 4], [5, 6, 7, 8, 9]]
 * roll(t, shift=[2, -3], axis=[1, 1]) ==&gt; [[1, 2, 3, 4, 0], [6, 7, 8, 9, 5]]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Roll.OP_NAME,
    inputsClass = Roll.Inputs.class
)
@Operator
public final class Roll<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Roll";

  private Output<T> output;

  public Roll(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Roll operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param shift Dimension must be 0-D or 1-D. {@code shift[i]} specifies the number of places by which
   * elements are shifted positively (towards larger indices) along the dimension
   * specified by {@code axis[i]}. Negative shifts will roll the elements in the opposite
   * direction.
   * @param axis Dimension must be 0-D or 1-D. {@code axis[i]} specifies the dimension that the shift
   * {@code shift[i]} should occur. If the same axis is referenced more than once, the
   * total shift for that axis will be the sum of all the shifts that belong to that
   * axis.
   * @param <T> data type for {@code Roll} output and operands
   * @return a new instance of Roll
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Roll<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> shift, Operand<? extends TNumber> axis) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Roll");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(shift.asOutput());
    opBuilder.addInput(axis.asOutput());
    return new Roll<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Has the same shape and size as the input. The elements are shifted
   * positively (towards larger indices) by the offsets of {@code shift} along the
   * dimensions of {@code axis}.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = Roll.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Roll<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * Dimension must be 0-D or 1-D. {@code shift[i]} specifies the number of places by which
     * elements are shifted positively (towards larger indices) along the dimension
     * specified by {@code axis[i]}. Negative shifts will roll the elements in the opposite
     * direction.
     */
    public final Operand<? extends TNumber> shift;

    /**
     * Dimension must be 0-D or 1-D. {@code axis[i]} specifies the dimension that the shift
     * {@code shift[i]} should occur. If the same axis is referenced more than once, the
     * total shift for that axis will be the sum of all the shifts that belong to that
     * axis.
     */
    public final Operand<? extends TNumber> axis;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tshift attribute
     */
    public final DataType Tshift;

    /**
     * The Taxis attribute
     */
    public final DataType Taxis;

    public Inputs(GraphOperation op) {
      super(new Roll<>(op), op, Arrays.asList("T", "Tshift", "Taxis"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      shift = (Operand<? extends TNumber>) op.input(inputIndex++);
      axis = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tshift = op.attributes().getAttrType("Tshift");
      Taxis = op.attributes().getAttrType("Taxis");
    }
  }
}
