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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Fills empty rows in the input 2-D `SparseTensor` with a default value.
 * <p>
 * The input `SparseTensor` is represented via the tuple of inputs
 * (`indices`, `values`, `dense_shape`).  The output `SparseTensor` has the
 * same `dense_shape` but with indices `output_indices` and values
 * `output_values`.
 * <p>
 * This op inserts a single entry for every row that doesn't have any values.
 * The index is created as `[row, 0, ..., 0]` and the inserted value
 * is `default_value`.
 * <p>
 * For example, suppose `sp_input` has shape `[5, 6]` and non-empty values:
 * <p>
 *     [0, 1]: a
 *     [0, 3]: b
 *     [2, 0]: c
 *     [3, 1]: d
 * <p>
 * Rows 1 and 4 are empty, so the output will be of shape `[5, 6]` with values:
 * <p>
 *     [0, 1]: a
 *     [0, 3]: b
 *     [1, 0]: default_value
 *     [2, 0]: c
 *     [3, 1]: d
 *     [4, 0]: default_value
 * <p>
 * The output `SparseTensor` will be in row-major order and will have the
 * same shape as the input.
 * <p>
 * This op also returns an indicator vector shaped `[dense_shape[0]]` such that
 * <p>
 *     empty_row_indicator[i] = True iff row i was an empty row.
 * <p>
 * And a reverse index map vector shaped `[indices.shape[0]]` that is used during
 * backpropagation,
 * <p>
 *     reverse_index_map[j] = out_j s.t. indices[j, :] == output_indices[out_j, :]
 * 
 * @param <T> data type for {@code outputValues()} output
 */
@Operator(group = "sparse")
public final class SparseFillEmptyRows<T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new SparseFillEmptyRows operation.
   * 
   * @param scope current scope
   * @param indices 2-D. the indices of the sparse tensor.
   * @param values 1-D. the values of the sparse tensor.
   * @param denseShape 1-D. the shape of the sparse tensor.
   * @param defaultValue 0-D. default value to insert into location `[row, 0, ..., 0]`
   *   for rows missing from the input sparse tensor.
   * output indices: 2-D. the indices of the filled sparse tensor.
   * @return a new instance of SparseFillEmptyRows
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseFillEmptyRows<T> create(Scope scope, Operand<TInt64> indices, Operand<T> values, Operand<TInt64> denseShape, Operand<T> defaultValue) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseFillEmptyRows", scope.makeOpName("SparseFillEmptyRows"));
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(denseShape.asOutput());
    opBuilder.addInput(defaultValue.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseFillEmptyRows<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }
  
  /**
   * 1-D. the values of the filled sparse tensor.
   */
  public Output<T> outputValues() {
    return outputValues;
  }
  
  /**
   * 1-D. whether the dense row was missing in the
   * input sparse tensor.
   */
  public Output<TBool> emptyRowIndicator() {
    return emptyRowIndicator;
  }
  
  /**
   * 1-D. a map from the input indices to the output indices.
   */
  public Output<TInt64> reverseIndexMap() {
    return reverseIndexMap;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseFillEmptyRows";
  
  private Output<TInt64> outputIndices;
  private Output<T> outputValues;
  private Output<TBool> emptyRowIndicator;
  private Output<TInt64> reverseIndexMap;
  
  private SparseFillEmptyRows(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
    emptyRowIndicator = operation.output(outputIdx++);
    reverseIndexMap = operation.output(outputIdx++);
  }
}
