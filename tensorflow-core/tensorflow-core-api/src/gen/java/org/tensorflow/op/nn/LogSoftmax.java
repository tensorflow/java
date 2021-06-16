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

package org.tensorflow.op.nn;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;

/**
 * Computes log softmax activations.
 * For each batch {@code i} and class {@code j} we have
 * <pre>
 * logsoftmax[i, j] = logits[i, j] - log(sum(exp(logits[i])))
 * </pre>
 *
 * @param <T> data type for {@code logsoftmax} output
 */
@Operator(
    group = "nn"
)
public final class LogSoftmax<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LogSoftmax";

  private Output<T> logsoftmax;

  private LogSoftmax(Operation operation) {
    super(operation);
    int outputIdx = 0;
    logsoftmax = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LogSoftmax operation.
   *
   * @param scope current scope
   * @param logits 2-D with shape {@code [batch_size, num_classes]}.
   * @param <T> data type for {@code LogSoftmax} output and operands
   * @return a new instance of LogSoftmax
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> LogSoftmax<T> create(Scope scope, Operand<T> logits) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("LogSoftmax"));
    opBuilder.addInput(logits.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new LogSoftmax<>(opBuilder.build());
  }

  /**
   * Gets logsoftmax.
   * Same shape as {@code logits}.
   * @return logsoftmax.
   */
  public Output<T> logsoftmax() {
    return logsoftmax;
  }

  @Override
  public Output<T> asOutput() {
    return logsoftmax;
  }
}
