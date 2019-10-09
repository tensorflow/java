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
import org.tensorflow.nio.nd.Shape;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;

/**
 * Concats all tensors in the list along the 0th dimension.
 * <p>
 * Requires that all tensors have the same shape except the first dimension.
 * <p>
 * input_handle: The input list.
 * tensor: The concated result.
 * lengths: Output tensor containing sizes of the 0th dimension of tensors in the list, used for computing the gradient.
 * 
 * 
 * @param <T> data type for {@code tensor()} output
 */
@Operator
public final class TensorListConcat<T> extends PrimitiveOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorListConcat}
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
   * Factory method to create a class wrapping a new TensorListConcat operation.
   * 
   * @param scope current scope
   * @param inputHandle 
   * @param elementDtype 
   * @param options carries optional attributes values
   * @return a new instance of TensorListConcat
   */
  public static <T> TensorListConcat<T> create(Scope scope, Operand<?> inputHandle, DataType<T> elementDtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListConcat", scope.makeOpName("TensorListConcat"));
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    opBuilder.setAttr("element_dtype", elementDtype);
    if (options != null) {
      for (Options opts : options) {
        if (opts.elementShape != null) {
          opBuilder.setAttr("element_shape", opts.elementShape);
        }
      }
    }
    return new TensorListConcat<T>(opBuilder.build());
  }
  
  /**
   * @param elementShape 
   */
  public static Options elementShape(Shape elementShape) {
    return new Options().elementShape(elementShape);
  }
  
  /**
   */
  public Output<T> tensor() {
    return tensor;
  }
  
  /**
   */
  public Output<TInt64> lengths() {
    return lengths;
  }
  
  private Output<T> tensor;
  private Output<TInt64> lengths;
  
  private TensorListConcat(Operation operation) {
    super(operation);
    int outputIdx = 0;
    tensor = operation.output(outputIdx++);
    lengths = operation.output(outputIdx++);
  }
}
