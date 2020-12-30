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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TString;

/**
 * Check if the input matches the regex pattern.
 * <p>
 * The input is a string tensor of any shape. The pattern is a scalar
 * string tensor which is applied to every element of the input tensor.
 * The boolean values (True or False) of the output tensor indicate
 * if the input matches the regex pattern provided.
 * <p>
 * The pattern follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
 * <p>
 * Examples:
 * <p>
 * >>> tf.strings.regex_full_match(["TF lib", "lib TF"], ".*lib$")
 * <tf.Tensor: shape=(2,), dtype=bool, numpy=array([ True, False])>
 * >>> tf.strings.regex_full_match(["TF lib", "lib TF"], ".*TF$")
 * <tf.Tensor: shape=(2,), dtype=bool, numpy=array([False,  True])>
 */
@Operator(group = "strings")
public final class RegexFullMatch extends RawOp implements Operand<TBool> {
  
  /**
   * Factory method to create a class wrapping a new RegexFullMatch operation.
   * 
   * @param scope current scope
   * @param input A string tensor of the text to be processed.
   * @param pattern A scalar string tensor containing the regular expression to match the input.
   * @return a new instance of RegexFullMatch
   */
  @Endpoint(describeByClass = true)
  public static RegexFullMatch create(Scope scope, Operand<TString> input, Operand<TString> pattern) {
    OperationBuilder opBuilder = scope.env().opBuilder("RegexFullMatch", scope.makeOpName("RegexFullMatch"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(pattern.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new RegexFullMatch(opBuilder.build());
  }
  
  /**
   * A bool tensor with the same shape as `input`.
   */
  public Output<TBool> output() {
    return output;
  }
  
  @Override
  public Output<TBool> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RegexFullMatch";
  
  private Output<TBool> output;
  
  private RegexFullMatch(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
