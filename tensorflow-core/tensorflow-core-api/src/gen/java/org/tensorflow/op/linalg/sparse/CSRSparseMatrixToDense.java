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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TType;

/**
 * Convert a (possibly batched) CSRSparseMatrix to dense.
 *
 * @param <T> data type for {@code dense_output} output
 */
public final class CSRSparseMatrixToDense<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CSRSparseMatrixToDense";

  private Output<T> denseOutput;

  private CSRSparseMatrixToDense(Operation operation) {
    super(operation);
    int outputIdx = 0;
    denseOutput = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CSRSparseMatrixToDense operation.
   *
   * @param scope current scope
   * @param sparseInput A batched CSRSparseMatrix.
   * @param type the value of the type property
   * @param <T> data type for {@code CSRSparseMatrixToDense} output and operands
   * @return a new instance of CSRSparseMatrixToDense
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> CSRSparseMatrixToDense<T> create(Scope scope,
      Operand<? extends TType> sparseInput, Class<T> type) {
    OperationBuilder opBuilder = scope.env().opBuilder("CSRSparseMatrixToDense", scope.makeOpName("CSRSparseMatrixToDense"));
    opBuilder.addInput(sparseInput.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("type", Operands.toDataType(type));
    return new CSRSparseMatrixToDense<>(opBuilder.build());
  }

  /**
   * Gets denseOutput.
   * A dense tensor.
   * @return denseOutput.
   */
  public Output<T> denseOutput() {
    return denseOutput;
  }

  @Override
  public Output<T> asOutput() {
    return denseOutput;
  }
}
