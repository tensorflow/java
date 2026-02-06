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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * The XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput operation
 */
@OpMetadata(
    opType = XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput.OP_NAME,
    inputsClass = XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput.Inputs.class
)
public final class XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput";

  private Output<TFloat32> activations;

  private Output<TInt32> preservedValencies;

  private Output<TFloat32> preservedVectors;

  public XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    activations = operation.output(outputIdx++);
    preservedValencies = operation.output(outputIdx++);
    preservedVectors = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput operation.
   *
   * @param scope current scope
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedPosIds The sortedPosIds value
   * @param sortedGains The sortedGains value
   * @param embeddingTable The embeddingTable value
   * @param weights The weights value
   * @param inputSize The value of the inputSize attribute
   * @param maxValency The value of the maxValency attribute
   * @param numWeights The value of the numWeights attribute
   * @param combinerComputation The value of the combinerComputation attribute
   * @param quantizationConfigLow The value of the quantizationConfigLow attribute
   * @param quantizationConfigHigh The value of the quantizationConfigHigh attribute
   * @param quantizationConfigNumBuckets The value of the quantizationConfigNumBuckets attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput create(Scope scope,
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TInt32> sortedPosIds, Operand<TFloat32> sortedGains, Operand<TFloat32> embeddingTable,
      Operand<TFloat32> weights, Long inputSize, Long maxValency, Long numWeights,
      ConcreteFunction combinerComputation, Float quantizationConfigLow,
      Float quantizationConfigHigh, Long quantizationConfigNumBuckets, String tableName,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput");
    opBuilder.addInput(rowPointers.asOutput());
    opBuilder.addInput(sortedSampleIds.asOutput());
    opBuilder.addInput(sortedTokenIds.asOutput());
    opBuilder.addInput(sortedPosIds.asOutput());
    opBuilder.addInput(sortedGains.asOutput());
    opBuilder.addInput(embeddingTable.asOutput());
    opBuilder.addInput(weights.asOutput());
    opBuilder.setAttr("input_size", inputSize);
    opBuilder.setAttr("max_valency", maxValency);
    opBuilder.setAttr("num_weights", numWeights);
    opBuilder.setAttr("combiner_computation", combinerComputation);
    opBuilder.setAttr("quantization_config_low", quantizationConfigLow);
    opBuilder.setAttr("quantization_config_high", quantizationConfigHigh);
    opBuilder.setAttr("quantization_config_num_buckets", quantizationConfigNumBuckets);
    opBuilder.setAttr("table_name", tableName);
    if (options != null) {
      for (Options opts : options) {
        if (opts.numSparsecoresPerDevice != null) {
          opBuilder.setAttr("num_sparsecores_per_device", opts.numSparsecoresPerDevice);
        }
      }
    }
    return new XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput(opBuilder.build());
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
   * Gets activations.
   *
   * @return activations.
   */
  public Output<TFloat32> activations() {
    return activations;
  }

  /**
   * Gets preservedValencies.
   *
   * @return preservedValencies.
   */
  public Output<TInt32> preservedValencies() {
    return preservedValencies;
  }

  /**
   * Gets preservedVectors.
   *
   * @return preservedVectors.
   */
  public Output<TFloat32> preservedVectors() {
    return preservedVectors;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.xla.XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput}
   */
  public static class Options {
    private Long numSparsecoresPerDevice;

    private Options() {
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
      outputsClass = XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput.class
  )
  public static class Inputs extends RawOpInputs<XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput> {
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
     * The embeddingTable input
     */
    public final Operand<TFloat32> embeddingTable;

    /**
     * The weights input
     */
    public final Operand<TFloat32> weights;

    /**
     * The inputSize attribute
     */
    public final long inputSize;

    /**
     * The maxValency attribute
     */
    public final long maxValency;

    /**
     * The numWeights attribute
     */
    public final long numWeights;

    /**
     * The quantizationConfigLow attribute
     */
    public final float quantizationConfigLow;

    /**
     * The quantizationConfigHigh attribute
     */
    public final float quantizationConfigHigh;

    /**
     * The quantizationConfigNumBuckets attribute
     */
    public final long quantizationConfigNumBuckets;

    /**
     * The tableName attribute
     */
    public final String tableName;

    /**
     * The numSparsecoresPerDevice attribute
     */
    public final long numSparsecoresPerDevice;

    public Inputs(GraphOperation op) {
      super(new XlaSparseDenseMatmulCustomCombinerOnTcWithCsrInput(op), op, Arrays.asList("input_size", "max_valency", "num_weights", "quantization_config_low", "quantization_config_high", "quantization_config_num_buckets", "table_name", "num_sparsecores_per_device"));
      int inputIndex = 0;
      rowPointers = (Operand<TInt32>) op.input(inputIndex++);
      sortedSampleIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedTokenIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedPosIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedGains = (Operand<TFloat32>) op.input(inputIndex++);
      embeddingTable = (Operand<TFloat32>) op.input(inputIndex++);
      weights = (Operand<TFloat32>) op.input(inputIndex++);
      inputSize = op.attributes().getAttrInt("input_size");
      maxValency = op.attributes().getAttrInt("max_valency");
      numWeights = op.attributes().getAttrInt("num_weights");
      quantizationConfigLow = op.attributes().getAttrFloat("quantization_config_low");
      quantizationConfigHigh = op.attributes().getAttrFloat("quantization_config_high");
      quantizationConfigNumBuckets = op.attributes().getAttrInt("quantization_config_num_buckets");
      tableName = op.attributes().getAttrString("table_name");
      numSparsecoresPerDevice = op.attributes().getAttrInt("num_sparsecores_per_device");
    }
  }
}
