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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Enqueues zero or more tuples of one or more tensors in the given queue.
 * This operation slices each component tensor along the 0th dimension to
 * make multiple queue elements. All of the tuple components must have the
 * same size in the 0th dimension.
 * <p>The components input has k elements, which correspond to the components of
 * tuples stored in the given queue.
 * <p>N.B. If the queue is full, this operation will block until the given
 * elements have been enqueued (or 'timeout_ms' elapses, if specified).
 */
@Operator(
    group = "io"
)
public final class QueueEnqueueMany extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QueueEnqueueManyV2";

  private QueueEnqueueMany(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new QueueEnqueueManyV2 operation.
   *
   * @param scope current scope
   * @param handle The handle to a queue.
   * @param components One or more tensors from which the enqueued tensors should
   * be taken.
   * @param options carries optional attribute values
   * @return a new instance of QueueEnqueueMany
   */
  @Endpoint(
      describeByClass = true
  )
  public static QueueEnqueueMany create(Scope scope, Operand<? extends TType> handle,
      Iterable<Operand<?>> components, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("QueueEnqueueManyV2", scope.makeOpName("QueueEnqueueMany"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInputList(Operands.asOutputs(components));
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.timeoutMs != null) {
          opBuilder.setAttr("timeout_ms", opts.timeoutMs);
        }
      }
    }
    return new QueueEnqueueMany(opBuilder.build());
  }

  /**
   * Sets the timeoutMs option.
   *
   * @param timeoutMs If the queue is too full, this operation will block for up
   * to timeout_ms milliseconds.
   * Note: This option is not supported yet.
   * @return this Options instance.
   */
  public static Options timeoutMs(Long timeoutMs) {
    return new Options().timeoutMs(timeoutMs);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.io.QueueEnqueueMany}
   */
  public static class Options {
    private Long timeoutMs;

    private Options() {
    }

    /**
     * Sets the timeoutMs option.
     *
     * @param timeoutMs If the queue is too full, this operation will block for up
     * to timeout_ms milliseconds.
     * Note: This option is not supported yet.
     * @return this Options instance.
     */
    public Options timeoutMs(Long timeoutMs) {
      this.timeoutMs = timeoutMs;
      return this;
    }
  }
}
