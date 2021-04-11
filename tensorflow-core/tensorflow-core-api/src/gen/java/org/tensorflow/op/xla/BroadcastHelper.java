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

package org.tensorflow.op.xla;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Helper operator for performing XLA-style broadcasts
 * Broadcasts {@code lhs} and {@code rhs} to the same rank, by adding size 1 dimensions to
 * whichever of {@code lhs} and {@code rhs} has the lower rank, using XLA's broadcasting rules
 * for binary operators.
 *
 * @param <T> data type for {@code lhs_output} output
 */
@Operator(
    group = "xla"
)
public final class BroadcastHelper<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaBroadcastHelper";

  private Output<T> lhsOutput;

  private Output<T> rhsOutput;

  private BroadcastHelper(Operation operation) {
    super(operation);
    int outputIdx = 0;
    lhsOutput = operation.output(outputIdx++);
    rhsOutput = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaBroadcastHelper operation.
   *
   * @param scope current scope
   * @param lhs the LHS input tensor
   * @param rhs the RHS input tensor
   * @param broadcastDims an XLA-style broadcast dimension specification
   * @param <T> data type for {@code XlaBroadcastHelper} output and operands
   * @return a new instance of BroadcastHelper
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> BroadcastHelper<T> create(Scope scope, Operand<T> lhs,
      Operand<T> rhs, Operand<? extends TNumber> broadcastDims) {
    OperationBuilder opBuilder = scope.env().opBuilder("XlaBroadcastHelper", scope.makeOpName("BroadcastHelper"));
    opBuilder.addInput(lhs.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder.addInput(broadcastDims.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new BroadcastHelper<>(opBuilder.build());
  }

  /**
   * Gets lhsOutput.
   * the broadcasted LHS tensor
   * @return lhsOutput.
   */
  public Output<T> lhsOutput() {
    return lhsOutput;
  }

  /**
   * Gets rhsOutput.
   * the broadcasted RHS tensor
   * @return rhsOutput.
   */
  public Output<T> rhsOutput() {
    return rhsOutput;
  }
}
