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

package org.tensorflow.op.tpu;

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
import org.tensorflow.types.TString;

/**
 * Worker heartbeat op.
 * Heartbeats may be sent periodically to indicate the coordinator is still active,
 * to retrieve the current worker status and to expedite shutdown when necessary.
 */
@OpMetadata(
    opType = WorkerHeartbeat.OP_NAME,
    inputsClass = WorkerHeartbeat.Inputs.class
)
public final class WorkerHeartbeat extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "WorkerHeartbeat";

  private Output<TString> response;

  public WorkerHeartbeat(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    response = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new WorkerHeartbeat operation.
   *
   * @param scope current scope
   * @param request A string tensor containing a serialized WorkerHeartbeatRequest
   * @return a new instance of WorkerHeartbeat
   */
  @Endpoint(
      describeByClass = true
  )
  public static WorkerHeartbeat create(Scope scope, Operand<TString> request) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "WorkerHeartbeat");
    opBuilder.addInput(request.asOutput());
    return new WorkerHeartbeat(opBuilder.build());
  }

  /**
   * Gets response.
   * A string tensor containing a serialized WorkerHeartbeatResponse
   * @return response.
   */
  public Output<TString> response() {
    return response;
  }

  @Override
  public Output<TString> asOutput() {
    return response;
  }

  @OpInputsMetadata(
      outputsClass = WorkerHeartbeat.class
  )
  public static class Inputs extends RawOpInputs<WorkerHeartbeat> {
    /**
     * A string tensor containing a serialized WorkerHeartbeatRequest
     */
    public final Operand<TString> request;

    public Inputs(GraphOperation op) {
      super(new WorkerHeartbeat(op), op, Arrays.asList());
      int inputIndex = 0;
      request = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
