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
 * Computes the complex absolute value of a tensor.
 * Given a tensor {@code x} of complex numbers, this operation returns a tensor of type
 * {@code float} or {@code double} that is the absolute value of each element in {@code x}. All
 * elements in {@code x} must be complex numbers of the form \(a + bj\). The absolute
 * value is computed as \( \sqrt{a^2 + b^2}\).
 *
 * @param <U> data type for {@code y} output
 */
@Operator(
    group = "math"
)
public final class ComplexAbs<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ComplexAbs";

  private Output<U> y;

  private ComplexAbs(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ComplexAbs operation.
   *
   * @param scope current scope
   * @param x the x value
   * @param Tout the value of the Tout property
   * @param <U> data type for {@code ComplexAbs} output and operands
   * @return a new instance of ComplexAbs
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> ComplexAbs<U> create(Scope scope, Operand<? extends TType> x,
      Class<U> Tout) {
    OperationBuilder opBuilder = scope.env().opBuilder("ComplexAbs", scope.makeOpName("ComplexAbs"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("Tout", Operands.toDataType(Tout));
    return new ComplexAbs<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new ComplexAbs operation, with the default output types.
   *
   * @param scope current scope
   * @param x the x value
   * @return a new instance of ComplexAbs, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static ComplexAbs<TFloat32> create(Scope scope, Operand<? extends TType> x) {
    return create(scope, x, TFloat32.class);
  }

  /**
   * Gets y.
   *
   * @return y.
   */
  public Output<U> y() {
    return y;
  }

  @Override
  public Output<U> asOutput() {
    return y;
  }
}
