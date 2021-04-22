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

package org.tensorflow.op.tpu;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TString;

/**
 * Returns the result of a TPU compilation.
 * This operation returns the result of a TPU compilation as a serialized
 * CompilationResultProto, which holds a status and an error message if an error
 * occurred during compilation.
 *
 * @deprecated use {@link org.tensorflow.op.tpu.CompilationResult} instead
 */
@Deprecated
public final class TPUCompilationResult extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUCompilationResult";

  private Output<TString> output;

  private TPUCompilationResult(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TPUCompilationResult operation.
   *
   * @param scope current scope
   * @return a new instance of TPUCompilationResult
   */
  @Endpoint(
      describeByClass = true
  )
  public static TPUCompilationResult create(Scope scope) {
    OperationBuilder opBuilder = scope.env().opBuilder("TPUCompilationResult", scope.makeOpName("TPUCompilationResult"));
    opBuilder = scope.apply(opBuilder);
    return new TPUCompilationResult(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<TString> output() {
    return output;
  }

  @Override
  public Output<TString> asOutput() {
    return output;
  }
}
