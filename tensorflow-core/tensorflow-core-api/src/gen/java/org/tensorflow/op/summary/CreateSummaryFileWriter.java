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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The CreateSummaryFileWriter operation
 */
@OpMetadata(
    opType = CreateSummaryFileWriter.OP_NAME,
    inputsClass = CreateSummaryFileWriter.Inputs.class
)
public final class CreateSummaryFileWriter extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CreateSummaryFileWriter";

  public CreateSummaryFileWriter(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new CreateSummaryFileWriter operation.
   *
   * @param scope current scope
   * @param writer The writer value
   * @param logdir The logdir value
   * @param maxQueue The maxQueue value
   * @param flushMillis The flushMillis value
   * @param filenameSuffix The filenameSuffix value
   * @return a new instance of CreateSummaryFileWriter
   */
  @Endpoint(
      describeByClass = true
  )
  public static CreateSummaryFileWriter create(Scope scope, Operand<? extends TType> writer,
      Operand<TString> logdir, Operand<TInt32> maxQueue, Operand<TInt32> flushMillis,
      Operand<TString> filenameSuffix) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CreateSummaryFileWriter");
    opBuilder.addInput(writer.asOutput());
    opBuilder.addInput(logdir.asOutput());
    opBuilder.addInput(maxQueue.asOutput());
    opBuilder.addInput(flushMillis.asOutput());
    opBuilder.addInput(filenameSuffix.asOutput());
    return new CreateSummaryFileWriter(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = CreateSummaryFileWriter.class
  )
  public static class Inputs extends RawOpInputs<CreateSummaryFileWriter> {
    /**
     * The writer input
     */
    public final Operand<? extends TType> writer;

    /**
     * The logdir input
     */
    public final Operand<TString> logdir;

    /**
     * The maxQueue input
     */
    public final Operand<TInt32> maxQueue;

    /**
     * The flushMillis input
     */
    public final Operand<TInt32> flushMillis;

    /**
     * The filenameSuffix input
     */
    public final Operand<TString> filenameSuffix;

    public Inputs(GraphOperation op) {
      super(new CreateSummaryFileWriter(op), op, Arrays.asList());
      int inputIndex = 0;
      writer = (Operand<? extends TType>) op.input(inputIndex++);
      logdir = (Operand<TString>) op.input(inputIndex++);
      maxQueue = (Operand<TInt32>) op.input(inputIndex++);
      flushMillis = (Operand<TInt32>) op.input(inputIndex++);
      filenameSuffix = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
