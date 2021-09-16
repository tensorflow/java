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
import org.tensorflow.types.TString;

/**
 * Replaces the match of pattern in input with rewrite.
 * It follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
 */
public final class StaticRegexReplace extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StaticRegexReplace";

  private Output<TString> output;

  private StaticRegexReplace(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StaticRegexReplace operation.
   *
   * @param scope current scope
   * @param input The text to be processed.
   * @param pattern The regular expression to match the input.
   * @param rewrite The rewrite to be applied to the matched expression.
   * @param options carries optional attribute values
   * @return a new instance of StaticRegexReplace
   */
  @Endpoint(
      describeByClass = true
  )
  public static StaticRegexReplace create(Scope scope, Operand<TString> input, String pattern,
      String rewrite, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StaticRegexReplace");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("pattern", pattern);
    opBuilder.setAttr("rewrite", rewrite);
    if (options != null) {
      for (Options opts : options) {
        if (opts.replaceGlobal != null) {
          opBuilder.setAttr("replace_global", opts.replaceGlobal);
        }
      }
    }
    return new StaticRegexReplace(opBuilder.build());
  }

  /**
   * Sets the replaceGlobal option.
   *
   * @param replaceGlobal If True, the replacement is global, otherwise the replacement
   * is done only on the first match.
   * @return this Options instance.
   */
  public static Options replaceGlobal(Boolean replaceGlobal) {
    return new Options().replaceGlobal(replaceGlobal);
  }

  /**
   * Gets output.
   * The text after applying pattern and rewrite.
   * @return output.
   */
  public Output<TString> output() {
    return output;
  }

  @Override
  public Output<TString> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.strings.StaticRegexReplace}
   */
  public static class Options {
    private Boolean replaceGlobal;

    private Options() {
    }

    /**
     * Sets the replaceGlobal option.
     *
     * @param replaceGlobal If True, the replacement is global, otherwise the replacement
     * is done only on the first match.
     * @return this Options instance.
     */
    public Options replaceGlobal(Boolean replaceGlobal) {
      this.replaceGlobal = replaceGlobal;
      return this;
    }
  }
}
