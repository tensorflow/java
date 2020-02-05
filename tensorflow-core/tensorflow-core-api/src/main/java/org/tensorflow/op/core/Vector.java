/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */

package org.tensorflow.op.core;

import java.nio.charset.Charset;
import org.tensorflow.Operation;
import org.tensorflow.Tensor;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.tools.Shape;
import org.tensorflow.tools.ndarray.NdArrays;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TType;

/**
 * An operator producing a vector (rank-1) constant value.
 */
@Operator
public final class Vector<T extends TType> extends Constant<T> {

  /**
   * Creates a constant of {@code int} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return the vector constant
   */
  public static Vector<TInt32> create(Scope scope, int... data) {
    try (Tensor<TInt32> value = TInt32.vectorOf(data)) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a constant of {@code float} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return the vector constant
   */
  public static Vector<TFloat32> create(Scope scope, float... data) {
    try (Tensor<TFloat32> value = TFloat32.vectorOf(data)) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a constant of {@code double} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return the vector constant
   */
  public static Vector<TFloat64> create(Scope scope, double... data) {
    try (Tensor<TFloat64> value = TFloat64.vectorOf(data)) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a constant of {@code long} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return the vector constant
   */
  public static Vector<TInt64> create(Scope scope, long... data) {
    try (Tensor<TInt64> value = TInt64.vectorOf(data)) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a constant of {@code boolean} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return the vector constant
   */
  public static Vector<TBool> create(Scope scope, boolean... data) {
    try (Tensor<TBool> value = TBool.vectorOf(data)) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a constant of {@code byte} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return the vector constant
   */
  public static Vector<TUint8> create(Scope scope, byte... data) {
    try (Tensor<TUint8> value = TUint8.vectorOf(data)) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a constant of {@code String} elements, using the default UTF-8 charset.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return the vector constant
   */
  public static Vector<TString> create(Scope scope, String... data) {
    try (Tensor<TString> value = TString.vectorOf(data)) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a constant of {@code String} elements, using the given charset.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param charset charset for encoding/decoding strings bytes.
   * @param data An array containing the values to put into the new constant. String elements are
   *     sequences of bytes from the last array dimension.
   * @return the vector constant
   */
  public static Vector<TString> create(Scope scope, Charset charset, String... data) {
    try (Tensor<TString> value = TString.tensorOf(charset, NdArrays.vectorOfObjects(data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a constant of {@link TInt64} elements representing the size of each dimension of the
   * given {@code shape}.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param shape the shape to serialize as a constant array
   * @return the vector constant
   * @throws IllegalArgumentException if the shape is unknown
   */
  public static Vector<TInt64> create(Scope scope, Shape shape) {
    long[] dims = shape.asArray();
    if (dims == null) {
      throw new IllegalArgumentException();
    }
    try (Tensor<TInt64> tensor = TInt64.vectorOf(dims)) {
      return createFromTensor(scope, tensor);
    }
  }

  private static <T extends TType> Vector<T> createFromTensor(Scope scope, Tensor<T> tensor) {
    return new Vector<>(buildConstOp(scope, tensor));
  }

  private Vector(Operation operation) {
    super(operation);
  }
}
