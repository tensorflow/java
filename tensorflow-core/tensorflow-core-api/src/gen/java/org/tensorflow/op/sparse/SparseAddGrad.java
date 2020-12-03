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
 * The gradient operator for the SparseAdd op.
 * <p>
 * The SparseAdd op calculates A + B, where A, B, and the sum are all represented
 * as `SparseTensor` objects.  This op takes in the upstream gradient w.r.t.
 * non-empty values of the sum, and outputs the gradients w.r.t. the non-empty
 * values of A and B.
 * 
 * @param <T> data type for {@code aValGrad()} output
 */
@Operator(group = "sparse")
public final class SparseAddGrad<T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new SparseAddGrad operation.
   * 
   * @param scope current scope
   * @param backpropValGrad 1-D with shape `[nnz(sum)]`.  The gradient with respect to
   * the non-empty values of the sum.
   * @param aIndices 2-D.  The `indices` of the `SparseTensor` A, size `[nnz(A), ndims]`.
   * @param bIndices 2-D.  The `indices` of the `SparseTensor` B, size `[nnz(B), ndims]`.
   * @param sumIndices 2-D.  The `indices` of the sum `SparseTensor`, size
   * `[nnz(sum), ndims]`.
   * @return a new instance of SparseAddGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseAddGrad<T> create(Scope scope, Operand<T> backpropValGrad, Operand<TInt64> aIndices, Operand<TInt64> bIndices, Operand<TInt64> sumIndices) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseAddGrad", scope.makeOpName("SparseAddGrad"));
    opBuilder.addInput(backpropValGrad.asOutput());
    opBuilder.addInput(aIndices.asOutput());
    opBuilder.addInput(bIndices.asOutput());
    opBuilder.addInput(sumIndices.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseAddGrad<T>(opBuilder.build());
  }
  
  /**
   * 1-D with shape `[nnz(A)]`. The gradient with respect to the
   * non-empty values of A.
   */
  public Output<T> aValGrad() {
    return aValGrad;
  }
  
  /**
   * 1-D with shape `[nnz(B)]`. The gradient with respect to the
   * non-empty values of B.
   */
  public Output<T> bValGrad() {
    return bValGrad;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseAddGrad";
  
  private Output<T> aValGrad;
  private Output<T> bValGrad;
  
  private SparseAddGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    aValGrad = operation.output(outputIdx++);
    bValGrad = operation.output(outputIdx++);
  }
}
