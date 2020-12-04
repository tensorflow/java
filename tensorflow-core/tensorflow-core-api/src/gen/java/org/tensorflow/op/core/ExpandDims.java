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
 * Inserts a dimension of 1 into a tensor's shape.
 * <p>
 * Given a tensor `input`, this operation inserts a dimension of 1 at the
 * dimension index `axis` of `input`'s shape. The dimension index `axis` starts at
 * zero; if you specify a negative number for `axis` it is counted backward from
 * the end.
 * <p>
 * This operation is useful if you want to add a batch dimension to a single
 * element. For example, if you have a single image of shape `[height, width,
 * channels]`, you can make it a batch of 1 image with `expand_dims(image, 0)`,
 * which will make the shape `[1, height, width, channels]`.
 * <p>
 * Other examples:
 * <pre>{@code
 * # 't' is a tensor of shape [2]
 * shape(expand_dims(t, 0)) ==> [1, 2]
 * shape(expand_dims(t, 1)) ==> [2, 1]
 * shape(expand_dims(t, -1)) ==> [2, 1]
 * 
 * # 't2' is a tensor of shape [2, 3, 5]
 * shape(expand_dims(t2, 0)) ==> [1, 2, 3, 5]
 * shape(expand_dims(t2, 2)) ==> [2, 3, 1, 5]
 * shape(expand_dims(t2, 3)) ==> [2, 3, 5, 1]
 * }</pre>
 * This operation requires that:
 * <p>
 * `-1-input.dims() <= dim <= input.dims()`
 * <p>
 * This operation is related to `squeeze()`, which removes dimensions of
 * size 1.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class ExpandDims<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new ExpandDims operation.
   * 
   * @param scope current scope
   * @param input 
   * @param axis 0-D (scalar). Specifies the dimension index at which to
   * expand the shape of `input`. Must be in the range
   * `[-rank(input) - 1, rank(input)]`.
   * @return a new instance of ExpandDims
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> ExpandDims<T> create(Scope scope, Operand<T> input, Operand<U> axis) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExpandDims", scope.makeOpName("ExpandDims"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(axis.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new ExpandDims<T>(opBuilder.build());
  }
  
  /**
   * Contains the same data as `input`, but its shape has an additional
   * dimension of size 1 added.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ExpandDims";
  
  private Output<T> output;
  
  private ExpandDims(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
