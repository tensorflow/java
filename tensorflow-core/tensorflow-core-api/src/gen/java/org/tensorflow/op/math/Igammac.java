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
 * Compute the upper regularized incomplete Gamma function {@code Q(a, x)}.
 * The upper regularized incomplete Gamma function is defined as:
 * <p>\(Q(a, x) = Gamma(a, x) / Gamma(a) = 1 - P(a, x)\)
 * <p>where
 * <p>\(Gamma(a, x) = \int_{x}^{\infty} t^{a-1} exp(-t) dt\)
 * <p>is the upper incomplete Gamma function.
 * <p>Note, above {@code P(a, x)} ({@code Igamma}) is the lower regularized complete
 * Gamma function.
 *
 * @param <T> data type for {@code z} output
 */
@OpMetadata(
    opType = Igammac.OP_NAME,
    inputsClass = Igammac.Inputs.class
)
@Operator(
    group = "math"
)
public final class Igammac<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Igammac";

  private Output<T> z;

  public Igammac(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Igammac operation.
   *
   * @param scope current scope
   * @param a The a value
   * @param x The x value
   * @param <T> data type for {@code Igammac} output and operands
   * @return a new instance of Igammac
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Igammac<T> create(Scope scope, Operand<T> a, Operand<T> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Igammac");
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(x.asOutput());
    return new Igammac<>(opBuilder.build());
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
      outputsClass = Igammac.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<Igammac<T>> {
    /**
     * The a input
     */
    public final Operand<T> a;

    /**
     * The x input
     */
    public final Operand<T> x;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Igammac<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      a = (Operand<T>) op.input(inputIndex++);
      x = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
