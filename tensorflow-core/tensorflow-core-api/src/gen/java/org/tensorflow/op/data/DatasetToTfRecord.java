/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Writes the given dataset to the given file using the TFRecord format.
 */
@OpMetadata(
    opType = DatasetToTfRecord.OP_NAME,
    inputsClass = DatasetToTfRecord.Inputs.class
)
@Operator(
    group = "data"
)
public final class DatasetToTfRecord extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DatasetToTFRecord";

  public DatasetToTfRecord(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new DatasetToTFRecord operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the dataset to write.
   * @param filename A scalar string tensor representing the filename to use.
   * @param compressionType A scalar string tensor containing either (i) the empty string (no
   * compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
   * @return a new instance of DatasetToTfRecord
   */
  @Endpoint(
      describeByClass = true
  )
  public static DatasetToTfRecord create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TString> filename, Operand<TString> compressionType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DatasetToTfRecord");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(filename.asOutput());
    opBuilder.addInput(compressionType.asOutput());
    return new DatasetToTfRecord(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = DatasetToTfRecord.class
  )
  public static class Inputs extends RawOpInputs<DatasetToTfRecord> {
    /**
     * A variant tensor representing the dataset to write.
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * A scalar string tensor representing the filename to use.
     */
    public final Operand<TString> filename;

    /**
     * A scalar string tensor containing either (i) the empty string (no
     * compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
     */
    public final Operand<TString> compressionType;

    public Inputs(GraphOperation op) {
      super(new DatasetToTfRecord(op), op, Arrays.asList());
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      filename = (Operand<TString>) op.input(inputIndex++);
      compressionType = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
