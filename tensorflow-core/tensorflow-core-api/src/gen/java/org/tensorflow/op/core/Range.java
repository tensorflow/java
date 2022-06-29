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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Creates a sequence of numbers.
 * This operation creates a sequence of numbers that begins at {@code start} and
 * extends by increments of {@code delta} up to but not including {@code limit}.
 * <p>For example:
 * <pre>
 * # 'start' is 3
 * # 'limit' is 18
 * # 'delta' is 3
 * tf.range(start, limit, delta) ==&gt; [3, 6, 9, 12, 15]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Range.OP_NAME,
    inputsClass = Range.Inputs.class
)
@Operator
public final class Range<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Range";

  private Output<T> output;

  public Range(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Range operation.
   *
   * @param scope current scope
   * @param start 0-D (scalar). First entry in the sequence.
   * @param limit 0-D (scalar). Upper limit of sequence, exclusive.
   * @param delta 0-D (scalar). Optional. Default is 1. Number that increments {@code start}.
   * @param <T> data type for {@code Range} output and operands
   * @return a new instance of Range
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Range<T> create(Scope scope, Operand<T> start, Operand<T> limit,
      Operand<T> delta) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Range");
    opBuilder.addInput(start.asOutput());
    opBuilder.addInput(limit.asOutput());
    opBuilder.addInput(delta.asOutput());
    return new Range<>(opBuilder.build());
  }

  /**
   * Gets output.
   * 1-D.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = Range.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<Range<T>> {
    /**
     * 0-D (scalar). First entry in the sequence.
     */
    public final Operand<T> start;

    /**
     * 0-D (scalar). Upper limit of sequence, exclusive.
     */
    public final Operand<T> limit;

    /**
     * 0-D (scalar). Optional. Default is 1. Number that increments {@code start}.
     */
    public final Operand<T> delta;

    /**
     * The Tidx attribute
     */
    public final DataType Tidx;

    public Inputs(GraphOperation op) {
      super(new Range<>(op), op, Arrays.asList("Tidx"));
      int inputIndex = 0;
      start = (Operand<T>) op.input(inputIndex++);
      limit = (Operand<T>) op.input(inputIndex++);
      delta = (Operand<T>) op.input(inputIndex++);
      Tidx = op.attributes().getAttrType("Tidx");
    }
  }
}
