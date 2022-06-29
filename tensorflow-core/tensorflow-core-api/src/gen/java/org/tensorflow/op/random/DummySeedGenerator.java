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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.family.TType;

/**
 * The DummySeedGenerator operation
 */
@OpMetadata(
    opType = DummySeedGenerator.OP_NAME,
    inputsClass = DummySeedGenerator.Inputs.class
)
public final class DummySeedGenerator extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DummySeedGenerator";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public DummySeedGenerator(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DummySeedGenerator operation.
   *
   * @param scope current scope
   * @return a new instance of DummySeedGenerator
   */
  @Endpoint(
      describeByClass = true
  )
  public static DummySeedGenerator create(Scope scope) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DummySeedGenerator");
    return new DummySeedGenerator(opBuilder.build());
  }

  /**
   * Gets handle.
   *
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  @OpInputsMetadata(
      outputsClass = DummySeedGenerator.class
  )
  public static class Inputs extends RawOpInputs<DummySeedGenerator> {
    public Inputs(GraphOperation op) {
      super(new DummySeedGenerator(op), op, Arrays.asList());
      int inputIndex = 0;
    }
  }
}
