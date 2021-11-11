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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Computes the gradient for the sqrt of {@code x} wrt its input.
 * Specifically, {@code grad = dy * 0.5 / y}, where {@code y = sqrt(x)}, and {@code dy}
 * is the corresponding input gradient.
 *
 * @param <T> data type for {@code z} output
 */
@OpMetadata(
    opType = SqrtGrad.OP_NAME,
    inputsClass = SqrtGrad.Inputs.class
)
public final class SqrtGrad<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SqrtGrad";

  private Output<T> z;

  public SqrtGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SqrtGrad operation.
   *
   * @param scope current scope
   * @param y The y value
   * @param dy The dy value
   * @param <T> data type for {@code SqrtGrad} output and operands
   * @return a new instance of SqrtGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SqrtGrad<T> create(Scope scope, Operand<T> y, Operand<T> dy) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SqrtGrad");
    opBuilder.addInput(y.asOutput());
    opBuilder.addInput(dy.asOutput());
    return new SqrtGrad<>(opBuilder.build());
  }

  /**
   * Gets z.
   *
   * @return z.
   */
  public Output<T> z() {
    return z;
  }

  @Override
  public Output<T> asOutput() {
    return z;
  }

  @OpInputsMetadata(
      outputsClass = SqrtGrad.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SqrtGrad<T>> {
    /**
     * The y input
     */
    public final Operand<T> y;

    /**
     * The dy input
     */
    public final Operand<T> dy;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SqrtGrad<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      y = (Operand<T>) op.input(inputIndex++);
      dy = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
