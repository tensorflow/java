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
 * Subtracts sparse {@code updates} from an existing tensor according to {@code indices}.
 * This operation creates a new tensor by subtracting sparse {@code updates} from the
 * passed in {@code tensor}.
 * This operation is very similar to {@code tf.scatter_nd_sub}, except that the updates
 * are subtracted from an existing tensor (as opposed to a variable). If the memory
 * for the existing tensor cannot be re-used, a copy is made and updated.
 * <p>{@code indices} is an integer tensor containing indices into a new tensor of shape
 * {@code shape}.  The last dimension of {@code indices} can be at most the rank of {@code shape}:
 * <pre>
 * indices.shape[-1] &lt;= shape.rank
 * </pre>
 * <p>The last dimension of {@code indices} corresponds to indices into elements
 * (if {@code indices.shape[-1] = shape.rank}) or slices
 * (if {@code indices.shape[-1] < shape.rank}) along dimension {@code indices.shape[-1]} of
 * {@code shape}.  {@code updates} is a tensor with shape
 * <pre>
 * indices.shape[:-1] + shape[indices.shape[-1]:]
 * </pre>
 * <p>The simplest form of tensor_scatter_sub is to subtract individual elements
 * from a tensor by index. For example, say we want to insert 4 scattered elements
 * in a rank-1 tensor with 8 elements.
 * <p>In Python, this scatter subtract operation would look like this:
 * <pre>
 *     indices = tf.constant([[4], [3], [1], [7]])
 *     updates = tf.constant([9, 10, 11, 12])
 *     tensor = tf.ones([8], dtype=tf.int32)
 *     updated = tf.tensor_scatter_nd_sub(tensor, indices, updates)
 *     print(updated)
 * </pre>
 * <p>The resulting tensor would look like this:
 * <pre>
 * [1, -10, 1, -9, -8, 1, 1, -11]
 * </pre>
 * <p>We can also, insert entire slices of a higher rank tensor all at once. For
 * example, if we wanted to insert two slices in the first dimension of a
 * rank-3 tensor with two matrices of new values.
 * <p>In Python, this scatter add operation would look like this:
 * <pre>
 *     indices = tf.constant([[0], [2]])
 *     updates = tf.constant([[[5, 5, 5, 5], [6, 6, 6, 6],
 *                             [7, 7, 7, 7], [8, 8, 8, 8]],
 *                            [[5, 5, 5, 5], [6, 6, 6, 6],
 *                             [7, 7, 7, 7], [8, 8, 8, 8]]])
 *     tensor = tf.ones([4, 4, 4],dtype=tf.int32)
 *     updated = tf.tensor_scatter_nd_sub(tensor, indices, updates)
 *     print(updated)
 * </pre>
 * <p>The resulting tensor would look like this:
 * <pre>
 * [[[-4, -4, -4, -4], [-5, -5, -5, -5], [-6, -6, -6, -6], [-7, -7, -7, -7]],
 *  [[1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]],
 *  [[-4, -4, -4, -4], [-5, -5, -5, -5], [-6, -6, -6, -6], [-7, -7, -7, -7]],
 *  [[1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]]]
 * </pre>
 * <p>Note that on CPU, if an out of bound index is found, an error is returned.
 * On GPU, if an out of bound index is found, the index is ignored.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = TensorScatterNdSub.OP_NAME,
    inputsClass = TensorScatterNdSub.Inputs.class
)
@Operator
public final class TensorScatterNdSub<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorScatterSub";

  private Output<T> output;

  public TensorScatterNdSub(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorScatterSub operation.
   *
   * @param scope current scope
   * @param tensor Tensor to copy/update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @param <T> data type for {@code TensorScatterSub} output and operands
   * @return a new instance of TensorScatterNdSub
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TensorScatterNdSub<T> create(Scope scope, Operand<T> tensor,
      Operand<? extends TNumber> indices, Operand<T> updates) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorScatterNdSub");
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    return new TensorScatterNdSub<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A new tensor copied from tensor and updates subtracted according to the indices.
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
      outputsClass = TensorScatterNdSub.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<TensorScatterNdSub<T>> {
    /**
     * Tensor to copy/update.
     */
    public final Operand<T> tensor;

    /**
     * Index tensor.
     */
    public final Operand<? extends TNumber> indices;

    /**
     * Updates to scatter into output.
     */
    public final Operand<T> updates;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    public Inputs(GraphOperation op) {
      super(new TensorScatterNdSub<>(op), op, Arrays.asList("T", "Tindices"));
      int inputIndex = 0;
      tensor = (Operand<T>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      updates = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
