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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Multiply matrix "a" by matrix "b".
 * <p>
 * The inputs must be two-dimensional matrices and the inner dimension of "a" must
 * match the outer dimension of "b". Both "a" and "b" must be `Tensor`s not
 * `SparseTensor`s.  This op is optimized for the case where at least one of "a" or
 * "b" is sparse, in the sense that they have a large proportion of zero values.
 * The breakeven for using this versus a dense matrix multiply on one platform was
 * 30% zero values in the sparse matrix.
 * <p>
 * The gradient computation of this operation will only take advantage of sparsity
 * in the input gradient when that gradient comes from a Relu.
 */
@Operator(group = "sparse")
public final class SparseMatMul extends RawOp implements Operand<TFloat32> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.SparseMatMul}
   */
  public static class Options {
    
    /**
     * @param transposeA 
     */
    public Options transposeA(Boolean transposeA) {
      this.transposeA = transposeA;
      return this;
    }
    
    /**
     * @param transposeB 
     */
    public Options transposeB(Boolean transposeB) {
      this.transposeB = transposeB;
      return this;
    }
    
    /**
     * @param aIsSparse 
     */
    public Options aIsSparse(Boolean aIsSparse) {
      this.aIsSparse = aIsSparse;
      return this;
    }
    
    /**
     * @param bIsSparse 
     */
    public Options bIsSparse(Boolean bIsSparse) {
      this.bIsSparse = bIsSparse;
      return this;
    }
    
    private Boolean transposeA;
    private Boolean transposeB;
    private Boolean aIsSparse;
    private Boolean bIsSparse;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SparseMatMul operation.
   * 
   * @param scope current scope
   * @param a 
   * @param b 
   * @param options carries optional attributes values
   * @return a new instance of SparseMatMul
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber> SparseMatMul create(Scope scope, Operand<T> a, Operand<U> b, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseMatMul", scope.makeOpName("SparseMatMul"));
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.transposeA != null) {
          opBuilder.setAttr("transpose_a", opts.transposeA);
        }
        if (opts.transposeB != null) {
          opBuilder.setAttr("transpose_b", opts.transposeB);
        }
        if (opts.aIsSparse != null) {
          opBuilder.setAttr("a_is_sparse", opts.aIsSparse);
        }
        if (opts.bIsSparse != null) {
          opBuilder.setAttr("b_is_sparse", opts.bIsSparse);
        }
      }
    }
    return new SparseMatMul(opBuilder.build());
  }
  
  /**
   * @param transposeA 
   */
  public static Options transposeA(Boolean transposeA) {
    return new Options().transposeA(transposeA);
  }
  
  /**
   * @param transposeB 
   */
  public static Options transposeB(Boolean transposeB) {
    return new Options().transposeB(transposeB);
  }
  
  /**
   * @param aIsSparse 
   */
  public static Options aIsSparse(Boolean aIsSparse) {
    return new Options().aIsSparse(aIsSparse);
  }
  
  /**
   * @param bIsSparse 
   */
  public static Options bIsSparse(Boolean bIsSparse) {
    return new Options().bIsSparse(bIsSparse);
  }
  
  /**
   */
  public Output<TFloat32> product() {
    return product;
  }
  
  @Override
  public Output<TFloat32> asOutput() {
    return product;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseMatMul";
  
  private Output<TFloat32> product;
  
  private SparseMatMul(Operation operation) {
    super(operation);
    int outputIdx = 0;
    product = operation.output(outputIdx++);
  }
}
