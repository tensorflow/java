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

package org.tensorflow.op.data;

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
import org.tensorflow.types.family.TType;

/**
 * The WrapDatasetVariant operation
 */
@OpMetadata(
    opType = WrapDatasetVariant.OP_NAME,
    inputsClass = WrapDatasetVariant.Inputs.class
)
@Operator(
    group = "data"
)
public final class WrapDatasetVariant extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "WrapDatasetVariant";

  private Output<? extends TType> outputHandle;

  @SuppressWarnings("unchecked")
  public WrapDatasetVariant(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new WrapDatasetVariant operation.
   *
   * @param scope current scope
   * @param inputHandle The inputHandle value
   * @return a new instance of WrapDatasetVariant
   */
  @Endpoint(
      describeByClass = true
  )
  public static WrapDatasetVariant create(Scope scope, Operand<? extends TType> inputHandle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "WrapDatasetVariant");
    opBuilder.addInput(inputHandle.asOutput());
    return new WrapDatasetVariant(opBuilder.build());
  }

  /**
   * Gets outputHandle.
   *
   * @return outputHandle.
   */
  public Output<? extends TType> outputHandle() {
    return outputHandle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) outputHandle;
  }

  @OpInputsMetadata(
      outputsClass = WrapDatasetVariant.class
  )
  public static class Inputs extends RawOpInputs<WrapDatasetVariant> {
    /**
     * The inputHandle input
     */
    public final Operand<? extends TType> inputHandle;

    public Inputs(GraphOperation op) {
      super(new WrapDatasetVariant(op), op, Arrays.asList());
      int inputIndex = 0;
      inputHandle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
