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
 * Reorders a SparseTensor into the canonical, row-major ordering.
 * <p>
 * Note that by convention, all sparse ops preserve the canonical ordering along
 * increasing dimension number. The only time ordering can be violated is during
 * manual manipulation of the indices and values vectors to add entries.
 * <p>
 * Reordering does not affect the shape of the SparseTensor.
 * <p>
 * If the tensor has rank `R` and `N` non-empty values, `input_indices` has
 * shape `[N, R]`, input_values has length `N`, and input_shape has length `R`.
 * 
 * @param <T> data type for {@code outputValues()} output
 */
@Operator(group = "sparse")
public final class SparseReorder<T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new SparseReorder operation.
   * 
   * @param scope current scope
   * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * SparseTensor, possibly not in canonical ordering.
   * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @return a new instance of SparseReorder
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseReorder<T> create(Scope scope, Operand<TInt64> inputIndices, Operand<T> inputValues, Operand<TInt64> inputShape) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseReorder", scope.makeOpName("SparseReorder"));
    opBuilder.addInput(inputIndices.asOutput());
    opBuilder.addInput(inputValues.asOutput());
    opBuilder.addInput(inputShape.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseReorder<T>(opBuilder.build());
  }
  
  /**
   * 2-D.  `N x R` matrix with the same indices as input_indices, but
   * in canonical row-major ordering.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }
  
  /**
   * 1-D.  `N` non-empty values corresponding to `output_indices`.
   */
  public Output<T> outputValues() {
    return outputValues;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseReorder";
  
  private Output<TInt64> outputIndices;
  private Output<T> outputValues;
  
  private SparseReorder(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
  }
}
