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
 * A placeholder op that passes through {@code input} when its output is not fed.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = PlaceholderWithDefault.OP_NAME,
    inputsClass = PlaceholderWithDefault.Inputs.class
)
@Operator
public final class PlaceholderWithDefault<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "PlaceholderWithDefault";

  private Output<T> output;

  public PlaceholderWithDefault(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new PlaceholderWithDefault operation.
   *
   * @param scope current scope
   * @param input The default value to produce when {@code output} is not fed.
   * @param shape The (possibly partial) shape of the tensor.
   * @param <T> data type for {@code PlaceholderWithDefault} output and operands
   * @return a new instance of PlaceholderWithDefault
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> PlaceholderWithDefault<T> create(Scope scope, Operand<T> input,
      Shape shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "PlaceholderWithDefault");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("shape", shape);
    return new PlaceholderWithDefault<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A placeholder tensor that defaults to {@code input} if it is not fed.
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
      outputsClass = PlaceholderWithDefault.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<PlaceholderWithDefault<T>> {
    /**
     * The default value to produce when {@code output} is not fed.
     */
    public final Operand<T> input;

    /**
     * The type of elements in the tensor.
     */
    public final DataType dtype;

    /**
     * The (possibly partial) shape of the tensor.
     */
    public final Shape shape;

    public Inputs(GraphOperation op) {
      super(new PlaceholderWithDefault<>(op), op, Arrays.asList("dtype", "shape"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      shape = op.attributes().getAttrShape("shape");
    }
  }
}
