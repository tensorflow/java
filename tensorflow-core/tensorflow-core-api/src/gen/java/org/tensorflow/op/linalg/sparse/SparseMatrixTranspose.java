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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Transposes the inner (matrix) dimensions of a CSRSparseMatrix.
 * <p>
 * Transposes the inner (matrix) dimensions of a SparseMatrix and optionally
 * conjugates its values.
 */
public final class SparseMatrixTranspose extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.sparse.SparseMatrixTranspose}
   */
  public static class Options {
    
    /**
     * @param conjugate Indicates whether `input` should be conjugated.
     */
    public Options conjugate(Boolean conjugate) {
      this.conjugate = conjugate;
      return this;
    }
    
    private Boolean conjugate;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SparseMatrixTranspose operation.
   * 
   * @param scope current scope
   * @param input A CSRSparseMatrix.
   * @param type 
   * @param options carries optional attributes values
   * @return a new instance of SparseMatrixTranspose
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseMatrixTranspose create(Scope scope, Operand<?> input, Class<T> type, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseMatrixTranspose", scope.makeOpName("SparseMatrixTranspose"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
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
   * @param conjugate Indicates whether `input` should be conjugated.
   */
  public static Options conjugate(Boolean conjugate) {
    return new Options().conjugate(conjugate);
  }
  
  /**
   * A CSRSparseMatrix.
   */
  public Output<?> output() {
    return output;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseMatrixTranspose";
  
  private Output<?> output;
  
  private SparseMatrixTranspose(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
