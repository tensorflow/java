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
 * Replaces matches of the `pattern` regular expression in `input` with the
 * replacement string provided in `rewrite`.
 * <p>
 * It follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
 */
@Operator(group = "strings")
public final class RegexReplace extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.strings.RegexReplace}
   */
  public static class Options {
    
    /**
     * @param replaceGlobal If True, the replacement is global (that is, all matches of the `pattern` regular
     * expression in each input string are rewritten), otherwise the `rewrite`
     * substitution is only made for the first `pattern` match.
     */
    public Options replaceGlobal(Boolean replaceGlobal) {
      this.replaceGlobal = replaceGlobal;
      return this;
    }
    
    private Boolean replaceGlobal;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new RegexReplace operation.
   * 
   * @param scope current scope
   * @param input The text to be processed.
   * @param pattern The regular expression to be matched in the `input` strings.
   * @param rewrite The rewrite string to be substituted for the `pattern` expression where it is
   * matched in the `input` strings.
   * @param options carries optional attributes values
   * @return a new instance of RegexReplace
   */
  @Endpoint(describeByClass = true)
  public static RegexReplace create(Scope scope, Operand<TString> input, Operand<TString> pattern, Operand<TString> rewrite, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("RegexReplace", scope.makeOpName("RegexReplace"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(pattern.asOutput());
    opBuilder.addInput(rewrite.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.replaceGlobal != null) {
          opBuilder.setAttr("replace_global", opts.replaceGlobal);
        }
      }
    }
    return new RegexReplace(opBuilder.build());
  }
  
  /**
   * @param replaceGlobal If True, the replacement is global (that is, all matches of the `pattern` regular
   * expression in each input string are rewritten), otherwise the `rewrite`
   * substitution is only made for the first `pattern` match.
   */
  public static Options replaceGlobal(Boolean replaceGlobal) {
    return new Options().replaceGlobal(replaceGlobal);
  }
  
  /**
   * The text after applying pattern match and rewrite substitution.
   */
  public Output<TString> output() {
    return output;
  }
  
  @Override
  public Output<TString> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RegexReplace";
  
  private Output<TString> output;
  
  private RegexReplace(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
