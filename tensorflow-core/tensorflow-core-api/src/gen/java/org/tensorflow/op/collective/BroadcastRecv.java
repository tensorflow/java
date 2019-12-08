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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.util.Shape;

/**
 * Receives a tensor value broadcast from another device.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class BroadcastRecv<T extends TNumber> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new BroadcastRecv operation.
   * 
   * @param scope current scope
   * @param T 
   * @param groupSize 
   * @param groupKey 
   * @param instanceKey 
   * @param shape 
   * @return a new instance of BroadcastRecv
   */
  public static <T extends TNumber> BroadcastRecv<T> create(Scope scope, DataType<T> T, Long groupSize, Long groupKey, Long instanceKey, Shape shape) {
    OperationBuilder opBuilder = scope.env().opBuilder("CollectiveBcastRecv", scope.makeOpName("BroadcastRecv"));
    opBuilder = scope.applyControlDependencies(opBuilder);
    opBuilder.setAttr("T", T);
    opBuilder.setAttr("group_size", groupSize);
    opBuilder.setAttr("group_key", groupKey);
    opBuilder.setAttr("instance_key", instanceKey);
    opBuilder.setAttr("shape", shape);
    return new BroadcastRecv<T>(opBuilder.build());
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
  
  private Output<T> output;
  
  private BroadcastRecv(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
