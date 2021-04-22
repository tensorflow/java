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
 * Interleave the values from the {@code data} tensors into a single tensor.
 * Builds a merged tensor such that
 * <pre>
 *     merged[indices[m][i, ..., j], ...] = data[m][i, ..., j, ...]
 * </pre>
 * <p>For example, if each {@code indices[m]} is scalar or vector, we have
 * <pre>
 *     # Scalar indices:
 *     merged[indices[m], ...] = data[m][...]
 *
 *     # Vector indices:
 *     merged[indices[m][i], ...] = data[m][i, ...]
 * </pre>
 * <p>Each {@code data[i].shape} must start with the corresponding {@code indices[i].shape},
 * and the rest of {@code data[i].shape} must be constant w.r.t. {@code i}.  That is, we
 * must have {@code data[i].shape = indices[i].shape + constant}.  In terms of this
 * {@code constant}, the output shape is
 * <pre>
 * merged.shape = [max(indices)] + constant
 * </pre>
 * <p>Values may be merged in parallel, so if an index appears in both {@code indices[m][i]}
 * and {@code indices[n][j]}, the result may be invalid. This differs from the normal
 * DynamicStitch operator that defines the behavior in that case.
 * <p>For example:
 * <pre>
 *     indices[0] = 6
 *     indices[1] = [4, 1]
 *     indices[2] = [[5, 2], [0, 3]]
 *     data[0] = [61, 62]
 *     data[1] = [[41, 42], [11, 12]]
 *     data[2] = [[[51, 52], [21, 22]], [[1, 2], [31, 32]]]
 *     merged = [[1, 2], [11, 12], [21, 22], [31, 32], [41, 42],
 *               [51, 52], [61, 62]]
 * </pre>
 * <p>This method can be used to merge partitions created by {@code dynamic_partition}
 * as illustrated on the following example:
 * <pre>
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
 * </pre>
 * <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
 * <img style="width:100%" src="https://www.tensorflow.org/images/DynamicStitch.png" alt>
 * </div>
 *
 * @param <T> data type for {@code merged} output
 */
@Operator
public final class ParallelDynamicStitch<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ParallelDynamicStitch";

  private Output<T> merged;

  private ParallelDynamicStitch(Operation operation) {
    super(operation);
    int outputIdx = 0;
    merged = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ParallelDynamicStitch operation.
   *
   * @param scope current scope
   * @param indices the indices value
   * @param data the data value
   * @param <T> data type for {@code ParallelDynamicStitch} output and operands
   * @return a new instance of ParallelDynamicStitch
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ParallelDynamicStitch<T> create(Scope scope,
      Iterable<Operand<TInt32>> indices, Iterable<Operand<T>> data) {
    OperationBuilder opBuilder = scope.env().opBuilder("ParallelDynamicStitch", scope.makeOpName("ParallelDynamicStitch"));
    opBuilder.addInputList(Operands.asOutputs(indices));
    opBuilder.addInputList(Operands.asOutputs(data));
    opBuilder = scope.apply(opBuilder);
    return new ParallelDynamicStitch<>(opBuilder.build());
  }

  /**
   * Gets merged.
   *
   * @return merged.
   */
  public Output<T> merged() {
    return merged;
  }

  @Override
  public Output<T> asOutput() {
    return merged;
  }
}
