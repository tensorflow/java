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
 * Pads a tensor with mirrored values.
 * This operation pads a {@code input} with mirrored values according to the {@code paddings}
 * you specify. {@code paddings} is an integer tensor with shape {@code [n, 2]}, where n is
 * the rank of {@code input}. For each dimension D of {@code input}, {@code paddings[D, 0]} indicates
 * how many values to add before the contents of {@code input} in that dimension, and
 * {@code paddings[D, 1]} indicates how many values to add after the contents of {@code input}
 * in that dimension. Both {@code paddings[D, 0]} and {@code paddings[D, 1]} must be no greater
 * than {@code input.dim_size(D)} (or {@code input.dim_size(D) - 1}) if {@code copy_border} is true
 * (if false, respectively).
 * <p>The padded size of each dimension D of the output is:
 * <p>{@code paddings(D, 0) + input.dim_size(D) + paddings(D, 1)}
 * <p>For example:
 * <pre>
 * # 't' is [[1, 2, 3], [4, 5, 6]].
 * # 'paddings' is [[1, 1]], [2, 2]].
 * # 'mode' is SYMMETRIC.
 * # rank of 't' is 2.
 * pad(t, paddings) ==&gt; [[2, 1, 1, 2, 3, 3, 2]
 *                       [2, 1, 1, 2, 3, 3, 2]
 *                       [5, 4, 4, 5, 6, 6, 5]
 *                       [5, 4, 4, 5, 6, 6, 5]]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = MirrorPad.OP_NAME,
    inputsClass = MirrorPad.Inputs.class
)
@Operator
public final class MirrorPad<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MirrorPad";

  private Output<T> output;

  public MirrorPad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MirrorPad operation.
   *
   * @param scope current scope
   * @param input The input tensor to be padded.
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   * rows must be the same as the rank of {@code input}.
   * @param mode Either {@code REFLECT} or {@code SYMMETRIC}. In reflect mode the padded regions
   * do not include the borders, while in symmetric mode the padded regions
   * do include the borders. For example, if {@code input} is {@code [1, 2, 3]} and {@code paddings}
   * is {@code [0, 2]}, then the output is {@code [1, 2, 3, 2, 1]} in reflect mode, and
   * it is {@code [1, 2, 3, 3, 2]} in symmetric mode.
   * @param <T> data type for {@code MirrorPad} output and operands
   * @return a new instance of MirrorPad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> MirrorPad<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> paddings, String mode) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MirrorPad");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(paddings.asOutput());
    opBuilder.setAttr("mode", mode);
    return new MirrorPad<>(opBuilder.build());
  }

  /**
   * Gets output.
   * The padded tensor.
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
      outputsClass = MirrorPad.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<MirrorPad<T>> {
    /**
     * The input tensor to be padded.
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
     * Either `REFLECT` or `SYMMETRIC`. In reflect mode the padded regions
     * do not include the borders, while in symmetric mode the padded regions
     * do include the borders. For example, if `input` is `[1, 2, 3]` and `paddings`
     * is `[0, 2]`, then the output is `[1, 2, 3, 2, 1]` in reflect mode, and
     * it is `[1, 2, 3, 3, 2]` in symmetric mode.
     */
    public final String mode;

    public Inputs(GraphOperation op) {
      super(new MirrorPad<>(op), op, Arrays.asList("T", "Tpaddings", "mode"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      paddings = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tpaddings = op.attributes().getAttrType("Tpaddings");
      mode = op.attributes().getAttrString("mode");
    }
  }
}
