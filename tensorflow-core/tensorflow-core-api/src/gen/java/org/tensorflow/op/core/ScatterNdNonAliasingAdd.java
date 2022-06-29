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
 * Applies sparse addition to {@code input} using individual values or slices
 * from {@code updates} according to indices {@code indices}.  The updates are non-aliasing:
 * {@code input} is only modified in-place if no other operations will use it.
 * Otherwise, a copy of {@code input} is made.  This operation has a gradient with
 * respect to both {@code input} and {@code updates}.
 * <p>{@code input} is a {@code Tensor} with rank {@code P} and {@code indices} is a {@code Tensor} of rank {@code Q}.
 * <p>{@code indices} must be integer tensor, containing indices into {@code input}.
 * It must be shape \([d_0, ..., d_{Q-2}, K]\) where {@code 0 < K <= P}.
 * <p>The innermost dimension of {@code indices} (with length {@code K}) corresponds to
 * indices into elements (if {@code K = P}) or {@code (P-K)}-dimensional slices
 * (if {@code K < P}) along the {@code K}th dimension of {@code input}.
 * <p>{@code updates} is {@code Tensor} of rank {@code Q-1+P-K} with shape:
 * <p>$$[d_0, ..., d_{Q-2}, input.shape[K], ..., input.shape[P-1]].$$
 * <p>For example, say we want to add 4 scattered elements to a rank-1 tensor to 8
 * elements. In Python, that addition would look like this:
 * <pre>
 * input = tf.constant([1, 2, 3, 4, 5, 6, 7, 8])
 * indices = tf.constant([[4], [3], [1], [7]])
 * updates = tf.constant([9, 10, 11, 12])
 * output = tf.scatter_nd_non_aliasing_add(input, indices, updates)
 * with tf.Session() as sess:
 *   print(sess.run(output))
 * </pre>
 * <p>The resulting value {@code output} would look like this:
 * <pre>
 * [1, 13, 3, 14, 14, 6, 7, 20]
 * </pre>
 * <p>See {@code tf.scatter_nd} for more details about how to make updates to slices.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = ScatterNdNonAliasingAdd.OP_NAME,
    inputsClass = ScatterNdNonAliasingAdd.Inputs.class
)
@Operator
public final class ScatterNdNonAliasingAdd<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ScatterNdNonAliasingAdd";

  private Output<T> output;

  public ScatterNdNonAliasingAdd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ScatterNdNonAliasingAdd operation.
   *
   * @param scope current scope
   * @param input A Tensor.
   * @param indices A Tensor. Must be one of the following types: {@code int32}, {@code int64}.
   * A tensor of indices into {@code input}.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated values
   * to add to {@code input}.
   * @param <T> data type for {@code ScatterNdNonAliasingAdd} output and operands
   * @return a new instance of ScatterNdNonAliasingAdd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ScatterNdNonAliasingAdd<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> indices, Operand<T> updates) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ScatterNdNonAliasingAdd");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    return new ScatterNdNonAliasingAdd<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A {@code Tensor} with the same shape as {@code input}, containing values of {@code input}
   * updated with {@code updates}.
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
      outputsClass = ScatterNdNonAliasingAdd.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<ScatterNdNonAliasingAdd<T>> {
    /**
     * A Tensor.
     */
    public final Operand<T> input;

    /**
     * A Tensor. Must be one of the following types: {@code int32}, {@code int64}.
     * A tensor of indices into {@code input}.
     */
    public final Operand<? extends TNumber> indices;

    /**
     * A Tensor. Must have the same type as ref. A tensor of updated values
     * to add to {@code input}.
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
      super(new ScatterNdNonAliasingAdd<>(op), op, Arrays.asList("T", "Tindices"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      updates = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
