/*
Copyright 2019 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================
*/
package org.tensorflow.ndarray;

import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.impl.dense.BooleanDenseNdArray;
import org.tensorflow.ndarray.impl.dense.ByteDenseNdArray;
import org.tensorflow.ndarray.impl.dense.DenseNdArray;
import org.tensorflow.ndarray.impl.dense.DoubleDenseNdArray;
import org.tensorflow.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.ndarray.impl.dense.IntDenseNdArray;
import org.tensorflow.ndarray.impl.dense.LongDenseNdArray;
import org.tensorflow.ndarray.impl.dense.ShortDenseNdArray;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.BooleanSparseNdArray;
import org.tensorflow.ndarray.impl.sparse.ByteSparseNdArray;
import org.tensorflow.ndarray.impl.sparse.DoubleSparseNdArray;
import org.tensorflow.ndarray.impl.sparse.FloatSparseNdArray;
import org.tensorflow.ndarray.impl.sparse.IntSparseNdArray;
import org.tensorflow.ndarray.impl.sparse.LongSparseNdArray;
import org.tensorflow.ndarray.impl.sparse.ShortSparseNdArray;

/** Utility class for instantiating {@link NdArray} objects. */
public final class NdArrays {

  // BYTE ARRAYS

  /**
   * Creates byte scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new byte scalar
   */
  public static ByteNdArray scalarOf(byte value) {
    return ofBytes(Shape.scalar()).setByte(value);
  }

  /**
   * Creates a byte vector (rank 1) initialized with the given values.
   *
   * <p>Modifying the data of the returned vector will also impact the values in the array passed in
   * parameter.
   *
   * @param values vector values
   * @return new byte vector
   * @throws IllegalArgumentException if values is null
   */
  public static ByteNdArray vectorOf(byte... values) {
    if (values == null) {
      throw new IllegalArgumentException("Values cannot be null");
    }
    return wrap(Shape.of(values.length), DataBuffers.of(values, false, false));
  }

  /**
   * Creates an N-dimensional array of bytes of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new byte N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  public static ByteNdArray ofBytes(Shape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    return wrap(shape, DataBuffers.ofBytes(shape.size()));
  }

  /**
   * Wraps a buffer in a byte N-dimensional array of a given shape.
   *
   * @param shape shape of the array
   * @param buffer buffer to wrap
   * @return new byte N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger in
   *     the buffer size
   */
  public static ByteNdArray wrap(Shape shape, ByteDataBuffer buffer) {
    return ByteDenseNdArray.create(buffer, shape);
  }

  /**
   * Creates a Sparse array of byte values with a default value of zero
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D ByteNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18, 3]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4,0]} of the NdArray has a value of {@code 3}.
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the byte sparse array.
   */
  public static ByteSparseNdArray sparseOf(LongNdArray indices, ByteNdArray values, Shape shape) {
    return ByteSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
  }

  /**
   * Creates a Sparse array of byte values
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non default values.
   * @param values A 1-D ByteNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18, 3]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4,0]} of the NdArray has a value of {@code 3}.
   * @param defaultValue Scalar value to set for indices not specified in 'indices'
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the byte sparse array.
   */
  public static ByteSparseNdArray sparseOf(
      LongNdArray indices, ByteNdArray values, byte defaultValue, Shape shape) {
    return ByteSparseNdArray.create(indices, values, defaultValue, DimensionalSpace.create(shape));
  }

  // LONG ARRAYS

  /**
   * Creates long scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new long scalar
   */
  public static LongNdArray scalarOf(long value) {
    return ofLongs(Shape.scalar()).setLong(value);
  }

  /**
   * Creates a long vector (rank 1) initialized with the given values.
   *
   * <p>Modifying the data of the returned vector will also impact the values in the array passed in
   * parameter.
   *
   * @param values vector values
   * @return new long vector
   * @throws IllegalArgumentException if values is null
   */
  public static LongNdArray vectorOf(long... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return wrap(Shape.of(values.length), DataBuffers.of(values, false, false));
  }

  /**
   * Creates an N-dimensional array of longs of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new long N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  public static LongNdArray ofLongs(Shape shape) {
    return wrap(shape, DataBuffers.ofLongs(shape.size()));
  }

  /**
   * Wraps a buffer in a long N-dimensional array of a given shape.
   *
   * @param shape shape of the array
   * @param buffer buffer to wrap
   * @return new long N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger in
   *     the buffer size
   */
  public static LongNdArray wrap(Shape shape, LongDataBuffer buffer) {
    return LongDenseNdArray.create(buffer, shape);
  }

  /**
   * Creates a Sparse array of long values with a default value of zero
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D LongNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18L, 3L]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value of
   *     {@code 18L}, and element {@code [2,4,0]} of the NdArray has a value of {@code 3L}.
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the long sparse array.
   */
  public static LongSparseNdArray sparseOf(LongNdArray indices, LongNdArray values, Shape shape) {
    return LongSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
  }

  /**
   * Creates a Sparse array of long values with a default value of zero
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D LongNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18L, 3L]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value of
   *     {@code 18L}, and element {@code [2,4,0]} of the NdArray has a value of {@code 3L}.
   * @param defaultValue Scalar value to set for indices not specified in 'indices'
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the long sparse array.
   */
  public static LongSparseNdArray sparseOf(
      LongNdArray indices, LongNdArray values, long defaultValue, Shape shape) {
    return LongSparseNdArray.create(indices, values, defaultValue, DimensionalSpace.create(shape));
  }

  // INT ARRAYS

  /**
   * Creates long scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new long scalar
   */
  public static IntNdArray scalarOf(int value) {
    return ofInts(Shape.scalar()).setInt(value);
  }

  /**
   * Creates a int vector (rank 1) initialized with the given values.
   *
   * <p>Modifying the data of the returned vector will also impact the values in the array passed in
   * parameter.
   *
   * @param values vector values
   * @return new int vector
   * @throws IllegalArgumentException if values is null
   */
  public static IntNdArray vectorOf(int... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return wrap(Shape.of(values.length), DataBuffers.of(values, false, false));
  }

  /**
   * Creates an N-dimensional array of ints of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new int N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  public static IntNdArray ofInts(Shape shape) {
    return wrap(shape, DataBuffers.ofInts(shape.size()));
  }

  /**
   * Wraps a buffer in an int N-dimensional array of a given shape.
   *
   * @param shape shape of the array
   * @param buffer buffer to wrap
   * @return new int N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger in
   *     the buffer size
   */
  public static IntNdArray wrap(Shape shape, IntDataBuffer buffer) {
    return IntDenseNdArray.create(buffer, shape);
  }

  /**
   * Creates a Sparse array of int values with a default value of zero.
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D IntNdArray of shape {@code [N]}, which supplies the values for each element
   *     in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18, 3]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4,0]} of the NdArray has a value of {@code 3}.
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the int sparse array.
   */
  public static IntSparseNdArray sparseOf(LongNdArray indices, IntNdArray values, Shape shape) {
    return IntSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
  }

  /**
   * Creates a Sparse array of int values
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D IntNdArray of shape {@code [N]}, which supplies the values for each element
   *     in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18, 3]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4,0]} of the NdArray has a value of {@code 3}.
   * @param defaultValue Scalar value to set for indices not specified in 'indices'
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the int sparse array.
   */
  public static IntSparseNdArray sparseOf(
      LongNdArray indices, IntNdArray values, int defaultValue, Shape shape) {
    return IntSparseNdArray.create(indices, values, defaultValue, DimensionalSpace.create(shape));
  }

  // SHORT ARRAYS

  /**
   * Creates short scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new short scalar
   */
  public static ShortNdArray scalarOf(short value) {
    return ofShorts(Shape.scalar()).setShort(value);
  }

  /**
   * Creates a short vector (rank 1) initialized with the given values.
   *
   * <p>Modifying the data of the returned vector will also impact the values in the array passed in
   * parameter.
   *
   * @param values vector values
   * @return new short vector
   * @throws IllegalArgumentException if values is null
   */
  public static ShortNdArray vectorOf(short... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return wrap(Shape.of(values.length), DataBuffers.of(values, false, false));
  }

  /**
   * Creates an N-dimensional array of shorts of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new short N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  public static ShortNdArray ofShorts(Shape shape) {
    return wrap(shape, DataBuffers.ofShorts(shape.size()));
  }

  /**
   * Wraps a buffer in a short N-dimensional array of a given shape.
   *
   * @param shape shape of the array
   * @param buffer buffer to wrap
   * @return new short N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger in
   *     the buffer size
   */
  public static ShortNdArray wrap(Shape shape, ShortDataBuffer buffer) {
    return ShortDenseNdArray.create(buffer, shape);
  }

  /**
   * Creates a Sparse array of short values with a default value of zero
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D ShortNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18, 3]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4,0]} of the NdArray has a value of {@code 3}.
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the short sparse array.
   */
  public static ShortSparseNdArray sparseOf(LongNdArray indices, ShortNdArray values, Shape shape) {
    return ShortSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
  }

  /**
   * Creates a Sparse array of short values
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D ShortNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18, 3]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4,0]} of the NdArray has a value of {@code 3}.
   * @param defaultValue Scalar value to set for indices not specified in 'indices'
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the short sparse array.
   */
  public static ShortSparseNdArray sparseOf(
      LongNdArray indices, ShortNdArray values, short defaultValue, Shape shape) {
    return ShortSparseNdArray.create(indices, values, defaultValue, DimensionalSpace.create(shape));
  }

  // FLOAT ARRAYS

  /**
   * Creates float scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new float scalar
   */
  public static FloatNdArray scalarOf(float value) {
    return ofFloats(Shape.scalar()).setFloat(value);
  }

  /**
   * Creates a float vector (rank 1) initialized with the given values.
   *
   * <p>Modifying the data of the returned vector will also impact the values in the array passed in
   * parameter.
   *
   * @param values vector values
   * @return new float vector
   * @throws IllegalArgumentException if values is null
   */
  public static FloatNdArray vectorOf(float... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return wrap(Shape.of(values.length), DataBuffers.of(values, false, false));
  }

  /**
   * Creates an N-dimensional array of floats of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new float N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  public static FloatNdArray ofFloats(Shape shape) {
    return wrap(shape, DataBuffers.ofFloats(shape.size()));
  }

  /**
   * Wraps a buffer in a float N-dimensional array of a given shape.
   *
   * @param shape shape of the array
   * @param buffer buffer to wrap
   * @return new float N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger in
   *     the buffer size
   */
  public static FloatNdArray wrap(Shape shape, FloatDataBuffer buffer) {
    return FloatDenseNdArray.create(buffer, shape);
  }

  /**
   * Creates a Sparse array of float values with a default value of zero
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D FloatNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18f, 3.8f]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value of
   *     {@code 18f}, and element {@code [2,4,0]} of the NdArray has a value of {@code 3.8f}.
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the float sparse array.
   */
  public static FloatSparseNdArray sparseOf(LongNdArray indices, FloatNdArray values, Shape shape) {
    return FloatSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
  }

  /**
   * Creates a Sparse array of float values
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D FloatNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18f, 3.8f]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value of
   *     {@code 18f}, and element {@code [2,4,0]} of the NdArray has a value of {@code 3.8f}.
   * @param defaultValue Scalar value to set for indices not specified in 'indices'
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the float sparse array.
   */
  public static FloatSparseNdArray sparseOf(
      LongNdArray indices, FloatNdArray values, float defaultValue, Shape shape) {
    return FloatSparseNdArray.create(indices, values, defaultValue, DimensionalSpace.create(shape));
  }

  // DOUBLE ARRAYS

  /**
   * Creates double scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new double scalar
   */
  public static DoubleNdArray scalarOf(double value) {
    return ofDoubles(Shape.scalar()).setDouble(value);
  }

  /**
   * Creates a double vector (rank 1) initialized with the given values.
   *
   * <p>Modifying the data of the returned vector will also impact the values in the array passed in
   * parameter.
   *
   * @param values vector values
   * @return new double vector
   * @throws IllegalArgumentException if values is null
   */
  public static DoubleNdArray vectorOf(double... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return wrap(Shape.of(values.length), DataBuffers.of(values, false, false));
  }

  /**
   * Creates an N-dimensional array of doubles of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new double N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  public static DoubleNdArray ofDoubles(Shape shape) {
    return wrap(shape, DataBuffers.ofDoubles(shape.size()));
  }

  /**
   * Wraps a buffer in a double N-dimensional array of a given shape.
   *
   * @param shape shape of the array
   * @param buffer buffer to wrap
   * @return new double N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger in
   *     the buffer size
   */
  public static DoubleNdArray wrap(Shape shape, DoubleDataBuffer buffer) {
    return DoubleDenseNdArray.create(buffer, shape);
  }

  /**
   * Creates a Sparse array of double values with a default value of zero
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D DoubleNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18, 3.8]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4,0]} of the NdArray has a value of {@code 3.8}.
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the float sparse array.
   */
  public static DoubleSparseNdArray sparseOf(
      LongNdArray indices, DoubleNdArray values, Shape shape) {
    return DoubleSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
  }

  /**
   * Creates a Sparse array of double values
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D DoubleNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[18, 3.8]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4,0]} of the NdArray has a value of {@code 3.8}.
   * @param defaultValue Scalar value to set for indices not specified in 'indices'
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the float sparse array.
   */
  public static DoubleSparseNdArray sparseOf(
      LongNdArray indices, DoubleNdArray values, double defaultValue, Shape shape) {
    return DoubleSparseNdArray.create(
        indices, values, defaultValue, DimensionalSpace.create(shape));
  }

  // BOOLEAN ARRAYS

  /**
   * Creates boolean scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new boolean scalar
   */
  public static BooleanNdArray scalarOf(boolean value) {
    return ofBooleans(Shape.scalar()).setBoolean(value);
  }

  /**
   * Creates a boolean vector (rank 1) initialized with the given values.
   *
   * <p>Modifying the data of the returned vector will also impact the values in the array passed in
   * parameter.
   *
   * @param values vector values
   * @return new boolean vector
   * @throws IllegalArgumentException if values is null
   */
  public static BooleanNdArray vectorOf(boolean... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return wrap(Shape.of(values.length), DataBuffers.of(values, false, false));
  }

  /**
   * Creates an N-dimensional array of booleans of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new boolean N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  public static BooleanNdArray ofBooleans(Shape shape) {
    return wrap(shape, DataBuffers.ofBooleans(shape.size()));
  }

  /**
   * Wraps a buffer in a boolean N-dimensional array of a given shape.
   *
   * @param shape shape of the array
   * @param buffer buffer to wrap
   * @return new boolean N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger in
   *     the buffer size
   */
  public static BooleanNdArray wrap(Shape shape, BooleanDataBuffer buffer) {
    return BooleanDenseNdArray.create(buffer, shape);
  }

  /**
   * Creates a Sparse array of boolean values with a default value of 'false'
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D BooleanNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[true, true]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value
   *     of true, and element {@code [2,4,0]} of the NdArray has a value of true. All other values are
   *     false.
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the float sparse array.
   */
  public static BooleanSparseNdArray sparseOf(
      LongNdArray indices, BooleanNdArray values, Shape shape) {
    return BooleanSparseNdArray.create(indices, values, DimensionalSpace.create(shape));
  }

  /**
   * Creates a Sparse array of boolean values
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D BooleanNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=[true, true]} specifies that element {@code [1,3,1]} of the sparse NdArray has a value
   *     of true, and element {@code [2,4,0]} of the NdArray has a value of true. All other values are
   *     false.
   * @param defaultValue Scalar value to set for indices not specified in 'indices'
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the float sparse array.
   */
  public static BooleanSparseNdArray sparseOf(
      LongNdArray indices, BooleanNdArray values, boolean defaultValue, Shape shape) {
    return BooleanSparseNdArray.create(
        indices, values, defaultValue, DimensionalSpace.create(shape));
  }

  // OBJECT ARRAYS

  /**
   * Creates scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @param <T> the data type
   * @return new scalar
   */
  @SuppressWarnings("unchecked")
  public static <T> NdArray<T> scalarOfObject(T value) {
    if (value == null) {
      throw new IllegalArgumentException();
    }
    return ofObjects((Class<T>) value.getClass(), Shape.scalar()).setObject(value);
  }

  /**
   * Creates a vector (rank 1) initialized with the given values.
   *
   * <p>Modifying the data of the returned vector will also impact the values in the array passed in
   * parameter.
   *
   * @param values vector values
   * @param <T> the data type
   * @return new vector
   * @throws IllegalArgumentException if values is null
   */
  @SafeVarargs
  public static <T> NdArray<T> vectorOfObjects(T... values) {
    if (values == null || values.length == 0) {
      throw new IllegalArgumentException("Null or zero length input supplied to vectorOfObjects.");
    }
    return wrap(Shape.of(values.length), DataBuffers.of(values, false, false));
  }

  /**
   * Creates an N-dimensional array of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param clazz class of the data to be stored in this array
   * @param shape shape of the array
   * @param <T> the data type
   * @return new N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  public static <T> NdArray<T> ofObjects(Class<T> clazz, Shape shape) {
    return wrap(shape, DataBuffers.ofObjects(clazz, shape.size()));
  }

  /**
   * Wraps a buffer in an N-dimensional array of a given shape.
   *
   * @param shape shape of the array
   * @param buffer buffer to wrap
   * @param <T> the data type
   * @return new N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger in
   *     the buffer size
   */
  public static <T> NdArray<T> wrap(Shape shape, DataBuffer<T> buffer) {
    return DenseNdArray.wrap(buffer, shape);
  }

  /**
   * Creates a Sparse array of values with a null default value
   *
   * @param type the class type represented by this sparse array.
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D NdArray of shape {@code [N]}, which supplies the values for each element in
   *     indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=["one", "two"]} specifies that element {@code [1,3,1]} of the sparse NdArray has a
   *     value of "one", and element {@code [2,4,0]} of the NdArray has a value of "two"". All other
   *     values are null.
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the float sparse array.
   */
  public static <T> NdArray<T> sparseOfObjects(
      Class<T> type, LongNdArray indices, NdArray<T> values, Shape shape) {
    return org.tensorflow.ndarray.impl.sparse.SparseNdArray.create(
        type, indices, values, DimensionalSpace.create(shape));
  }

  /**
   * Creates a Sparse array of values
   *
   * @param type the class type represented by this sparse array.
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3,1], [2,4,0]]} specifies that the elements with indexes of
   *     {@code [1,3,1]} and {@code [2,4,0]} have non-default values.
   * @param values A 1-D NdArray of shape {@code [N]}, which supplies the values for each element in
   *     indices. For example, given {@code indices=[[1,3,1], [2,4,0]]}, the parameter {@code
   *     values=["one", "two"]} specifies that element {@code [1,3,1]} of the sparse NdArray has a
   *     value of "one", and element {@code [2,4,0]} of the NdArray has a value of "two"". All other
   *     values are null.
   * @param defaultValue Scalar value to set for indices not specified in 'indices'
   * @param shape the shape of the dense array represented by this sparse array.
   * @return the float sparse array.
   */
  public static <T> NdArray<T> sparseOfObjects(
      Class<T> type, LongNdArray indices, NdArray<T> values, T defaultValue, Shape shape) {
    return org.tensorflow.ndarray.impl.sparse.SparseNdArray.create(
        type, indices, values, defaultValue, DimensionalSpace.create(shape));
  }
}
