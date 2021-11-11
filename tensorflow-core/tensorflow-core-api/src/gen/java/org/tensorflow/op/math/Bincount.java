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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Counts the number of occurrences of each value in an integer array.
 * Outputs a vector with length {@code size} and the same dtype as {@code weights}. If
 * {@code weights} are empty, then index {@code i} stores the number of times the value {@code i} is
 * counted in {@code arr}. If {@code weights} are non-empty, then index {@code i} stores the sum of
 * the value in {@code weights} at each index where the corresponding value in {@code arr} is
 * {@code i}.
 * <p>Values in {@code arr} outside of the range [0, size) are ignored.
 *
 * @param <T> data type for {@code bins} output
 */
@OpMetadata(
    opType = Bincount.OP_NAME,
    inputsClass = Bincount.Inputs.class
)
@Operator(
    group = "math"
)
public final class Bincount<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Bincount";

  private Output<T> bins;

  public Bincount(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    bins = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Bincount operation.
   *
   * @param scope current scope
   * @param arr int32 {@code Tensor}.
   * @param sizeOutput non-negative int32 scalar {@code Tensor}.
   * @param weights is an int32, int64, float32, or float64 {@code Tensor} with the same
   * shape as {@code arr}, or a length-0 {@code Tensor}, in which case it acts as all weights
   * equal to 1.
   * @param <T> data type for {@code Bincount} output and operands
   * @return a new instance of Bincount
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Bincount<T> create(Scope scope, Operand<TInt32> arr,
      Operand<TInt32> sizeOutput, Operand<T> weights) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Bincount");
    opBuilder.addInput(arr.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    opBuilder.addInput(weights.asOutput());
    return new Bincount<>(opBuilder.build());
  }

  /**
   * Gets bins.
   * 1D {@code Tensor} with length equal to {@code size}. The counts or summed weights for
   * each value in the range [0, size).
   * @return bins.
   */
  public Output<T> bins() {
    return bins;
  }

  @Override
  public Output<T> asOutput() {
    return bins;
  }

  @OpInputsMetadata(
      outputsClass = Bincount.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<Bincount<T>> {
    /**
     * int32 {@code Tensor}.
     */
    public final Operand<TInt32> arr;

    /**
     * non-negative int32 scalar {@code Tensor}.
     */
    public final Operand<TInt32> sizeOutput;

    /**
     * is an int32, int64, float32, or float64 {@code Tensor} with the same
     * shape as {@code arr}, or a length-0 {@code Tensor}, in which case it acts as all weights
     * equal to 1.
     */
    public final Operand<T> weights;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Bincount<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      arr = (Operand<TInt32>) op.input(inputIndex++);
      sizeOutput = (Operand<TInt32>) op.input(inputIndex++);
      weights = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
