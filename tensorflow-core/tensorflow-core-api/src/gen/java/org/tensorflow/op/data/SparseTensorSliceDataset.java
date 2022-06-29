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

package org.tensorflow.op.data;

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
 * Creates a dataset that splits a SparseTensor into elements row-wise.
 */
@OpMetadata(
    opType = SparseTensorSliceDataset.OP_NAME,
    inputsClass = SparseTensorSliceDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class SparseTensorSliceDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseTensorSliceDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public SparseTensorSliceDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseTensorSliceDataset operation.
   *
   * @param scope current scope
   * @param indices The indices value
   * @param values The values value
   * @param denseShape The denseShape value
   * @return a new instance of SparseTensorSliceDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static SparseTensorSliceDataset create(Scope scope, Operand<TInt64> indices,
      Operand<? extends TType> values, Operand<TInt64> denseShape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseTensorSliceDataset");
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(denseShape.asOutput());
    return new SparseTensorSliceDataset(opBuilder.build());
  }

  /**
   * Gets handle.
   *
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  @OpInputsMetadata(
      outputsClass = SparseTensorSliceDataset.class
  )
  public static class Inputs extends RawOpInputs<SparseTensorSliceDataset> {
    /**
     * The indices input
     */
    public final Operand<TInt64> indices;

    /**
     * The values input
     */
    public final Operand<? extends TType> values;

    /**
     * The denseShape input
     */
    public final Operand<TInt64> denseShape;

    /**
     * The Tvalues attribute
     */
    public final DataType Tvalues;

    public Inputs(GraphOperation op) {
      super(new SparseTensorSliceDataset(op), op, Arrays.asList("Tvalues"));
      int inputIndex = 0;
      indices = (Operand<TInt64>) op.input(inputIndex++);
      values = (Operand<? extends TType>) op.input(inputIndex++);
      denseShape = (Operand<TInt64>) op.input(inputIndex++);
      Tvalues = op.attributes().getAttrType("Tvalues");
    }
  }
}
