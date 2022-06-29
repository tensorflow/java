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
 * Replaces matches of the {@code pattern} regular expression in {@code input} with the
 * replacement string provided in {@code rewrite}.
 * It follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
 */
@OpMetadata(
    opType = RegexReplace.OP_NAME,
    inputsClass = RegexReplace.Inputs.class
)
@Operator(
    group = "strings"
)
public final class RegexReplace extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RegexReplace";

  private Output<TString> output;

  public RegexReplace(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RegexReplace operation.
   *
   * @param scope current scope
   * @param input The text to be processed.
   * @param pattern The regular expression to be matched in the {@code input} strings.
   * @param rewrite The rewrite string to be substituted for the {@code pattern} expression where it is
   * matched in the {@code input} strings.
   * @param options carries optional attribute values
   * @return a new instance of RegexReplace
   */
  @Endpoint(
      describeByClass = true
  )
  public static RegexReplace create(Scope scope, Operand<TString> input, Operand<TString> pattern,
      Operand<TString> rewrite, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RegexReplace");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(pattern.asOutput());
    opBuilder.addInput(rewrite.asOutput());
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
   * Sets the replaceGlobal option.
   *
   * @param replaceGlobal If True, the replacement is global (that is, all matches of the {@code pattern} regular
   * expression in each input string are rewritten), otherwise the {@code rewrite}
   * substitution is only made for the first {@code pattern} match.
   * @return this Options instance.
   */
  public static Options replaceGlobal(Boolean replaceGlobal) {
    return new Options().replaceGlobal(replaceGlobal);
  }

  /**
   * Gets output.
   * The text after applying pattern match and rewrite substitution.
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
   * Optional attributes for {@link org.tensorflow.op.strings.RegexReplace}
   */
  public static class Options {
    private Boolean replaceGlobal;

    private Options() {
    }

    /**
     * Sets the replaceGlobal option.
     *
     * @param replaceGlobal If True, the replacement is global (that is, all matches of the {@code pattern} regular
     * expression in each input string are rewritten), otherwise the {@code rewrite}
     * substitution is only made for the first {@code pattern} match.
     * @return this Options instance.
     */
    public Options replaceGlobal(Boolean replaceGlobal) {
      this.replaceGlobal = replaceGlobal;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RegexReplace.class
  )
  public static class Inputs extends RawOpInputs<RegexReplace> {
    /**
     * The text to be processed.
     */
    public final Operand<TString> input;

    /**
     * The regular expression to be matched in the {@code input} strings.
     */
    public final Operand<TString> pattern;

    /**
     * The rewrite string to be substituted for the {@code pattern} expression where it is
     * matched in the {@code input} strings.
     */
    public final Operand<TString> rewrite;

    /**
     * If True, the replacement is global (that is, all matches of the `pattern` regular
     * expression in each input string are rewritten), otherwise the `rewrite`
     * substitution is only made for the first `pattern` match.
     */
    public final boolean replaceGlobal;

    public Inputs(GraphOperation op) {
      super(new RegexReplace(op), op, Arrays.asList("replace_global"));
      int inputIndex = 0;
      input = (Operand<TString>) op.input(inputIndex++);
      pattern = (Operand<TString>) op.input(inputIndex++);
      rewrite = (Operand<TString>) op.input(inputIndex++);
      replaceGlobal = op.attributes().getAttrBool("replace_global");
    }
  }
}
