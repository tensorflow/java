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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * Checks whether a tensor has been initialized.
 * Outputs boolean scalar indicating whether the tensor has been initialized.
 */
@OpMetadata(
    opType = IsVariableInitialized.OP_NAME,
    inputsClass = IsVariableInitialized.Inputs.class
)
@Operator
public final class IsVariableInitialized extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IsVariableInitialized";

  private Output<TBool> isInitialized;

  public IsVariableInitialized(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    isInitialized = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IsVariableInitialized operation.
   *
   * @param scope current scope
   * @param ref Should be from a {@code Variable} node. May be uninitialized.
   * @return a new instance of IsVariableInitialized
   */
  @Endpoint(
      describeByClass = true
  )
  public static IsVariableInitialized create(Scope scope, Operand<? extends TType> ref) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IsVariableInitialized");
    opBuilder.addInput(ref.asOutput());
    return new IsVariableInitialized(opBuilder.build());
  }

  /**
   * Gets isInitialized.
   *
   * @return isInitialized.
   */
  public Output<TBool> isInitialized() {
    return isInitialized;
  }

  @Override
  public Output<TBool> asOutput() {
    return isInitialized;
  }

  @OpInputsMetadata(
      outputsClass = IsVariableInitialized.class
  )
  public static class Inputs extends RawOpInputs<IsVariableInitialized> {
    /**
     * Should be from a {@code Variable} node. May be uninitialized.
     */
    public final Operand<? extends TType> ref;

    /**
     * The type of elements in the variable tensor.
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new IsVariableInitialized(op), op, Arrays.asList("dtype"));
      int inputIndex = 0;
      ref = (Operand<? extends TType>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
