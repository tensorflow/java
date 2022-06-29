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
 * Increments variable pointed to by 'resource' until it reaches 'limit'.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = ResourceCountUpTo.OP_NAME,
    inputsClass = ResourceCountUpTo.Inputs.class
)
@Operator
public final class ResourceCountUpTo<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceCountUpTo";

  private Output<T> output;

  public ResourceCountUpTo(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ResourceCountUpTo operation.
   *
   * @param scope current scope
   * @param resource Should be from a scalar {@code Variable} node.
   * @param limit If incrementing ref would bring it above limit, instead generates an
   * 'OutOfRange' error.
   * @param T The value of the T attribute
   * @param <T> data type for {@code ResourceCountUpTo} output and operands
   * @return a new instance of ResourceCountUpTo
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> ResourceCountUpTo<T> create(Scope scope,
      Operand<? extends TType> resource, Long limit, Class<T> T) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResourceCountUpTo");
    opBuilder.addInput(resource.asOutput());
    opBuilder.setAttr("limit", limit);
    opBuilder.setAttr("T", Operands.toDataType(T));
    return new ResourceCountUpTo<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A copy of the input before increment. If nothing else modifies the
   * input, the values produced will all be distinct.
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
      outputsClass = ResourceCountUpTo.class
  )
  public static class Inputs extends RawOpInputs<ResourceCountUpTo<?>> {
    /**
     * Should be from a scalar {@code Variable} node.
     */
    public final Operand<? extends TType> resource;

    /**
     * If incrementing ref would bring it above limit, instead generates an
     * 'OutOfRange' error.
     */
    public final long limit;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new ResourceCountUpTo<>(op), op, Arrays.asList("limit", "T"));
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      limit = op.attributes().getAttrInt("limit");
      T = op.attributes().getAttrType("T");
    }
  }
}
