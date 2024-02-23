/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
 * The ResourceScatterNdMax operation
 */
@OpMetadata(
    opType = ResourceScatterNdMax.OP_NAME,
    inputsClass = ResourceScatterNdMax.Inputs.class
)
@Operator
public final class ResourceScatterNdMax extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceScatterNdMax";

  public ResourceScatterNdMax(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new ResourceScatterNdMax operation.
   *
   * @param scope current scope
   * @param ref A resource handle. Must be from a VarHandleOp.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   * A tensor of indices into ref.
   * @param updates A Tensor. Must have the same type as ref. A tensor of
   * values whose element wise max is taken with ref
   * @param options carries optional attribute values
   * @return a new instance of ResourceScatterNdMax
   */
  @Endpoint(
      describeByClass = true
  )
  public static ResourceScatterNdMax create(Scope scope, Operand<? extends TType> ref,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResourceScatterNdMax");
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
    return new ResourceScatterNdMax(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.core.ResourceScatterNdMax}
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
      outputsClass = ResourceScatterNdMax.class
  )
  public static class Inputs extends RawOpInputs<ResourceScatterNdMax> {
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
     * A Tensor. Must have the same type as ref. A tensor of
     * values whose element wise max is taken with ref
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
      super(new ResourceScatterNdMax(op), op, Arrays.asList("T", "Tindices", "use_locking"));
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
