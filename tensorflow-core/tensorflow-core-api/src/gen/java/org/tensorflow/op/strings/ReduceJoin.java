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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;

/**
 * Joins a string Tensor across the given dimensions.
 * Computes the string join across dimensions in the given string Tensor of shape
 * {@code [\\(d_0, d_1, ..., d_{n-1}\\)]}.  Returns a new Tensor created by joining the input
 * strings with the given separator (default: empty string).  Negative indices are
 * counted backwards from the end, with {@code -1} being equivalent to {@code n - 1}.  If
 * indices are not specified, joins across all dimensions beginning from {@code n - 1}
 * through {@code 0}.
 * <p>For example:
 * <pre>
 * # tensor `a` is [[&quot;a&quot;, &quot;b&quot;], [&quot;c&quot;, &quot;d&quot;]]
 * tf.reduce_join(a, 0) ==&gt; [&quot;ac&quot;, &quot;bd&quot;]
 * tf.reduce_join(a, 1) ==&gt; [&quot;ab&quot;, &quot;cd&quot;]
 * tf.reduce_join(a, -2) = tf.reduce_join(a, 0) ==&gt; [&quot;ac&quot;, &quot;bd&quot;]
 * tf.reduce_join(a, -1) = tf.reduce_join(a, 1) ==&gt; [&quot;ab&quot;, &quot;cd&quot;]
 * tf.reduce_join(a, 0, keep_dims=True) ==&gt; [[&quot;ac&quot;, &quot;bd&quot;]]
 * tf.reduce_join(a, 1, keep_dims=True) ==&gt; [[&quot;ab&quot;], [&quot;cd&quot;]]
 * tf.reduce_join(a, 0, separator=&quot;.&quot;) ==&gt; [&quot;a.c&quot;, &quot;b.d&quot;]
 * tf.reduce_join(a, [0, 1]) ==&gt; &quot;acbd&quot;
 * tf.reduce_join(a, [1, 0]) ==&gt; &quot;abcd&quot;
 * tf.reduce_join(a, []) ==&gt; [[&quot;a&quot;, &quot;b&quot;], [&quot;c&quot;, &quot;d&quot;]]
 * tf.reduce_join(a) = tf.reduce_join(a, [1, 0]) ==&gt; &quot;abcd&quot;
 * </pre>
 */
@OpMetadata(
    opType = ReduceJoin.OP_NAME,
    inputsClass = ReduceJoin.Inputs.class
)
@Operator(
    group = "strings"
)
public final class ReduceJoin extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ReduceJoin";

  private Output<TString> output;

  public ReduceJoin(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ReduceJoin operation.
   *
   * @param scope current scope
   * @param inputs The input to be joined.  All reduced indices must have non-zero size.
   * @param reductionIndices The dimensions to reduce over.  Dimensions are reduced in the
   * order specified.  Omitting {@code reduction_indices} is equivalent to passing
   * {@code [n-1, n-2, ..., 0]}.  Negative indices from {@code -n} to {@code -1} are supported.
   * @param options carries optional attribute values
   * @return a new instance of ReduceJoin
   */
  @Endpoint(
      describeByClass = true
  )
  public static ReduceJoin create(Scope scope, Operand<TString> inputs,
      Operand<TInt32> reductionIndices, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReduceJoin");
    opBuilder.addInput(inputs.asOutput());
    opBuilder.addInput(reductionIndices.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.keepDims != null) {
          opBuilder.setAttr("keep_dims", opts.keepDims);
        }
        if (opts.separator != null) {
          opBuilder.setAttr("separator", opts.separator);
        }
      }
    }
    return new ReduceJoin(opBuilder.build());
  }

  /**
   * Sets the keepDims option.
   *
   * @param keepDims If {@code True}, retain reduced dimensions with length {@code 1}.
   * @return this Options instance.
   */
  public static Options keepDims(Boolean keepDims) {
    return new Options().keepDims(keepDims);
  }

  /**
   * Sets the separator option.
   *
   * @param separator The separator to use when joining.
   * @return this Options instance.
   */
  public static Options separator(String separator) {
    return new Options().separator(separator);
  }

  /**
   * Gets output.
   * Has shape equal to that of the input with reduced dimensions removed or
   * set to {@code 1} depending on {@code keep_dims}.
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
   * Optional attributes for {@link org.tensorflow.op.strings.ReduceJoin}
   */
  public static class Options {
    private Boolean keepDims;

    private String separator;

    private Options() {
    }

    /**
     * Sets the keepDims option.
     *
     * @param keepDims If {@code True}, retain reduced dimensions with length {@code 1}.
     * @return this Options instance.
     */
    public Options keepDims(Boolean keepDims) {
      this.keepDims = keepDims;
      return this;
    }

    /**
     * Sets the separator option.
     *
     * @param separator The separator to use when joining.
     * @return this Options instance.
     */
    public Options separator(String separator) {
      this.separator = separator;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ReduceJoin.class
  )
  public static class Inputs extends RawOpInputs<ReduceJoin> {
    /**
     * The input to be joined.  All reduced indices must have non-zero size.
     */
    public final Operand<TString> inputs;

    /**
     * The dimensions to reduce over.  Dimensions are reduced in the
     * order specified.  Omitting {@code reduction_indices} is equivalent to passing
     * {@code [n-1, n-2, ..., 0]}.  Negative indices from {@code -n} to {@code -1} are supported.
     */
    public final Operand<TInt32> reductionIndices;

    /**
     * If `True`, retain reduced dimensions with length `1`.
     */
    public final boolean keepDims;

    /**
     * The separator to use when joining.
     */
    public final String separator;

    public Inputs(GraphOperation op) {
      super(new ReduceJoin(op), op, Arrays.asList("keep_dims", "separator"));
      int inputIndex = 0;
      inputs = (Operand<TString>) op.input(inputIndex++);
      reductionIndices = (Operand<TInt32>) op.input(inputIndex++);
      keepDims = op.attributes().getAttrBool("keep_dims");
      separator = op.attributes().getAttrString("separator");
    }
  }
}
