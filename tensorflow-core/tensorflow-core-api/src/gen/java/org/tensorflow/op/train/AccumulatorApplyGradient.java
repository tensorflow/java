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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Applies a gradient to a given accumulator.
 * Does not add if local_step is lesser than the accumulator's global_step.
 */
@OpMetadata(
    opType = AccumulatorApplyGradient.OP_NAME,
    inputsClass = AccumulatorApplyGradient.Inputs.class
)
@Operator(
    group = "train"
)
public final class AccumulatorApplyGradient extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AccumulatorApplyGradient";

  public AccumulatorApplyGradient(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new AccumulatorApplyGradient operation.
   *
   * @param scope current scope
   * @param handle The handle to a accumulator.
   * @param localStep The local_step value at which the gradient was computed.
   * @param gradient A tensor of the gradient to be accumulated.
   * @return a new instance of AccumulatorApplyGradient
   */
  @Endpoint(
      describeByClass = true
  )
  public static AccumulatorApplyGradient create(Scope scope, Operand<TString> handle,
      Operand<TInt64> localStep, Operand<? extends TType> gradient) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AccumulatorApplyGradient");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(localStep.asOutput());
    opBuilder.addInput(gradient.asOutput());
    return new AccumulatorApplyGradient(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = AccumulatorApplyGradient.class
  )
  public static class Inputs extends RawOpInputs<AccumulatorApplyGradient> {
    /**
     * The handle to a accumulator.
     */
    public final Operand<TString> handle;

    /**
     * The local_step value at which the gradient was computed.
     */
    public final Operand<TInt64> localStep;

    /**
     * A tensor of the gradient to be accumulated.
     */
    public final Operand<? extends TType> gradient;

    /**
     * The data type of accumulated gradients. Needs to correspond to the type
     * of the accumulator.
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new AccumulatorApplyGradient(op), op, Arrays.asList("dtype"));
      int inputIndex = 0;
      handle = (Operand<TString>) op.input(inputIndex++);
      localStep = (Operand<TInt64>) op.input(inputIndex++);
      gradient = (Operand<? extends TType>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
