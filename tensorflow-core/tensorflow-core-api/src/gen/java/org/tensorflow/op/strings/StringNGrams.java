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

import java.util.List;
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
 * Creates ngrams from ragged string data.
 * <p>
 * This op accepts a ragged tensor with 1 ragged dimension containing only
 * strings and outputs a ragged tensor with 1 ragged dimension containing ngrams
 * of that string, joined along the innermost axis.
 * 
 * @param <T> data type for {@code ngramsSplits()} output
 */
@Operator(group = "strings")
public final class StringNGrams<T extends TNumber> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new StringNGrams operation.
   * 
   * @param scope current scope
   * @param data The values tensor of the ragged string tensor to make ngrams out of. Must be a
   * 1D string tensor.
   * @param dataSplits The splits tensor of the ragged string tensor to make ngrams out of.
   * @param separator The string to append between elements of the token. Use "" for no separator.
   * @param ngramWidths The sizes of the ngrams to create.
   * @param leftPad The string to use to pad the left side of the ngram sequence. Only used if
   * pad_width != 0.
   * @param rightPad The string to use to pad the right side of the ngram sequence. Only used if
   * pad_width != 0.
   * @param padWidth The number of padding elements to add to each side of each
   * sequence. Note that padding will never be greater than 'ngram_widths'-1
   * regardless of this value. If `pad_width=-1`, then add `max(ngram_widths)-1`
   * elements.
   * @param preserveShortSequences 
   * @return a new instance of StringNGrams
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> StringNGrams<T> create(Scope scope, Operand<TString> data, Operand<T> dataSplits, String separator, List<Long> ngramWidths, String leftPad, String rightPad, Long padWidth, Boolean preserveShortSequences) {
    OperationBuilder opBuilder = scope.env().opBuilder("StringNGrams", scope.makeOpName("StringNGrams"));
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(dataSplits.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("separator", separator);
    long[] ngramWidthsArray = new long[ngramWidths.size()];
    for (int i = 0; i < ngramWidthsArray.length; ++i) {
      ngramWidthsArray[i] = ngramWidths.get(i);
    }
    opBuilder.setAttr("ngram_widths", ngramWidthsArray);
    opBuilder.setAttr("left_pad", leftPad);
    opBuilder.setAttr("right_pad", rightPad);
    opBuilder.setAttr("pad_width", padWidth);
    opBuilder.setAttr("preserve_short_sequences", preserveShortSequences);
    return new StringNGrams<T>(opBuilder.build());
  }
  
  /**
   * The values tensor of the output ngrams ragged tensor.
   */
  public Output<TString> ngrams() {
    return ngrams;
  }
  
  /**
   * The splits tensor of the output ngrams ragged tensor.
   */
  public Output<T> ngramsSplits() {
    return ngramsSplits;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StringNGrams";
  
  private Output<TString> ngrams;
  private Output<T> ngramsSplits;
  
  private StringNGrams(Operation operation) {
    super(operation);
    int outputIdx = 0;
    ngrams = operation.output(outputIdx++);
    ngramsSplits = operation.output(outputIdx++);
  }
}
