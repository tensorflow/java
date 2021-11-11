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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Extracts the average gradient in the given ConditionalAccumulator.
 * The op blocks until sufficient (i.e., more than num_required)
 * gradients have been accumulated.  If the accumulator has already
 * aggregated more than num_required gradients, it returns the average of
 * the accumulated gradients.  Also automatically increments the recorded
 * global_step in the accumulator by 1, and resets the aggregate to 0.
 *
 * @param <T> data type for {@code average} output
 */
@OpMetadata(
    opType = AccumulatorTakeGradient.OP_NAME,
    inputsClass = AccumulatorTakeGradient.Inputs.class
)
@Operator(
    group = "train"
)
public final class AccumulatorTakeGradient<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AccumulatorTakeGradient";

  private Output<T> average;

  public AccumulatorTakeGradient(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    average = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AccumulatorTakeGradient operation.
   *
   * @param scope current scope
   * @param handle The handle to an accumulator.
   * @param numRequired Number of gradients required before we return an aggregate.
   * @param dtype The data type of accumulated gradients. Needs to correspond to the type
   * of the accumulator.
   * @param <T> data type for {@code AccumulatorTakeGradient} output and operands
   * @return a new instance of AccumulatorTakeGradient
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> AccumulatorTakeGradient<T> create(Scope scope,
      Operand<TString> handle, Operand<TInt32> numRequired, Class<T> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AccumulatorTakeGradient");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(numRequired.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new AccumulatorTakeGradient<>(opBuilder.build());
  }

  /**
   * Gets average.
   * The average of the accumulated gradients.
   * @return average.
   */
  public Output<T> average() {
    return average;
  }

  @Override
  public Output<T> asOutput() {
    return average;
  }

  @OpInputsMetadata(
      outputsClass = AccumulatorTakeGradient.class
  )
  public static class Inputs extends RawOpInputs<AccumulatorTakeGradient<?>> {
    /**
     * The handle to an accumulator.
     */
    public final Operand<TString> handle;

    /**
     * Number of gradients required before we return an aggregate.
     */
    public final Operand<TInt32> numRequired;

    /**
     * The data type of accumulated gradients. Needs to correspond to the type
     * of the accumulator.
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new AccumulatorTakeGradient<>(op), op, Arrays.asList("dtype"));
      int inputIndex = 0;
      handle = (Operand<TString>) op.input(inputIndex++);
      numRequired = (Operand<TInt32>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
