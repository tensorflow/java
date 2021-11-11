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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes gradients for SparseSegmentSum.
 * Returns tensor &quot;output&quot; with same shape as grad, except for dimension 0 whose
 * value is output_dim0.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = SparseSegmentSumGrad.OP_NAME,
    inputsClass = SparseSegmentSumGrad.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseSegmentSumGrad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseSegmentSumGrad";

  private Output<T> output;

  public SparseSegmentSumGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseSegmentSumGrad operation.
   *
   * @param scope current scope
   * @param grad gradient propagated to the SparseSegmentSum op.
   * @param indices indices passed to the corresponding SparseSegmentSum op.
   * @param segmentIds segment_ids passed to the corresponding SparseSegmentSum op.
   * @param outputDim0 dimension 0 of &quot;data&quot; passed to SparseSegmentSum op.
   * @param <T> data type for {@code SparseSegmentSumGrad} output and operands
   * @return a new instance of SparseSegmentSumGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> SparseSegmentSumGrad<T> create(Scope scope, Operand<T> grad,
      Operand<? extends TNumber> indices, Operand<? extends TNumber> segmentIds,
      Operand<TInt32> outputDim0) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseSegmentSumGrad");
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(segmentIds.asOutput());
    opBuilder.addInput(outputDim0.asOutput());
    return new SparseSegmentSumGrad<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
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
      outputsClass = SparseSegmentSumGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<SparseSegmentSumGrad<T>> {
    /**
     * gradient propagated to the SparseSegmentSum op.
     */
    public final Operand<T> grad;

    /**
     * indices passed to the corresponding SparseSegmentSum op.
     */
    public final Operand<? extends TNumber> indices;

    /**
     * segment_ids passed to the corresponding SparseSegmentSum op.
     */
    public final Operand<? extends TNumber> segmentIds;

    /**
     * dimension 0 of &quot;data&quot; passed to SparseSegmentSum op.
     */
    public final Operand<TInt32> outputDim0;

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
      super(new SparseSegmentSumGrad<>(op), op, Arrays.asList("T", "Tidx", "Tsegmentids"));
      int inputIndex = 0;
      grad = (Operand<T>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      segmentIds = (Operand<? extends TNumber>) op.input(inputIndex++);
      outputDim0 = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tidx = op.attributes().getAttrType("Tidx");
      Tsegmentids = op.attributes().getAttrType("Tsegmentids");
    }
  }
}
