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

package org.tensorflow.op.tpu;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;

/**
 * Enqueue multiple Tensor values on the computation outfeed.
 */
public final class OutfeedEnqueueTuple extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "OutfeedEnqueueTuple";

  private OutfeedEnqueueTuple(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new OutfeedEnqueueTuple operation.
   *
   * @param scope current scope
   * @param inputs A list of tensors that will be inserted into the outfeed queue as an
   * XLA tuple.
   * @return a new instance of OutfeedEnqueueTuple
   */
  @Endpoint(
      describeByClass = true
  )
  public static OutfeedEnqueueTuple create(Scope scope, Iterable<Operand<?>> inputs) {
    OperationBuilder opBuilder = scope.env().opBuilder("OutfeedEnqueueTuple", scope.makeOpName("OutfeedEnqueueTuple"));
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder = scope.apply(opBuilder);
    return new OutfeedEnqueueTuple(opBuilder.build());
  }
}
