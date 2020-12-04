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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates a `Dataset` that includes only 1/`num_shards` of this dataset.
 */
public final class ShardDataset extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.data.ShardDataset}
   */
  public static class Options {
    
    /**
     * @param requireNonEmpty 
     */
    public Options requireNonEmpty(Boolean requireNonEmpty) {
      this.requireNonEmpty = requireNonEmpty;
      return this;
    }
    
    private Boolean requireNonEmpty;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ShardDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset 
   * @param numShards An integer representing the number of shards operating in parallel.
   * @param index An integer representing the current worker index.
   * @param outputTypes 
   * @param outputShapes 
   * @param options carries optional attributes values
   * @return a new instance of ShardDataset
   */
  @Endpoint(describeByClass = true)
  public static ShardDataset create(Scope scope, Operand<?> inputDataset, Operand<TInt64> numShards, Operand<TInt64> index, List<DataType<?>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ShardDataset", scope.makeOpName("ShardDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(numShards.asOutput());
    opBuilder.addInput(index.asOutput());
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
        if (opts.requireNonEmpty != null) {
          opBuilder.setAttr("require_non_empty", opts.requireNonEmpty);
        }
      }
    }
    return new ShardDataset(opBuilder.build());
  }
  
  /**
   * @param requireNonEmpty 
   */
  public static Options requireNonEmpty(Boolean requireNonEmpty) {
    return new Options().requireNonEmpty(requireNonEmpty);
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
  public static final String OP_NAME = "ShardDataset";
  
  private Output<?> handle;
  
  private ShardDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
