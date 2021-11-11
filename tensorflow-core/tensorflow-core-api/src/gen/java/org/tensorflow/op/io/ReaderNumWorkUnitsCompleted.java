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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Returns the number of work units this Reader has finished processing.
 */
@OpMetadata(
    opType = ReaderNumWorkUnitsCompleted.OP_NAME,
    inputsClass = ReaderNumWorkUnitsCompleted.Inputs.class
)
@Operator(
    group = "io"
)
public final class ReaderNumWorkUnitsCompleted extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ReaderNumWorkUnitsCompletedV2";

  private Output<TInt64> unitsCompleted;

  public ReaderNumWorkUnitsCompleted(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    unitsCompleted = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ReaderNumWorkUnitsCompletedV2 operation.
   *
   * @param scope current scope
   * @param readerHandle Handle to a Reader.
   * @return a new instance of ReaderNumWorkUnitsCompleted
   */
  @Endpoint(
      describeByClass = true
  )
  public static ReaderNumWorkUnitsCompleted create(Scope scope,
      Operand<? extends TType> readerHandle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReaderNumWorkUnitsCompleted");
    opBuilder.addInput(readerHandle.asOutput());
    return new ReaderNumWorkUnitsCompleted(opBuilder.build());
  }

  /**
   * Gets unitsCompleted.
   *
   * @return unitsCompleted.
   */
  public Output<TInt64> unitsCompleted() {
    return unitsCompleted;
  }

  @Override
  public Output<TInt64> asOutput() {
    return unitsCompleted;
  }

  @OpInputsMetadata(
      outputsClass = ReaderNumWorkUnitsCompleted.class
  )
  public static class Inputs extends RawOpInputs<ReaderNumWorkUnitsCompleted> {
    /**
     * Handle to a Reader.
     */
    public final Operand<? extends TType> readerHandle;

    public Inputs(GraphOperation op) {
      super(new ReaderNumWorkUnitsCompleted(op), op, Arrays.asList());
      int inputIndex = 0;
      readerHandle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
