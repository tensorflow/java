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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Split elements of `source` based on `sep` into a `SparseTensor`.
 * <p>
 * Let N be the size of source (typically N will be the batch size). Split each
 * element of `source` based on `sep` and return a `SparseTensor`
 * containing the split tokens. Empty tokens are ignored.
 * <p>
 * For example, N = 2, source[0] is 'hello world' and source[1] is 'a b c',
 * then the output will be
 * <pre>{@code
 * st.indices = [0, 0;
 *               0, 1;
 *               1, 0;
 *               1, 1;
 *               1, 2]
 * st.shape = [2, 3]
 * st.values = ['hello', 'world', 'a', 'b', 'c']
 * }</pre>
 * If `sep` is given, consecutive delimiters are not grouped together and are
 * deemed to delimit empty strings. For example, source of `"1<>2<><>3"` and
 * sep of `"<>"` returns `["1", "2", "", "3"]`. If `sep` is None or an empty
 * string, consecutive whitespace are regarded as a single separator, and the
 * result will contain no empty strings at the startor end if the string has
 * leading or trailing whitespace.
 * <p>
 * Note that the above mentioned behavior matches python's str.split.
 */
@Operator(group = "strings")
public final class StringSplit extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.strings.StringSplit}
   */
  public static class Options {
    
    /**
     * @param maxsplit An `int`. If `maxsplit > 0`, limit of the split of the result.
     */
    public Options maxsplit(Long maxsplit) {
      this.maxsplit = maxsplit;
      return this;
    }
    
    private Long maxsplit;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new StringSplit operation.
   * 
   * @param scope current scope
   * @param input `1-D` string `Tensor`, the strings to split.
   * @param sep `0-D` string `Tensor`, the delimiter character.
   * @param options carries optional attributes values
   * @return a new instance of StringSplit
   */
  @Endpoint(describeByClass = true)
  public static StringSplit create(Scope scope, Operand<TString> input, Operand<TString> sep, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("StringSplitV2", scope.makeOpName("StringSplit"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(sep.asOutput());
    opBuilder = scope.apply(opBuilder);
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
   * @param maxsplit An `int`. If `maxsplit > 0`, limit of the split of the result.
   */
  public static Options maxsplit(Long maxsplit) {
    return new Options().maxsplit(maxsplit);
  }
  
  /**
   */
  public Output<TInt64> indices() {
    return indices;
  }
  
  /**
   */
  public Output<TString> values() {
    return values;
  }
  
  /**
   */
  public Output<TInt64> shape() {
    return shape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StringSplitV2";
  
  private Output<TInt64> indices;
  private Output<TString> values;
  private Output<TInt64> shape;
  
  private StringSplit(Operation operation) {
    super(operation);
    int outputIdx = 0;
    indices = operation.output(outputIdx++);
    values = operation.output(outputIdx++);
    shape = operation.output(outputIdx++);
  }
}
