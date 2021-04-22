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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Writes a scalar summary.
 * Writes scalar {@code value} at {@code step} with {@code tag} using summary {@code writer}.
 */
public final class WriteScalarSummary extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "WriteScalarSummary";

  private WriteScalarSummary(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new WriteScalarSummary operation.
   *
   * @param scope current scope
   * @param writer the writer value
   * @param step the step value
   * @param tag the tag value
   * @param value the value value
   * @return a new instance of WriteScalarSummary
   */
  @Endpoint(
      describeByClass = true
  )
  public static WriteScalarSummary create(Scope scope, Operand<? extends TType> writer,
      Operand<TInt64> step, Operand<TString> tag, Operand<? extends TNumber> value) {
    OperationBuilder opBuilder = scope.env().opBuilder("WriteScalarSummary", scope.makeOpName("WriteScalarSummary"));
    opBuilder.addInput(writer.asOutput());
    opBuilder.addInput(step.asOutput());
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(value.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new WriteScalarSummary(opBuilder.build());
  }
}
