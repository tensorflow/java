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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Returns the size of a tensor.
 * This operation returns an integer representing the number of elements in
 * {@code input}.
 * <p>For example:
 * <pre>
 * # 't' is [[[1, 1,, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]]
 * size(t) ==&gt; 12
 * </pre>
 *
 * @param <U> data type for {@code output} output
 */
@Operator
public final class Size<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Size";

  private Output<U> output;

  private Size(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Size operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param outType the value of the outType property
   * @param <U> data type for {@code Size} output and operands
   * @return a new instance of Size
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> Size<U> create(Scope scope, Operand<? extends TType> input,
      Class<U> outType) {
    OperationBuilder opBuilder = scope.env().opBuilder("Size", scope.makeOpName("Size"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new Size<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new Size operation, with the default output types.
   *
   * @param scope current scope
   * @param input the input value
   * @return a new instance of Size, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static Size<TInt32> create(Scope scope, Operand<? extends TType> input) {
    return create(scope, input, TInt32.class);
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
