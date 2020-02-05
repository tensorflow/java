/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

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

import org.tensorflow.Operation;
import org.tensorflow.Tensor;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.NdArrays;
import org.tensorflow.tools.ndarray.StdArrays;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TType;

/**
 * An operator producing a matrix (rank-2 or higher) constant value.
 */
@Operator
public final class Matrix<T extends TType> extends Constant<T> {

  /**
   * Creates a rank-2 constant of {@code int} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TInt32} constant matrix
   */
  public static Matrix<TInt32> create(Scope scope, int[][] data) {
    try (Tensor<TInt32> value = TInt32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-3 constant of {@code int} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TInt32} constant matrix
   */
  public static Matrix<TInt32> create(Scope scope, int[][][] data) {
    try (Tensor<TInt32> value = TInt32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code int} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TInt32} constant matrix
   */
  public static Matrix<TInt32> create(Scope scope, int[][][][] data) {
    try (Tensor<TInt32> value = TInt32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code int} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TInt32} constant matrix
   */
  public static Matrix<TInt32> create(Scope scope, int[][][][][] data) {
    try (Tensor<TInt32> value = TInt32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code int} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TInt32} constant matrix
   */
  public static Matrix<TInt32> create(Scope scope, int[][][][][][] data) {
    try (Tensor<TInt32> value = TInt32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-2 constant of {@code float} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TFloat32} constant matrix
   */
  public static Matrix<TFloat32> create(Scope scope, float[][] data) {
    try (Tensor<TFloat32> value = TFloat32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-3 constant of {@code float} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TFloat32} constant matrix
   */
  public static Matrix<TFloat32> create(Scope scope, float[][][] data) {
    try (Tensor<TFloat32> value = TFloat32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code float} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TFloat32} constant matrix
   */
  public static Matrix<TFloat32> create(Scope scope, float[][][][] data) {
    try (Tensor<TFloat32> value = TFloat32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code float} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TFloat32} constant matrix
   */
  public static Matrix<TFloat32> create(Scope scope, float[][][][][] data) {
    try (Tensor<TFloat32> value = TFloat32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code float} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TFloat32} constant matrix
   */
  public static Matrix<TFloat32> create(Scope scope, float[][][][][][] data) {
    try (Tensor<TFloat32> value = TFloat32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-2 constant of {@code double} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TFloat64} constant matrix
   */
  public static Matrix<TFloat64> create(Scope scope, double[][] data) {
    try (Tensor<TFloat64> value = TFloat64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-3 constant of {@code double} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TFloat64} constant matrix
   */
  public static Matrix<TFloat64> create(Scope scope, double[][][] data) {
    try (Tensor<TFloat64> value = TFloat64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code double} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TFloat64} constant matrix
   */
  public static Matrix<TFloat64> create(Scope scope, double[][][][] data) {
    try (Tensor<TFloat64> value = TFloat64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code double} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TFloat64} constant matrix
   */
  public static Matrix<TFloat64> create(Scope scope, double[][][][][] data) {
    try (Tensor<TFloat64> value = TFloat64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code double} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TFloat64} constant matrix
   */
  public static Matrix<TFloat64> create(Scope scope, double[][][][][][] data) {
    try (Tensor<TFloat64> value = TFloat64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-2 constant of {@code long} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TInt64} constant matrix
   */
  public static Matrix<TInt64> create(Scope scope, long[][] data) {
    try (Tensor<TInt64> value = TInt64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-3 constant of {@code long} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TInt64} constant matrix
   */
  public static Matrix<TInt64> create(Scope scope, long[][][] data) {
    try (Tensor<TInt64> value = TInt64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code long} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TInt64} constant matrix
   */
  public static Matrix<TInt64> create(Scope scope, long[][][][] data) {
    try (Tensor<TInt64> value = TInt64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code long} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TInt64} constant matrix
   */
  public static Matrix<TInt64> create(Scope scope, long[][][][][] data) {
    try (Tensor<TInt64> value = TInt64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code long} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TInt64} constant matrix
   */
  public static Matrix<TInt64> create(Scope scope, long[][][][][][] data) {
    try (Tensor<TInt64> value = TInt64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-2 constant of {@code boolean} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TBool} constant matrix
   */
  public static Matrix<TBool> create(Scope scope, boolean[][] data) {
    try (Tensor<TBool> value = TBool.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-3 constant of {@code boolean} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TBool} constant matrix
   */
  public static Matrix<TBool> create(Scope scope, boolean[][][] data) {
    try (Tensor<TBool> value = TBool.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code boolean} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TBool} constant matrix
   */
  public static Matrix<TBool> create(Scope scope, boolean[][][][] data) {
    try (Tensor<TBool> value = TBool.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code boolean} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TBool} constant matrix
   */
  public static Matrix<TBool> create(Scope scope, boolean[][][][][] data) {
    try (Tensor<TBool> value = TBool.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code boolean} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TBool} constant matrix
   */
  public static Matrix<TBool> create(Scope scope, boolean[][][][][][] data) {
    try (Tensor<TBool> value = TBool.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-2 constant of {@code byte} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TUint8} constant matrix
   */
  public static Matrix<TUint8> create(Scope scope, byte[][] data) {
    try (Tensor<TUint8> value = TUint8.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-3 constant of {@code byte} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TUint8} constant matrix
   */
  public static Matrix<TUint8> create(Scope scope, byte[][][] data) {
    try (Tensor<TUint8> value = TUint8.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code byte} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TUint8} constant matrix
   */
  public static Matrix<TUint8> create(Scope scope, byte[][][][] data) {
    try (Tensor<TUint8> value = TUint8.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code byte} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TUint8} constant matrix
   */
  public static Matrix<TUint8> create(Scope scope, byte[][][][][] data) {
    try (Tensor<TUint8> value = TUint8.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code byte} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a {@link TUint8} constant matrix
   */
  public static Matrix<TUint8> create(Scope scope, byte[][][][][][] data) {
    try (Tensor<TUint8> value = TUint8.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(t, data))) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-1 constant of {@code String} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a {@link TString} constant matrix
   */
  public static Matrix<TString> create(Scope scope, String[] data) {
    NdArray<String> src = NdArrays.vectorOfObjects(data);
    try (Tensor<TString> value = TString.tensorOf(src)) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-2 constant of {@code String} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a {@link TString} constant matrix
   */
  public static Matrix<TString> create(Scope scope, String[][] data) {
    NdArray<String> src = NdArrays.ofObjects(String.class, StdArrays.shapeOf(data));
    StdArrays.copyTo(src, data);
    try (Tensor<TString> value = TString.tensorOf(src)) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-3 constant of {@code String} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a {@link TString} constant matrix
   */
  public static Matrix<TString> create(Scope scope, String[][][] data) {
    NdArray<String> src = NdArrays.ofObjects(String.class, StdArrays.shapeOf(data));
    StdArrays.copyTo(src, data);
    try (Tensor<TString> value = TString.tensorOf(src)) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code String} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a {@link TString} constant matrix
   */
  public static Matrix<TString> create(Scope scope, String[][][][] data) {
    NdArray<String> src = NdArrays.ofObjects(String.class, StdArrays.shapeOf(data));
    StdArrays.copyTo(src, data);
    try (Tensor<TString> value = TString.tensorOf(src)) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code String} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a {@link TString} constant matrix
   */
  public static Matrix<TString> create(Scope scope, String[][][][][] data) {
    NdArray<String> src = NdArrays.ofObjects(String.class, StdArrays.shapeOf(data));
    StdArrays.copyTo(src, data);
    try (Tensor<TString> value = TString.tensorOf(src)) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code String} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a {@link TString} constant matrix
   */
  public static Matrix<TString> create(Scope scope, String[][][][][][] data) {
    NdArray<String> src = NdArrays.ofObjects(String.class, StdArrays.shapeOf(data));
    StdArrays.copyTo(src, data);
    try (Tensor<TString> value = TString.tensorOf(src)) {
      return createFromTensor(scope, value);
    }
  }

  /**
   * Create a constant from a Tensor.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param tensor a Tensor holding the constant value
   * @return a constant matrix of the same data type as `tensor`
   */
  private static <T extends TType> Matrix<T> createFromTensor(Scope scope, Tensor<T> tensor) {
    return new Matrix<>(buildConstOp(scope, tensor));
  }

  private Matrix(Operation operation) {
    super(operation);
  }
}
