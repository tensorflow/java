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
 * Returns a batched matrix tensor with new batched diagonal values.
 * <p>
 * Given `input` and `diagonal`, this operation returns a tensor with the
 * same shape and values as `input`, except for the main diagonal of the
 * innermost matrices.  These will be overwritten by the values in `diagonal`.
 * <p>
 * The output is computed as follows:
 * <p>
 * Assume `input` has `k+1` dimensions `[I, J, K, ..., M, N]` and `diagonal` has
 * `k` dimensions `[I, J, K, ..., min(M, N)]`.  Then the output is a
 * tensor of rank `k+1` with dimensions `[I, J, K, ..., M, N]` where:
 * <p>
 *   * `output[i, j, k, ..., m, n] = diagonal[i, j, k, ..., n]` for `m == n`.
 *   * `output[i, j, k, ..., m, n] = input[i, j, k, ..., m, n]` for `m != n`.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "linalg")
public final class SetDiag<T extends TType> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SetDiag operation.
   * 
   * @param scope current scope
   * @param input Rank `k+1`, where `k >= 1`.
   * @param diagonal Rank `k`, where `k >= 1`.
   * @return a new instance of SetDiag
   */
  public static <T extends TType> SetDiag<T> create(Scope scope, Operand<T> input, Operand<T> diagonal) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatrixSetDiag", scope.makeOpName("SetDiag"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(diagonal.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new SetDiag<T>(opBuilder.build());
  }
  
  /**
   * Rank `k+1`, with `output.shape = input.shape`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  private Output<T> output;
  
  private SetDiag(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
