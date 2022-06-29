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

package org.tensorflow.op.linalg;

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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * The BatchMatrixBandPart operation
 *
 * @param <T> data type for {@code band} output
 */
@OpMetadata(
    opType = BatchMatrixBandPart.OP_NAME,
    inputsClass = BatchMatrixBandPart.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class BatchMatrixBandPart<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BatchMatrixBandPart";

  private Output<T> band;

  public BatchMatrixBandPart(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    band = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BatchMatrixBandPart operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param numLower The numLower value
   * @param numUpper The numUpper value
   * @param <T> data type for {@code BatchMatrixBandPart} output and operands
   * @return a new instance of BatchMatrixBandPart
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> BatchMatrixBandPart<T> create(Scope scope, Operand<T> input,
      Operand<TInt64> numLower, Operand<TInt64> numUpper) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BatchMatrixBandPart");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(numLower.asOutput());
    opBuilder.addInput(numUpper.asOutput());
    return new BatchMatrixBandPart<>(opBuilder.build());
  }

  /**
   * Gets band.
   *
   * @return band.
   */
  public Output<T> band() {
    return band;
  }

  @Override
  public Output<T> asOutput() {
    return band;
  }

  @OpInputsMetadata(
      outputsClass = BatchMatrixBandPart.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<BatchMatrixBandPart<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The numLower input
     */
    public final Operand<TInt64> numLower;

    /**
     * The numUpper input
     */
    public final Operand<TInt64> numUpper;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new BatchMatrixBandPart<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      numLower = (Operand<TInt64>) op.input(inputIndex++);
      numUpper = (Operand<TInt64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
