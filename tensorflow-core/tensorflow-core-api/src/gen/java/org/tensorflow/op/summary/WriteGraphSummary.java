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

package org.tensorflow.op.summary;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Writes a graph summary.
 * Writes TensorFlow graph {@code tensor} at {@code step} using summary {@code writer}.
 */
@OpMetadata(
    opType = WriteGraphSummary.OP_NAME,
    inputsClass = WriteGraphSummary.Inputs.class
)
public final class WriteGraphSummary extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "WriteGraphSummary";

  public WriteGraphSummary(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new WriteGraphSummary operation.
   *
   * @param scope current scope
   * @param writer The writer value
   * @param step The step value
   * @param tensor The tensor value
   * @return a new instance of WriteGraphSummary
   */
  @Endpoint(
      describeByClass = true
  )
  public static WriteGraphSummary create(Scope scope, Operand<? extends TType> writer,
      Operand<TInt64> step, Operand<TString> tensor) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "WriteGraphSummary");
    opBuilder.addInput(writer.asOutput());
    opBuilder.addInput(step.asOutput());
    opBuilder.addInput(tensor.asOutput());
    return new WriteGraphSummary(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = WriteGraphSummary.class
  )
  public static class Inputs extends RawOpInputs<WriteGraphSummary> {
    /**
     * The writer input
     */
    public final Operand<? extends TType> writer;

    /**
     * The step input
     */
    public final Operand<TInt64> step;

    /**
     * The tensor input
     */
    public final Operand<TString> tensor;

    public Inputs(GraphOperation op) {
      super(new WriteGraphSummary(op), op, Arrays.asList());
      int inputIndex = 0;
      writer = (Operand<? extends TType>) op.input(inputIndex++);
      step = (Operand<TInt64>) op.input(inputIndex++);
      tensor = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
