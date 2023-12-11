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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * An op that sets up communication between TPUEmbedding host software instances
 * after ConfigureTPUEmbeddingHost has been called on each host.
 */
@OpMetadata(
    opType = ConnectTPUEmbeddingHosts.OP_NAME,
    inputsClass = ConnectTPUEmbeddingHosts.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class ConnectTPUEmbeddingHosts extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ConnectTPUEmbeddingHosts";

  public ConnectTPUEmbeddingHosts(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new ConnectTPUEmbeddingHosts operation.
   *
   * @param scope current scope
   * @param networkConfigs Strings containing metadata about the hostname and RPC port
   * used for communication with all hosts.
   * @return a new instance of ConnectTPUEmbeddingHosts
   */
  @Endpoint(
      describeByClass = true
  )
  public static ConnectTPUEmbeddingHosts create(Scope scope,
      Iterable<Operand<TString>> networkConfigs) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ConnectTPUEmbeddingHosts");
    opBuilder.addInputList(Operands.asOutputs(networkConfigs));
    return new ConnectTPUEmbeddingHosts(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = ConnectTPUEmbeddingHosts.class
  )
  public static class Inputs extends RawOpInputs<ConnectTPUEmbeddingHosts> {
    /**
     * Strings containing metadata about the hostname and RPC port
     * used for communication with all hosts.
     */
    public final Iterable<Operand<TString>> networkConfigs;

    public Inputs(GraphOperation op) {
      super(new ConnectTPUEmbeddingHosts(op), op, Arrays.asList());
      int inputIndex = 0;
      int networkConfigsLength = op.inputListLength("network_configs");
      networkConfigs = Arrays.asList((Operand<TString>[]) op.inputList(inputIndex, networkConfigsLength));
      inputIndex += networkConfigsLength;
    }
  }
}
