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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

/**
 * Closes the given queue.
 * <p>
 * This operation signals that no more elements will be enqueued in the
 * given queue. Subsequent Enqueue(Many) operations will fail.
 * Subsequent Dequeue(Many) operations will continue to succeed if
 * sufficient elements remain in the queue. Subsequent Dequeue(Many)
 * operations that would block will fail immediately.
 */
@Operator(group = "io")
public final class QueueClose extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.io.QueueClose}
   */
  public static class Options {
    
    /**
     * @param cancelPendingEnqueues If true, all pending enqueue requests that are
     * blocked on the given queue will be canceled.
     */
    public Options cancelPendingEnqueues(Boolean cancelPendingEnqueues) {
      this.cancelPendingEnqueues = cancelPendingEnqueues;
      return this;
    }
    
    private Boolean cancelPendingEnqueues;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new QueueClose operation.
   * 
   * @param scope current scope
   * @param handle The handle to a queue.
   * @param options carries optional attributes values
   * @return a new instance of QueueClose
   */
  @Endpoint(describeByClass = true)
  public static QueueClose create(Scope scope, Operand<?> handle, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("QueueCloseV2", scope.makeOpName("QueueClose"));
    opBuilder.addInput(handle.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.cancelPendingEnqueues != null) {
          opBuilder.setAttr("cancel_pending_enqueues", opts.cancelPendingEnqueues);
        }
      }
    }
    return new QueueClose(opBuilder.build());
  }
  
  /**
   * @param cancelPendingEnqueues If true, all pending enqueue requests that are
   * blocked on the given queue will be canceled.
   */
  public static Options cancelPendingEnqueues(Boolean cancelPendingEnqueues) {
    return new Options().cancelPendingEnqueues(cancelPendingEnqueues);
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "QueueCloseV2";
  
  private QueueClose(Operation operation) {
    super(operation);
  }
}
