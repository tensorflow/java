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
import org.tensorflow.types.family.TType;

/**
 * Identity transformation that models performance.
 * <p>
 * Identity transformation that models performance.
 */
public final class ModelDataset extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.data.ModelDataset}
   */
  public static class Options {
    
    /**
     * @param algorithm 
     */
    public Options algorithm(Long algorithm) {
      this.algorithm = algorithm;
      return this;
    }
    
    /**
     * @param cpuBudget 
     */
    public Options cpuBudget(Long cpuBudget) {
      this.cpuBudget = cpuBudget;
      return this;
    }
    
    private Long algorithm;
    private Long cpuBudget;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ModelDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param outputTypes 
   * @param outputShapes 
   * @param options carries optional attributes values
   * @return a new instance of ModelDataset
   */
  @Endpoint(describeByClass = true)
  public static ModelDataset create(Scope scope, Operand<?> inputDataset, List<DataType<?>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ModelDataset", scope.makeOpName("ModelDataset"));
    opBuilder.addInput(inputDataset.asOutput());
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
        if (opts.algorithm != null) {
          opBuilder.setAttr("algorithm", opts.algorithm);
        }
        if (opts.cpuBudget != null) {
          opBuilder.setAttr("cpu_budget", opts.cpuBudget);
        }
      }
    }
    return new ModelDataset(opBuilder.build());
  }
  
  /**
   * @param algorithm 
   */
  public static Options algorithm(Long algorithm) {
    return new Options().algorithm(algorithm);
  }
  
  /**
   * @param cpuBudget 
   */
  public static Options cpuBudget(Long cpuBudget) {
    return new Options().cpuBudget(cpuBudget);
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
  public static final String OP_NAME = "ModelDataset";
  
  private Output<?> handle;
  
  private ModelDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
