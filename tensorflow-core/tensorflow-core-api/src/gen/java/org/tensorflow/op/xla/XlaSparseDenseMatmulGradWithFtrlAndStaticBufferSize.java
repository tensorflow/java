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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * The XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize operation
 */
@OpMetadata(
    opType = XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize.OP_NAME,
    inputsClass = XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize.Inputs.class
)
public final class XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize";

  private Output<TFloat32> updatedEmbeddingTable;

  private Output<TFloat32> updatedAccumulator;

  private Output<TFloat32> updatedLinear;

  public XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    updatedEmbeddingTable = operation.output(outputIdx++);
    updatedAccumulator = operation.output(outputIdx++);
    updatedLinear = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize operation.
   *
   * @param scope current scope
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedGains The sortedGains value
   * @param activationGradients The activationGradients value
   * @param learningRate The learningRate value
   * @param embeddingTable The embeddingTable value
   * @param accumulator The accumulator value
   * @param linear The linear value
   * @param numMinibatchesPerPhysicalSparseCore The numMinibatchesPerPhysicalSparseCore value
   * @param multiplyLinearByLearningRate The value of the multiplyLinearByLearningRate attribute
   * @param beta The value of the beta attribute
   * @param learningRatePower The value of the learningRatePower attribute
   * @param l1RegularizationStrength The value of the l1RegularizationStrength attribute
   * @param l2RegularizationStrength The value of the l2RegularizationStrength attribute
   * @param maxIdsPerSparseCore The value of the maxIdsPerSparseCore attribute
   * @param maxUniqueIdsPerSparseCore The value of the maxUniqueIdsPerSparseCore attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize create(Scope scope,
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TFloat32> sortedGains, Operand<TFloat32> activationGradients,
      Operand<TFloat32> learningRate, Operand<TFloat32> embeddingTable,
      Operand<TFloat32> accumulator, Operand<TFloat32> linear,
      Operand<TInt32> numMinibatchesPerPhysicalSparseCore, Boolean multiplyLinearByLearningRate,
      Float beta, Float learningRatePower, Float l1RegularizationStrength,
      Float l2RegularizationStrength, Long maxIdsPerSparseCore, Long maxUniqueIdsPerSparseCore,
      String tableName, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize");
    opBuilder.addInput(rowPointers.asOutput());
    opBuilder.addInput(sortedSampleIds.asOutput());
    opBuilder.addInput(sortedTokenIds.asOutput());
    opBuilder.addInput(sortedGains.asOutput());
    opBuilder.addInput(activationGradients.asOutput());
    opBuilder.addInput(learningRate.asOutput());
    opBuilder.addInput(embeddingTable.asOutput());
    opBuilder.addInput(accumulator.asOutput());
    opBuilder.addInput(linear.asOutput());
    opBuilder.addInput(numMinibatchesPerPhysicalSparseCore.asOutput());
    opBuilder.setAttr("multiply_linear_by_learning_rate", multiplyLinearByLearningRate);
    opBuilder.setAttr("beta", beta);
    opBuilder.setAttr("learning_rate_power", learningRatePower);
    opBuilder.setAttr("l1_regularization_strength", l1RegularizationStrength);
    opBuilder.setAttr("l2_regularization_strength", l2RegularizationStrength);
    opBuilder.setAttr("max_ids_per_sparse_core", maxIdsPerSparseCore);
    opBuilder.setAttr("max_unique_ids_per_sparse_core", maxUniqueIdsPerSparseCore);
    opBuilder.setAttr("table_name", tableName);
    if (options != null) {
      for (Options opts : options) {
        if (opts.clipWeightMin != null) {
          opBuilder.setAttr("clip_weight_min", opts.clipWeightMin);
        }
        if (opts.clipWeightMax != null) {
          opBuilder.setAttr("clip_weight_max", opts.clipWeightMax);
        }
      }
    }
    return new XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize(opBuilder.build());
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

  /**
   * Optional attributes for {@link org.tensorflow.op.xla.XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize}
   */
  public static class Options {
    private Float clipWeightMin;

    private Float clipWeightMax;

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
  }

  @OpInputsMetadata(
      outputsClass = XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize.class
  )
  public static class Inputs extends RawOpInputs<XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize> {
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
     * The sortedGains input
     */
    public final Operand<TFloat32> sortedGains;

    /**
     * The activationGradients input
     */
    public final Operand<TFloat32> activationGradients;

    /**
     * The learningRate input
     */
    public final Operand<TFloat32> learningRate;

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
     * The numMinibatchesPerPhysicalSparseCore input
     */
    public final Operand<TInt32> numMinibatchesPerPhysicalSparseCore;

    /**
     * The multiplyLinearByLearningRate attribute
     */
    public final boolean multiplyLinearByLearningRate;

    /**
     * The beta attribute
     */
    public final float beta;

    /**
     * The learningRatePower attribute
     */
    public final float learningRatePower;

    /**
     * The l1RegularizationStrength attribute
     */
    public final float l1RegularizationStrength;

    /**
     * The l2RegularizationStrength attribute
     */
    public final float l2RegularizationStrength;

    /**
     * The clipWeightMin attribute
     */
    public final float clipWeightMin;

    /**
     * The clipWeightMax attribute
     */
    public final float clipWeightMax;

    /**
     * The maxIdsPerSparseCore attribute
     */
    public final long maxIdsPerSparseCore;

    /**
     * The maxUniqueIdsPerSparseCore attribute
     */
    public final long maxUniqueIdsPerSparseCore;

    /**
     * The tableName attribute
     */
    public final String tableName;

    public Inputs(GraphOperation op) {
      super(new XlaSparseDenseMatmulGradWithFtrlAndStaticBufferSize(op), op, Arrays.asList("multiply_linear_by_learning_rate", "beta", "learning_rate_power", "l1_regularization_strength", "l2_regularization_strength", "clip_weight_min", "clip_weight_max", "max_ids_per_sparse_core", "max_unique_ids_per_sparse_core", "table_name"));
      int inputIndex = 0;
      rowPointers = (Operand<TInt32>) op.input(inputIndex++);
      sortedSampleIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedTokenIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedGains = (Operand<TFloat32>) op.input(inputIndex++);
      activationGradients = (Operand<TFloat32>) op.input(inputIndex++);
      learningRate = (Operand<TFloat32>) op.input(inputIndex++);
      embeddingTable = (Operand<TFloat32>) op.input(inputIndex++);
      accumulator = (Operand<TFloat32>) op.input(inputIndex++);
      linear = (Operand<TFloat32>) op.input(inputIndex++);
      numMinibatchesPerPhysicalSparseCore = (Operand<TInt32>) op.input(inputIndex++);
      multiplyLinearByLearningRate = op.attributes().getAttrBool("multiply_linear_by_learning_rate");
      beta = op.attributes().getAttrFloat("beta");
      learningRatePower = op.attributes().getAttrFloat("learning_rate_power");
      l1RegularizationStrength = op.attributes().getAttrFloat("l1_regularization_strength");
      l2RegularizationStrength = op.attributes().getAttrFloat("l2_regularization_strength");
      clipWeightMin = op.attributes().getAttrFloat("clip_weight_min");
      clipWeightMax = op.attributes().getAttrFloat("clip_weight_max");
      maxIdsPerSparseCore = op.attributes().getAttrInt("max_ids_per_sparse_core");
      maxUniqueIdsPerSparseCore = op.attributes().getAttrInt("max_unique_ids_per_sparse_core");
      tableName = op.attributes().getAttrString("table_name");
    }
  }
}
