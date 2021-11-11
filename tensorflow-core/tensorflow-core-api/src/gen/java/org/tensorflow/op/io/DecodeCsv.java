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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Convert CSV records to tensors. Each column maps to one tensor.
 * RFC 4180 format is expected for the CSV records.
 * (https://tools.ietf.org/html/rfc4180)
 * Note that we allow leading and trailing spaces with int or float field.
 */
@OpMetadata(
    opType = DecodeCsv.OP_NAME,
    inputsClass = DecodeCsv.Inputs.class
)
@Operator(
    group = "io"
)
public final class DecodeCsv extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DecodeCSV";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  public DecodeCsv(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new DecodeCSV operation.
   *
   * @param scope current scope
   * @param records Each string is a record/row in the csv and all records should have
   * the same format.
   * @param recordDefaults One tensor per column of the input record, with either a
   * scalar default value for that column or an empty vector if the column is
   * required.
   * @param options carries optional attribute values
   * @return a new instance of DecodeCsv
   */
  @Endpoint(
      describeByClass = true
  )
  public static DecodeCsv create(Scope scope, Operand<TString> records,
      Iterable<Operand<?>> recordDefaults, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DecodeCsv");
    opBuilder.addInput(records.asOutput());
    opBuilder.addInputList(Operands.asOutputs(recordDefaults));
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
          for (int i = 0 ; i < selectColsArray.length ; i++) {
            selectColsArray[i] = opts.selectCols.get(i);
          }
          opBuilder.setAttr("select_cols", selectColsArray);
        }
      }
    }
    return new DecodeCsv(opBuilder.build());
  }

  /**
   * Sets the fieldDelim option.
   *
   * @param fieldDelim char delimiter to separate fields in a record.
   * @return this Options instance.
   */
  public static Options fieldDelim(String fieldDelim) {
    return new Options().fieldDelim(fieldDelim);
  }

  /**
   * Sets the useQuoteDelim option.
   *
   * @param useQuoteDelim If false, treats double quotation marks as regular
   * characters inside of the string fields (ignoring RFC 4180, Section 2,
   * Bullet 5).
   * @return this Options instance.
   */
  public static Options useQuoteDelim(Boolean useQuoteDelim) {
    return new Options().useQuoteDelim(useQuoteDelim);
  }

  /**
   * Sets the naValue option.
   *
   * @param naValue Additional string to recognize as NA/NaN.
   * @return this Options instance.
   */
  public static Options naValue(String naValue) {
    return new Options().naValue(naValue);
  }

  /**
   * Sets the selectCols option.
   *
   * @param selectCols the selectCols option
   * @return this Options instance.
   */
  public static Options selectCols(List<Long> selectCols) {
    return new Options().selectCols(selectCols);
  }

  /**
   * Sets the selectCols option.
   *
   * @param selectCols the selectCols option
   * @return this Options instance.
   */
  public static Options selectCols(Long... selectCols) {
    return new Options().selectCols(selectCols);
  }

  /**
   * Gets output.
   * Each tensor will have the same shape as records.
   * @return output.
   */
  public List<Output<?>> output() {
    return output;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) output.iterator();
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.io.DecodeCsv}
   */
  public static class Options {
    private String fieldDelim;

    private Boolean useQuoteDelim;

    private String naValue;

    private List<Long> selectCols;

    private Options() {
    }

    /**
     * Sets the fieldDelim option.
     *
     * @param fieldDelim char delimiter to separate fields in a record.
     * @return this Options instance.
     */
    public Options fieldDelim(String fieldDelim) {
      this.fieldDelim = fieldDelim;
      return this;
    }

    /**
     * Sets the useQuoteDelim option.
     *
     * @param useQuoteDelim If false, treats double quotation marks as regular
     * characters inside of the string fields (ignoring RFC 4180, Section 2,
     * Bullet 5).
     * @return this Options instance.
     */
    public Options useQuoteDelim(Boolean useQuoteDelim) {
      this.useQuoteDelim = useQuoteDelim;
      return this;
    }

    /**
     * Sets the naValue option.
     *
     * @param naValue Additional string to recognize as NA/NaN.
     * @return this Options instance.
     */
    public Options naValue(String naValue) {
      this.naValue = naValue;
      return this;
    }

    /**
     * Sets the selectCols option.
     *
     * @param selectCols the selectCols option
     * @return this Options instance.
     */
    public Options selectCols(List<Long> selectCols) {
      this.selectCols = selectCols;
      return this;
    }

    /**
     * Sets the selectCols option.
     *
     * @param selectCols the selectCols option
     * @return this Options instance.
     */
    public Options selectCols(Long... selectCols) {
      this.selectCols = Arrays.asList(selectCols);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DecodeCsv.class
  )
  public static class Inputs extends RawOpInputs<DecodeCsv> {
    /**
     * Each string is a record/row in the csv and all records should have
     * the same format.
     */
    public final Operand<TString> records;

    /**
     * One tensor per column of the input record, with either a
     * scalar default value for that column or an empty vector if the column is
     * required.
     */
    public final Iterable<Operand<?>> recordDefaults;

    /**
     * The OUTTYPE attribute
     */
    public final DataType[] OUTTYPE;

    /**
     * char delimiter to separate fields in a record.
     */
    public final String fieldDelim;

    /**
     * If false, treats double quotation marks as regular
     * characters inside of the string fields (ignoring RFC 4180, Section 2,
     * Bullet 5).
     */
    public final boolean useQuoteDelim;

    /**
     * Additional string to recognize as NA/NaN.
     */
    public final String naValue;

    /**
     * The selectCols attribute
     */
    public final long[] selectCols;

    public Inputs(GraphOperation op) {
      super(new DecodeCsv(op), op, Arrays.asList("OUT_TYPE", "field_delim", "use_quote_delim", "na_value", "select_cols"));
      int inputIndex = 0;
      records = (Operand<TString>) op.input(inputIndex++);
      int recordDefaultsLength = op.inputListLength("record_defaults");
      recordDefaults = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, recordDefaultsLength));
      inputIndex += recordDefaultsLength;
      OUTTYPE = op.attributes().getAttrTypeList("OUT_TYPE");
      fieldDelim = op.attributes().getAttrString("field_delim");
      useQuoteDelim = op.attributes().getAttrBool("use_quote_delim");
      naValue = op.attributes().getAttrString("na_value");
      selectCols = op.attributes().getAttrIntList("select_cols");
    }
  }
}
