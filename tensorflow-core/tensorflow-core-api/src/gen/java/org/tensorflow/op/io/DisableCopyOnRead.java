/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.family.TType;

/**
 * Turns off the copy-on-read mode.
 * Turns off the copy-on-read mode of a resource variable. If the variable is not in copy-on-read mode, this op has no effect.
 */
@OpMetadata(
    opType = DisableCopyOnRead.OP_NAME,
    inputsClass = DisableCopyOnRead.Inputs.class
)
public final class DisableCopyOnRead extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DisableCopyOnRead";

  public DisableCopyOnRead(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new DisableCopyOnRead operation.
   *
   * @param scope current scope
   * @param resource The resource handle of the resource variable.
   * @return a new instance of DisableCopyOnRead
   */
  @Endpoint(
      describeByClass = true
  )
  public static DisableCopyOnRead create(Scope scope, Operand<? extends TType> resource) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DisableCopyOnRead");
    opBuilder.addInput(resource.asOutput());
    return new DisableCopyOnRead(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = DisableCopyOnRead.class
  )
  public static class Inputs extends RawOpInputs<DisableCopyOnRead> {
    /**
     * The resource handle of the resource variable.
     */
    public final Operand<? extends TType> resource;

    public Inputs(GraphOperation op) {
      super(new DisableCopyOnRead(op), op, Arrays.asList());
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
