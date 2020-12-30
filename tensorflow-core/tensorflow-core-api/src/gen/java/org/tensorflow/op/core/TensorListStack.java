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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Stacks all tensors in the list.
 * <p>
 * Requires that all tensors have the same shape.
 * <p>
 * input_handle: the input list
 * tensor: the gathered result
 * num_elements: optional. If not -1, the number of elements in the list.
 * 
 * 
 * @param <T> data type for {@code tensor()} output
 */
@Operator
public final class TensorListStack<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorListStack}
   */
  public static class Options {
    
    /**
     * @param numElements 
     */
    public Options numElements(Long numElements) {
      this.numElements = numElements;
      return this;
    }
    
    private Long numElements;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new TensorListStack operation.
   * 
   * @param scope current scope
   * @param inputHandle 
   * @param elementShape 
   * @param elementDtype 
   * @param options carries optional attributes values
   * @return a new instance of TensorListStack
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TensorListStack<T> create(Scope scope, Operand<?> inputHandle, Operand<TInt32> elementShape, Class<T> elementDtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListStack", scope.makeOpName("TensorListStack"));
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(elementShape.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("element_dtype", Operands.toDataType(elementDtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.numElements != null) {
          opBuilder.setAttr("num_elements", opts.numElements);
        }
      }
    }
    return new TensorListStack<T>(opBuilder.build());
  }
  
  /**
   * @param numElements 
   */
  public static Options numElements(Long numElements) {
    return new Options().numElements(numElements);
  }
  
  /**
   */
  public Output<T> tensor() {
    return tensor;
  }
  
  @Override
  public Output<T> asOutput() {
    return tensor;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorListStack";
  
  private Output<T> tensor;
  
  private TensorListStack(Operation operation) {
    super(operation);
    int outputIdx = 0;
    tensor = operation.output(outputIdx++);
  }
}
