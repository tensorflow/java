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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * @param <T> data type for {@code value()} output
 */
@Operator
public final class TensorArrayPack<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorArrayPack}
   */
  public static class Options {
    
    /**
     * @param elementShape 
     */
    public Options elementShape(Shape elementShape) {
      this.elementShape = elementShape;
      return this;
    }
    
    private Shape elementShape;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new TensorArrayPack operation.
   * 
   * @param scope current scope
   * @param handle 
   * @param flowIn 
   * @param dtype 
   * @param options carries optional attributes values
   * @return a new instance of TensorArrayPack
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TensorArrayPack<T> create(Scope scope, Operand<TString> handle, Operand<TFloat32> flowIn, DataType<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorArrayPack", scope.makeOpName("TensorArrayPack"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(flowIn.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", dtype);
    if (options != null) {
      for (Options opts : options) {
        if (opts.elementShape != null) {
          opBuilder.setAttr("element_shape", opts.elementShape);
        }
      }
    }
    return new TensorArrayPack<T>(opBuilder.build());
  }
  
  /**
   * @param elementShape 
   */
  public static Options elementShape(Shape elementShape) {
    return new Options().elementShape(elementShape);
  }
  
  /**
   */
  public Output<T> value() {
    return value;
  }
  
  @Override
  public Output<T> asOutput() {
    return value;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorArrayPack";
  
  private Output<T> value;
  
  private TensorArrayPack(Operation operation) {
    super(operation);
    int outputIdx = 0;
    value = operation.output(outputIdx++);
  }
}
