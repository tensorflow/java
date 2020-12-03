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

package org.tensorflow.op.linalg.sparse;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Matrix-multiplies a sparse matrix with a dense matrix.
 * <p>
 * Returns a dense matrix.
 * For inputs A and B, where A is CSR and B is dense; this op returns a dense C;
 * <p>
 * If transpose_output is false, returns:
 * <pre>{@code
 *   C = A . B
 * }</pre>
 * If transpose_output is `true`, returns:
 * <pre>{@code
 *   C = transpose(A . B) = transpose(B) . transpose(A)
 * }</pre>
 * where the transposition is performed along the two innermost (matrix)
 * dimensions.
 * <p>
 * If conjugate_output is `true`, returns:
 * <pre>{@code
 *   C = conjugate(A . B) = conjugate(A) . conjugate(B)
 * }</pre>
 * If both conjugate_output and transpose_output are `true`, returns:
 * <pre>{@code
 *   C = conjugate(transpose(A . B)) = conjugate(transpose(B)) .
 *                                     conjugate(transpose(A))
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
public final class SparseMatrixMatMul<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.sparse.SparseMatrixMatMul}
   */
  public static class Options {
    
    /**
     * @param transposeA Indicates whether `a` should be transposed.
     */
    public Options transposeA(Boolean transposeA) {
      this.transposeA = transposeA;
      return this;
    }
    
    /**
     * @param transposeB Indicates whether `b` should be transposed.
     */
    public Options transposeB(Boolean transposeB) {
      this.transposeB = transposeB;
      return this;
    }
    
    /**
     * @param adjointA Indicates whether `a` should be conjugate-transposed.
     */
    public Options adjointA(Boolean adjointA) {
      this.adjointA = adjointA;
      return this;
    }
    
    /**
     * @param adjointB Indicates whether `b` should be conjugate-transposed.
     */
    public Options adjointB(Boolean adjointB) {
      this.adjointB = adjointB;
      return this;
    }
    
    /**
     * @param transposeOutput Transposes the product of `a` and `b`.
     */
    public Options transposeOutput(Boolean transposeOutput) {
      this.transposeOutput = transposeOutput;
      return this;
    }
    
    /**
     * @param conjugateOutput Conjugates the product of `a` and `b`.
     */
    public Options conjugateOutput(Boolean conjugateOutput) {
      this.conjugateOutput = conjugateOutput;
      return this;
    }
    
    private Boolean transposeA;
    private Boolean transposeB;
    private Boolean adjointA;
    private Boolean adjointB;
    private Boolean transposeOutput;
    private Boolean conjugateOutput;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SparseMatrixMatMul operation.
   * 
   * @param scope current scope
   * @param a A CSRSparseMatrix.
   * @param b A dense tensor.
   * @param options carries optional attributes values
   * @return a new instance of SparseMatrixMatMul
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseMatrixMatMul<T> create(Scope scope, Operand<?> a, Operand<T> b, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseMatrixMatMul", scope.makeOpName("SparseMatrixMatMul"));
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
        if (opts.adjointA != null) {
          opBuilder.setAttr("adjoint_a", opts.adjointA);
        }
        if (opts.adjointB != null) {
          opBuilder.setAttr("adjoint_b", opts.adjointB);
        }
        if (opts.transposeOutput != null) {
          opBuilder.setAttr("transpose_output", opts.transposeOutput);
        }
        if (opts.conjugateOutput != null) {
          opBuilder.setAttr("conjugate_output", opts.conjugateOutput);
        }
      }
    }
    return new SparseMatrixMatMul<T>(opBuilder.build());
  }
  
  /**
   * @param transposeA Indicates whether `a` should be transposed.
   */
  public static Options transposeA(Boolean transposeA) {
    return new Options().transposeA(transposeA);
  }
  
  /**
   * @param transposeB Indicates whether `b` should be transposed.
   */
  public static Options transposeB(Boolean transposeB) {
    return new Options().transposeB(transposeB);
  }
  
  /**
   * @param adjointA Indicates whether `a` should be conjugate-transposed.
   */
  public static Options adjointA(Boolean adjointA) {
    return new Options().adjointA(adjointA);
  }
  
  /**
   * @param adjointB Indicates whether `b` should be conjugate-transposed.
   */
  public static Options adjointB(Boolean adjointB) {
    return new Options().adjointB(adjointB);
  }
  
  /**
   * @param transposeOutput Transposes the product of `a` and `b`.
   */
  public static Options transposeOutput(Boolean transposeOutput) {
    return new Options().transposeOutput(transposeOutput);
  }
  
  /**
   * @param conjugateOutput Conjugates the product of `a` and `b`.
   */
  public static Options conjugateOutput(Boolean conjugateOutput) {
    return new Options().conjugateOutput(conjugateOutput);
  }
  
  /**
   * A dense output tensor.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseMatrixMatMul";
  
  private Output<T> output;
  
  private SparseMatrixMatMul(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
