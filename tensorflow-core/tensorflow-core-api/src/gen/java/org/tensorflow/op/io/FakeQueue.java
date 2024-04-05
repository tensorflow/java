/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
 * Deprecated. Do not use.
 */
@OpMetadata(
    opType = FakeQueue.OP_NAME,
    inputsClass = FakeQueue.Inputs.class
)
@Operator(
    group = "io"
)
public final class FakeQueue extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FakeQueue";

  private Output<TString> handle;

  public FakeQueue(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FakeQueue operation.
   *
   * @param scope current scope
   * @param resource The resource value
   * @return a new instance of FakeQueue
   */
  @Endpoint(
      describeByClass = true
  )
  public static FakeQueue create(Scope scope, Operand<? extends TType> resource) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FakeQueue");
    opBuilder.addInput(resource.asOutput());
    return new FakeQueue(opBuilder.build());
  }

  /**
   * Gets handle.
   *
   * @return handle.
   */
  public Output<TString> handle() {
    return handle;
  }

  @Override
  public Output<TString> asOutput() {
    return handle;
  }

  @OpInputsMetadata(
      outputsClass = FakeQueue.class
  )
  public static class Inputs extends RawOpInputs<FakeQueue> {
    /**
     * The resource input
     */
    public final Operand<? extends TType> resource;

    public Inputs(GraphOperation op) {
      super(new FakeQueue(op), op, Arrays.asList());
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
