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
 * Sends the named tensor to another XLA computation. Wraps the XLA Send operator
 * documented at
 * https://www.tensorflow.org/performance/xla/operation_semantics#send .
 */
@OpMetadata(
    opType = Send.OP_NAME,
    inputsClass = Send.Inputs.class
)
@Operator(
    group = "xla"
)
public final class Send extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSend";

  public Send(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new XlaSend operation.
   *
   * @param scope current scope
   * @param tensor The tensor to send.
   * @param tensorName A string key that identifies the channel.
   * @return a new instance of Send
   */
  @Endpoint(
      describeByClass = true
  )
  public static Send create(Scope scope, Operand<? extends TType> tensor, String tensorName) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Send");
    opBuilder.addInput(tensor.asOutput());
    opBuilder.setAttr("tensor_name", tensorName);
    return new Send(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = Send.class
  )
  public static class Inputs extends RawOpInputs<Send> {
    /**
     * The tensor to send.
     */
    public final Operand<? extends TType> tensor;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * A string key that identifies the channel.
     */
    public final String tensorName;

    public Inputs(GraphOperation op) {
      super(new Send(op), op, Arrays.asList("T", "tensor_name"));
      int inputIndex = 0;
      tensor = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      tensorName = op.attributes().getAttrString("tensor_name");
    }
  }
}
