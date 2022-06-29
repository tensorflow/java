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
import org.tensorflow.types.family.TNumber;

/**
 * Computes the gradient of {@code igamma(a, x)} wrt {@code a}.
 *
 * @param <T> data type for {@code z} output
 */
@OpMetadata(
    opType = IgammaGradA.OP_NAME,
    inputsClass = IgammaGradA.Inputs.class
)
public final class IgammaGradA<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IgammaGradA";

  private Output<T> z;

  public IgammaGradA(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IgammaGradA operation.
   *
   * @param scope current scope
   * @param a The a value
   * @param x The x value
   * @param <T> data type for {@code IgammaGradA} output and operands
   * @return a new instance of IgammaGradA
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> IgammaGradA<T> create(Scope scope, Operand<T> a, Operand<T> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IgammaGradA");
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(x.asOutput());
    return new IgammaGradA<>(opBuilder.build());
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
      outputsClass = IgammaGradA.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<IgammaGradA<T>> {
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
      super(new IgammaGradA<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      a = (Operand<T>) op.input(inputIndex++);
      x = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
