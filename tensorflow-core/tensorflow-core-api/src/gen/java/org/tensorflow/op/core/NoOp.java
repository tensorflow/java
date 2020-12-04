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
 * Does nothing. Only useful as a placeholder for control edges.
 */
@Operator
public final class NoOp extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new NoOp operation.
   * 
   * @param scope current scope
   * @return a new instance of NoOp
   */
  @Endpoint(describeByClass = true)
  public static NoOp create(Scope scope) {
    OperationBuilder opBuilder = scope.env().opBuilder("NoOp", scope.makeOpName("NoOp"));
    opBuilder = scope.apply(opBuilder);
    return new NoOp(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "NoOp";
  
  private NoOp(Operation operation) {
    super(operation);
  }
}
