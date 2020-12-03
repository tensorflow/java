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
 * Solves systems of linear equations.
 * <p>
 * `Matrix` is a tensor of shape `[..., M, M]` whose inner-most 2 dimensions
 * form square matrices. `Rhs` is a tensor of shape `[..., M, K]`. The `output` is
 * a tensor shape `[..., M, K]`.  If `adjoint` is `False` then each output matrix
 * satisfies `matrix[..., :, :] * output[..., :, :] = rhs[..., :, :]`.
 * If `adjoint` is `True` then each output matrix satisfies
 * `adjoint(matrix[..., :, :]) * output[..., :, :] = rhs[..., :, :]`.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "linalg")
public final class Solve<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.Solve}
   */
  public static class Options {
    
    /**
     * @param adjoint Boolean indicating whether to solve with `matrix` or its (block-wise)
     * adjoint.
     */
    public Options adjoint(Boolean adjoint) {
      this.adjoint = adjoint;
      return this;
    }
    
    private Boolean adjoint;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Solve operation.
   * 
   * @param scope current scope
   * @param matrix Shape is `[..., M, M]`.
   * @param rhs Shape is `[..., M, K]`.
   * @param options carries optional attributes values
   * @return a new instance of Solve
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Solve<T> create(Scope scope, Operand<T> matrix, Operand<T> rhs, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatrixSolve", scope.makeOpName("Solve"));
    opBuilder.addInput(matrix.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.adjoint != null) {
          opBuilder.setAttr("adjoint", opts.adjoint);
        }
      }
    }
    return new Solve<T>(opBuilder.build());
  }
  
  /**
   * @param adjoint Boolean indicating whether to solve with `matrix` or its (block-wise)
   * adjoint.
   */
  public static Options adjoint(Boolean adjoint) {
    return new Options().adjoint(adjoint);
  }
  
  /**
   * Shape is `[..., M, K]`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MatrixSolve";
  
  private Output<T> output;
  
  private Solve(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
