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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Interleave the values from the `data` tensors into a single tensor.
 * <p>
 * Builds a merged tensor such that
 * <pre>{@code
 *     merged[indices[m][i, ..., j], ...] = data[m][i, ..., j, ...]
 * }</pre>
 * For example, if each `indices[m]` is scalar or vector, we have
 * <pre>{@code
 *     # Scalar indices:
 *     merged[indices[m], ...] = data[m][...]
 * 
 *     # Vector indices:
 *     merged[indices[m][i], ...] = data[m][i, ...]
 * }</pre>
 * Each `data[i].shape` must start with the corresponding `indices[i].shape`,
 * and the rest of `data[i].shape` must be constant w.r.t. `i`.  That is, we
 * must have `data[i].shape = indices[i].shape + constant`.  In terms of this
 * `constant`, the output shape is
 * <p>
 *     merged.shape = [max(indices)] + constant
 * <p>
 * Values are merged in order, so if an index appears in both `indices[m][i]` and
 * `indices[n][j]` for `(m,i) < (n,j)` the slice `data[n][j]` will appear in the
 * merged result. If you do not need this guarantee, ParallelDynamicStitch might
 * perform better on some devices.
 * <p>
 * For example:
 * <pre>{@code
 *     indices[0] = 6
 *     indices[1] = [4, 1]
 *     indices[2] = [[5, 2], [0, 3]]
 *     data[0] = [61, 62]
 *     data[1] = [[41, 42], [11, 12]]
 *     data[2] = [[[51, 52], [21, 22]], [[1, 2], [31, 32]]]
 *     merged = [[1, 2], [11, 12], [21, 22], [31, 32], [41, 42],
 *               [51, 52], [61, 62]]
 * }</pre>
 * This method can be used to merge partitions created by `dynamic_partition`
 * as illustrated on the following example:
 * <pre>{@code
 *     # Apply function (increments x_i) on elements for which a certain condition
 *     # apply (x_i != -1 in this example).
 *     x=tf.constant([0.1, -1., 5.2, 4.3, -1., 7.4])
 *     condition_mask=tf.not_equal(x,tf.constant(-1.))
 *     partitioned_data = tf.dynamic_partition(
 *         x, tf.cast(condition_mask, tf.int32) , 2)
 *     partitioned_data[1] = partitioned_data[1] + 1.0
 *     condition_indices = tf.dynamic_partition(
 *         tf.range(tf.shape(x)[0]), tf.cast(condition_mask, tf.int32) , 2)
 *     x = tf.dynamic_stitch(condition_indices, partitioned_data)
 *     # Here x=[1.1, -1., 6.2, 5.3, -1, 8.4], the -1. values remain
 *     # unchanged.
 * }</pre>
 * <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
 * <img style="width:100%" src="https://www.tensorflow.org/images/DynamicStitch.png" alt>
 * </div>
 * 
 * @param <T> data type for {@code merged()} output
 */
@Operator
public final class DynamicStitch<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new DynamicStitch operation.
   * 
   * @param scope current scope
   * @param indices 
   * @param data 
   * @return a new instance of DynamicStitch
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> DynamicStitch<T> create(Scope scope, Iterable<Operand<TInt32>> indices, Iterable<Operand<T>> data) {
    OperationBuilder opBuilder = scope.env().opBuilder("DynamicStitch", scope.makeOpName("DynamicStitch"));
    opBuilder.addInputList(Operands.asOutputs(indices));
    opBuilder.addInputList(Operands.asOutputs(data));
    opBuilder = scope.apply(opBuilder);
    return new DynamicStitch<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> merged() {
    return merged;
  }
  
  @Override
  public Output<T> asOutput() {
    return merged;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DynamicStitch";
  
  private Output<T> merged;
  
  private DynamicStitch(Operation operation) {
    super(operation);
    int outputIdx = 0;
    merged = operation.output(outputIdx++);
  }
}
