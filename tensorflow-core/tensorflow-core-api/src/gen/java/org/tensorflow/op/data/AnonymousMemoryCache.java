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
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.family.TType;

/**
 * The AnonymousMemoryCache operation
 */
@OpMetadata(
    opType = AnonymousMemoryCache.OP_NAME,
    inputsClass = AnonymousMemoryCache.Inputs.class
)
public final class AnonymousMemoryCache extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AnonymousMemoryCache";

  private Output<? extends TType> handle;

  private Output<? extends TType> deleter;

  @SuppressWarnings("unchecked")
  public AnonymousMemoryCache(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
    deleter = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AnonymousMemoryCache operation.
   *
   * @param scope current scope
   * @return a new instance of AnonymousMemoryCache
   */
  @Endpoint(
      describeByClass = true
  )
  public static AnonymousMemoryCache create(Scope scope) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AnonymousMemoryCache");
    return new AnonymousMemoryCache(opBuilder.build());
  }

  /**
   * Gets handle.
   *
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  /**
   * Gets deleter.
   *
   * @return deleter.
   */
  public Output<? extends TType> deleter() {
    return deleter;
  }

  @OpInputsMetadata(
      outputsClass = AnonymousMemoryCache.class
  )
  public static class Inputs extends RawOpInputs<AnonymousMemoryCache> {
    public Inputs(GraphOperation op) {
      super(new AnonymousMemoryCache(op), op, Arrays.asList());
      int inputIndex = 0;
    }
  }
}
