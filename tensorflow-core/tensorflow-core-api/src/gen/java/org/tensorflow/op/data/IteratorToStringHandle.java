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

package org.tensorflow.op.data;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Converts the given {@code resource_handle} representing an iterator to a string.
 */
@Operator(
    group = "data"
)
public final class IteratorToStringHandle extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IteratorToStringHandle";

  private Output<TString> stringHandle;

  private IteratorToStringHandle(Operation operation) {
    super(operation);
    int outputIdx = 0;
    stringHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IteratorToStringHandle operation.
   *
   * @param scope current scope
   * @param resourceHandle A handle to an iterator resource.
   * @return a new instance of IteratorToStringHandle
   */
  @Endpoint(
      describeByClass = true
  )
  public static IteratorToStringHandle create(Scope scope,
      Operand<? extends TType> resourceHandle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IteratorToStringHandle");
    opBuilder.addInput(resourceHandle.asOutput());
    return new IteratorToStringHandle(opBuilder.build());
  }

  /**
   * Gets stringHandle.
   * A string representation of the given handle.
   * @return stringHandle.
   */
  public Output<TString> stringHandle() {
    return stringHandle;
  }

  @Override
  public Output<TString> asOutput() {
    return stringHandle;
  }

  public static class Inputs extends RawOpInputs<IteratorToStringHandle> {
    /**
     * A handle to an iterator resource.
     */
    public final Operand<? extends TType> resourceHandle;

    public Inputs(GraphOperation op) {
      super(new IteratorToStringHandle(op), op, Arrays.asList());
      int inputIndex = 0;
      resourceHandle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
