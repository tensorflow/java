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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;

/**
 * Replica ID.
 */
@Operator(group = "xla")
public final class ReplicaId extends RawOp implements Operand<TInt32> {
  
  /**
   * Factory method to create a class wrapping a new ReplicaId operation.
   * 
   * @param scope current scope
   * @return a new instance of ReplicaId
   */
  @Endpoint(describeByClass = true)
  public static ReplicaId create(Scope scope) {
    OperationBuilder opBuilder = scope.env().opBuilder("XlaReplicaId", scope.makeOpName("ReplicaId"));
    opBuilder = scope.apply(opBuilder);
    return new ReplicaId(opBuilder.build());
  }
  
  /**
   */
  public Output<TInt32> id() {
    return id;
  }
  
  @Override
  public Output<TInt32> asOutput() {
    return id;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "XlaReplicaId";
  
  private Output<TInt32> id;
  
  private ReplicaId(Operation operation) {
    super(operation);
    int outputIdx = 0;
    id = operation.output(outputIdx++);
  }
}
