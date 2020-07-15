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

import org.tensorflow.ndarray.impl.dense.DenseNdArray;
import org.tensorflow.ndarray.impl.dense.IntDenseNdArray;
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
import org.tensorflow.ndarray.impl.dense.DoubleDenseNdArray;
import org.tensorflow.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.ndarray.impl.dense.LongDenseNdArray;
import org.tensorflow.ndarray.impl.dense.ShortDenseNdArray;

/**
 * Utility class for instantiating {@link NdArray} objects.
 */
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
   * <p>Modifying the data of the returned vector will also impact the values in the array
   * passed in parameter.
   *
   * @param values vector values
   * @return new byte vector
   * @throws IllegalArgumentException if values is null
   */
  public static ByteNdArray vectorOf(byte... values) {
    if (values == null) {
      throw new IllegalArgumentException("Values cannot be null");
    }
    return wrap(DataBuffers.of(values, false, false), Shape.of(values.length));
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
    return wrap(DataBuffers.ofBytes(shape.size()), shape);
  }

  /**
   * Wraps a buffer in a byte N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new byte N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static ByteNdArray wrap(ByteDataBuffer buffer, Shape shape) {
    return ByteDenseNdArray.create(buffer, shape);
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
   * <p>Modifying the data of the returned vector will also impact the values in the array
   * passed in parameter.
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
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static LongNdArray wrap(Shape shape, LongDataBuffer buffer) {
    return LongDenseNdArray.create(buffer, shape);
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
   * <p>Modifying the data of the returned vector will also impact the values in the array
   * passed in parameter.
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
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static IntNdArray wrap(Shape shape, IntDataBuffer buffer) {
    return IntDenseNdArray.create(buffer, shape);
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
   * <p>Modifying the data of the returned vector will also impact the values in the array
   * passed in parameter.
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
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static ShortNdArray wrap(Shape shape, ShortDataBuffer buffer) {
    return ShortDenseNdArray.create(buffer, shape);
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
   * <p>Modifying the data of the returned vector will also impact the values in the array
   * passed in parameter.
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
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static FloatNdArray wrap(Shape shape, FloatDataBuffer buffer) {
    return FloatDenseNdArray.create(buffer, shape);
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
   * <p>Modifying the data of the returned vector will also impact the values in the array
   * passed in parameter.
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
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static DoubleNdArray wrap(Shape shape, DoubleDataBuffer buffer) {
    return DoubleDenseNdArray.create(buffer, shape);
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
   * <p>Modifying the data of the returned vector will also impact the values in the array
   * passed in parameter.
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
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static BooleanNdArray wrap(Shape shape, BooleanDataBuffer buffer) {
    return BooleanDenseNdArray.create(buffer, shape);
  }

  // OBJECT ARRAYS

  /**
   * Creates scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new scalar
   */
  @SuppressWarnings("unchecked")
  public static <T> NdArray<T> scalarOfObject(T value) {
    if (value == null) {
      throw new IllegalArgumentException();
    }
    return ofObjects((Class<T>)value.getClass(), Shape.scalar()).setObject(value);
  }

  /**
   * Creates a vector (rank 1) initialized with the given values.
   *
   * <p>Modifying the data of the returned vector will also impact the values in the array
   * passed in parameter.
   *
   * @param values vector values
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
   * @return new N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static <T> NdArray<T> wrap(Shape shape, DataBuffer<T> buffer) {
    return DenseNdArray.wrap(buffer, shape);
  }
}

