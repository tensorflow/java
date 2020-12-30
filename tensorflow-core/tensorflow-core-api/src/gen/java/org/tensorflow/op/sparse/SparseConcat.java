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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Concatenates a list of `SparseTensor` along the specified dimension.
 * <p>
 * Concatenation is with respect to the dense versions of these sparse tensors.
 * It is assumed that each input is a `SparseTensor` whose elements are ordered
 * along increasing dimension number.
 * <p>
 * All inputs' shapes must match, except for the concat dimension.  The
 * `indices`, `values`, and `shapes` lists must have the same length.
 * <p>
 * The output shape is identical to the inputs', except along the concat
 * dimension, where it is the sum of the inputs' sizes along that dimension.
 * <p>
 * The output elements will be resorted to preserve the sort order along
 * increasing dimension number.
 * <p>
 * This op runs in `O(M log M)` time, where `M` is the total number of non-empty
 * values across all inputs. This is due to the need for an internal sort in
 * order to concatenate efficiently across an arbitrary dimension.
 * <p>
 * For example, if `concat_dim = 1` and the inputs are
 * <p>
 *     sp_inputs[0]: shape = [2, 3]
 *     [0, 2]: "a"
 *     [1, 0]: "b"
 *     [1, 1]: "c"
 * <p>
 *     sp_inputs[1]: shape = [2, 4]
 *     [0, 1]: "d"
 *     [0, 2]: "e"
 * <p>
 * then the output will be
 * <p>
 *     shape = [2, 7]
 *     [0, 2]: "a"
 *     [0, 4]: "d"
 *     [0, 5]: "e"
 *     [1, 0]: "b"
 *     [1, 1]: "c"
 * <p>
 * Graphically this is equivalent to doing
 * <p>
 *     [    a] concat [  d e  ] = [    a   d e  ]
 *     [b c  ]        [       ]   [b c          ]
 * 
 * @param <T> data type for {@code outputValues()} output
 */
@Operator(group = "sparse")
public final class SparseConcat<T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new SparseConcat operation.
   * 
   * @param scope current scope
   * @param indices 2-D.  Indices of each input `SparseTensor`.
   * @param values 1-D.  Non-empty values of each `SparseTensor`.
   * @param shapes 1-D.  Shapes of each `SparseTensor`.
   * @param concatDim Dimension to concatenate along. Must be in range [-rank, rank),
   * where rank is the number of dimensions in each input `SparseTensor`.
   * @return a new instance of SparseConcat
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseConcat<T> create(Scope scope, Iterable<Operand<TInt64>> indices, Iterable<Operand<T>> values, Iterable<Operand<TInt64>> shapes, Long concatDim) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseConcat", scope.makeOpName("SparseConcat"));
    opBuilder.addInputList(Operands.asOutputs(indices));
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder.addInputList(Operands.asOutputs(shapes));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("concat_dim", concatDim);
    return new SparseConcat<T>(opBuilder.build());
  }
  
  /**
   * 2-D.  Indices of the concatenated `SparseTensor`.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }
  
  /**
   * 1-D.  Non-empty values of the concatenated `SparseTensor`.
   */
  public Output<T> outputValues() {
    return outputValues;
  }
  
  /**
   * 1-D.  Shape of the concatenated `SparseTensor`.
   */
  public Output<TInt64> outputShape() {
    return outputShape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseConcat";
  
  private Output<TInt64> outputIndices;
  private Output<T> outputValues;
  private Output<TInt64> outputShape;
  
  private SparseConcat(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
    outputShape = operation.output(outputIdx++);
  }
}
