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

package org.tensorflow.op.train;

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
import org.tensorflow.types.TString;

/**
 * Computes fingerprints of the input strings.
 */
@OpMetadata(
    opType = SdcaFprint.OP_NAME,
    inputsClass = SdcaFprint.Inputs.class
)
@Operator(
    group = "train"
)
public final class SdcaFprint extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SdcaFprint";

  private Output<TInt64> output;

  public SdcaFprint(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SdcaFprint operation.
   *
   * @param scope current scope
   * @param input vector of strings to compute fingerprints on.
   * @return a new instance of SdcaFprint
   */
  @Endpoint(
      describeByClass = true
  )
  public static SdcaFprint create(Scope scope, Operand<TString> input) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SdcaFprint");
    opBuilder.addInput(input.asOutput());
    return new SdcaFprint(opBuilder.build());
  }

  /**
   * Gets output.
   * a (N,2) shaped matrix where N is the number of elements in the input
   * vector. Each row contains the low and high parts of the fingerprint.
   * @return output.
   */
  public Output<TInt64> output() {
    return output;
  }

  @Override
  public Output<TInt64> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = SdcaFprint.class
  )
  public static class Inputs extends RawOpInputs<SdcaFprint> {
    /**
     * vector of strings to compute fingerprints on.
     */
    public final Operand<TString> input;

    public Inputs(GraphOperation op) {
      super(new SdcaFprint(op), op, Arrays.asList());
      int inputIndex = 0;
      input = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
