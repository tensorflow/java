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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * Decodes each string in `input` into a sequence of Unicode code points.
 * <p>
 * The character codepoints for all strings are returned using a single vector
 * `char_values`, with strings expanded to characters in row-major order.
 * <p>
 * The `row_splits` tensor indicates where the codepoints for
 * each input string begin and end within the `char_values` tensor.
 * In particular, the values for the `i`th
 * string (in row-major order) are stored in the slice
 * `[row_splits[i]:row_splits[i+1]]`. Thus:
 * <ul>
 * <li>
 * `char_values[row_splits[i]+j]` is the Unicode codepoint for the `j`th
 *   character in the `i`th string (in row-major order).
 * </li>
 * <li>
 * `row_splits[i+1] - row_splits[i]` is the number of characters in the `i`th
 *   string (in row-major order).
 * 
 * @param <T> data type for {@code rowSplits()} output
 */
public final class UnicodeDecode<T extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.strings.UnicodeDecode}
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
   * Factory method to create a class wrapping a new UnicodeDecode operation.
   * 
   * @param scope current scope
   * @param input The text to be decoded. Can have any shape. Note that the output is flattened
   * to a vector of char values.
   * @param inputEncoding Text encoding of the input strings. This is any of the encodings supported
   * by ICU ucnv algorithmic converters. Examples: `"UTF-16", "US ASCII", "UTF-8"`.
   * @param Tsplits 
   * @param options carries optional attributes values
   * @return a new instance of UnicodeDecode
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> UnicodeDecode<T> create(Scope scope, Operand<TString> input, String inputEncoding, Class<T> Tsplits, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("UnicodeDecode", scope.makeOpName("UnicodeDecode"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
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
    return new UnicodeDecode<T>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new UnicodeDecode operation using default output types.
   * 
   * @param scope current scope
   * @param input The text to be decoded. Can have any shape. Note that the output is flattened
   * to a vector of char values.
   * @param inputEncoding Text encoding of the input strings. This is any of the encodings supported
   * by ICU ucnv algorithmic converters. Examples: `"UTF-16", "US ASCII", "UTF-8"`.
   * @param options carries optional attributes values
   * @return a new instance of UnicodeDecode
   */
  @Endpoint(describeByClass = true)
  public static UnicodeDecode<TInt64> create(Scope scope, Operand<TString> input, String inputEncoding, Options... options) {
    return create(scope, input, inputEncoding, TInt64.class, options);
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
   * A 1D int32 tensor containing the row splits.
   */
  public Output<T> rowSplits() {
    return rowSplits;
  }
  
  /**
   * A 1D int32 Tensor containing the decoded codepoints.
   */
  public Output<TInt32> charValues() {
    return charValues;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "UnicodeDecode";
  
  private Output<T> rowSplits;
  private Output<TInt32> charValues;
  
  private UnicodeDecode(Operation operation) {
    super(operation);
    int outputIdx = 0;
    rowSplits = operation.output(outputIdx++);
    charValues = operation.output(outputIdx++);
  }
}
