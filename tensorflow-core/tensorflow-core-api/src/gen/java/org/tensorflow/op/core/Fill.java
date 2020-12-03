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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Creates a tensor filled with a scalar value.
 * <p>
 * This operation creates a tensor of shape `dims` and fills it with `value`.
 * <p>
 * For example:
 * <pre>{@code
 * # Output tensor has shape [2, 3].
 * fill([2, 3], 9) ==> [[9, 9, 9]
 *                      [9, 9, 9]]
 * }</pre>
 * `tf.fill` differs from `tf.constant` in a few ways:
 * <ul>
 * <li>
 * `tf.fill` only supports scalar contents, whereas `tf.constant` supports
 *     Tensor values.
 * </li>
 * <li>
 * `tf.fill` creates an Op in the computation graph that constructs the actual
 *     Tensor value at runtime. This is in contrast to `tf.constant` which embeds
 *     the entire Tensor into the graph with a `Const` node.
 * </li>
 * <li>
 * Because `tf.fill` evaluates at graph runtime, it supports dynamic shapes
 *     based on other runtime Tensors, unlike `tf.constant`.
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator
public final class Fill<U extends TType> extends RawOp implements Operand<U> {
  
  /**
   * Factory method to create a class wrapping a new Fill operation.
   * 
   * @param scope current scope
   * @param dims 1-D. Represents the shape of the output tensor.
   * @param value 0-D (scalar). Value to fill the returned tensor.
   * <p>
   * @compatibility(numpy)
   * Equivalent to np.full
   * @end_compatibility
   * @return a new instance of Fill
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TNumber> Fill<U> create(Scope scope, Operand<T> dims, Operand<U> value) {
    OperationBuilder opBuilder = scope.env().opBuilder("Fill", scope.makeOpName("Fill"));
    opBuilder.addInput(dims.asOutput());
    opBuilder.addInput(value.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Fill<U>(opBuilder.build());
  }
  
  /**
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Fill";
  
  private Output<U> output;
  
  private Fill(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
