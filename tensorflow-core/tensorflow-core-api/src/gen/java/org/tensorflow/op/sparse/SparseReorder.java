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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Reorders a SparseTensor into the canonical, row-major ordering.
 * Note that by convention, all sparse ops preserve the canonical ordering along
 * increasing dimension number. The only time ordering can be violated is during
 * manual manipulation of the indices and values vectors to add entries.
 * <p>Reordering does not affect the shape of the SparseTensor.
 * <p>If the tensor has rank {@code R} and {@code N} non-empty values, {@code input_indices} has
 * shape {@code [N, R]}, input_values has length {@code N}, and input_shape has length {@code R}.
 *
 * @param <T> data type for {@code output_values} output
 */
@OpMetadata(
    opType = SparseReorder.OP_NAME,
    inputsClass = SparseReorder.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseReorder<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseReorder";

  private Output<TInt64> outputIndices;

  private Output<T> outputValues;

  public SparseReorder(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseReorder operation.
   *
   * @param scope current scope
   * @param inputIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   * SparseTensor, possibly not in canonical ordering.
   * @param inputValues 1-D.  {@code N} non-empty values corresponding to {@code input_indices}.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param <T> data type for {@code SparseReorder} output and operands
   * @return a new instance of SparseReorder
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseReorder<T> create(Scope scope, Operand<TInt64> inputIndices,
      Operand<T> inputValues, Operand<TInt64> inputShape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseReorder");
    opBuilder.addInput(inputIndices.asOutput());
    opBuilder.addInput(inputValues.asOutput());
    opBuilder.addInput(inputShape.asOutput());
    return new SparseReorder<>(opBuilder.build());
  }

  /**
   * Gets outputIndices.
   * 2-D.  {@code N x R} matrix with the same indices as input_indices, but
   * in canonical row-major ordering.
   * @return outputIndices.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }

  /**
   * Gets outputValues.
   * 1-D.  {@code N} non-empty values corresponding to {@code output_indices}.
   * @return outputValues.
   */
  public Output<T> outputValues() {
    return outputValues;
  }

  @OpInputsMetadata(
      outputsClass = SparseReorder.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SparseReorder<T>> {
    /**
     * 2-D.  {@code N x R} matrix with the indices of non-empty values in a
     * SparseTensor, possibly not in canonical ordering.
     */
    public final Operand<TInt64> inputIndices;

    /**
     * 1-D.  {@code N} non-empty values corresponding to {@code input_indices}.
     */
    public final Operand<T> inputValues;

    /**
     * 1-D.  Shape of the input SparseTensor.
     */
    public final Operand<TInt64> inputShape;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseReorder<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      inputIndices = (Operand<TInt64>) op.input(inputIndex++);
      inputValues = (Operand<T>) op.input(inputIndex++);
      inputShape = (Operand<TInt64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
