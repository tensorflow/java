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

package org.tensorflow.op.math;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Returns the element-wise sum of a list of tensors.
 * {@code tf.accumulate_n_v2} performs the same operation as {@code tf.add_n}, but does not
 * wait for all of its inputs to be ready before beginning to sum. This can
 * save memory if inputs are ready at different times, since minimum temporary
 * storage is proportional to the output size rather than the inputs size.
 * <p>Unlike the original {@code accumulate_n}, {@code accumulate_n_v2} is differentiable.
 * <p>Returns a {@code Tensor} of same shape and type as the elements of {@code inputs}.
 *
 * @param <T> data type for {@code sum} output
 */
@OpMetadata(
    opType = AccumulateN.OP_NAME,
    inputsClass = AccumulateN.Inputs.class
)
@Operator(
    group = "math"
)
public final class AccumulateN<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AccumulateNV2";

  private Output<T> sum;

  public AccumulateN(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sum = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AccumulateNV2 operation.
   *
   * @param scope current scope
   * @param inputs A list of {@code Tensor} objects, each with same shape and type.
   * @param shape Shape of elements of {@code inputs}.
   * @param <T> data type for {@code AccumulateNV2} output and operands
   * @return a new instance of AccumulateN
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> AccumulateN<T> create(Scope scope, Iterable<Operand<T>> inputs,
      Shape shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AccumulateN");
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder.setAttr("shape", shape);
    return new AccumulateN<>(opBuilder.build());
  }

  /**
   * Gets sum.
   *
   * @return sum.
   */
  public Output<T> sum() {
    return sum;
  }

  @Override
  public Output<T> asOutput() {
    return sum;
  }

  @OpInputsMetadata(
      outputsClass = AccumulateN.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<AccumulateN<T>> {
    /**
     * A list of {@code Tensor} objects, each with same shape and type.
     */
    public final Iterable<Operand<T>> inputs;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Shape of elements of `inputs`.
     */
    public final Shape shape;

    public Inputs(GraphOperation op) {
      super(new AccumulateN<>(op), op, Arrays.asList("T", "shape"));
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      T = op.attributes().getAttrType("T");
      shape = op.attributes().getAttrShape("shape");
    }
  }
}
