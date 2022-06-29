/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.data;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset by applying related optimizations to {@code input_dataset}.
 * Creates a dataset by applying related optimizations to {@code input_dataset}.
 */
@OpMetadata(
    opType = OptimizeDataset.OP_NAME,
    inputsClass = OptimizeDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class OptimizeDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "OptimizeDatasetV2";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public OptimizeDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new OptimizeDatasetV2 operation.
   *
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param optimizationsEnabled A {@code tf.string} vector {@code tf.Tensor} identifying user enabled optimizations.
   * @param optimizationsDisabled A {@code tf.string} vector {@code tf.Tensor} identifying user disabled optimizations.
   * @param optimizationsDefault A {@code tf.string} vector {@code tf.Tensor} identifying optimizations by default.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of OptimizeDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static OptimizeDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TString> optimizationsEnabled, Operand<TString> optimizationsDisabled,
      Operand<TString> optimizationsDefault, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "OptimizeDataset");
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(optimizationsEnabled.asOutput());
    opBuilder.addInput(optimizationsDisabled.asOutput());
    opBuilder.addInput(optimizationsDefault.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.optimizationConfigs != null) {
          String[] optimizationConfigsArray = new String[opts.optimizationConfigs.size()];
          for (int i = 0 ; i < optimizationConfigsArray.length ; i++) {
            optimizationConfigsArray[i] = opts.optimizationConfigs.get(i);
          }
          opBuilder.setAttr("optimization_configs", optimizationConfigsArray);
        }
      }
    }
    return new OptimizeDataset(opBuilder.build());
  }

  /**
   * Sets the optimizationConfigs option.
   *
   * @param optimizationConfigs the optimizationConfigs option
   * @return this Options instance.
   */
  public static Options optimizationConfigs(List<String> optimizationConfigs) {
    return new Options().optimizationConfigs(optimizationConfigs);
  }

  /**
   * Sets the optimizationConfigs option.
   *
   * @param optimizationConfigs the optimizationConfigs option
   * @return this Options instance.
   */
  public static Options optimizationConfigs(String... optimizationConfigs) {
    return new Options().optimizationConfigs(optimizationConfigs);
  }

  /**
   * Gets handle.
   *
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.data.OptimizeDataset}
   */
  public static class Options {
    private List<String> optimizationConfigs;

    private Options() {
    }

    /**
     * Sets the optimizationConfigs option.
     *
     * @param optimizationConfigs the optimizationConfigs option
     * @return this Options instance.
     */
    public Options optimizationConfigs(List<String> optimizationConfigs) {
      this.optimizationConfigs = optimizationConfigs;
      return this;
    }

    /**
     * Sets the optimizationConfigs option.
     *
     * @param optimizationConfigs the optimizationConfigs option
     * @return this Options instance.
     */
    public Options optimizationConfigs(String... optimizationConfigs) {
      this.optimizationConfigs = Arrays.asList(optimizationConfigs);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = OptimizeDataset.class
  )
  public static class Inputs extends RawOpInputs<OptimizeDataset> {
    /**
     * A variant tensor representing the input dataset.
     */
    public final Operand<? extends TType> inputDataset;

    /**
     * A {@code tf.string} vector {@code tf.Tensor} identifying user enabled optimizations.
     */
    public final Operand<TString> optimizationsEnabled;

    /**
     * A {@code tf.string} vector {@code tf.Tensor} identifying user disabled optimizations.
     */
    public final Operand<TString> optimizationsDisabled;

    /**
     * A {@code tf.string} vector {@code tf.Tensor} identifying optimizations by default.
     */
    public final Operand<TString> optimizationsDefault;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The optimizationConfigs attribute
     */
    public final String[] optimizationConfigs;

    public Inputs(GraphOperation op) {
      super(new OptimizeDataset(op), op, Arrays.asList("output_types", "output_shapes", "optimization_configs"));
      int inputIndex = 0;
      inputDataset = (Operand<? extends TType>) op.input(inputIndex++);
      optimizationsEnabled = (Operand<TString>) op.input(inputIndex++);
      optimizationsDisabled = (Operand<TString>) op.input(inputIndex++);
      optimizationsDefault = (Operand<TString>) op.input(inputIndex++);
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      optimizationConfigs = op.attributes().getAttrStringList("optimization_configs");
    }
  }
}
