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
import org.tensorflow.types.TString;

/**
 * Returns the name of the device on which `resource` has been placed.
 */
public final class IteratorGetDevice extends PrimitiveOp implements Operand<TString> {
  
  /**
   * Factory method to create a class wrapping a new IteratorGetDevice operation.
   * 
   * @param scope current scope
   * @param resource 
   * @return a new instance of IteratorGetDevice
   */
  public static IteratorGetDevice create(Scope scope, Operand<?> resource) {
    OperationBuilder opBuilder = scope.env().opBuilder("IteratorGetDevice", scope.makeOpName("IteratorGetDevice"));
    opBuilder.addInput(resource.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new IteratorGetDevice(opBuilder.build());
  }
  
  /**
   */
  public Output<TString> device() {
    return device;
  }
  
  @Override
  public Output<TString> asOutput() {
    return device;
  }
  
  private Output<TString> device;
  
  private IteratorGetDevice(Operation operation) {
    super(operation);
    int outputIdx = 0;
    device = operation.output(outputIdx++);
  }
}
