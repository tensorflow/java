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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Returns immutable tensor from memory region.
 * The current implementation memmaps the tensor from a file.
 *
 * @param <T> data type for {@code tensor} output
 */
@OpMetadata(
    opType = ImmutableConst.OP_NAME,
    inputsClass = ImmutableConst.Inputs.class
)
@Operator
public final class ImmutableConst<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ImmutableConst";

  private Output<T> tensor;

  public ImmutableConst(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    tensor = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ImmutableConst operation.
   *
   * @param scope current scope
   * @param dtype Type of the returned tensor.
   * @param shape Shape of the returned tensor.
   * @param memoryRegionName Name of readonly memory region used by the tensor, see
   * NewReadOnlyMemoryRegionFromFile in tensorflow::Env.
   * @param <T> data type for {@code ImmutableConst} output and operands
   * @return a new instance of ImmutableConst
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ImmutableConst<T> create(Scope scope, Class<T> dtype, Shape shape,
      String memoryRegionName) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ImmutableConst");
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    opBuilder.setAttr("shape", shape);
    opBuilder.setAttr("memory_region_name", memoryRegionName);
    return new ImmutableConst<>(opBuilder.build());
  }

  /**
   * Gets tensor.
   *
   * @return tensor.
   */
  public Output<T> tensor() {
    return tensor;
  }

  @Override
  public Output<T> asOutput() {
    return tensor;
  }

  @OpInputsMetadata(
      outputsClass = ImmutableConst.class
  )
  public static class Inputs extends RawOpInputs<ImmutableConst<?>> {
    /**
     * Type of the returned tensor.
     */
    public final DataType dtype;

    /**
     * Shape of the returned tensor.
     */
    public final Shape shape;

    /**
     * Name of readonly memory region used by the tensor, see
     * NewReadOnlyMemoryRegionFromFile in tensorflow::Env.
     */
    public final String memoryRegionName;

    public Inputs(GraphOperation op) {
      super(new ImmutableConst<>(op), op, Arrays.asList("dtype", "shape", "memory_region_name"));
      int inputIndex = 0;
      dtype = op.attributes().getAttrType("dtype");
      shape = op.attributes().getAttrShape("shape");
      memoryRegionName = op.attributes().getAttrString("memory_region_name");
    }
  }
}
