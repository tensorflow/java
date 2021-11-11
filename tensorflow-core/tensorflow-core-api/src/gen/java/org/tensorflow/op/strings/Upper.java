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
 * Converts all lowercase characters into their respective uppercase replacements.
 * Example:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>tf.strings.upper(&quot;CamelCase string and ALL CAPS&quot;)
 * &lt;tf.Tensor: shape=(), dtype=string, numpy=b'CAMELCASE STRING AND ALL CAPS'&gt;
 * </blockquote>
 * </blockquote>
 * </blockquote>
 */
@OpMetadata(
    opType = Upper.OP_NAME,
    inputsClass = Upper.Inputs.class
)
@Operator(
    group = "strings"
)
public final class Upper extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StringUpper";

  private Output<TString> output;

  public Upper(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StringUpper operation.
   *
   * @param scope current scope
   * @param input The input to be upper-cased.
   * @param options carries optional attribute values
   * @return a new instance of Upper
   */
  @Endpoint(
      describeByClass = true
  )
  public static Upper create(Scope scope, Operand<TString> input, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Upper");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.encoding != null) {
          opBuilder.setAttr("encoding", opts.encoding);
        }
      }
    }
    return new Upper(opBuilder.build());
  }

  /**
   * Sets the encoding option.
   *
   * @param encoding Character encoding of {@code input}. Allowed values are '' and 'utf-8'.
   * Value '' is interpreted as ASCII.
   * @return this Options instance.
   */
  public static Options encoding(String encoding) {
    return new Options().encoding(encoding);
  }

  /**
   * Gets output.
   *
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
   * Optional attributes for {@link org.tensorflow.op.strings.Upper}
   */
  public static class Options {
    private String encoding;

    private Options() {
    }

    /**
     * Sets the encoding option.
     *
     * @param encoding Character encoding of {@code input}. Allowed values are '' and 'utf-8'.
     * Value '' is interpreted as ASCII.
     * @return this Options instance.
     */
    public Options encoding(String encoding) {
      this.encoding = encoding;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Upper.class
  )
  public static class Inputs extends RawOpInputs<Upper> {
    /**
     * The input to be upper-cased.
     */
    public final Operand<TString> input;

    /**
     * Character encoding of `input`. Allowed values are '' and 'utf-8'.
     * Value '' is interpreted as ASCII.
     */
    public final String encoding;

    public Inputs(GraphOperation op) {
      super(new Upper(op), op, Arrays.asList("encoding"));
      int inputIndex = 0;
      input = (Operand<TString>) op.input(inputIndex++);
      encoding = op.attributes().getAttrString("encoding");
    }
  }
}
