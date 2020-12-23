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
 * Finds unique elements along an axis of a tensor.
 * <p>
 * This operation either returns a tensor `y` containing unique elements
 * along the `axis` of a tensor. The returned unique elements is sorted
 * in the same order as they occur along `axis` in `x`.
 * This operation also returns a tensor `idx` and a tensor `count`
 * that are the same size as the number of the elements in `x` along the
 * `axis` dimension. The `idx` contains the index in the unique output `y`
 * and the `count` contains the count in the unique output `y`.
 * In other words, for an `1-D` tensor `x` with `axis = None:
 * <p>
 * `y[idx[i]] = x[i] for i in [0, 1,...,rank(x) - 1]`
 * <p>
 * For example:
 * <pre>{@code
 * # tensor 'x' is [1, 1, 2, 4, 4, 4, 7, 8, 8]
 * y, idx, count = unique_with_counts(x)
 * y ==> [1, 2, 4, 7, 8]
 * idx ==> [0, 0, 1, 2, 2, 2, 3, 4, 4]
 * count ==> [2, 1, 3, 1, 2]
 * }</pre>
 * For an `2-D` tensor `x` with `axis = 0`:
 * <pre>{@code
 * # tensor 'x' is [[1, 0, 0],
 * #                [1, 0, 0],
 * #                [2, 0, 0]]
 * y, idx, count = unique_with_counts(x, axis=0)
 * y ==> [[1, 0, 0],
 *        [2, 0, 0]]
 * idx ==> [0, 0, 1]
 * count ==> [2, 1]
 * }</pre>
 * For an `2-D` tensor `x` with `axis = 1`:
 * <pre>{@code
 * # tensor 'x' is [[1, 0, 0],
 * #                [1, 0, 0],
 * #                [2, 0, 0]]
 * y, idx, count = unique_with_counts(x, axis=1)
 * y ==> [[1, 0],
 *        [1, 0],
 *        [2, 0]]
 * idx ==> [0, 1, 1]
 * count ==> [1, 2]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code y()} output
 * @param <V> data type for {@code idx()} output
 */
@Operator
public final class UniqueWithCounts<T extends TType, V extends TNumber> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new UniqueWithCounts operation.
   * 
   * @param scope current scope
   * @param x A `Tensor`.
   * @param axis A `Tensor` of type `int32` (default: None). The axis of the Tensor to
   * find the unique elements.
   * @param outIdx 
   * @return a new instance of UniqueWithCounts
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, V extends TNumber, U extends TNumber> UniqueWithCounts<T, V> create(Scope scope, Operand<T> x, Operand<U> axis, Class<V> outIdx) {
    OperationBuilder opBuilder = scope.env().opBuilder("UniqueWithCountsV2", scope.makeOpName("UniqueWithCounts"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(axis.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_idx", Operands.toDataType(outIdx));
    return new UniqueWithCounts<T, V>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new UniqueWithCounts operation using default output types.
   * 
   * @param scope current scope
   * @param x A `Tensor`.
   * @param axis A `Tensor` of type `int32` (default: None). The axis of the Tensor to
   * find the unique elements.
   * @return a new instance of UniqueWithCounts
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> UniqueWithCounts<T, TInt32> create(Scope scope, Operand<T> x, Operand<U> axis) {
    return create(scope, x, axis, TInt32.class);
  }
  
  /**
   * A `Tensor`. Unique elements along the `axis` of `Tensor` x.
   */
  public Output<T> y() {
    return y;
  }
  
  /**
   * A 1-D Tensor. Has the same type as x that contains the index of each
   * value of x in the output y.
   */
  public Output<V> idx() {
    return idx;
  }
  
  /**
   * A 1-D Tensor. The count of each value of x in the output y.
   */
  public Output<V> count() {
    return count;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "UniqueWithCountsV2";
  
  private Output<T> y;
  private Output<V> idx;
  private Output<V> count;
  
  private UniqueWithCounts(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
    idx = operation.output(outputIdx++);
    count = operation.output(outputIdx++);
  }
}
