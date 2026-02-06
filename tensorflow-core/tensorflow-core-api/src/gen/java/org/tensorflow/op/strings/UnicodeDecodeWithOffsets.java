/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * Decodes each string in {@code input} into a sequence of Unicode code points.
 * The character codepoints for all strings are returned using a single vector
 * {@code char_values}, with strings expanded to characters in row-major order.
 * Similarly, the character start byte offsets are returned using a single vector
 * {@code char_to_byte_starts}, with strings expanded in row-major order.
 * <p>The {@code row_splits} tensor indicates where the codepoints and start offsets for
 * each input string begin and end within the {@code char_values} and
 * {@code char_to_byte_starts} tensors.  In particular, the values for the {@code i}th
 * string (in row-major order) are stored in the slice
 * {@code [row_splits[i]:row_splits[i+1]]}. Thus:
 * <ul>
 * <li>{@code char_values[row_splits[i]+j]} is the Unicode codepoint for the {@code j}th
 * character in the {@code i}th string (in row-major order).</li>
 * <li>{@code char_to_bytes_starts[row_splits[i]+j]} is the start byte offset for the {@code j}th
 * character in the {@code i}th string (in row-major order).</li>
 * <li>{@code row_splits[i+1] - row_splits[i]} is the number of characters in the {@code i}th
 * string (in row-major order).</li>
 * </ul>
 */
@OpMetadata(
    opType = UnicodeDecodeWithOffsets.OP_NAME,
    inputsClass = UnicodeDecodeWithOffsets.Inputs.class
)
@Operator(
    group = "strings"
)
public final class UnicodeDecodeWithOffsets<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UnicodeDecodeWithOffsets";

  private Output<T> rowSplits;

  private Output<TInt32> charValues;

  private Output<TInt64> charToByteStarts;

  public UnicodeDecodeWithOffsets(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    rowSplits = operation.output(outputIdx++);
    charValues = operation.output(outputIdx++);
    charToByteStarts = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UnicodeDecodeWithOffsets operation.
   *
   * @param scope current scope
   * @param input The text to be decoded. Can have any shape. Note that the output is flattened
   * to a vector of char values.
   * @param inputEncoding Text encoding of the input strings. This is any of the encodings supported
   * by ICU ucnv algorithmic converters. Examples: {@code "UTF-16", "US ASCII", "UTF-8"}.
   * @param Tsplits The value of the Tsplits attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code UnicodeDecodeWithOffsets} output and operands
   * @return a new instance of UnicodeDecodeWithOffsets
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> UnicodeDecodeWithOffsets<T> create(Scope scope,
      Operand<TString> input, String inputEncoding, Class<T> Tsplits, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UnicodeDecodeWithOffsets");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("input_encoding", inputEncoding);
    opBuilder.setAttr("Tsplits", Operands.toDataType(Tsplits));
    if (options != null) {
      for (Options opts : options) {
        if (opts.errors != null) {
          opBuilder.setAttr("errors", opts.errors);
        }
        if (opts.replacementChar != null) {
          opBuilder.setAttr("replacement_char", opts.replacementChar);
        }
        if (opts.replaceControlCharacters != null) {
          opBuilder.setAttr("replace_control_characters", opts.replaceControlCharacters);
        }
      }
    }
    return new UnicodeDecodeWithOffsets<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new UnicodeDecodeWithOffsets operation, with the default output types.
   *
   * @param scope current scope
   * @param input The text to be decoded. Can have any shape. Note that the output is flattened
   * to a vector of char values.
   * @param inputEncoding Text encoding of the input strings. This is any of the encodings supported
   * by ICU ucnv algorithmic converters. Examples: {@code "UTF-16", "US ASCII", "UTF-8"}.
   * @param options carries optional attribute values
   * @return a new instance of UnicodeDecodeWithOffsets, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static UnicodeDecodeWithOffsets<TInt64> create(Scope scope, Operand<TString> input,
      String inputEncoding, Options... options) {
    return create(scope, input, inputEncoding, TInt64.class, options);
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
   * 0xFFFD or U+65533.)
   * @return this Options instance.
   */
  public static Options replacementChar(Long replacementChar) {
    return new Options().replacementChar(replacementChar);
  }

  /**
   * Sets the replaceControlCharacters option.
   *
   * @param replaceControlCharacters Whether to replace the C0 control characters (00-1F) with the
   * {@code replacement_char}. Default is false.
   * @return this Options instance.
   */
  public static Options replaceControlCharacters(Boolean replaceControlCharacters) {
    return new Options().replaceControlCharacters(replaceControlCharacters);
  }

  /**
   * Gets rowSplits.
   * A 1D int32 tensor containing the row splits.
   * @return rowSplits.
   */
  public Output<T> rowSplits() {
    return rowSplits;
  }

  /**
   * Gets charValues.
   * A 1D int32 Tensor containing the decoded codepoints.
   * @return charValues.
   */
  public Output<TInt32> charValues() {
    return charValues;
  }

  /**
   * Gets charToByteStarts.
   * A 1D int32 Tensor containing the byte index in the input string where each
   * character in {@code char_values} starts.
   * @return charToByteStarts.
   */
  public Output<TInt64> charToByteStarts() {
    return charToByteStarts;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.strings.UnicodeDecodeWithOffsets}
   */
  public static class Options {
    private String errors;

    private Long replacementChar;

    private Boolean replaceControlCharacters;

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
     * 0xFFFD or U+65533.)
     * @return this Options instance.
     */
    public Options replacementChar(Long replacementChar) {
      this.replacementChar = replacementChar;
      return this;
    }

    /**
     * Sets the replaceControlCharacters option.
     *
     * @param replaceControlCharacters Whether to replace the C0 control characters (00-1F) with the
     * {@code replacement_char}. Default is false.
     * @return this Options instance.
     */
    public Options replaceControlCharacters(Boolean replaceControlCharacters) {
      this.replaceControlCharacters = replaceControlCharacters;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = UnicodeDecodeWithOffsets.class
  )
  public static class Inputs extends RawOpInputs<UnicodeDecodeWithOffsets<?>> {
    /**
     * The text to be decoded. Can have any shape. Note that the output is flattened
     * to a vector of char values.
     */
    public final Operand<TString> input;

    /**
     * Text encoding of the input strings. This is any of the encodings supported
     * by ICU ucnv algorithmic converters. Examples: {@code "UTF-16", "US ASCII", "UTF-8"}.
     */
    public final String inputEncoding;

    /**
     * Error handling policy when there is invalid formatting found in the input.
     * The value of 'strict' will cause the operation to produce a InvalidArgument
     * error on any invalid input formatting. A value of 'replace' (the default) will
     * cause the operation to replace any invalid formatting in the input with the
     * {@code replacement_char} codepoint. A value of 'ignore' will cause the operation to
     * skip any invalid formatting in the input and produce no corresponding output
     * character.
     */
    public final String errors;

    /**
     * The replacement character codepoint to be used in place of any invalid
     * formatting in the input when {@code errors='replace'}. Any valid unicode codepoint may
     * be used. The default value is the default unicode replacement character is
     * 0xFFFD or U+65533.)
     */
    public final long replacementChar;

    /**
     * Whether to replace the C0 control characters (00-1F) with the
     * {@code replacement_char}. Default is false.
     */
    public final boolean replaceControlCharacters;

    /**
     * The Tsplits attribute
     */
    public final DataType Tsplits;

    public Inputs(GraphOperation op) {
      super(new UnicodeDecodeWithOffsets<>(op), op, Arrays.asList("input_encoding", "errors", "replacement_char", "replace_control_characters", "Tsplits"));
      int inputIndex = 0;
      input = (Operand<TString>) op.input(inputIndex++);
      inputEncoding = op.attributes().getAttrString("input_encoding");
      errors = op.attributes().getAttrString("errors");
      replacementChar = op.attributes().getAttrInt("replacement_char");
      replaceControlCharacters = op.attributes().getAttrBool("replace_control_characters");
      Tsplits = op.attributes().getAttrType("Tsplits");
    }
  }
}
