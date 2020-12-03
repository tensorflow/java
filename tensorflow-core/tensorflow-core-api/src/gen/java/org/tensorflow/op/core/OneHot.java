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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Returns a one-hot tensor.
 * <p>
 * The locations represented by indices in `indices` take value `on_value`,
 * while all other locations take value `off_value`.
 * <p>
 * If the input `indices` is rank `N`, the output will have rank `N+1`,
 * The new axis is created at dimension `axis` (default: the new axis is
 * appended at the end).
 * <p>
 * If `indices` is a scalar the output shape will be a vector of length `depth`.
 * <p>
 * If `indices` is a vector of length `features`, the output shape will be:
 * <pre>{@code
 *   features x depth if axis == -1
 *   depth x features if axis == 0
 * }</pre>
 * If `indices` is a matrix (batch) with shape `[batch, features]`,
 * the output shape will be:
 * <pre>{@code
 *   batch x features x depth if axis == -1
 *   batch x depth x features if axis == 1
 *   depth x batch x features if axis == 0
 * }</pre>
 * Examples
 * =========
 * <p>
 * Suppose that
 * <pre>{@code
 *   indices = [0, 2, -1, 1]
 *   depth = 3
 *   on_value = 5.0
 *   off_value = 0.0
 *   axis = -1
 * }</pre>
 * Then output is `[4 x 3]`:
 * <pre>{@code
 * output =
 *   [5.0 0.0 0.0]  // one_hot(0)
 *   [0.0 0.0 5.0]  // one_hot(2)
 *   [0.0 0.0 0.0]  // one_hot(-1)
 *   [0.0 5.0 0.0]  // one_hot(1)
 * }</pre>
 * Suppose that
 * <pre>{@code
 *   indices = [0, 2, -1, 1]
 *   depth = 3
 *   on_value = 0.0
 *   off_value = 3.0
 *   axis = 0
 * }</pre>
 * Then output is `[3 x 4]`:
 * <pre>{@code
 * output =
 *   [0.0 3.0 3.0 3.0]
 *   [3.0 3.0 3.0 0.0]
 *   [3.0 3.0 3.0 3.0]
 *   [3.0 0.0 3.0 3.0]
 * //  ^                one_hot(0)
 * //      ^            one_hot(2)
 * //          ^        one_hot(-1)
 * //              ^    one_hot(1)
 * }</pre>
 * Suppose that
 * <pre>{@code
 *   indices = [[0, 2], [1, -1]]
 *   depth = 3
 *   on_value = 1.0
 *   off_value = 0.0
 *   axis = -1
 * }</pre>
 * Then output is `[2 x 2 x 3]`:
 * <pre>{@code
 * output =
 *   [
 *     [1.0, 0.0, 0.0]  // one_hot(0)
 *     [0.0, 0.0, 1.0]  // one_hot(2)
 *   ][
 *     [0.0, 1.0, 0.0]  // one_hot(1)
 *     [0.0, 0.0, 0.0]  // one_hot(-1)
 *   ]
 * }</pre>
 * 
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator
public final class OneHot<U extends TType> extends RawOp implements Operand<U> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.OneHot}
   */
  public static class Options {
    
    /**
     * @param axis The axis to fill (default: -1, a new inner-most axis).
     */
    public Options axis(Long axis) {
      this.axis = axis;
      return this;
    }
    
    private Long axis;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new OneHot operation.
   * 
   * @param scope current scope
   * @param indices A tensor of indices.
   * @param depth A scalar defining the depth of the one hot dimension.
   * @param onValue A scalar defining the value to fill in output when `indices[j] = i`.
   * @param offValue A scalar defining the value to fill in output when `indices[j] != i`.
   * @param options carries optional attributes values
   * @return a new instance of OneHot
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TNumber> OneHot<U> create(Scope scope, Operand<T> indices, Operand<TInt32> depth, Operand<U> onValue, Operand<U> offValue, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("OneHot", scope.makeOpName("OneHot"));
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(depth.asOutput());
    opBuilder.addInput(onValue.asOutput());
    opBuilder.addInput(offValue.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.axis != null) {
          opBuilder.setAttr("axis", opts.axis);
        }
      }
    }
    return new OneHot<U>(opBuilder.build());
  }
  
  /**
   * @param axis The axis to fill (default: -1, a new inner-most axis).
   */
  public static Options axis(Long axis) {
    return new Options().axis(axis);
  }
  
  /**
   * The one-hot tensor.
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "OneHot";
  
  private Output<U> output;
  
  private OneHot(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
