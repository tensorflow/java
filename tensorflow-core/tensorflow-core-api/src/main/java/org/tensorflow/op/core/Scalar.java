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
 * An operator producing a constant scalar value.
 */
@Operator
public final class Scalar<T extends TType> extends Const<T> {

  /**
   * Creates a constant containing a single {@code int} element.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The value to put into the new constant.
   * @return a scalar constant
   */
  public static Scalar<TInt32> create(Scope scope, int data) {
    try (Tensor<TInt32> value = TInt32.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant containing a single {@code float} element.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The value to put into the new constant.
   * @return a scalar constant
   */
  public static Scalar<TFloat32> create(Scope scope, float data) {
    try (Tensor<TFloat32> value = TFloat32.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant containing a single {@code double} element.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The value to put into the new constant.
   * @return a scalar constant
   */
  public static Scalar<TFloat64> create(Scope scope, double data) {
    try (Tensor<TFloat64> value = TFloat64.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant containing a single {@code long} element.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The value to put into the new constant.
   * @return a scalar constant
   */
  public static Scalar<TInt64> create(Scope scope, long data) {
    try (Tensor<TInt64> value = TInt64.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant containing a single {@code boolean} element.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The value to put into the new constant.
   * @return a scalar constant
   */
  public static Scalar<TBool> create(Scope scope, boolean data) {
    try (Tensor<TBool> value = TBool.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant containing a single {@code byte} element.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The value to put into the new constant.
   * @return a scalar constant
   */
  public static Scalar<TUint8> create(Scope scope, byte data) {
    try (Tensor<TUint8> value = TUint8.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a {@code String} constant using the default UTF-8 encoding.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The string to put into the new constant.
   * @return a scalar constant
   */
  public static Scalar<TString> create(Scope scope, String data) {
    try (Tensor<TString> value = TString.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a {@code String} constant using a specified encoding.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param charset The encoding from String to bytes.
   * @param data The string to put into the new constant.
   * @return a scalar constant
   */
  public static Scalar<TString> create(Scope scope, Charset charset, String data) {
    try (Tensor<TString> value = TString.tensorOf(charset, NdArrays.scalarOfObject(data))) {
      return create(scope, value);
    }
  }

  private static <T extends TType> Scalar<T> create(Scope scope, Tensor<T> tensor) {
    return new Scalar<>(buildConstOp(scope, tensor));
  }

  private Scalar(Operation operation) {
    super(operation);
  }
}
