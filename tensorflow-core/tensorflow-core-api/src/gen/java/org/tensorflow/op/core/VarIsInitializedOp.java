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

package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * Checks whether a resource handle-based variable has been initialized.
 */
@Operator
public final class VarIsInitializedOp extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "VarIsInitializedOp";

  private Output<TBool> isInitialized;

  private VarIsInitializedOp(Operation operation) {
    super(operation);
    int outputIdx = 0;
    isInitialized = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new VarIsInitializedOp operation.
   *
   * @param scope current scope
   * @param resource the input resource handle.
   * @return a new instance of VarIsInitializedOp
   */
  @Endpoint(
      describeByClass = true
  )
  public static VarIsInitializedOp create(Scope scope, Operand<? extends TType> resource) {
    OperationBuilder opBuilder = scope.env().opBuilder("VarIsInitializedOp", scope.makeOpName("VarIsInitializedOp"));
    opBuilder.addInput(resource.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new VarIsInitializedOp(opBuilder.build());
  }

  /**
   * Gets isInitialized.
   * a scalar boolean which is true if the variable has been
   * initialized.
   * @return isInitialized.
   */
  public Output<TBool> isInitialized() {
    return isInitialized;
  }

  @Override
  public Output<TBool> asOutput() {
    return isInitialized;
  }
}
