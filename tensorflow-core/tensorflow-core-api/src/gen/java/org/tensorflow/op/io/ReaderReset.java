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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Restore a Reader to its initial clean state.
 */
@OpMetadata(
    opType = ReaderReset.OP_NAME,
    inputsClass = ReaderReset.Inputs.class
)
@Operator(
    group = "io"
)
public final class ReaderReset extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ReaderResetV2";

  public ReaderReset(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new ReaderResetV2 operation.
   *
   * @param scope current scope
   * @param readerHandle Handle to a Reader.
   * @return a new instance of ReaderReset
   */
  @Endpoint(
      describeByClass = true
  )
  public static ReaderReset create(Scope scope, Operand<? extends TType> readerHandle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReaderReset");
    opBuilder.addInput(readerHandle.asOutput());
    return new ReaderReset(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = ReaderReset.class
  )
  public static class Inputs extends RawOpInputs<ReaderReset> {
    /**
     * Handle to a Reader.
     */
    public final Operand<? extends TType> readerHandle;

    public Inputs(GraphOperation op) {
      super(new ReaderReset(op), op, Arrays.asList());
      int inputIndex = 0;
      readerHandle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
