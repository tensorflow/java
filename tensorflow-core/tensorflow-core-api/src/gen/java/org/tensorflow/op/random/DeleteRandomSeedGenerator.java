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

package org.tensorflow.op.random;

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
 * The DeleteRandomSeedGenerator operation
 */
@OpMetadata(
    opType = DeleteRandomSeedGenerator.OP_NAME,
    inputsClass = DeleteRandomSeedGenerator.Inputs.class
)
public final class DeleteRandomSeedGenerator extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DeleteRandomSeedGenerator";

  public DeleteRandomSeedGenerator(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new DeleteRandomSeedGenerator operation.
   *
   * @param scope current scope
   * @param handle The handle value
   * @param deleter The deleter value
   * @return a new instance of DeleteRandomSeedGenerator
   */
  @Endpoint(
      describeByClass = true
  )
  public static DeleteRandomSeedGenerator create(Scope scope, Operand<? extends TType> handle,
      Operand<? extends TType> deleter) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DeleteRandomSeedGenerator");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(deleter.asOutput());
    return new DeleteRandomSeedGenerator(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = DeleteRandomSeedGenerator.class
  )
  public static class Inputs extends RawOpInputs<DeleteRandomSeedGenerator> {
    /**
     * The handle input
     */
    public final Operand<? extends TType> handle;

    /**
     * The deleter input
     */
    public final Operand<? extends TType> deleter;

    public Inputs(GraphOperation op) {
      super(new DeleteRandomSeedGenerator(op), op, Arrays.asList());
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
      deleter = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
