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

package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Initializes a table from a text file.
 * It inserts one key-value pair into the table for each line of the file.
 * The key and value is extracted from the whole line content, elements from the
 * split line based on {@code delimiter} or the line number (starting from zero).
 * Where to extract the key and value from a line is specified by {@code key_index} and
 * {@code value_index}.
 * <ul>
 * <li>A value of -1 means use the line number(starting from zero), expects {@code int64}.</li>
 * <li>A value of -2 means use the whole line content, expects {@code string}.</li>
 * <li>A value &gt;= 0 means use the index (starting at zero) of the split line based
 * on {@code delimiter}.</li>
 * </ul>
 */
@Operator
public final class InitializeTableFromTextFile extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "InitializeTableFromTextFileV2";

  private InitializeTableFromTextFile(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new InitializeTableFromTextFileV2 operation.
   *
   * @param scope current scope
   * @param tableHandle Handle to a table which will be initialized.
   * @param filename Filename of a vocabulary text file.
   * @param keyIndex Column index in a line to get the table {@code key} values from.
   * @param valueIndex Column index that represents information of a line to get the table
   * {@code value} values from.
   * @param options carries optional attribute values
   * @return a new instance of InitializeTableFromTextFile
   */
  @Endpoint(
      describeByClass = true
  )
  public static InitializeTableFromTextFile create(Scope scope,
      Operand<? extends TType> tableHandle, Operand<TString> filename, Long keyIndex,
      Long valueIndex, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("InitializeTableFromTextFileV2", scope.makeOpName("InitializeTableFromTextFile"));
    opBuilder.addInput(tableHandle.asOutput());
    opBuilder.addInput(filename.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("key_index", keyIndex);
    opBuilder.setAttr("value_index", valueIndex);
    if (options != null) {
      for (Options opts : options) {
        if (opts.vocabSize != null) {
          opBuilder.setAttr("vocab_size", opts.vocabSize);
        }
        if (opts.delimiter != null) {
          opBuilder.setAttr("delimiter", opts.delimiter);
        }
      }
    }
    return new InitializeTableFromTextFile(opBuilder.build());
  }

  /**
   * Sets the vocabSize option.
   *
   * @param vocabSize Number of elements of the file, use -1 if unknown.
   * @return this Options instance.
   */
  public static Options vocabSize(Long vocabSize) {
    return new Options().vocabSize(vocabSize);
  }

  /**
   * Sets the delimiter option.
   *
   * @param delimiter Delimiter to separate fields in a line.
   * @return this Options instance.
   */
  public static Options delimiter(String delimiter) {
    return new Options().delimiter(delimiter);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.InitializeTableFromTextFile}
   */
  public static class Options {
    private Long vocabSize;

    private String delimiter;

    private Options() {
    }

    /**
     * Sets the vocabSize option.
     *
     * @param vocabSize Number of elements of the file, use -1 if unknown.
     * @return this Options instance.
     */
    public Options vocabSize(Long vocabSize) {
      this.vocabSize = vocabSize;
      return this;
    }

    /**
     * Sets the delimiter option.
     *
     * @param delimiter Delimiter to separate fields in a line.
     * @return this Options instance.
     */
    public Options delimiter(String delimiter) {
      this.delimiter = delimiter;
      return this;
    }
  }
}
