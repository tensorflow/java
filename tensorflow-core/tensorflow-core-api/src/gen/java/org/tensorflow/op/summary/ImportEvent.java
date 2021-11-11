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

package org.tensorflow.op.summary;

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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The ImportEvent operation
 */
@OpMetadata(
    opType = ImportEvent.OP_NAME,
    inputsClass = ImportEvent.Inputs.class
)
public final class ImportEvent extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ImportEvent";

  public ImportEvent(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new ImportEvent operation.
   *
   * @param scope current scope
   * @param writer The writer value
   * @param event The event value
   * @return a new instance of ImportEvent
   */
  @Endpoint(
      describeByClass = true
  )
  public static ImportEvent create(Scope scope, Operand<? extends TType> writer,
      Operand<TString> event) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ImportEvent");
    opBuilder.addInput(writer.asOutput());
    opBuilder.addInput(event.asOutput());
    return new ImportEvent(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = ImportEvent.class
  )
  public static class Inputs extends RawOpInputs<ImportEvent> {
    /**
     * The writer input
     */
    public final Operand<? extends TType> writer;

    /**
     * The event input
     */
    public final Operand<TString> event;

    public Inputs(GraphOperation op) {
      super(new ImportEvent(op), op, Arrays.asList());
      int inputIndex = 0;
      writer = (Operand<? extends TType>) op.input(inputIndex++);
      event = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
