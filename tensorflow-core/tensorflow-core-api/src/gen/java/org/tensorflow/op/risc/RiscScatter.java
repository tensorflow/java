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
 * The RiscScatter operation
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = RiscScatter.OP_NAME,
    inputsClass = RiscScatter.Inputs.class
)
public final class RiscScatter<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscScatter";

  private Output<U> output;

  public RiscScatter(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscScatter operation.
   *
   * @param scope current scope
   * @param indices The indices value
   * @param updates The updates value
   * @param shape The shape value
   * @param <U> data type for {@code RiscScatter} output and operands
   * @param <T> data type for {@code RiscScatter} output and operands
   * @return a new instance of RiscScatter
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber, T extends TNumber> RiscScatter<U> create(Scope scope,
      Operand<T> indices, Operand<U> updates, Operand<T> shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscScatter");
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    opBuilder.addInput(shape.asOutput());
    return new RiscScatter<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = RiscScatter.class
  )
  public static class Inputs<T extends TNumber, U extends TNumber> extends RawOpInputs<RiscScatter<U>> {
    /**
     * The indices input
     */
    public final Operand<T> indices;

    /**
     * The updates input
     */
    public final Operand<U> updates;

    /**
     * The shape input
     */
    public final Operand<T> shape;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    public Inputs(GraphOperation op) {
      super(new RiscScatter<>(op), op, Arrays.asList("T", "Tindices"));
      int inputIndex = 0;
      indices = (Operand<T>) op.input(inputIndex++);
      updates = (Operand<U>) op.input(inputIndex++);
      shape = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
