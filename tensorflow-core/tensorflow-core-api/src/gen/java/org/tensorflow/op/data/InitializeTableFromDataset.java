/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * The InitializeTableFromDataset operation
 */
@OpMetadata(
    opType = InitializeTableFromDataset.OP_NAME,
    inputsClass = InitializeTableFromDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class InitializeTableFromDataset extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "InitializeTableFromDataset";

  public InitializeTableFromDataset(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new InitializeTableFromDataset operation.
   *
   * @param scope current scope
   * @param tableHandle The tableHandle value
   * @param dataset The dataset value
   * @return a new instance of InitializeTableFromDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static InitializeTableFromDataset create(Scope scope, Operand<? extends TType> tableHandle,
      Operand<? extends TType> dataset) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "InitializeTableFromDataset");
    opBuilder.addInput(tableHandle.asOutput());
    opBuilder.addInput(dataset.asOutput());
    return new InitializeTableFromDataset(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = InitializeTableFromDataset.class
  )
  public static class Inputs extends RawOpInputs<InitializeTableFromDataset> {
    /**
     * The tableHandle input
     */
    public final Operand<? extends TType> tableHandle;

    /**
     * The dataset input
     */
    public final Operand<? extends TType> dataset;

    public Inputs(GraphOperation op) {
      super(new InitializeTableFromDataset(op), op, Arrays.asList());
      int inputIndex = 0;
      tableHandle = (Operand<? extends TType>) op.input(inputIndex++);
      dataset = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
