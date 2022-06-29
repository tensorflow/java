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

package org.tensorflow.op.train;

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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Computes the static batch size of a dataset sans partial batches.
 */
@OpMetadata(
    opType = ComputeBatchSize.OP_NAME,
    inputsClass = ComputeBatchSize.Inputs.class
)
public final class ComputeBatchSize extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ComputeBatchSize";

  private Output<TInt64> batchSize;

  public ComputeBatchSize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    batchSize = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ComputeBatchSize operation.
   *
   * @param scope current scope
   * @param inputDataset The inputDataset value
   * @return a new instance of ComputeBatchSize
   */
  @Endpoint(
      describeByClass = true
  )
  public static ComputeBatchSize create(Scope scope, Operand<? extends TType> inputDataset) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ComputeBatchSize");
    opBuilder.addInput(inputDataset.asOutput());
    return new ComputeBatchSize(opBuilder.build());
  }

  /**
   * Gets batchSize.
   *
   * @return batchSize.
   */
  public Output<TInt64> batchSize() {
    return batchSize;
  }

  @Override
  public Output<TInt64> asOutput() {
    return batchSize;
  }

  @OpInputsMetadata(
      outputsClass = ComputeBatchSize.class
  )
  public static class Inputs extends RawOpInputs<ComputeBatchSize> {
    /**
     * The inputDataset input
     */
    public final Operand<? extends TType> inputDataset;

    public Inputs(GraphOperation op) {
      super(new ComputeBatchSize(op), op, Arrays.asList());
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
