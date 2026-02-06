/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.tpu;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Generates a zero-valued tensor for use as a dummy input to a TPU.
 * For the internal use of the TF2XLA bridge in the XLA Broadcast pass. This op
 */
@OpMetadata(
    opType = TPUDummyInput.OP_NAME,
    inputsClass = TPUDummyInput.Inputs.class
)
public final class TPUDummyInput<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUDummyInput";

  private Output<T> output;

  public TPUDummyInput(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TPUDummyInput operation.
   *
   * @param scope current scope
   * @param dtype The element type of the produced tensor.
   * @param shape The shape of the produced tensor.
   * @param <T> data type for {@code TPUDummyInput} output and operands
   * @return a new instance of TPUDummyInput
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> TPUDummyInput<T> create(Scope scope, Class<T> dtype,
      Shape shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TPUDummyInput");
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    opBuilder.setAttr("shape", shape);
    return new TPUDummyInput<>(opBuilder.build());
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
      outputsClass = TPUDummyInput.class
  )
  public static class Inputs extends RawOpInputs<TPUDummyInput<?>> {
    /**
     * The element type of the produced tensor.
     */
    public final DataType dtype;

    /**
     * The shape of the produced tensor.
     */
    public final Shape shape;

    public Inputs(GraphOperation op) {
      super(new TPUDummyInput<>(op), op, Arrays.asList("dtype", "shape"));
      int inputIndex = 0;
      dtype = op.attributes().getAttrType("dtype");
      shape = op.attributes().getAttrShape("shape");
    }
  }
}
