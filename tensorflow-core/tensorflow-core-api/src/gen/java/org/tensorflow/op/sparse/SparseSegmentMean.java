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

package org.tensorflow.op.sparse;

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
 * Computes the mean along sparse segments of a tensor.
 * See {@code tf.sparse.segment_sum} for usage examples.
 * <p>Like {@code SegmentMean}, but {@code segment_ids} can have rank less than {@code data}'s first
 * dimension, selecting a subset of dimension 0, specified by {@code indices}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = SparseSegmentMean.OP_NAME,
    inputsClass = SparseSegmentMean.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseSegmentMean<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseSegmentMean";

  private Output<T> output;

  public SparseSegmentMean(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseSegmentMean operation.
   *
   * @param scope current scope
   * @param data The data value
   * @param indices A 1-D tensor. Has same rank as {@code segment_ids}.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param <T> data type for {@code SparseSegmentMean} output and operands
   * @return a new instance of SparseSegmentMean
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> SparseSegmentMean<T> create(Scope scope, Operand<T> data,
      Operand<? extends TNumber> indices, Operand<? extends TNumber> segmentIds) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseSegmentMean");
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(segmentIds.asOutput());
    return new SparseSegmentMean<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Has same shape as data, except for dimension 0 which
   * has size {@code k}, the number of segments.
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
      outputsClass = SparseSegmentMean.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<SparseSegmentMean<T>> {
    /**
     * The data input
     */
    public final Operand<T> data;

    /**
     * A 1-D tensor. Has same rank as {@code segment_ids}.
     */
    public final Operand<? extends TNumber> indices;

    /**
     * A 1-D tensor. Values should be sorted and can be repeated.
     */
    public final Operand<? extends TNumber> segmentIds;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tidx attribute
     */
    public final DataType Tidx;

    /**
     * The Tsegmentids attribute
     */
    public final DataType Tsegmentids;

    public Inputs(GraphOperation op) {
      super(new SparseSegmentMean<>(op), op, Arrays.asList("T", "Tidx", "Tsegmentids"));
      int inputIndex = 0;
      data = (Operand<T>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      segmentIds = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tidx = op.attributes().getAttrType("Tidx");
      Tsegmentids = op.attributes().getAttrType("Tsegmentids");
    }
  }
}
