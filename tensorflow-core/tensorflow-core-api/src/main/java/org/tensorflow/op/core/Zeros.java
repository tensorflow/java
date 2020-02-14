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
==============================================================================*/
package org.tensorflow.op.core;

import java.nio.ByteBuffer;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An operator creating a constant initialized with zeros of the shape given by `dims`.
 * 
 * <p>For example, the following expression
 * <pre>{@code tf.zeros(tf.val(shape), TFloat32.DTYPE)</pre>
 * is the equivalent of
 * <pre>{@code tf.fill(tf.val(shape), tf.val(0.0f))</pre>
 *
 * @param <T> constant type
 */
@Operator
public final class Zeros<T extends TType> implements Op, Operand<T> {

  /**
   * Creates a zeroed tensor given its type and shape.
   *
   * @param scope is a scope used to add the underlying operation
   * @param dims a 1-D operand that represents the shape of the output tensor
   * @param type the output tensor datatype
   * @return a constant tensor initialized with zeros
   * @throws IllegalArgumentException if the tensor type or shape cannot be initialized with zeros.
   */
  @Endpoint
  public static <T extends TType, U extends TNumber> Zeros<T> create(Scope scope, Operand<U> dims, DataType<T> type) {
    Scope childScope = scope.withSubScope("Zeros"); // If scope had an op name set, it will prevail on "Zeros"
    int zeroSize = type.byteSize();
    if (zeroSize < 0) {
      throw new IllegalArgumentException(type.name() + " tensors cannot be initialized with zeros");
    }
    Constant<T> zero = Constant.create(childScope.withName("Zero"), type, new long[]{}, ByteBuffer.allocate(zeroSize));
    return new Zeros<>(Fill.create(childScope, dims, zero));
  }

  @Override
  public Output<T> asOutput() {
    return fill.asOutput();
  }
  
  private final Fill<T> fill;
  
  private Zeros(Fill<T> fill) {
    this.fill = fill;
  }
}
