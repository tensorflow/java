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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Concats all tensors in the list along the 0th dimension.
 * <p>
 * Requires that all tensors have the same shape except the first dimension.
 * <p>
 * input_handle: The input list.
 * element_shape: The shape of the uninitialized elements in the list. If the first
 *   dimension is not -1, it is assumed that all list elements have the same
 *   leading dim.
 * leading_dims: The list of leading dims of uninitialized list elements. Used if
 *   the leading dim of input_handle.element_shape or the element_shape input arg
 *   is not already set.
 * tensor: The concated result.
 * lengths: Output tensor containing sizes of the 0th dimension of tensors in the list, used for computing the gradient.
 * 
 * 
 * @param <U> data type for {@code tensor()} output
 */
@Operator
public final class TensorListConcat<U extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new TensorListConcat operation.
   * 
   * @param scope current scope
   * @param inputHandle 
   * @param elementShape 
   * @param leadingDims 
   * @param elementDtype 
   * @return a new instance of TensorListConcat
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TNumber> TensorListConcat<U> create(Scope scope, Operand<?> inputHandle, Operand<T> elementShape, Operand<TInt64> leadingDims, Class<U> elementDtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorListConcatV2", scope.makeOpName("TensorListConcat"));
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(elementShape.asOutput());
    opBuilder.addInput(leadingDims.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("element_dtype", Operands.toDataType(elementDtype));
    return new TensorListConcat<U>(opBuilder.build());
  }
  
  /**
   */
  public Output<U> tensor() {
    return tensor;
  }
  
  /**
   */
  public Output<TInt64> lengths() {
    return lengths;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorListConcatV2";
  
  private Output<U> tensor;
  private Output<TInt64> lengths;
  
  private TensorListConcat(Operation operation) {
    super(operation);
    int outputIdx = 0;
    tensor = operation.output(outputIdx++);
    lengths = operation.output(outputIdx++);
  }
}
