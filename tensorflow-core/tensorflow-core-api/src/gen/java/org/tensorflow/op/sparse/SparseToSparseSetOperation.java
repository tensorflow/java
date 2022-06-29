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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Applies set operation along last dimension of 2 {@code SparseTensor} inputs.
 * See SetOperationOp::SetOperationFromContext for values of {@code set_operation}.
 * <p>If {@code validate_indices} is {@code True}, {@code sparse.SparseToSparseSetOperation} validates the
 * order and range of {@code set1} and {@code set2} indices.
 * <p>Input {@code set1} is a {@code SparseTensor} represented by {@code set1_indices}, {@code set1_values},
 * and {@code set1_shape}. For {@code set1} ranked {@code n}, 1st {@code n-1} dimensions must be the same
 * as {@code set2}. Dimension {@code n} contains values in a set, duplicates are allowed but
 * ignored.
 * <p>Input {@code set2} is a {@code SparseTensor} represented by {@code set2_indices}, {@code set2_values},
 * and {@code set2_shape}. For {@code set2} ranked {@code n}, 1st {@code n-1} dimensions must be the same
 * as {@code set1}. Dimension {@code n} contains values in a set, duplicates are allowed but
 * ignored.
 * <p>If {@code validate_indices} is {@code True}, this op validates the order and range of {@code set1}
 * and {@code set2} indices.
 * <p>Output {@code result} is a {@code SparseTensor} represented by {@code result_indices},
 * {@code result_values}, and {@code result_shape}. For {@code set1} and {@code set2} ranked {@code n}, this
 * has rank {@code n} and the same 1st {@code n-1} dimensions as {@code set1} and {@code set2}. The {@code nth}
 * dimension contains the result of {@code set_operation} applied to the corresponding
 * {@code [0...n-1]} dimension of {@code set}.
 *
 * @param <T> data type for {@code result_values} output
 */
@OpMetadata(
    opType = SparseToSparseSetOperation.OP_NAME,
    inputsClass = SparseToSparseSetOperation.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseToSparseSetOperation<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseToSparseSetOperation";

  private Output<TInt64> resultIndices;

  private Output<T> resultValues;

  private Output<TInt64> resultShape;

  public SparseToSparseSetOperation(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    resultIndices = operation.output(outputIdx++);
    resultValues = operation.output(outputIdx++);
    resultShape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseToSparseSetOperation operation.
   *
   * @param scope current scope
   * @param set1Indices 2D {@code Tensor}, indices of a {@code SparseTensor}. Must be in row-major
   * order.
   * @param set1Values 1D {@code Tensor}, values of a {@code SparseTensor}. Must be in row-major
   * order.
   * @param set1Shape 1D {@code Tensor}, shape of a {@code SparseTensor}. {@code set1_shape[0...n-1]} must
   * be the same as {@code set2_shape[0...n-1]}, {@code set1_shape[n]} is the
   * max set size across {@code 0...n-1} dimensions.
   * @param set2Indices 2D {@code Tensor}, indices of a {@code SparseTensor}. Must be in row-major
   * order.
   * @param set2Values 1D {@code Tensor}, values of a {@code SparseTensor}. Must be in row-major
   * order.
   * @param set2Shape 1D {@code Tensor}, shape of a {@code SparseTensor}. {@code set2_shape[0...n-1]} must
   * be the same as {@code set1_shape[0...n-1]}, {@code set2_shape[n]} is the
   * max set size across {@code 0...n-1} dimensions.
   * @param setOperation The value of the setOperation attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseToSparseSetOperation} output and operands
   * @return a new instance of SparseToSparseSetOperation
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseToSparseSetOperation<T> create(Scope scope,
      Operand<TInt64> set1Indices, Operand<T> set1Values, Operand<TInt64> set1Shape,
      Operand<TInt64> set2Indices, Operand<T> set2Values, Operand<TInt64> set2Shape,
      String setOperation, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseToSparseSetOperation");
    opBuilder.addInput(set1Indices.asOutput());
    opBuilder.addInput(set1Values.asOutput());
    opBuilder.addInput(set1Shape.asOutput());
    opBuilder.addInput(set2Indices.asOutput());
    opBuilder.addInput(set2Values.asOutput());
    opBuilder.addInput(set2Shape.asOutput());
    opBuilder.setAttr("set_operation", setOperation);
    if (options != null) {
      for (Options opts : options) {
        if (opts.validateIndices != null) {
          opBuilder.setAttr("validate_indices", opts.validateIndices);
        }
      }
    }
    return new SparseToSparseSetOperation<>(opBuilder.build());
  }

  /**
   * Sets the validateIndices option.
   *
   * @param validateIndices the validateIndices option
   * @return this Options instance.
   */
  public static Options validateIndices(Boolean validateIndices) {
    return new Options().validateIndices(validateIndices);
  }

  /**
   * Gets resultIndices.
   * 2D indices of a {@code SparseTensor}.
   * @return resultIndices.
   */
  public Output<TInt64> resultIndices() {
    return resultIndices;
  }

  /**
   * Gets resultValues.
   * 1D values of a {@code SparseTensor}.
   * @return resultValues.
   */
  public Output<T> resultValues() {
    return resultValues;
  }

  /**
   * Gets resultShape.
   * 1D {@code Tensor} shape of a {@code SparseTensor}. {@code result_shape[0...n-1]} is
   * the same as the 1st {@code n-1} dimensions of {@code set1} and {@code set2}, {@code result_shape[n]}
   * is the max result set size across all {@code 0...n-1} dimensions.
   * @return resultShape.
   */
  public Output<TInt64> resultShape() {
    return resultShape;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.SparseToSparseSetOperation}
   */
  public static class Options {
    private Boolean validateIndices;

    private Options() {
    }

    /**
     * Sets the validateIndices option.
     *
     * @param validateIndices the validateIndices option
     * @return this Options instance.
     */
    public Options validateIndices(Boolean validateIndices) {
      this.validateIndices = validateIndices;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SparseToSparseSetOperation.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SparseToSparseSetOperation<T>> {
    /**
     * 2D {@code Tensor}, indices of a {@code SparseTensor}. Must be in row-major
     * order.
     */
    public final Operand<TInt64> set1Indices;

    /**
     * 1D {@code Tensor}, values of a {@code SparseTensor}. Must be in row-major
     * order.
     */
    public final Operand<T> set1Values;

    /**
     * 1D {@code Tensor}, shape of a {@code SparseTensor}. {@code set1_shape[0...n-1]} must
     * be the same as {@code set2_shape[0...n-1]}, {@code set1_shape[n]} is the
     * max set size across {@code 0...n-1} dimensions.
     */
    public final Operand<TInt64> set1Shape;

    /**
     * 2D {@code Tensor}, indices of a {@code SparseTensor}. Must be in row-major
     * order.
     */
    public final Operand<TInt64> set2Indices;

    /**
     * 1D {@code Tensor}, values of a {@code SparseTensor}. Must be in row-major
     * order.
     */
    public final Operand<T> set2Values;

    /**
     * 1D {@code Tensor}, shape of a {@code SparseTensor}. {@code set2_shape[0...n-1]} must
     * be the same as {@code set1_shape[0...n-1]}, {@code set2_shape[n]} is the
     * max set size across {@code 0...n-1} dimensions.
     */
    public final Operand<TInt64> set2Shape;

    /**
     * The setOperation attribute
     */
    public final String setOperation;

    /**
     * The validateIndices attribute
     */
    public final boolean validateIndices;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseToSparseSetOperation<>(op), op, Arrays.asList("set_operation", "validate_indices", "T"));
      int inputIndex = 0;
      set1Indices = (Operand<TInt64>) op.input(inputIndex++);
      set1Values = (Operand<T>) op.input(inputIndex++);
      set1Shape = (Operand<TInt64>) op.input(inputIndex++);
      set2Indices = (Operand<TInt64>) op.input(inputIndex++);
      set2Values = (Operand<T>) op.input(inputIndex++);
      set2Shape = (Operand<TInt64>) op.input(inputIndex++);
      setOperation = op.attributes().getAttrString("set_operation");
      validateIndices = op.attributes().getAttrBool("validate_indices");
      T = op.attributes().getAttrType("T");
    }
  }
}
