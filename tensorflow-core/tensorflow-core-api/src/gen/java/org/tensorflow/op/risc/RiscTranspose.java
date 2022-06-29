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
import org.tensorflow.types.family.TType;

/**
 * The RiscTranspose operation
 *
 * @param <T> data type for {@code y} output
 */
@OpMetadata(
    opType = RiscTranspose.OP_NAME,
    inputsClass = RiscTranspose.Inputs.class
)
public final class RiscTranspose<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscTranspose";

  private Output<T> y;

  public RiscTranspose(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscTranspose operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param perm The perm value
   * @param <T> data type for {@code RiscTranspose} output and operands
   * @return a new instance of RiscTranspose
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> RiscTranspose<T> create(Scope scope, Operand<T> x,
      Operand<? extends TNumber> perm) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscTranspose");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(perm.asOutput());
    return new RiscTranspose<>(opBuilder.build());
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
      outputsClass = RiscTranspose.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<RiscTranspose<T>> {
    /**
     * The x input
     */
    public final Operand<T> x;

    /**
     * The perm input
     */
    public final Operand<? extends TNumber> perm;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tperm attribute
     */
    public final DataType Tperm;

    public Inputs(GraphOperation op) {
      super(new RiscTranspose<>(op), op, Arrays.asList("T", "Tperm"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      perm = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tperm = op.attributes().getAttrType("Tperm");
    }
  }
}
