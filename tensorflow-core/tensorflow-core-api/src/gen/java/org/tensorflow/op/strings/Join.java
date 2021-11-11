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
import org.tensorflow.types.TString;

/**
 * Joins the strings in the given list of string tensors into one tensor;
 * with the given separator (default is an empty separator).
 * <p>Examples:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>s = [&quot;hello&quot;, &quot;world&quot;, &quot;tensorflow&quot;]
 * tf.strings.join(s, &quot; &quot;)
 * &lt;tf.Tensor: shape=(), dtype=string, numpy=b'hello world tensorflow'&gt;
 * </blockquote>
 * </blockquote>
 * </blockquote>
 */
@OpMetadata(
    opType = Join.OP_NAME,
    inputsClass = Join.Inputs.class
)
@Operator(
    group = "strings"
)
public final class Join extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StringJoin";

  private Output<TString> output;

  public Join(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StringJoin operation.
   *
   * @param scope current scope
   * @param inputs A list of string tensors.  The tensors must all have the same shape,
   * or be scalars.  Scalars may be mixed in; these will be broadcast to the shape
   * of non-scalar inputs.
   * @param options carries optional attribute values
   * @return a new instance of Join
   */
  @Endpoint(
      describeByClass = true
  )
  public static Join create(Scope scope, Iterable<Operand<TString>> inputs, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Join");
    opBuilder.addInputList(Operands.asOutputs(inputs));
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
   * Sets the separator option.
   *
   * @param separator string, an optional join separator.
   * @return this Options instance.
   */
  public static Options separator(String separator) {
    return new Options().separator(separator);
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
   * Optional attributes for {@link org.tensorflow.op.strings.Join}
   */
  public static class Options {
    private String separator;

    private Options() {
    }

    /**
     * Sets the separator option.
     *
     * @param separator string, an optional join separator.
     * @return this Options instance.
     */
    public Options separator(String separator) {
      this.separator = separator;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Join.class
  )
  public static class Inputs extends RawOpInputs<Join> {
    /**
     * A list of string tensors.  The tensors must all have the same shape,
     * or be scalars.  Scalars may be mixed in; these will be broadcast to the shape
     * of non-scalar inputs.
     */
    public final Iterable<Operand<TString>> inputs;

    /**
     * string, an optional join separator.
     */
    public final String separator;

    public Inputs(GraphOperation op) {
      super(new Join(op), op, Arrays.asList("separator"));
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<TString>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      separator = op.attributes().getAttrString("separator");
    }
  }
}
