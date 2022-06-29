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

package org.tensorflow.op.linalg.sparse;

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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Calculates the gradient of the SparseMatrixSoftmax op.
 */
@OpMetadata(
    opType = SparseMatrixSoftmaxGrad.OP_NAME,
    inputsClass = SparseMatrixSoftmaxGrad.Inputs.class
)
public final class SparseMatrixSoftmaxGrad extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseMatrixSoftmaxGrad";

  private Output<? extends TType> gradient;

  @SuppressWarnings("unchecked")
  public SparseMatrixSoftmaxGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    gradient = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseMatrixSoftmaxGrad operation.
   *
   * @param scope current scope
   * @param softmax A CSRSparseMatrix.
   * @param gradSoftmax The gradient of {@code softmax}.
   * @param type The value of the type attribute
   * @param <T> data type for {@code SparseMatrixSoftmaxGrad} output and operands
   * @return a new instance of SparseMatrixSoftmaxGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> SparseMatrixSoftmaxGrad create(Scope scope,
      Operand<? extends TType> softmax, Operand<? extends TType> gradSoftmax, Class<T> type) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseMatrixSoftmaxGrad");
    opBuilder.addInput(softmax.asOutput());
    opBuilder.addInput(gradSoftmax.asOutput());
    opBuilder.setAttr("type", Operands.toDataType(type));
    return new SparseMatrixSoftmaxGrad(opBuilder.build());
  }

  /**
   * Gets gradient.
   * The output gradient.
   * @return gradient.
   */
  public Output<? extends TType> gradient() {
    return gradient;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) gradient;
  }

  @OpInputsMetadata(
      outputsClass = SparseMatrixSoftmaxGrad.class
  )
  public static class Inputs extends RawOpInputs<SparseMatrixSoftmaxGrad> {
    /**
     * A CSRSparseMatrix.
     */
    public final Operand<? extends TType> softmax;

    /**
     * The gradient of {@code softmax}.
     */
    public final Operand<? extends TType> gradSoftmax;

    /**
     * The type attribute
     */
    public final DataType type;

    public Inputs(GraphOperation op) {
      super(new SparseMatrixSoftmaxGrad(op), op, Arrays.asList("type"));
      int inputIndex = 0;
      softmax = (Operand<? extends TType>) op.input(inputIndex++);
      gradSoftmax = (Operand<? extends TType>) op.input(inputIndex++);
      type = op.attributes().getAttrType("type");
    }
  }
}
