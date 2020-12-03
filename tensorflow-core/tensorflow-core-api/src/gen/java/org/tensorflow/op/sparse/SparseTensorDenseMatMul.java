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
 * Multiply SparseTensor (of rank 2) "A" by dense matrix "B".
 * <p>
 * No validity checking is performed on the indices of A.  However, the following
 * input format is recommended for optimal behavior:
 * <p>
 * if adjoint_a == false:
 *   A should be sorted in lexicographically increasing order.  Use SparseReorder
 *   if you're not sure.
 * if adjoint_a == true:
 *   A should be sorted in order of increasing dimension 1 (i.e., "column major"
 *   order instead of "row major" order).
 * 
 * @param <U> data type for {@code product()} output
 */
@Operator(group = "sparse")
public final class SparseTensorDenseMatMul<U extends TType> extends RawOp implements Operand<U> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.SparseTensorDenseMatMul}
   */
  public static class Options {
    
    /**
     * @param adjointA Use the adjoint of A in the matrix multiply.  If A is complex, this
     * is transpose(conj(A)).  Otherwise it's transpose(A).
     */
    public Options adjointA(Boolean adjointA) {
      this.adjointA = adjointA;
      return this;
    }
    
    /**
     * @param adjointB Use the adjoint of B in the matrix multiply.  If B is complex, this
     * is transpose(conj(B)).  Otherwise it's transpose(B).
     */
    public Options adjointB(Boolean adjointB) {
      this.adjointB = adjointB;
      return this;
    }
    
    private Boolean adjointA;
    private Boolean adjointB;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SparseTensorDenseMatMul operation.
   * 
   * @param scope current scope
   * @param aIndices 2-D.  The `indices` of the `SparseTensor`, size `[nnz, 2]` Matrix.
   * @param aValues 1-D.  The `values` of the `SparseTensor`, size `[nnz]` Vector.
   * @param aShape 1-D.  The `shape` of the `SparseTensor`, size `[2]` Vector.
   * @param b 2-D.  A dense Matrix.
   * @param options carries optional attributes values
   * @return a new instance of SparseTensorDenseMatMul
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TNumber> SparseTensorDenseMatMul<U> create(Scope scope, Operand<T> aIndices, Operand<U> aValues, Operand<TInt64> aShape, Operand<U> b, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseTensorDenseMatMul", scope.makeOpName("SparseTensorDenseMatMul"));
    opBuilder.addInput(aIndices.asOutput());
    opBuilder.addInput(aValues.asOutput());
    opBuilder.addInput(aShape.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.adjointA != null) {
          opBuilder.setAttr("adjoint_a", opts.adjointA);
        }
        if (opts.adjointB != null) {
          opBuilder.setAttr("adjoint_b", opts.adjointB);
        }
      }
    }
    return new SparseTensorDenseMatMul<U>(opBuilder.build());
  }
  
  /**
   * @param adjointA Use the adjoint of A in the matrix multiply.  If A is complex, this
   * is transpose(conj(A)).  Otherwise it's transpose(A).
   */
  public static Options adjointA(Boolean adjointA) {
    return new Options().adjointA(adjointA);
  }
  
  /**
   * @param adjointB Use the adjoint of B in the matrix multiply.  If B is complex, this
   * is transpose(conj(B)).  Otherwise it's transpose(B).
   */
  public static Options adjointB(Boolean adjointB) {
    return new Options().adjointB(adjointB);
  }
  
  /**
   */
  public Output<U> product() {
    return product;
  }
  
  @Override
  public Output<U> asOutput() {
    return product;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseTensorDenseMatMul";
  
  private Output<U> product;
  
  private SparseTensorDenseMatMul(Operation operation) {
    super(operation);
    int outputIdx = 0;
    product = operation.output(outputIdx++);
  }
}
