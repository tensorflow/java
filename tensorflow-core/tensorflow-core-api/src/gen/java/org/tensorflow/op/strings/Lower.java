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
import org.tensorflow.types.TString;

/**
 * Converts all uppercase characters into their respective lowercase replacements.
 * <p>
 * Example:
 * <p>
 * >>> tf.strings.lower("CamelCase string and ALL CAPS")
 * <tf.Tensor: shape=(), dtype=string, numpy=b'camelcase string and all caps'>
 * 
 */
@Operator(group = "strings")
public final class Lower extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.strings.Lower}
   */
  public static class Options {
    
    /**
     * @param encoding 
     */
    public Options encoding(String encoding) {
      this.encoding = encoding;
      return this;
    }
    
    private String encoding;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Lower operation.
   * 
   * @param scope current scope
   * @param input 
   * @param options carries optional attributes values
   * @return a new instance of Lower
   */
  @Endpoint(describeByClass = true)
  public static Lower create(Scope scope, Operand<TString> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("StringLower", scope.makeOpName("Lower"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.encoding != null) {
          opBuilder.setAttr("encoding", opts.encoding);
        }
      }
    }
    return new Lower(opBuilder.build());
  }
  
  /**
   * @param encoding 
   */
  public static Options encoding(String encoding) {
    return new Options().encoding(encoding);
  }
  
  /**
   */
  public Output<TString> output() {
    return output;
  }
  
  @Override
  public Output<TString> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StringLower";
  
  private Output<TString> output;
  
  private Lower(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
