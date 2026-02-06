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
import java.util.Iterator;
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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * The XlaSparseDenseMatmulGradWithCsrInput operation
 */
@OpMetadata(
    opType = XlaSparseDenseMatmulGradWithCsrInput.OP_NAME,
    inputsClass = XlaSparseDenseMatmulGradWithCsrInput.Inputs.class
)
public final class XlaSparseDenseMatmulGradWithCsrInput<T extends TNumber> extends RawOp implements Iterable<Operand<T>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseDenseMatmulGradWithCsrInput";

  private List<Output<T>> updatedTables;

  @SuppressWarnings("unchecked")
  public XlaSparseDenseMatmulGradWithCsrInput(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int updatedTablesLength = operation.outputListLength("updated_tables");
    updatedTables = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, updatedTablesLength));
    outputIdx += updatedTablesLength;
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseDenseMatmulGradWithCsrInput operation.
   *
   * @param scope current scope
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedGains The sortedGains value
   * @param activationGradients The activationGradients value
   * @param tables The tables value
   * @param hyperparameters The hyperparameters value
   * @param numMinibatchesPerPhysicalSparseCore The numMinibatchesPerPhysicalSparseCore value
   * @param customComputation The value of the customComputation attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code XlaSparseDenseMatmulGradWithCsrInput} output and operands
   * @return a new instance of XlaSparseDenseMatmulGradWithCsrInput
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> XlaSparseDenseMatmulGradWithCsrInput<T> create(Scope scope,
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TFloat32> sortedGains, Operand<TFloat32> activationGradients,
      Iterable<Operand<T>> tables, Iterable<Operand<TFloat32>> hyperparameters,
      Operand<TInt32> numMinibatchesPerPhysicalSparseCore, ConcreteFunction customComputation,
      String tableName, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseDenseMatmulGradWithCsrInput");
    opBuilder.addInput(rowPointers.asOutput());
    opBuilder.addInput(sortedSampleIds.asOutput());
    opBuilder.addInput(sortedTokenIds.asOutput());
    opBuilder.addInput(sortedGains.asOutput());
    opBuilder.addInput(activationGradients.asOutput());
    opBuilder.addInputList(Operands.asOutputs(tables));
    opBuilder.addInputList(Operands.asOutputs(hyperparameters));
    opBuilder.addInput(numMinibatchesPerPhysicalSparseCore.asOutput());
    opBuilder.setAttr("custom_computation", customComputation);
    opBuilder.setAttr("table_name", tableName);
    if (options != null) {
      for (Options opts : options) {
        if (opts.numSparsecoresPerDevice != null) {
          opBuilder.setAttr("num_sparsecores_per_device", opts.numSparsecoresPerDevice);
        }
      }
    }
    return new XlaSparseDenseMatmulGradWithCsrInput<>(opBuilder.build());
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
  public List<Output<T>> updatedTables() {
    return updatedTables;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<T>> iterator() {
    return (Iterator) updatedTables.iterator();
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.xla.XlaSparseDenseMatmulGradWithCsrInput}
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
      outputsClass = XlaSparseDenseMatmulGradWithCsrInput.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<XlaSparseDenseMatmulGradWithCsrInput<T>> {
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
     * The tables input
     */
    public final Iterable<Operand<T>> tables;

    /**
     * The hyperparameters input
     */
    public final Iterable<Operand<TFloat32>> hyperparameters;

    /**
     * The numMinibatchesPerPhysicalSparseCore input
     */
    public final Operand<TInt32> numMinibatchesPerPhysicalSparseCore;

    /**
     * The tableName attribute
     */
    public final String tableName;

    /**
     * The numSparsecoresPerDevice attribute
     */
    public final long numSparsecoresPerDevice;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new XlaSparseDenseMatmulGradWithCsrInput<>(op), op, Arrays.asList("table_name", "num_sparsecores_per_device", "T"));
      int inputIndex = 0;
      rowPointers = (Operand<TInt32>) op.input(inputIndex++);
      sortedSampleIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedTokenIds = (Operand<TInt32>) op.input(inputIndex++);
      sortedGains = (Operand<TFloat32>) op.input(inputIndex++);
      activationGradients = (Operand<TFloat32>) op.input(inputIndex++);
      int tablesLength = op.inputListLength("tables");
      tables = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, tablesLength));
      inputIndex += tablesLength;
      int hyperparametersLength = op.inputListLength("hyperparameters");
      hyperparameters = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, hyperparametersLength));
      inputIndex += hyperparametersLength;
      numMinibatchesPerPhysicalSparseCore = (Operand<TInt32>) op.input(inputIndex++);
      tableName = op.attributes().getAttrString("table_name");
      numSparsecoresPerDevice = op.attributes().getAttrInt("num_sparsecores_per_device");
      T = op.attributes().getAttrType("T");
    }
  }
}
