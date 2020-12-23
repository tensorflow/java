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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that batches `batch_size` elements from `input_dataset`.
 */
@Operator(group = "data")
public final class BatchDataset extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.data.BatchDataset}
   */
  public static class Options {
    
    /**
     * @param parallelCopy 
     */
    public Options parallelCopy(Boolean parallelCopy) {
      this.parallelCopy = parallelCopy;
      return this;
    }
    
    private Boolean parallelCopy;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new BatchDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset 
   * @param batchSize A scalar representing the number of elements to accumulate in a batch.
   * @param dropRemainder A scalar representing whether the last batch should be dropped in case its size
   * is smaller than desired.
   * @param outputTypes 
   * @param outputShapes 
   * @param options carries optional attributes values
   * @return a new instance of BatchDataset
   */
  @Endpoint(describeByClass = true)
  public static BatchDataset create(Scope scope, Operand<?> inputDataset, Operand<TInt64> batchSize, Operand<TBool> dropRemainder, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("BatchDatasetV2", scope.makeOpName("BatchDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(batchSize.asOutput());
    opBuilder.addInput(dropRemainder.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.parallelCopy != null) {
          opBuilder.setAttr("parallel_copy", opts.parallelCopy);
        }
      }
    }
    return new BatchDataset(opBuilder.build());
  }
  
  /**
   * @param parallelCopy 
   */
  public static Options parallelCopy(Boolean parallelCopy) {
    return new Options().parallelCopy(parallelCopy);
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
  public static final String OP_NAME = "BatchDatasetV2";
  
  private Output<?> handle;
  
  private BatchDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
