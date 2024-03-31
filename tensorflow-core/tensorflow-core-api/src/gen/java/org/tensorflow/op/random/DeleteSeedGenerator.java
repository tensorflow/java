/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * The DeleteSeedGenerator operation
 */
@OpMetadata(
    opType = DeleteSeedGenerator.OP_NAME,
    inputsClass = DeleteSeedGenerator.Inputs.class
)
@Operator(
    group = "random"
)
public final class DeleteSeedGenerator extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DeleteSeedGenerator";

  public DeleteSeedGenerator(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new DeleteSeedGenerator operation.
   *
   * @param scope current scope
   * @param handle The handle value
   * @param deleter The deleter value
   * @return a new instance of DeleteSeedGenerator
   */
  @Endpoint(
      describeByClass = true
  )
  public static DeleteSeedGenerator create(Scope scope, Operand<? extends TType> handle,
      Operand<? extends TType> deleter) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DeleteSeedGenerator");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(deleter.asOutput());
    return new DeleteSeedGenerator(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = DeleteSeedGenerator.class
  )
  public static class Inputs extends RawOpInputs<DeleteSeedGenerator> {
    /**
     * The handle input
     */
    public final Operand<? extends TType> handle;

    /**
     * The deleter input
     */
    public final Operand<? extends TType> deleter;

    public Inputs(GraphOperation op) {
      super(new DeleteSeedGenerator(op), op, Arrays.asList());
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
      deleter = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
