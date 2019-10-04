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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;

/**
 * Check if the input matches the regex pattern.
 * <p>
 * The input is a string tensor of any shape. The pattern is a scalar
 * string tensor which is applied to every element of the input tensor.
 * The boolean values (True or False) of the output tensor indicate
 * if the input matches the regex pattern provided.
 * <p>
 * The pattern follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
 */
@Operator(group = "strings")
public final class RegexFullMatch extends PrimitiveOp implements Operand<Boolean> {
  
  /**
   * Factory method to create a class wrapping a new RegexFullMatch operation.
   * 
   * @param scope current scope
   * @param input A string tensor of the text to be processed.
   * @param pattern A scalar string tensor containing the regular expression to match the input.
   * @return a new instance of RegexFullMatch
   */
  public static RegexFullMatch create(Scope scope, Operand<String> input, Operand<String> pattern) {
    OperationBuilder opBuilder = scope.env().opBuilder("RegexFullMatch", scope.makeOpName("RegexFullMatch"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(pattern.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new RegexFullMatch(opBuilder.build());
  }
  
  /**
   * A bool tensor with the same shape as `input`.
   */
  public Output<Boolean> output() {
    return output;
  }
  
  @Override
  public Output<Boolean> asOutput() {
    return output;
  }
  
  private Output<Boolean> output;
  
  private RegexFullMatch(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
