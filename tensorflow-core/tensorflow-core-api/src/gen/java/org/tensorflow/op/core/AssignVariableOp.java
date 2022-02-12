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
 * Assigns a new value to a variable.
 * Any ReadVariableOp with a control dependency on this op is guaranteed to return
 * this value or a subsequent newer value of the variable.
 */
@OpMetadata(
    opType = AssignVariableOp.OP_NAME,
    inputsClass = AssignVariableOp.Inputs.class
)
@Operator
public final class AssignVariableOp extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AssignVariableOp";

  public AssignVariableOp(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new AssignVariableOp operation.
   *
   * @param scope current scope
   * @param resource handle to the resource in which to store the variable.
   * @param value the value to set the new tensor to use.
   * @param options carries optional attribute values
   * @return a new instance of AssignVariableOp
   */
  @Endpoint(
      describeByClass = true
  )
  public static AssignVariableOp create(Scope scope, Operand<? extends TType> resource,
      Operand<? extends TType> value, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AssignVariableOp");
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(value.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.validateShape != null) {
          opBuilder.setAttr("validate_shape", opts.validateShape);
        }
      }
    }
    return new AssignVariableOp(opBuilder.build());
  }

  /**
   * Sets the validateShape option.
   *
   * @param validateShape the validateShape option
   * @return this Options instance.
   */
  public static Options validateShape(Boolean validateShape) {
    return new Options().validateShape(validateShape);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.AssignVariableOp}
   */
  public static class Options {
    private Boolean validateShape;

    private Options() {
    }

    /**
     * Sets the validateShape option.
     *
     * @param validateShape the validateShape option
     * @return this Options instance.
     */
    public Options validateShape(Boolean validateShape) {
      this.validateShape = validateShape;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = AssignVariableOp.class
  )
  public static class Inputs extends RawOpInputs<AssignVariableOp> {
    /**
     * handle to the resource in which to store the variable.
     */
    public final Operand<? extends TType> resource;

    /**
     * the value to set the new tensor to use.
     */
    public final Operand<? extends TType> value;

    /**
     * the dtype of the value.
     */
    public final DataType dtype;

    /**
     * The validateShape attribute
     */
    public final boolean validateShape;

    public Inputs(GraphOperation op) {
      super(new AssignVariableOp(op), op, Arrays.asList("dtype", "validate_shape"));
      int inputIndex = 0;
      resource = (Operand<? extends TType>) op.input(inputIndex++);
      value = (Operand<? extends TType>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      validateShape = op.attributes().getAttrBool("validate_shape");
    }
  }
}
