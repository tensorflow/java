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

package org.tensorflow.op.core;

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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Returns the {@code tf.data.Options} attached to {@code input_dataset}.
 */
@OpMetadata(
    opType = GetOptions.OP_NAME,
    inputsClass = GetOptions.Inputs.class
)
public final class GetOptions extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "GetOptions";

  private Output<TString> serializedOptions;

  public GetOptions(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    serializedOptions = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GetOptions operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @return a new instance of GetOptions
   */
  @Endpoint(
      describeByClass = true
  )
  public static GetOptions create(Scope scope, Operand<? extends TType> inputDataset) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "GetOptions");
    opBuilder.addInput(inputDataset.asOutput());
    return new GetOptions(opBuilder.build());
  }

  /**
   * Gets serializedOptions.
   *
   * @return serializedOptions.
   */
  public Output<TString> serializedOptions() {
    return serializedOptions;
  }

  @Override
  public Output<TString> asOutput() {
    return serializedOptions;
  }

  @OpInputsMetadata(
      outputsClass = GetOptions.class
  )
  public static class Inputs extends RawOpInputs<GetOptions> {
    /**
     * A variant tensor representing the input dataset.
     */
    public final Operand<? extends TType> inputDataset;

    public Inputs(GraphOperation op) {
      super(new GetOptions(op), op, Arrays.asList());
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
