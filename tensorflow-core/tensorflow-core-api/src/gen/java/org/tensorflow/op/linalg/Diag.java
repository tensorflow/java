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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Returns a batched diagonal tensor with a given batched diagonal values.
 * <p>
 * Given a `diagonal`, this operation returns a tensor with the `diagonal` and
 * everything else padded with zeros. The diagonal is computed as follows:
 * <p>
 * Assume `diagonal` has `k` dimensions `[I, J, K, ..., N]`, then the output is a
 * tensor of rank `k+1` with dimensions [I, J, K, ..., N, N]` where:
 * <p>
 * `output[i, j, k, ..., m, n] = 1{m=n} * diagonal[i, j, k, ..., n]`.
 * <p>
 * For example:
 * <pre>{@code
 * # 'diagonal' is [[1, 2, 3, 4], [5, 6, 7, 8]]
 * 
 * and diagonal.shape = (2, 4)
 * 
 * tf.matrix_diag(diagonal) ==> [[[1, 0, 0, 0]
 *                                      [0, 2, 0, 0]
 *                                      [0, 0, 3, 0]
 *                                      [0, 0, 0, 4]],
 *                                     [[5, 0, 0, 0]
 *                                      [0, 6, 0, 0]
 *                                      [0, 0, 7, 0]
 *                                      [0, 0, 0, 8]]]
 * 
 * which has shape (2, 4, 4)
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "linalg")
public final class Diag<T extends TType> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Diag operation.
   * 
   * @param scope current scope
   * @param diagonal Rank `k`, where `k >= 1`.
   * @return a new instance of Diag
   */
  public static <T extends TType> Diag<T> create(Scope scope, Operand<T> diagonal) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatrixDiag", scope.makeOpName("Diag"));
    opBuilder.addInput(diagonal.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new Diag<T>(opBuilder.build());
  }
  
  /**
   * Rank `k+1`, with `output.shape = diagonal.shape + [diagonal.shape[-1]]`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  private Output<T> output;
  
  private Diag(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
