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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Serialize an {@code N}-minibatch {@code SparseTensor} into an {@code [N, 3]} {@code Tensor} object.
 * The {@code SparseTensor} must have rank {@code R} greater than 1, and the first dimension
 * is treated as the minibatch dimension.  Elements of the {@code SparseTensor}
 * must be sorted in increasing order of this first dimension.  The serialized
 * {@code SparseTensor} objects going into each row of {@code serialized_sparse} will have
 * rank {@code R-1}.
 * <p>The minibatch size {@code N} is extracted from {@code sparse_shape[0]}.
 *
 * @param <U> data type for {@code serialized_sparse} output
 */
@Operator(
    group = "io"
)
public final class SerializeManySparse<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SerializeManySparse";

  private Output<U> serializedSparse;

  private SerializeManySparse(Operation operation) {
    super(operation);
    int outputIdx = 0;
    serializedSparse = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SerializeManySparse operation.
   *
   * @param scope current scope
   * @param sparseIndices 2-D.  The {@code indices} of the minibatch {@code SparseTensor}.
   * @param sparseValues 1-D.  The {@code values} of the minibatch {@code SparseTensor}.
   * @param sparseShape 1-D.  The {@code shape} of the minibatch {@code SparseTensor}.
   * @param outType The {@code dtype} to use for serialization; the supported types are {@code string}
   * (default) and {@code variant}.
   * @param <U> data type for {@code SerializeManySparse} output and operands
   * @return a new instance of SerializeManySparse
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> SerializeManySparse<U> create(Scope scope,
      Operand<TInt64> sparseIndices, Operand<? extends TType> sparseValues,
      Operand<TInt64> sparseShape, Class<U> outType) {
    OperationBuilder opBuilder = scope.env().opBuilder("SerializeManySparse", scope.makeOpName("SerializeManySparse"));
    opBuilder.addInput(sparseIndices.asOutput());
    opBuilder.addInput(sparseValues.asOutput());
    opBuilder.addInput(sparseShape.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new SerializeManySparse<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new SerializeManySparse operation, with the default output types.
   *
   * @param scope current scope
   * @param sparseIndices 2-D.  The {@code indices} of the minibatch {@code SparseTensor}.
   * @param sparseValues 1-D.  The {@code values} of the minibatch {@code SparseTensor}.
   * @param sparseShape 1-D.  The {@code shape} of the minibatch {@code SparseTensor}.
   * @return a new instance of SerializeManySparse, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static SerializeManySparse<TString> create(Scope scope, Operand<TInt64> sparseIndices,
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
}
