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

package org.tensorflow.op.xla;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Sends the named tensor to another XLA computation. Wraps the XLA Send operator
 * <p>
 * documented at
 *  https://www.tensorflow.org/performance/xla/operation_semantics#send .
 */
@Operator(group = "xla")
public final class Send extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new Send operation.
   * 
   * @param scope current scope
   * @param tensor The tensor to send.
   * @param tensorName A string key that identifies the channel.
   * @return a new instance of Send
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Send create(Scope scope, Operand<T> tensor, String tensorName) {
    OperationBuilder opBuilder = scope.env().opBuilder("XlaSend", scope.makeOpName("Send"));
    opBuilder.addInput(tensor.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("tensor_name", tensorName);
    return new Send(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "XlaSend";
  
  private Send(Operation operation) {
    super(operation);
  }
}
