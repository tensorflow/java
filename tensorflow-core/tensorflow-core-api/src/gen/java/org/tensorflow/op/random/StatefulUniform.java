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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Outputs random values from a uniform distribution.
 * The generated values follow a uniform distribution in the range {@code [0, 1)}. The
 * lower bound 0 is included in the range, while the upper bound 1 is excluded.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = StatefulUniform.OP_NAME,
    inputsClass = StatefulUniform.Inputs.class
)
public final class StatefulUniform<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatefulUniform";

  private Output<U> output;

  public StatefulUniform(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatefulUniform operation.
   *
   * @param scope current scope
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param <U> data type for {@code StatefulUniform} output and operands
   * @return a new instance of StatefulUniform
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> StatefulUniform<U> create(Scope scope,
      Operand<? extends TType> resource, Operand<TInt64> algorithm, Operand<? extends TType> shape,
      Class<U> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatefulUniform");
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(algorithm.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new StatefulUniform<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new StatefulUniform operation, with the default output types.
   *
   * @param scope current scope
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @return a new instance of StatefulUniform, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static StatefulUniform<TFloat32> create(Scope scope, Operand<? extends TType> resource,
      Operand<TInt64> algorithm, Operand<? extends TType> shape) {
    return create(scope, resource, algorithm, shape, TFloat32.class);
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
      outputsClass = StatefulUniform.class
  )
  public static class Inputs extends RawOpInputs<StatefulUniform<?>> {
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
      super(new StatefulUniform<>(op), op, Arrays.asList("dtype", "shape_dtype"));
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      algorithm = (Operand<TInt64>) op.input(inputIndex++);
      shape = (Operand<? extends TType>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      shapeDtype = op.attributes().getAttrType("shape_dtype");
    }
  }
}
