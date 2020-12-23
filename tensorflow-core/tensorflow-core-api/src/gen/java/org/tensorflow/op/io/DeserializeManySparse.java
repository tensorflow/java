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
 * Deserialize and concatenate `SparseTensors` from a serialized minibatch.
 * <p>
 * The input `serialized_sparse` must be a string matrix of shape `[N x 3]` where
 * `N` is the minibatch size and the rows correspond to packed outputs of
 * `SerializeSparse`.  The ranks of the original `SparseTensor` objects
 * must all match.  When the final `SparseTensor` is created, it has rank one
 * higher than the ranks of the incoming `SparseTensor` objects
 * (they have been concatenated along a new row dimension).
 * <p>
 * The output `SparseTensor` object's shape values for all dimensions but the
 * first are the max across the input `SparseTensor` objects' shape values
 * for the corresponding dimensions.  Its first shape value is `N`, the minibatch
 * size.
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
 * @param <T> data type for {@code sparseValues()} output
 */
@Operator(group = "io")
public final class DeserializeManySparse<T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new DeserializeManySparse operation.
   * 
   * @param scope current scope
   * @param serializedSparse 2-D, The `N` serialized `SparseTensor` objects.
   * Must have 3 columns.
   * @param dtype The `dtype` of the serialized `SparseTensor` objects.
   * @return a new instance of DeserializeManySparse
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> DeserializeManySparse<T> create(Scope scope, Operand<TString> serializedSparse, Class<T> dtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("DeserializeManySparse", scope.makeOpName("DeserializeManySparse"));
    opBuilder.addInput(serializedSparse.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new DeserializeManySparse<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<TInt64> sparseIndices() {
    return sparseIndices;
  }
  
  /**
   */
  public Output<T> sparseValues() {
    return sparseValues;
  }
  
  /**
   */
  public Output<TInt64> sparseShape() {
    return sparseShape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DeserializeManySparse";
  
  private Output<TInt64> sparseIndices;
  private Output<T> sparseValues;
  private Output<TInt64> sparseShape;
  
  private DeserializeManySparse(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sparseIndices = operation.output(outputIdx++);
    sparseValues = operation.output(outputIdx++);
    sparseShape = operation.output(outputIdx++);
  }
}
