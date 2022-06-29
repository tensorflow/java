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
import org.tensorflow.types.TBool;
import org.tensorflow.types.TString;

/**
 * Check if the input matches the regex pattern.
 * The input is a string tensor of any shape. The pattern is the
 * regular expression to be matched with every element of the input tensor.
 * The boolean values (True or False) of the output tensor indicate
 * if the input matches the regex pattern provided.
 * <p>The pattern follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
 */
@OpMetadata(
    opType = StaticRegexFullMatch.OP_NAME,
    inputsClass = StaticRegexFullMatch.Inputs.class
)
public final class StaticRegexFullMatch extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StaticRegexFullMatch";

  private Output<TBool> output;

  public StaticRegexFullMatch(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StaticRegexFullMatch operation.
   *
   * @param scope current scope
   * @param input A string tensor of the text to be processed.
   * @param pattern The regular expression to match the input.
   * @return a new instance of StaticRegexFullMatch
   */
  @Endpoint(
      describeByClass = true
  )
  public static StaticRegexFullMatch create(Scope scope, Operand<TString> input, String pattern) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StaticRegexFullMatch");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("pattern", pattern);
    return new StaticRegexFullMatch(opBuilder.build());
  }

  /**
   * Gets output.
   * A bool tensor with the same shape as {@code input}.
   * @return output.
   */
  public Output<TBool> output() {
    return output;
  }

  @Override
  public Output<TBool> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = StaticRegexFullMatch.class
  )
  public static class Inputs extends RawOpInputs<StaticRegexFullMatch> {
    /**
     * A string tensor of the text to be processed.
     */
    public final Operand<TString> input;

    /**
     * The regular expression to match the input.
     */
    public final String pattern;

    public Inputs(GraphOperation op) {
      super(new StaticRegexFullMatch(op), op, Arrays.asList("pattern"));
      int inputIndex = 0;
      input = (Operand<TString>) op.input(inputIndex++);
      pattern = op.attributes().getAttrString("pattern");
    }
  }
}
