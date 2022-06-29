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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Closes the given barrier.
 * This operation signals that no more new elements will be inserted in the
 * given barrier. Subsequent InsertMany that try to introduce a new key will fail.
 * Subsequent InsertMany operations that just add missing components to already
 * existing elements will continue to succeed. Subsequent TakeMany operations will
 * continue to succeed if sufficient completed elements remain in the barrier.
 * Subsequent TakeMany operations that would block will fail immediately.
 */
@OpMetadata(
    opType = BarrierClose.OP_NAME,
    inputsClass = BarrierClose.Inputs.class
)
@Operator
public final class BarrierClose extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BarrierClose";

  public BarrierClose(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new BarrierClose operation.
   *
   * @param scope current scope
   * @param handle The handle to a barrier.
   * @param options carries optional attribute values
   * @return a new instance of BarrierClose
   */
  @Endpoint(
      describeByClass = true
  )
  public static BarrierClose create(Scope scope, Operand<TString> handle, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BarrierClose");
    opBuilder.addInput(handle.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.cancelPendingEnqueues != null) {
          opBuilder.setAttr("cancel_pending_enqueues", opts.cancelPendingEnqueues);
        }
      }
    }
    return new BarrierClose(opBuilder.build());
  }

  /**
   * Sets the cancelPendingEnqueues option.
   *
   * @param cancelPendingEnqueues If true, all pending enqueue requests that are
   * blocked on the barrier's queue will be canceled. InsertMany will fail, even
   * if no new key is introduced.
   * @return this Options instance.
   */
  public static Options cancelPendingEnqueues(Boolean cancelPendingEnqueues) {
    return new Options().cancelPendingEnqueues(cancelPendingEnqueues);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.BarrierClose}
   */
  public static class Options {
    private Boolean cancelPendingEnqueues;

    private Options() {
    }

    /**
     * Sets the cancelPendingEnqueues option.
     *
     * @param cancelPendingEnqueues If true, all pending enqueue requests that are
     * blocked on the barrier's queue will be canceled. InsertMany will fail, even
     * if no new key is introduced.
     * @return this Options instance.
     */
    public Options cancelPendingEnqueues(Boolean cancelPendingEnqueues) {
      this.cancelPendingEnqueues = cancelPendingEnqueues;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = BarrierClose.class
  )
  public static class Inputs extends RawOpInputs<BarrierClose> {
    /**
     * The handle to a barrier.
     */
    public final Operand<TString> handle;

    /**
     * If true, all pending enqueue requests that are
     * blocked on the barrier's queue will be canceled. InsertMany will fail, even
     * if no new key is introduced.
     */
    public final boolean cancelPendingEnqueues;

    public Inputs(GraphOperation op) {
      super(new BarrierClose(op), op, Arrays.asList("cancel_pending_enqueues"));
      int inputIndex = 0;
      handle = (Operand<TString>) op.input(inputIndex++);
      cancelPendingEnqueues = op.attributes().getAttrBool("cancel_pending_enqueues");
    }
  }
}
