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

package org.tensorflow.op.io;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Convert CSV records to tensors. Each column maps to one tensor.
 * <p>
 * RFC 4180 format is expected for the CSV records.
 * (https://tools.ietf.org/html/rfc4180)
 * Note that we allow leading and trailing spaces with int or float field.
 */
@Operator(group = "io")
public final class DecodeCsv extends RawOp implements Iterable<Operand<TType>> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.io.DecodeCsv}
   */
  public static class Options {
    
    /**
     * @param fieldDelim char delimiter to separate fields in a record.
     */
    public Options fieldDelim(String fieldDelim) {
      this.fieldDelim = fieldDelim;
      return this;
    }
    
    /**
     * @param useQuoteDelim If false, treats double quotation marks as regular
     * characters inside of the string fields (ignoring RFC 4180, Section 2,
     * Bullet 5).
     */
    public Options useQuoteDelim(Boolean useQuoteDelim) {
      this.useQuoteDelim = useQuoteDelim;
      return this;
    }
    
    /**
     * @param naValue Additional string to recognize as NA/NaN.
     */
    public Options naValue(String naValue) {
      this.naValue = naValue;
      return this;
    }
    
    /**
     * @param selectCols 
     */
    public Options selectCols(List<Long> selectCols) {
      this.selectCols = selectCols;
      return this;
    }
    
    private String fieldDelim;
    private Boolean useQuoteDelim;
    private String naValue;
    private List<Long> selectCols;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new DecodeCsv operation.
   * 
   * @param scope current scope
   * @param records Each string is a record/row in the csv and all records should have
   * the same format.
   * @param recordDefaults One tensor per column of the input record, with either a
   * scalar default value for that column or an empty vector if the column is
   * required.
   * @param options carries optional attributes values
   * @return a new instance of DecodeCsv
   */
  @Endpoint(describeByClass = true)
  public static DecodeCsv create(Scope scope, Operand<TString> records, Iterable<Operand<?>> recordDefaults, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DecodeCSV", scope.makeOpName("DecodeCsv"));
    opBuilder.addInput(records.asOutput());
    opBuilder.addInputList(Operands.asOutputs(recordDefaults));
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.fieldDelim != null) {
          opBuilder.setAttr("field_delim", opts.fieldDelim);
        }
        if (opts.useQuoteDelim != null) {
          opBuilder.setAttr("use_quote_delim", opts.useQuoteDelim);
        }
        if (opts.naValue != null) {
          opBuilder.setAttr("na_value", opts.naValue);
        }
        if (opts.selectCols != null) {
          long[] selectColsArray = new long[opts.selectCols.size()];
          for (int i = 0; i < selectColsArray.length; ++i) {
            selectColsArray[i] = opts.selectCols.get(i);
          }
          opBuilder.setAttr("select_cols", selectColsArray);
        }
      }
    }
    return new DecodeCsv(opBuilder.build());
  }
  
  /**
   * @param fieldDelim char delimiter to separate fields in a record.
   */
  public static Options fieldDelim(String fieldDelim) {
    return new Options().fieldDelim(fieldDelim);
  }
  
  /**
   * @param useQuoteDelim If false, treats double quotation marks as regular
   * characters inside of the string fields (ignoring RFC 4180, Section 2,
   * Bullet 5).
   */
  public static Options useQuoteDelim(Boolean useQuoteDelim) {
    return new Options().useQuoteDelim(useQuoteDelim);
  }
  
  /**
   * @param naValue Additional string to recognize as NA/NaN.
   */
  public static Options naValue(String naValue) {
    return new Options().naValue(naValue);
  }
  
  /**
   * @param selectCols 
   */
  public static Options selectCols(List<Long> selectCols) {
    return new Options().selectCols(selectCols);
  }
  
  /**
   * Each tensor will have the same shape as records.
   */
  public List<Output<?>> output() {
    return output;
  }
  
  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) output.iterator();
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DecodeCSV";
  
  private List<Output<?>> output;
  
  private DecodeCsv(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }
}
