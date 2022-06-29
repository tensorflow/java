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
import org.tensorflow.types.family.TType;

/**
 * Converts a sparse representation into a dense tensor.
 * Builds an array {@code dense} with shape {@code output_shape} such that
 * <pre>
 * # If sparse_indices is scalar
 * dense[i] = (i == sparse_indices ? sparse_values : default_value)
 *
 * # If sparse_indices is a vector, then for each i
 * dense[sparse_indices[i]] = sparse_values[i]
 *
 * # If sparse_indices is an n by d matrix, then for each i in [0, n)
 * dense[sparse_indices[i][0], ..., sparse_indices[i][d-1]] = sparse_values[i]
 * </pre>
 * <p>All other values in {@code dense} are set to {@code default_value}.  If {@code sparse_values} is a
 * scalar, all sparse indices are set to this single value.
 * <p>Indices should be sorted in lexicographic order, and indices must not
 * contain any repeats. If {@code validate_indices} is true, these properties
 * are checked during execution.
 *
 * @param <U> data type for {@code dense} output
 */
@OpMetadata(
    opType = SparseToDense.OP_NAME,
    inputsClass = SparseToDense.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseToDense<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseToDense";

  private Output<U> dense;

  public SparseToDense(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    dense = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseToDense operation.
   *
   * @param scope current scope
   * @param sparseIndices 0-D, 1-D, or 2-D.  {@code sparse_indices[i]} contains the complete
   * index where {@code sparse_values[i]} will be placed.
   * @param outputShape 1-D.  Shape of the dense output tensor.
   * @param sparseValues 1-D.  Values corresponding to each row of {@code sparse_indices},
   * or a scalar value to be used for all sparse indices.
   * @param defaultValue Scalar value to set for indices not specified in
   * {@code sparse_indices}.
   * @param options carries optional attribute values
   * @param <U> data type for {@code SparseToDense} output and operands
   * @param <T> data type for {@code SparseToDense} output and operands
   * @return a new instance of SparseToDense
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType, T extends TNumber> SparseToDense<U> create(Scope scope,
      Operand<T> sparseIndices, Operand<T> outputShape, Operand<U> sparseValues,
      Operand<U> defaultValue, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseToDense");
    opBuilder.addInput(sparseIndices.asOutput());
    opBuilder.addInput(outputShape.asOutput());
    opBuilder.addInput(sparseValues.asOutput());
    opBuilder.addInput(defaultValue.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.validateIndices != null) {
          opBuilder.setAttr("validate_indices", opts.validateIndices);
        }
      }
    }
    return new SparseToDense<>(opBuilder.build());
  }

  /**
   * Sets the validateIndices option.
   *
   * @param validateIndices If true, indices are checked to make sure they are sorted in
   * lexicographic order and that there are no repeats.
   * @return this Options instance.
   */
  public static Options validateIndices(Boolean validateIndices) {
    return new Options().validateIndices(validateIndices);
  }

  /**
   * Gets dense.
   * Dense output tensor of shape {@code output_shape}.
   * @return dense.
   */
  public Output<U> dense() {
    return dense;
  }

  @Override
  public Output<U> asOutput() {
    return dense;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.SparseToDense}
   */
  public static class Options {
    private Boolean validateIndices;

    private Options() {
    }

    /**
     * Sets the validateIndices option.
     *
     * @param validateIndices If true, indices are checked to make sure they are sorted in
     * lexicographic order and that there are no repeats.
     * @return this Options instance.
     */
    public Options validateIndices(Boolean validateIndices) {
      this.validateIndices = validateIndices;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SparseToDense.class
  )
  public static class Inputs<T extends TNumber, U extends TType> extends RawOpInputs<SparseToDense<U>> {
    /**
     * 0-D, 1-D, or 2-D.  {@code sparse_indices[i]} contains the complete
     * index where {@code sparse_values[i]} will be placed.
     */
    public final Operand<T> sparseIndices;

    /**
     * 1-D.  Shape of the dense output tensor.
     */
    public final Operand<T> outputShape;

    /**
     * 1-D.  Values corresponding to each row of {@code sparse_indices},
     * or a scalar value to be used for all sparse indices.
     */
    public final Operand<U> sparseValues;

    /**
     * Scalar value to set for indices not specified in
     * {@code sparse_indices}.
     */
    public final Operand<U> defaultValue;

    /**
     * If true, indices are checked to make sure they are sorted in
     * lexicographic order and that there are no repeats.
     */
    public final boolean validateIndices;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    public Inputs(GraphOperation op) {
      super(new SparseToDense<>(op), op, Arrays.asList("validate_indices", "T", "Tindices"));
      int inputIndex = 0;
      sparseIndices = (Operand<T>) op.input(inputIndex++);
      outputShape = (Operand<T>) op.input(inputIndex++);
      sparseValues = (Operand<U>) op.input(inputIndex++);
      defaultValue = (Operand<U>) op.input(inputIndex++);
      validateIndices = op.attributes().getAttrBool("validate_indices");
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
