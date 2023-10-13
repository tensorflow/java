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
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;

/**
 * Synchronizes the device this op is run on.
 * Only GPU ops are asynchrous in TensorFlow, and so this only has an effect when
 * run on GPUs. On GPUs, this op synchronizes the GPU's compute stream.
 */
@OpMetadata(
    opType = SyncDevice.OP_NAME,
    inputsClass = SyncDevice.Inputs.class
)
public final class SyncDevice extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SyncDevice";

  public SyncDevice(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new SyncDevice operation.
   *
   * @param scope current scope
   * @return a new instance of SyncDevice
   */
  @Endpoint(
      describeByClass = true
  )
  public static SyncDevice create(Scope scope) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SyncDevice");
    return new SyncDevice(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = SyncDevice.class
  )
  public static class Inputs extends RawOpInputs<SyncDevice> {
    public Inputs(GraphOperation op) {
      super(new SyncDevice(op), op, Arrays.asList());
      int inputIndex = 0;
    }
  }
}
