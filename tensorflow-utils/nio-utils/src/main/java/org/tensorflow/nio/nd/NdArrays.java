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
package org.tensorflow.nio.nd;

import org.tensorflow.nio.buffer.BooleanDataBuffer;
import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.DataBuffers;
import org.tensorflow.nio.buffer.DoubleDataBuffer;
import org.tensorflow.nio.buffer.FloatDataBuffer;
import org.tensorflow.nio.buffer.IntDataBuffer;
import org.tensorflow.nio.buffer.LongDataBuffer;
import org.tensorflow.nio.buffer.ShortDataBuffer;
import org.tensorflow.nio.nd.impl.dense.BooleanDenseNdArray;
import org.tensorflow.nio.nd.impl.dense.ByteDenseNdArray;
import org.tensorflow.nio.nd.impl.dense.DenseNdArray;
import org.tensorflow.nio.nd.impl.dense.DoubleDenseNdArray;
import org.tensorflow.nio.nd.impl.dense.FloatDenseNdArray;
import org.tensorflow.nio.nd.impl.dense.IntDenseNdArray;
import org.tensorflow.nio.nd.impl.dense.LongDenseNdArray;
import org.tensorflow.nio.nd.impl.dense.ShortDenseNdArray;

/**
 * Helper class for instantiating {@link NdArray} objects.
 */
public final class NdArrays {

  // BYTE ARRAYS

  /**
   * Creates byte scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new byte scalar
   */
  public static ByteNdArray scalar(byte value) {
    return ofBytes(Shape.scalar()).setByte(value);
  }

  /**
   * Creates a byte vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new byte vector
   * @throws IllegalArgumentException if values is null
   */
  public static ByteNdArray vector(byte... values) {
    if (values == null) {
      throw new IllegalArgumentException("Values cannot be null");
    }
    return ofBytes(Shape.make(values.length)).write(values);
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
  public static LongNdArray scalar(long value) {
    return ofLongs(Shape.scalar()).setLong(value);
  }

  /**
   * Creates a long vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new long vector
   * @throws IllegalArgumentException if values is null
   */
  public static LongNdArray vector(long... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return ofLongs(Shape.make(values.length)).write(values);
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
    return wrap(DataBuffers.ofLongs(shape.size()), shape);
  }

  /**
   * Wraps a buffer in a long N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new long N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static LongNdArray wrap(LongDataBuffer buffer, Shape shape) {
    return LongDenseNdArray.create(buffer, shape);
  }

  // INT ARRAYS

  /**
   * Creates long scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new long scalar
   */
  public static IntNdArray scalar(int value) {
    return ofInts(Shape.scalar()).setInt(value);
  }

  /**
   * Creates a int vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new int vector
   * @throws IllegalArgumentException if values is null
   */
  public static IntNdArray vector(int... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return ofInts(Shape.make(values.length)).write(values);
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
    return wrap(DataBuffers.ofInts(shape.size()), shape);
  }

  /**
   * Wraps a buffer in an int N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new int N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static IntNdArray wrap(IntDataBuffer buffer, Shape shape) {
    return IntDenseNdArray.create(buffer, shape);
  }

  // SHORT ARRAYS

  /**
   * Creates short scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new short scalar
   */
  public static ShortNdArray scalar(short value) {
    return ofShorts(Shape.scalar()).setShort(value);
  }

  /**
   * Creates a short vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new short vector
   * @throws IllegalArgumentException if values is null
   */
  public static ShortNdArray vector(short... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return ofShorts(Shape.make(values.length)).write(values);
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
    return wrap(DataBuffers.ofShorts(shape.size()), shape);
  }

  /**
   * Wraps a buffer in a short N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new short N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static ShortNdArray wrap(ShortDataBuffer buffer, Shape shape) {
    return ShortDenseNdArray.create(buffer, shape);
  }

  // FLOAT ARRAYS

  /**
   * Creates float scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new float scalar
   */
  public static FloatNdArray scalar(float value) {
    return ofFloats(Shape.scalar()).setFloat(value);
  }

  /**
   * Creates a float vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new float vector
   * @throws IllegalArgumentException if values is null
   */
  public static FloatNdArray vector(float... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return ofFloats(Shape.make(values.length)).write(values);
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
    return wrap(DataBuffers.ofFloats(shape.size()), shape);
  }

  /**
   * Wraps a buffer in a float N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new float N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static FloatNdArray wrap(FloatDataBuffer buffer, Shape shape) {
    return FloatDenseNdArray.create(buffer, shape);
  }

  // DOUBLE ARRAYS

  /**
   * Creates double scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new double scalar
   */
  public static DoubleNdArray scalar(double value) {
    return ofDoubles(Shape.scalar()).setDouble(value);
  }

  /**
   * Creates a double vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new double vector
   * @throws IllegalArgumentException if values is null
   */
  public static DoubleNdArray vector(double... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return ofDoubles(Shape.make(values.length)).write(values);
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
    return wrap(DataBuffers.ofDoubles(shape.size()), shape);
  }

  /**
   * Wraps a buffer in a double N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new double N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static DoubleNdArray wrap(DoubleDataBuffer buffer, Shape shape) {
    return DoubleDenseNdArray.create(buffer, shape);
  }

  // BOOLEAN ARRAYS

  /**
   * Creates boolean scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new boolean scalar
   */
  public static BooleanNdArray scalar(boolean value) {
    return ofBooleans(Shape.scalar()).setBoolean(value);
  }

  /**
   * Creates a boolean vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new boolean vector
   * @throws IllegalArgumentException if values is null
   */
  public static BooleanNdArray vector(boolean... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return ofBooleans(Shape.make(values.length)).write(values);
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
    return wrap(DataBuffers.ofBooleans(shape.size()), shape);
  }

  /**
   * Wraps a buffer in a boolean N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new boolean N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static BooleanNdArray wrap(BooleanDataBuffer buffer, Shape shape) {
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
  public static <T> NdArray<T> scalarOf(T value) {
    if (value == null) {
      throw new IllegalArgumentException();
    }
    return of((Class<T>)value.getClass(), Shape.scalar()).setValue(value);
  }

  /**
   * Creates a vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new vector
   * @throws IllegalArgumentException if values is null
   */
  @SuppressWarnings("unchecked")
  public static <T> NdArray<T> vectorOf(T... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return of((Class<T>)values[0].getClass(), Shape.make(values.length)).write(values);
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
  public static <T> NdArray<T> of(Class<T> clazz, Shape shape) {
    return wrap(DataBuffers.of(clazz, shape.size()), shape);
  }

  /**
   * Wraps a buffer in an N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  public static <T> NdArray<T> wrap(DataBuffer<T> buffer, Shape shape) {
    return DenseNdArray.wrap(buffer, shape);
  }
}

