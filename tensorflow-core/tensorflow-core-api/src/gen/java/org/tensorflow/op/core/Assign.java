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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Update 'ref' by assigning 'value' to it.
 * <p>
 * This operation outputs "ref" after the assignment is done.
 * This makes it easier to chain operations that need to use the reset value.
 * 
 * @param <T> data type for {@code outputRef()} output
 */
@Operator
public final class Assign<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Assign}
   */
  public static class Options {
    
    /**
     * @param validateShape If true, the operation will validate that the shape
     * of 'value' matches the shape of the Tensor being assigned to.  If false,
     * 'ref' will take on the shape of 'value'.
     */
    public Options validateShape(Boolean validateShape) {
      this.validateShape = validateShape;
      return this;
    }
    
    /**
     * @param useLocking If True, the assignment will be protected by a lock;
     * otherwise the behavior is undefined, but may exhibit less contention.
     */
    public Options useLocking(Boolean useLocking) {
      this.useLocking = useLocking;
      return this;
    }
    
    private Boolean validateShape;
    private Boolean useLocking;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Assign operation.
   * 
   * @param scope current scope
   * @param ref Should be from a `Variable` node. May be uninitialized.
   * @param value The value to be assigned to the variable.
   * @param options carries optional attributes values
   * @return a new instance of Assign
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Assign<T> create(Scope scope, Operand<T> ref, Operand<T> value, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Assign", scope.makeOpName("Assign"));
    opBuilder.addInput(ref.asOutput());
    opBuilder.addInput(value.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.validateShape != null) {
          opBuilder.setAttr("validate_shape", opts.validateShape);
        }
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new Assign<T>(opBuilder.build());
  }
  
  /**
   * @param validateShape If true, the operation will validate that the shape
   * of 'value' matches the shape of the Tensor being assigned to.  If false,
   * 'ref' will take on the shape of 'value'.
   */
  public static Options validateShape(Boolean validateShape) {
    return new Options().validateShape(validateShape);
  }
  
  /**
   * @param useLocking If True, the assignment will be protected by a lock;
   * otherwise the behavior is undefined, but may exhibit less contention.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }
  
  /**
   * = Same as "ref".  Returned as a convenience for operations that want
   * to use the new value after the variable has been reset.
   */
  public Output<T> outputRef() {
    return outputRef;
  }
  
  @Override
  public Output<T> asOutput() {
    return outputRef;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Assign";
  
  private Output<T> outputRef;
  
  private Assign(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputRef = operation.output(outputIdx++);
  }
}
