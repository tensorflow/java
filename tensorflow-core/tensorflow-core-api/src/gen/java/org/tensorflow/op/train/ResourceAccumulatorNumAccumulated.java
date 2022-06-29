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

package org.tensorflow.op.train;

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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Returns the number of gradients aggregated in the given accumulators.
 */
@OpMetadata(
    opType = ResourceAccumulatorNumAccumulated.OP_NAME,
    inputsClass = ResourceAccumulatorNumAccumulated.Inputs.class
)
public final class ResourceAccumulatorNumAccumulated extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceAccumulatorNumAccumulated";

  private Output<TInt32> numAccumulated;

  public ResourceAccumulatorNumAccumulated(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    numAccumulated = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ResourceAccumulatorNumAccumulated operation.
   *
   * @param scope current scope
   * @param handle The handle to an accumulator.
   * @return a new instance of ResourceAccumulatorNumAccumulated
   */
  @Endpoint(
      describeByClass = true
  )
  public static ResourceAccumulatorNumAccumulated create(Scope scope,
      Operand<? extends TType> handle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResourceAccumulatorNumAccumulated");
    opBuilder.addInput(handle.asOutput());
    return new ResourceAccumulatorNumAccumulated(opBuilder.build());
  }

  /**
   * Gets numAccumulated.
   * The number of gradients aggregated in the given accumulator.
   * @return numAccumulated.
   */
  public Output<TInt32> numAccumulated() {
    return numAccumulated;
  }

  @Override
  public Output<TInt32> asOutput() {
    return numAccumulated;
  }

  @OpInputsMetadata(
      outputsClass = ResourceAccumulatorNumAccumulated.class
  )
  public static class Inputs extends RawOpInputs<ResourceAccumulatorNumAccumulated> {
    /**
     * The handle to an accumulator.
     */
    public final Operand<? extends TType> handle;

    public Inputs(GraphOperation op) {
      super(new ResourceAccumulatorNumAccumulated(op), op, Arrays.asList());
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
