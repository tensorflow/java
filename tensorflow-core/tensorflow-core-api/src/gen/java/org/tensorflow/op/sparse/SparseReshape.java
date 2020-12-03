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

/**
 * Reshapes a SparseTensor to represent values in a new dense shape.
 * <p>
 * This operation has the same semantics as reshape on the represented dense
 * tensor.  The `input_indices` are recomputed based on the requested `new_shape`.
 * <p>
 * If one component of `new_shape` is the special value -1, the size of that
 * dimension is computed so that the total dense size remains constant.  At
 * most one component of `new_shape` can be -1.  The number of dense elements
 * implied by `new_shape` must be the same as the number of dense elements
 * originally implied by `input_shape`.
 * <p>
 * Reshaping does not affect the order of values in the SparseTensor.
 * <p>
 * If the input tensor has rank `R_in` and `N` non-empty values, and `new_shape`
 * has length `R_out`, then `input_indices` has shape `[N, R_in]`,
 * `input_shape` has length `R_in`, `output_indices` has shape `[N, R_out]`, and
 * `output_shape` has length `R_out`.
 */
@Operator(group = "sparse")
public final class SparseReshape extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new SparseReshape operation.
   * 
   * @param scope current scope
   * @param inputIndices 2-D.  `N x R_in` matrix with the indices of non-empty values in a
   * SparseTensor.
   * @param inputShape 1-D.  `R_in` vector with the input SparseTensor's dense shape.
   * @param newShape 1-D.  `R_out` vector with the requested new dense shape.
   * @return a new instance of SparseReshape
   */
  @Endpoint(describeByClass = true)
  public static SparseReshape create(Scope scope, Operand<TInt64> inputIndices, Operand<TInt64> inputShape, Operand<TInt64> newShape) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseReshape", scope.makeOpName("SparseReshape"));
    opBuilder.addInput(inputIndices.asOutput());
    opBuilder.addInput(inputShape.asOutput());
    opBuilder.addInput(newShape.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseReshape(opBuilder.build());
  }
  
  /**
   * 2-D.  `N x R_out` matrix with the updated indices of non-empty
   * values in the output SparseTensor.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }
  
  /**
   * 1-D.  `R_out` vector with the full dense shape of the output
   * SparseTensor.  This is the same as `new_shape` but with any -1 dimensions
   * filled in.
   */
  public Output<TInt64> outputShape() {
    return outputShape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseReshape";
  
  private Output<TInt64> outputIndices;
  private Output<TInt64> outputShape;
  
  private SparseReshape(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputShape = operation.output(outputIdx++);
  }
}
