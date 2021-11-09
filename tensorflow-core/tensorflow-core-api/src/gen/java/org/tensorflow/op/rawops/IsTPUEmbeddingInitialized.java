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

package org.tensorflow.op.rawops;

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
import org.tensorflow.types.TBool;

/**
 * Whether TPU Embedding is initialized in a distributed TPU system.
 */
public final class IsTPUEmbeddingInitialized extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IsTPUEmbeddingInitialized";

  private Output<TBool> isTpuEmbeddingInitialized;

  private IsTPUEmbeddingInitialized(Operation operation) {
    super(operation);
    int outputIdx = 0;
    isTpuEmbeddingInitialized = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IsTPUEmbeddingInitialized operation.
   *
   * @param scope current scope
   * @return a new instance of IsTPUEmbeddingInitialized
   */
  @Endpoint(
      describeByClass = true
  )
  public static IsTPUEmbeddingInitialized create(Scope scope) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IsTPUEmbeddingInitialized");
    return new IsTPUEmbeddingInitialized(opBuilder.build());
  }

  /**
   * Gets isTpuEmbeddingInitialized.
   *
   * @return isTpuEmbeddingInitialized.
   */
  public Output<TBool> isTpuEmbeddingInitialized() {
    return isTpuEmbeddingInitialized;
  }

  @Override
  public Output<TBool> asOutput() {
    return isTpuEmbeddingInitialized;
  }

  public static class Inputs extends RawOpInputs<IsTPUEmbeddingInitialized> {
    public Inputs(GraphOperation op) {
      super(new IsTPUEmbeddingInitialized(op), op, Arrays.asList());
      int inputIndex = 0;
    }
  }
}
