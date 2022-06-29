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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The MatchingFilesDataset operation
 */
@OpMetadata(
    opType = MatchingFilesDataset.OP_NAME,
    inputsClass = MatchingFilesDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class MatchingFilesDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MatchingFilesDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public MatchingFilesDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MatchingFilesDataset operation.
   *
   * @param scope current scope
   * @param patterns The patterns value
   * @return a new instance of MatchingFilesDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static MatchingFilesDataset create(Scope scope, Operand<TString> patterns) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MatchingFilesDataset");
    opBuilder.addInput(patterns.asOutput());
    return new MatchingFilesDataset(opBuilder.build());
  }

  /**
   * Gets handle.
   *
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  @OpInputsMetadata(
      outputsClass = MatchingFilesDataset.class
  )
  public static class Inputs extends RawOpInputs<MatchingFilesDataset> {
    /**
     * The patterns input
     */
    public final Operand<TString> patterns;

    public Inputs(GraphOperation op) {
      super(new MatchingFilesDataset(op), op, Arrays.asList());
      int inputIndex = 0;
      patterns = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
