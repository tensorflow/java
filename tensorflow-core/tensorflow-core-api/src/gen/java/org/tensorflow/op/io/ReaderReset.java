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

package org.tensorflow.op.io;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

/**
 * Restore a Reader to its initial clean state.
 */
@Operator(group = "io")
public final class ReaderReset extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new ReaderReset operation.
   * 
   * @param scope current scope
   * @param readerHandle Handle to a Reader.
   * @return a new instance of ReaderReset
   */
  @Endpoint(describeByClass = true)
  public static ReaderReset create(Scope scope, Operand<?> readerHandle) {
    OperationBuilder opBuilder = scope.env().opBuilder("ReaderResetV2", scope.makeOpName("ReaderReset"));
    opBuilder.addInput(readerHandle.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new ReaderReset(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ReaderResetV2";
  
  private ReaderReset(Operation operation) {
    super(operation);
  }
}
