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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Computes the number of elements in the given queue.
 */
@OpMetadata(
    opType = QueueSize.OP_NAME,
    inputsClass = QueueSize.Inputs.class
)
@Operator(
    group = "io"
)
public final class QueueSize extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QueueSizeV2";

  private Output<TInt32> output;

  public QueueSize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QueueSizeV2 operation.
   *
   * @param scope current scope
   * @param handle The handle to a queue.
   * @return a new instance of QueueSize
   */
  @Endpoint(
      describeByClass = true
  )
  public static QueueSize create(Scope scope, Operand<? extends TType> handle) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QueueSize");
    opBuilder.addInput(handle.asOutput());
    return new QueueSize(opBuilder.build());
  }

  /**
   * Gets output.
   * The number of elements in the given queue.
   * @return output.
   */
  public Output<TInt32> output() {
    return output;
  }

  @Override
  public Output<TInt32> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = QueueSize.class
  )
  public static class Inputs extends RawOpInputs<QueueSize> {
    /**
     * The handle to a queue.
     */
    public final Operand<? extends TType> handle;

    public Inputs(GraphOperation op) {
      super(new QueueSize(op), op, Arrays.asList());
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
