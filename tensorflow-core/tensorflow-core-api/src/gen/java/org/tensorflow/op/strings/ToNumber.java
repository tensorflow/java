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

package org.tensorflow.op.strings;

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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * Converts each string in the input Tensor to the specified numeric type.
 * (Note that int32 overflow results in an error while float overflow
 * results in a rounded value.)
 * <p>Example:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>strings = [&quot;5.0&quot;, &quot;3.0&quot;, &quot;7.0&quot;]
 * tf.strings.to_number(strings)
 * &lt;tf.Tensor: shape=(3,), dtype=float32, numpy=array([5., 3., 7.], dtype=float32)&gt;
 * </blockquote>
 * </blockquote>
 * </blockquote>
 *
 * @param <T> data type for {@code output} output
 */
@Operator(
    group = "strings"
)
public final class ToNumber<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StringToNumber";

  private Output<T> output;

  private ToNumber(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StringToNumber operation.
   *
   * @param scope current scope
   * @param stringTensor the stringTensor value
   * @param outType The numeric type to interpret each string in {@code string_tensor} as.
   * @param <T> data type for {@code StringToNumber} output and operands
   * @return a new instance of ToNumber
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> ToNumber<T> create(Scope scope, Operand<TString> stringTensor,
      Class<T> outType) {
    OperationBuilder opBuilder = scope.env().opBuilder("StringToNumber", scope.makeOpName("ToNumber"));
    opBuilder.addInput(stringTensor.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new ToNumber<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new StringToNumber operation, with the default output types.
   *
   * @param scope current scope
   * @param stringTensor the stringTensor value
   * @return a new instance of ToNumber, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static ToNumber<TFloat32> create(Scope scope, Operand<TString> stringTensor) {
    return create(scope, stringTensor, TFloat32.class);
  }

  /**
   * Gets output.
   * A Tensor of the same shape as the input {@code string_tensor}.
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
