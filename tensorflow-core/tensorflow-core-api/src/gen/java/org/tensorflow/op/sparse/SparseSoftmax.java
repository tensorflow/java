/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.types.family.TNumber;

/**
 * Applies softmax to a batched N-D {@code SparseTensor}.
 * The inputs represent an N-D SparseTensor  with logical shape {@code [..., B, C]}
 * (where {@code N >= 2}), and with indices sorted in the canonical lexicographic order.
 * <p>This op is equivalent to applying the normal {@code tf.nn.softmax()} to each innermost
 * logical submatrix with shape {@code [B, C]}, but with the catch that <em>the implicitly
 * zero elements do not participate</em>.  Specifically, the algorithm is equivalent
 * to the following:
 * <p>(1) Applies {@code tf.nn.softmax()} to a densified view of each innermost submatrix
 * with shape {@code [B, C]}, along the size-C dimension;
 * (2) Masks out the original implicitly-zero locations;
 * (3) Renormalizes the remaining elements.
 * <p>Hence, the {@code SparseTensor} result has exactly the same non-zero indices and
 * shape.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = SparseSoftmax.OP_NAME,
    inputsClass = SparseSoftmax.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseSoftmax<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseSoftmax";

  private Output<T> output;

  public SparseSoftmax(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseSoftmax operation.
   *
   * @param scope current scope
   * @param spIndices 2-D.  {@code NNZ x R} matrix with the indices of non-empty values in a
   * SparseTensor, in canonical ordering.
   * @param spValues 1-D.  {@code NNZ} non-empty values corresponding to {@code sp_indices}.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param <T> data type for {@code SparseSoftmax} output and operands
   * @return a new instance of SparseSoftmax
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> SparseSoftmax<T> create(Scope scope, Operand<TInt64> spIndices,
      Operand<T> spValues, Operand<TInt64> spShape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseSoftmax");
    opBuilder.addInput(spIndices.asOutput());
    opBuilder.addInput(spValues.asOutput());
    opBuilder.addInput(spShape.asOutput());
    return new SparseSoftmax<>(opBuilder.build());
  }

  /**
   * Gets output.
   * 1-D.  The {@code NNZ} values for the result {@code SparseTensor}.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = SparseSoftmax.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<SparseSoftmax<T>> {
    /**
     * 2-D.  {@code NNZ x R} matrix with the indices of non-empty values in a
     * SparseTensor, in canonical ordering.
     */
    public final Operand<TInt64> spIndices;

    /**
     * 1-D.  {@code NNZ} non-empty values corresponding to {@code sp_indices}.
     */
    public final Operand<T> spValues;

    /**
     * 1-D.  Shape of the input SparseTensor.
     */
    public final Operand<TInt64> spShape;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseSoftmax<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      spIndices = (Operand<TInt64>) op.input(inputIndex++);
      spValues = (Operand<T>) op.input(inputIndex++);
      spShape = (Operand<TInt64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
