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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Receives the named tensor from another XLA computation. Wraps the XLA Recv
 * operator documented at
 * https://www.tensorflow.org/performance/xla/operation_semantics#recv .
 *
 * @param <T> data type for {@code tensor} output
 */
@OpMetadata(
    opType = Recv.OP_NAME,
    inputsClass = Recv.Inputs.class
)
@Operator(
    group = "xla"
)
public final class Recv<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaRecv";

  private Output<T> tensor;

  public Recv(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    tensor = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaRecv operation.
   *
   * @param scope current scope
   * @param dtype The type of the tensor.
   * @param tensorName A string key that identifies the channel.
   * @param shape The shape of the tensor.
   * @param <T> data type for {@code XlaRecv} output and operands
   * @return a new instance of Recv
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Recv<T> create(Scope scope, Class<T> dtype, String tensorName,
      Shape shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Recv");
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    opBuilder.setAttr("tensor_name", tensorName);
    opBuilder.setAttr("shape", shape);
    return new Recv<>(opBuilder.build());
  }

  /**
   * Gets tensor.
   * The tensor to receive.
   * @return tensor.
   */
  public Output<T> tensor() {
    return tensor;
  }

  @Override
  public Output<T> asOutput() {
    return tensor;
  }

  @OpInputsMetadata(
      outputsClass = Recv.class
  )
  public static class Inputs extends RawOpInputs<Recv<?>> {
    /**
     * The type of the tensor.
     */
    public final DataType dtype;

    /**
     * A string key that identifies the channel.
     */
    public final String tensorName;

    /**
     * The shape of the tensor.
     */
    public final Shape shape;

    public Inputs(GraphOperation op) {
      super(new Recv<>(op), op, Arrays.asList("dtype", "tensor_name", "shape"));
      int inputIndex = 0;
      dtype = op.attributes().getAttrType("dtype");
      tensorName = op.attributes().getAttrString("tensor_name");
      shape = op.attributes().getAttrShape("shape");
    }
  }
}
