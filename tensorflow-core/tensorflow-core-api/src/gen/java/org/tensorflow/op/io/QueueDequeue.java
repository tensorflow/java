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
import java.util.Iterator;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
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
 * Dequeues a tuple of one or more tensors from the given queue.
 * This operation has k outputs, where k is the number of components
 * in the tuples stored in the given queue, and output i is the ith
 * component of the dequeued tuple.
 * <p>N.B. If the queue is empty, this operation will block until an element
 * has been dequeued (or 'timeout_ms' elapses, if specified).
 */
@OpMetadata(
    opType = QueueDequeue.OP_NAME,
    inputsClass = QueueDequeue.Inputs.class
)
@Operator(
    group = "io"
)
public final class QueueDequeue extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QueueDequeueV2";

  private List<Output<?>> components;

  @SuppressWarnings("unchecked")
  public QueueDequeue(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int componentsLength = operation.outputListLength("components");
    components = Arrays.asList(operation.outputList(outputIdx, componentsLength));
    outputIdx += componentsLength;
  }

  /**
   * Factory method to create a class wrapping a new QueueDequeueV2 operation.
   *
   * @param scope current scope
   * @param handle The handle to a queue.
   * @param componentTypes The type of each component in a tuple.
   * @param options carries optional attribute values
   * @return a new instance of QueueDequeue
   */
  @Endpoint(
      describeByClass = true
  )
  public static QueueDequeue create(Scope scope, Operand<? extends TType> handle,
      List<Class<? extends TType>> componentTypes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QueueDequeue");
    opBuilder.addInput(handle.asOutput());
    opBuilder.setAttr("component_types", Operands.toDataTypes(componentTypes));
    if (options != null) {
      for (Options opts : options) {
        if (opts.timeoutMs != null) {
          opBuilder.setAttr("timeout_ms", opts.timeoutMs);
        }
      }
    }
    return new QueueDequeue(opBuilder.build());
  }

  /**
   * Sets the timeoutMs option.
   *
   * @param timeoutMs If the queue is empty, this operation will block for up to
   * timeout_ms milliseconds.
   * Note: This option is not supported yet.
   * @return this Options instance.
   */
  public static Options timeoutMs(Long timeoutMs) {
    return new Options().timeoutMs(timeoutMs);
  }

  /**
   * Gets components.
   * One or more tensors that were dequeued as a tuple.
   * @return components.
   */
  public List<Output<?>> components() {
    return components;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) components.iterator();
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.io.QueueDequeue}
   */
  public static class Options {
    private Long timeoutMs;

    private Options() {
    }

    /**
     * Sets the timeoutMs option.
     *
     * @param timeoutMs If the queue is empty, this operation will block for up to
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
      outputsClass = QueueDequeue.class
  )
  public static class Inputs extends RawOpInputs<QueueDequeue> {
    /**
     * The handle to a queue.
     */
    public final Operand<? extends TType> handle;

    /**
     * The type of each component in a tuple.
     */
    public final DataType[] componentTypes;

    /**
     * If the queue is empty, this operation will block for up to
     * timeout_ms milliseconds.
     * Note: This option is not supported yet.
     */
    public final long timeoutMs;

    public Inputs(GraphOperation op) {
      super(new QueueDequeue(op), op, Arrays.asList("component_types", "timeout_ms"));
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
      componentTypes = op.attributes().getAttrTypeList("component_types");
      timeoutMs = op.attributes().getAttrInt("timeout_ms");
    }
  }
}
