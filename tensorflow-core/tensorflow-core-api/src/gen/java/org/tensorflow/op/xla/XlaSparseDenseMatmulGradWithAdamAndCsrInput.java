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
 * The XlaSparseDenseMatmulGradWithAdamAndCsrInput operation
 */
@OpMetadata(
    opType = XlaSparseDenseMatmulGradWithAdamAndCsrInput.OP_NAME,
    inputsClass = XlaSparseDenseMatmulGradWithAdamAndCsrInput.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaSparseDenseMatmulGradWithAdamAndCsrInput extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseDenseMatmulGradWithAdamAndCsrInput";

  private Output<TFloat32> updatedEmbeddingTable;

  private Output<TFloat32> updatedMomenta;

  private Output<TFloat32> updatedVelocity;

  public XlaSparseDenseMatmulGradWithAdamAndCsrInput(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    updatedEmbeddingTable = operation.output(outputIdx++);
    updatedMomenta = operation.output(outputIdx++);
    updatedVelocity = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseDenseMatmulGradWithAdamAndCsrInput operation.
   *
   * @param scope current scope
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedGains The sortedGains value
   * @param activationGradients The activationGradients value
   * @param learningRate The learningRate value
   * @param embeddingTable The embeddingTable value
   * @param momenta The momenta value
   * @param velocity The velocity value
   * @param numMinibatchesPerPhysicalSparseCore The numMinibatchesPerPhysicalSparseCore value
   * @param useSumInsideSqrt The value of the useSumInsideSqrt attribute
   * @param beta1 The value of the beta1 attribute
   * @param beta2 The value of the beta2 attribute
   * @param epsilon The value of the epsilon attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulGradWithAdamAndCsrInput
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSparseDenseMatmulGradWithAdamAndCsrInput create(Scope scope,
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TFloat32> sortedGains, Operand<TFloat32> activationGradients,
      Operand<TFloat32> learningRate, Operand<TFloat32> embeddingTable, Operand<TFloat32> momenta,
      Operand<TFloat32> velocity, Operand<TInt32> numMinibatchesPerPhysicalSparseCore,
      Boolean useSumInsideSqrt, Float beta1, Float beta2, Float epsilon, String tableName,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseDenseMatmulGradWithAdamAndCsrInput");
    opBuilder.addInput(rowPointers.asOutput());
    opBuilder.addInput(sortedSampleIds.asOutput());
    opBuilder.addInput(sortedTokenIds.asOutput());
    opBuilder.addInput(sortedGains.asOutput());
    opBuilder.addInput(activationGradients.asOutput());
    opBuilder.addInput(learningRate.asOutput());
    opBuilder.addInput(embeddingTable.asOutput());
    opBuilder.addInput(momenta.asOutput());
    opBuilder.addInput(velocity.asOutput());
    opBuilder.addInput(numMinibatchesPerPhysicalSparseCore.asOutput());
    opBuilder.setAttr("use_sum_inside_sqrt", useSumInsideSqrt);
    opBuilder.setAttr("beta1", beta1);
    opBuilder.setAttr("beta2", beta2);
    opBuilder.setAttr("epsilon", epsilon);
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
    return new XlaSparseDenseMatmulGradWithAdamAndCsrInput(opBuilder.build());
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
   * Gets updatedMomenta.
   *
   * @return updatedMomenta.
   */
  public Output<TFloat32> updatedMomenta() {
    return updatedMomenta;
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
   * Optional attributes for {@link org.tensorflow.op.xla.XlaSparseDenseMatmulGradWithAdamAndCsrInput}
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
      outputsClass = XlaSparseDenseMatmulGradWithAdamAndCsrInput.class
  )
  public static class Inputs extends RawOpInputs<XlaSparseDenseMatmulGradWithAdamAndCsrInput> {
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
     * The momenta input
     */
    public final Operand<TFloat32> momenta;

    /**
     * The velocity input
     */
    public final Operand<TFloat32> velocity;

    /**
     * The numMinibatchesPerPhysicalSparseCore input
     */
    public final Operand<TInt32> numMinibatchesPerPhysicalSparseCore;

    /**
     * The useSumInsideSqrt attribute
     */
    public final boolean useSumInsideSqrt;

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
     * The tableName attribute
     */
    public final String tableName;

    /**
     * The numSparsecoresPerDevice attribute
     */
    public final long numSparsecoresPerDevice;

    public Inputs(GraphOperation op) {
      super(new XlaSparseDenseMatmulGradWithAdamAndCsrInput(op), op, Arrays.asList("use_sum_inside_sqrt", "beta1", "beta2", "epsilon", "clip_weight_min", "clip_weight_max", "table_name", "num_sparsecores_per_device"));
      int inputIndex = 0;
      rowPointers = (Operand<TInt32>) op.input(inputIndex++);
      sortedSampleIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedTokenIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedGains = (Operand<TFloat32>) op.input(inputIndex++);
      activationGradients = (Operand<TFloat32>) op.input(inputIndex++);
      learningRate = (Operand<TFloat32>) op.input(inputIndex++);
      embeddingTable = (Operand<TFloat32>) op.input(inputIndex++);
      momenta = (Operand<TFloat32>) op.input(inputIndex++);
      velocity = (Operand<TFloat32>) op.input(inputIndex++);
      numMinibatchesPerPhysicalSparseCore = (Operand<TInt32>) op.input(inputIndex++);
      useSumInsideSqrt = op.attributes().getAttrBool("use_sum_inside_sqrt");
      beta1 = op.attributes().getAttrFloat("beta1");
      beta2 = op.attributes().getAttrFloat("beta2");
      epsilon = op.attributes().getAttrFloat("epsilon");
      clipWeightMin = op.attributes().getAttrFloat("clip_weight_min");
      clipWeightMax = op.attributes().getAttrFloat("clip_weight_max");
      tableName = op.attributes().getAttrString("table_name");
      numSparsecoresPerDevice = op.attributes().getAttrInt("num_sparsecores_per_device");
    }
  }
}
