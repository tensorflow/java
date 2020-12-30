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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;

/**
 * Mutually reduces multiple tensors of identical type and shape.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class AllReduce<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.collective.AllReduce}
   */
  public static class Options {
    
    /**
     * @param waitFor 
     */
    public Options waitFor(List<Long> waitFor) {
      this.waitFor = waitFor;
      return this;
    }
    
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
    
    private List<Long> waitFor;
    private String communicationHint;
    private Float timeoutSeconds;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new AllReduce operation.
   * 
   * @param scope current scope
   * @param input 
   * @param groupSize 
   * @param groupKey 
   * @param instanceKey 
   * @param mergeOp 
   * @param finalOp 
   * @param subdivOffsets 
   * @param options carries optional attributes values
   * @return a new instance of AllReduce
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> AllReduce<T> create(Scope scope, Operand<T> input, Long groupSize, Long groupKey, Long instanceKey, String mergeOp, String finalOp, List<Long> subdivOffsets, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("CollectiveReduce", scope.makeOpName("AllReduce"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("group_size", groupSize);
    opBuilder.setAttr("group_key", groupKey);
    opBuilder.setAttr("instance_key", instanceKey);
    opBuilder.setAttr("merge_op", mergeOp);
    opBuilder.setAttr("final_op", finalOp);
    long[] subdivOffsetsArray = new long[subdivOffsets.size()];
    for (int i = 0; i < subdivOffsetsArray.length; ++i) {
      subdivOffsetsArray[i] = subdivOffsets.get(i);
    }
    opBuilder.setAttr("subdiv_offsets", subdivOffsetsArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.waitFor != null) {
          long[] waitForArray = new long[opts.waitFor.size()];
          for (int i = 0; i < waitForArray.length; ++i) {
            waitForArray[i] = opts.waitFor.get(i);
          }
          opBuilder.setAttr("wait_for", waitForArray);
        }
        if (opts.communicationHint != null) {
          opBuilder.setAttr("communication_hint", opts.communicationHint);
        }
        if (opts.timeoutSeconds != null) {
          opBuilder.setAttr("timeout_seconds", opts.timeoutSeconds);
        }
      }
    }
    return new AllReduce<T>(opBuilder.build());
  }
  
  /**
   * @param waitFor 
   */
  public static Options waitFor(List<Long> waitFor) {
    return new Options().waitFor(waitFor);
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
  public static final String OP_NAME = "CollectiveReduce";
  
  private Output<T> output;
  
  private AllReduce(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
