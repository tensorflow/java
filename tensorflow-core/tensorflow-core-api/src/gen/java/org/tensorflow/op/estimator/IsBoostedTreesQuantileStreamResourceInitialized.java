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

package org.tensorflow.op.estimator;

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
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * Checks whether a quantile stream has been initialized.
 * An Op that checks if quantile stream resource is initialized.
 */
@OpMetadata(
    opType = IsBoostedTreesQuantileStreamResourceInitialized.OP_NAME,
    inputsClass = IsBoostedTreesQuantileStreamResourceInitialized.Inputs.class
)
public final class IsBoostedTreesQuantileStreamResourceInitialized extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IsBoostedTreesQuantileStreamResourceInitialized";

  private Output<TBool> isInitialized;

  public IsBoostedTreesQuantileStreamResourceInitialized(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    isInitialized = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IsBoostedTreesQuantileStreamResourceInitialized operation.
   *
   * @param scope current scope
   * @param quantileStreamResourceHandle resource; The reference to quantile stream resource handle.
   * @return a new instance of IsBoostedTreesQuantileStreamResourceInitialized
   */
  @Endpoint(
      describeByClass = true
  )
  public static IsBoostedTreesQuantileStreamResourceInitialized create(Scope scope,
      Operand<? extends TType> quantileStreamResourceHandle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IsBoostedTreesQuantileStreamResourceInitialized");
    opBuilder.addInput(quantileStreamResourceHandle.asOutput());
    return new IsBoostedTreesQuantileStreamResourceInitialized(opBuilder.build());
  }

  /**
   * Gets isInitialized.
   * bool; True if the resource is initialized, False otherwise.
   * @return isInitialized.
   */
  public Output<TBool> isInitialized() {
    return isInitialized;
  }

  @Override
  public Output<TBool> asOutput() {
    return isInitialized;
  }

  @OpInputsMetadata(
      outputsClass = IsBoostedTreesQuantileStreamResourceInitialized.class
  )
  public static class Inputs extends RawOpInputs<IsBoostedTreesQuantileStreamResourceInitialized> {
    /**
     * resource; The reference to quantile stream resource handle.
     */
    public final Operand<? extends TType> quantileStreamResourceHandle;

    public Inputs(GraphOperation op) {
      super(new IsBoostedTreesQuantileStreamResourceInitialized(op), op, Arrays.asList());
      int inputIndex = 0;
      quantileStreamResourceHandle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
