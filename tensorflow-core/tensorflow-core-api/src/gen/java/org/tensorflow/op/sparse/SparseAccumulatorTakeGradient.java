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

package org.tensorflow.op.sparse;

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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Extracts the average sparse gradient in a SparseConditionalAccumulator.
 * The op will blocks until sufficient (i.e., more than num_required)
 * gradients have been accumulated. If the accumulator has already
 * aggregated more than num_required gradients, it will return its
 * average of the accumulated gradients.  Also automatically increments
 * the recorded global_step in the accumulator by 1, and resets the
 * aggregate to 0.
 *
 * @param <T> data type for {@code values} output
 */
@OpMetadata(
    opType = SparseAccumulatorTakeGradient.OP_NAME,
    inputsClass = SparseAccumulatorTakeGradient.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseAccumulatorTakeGradient<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseAccumulatorTakeGradient";

  private Output<TInt64> indices;

  private Output<T> values;

  private Output<TInt64> shape;

  public SparseAccumulatorTakeGradient(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    indices = operation.output(outputIdx++);
    values = operation.output(outputIdx++);
    shape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseAccumulatorTakeGradient operation.
   *
   * @param scope current scope
   * @param handle The handle to a SparseConditionalAccumulator.
   * @param numRequired Number of gradients required before we return an aggregate.
   * @param dtype The data type of accumulated gradients. Needs to correspond to the type
   * of the accumulator.
   * @param <T> data type for {@code SparseAccumulatorTakeGradient} output and operands
   * @return a new instance of SparseAccumulatorTakeGradient
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseAccumulatorTakeGradient<T> create(Scope scope,
      Operand<TString> handle, Operand<TInt32> numRequired, Class<T> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseAccumulatorTakeGradient");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(numRequired.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new SparseAccumulatorTakeGradient<>(opBuilder.build());
  }

  /**
   * Gets indices.
   * Indices of the average of the accumulated sparse gradients.
   * @return indices.
   */
  public Output<TInt64> indices() {
    return indices;
  }

  /**
   * Gets values.
   * Values of the average of the accumulated sparse gradients.
   * @return values.
   */
  public Output<T> values() {
    return values;
  }

  /**
   * Gets shape.
   * Shape of the average of the accumulated sparse gradients.
   * @return shape.
   */
  public Output<TInt64> shape() {
    return shape;
  }

  @OpInputsMetadata(
      outputsClass = SparseAccumulatorTakeGradient.class
  )
  public static class Inputs extends RawOpInputs<SparseAccumulatorTakeGradient<?>> {
    /**
     * The handle to a SparseConditionalAccumulator.
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
      super(new SparseAccumulatorTakeGradient<>(op), op, Arrays.asList("dtype"));
      int inputIndex = 0;
      handle = (Operand<TString>) op.input(inputIndex++);
      numRequired = (Operand<TInt32>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
