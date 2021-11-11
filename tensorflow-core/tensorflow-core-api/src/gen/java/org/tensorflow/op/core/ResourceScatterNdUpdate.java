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
 * Applies sparse {@code updates} to individual values or slices within a given
 * variable according to {@code indices}.
 * <p>{@code ref} is a {@code Tensor} with rank {@code P} and {@code indices} is a {@code Tensor} of rank {@code Q}.
 * <p>{@code indices} must be integer tensor, containing indices into {@code ref}.
 * It must be shape {@code [d_0, ..., d_{Q-2}, K]} where {@code 0 < K <= P}.
 * <p>The innermost dimension of {@code indices} (with length {@code K}) corresponds to
 * indices into elements (if {@code K = P}) or slices (if {@code K < P}) along the {@code K}th
 * dimension of {@code ref}.
 * <p>{@code updates} is {@code Tensor} of rank {@code Q-1+P-K} with shape:
 * <pre>
 * [d_0, ..., d_{Q-2}, ref.shape[K], ..., ref.shape[P-1]].
 * </pre>
 * <p>For example, say we want to update 4 scattered elements to a rank-1 tensor to
 * 8 elements. In Python, that update would look like this:
 * <pre>
 *     ref = tf.Variable([1, 2, 3, 4, 5, 6, 7, 8])
 *     indices = tf.constant([[4], [3], [1] ,[7]])
 *     updates = tf.constant([9, 10, 11, 12])
 *     update = tf.scatter_nd_update(ref, indices, updates)
 *     with tf.Session() as sess:
 *       print sess.run(update)
 * </pre>
 * <p>The resulting update to ref would look like this:
 * <pre>
 * [1, 11, 3, 10, 9, 6, 7, 12]
 * </pre>
 * <p>See {@code tf.scatter_nd} for more details about how to make updates to
 * slices.
 */
@OpMetadata(
    opType = ResourceScatterNdUpdate.OP_NAME,
    inputsClass = ResourceScatterNdUpdate.Inputs.class
)
@Operator
public final class ResourceScatterNdUpdate extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceScatterNdUpdate";

  public ResourceScatterNdUpdate(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new ResourceScatterNdUpdate operation.
   *
   * @param scope current scope
   * @param ref A resource handle. Must be from a VarHandleOp.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   * A tensor of indices into ref.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated
   * values to add to ref.
   * @param options carries optional attribute values
   * @return a new instance of ResourceScatterNdUpdate
   */
  @Endpoint(
      describeByClass = true
  )
  public static ResourceScatterNdUpdate create(Scope scope, Operand<? extends TType> ref,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResourceScatterNdUpdate");
    opBuilder.addInput(ref.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new ResourceScatterNdUpdate(opBuilder.build());
  }

  /**
   * Sets the useLocking option.
   *
   * @param useLocking An optional bool. Defaults to True. If True, the assignment will
   * be protected by a lock; otherwise the behavior is undefined,
   * but may exhibit less contention.
   * @return this Options instance.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.ResourceScatterNdUpdate}
   */
  public static class Options {
    private Boolean useLocking;

    private Options() {
    }

    /**
     * Sets the useLocking option.
     *
     * @param useLocking An optional bool. Defaults to True. If True, the assignment will
     * be protected by a lock; otherwise the behavior is undefined,
     * but may exhibit less contention.
     * @return this Options instance.
     */
    public Options useLocking(Boolean useLocking) {
      this.useLocking = useLocking;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ResourceScatterNdUpdate.class
  )
  public static class Inputs extends RawOpInputs<ResourceScatterNdUpdate> {
    /**
     * A resource handle. Must be from a VarHandleOp.
     */
    public final Operand<? extends TType> ref;

    /**
     * A Tensor. Must be one of the following types: int32, int64.
     * A tensor of indices into ref.
     */
    public final Operand<? extends TNumber> indices;

    /**
     * A Tensor. Must have the same type as ref. A tensor of updated
     * values to add to ref.
     */
    public final Operand<? extends TType> updates;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    /**
     * An optional bool. Defaults to True. If True, the assignment will
     * be protected by a lock; otherwise the behavior is undefined,
     * but may exhibit less contention.
     */
    public final boolean useLocking;

    public Inputs(GraphOperation op) {
      super(new ResourceScatterNdUpdate(op), op, Arrays.asList("T", "Tindices", "use_locking"));
      int inputIndex = 0;
      ref = (Operand<? extends TType>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      updates = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
      useLocking = op.attributes().getAttrBool("use_locking");
    }
  }
}
