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
 * Transposes the inner (matrix) dimensions of a CSRSparseMatrix.
 * Transposes the inner (matrix) dimensions of a SparseMatrix and optionally
 * conjugates its values.
 */
public final class SparseMatrixTranspose extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseMatrixTranspose";

  private Output<? extends TType> output;

  @SuppressWarnings("unchecked")
  private SparseMatrixTranspose(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseMatrixTranspose operation.
   *
   * @param scope current scope
   * @param input A CSRSparseMatrix.
   * @param type the value of the type property
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseMatrixTranspose} output and operands
   * @return a new instance of SparseMatrixTranspose
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseMatrixTranspose create(Scope scope,
      Operand<? extends TType> input, Class<T> type, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseMatrixTranspose");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("type", Operands.toDataType(type));
    if (options != null) {
      for (Options opts : options) {
        if (opts.conjugate != null) {
          opBuilder.setAttr("conjugate", opts.conjugate);
        }
      }
    }
    return new SparseMatrixTranspose(opBuilder.build());
  }

  /**
   * Sets the conjugate option.
   *
   * @param conjugate Indicates whether {@code input} should be conjugated.
   * @return this Options instance.
   */
  public static Options conjugate(Boolean conjugate) {
    return new Options().conjugate(conjugate);
  }

  /**
   * Gets output.
   * A CSRSparseMatrix.
   * @return output.
   */
  public Output<? extends TType> output() {
    return output;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.sparse.SparseMatrixTranspose}
   */
  public static class Options {
    private Boolean conjugate;

    private Options() {
    }

    /**
     * Sets the conjugate option.
     *
     * @param conjugate Indicates whether {@code input} should be conjugated.
     * @return this Options instance.
     */
    public Options conjugate(Boolean conjugate) {
      this.conjugate = conjugate;
      return this;
    }
  }
}
