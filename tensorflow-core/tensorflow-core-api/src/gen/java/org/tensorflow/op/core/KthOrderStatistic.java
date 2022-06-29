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
import org.tensorflow.types.TFloat32;

/**
 * Computes the Kth order statistic of a data set. The current
 * implementation uses a binary search requiring exactly 32 passes over
 * the input data. The running time is linear with respect to input
 * size. The median-of-medians algorithm is probably faster, but is
 * difficult to implement efficiently in XLA. The implementation imposes
 * a total ordering on floats. The ordering is consistent with the usual
 * partial order.  Positive NaNs are greater than positive
 * infinity. Negative NaNs are less than negative infinity. NaNs with
 * distinct payloads are treated as distinct. Subnormal numbers are
 * preserved (not flushed to zero). Positive infinity is greater than all
 * numbers. Negative infinity is less than all numbers. Positive is
 * greater than negative zero. There are less than k values greater than
 * the kth order statistic. There are at least k values greater than or
 * equal to the Kth order statistic. The semantics are not the same as
 * top_k_unique.
 */
@OpMetadata(
    opType = KthOrderStatistic.OP_NAME,
    inputsClass = KthOrderStatistic.Inputs.class
)
@Operator
public final class KthOrderStatistic extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "KthOrderStatistic";

  private Output<TFloat32> output;

  public KthOrderStatistic(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new KthOrderStatistic operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param k The value of the k attribute
   * @return a new instance of KthOrderStatistic
   */
  @Endpoint(
      describeByClass = true
  )
  public static KthOrderStatistic create(Scope scope, Operand<TFloat32> input, Long k) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "KthOrderStatistic");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("k", k);
    return new KthOrderStatistic(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<TFloat32> output() {
    return output;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = KthOrderStatistic.class
  )
  public static class Inputs extends RawOpInputs<KthOrderStatistic> {
    /**
     * The input input
     */
    public final Operand<TFloat32> input;

    /**
     * The k attribute
     */
    public final long k;

    public Inputs(GraphOperation op) {
      super(new KthOrderStatistic(op), op, Arrays.asList("k"));
      int inputIndex = 0;
      input = (Operand<TFloat32>) op.input(inputIndex++);
      k = op.attributes().getAttrInt("k");
    }
  }
}
