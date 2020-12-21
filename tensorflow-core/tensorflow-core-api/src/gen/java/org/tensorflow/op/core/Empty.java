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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Creates a tensor with the given shape.
 * <p>
 * This operation creates a tensor of `shape` and `dtype`.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class Empty<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Empty}
   */
  public static class Options {
    
    /**
     * @param init If True, initialize the returned tensor with the default value of dtype.  Otherwise, the implementation is free not to initializethe tensor's content.
     */
    public Options init(Boolean init) {
      this.init = init;
      return this;
    }
    
    private Boolean init;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Empty operation.
   * 
   * @param scope current scope
   * @param shape 1-D. Represents the shape of the output tensor.
   * @param dtype 
   * @param options carries optional attributes values
   * @return a new instance of Empty
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Empty<T> create(Scope scope, Operand<TInt32> shape, Class<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Empty", scope.makeOpName("Empty"));
    opBuilder.addInput(shape.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.init != null) {
          opBuilder.setAttr("init", opts.init);
        }
      }
    }
    return new Empty<T>(opBuilder.build());
  }
  
  /**
   * @param init If True, initialize the returned tensor with the default value of dtype.  Otherwise, the implementation is free not to initializethe tensor's content.
   */
  public static Options init(Boolean init) {
    return new Options().init(init);
  }
  
  /**
   * A `Tensor` of type `T`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Empty";
  
  private Output<T> output;
  
  private Empty(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
