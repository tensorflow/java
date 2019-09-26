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

package org.tensorflow.op.dtypes;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;

/**
 * Cast x of type SrcT to y of DstT.
 * 
 * @param <U> data type for {@code y()} output
 */
@Operator(group = "dtypes")
public final class Cast<U> extends PrimitiveOp implements Operand<U> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.dtypes.Cast}
   */
  public static class Options {
    
    /**
     * @param Truncate 
     */
    public Options Truncate(Boolean Truncate) {
      this.Truncate = Truncate;
      return this;
    }
    
    private Boolean Truncate;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Cast operation.
   * 
   * @param scope current scope
   * @param x 
   * @param DstT 
   * @param options carries optional attributes values
   * @return a new instance of Cast
   */
  public static <U, T> Cast<U> create(Scope scope, Operand<T> x, Class<U> DstT, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Cast", scope.makeOpName("Cast"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    opBuilder.setAttr("DstT", DataType.fromClass(DstT));
    if (options != null) {
      for (Options opts : options) {
        if (opts.Truncate != null) {
          opBuilder.setAttr("Truncate", opts.Truncate);
        }
      }
    }
    return new Cast<U>(opBuilder.build());
  }
  
  /**
   * @param Truncate 
   */
  public static Options Truncate(Boolean Truncate) {
    return new Options().Truncate(Truncate);
  }
  
  /**
   */
  public Output<U> y() {
    return y;
  }
  
  @Override
  public Output<U> asOutput() {
    return y;
  }
  
  private Output<U> y;
  
  private Cast(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }
}
