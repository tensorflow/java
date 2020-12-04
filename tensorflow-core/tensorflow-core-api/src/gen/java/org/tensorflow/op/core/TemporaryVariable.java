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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Returns a tensor that may be mutated, but only persists within a single step.
 * <p>
 * This is an experimental op for internal use only and it is possible to use this
 * op in unsafe ways.  DO NOT USE unless you fully understand the risks.
 * <p>
 * It is the caller's responsibility to ensure that 'ref' is eventually passed to a
 * matching 'DestroyTemporaryVariable' op after all other uses have completed.
 * <p>
 * Outputs a ref to the tensor state so it may be read or modified.
 * <p>
 *   E.g.
 *       var = state_ops._temporary_variable([1, 2], types.float_)
 *       var_name = var.op.name
 *       var = state_ops.assign(var, [[4.0, 5.0]])
 *       var = state_ops.assign_add(var, [[6.0, 7.0]])
 *       final = state_ops._destroy_temporary_variable(var, var_name=var_name)
 * 
 * @param <T> data type for {@code ref()} output
 */
@Operator
public final class TemporaryVariable<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.TemporaryVariable}
   */
  public static class Options {
    
    /**
     * @param varName Overrides the name used for the temporary variable resource. Default
     * value is the name of the 'TemporaryVariable' op (which is guaranteed unique).
     */
    public Options varName(String varName) {
      this.varName = varName;
      return this;
    }
    
    private String varName;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new TemporaryVariable operation.
   * 
   * @param scope current scope
   * @param shape The shape of the variable tensor.
   * @param dtype The type of elements in the variable tensor.
   * @param options carries optional attributes values
   * @return a new instance of TemporaryVariable
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TemporaryVariable<T> create(Scope scope, Shape shape, DataType<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TemporaryVariable", scope.makeOpName("TemporaryVariable"));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("shape", shape);
    opBuilder.setAttr("dtype", dtype);
    if (options != null) {
      for (Options opts : options) {
        if (opts.varName != null) {
          opBuilder.setAttr("var_name", opts.varName);
        }
      }
    }
    return new TemporaryVariable<T>(opBuilder.build());
  }
  
  /**
   * @param varName Overrides the name used for the temporary variable resource. Default
   * value is the name of the 'TemporaryVariable' op (which is guaranteed unique).
   */
  public static Options varName(String varName) {
    return new Options().varName(varName);
  }
  
  /**
   * A reference to the variable tensor.
   */
  public Output<T> ref() {
    return ref;
  }
  
  @Override
  public Output<T> asOutput() {
    return ref;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TemporaryVariable";
  
  private Output<T> ref;
  
  private TemporaryVariable(Operation operation) {
    super(operation);
    int outputIdx = 0;
    ref = operation.output(outputIdx++);
  }
}
