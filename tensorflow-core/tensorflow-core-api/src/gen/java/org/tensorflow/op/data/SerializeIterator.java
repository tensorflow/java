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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Converts the given `resource_handle` representing an iterator to a variant tensor.
 */
@Operator(group = "data")
public final class SerializeIterator extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new SerializeIterator operation.
   * 
   * @param scope current scope
   * @param resourceHandle A handle to an iterator resource.
   * @return a new instance of SerializeIterator
   */
  @Endpoint(describeByClass = true)
  public static SerializeIterator create(Scope scope, Operand<?> resourceHandle) {
    OperationBuilder opBuilder = scope.env().opBuilder("SerializeIterator", scope.makeOpName("SerializeIterator"));
    opBuilder.addInput(resourceHandle.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new SerializeIterator(opBuilder.build());
  }
  
  /**
   * A variant tensor storing the state of the iterator contained in the
   * resource.
   */
  public Output<?> serialized() {
    return serialized;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) serialized;
  }
  
  private Output<?> serialized;
  
  private SerializeIterator(Operation operation) {
    super(operation);
    int outputIdx = 0;
    serialized = operation.output(outputIdx++);
  }
}
