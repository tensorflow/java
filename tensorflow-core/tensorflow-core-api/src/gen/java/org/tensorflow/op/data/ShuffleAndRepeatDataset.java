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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 */
public final class ShuffleAndRepeatDataset extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.data.ShuffleAndRepeatDataset}
   */
  public static class Options {
    
    /**
     * @param reshuffleEachIteration 
     */
    public Options reshuffleEachIteration(Boolean reshuffleEachIteration) {
      this.reshuffleEachIteration = reshuffleEachIteration;
      return this;
    }
    
    private Boolean reshuffleEachIteration;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ShuffleAndRepeatDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset 
   * @param bufferSize 
   * @param seed 
   * @param seed2 
   * @param count 
   * @param seedGenerator 
   * @param outputTypes 
   * @param outputShapes 
   * @param options carries optional attributes values
   * @return a new instance of ShuffleAndRepeatDataset
   */
  @Endpoint(describeByClass = true)
  public static ShuffleAndRepeatDataset create(Scope scope, Operand<?> inputDataset, Operand<TInt64> bufferSize, Operand<TInt64> seed, Operand<TInt64> seed2, Operand<TInt64> count, Operand<?> seedGenerator, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ShuffleAndRepeatDatasetV2", scope.makeOpName("ShuffleAndRepeatDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(bufferSize.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(seed2.asOutput());
    opBuilder.addInput(count.asOutput());
    opBuilder.addInput(seedGenerator.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.reshuffleEachIteration != null) {
          opBuilder.setAttr("reshuffle_each_iteration", opts.reshuffleEachIteration);
        }
      }
    }
    return new ShuffleAndRepeatDataset(opBuilder.build());
  }
  
  /**
   * @param reshuffleEachIteration 
   */
  public static Options reshuffleEachIteration(Boolean reshuffleEachIteration) {
    return new Options().reshuffleEachIteration(reshuffleEachIteration);
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
  public static final String OP_NAME = "ShuffleAndRepeatDatasetV2";
  
  private Output<?> handle;
  
  private ShuffleAndRepeatDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
