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

package org.tensorflow.op.linalg;

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
 * Solves tridiagonal systems of equations.
 * <p>
 *   Solves tridiagonal systems of equations.
 *   Supports batch dimensions and multiple right-hand sides per each left-hand
 *   side.
 *   On CPU, solution is computed via Gaussian elimination with or without partial
 *   pivoting, depending on `partial_pivoting` attribute. On GPU, Nvidia's cuSPARSE
 *   library is used: https://docs.nvidia.com/cuda/cusparse/index.html#gtsv
 *   Partial pivoting is not yet supported by XLA backends.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class TridiagonalSolve<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.TridiagonalSolve}
   */
  public static class Options {
    
    /**
     * @param partialPivoting Whether to apply partial pivoting. Partial pivoting makes the procedure more
     * stable, but slower.
     */
    public Options partialPivoting(Boolean partialPivoting) {
      this.partialPivoting = partialPivoting;
      return this;
    }
    
    private Boolean partialPivoting;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new TridiagonalSolve operation.
   * 
   * @param scope current scope
   * @param diagonals Tensor of shape `[..., 3, M]` whose innermost 2 dimensions represent the
   * tridiagonal matrices with three rows being the superdiagonal, diagonals, and
   * subdiagonals, in order. The last element of the superdiagonal and the first
   * element of the subdiagonal is ignored.
   * @param rhs Tensor of shape `[..., M, K]`, representing K right-hand sides per each
   * left-hand side.
   * @param options carries optional attributes values
   * @return a new instance of TridiagonalSolve
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TridiagonalSolve<T> create(Scope scope, Operand<T> diagonals, Operand<T> rhs, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TridiagonalSolve", scope.makeOpName("TridiagonalSolve"));
    opBuilder.addInput(diagonals.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.partialPivoting != null) {
          opBuilder.setAttr("partial_pivoting", opts.partialPivoting);
        }
      }
    }
    return new TridiagonalSolve<T>(opBuilder.build());
  }
  
  /**
   * @param partialPivoting Whether to apply partial pivoting. Partial pivoting makes the procedure more
   * stable, but slower.
   */
  public static Options partialPivoting(Boolean partialPivoting) {
    return new Options().partialPivoting(partialPivoting);
  }
  
  /**
   * Tensor of shape `[..., M, K]` containing the solutions
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TridiagonalSolve";
  
  private Output<T> output;
  
  private TridiagonalSolve(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
