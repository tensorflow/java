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

package org.tensorflow.op.summary;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TType;

/**
 * The FlushSummaryWriter operation
 */
public final class FlushSummaryWriter extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FlushSummaryWriter";

  private FlushSummaryWriter(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new FlushSummaryWriter operation.
   *
   * @param scope current scope
   * @param writer the writer value
   * @return a new instance of FlushSummaryWriter
   */
  @Endpoint(
      describeByClass = true
  )
  public static FlushSummaryWriter create(Scope scope, Operand<? extends TType> writer) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FlushSummaryWriter");
    opBuilder.addInput(writer.asOutput());
    return new FlushSummaryWriter(opBuilder.build());
  }
}
