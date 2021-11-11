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
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;

/**
 * The RiscIsFinite operation
 */
@OpMetadata(
    opType = RiscIsFinite.OP_NAME,
    inputsClass = RiscIsFinite.Inputs.class
)
public final class RiscIsFinite extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscIsFinite";

  private Output<TBool> y;

  public RiscIsFinite(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscIsFinite operation.
   *
   * @param scope current scope
   * @param x The x value
   * @return a new instance of RiscIsFinite
   */
  @Endpoint(
      describeByClass = true
  )
  public static RiscIsFinite create(Scope scope, Operand<? extends TNumber> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscIsFinite");
    opBuilder.addInput(x.asOutput());
    return new RiscIsFinite(opBuilder.build());
  }

  /**
   * Gets y.
   *
   * @return y.
   */
  public Output<TBool> y() {
    return y;
  }

  @Override
  public Output<TBool> asOutput() {
    return y;
  }

  @OpInputsMetadata(
      outputsClass = RiscIsFinite.class
  )
  public static class Inputs extends RawOpInputs<RiscIsFinite> {
    /**
     * The x input
     */
    public final Operand<? extends TNumber> x;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new RiscIsFinite(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      x = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
