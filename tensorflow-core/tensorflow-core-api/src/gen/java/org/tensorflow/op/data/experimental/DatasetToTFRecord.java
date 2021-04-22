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

package org.tensorflow.op.data.experimental;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Writes the given dataset to the given file using the TFRecord format.
 */
public final class DatasetToTFRecord extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExperimentalDatasetToTFRecord";

  private DatasetToTFRecord(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new ExperimentalDatasetToTFRecord operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the dataset to write.
   * @param filename A scalar string tensor representing the filename to use.
   * @param compressionType A scalar string tensor containing either (i) the empty string (no
   * compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
   * @return a new instance of DatasetToTFRecord
   */
  @Endpoint(
      describeByClass = true
  )
  public static DatasetToTFRecord create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TString> filename, Operand<TString> compressionType) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExperimentalDatasetToTFRecord", scope.makeOpName("DatasetToTFRecord"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(filename.asOutput());
    opBuilder.addInput(compressionType.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new DatasetToTFRecord(opBuilder.build());
  }
}
