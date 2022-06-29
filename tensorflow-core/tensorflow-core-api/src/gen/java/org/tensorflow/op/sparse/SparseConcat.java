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
import org.tensorflow.types.family.TType;

/**
 * Concatenates a list of {@code SparseTensor} along the specified dimension.
 * Concatenation is with respect to the dense versions of these sparse tensors.
 * It is assumed that each input is a {@code SparseTensor} whose elements are ordered
 * along increasing dimension number.
 * <p>All inputs' shapes must match, except for the concat dimension.  The
 * {@code indices}, {@code values}, and {@code shapes} lists must have the same length.
 * <p>The output shape is identical to the inputs', except along the concat
 * dimension, where it is the sum of the inputs' sizes along that dimension.
 * <p>The output elements will be resorted to preserve the sort order along
 * increasing dimension number.
 * <p>This op runs in {@code O(M log M)} time, where {@code M} is the total number of non-empty
 * values across all inputs. This is due to the need for an internal sort in
 * order to concatenate efficiently across an arbitrary dimension.
 * <p>For example, if {@code concat_dim = 1} and the inputs are
 * <pre>
 * sp_inputs[0]: shape = [2, 3]
 * [0, 2]: &quot;a&quot;
 * [1, 0]: &quot;b&quot;
 * [1, 1]: &quot;c&quot;
 *
 * sp_inputs[1]: shape = [2, 4]
 * [0, 1]: &quot;d&quot;
 * [0, 2]: &quot;e&quot;
 * </pre>
 * <p>then the output will be
 * <pre>
 * shape = [2, 7]
 * [0, 2]: &quot;a&quot;
 * [0, 4]: &quot;d&quot;
 * [0, 5]: &quot;e&quot;
 * [1, 0]: &quot;b&quot;
 * [1, 1]: &quot;c&quot;
 * </pre>
 * <p>Graphically this is equivalent to doing
 * <pre>
 * [    a] concat [  d e  ] = [    a   d e  ]
 * [b c  ]        [       ]   [b c          ]
 * </pre>
 *
 * @param <T> data type for {@code output_values} output
 */
@OpMetadata(
    opType = SparseConcat.OP_NAME,
    inputsClass = SparseConcat.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseConcat<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseConcat";

  private Output<TInt64> outputIndices;

  private Output<T> outputValues;

  private Output<TInt64> outputShape;

  public SparseConcat(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
    outputShape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseConcat operation.
   *
   * @param scope current scope
   * @param indices 2-D.  Indices of each input {@code SparseTensor}.
   * @param values 1-D.  Non-empty values of each {@code SparseTensor}.
   * @param shapes 1-D.  Shapes of each {@code SparseTensor}.
   * @param concatDim Dimension to concatenate along. Must be in range [-rank, rank),
   * where rank is the number of dimensions in each input {@code SparseTensor}.
   * @param <T> data type for {@code SparseConcat} output and operands
   * @return a new instance of SparseConcat
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseConcat<T> create(Scope scope,
      Iterable<Operand<TInt64>> indices, Iterable<Operand<T>> values,
      Iterable<Operand<TInt64>> shapes, Long concatDim) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseConcat");
    opBuilder.addInputList(Operands.asOutputs(indices));
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder.addInputList(Operands.asOutputs(shapes));
    opBuilder.setAttr("concat_dim", concatDim);
    return new SparseConcat<>(opBuilder.build());
  }

  /**
   * Gets outputIndices.
   * 2-D.  Indices of the concatenated {@code SparseTensor}.
   * @return outputIndices.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }

  /**
   * Gets outputValues.
   * 1-D.  Non-empty values of the concatenated {@code SparseTensor}.
   * @return outputValues.
   */
  public Output<T> outputValues() {
    return outputValues;
  }

  /**
   * Gets outputShape.
   * 1-D.  Shape of the concatenated {@code SparseTensor}.
   * @return outputShape.
   */
  public Output<TInt64> outputShape() {
    return outputShape;
  }

  @OpInputsMetadata(
      outputsClass = SparseConcat.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SparseConcat<T>> {
    /**
     * 2-D.  Indices of each input {@code SparseTensor}.
     */
    public final Iterable<Operand<TInt64>> indices;

    /**
     * 1-D.  Non-empty values of each {@code SparseTensor}.
     */
    public final Iterable<Operand<T>> values;

    /**
     * 1-D.  Shapes of each {@code SparseTensor}.
     */
    public final Iterable<Operand<TInt64>> shapes;

    /**
     * Dimension to concatenate along. Must be in range [-rank, rank),
     * where rank is the number of dimensions in each input `SparseTensor`.
     */
    public final long concatDim;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseConcat<>(op), op, Arrays.asList("concat_dim", "T"));
      int inputIndex = 0;
      int indicesLength = op.inputListLength("indices");
      indices = Arrays.asList((Operand<TInt64>[]) op.inputList(inputIndex, indicesLength));
      inputIndex += indicesLength;
      int valuesLength = op.inputListLength("values");
      values = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, valuesLength));
      inputIndex += valuesLength;
      int shapesLength = op.inputListLength("shapes");
      shapes = Arrays.asList((Operand<TInt64>[]) op.inputList(inputIndex, shapesLength));
      inputIndex += shapesLength;
      concatDim = op.attributes().getAttrInt("concat_dim");
      T = op.attributes().getAttrType("T");
    }
  }
}
