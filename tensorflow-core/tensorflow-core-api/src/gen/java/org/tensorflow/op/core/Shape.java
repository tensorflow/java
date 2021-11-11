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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Returns the shape of a tensor.
 * This operation returns a 1-D integer tensor representing the shape of {@code input}.
 * <p>For example:
 * <pre>
 * # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
 * shape(t) ==&gt; [2, 2, 3]
 * </pre>
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = Shape.OP_NAME,
    inputsClass = Shape.Inputs.class
)
@Operator
public final class Shape<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Shape";

  private Output<U> output;

  public Shape(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Shape operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param outType The value of the outType attribute
   * @param <U> data type for {@code Shape} output and operands
   * @return a new instance of Shape
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> Shape<U> create(Scope scope, Operand<? extends TType> input,
      Class<U> outType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Shape");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new Shape<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new Shape operation, with the default output types.
   *
   * @param scope current scope
   * @param input The input value
   * @return a new instance of Shape, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static Shape<TInt32> create(Scope scope, Operand<? extends TType> input) {
    return create(scope, input, TInt32.class);
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
      outputsClass = Shape.class
  )
  public static class Inputs extends RawOpInputs<Shape<?>> {
    /**
     * The input input
     */
    public final Operand<? extends TType> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The outType attribute
     */
    public final DataType outType;

    public Inputs(GraphOperation op) {
      super(new Shape<>(op), op, Arrays.asList("T", "out_type"));
      int inputIndex = 0;
      input = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      outType = op.attributes().getAttrType("out_type");
    }
  }
}
