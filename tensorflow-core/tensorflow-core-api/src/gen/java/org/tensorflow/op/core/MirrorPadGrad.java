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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Gradient op for {@code MirrorPad} op. This op folds a mirror-padded tensor.
 * This operation folds the padded areas of {@code input} by {@code MirrorPad} according to the
 * {@code paddings} you specify. {@code paddings} must be the same as {@code paddings} argument
 * given to the corresponding {@code MirrorPad} op.
 * <p>The folded size of each dimension D of the output is:
 * <p>{@code input.dim_size(D) - paddings(D, 0) - paddings(D, 1)}
 * <p>For example:
 * <pre>
 * # 't' is [[1, 2, 3], [4, 5, 6], [7, 8, 9]].
 * # 'paddings' is [[0, 1]], [0, 1]].
 * # 'mode' is SYMMETRIC.
 * # rank of 't' is 2.
 * pad(t, paddings) ==&gt; [[ 1,  5]
 *                       [11, 28]]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = MirrorPadGrad.OP_NAME,
    inputsClass = MirrorPadGrad.Inputs.class
)
public final class MirrorPadGrad<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MirrorPadGrad";

  private Output<T> output;

  public MirrorPadGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MirrorPadGrad operation.
   *
   * @param scope current scope
   * @param input The input tensor to be folded.
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   * rows must be the same as the rank of {@code input}.
   * @param mode The mode used in the {@code MirrorPad} op.
   * @param <T> data type for {@code MirrorPadGrad} output and operands
   * @return a new instance of MirrorPadGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> MirrorPadGrad<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> paddings, String mode) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MirrorPadGrad");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(paddings.asOutput());
    opBuilder.setAttr("mode", mode);
    return new MirrorPadGrad<>(opBuilder.build());
  }

  /**
   * Gets output.
   * The folded tensor.
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
      outputsClass = MirrorPadGrad.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<MirrorPadGrad<T>> {
    /**
     * The input tensor to be folded.
     */
    public final Operand<T> input;

    /**
     * A two-column matrix specifying the padding sizes. The number of
     * rows must be the same as the rank of {@code input}.
     */
    public final Operand<? extends TNumber> paddings;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tpaddings attribute
     */
    public final DataType Tpaddings;

    /**
     * The mode used in the `MirrorPad` op.
     */
    public final String mode;

    public Inputs(GraphOperation op) {
      super(new MirrorPadGrad<>(op), op, Arrays.asList("T", "Tpaddings", "mode"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      paddings = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tpaddings = op.attributes().getAttrType("Tpaddings");
      mode = op.attributes().getAttrString("mode");
    }
  }
}
