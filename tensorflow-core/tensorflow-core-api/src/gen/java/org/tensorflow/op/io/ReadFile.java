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

package org.tensorflow.op.io;

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
import org.tensorflow.types.TString;

/**
 * Reads and outputs the entire contents of the input filename.
 */
@OpMetadata(
    opType = ReadFile.OP_NAME,
    inputsClass = ReadFile.Inputs.class
)
@Operator(
    group = "io"
)
public final class ReadFile extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ReadFile";

  private Output<TString> contents;

  public ReadFile(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    contents = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ReadFile operation.
   *
   * @param scope current scope
   * @param filename The filename value
   * @return a new instance of ReadFile
   */
  @Endpoint(
      describeByClass = true
  )
  public static ReadFile create(Scope scope, Operand<TString> filename) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReadFile");
    opBuilder.addInput(filename.asOutput());
    return new ReadFile(opBuilder.build());
  }

  /**
   * Gets contents.
   *
   * @return contents.
   */
  public Output<TString> contents() {
    return contents;
  }

  @Override
  public Output<TString> asOutput() {
    return contents;
  }

  @OpInputsMetadata(
      outputsClass = ReadFile.class
  )
  public static class Inputs extends RawOpInputs<ReadFile> {
    /**
     * The filename input
     */
    public final Operand<TString> filename;

    public Inputs(GraphOperation op) {
      super(new ReadFile(op), op, Arrays.asList());
      int inputIndex = 0;
      filename = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
