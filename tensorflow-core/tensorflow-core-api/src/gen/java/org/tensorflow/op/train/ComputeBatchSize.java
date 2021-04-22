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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Computes the static batch size of a dataset sans partial batches.
 */
public final class ComputeBatchSize extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ComputeBatchSize";

  private Output<TInt64> batchSize;

  private ComputeBatchSize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    batchSize = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ComputeBatchSize operation.
   *
   * @param scope current scope
   * @param inputDataset the inputDataset value
   * @return a new instance of ComputeBatchSize
   */
  @Endpoint(
      describeByClass = true
  )
  public static ComputeBatchSize create(Scope scope, Operand<? extends TType> inputDataset) {
    OperationBuilder opBuilder = scope.env().opBuilder("ComputeBatchSize", scope.makeOpName("ComputeBatchSize"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder = scope.apply(opBuilder);
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
}
