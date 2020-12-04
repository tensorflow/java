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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Joins the strings in the given list of string tensors into one tensor;
 * <p>
 * with the given separator (default is an empty separator).
 * <p>
 * Examples:
 * <p>
 * >>> s = ["hello", "world", "tensorflow"]
 * >>> tf.strings.join(s, " ")
 * <tf.Tensor: shape=(), dtype=string, numpy=b'hello world tensorflow'>
 */
@Operator(group = "strings")
public final class Join extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.strings.Join}
   */
  public static class Options {
    
    /**
     * @param separator string, an optional join separator.
     */
    public Options separator(String separator) {
      this.separator = separator;
      return this;
    }
    
    private String separator;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Join operation.
   * 
   * @param scope current scope
   * @param inputs A list of string tensors.  The tensors must all have the same shape,
   * or be scalars.  Scalars may be mixed in; these will be broadcast to the shape
   * of non-scalar inputs.
   * @param options carries optional attributes values
   * @return a new instance of Join
   */
  @Endpoint(describeByClass = true)
  public static Join create(Scope scope, Iterable<Operand<TString>> inputs, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("StringJoin", scope.makeOpName("Join"));
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.separator != null) {
          opBuilder.setAttr("separator", opts.separator);
        }
      }
    }
    return new Join(opBuilder.build());
  }
  
  /**
   * @param separator string, an optional join separator.
   */
  public static Options separator(String separator) {
    return new Options().separator(separator);
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
  public static final String OP_NAME = "StringJoin";
  
  private Output<TString> output;
  
  private Join(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
