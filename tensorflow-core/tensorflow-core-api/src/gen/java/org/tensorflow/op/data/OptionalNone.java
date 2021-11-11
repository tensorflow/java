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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Creates an Optional variant with no value.
 */
@OpMetadata(
    opType = OptionalNone.OP_NAME,
    inputsClass = OptionalNone.Inputs.class
)
@Operator(
    group = "data"
)
public final class OptionalNone extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "OptionalNone";

  private Output<? extends TType> optional;

  @SuppressWarnings("unchecked")
  public OptionalNone(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    optional = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new OptionalNone operation.
   *
   * @param scope current scope
   * @return a new instance of OptionalNone
   */
  @Endpoint(
      describeByClass = true
  )
  public static OptionalNone create(Scope scope) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "OptionalNone");
    return new OptionalNone(opBuilder.build());
  }

  /**
   * Gets optional.
   *
   * @return optional.
   */
  public Output<? extends TType> optional() {
    return optional;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) optional;
  }

  @OpInputsMetadata(
      outputsClass = OptionalNone.class
  )
  public static class Inputs extends RawOpInputs<OptionalNone> {
    public Inputs(GraphOperation op) {
      super(new OptionalNone(op), op, Arrays.asList());
      int inputIndex = 0;
    }
  }
}
