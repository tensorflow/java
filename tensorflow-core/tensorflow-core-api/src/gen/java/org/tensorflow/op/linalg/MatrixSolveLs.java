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
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.family.TType;

/**
 * Solves one or more linear least-squares problems.
 * <p>
 * `matrix` is a tensor of shape `[..., M, N]` whose inner-most 2 dimensions
 * form real or complex matrices of size `[M, N]`. `Rhs` is a tensor of the same
 * type as `matrix` and shape `[..., M, K]`.
 * The output is a tensor shape `[..., N, K]` where each output matrix solves
 * each of the equations
 * `matrix[..., :, :]` * `output[..., :, :]` = `rhs[..., :, :]`
 * in the least squares sense.
 * <p>
 * We use the following notation for (complex) matrix and right-hand sides
 * in the batch:
 * <p>
 * `matrix`=\\(A \in \mathbb{C}^{m \times n}\\),
 * `rhs`=\\(B  \in \mathbb{C}^{m \times k}\\),
 * `output`=\\(X  \in \mathbb{C}^{n \times k}\\),
 * `l2_regularizer`=\\(\lambda \in \mathbb{R}\\).
 * <p>
 * If `fast` is `True`, then the solution is computed by solving the normal
 * equations using Cholesky decomposition. Specifically, if \\(m \ge n\\) then
 * \\(X = (A^H A + \lambda I)^{-1} A^H B\\), which solves the least-squares
 * problem \\(X = \mathrm{argmin}_{Z \in \Re^{n \times k} } ||A Z - B||_F^2 + \lambda ||Z||_F^2\\).
 * If \\(m \lt n\\) then `output` is computed as
 * \\(X = A^H (A A^H + \lambda I)^{-1} B\\), which (for \\(\lambda = 0\\)) is the
 * minimum-norm solution to the under-determined linear system, i.e.
 * \\(X = \mathrm{argmin}_{Z \in \mathbb{C}^{n \times k} } ||Z||_F^2 \\),
 * subject to \\(A Z = B\\). Notice that the fast path is only numerically stable
 * when \\(A\\) is numerically full rank and has a condition number
 * \\(\mathrm{cond}(A) \lt \frac{1}{\sqrt{\epsilon_{mach} } }\\) or \\(\lambda\\) is
 * sufficiently large.
 * <p>
 * If `fast` is `False` an algorithm based on the numerically robust complete
 * orthogonal decomposition is used. This computes the minimum-norm
 * least-squares solution, even when \\(A\\) is rank deficient. This path is
 * typically 6-7 times slower than the fast path. If `fast` is `False` then
 * `l2_regularizer` is ignored.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "linalg")
public final class MatrixSolveLs<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.MatrixSolveLs}
   */
  public static class Options {
    
    /**
     * @param fast 
     */
    public Options fast(Boolean fast) {
      this.fast = fast;
      return this;
    }
    
    private Boolean fast;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new MatrixSolveLs operation.
   * 
   * @param scope current scope
   * @param matrix Shape is `[..., M, N]`.
   * @param rhs Shape is `[..., M, K]`.
   * @param l2Regularizer Scalar tensor.
   * <p>
   * @compatibility(numpy)
   * Equivalent to np.linalg.lstsq
   * @end_compatibility
   * @param options carries optional attributes values
   * @return a new instance of MatrixSolveLs
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> MatrixSolveLs<T> create(Scope scope, Operand<T> matrix, Operand<T> rhs, Operand<TFloat64> l2Regularizer, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatrixSolveLs", scope.makeOpName("MatrixSolveLs"));
    opBuilder.addInput(matrix.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder.addInput(l2Regularizer.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.fast != null) {
          opBuilder.setAttr("fast", opts.fast);
        }
      }
    }
    return new MatrixSolveLs<T>(opBuilder.build());
  }
  
  /**
   * @param fast 
   */
  public static Options fast(Boolean fast) {
    return new Options().fast(fast);
  }
  
  /**
   * Shape is `[..., N, K]`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MatrixSolveLs";
  
  private Output<T> output;
  
  private MatrixSolveLs(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
