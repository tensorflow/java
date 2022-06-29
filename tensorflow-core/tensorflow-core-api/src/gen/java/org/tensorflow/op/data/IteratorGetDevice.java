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

package org.tensorflow.op.data;

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
import org.tensorflow.types.family.TType;

/**
 * Returns the name of the device on which {@code resource} has been placed.
 */
@OpMetadata(
    opType = IteratorGetDevice.OP_NAME,
    inputsClass = IteratorGetDevice.Inputs.class
)
public final class IteratorGetDevice extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IteratorGetDevice";

  private Output<TString> device;

  public IteratorGetDevice(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    device = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IteratorGetDevice operation.
   *
   * @param scope current scope
   * @param resource The resource value
   * @return a new instance of IteratorGetDevice
   */
  @Endpoint(
      describeByClass = true
  )
  public static IteratorGetDevice create(Scope scope, Operand<? extends TType> resource) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IteratorGetDevice");
    opBuilder.addInput(resource.asOutput());
    return new IteratorGetDevice(opBuilder.build());
  }

  /**
   * Gets device.
   *
   * @return device.
   */
  public Output<TString> device() {
    return device;
  }

  @Override
  public Output<TString> asOutput() {
    return device;
  }

  @OpInputsMetadata(
      outputsClass = IteratorGetDevice.class
  )
  public static class Inputs extends RawOpInputs<IteratorGetDevice> {
    /**
     * The resource input
     */
    public final Operand<? extends TType> resource;

    public Inputs(GraphOperation op) {
      super(new IteratorGetDevice(op), op, Arrays.asList());
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
