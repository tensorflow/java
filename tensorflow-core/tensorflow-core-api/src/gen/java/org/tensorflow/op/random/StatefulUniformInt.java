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
 * The generated values are uniform integers in the range {@code [minval, maxval)}.
 * The lower bound {@code minval} is included in the range, while the upper bound
 * {@code maxval} is excluded.
 * <p>The random integers are slightly biased unless {@code maxval - minval} is an exact
 * power of two.  The bias is small for values of {@code maxval - minval} significantly
 * smaller than the range of the output (either {@code 2^32} or {@code 2^64}).
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = StatefulUniformInt.OP_NAME,
    inputsClass = StatefulUniformInt.Inputs.class
)
public final class StatefulUniformInt<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatefulUniformInt";

  private Output<U> output;

  public StatefulUniformInt(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatefulUniformInt operation.
   *
   * @param scope current scope
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @param minval Minimum value (inclusive, scalar).
   * @param maxval Maximum value (exclusive, scalar).
   * @param <U> data type for {@code StatefulUniformInt} output and operands
   * @return a new instance of StatefulUniformInt
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> StatefulUniformInt<U> create(Scope scope,
      Operand<? extends TType> resource, Operand<TInt64> algorithm, Operand<? extends TType> shape,
      Operand<U> minval, Operand<U> maxval) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatefulUniformInt");
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(algorithm.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(minval.asOutput());
    opBuilder.addInput(maxval.asOutput());
    return new StatefulUniformInt<>(opBuilder.build());
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
      outputsClass = StatefulUniformInt.class
  )
  public static class Inputs<U extends TType> extends RawOpInputs<StatefulUniformInt<U>> {
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
     * Minimum value (inclusive, scalar).
     */
    public final Operand<U> minval;

    /**
     * Maximum value (exclusive, scalar).
     */
    public final Operand<U> maxval;

    /**
     * The type of the output.
     */
    public final DataType dtype;

    /**
     * The shapeDtype attribute
     */
    public final DataType shapeDtype;

    public Inputs(GraphOperation op) {
      super(new StatefulUniformInt<>(op), op, Arrays.asList("dtype", "shape_dtype"));
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      algorithm = (Operand<TInt64>) op.input(inputIndex++);
      shape = (Operand<? extends TType>) op.input(inputIndex++);
      minval = (Operand<U>) op.input(inputIndex++);
      maxval = (Operand<U>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      shapeDtype = op.attributes().getAttrType("shape_dtype");
    }
  }
}
