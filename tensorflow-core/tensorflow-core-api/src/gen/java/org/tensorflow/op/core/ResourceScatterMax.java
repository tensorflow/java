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
 * Reduces sparse updates into the variable referenced by {@code resource} using the {@code max} operation.
 * This operation computes
 * <pre>
 * # Scalar indices
 * ref[indices, ...] = max(ref[indices, ...], updates[...])
 *
 * # Vector indices (for each i)
 * ref[indices[i], ...] = max(ref[indices[i], ...], updates[i, ...])
 *
 * # High rank indices (for each i, ..., j)
 * ref[indices[i, ..., j], ...] = max(ref[indices[i, ..., j], ...], updates[i, ..., j, ...])
 * </pre>
 * <p>Duplicate entries are handled correctly: if multiple {@code indices} reference
 * the same location, their contributions are combined.
 * <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
 * <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
 * <img style="width:100%" src='https://www.tensorflow.org/images/ScatterAdd.png' alt>
 * </div>
 */
@OpMetadata(
    opType = ResourceScatterMax.OP_NAME,
    inputsClass = ResourceScatterMax.Inputs.class
)
@Operator
public final class ResourceScatterMax extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceScatterMax";

  public ResourceScatterMax(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new ResourceScatterMax operation.
   *
   * @param scope current scope
   * @param resource Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to add to {@code ref}.
   * @return a new instance of ResourceScatterMax
   */
  @Endpoint(
      describeByClass = true
  )
  public static ResourceScatterMax create(Scope scope, Operand<? extends TType> resource,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResourceScatterMax");
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    return new ResourceScatterMax(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = ResourceScatterMax.class
  )
  public static class Inputs extends RawOpInputs<ResourceScatterMax> {
    /**
     * Should be from a {@code Variable} node.
     */
    public final Operand<? extends TType> resource;

    /**
     * A tensor of indices into the first dimension of {@code ref}.
     */
    public final Operand<? extends TNumber> indices;

    /**
     * A tensor of updated values to add to {@code ref}.
     */
    public final Operand<? extends TType> updates;

    /**
     * The dtype attribute
     */
    public final DataType dtype;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    public Inputs(GraphOperation op) {
      super(new ResourceScatterMax(op), op, Arrays.asList("dtype", "Tindices"));
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      updates = (Operand<? extends TType>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
