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
 * Deserialize and concatenate {@code SparseTensors} from a serialized minibatch.
 * The input {@code serialized_sparse} must be a string matrix of shape {@code [N x 3]} where
 * {@code N} is the minibatch size and the rows correspond to packed outputs of
 * {@code SerializeSparse}.  The ranks of the original {@code SparseTensor} objects
 * must all match.  When the final {@code SparseTensor} is created, it has rank one
 * higher than the ranks of the incoming {@code SparseTensor} objects
 * (they have been concatenated along a new row dimension).
 * <p>The output {@code SparseTensor} object's shape values for all dimensions but the
 * first are the max across the input {@code SparseTensor} objects' shape values
 * for the corresponding dimensions.  Its first shape value is {@code N}, the minibatch
 * size.
 * <p>The input {@code SparseTensor} objects' indices are assumed ordered in
 * standard lexicographic order.  If this is not the case, after this
 * step run {@code SparseReorder} to restore index ordering.
 * <p>For example, if the serialized input is a {@code [2 x 3]} matrix representing two
 * original {@code SparseTensor} objects:
 * <pre>
 * index = [ 0]
 *         [10]
 *         [20]
 * values = [1, 2, 3]
 * shape = [50]
 * </pre>
 * <p>and
 * <pre>
 * index = [ 2]
 *         [10]
 * values = [4, 5]
 * shape = [30]
 * </pre>
 * <p>then the final deserialized {@code SparseTensor} will be:
 * <pre>
 * index = [0  0]
 *         [0 10]
 *         [0 20]
 *         [1  2]
 *         [1 10]
 * values = [1, 2, 3, 4, 5]
 * shape = [2 50]
 * </pre>
 *
 * @param <T> data type for {@code sparse_values} output
 */
@OpMetadata(
    opType = DeserializeManySparse.OP_NAME,
    inputsClass = DeserializeManySparse.Inputs.class
)
@Operator(
    group = "io"
)
public final class DeserializeManySparse<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DeserializeManySparse";

  private Output<TInt64> sparseIndices;

  private Output<T> sparseValues;

  private Output<TInt64> sparseShape;

  public DeserializeManySparse(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sparseIndices = operation.output(outputIdx++);
    sparseValues = operation.output(outputIdx++);
    sparseShape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DeserializeManySparse operation.
   *
   * @param scope current scope
   * @param serializedSparse 2-D, The {@code N} serialized {@code SparseTensor} objects.
   * Must have 3 columns.
   * @param dtype The {@code dtype} of the serialized {@code SparseTensor} objects.
   * @param <T> data type for {@code DeserializeManySparse} output and operands
   * @return a new instance of DeserializeManySparse
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> DeserializeManySparse<T> create(Scope scope,
      Operand<TString> serializedSparse, Class<T> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DeserializeManySparse");
    opBuilder.addInput(serializedSparse.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new DeserializeManySparse<>(opBuilder.build());
  }

  /**
   * Gets sparseIndices.
   *
   * @return sparseIndices.
   */
  public Output<TInt64> sparseIndices() {
    return sparseIndices;
  }

  /**
   * Gets sparseValues.
   *
   * @return sparseValues.
   */
  public Output<T> sparseValues() {
    return sparseValues;
  }

  /**
   * Gets sparseShape.
   *
   * @return sparseShape.
   */
  public Output<TInt64> sparseShape() {
    return sparseShape;
  }

  @OpInputsMetadata(
      outputsClass = DeserializeManySparse.class
  )
  public static class Inputs extends RawOpInputs<DeserializeManySparse<?>> {
    /**
     * 2-D, The {@code N} serialized {@code SparseTensor} objects.
     * Must have 3 columns.
     */
    public final Operand<TString> serializedSparse;

    /**
     * The `dtype` of the serialized `SparseTensor` objects.
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new DeserializeManySparse<>(op), op, Arrays.asList("dtype"));
      int inputIndex = 0;
      serializedSparse = (Operand<TString>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
