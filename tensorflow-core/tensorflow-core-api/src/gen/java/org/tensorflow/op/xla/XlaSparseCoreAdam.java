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
 * The XlaSparseCoreAdam operation
 */
@OpMetadata(
    opType = XlaSparseCoreAdam.OP_NAME,
    inputsClass = XlaSparseCoreAdam.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaSparseCoreAdam extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseCoreAdam";

  private Output<TFloat32> updatedEmbeddingTable;

  private Output<TFloat32> updatedVelocity;

  private Output<TFloat32> updatedMomentum;

  public XlaSparseCoreAdam(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    updatedEmbeddingTable = operation.output(outputIdx++);
    updatedVelocity = operation.output(outputIdx++);
    updatedMomentum = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseCoreAdam operation.
   *
   * @param scope current scope
   * @param embeddingTable The embeddingTable value
   * @param indices The indices value
   * @param gradient The gradient value
   * @param learningRate The learningRate value
   * @param momentum The momentum value
   * @param velocity The velocity value
   * @param beta1 The beta1 value
   * @param beta2 The beta2 value
   * @param epsilon The epsilon value
   * @param featureWidth The value of the featureWidth attribute
   * @param useSumInsideSqrt The value of the useSumInsideSqrt attribute
   * @return a new instance of XlaSparseCoreAdam
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSparseCoreAdam create(Scope scope, Operand<TFloat32> embeddingTable,
      Operand<TInt32> indices, Operand<TFloat32> gradient, Operand<TFloat32> learningRate,
      Operand<TFloat32> momentum, Operand<TFloat32> velocity, Operand<TFloat32> beta1,
      Operand<TFloat32> beta2, Operand<TFloat32> epsilon, Long featureWidth,
      Boolean useSumInsideSqrt) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseCoreAdam");
    opBuilder.addInput(embeddingTable.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(gradient.asOutput());
    opBuilder.addInput(learningRate.asOutput());
    opBuilder.addInput(momentum.asOutput());
    opBuilder.addInput(velocity.asOutput());
    opBuilder.addInput(beta1.asOutput());
    opBuilder.addInput(beta2.asOutput());
    opBuilder.addInput(epsilon.asOutput());
    opBuilder.setAttr("feature_width", featureWidth);
    opBuilder.setAttr("use_sum_inside_sqrt", useSumInsideSqrt);
    return new XlaSparseCoreAdam(opBuilder.build());
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
   * Gets updatedVelocity.
   *
   * @return updatedVelocity.
   */
  public Output<TFloat32> updatedVelocity() {
    return updatedVelocity;
  }

  /**
   * Gets updatedMomentum.
   *
   * @return updatedMomentum.
   */
  public Output<TFloat32> updatedMomentum() {
    return updatedMomentum;
  }

  @OpInputsMetadata(
      outputsClass = XlaSparseCoreAdam.class
  )
  public static class Inputs extends RawOpInputs<XlaSparseCoreAdam> {
    /**
     * The embeddingTable input
     */
    public final Operand<TFloat32> embeddingTable;

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
     * The momentum input
     */
    public final Operand<TFloat32> momentum;

    /**
     * The velocity input
     */
    public final Operand<TFloat32> velocity;

    /**
     * The beta1 input
     */
    public final Operand<TFloat32> beta1;

    /**
     * The beta2 input
     */
    public final Operand<TFloat32> beta2;

    /**
     * The epsilon input
     */
    public final Operand<TFloat32> epsilon;

    /**
     * The featureWidth attribute
     */
    public final long featureWidth;

    /**
     * The useSumInsideSqrt attribute
     */
    public final boolean useSumInsideSqrt;

    public Inputs(GraphOperation op) {
      super(new XlaSparseCoreAdam(op), op, Arrays.asList("feature_width", "use_sum_inside_sqrt"));
      int inputIndex = 0;
      embeddingTable = (Operand<TFloat32>) op.input(inputIndex++);
      indices = (Operand<TInt32>) op.input(inputIndex++);
      gradient = (Operand<TFloat32>) op.input(inputIndex++);
      learningRate = (Operand<TFloat32>) op.input(inputIndex++);
      momentum = (Operand<TFloat32>) op.input(inputIndex++);
      velocity = (Operand<TFloat32>) op.input(inputIndex++);
      beta1 = (Operand<TFloat32>) op.input(inputIndex++);
      beta2 = (Operand<TFloat32>) op.input(inputIndex++);
      epsilon = (Operand<TFloat32>) op.input(inputIndex++);
      featureWidth = op.attributes().getAttrInt("feature_width");
      useSumInsideSqrt = op.attributes().getAttrBool("use_sum_inside_sqrt");
    }
  }
}
