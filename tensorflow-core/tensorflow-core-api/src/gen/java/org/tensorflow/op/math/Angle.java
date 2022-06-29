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

package org.tensorflow.op.math;

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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Returns the argument of a complex number.
 * Given a tensor {@code input} of complex numbers, this operation returns a tensor of
 * type {@code float} that is the argument of each element in {@code input}. All elements in
 * {@code input} must be complex numbers of the form \(a + bj\), where <em>a</em>
 * is the real part and <em>b</em> is the imaginary part.
 * <p>The argument returned by this operation is of the form \(atan2(b, a)\).
 * <p>For example:
 * <pre>
 * # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
 * tf.angle(input) ==&gt; [2.0132, 1.056]
 * </pre>
 * <p>{@literal @}compatibility(numpy)<br>
 * Equivalent to np.angle.
 * <br>{@literal @}end_compatibility
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = Angle.OP_NAME,
    inputsClass = Angle.Inputs.class
)
@Operator(
    group = "math"
)
public final class Angle<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Angle";

  private Output<U> output;

  public Angle(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Angle operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param Tout The value of the Tout attribute
   * @param <U> data type for {@code Angle} output and operands
   * @return a new instance of Angle
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> Angle<U> create(Scope scope, Operand<? extends TType> input,
      Class<U> Tout) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Angle");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("Tout", Operands.toDataType(Tout));
    return new Angle<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new Angle operation, with the default output types.
   *
   * @param scope current scope
   * @param input The input value
   * @return a new instance of Angle, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static Angle<TFloat32> create(Scope scope, Operand<? extends TType> input) {
    return create(scope, input, TFloat32.class);
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
      outputsClass = Angle.class
  )
  public static class Inputs extends RawOpInputs<Angle<?>> {
    /**
     * The input input
     */
    public final Operand<? extends TType> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tout attribute
     */
    public final DataType Tout;

    public Inputs(GraphOperation op) {
      super(new Angle<>(op), op, Arrays.asList("T", "Tout"));
      int inputIndex = 0;
      input = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tout = op.attributes().getAttrType("Tout");
    }
  }
}
