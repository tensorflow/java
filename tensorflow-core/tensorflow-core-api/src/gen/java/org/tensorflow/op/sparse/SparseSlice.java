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
 * Slice a {@code SparseTensor} based on the {@code start} and {@code size}.
 * For example, if the input is
 * <pre>
 * input_tensor = shape = [2, 7]
 * [    a   d e  ]
 * [b c          ]
 * </pre>
 * <p>Graphically the output tensors are:
 * <pre>
 * sparse_slice([0, 0], [2, 4]) = shape = [2, 4]
 * [    a  ]
 * [b c    ]
 *
 * sparse_slice([0, 4], [2, 3]) = shape = [2, 3]
 * [ d e  ]
 * [      ]
 * </pre>
 *
 * @param <T> data type for {@code output_values} output
 */
@OpMetadata(
    opType = SparseSlice.OP_NAME,
    inputsClass = SparseSlice.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseSlice<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseSlice";

  private Output<TInt64> outputIndices;

  private Output<T> outputValues;

  private Output<TInt64> outputShape;

  public SparseSlice(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
    outputShape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseSlice operation.
   *
   * @param scope current scope
   * @param indices 2-D tensor represents the indices of the sparse tensor.
   * @param values 1-D tensor represents the values of the sparse tensor.
   * @param shape 1-D. tensor represents the shape of the sparse tensor.
   * @param start 1-D. tensor represents the start of the slice.
   * @param sizeOutput 1-D. tensor represents the size of the slice.
   * output indices: A list of 1-D tensors represents the indices of the output
   * sparse tensors.
   * @param <T> data type for {@code SparseSlice} output and operands
   * @return a new instance of SparseSlice
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseSlice<T> create(Scope scope, Operand<TInt64> indices,
      Operand<T> values, Operand<TInt64> shape, Operand<TInt64> start, Operand<TInt64> sizeOutput) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseSlice");
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(start.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    return new SparseSlice<>(opBuilder.build());
  }

  /**
   * Gets outputIndices.
   *
   * @return outputIndices.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }

  /**
   * Gets outputValues.
   * A list of 1-D tensors represents the values of the output sparse
   * tensors.
   * @return outputValues.
   */
  public Output<T> outputValues() {
    return outputValues;
  }

  /**
   * Gets outputShape.
   * A list of 1-D tensors represents the shape of the output sparse
   * tensors.
   * @return outputShape.
   */
  public Output<TInt64> outputShape() {
    return outputShape;
  }

  @OpInputsMetadata(
      outputsClass = SparseSlice.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SparseSlice<T>> {
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
     */
    public final Operand<TInt64> shape;

    /**
     * 1-D. tensor represents the start of the slice.
     */
    public final Operand<TInt64> start;

    /**
     * 1-D. tensor represents the size of the slice.
     * output indices: A list of 1-D tensors represents the indices of the output
     * sparse tensors.
     */
    public final Operand<TInt64> sizeOutput;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseSlice<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      indices = (Operand<TInt64>) op.input(inputIndex++);
      values = (Operand<T>) op.input(inputIndex++);
      shape = (Operand<TInt64>) op.input(inputIndex++);
      start = (Operand<TInt64>) op.input(inputIndex++);
      sizeOutput = (Operand<TInt64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
