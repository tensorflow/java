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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * Encode a tensor of ints into unicode strings.
 * Returns a vector of strings, where {@code output[i]} is constructed by encoding the
 * Unicode codepoints in {@code input_values[input_splits[i]:input_splits[i+1]]}
 * using {@code output_encoding}.
 * <hr />
 * <p>Example:
 * <pre>
 * input_values = [72, 101, 108, 108, 111, 87, 111, 114, 108, 100]
 * input_splits = [0, 5, 10]
 * output_encoding = 'UTF-8'
 *
 * output = ['Hello', 'World']
 * </pre>
 */
@OpMetadata(
    opType = UnicodeEncode.OP_NAME,
    inputsClass = UnicodeEncode.Inputs.class
)
public final class UnicodeEncode extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UnicodeEncode";

  private Output<TString> output;

  public UnicodeEncode(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UnicodeEncode operation.
   *
   * @param scope current scope
   * @param inputValues A 1D tensor containing the unicode codepoints that should be encoded.
   * @param inputSplits A 1D tensor specifying how the unicode codepoints should be split into strings.
   * In particular, {@code output[i]} is constructed by encoding the codepoints in the
   * slice {@code input_values[input_splits[i]:input_splits[i+1]]}.
   * @param outputEncoding Unicode encoding of the output strings. Valid encodings are: {@code "UTF-8", "UTF-16-BE", and "UTF-32-BE"}.
   * @param options carries optional attribute values
   * @return a new instance of UnicodeEncode
   */
  @Endpoint(
      describeByClass = true
  )
  public static UnicodeEncode create(Scope scope, Operand<TInt32> inputValues,
      Operand<? extends TNumber> inputSplits, String outputEncoding, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UnicodeEncode");
    opBuilder.addInput(inputValues.asOutput());
    opBuilder.addInput(inputSplits.asOutput());
    opBuilder.setAttr("output_encoding", outputEncoding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.errors != null) {
          opBuilder.setAttr("errors", opts.errors);
        }
        if (opts.replacementChar != null) {
          opBuilder.setAttr("replacement_char", opts.replacementChar);
        }
      }
    }
    return new UnicodeEncode(opBuilder.build());
  }

  /**
   * Sets the errors option.
   *
   * @param errors Error handling policy when there is invalid formatting found in the input.
   * The value of 'strict' will cause the operation to produce a InvalidArgument
   * error on any invalid input formatting. A value of 'replace' (the default) will
   * cause the operation to replace any invalid formatting in the input with the
   * {@code replacement_char} codepoint. A value of 'ignore' will cause the operation to
   * skip any invalid formatting in the input and produce no corresponding output
   * character.
   * @return this Options instance.
   */
  public static Options errors(String errors) {
    return new Options().errors(errors);
  }

  /**
   * Sets the replacementChar option.
   *
   * @param replacementChar The replacement character codepoint to be used in place of any invalid
   * formatting in the input when {@code errors='replace'}. Any valid unicode codepoint may
   * be used. The default value is the default unicode replacement character is
   * 0xFFFD (U+65533).
   * @return this Options instance.
   */
  public static Options replacementChar(Long replacementChar) {
    return new Options().replacementChar(replacementChar);
  }

  /**
   * Gets output.
   * The 1-D Tensor of strings encoded from the provided unicode codepoints.
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
   * Optional attributes for {@link org.tensorflow.op.strings.UnicodeEncode}
   */
  public static class Options {
    private String errors;

    private Long replacementChar;

    private Options() {
    }

    /**
     * Sets the errors option.
     *
     * @param errors Error handling policy when there is invalid formatting found in the input.
     * The value of 'strict' will cause the operation to produce a InvalidArgument
     * error on any invalid input formatting. A value of 'replace' (the default) will
     * cause the operation to replace any invalid formatting in the input with the
     * {@code replacement_char} codepoint. A value of 'ignore' will cause the operation to
     * skip any invalid formatting in the input and produce no corresponding output
     * character.
     * @return this Options instance.
     */
    public Options errors(String errors) {
      this.errors = errors;
      return this;
    }

    /**
     * Sets the replacementChar option.
     *
     * @param replacementChar The replacement character codepoint to be used in place of any invalid
     * formatting in the input when {@code errors='replace'}. Any valid unicode codepoint may
     * be used. The default value is the default unicode replacement character is
     * 0xFFFD (U+65533).
     * @return this Options instance.
     */
    public Options replacementChar(Long replacementChar) {
      this.replacementChar = replacementChar;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = UnicodeEncode.class
  )
  public static class Inputs extends RawOpInputs<UnicodeEncode> {
    /**
     * A 1D tensor containing the unicode codepoints that should be encoded.
     */
    public final Operand<TInt32> inputValues;

    /**
     * A 1D tensor specifying how the unicode codepoints should be split into strings.
     * In particular, {@code output[i]} is constructed by encoding the codepoints in the
     * slice {@code input_values[input_splits[i]:input_splits[i+1]]}.
     */
    public final Operand<? extends TNumber> inputSplits;

    /**
     * Error handling policy when there is invalid formatting found in the input.
     * The value of 'strict' will cause the operation to produce a InvalidArgument
     * error on any invalid input formatting. A value of 'replace' (the default) will
     * cause the operation to replace any invalid formatting in the input with the
     * `replacement_char` codepoint. A value of 'ignore' will cause the operation to
     * skip any invalid formatting in the input and produce no corresponding output
     * character.
     */
    public final String errors;

    /**
     * Unicode encoding of the output strings. Valid encodings are: `"UTF-8",
     * "UTF-16-BE", and "UTF-32-BE"`.
     */
    public final String outputEncoding;

    /**
     * The replacement character codepoint to be used in place of any invalid
     * formatting in the input when `errors='replace'`. Any valid unicode codepoint may
     * be used. The default value is the default unicode replacement character is
     * 0xFFFD (U+65533).
     */
    public final long replacementChar;

    /**
     * The Tsplits attribute
     */
    public final DataType Tsplits;

    public Inputs(GraphOperation op) {
      super(new UnicodeEncode(op), op, Arrays.asList("errors", "output_encoding", "replacement_char", "Tsplits"));
      int inputIndex = 0;
      inputValues = (Operand<TInt32>) op.input(inputIndex++);
      inputSplits = (Operand<? extends TNumber>) op.input(inputIndex++);
      errors = op.attributes().getAttrString("errors");
      outputEncoding = op.attributes().getAttrString("output_encoding");
      replacementChar = op.attributes().getAttrInt("replacement_char");
      Tsplits = op.attributes().getAttrType("Tsplits");
    }
  }
}
