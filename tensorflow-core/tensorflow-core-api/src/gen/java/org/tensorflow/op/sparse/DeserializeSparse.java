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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Deserialize `SparseTensor` objects.
 * <p>
 * The input `serialized_sparse` must have the shape `[?, ?, ..., ?, 3]` where
 * the last dimension stores serialized `SparseTensor` objects and the other N
 * dimensions (N >= 0) correspond to a batch. The ranks of the original
 * `SparseTensor` objects must all match. When the final `SparseTensor` is
 * created, its rank is the rank of the incoming `SparseTensor` objects plus N;
 * the sparse tensors have been concatenated along new dimensions, one for each
 * batch.
 * <p>
 * The output `SparseTensor` object's shape values for the original dimensions
 * are the max across the input `SparseTensor` objects' shape values for the
 * corresponding dimensions. The new dimensions match the size of the batch.
 * <p>
 * The input `SparseTensor` objects' indices are assumed ordered in
 * standard lexicographic order.  If this is not the case, after this
 * step run `SparseReorder` to restore index ordering.
 * <p>
 * For example, if the serialized input is a `[2 x 3]` matrix representing two
 * original `SparseTensor` objects:
 * <p>
 *     index = [ 0]
 *             [10]
 *             [20]
 *     values = [1, 2, 3]
 *     shape = [50]
 * <p>
 * and
 * <p>
 *     index = [ 2]
 *             [10]
 *     values = [4, 5]
 *     shape = [30]
 * <p>
 * then the final deserialized `SparseTensor` will be:
 * <p>
 *     index = [0  0]
 *             [0 10]
 *             [0 20]
 *             [1  2]
 *             [1 10]
 *     values = [1, 2, 3, 4, 5]
 *     shape = [2 50]
 * 
 * @param <U> data type for {@code sparseValues()} output
 */
@Operator(group = "sparse")
public final class DeserializeSparse<U extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new DeserializeSparse operation.
   * 
   * @param scope current scope
   * @param serializedSparse The serialized `SparseTensor` objects. The last dimension
   * must have 3 columns.
   * @param dtype The `dtype` of the serialized `SparseTensor` objects.
   * @return a new instance of DeserializeSparse
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TType> DeserializeSparse<U> create(Scope scope, Operand<T> serializedSparse, Class<U> dtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("DeserializeSparse", scope.makeOpName("DeserializeSparse"));
    opBuilder.addInput(serializedSparse.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new DeserializeSparse<U>(opBuilder.build());
  }
  
  /**
   */
  public Output<TInt64> sparseIndices() {
    return sparseIndices;
  }
  
  /**
   */
  public Output<U> sparseValues() {
    return sparseValues;
  }
  
  /**
   */
  public Output<TInt64> sparseShape() {
    return sparseShape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DeserializeSparse";
  
  private Output<TInt64> sparseIndices;
  private Output<U> sparseValues;
  private Output<TInt64> sparseShape;
  
  private DeserializeSparse(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sparseIndices = operation.output(outputIdx++);
    sparseValues = operation.output(outputIdx++);
    sparseShape = operation.output(outputIdx++);
  }
}
