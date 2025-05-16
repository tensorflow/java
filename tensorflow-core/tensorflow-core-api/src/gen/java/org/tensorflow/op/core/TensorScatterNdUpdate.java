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
 * Scatter {@code updates} into an existing tensor according to {@code indices}.
 * This operation creates a new tensor by applying sparse {@code updates} to the passed
 * in {@code tensor}.
 * This operation is very similar to {@code tf.scatter_nd}, except that the updates are
 * scattered onto an existing tensor (as opposed to a zero-tensor). If the memory
 * for the existing tensor cannot be re-used, a copy is made and updated.
 * <p>If {@code indices} contains duplicates, then we pick the last update for the index.
 * <p><strong>WARNING</strong>: There are some GPU specific semantics for this operation.
 * <ul>
 * <li>If an out of bound index is found, the index is ignored.</li>
 * <li>The order in which updates are applied is nondeterministic, so the output
 * will be nondeterministic if {@code indices} contains duplicates.</li>
 * </ul>
 * <p>{@code indices} is an integer tensor containing indices into a new tensor of shape
 * {@code shape}.
 * <ul>
 * <li>{@code indices} must have at least 2 axes: {@code (num_updates, index_depth)}.</li>
 * <li>The last axis of {@code indices} is how deep to index into {@code tensor} so  this index
 * depth must be less than the rank of {@code tensor}: {@code indices.shape[-1] <= tensor.ndim}</li>
 * </ul>
 * <p>if {@code indices.shape[-1] = tensor.rank} this Op indexes and updates scalar elements.
 * if {@code indices.shape[-1] < tensor.rank} it indexes and updates slices of the input
 * {@code tensor}.
 * <p>Each {@code update} has a rank of {@code tensor.rank - indices.shape[-1]}.
 * The overall shape of {@code updates} is:
 * <pre>
 * indices.shape[:-1] + tensor.shape[indices.shape[-1]:]
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
 * <p>For usage examples see the python  tf.tensor_scatter_nd_update {@link org.tensorflow.op.Ops#tensorScatterNdUpdate}  function
 */
@OpMetadata(
    opType = TensorScatterNdUpdate.OP_NAME,
    inputsClass = TensorScatterNdUpdate.Inputs.class
)
@Operator
public final class TensorScatterNdUpdate<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorScatterUpdate";

  private Output<T> output;

  public TensorScatterNdUpdate(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorScatterUpdate operation.
   *
   * @param scope current scope
   * @param tensor Tensor to copy/update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TensorScatterUpdate} output and operands
   * @return a new instance of TensorScatterNdUpdate
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TensorScatterNdUpdate<T> create(Scope scope, Operand<T> tensor,
      Operand<? extends TNumber> indices, Operand<T> updates, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorScatterNdUpdate");
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.badIndicesPolicy != null) {
          opBuilder.setAttr("bad_indices_policy", opts.badIndicesPolicy);
        }
      }
    }
    return new TensorScatterNdUpdate<>(opBuilder.build());
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
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorScatterNdUpdate}
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
      outputsClass = TensorScatterNdUpdate.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<TensorScatterNdUpdate<T>> {
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

    /**
     * The badIndicesPolicy attribute
     */
    public final String badIndicesPolicy;

    public Inputs(GraphOperation op) {
      super(new TensorScatterNdUpdate<>(op), op, Arrays.asList("T", "Tindices", "bad_indices_policy"));
      int inputIndex = 0;
      tensor = (Operand<T>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      updates = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
      badIndicesPolicy = op.attributes().getAttrString("bad_indices_policy");
    }
  }
}
