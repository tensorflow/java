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
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
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
 * Creates a dataset that batches input elements into a SparseTensor.
 */
@OpMetadata(
    opType = DenseToSparseBatchDataset.OP_NAME,
    inputsClass = DenseToSparseBatchDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class DenseToSparseBatchDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DenseToSparseBatchDataset";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public DenseToSparseBatchDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DenseToSparseBatchDataset operation.
   *
   * @param scope current scope
   * @param inputDataset A handle to an input dataset. Must have a single component.
   * @param batchSize A scalar representing the number of elements to accumulate in a
   * batch.
   * @param rowShape A vector representing the dense shape of each row in the produced
   * SparseTensor. The shape may be partially specified, using {@code -1} to indicate
   * that a particular dimension should use the maximum size of all batch elements.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of DenseToSparseBatchDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static DenseToSparseBatchDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TInt64> batchSize, Operand<TInt64> rowShape, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DenseToSparseBatchDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(batchSize.asOutput());
    opBuilder.addInput(rowShape.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new DenseToSparseBatchDataset(opBuilder.build());
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
      outputsClass = DenseToSparseBatchDataset.class
  )
  public static class Inputs extends RawOpInputs<DenseToSparseBatchDataset> {
    /**
     * A handle to an input dataset. Must have a single component.
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * A scalar representing the number of elements to accumulate in a
     * batch.
     */
    public final Operand<TInt64> batchSize;

    /**
     * A vector representing the dense shape of each row in the produced
     * SparseTensor. The shape may be partially specified, using {@code -1} to indicate
     * that a particular dimension should use the maximum size of all batch elements.
     */
    public final Operand<TInt64> rowShape;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    public Inputs(GraphOperation op) {
      super(new DenseToSparseBatchDataset(op), op, Arrays.asList("output_types", "output_shapes"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      batchSize = (Operand<TInt64>) op.input(inputIndex++);
      rowShape = (Operand<TInt64>) op.input(inputIndex++);
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
    }
  }
}
