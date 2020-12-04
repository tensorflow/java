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

/**
 * Transcode the input text from a source encoding to a destination encoding.
 * <p>
 * The input is a string tensor of any shape. The output is a string tensor of
 * the same shape containing the transcoded strings. Output strings are always
 * valid unicode. If the input contains invalid encoding positions, the
 * `errors` attribute sets the policy for how to deal with them. If the default
 * error-handling policy is used, invalid formatting will be substituted in the
 * output by the `replacement_char`. If the errors policy is to `ignore`, any
 * invalid encoding positions in the input are skipped and not included in the
 * output. If it set to `strict` then any invalid formatting will result in an
 * InvalidArgument error.
 * <p>
 * This operation can be used with `output_encoding = input_encoding` to enforce
 * correct formatting for inputs even if they are already in the desired encoding.
 * <p>
 * If the input is prefixed by a Byte Order Mark needed to determine encoding
 * (e.g. if the encoding is UTF-16 and the BOM indicates big-endian), then that
 * BOM will be consumed and not emitted into the output. If the input encoding
 * is marked with an explicit endianness (e.g. UTF-16-BE), then the BOM is
 * interpreted as a non-breaking-space and is preserved in the output (including
 * always for UTF-8).
 * <p>
 * The end result is that if the input is marked as an explicit endianness the
 * transcoding is faithful to all codepoints in the source. If it is not marked
 * with an explicit endianness, the BOM is not considered part of the string itself
 * but as metadata, and so is not preserved in the output.
 * <p>
 * Examples:
 * <p>
 * >>> tf.strings.unicode_transcode(["Hello", "TensorFlow", "2.x"], "UTF-8", "UTF-16-BE")
 * <tf.Tensor: shape=(3,), dtype=string, numpy=
 * array([b'\x00H\x00e\x00l\x00l\x00o',
 *        b'\x00T\x00e\x00n\x00s\x00o\x00r\x00F\x00l\x00o\x00w',
 *        b'\x002\x00.\x00x'], dtype=object)>
 * >>> tf.strings.unicode_transcode(["A", "B", "C"], "US ASCII", "UTF-8").numpy()
 * array([b'A', b'B', b'C'], dtype=object)
 */
@Operator(group = "strings")
public final class UnicodeTranscode extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.strings.UnicodeTranscode}
   */
  public static class Options {
    
    /**
     * @param errors Error handling policy when there is invalid formatting found in the input.
     * The value of 'strict' will cause the operation to produce a InvalidArgument
     * error on any invalid input formatting. A value of 'replace' (the default) will
     * cause the operation to replace any invalid formatting in the input with the
     * `replacement_char` codepoint. A value of 'ignore' will cause the operation to
     * skip any invalid formatting in the input and produce no corresponding output
     * character.
     */
    public Options errors(String errors) {
      this.errors = errors;
      return this;
    }
    
    /**
     * @param replacementChar The replacement character codepoint to be used in place of any invalid
     * formatting in the input when `errors='replace'`. Any valid unicode codepoint may
     * be used. The default value is the default unicode replacement character is
     * 0xFFFD or U+65533.)
     * <p>
     * Note that for UTF-8, passing a replacement character expressible in 1 byte, such
     * as ' ', will preserve string alignment to the source since invalid bytes will be
     * replaced with a 1-byte replacement. For UTF-16-BE and UTF-16-LE, any 1 or 2 byte
     * replacement character will preserve byte alignment to the source.
     */
    public Options replacementChar(Long replacementChar) {
      this.replacementChar = replacementChar;
      return this;
    }
    
    /**
     * @param replaceControlCharacters Whether to replace the C0 control characters (00-1F) with the
     * `replacement_char`. Default is false.
     */
    public Options replaceControlCharacters(Boolean replaceControlCharacters) {
      this.replaceControlCharacters = replaceControlCharacters;
      return this;
    }
    
    private String errors;
    private Long replacementChar;
    private Boolean replaceControlCharacters;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new UnicodeTranscode operation.
   * 
   * @param scope current scope
   * @param input The text to be processed. Can have any shape.
   * @param inputEncoding Text encoding of the input strings. This is any of the encodings supported
   * by ICU ucnv algorithmic converters. Examples: `"UTF-16", "US ASCII", "UTF-8"`.
   * @param outputEncoding The unicode encoding to use in the output. Must be one of
   * `"UTF-8", "UTF-16-BE", "UTF-32-BE"`. Multi-byte encodings will be big-endian.
   * @param options carries optional attributes values
   * @return a new instance of UnicodeTranscode
   */
  @Endpoint(describeByClass = true)
  public static UnicodeTranscode create(Scope scope, Operand<TString> input, String inputEncoding, String outputEncoding, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("UnicodeTranscode", scope.makeOpName("UnicodeTranscode"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("input_encoding", inputEncoding);
    opBuilder.setAttr("output_encoding", outputEncoding);
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
    return new UnicodeTranscode(opBuilder.build());
  }
  
  /**
   * @param errors Error handling policy when there is invalid formatting found in the input.
   * The value of 'strict' will cause the operation to produce a InvalidArgument
   * error on any invalid input formatting. A value of 'replace' (the default) will
   * cause the operation to replace any invalid formatting in the input with the
   * `replacement_char` codepoint. A value of 'ignore' will cause the operation to
   * skip any invalid formatting in the input and produce no corresponding output
   * character.
   */
  public static Options errors(String errors) {
    return new Options().errors(errors);
  }
  
  /**
   * @param replacementChar The replacement character codepoint to be used in place of any invalid
   * formatting in the input when `errors='replace'`. Any valid unicode codepoint may
   * be used. The default value is the default unicode replacement character is
   * 0xFFFD or U+65533.)
   * <p>
   * Note that for UTF-8, passing a replacement character expressible in 1 byte, such
   * as ' ', will preserve string alignment to the source since invalid bytes will be
   * replaced with a 1-byte replacement. For UTF-16-BE and UTF-16-LE, any 1 or 2 byte
   * replacement character will preserve byte alignment to the source.
   */
  public static Options replacementChar(Long replacementChar) {
    return new Options().replacementChar(replacementChar);
  }
  
  /**
   * @param replaceControlCharacters Whether to replace the C0 control characters (00-1F) with the
   * `replacement_char`. Default is false.
   */
  public static Options replaceControlCharacters(Boolean replaceControlCharacters) {
    return new Options().replaceControlCharacters(replaceControlCharacters);
  }
  
  /**
   * A string tensor containing unicode text encoded using `output_encoding`.
   */
  public Output<TString> output() {
    return output;
  }
  
  @Override
  public Output<TString> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "UnicodeTranscode";
  
  private Output<TString> output;
  
  private UnicodeTranscode(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
