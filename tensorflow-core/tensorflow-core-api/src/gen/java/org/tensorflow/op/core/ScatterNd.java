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
 * Scatter `updates` into a new tensor according to `indices`.
 * <p>
 * Creates a new tensor by applying sparse `updates` to individual values or
 * slices within a tensor (initially zero for numeric, empty for string) of
 * the given `shape` according to indices.  This operator is the inverse of the
 * `tf.gather_nd` operator which extracts values or slices from a given tensor.
 * <p>
 * This operation is similar to tensor_scatter_add, except that the tensor is
 * zero-initialized. Calling `tf.scatter_nd(indices, values, shape)` is identical
 * to `tensor_scatter_add(tf.zeros(shape, values.dtype), indices, values)`
 * <p>
 * If `indices` contains duplicates, then their updates are accumulated (summed).
 * <p>
 * <b>WARNING</b>: The order in which updates are applied is nondeterministic, so the
 * output will be nondeterministic if `indices` contains duplicates -- because
 * of some numerical approximation issues, numbers summed in different order
 * may yield different results.
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
 * The simplest form of scatter is to insert individual elements in a tensor by
 * index. For example, say we want to insert 4 scattered elements in a rank-1
 * tensor with 8 elements.
 * <p>
 * <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
 * <img style="width:100%" src="https://www.tensorflow.org/images/ScatterNd1.png" alt>
 * </div>
 * <p>
 * In Python, this scatter operation would look like this:
 * <pre>{@code
 *     indices = tf.constant([[4], [3], [1], [7]])
 *     updates = tf.constant([9, 10, 11, 12])
 *     shape = tf.constant([8])
 *     scatter = tf.scatter_nd(indices, updates, shape)
 *     print(scatter)
 * }</pre>
 * The resulting tensor would look like this:
 * <p>
 *     [0, 11, 0, 10, 9, 0, 0, 12]
 * <p>
 * We can also, insert entire slices of a higher rank tensor all at once. For
 * example, if we wanted to insert two slices in the first dimension of a
 * rank-3 tensor with two matrices of new values.
 * <p>
 * <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
 * <img style="width:100%" src="https://www.tensorflow.org/images/ScatterNd2.png" alt>
 * </div>
 * <p>
 * In Python, this scatter operation would look like this:
 * <pre>{@code
 *     indices = tf.constant([[0], [2]])
 *     updates = tf.constant([[[5, 5, 5, 5], [6, 6, 6, 6],
 *                             [7, 7, 7, 7], [8, 8, 8, 8]],
 *                            [[5, 5, 5, 5], [6, 6, 6, 6],
 *                             [7, 7, 7, 7], [8, 8, 8, 8]]])
 *     shape = tf.constant([4, 4, 4])
 *     scatter = tf.scatter_nd(indices, updates, shape)
 *     print(scatter)
 * }</pre>
 * The resulting tensor would look like this:
 * <p>
 *     [[[5, 5, 5, 5], [6, 6, 6, 6], [7, 7, 7, 7], [8, 8, 8, 8]],
 *      [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]],
 *      [[5, 5, 5, 5], [6, 6, 6, 6], [7, 7, 7, 7], [8, 8, 8, 8]],
 *      [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]]
 * <p>
 * Note that on CPU, if an out of bound index is found, an error is returned.
 * On GPU, if an out of bound index is found, the index is ignored.
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator
public final class ScatterNd<U extends TType> extends RawOp implements Operand<U> {
  
  /**
   * Factory method to create a class wrapping a new ScatterNd operation.
   * 
   * @param scope current scope
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @param shape 1-D. The shape of the resulting tensor.
   * @return a new instance of ScatterNd
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TNumber> ScatterNd<U> create(Scope scope, Operand<T> indices, Operand<U> updates, Operand<T> shape) {
    OperationBuilder opBuilder = scope.env().opBuilder("ScatterNd", scope.makeOpName("ScatterNd"));
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new ScatterNd<U>(opBuilder.build());
  }
  
  /**
   * A new tensor with the given shape and updates applied according
   * to the indices.
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ScatterNd";
  
  private Output<U> output;
  
  private ScatterNd(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
