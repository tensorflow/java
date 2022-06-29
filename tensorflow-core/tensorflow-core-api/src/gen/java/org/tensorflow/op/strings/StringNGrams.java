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
import java.util.List;
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
 * Creates ngrams from ragged string data.
 * This op accepts a ragged tensor with 1 ragged dimension containing only
 * strings and outputs a ragged tensor with 1 ragged dimension containing ngrams
 * of that string, joined along the innermost axis.
 *
 * @param <T> data type for {@code ngrams_splits} output
 */
@OpMetadata(
    opType = StringNGrams.OP_NAME,
    inputsClass = StringNGrams.Inputs.class
)
@Operator(
    group = "strings"
)
public final class StringNGrams<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StringNGrams";

  private Output<TString> ngrams;

  private Output<T> ngramsSplits;

  public StringNGrams(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    ngrams = operation.output(outputIdx++);
    ngramsSplits = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StringNGrams operation.
   *
   * @param scope current scope
   * @param data The values tensor of the ragged string tensor to make ngrams out of. Must be a
   * 1D string tensor.
   * @param dataSplits The splits tensor of the ragged string tensor to make ngrams out of.
   * @param separator The string to append between elements of the token. Use &quot;&quot; for no separator.
   * @param ngramWidths The sizes of the ngrams to create.
   * @param leftPad The string to use to pad the left side of the ngram sequence. Only used if
   * pad_width != 0.
   * @param rightPad The string to use to pad the right side of the ngram sequence. Only used if
   * pad_width != 0.
   * @param padWidth The number of padding elements to add to each side of each
   * sequence. Note that padding will never be greater than 'ngram_widths'-1
   * regardless of this value. If {@code pad_width=-1}, then add {@code max(ngram_widths)-1}
   * elements.
   * @param preserveShortSequences The value of the preserveShortSequences attribute
   * @param <T> data type for {@code StringNGrams} output and operands
   * @return a new instance of StringNGrams
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> StringNGrams<T> create(Scope scope, Operand<TString> data,
      Operand<T> dataSplits, String separator, List<Long> ngramWidths, String leftPad,
      String rightPad, Long padWidth, Boolean preserveShortSequences) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StringNGrams");
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(dataSplits.asOutput());
    opBuilder.setAttr("separator", separator);
    long[] ngramWidthsArray = new long[ngramWidths.size()];
    for (int i = 0 ; i < ngramWidthsArray.length ; i++) {
      ngramWidthsArray[i] = ngramWidths.get(i);
    }
    opBuilder.setAttr("ngram_widths", ngramWidthsArray);
    opBuilder.setAttr("left_pad", leftPad);
    opBuilder.setAttr("right_pad", rightPad);
    opBuilder.setAttr("pad_width", padWidth);
    opBuilder.setAttr("preserve_short_sequences", preserveShortSequences);
    return new StringNGrams<>(opBuilder.build());
  }

  /**
   * Gets ngrams.
   * The values tensor of the output ngrams ragged tensor.
   * @return ngrams.
   */
  public Output<TString> ngrams() {
    return ngrams;
  }

  /**
   * Gets ngramsSplits.
   * The splits tensor of the output ngrams ragged tensor.
   * @return ngramsSplits.
   */
  public Output<T> ngramsSplits() {
    return ngramsSplits;
  }

  @OpInputsMetadata(
      outputsClass = StringNGrams.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<StringNGrams<T>> {
    /**
     * The values tensor of the ragged string tensor to make ngrams out of. Must be a
     * 1D string tensor.
     */
    public final Operand<TString> data;

    /**
     * The splits tensor of the ragged string tensor to make ngrams out of.
     */
    public final Operand<T> dataSplits;

    /**
     * The string to append between elements of the token. Use "" for no separator.
     */
    public final String separator;

    /**
     * The sizes of the ngrams to create.
     */
    public final long[] ngramWidths;

    /**
     * The string to use to pad the left side of the ngram sequence. Only used if
     * pad_width != 0.
     */
    public final String leftPad;

    /**
     * The string to use to pad the right side of the ngram sequence. Only used if
     * pad_width != 0.
     */
    public final String rightPad;

    /**
     * The number of padding elements to add to each side of each
     * sequence. Note that padding will never be greater than 'ngram_widths'-1
     * regardless of this value. If `pad_width=-1`, then add `max(ngram_widths)-1`
     * elements.
     */
    public final long padWidth;

    /**
     * The preserveShortSequences attribute
     */
    public final boolean preserveShortSequences;

    /**
     * The Tsplits attribute
     */
    public final DataType Tsplits;

    public Inputs(GraphOperation op) {
      super(new StringNGrams<>(op), op, Arrays.asList("separator", "ngram_widths", "left_pad", "right_pad", "pad_width", "preserve_short_sequences", "Tsplits"));
      int inputIndex = 0;
      data = (Operand<TString>) op.input(inputIndex++);
      dataSplits = (Operand<T>) op.input(inputIndex++);
      separator = op.attributes().getAttrString("separator");
      ngramWidths = op.attributes().getAttrIntList("ngram_widths");
      leftPad = op.attributes().getAttrString("left_pad");
      rightPad = op.attributes().getAttrString("right_pad");
      padWidth = op.attributes().getAttrInt("pad_width");
      preserveShortSequences = op.attributes().getAttrBool("preserve_short_sequences");
      Tsplits = op.attributes().getAttrType("Tsplits");
    }
  }
}
