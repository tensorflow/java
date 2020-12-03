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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Converts a sparse representation into a dense tensor.
 * <p>
 * Builds an array `dense` with shape `output_shape` such that
 * <pre>{@code
 * # If sparse_indices is scalar
 * dense[i] = (i == sparse_indices ? sparse_values : default_value)
 * 
 * # If sparse_indices is a vector, then for each i
 * dense[sparse_indices[i]] = sparse_values[i]
 * 
 * # If sparse_indices is an n by d matrix, then for each i in [0, n)
 * dense[sparse_indices[i][0], ..., sparse_indices[i][d-1]] = sparse_values[i]
 * }</pre>
 * All other values in `dense` are set to `default_value`.  If `sparse_values` is a
 * scalar, all sparse indices are set to this single value.
 * <p>
 * Indices should be sorted in lexicographic order, and indices must not
 * contain any repeats. If `validate_indices` is true, these properties
 * are checked during execution.
 * 
 * @param <U> data type for {@code dense()} output
 */
@Operator(group = "sparse")
public final class SparseToDense<U extends TType> extends RawOp implements Operand<U> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.SparseToDense}
   */
  public static class Options {
    
    /**
     * @param validateIndices If true, indices are checked to make sure they are sorted in
     * lexicographic order and that there are no repeats.
     */
    public Options validateIndices(Boolean validateIndices) {
      this.validateIndices = validateIndices;
      return this;
    }
    
    private Boolean validateIndices;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SparseToDense operation.
   * 
   * @param scope current scope
   * @param sparseIndices 0-D, 1-D, or 2-D.  `sparse_indices[i]` contains the complete
   * index where `sparse_values[i]` will be placed.
   * @param outputShape 1-D.  Shape of the dense output tensor.
   * @param sparseValues 1-D.  Values corresponding to each row of `sparse_indices`,
   * or a scalar value to be used for all sparse indices.
   * @param defaultValue Scalar value to set for indices not specified in
   * `sparse_indices`.
   * @param options carries optional attributes values
   * @return a new instance of SparseToDense
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TNumber> SparseToDense<U> create(Scope scope, Operand<T> sparseIndices, Operand<T> outputShape, Operand<U> sparseValues, Operand<U> defaultValue, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseToDense", scope.makeOpName("SparseToDense"));
    opBuilder.addInput(sparseIndices.asOutput());
    opBuilder.addInput(outputShape.asOutput());
    opBuilder.addInput(sparseValues.asOutput());
    opBuilder.addInput(defaultValue.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.validateIndices != null) {
          opBuilder.setAttr("validate_indices", opts.validateIndices);
        }
      }
    }
    return new SparseToDense<U>(opBuilder.build());
  }
  
  /**
   * @param validateIndices If true, indices are checked to make sure they are sorted in
   * lexicographic order and that there are no repeats.
   */
  public static Options validateIndices(Boolean validateIndices) {
    return new Options().validateIndices(validateIndices);
  }
  
  /**
   * Dense output tensor of shape `output_shape`.
   */
  public Output<U> dense() {
    return dense;
  }
  
  @Override
  public Output<U> asOutput() {
    return dense;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseToDense";
  
  private Output<U> dense;
  
  private SparseToDense(Operation operation) {
    super(operation);
    int outputIdx = 0;
    dense = operation.output(outputIdx++);
  }
}
