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

package org.tensorflow.op.ragged;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Returns a {@code RaggedTensor} containing the specified sequences of numbers.
 * Returns a {@code RaggedTensor} {@code result} composed from {@code rt_dense_values} and
 * {@code rt_nested_splits}, such that
 * {@code result[i] = range(starts[i], limits[i], deltas[i])}.
 * <pre>
 * (rt_nested_splits, rt_dense_values) = ragged_range(
 *       starts=[2, 5, 8], limits=[3, 5, 12], deltas=1)
 * result = tf.ragged.from_row_splits(rt_dense_values, rt_nested_splits)
 * print(result)
 * &lt;tf.RaggedTensor [[2], [], [8, 9, 10, 11]] &gt;
 * </pre>
 * <p>The input tensors {@code starts}, {@code limits}, and {@code deltas} may be scalars or vectors.
 * The vector inputs must all have the same size.  Scalar inputs are broadcast
 * to match the size of the vector inputs.
 *
 * @param <U> data type for {@code rt_nested_splits} output
 *
 * @param <T> data type for {@code rt_dense_values} output
 */
@OpMetadata(
    opType = RaggedRange.OP_NAME,
    inputsClass = RaggedRange.Inputs.class
)
public final class RaggedRange<U extends TNumber, T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RaggedRange";

  private Output<U> rtNestedSplits;

  private Output<T> rtDenseValues;

  public RaggedRange(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    rtNestedSplits = operation.output(outputIdx++);
    rtDenseValues = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RaggedRange operation.
   *
   * @param scope current scope
   * @param starts The starts of each range.
   * @param limits The limits of each range.
   * @param deltas The deltas of each range.
   * @param Tsplits The value of the Tsplits attribute
   * @param <U> data type for {@code RaggedRange} output and operands
   * @param <T> data type for {@code RaggedRange} output and operands
   * @return a new instance of RaggedRange
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber, T extends TNumber> RaggedRange<U, T> create(Scope scope,
      Operand<T> starts, Operand<T> limits, Operand<T> deltas, Class<U> Tsplits) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RaggedRange");
    opBuilder.addInput(starts.asOutput());
    opBuilder.addInput(limits.asOutput());
    opBuilder.addInput(deltas.asOutput());
    opBuilder.setAttr("Tsplits", Operands.toDataType(Tsplits));
    return new RaggedRange<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new RaggedRange operation, with the default output types.
   *
   * @param scope current scope
   * @param starts The starts of each range.
   * @param limits The limits of each range.
   * @param deltas The deltas of each range.
   * @param <T> data type for {@code RaggedRange} output and operands
   * @return a new instance of RaggedRange, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RaggedRange<TInt64, T> create(Scope scope, Operand<T> starts,
      Operand<T> limits, Operand<T> deltas) {
    return create(scope, starts, limits, deltas, TInt64.class);
  }

  /**
   * Gets rtNestedSplits.
   * The {@code row_splits} for the returned {@code RaggedTensor}.
   * @return rtNestedSplits.
   */
  public Output<U> rtNestedSplits() {
    return rtNestedSplits;
  }

  /**
   * Gets rtDenseValues.
   * The {@code flat_values} for the returned {@code RaggedTensor}.
   * @return rtDenseValues.
   */
  public Output<T> rtDenseValues() {
    return rtDenseValues;
  }

  @OpInputsMetadata(
      outputsClass = RaggedRange.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<RaggedRange<?, T>> {
    /**
     * The starts of each range.
     */
    public final Operand<T> starts;

    /**
     * The limits of each range.
     */
    public final Operand<T> limits;

    /**
     * The deltas of each range.
     */
    public final Operand<T> deltas;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tsplits attribute
     */
    public final DataType Tsplits;

    public Inputs(GraphOperation op) {
      super(new RaggedRange<>(op), op, Arrays.asList("T", "Tsplits"));
      int inputIndex = 0;
      starts = (Operand<T>) op.input(inputIndex++);
      limits = (Operand<T>) op.input(inputIndex++);
      deltas = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tsplits = op.attributes().getAttrType("Tsplits");
    }
  }
}
