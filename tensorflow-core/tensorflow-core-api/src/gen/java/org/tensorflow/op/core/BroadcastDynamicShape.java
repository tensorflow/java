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

package org.tensorflow.op.core;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Return the shape of s0 op s1 with broadcast.
 * Given {@code s0} and {@code s1}, tensors that represent shapes, compute {@code r0}, the
 * broadcasted shape. {@code s0}, {@code s1} and {@code r0} are all integer vectors.
 *
 * @param <T> data type for {@code r0} output
 */
@OpMetadata(
    opType = BroadcastDynamicShape.OP_NAME,
    inputsClass = BroadcastDynamicShape.Inputs.class
)
@Operator
public final class BroadcastDynamicShape<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BroadcastArgs";

  private Output<T> r0;

  public BroadcastDynamicShape(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    r0 = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BroadcastArgs operation.
   *
   * @param scope current scope
   * @param s0 The s0 value
   * @param s1 The s1 value
   * @param <T> data type for {@code BroadcastArgs} output and operands
   * @return a new instance of BroadcastDynamicShape
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> BroadcastDynamicShape<T> create(Scope scope, Operand<T> s0,
      Operand<T> s1) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BroadcastDynamicShape");
    opBuilder.addInput(s0.asOutput());
    opBuilder.addInput(s1.asOutput());
    return new BroadcastDynamicShape<>(opBuilder.build());
  }

  /**
   * Gets r0.
   *
   * @return r0.
   */
  public Output<T> r0() {
    return r0;
  }

  @Override
  public Output<T> asOutput() {
    return r0;
  }

  @OpInputsMetadata(
      outputsClass = BroadcastDynamicShape.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<BroadcastDynamicShape<T>> {
    /**
     * The s0 input
     */
    public final Operand<T> s0;

    /**
     * The s1 input
     */
    public final Operand<T> s1;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new BroadcastDynamicShape<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      s0 = (Operand<T>) op.input(inputIndex++);
      s1 = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
