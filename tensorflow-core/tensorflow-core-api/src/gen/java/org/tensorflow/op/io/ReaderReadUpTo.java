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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Returns up to {@code num_records} (key, value) pairs produced by a Reader.
 * Will dequeue from the input queue if necessary (e.g. when the
 * Reader needs to start reading from a new file since it has finished
 * with the previous file).
 * It may return less than {@code num_records} even before the last batch.
 */
@Operator(
    group = "io"
)
public final class ReaderReadUpTo extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ReaderReadUpToV2";

  private Output<TString> keys;

  private Output<TString> values;

  private ReaderReadUpTo(Operation operation) {
    super(operation);
    int outputIdx = 0;
    keys = operation.output(outputIdx++);
    values = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ReaderReadUpToV2 operation.
   *
   * @param scope current scope
   * @param readerHandle Handle to a {@code Reader}.
   * @param queueHandle Handle to a {@code Queue}, with string work items.
   * @param numRecords number of records to read from {@code Reader}.
   * @return a new instance of ReaderReadUpTo
   */
  @Endpoint(
      describeByClass = true
  )
  public static ReaderReadUpTo create(Scope scope, Operand<? extends TType> readerHandle,
      Operand<? extends TType> queueHandle, Operand<TInt64> numRecords) {
    OperationBuilder opBuilder = scope.env().opBuilder("ReaderReadUpToV2", scope.makeOpName("ReaderReadUpTo"));
    opBuilder.addInput(readerHandle.asOutput());
    opBuilder.addInput(queueHandle.asOutput());
    opBuilder.addInput(numRecords.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new ReaderReadUpTo(opBuilder.build());
  }

  /**
   * Gets keys.
   * A 1-D tensor.
   * @return keys.
   */
  public Output<TString> keys() {
    return keys;
  }

  /**
   * Gets values.
   * A 1-D tensor.
   * @return values.
   */
  public Output<TString> values() {
    return values;
  }
}
