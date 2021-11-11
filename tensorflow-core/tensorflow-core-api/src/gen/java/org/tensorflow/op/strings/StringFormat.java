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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;

/**
 * Formats a string template using a list of tensors.
 * Formats a string template using a list of tensors, pretty-printing tensor summaries.
 */
@OpMetadata(
    opType = StringFormat.OP_NAME,
    inputsClass = StringFormat.Inputs.class
)
@Operator(
    group = "strings"
)
public final class StringFormat extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StringFormat";

  private Output<TString> output;

  public StringFormat(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StringFormat operation.
   *
   * @param scope current scope
   * @param inputs The list of tensors to format into the placeholder string.
   * @param options carries optional attribute values
   * @return a new instance of StringFormat
   */
  @Endpoint(
      describeByClass = true
  )
  public static StringFormat create(Scope scope, Iterable<Operand<?>> inputs, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StringFormat");
    opBuilder.addInputList(Operands.asOutputs(inputs));
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
   * Sets the template option.
   *
   * @param template A string, the template to format tensor summaries into.
   * @return this Options instance.
   */
  public static Options template(String template) {
    return new Options().template(template);
  }

  /**
   * Sets the placeholder option.
   *
   * @param placeholder A string, at each placeholder in the template a subsequent tensor summary will be inserted.
   * @return this Options instance.
   */
  public static Options placeholder(String placeholder) {
    return new Options().placeholder(placeholder);
  }

  /**
   * Sets the summarize option.
   *
   * @param summarize When formatting the tensor summaries print the first and last summarize entries of each tensor dimension.
   * @return this Options instance.
   */
  public static Options summarize(Long summarize) {
    return new Options().summarize(summarize);
  }

  /**
   * Gets output.
   * = The resulting string scalar.
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
   * Optional attributes for {@link org.tensorflow.op.strings.StringFormat}
   */
  public static class Options {
    private String template;

    private String placeholder;

    private Long summarize;

    private Options() {
    }

    /**
     * Sets the template option.
     *
     * @param template A string, the template to format tensor summaries into.
     * @return this Options instance.
     */
    public Options template(String template) {
      this.template = template;
      return this;
    }

    /**
     * Sets the placeholder option.
     *
     * @param placeholder A string, at each placeholder in the template a subsequent tensor summary will be inserted.
     * @return this Options instance.
     */
    public Options placeholder(String placeholder) {
      this.placeholder = placeholder;
      return this;
    }

    /**
     * Sets the summarize option.
     *
     * @param summarize When formatting the tensor summaries print the first and last summarize entries of each tensor dimension.
     * @return this Options instance.
     */
    public Options summarize(Long summarize) {
      this.summarize = summarize;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = StringFormat.class
  )
  public static class Inputs extends RawOpInputs<StringFormat> {
    /**
     * The list of tensors to format into the placeholder string.
     */
    public final Iterable<Operand<?>> inputs;

    /**
     * The T attribute
     */
    public final DataType[] T;

    /**
     * A string, the template to format tensor summaries into.
     */
    public final String template;

    /**
     * A string, at each placeholder in the template a subsequent tensor summary will be inserted.
     */
    public final String placeholder;

    /**
     * When formatting the tensor summaries print the first and last summarize entries of each tensor dimension.
     */
    public final long summarize;

    public Inputs(GraphOperation op) {
      super(new StringFormat(op), op, Arrays.asList("T", "template", "placeholder", "summarize"));
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      T = op.attributes().getAttrTypeList("T");
      template = op.attributes().getAttrString("template");
      placeholder = op.attributes().getAttrString("placeholder");
      summarize = op.attributes().getAttrInt("summarize");
    }
  }
}
