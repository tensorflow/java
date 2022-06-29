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
 * Returns the shape of the variable pointed to by {@code resource}.
 * This operation returns a 1-D integer tensor representing the shape of {@code input}.
 * <p>For example:
 * <pre>
 * # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
 * shape(t) ==&gt; [2, 2, 3]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = VariableShape.OP_NAME,
    inputsClass = VariableShape.Inputs.class
)
@Operator
public final class VariableShape<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "VariableShape";

  private Output<T> output;

  public VariableShape(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new VariableShape operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param outType The value of the outType attribute
   * @param <T> data type for {@code VariableShape} output and operands
   * @return a new instance of VariableShape
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> VariableShape<T> create(Scope scope,
      Operand<? extends TType> input, Class<T> outType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "VariableShape");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new VariableShape<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new VariableShape operation, with the default output types.
   *
   * @param scope current scope
   * @param input The input value
   * @return a new instance of VariableShape, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static VariableShape<TInt32> create(Scope scope, Operand<? extends TType> input) {
    return create(scope, input, TInt32.class);
  }

  /**
   * Gets output.
   *
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
      outputsClass = VariableShape.class
  )
  public static class Inputs extends RawOpInputs<VariableShape<?>> {
    /**
     * The input input
     */
    public final Operand<? extends TType> input;

    /**
     * The outType attribute
     */
    public final DataType outType;

    public Inputs(GraphOperation op) {
      super(new VariableShape<>(op), op, Arrays.asList("out_type"));
      int inputIndex = 0;
      input = (Operand<? extends TType>) op.input(inputIndex++);
      outType = op.attributes().getAttrType("out_type");
    }
  }
}
