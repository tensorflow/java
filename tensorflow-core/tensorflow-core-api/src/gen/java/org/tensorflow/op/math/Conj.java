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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Returns the complex conjugate of a complex number.
 * Given a tensor {@code input} of complex numbers, this operation returns a tensor of
 * complex numbers that are the complex conjugate of each element in {@code input}. The
 * complex numbers in {@code input} must be of the form \(a + bj\), where <em>a</em> is the
 * real part and <em>b</em> is the imaginary part.
 * <p>The complex conjugate returned by this operation is of the form \(a - bj\).
 * <p>For example:
 * <pre>
 * # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
 * tf.conj(input) ==&gt; [-2.25 - 4.75j, 3.25 - 5.75j]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@Operator(
    group = "math"
)
public final class Conj<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Conj";

  private Output<T> output;

  private Conj(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Conj operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param <T> data type for {@code Conj} output and operands
   * @return a new instance of Conj
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Conj<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("Conj", scope.makeOpName("Conj"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Conj<>(opBuilder.build());
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
}
