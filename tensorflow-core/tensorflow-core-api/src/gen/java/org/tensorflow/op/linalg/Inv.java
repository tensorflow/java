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
 * Computes the inverse of one or more square invertible matrices or their
 * <p>
 * adjoints (conjugate transposes).
 * <p>
 * The input is a tensor of shape `[..., M, M]` whose inner-most 2 dimensions
 * form square matrices. The output is a tensor of the same shape as the input
 * containing the inverse for all input submatrices `[..., :, :]`.
 * <p>
 * The op uses LU decomposition with partial pivoting to compute the inverses.
 * <p>
 * If a matrix is not invertible there is no guarantee what the op does. It
 * may detect the condition and raise an exception or it may simply return a
 * garbage result.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "linalg")
public final class Inv<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.Inv}
   */
  public static class Options {
    
    /**
     * @param adjoint 
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
   * Factory method to create a class wrapping a new Inv operation.
   * 
   * @param scope current scope
   * @param input Shape is `[..., M, M]`.
   * @param options carries optional attributes values
   * @return a new instance of Inv
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Inv<T> create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatrixInverse", scope.makeOpName("Inv"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.adjoint != null) {
          opBuilder.setAttr("adjoint", opts.adjoint);
        }
      }
    }
    return new Inv<T>(opBuilder.build());
  }
  
  /**
   * @param adjoint 
   */
  public static Options adjoint(Boolean adjoint) {
    return new Options().adjoint(adjoint);
  }
  
  /**
   * Shape is `[..., M, M]`.
   * <p>
   * @compatibility(numpy)
   * Equivalent to np.linalg.inv
   * @end_compatibility
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MatrixInverse";
  
  private Output<T> output;
  
  private Inv(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
