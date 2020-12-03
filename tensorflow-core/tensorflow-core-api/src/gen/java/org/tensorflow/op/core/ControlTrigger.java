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

import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

/**
 * Does nothing. Serves as a control trigger for scheduling.
 * <p>
 * Only useful as a placeholder for control edges.
 */
@Operator
public final class ControlTrigger extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new ControlTrigger operation.
   * 
   * @param scope current scope
   * @return a new instance of ControlTrigger
   */
  @Endpoint(describeByClass = true)
  public static ControlTrigger create(Scope scope) {
    OperationBuilder opBuilder = scope.env().opBuilder("ControlTrigger", scope.makeOpName("ControlTrigger"));
    opBuilder = scope.apply(opBuilder);
    return new ControlTrigger(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ControlTrigger";
  
  private ControlTrigger(Operation operation) {
    super(operation);
  }
}
