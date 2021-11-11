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
 * Applies set operation along last dimension of 2 {@code Tensor} inputs.
 * See SetOperationOp::SetOperationFromContext for values of {@code set_operation}.
 * <p>Output {@code result} is a {@code SparseTensor} represented by {@code result_indices},
 * {@code result_values}, and {@code result_shape}. For {@code set1} and {@code set2} ranked {@code n}, this
 * has rank {@code n} and the same 1st {@code n-1} dimensions as {@code set1} and {@code set2}. The {@code nth}
 * dimension contains the result of {@code set_operation} applied to the corresponding
 * {@code [0...n-1]} dimension of {@code set}.
 *
 * @param <T> data type for {@code result_values} output
 */
@OpMetadata(
    opType = DenseToDenseSetOperation.OP_NAME,
    inputsClass = DenseToDenseSetOperation.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class DenseToDenseSetOperation<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DenseToDenseSetOperation";

  private Output<TInt64> resultIndices;

  private Output<T> resultValues;

  private Output<TInt64> resultShape;

  public DenseToDenseSetOperation(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    resultIndices = operation.output(outputIdx++);
    resultValues = operation.output(outputIdx++);
    resultShape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DenseToDenseSetOperation operation.
   *
   * @param scope current scope
   * @param set1 {@code Tensor} with rank {@code n}. 1st {@code n-1} dimensions must be the same as {@code set2}.
   * Dimension {@code n} contains values in a set, duplicates are allowed but ignored.
   * @param set2 {@code Tensor} with rank {@code n}. 1st {@code n-1} dimensions must be the same as {@code set1}.
   * Dimension {@code n} contains values in a set, duplicates are allowed but ignored.
   * @param setOperation The value of the setOperation attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code DenseToDenseSetOperation} output and operands
   * @return a new instance of DenseToDenseSetOperation
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> DenseToDenseSetOperation<T> create(Scope scope, Operand<T> set1,
      Operand<T> set2, String setOperation, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DenseToDenseSetOperation");
    opBuilder.addInput(set1.asOutput());
    opBuilder.addInput(set2.asOutput());
    opBuilder.setAttr("set_operation", setOperation);
    if (options != null) {
      for (Options opts : options) {
        if (opts.validateIndices != null) {
          opBuilder.setAttr("validate_indices", opts.validateIndices);
        }
      }
    }
    return new DenseToDenseSetOperation<>(opBuilder.build());
  }

  /**
   * Sets the validateIndices option.
   *
   * @param validateIndices the validateIndices option
   * @return this Options instance.
   */
  public static Options validateIndices(Boolean validateIndices) {
    return new Options().validateIndices(validateIndices);
  }

  /**
   * Gets resultIndices.
   * 2D indices of a {@code SparseTensor}.
   * @return resultIndices.
   */
  public Output<TInt64> resultIndices() {
    return resultIndices;
  }

  /**
   * Gets resultValues.
   * 1D values of a {@code SparseTensor}.
   * @return resultValues.
   */
  public Output<T> resultValues() {
    return resultValues;
  }

  /**
   * Gets resultShape.
   * 1D {@code Tensor} shape of a {@code SparseTensor}. {@code result_shape[0...n-1]} is
   * the same as the 1st {@code n-1} dimensions of {@code set1} and {@code set2}, {@code result_shape[n]}
   * is the max result set size across all {@code 0...n-1} dimensions.
   * @return resultShape.
   */
  public Output<TInt64> resultShape() {
    return resultShape;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.DenseToDenseSetOperation}
   */
  public static class Options {
    private Boolean validateIndices;

    private Options() {
    }

    /**
     * Sets the validateIndices option.
     *
     * @param validateIndices the validateIndices option
     * @return this Options instance.
     */
    public Options validateIndices(Boolean validateIndices) {
      this.validateIndices = validateIndices;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DenseToDenseSetOperation.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<DenseToDenseSetOperation<T>> {
    /**
     * {@code Tensor} with rank {@code n}. 1st {@code n-1} dimensions must be the same as {@code set2}.
     * Dimension {@code n} contains values in a set, duplicates are allowed but ignored.
     */
    public final Operand<T> set1;

    /**
     * {@code Tensor} with rank {@code n}. 1st {@code n-1} dimensions must be the same as {@code set1}.
     * Dimension {@code n} contains values in a set, duplicates are allowed but ignored.
     */
    public final Operand<T> set2;

    /**
     * The setOperation attribute
     */
    public final String setOperation;

    /**
     * The validateIndices attribute
     */
    public final boolean validateIndices;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new DenseToDenseSetOperation<>(op), op, Arrays.asList("set_operation", "validate_indices", "T"));
      int inputIndex = 0;
      set1 = (Operand<T>) op.input(inputIndex++);
      set2 = (Operand<T>) op.input(inputIndex++);
      setOperation = op.attributes().getAttrString("set_operation");
      validateIndices = op.attributes().getAttrBool("validate_indices");
      T = op.attributes().getAttrType("T");
    }
  }
}
