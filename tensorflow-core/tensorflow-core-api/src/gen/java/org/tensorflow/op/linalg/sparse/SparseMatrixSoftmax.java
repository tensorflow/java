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
 * Calculates the softmax of a CSRSparseMatrix.
 * Calculate the softmax of the innermost dimensions of a SparseMatrix.
 * <p>Missing values are treated as {@code -inf} (i.e., logits of zero probability); and
 * the output has the same sparsity structure as the input (though missing values
 * in the output may now be treated as having probability zero).
 */
@OpMetadata(
    opType = SparseMatrixSoftmax.OP_NAME,
    inputsClass = SparseMatrixSoftmax.Inputs.class
)
public final class SparseMatrixSoftmax extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseMatrixSoftmax";

  private Output<? extends TType> softmax;

  @SuppressWarnings("unchecked")
  public SparseMatrixSoftmax(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    softmax = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseMatrixSoftmax operation.
   *
   * @param scope current scope
   * @param logits A CSRSparseMatrix.
   * @param type The value of the type attribute
   * @param <T> data type for {@code SparseMatrixSoftmax} output and operands
   * @return a new instance of SparseMatrixSoftmax
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> SparseMatrixSoftmax create(Scope scope,
      Operand<? extends TType> logits, Class<T> type) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseMatrixSoftmax");
    opBuilder.addInput(logits.asOutput());
    opBuilder.setAttr("type", Operands.toDataType(type));
    return new SparseMatrixSoftmax(opBuilder.build());
  }

  /**
   * Gets softmax.
   * A CSRSparseMatrix.
   * @return softmax.
   */
  public Output<? extends TType> softmax() {
    return softmax;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) softmax;
  }

  @OpInputsMetadata(
      outputsClass = SparseMatrixSoftmax.class
  )
  public static class Inputs extends RawOpInputs<SparseMatrixSoftmax> {
    /**
     * A CSRSparseMatrix.
     */
    public final Operand<? extends TType> logits;

    /**
     * The type attribute
     */
    public final DataType type;

    public Inputs(GraphOperation op) {
      super(new SparseMatrixSoftmax(op), op, Arrays.asList("type"));
      int inputIndex = 0;
      logits = (Operand<? extends TType>) op.input(inputIndex++);
      type = op.attributes().getAttrType("type");
    }
  }
}
