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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Return the reduction indices for computing gradients of s0 op s1 with broadcast.
 * This is typically used by gradient computations for a broadcasting operation.
 *
 * @param <T> data type for {@code r0} output
 */
@OpMetadata(
    opType = BroadcastGradientArgs.OP_NAME,
    inputsClass = BroadcastGradientArgs.Inputs.class
)
public final class BroadcastGradientArgs<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BroadcastGradientArgs";

  private Output<T> r0;

  private Output<T> r1;

  public BroadcastGradientArgs(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    r0 = operation.output(outputIdx++);
    r1 = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BroadcastGradientArgs operation.
   *
   * @param scope current scope
   * @param s0 The s0 value
   * @param s1 The s1 value
   * @param <T> data type for {@code BroadcastGradientArgs} output and operands
   * @return a new instance of BroadcastGradientArgs
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> BroadcastGradientArgs<T> create(Scope scope, Operand<T> s0,
      Operand<T> s1) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BroadcastGradientArgs");
    opBuilder.addInput(s0.asOutput());
    opBuilder.addInput(s1.asOutput());
    return new BroadcastGradientArgs<>(opBuilder.build());
  }

  /**
   * Gets r0.
   *
   * @return r0.
   */
  public Output<T> r0() {
    return r0;
  }

  /**
   * Gets r1.
   *
   * @return r1.
   */
  public Output<T> r1() {
    return r1;
  }

  @OpInputsMetadata(
      outputsClass = BroadcastGradientArgs.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<BroadcastGradientArgs<T>> {
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
      super(new BroadcastGradientArgs<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      s0 = (Operand<T>) op.input(inputIndex++);
      s1 = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
