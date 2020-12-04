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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Return substrings from `Tensor` of strings.
 * <p>
 * For each string in the input `Tensor`, creates a substring starting at index
 * `pos` with a total length of `len`.
 * <p>
 * If `len` defines a substring that would extend beyond the length of the input
 * string, or if `len` is negative, then as many characters as possible are used.
 * <p>
 * A negative `pos` indicates distance within the string backwards from the end.
 * <p>
 * If `pos` specifies an index which is out of range for any of the input strings,
 * then an `InvalidArgumentError` is thrown.
 * <p>
 * `pos` and `len` must have the same shape, otherwise a `ValueError` is thrown on
 * Op creation.
 * <p>
 * <i>NOTE</i>: `strings.Substr` supports broadcasting up to two dimensions. More about
 * broadcasting
 * [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
 * <p>
 * ---
 * <p>
 * Examples
 * <p>
 * Using scalar `pos` and `len`:
 * <pre>{@code
 * input = [b'Hello', b'World']
 * position = 1
 * length = 3
 * 
 * output = [b'ell', b'orl']
 * }</pre>
 * Using `pos` and `len` with same shape as `input`:
 * <pre>{@code
 * input = [[b'ten', b'eleven', b'twelve'],
 *          [b'thirteen', b'fourteen', b'fifteen'],
 *          [b'sixteen', b'seventeen', b'eighteen']]
 * position = [[1, 2, 3],
 *             [1, 2, 3],
 *             [1, 2, 3]]
 * length =   [[2, 3, 4],
 *             [4, 3, 2],
 *             [5, 5, 5]]
 * 
 * output = [[b'en', b'eve', b'lve'],
 *           [b'hirt', b'urt', b'te'],
 *           [b'ixtee', b'vente', b'hteen']]
 * }</pre>
 * Broadcasting `pos` and `len` onto `input`:
 * <pre>{@code
 * input = [[b'ten', b'eleven', b'twelve'],
 *          [b'thirteen', b'fourteen', b'fifteen'],
 *          [b'sixteen', b'seventeen', b'eighteen'],
 *          [b'nineteen', b'twenty', b'twentyone']]
 * position = [1, 2, 3]
 * length =   [1, 2, 3]
 * 
 * output = [[b'e', b'ev', b'lve'],
 *           [b'h', b'ur', b'tee'],
 *           [b'i', b've', b'hte'],
 *           [b'i', b'en', b'nty']]
 * }</pre>
 * Broadcasting `input` onto `pos` and `len`:
 * <pre>{@code
 * input = b'thirteen'
 * position = [1, 5, 7]
 * length =   [3, 2, 1]
 * 
 * output = [b'hir', b'ee', b'n']
 * }</pre>
 * Raises:
 * <p>
 *   * `ValueError`: If the first argument cannot be converted to a
 *      Tensor of `dtype string`.
 *   * `InvalidArgumentError`: If indices are out of range.
 *   * `ValueError`: If `pos` and `len` are not the same shape.
 * 
 */
@Operator(group = "strings")
public final class Substr extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.strings.Substr}
   */
  public static class Options {
    
    /**
     * @param unit The unit that is used to create the substring.  One of: `"BYTE"` (for
     * defining position and length by bytes) or `"UTF8_CHAR"` (for the UTF-8
     * encoded Unicode code points).  The default is `"BYTE"`. Results are undefined if
     * `unit=UTF8_CHAR` and the `input` strings do not contain structurally valid
     * UTF-8.
     */
    public Options unit(String unit) {
      this.unit = unit;
      return this;
    }
    
    private String unit;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Substr operation.
   * 
   * @param scope current scope
   * @param input Tensor of strings
   * @param pos Scalar defining the position of first character in each substring
   * @param len Scalar defining the number of characters to include in each substring
   * @param options carries optional attributes values
   * @return a new instance of Substr
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> Substr create(Scope scope, Operand<TString> input, Operand<T> pos, Operand<T> len, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Substr", scope.makeOpName("Substr"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(pos.asOutput());
    opBuilder.addInput(len.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.unit != null) {
          opBuilder.setAttr("unit", opts.unit);
        }
      }
    }
    return new Substr(opBuilder.build());
  }
  
  /**
   * @param unit The unit that is used to create the substring.  One of: `"BYTE"` (for
   * defining position and length by bytes) or `"UTF8_CHAR"` (for the UTF-8
   * encoded Unicode code points).  The default is `"BYTE"`. Results are undefined if
   * `unit=UTF8_CHAR` and the `input` strings do not contain structurally valid
   * UTF-8.
   */
  public static Options unit(String unit) {
    return new Options().unit(unit);
  }
  
  /**
   * Tensor of substrings
   */
  public Output<TString> output() {
    return output;
  }
  
  @Override
  public Output<TString> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Substr";
  
  private Output<TString> output;
  
  private Substr(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
