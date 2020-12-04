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

package org.tensorflow.op.core;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Receives the named tensor from send_device on recv_device.
 * 
 * @param <T> data type for {@code tensor()} output
 */
public final class Recv<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Recv}
   */
  public static class Options {
    
    /**
     * @param clientTerminated If set to true, this indicates that the node was added
     * to the graph as a result of a client-side feed or fetch of Tensor data,
     * in which case the corresponding send or recv is expected to be managed
     * locally by the caller.
     */
    public Options clientTerminated(Boolean clientTerminated) {
      this.clientTerminated = clientTerminated;
      return this;
    }
    
    private Boolean clientTerminated;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Recv operation.
   * 
   * @param scope current scope
   * @param tensorType 
   * @param tensorName The name of the tensor to receive.
   * @param sendDevice The name of the device sending the tensor.
   * @param sendDeviceIncarnation The current incarnation of send_device.
   * @param recvDevice The name of the device receiving the tensor.
   * @param options carries optional attributes values
   * @return a new instance of Recv
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Recv<T> create(Scope scope, DataType<T> tensorType, String tensorName, String sendDevice, Long sendDeviceIncarnation, String recvDevice, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Recv", scope.makeOpName("Recv"));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("tensor_type", tensorType);
    opBuilder.setAttr("tensor_name", tensorName);
    opBuilder.setAttr("send_device", sendDevice);
    opBuilder.setAttr("send_device_incarnation", sendDeviceIncarnation);
    opBuilder.setAttr("recv_device", recvDevice);
    if (options != null) {
      for (Options opts : options) {
        if (opts.clientTerminated != null) {
          opBuilder.setAttr("client_terminated", opts.clientTerminated);
        }
      }
    }
    return new Recv<T>(opBuilder.build());
  }
  
  /**
   * @param clientTerminated If set to true, this indicates that the node was added
   * to the graph as a result of a client-side feed or fetch of Tensor data,
   * in which case the corresponding send or recv is expected to be managed
   * locally by the caller.
   */
  public static Options clientTerminated(Boolean clientTerminated) {
    return new Options().clientTerminated(clientTerminated);
  }
  
  /**
   * The tensor to receive.
   */
  public Output<T> tensor() {
    return tensor;
  }
  
  @Override
  public Output<T> asOutput() {
    return tensor;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Recv";
  
  private Output<T> tensor;
  
  private Recv(Operation operation) {
    super(operation);
    int outputIdx = 0;
    tensor = operation.output(outputIdx++);
  }
}
