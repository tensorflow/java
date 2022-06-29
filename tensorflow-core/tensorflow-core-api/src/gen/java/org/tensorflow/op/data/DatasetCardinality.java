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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Returns the cardinality of {@code input_dataset}.
 * Returns the cardinality of {@code input_dataset}.
 */
@OpMetadata(
    opType = DatasetCardinality.OP_NAME,
    inputsClass = DatasetCardinality.Inputs.class
)
@Operator(
    group = "data"
)
public final class DatasetCardinality extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DatasetCardinality";

  private Output<TInt64> cardinality;

  public DatasetCardinality(Operation operation) {
    super(operation, OP_NAME);
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
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DatasetCardinality");
    opBuilder.addInput(inputDataset.asOutput());
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

  @OpInputsMetadata(
      outputsClass = DatasetCardinality.class
  )
  public static class Inputs extends RawOpInputs<DatasetCardinality> {
    /**
     * A variant tensor representing the dataset to return cardinality for.
     */
    public final Operand<? extends TType> inputDataset;

    public Inputs(GraphOperation op) {
      super(new DatasetCardinality(op), op, Arrays.asList());
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
