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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Computes the inverse permutation of a tensor.
 * <p>
 * This operation computes the inverse of an index permutation. It takes a 1-D
 * integer tensor `x`, which represents the indices of a zero-based array, and
 * swaps each value with its index position. In other words, for an output tensor
 * `y` and an input tensor `x`, this operation computes the following:
 * <p>
 * `y[x[i]] = i for i in [0, 1, ..., len(x) - 1]`
 * <p>
 * The values must include 0. There can be no duplicate values or negative values.
 * <p>
 * For example:
 * <pre>{@code
 * # tensor `x` is [3, 4, 0, 2, 1]
 * invert_permutation(x) ==> [2, 4, 3, 0, 1]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code y()} output
 */
@Operator(group = "math")
public final class InvertPermutation<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new InvertPermutation operation.
   * 
   * @param scope current scope
   * @param x 1-D.
   * @return a new instance of InvertPermutation
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> InvertPermutation<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.env().opBuilder("InvertPermutation", scope.makeOpName("InvertPermutation"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new InvertPermutation<T>(opBuilder.build());
  }
  
  /**
   * 1-D.
   */
  public Output<T> y() {
    return y;
  }
  
  @Override
  public Output<T> asOutput() {
    return y;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "InvertPermutation";
  
  private Output<T> y;
  
  private InvertPermutation(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }
}
