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
 * Creates a dataset that shards the input dataset.
 * <p>
 * Creates a dataset that shards the input dataset by num_workers, returning a
 * sharded dataset for the index-th worker. This attempts to automatically shard
 * a dataset by examining the Dataset graph and inserting a shard op before the
 * inputs to a reader Dataset (e.g. CSVDataset, TFRecordDataset).
 * <p>
 * This dataset will throw a NotFound error if we cannot shard the dataset
 * automatically.
 */
public final class AutoShardDataset extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.data.AutoShardDataset}
   */
  public static class Options {
    
    /**
     * @param autoShardPolicy 
     */
    public Options autoShardPolicy(Long autoShardPolicy) {
      this.autoShardPolicy = autoShardPolicy;
      return this;
    }
    
    private Long autoShardPolicy;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new AutoShardDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset A variant tensor representing the input dataset.
   * @param numWorkers A scalar representing the number of workers to distribute this dataset across.
   * @param index A scalar representing the index of the current worker out of num_workers.
   * @param outputTypes 
   * @param outputShapes 
   * @param options carries optional attributes values
   * @return a new instance of AutoShardDataset
   */
  @Endpoint(describeByClass = true)
  public static AutoShardDataset create(Scope scope, Operand<?> inputDataset, Operand<TInt64> numWorkers, Operand<TInt64> index, List<DataType<?>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("AutoShardDataset", scope.makeOpName("AutoShardDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(numWorkers.asOutput());
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
        if (opts.autoShardPolicy != null) {
          opBuilder.setAttr("auto_shard_policy", opts.autoShardPolicy);
        }
      }
    }
    return new AutoShardDataset(opBuilder.build());
  }
  
  /**
   * @param autoShardPolicy 
   */
  public static Options autoShardPolicy(Long autoShardPolicy) {
    return new Options().autoShardPolicy(autoShardPolicy);
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
  public static final String OP_NAME = "AutoShardDataset";
  
  private Output<?> handle;
  
  private AutoShardDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
