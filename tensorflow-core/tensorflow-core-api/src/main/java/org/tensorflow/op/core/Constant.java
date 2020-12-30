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

import java.nio.charset.Charset;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.Output;
import org.tensorflow.Tensor;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.BooleanNdArray;
import org.tensorflow.ndarray.ByteNdArray;
import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TType;

/**
 * An operator producing a constant value.
 *
 * <p>All endpoints of this operator are named `constant`, except those accepting vararg
 * elements in parameter, which are named `array`. For example:
 *
 * <pre>{@code
 * Ops tf = Ops.create();
 * tf.constant(1.0f);  // mapped to Constant.scalarOf(scope, float);
 * tf.constant(new float[] {1.0f, 2.0f});  // mapped to Constant.vectorOf(scope, float[])
 * tf.constant(new float[][] { {1.0f, 2.0f}, {3.0f, 4.0f} });  //mapped to Constant.tensorOf(scope, float[][])
 * tf.array(1.0f, 2.0f, 3.0f);  // mapped to Constant.arrayOf(scope, float...)
 * }</pre>
 */
@Operator
public final class Constant<T extends TType> extends RawOp implements Operand<T> {

  /**
   * Creates a constant containing a single {@code int} element.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The value to put into the new constant.
   * @return an integer constant
   */
  @Endpoint
  public static Constant<TInt32> scalarOf(Scope scope, int data) {
    try (TInt32 value = TInt32.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-1 constant of {@code int} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return an integer constant
   */
  @Endpoint
  public static Constant<TInt32> vectorOf(Scope scope, int[] data) {
    try (TInt32 value = TInt32.vectorOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code int} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a float constant
   */
  @Endpoint(name = "array")
  public static Constant<TInt32> arrayOf(Scope scope, int... data) {
    if (data == null) {
      throw new IllegalArgumentException("data cannot be null");
    }
    return vectorOf(scope, data);
  }

  /**
   * Creates a rank-2 constant of {@code int} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return an integer constant
   */
  @Endpoint
  public static Constant<TInt32> tensorOf(Scope scope, int[][] data) {
    try (TInt32 value = TInt32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-3 constant of {@code int} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return an integer constant
   */
  @Endpoint
  public static Constant<TInt32> tensorOf(Scope scope, int[][][] data) {
    try (TInt32 value = TInt32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code int} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return an integer constant
   */
  @Endpoint
  public static Constant<TInt32> tensorOf(Scope scope, int[][][][] data) {
    try (TInt32 value = TInt32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code int} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return an integer constant
   */
  @Endpoint
  public static Constant<TInt32> tensorOf(Scope scope, int[][][][][] data) {
    try (TInt32 value = TInt32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code int} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return an integer constant
   */
  @Endpoint
  public static Constant<TInt32> tensorOf(Scope scope, int[][][][][][] data) {
    try (TInt32 value = TInt32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code int} elements that is a copy of a given n-dimensional array.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data an n-dimensional array of {@code int} elements.
   * @return an integer constant
   */
  @Endpoint
  public static Constant<TInt32> tensorOf(Scope scope, IntNdArray data) {
    if (data instanceof TInt32) {
      return create(scope, (TInt32) data);
    }
    try (TInt32 value = TInt32.tensorOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Create a {@link TInt32} constant with data from the given buffer.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return an integer constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  @Endpoint
  public static Constant<TInt32> tensorOf(Scope scope, Shape shape, IntDataBuffer data) {
    try (TInt32 value = TInt32.tensorOf(shape, data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant containing a single {@code float} element.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The value to put into the new constant.
   * @return a float constant
   */
  @Endpoint
  public static Constant<TFloat32> scalarOf(Scope scope, float data) {
    try (TFloat32 value = TFloat32.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-1 constant of {@code float} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a float constant
   */
  @Endpoint
  public static Constant<TFloat32> vectorOf(Scope scope, float[] data) {
    try (TFloat32 value = TFloat32.vectorOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code float} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a float constant
   */
  @Endpoint(name = "array")
  public static Constant<TFloat32> arrayOf(Scope scope, float... data) {
    if (data == null) {
      throw new IllegalArgumentException("data cannot be null");
    }
    return vectorOf(scope, data);
  }

  /**
   * Creates a rank-2 constant of {@code float} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a float constant
   */
  @Endpoint
  public static Constant<TFloat32> tensorOf(Scope scope, float[][] data) {
    try (TFloat32 value = TFloat32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(
        data, t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-3 constant of {@code float} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a float constant
   */
  @Endpoint
  public static Constant<TFloat32> tensorOf(Scope scope, float[][][] data) {
    try (TFloat32 value = TFloat32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(
        data, t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code float} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a float constant
   */
  @Endpoint
  public static Constant<TFloat32> tensorOf(Scope scope, float[][][][] data) {
    try (TFloat32 value = TFloat32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(
        data, t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code float} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a float constant
   */
  @Endpoint
  public static Constant<TFloat32> tensorOf(Scope scope, float[][][][][] data) {
    try (TFloat32 value = TFloat32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(
        data, t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code float} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a float constant
   */
  @Endpoint
  public static Constant<TFloat32> tensorOf(Scope scope, float[][][][][][] data) {
    try (TFloat32 value = TFloat32.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(
        data, t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code float} elements that is a copy of a given n-dimensional array.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data an n-dimensional array of {@code float} elements.
   * @return a float constant
   */
  @Endpoint
  public static Constant<TFloat32> tensorOf(Scope scope, FloatNdArray data) {
    if (data instanceof TFloat32) {
      return create(scope, (TFloat32) data);
    }
    try (TFloat32 value = TFloat32.tensorOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Create a {@link TFloat32} constant with data from the given buffer.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a float constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  @Endpoint
  public static Constant<TFloat32> tensorOf(Scope scope, Shape shape, FloatDataBuffer data) {
    try (TFloat32 value = TFloat32.tensorOf(shape, data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant containing a single {@code double} element.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The value to put into the new constant.
   * @return a double constant
   */
  @Endpoint
  public static Constant<TFloat64> scalarOf(Scope scope, double data) {
    try (TFloat64 value = TFloat64.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-1 constant of {@code double} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a double constant
   */
  @Endpoint
  public static Constant<TFloat64> vectorOf(Scope scope, double[] data) {
    try (TFloat64 value = TFloat64.vectorOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code double} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a double constant
   */
  @Endpoint(name = "array")
  public static Constant<TFloat64> arrayOf(Scope scope, double... data) {
    if (data == null) {
      throw new IllegalArgumentException("data cannot be null");
    }
    return vectorOf(scope, data);
  }

  /**
   * Creates a rank-2 constant of {@code double} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a double constant
   */
  @Endpoint
  public static Constant<TFloat64> tensorOf(Scope scope, double[][] data) {
    try (TFloat64 value = TFloat64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(
        data, t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-3 constant of {@code double} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a double constant
   */
  @Endpoint
  public static Constant<TFloat64> tensorOf(Scope scope, double[][][] data) {
    try (TFloat64 value = TFloat64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(
        data, t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code double} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a double constant
   */
  @Endpoint
  public static Constant<TFloat64> tensorOf(Scope scope, double[][][][] data) {
    try (TFloat64 value = TFloat64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(
        data, t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code double} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a double constant
   */
  @Endpoint
  public static Constant<TFloat64> tensorOf(Scope scope, double[][][][][] data) {
    try (TFloat64 value = TFloat64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(
        data, t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code double} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a double constant
   */
  @Endpoint
  public static Constant<TFloat64> tensorOf(Scope scope, double[][][][][][] data) {
    try (TFloat64 value = TFloat64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(
        data, t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code double} elements that is a copy of a given n-dimensional array.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data an n-dimensional array of {@code double} elements.
   * @return a double constant
   */
  @Endpoint
  public static Constant<TFloat64> tensorOf(Scope scope, DoubleNdArray data) {
    if (data instanceof TFloat64) {
      return create(scope, (TFloat64) data);
    }
    try (TFloat64 value = TFloat64.tensorOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Create a {@link TFloat64} constant with data from the given buffer.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a double constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  @Endpoint
  public static Constant<TFloat64> tensorOf(Scope scope, Shape shape, DoubleDataBuffer data) {
    try (TFloat64 value = TFloat64.tensorOf(shape, data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant containing a single {@code long} element.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The value to put into the new constant.
   * @return a long constant
   */
  @Endpoint
  public static Constant<TInt64> scalarOf(Scope scope, long data) {
    try (TInt64 value = TInt64.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-1 constant of {@code long} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a long constant
   */
  @Endpoint
  public static Constant<TInt64> vectorOf(Scope scope, long[] data) {
    try (TInt64 value = TInt64.vectorOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-2 constant of {@code long} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a long constant
   */
  @Endpoint
  public static Constant<TInt64> tensorOf(Scope scope, long[][] data) {
    try (TInt64 value = TInt64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code long} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a long constant
   */
  @Endpoint(name = "array")
  public static Constant<TInt64> arrayOf(Scope scope, long... data) {
    if (data == null) {
      throw new IllegalArgumentException("data cannot be null");
    }
    return vectorOf(scope, data);
  }

  /**
   * Creates a rank-3 constant of {@code long} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a long constant
   */
  @Endpoint
  public static Constant<TInt64> tensorOf(Scope scope, long[][][] data) {
    try (TInt64 value = TInt64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code long} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a long constant
   */
  @Endpoint
  public static Constant<TInt64> tensorOf(Scope scope, long[][][][] data) {
    try (TInt64 value = TInt64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code long} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a long constant
   */
  @Endpoint
  public static Constant<TInt64> tensorOf(Scope scope, long[][][][][] data) {
    try (TInt64 value = TInt64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code long} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a long constant
   */
  @Endpoint
  public static Constant<TInt64> tensorOf(Scope scope, long[][][][][][] data) {
    try (TInt64 value = TInt64.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code long} elements that is a copy of a given n-dimensional array.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data an n-dimensional array of {@code long} elements.
   * @return a long constant
   */
  @Endpoint
  public static Constant<TInt64> tensorOf(Scope scope, LongNdArray data) {
    if (data instanceof TInt64) {
      return create(scope, (TInt64) data);
    }
    try (TInt64 value = TInt64.tensorOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Create a {@link TInt64} constant with data from the given buffer.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a long constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  @Endpoint
  public static Constant<TInt64> tensorOf(Scope scope, Shape shape, LongDataBuffer data) {
    try (TInt64 value = TInt64.tensorOf(shape, data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant containing a single {@code boolean} element.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The value to put into the new constant.
   * @return a boolean constant
   */
  @Endpoint
  public static Constant<TBool> scalarOf(Scope scope, boolean data) {
    try (TBool value = TBool.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-1 constant of {@code boolean} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a boolean constant
   */
  @Endpoint
  public static Constant<TBool> vectorOf(Scope scope, boolean[] data) {
    try (TBool value = TBool.vectorOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code boolean} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a boolean constant
   */
  @Endpoint(name = "array")
  public static Constant<TBool> arrayOf(Scope scope, boolean... data) {
    if (data == null) {
      throw new IllegalArgumentException("data cannot be null");
    }
    return vectorOf(scope, data);
  }

  /**
   * Creates a rank-2 constant of {@code boolean} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a boolean constant
   */
  @Endpoint
  public static Constant<TBool> tensorOf(Scope scope, boolean[][] data) {
    try (TBool value = TBool.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-3 constant of {@code boolean} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a boolean constant
   */
  @Endpoint
  public static Constant<TBool> tensorOf(Scope scope, boolean[][][] data) {
    try (TBool value = TBool.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code boolean} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a boolean constant
   */
  @Endpoint
  public static Constant<TBool> tensorOf(Scope scope, boolean[][][][] data) {
    try (TBool value = TBool.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code boolean} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a boolean constant
   */
  @Endpoint
  public static Constant<TBool> tensorOf(Scope scope, boolean[][][][][] data) {
    try (TBool value = TBool.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code boolean} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a boolean constant
   */
  @Endpoint
  public static Constant<TBool> tensorOf(Scope scope, boolean[][][][][][] data) {
    try (TBool value = TBool.tensorOf(StdArrays.shapeOf(data), t -> StdArrays.copyTo(data,
        t))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code boolean} elements that is a copy of a given n-dimensional array.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data an n-dimensional array of {@code boolean} elements.
   * @return a boolean constant
   */
  @Endpoint
  public static Constant<TBool> tensorOf(Scope scope, BooleanNdArray data) {
    if (data instanceof TBool) {
      return create(scope, (TBool) data);
    }
    try (TBool value = TBool.tensorOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Create a {@link TBool} constant with data from the given buffer.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return an boolean constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  @Endpoint
  public static Constant<TBool> tensorOf(Scope scope, Shape shape, BooleanDataBuffer data) {
    try (TBool value = TBool.tensorOf(shape, data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant containing a single {@code byte} element.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The value to put into the new constant.
   * @return a byte constant
   */
  @Endpoint
  public static Constant<TUint8> scalarOf(Scope scope, byte data) {
    try (TUint8 value = TUint8.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-1 constant of {@code byte} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a byte constant
   */
  @Endpoint
  public static Constant<TUint8> vectorOf(Scope scope, byte[] data) {
    try (TUint8 value = TUint8.vectorOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code byte} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a byte constant
   */
  @Endpoint(name = "array")
  public static Constant<TUint8> arrayOf(Scope scope, byte... data) {
    if (data == null) {
      throw new IllegalArgumentException("data cannot be null");
    }
    return vectorOf(scope, data);
  }

  /**
   * Creates a rank-2 constant of {@code byte} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a byte constant
   */
  @Endpoint
  public static Constant<TUint8> tensorOf(Scope scope, byte[][] data) {
    try (TUint8 value = TUint8.tensorOf(StdArrays.shapeOf(data), d -> StdArrays.copyTo(data,
        d))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-3 constant of {@code byte} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a byte constant
   */
  @Endpoint
  public static Constant<TUint8> tensorOf(Scope scope, byte[][][] data) {
    try (TUint8 value = TUint8.tensorOf(StdArrays.shapeOf(data), d -> StdArrays.copyTo(data,
        d))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code byte} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a byte constant
   */
  @Endpoint
  public static Constant<TUint8> tensorOf(Scope scope, byte[][][][] data) {
    try (TUint8 value = TUint8.tensorOf(StdArrays.shapeOf(data), d -> StdArrays.copyTo(data,
        d))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code byte} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a byte constant
   */
  @Endpoint
  public static Constant<TUint8> tensorOf(Scope scope, byte[][][][][] data) {
    try (TUint8 value = TUint8.tensorOf(StdArrays.shapeOf(data), d -> StdArrays.copyTo(data,
        d))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code byte} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *     new constant will match those of the array.
   * @return a byte constant
   */
  @Endpoint
  public static Constant<TUint8> tensorOf(Scope scope, byte[][][][][][] data) {
    try (TUint8 value = TUint8.tensorOf(StdArrays.shapeOf(data), d -> StdArrays.copyTo(data,
        d))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code byte} elements that is a copy of a given n-dimensional array.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data an n-dimensional array of {@code byte} elements.
   * @return a byte constant
   */
  @Endpoint
  public static Constant<TUint8> tensorOf(Scope scope, ByteNdArray data) {
    if (data instanceof TUint8) {
      return create(scope, (TUint8) data);
    }
    try (TUint8 value = TUint8.tensorOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Create a {@link TUint8} constant with data from the given buffer.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a byte constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  @Endpoint
  public static Constant<TUint8> tensorOf(Scope scope, Shape shape, ByteDataBuffer data) {
    try (TUint8 value = TUint8.tensorOf(shape, data)) {
      return create(scope, value);
    }
  }

  /**
   * Create a constant with data from the given buffer.
   *
   * @param <T> the tensor type
   * @param scope is a scope used to add the underlying operation.
   * @param type the tensor type class
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a constant of type `type`
   * @throws IllegalArgumentException If the tensor datatype or shape is not compatible with the
   *     buffer
   */
  @Endpoint
  public static <T extends TType> Constant<T> tensorOf(Scope scope, Class<T> type, Shape shape,
      ByteDataBuffer data) {
    try (T value = Tensor.of(type, shape, data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a {@code String} constant using the default, UTF-8 encoding.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data The string to put into the new constant.
   * @return a string constant
   */
  @Endpoint
  public static Constant<TString> scalarOf(Scope scope, String data) {
    try (TString value = TString.scalarOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a {@code String} constant using a specified encoding.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param charset The encoding from String to bytes.
   * @param data The string to put into the new constant.
   * @return a string constant
   */
  @Endpoint
  public static Constant<TString> scalarOf(Scope scope, Charset charset, String data) {
    try (TString value = TString.tensorOf(charset, NdArrays.scalarOfObject(data))) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-1 constant of {@code String} elements.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a {@link TString} constant matrix
   */
  public static Constant<TString> vectorOf(Scope scope, String[] data) {
    NdArray<String> src = NdArrays.vectorOfObjects(data);
    try (TString value = TString.tensorOf(src)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code String} elements, using the given charset.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param charset charset for encoding/decoding strings bytes.
   * @param data An array containing the values to put into the new constant. String elements are
   *     sequences of bytes from the last array dimension.
   * @return the {@code String} constant
   */
  @Endpoint
  public static Constant<TString> vectorOf(Scope scope, Charset charset, String[] data) {
    try (TString value = TString.tensorOf(charset, NdArrays.vectorOfObjects(data))) {
      return Constant.create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code String} elements, using the default UTF-8 charset.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return the {@code String} constant
   */
  @Endpoint(name = "array")
  public static Constant<TString> arrayOf(Scope scope, String... data) {
    if (data == null) {
      throw new IllegalArgumentException("data cannot be null");
    }
    return vectorOf(scope, data);
  }

  /**
   * Creates a constant of {@code String} elements, using the given charset.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param charset charset for encoding/decoding strings bytes.
   * @param data An array containing the values to put into the new constant. String elements are
   *     sequences of bytes from the last array dimension.
   * @return the {@code String} constant
   */
  @Endpoint(name = "array")
  public static Constant<TString> arrayOf(Scope scope, Charset charset, String... data) {
    if (data == null) {
      throw new IllegalArgumentException("data cannot be null");
    }
    return vectorOf(scope, charset, data);
  }

  /**
   * Creates a rank-2 constant of {@code String} elements, using default UTF-8 encoding.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a {@link TString} constant matrix
   */
  public static Constant<TString> tensorOf(Scope scope, String[][] data) {
    NdArray<String> src = NdArrays.ofObjects(String.class, StdArrays.shapeOf(data));
    StdArrays.copyTo(data, src);
    try (TString value = TString.tensorOf(src)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-3 constant of {@code String} elements, using default UTF-8 encoding.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a {@link TString} constant matrix
   */
  public static Constant<TString> tensorOf(Scope scope, String[][][] data) {
    NdArray<String> src = NdArrays.ofObjects(String.class, StdArrays.shapeOf(data));
    StdArrays.copyTo(data, src);
    try (TString value = TString.tensorOf(src)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-4 constant of {@code String} elements, using default UTF-8 encoding.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a {@link TString} constant matrix
   */
  public static Constant<TString> tensorOf(Scope scope, String[][][][] data) {
    NdArray<String> src = NdArrays.ofObjects(String.class, StdArrays.shapeOf(data));
    StdArrays.copyTo(data, src);
    try (TString value = TString.tensorOf(src)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-5 constant of {@code String} elements, using default UTF-8 encoding.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a {@link TString} constant matrix
   */
  public static Constant<TString> tensorOf(Scope scope, String[][][][][] data) {
    NdArray<String> src = NdArrays.ofObjects(String.class, StdArrays.shapeOf(data));
    StdArrays.copyTo(data, src);
    try (TString value = TString.tensorOf(src)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-6 constant of {@code String} elements, using default UTF-8 encoding.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data An array containing the values to put into the new constant.
   * @return a {@link TString} constant matrix
   */
  public static Constant<TString> tensorOf(Scope scope, String[][][][][][] data) {
    NdArray<String> src = NdArrays.ofObjects(String.class, StdArrays.shapeOf(data));
    StdArrays.copyTo(data, src);
    try (TString value = TString.tensorOf(src)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code String} elements that is a copy of a given n-dimensional array,
   * using the default UTF-8 encoding.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param data an n-dimensional array of {@code String} elements.
   * @return a string constant
   */
  @Endpoint
  public static Constant<TString> tensorOf(Scope scope, NdArray<String> data) {
    if (data instanceof TString) {
      return create(scope, (TString) data);
    }
    try (TString value = TString.tensorOf(data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a constant of {@code String} elements that is a copy of a given n-dimensional array,
   * using the given encoding.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param charset charset used to encode/decode string bytes.
   * @param data an n-dimensional array of {@code String} elements.
   * @return a string constant
   */
  @Endpoint
  public static Constant<TString> tensorOf(Scope scope, Charset charset, NdArray<String> data) {
    try (TString value = TString.tensorOf(charset, data)) {
      return create(scope, value);
    }
  }

  /**
   * Create a {@link TString} constant with data from the given buffer, using the default UTF-8
   * encoding.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a string constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  @Endpoint
  public static Constant<TString> tensorOf(Scope scope, Shape shape, DataBuffer<String> data) {
    try (TString value = TString.tensorOf(shape, data)) {
      return create(scope, value);
    }
  }

  /**
   * Create a {@link TString} constant with data from the given buffer, using the given encoding.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param charset charset used to encode/decode string bytes.
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a string constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  @Endpoint
  public static Constant<TString> tensorOf(Scope scope, Charset charset, Shape shape,
      DataBuffer<String> data) {
    try (TString value = TString.tensorOf(charset, shape, data)) {
      return create(scope, value);
    }
  }

  /**
   * Creates a rank-1 constant of {@code long} elements representing the size of each dimensions of
   * the given shape.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param shape a shape
   * @return a long constant
   */
  @Endpoint
  public static Constant<TInt64> tensorOf(Scope scope, Shape shape) {
    return vectorOf(scope, shape.asArray());
  }

  /**
   * Create a constant by making an immutable copy of {@code tensor}.
   *
   * <p>Note: this endpoint cannot be simply called {@code constant} since it will conflict with
   * other endpoints accepting an NdArray in parameter {e.g. {@link #tensorOf(Scope, FloatNdArray)}}.
   *
   * @param scope is a scope used to add the underlying operation.
   * @param tensor a Tensor holding the constant value
   * @return a constant of the same data type as `tensor`
   */
  @Endpoint(name = "constantOf")
  public static <T extends TType> Constant<T> create(Scope scope, T tensor) {
    return new Constant<>(
        scope
            .env()
            .opBuilder("Const", scope.makeOpName("Const"))
            .setAttr("value", tensor)
            .setAttr("dtype", tensor.dataType())
            .build());
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  private Constant(Operation operation) {
    super(operation);
    output = operation.output(0);
  }

  private final Output<T> output;
}
