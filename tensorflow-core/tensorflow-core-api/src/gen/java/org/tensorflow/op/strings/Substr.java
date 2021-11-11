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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * Return substrings from {@code Tensor} of strings.
 * For each string in the input {@code Tensor}, creates a substring starting at index
 * {@code pos} with a total length of {@code len}.
 * <p>If {@code len} defines a substring that would extend beyond the length of the input
 * string, or if {@code len} is negative, then as many characters as possible are used.
 * <p>A negative {@code pos} indicates distance within the string backwards from the end.
 * <p>If {@code pos} specifies an index which is out of range for any of the input strings,
 * then an {@code InvalidArgumentError} is thrown.
 * <p>{@code pos} and {@code len} must have the same shape, otherwise a {@code ValueError} is thrown on
 * Op creation.
 * <p><em>NOTE</em>: {@code strings.Substr} supports broadcasting up to two dimensions. More about
 * broadcasting
 *  <a href="http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html">here</a> 
 * <hr />
 * <p>Examples
 * <p>Using scalar {@code pos} and {@code len}:
 * <pre>
 * input = [b'Hello', b'World']
 * position = 1
 * length = 3
 *
 * output = [b'ell', b'orl']
 * </pre>
 * <p>Using {@code pos} and {@code len} with same shape as {@code input}:
 * <pre>
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
 * </pre>
 * <p>Broadcasting {@code pos} and {@code len} onto {@code input}:
 * <pre>
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
 * </pre>
 * <p>Broadcasting {@code input} onto {@code pos} and {@code len}:
 * <pre>
 * input = b'thirteen'
 * position = [1, 5, 7]
 * length =   [3, 2, 1]
 *
 * output = [b'hir', b'ee', b'n']
 * </pre>
 * <p>Raises:
 * <ul>
 * <li>{@code ValueError}: If the first argument cannot be converted to a
 * Tensor of {@code dtype string}.</li>
 * <li>{@code InvalidArgumentError}: If indices are out of range.</li>
 * <li>{@code ValueError}: If {@code pos} and {@code len} are not the same shape.</li>
 * </ul>
 */
@OpMetadata(
    opType = Substr.OP_NAME,
    inputsClass = Substr.Inputs.class
)
@Operator(
    group = "strings"
)
public final class Substr extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Substr";

  private Output<TString> output;

  public Substr(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Substr operation.
   *
   * @param scope current scope
   * @param input Tensor of strings
   * @param pos Scalar defining the position of first character in each substring
   * @param len Scalar defining the number of characters to include in each substring
   * @param options carries optional attribute values
   * @param <T> data type for {@code Substr} output and operands
   * @return a new instance of Substr
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Substr create(Scope scope, Operand<TString> input,
      Operand<T> pos, Operand<T> len, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Substr");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(pos.asOutput());
    opBuilder.addInput(len.asOutput());
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
   * Sets the unit option.
   *
   * @param unit The unit that is used to create the substring.  One of: {@code "BYTE"} (for
   * defining position and length by bytes) or {@code "UTF8_CHAR"} (for the UTF-8
   * encoded Unicode code points).  The default is {@code "BYTE"}. Results are undefined if
   * {@code unit=UTF8_CHAR} and the {@code input} strings do not contain structurally valid
   * UTF-8.
   * @return this Options instance.
   */
  public static Options unit(String unit) {
    return new Options().unit(unit);
  }

  /**
   * Gets output.
   * Tensor of substrings
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
   * Optional attributes for {@link org.tensorflow.op.strings.Substr}
   */
  public static class Options {
    private String unit;

    private Options() {
    }

    /**
     * Sets the unit option.
     *
     * @param unit The unit that is used to create the substring.  One of: {@code "BYTE"} (for
     * defining position and length by bytes) or {@code "UTF8_CHAR"} (for the UTF-8
     * encoded Unicode code points).  The default is {@code "BYTE"}. Results are undefined if
     * {@code unit=UTF8_CHAR} and the {@code input} strings do not contain structurally valid
     * UTF-8.
     * @return this Options instance.
     */
    public Options unit(String unit) {
      this.unit = unit;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Substr.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<Substr> {
    /**
     * Tensor of strings
     */
    public final Operand<TString> input;

    /**
     * Scalar defining the position of first character in each substring
     */
    public final Operand<T> pos;

    /**
     * Scalar defining the number of characters to include in each substring
     */
    public final Operand<T> len;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The unit that is used to create the substring.  One of: `"BYTE"` (for
     * defining position and length by bytes) or `"UTF8_CHAR"` (for the UTF-8
     * encoded Unicode code points).  The default is `"BYTE"`. Results are undefined if
     * `unit=UTF8_CHAR` and the `input` strings do not contain structurally valid
     * UTF-8.
     */
    public final String unit;

    public Inputs(GraphOperation op) {
      super(new Substr(op), op, Arrays.asList("T", "unit"));
      int inputIndex = 0;
      input = (Operand<TString>) op.input(inputIndex++);
      pos = (Operand<T>) op.input(inputIndex++);
      len = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      unit = op.attributes().getAttrString("unit");
    }
  }
}
