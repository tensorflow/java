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
import org.tensorflow.op.Operands;
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
 * The ResourceGatherNd operation
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = ResourceGatherNd.OP_NAME,
    inputsClass = ResourceGatherNd.Inputs.class
)
@Operator
public final class ResourceGatherNd<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceGatherNd";

  private Output<U> output;

  public ResourceGatherNd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ResourceGatherNd operation.
   *
   * @param scope current scope
   * @param resource The resource value
   * @param indices The indices value
   * @param dtype The value of the dtype attribute
   * @param <U> data type for {@code ResourceGatherNd} output and operands
   * @return a new instance of ResourceGatherNd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> ResourceGatherNd<U> create(Scope scope,
      Operand<? extends TType> resource, Operand<? extends TNumber> indices, Class<U> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResourceGatherNd");
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new ResourceGatherNd<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = ResourceGatherNd.class
  )
  public static class Inputs extends RawOpInputs<ResourceGatherNd<?>> {
    /**
     * The resource input
     */
    public final Operand<? extends TType> resource;

    /**
     * The indices input
     */
    public final Operand<? extends TNumber> indices;

    /**
     * The dtype attribute
     */
    public final DataType dtype;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    public Inputs(GraphOperation op) {
      super(new ResourceGatherNd<>(op), op, Arrays.asList("dtype", "Tindices"));
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
