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

package org.tensorflow.op.io;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Serialize a {@code SparseTensor} into a {@code [3]} {@code Tensor} object.
 *
 * @param <U> data type for {@code serialized_sparse} output
 */
@OpMetadata(
    opType = SerializeSparse.OP_NAME,
    inputsClass = SerializeSparse.Inputs.class
)
@Operator(
    group = "io"
)
public final class SerializeSparse<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SerializeSparse";

  private Output<U> serializedSparse;

  public SerializeSparse(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    serializedSparse = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SerializeSparse operation.
   *
   * @param scope current scope
   * @param sparseIndices 2-D.  The {@code indices} of the {@code SparseTensor}.
   * @param sparseValues 1-D.  The {@code values} of the {@code SparseTensor}.
   * @param sparseShape 1-D.  The {@code shape} of the {@code SparseTensor}.
   * @param outType The {@code dtype} to use for serialization; the supported types are {@code string}
   * (default) and {@code variant}.
   * @param <U> data type for {@code SerializeSparse} output and operands
   * @return a new instance of SerializeSparse
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> SerializeSparse<U> create(Scope scope,
      Operand<TInt64> sparseIndices, Operand<? extends TType> sparseValues,
      Operand<TInt64> sparseShape, Class<U> outType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SerializeSparse");
    opBuilder.addInput(sparseIndices.asOutput());
    opBuilder.addInput(sparseValues.asOutput());
    opBuilder.addInput(sparseShape.asOutput());
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new SerializeSparse<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new SerializeSparse operation, with the default output types.
   *
   * @param scope current scope
   * @param sparseIndices 2-D.  The {@code indices} of the {@code SparseTensor}.
   * @param sparseValues 1-D.  The {@code values} of the {@code SparseTensor}.
   * @param sparseShape 1-D.  The {@code shape} of the {@code SparseTensor}.
   * @return a new instance of SerializeSparse, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static SerializeSparse<TString> create(Scope scope, Operand<TInt64> sparseIndices,
      Operand<? extends TType> sparseValues, Operand<TInt64> sparseShape) {
    return create(scope, sparseIndices, sparseValues, sparseShape, TString.class);
  }

  /**
   * Gets serializedSparse.
   *
   * @return serializedSparse.
   */
  public Output<U> serializedSparse() {
    return serializedSparse;
  }

  @Override
  public Output<U> asOutput() {
    return serializedSparse;
  }

  @OpInputsMetadata(
      outputsClass = SerializeSparse.class
  )
  public static class Inputs extends RawOpInputs<SerializeSparse<?>> {
    /**
     * 2-D.  The {@code indices} of the {@code SparseTensor}.
     */
    public final Operand<TInt64> sparseIndices;

    /**
     * 1-D.  The {@code values} of the {@code SparseTensor}.
     */
    public final Operand<? extends TType> sparseValues;

    /**
     * 1-D.  The {@code shape} of the {@code SparseTensor}.
     */
    public final Operand<TInt64> sparseShape;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The `dtype` to use for serialization; the supported types are `string`
     * (default) and `variant`.
     */
    public final DataType outType;

    public Inputs(GraphOperation op) {
      super(new SerializeSparse<>(op), op, Arrays.asList("T", "out_type"));
      int inputIndex = 0;
      sparseIndices = (Operand<TInt64>) op.input(inputIndex++);
      sparseValues = (Operand<? extends TType>) op.input(inputIndex++);
      sparseShape = (Operand<TInt64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      outType = op.attributes().getAttrType("out_type");
    }
  }
}
