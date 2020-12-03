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
 * Adds up a SparseTensor and a dense Tensor, using these special rules:
 * <p>
 * (1) Broadcasts the dense side to have the same shape as the sparse side, if
 *     eligible;
 * (2) Then, only the dense values pointed to by the indices of the SparseTensor
 *     participate in the cwise addition.
 * <p>
 * By these rules, the result is a logical SparseTensor with exactly the same
 * indices and shape, but possibly with different non-zero values.  The output of
 * this Op is the resultant non-zero values.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "sparse")
public final class SparseDenseCwiseAdd<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SparseDenseCwiseAdd operation.
   * 
   * @param scope current scope
   * @param spIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * SparseTensor, possibly not in canonical ordering.
   * @param spValues 1-D.  `N` non-empty values corresponding to `sp_indices`.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param dense `R`-D.  The dense Tensor operand.
   * @return a new instance of SparseDenseCwiseAdd
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseDenseCwiseAdd<T> create(Scope scope, Operand<TInt64> spIndices, Operand<T> spValues, Operand<TInt64> spShape, Operand<T> dense) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseDenseCwiseAdd", scope.makeOpName("SparseDenseCwiseAdd"));
    opBuilder.addInput(spIndices.asOutput());
    opBuilder.addInput(spValues.asOutput());
    opBuilder.addInput(spShape.asOutput());
    opBuilder.addInput(dense.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseDenseCwiseAdd<T>(opBuilder.build());
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
  public static final String OP_NAME = "SparseDenseCwiseAdd";
  
  private Output<T> output;
  
  private SparseDenseCwiseAdd(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
