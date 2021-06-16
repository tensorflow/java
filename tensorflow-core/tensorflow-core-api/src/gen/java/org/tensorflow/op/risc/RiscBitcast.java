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

package org.tensorflow.op.risc;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TType;

/**
 * The RiscBitcast operation
 *
 * @param <U> data type for {@code y} output
 */
public final class RiscBitcast<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscBitcast";

  private Output<U> y;

  private RiscBitcast(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscBitcast operation.
   *
   * @param scope current scope
   * @param x the x value
   * @param DstT the value of the DstT property
   * @param <U> data type for {@code RiscBitcast} output and operands
   * @return a new instance of RiscBitcast
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> RiscBitcast<U> create(Scope scope, Operand<? extends TType> x,
      Class<U> DstT) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("RiscBitcast"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("DstT", Operands.toDataType(DstT));
    return new RiscBitcast<>(opBuilder.build());
  }

  /**
   * Gets y.
   *
   * @return y.
   */
  public Output<U> y() {
    return y;
  }

  @Override
  public Output<U> asOutput() {
    return y;
  }
}
