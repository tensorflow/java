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

package org.tensorflow.op.random;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Outputs random integers from a uniform distribution.
 * The generated values are uniform integers covering the whole range of {@code dtype}.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = StatefulUniformFullInt.OP_NAME,
    inputsClass = StatefulUniformFullInt.Inputs.class
)
public final class StatefulUniformFullInt<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatefulUniformFullInt";

  private Output<U> output;

  public StatefulUniformFullInt(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatefulUniformFullInt operation.
   *
   * @param scope current scope
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param <U> data type for {@code StatefulUniformFullInt} output and operands
   * @return a new instance of StatefulUniformFullInt
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> StatefulUniformFullInt<U> create(Scope scope,
      Operand<? extends TType> resource, Operand<TInt64> algorithm, Operand<? extends TType> shape,
      Class<U> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatefulUniformFullInt");
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(algorithm.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new StatefulUniformFullInt<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Random values with specified shape.
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
      outputsClass = StatefulUniformFullInt.class
  )
  public static class Inputs extends RawOpInputs<StatefulUniformFullInt<?>> {
    /**
     * The handle of the resource variable that stores the state of the RNG.
     */
    public final Operand<? extends TType> resource;

    /**
     * The RNG algorithm.
     */
    public final Operand<TInt64> algorithm;

    /**
     * The shape of the output tensor.
     */
    public final Operand<? extends TType> shape;

    /**
     * The type of the output.
     */
    public final DataType dtype;

    /**
     * The shapeDtype attribute
     */
    public final DataType shapeDtype;

    public Inputs(GraphOperation op) {
      super(new StatefulUniformFullInt<>(op), op, Arrays.asList("dtype", "shape_dtype"));
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      algorithm = (Operand<TInt64>) op.input(inputIndex++);
      shape = (Operand<? extends TType>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      shapeDtype = op.attributes().getAttrType("shape_dtype");
    }
  }
}
