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
 * Returns a diagonal tensor with a given diagonal values.
 * <p>
 * Given a `diagonal`, this operation returns a tensor with the `diagonal` and
 * everything else padded with zeros. The diagonal is computed as follows:
 * <p>
 * Assume `diagonal` has dimensions [D1,..., Dk], then the output is a tensor of
 * rank 2k with dimensions [D1,..., Dk, D1,..., Dk] where:
 * <p>
 * `output[i1,..., ik, i1,..., ik] = diagonal[i1, ..., ik]` and 0 everywhere else.
 * <p>
 * For example:
 * <pre>{@code
 * # 'diagonal' is [1, 2, 3, 4]
 * tf.diag(diagonal) ==> [[1, 0, 0, 0]
 *                        [0, 2, 0, 0]
 *                        [0, 0, 3, 0]
 *                        [0, 0, 0, 4]]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "linalg")
public final class TensorDiag<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new TensorDiag operation.
   * 
   * @param scope current scope
   * @param diagonal Rank k tensor where k is at most 1.
   * @return a new instance of TensorDiag
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TensorDiag<T> create(Scope scope, Operand<T> diagonal) {
    OperationBuilder opBuilder = scope.env().opBuilder("Diag", scope.makeOpName("TensorDiag"));
    opBuilder.addInput(diagonal.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorDiag<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Diag";
  
  private Output<T> output;
  
  private TensorDiag(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
