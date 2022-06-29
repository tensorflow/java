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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * The RiscCast operation
 *
 * @param <U> data type for {@code y} output
 */
@OpMetadata(
    opType = RiscCast.OP_NAME,
    inputsClass = RiscCast.Inputs.class
)
public final class RiscCast<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscCast";

  private Output<U> y;

  public RiscCast(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscCast operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param DstT The value of the DstT attribute
   * @param <U> data type for {@code RiscCast} output and operands
   * @return a new instance of RiscCast
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> RiscCast<U> create(Scope scope, Operand<? extends TType> x,
      Class<U> DstT) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscCast");
    opBuilder.addInput(x.asOutput());
    opBuilder.setAttr("DstT", Operands.toDataType(DstT));
    return new RiscCast<>(opBuilder.build());
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

  @OpInputsMetadata(
      outputsClass = RiscCast.class
  )
  public static class Inputs extends RawOpInputs<RiscCast<?>> {
    /**
     * The x input
     */
    public final Operand<? extends TType> x;

    /**
     * The SrcT attribute
     */
    public final DataType SrcT;

    /**
     * The DstT attribute
     */
    public final DataType DstT;

    public Inputs(GraphOperation op) {
      super(new RiscCast<>(op), op, Arrays.asList("SrcT", "DstT"));
      int inputIndex = 0;
      x = (Operand<? extends TType>) op.input(inputIndex++);
      SrcT = op.attributes().getAttrType("SrcT");
      DstT = op.attributes().getAttrType("DstT");
    }
  }
}
