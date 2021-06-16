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
 * Returns the cardinality of {@code input_dataset}.
 * Returns the cardinality of {@code input_dataset}.
 */
public final class DatasetCardinality extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DatasetCardinality";

  private Output<TInt64> cardinality;

  private DatasetCardinality(Operation operation) {
    super(operation);
    int outputIdx = 0;
    cardinality = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DatasetCardinality operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the dataset to return cardinality for.
   * @return a new instance of DatasetCardinality
   */
  @Endpoint(
      describeByClass = true
  )
  public static DatasetCardinality create(Scope scope, Operand<? extends TType> inputDataset) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("DatasetCardinality"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new DatasetCardinality(opBuilder.build());
  }

  /**
   * Gets cardinality.
   * The cardinality of {@code input_dataset}. Named constants are used to represent
   * infinite and unknown cardinality.
   * @return cardinality.
   */
  public Output<TInt64> cardinality() {
    return cardinality;
  }

  @Override
  public Output<TInt64> asOutput() {
    return cardinality;
  }
}
