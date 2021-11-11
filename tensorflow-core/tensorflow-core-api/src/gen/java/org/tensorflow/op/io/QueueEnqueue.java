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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Enqueues a tuple of one or more tensors in the given queue.
 * The components input has k elements, which correspond to the components of
 * tuples stored in the given queue.
 * <p>N.B. If the queue is full, this operation will block until the given
 * element has been enqueued (or 'timeout_ms' elapses, if specified).
 */
@OpMetadata(
    opType = QueueEnqueue.OP_NAME,
    inputsClass = QueueEnqueue.Inputs.class
)
@Operator(
    group = "io"
)
public final class QueueEnqueue extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QueueEnqueueV2";

  public QueueEnqueue(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new QueueEnqueueV2 operation.
   *
   * @param scope current scope
   * @param handle The handle to a queue.
   * @param components One or more tensors from which the enqueued tensors should be taken.
   * @param options carries optional attribute values
   * @return a new instance of QueueEnqueue
   */
  @Endpoint(
      describeByClass = true
  )
  public static QueueEnqueue create(Scope scope, Operand<? extends TType> handle,
      Iterable<Operand<?>> components, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QueueEnqueue");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInputList(Operands.asOutputs(components));
    if (options != null) {
      for (Options opts : options) {
        if (opts.timeoutMs != null) {
          opBuilder.setAttr("timeout_ms", opts.timeoutMs);
        }
      }
    }
    return new QueueEnqueue(opBuilder.build());
  }

  /**
   * Sets the timeoutMs option.
   *
   * @param timeoutMs If the queue is full, this operation will block for up to
   * timeout_ms milliseconds.
   * Note: This option is not supported yet.
   * @return this Options instance.
   */
  public static Options timeoutMs(Long timeoutMs) {
    return new Options().timeoutMs(timeoutMs);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.io.QueueEnqueue}
   */
  public static class Options {
    private Long timeoutMs;

    private Options() {
    }

    /**
     * Sets the timeoutMs option.
     *
     * @param timeoutMs If the queue is full, this operation will block for up to
     * timeout_ms milliseconds.
     * Note: This option is not supported yet.
     * @return this Options instance.
     */
    public Options timeoutMs(Long timeoutMs) {
      this.timeoutMs = timeoutMs;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = QueueEnqueue.class
  )
  public static class Inputs extends RawOpInputs<QueueEnqueue> {
    /**
     * The handle to a queue.
     */
    public final Operand<? extends TType> handle;

    /**
     * One or more tensors from which the enqueued tensors should be taken.
     */
    public final Iterable<Operand<?>> components;

    /**
     * The Tcomponents attribute
     */
    public final DataType[] Tcomponents;

    /**
     * If the queue is full, this operation will block for up to
     * timeout_ms milliseconds.
     * Note: This option is not supported yet.
     */
    public final long timeoutMs;

    public Inputs(GraphOperation op) {
      super(new QueueEnqueue(op), op, Arrays.asList("Tcomponents", "timeout_ms"));
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
      int componentsLength = op.inputListLength("components");
      components = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, componentsLength));
      inputIndex += componentsLength;
      Tcomponents = op.attributes().getAttrTypeList("Tcomponents");
      timeoutMs = op.attributes().getAttrInt("timeout_ms");
    }
  }
}
