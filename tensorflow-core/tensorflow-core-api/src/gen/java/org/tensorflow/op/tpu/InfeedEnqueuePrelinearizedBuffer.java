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

package org.tensorflow.op.tpu;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

/**
 * An op which enqueues prelinearized buffer into TPU infeed.
 */
public final class InfeedEnqueuePrelinearizedBuffer extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.InfeedEnqueuePrelinearizedBuffer}
   */
  public static class Options {
    
    /**
     * @param deviceOrdinal The TPU device to use. This should be -1 when the Op is running on a TPU device
     * and = 0 when the Op is running on the CPU device.
     */
    public Options deviceOrdinal(Long deviceOrdinal) {
      this.deviceOrdinal = deviceOrdinal;
      return this;
    }
    
    private Long deviceOrdinal;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new InfeedEnqueuePrelinearizedBuffer operation.
   * 
   * @param scope current scope
   * @param input A variant tensor representing linearized output.
   * @param options carries optional attributes values
   * @return a new instance of InfeedEnqueuePrelinearizedBuffer
   */
  @Endpoint(describeByClass = true)
  public static InfeedEnqueuePrelinearizedBuffer create(Scope scope, Operand<?> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("InfeedEnqueuePrelinearizedBuffer", scope.makeOpName("InfeedEnqueuePrelinearizedBuffer"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.deviceOrdinal != null) {
          opBuilder.setAttr("device_ordinal", opts.deviceOrdinal);
        }
      }
    }
    return new InfeedEnqueuePrelinearizedBuffer(opBuilder.build());
  }
  
  /**
   * @param deviceOrdinal The TPU device to use. This should be -1 when the Op is running on a TPU device
   * and = 0 when the Op is running on the CPU device.
   */
  public static Options deviceOrdinal(Long deviceOrdinal) {
    return new Options().deviceOrdinal(deviceOrdinal);
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "InfeedEnqueuePrelinearizedBuffer";
  
  private InfeedEnqueuePrelinearizedBuffer(Operation operation) {
    super(operation);
  }
}
