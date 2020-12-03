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
 * Returns the element-wise min of two SparseTensors.
 * <p>
 * Assumes the two SparseTensors have the same shape, i.e., no broadcasting.
 * 
 * @param <T> data type for {@code outputValues()} output
 */
@Operator(group = "sparse")
public final class SparseSparseMinimum<T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new SparseSparseMinimum operation.
   * 
   * @param scope current scope
   * @param aIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * SparseTensor, in the canonical lexicographic ordering.
   * @param aValues 1-D.  `N` non-empty values corresponding to `a_indices`.
   * @param aShape 1-D.  Shape of the input SparseTensor.
   * @param bIndices counterpart to `a_indices` for the other operand.
   * @param bValues counterpart to `a_values` for the other operand; must be of the same dtype.
   * @param bShape counterpart to `a_shape` for the other operand; the two shapes must be equal.
   * @return a new instance of SparseSparseMinimum
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseSparseMinimum<T> create(Scope scope, Operand<TInt64> aIndices, Operand<T> aValues, Operand<TInt64> aShape, Operand<TInt64> bIndices, Operand<T> bValues, Operand<TInt64> bShape) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseSparseMinimum", scope.makeOpName("SparseSparseMinimum"));
    opBuilder.addInput(aIndices.asOutput());
    opBuilder.addInput(aValues.asOutput());
    opBuilder.addInput(aShape.asOutput());
    opBuilder.addInput(bIndices.asOutput());
    opBuilder.addInput(bValues.asOutput());
    opBuilder.addInput(bShape.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseSparseMinimum<T>(opBuilder.build());
  }
  
  /**
   * 2-D.  The indices of the output SparseTensor.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }
  
  /**
   * 1-D.  The values of the output SparseTensor.
   */
  public Output<T> outputValues() {
    return outputValues;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseSparseMinimum";
  
  private Output<TInt64> outputIndices;
  private Output<T> outputValues;
  
  private SparseSparseMinimum(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
  }
}
