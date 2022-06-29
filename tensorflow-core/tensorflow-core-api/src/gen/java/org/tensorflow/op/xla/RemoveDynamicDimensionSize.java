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
 * Inverse of XlaSetDynamicDimensionSize.
 * Make an xla bounded dynamic dimension into a static dimension. The bound of the
 * size of dimension {@code dim_index} becomes the static dimension size.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RemoveDynamicDimensionSize.OP_NAME,
    inputsClass = RemoveDynamicDimensionSize.Inputs.class
)
@Operator(
    group = "xla"
)
public final class RemoveDynamicDimensionSize<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaRemoveDynamicDimensionSize";

  private Output<T> output;

  public RemoveDynamicDimensionSize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaRemoveDynamicDimensionSize operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param dimIndex The dimIndex value
   * @param <T> data type for {@code XlaRemoveDynamicDimensionSize} output and operands
   * @return a new instance of RemoveDynamicDimensionSize
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> RemoveDynamicDimensionSize<T> create(Scope scope,
      Operand<T> input, Operand<TInt32> dimIndex) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RemoveDynamicDimensionSize");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(dimIndex.asOutput());
    return new RemoveDynamicDimensionSize<>(opBuilder.build());
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
      outputsClass = RemoveDynamicDimensionSize.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<RemoveDynamicDimensionSize<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The dimIndex input
     */
    public final Operand<TInt32> dimIndex;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new RemoveDynamicDimensionSize<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      dimIndex = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
