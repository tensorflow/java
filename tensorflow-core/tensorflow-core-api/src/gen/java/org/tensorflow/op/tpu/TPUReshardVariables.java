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

package org.tensorflow.op.tpu;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Op that reshards on-device TPU variables to specified state.
 * Op that reshards on-device TPU variables to specified state. Internal use only.
 * <p>The sharding state is represented as the key of the compilation that generated
 * the sharding/unsharding programs along with the main program. new_format_key
 * specifies the desired state, and format_state_var is the current state of the
 * variables.
 */
@OpMetadata(
    opType = TPUReshardVariables.OP_NAME,
    inputsClass = TPUReshardVariables.Inputs.class
)
public final class TPUReshardVariables extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUReshardVariables";

  public TPUReshardVariables(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new TPUReshardVariables operation.
   *
   * @param scope current scope
   * @param vars The vars value
   * @param newFormatKey The newFormatKey value
   * @param formatStateVar The formatStateVar value
   * @return a new instance of TPUReshardVariables
   */
  @Endpoint(
      describeByClass = true
  )
  public static TPUReshardVariables create(Scope scope, Iterable<Operand<? extends TType>> vars,
      Operand<TString> newFormatKey, Operand<? extends TType> formatStateVar) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TPUReshardVariables");
    opBuilder.addInputList(Operands.asOutputs(vars));
    opBuilder.addInput(newFormatKey.asOutput());
    opBuilder.addInput(formatStateVar.asOutput());
    return new TPUReshardVariables(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = TPUReshardVariables.class
  )
  public static class Inputs extends RawOpInputs<TPUReshardVariables> {
    /**
     * The vars input
     */
    public final Iterable<Operand<? extends TType>> vars;

    /**
     * The newFormatKey input
     */
    public final Operand<TString> newFormatKey;

    /**
     * The formatStateVar input
     */
    public final Operand<? extends TType> formatStateVar;

    public Inputs(GraphOperation op) {
      super(new TPUReshardVariables(op), op, Arrays.asList());
      int inputIndex = 0;
      int varsLength = op.inputListLength("vars");
      vars = Arrays.asList((Operand<? extends TType>[]) op.inputList(inputIndex, varsLength));
      inputIndex += varsLength;
      newFormatKey = (Operand<TString>) op.input(inputIndex++);
      formatStateVar = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
