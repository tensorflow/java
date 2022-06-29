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

package org.tensorflow.op.core;

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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Number of unique elements along last dimension of input {@code set}.
 * Input {@code set} is a {@code SparseTensor} represented by {@code set_indices}, {@code set_values},
 * and {@code set_shape}. The last dimension contains values in a set, duplicates are
 * allowed but ignored.
 * <p>If {@code validate_indices} is {@code True}, this op validates the order and range of {@code set}
 * indices.
 */
@OpMetadata(
    opType = SetSize.OP_NAME,
    inputsClass = SetSize.Inputs.class
)
@Operator
public final class SetSize extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SetSize";

  private Output<TInt32> output;

  public SetSize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SetSize operation.
   *
   * @param scope current scope
   * @param setIndices 2D {@code Tensor}, indices of a {@code SparseTensor}.
   * @param setValues 1D {@code Tensor}, values of a {@code SparseTensor}.
   * @param setShape 1D {@code Tensor}, shape of a {@code SparseTensor}.
   * @param options carries optional attribute values
   * @return a new instance of SetSize
   */
  @Endpoint(
      describeByClass = true
  )
  public static SetSize create(Scope scope, Operand<TInt64> setIndices,
      Operand<? extends TType> setValues, Operand<TInt64> setShape, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SetSize");
    opBuilder.addInput(setIndices.asOutput());
    opBuilder.addInput(setValues.asOutput());
    opBuilder.addInput(setShape.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.validateIndices != null) {
          opBuilder.setAttr("validate_indices", opts.validateIndices);
        }
      }
    }
    return new SetSize(opBuilder.build());
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
   * Gets output.
   * For {@code set} ranked {@code n}, this is a {@code Tensor} with rank {@code n-1}, and the same 1st
   * {@code n-1} dimensions as {@code set}. Each value is the number of unique elements in
   * the corresponding {@code [0...n-1]} dimension of {@code set}.
   * @return output.
   */
  public Output<TInt32> output() {
    return output;
  }

  @Override
  public Output<TInt32> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.SetSize}
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
      outputsClass = SetSize.class
  )
  public static class Inputs extends RawOpInputs<SetSize> {
    /**
     * 2D {@code Tensor}, indices of a {@code SparseTensor}.
     */
    public final Operand<TInt64> setIndices;

    /**
     * 1D {@code Tensor}, values of a {@code SparseTensor}.
     */
    public final Operand<? extends TType> setValues;

    /**
     * 1D {@code Tensor}, shape of a {@code SparseTensor}.
     */
    public final Operand<TInt64> setShape;

    /**
     * The validateIndices attribute
     */
    public final boolean validateIndices;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SetSize(op), op, Arrays.asList("validate_indices", "T"));
      int inputIndex = 0;
      setIndices = (Operand<TInt64>) op.input(inputIndex++);
      setValues = (Operand<? extends TType>) op.input(inputIndex++);
      setShape = (Operand<TInt64>) op.input(inputIndex++);
      validateIndices = op.attributes().getAttrBool("validate_indices");
      T = op.attributes().getAttrType("T");
    }
  }
}
