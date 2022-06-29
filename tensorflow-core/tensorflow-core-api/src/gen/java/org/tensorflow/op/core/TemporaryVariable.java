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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
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
 * Returns a tensor that may be mutated, but only persists within a single step.
 * This is an experimental op for internal use only and it is possible to use this
 * op in unsafe ways.  DO NOT USE unless you fully understand the risks.
 * <p>It is the caller's responsibility to ensure that 'ref' is eventually passed to a
 * matching 'DestroyTemporaryVariable' op after all other uses have completed.
 * <p>Outputs a ref to the tensor state so it may be read or modified.
 * <p>E.g.
 * var = state_ops.<em>temporary_variable([1, 2], types.float</em>)
 * var_name = var.op.name
 * var = state_ops.assign(var, [[4.0, 5.0]])
 * var = state_ops.assign_add(var, [[6.0, 7.0]])
 * final = state_ops._destroy_temporary_variable(var, var_name=var_name)
 *
 * @param <T> data type for {@code ref} output
 */
@OpMetadata(
    opType = TemporaryVariable.OP_NAME,
    inputsClass = TemporaryVariable.Inputs.class
)
@Operator
public final class TemporaryVariable<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TemporaryVariable";

  private Output<T> ref;

  public TemporaryVariable(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    ref = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TemporaryVariable operation.
   *
   * @param scope current scope
   * @param shape The shape of the variable tensor.
   * @param dtype The type of elements in the variable tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TemporaryVariable} output and operands
   * @return a new instance of TemporaryVariable
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TemporaryVariable<T> create(Scope scope, Shape shape,
      Class<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TemporaryVariable");
    opBuilder.setAttr("shape", shape);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.varName != null) {
          opBuilder.setAttr("var_name", opts.varName);
        }
      }
    }
    return new TemporaryVariable<>(opBuilder.build());
  }

  /**
   * Sets the varName option.
   *
   * @param varName Overrides the name used for the temporary variable resource. Default
   * value is the name of the 'TemporaryVariable' op (which is guaranteed unique).
   * @return this Options instance.
   */
  public static Options varName(String varName) {
    return new Options().varName(varName);
  }

  /**
   * Gets ref.
   * A reference to the variable tensor.
   * @return ref.
   */
  public Output<T> ref() {
    return ref;
  }

  @Override
  public Output<T> asOutput() {
    return ref;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.TemporaryVariable}
   */
  public static class Options {
    private String varName;

    private Options() {
    }

    /**
     * Sets the varName option.
     *
     * @param varName Overrides the name used for the temporary variable resource. Default
     * value is the name of the 'TemporaryVariable' op (which is guaranteed unique).
     * @return this Options instance.
     */
    public Options varName(String varName) {
      this.varName = varName;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = TemporaryVariable.class
  )
  public static class Inputs extends RawOpInputs<TemporaryVariable<?>> {
    /**
     * The shape of the variable tensor.
     */
    public final Shape shape;

    /**
     * The type of elements in the variable tensor.
     */
    public final DataType dtype;

    /**
     * Overrides the name used for the temporary variable resource. Default
     * value is the name of the 'TemporaryVariable' op (which is guaranteed unique).
     */
    public final String varName;

    public Inputs(GraphOperation op) {
      super(new TemporaryVariable<>(op), op, Arrays.asList("shape", "dtype", "var_name"));
      int inputIndex = 0;
      shape = op.attributes().getAttrShape("shape");
      dtype = op.attributes().getAttrType("dtype");
      varName = op.attributes().getAttrString("var_name");
    }
  }
}
