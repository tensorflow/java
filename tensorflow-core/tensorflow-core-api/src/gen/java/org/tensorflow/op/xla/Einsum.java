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

package org.tensorflow.op.xla;

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
 * An op which supports basic einsum op with 2 inputs and 1 output.
 * This op has better TPU performance since it doesn't have explicitly reshape and
 * transpose operations as tf.einsum does.
 *
 * @param <T> data type for {@code product} output
 */
@OpMetadata(
    opType = Einsum.OP_NAME,
    inputsClass = Einsum.Inputs.class
)
@Operator(
    group = "xla"
)
public final class Einsum<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaEinsum";

  private Output<T> product;

  public Einsum(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    product = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaEinsum operation.
   *
   * @param scope current scope
   * @param a The a value
   * @param b The b value
   * @param equation The value of the equation attribute
   * @param <T> data type for {@code XlaEinsum} output and operands
   * @return a new instance of Einsum
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Einsum<T> create(Scope scope, Operand<T> a, Operand<T> b,
      String equation) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Einsum");
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder.setAttr("equation", equation);
    return new Einsum<>(opBuilder.build());
  }

  /**
   * Gets product.
   *
   * @return product.
   */
  public Output<T> product() {
    return product;
  }

  @Override
  public Output<T> asOutput() {
    return product;
  }

  @OpInputsMetadata(
      outputsClass = Einsum.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Einsum<T>> {
    /**
     * The a input
     */
    public final Operand<T> a;

    /**
     * The b input
     */
    public final Operand<T> b;

    /**
     * The equation attribute
     */
    public final String equation;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Einsum<>(op), op, Arrays.asList("equation", "T"));
      int inputIndex = 0;
      a = (Operand<T>) op.input(inputIndex++);
      b = (Operand<T>) op.input(inputIndex++);
      equation = op.attributes().getAttrString("equation");
      T = op.attributes().getAttrType("T");
    }
  }
}
