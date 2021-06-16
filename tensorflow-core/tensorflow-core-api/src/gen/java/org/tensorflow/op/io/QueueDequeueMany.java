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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Dequeues {@code n} tuples of one or more tensors from the given queue.
 * If the queue is closed and there are fewer than {@code n} elements, then an
 * OutOfRange error is returned.
 * <p>This operation concatenates queue-element component tensors along the
 * 0th dimension to make a single component tensor.  All of the components
 * in the dequeued tuple will have size {@code n} in the 0th dimension.
 * <p>This operation has {@code k} outputs, where {@code k} is the number of components in
 * the tuples stored in the given queue, and output {@code i} is the ith
 * component of the dequeued tuple.
 * <p>N.B. If the queue is empty, this operation will block until {@code n} elements
 * have been dequeued (or 'timeout_ms' elapses, if specified).
 */
@Operator(
    group = "io"
)
public final class QueueDequeueMany extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QueueDequeueManyV2";

  private List<Output<?>> components;

  @SuppressWarnings("unchecked")
  private QueueDequeueMany(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int componentsLength = operation.outputListLength("components");
    components = Arrays.asList(operation.outputList(outputIdx, componentsLength));
    outputIdx += componentsLength;
  }

  /**
   * Factory method to create a class wrapping a new QueueDequeueManyV2 operation.
   *
   * @param scope current scope
   * @param handle The handle to a queue.
   * @param n The number of tuples to dequeue.
   * @param componentTypes The type of each component in a tuple.
   * @param options carries optional attribute values
   * @return a new instance of QueueDequeueMany
   */
  @Endpoint(
      describeByClass = true
  )
  public static QueueDequeueMany create(Scope scope, Operand<? extends TType> handle,
      Operand<TInt32> n, List<Class<? extends TType>> componentTypes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("QueueDequeueMany"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(n.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("component_types", Operands.toDataTypes(componentTypes));
    if (options != null) {
      for (Options opts : options) {
        if (opts.timeoutMs != null) {
          opBuilder.setAttr("timeout_ms", opts.timeoutMs);
        }
      }
    }
    return new QueueDequeueMany(opBuilder.build());
  }

  /**
   * Sets the timeoutMs option.
   *
   * @param timeoutMs If the queue has fewer than n elements, this operation
   * will block for up to timeout_ms milliseconds.
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
   * Optional attributes for {@link org.tensorflow.op.io.QueueDequeueMany}
   */
  public static class Options {
    private Long timeoutMs;

    private Options() {
    }

    /**
     * Sets the timeoutMs option.
     *
     * @param timeoutMs If the queue has fewer than n elements, this operation
     * will block for up to timeout_ms milliseconds.
     * Note: This option is not supported yet.
     * @return this Options instance.
     */
    public Options timeoutMs(Long timeoutMs) {
      this.timeoutMs = timeoutMs;
      return this;
    }
  }
}
