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
 * Returns the diagonal part of the tensor.
 * <p>
 * This operation returns a tensor with the `diagonal` part
 * of the `input`. The `diagonal` part is computed as follows:
 * <p>
 * Assume `input` has dimensions `[D1,..., Dk, D1,..., Dk]`, then the output is a
 * tensor of rank `k` with dimensions `[D1,..., Dk]` where:
 * <p>
 * `diagonal[i1,..., ik] = input[i1, ..., ik, i1,..., ik]`.
 * <p>
 * For example:
 * <pre>{@code
 * # 'input' is [[1, 0, 0, 0]
 *               [0, 2, 0, 0]
 *               [0, 0, 3, 0]
 *               [0, 0, 0, 4]]
 * 
 * tf.diag_part(input) ==> [1, 2, 3, 4]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code diagonal()} output
 */
@Operator(group = "linalg")
public final class TensorDiagPart<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new TensorDiagPart operation.
   * 
   * @param scope current scope
   * @param input Rank k tensor where k is even and not zero.
   * @return a new instance of TensorDiagPart
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TensorDiagPart<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("DiagPart", scope.makeOpName("TensorDiagPart"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorDiagPart<T>(opBuilder.build());
  }
  
  /**
   * The extracted diagonal.
   */
  public Output<T> diagonal() {
    return diagonal;
  }
  
  @Override
  public Output<T> asOutput() {
    return diagonal;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DiagPart";
  
  private Output<T> diagonal;
  
  private TensorDiagPart(Operation operation) {
    super(operation);
    int outputIdx = 0;
    diagonal = operation.output(outputIdx++);
  }
}
