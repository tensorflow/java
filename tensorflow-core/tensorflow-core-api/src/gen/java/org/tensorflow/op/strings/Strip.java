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

package org.tensorflow.op.strings;

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
 * Strip leading and trailing whitespaces from the Tensor.
 * Examples:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>tf.strings.strip([&quot;\nTensorFlow&quot;, &quot;     The python library    &quot;]).numpy()
 * array([b'TensorFlow', b'The python library'], dtype=object)
 * </blockquote>
 * </blockquote>
 * </blockquote>
 */
@OpMetadata(
    opType = Strip.OP_NAME,
    inputsClass = Strip.Inputs.class
)
@Operator(
    group = "strings"
)
public final class Strip extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StringStrip";

  private Output<TString> output;

  public Strip(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StringStrip operation.
   *
   * @param scope current scope
   * @param input A string {@code Tensor} of any shape.
   * @return a new instance of Strip
   */
  @Endpoint(
      describeByClass = true
  )
  public static Strip create(Scope scope, Operand<TString> input) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Strip");
    opBuilder.addInput(input.asOutput());
    return new Strip(opBuilder.build());
  }

  /**
   * Gets output.
   * A string {@code Tensor} of the same shape as the input.
   * @return output.
   */
  public Output<TString> output() {
    return output;
  }

  @Override
  public Output<TString> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = Strip.class
  )
  public static class Inputs extends RawOpInputs<Strip> {
    /**
     * A string {@code Tensor} of any shape.
     */
    public final Operand<TString> input;

    public Inputs(GraphOperation op) {
      super(new Strip(op), op, Arrays.asList());
      int inputIndex = 0;
      input = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
