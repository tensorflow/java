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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TInt32;

/**
 * Picks the best counter-based RNG algorithm based on device.
 * This op picks the best counter-based RNG algorithm based on device.
 */
public final class StatelessRandomGetAlg extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessRandomGetAlg";

  private Output<TInt32> alg;

  private StatelessRandomGetAlg(Operation operation) {
    super(operation);
    int outputIdx = 0;
    alg = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatelessRandomGetAlg operation.
   *
   * @param scope current scope
   * @return a new instance of StatelessRandomGetAlg
   */
  @Endpoint(
      describeByClass = true
  )
  public static StatelessRandomGetAlg create(Scope scope) {
    OperationBuilder opBuilder = scope.env().opBuilder("StatelessRandomGetAlg", scope.makeOpName("StatelessRandomGetAlg"));
    opBuilder = scope.apply(opBuilder);
    return new StatelessRandomGetAlg(opBuilder.build());
  }

  /**
   * Gets alg.
   * The RNG algorithm (shape int32[]).
   * @return alg.
   */
  public Output<TInt32> alg() {
    return alg;
  }

  @Override
  public Output<TInt32> asOutput() {
    return alg;
  }
}
