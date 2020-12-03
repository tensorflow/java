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
 * Formats a string template using a list of tensors.
 * <p>
 * Formats a string template using a list of tensors, pretty-printing tensor summaries.
 */
@Operator(group = "strings")
public final class StringFormat extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.strings.StringFormat}
   */
  public static class Options {
    
    /**
     * @param template A string, the template to format tensor summaries into.
     */
    public Options template(String template) {
      this.template = template;
      return this;
    }
    
    /**
     * @param placeholder A string, at each placeholder in the template a subsequent tensor summary will be inserted.
     */
    public Options placeholder(String placeholder) {
      this.placeholder = placeholder;
      return this;
    }
    
    /**
     * @param summarize When formatting the tensor summaries print the first and last summarize entries of each tensor dimension.
     */
    public Options summarize(Long summarize) {
      this.summarize = summarize;
      return this;
    }
    
    private String template;
    private String placeholder;
    private Long summarize;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new StringFormat operation.
   * 
   * @param scope current scope
   * @param inputs The list of tensors to format into the placeholder string.
   * @param options carries optional attributes values
   * @return a new instance of StringFormat
   */
  @Endpoint(describeByClass = true)
  public static StringFormat create(Scope scope, Iterable<Operand<?>> inputs, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("StringFormat", scope.makeOpName("StringFormat"));
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.template != null) {
          opBuilder.setAttr("template", opts.template);
        }
        if (opts.placeholder != null) {
          opBuilder.setAttr("placeholder", opts.placeholder);
        }
        if (opts.summarize != null) {
          opBuilder.setAttr("summarize", opts.summarize);
        }
      }
    }
    return new StringFormat(opBuilder.build());
  }
  
  /**
   * @param template A string, the template to format tensor summaries into.
   */
  public static Options template(String template) {
    return new Options().template(template);
  }
  
  /**
   * @param placeholder A string, at each placeholder in the template a subsequent tensor summary will be inserted.
   */
  public static Options placeholder(String placeholder) {
    return new Options().placeholder(placeholder);
  }
  
  /**
   * @param summarize When formatting the tensor summaries print the first and last summarize entries of each tensor dimension.
   */
  public static Options summarize(Long summarize) {
    return new Options().summarize(summarize);
  }
  
  /**
   * = The resulting string scalar.
   */
  public Output<TString> output() {
    return output;
  }
  
  @Override
  public Output<TString> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StringFormat";
  
  private Output<TString> output;
  
  private StringFormat(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
