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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Split elements of {@code source} based on {@code sep} into a {@code SparseTensor}.
 * Let N be the size of source (typically N will be the batch size). Split each
 * element of {@code source} based on {@code sep} and return a {@code SparseTensor}
 * containing the split tokens. Empty tokens are ignored.
 * <p>For example, N = 2, source[0] is 'hello world' and source[1] is 'a b c',
 * then the output will be
 * <pre>
 * st.indices = [0, 0;
 *               0, 1;
 *               1, 0;
 *               1, 1;
 *               1, 2]
 * st.shape = [2, 3]
 * st.values = ['hello', 'world', 'a', 'b', 'c']
 * </pre>
 * <p>If {@code sep} is given, consecutive delimiters are not grouped together and are
 * deemed to delimit empty strings. For example, source of {@code "1<>2<><>3"} and
 * sep of {@code "<>"} returns {@code ["1", "2", "", "3"]}. If {@code sep} is None or an empty
 * string, consecutive whitespace are regarded as a single separator, and the
 * result will contain no empty strings at the startor end if the string has
 * leading or trailing whitespace.
 * <p>Note that the above mentioned behavior matches python's str.split.
 */
@OpMetadata(
    opType = StringSplit.OP_NAME,
    inputsClass = StringSplit.Inputs.class
)
@Operator(
    group = "strings"
)
public final class StringSplit extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StringSplitV2";

  private Output<TInt64> indices;

  private Output<TString> values;

  private Output<TInt64> shape;

  public StringSplit(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    indices = operation.output(outputIdx++);
    values = operation.output(outputIdx++);
    shape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StringSplitV2 operation.
   *
   * @param scope current scope
   * @param input {@code 1-D} string {@code Tensor}, the strings to split.
   * @param sep {@code 0-D} string {@code Tensor}, the delimiter character.
   * @param options carries optional attribute values
   * @return a new instance of StringSplit
   */
  @Endpoint(
      describeByClass = true
  )
  public static StringSplit create(Scope scope, Operand<TString> input, Operand<TString> sep,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StringSplit");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(sep.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.maxsplit != null) {
          opBuilder.setAttr("maxsplit", opts.maxsplit);
        }
      }
    }
    return new StringSplit(opBuilder.build());
  }

  /**
   * Sets the maxsplit option.
   *
   * @param maxsplit An {@code int}. If {@code maxsplit > 0}, limit of the split of the result.
   * @return this Options instance.
   */
  public static Options maxsplit(Long maxsplit) {
    return new Options().maxsplit(maxsplit);
  }

  /**
   * Gets indices.
   *
   * @return indices.
   */
  public Output<TInt64> indices() {
    return indices;
  }

  /**
   * Gets values.
   *
   * @return values.
   */
  public Output<TString> values() {
    return values;
  }

  /**
   * Gets shape.
   *
   * @return shape.
   */
  public Output<TInt64> shape() {
    return shape;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.strings.StringSplit}
   */
  public static class Options {
    private Long maxsplit;

    private Options() {
    }

    /**
     * Sets the maxsplit option.
     *
     * @param maxsplit An {@code int}. If {@code maxsplit > 0}, limit of the split of the result.
     * @return this Options instance.
     */
    public Options maxsplit(Long maxsplit) {
      this.maxsplit = maxsplit;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = StringSplit.class
  )
  public static class Inputs extends RawOpInputs<StringSplit> {
    /**
     * {@code 1-D} string {@code Tensor}, the strings to split.
     */
    public final Operand<TString> input;

    /**
     * {@code 0-D} string {@code Tensor}, the delimiter character.
     */
    public final Operand<TString> sep;

    /**
     * An `int`. If `maxsplit > 0`, limit of the split of the result.
     */
    public final long maxsplit;

    public Inputs(GraphOperation op) {
      super(new StringSplit(op), op, Arrays.asList("maxsplit"));
      int inputIndex = 0;
      input = (Operand<TString>) op.input(inputIndex++);
      sep = (Operand<TString>) op.input(inputIndex++);
      maxsplit = op.attributes().getAttrInt("maxsplit");
    }
  }
}
