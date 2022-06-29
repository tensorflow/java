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
 * The RiscReverse operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RiscReverse.OP_NAME,
    inputsClass = RiscReverse.Inputs.class
)
public final class RiscReverse<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscReverse";

  private Output<T> output;

  public RiscReverse(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscReverse operation.
   *
   * @param scope current scope
   * @param tensor The tensor value
   * @param axis The axis value
   * @param <T> data type for {@code RiscReverse} output and operands
   * @return a new instance of RiscReverse
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RiscReverse<T> create(Scope scope, Operand<T> tensor,
      Operand<? extends TNumber> axis) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscReverse");
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(axis.asOutput());
    return new RiscReverse<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = RiscReverse.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<RiscReverse<T>> {
    /**
     * The tensor input
     */
    public final Operand<T> tensor;

    /**
     * The axis input
     */
    public final Operand<? extends TNumber> axis;

    /**
     * The Tidx attribute
     */
    public final DataType Tidx;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new RiscReverse<>(op), op, Arrays.asList("Tidx", "T"));
      int inputIndex = 0;
      tensor = (Operand<T>) op.input(inputIndex++);
      axis = (Operand<? extends TNumber>) op.input(inputIndex++);
      Tidx = op.attributes().getAttrType("Tidx");
      T = op.attributes().getAttrType("T");
    }
  }
}
