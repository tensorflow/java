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
 * The gradient operator for the SparseSlice op.
 * This op takes in the upstream gradient w.r.t. non-empty values of
 * the sliced {@code SparseTensor}, and outputs the gradients w.r.t.
 * the non-empty values of input {@code SparseTensor}.
 *
 * @param <T> data type for {@code val_grad} output
 */
@OpMetadata(
    opType = SparseSliceGrad.OP_NAME,
    inputsClass = SparseSliceGrad.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseSliceGrad<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseSliceGrad";

  private Output<T> valGrad;

  public SparseSliceGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    valGrad = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseSliceGrad operation.
   *
   * @param scope current scope
   * @param backpropValGrad 1-D. The gradient with respect to
   * the non-empty values of the sliced {@code SparseTensor}.
   * @param inputIndices 2-D.  The {@code indices} of the input {@code SparseTensor}.
   * @param inputStart 1-D. tensor represents the start of the slice.
   * @param outputIndices 2-D.  The {@code indices} of the sliced {@code SparseTensor}.
   * @param <T> data type for {@code SparseSliceGrad} output and operands
   * @return a new instance of SparseSliceGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseSliceGrad<T> create(Scope scope, Operand<T> backpropValGrad,
      Operand<TInt64> inputIndices, Operand<TInt64> inputStart, Operand<TInt64> outputIndices) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseSliceGrad");
    opBuilder.addInput(backpropValGrad.asOutput());
    opBuilder.addInput(inputIndices.asOutput());
    opBuilder.addInput(inputStart.asOutput());
    opBuilder.addInput(outputIndices.asOutput());
    return new SparseSliceGrad<>(opBuilder.build());
  }

  /**
   * Gets valGrad.
   * 1-D. The gradient with respect to the non-empty values of input {@code SparseTensor}.
   * @return valGrad.
   */
  public Output<T> valGrad() {
    return valGrad;
  }

  @Override
  public Output<T> asOutput() {
    return valGrad;
  }

  @OpInputsMetadata(
      outputsClass = SparseSliceGrad.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SparseSliceGrad<T>> {
    /**
     * 1-D. The gradient with respect to
     * the non-empty values of the sliced {@code SparseTensor}.
     */
    public final Operand<T> backpropValGrad;

    /**
     * 2-D.  The {@code indices} of the input {@code SparseTensor}.
     */
    public final Operand<TInt64> inputIndices;

    /**
     * 1-D. tensor represents the start of the slice.
     */
    public final Operand<TInt64> inputStart;

    /**
     * 2-D.  The {@code indices} of the sliced {@code SparseTensor}.
     */
    public final Operand<TInt64> outputIndices;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseSliceGrad<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      backpropValGrad = (Operand<T>) op.input(inputIndex++);
      inputIndices = (Operand<TInt64>) op.input(inputIndex++);
      inputStart = (Operand<TInt64>) op.input(inputIndex++);
      outputIndices = (Operand<TInt64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
