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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The CreateSummaryFileWriter operation
 */
public final class CreateSummaryFileWriter extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CreateSummaryFileWriter";

  private CreateSummaryFileWriter(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new CreateSummaryFileWriter operation.
   *
   * @param scope current scope
   * @param writer the writer value
   * @param logdir the logdir value
   * @param maxQueue the maxQueue value
   * @param flushMillis the flushMillis value
   * @param filenameSuffix the filenameSuffix value
   * @return a new instance of CreateSummaryFileWriter
   */
  @Endpoint(
      describeByClass = true
  )
  public static CreateSummaryFileWriter create(Scope scope, Operand<? extends TType> writer,
      Operand<TString> logdir, Operand<TInt32> maxQueue, Operand<TInt32> flushMillis,
      Operand<TString> filenameSuffix) {
    OperationBuilder opBuilder = scope.env().opBuilder("CreateSummaryFileWriter", scope.makeOpName("CreateSummaryFileWriter"));
    opBuilder.addInput(writer.asOutput());
    opBuilder.addInput(logdir.asOutput());
    opBuilder.addInput(maxQueue.asOutput());
    opBuilder.addInput(flushMillis.asOutput());
    opBuilder.addInput(filenameSuffix.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new CreateSummaryFileWriter(opBuilder.build());
  }
}
