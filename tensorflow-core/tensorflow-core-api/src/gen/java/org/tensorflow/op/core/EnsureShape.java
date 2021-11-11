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
 * Ensures that the tensor's shape matches the expected shape.
 * Raises an error if the input tensor's shape does not match the specified shape.
 * Returns the input tensor otherwise.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = EnsureShape.OP_NAME,
    inputsClass = EnsureShape.Inputs.class
)
@Operator
public final class EnsureShape<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "EnsureShape";

  private Output<T> output;

  public EnsureShape(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new EnsureShape operation.
   *
   * @param scope current scope
   * @param input A tensor, whose shape is to be validated.
   * @param shape The expected (possibly partially specified) shape of the input tensor.
   * @param <T> data type for {@code EnsureShape} output and operands
   * @return a new instance of EnsureShape
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> EnsureShape<T> create(Scope scope, Operand<T> input,
      Shape shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "EnsureShape");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("shape", shape);
    return new EnsureShape<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A tensor with the same shape and contents as the input tensor or value.
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
      outputsClass = EnsureShape.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<EnsureShape<T>> {
    /**
     * A tensor, whose shape is to be validated.
     */
    public final Operand<T> input;

    /**
     * The expected (possibly partially specified) shape of the input tensor.
     */
    public final Shape shape;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new EnsureShape<>(op), op, Arrays.asList("shape", "T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      shape = op.attributes().getAttrShape("shape");
      T = op.attributes().getAttrType("T");
    }
  }
}
