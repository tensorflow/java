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
 * Adds two `SparseTensor` objects to produce another `SparseTensor`.
 * <p>
 * The input `SparseTensor` objects' indices are assumed ordered in standard
 * lexicographic order.  If this is not the case, before this step run
 * `SparseReorder` to restore index ordering.
 * <p>
 * By default, if two values sum to zero at some index, the output `SparseTensor`
 * would still include that particular location in its index, storing a zero in the
 * corresponding value slot.  To override this, callers can specify `thresh`,
 * indicating that if the sum has a magnitude strictly smaller than `thresh`, its
 * corresponding value and index would then not be included.  In particular,
 * `thresh == 0` (default) means everything is kept and actual thresholding happens
 * only for a positive value.
 * <p>
 * In the following shapes, `nnz` is the count after taking `thresh` into account.
 * 
 * @param <T> data type for {@code sumValues()} output
 */
@Operator(group = "sparse")
public final class SparseAdd<T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new SparseAdd operation.
   * 
   * @param scope current scope
   * @param aIndices 2-D.  The `indices` of the first `SparseTensor`, size `[nnz, ndims]` Matrix.
   * @param aValues 1-D.  The `values` of the first `SparseTensor`, size `[nnz]` Vector.
   * @param aShape 1-D.  The `shape` of the first `SparseTensor`, size `[ndims]` Vector.
   * @param bIndices 2-D.  The `indices` of the second `SparseTensor`, size `[nnz, ndims]` Matrix.
   * @param bValues 1-D.  The `values` of the second `SparseTensor`, size `[nnz]` Vector.
   * @param bShape 1-D.  The `shape` of the second `SparseTensor`, size `[ndims]` Vector.
   * @param thresh 0-D.  The magnitude threshold that determines if an output value/index
   * pair takes space.
   * @return a new instance of SparseAdd
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> SparseAdd<T> create(Scope scope, Operand<TInt64> aIndices, Operand<T> aValues, Operand<TInt64> aShape, Operand<TInt64> bIndices, Operand<T> bValues, Operand<TInt64> bShape, Operand<U> thresh) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseAdd", scope.makeOpName("SparseAdd"));
    opBuilder.addInput(aIndices.asOutput());
    opBuilder.addInput(aValues.asOutput());
    opBuilder.addInput(aShape.asOutput());
    opBuilder.addInput(bIndices.asOutput());
    opBuilder.addInput(bValues.asOutput());
    opBuilder.addInput(bShape.asOutput());
    opBuilder.addInput(thresh.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseAdd<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<TInt64> sumIndices() {
    return sumIndices;
  }
  
  /**
   */
  public Output<T> sumValues() {
    return sumValues;
  }
  
  /**
   */
  public Output<TInt64> sumShape() {
    return sumShape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseAdd";
  
  private Output<TInt64> sumIndices;
  private Output<T> sumValues;
  private Output<TInt64> sumShape;
  
  private SparseAdd(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sumIndices = operation.output(outputIdx++);
    sumValues = operation.output(outputIdx++);
    sumShape = operation.output(outputIdx++);
  }
}
