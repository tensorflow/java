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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Subtracts a value from the current value of a variable.
 * Any ReadVariableOp with a control dependency on this op is guaranteed to
 * see the decremented value or a subsequent newer one.
 */
@OpMetadata(
    opType = AssignSubVariableOp.OP_NAME,
    inputsClass = AssignSubVariableOp.Inputs.class
)
@Operator
public final class AssignSubVariableOp extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AssignSubVariableOp";

  public AssignSubVariableOp(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new AssignSubVariableOp operation.
   *
   * @param scope current scope
   * @param resource handle to the resource in which to store the variable.
   * @param value the value by which the variable will be incremented.
   * @return a new instance of AssignSubVariableOp
   */
  @Endpoint(
      describeByClass = true
  )
  public static AssignSubVariableOp create(Scope scope, Operand<? extends TType> resource,
      Operand<? extends TType> value) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AssignSubVariableOp");
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(value.asOutput());
    return new AssignSubVariableOp(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = AssignSubVariableOp.class
  )
  public static class Inputs extends RawOpInputs<AssignSubVariableOp> {
    /**
     * handle to the resource in which to store the variable.
     */
    public final Operand<? extends TType> resource;

    /**
     * the value by which the variable will be incremented.
     */
    public final Operand<? extends TType> value;

    /**
     * the dtype of the value.
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new AssignSubVariableOp(op), op, Arrays.asList("dtype"));
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      value = (Operand<? extends TType>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
