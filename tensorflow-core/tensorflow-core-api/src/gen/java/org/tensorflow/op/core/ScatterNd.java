/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Scatters {@code updates} into a tensor of shape {@code shape} according to {@code indices}.
 * Scatter sparse {@code updates} according to individual values at the specified
 * {@code indices}. This op returns an output tensor with the {@code shape} you specify. This
 * op is the inverse of the {@code tf.gather_nd} operator which extracts values or slices
 * from a given tensor.
 * <p>This operation is similar to {@code tf.tensor_scatter_nd_add}, except that the tensor
 * is zero-initialized. Calling {@code tf.scatter_nd(indices, updates, shape)}
 * is identical to calling
 * {@code tf.tensor_scatter_nd_add(tf.zeros(shape, updates.dtype), indices, updates)}
 * <p>If {@code indices} contains duplicates, the associated {@code updates} are accumulated
 * (summed) into the output tensor.
 * <p><strong>WARNING</strong>: For floating-point data types, the output may be nondeterministic.
 * This is because the order in which the updates are applied is nondeterministic
 * and when floating-point numbers are added in different orders the resulting
 * numerical approximation error can be slightly different. However, the output
 * will be deterministic if op determinism is enabled via
 * {@code tf.config.experimental.enable_op_determinism}.
 * <p>{@code indices} is an integer tensor containing indices into the output tensor. The
 * last dimension of {@code indices} can be at most the rank of {@code shape}:
 * <pre>
 * indices.shape[-1] &lt;= shape.rank
 * </pre>
 * <p>The last dimension of {@code indices} corresponds to indices of elements
 * (if {@code indices.shape[-1] = shape.rank}) or slices
 * (if {@code indices.shape[-1] < shape.rank}) along dimension {@code indices.shape[-1]} of
 * {@code shape}.
 * <p>{@code updates} is a tensor with shape:
 * <pre>
 * indices.shape[:-1] + shape[indices.shape[-1]:]
 * </pre>
 * <p>The simplest form of the scatter op is to insert individual elements in
 * a tensor by index. Consider an example where you want to insert 4 scattered
 * elements in a rank-1 tensor with 8 elements.
 * <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
 * <img style="width:100%" src="https://www.tensorflow.org/images/ScatterNd1.png" alt>
 * </div>
 * <p>In Python, this scatter operation would look like this:
 * <pre>
 *     indices = tf.constant([[4], [3], [1], [7]])
 *     updates = tf.constant([9, 10, 11, 12])
 *     shape = tf.constant([8])
 *     scatter = tf.scatter_nd(indices, updates, shape)
 *     print(scatter)
 * </pre>
 * <p>The resulting tensor would look like this:
 * <pre>
 * [0, 11, 0, 10, 9, 0, 0, 12]
 * </pre>
 * <p>You can also insert entire slices of a higher rank tensor all at once. For
 * example, you can insert two slices in the first dimension of a rank-3 tensor
 * with two matrices of new values.
 * <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
 * <img style="width:100%" src="https://www.tensorflow.org/images/ScatterNd2.png" alt>
 * </div>
 * <p>In Python, this scatter operation would look like this:
 * <pre>
 *     indices = tf.constant([[1], [3]])
 *     updates = tf.constant([[[5, 5, 5, 5], [6, 6, 6, 6],
 *                             [7, 7, 7, 7], [8, 8, 8, 8]],
 *                            [[5, 5, 5, 5], [6, 6, 6, 6],
 *                             [7, 7, 7, 7], [8, 8, 8, 8]]])
 *     shape = tf.constant([4, 4, 4])
 *     scatter = tf.scatter_nd(indices, updates, shape)
 *     print(scatter)
 * </pre>
 * <p>The resulting tensor would look like this:
 * <pre>
 * [[[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]],
 *  [[5, 5, 5, 5], [6, 6, 6, 6], [7, 7, 7, 7], [8, 8, 8, 8]],
 *  [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]],
 *  [[5, 5, 5, 5], [6, 6, 6, 6], [7, 7, 7, 7], [8, 8, 8, 8]]]
 * </pre>
 * <p>If {@code indices} contains any out-of-bound indices, depending on
 * {@code bad_indices_policy}, the op will either return an error or ignore the
 * out-of-bound indices. {@code bad_indices_policy} can be one of the following values:
 * <ol>
 * <li>&quot;&quot; or &quot;DEFAULT&quot;: raises on CPU and ignore on GPU. This is because
 * historically on CPU and GPU we handle errors in different ways, and for
 * backward compatibility we keep the default behavior.</li>
 * <li>&quot;ERROR&quot;: raises error; GPU does not support this value.</li>
 * <li>&quot;IGNORE&quot;: ignore the bad indices; supported on both CPU and GPU.</li>
 * </ol>
 */
@OpMetadata(
    opType = ScatterNd.OP_NAME,
    inputsClass = ScatterNd.Inputs.class
)
@Operator
public final class ScatterNd<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ScatterNd";

  private Output<U> output;

  public ScatterNd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ScatterNd operation.
   *
   * @param scope current scope
   * @param indices Tensor of indices.
   * @param updates Values to scatter into the output tensor.
   * @param shape 1-D. The shape of the output tensor.
   * @param options carries optional attribute values
   * @param <U> data type for {@code ScatterNd} output and operands
   * @param <T> data type for {@code ScatterNd} output and operands
   * @return a new instance of ScatterNd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType, T extends TNumber> ScatterNd<U> create(Scope scope,
      Operand<T> indices, Operand<U> updates, Operand<T> shape, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ScatterNd");
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    opBuilder.addInput(shape.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.badIndicesPolicy != null) {
          opBuilder.setAttr("bad_indices_policy", opts.badIndicesPolicy);
        }
      }
    }
    return new ScatterNd<>(opBuilder.build());
  }

  /**
   * Sets the badIndicesPolicy option.
   *
   * @param badIndicesPolicy the badIndicesPolicy option
   * @return this Options instance.
   */
  public static Options badIndicesPolicy(String badIndicesPolicy) {
    return new Options().badIndicesPolicy(badIndicesPolicy);
  }

  /**
   * Gets output.
   * A new tensor with the given shape and updates applied according
   * to the indices.
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.ScatterNd}
   */
  public static class Options {
    private String badIndicesPolicy;

    private Options() {
    }

    /**
     * Sets the badIndicesPolicy option.
     *
     * @param badIndicesPolicy the badIndicesPolicy option
     * @return this Options instance.
     */
    public Options badIndicesPolicy(String badIndicesPolicy) {
      this.badIndicesPolicy = badIndicesPolicy;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ScatterNd.class
  )
  public static class Inputs<T extends TNumber, U extends TType> extends RawOpInputs<ScatterNd<U>> {
    /**
     * Tensor of indices.
     */
    public final Operand<T> indices;

    /**
     * Values to scatter into the output tensor.
     */
    public final Operand<U> updates;

    /**
     * 1-D. The shape of the output tensor.
     */
    public final Operand<T> shape;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    /**
     * The badIndicesPolicy attribute
     */
    public final String badIndicesPolicy;

    public Inputs(GraphOperation op) {
      super(new ScatterNd<>(op), op, Arrays.asList("T", "Tindices", "bad_indices_policy"));
      int inputIndex = 0;
      indices = (Operand<T>) op.input(inputIndex++);
      updates = (Operand<U>) op.input(inputIndex++);
      shape = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
      badIndicesPolicy = op.attributes().getAttrString("bad_indices_policy");
    }
  }
}
