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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Returns a serialized GraphDef representing `input_dataset`.
 * <p>
 * Returns a graph representation for `input_dataset`.
 */
public final class DatasetToGraph extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.data.DatasetToGraph}
   */
  public static class Options {
    
    /**
     * @param externalStatePolicy 
     */
    public Options externalStatePolicy(Long externalStatePolicy) {
      this.externalStatePolicy = externalStatePolicy;
      return this;
    }
    
    /**
     * @param stripDeviceAssignment 
     */
    public Options stripDeviceAssignment(Boolean stripDeviceAssignment) {
      this.stripDeviceAssignment = stripDeviceAssignment;
      return this;
    }
    
    private Long externalStatePolicy;
    private Boolean stripDeviceAssignment;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new DatasetToGraph operation.
   * 
   * @param scope current scope
   * @param inputDataset A variant tensor representing the dataset to return the graph representation for.
   * @param options carries optional attributes values
   * @return a new instance of DatasetToGraph
   */
  @Endpoint(describeByClass = true)
  public static DatasetToGraph create(Scope scope, Operand<?> inputDataset, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DatasetToGraphV2", scope.makeOpName("DatasetToGraph"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.externalStatePolicy != null) {
          opBuilder.setAttr("external_state_policy", opts.externalStatePolicy);
        }
        if (opts.stripDeviceAssignment != null) {
          opBuilder.setAttr("strip_device_assignment", opts.stripDeviceAssignment);
        }
      }
    }
    return new DatasetToGraph(opBuilder.build());
  }
  
  /**
   * @param externalStatePolicy 
   */
  public static Options externalStatePolicy(Long externalStatePolicy) {
    return new Options().externalStatePolicy(externalStatePolicy);
  }
  
  /**
   * @param stripDeviceAssignment 
   */
  public static Options stripDeviceAssignment(Boolean stripDeviceAssignment) {
    return new Options().stripDeviceAssignment(stripDeviceAssignment);
  }
  
  /**
   * The graph representation of the dataset (as serialized GraphDef).
   */
  public Output<TString> graph() {
    return graph;
  }
  
  @Override
  public Output<TString> asOutput() {
    return graph;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DatasetToGraphV2";
  
  private Output<TString> graph;
  
  private DatasetToGraph(Operation operation) {
    super(operation);
    int outputIdx = 0;
    graph = operation.output(outputIdx++);
  }
}
