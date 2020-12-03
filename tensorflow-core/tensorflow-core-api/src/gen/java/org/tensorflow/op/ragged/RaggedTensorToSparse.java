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

package org.tensorflow.op.ragged;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Converts a `RaggedTensor` into a `SparseTensor` with the same values.
 * <p>
 * input=ragged.from_nested_row_splits(rt_dense_values, rt_nested_splits)
 * output=SparseTensor(indices=sparse_indices, values=sparse_values,
 *                     dense_shape=sparse_dense_shape)
 * 
 * @param <U> data type for {@code sparseValues()} output
 */
public final class RaggedTensorToSparse<U extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new RaggedTensorToSparse operation.
   * 
   * @param scope current scope
   * @param rtNestedSplits The `row_splits` for the `RaggedTensor`.
   * @param rtDenseValues The `flat_values` for the `RaggedTensor`.
   * @return a new instance of RaggedTensorToSparse
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TNumber> RaggedTensorToSparse<U> create(Scope scope, Iterable<Operand<T>> rtNestedSplits, Operand<U> rtDenseValues) {
    OperationBuilder opBuilder = scope.env().opBuilder("RaggedTensorToSparse", scope.makeOpName("RaggedTensorToSparse"));
    opBuilder.addInputList(Operands.asOutputs(rtNestedSplits));
    opBuilder.addInput(rtDenseValues.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new RaggedTensorToSparse<U>(opBuilder.build());
  }
  
  /**
   * The indices for the `SparseTensor`.
   */
  public Output<TInt64> sparseIndices() {
    return sparseIndices;
  }
  
  /**
   * The values of the `SparseTensor`.
   */
  public Output<U> sparseValues() {
    return sparseValues;
  }
  
  /**
   * `sparse_dense_shape` is a tight bounding box of the input `RaggedTensor`.
   */
  public Output<TInt64> sparseDenseShape() {
    return sparseDenseShape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RaggedTensorToSparse";
  
  private Output<TInt64> sparseIndices;
  private Output<U> sparseValues;
  private Output<TInt64> sparseDenseShape;
  
  private RaggedTensorToSparse(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sparseIndices = operation.output(outputIdx++);
    sparseValues = operation.output(outputIdx++);
    sparseDenseShape = operation.output(outputIdx++);
  }
}
