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
 * Returns the number of records this Reader has produced.
 * This is the same as the number of ReaderRead executions that have
 * succeeded.
 */
@OpMetadata(
    opType = ReaderNumRecordsProduced.OP_NAME,
    inputsClass = ReaderNumRecordsProduced.Inputs.class
)
@Operator(
    group = "io"
)
public final class ReaderNumRecordsProduced extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ReaderNumRecordsProducedV2";

  private Output<TInt64> recordsProduced;

  public ReaderNumRecordsProduced(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    recordsProduced = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ReaderNumRecordsProducedV2 operation.
   *
   * @param scope current scope
   * @param readerHandle Handle to a Reader.
   * @return a new instance of ReaderNumRecordsProduced
   */
  @Endpoint(
      describeByClass = true
  )
  public static ReaderNumRecordsProduced create(Scope scope,
      Operand<? extends TType> readerHandle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReaderNumRecordsProduced");
    opBuilder.addInput(readerHandle.asOutput());
    return new ReaderNumRecordsProduced(opBuilder.build());
  }

  /**
   * Gets recordsProduced.
   *
   * @return recordsProduced.
   */
  public Output<TInt64> recordsProduced() {
    return recordsProduced;
  }

  @Override
  public Output<TInt64> asOutput() {
    return recordsProduced;
  }

  @OpInputsMetadata(
      outputsClass = ReaderNumRecordsProduced.class
  )
  public static class Inputs extends RawOpInputs<ReaderNumRecordsProduced> {
    /**
     * Handle to a Reader.
     */
    public final Operand<? extends TType> readerHandle;

    public Inputs(GraphOperation op) {
      super(new ReaderNumRecordsProduced(op), op, Arrays.asList());
      int inputIndex = 0;
      readerHandle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
