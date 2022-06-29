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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The CreateSummaryDbWriter operation
 */
@OpMetadata(
    opType = CreateSummaryDbWriter.OP_NAME,
    inputsClass = CreateSummaryDbWriter.Inputs.class
)
public final class CreateSummaryDbWriter extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CreateSummaryDbWriter";

  public CreateSummaryDbWriter(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new CreateSummaryDbWriter operation.
   *
   * @param scope current scope
   * @param writer The writer value
   * @param dbUri The dbUri value
   * @param experimentName The experimentName value
   * @param runName The runName value
   * @param userName The userName value
   * @return a new instance of CreateSummaryDbWriter
   */
  @Endpoint(
      describeByClass = true
  )
  public static CreateSummaryDbWriter create(Scope scope, Operand<? extends TType> writer,
      Operand<TString> dbUri, Operand<TString> experimentName, Operand<TString> runName,
      Operand<TString> userName) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CreateSummaryDbWriter");
    opBuilder.addInput(writer.asOutput());
    opBuilder.addInput(dbUri.asOutput());
    opBuilder.addInput(experimentName.asOutput());
    opBuilder.addInput(runName.asOutput());
    opBuilder.addInput(userName.asOutput());
    return new CreateSummaryDbWriter(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = CreateSummaryDbWriter.class
  )
  public static class Inputs extends RawOpInputs<CreateSummaryDbWriter> {
    /**
     * The writer input
     */
    public final Operand<? extends TType> writer;

    /**
     * The dbUri input
     */
    public final Operand<TString> dbUri;

    /**
     * The experimentName input
     */
    public final Operand<TString> experimentName;

    /**
     * The runName input
     */
    public final Operand<TString> runName;

    /**
     * The userName input
     */
    public final Operand<TString> userName;

    public Inputs(GraphOperation op) {
      super(new CreateSummaryDbWriter(op), op, Arrays.asList());
      int inputIndex = 0;
      writer = (Operand<? extends TType>) op.input(inputIndex++);
      dbUri = (Operand<TString>) op.input(inputIndex++);
      experimentName = (Operand<TString>) op.input(inputIndex++);
      runName = (Operand<TString>) op.input(inputIndex++);
      userName = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
