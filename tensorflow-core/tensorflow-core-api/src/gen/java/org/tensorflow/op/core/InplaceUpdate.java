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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Updates specified rows 'i' with values 'v'.
 * <p>
 * Computes `x[i, :] = v; return x`.
 * <p>
 * Originally this function is mutative however for compilation we make this
 * operation create / operate on a copy of `x`.
 * 
 * @param <T> data type for {@code y()} output
 */
@Operator
public final class InplaceUpdate<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new InplaceUpdate operation.
   * 
   * @param scope current scope
   * @param x A tensor of type `T`.
   * @param i A vector. Indices into the left-most dimension of `x`.
   * @param v A `Tensor` of type T. Same dimension sizes as x except the first dimension, which must be the same as i's size.
   * @return a new instance of InplaceUpdate
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> InplaceUpdate<T> create(Scope scope, Operand<T> x, Operand<TInt32> i, Operand<T> v) {
    OperationBuilder opBuilder = scope.env().opBuilder("InplaceUpdate", scope.makeOpName("InplaceUpdate"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(i.asOutput());
    opBuilder.addInput(v.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new InplaceUpdate<T>(opBuilder.build());
  }
  
  /**
   * A `Tensor` of type T. An alias of `x`. The content of `y` is undefined if there are duplicates in `i`.
   */
  public Output<T> y() {
    return y;
  }
  
  @Override
  public Output<T> asOutput() {
    return y;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "InplaceUpdate";
  
  private Output<T> y;
  
  private InplaceUpdate(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }
}
