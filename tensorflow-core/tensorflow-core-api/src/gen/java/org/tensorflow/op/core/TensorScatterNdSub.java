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
 * Subtracts sparse `updates` from an existing tensor according to `indices`.
 * <p>
 * This operation creates a new tensor by subtracting sparse `updates` from the
 * passed in `tensor`.
 * This operation is very similar to `tf.scatter_nd_sub`, except that the updates
 * are subtracted from an existing tensor (as opposed to a variable). If the memory
 * for the existing tensor cannot be re-used, a copy is made and updated.
 * <p>
 * `indices` is an integer tensor containing indices into a new tensor of shape
 * `shape`.  The last dimension of `indices` can be at most the rank of `shape`:
 * <p>
 *     indices.shape[-1] <= shape.rank
 * <p>
 * The last dimension of `indices` corresponds to indices into elements
 * (if `indices.shape[-1] = shape.rank`) or slices
 * (if `indices.shape[-1] < shape.rank`) along dimension `indices.shape[-1]` of
 * `shape`.  `updates` is a tensor with shape
 * <p>
 *     indices.shape[:-1] + shape[indices.shape[-1]:]
 * <p>
 * The simplest form of tensor_scatter_sub is to subtract individual elements
 * from a tensor by index. For example, say we want to insert 4 scattered elements
 * in a rank-1 tensor with 8 elements.
 * <p>
 * In Python, this scatter subtract operation would look like this:
 * <pre>{@code
 *     indices = tf.constant([[4], [3], [1], [7]])
 *     updates = tf.constant([9, 10, 11, 12])
 *     tensor = tf.ones([8], dtype=tf.int32)
 *     updated = tf.tensor_scatter_nd_sub(tensor, indices, updates)
 *     print(updated)
 * }</pre>
 * The resulting tensor would look like this:
 * <p>
 *     [1, -10, 1, -9, -8, 1, 1, -11]
 * <p>
 * We can also, insert entire slices of a higher rank tensor all at once. For
 * example, if we wanted to insert two slices in the first dimension of a
 * rank-3 tensor with two matrices of new values.
 * <p>
 * In Python, this scatter add operation would look like this:
 * <pre>{@code
 *     indices = tf.constant([[0], [2]])
 *     updates = tf.constant([[[5, 5, 5, 5], [6, 6, 6, 6],
 *                             [7, 7, 7, 7], [8, 8, 8, 8]],
 *                            [[5, 5, 5, 5], [6, 6, 6, 6],
 *                             [7, 7, 7, 7], [8, 8, 8, 8]]])
 *     tensor = tf.ones([4, 4, 4],dtype=tf.int32)
 *     updated = tf.tensor_scatter_nd_sub(tensor, indices, updates)
 *     print(updated)
 * }</pre>
 * The resulting tensor would look like this:
 * <p>
 *     [[[-4, -4, -4, -4], [-5, -5, -5, -5], [-6, -6, -6, -6], [-7, -7, -7, -7]],
 *      [[1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]],
 *      [[-4, -4, -4, -4], [-5, -5, -5, -5], [-6, -6, -6, -6], [-7, -7, -7, -7]],
 *      [[1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]]]
 * <p>
 * Note that on CPU, if an out of bound index is found, an error is returned.
 * On GPU, if an out of bound index is found, the index is ignored.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class TensorScatterNdSub<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new TensorScatterNdSub operation.
   * 
   * @param scope current scope
   * @param tensor Tensor to copy/update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @return a new instance of TensorScatterNdSub
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> TensorScatterNdSub<T> create(Scope scope, Operand<T> tensor, Operand<U> indices, Operand<T> updates) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorScatterSub", scope.makeOpName("TensorScatterNdSub"));
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorScatterNdSub<T>(opBuilder.build());
  }
  
  /**
   * A new tensor copied from tensor and updates subtracted according to the indices.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorScatterSub";
  
  private Output<T> output;
  
  private TensorScatterNdSub(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
