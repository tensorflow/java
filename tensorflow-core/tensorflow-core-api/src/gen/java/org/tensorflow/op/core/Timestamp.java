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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat64;

/**
 * Provides the time since epoch in seconds.
 * Returns the timestamp as a {@code float64} for seconds since the Unix epoch.
 * <p>Note: the timestamp is computed when the op is executed, not when it is added
 * to the graph.
 */
@OpMetadata(
    opType = Timestamp.OP_NAME,
    inputsClass = Timestamp.Inputs.class
)
@Operator
public final class Timestamp extends RawOp implements Operand<TFloat64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Timestamp";

  private Output<TFloat64> ts;

  public Timestamp(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    ts = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Timestamp operation.
   *
   * @param scope current scope
   * @return a new instance of Timestamp
   */
  @Endpoint(
      describeByClass = true
  )
  public static Timestamp create(Scope scope) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Timestamp");
    return new Timestamp(opBuilder.build());
  }

  /**
   * Gets ts.
   *
   * @return ts.
   */
  public Output<TFloat64> ts() {
    return ts;
  }

  @Override
  public Output<TFloat64> asOutput() {
    return ts;
  }

  @OpInputsMetadata(
      outputsClass = Timestamp.class
  )
  public static class Inputs extends RawOpInputs<Timestamp> {
    public Inputs(GraphOperation op) {
      super(new Timestamp(op), op, Arrays.asList());
      int inputIndex = 0;
    }
  }
}
