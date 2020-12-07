/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.Output;
import org.tensorflow.op.Op;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An operator creating a constant initialized with ones of the shape given by `dims`.
 * 
 * <p>For example, the following expression
 * <pre>{@code tf.ones(tf.constant(shape), TFloat32.DTYPE)}</pre>
 * is the equivalent of
 * <pre>{@code tf.fill(tf.constant(shape), tf.constant(1.0f))}</pre>
 *
 * @param <T> constant type
 */
@Operator
public final class Ones<T extends TType> implements Op, Operand<T> {

  /**
   * Creates a one valued tensor given its type and shape.
   *
   * @param scope is a scope used to add the underlying operation
   * @param dims a 1-D operand that represents the shape of the output tensor
   * @param type the output tensor datatype. Can not be TString.
   * @return a constant tensor initialized with ones
   * @throws IllegalArgumentException if the tensor type or shape cannot be initialized with ones.
   */
  @Endpoint
  public static <T extends TType, U extends TNumber> Ones<T> create(Scope scope, Operand<U> dims, DataType<T> type) {
    Scope onesScope = scope.withSubScope("Ones");
    if (type == TString.DTYPE) {
      throw new IllegalArgumentException("Can't create Ones of String DataType");
    }
    Operand<T> one = Cast.create(onesScope.withName("One"), Constant.scalarOf(onesScope, 1), type);
    return new Ones<>(Fill.create(onesScope.withName("Fill"), dims, one));
  }

  @Override
  public Operation op() {
    return fill.op();
  }

  @Override
  public Output<T> asOutput() {
    return fill.asOutput();
  }

  private final Fill<T> fill;

  private Ones(Fill<T> fill) {
    this.fill = fill;
  }
}
