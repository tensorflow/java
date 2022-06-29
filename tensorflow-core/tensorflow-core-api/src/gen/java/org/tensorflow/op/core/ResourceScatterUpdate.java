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
 * Assigns sparse updates to the variable referenced by {@code resource}.
 * This operation computes
 * <pre>
 * # Scalar indices
 * ref[indices, ...] = updates[...]
 *
 * # Vector indices (for each i)
 * ref[indices[i], ...] = updates[i, ...]
 *
 * # High rank indices (for each i, ..., j)
 * ref[indices[i, ..., j], ...] = updates[i, ..., j, ...]
 * </pre>
 */
@OpMetadata(
    opType = ResourceScatterUpdate.OP_NAME,
    inputsClass = ResourceScatterUpdate.Inputs.class
)
@Operator
public final class ResourceScatterUpdate extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceScatterUpdate";

  public ResourceScatterUpdate(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new ResourceScatterUpdate operation.
   *
   * @param scope current scope
   * @param resource Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to add to {@code ref}.
   * @return a new instance of ResourceScatterUpdate
   */
  @Endpoint(
      describeByClass = true
  )
  public static ResourceScatterUpdate create(Scope scope, Operand<? extends TType> resource,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResourceScatterUpdate");
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    return new ResourceScatterUpdate(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = ResourceScatterUpdate.class
  )
  public static class Inputs extends RawOpInputs<ResourceScatterUpdate> {
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
      super(new ResourceScatterUpdate(op), op, Arrays.asList("dtype", "Tindices"));
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      updates = (Operand<? extends TType>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
