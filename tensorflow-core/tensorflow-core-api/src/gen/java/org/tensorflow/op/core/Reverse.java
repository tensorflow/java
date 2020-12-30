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
 * Reverses specific dimensions of a tensor.
 * <p>
 * NOTE `tf.reverse` has now changed behavior in preparation for 1.0.
 * `tf.reverse_v2` is currently an alias that will be deprecated before TF 1.0.
 * <p>
 * Given a `tensor`, and a `int32` tensor `axis` representing the set of
 * dimensions of `tensor` to reverse. This operation reverses each dimension
 * `i` for which there exists `j` s.t. `axis[j] == i`.
 * <p>
 * `tensor` can have up to 8 dimensions. The number of dimensions specified
 * in `axis` may be 0 or more entries. If an index is specified more than
 * once, a InvalidArgument error is raised.
 * <p>
 * For example:
 * <pre>{@code
 * # tensor 't' is [[[[ 0,  1,  2,  3],
 * #                  [ 4,  5,  6,  7],
 * #                  [ 8,  9, 10, 11]],
 * #                 [[12, 13, 14, 15],
 * #                  [16, 17, 18, 19],
 * #                  [20, 21, 22, 23]]]]
 * # tensor 't' shape is [1, 2, 3, 4]
 * 
 * # 'dims' is [3] or 'dims' is [-1]
 * reverse(t, dims) ==> [[[[ 3,  2,  1,  0],
 *                         [ 7,  6,  5,  4],
 *                         [ 11, 10, 9, 8]],
 *                        [[15, 14, 13, 12],
 *                         [19, 18, 17, 16],
 *                         [23, 22, 21, 20]]]]
 * 
 * # 'dims' is '[1]' (or 'dims' is '[-3]')
 * reverse(t, dims) ==> [[[[12, 13, 14, 15],
 *                         [16, 17, 18, 19],
 *                         [20, 21, 22, 23]
 *                        [[ 0,  1,  2,  3],
 *                         [ 4,  5,  6,  7],
 *                         [ 8,  9, 10, 11]]]]
 * 
 * # 'dims' is '[2]' (or 'dims' is '[-2]')
 * reverse(t, dims) ==> [[[[8, 9, 10, 11],
 *                         [4, 5, 6, 7],
 *                         [0, 1, 2, 3]]
 *                        [[20, 21, 22, 23],
 *                         [16, 17, 18, 19],
 *                         [12, 13, 14, 15]]]]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class Reverse<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Reverse operation.
   * 
   * @param scope current scope
   * @param tensor Up to 8-D.
   * @param axis 1-D. The indices of the dimensions to reverse. Must be in the range
   * `[-rank(tensor), rank(tensor))`.
   * @return a new instance of Reverse
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> Reverse<T> create(Scope scope, Operand<T> tensor, Operand<U> axis) {
    OperationBuilder opBuilder = scope.env().opBuilder("ReverseV2", scope.makeOpName("Reverse"));
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(axis.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Reverse<T>(opBuilder.build());
  }
  
  /**
   * The same shape as `tensor`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ReverseV2";
  
  private Output<T> output;
  
  private Reverse(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
