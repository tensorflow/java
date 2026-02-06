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
 * The XlaSparseDenseMatmulWithStaticBufferSize operation
 */
@OpMetadata(
    opType = XlaSparseDenseMatmulWithStaticBufferSize.OP_NAME,
    inputsClass = XlaSparseDenseMatmulWithStaticBufferSize.Inputs.class
)
public final class XlaSparseDenseMatmulWithStaticBufferSize extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseDenseMatmulWithStaticBufferSize";

  private Output<TFloat32> activations;

  public XlaSparseDenseMatmulWithStaticBufferSize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    activations = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseDenseMatmulWithStaticBufferSize operation.
   *
   * @param scope current scope
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedGains The sortedGains value
   * @param embeddingTable The embeddingTable value
   * @param numMinibatchesPerPhysicalSparseCore The numMinibatchesPerPhysicalSparseCore value
   * @param inputSize The value of the inputSize attribute
   * @param quantizationConfigLow The value of the quantizationConfigLow attribute
   * @param quantizationConfigHigh The value of the quantizationConfigHigh attribute
   * @param quantizationConfigNumBuckets The value of the quantizationConfigNumBuckets attribute
   * @param maxIdsPerSparseCore The value of the maxIdsPerSparseCore attribute
   * @param maxUniqueIdsPerSparseCore The value of the maxUniqueIdsPerSparseCore attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulWithStaticBufferSize
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSparseDenseMatmulWithStaticBufferSize create(Scope scope,
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TFloat32> sortedGains, Operand<TFloat32> embeddingTable,
      Operand<TInt32> numMinibatchesPerPhysicalSparseCore, Long inputSize,
      Float quantizationConfigLow, Float quantizationConfigHigh, Long quantizationConfigNumBuckets,
      Long maxIdsPerSparseCore, Long maxUniqueIdsPerSparseCore, String tableName,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseDenseMatmulWithStaticBufferSize");
    opBuilder.addInput(rowPointers.asOutput());
    opBuilder.addInput(sortedSampleIds.asOutput());
    opBuilder.addInput(sortedTokenIds.asOutput());
    opBuilder.addInput(sortedGains.asOutput());
    opBuilder.addInput(embeddingTable.asOutput());
    opBuilder.addInput(numMinibatchesPerPhysicalSparseCore.asOutput());
    opBuilder.setAttr("input_size", inputSize);
    opBuilder.setAttr("quantization_config_low", quantizationConfigLow);
    opBuilder.setAttr("quantization_config_high", quantizationConfigHigh);
    opBuilder.setAttr("quantization_config_num_buckets", quantizationConfigNumBuckets);
    opBuilder.setAttr("max_ids_per_sparse_core", maxIdsPerSparseCore);
    opBuilder.setAttr("max_unique_ids_per_sparse_core", maxUniqueIdsPerSparseCore);
    opBuilder.setAttr("table_name", tableName);
    if (options != null) {
      for (Options opts : options) {
        if (opts.numSparsecoresPerDevice != null) {
          opBuilder.setAttr("num_sparsecores_per_device", opts.numSparsecoresPerDevice);
        }
      }
    }
    return new XlaSparseDenseMatmulWithStaticBufferSize(opBuilder.build());
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

  @Override
  public Output<TFloat32> asOutput() {
    return activations;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.xla.XlaSparseDenseMatmulWithStaticBufferSize}
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
      outputsClass = XlaSparseDenseMatmulWithStaticBufferSize.class
  )
  public static class Inputs extends RawOpInputs<XlaSparseDenseMatmulWithStaticBufferSize> {
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
     * The embeddingTable input
     */
    public final Operand<TFloat32> embeddingTable;

    /**
     * The numMinibatchesPerPhysicalSparseCore input
     */
    public final Operand<TInt32> numMinibatchesPerPhysicalSparseCore;

    /**
     * The inputSize attribute
     */
    public final long inputSize;

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

    /**
     * The numSparsecoresPerDevice attribute
     */
    public final long numSparsecoresPerDevice;

    public Inputs(GraphOperation op) {
      super(new XlaSparseDenseMatmulWithStaticBufferSize(op), op, Arrays.asList("input_size", "quantization_config_low", "quantization_config_high", "quantization_config_num_buckets", "max_ids_per_sparse_core", "max_unique_ids_per_sparse_core", "table_name", "num_sparsecores_per_device"));
      int inputIndex = 0;
      rowPointers = (Operand<TInt32>) op.input(inputIndex++);
      sortedSampleIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedTokenIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedGains = (Operand<TFloat32>) op.input(inputIndex++);
      embeddingTable = (Operand<TFloat32>) op.input(inputIndex++);
      numMinibatchesPerPhysicalSparseCore = (Operand<TInt32>) op.input(inputIndex++);
      inputSize = op.attributes().getAttrInt("input_size");
      quantizationConfigLow = op.attributes().getAttrFloat("quantization_config_low");
      quantizationConfigHigh = op.attributes().getAttrFloat("quantization_config_high");
      quantizationConfigNumBuckets = op.attributes().getAttrInt("quantization_config_num_buckets");
      maxIdsPerSparseCore = op.attributes().getAttrInt("max_ids_per_sparse_core");
      maxUniqueIdsPerSparseCore = op.attributes().getAttrInt("max_unique_ids_per_sparse_core");
      tableName = op.attributes().getAttrString("table_name");
      numSparsecoresPerDevice = op.attributes().getAttrInt("num_sparsecores_per_device");
    }
  }
}
