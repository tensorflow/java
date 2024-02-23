/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.io;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Transforms a Tensor into a serialized TensorProto proto.
 */
@OpMetadata(
    opType = SerializeTensor.OP_NAME,
    inputsClass = SerializeTensor.Inputs.class
)
@Operator(
    group = "io"
)
public final class SerializeTensor extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SerializeTensor";

  private Output<TString> serialized;

  public SerializeTensor(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    serialized = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SerializeTensor operation.
   *
   * @param scope current scope
   * @param tensor A Tensor of type {@code T}.
   * @return a new instance of SerializeTensor
   */
  @Endpoint(
      describeByClass = true
  )
  public static SerializeTensor create(Scope scope, Operand<? extends TType> tensor) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SerializeTensor");
    opBuilder.addInput(tensor.asOutput());
    return new SerializeTensor(opBuilder.build());
  }

  /**
   * Gets serialized.
   * A serialized TensorProto proto of the input tensor.
   * @return serialized.
   */
  public Output<TString> serialized() {
    return serialized;
  }

  @Override
  public Output<TString> asOutput() {
    return serialized;
  }

  @OpInputsMetadata(
      outputsClass = SerializeTensor.class
  )
  public static class Inputs extends RawOpInputs<SerializeTensor> {
    /**
     * A Tensor of type {@code T}.
     */
    public final Operand<? extends TType> tensor;

    /**
     * The type of the input tensor.
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SerializeTensor(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      tensor = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
