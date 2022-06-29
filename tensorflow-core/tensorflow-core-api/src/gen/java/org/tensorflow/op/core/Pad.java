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
 * Pads a tensor.
 * This operation pads {@code input} according to the {@code paddings} and {@code constant_values}
 * you specify. {@code paddings} is an integer tensor with shape {@code [Dn, 2]}, where n is
 * the rank of {@code input}. For each dimension D of {@code input}, {@code paddings[D, 0]} indicates
 * how many padding values to add before the contents of {@code input} in that dimension,
 * and {@code paddings[D, 1]} indicates how many padding values to add after the contents
 * of {@code input} in that dimension. {@code constant_values} is a scalar tensor of the same
 * type as {@code input} that indicates the value to use for padding {@code input}.
 * <p>The padded size of each dimension D of the output is:
 * <p>{@code paddings(D, 0) + input.dim_size(D) + paddings(D, 1)}
 * <p>For example:
 * <pre>
 * # 't' is [[1, 1], [2, 2]]
 * # 'paddings' is [[1, 1], [2, 2]]
 * # 'constant_values' is 0
 * # rank of 't' is 2
 * pad(t, paddings) ==&gt; [[0, 0, 0, 0, 0, 0]
 *                       [0, 0, 1, 1, 0, 0]
 *                       [0, 0, 2, 2, 0, 0]
 *                       [0, 0, 0, 0, 0, 0]]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Pad.OP_NAME,
    inputsClass = Pad.Inputs.class
)
@Operator
public final class Pad<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "PadV2";

  private Output<T> output;

  public Pad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new PadV2 operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param paddings The paddings value
   * @param constantValues The constantValues value
   * @param <T> data type for {@code PadV2} output and operands
   * @return a new instance of Pad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Pad<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> paddings, Operand<T> constantValues) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Pad");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(paddings.asOutput());
    opBuilder.addInput(constantValues.asOutput());
    return new Pad<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
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
      outputsClass = Pad.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Pad<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The paddings input
     */
    public final Operand<? extends TNumber> paddings;

    /**
     * The constantValues input
     */
    public final Operand<T> constantValues;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tpaddings attribute
     */
    public final DataType Tpaddings;

    public Inputs(GraphOperation op) {
      super(new Pad<>(op), op, Arrays.asList("T", "Tpaddings"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      paddings = (Operand<? extends TNumber>) op.input(inputIndex++);
      constantValues = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tpaddings = op.attributes().getAttrType("Tpaddings");
    }
  }
}
