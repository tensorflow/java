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

package org.tensorflow.op.core;

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
import org.tensorflow.types.family.TType;

/**
 * The RpcServerStart operation
 */
@OpMetadata(
    opType = RpcServerStart.OP_NAME,
    inputsClass = RpcServerStart.Inputs.class
)
@Operator
public final class RpcServerStart extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RpcServerStart";

  public RpcServerStart(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new RpcServerStart operation.
   *
   * @param scope current scope
   * @param server The server value
   * @return a new instance of RpcServerStart
   */
  @Endpoint(
      describeByClass = true
  )
  public static RpcServerStart create(Scope scope, Operand<? extends TType> server) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RpcServerStart");
    opBuilder.addInput(server.asOutput());
    return new RpcServerStart(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = RpcServerStart.class
  )
  public static class Inputs extends RawOpInputs<RpcServerStart> {
    /**
     * The server input
     */
    public final Operand<? extends TType> server;

    public Inputs(GraphOperation op) {
      super(new RpcServerStart(op), op, Arrays.asList());
      int inputIndex = 0;
      server = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
