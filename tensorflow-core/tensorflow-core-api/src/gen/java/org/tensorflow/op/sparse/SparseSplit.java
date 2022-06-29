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
import java.util.List;
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
 * Split a {@code SparseTensor} into {@code num_split} tensors along one dimension.
 * If the {@code shape[split_dim]} is not an integer multiple of {@code num_split}. Slices
 * {@code [0 : shape[split_dim] % num_split]} gets one extra dimension.
 * For example, if {@code split_dim = 1} and {@code num_split = 2} and the input is
 * <pre>
 * input_tensor = shape = [2, 7]
 * [    a   d e  ]
 * [b c          ]
 * </pre>
 * <p>Graphically the output tensors are:
 * <pre>
 * output_tensor[0] = shape = [2, 4]
 * [    a  ]
 * [b c    ]
 *
 * output_tensor[1] = shape = [2, 3]
 * [ d e  ]
 * [      ]
 * </pre>
 *
 * @param <T> data type for {@code output_values} output
 */
@OpMetadata(
    opType = SparseSplit.OP_NAME,
    inputsClass = SparseSplit.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseSplit<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseSplit";

  private List<Output<TInt64>> outputIndices;

  private List<Output<T>> outputValues;

  private List<Output<TInt64>> outputShape;

  @SuppressWarnings("unchecked")
  public SparseSplit(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputIndicesLength = operation.outputListLength("output_indices");
    outputIndices = Arrays.asList((Output<TInt64>[]) operation.outputList(outputIdx, outputIndicesLength));
    outputIdx += outputIndicesLength;
    int outputValuesLength = operation.outputListLength("output_values");
    outputValues = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, outputValuesLength));
    outputIdx += outputValuesLength;
    int outputShapeLength = operation.outputListLength("output_shape");
    outputShape = Arrays.asList((Output<TInt64>[]) operation.outputList(outputIdx, outputShapeLength));
    outputIdx += outputShapeLength;
  }

  /**
   * Factory method to create a class wrapping a new SparseSplit operation.
   *
   * @param scope current scope
   * @param splitDim 0-D.  The dimension along which to split.  Must be in the range
   * {@code [0, rank(shape))}.
   * @param indices 2-D tensor represents the indices of the sparse tensor.
   * @param values 1-D tensor represents the values of the sparse tensor.
   * @param shape 1-D. tensor represents the shape of the sparse tensor.
   * output indices: A list of 1-D tensors represents the indices of the output
   * sparse tensors.
   * @param numSplit The number of ways to split.
   * @param <T> data type for {@code SparseSplit} output and operands
   * @return a new instance of SparseSplit
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseSplit<T> create(Scope scope, Operand<TInt64> splitDim,
      Operand<TInt64> indices, Operand<T> values, Operand<TInt64> shape, Long numSplit) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseSplit");
    opBuilder.addInput(splitDim.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder.setAttr("num_split", numSplit);
    return new SparseSplit<>(opBuilder.build());
  }

  /**
   * Gets outputIndices.
   *
   * @return outputIndices.
   */
  public List<Output<TInt64>> outputIndices() {
    return outputIndices;
  }

  /**
   * Gets outputValues.
   * A list of 1-D tensors represents the values of the output sparse
   * tensors.
   * @return outputValues.
   */
  public List<Output<T>> outputValues() {
    return outputValues;
  }

  /**
   * Gets outputShape.
   * A list of 1-D tensors represents the shape of the output sparse
   * tensors.
   * @return outputShape.
   */
  public List<Output<TInt64>> outputShape() {
    return outputShape;
  }

  @OpInputsMetadata(
      outputsClass = SparseSplit.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SparseSplit<T>> {
    /**
     * 0-D.  The dimension along which to split.  Must be in the range
     * {@code [0, rank(shape))}.
     */
    public final Operand<TInt64> splitDim;

    /**
     * 2-D tensor represents the indices of the sparse tensor.
     */
    public final Operand<TInt64> indices;

    /**
     * 1-D tensor represents the values of the sparse tensor.
     */
    public final Operand<T> values;

    /**
     * 1-D. tensor represents the shape of the sparse tensor.
     * output indices: A list of 1-D tensors represents the indices of the output
     * sparse tensors.
     */
    public final Operand<TInt64> shape;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseSplit<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      splitDim = (Operand<TInt64>) op.input(inputIndex++);
      indices = (Operand<TInt64>) op.input(inputIndex++);
      values = (Operand<T>) op.input(inputIndex++);
      shape = (Operand<TInt64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
