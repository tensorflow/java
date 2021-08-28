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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Asserts that compilation succeeded. This op produces no output and closes the
 * device during failure to ensure all pending device interactions fail.
 * <p>'compilation_status' is a serialized CompilationResultProto.
 */
@Operator(
    group = "tpu"
)
public final class CompileSucceededAssert extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUCompileSucceededAssert";

  private CompileSucceededAssert(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new TPUCompileSucceededAssert operation.
   *
   * @param scope current scope
   * @param compilationStatus the compilationStatus value
   * @return a new instance of CompileSucceededAssert
   */
  @Endpoint(
      describeByClass = true
  )
  public static CompileSucceededAssert create(Scope scope, Operand<TString> compilationStatus) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CompileSucceededAssert");
    opBuilder.addInput(compilationStatus.asOutput());
    return new CompileSucceededAssert(opBuilder.build());
  }
}
