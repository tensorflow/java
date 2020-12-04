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
 * Component-wise multiplies a SparseTensor by a dense Tensor.
 * <p>
 * The output locations corresponding to the implicitly zero elements in the sparse
 * tensor will be zero (i.e., will not take up storage space), regardless of the
 * contents of the dense tensor (even if it's +/-INF and that INF<i>0 == NaN).
 * <p>
 * </i>Limitation*: this Op only broadcasts the dense side to the sparse side, but not
 * the other direction.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "sparse")
public final class SparseDenseCwiseMul<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SparseDenseCwiseMul operation.
   * 
   * @param scope current scope
   * @param spIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * SparseTensor, possibly not in canonical ordering.
   * @param spValues 1-D.  `N` non-empty values corresponding to `sp_indices`.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param dense `R`-D.  The dense Tensor operand.
   * @return a new instance of SparseDenseCwiseMul
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseDenseCwiseMul<T> create(Scope scope, Operand<TInt64> spIndices, Operand<T> spValues, Operand<TInt64> spShape, Operand<T> dense) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseDenseCwiseMul", scope.makeOpName("SparseDenseCwiseMul"));
    opBuilder.addInput(spIndices.asOutput());
    opBuilder.addInput(spValues.asOutput());
    opBuilder.addInput(spShape.asOutput());
    opBuilder.addInput(dense.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseDenseCwiseMul<T>(opBuilder.build());
  }
  
  /**
   * 1-D.  The `N` values that are operated on.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseDenseCwiseMul";
  
  private Output<T> output;
  
  private SparseDenseCwiseMul(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
