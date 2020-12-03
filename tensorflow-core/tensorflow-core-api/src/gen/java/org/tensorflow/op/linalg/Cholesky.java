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
 * Computes the Cholesky decomposition of one or more square matrices.
 * <p>
 * The input is a tensor of shape `[..., M, M]` whose inner-most 2 dimensions
 * form square matrices.
 * <p>
 * The input has to be symmetric and positive definite. Only the lower-triangular
 * part of the input will be used for this operation. The upper-triangular part
 * will not be read.
 * <p>
 * The output is a tensor of the same shape as the input
 * containing the Cholesky decompositions for all input submatrices `[..., :, :]`.
 * <p>
 * <b>Note</b>: The gradient computation on GPU is faster for large matrices but
 * not for large batch dimensions when the submatrices are small. In this
 * case it might be faster to use the CPU.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "linalg")
public final class Cholesky<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Cholesky operation.
   * 
   * @param scope current scope
   * @param input Shape is `[..., M, M]`.
   * @return a new instance of Cholesky
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Cholesky<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("Cholesky", scope.makeOpName("Cholesky"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Cholesky<T>(opBuilder.build());
  }
  
  /**
   * Shape is `[..., M, M]`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Cholesky";
  
  private Output<T> output;
  
  private Cholesky(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
