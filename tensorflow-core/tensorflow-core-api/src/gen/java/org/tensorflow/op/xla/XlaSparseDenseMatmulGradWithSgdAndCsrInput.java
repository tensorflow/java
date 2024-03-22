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
 * The XlaSparseDenseMatmulGradWithSgdAndCsrInput operation
 */
@OpMetadata(
    opType = XlaSparseDenseMatmulGradWithSgdAndCsrInput.OP_NAME,
    inputsClass = XlaSparseDenseMatmulGradWithSgdAndCsrInput.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaSparseDenseMatmulGradWithSgdAndCsrInput extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseDenseMatmulGradWithSgdAndCsrInput";

  private Output<TFloat32> updatedEmbeddingTable;

  public XlaSparseDenseMatmulGradWithSgdAndCsrInput(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    updatedEmbeddingTable = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseDenseMatmulGradWithSgdAndCsrInput operation.
   *
   * @param scope current scope
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedGains The sortedGains value
   * @param activationGradients The activationGradients value
   * @param learningRate The learningRate value
   * @param embeddingTable The embeddingTable value
   * @param numMinibatchesPerPhysicalSparseCore The numMinibatchesPerPhysicalSparseCore value
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulGradWithSgdAndCsrInput
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSparseDenseMatmulGradWithSgdAndCsrInput create(Scope scope,
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TFloat32> sortedGains, Operand<TFloat32> activationGradients,
      Operand<TFloat32> learningRate, Operand<TFloat32> embeddingTable,
      Operand<TInt32> numMinibatchesPerPhysicalSparseCore, String tableName, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseDenseMatmulGradWithSgdAndCsrInput");
    opBuilder.addInput(rowPointers.asOutput());
    opBuilder.addInput(sortedSampleIds.asOutput());
    opBuilder.addInput(sortedTokenIds.asOutput());
    opBuilder.addInput(sortedGains.asOutput());
    opBuilder.addInput(activationGradients.asOutput());
    opBuilder.addInput(learningRate.asOutput());
    opBuilder.addInput(embeddingTable.asOutput());
    opBuilder.addInput(numMinibatchesPerPhysicalSparseCore.asOutput());
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
    return new XlaSparseDenseMatmulGradWithSgdAndCsrInput(opBuilder.build());
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

  @Override
  public Output<TFloat32> asOutput() {
    return updatedEmbeddingTable;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.xla.XlaSparseDenseMatmulGradWithSgdAndCsrInput}
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
      outputsClass = XlaSparseDenseMatmulGradWithSgdAndCsrInput.class
  )
  public static class Inputs extends RawOpInputs<XlaSparseDenseMatmulGradWithSgdAndCsrInput> {
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
     * The numMinibatchesPerPhysicalSparseCore input
     */
    public final Operand<TInt32> numMinibatchesPerPhysicalSparseCore;

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

    public Inputs(GraphOperation op) {
      super(new XlaSparseDenseMatmulGradWithSgdAndCsrInput(op), op, Arrays.asList("clip_weight_min", "clip_weight_max", "table_name"));
      int inputIndex = 0;
      rowPointers = (Operand<TInt32>) op.input(inputIndex++);
      sortedSampleIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedTokenIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedGains = (Operand<TFloat32>) op.input(inputIndex++);
      activationGradients = (Operand<TFloat32>) op.input(inputIndex++);
      learningRate = (Operand<TFloat32>) op.input(inputIndex++);
      embeddingTable = (Operand<TFloat32>) op.input(inputIndex++);
      numMinibatchesPerPhysicalSparseCore = (Operand<TInt32>) op.input(inputIndex++);
      clipWeightMin = op.attributes().getAttrFloat("clip_weight_min");
      clipWeightMax = op.attributes().getAttrFloat("clip_weight_max");
      tableName = op.attributes().getAttrString("table_name");
    }
  }
}
