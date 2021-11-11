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

package org.tensorflow.op.sparse;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Fills empty rows in the input 2-D {@code SparseTensor} with a default value.
 * The input {@code SparseTensor} is represented via the tuple of inputs
 * ({@code indices}, {@code values}, {@code dense_shape}).  The output {@code SparseTensor} has the
 * same {@code dense_shape} but with indices {@code output_indices} and values
 * {@code output_values}.
 * <p>This op inserts a single entry for every row that doesn't have any values.
 * The index is created as {@code [row, 0, ..., 0]} and the inserted value
 * is {@code default_value}.
 * <p>For example, suppose {@code sp_input} has shape {@code [5, 6]} and non-empty values:
 * <pre>
 * [0, 1]: a
 * [0, 3]: b
 * [2, 0]: c
 * [3, 1]: d
 * </pre>
 * <p>Rows 1 and 4 are empty, so the output will be of shape {@code [5, 6]} with values:
 * <pre>
 * [0, 1]: a
 * [0, 3]: b
 * [1, 0]: default_value
 * [2, 0]: c
 * [3, 1]: d
 * [4, 0]: default_value
 * </pre>
 * <p>The output {@code SparseTensor} will be in row-major order and will have the
 * same shape as the input.
 * <p>This op also returns an indicator vector shaped {@code [dense_shape[0]]} such that
 * <pre>
 * empty_row_indicator[i] = True iff row i was an empty row.
 * </pre>
 * <p>And a reverse index map vector shaped {@code [indices.shape[0]]} that is used during
 * backpropagation,
 * <pre>
 * reverse_index_map[j] = out_j s.t. indices[j, :] == output_indices[out_j, :]
 * </pre>
 *
 * @param <T> data type for {@code output_values} output
 */
@OpMetadata(
    opType = SparseFillEmptyRows.OP_NAME,
    inputsClass = SparseFillEmptyRows.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseFillEmptyRows<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseFillEmptyRows";

  private Output<TInt64> outputIndices;

  private Output<T> outputValues;

  private Output<TBool> emptyRowIndicator;

  private Output<TInt64> reverseIndexMap;

  public SparseFillEmptyRows(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
    emptyRowIndicator = operation.output(outputIdx++);
    reverseIndexMap = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseFillEmptyRows operation.
   *
   * @param scope current scope
   * @param indices 2-D. the indices of the sparse tensor.
   * @param values 1-D. the values of the sparse tensor.
   * @param denseShape 1-D. the shape of the sparse tensor.
   * @param defaultValue 0-D. default value to insert into location {@code [row, 0, ..., 0]}
   * for rows missing from the input sparse tensor.
   * output indices: 2-D. the indices of the filled sparse tensor.
   * @param <T> data type for {@code SparseFillEmptyRows} output and operands
   * @return a new instance of SparseFillEmptyRows
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseFillEmptyRows<T> create(Scope scope,
      Operand<TInt64> indices, Operand<T> values, Operand<TInt64> denseShape,
      Operand<T> defaultValue) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseFillEmptyRows");
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(denseShape.asOutput());
    opBuilder.addInput(defaultValue.asOutput());
    return new SparseFillEmptyRows<>(opBuilder.build());
  }

  /**
   * Gets outputIndices.
   *
   * @return outputIndices.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }

  /**
   * Gets outputValues.
   * 1-D. the values of the filled sparse tensor.
   * @return outputValues.
   */
  public Output<T> outputValues() {
    return outputValues;
  }

  /**
   * Gets emptyRowIndicator.
   * 1-D. whether the dense row was missing in the
   * input sparse tensor.
   * @return emptyRowIndicator.
   */
  public Output<TBool> emptyRowIndicator() {
    return emptyRowIndicator;
  }

  /**
   * Gets reverseIndexMap.
   * 1-D. a map from the input indices to the output indices.
   * @return reverseIndexMap.
   */
  public Output<TInt64> reverseIndexMap() {
    return reverseIndexMap;
  }

  @OpInputsMetadata(
      outputsClass = SparseFillEmptyRows.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SparseFillEmptyRows<T>> {
    /**
     * 2-D. the indices of the sparse tensor.
     */
    public final Operand<TInt64> indices;

    /**
     * 1-D. the values of the sparse tensor.
     */
    public final Operand<T> values;

    /**
     * 1-D. the shape of the sparse tensor.
     */
    public final Operand<TInt64> denseShape;

    /**
     * 0-D. default value to insert into location {@code [row, 0, ..., 0]}
     * for rows missing from the input sparse tensor.
     * output indices: 2-D. the indices of the filled sparse tensor.
     */
    public final Operand<T> defaultValue;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseFillEmptyRows<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      indices = (Operand<TInt64>) op.input(inputIndex++);
      values = (Operand<T>) op.input(inputIndex++);
      denseShape = (Operand<TInt64>) op.input(inputIndex++);
      defaultValue = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
