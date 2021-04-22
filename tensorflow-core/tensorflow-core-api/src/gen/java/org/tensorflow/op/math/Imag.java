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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Returns the imaginary part of a complex number.
 * Given a tensor {@code input} of complex numbers, this operation returns a tensor of
 * type {@code float} that is the imaginary part of each element in {@code input}. All
 * elements in {@code input} must be complex numbers of the form \(a + bj\), where <em>a</em>
 * is the real part and <em>b</em> is the imaginary part returned by this operation.
 * <p>For example:
 * <pre>
 * # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
 * tf.imag(input) ==&gt; [4.75, 5.75]
 * </pre>
 *
 * @param <U> data type for {@code output} output
 */
@Operator(
    group = "math"
)
public final class Imag<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Imag";

  private Output<U> output;

  private Imag(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Imag operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param Tout the value of the Tout property
   * @param <U> data type for {@code Imag} output and operands
   * @return a new instance of Imag
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> Imag<U> create(Scope scope, Operand<? extends TType> input,
      Class<U> Tout) {
    OperationBuilder opBuilder = scope.env().opBuilder("Imag", scope.makeOpName("Imag"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("Tout", Operands.toDataType(Tout));
    return new Imag<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new Imag operation, with the default output types.
   *
   * @param scope current scope
   * @param input the input value
   * @return a new instance of Imag, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static Imag<TFloat32> create(Scope scope, Operand<? extends TType> input) {
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
}
