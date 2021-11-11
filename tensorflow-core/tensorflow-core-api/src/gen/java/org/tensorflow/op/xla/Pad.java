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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Wraps the XLA Pad operator, documented at
 * https://www.tensorflow.org/performance/xla/operation_semantics#pad
 * .
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Pad.OP_NAME,
    inputsClass = Pad.Inputs.class
)
@Operator(
    group = "xla"
)
public final class Pad<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaPad";

  private Output<T> output;

  public Pad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaPad operation.
   *
   * @param scope current scope
   * @param input A {@code Tensor} of type T.
   * @param paddingValue A scalar {@code Tensor} of type T.
   * @param paddingLow the padding to apply at the start of each input dimensions. Must
   * be a compile-time constant 1D tensor of length equal to rank of input.
   * @param paddingHigh the padding to apply at the end of each input dimension. Must
   * be a compile-time constant 1D tensor of length equal to rank of input.
   * @param paddingInterior the padding to apply between each input element. Must
   * be a compile-time constant 1D tensor of length equal to rank of input,
   * containing only non-negative values.
   * @param <T> data type for {@code XlaPad} output and operands
   * @param <U> data type for {@code XlaPad} output and operands
   * @return a new instance of Pad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TNumber> Pad<T> create(Scope scope, Operand<T> input,
      Operand<T> paddingValue, Operand<U> paddingLow, Operand<U> paddingHigh,
      Operand<U> paddingInterior) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Pad");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(paddingValue.asOutput());
    opBuilder.addInput(paddingLow.asOutput());
    opBuilder.addInput(paddingHigh.asOutput());
    opBuilder.addInput(paddingInterior.asOutput());
    return new Pad<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A {@code Tensor} of type T.
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
  public static class Inputs<T extends TType, U extends TNumber> extends RawOpInputs<Pad<T>> {
    /**
     * A {@code Tensor} of type T.
     */
    public final Operand<T> input;

    /**
     * A scalar {@code Tensor} of type T.
     */
    public final Operand<T> paddingValue;

    /**
     * the padding to apply at the start of each input dimensions. Must
     * be a compile-time constant 1D tensor of length equal to rank of input.
     */
    public final Operand<U> paddingLow;

    /**
     * the padding to apply at the end of each input dimension. Must
     * be a compile-time constant 1D tensor of length equal to rank of input.
     */
    public final Operand<U> paddingHigh;

    /**
     * the padding to apply between each input element. Must
     * be a compile-time constant 1D tensor of length equal to rank of input,
     * containing only non-negative values.
     */
    public final Operand<U> paddingInterior;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    public Inputs(GraphOperation op) {
      super(new Pad<>(op), op, Arrays.asList("T", "Tindices"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      paddingValue = (Operand<T>) op.input(inputIndex++);
      paddingLow = (Operand<U>) op.input(inputIndex++);
      paddingHigh = (Operand<U>) op.input(inputIndex++);
      paddingInterior = (Operand<U>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
