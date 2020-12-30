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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;

/**
 * Joins a string Tensor across the given dimensions.
 * <p>
 * Computes the string join across dimensions in the given string Tensor of shape
 * `[\\(d_0, d_1, ..., d_{n-1}\\)]`.  Returns a new Tensor created by joining the input
 * strings with the given separator (default: empty string).  Negative indices are
 * counted backwards from the end, with `-1` being equivalent to `n - 1`.  If
 * indices are not specified, joins across all dimensions beginning from `n - 1`
 * through `0`.
 * <p>
 * For example:
 * <pre>{@code
 * # tensor `a` is [["a", "b"], ["c", "d"]]
 * tf.reduce_join(a, 0) ==> ["ac", "bd"]
 * tf.reduce_join(a, 1) ==> ["ab", "cd"]
 * tf.reduce_join(a, -2) = tf.reduce_join(a, 0) ==> ["ac", "bd"]
 * tf.reduce_join(a, -1) = tf.reduce_join(a, 1) ==> ["ab", "cd"]
 * tf.reduce_join(a, 0, keep_dims=True) ==> [["ac", "bd"]]
 * tf.reduce_join(a, 1, keep_dims=True) ==> [["ab"], ["cd"]]
 * tf.reduce_join(a, 0, separator=".") ==> ["a.c", "b.d"]
 * tf.reduce_join(a, [0, 1]) ==> "acbd"
 * tf.reduce_join(a, [1, 0]) ==> "abcd"
 * tf.reduce_join(a, []) ==> [["a", "b"], ["c", "d"]]
 * tf.reduce_join(a) = tf.reduce_join(a, [1, 0]) ==> "abcd"
 * }</pre>
 * 
 */
@Operator(group = "strings")
public final class ReduceJoin extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.strings.ReduceJoin}
   */
  public static class Options {
    
    /**
     * @param keepDims If `True`, retain reduced dimensions with length `1`.
     */
    public Options keepDims(Boolean keepDims) {
      this.keepDims = keepDims;
      return this;
    }
    
    /**
     * @param separator The separator to use when joining.
     */
    public Options separator(String separator) {
      this.separator = separator;
      return this;
    }
    
    private Boolean keepDims;
    private String separator;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ReduceJoin operation.
   * 
   * @param scope current scope
   * @param inputs The input to be joined.  All reduced indices must have non-zero size.
   * @param reductionIndices The dimensions to reduce over.  Dimensions are reduced in the
   * order specified.  Omitting `reduction_indices` is equivalent to passing
   * `[n-1, n-2, ..., 0]`.  Negative indices from `-n` to `-1` are supported.
   * @param options carries optional attributes values
   * @return a new instance of ReduceJoin
   */
  @Endpoint(describeByClass = true)
  public static ReduceJoin create(Scope scope, Operand<TString> inputs, Operand<TInt32> reductionIndices, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ReduceJoin", scope.makeOpName("ReduceJoin"));
    opBuilder.addInput(inputs.asOutput());
    opBuilder.addInput(reductionIndices.asOutput());
    opBuilder = scope.apply(opBuilder);
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
   * @param keepDims If `True`, retain reduced dimensions with length `1`.
   */
  public static Options keepDims(Boolean keepDims) {
    return new Options().keepDims(keepDims);
  }
  
  /**
   * @param separator The separator to use when joining.
   */
  public static Options separator(String separator) {
    return new Options().separator(separator);
  }
  
  /**
   * Has shape equal to that of the input with reduced dimensions removed or
   * set to `1` depending on `keep_dims`.
   */
  public Output<TString> output() {
    return output;
  }
  
  @Override
  public Output<TString> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ReduceJoin";
  
  private Output<TString> output;
  
  private ReduceJoin(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
