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
import org.tensorflow.types.family.TNumber;

/**
 * Compute the regularized incomplete beta integral \(I_x(a, b)\).
 * The regularized incomplete beta integral is defined as:
 * <p>\(I_x(a, b) = \frac{B(x; a, b)}{B(a, b)}\)
 * <p>where
 * <p>\(B(x; a, b) = \int_0^x t^{a-1} (1 - t)^{b-1} dt\)
 * <p>is the incomplete beta function and \(B(a, b)\) is the <em>complete</em>
 * beta function.
 *
 * @param <T> data type for {@code z} output
 */
@OpMetadata(
    opType = Betainc.OP_NAME,
    inputsClass = Betainc.Inputs.class
)
@Operator(
    group = "math"
)
public final class Betainc<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Betainc";

  private Output<T> z;

  public Betainc(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Betainc operation.
   *
   * @param scope current scope
   * @param a The a value
   * @param b The b value
   * @param x The x value
   * @param <T> data type for {@code Betainc} output and operands
   * @return a new instance of Betainc
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Betainc<T> create(Scope scope, Operand<T> a, Operand<T> b,
      Operand<T> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Betainc");
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder.addInput(x.asOutput());
    return new Betainc<>(opBuilder.build());
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
      outputsClass = Betainc.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<Betainc<T>> {
    /**
     * The a input
     */
    public final Operand<T> a;

    /**
     * The b input
     */
    public final Operand<T> b;

    /**
     * The x input
     */
    public final Operand<T> x;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Betainc<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      a = (Operand<T>) op.input(inputIndex++);
      b = (Operand<T>) op.input(inputIndex++);
      x = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
