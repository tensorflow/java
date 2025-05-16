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

package org.tensorflow.op.ragged;

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * The RaggedFillEmptyRows operation
 */
@OpMetadata(
    opType = RaggedFillEmptyRows.OP_NAME,
    inputsClass = RaggedFillEmptyRows.Inputs.class
)
@Operator(
    group = "ragged"
)
public final class RaggedFillEmptyRows<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RaggedFillEmptyRows";

  private Output<TInt64> outputValueRowids;

  private Output<T> outputValues;

  private Output<TBool> emptyRowIndicator;

  private Output<TInt64> reverseIndexMap;

  public RaggedFillEmptyRows(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputValueRowids = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
    emptyRowIndicator = operation.output(outputIdx++);
    reverseIndexMap = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RaggedFillEmptyRows operation.
   *
   * @param scope current scope
   * @param valueRowids The valueRowids value
   * @param values The values value
   * @param nrows The nrows value
   * @param defaultValue The defaultValue value
   * @param <T> data type for {@code RaggedFillEmptyRows} output and operands
   * @return a new instance of RaggedFillEmptyRows
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> RaggedFillEmptyRows<T> create(Scope scope,
      Operand<TInt64> valueRowids, Operand<T> values, Operand<TInt64> nrows,
      Operand<T> defaultValue) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RaggedFillEmptyRows");
    opBuilder.addInput(valueRowids.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(nrows.asOutput());
    opBuilder.addInput(defaultValue.asOutput());
    return new RaggedFillEmptyRows<>(opBuilder.build());
  }

  /**
   * Gets outputValueRowids.
   *
   * @return outputValueRowids.
   */
  public Output<TInt64> outputValueRowids() {
    return outputValueRowids;
  }

  /**
   * Gets outputValues.
   *
   * @return outputValues.
   */
  public Output<T> outputValues() {
    return outputValues;
  }

  /**
   * Gets emptyRowIndicator.
   *
   * @return emptyRowIndicator.
   */
  public Output<TBool> emptyRowIndicator() {
    return emptyRowIndicator;
  }

  /**
   * Gets reverseIndexMap.
   *
   * @return reverseIndexMap.
   */
  public Output<TInt64> reverseIndexMap() {
    return reverseIndexMap;
  }

  @OpInputsMetadata(
      outputsClass = RaggedFillEmptyRows.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<RaggedFillEmptyRows<T>> {
    /**
     * The valueRowids input
     */
    public final Operand<TInt64> valueRowids;

    /**
     * The values input
     */
    public final Operand<T> values;

    /**
     * The nrows input
     */
    public final Operand<TInt64> nrows;

    /**
     * The defaultValue input
     */
    public final Operand<T> defaultValue;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new RaggedFillEmptyRows<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      valueRowids = (Operand<TInt64>) op.input(inputIndex++);
      values = (Operand<T>) op.input(inputIndex++);
      nrows = (Operand<TInt64>) op.input(inputIndex++);
      defaultValue = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
