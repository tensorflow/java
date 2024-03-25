/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.xla;

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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * The XlaSparseCoreAdagrad operation
 */
@OpMetadata(
    opType = XlaSparseCoreAdagrad.OP_NAME,
    inputsClass = XlaSparseCoreAdagrad.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaSparseCoreAdagrad extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseCoreAdagrad";

  private Output<TFloat32> updatedEmbeddingTable;

  private Output<TFloat32> updatedAccumulator;

  public XlaSparseCoreAdagrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    updatedEmbeddingTable = operation.output(outputIdx++);
    updatedAccumulator = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseCoreAdagrad operation.
   *
   * @param scope current scope
   * @param indices The indices value
   * @param gradient The gradient value
   * @param learningRate The learningRate value
   * @param accumulator The accumulator value
   * @param embeddingTable The embeddingTable value
   * @param featureWidth The value of the featureWidth attribute
   * @return a new instance of XlaSparseCoreAdagrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSparseCoreAdagrad create(Scope scope, Operand<TInt32> indices,
      Operand<TFloat32> gradient, Operand<TFloat32> learningRate, Operand<TFloat32> accumulator,
      Operand<TFloat32> embeddingTable, Long featureWidth) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseCoreAdagrad");
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(gradient.asOutput());
    opBuilder.addInput(learningRate.asOutput());
    opBuilder.addInput(accumulator.asOutput());
    opBuilder.addInput(embeddingTable.asOutput());
    opBuilder.setAttr("feature_width", featureWidth);
    return new XlaSparseCoreAdagrad(opBuilder.build());
  }

  /**
   * Gets updatedEmbeddingTable.
   *
   * @return updatedEmbeddingTable.
   */
  public Output<TFloat32> updatedEmbeddingTable() {
    return updatedEmbeddingTable;
  }

  /**
   * Gets updatedAccumulator.
   *
   * @return updatedAccumulator.
   */
  public Output<TFloat32> updatedAccumulator() {
    return updatedAccumulator;
  }

  @OpInputsMetadata(
      outputsClass = XlaSparseCoreAdagrad.class
  )
  public static class Inputs extends RawOpInputs<XlaSparseCoreAdagrad> {
    /**
     * The indices input
     */
    public final Operand<TInt32> indices;

    /**
     * The gradient input
     */
    public final Operand<TFloat32> gradient;

    /**
     * The learningRate input
     */
    public final Operand<TFloat32> learningRate;

    /**
     * The accumulator input
     */
    public final Operand<TFloat32> accumulator;

    /**
     * The embeddingTable input
     */
    public final Operand<TFloat32> embeddingTable;

    /**
     * The featureWidth attribute
     */
    public final long featureWidth;

    public Inputs(GraphOperation op) {
      super(new XlaSparseCoreAdagrad(op), op, Arrays.asList("feature_width"));
      int inputIndex = 0;
      indices = (Operand<TInt32>) op.input(inputIndex++);
      gradient = (Operand<TFloat32>) op.input(inputIndex++);
      learningRate = (Operand<TFloat32>) op.input(inputIndex++);
      accumulator = (Operand<TFloat32>) op.input(inputIndex++);
      embeddingTable = (Operand<TFloat32>) op.input(inputIndex++);
      featureWidth = op.attributes().getAttrInt("feature_width");
    }
  }
}
