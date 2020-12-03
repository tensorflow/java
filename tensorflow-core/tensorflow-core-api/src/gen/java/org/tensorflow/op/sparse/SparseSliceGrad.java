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
 * The gradient operator for the SparseSlice op.
 * <p>
 * This op takes in the upstream gradient w.r.t. non-empty values of
 * the sliced `SparseTensor`, and outputs the gradients w.r.t.
 * the non-empty values of input `SparseTensor`.
 * 
 * @param <T> data type for {@code valGrad()} output
 */
@Operator(group = "sparse")
public final class SparseSliceGrad<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SparseSliceGrad operation.
   * 
   * @param scope current scope
   * @param backpropValGrad 1-D. The gradient with respect to
   * the non-empty values of the sliced `SparseTensor`.
   * @param inputIndices 2-D.  The `indices` of the input `SparseTensor`.
   * @param inputStart 1-D. tensor represents the start of the slice.
   * @param outputIndices 2-D.  The `indices` of the sliced `SparseTensor`.
   * @return a new instance of SparseSliceGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseSliceGrad<T> create(Scope scope, Operand<T> backpropValGrad, Operand<TInt64> inputIndices, Operand<TInt64> inputStart, Operand<TInt64> outputIndices) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseSliceGrad", scope.makeOpName("SparseSliceGrad"));
    opBuilder.addInput(backpropValGrad.asOutput());
    opBuilder.addInput(inputIndices.asOutput());
    opBuilder.addInput(inputStart.asOutput());
    opBuilder.addInput(outputIndices.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseSliceGrad<T>(opBuilder.build());
  }
  
  /**
   * 1-D. The gradient with respect to the non-empty values of input `SparseTensor`.
   */
  public Output<T> valGrad() {
    return valGrad;
  }
  
  @Override
  public Output<T> asOutput() {
    return valGrad;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseSliceGrad";
  
  private Output<T> valGrad;
  
  private SparseSliceGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    valGrad = operation.output(outputIdx++);
  }
}
