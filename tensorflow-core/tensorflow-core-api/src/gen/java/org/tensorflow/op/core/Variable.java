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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Holds state in the form of a tensor that persists across steps.
 * <p>
 * Outputs a ref to the tensor state so it may be read or modified.
 * TODO(zhifengc/mrry): Adds a pointer to a more detail document
 * about sharing states in tensorflow.
 * 
 * @param <T> data type for {@code ref()} output
 */
@Operator
public final class Variable<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Variable}
   */
  public static class Options {
    
    /**
     * @param container If non-empty, this variable is placed in the given container.
     * Otherwise, a default container is used.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }
    
    /**
     * @param sharedName If non-empty, this variable is named in the given bucket
     * with this shared_name. Otherwise, the node name is used instead.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
    
    private String container;
    private String sharedName;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Variable operation.
   * 
   * @param scope current scope
   * @param shape The shape of the variable tensor.
   * @param dtype The type of elements in the variable tensor.
   * @param options carries optional attributes values
   * @return a new instance of Variable
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Variable<T> create(Scope scope, Shape shape, Class<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("VariableV2", scope.makeOpName("Variable"));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("shape", shape);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
      }
    }
    return new Variable<T>(opBuilder.build());
  }
  
  /**
   * @param container If non-empty, this variable is placed in the given container.
   * Otherwise, a default container is used.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }
  
  /**
   * @param sharedName If non-empty, this variable is named in the given bucket
   * with this shared_name. Otherwise, the node name is used instead.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
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
  public static final String OP_NAME = "VariableV2";
  
  private Output<T> ref;
  
  private Variable(Operation operation) {
    super(operation);
    int outputIdx = 0;
    ref = operation.output(outputIdx++);
  }
}
