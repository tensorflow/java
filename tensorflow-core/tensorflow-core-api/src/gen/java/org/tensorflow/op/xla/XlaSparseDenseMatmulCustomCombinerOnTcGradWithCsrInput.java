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
import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
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
 * The XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput operation
 */
@OpMetadata(
    opType = XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput.OP_NAME,
    inputsClass = XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput";

  private List<Output<TFloat32>> updatedTables;

  private Output<TFloat32> updatedWeights;

  @SuppressWarnings("unchecked")
  public XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int updatedTablesLength = operation.outputListLength("updated_tables");
    updatedTables = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, updatedTablesLength));
    outputIdx += updatedTablesLength;
    updatedWeights = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput operation.
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
   * @param tables The tables value
   * @param hyperparameters The hyperparameters value
   * @param combinerWeightsLearningRate The combinerWeightsLearningRate value
   * @param maxValency The value of the maxValency attribute
   * @param numWeights The value of the numWeights attribute
   * @param combinerTableVjpComputation The value of the combinerTableVjpComputation attribute
   * @param combinerWeightsVjpComputation The value of the combinerWeightsVjpComputation attribute
   * @param optimizerCustomComputation The value of the optimizerCustomComputation attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput create(Scope scope,
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TInt32> sortedPosIds, Operand<TFloat32> sortedGains, Operand<TFloat32> weights,
      Operand<TInt32> preservedValencies, Operand<TFloat32> preservedVectors,
      Operand<TFloat32> preservedWeights, Operand<TFloat32> activationGradients,
      Iterable<Operand<TFloat32>> tables, Iterable<Operand<TFloat32>> hyperparameters,
      Operand<TFloat32> combinerWeightsLearningRate, Long maxValency, Long numWeights,
      ConcreteFunction combinerTableVjpComputation, ConcreteFunction combinerWeightsVjpComputation,
      ConcreteFunction optimizerCustomComputation, String tableName, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput");
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
    opBuilder.addInputList(Operands.asOutputs(tables));
    opBuilder.addInputList(Operands.asOutputs(hyperparameters));
    opBuilder.addInput(combinerWeightsLearningRate.asOutput());
    opBuilder.setAttr("max_valency", maxValency);
    opBuilder.setAttr("num_weights", numWeights);
    opBuilder.setAttr("combiner_table_vjp_computation", combinerTableVjpComputation);
    opBuilder.setAttr("combiner_weights_vjp_computation", combinerWeightsVjpComputation);
    opBuilder.setAttr("optimizer_custom_computation", optimizerCustomComputation);
    opBuilder.setAttr("table_name", tableName);
    if (options != null) {
      for (Options opts : options) {
        if (opts.numSparsecoresPerDevice != null) {
          opBuilder.setAttr("num_sparsecores_per_device", opts.numSparsecoresPerDevice);
        }
      }
    }
    return new XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput(opBuilder.build());
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
   * Gets updatedTables.
   *
   * @return updatedTables.
   */
  public List<Output<TFloat32>> updatedTables() {
    return updatedTables;
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
   * Optional attributes for {@link org.tensorflow.op.xla.XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput}
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
      outputsClass = XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput.class
  )
  public static class Inputs extends RawOpInputs<XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput> {
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
     * The tables input
     */
    public final Iterable<Operand<TFloat32>> tables;

    /**
     * The hyperparameters input
     */
    public final Iterable<Operand<TFloat32>> hyperparameters;

    /**
     * The combinerWeightsLearningRate input
     */
    public final Operand<TFloat32> combinerWeightsLearningRate;

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
      super(new XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput(op), op, Arrays.asList("max_valency", "num_weights", "table_name", "num_sparsecores_per_device"));
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
      int tablesLength = op.inputListLength("tables");
      tables = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, tablesLength));
      inputIndex += tablesLength;
      int hyperparametersLength = op.inputListLength("hyperparameters");
      hyperparameters = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, hyperparametersLength));
      inputIndex += hyperparametersLength;
      combinerWeightsLearningRate = (Operand<TFloat32>) op.input(inputIndex++);
      maxValency = op.attributes().getAttrInt("max_valency");
      numWeights = op.attributes().getAttrInt("num_weights");
      tableName = op.attributes().getAttrString("table_name");
      numSparsecoresPerDevice = op.attributes().getAttrInt("num_sparsecores_per_device");
    }
  }
}
