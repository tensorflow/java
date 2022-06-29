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

package org.tensorflow.op.data;

import java.util.Arrays;
import java.util.List;
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
 * Gets the next output from the given iterator as an Optional variant.
 */
@OpMetadata(
    opType = IteratorGetNextAsOptional.OP_NAME,
    inputsClass = IteratorGetNextAsOptional.Inputs.class
)
@Operator(
    group = "data"
)
public final class IteratorGetNextAsOptional extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IteratorGetNextAsOptional";

  private Output<? extends TType> optional;

  @SuppressWarnings("unchecked")
  public IteratorGetNextAsOptional(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    optional = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IteratorGetNextAsOptional operation.
   *
   * @param scope current scope
   * @param iterator The iterator value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of IteratorGetNextAsOptional
   */
  @Endpoint(
      describeByClass = true
  )
  public static IteratorGetNextAsOptional create(Scope scope, Operand<? extends TType> iterator,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IteratorGetNextAsOptional");
    opBuilder.addInput(iterator.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new IteratorGetNextAsOptional(opBuilder.build());
  }

  /**
   * Gets optional.
   *
   * @return optional.
   */
  public Output<? extends TType> optional() {
    return optional;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) optional;
  }

  @OpInputsMetadata(
      outputsClass = IteratorGetNextAsOptional.class
  )
  public static class Inputs extends RawOpInputs<IteratorGetNextAsOptional> {
    /**
     * The iterator input
     */
    public final Operand<? extends TType> iterator;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new IteratorGetNextAsOptional(op), op, Arrays.asList("output_types", "output_shapes"));
      int inputIndex = 0;
      iterator = (Operand<? extends TType>) op.input(inputIndex++);
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
