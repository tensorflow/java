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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Counts the number of occurrences of each value in an integer array.
 * <p>
 * Outputs a vector with length `size` and the same dtype as `weights`. If
 * `weights` are empty, then index `i` stores the number of times the value `i` is
 * counted in `arr`. If `weights` are non-empty, then index `i` stores the sum of
 * the value in `weights` at each index where the corresponding value in `arr` is
 * `i`.
 * <p>
 * Values in `arr` outside of the range [0, size) are ignored.
 * 
 * @param <T> data type for {@code bins()} output
 */
@Operator(group = "math")
public final class Bincount<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Bincount operation.
   * 
   * @param scope current scope
   * @param arr int32 `Tensor`.
   * @param size non-negative int32 scalar `Tensor`.
   * @param weights is an int32, int64, float32, or float64 `Tensor` with the same
   * shape as `arr`, or a length-0 `Tensor`, in which case it acts as all weights
   * equal to 1.
   * @return a new instance of Bincount
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> Bincount<T> create(Scope scope, Operand<TInt32> arr, Operand<TInt32> size, Operand<T> weights) {
    OperationBuilder opBuilder = scope.env().opBuilder("Bincount", scope.makeOpName("Bincount"));
    opBuilder.addInput(arr.asOutput());
    opBuilder.addInput(size.asOutput());
    opBuilder.addInput(weights.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Bincount<T>(opBuilder.build());
  }
  
  /**
   * 1D `Tensor` with length equal to `size`. The counts or summed weights for
   * each value in the range [0, size).
   */
  public Output<T> bins() {
    return bins;
  }
  
  @Override
  public Output<T> asOutput() {
    return bins;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Bincount";
  
  private Output<T> bins;
  
  private Bincount(Operation operation) {
    super(operation);
    int outputIdx = 0;
    bins = operation.output(outputIdx++);
  }
}
