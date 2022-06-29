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
import org.tensorflow.types.family.TType;

/**
 * Broadcast an array for a compatible shape.
 * Broadcasting is the process of making arrays to have compatible shapes
 * for arithmetic operations. Two shapes are compatible if for each
 * dimension pair they are either equal or one of them is one. When trying
 * to broadcast a Tensor to a shape, it starts with the trailing dimensions,
 * and works its way forward.
 * <p>For example,
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>x = tf.constant([1, 2, 3])
 * y = tf.broadcast_to(x, [3, 3])
 * print(y)
 * tf.Tensor(
 * [[1 2 3]
 * [1 2 3]
 * [1 2 3]], shape=(3, 3), dtype=int32)
 * </blockquote>
 * </blockquote>
 * </blockquote>
 * <p>In the above example, the input Tensor with the shape of {@code [1, 3]}
 * is broadcasted to output Tensor with shape of {@code [3, 3]}.
 * <p>When doing broadcasted operations such as multiplying a tensor
 * by a scalar, broadcasting (usually) confers some time or space
 * benefit, as the broadcasted tensor is never materialized.
 * <p>However, {@code broadcast_to} does not carry with it any such benefits.
 * The newly-created tensor takes the full memory of the broadcasted
 * shape. (In a graph context, {@code broadcast_to} might be fused to
 * subsequent operation and then be optimized away, however.)
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = BroadcastTo.OP_NAME,
    inputsClass = BroadcastTo.Inputs.class
)
@Operator
public final class BroadcastTo<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BroadcastTo";

  private Output<T> output;

  public BroadcastTo(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BroadcastTo operation.
   *
   * @param scope current scope
   * @param input A Tensor to broadcast.
   * @param shape An 1-D {@code int} Tensor. The shape of the desired output.
   * @param <T> data type for {@code BroadcastTo} output and operands
   * @return a new instance of BroadcastTo
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> BroadcastTo<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BroadcastTo");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(shape.asOutput());
    return new BroadcastTo<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A Tensor.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = BroadcastTo.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<BroadcastTo<T>> {
    /**
     * A Tensor to broadcast.
     */
    public final Operand<T> input;

    /**
     * An 1-D {@code int} Tensor. The shape of the desired output.
     */
    public final Operand<? extends TNumber> shape;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tidx attribute
     */
    public final DataType Tidx;

    public Inputs(GraphOperation op) {
      super(new BroadcastTo<>(op), op, Arrays.asList("T", "Tidx"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tidx = op.attributes().getAttrType("Tidx");
    }
  }
}
