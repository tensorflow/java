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

package org.tensorflow.op.signal;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * The BatchIFFT2D operation
 */
@Operator(
    group = "signal"
)
public final class BatchIfft2d extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BatchIFFT2D";

  private Output<? extends TType> output;

  @SuppressWarnings("unchecked")
  private BatchIfft2d(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BatchIFFT2D operation.
   *
   * @param scope current scope
   * @param input The input value
   * @return a new instance of BatchIfft2d
   */
  @Endpoint(
      describeByClass = true
  )
  public static BatchIfft2d create(Scope scope, Operand<? extends TType> input) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BatchIfft2d");
    opBuilder.addInput(input.asOutput());
    return new BatchIfft2d(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<? extends TType> output() {
    return output;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) output;
  }

  public static class Inputs extends RawOpInputs<BatchIfft2d> {
    /**
     * The input input
     */
    public final Operand<? extends TType> input;

    public Inputs(GraphOperation op) {
      super(new BatchIfft2d(op), op, Arrays.asList());
      int inputIndex = 0;
      input = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
