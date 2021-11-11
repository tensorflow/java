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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.family.TType;

/**
 * The DeleteMemoryCache operation
 */
@OpMetadata(
    opType = DeleteMemoryCache.OP_NAME,
    inputsClass = DeleteMemoryCache.Inputs.class
)
public final class DeleteMemoryCache extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DeleteMemoryCache";

  public DeleteMemoryCache(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new DeleteMemoryCache operation.
   *
   * @param scope current scope
   * @param handle The handle value
   * @param deleter The deleter value
   * @return a new instance of DeleteMemoryCache
   */
  @Endpoint(
      describeByClass = true
  )
  public static DeleteMemoryCache create(Scope scope, Operand<? extends TType> handle,
      Operand<? extends TType> deleter) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DeleteMemoryCache");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(deleter.asOutput());
    return new DeleteMemoryCache(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = DeleteMemoryCache.class
  )
  public static class Inputs extends RawOpInputs<DeleteMemoryCache> {
    /**
     * The handle input
     */
    public final Operand<? extends TType> handle;

    /**
     * The deleter input
     */
    public final Operand<? extends TType> deleter;

    public Inputs(GraphOperation op) {
      super(new DeleteMemoryCache(op), op, Arrays.asList());
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
      deleter = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
