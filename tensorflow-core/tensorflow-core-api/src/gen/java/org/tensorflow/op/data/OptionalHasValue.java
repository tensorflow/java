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
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * Returns true if and only if the given Optional variant has a value.
 */
@OpMetadata(
    opType = OptionalHasValue.OP_NAME,
    inputsClass = OptionalHasValue.Inputs.class
)
@Operator(
    group = "data"
)
public final class OptionalHasValue extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "OptionalHasValue";

  private Output<TBool> hasValue;

  public OptionalHasValue(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    hasValue = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new OptionalHasValue operation.
   *
   * @param scope current scope
   * @param optional The optional value
   * @return a new instance of OptionalHasValue
   */
  @Endpoint(
      describeByClass = true
  )
  public static OptionalHasValue create(Scope scope, Operand<? extends TType> optional) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "OptionalHasValue");
    opBuilder.addInput(optional.asOutput());
    return new OptionalHasValue(opBuilder.build());
  }

  /**
   * Gets hasValue.
   *
   * @return hasValue.
   */
  public Output<TBool> hasValue() {
    return hasValue;
  }

  @Override
  public Output<TBool> asOutput() {
    return hasValue;
  }

  @OpInputsMetadata(
      outputsClass = OptionalHasValue.class
  )
  public static class Inputs extends RawOpInputs<OptionalHasValue> {
    /**
     * The optional input
     */
    public final Operand<? extends TType> optional;

    public Inputs(GraphOperation op) {
      super(new OptionalHasValue(op), op, Arrays.asList());
      int inputIndex = 0;
      optional = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
