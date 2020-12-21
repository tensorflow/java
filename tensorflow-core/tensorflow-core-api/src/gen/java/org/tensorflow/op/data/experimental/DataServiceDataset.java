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

package org.tensorflow.op.data.experimental;

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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 */
@Operator(group = "data.experimental")
public final class DataServiceDataset extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.data.experimental.DataServiceDataset}
   */
  public static class Options {
    
    /**
     * @param taskRefreshIntervalHintMs 
     */
    public Options taskRefreshIntervalHintMs(Long taskRefreshIntervalHintMs) {
      this.taskRefreshIntervalHintMs = taskRefreshIntervalHintMs;
      return this;
    }
    
    private Long taskRefreshIntervalHintMs;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new DataServiceDataset operation.
   * 
   * @param scope current scope
   * @param datasetId 
   * @param processingMode 
   * @param address 
   * @param protocol 
   * @param jobName 
   * @param maxOutstandingRequests 
   * @param iterationCounter 
   * @param outputTypes 
   * @param outputShapes 
   * @param options carries optional attributes values
   * @return a new instance of DataServiceDataset
   */
  @Endpoint(describeByClass = true)
  public static DataServiceDataset create(Scope scope, Operand<TInt64> datasetId, Operand<TString> processingMode, Operand<TString> address, Operand<TString> protocol, Operand<TString> jobName, Operand<TInt64> maxOutstandingRequests, Operand<?> iterationCounter, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DataServiceDataset", scope.makeOpName("DataServiceDataset"));
    opBuilder.addInput(datasetId.asOutput());
    opBuilder.addInput(processingMode.asOutput());
    opBuilder.addInput(address.asOutput());
    opBuilder.addInput(protocol.asOutput());
    opBuilder.addInput(jobName.asOutput());
    opBuilder.addInput(maxOutstandingRequests.asOutput());
    opBuilder.addInput(iterationCounter.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.taskRefreshIntervalHintMs != null) {
          opBuilder.setAttr("task_refresh_interval_hint_ms", opts.taskRefreshIntervalHintMs);
        }
      }
    }
    return new DataServiceDataset(opBuilder.build());
  }
  
  /**
   * @param taskRefreshIntervalHintMs 
   */
  public static Options taskRefreshIntervalHintMs(Long taskRefreshIntervalHintMs) {
    return new Options().taskRefreshIntervalHintMs(taskRefreshIntervalHintMs);
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
  public static final String OP_NAME = "DataServiceDataset";
  
  private Output<?> handle;
  
  private DataServiceDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
