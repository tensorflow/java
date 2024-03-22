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
 * The XlaSparseCoreFtrl operation
 */
@OpMetadata(
    opType = XlaSparseCoreFtrl.OP_NAME,
    inputsClass = XlaSparseCoreFtrl.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaSparseCoreFtrl extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseCoreFtrl";

  private Output<TFloat32> updatedEmbeddingTable;

  private Output<TFloat32> updatedAccumulator;

  private Output<TFloat32> updatedLinear;

  public XlaSparseCoreFtrl(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    updatedEmbeddingTable = operation.output(outputIdx++);
    updatedAccumulator = operation.output(outputIdx++);
    updatedLinear = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseCoreFtrl operation.
   *
   * @param scope current scope
   * @param embeddingTable The embeddingTable value
   * @param accumulator The accumulator value
   * @param linear The linear value
   * @param learningRate The learningRate value
   * @param indices The indices value
   * @param gradient The gradient value
   * @param beta The beta value
   * @param learningRatePower The learningRatePower value
   * @param l2RegularizationStrength The l2RegularizationStrength value
   * @param featureWidth The value of the featureWidth attribute
   * @param multiplyLinearByLearningRate The value of the multiplyLinearByLearningRate attribute
   * @param l1RegularizationStrength The value of the l1RegularizationStrength attribute
   * @return a new instance of XlaSparseCoreFtrl
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSparseCoreFtrl create(Scope scope, Operand<TFloat32> embeddingTable,
      Operand<TFloat32> accumulator, Operand<TFloat32> linear, Operand<TFloat32> learningRate,
      Operand<TInt32> indices, Operand<TFloat32> gradient, Operand<TFloat32> beta,
      Operand<TFloat32> learningRatePower, Operand<TFloat32> l2RegularizationStrength,
      Long featureWidth, Boolean multiplyLinearByLearningRate, Float l1RegularizationStrength) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseCoreFtrl");
    opBuilder.addInput(embeddingTable.asOutput());
    opBuilder.addInput(accumulator.asOutput());
    opBuilder.addInput(linear.asOutput());
    opBuilder.addInput(learningRate.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(gradient.asOutput());
    opBuilder.addInput(beta.asOutput());
    opBuilder.addInput(learningRatePower.asOutput());
    opBuilder.addInput(l2RegularizationStrength.asOutput());
    opBuilder.setAttr("feature_width", featureWidth);
    opBuilder.setAttr("multiply_linear_by_learning_rate", multiplyLinearByLearningRate);
    opBuilder.setAttr("l1_regularization_strength", l1RegularizationStrength);
    return new XlaSparseCoreFtrl(opBuilder.build());
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

  /**
   * Gets updatedLinear.
   *
   * @return updatedLinear.
   */
  public Output<TFloat32> updatedLinear() {
    return updatedLinear;
  }

  @OpInputsMetadata(
      outputsClass = XlaSparseCoreFtrl.class
  )
  public static class Inputs extends RawOpInputs<XlaSparseCoreFtrl> {
    /**
     * The embeddingTable input
     */
    public final Operand<TFloat32> embeddingTable;

    /**
     * The accumulator input
     */
    public final Operand<TFloat32> accumulator;

    /**
     * The linear input
     */
    public final Operand<TFloat32> linear;

    /**
     * The learningRate input
     */
    public final Operand<TFloat32> learningRate;

    /**
     * The indices input
     */
    public final Operand<TInt32> indices;

    /**
     * The gradient input
     */
    public final Operand<TFloat32> gradient;

    /**
     * The beta input
     */
    public final Operand<TFloat32> beta;

    /**
     * The learningRatePower input
     */
    public final Operand<TFloat32> learningRatePower;

    /**
     * The l2RegularizationStrength input
     */
    public final Operand<TFloat32> l2RegularizationStrength;

    /**
     * The featureWidth attribute
     */
    public final long featureWidth;

    /**
     * The multiplyLinearByLearningRate attribute
     */
    public final boolean multiplyLinearByLearningRate;

    /**
     * The l1RegularizationStrength attribute
     */
    public final float l1RegularizationStrength;

    public Inputs(GraphOperation op) {
      super(new XlaSparseCoreFtrl(op), op, Arrays.asList("feature_width", "multiply_linear_by_learning_rate", "l1_regularization_strength"));
      int inputIndex = 0;
      embeddingTable = (Operand<TFloat32>) op.input(inputIndex++);
      accumulator = (Operand<TFloat32>) op.input(inputIndex++);
      linear = (Operand<TFloat32>) op.input(inputIndex++);
      learningRate = (Operand<TFloat32>) op.input(inputIndex++);
      indices = (Operand<TInt32>) op.input(inputIndex++);
      gradient = (Operand<TFloat32>) op.input(inputIndex++);
      beta = (Operand<TFloat32>) op.input(inputIndex++);
      learningRatePower = (Operand<TFloat32>) op.input(inputIndex++);
      l2RegularizationStrength = (Operand<TFloat32>) op.input(inputIndex++);
      featureWidth = op.attributes().getAttrInt("feature_width");
      multiplyLinearByLearningRate = op.attributes().getAttrBool("multiply_linear_by_learning_rate");
      l1RegularizationStrength = op.attributes().getAttrFloat("l1_regularization_strength");
    }
  }
}
