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

package org.tensorflow.op.math;

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
import org.tensorflow.types.TString;

/**
 * Output a fact about factorials.
 */
@OpMetadata(
    opType = Fact.OP_NAME,
    inputsClass = Fact.Inputs.class
)
@Operator(
    group = "math"
)
public final class Fact extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Fact";

  private Output<TString> fact;

  public Fact(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    fact = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Fact operation.
   *
   * @param scope current scope
   * @return a new instance of Fact
   */
  @Endpoint(
      describeByClass = true
  )
  public static Fact create(Scope scope) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Fact");
    return new Fact(opBuilder.build());
  }

  /**
   * Gets fact.
   *
   * @return fact.
   */
  public Output<TString> fact() {
    return fact;
  }

  @Override
  public Output<TString> asOutput() {
    return fact;
  }

  @OpInputsMetadata(
      outputsClass = Fact.class
  )
  public static class Inputs extends RawOpInputs<Fact> {
    public Inputs(GraphOperation op) {
      super(new Fact(op), op, Arrays.asList());
      int inputIndex = 0;
    }
  }
}
