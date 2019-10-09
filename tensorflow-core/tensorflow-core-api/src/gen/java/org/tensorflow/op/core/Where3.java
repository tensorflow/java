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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;

/**
 * Selects elements from `x` or `y`, depending on `condition`.
 * <p>
 * The `x`, and `y` tensors must all have the same shape, and the
 * output will also have that shape.
 * <p>
 * The `condition` tensor must be a scalar if `x` and `y` are scalars.
 * If `x` and `y` are vectors or higher rank, then `condition` must be either a
 * scalar, a vector with size matching the first dimension of `x`, or must have
 * the same shape as `x`.
 * <p>
 * The `condition` tensor acts as a mask that chooses, based on the value at each
 * element, whether the corresponding element / row in the output should be
 * taken from `x` (if true) or `y` (if false).
 * <p>
 * If `condition` is a vector and `x` and `y` are higher rank matrices, then
 * it chooses which row (outer dimension) to copy from `x` and `y`.
 * If `condition` has the same shape as `x` and `y`, then it chooses which
 * element to copy from `x` and `y`.
 * <p>
 * For example:
 * <pre>{@code
 * # 'condition' tensor is [[True,  False]
 * #                        [False, True]]
 * # 't' is [[1, 2],
 * #         [3, 4]]
 * # 'e' is [[5, 6],
 * #         [7, 8]]
 * select(condition, t, e)  # => [[1, 6], [7, 4]]
 * 
 * 
 * # 'condition' tensor is [True, False]
 * # 't' is [[1, 2],
 * #         [3, 4]]
 * # 'e' is [[5, 6],
 * #         [7, 8]]
 * select(condition, t, e) ==> [[1, 2],
 *                              [7, 8]]
 * 
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class Where3<T> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Where3 operation.
   * 
   * @param scope current scope
   * @param condition 
   * @param x = A `Tensor` which may have the same shape as `condition`.
   * If `condition` is rank 1, `x` may have higher rank,
   * but its first dimension must match the size of `condition`.
   * @param y = A `Tensor` with the same type and shape as `x`.
   * @return a new instance of Where3
   */
  public static <T> Where3<T> create(Scope scope, Operand<TBool> condition, Operand<T> x, Operand<T> y) {
    OperationBuilder opBuilder = scope.env().opBuilder("Select", scope.makeOpName("Where3"));
    opBuilder.addInput(condition.asOutput());
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new Where3<T>(opBuilder.build());
  }
  
  /**
   * = A `Tensor` with the same type and shape as `x` and `y`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  private Output<T> output;
  
  private Where3(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
