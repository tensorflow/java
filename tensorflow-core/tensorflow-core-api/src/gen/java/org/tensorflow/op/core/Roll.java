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
 * Rolls the elements of a tensor along an axis.
 * <p>
 * The elements are shifted positively (towards larger indices) by the offset of
 * `shift` along the dimension of `axis`. Negative `shift` values will shift
 * elements in the opposite direction. Elements that roll passed the last position
 * will wrap around to the first and vice versa. Multiple shifts along multiple
 * axes may be specified.
 * <p>
 * For example:
 * <pre>{@code
 * # 't' is [0, 1, 2, 3, 4]
 * roll(t, shift=2, axis=0) ==> [3, 4, 0, 1, 2]
 * 
 * # shifting along multiple dimensions
 * # 't' is [[0, 1, 2, 3, 4], [5, 6, 7, 8, 9]]
 * roll(t, shift=[1, -2], axis=[0, 1]) ==> [[7, 8, 9, 5, 6], [2, 3, 4, 0, 1]]
 * 
 * # shifting along the same axis multiple times
 * # 't' is [[0, 1, 2, 3, 4], [5, 6, 7, 8, 9]]
 * roll(t, shift=[2, -3], axis=[1, 1]) ==> [[1, 2, 3, 4, 0], [6, 7, 8, 9, 5]]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class Roll<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Roll operation.
   * 
   * @param scope current scope
   * @param input 
   * @param shift Dimension must be 0-D or 1-D. `shift[i]` specifies the number of places by which
   * elements are shifted positively (towards larger indices) along the dimension
   * specified by `axis[i]`. Negative shifts will roll the elements in the opposite
   * direction.
   * @param axis Dimension must be 0-D or 1-D. `axis[i]` specifies the dimension that the shift
   * `shift[i]` should occur. If the same axis is referenced more than once, the
   * total shift for that axis will be the sum of all the shifts that belong to that
   * axis.
   * @return a new instance of Roll
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber, V extends TNumber> Roll<T> create(Scope scope, Operand<T> input, Operand<U> shift, Operand<V> axis) {
    OperationBuilder opBuilder = scope.env().opBuilder("Roll", scope.makeOpName("Roll"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(shift.asOutput());
    opBuilder.addInput(axis.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Roll<T>(opBuilder.build());
  }
  
  /**
   * Has the same shape and size as the input. The elements are shifted
   * positively (towards larger indices) by the offsets of `shift` along the
   * dimensions of `axis`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Roll";
  
  private Output<T> output;
  
  private Roll(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
