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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * The RiscUnary operation
 *
 * @param <T> data type for {@code y} output
 */
@OpMetadata(
    opType = RiscUnary.OP_NAME,
    inputsClass = RiscUnary.Inputs.class
)
public final class RiscUnary<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscUnary";

  private Output<T> y;

  public RiscUnary(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscUnary operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param opType The value of the opType attribute
   * @param <T> data type for {@code RiscUnary} output and operands
   * @return a new instance of RiscUnary
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RiscUnary<T> create(Scope scope, Operand<T> x, String opType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscUnary");
    opBuilder.addInput(x.asOutput());
    opBuilder.setAttr("op_type", opType);
    return new RiscUnary<>(opBuilder.build());
  }

  /**
   * Gets y.
   *
   * @return y.
   */
  public Output<T> y() {
    return y;
  }

  @Override
  public Output<T> asOutput() {
    return y;
  }

  @OpInputsMetadata(
      outputsClass = RiscUnary.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<RiscUnary<T>> {
    /**
     * The x input
     */
    public final Operand<T> x;

    /**
     * The opType attribute
     */
    public final String opType;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new RiscUnary<>(op), op, Arrays.asList("op_type", "T"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      opType = op.attributes().getAttrString("op_type");
      T = op.attributes().getAttrType("T");
    }
  }
}
