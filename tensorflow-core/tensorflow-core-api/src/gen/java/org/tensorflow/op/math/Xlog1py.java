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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Returns 0 if x == 0, and x * log1p(y) otherwise, elementwise.
 *
 * @param <T> data type for {@code z} output
 */
@OpMetadata(
    opType = Xlog1py.OP_NAME,
    inputsClass = Xlog1py.Inputs.class
)
@Operator(
    group = "math"
)
public final class Xlog1py<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Xlog1py";

  private Output<T> z;

  public Xlog1py(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Xlog1py operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param y The y value
   * @param <T> data type for {@code Xlog1py} output and operands
   * @return a new instance of Xlog1py
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Xlog1py<T> create(Scope scope, Operand<T> x, Operand<T> y) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Xlog1py");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    return new Xlog1py<>(opBuilder.build());
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
      outputsClass = Xlog1py.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Xlog1py<T>> {
    /**
     * The x input
     */
    public final Operand<T> x;

    /**
     * The y input
     */
    public final Operand<T> y;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Xlog1py<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      y = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
