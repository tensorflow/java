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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Applies softmax to a batched N-D `SparseTensor`.
 * <p>
 * The inputs represent an N-D SparseTensor  with logical shape `[..., B, C]`
 * (where `N >= 2`), and with indices sorted in the canonical lexicographic order.
 * <p>
 * This op is equivalent to applying the normal `tf.nn.softmax()` to each innermost
 * logical submatrix with shape `[B, C]`, but with the catch that <i>the implicitly
 * zero elements do not participate</i>.  Specifically, the algorithm is equivalent
 * to the following:
 * <p>
 *   (1) Applies `tf.nn.softmax()` to a densified view of each innermost submatrix
 *       with shape `[B, C]`, along the size-C dimension;
 *   (2) Masks out the original implicitly-zero locations;
 *   (3) Renormalizes the remaining elements.
 * <p>
 * Hence, the `SparseTensor` result has exactly the same non-zero indices and
 * shape.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "sparse")
public final class SparseSoftmax<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SparseSoftmax operation.
   * 
   * @param scope current scope
   * @param spIndices 2-D.  `NNZ x R` matrix with the indices of non-empty values in a
   * SparseTensor, in canonical ordering.
   * @param spValues 1-D.  `NNZ` non-empty values corresponding to `sp_indices`.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @return a new instance of SparseSoftmax
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> SparseSoftmax<T> create(Scope scope, Operand<TInt64> spIndices, Operand<T> spValues, Operand<TInt64> spShape) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseSoftmax", scope.makeOpName("SparseSoftmax"));
    opBuilder.addInput(spIndices.asOutput());
    opBuilder.addInput(spValues.asOutput());
    opBuilder.addInput(spShape.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseSoftmax<T>(opBuilder.build());
  }
  
  /**
   * 1-D.  The `NNZ` values for the result `SparseTensor`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseSoftmax";
  
  private Output<T> output;
  
  private SparseSoftmax(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
