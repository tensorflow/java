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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Applies set operation along last dimension of 2 `SparseTensor` inputs.
 * <p>
 * See SetOperationOp::SetOperationFromContext for values of `set_operation`.
 * <p>
 * If `validate_indices` is `True`, `sparse.SparseToSparseSetOperation` validates the
 * order and range of `set1` and `set2` indices.
 * <p>
 * Input `set1` is a `SparseTensor` represented by `set1_indices`, `set1_values`,
 * and `set1_shape`. For `set1` ranked `n`, 1st `n-1` dimensions must be the same
 * as `set2`. Dimension `n` contains values in a set, duplicates are allowed but
 * ignored.
 * <p>
 * Input `set2` is a `SparseTensor` represented by `set2_indices`, `set2_values`,
 * and `set2_shape`. For `set2` ranked `n`, 1st `n-1` dimensions must be the same
 * as `set1`. Dimension `n` contains values in a set, duplicates are allowed but
 * ignored.
 * <p>
 * If `validate_indices` is `True`, this op validates the order and range of `set1`
 * and `set2` indices.
 * <p>
 * Output `result` is a `SparseTensor` represented by `result_indices`,
 * `result_values`, and `result_shape`. For `set1` and `set2` ranked `n`, this
 * has rank `n` and the same 1st `n-1` dimensions as `set1` and `set2`. The `nth`
 * dimension contains the result of `set_operation` applied to the corresponding
 * `[0...n-1]` dimension of `set`.
 * 
 * @param <T> data type for {@code resultValues()} output
 */
@Operator(group = "sparse")
public final class SparseToSparseSetOperation<T extends TType> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.SparseToSparseSetOperation}
   */
  public static class Options {
    
    /**
     * @param validateIndices 
     */
    public Options validateIndices(Boolean validateIndices) {
      this.validateIndices = validateIndices;
      return this;
    }
    
    private Boolean validateIndices;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SparseToSparseSetOperation operation.
   * 
   * @param scope current scope
   * @param set1Indices 2D `Tensor`, indices of a `SparseTensor`. Must be in row-major
   * order.
   * @param set1Values 1D `Tensor`, values of a `SparseTensor`. Must be in row-major
   * order.
   * @param set1Shape 1D `Tensor`, shape of a `SparseTensor`. `set1_shape[0...n-1]` must
   * be the same as `set2_shape[0...n-1]`, `set1_shape[n]` is the
   * max set size across `0...n-1` dimensions.
   * @param set2Indices 2D `Tensor`, indices of a `SparseTensor`. Must be in row-major
   * order.
   * @param set2Values 1D `Tensor`, values of a `SparseTensor`. Must be in row-major
   * order.
   * @param set2Shape 1D `Tensor`, shape of a `SparseTensor`. `set2_shape[0...n-1]` must
   * be the same as `set1_shape[0...n-1]`, `set2_shape[n]` is the
   * max set size across `0...n-1` dimensions.
   * @param setOperation 
   * @param options carries optional attributes values
   * @return a new instance of SparseToSparseSetOperation
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseToSparseSetOperation<T> create(Scope scope, Operand<TInt64> set1Indices, Operand<T> set1Values, Operand<TInt64> set1Shape, Operand<TInt64> set2Indices, Operand<T> set2Values, Operand<TInt64> set2Shape, String setOperation, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseToSparseSetOperation", scope.makeOpName("SparseToSparseSetOperation"));
    opBuilder.addInput(set1Indices.asOutput());
    opBuilder.addInput(set1Values.asOutput());
    opBuilder.addInput(set1Shape.asOutput());
    opBuilder.addInput(set2Indices.asOutput());
    opBuilder.addInput(set2Values.asOutput());
    opBuilder.addInput(set2Shape.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("set_operation", setOperation);
    if (options != null) {
      for (Options opts : options) {
        if (opts.validateIndices != null) {
          opBuilder.setAttr("validate_indices", opts.validateIndices);
        }
      }
    }
    return new SparseToSparseSetOperation<T>(opBuilder.build());
  }
  
  /**
   * @param validateIndices 
   */
  public static Options validateIndices(Boolean validateIndices) {
    return new Options().validateIndices(validateIndices);
  }
  
  /**
   * 2D indices of a `SparseTensor`.
   */
  public Output<TInt64> resultIndices() {
    return resultIndices;
  }
  
  /**
   * 1D values of a `SparseTensor`.
   */
  public Output<T> resultValues() {
    return resultValues;
  }
  
  /**
   * 1D `Tensor` shape of a `SparseTensor`. `result_shape[0...n-1]` is
   * the same as the 1st `n-1` dimensions of `set1` and `set2`, `result_shape[n]`
   * is the max result set size across all `0...n-1` dimensions.
   */
  public Output<TInt64> resultShape() {
    return resultShape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseToSparseSetOperation";
  
  private Output<TInt64> resultIndices;
  private Output<T> resultValues;
  private Output<TInt64> resultShape;
  
  private SparseToSparseSetOperation(Operation operation) {
    super(operation);
    int outputIdx = 0;
    resultIndices = operation.output(outputIdx++);
    resultValues = operation.output(outputIdx++);
    resultShape = operation.output(outputIdx++);
  }
}
