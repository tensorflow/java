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
import org.tensorflow.types.family.TType;

/**
 * Add all input tensors element wise.
 * Inputs must be of same size and shape.
 * <pre>
 * x = [9, 7, 10]
 * tf.math.add_n(x) ==&gt; 26
 * </pre>
 *
 * @param <T> data type for {@code sum} output
 */
@Operator(
    group = "math"
)
public final class AddN<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AddN";

  private Output<T> sum;

  private AddN(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sum = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AddN operation.
   *
   * @param scope current scope
   * @param inputs the inputs value
   * @param <T> data type for {@code AddN} output and operands
   * @return a new instance of AddN
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> AddN<T> create(Scope scope, Iterable<Operand<T>> inputs) {
    OperationBuilder opBuilder = scope.env().opBuilder("AddN", scope.makeOpName("AddN"));
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder = scope.apply(opBuilder);
    return new AddN<>(opBuilder.build());
  }

  /**
   * Gets sum.
   *
   * @return sum.
   */
  public Output<T> sum() {
    return sum;
  }

  @Override
  public Output<T> asOutput() {
    return sum;
  }
}
