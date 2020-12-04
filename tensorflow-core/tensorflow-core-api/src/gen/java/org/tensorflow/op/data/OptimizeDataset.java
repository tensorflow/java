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

import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset by applying optimizations to `input_dataset`.
 * <p>
 * Creates a dataset by applying optimizations to `input_dataset`.
 */
public final class OptimizeDataset extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.data.OptimizeDataset}
   */
  public static class Options {
    
    /**
     * @param optimizationConfigs 
     */
    public Options optimizationConfigs(List<String> optimizationConfigs) {
      this.optimizationConfigs = optimizationConfigs;
      return this;
    }
    
    private List<String> optimizationConfigs;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new OptimizeDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param optimizations A `tf.string` vector `tf.Tensor` identifying optimizations to use.
   * @param outputTypes 
   * @param outputShapes 
   * @param options carries optional attributes values
   * @return a new instance of OptimizeDataset
   */
  @Endpoint(describeByClass = true)
  public static OptimizeDataset create(Scope scope, Operand<?> inputDataset, Operand<TString> optimizations, List<DataType<?>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("OptimizeDataset", scope.makeOpName("OptimizeDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(optimizations.asOutput());
    opBuilder = scope.apply(opBuilder);
    DataType[] outputTypesArray = new DataType[outputTypes.size()];
    for (int i = 0; i < outputTypesArray.length; ++i) {
      outputTypesArray[i] = outputTypes.get(i);
    }
    opBuilder.setAttr("output_types", outputTypesArray);
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.optimizationConfigs != null) {
          String[] optimizationConfigsArray = new String[opts.optimizationConfigs.size()];
          for (int i = 0; i < optimizationConfigsArray.length; ++i) {
            optimizationConfigsArray[i] = opts.optimizationConfigs.get(i);
          }
          opBuilder.setAttr("optimization_configs", optimizationConfigsArray);
        }
      }
    }
    return new OptimizeDataset(opBuilder.build());
  }
  
  /**
   * @param optimizationConfigs 
   */
  public static Options optimizationConfigs(List<String> optimizationConfigs) {
    return new Options().optimizationConfigs(optimizationConfigs);
  }
  
  /**
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "OptimizeDataset";
  
  private Output<?> handle;
  
  private OptimizeDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
