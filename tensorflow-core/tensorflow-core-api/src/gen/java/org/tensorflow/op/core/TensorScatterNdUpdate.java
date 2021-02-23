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
 * Scatter `updates` into an existing tensor according to `indices`.
 * <p>
 * This operation creates a new tensor by applying sparse `updates` to the passed
 * in `tensor`.
 * This operation is very similar to `tf.scatter_nd`, except that the updates are
 * scattered onto an existing tensor (as opposed to a zero-tensor). If the memory
 * for the existing tensor cannot be re-used, a copy is made and updated.
 * <p>
 * If `indices` contains duplicates, then we pick the last update for the index.
 * <p>
 * If an out of bound index is found on CPU, an error is returned.
 * <p>
 * <b>WARNING</b>: There are some GPU specific semantics for this operation.
 * - If an out of bound index is found, the index is ignored.
 * - The order in which updates are applied is nondeterministic, so the output
 * will be nondeterministic if `indices` contains duplicates.
 * <p>
 * `indices` is an integer tensor containing indices into a new tensor of shape
 * `shape`.
 * <ul>
 * <li>
 * `indices` must have at least 2 axes: `(num_updates, index_depth)`.
 * </li>
 * <li>
 * The last axis of `indices` is how deep to index into `tensor` so  this index
 *   depth must be less than the rank of `tensor`: `indices.shape[-1] <= tensor.ndim`
 * </li>
 * </ul>
 * if `indices.shape[-1] = tensor.rank` this Op indexes and updates scalar elements.
 * if `indices.shape[-1] < tensor.rank` it indexes and updates slices of the input
 * `tensor`.
 * <p>
 * Each `update` has a rank of `tensor.rank - indices.shape[-1]`.
 * The overall shape of `updates` is:
 * <pre>{@code
 * indices.shape[:-1] + tensor.shape[indices.shape[-1]:]
 * }</pre>
 * For usage examples see the python [tf.tensor_scatter_nd_update](
 * https://www.tensorflow.org/api_docs/python/tf/tensor_scatter_nd_update) function
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class TensorScatterNdUpdate<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new TensorScatterNdUpdate operation.
   * 
   * @param scope current scope
   * @param tensor Tensor to copy/update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @return a new instance of TensorScatterNdUpdate
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TensorScatterNdUpdate<T> create(Scope scope, Operand<T> tensor, Operand<? extends TNumber> indices, Operand<T> updates) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorScatterUpdate", scope.makeOpName("TensorScatterNdUpdate"));
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorScatterNdUpdate<T>(opBuilder.build());
  }
  
  /**
   * A new tensor with the given shape and updates applied according
   * to the indices.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorScatterUpdate";
  
  private Output<T> output;
  
  private TensorScatterNdUpdate(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
