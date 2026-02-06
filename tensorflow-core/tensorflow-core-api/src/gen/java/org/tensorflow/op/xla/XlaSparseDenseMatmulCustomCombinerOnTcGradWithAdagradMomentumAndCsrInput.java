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
import org.tensorflow.ConcreteFunction;
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
 * The XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput operation
 */
@OpMetadata(
    opType = XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput.OP_NAME,
    inputsClass = XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput";

  private Output<TFloat32> updatedEmbeddingTable;

  private Output<TFloat32> updatedAccumulator;

  private Output<TFloat32> updatedMomenta;

  private Output<TFloat32> updatedWeights;

  public XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput(
      Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    updatedEmbeddingTable = operation.output(outputIdx++);
    updatedAccumulator = operation.output(outputIdx++);
    updatedMomenta = operation.output(outputIdx++);
    updatedWeights = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput operation.
   *
   * @param scope current scope
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedPosIds The sortedPosIds value
   * @param sortedGains The sortedGains value
   * @param weights The weights value
   * @param preservedValencies The preservedValencies value
   * @param preservedVectors The preservedVectors value
   * @param preservedWeights The preservedWeights value
   * @param activationGradients The activationGradients value
   * @param learningRate The learningRate value
   * @param combinerWeightsLearningRate The combinerWeightsLearningRate value
   * @param embeddingTable The embeddingTable value
   * @param accumulator The accumulator value
   * @param momenta The momenta value
   * @param useNesterov The value of the useNesterov attribute
   * @param exponent The value of the exponent attribute
   * @param beta1 The value of the beta1 attribute
   * @param beta2 The value of the beta2 attribute
   * @param epsilon The value of the epsilon attribute
   * @param maxValency The value of the maxValency attribute
   * @param numWeights The value of the numWeights attribute
   * @param combinerTableVjpComputation The value of the combinerTableVjpComputation attribute
   * @param combinerWeightsVjpComputation The value of the combinerWeightsVjpComputation attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput create(
      Scope scope, Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds,
      Operand<TInt32> sortedTokenIds, Operand<TInt32> sortedPosIds, Operand<TFloat32> sortedGains,
      Operand<TFloat32> weights, Operand<TInt32> preservedValencies,
      Operand<TFloat32> preservedVectors, Operand<TFloat32> preservedWeights,
      Operand<TFloat32> activationGradients, Operand<TFloat32> learningRate,
      Operand<TFloat32> combinerWeightsLearningRate, Operand<TFloat32> embeddingTable,
      Operand<TFloat32> accumulator, Operand<TFloat32> momenta, Boolean useNesterov, Float exponent,
      Float beta1, Float beta2, Float epsilon, Long maxValency, Long numWeights,
      ConcreteFunction combinerTableVjpComputation, ConcreteFunction combinerWeightsVjpComputation,
      String tableName, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput");
    opBuilder.addInput(rowPointers.asOutput());
    opBuilder.addInput(sortedSampleIds.asOutput());
    opBuilder.addInput(sortedTokenIds.asOutput());
    opBuilder.addInput(sortedPosIds.asOutput());
    opBuilder.addInput(sortedGains.asOutput());
    opBuilder.addInput(weights.asOutput());
    opBuilder.addInput(preservedValencies.asOutput());
    opBuilder.addInput(preservedVectors.asOutput());
    opBuilder.addInput(preservedWeights.asOutput());
    opBuilder.addInput(activationGradients.asOutput());
    opBuilder.addInput(learningRate.asOutput());
    opBuilder.addInput(combinerWeightsLearningRate.asOutput());
    opBuilder.addInput(embeddingTable.asOutput());
    opBuilder.addInput(accumulator.asOutput());
    opBuilder.addInput(momenta.asOutput());
    opBuilder.setAttr("use_nesterov", useNesterov);
    opBuilder.setAttr("exponent", exponent);
    opBuilder.setAttr("beta1", beta1);
    opBuilder.setAttr("beta2", beta2);
    opBuilder.setAttr("epsilon", epsilon);
    opBuilder.setAttr("max_valency", maxValency);
    opBuilder.setAttr("num_weights", numWeights);
    opBuilder.setAttr("combiner_table_vjp_computation", combinerTableVjpComputation);
    opBuilder.setAttr("combiner_weights_vjp_computation", combinerWeightsVjpComputation);
    opBuilder.setAttr("table_name", tableName);
    if (options != null) {
      for (Options opts : options) {
        if (opts.clipWeightMin != null) {
          opBuilder.setAttr("clip_weight_min", opts.clipWeightMin);
        }
        if (opts.clipWeightMax != null) {
          opBuilder.setAttr("clip_weight_max", opts.clipWeightMax);
        }
        if (opts.numSparsecoresPerDevice != null) {
          opBuilder.setAttr("num_sparsecores_per_device", opts.numSparsecoresPerDevice);
        }
      }
    }
    return new XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput(opBuilder.build());
  }

  /**
   * Sets the clipWeightMin option.
   *
   * @param clipWeightMin the clipWeightMin option
   * @return this Options instance.
   */
  public static Options clipWeightMin(Float clipWeightMin) {
    return new Options().clipWeightMin(clipWeightMin);
  }

  /**
   * Sets the clipWeightMax option.
   *
   * @param clipWeightMax the clipWeightMax option
   * @return this Options instance.
   */
  public static Options clipWeightMax(Float clipWeightMax) {
    return new Options().clipWeightMax(clipWeightMax);
  }

  /**
   * Sets the numSparsecoresPerDevice option.
   *
   * @param numSparsecoresPerDevice the numSparsecoresPerDevice option
   * @return this Options instance.
   */
  public static Options numSparsecoresPerDevice(Long numSparsecoresPerDevice) {
    return new Options().numSparsecoresPerDevice(numSparsecoresPerDevice);
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
   * Gets updatedMomenta.
   *
   * @return updatedMomenta.
   */
  public Output<TFloat32> updatedMomenta() {
    return updatedMomenta;
  }

  /**
   * Gets updatedWeights.
   *
   * @return updatedWeights.
   */
  public Output<TFloat32> updatedWeights() {
    return updatedWeights;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.xla.XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput}
   */
  public static class Options {
    private Float clipWeightMin;

    private Float clipWeightMax;

    private Long numSparsecoresPerDevice;

    private Options() {
    }

    /**
     * Sets the clipWeightMin option.
     *
     * @param clipWeightMin the clipWeightMin option
     * @return this Options instance.
     */
    public Options clipWeightMin(Float clipWeightMin) {
      this.clipWeightMin = clipWeightMin;
      return this;
    }

    /**
     * Sets the clipWeightMax option.
     *
     * @param clipWeightMax the clipWeightMax option
     * @return this Options instance.
     */
    public Options clipWeightMax(Float clipWeightMax) {
      this.clipWeightMax = clipWeightMax;
      return this;
    }

    /**
     * Sets the numSparsecoresPerDevice option.
     *
     * @param numSparsecoresPerDevice the numSparsecoresPerDevice option
     * @return this Options instance.
     */
    public Options numSparsecoresPerDevice(Long numSparsecoresPerDevice) {
      this.numSparsecoresPerDevice = numSparsecoresPerDevice;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput.class
  )
  public static class Inputs extends RawOpInputs<XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput> {
    /**
     * The rowPointers input
     */
    public final Operand<TInt32> rowPointers;

    /**
     * The sortedSampleIds input
     */
    public final Operand<TInt32> sortedSampleIds;

    /**
     * The sortedTokenIds input
     */
    public final Operand<TInt32> sortedTokenIds;

    /**
     * The sortedPosIds input
     */
    public final Operand<TInt32> sortedPosIds;

    /**
     * The sortedGains input
     */
    public final Operand<TFloat32> sortedGains;

    /**
     * The weights input
     */
    public final Operand<TFloat32> weights;

    /**
     * The preservedValencies input
     */
    public final Operand<TInt32> preservedValencies;

    /**
     * The preservedVectors input
     */
    public final Operand<TFloat32> preservedVectors;

    /**
     * The preservedWeights input
     */
    public final Operand<TFloat32> preservedWeights;

    /**
     * The activationGradients input
     */
    public final Operand<TFloat32> activationGradients;

    /**
     * The learningRate input
     */
    public final Operand<TFloat32> learningRate;

    /**
     * The combinerWeightsLearningRate input
     */
    public final Operand<TFloat32> combinerWeightsLearningRate;

    /**
     * The embeddingTable input
     */
    public final Operand<TFloat32> embeddingTable;

    /**
     * The accumulator input
     */
    public final Operand<TFloat32> accumulator;

    /**
     * The momenta input
     */
    public final Operand<TFloat32> momenta;

    /**
     * The useNesterov attribute
     */
    public final boolean useNesterov;

    /**
     * The exponent attribute
     */
    public final float exponent;

    /**
     * The beta1 attribute
     */
    public final float beta1;

    /**
     * The beta2 attribute
     */
    public final float beta2;

    /**
     * The epsilon attribute
     */
    public final float epsilon;

    /**
     * The clipWeightMin attribute
     */
    public final float clipWeightMin;

    /**
     * The clipWeightMax attribute
     */
    public final float clipWeightMax;

    /**
     * The maxValency attribute
     */
    public final long maxValency;

    /**
     * The numWeights attribute
     */
    public final long numWeights;

    /**
     * The tableName attribute
     */
    public final String tableName;

    /**
     * The numSparsecoresPerDevice attribute
     */
    public final long numSparsecoresPerDevice;

    public Inputs(GraphOperation op) {
      super(new XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput(op), op, Arrays.asList("use_nesterov", "exponent", "beta1", "beta2", "epsilon", "clip_weight_min", "clip_weight_max", "max_valency", "num_weights", "table_name", "num_sparsecores_per_device"));
      int inputIndex = 0;
      rowPointers = (Operand<TInt32>) op.input(inputIndex++);
      sortedSampleIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedTokenIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedPosIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedGains = (Operand<TFloat32>) op.input(inputIndex++);
      weights = (Operand<TFloat32>) op.input(inputIndex++);
      preservedValencies = (Operand<TInt32>) op.input(inputIndex++);
      preservedVectors = (Operand<TFloat32>) op.input(inputIndex++);
      preservedWeights = (Operand<TFloat32>) op.input(inputIndex++);
      activationGradients = (Operand<TFloat32>) op.input(inputIndex++);
      learningRate = (Operand<TFloat32>) op.input(inputIndex++);
      combinerWeightsLearningRate = (Operand<TFloat32>) op.input(inputIndex++);
      embeddingTable = (Operand<TFloat32>) op.input(inputIndex++);
      accumulator = (Operand<TFloat32>) op.input(inputIndex++);
      momenta = (Operand<TFloat32>) op.input(inputIndex++);
      useNesterov = op.attributes().getAttrBool("use_nesterov");
      exponent = op.attributes().getAttrFloat("exponent");
      beta1 = op.attributes().getAttrFloat("beta1");
      beta2 = op.attributes().getAttrFloat("beta2");
      epsilon = op.attributes().getAttrFloat("epsilon");
      clipWeightMin = op.attributes().getAttrFloat("clip_weight_min");
      clipWeightMax = op.attributes().getAttrFloat("clip_weight_max");
      maxValency = op.attributes().getAttrInt("max_valency");
      numWeights = op.attributes().getAttrInt("num_weights");
      tableName = op.attributes().getAttrString("table_name");
      numSparsecoresPerDevice = op.attributes().getAttrInt("num_sparsecores_per_device");
    }
  }
}
