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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Broadcast an array for a compatible shape.
 * <p>
 * Broadcasting is the process of making arrays to have compatible shapes
 * for arithmetic operations. Two shapes are compatible if for each
 * dimension pair they are either equal or one of them is one. When trying
 * to broadcast a Tensor to a shape, it starts with the trailing dimensions,
 * and works its way forward.
 * <p>
 * For example,
 * <p>
 * >>> x = tf.constant([1, 2, 3])
 * >>> y = tf.broadcast_to(x, [3, 3])
 * >>> print(y)
 * tf.Tensor(
 *     [[1 2 3]
 *      [1 2 3]
 *      [1 2 3]], shape=(3, 3), dtype=int32)
 * <p>
 * In the above example, the input Tensor with the shape of `[1, 3]`
 * is broadcasted to output Tensor with shape of `[3, 3]`.
 * <p>
 * When doing broadcasted operations such as multiplying a tensor
 * by a scalar, broadcasting (usually) confers some time or space
 * benefit, as the broadcasted tensor is never materialized.
 * <p>
 * However, `broadcast_to` does not carry with it any such benefits.
 * The newly-created tensor takes the full memory of the broadcasted
 * shape. (In a graph context, `broadcast_to` might be fused to
 * subsequent operation and then be optimized away, however.)
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class BroadcastTo<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new BroadcastTo operation.
   * 
   * @param scope current scope
   * @param input A Tensor to broadcast.
   * @param shape An 1-D `int` Tensor. The shape of the desired output.
   * @return a new instance of BroadcastTo
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> BroadcastTo<T> create(Scope scope, Operand<T> input, Operand<U> shape) {
    OperationBuilder opBuilder = scope.env().opBuilder("BroadcastTo", scope.makeOpName("BroadcastTo"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new BroadcastTo<T>(opBuilder.build());
  }
  
  /**
   * A Tensor.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BroadcastTo";
  
  private Output<T> output;
  
  private BroadcastTo(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
