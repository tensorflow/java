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
 * Applies sparse addition to `input` using individual values or slices
 * <p>
 * from `updates` according to indices `indices`.  The updates are non-aliasing:
 * `input` is only modified in-place if no other operations will use it.
 * Otherwise, a copy of `input` is made.  This operation has a gradient with
 * respect to both `input` and `updates`.
 * <p>
 * `input` is a `Tensor` with rank `P` and `indices` is a `Tensor` of rank `Q`.
 * <p>
 * `indices` must be integer tensor, containing indices into `input`.
 * It must be shape \\([d_0, ..., d_{Q-2}, K]\\) where `0 < K <= P`.
 * <p>
 * The innermost dimension of `indices` (with length `K`) corresponds to
 * indices into elements (if `K = P`) or `(P-K)`-dimensional slices
 * (if `K < P`) along the `K`th dimension of `input`.
 * <p>
 * `updates` is `Tensor` of rank `Q-1+P-K` with shape:
 * <p>
 * $$[d_0, ..., d_{Q-2}, input.shape[K], ..., input.shape[P-1]].$$
 * <p>
 * For example, say we want to add 4 scattered elements to a rank-1 tensor to 8
 * elements. In Python, that addition would look like this:
 * <p>
 *     input = tf.constant([1, 2, 3, 4, 5, 6, 7, 8])
 *     indices = tf.constant([[4], [3], [1], [7]])
 *     updates = tf.constant([9, 10, 11, 12])
 *     output = tf.scatter_nd_non_aliasing_add(input, indices, updates)
 *     with tf.Session() as sess:
 *       print(sess.run(output))
 * <p>
 * The resulting value `output` would look like this:
 * <p>
 *     [1, 13, 3, 14, 14, 6, 7, 20]
 * <p>
 * See `tf.scatter_nd` for more details about how to make updates to slices.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class ScatterNdNonAliasingAdd<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new ScatterNdNonAliasingAdd operation.
   * 
   * @param scope current scope
   * @param input A Tensor.
   * @param indices A Tensor. Must be one of the following types: `int32`, `int64`.
   * A tensor of indices into `input`.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated values
   * to add to `input`.
   * @return a new instance of ScatterNdNonAliasingAdd
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> ScatterNdNonAliasingAdd<T> create(Scope scope, Operand<T> input, Operand<U> indices, Operand<T> updates) {
    OperationBuilder opBuilder = scope.env().opBuilder("ScatterNdNonAliasingAdd", scope.makeOpName("ScatterNdNonAliasingAdd"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new ScatterNdNonAliasingAdd<T>(opBuilder.build());
  }
  
  /**
   * A `Tensor` with the same shape as `input`, containing values of `input`
   * updated with `updates`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ScatterNdNonAliasingAdd";
  
  private Output<T> output;
  
  private ScatterNdNonAliasingAdd(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
