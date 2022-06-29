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

package org.tensorflow.op.xla;

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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Make a static dimension into a xla bounded dynamic dimension.
 * <pre>
 *     The current static dimension size will become the bound and the second
 *     operand becomes the dynamic size of the dimension.
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = SetDynamicDimensionSize.OP_NAME,
    inputsClass = SetDynamicDimensionSize.Inputs.class
)
@Operator(
    group = "xla"
)
public final class SetDynamicDimensionSize<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSetDynamicDimensionSize";

  private Output<T> output;

  public SetDynamicDimensionSize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSetDynamicDimensionSize operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param dimIndex The dimIndex value
   * @param sizeOutput The sizeOutput value
   * @param <T> data type for {@code XlaSetDynamicDimensionSize} output and operands
   * @return a new instance of SetDynamicDimensionSize
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SetDynamicDimensionSize<T> create(Scope scope, Operand<T> input,
      Operand<TInt32> dimIndex, Operand<TInt32> sizeOutput) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SetDynamicDimensionSize");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(dimIndex.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    return new SetDynamicDimensionSize<>(opBuilder.build());
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
      outputsClass = SetDynamicDimensionSize.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SetDynamicDimensionSize<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The dimIndex input
     */
    public final Operand<TInt32> dimIndex;

    /**
     * The sizeOutput input
     */
    public final Operand<TInt32> sizeOutput;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SetDynamicDimensionSize<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      dimIndex = (Operand<TInt32>) op.input(inputIndex++);
      sizeOutput = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
