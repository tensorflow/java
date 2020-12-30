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

package org.tensorflow.op.collective;

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
import org.tensorflow.types.family.TType;

/**
 * Receives a tensor value broadcast from another device.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class BroadcastRecv<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.collective.BroadcastRecv}
   */
  public static class Options {
    
    /**
     * @param communicationHint 
     */
    public Options communicationHint(String communicationHint) {
      this.communicationHint = communicationHint;
      return this;
    }
    
    /**
     * @param timeoutSeconds 
     */
    public Options timeoutSeconds(Float timeoutSeconds) {
      this.timeoutSeconds = timeoutSeconds;
      return this;
    }
    
    private String communicationHint;
    private Float timeoutSeconds;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new BroadcastRecv operation.
   * 
   * @param scope current scope
   * @param T 
   * @param groupSize 
   * @param groupKey 
   * @param instanceKey 
   * @param shape 
   * @param options carries optional attributes values
   * @return a new instance of BroadcastRecv
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> BroadcastRecv<T> create(Scope scope, Class<T> T, Long groupSize, Long groupKey, Long instanceKey, Shape shape, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("CollectiveBcastRecv", scope.makeOpName("BroadcastRecv"));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("T", Operands.toDataType(T));
    opBuilder.setAttr("group_size", groupSize);
    opBuilder.setAttr("group_key", groupKey);
    opBuilder.setAttr("instance_key", instanceKey);
    opBuilder.setAttr("shape", shape);
    if (options != null) {
      for (Options opts : options) {
        if (opts.communicationHint != null) {
          opBuilder.setAttr("communication_hint", opts.communicationHint);
        }
        if (opts.timeoutSeconds != null) {
          opBuilder.setAttr("timeout_seconds", opts.timeoutSeconds);
        }
      }
    }
    return new BroadcastRecv<T>(opBuilder.build());
  }
  
  /**
   * @param communicationHint 
   */
  public static Options communicationHint(String communicationHint) {
    return new Options().communicationHint(communicationHint);
  }
  
  /**
   * @param timeoutSeconds 
   */
  public static Options timeoutSeconds(Float timeoutSeconds) {
    return new Options().timeoutSeconds(timeoutSeconds);
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "CollectiveBcastRecv";
  
  private Output<T> output;
  
  private BroadcastRecv(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
