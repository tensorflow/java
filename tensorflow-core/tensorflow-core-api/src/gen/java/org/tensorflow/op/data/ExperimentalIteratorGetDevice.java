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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

/**
 * Returns the name of the device on which `resource` has been placed.
 */
public final class ExperimentalIteratorGetDevice extends PrimitiveOp implements Operand<String> {
  
  /**
   * Factory method to create a class wrapping a new ExperimentalIteratorGetDevice operation.
   * 
   * @param scope current scope
   * @param resource 
   * @return a new instance of ExperimentalIteratorGetDevice
   */
  public static ExperimentalIteratorGetDevice create(Scope scope, Operand<?> resource) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExperimentalIteratorGetDevice", scope.makeOpName("ExperimentalIteratorGetDevice"));
    opBuilder.addInput(resource.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new ExperimentalIteratorGetDevice(opBuilder.build());
  }
  
  /**
   */
  public Output<String> device() {
    return device;
  }
  
  @Override
  public Output<String> asOutput() {
    return device;
  }
  
  private Output<String> device;
  
  private ExperimentalIteratorGetDevice(Operation operation) {
    super(operation);
    int outputIdx = 0;
    device = operation.output(outputIdx++);
  }
}
