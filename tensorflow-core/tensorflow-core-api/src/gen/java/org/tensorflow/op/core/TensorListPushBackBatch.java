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
import org.tensorflow.types.family.TType;

/**
 * The TensorListPushBackBatch operation
 */
@OpMetadata(
    opType = TensorListPushBackBatch.OP_NAME,
    inputsClass = TensorListPushBackBatch.Inputs.class
)
@Operator
public final class TensorListPushBackBatch extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListPushBackBatch";

  private Output<? extends TType> outputHandles;

  @SuppressWarnings("unchecked")
  public TensorListPushBackBatch(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputHandles = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListPushBackBatch operation.
   *
   * @param scope current scope
   * @param inputHandles The inputHandles value
   * @param tensor The tensor value
   * @return a new instance of TensorListPushBackBatch
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorListPushBackBatch create(Scope scope, Operand<? extends TType> inputHandles,
      Operand<? extends TType> tensor) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorListPushBackBatch");
    opBuilder.addInput(inputHandles.asOutput());
    opBuilder.addInput(tensor.asOutput());
    return new TensorListPushBackBatch(opBuilder.build());
  }

  /**
   * Gets outputHandles.
   *
   * @return outputHandles.
   */
  public Output<? extends TType> outputHandles() {
    return outputHandles;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) outputHandles;
  }

  @OpInputsMetadata(
      outputsClass = TensorListPushBackBatch.class
  )
  public static class Inputs extends RawOpInputs<TensorListPushBackBatch> {
    /**
     * The inputHandles input
     */
    public final Operand<? extends TType> inputHandles;

    /**
     * The tensor input
     */
    public final Operand<? extends TType> tensor;

    /**
     * The elementDtype attribute
     */
    public final DataType elementDtype;

    public Inputs(GraphOperation op) {
      super(new TensorListPushBackBatch(op), op, Arrays.asList("element_dtype"));
      int inputIndex = 0;
      inputHandles = (Operand<? extends TType>) op.input(inputIndex++);
      tensor = (Operand<? extends TType>) op.input(inputIndex++);
      elementDtype = op.attributes().getAttrType("element_dtype");
    }
  }
}
