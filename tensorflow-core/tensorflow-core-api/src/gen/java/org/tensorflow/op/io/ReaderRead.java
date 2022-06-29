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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Returns the next record (key, value pair) produced by a Reader.
 * Will dequeue from the input queue if necessary (e.g. when the
 * Reader needs to start reading from a new file since it has finished
 * with the previous file).
 */
@OpMetadata(
    opType = ReaderRead.OP_NAME,
    inputsClass = ReaderRead.Inputs.class
)
@Operator(
    group = "io"
)
public final class ReaderRead extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ReaderReadV2";

  private Output<TString> key;

  private Output<TString> value;

  public ReaderRead(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    key = operation.output(outputIdx++);
    value = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ReaderReadV2 operation.
   *
   * @param scope current scope
   * @param readerHandle Handle to a Reader.
   * @param queueHandle Handle to a Queue, with string work items.
   * @return a new instance of ReaderRead
   */
  @Endpoint(
      describeByClass = true
  )
  public static ReaderRead create(Scope scope, Operand<? extends TType> readerHandle,
      Operand<? extends TType> queueHandle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReaderRead");
    opBuilder.addInput(readerHandle.asOutput());
    opBuilder.addInput(queueHandle.asOutput());
    return new ReaderRead(opBuilder.build());
  }

  /**
   * Gets key.
   * A scalar.
   * @return key.
   */
  public Output<TString> key() {
    return key;
  }

  /**
   * Gets value.
   * A scalar.
   * @return value.
   */
  public Output<TString> value() {
    return value;
  }

  @OpInputsMetadata(
      outputsClass = ReaderRead.class
  )
  public static class Inputs extends RawOpInputs<ReaderRead> {
    /**
     * Handle to a Reader.
     */
    public final Operand<? extends TType> readerHandle;

    /**
     * Handle to a Queue, with string work items.
     */
    public final Operand<? extends TType> queueHandle;

    public Inputs(GraphOperation op) {
      super(new ReaderRead(op), op, Arrays.asList());
      int inputIndex = 0;
      readerHandle = (Operand<? extends TType>) op.input(inputIndex++);
      queueHandle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
