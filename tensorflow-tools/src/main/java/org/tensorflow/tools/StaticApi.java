/*
 *  Copyright 2019 The TensorFlow Authors. All Rights Reserved.
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

package org.tensorflow.tools;

import org.tensorflow.tools.buffer.BooleanDataBuffer;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.DataBuffers;
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.ShortDataBuffer;
import org.tensorflow.tools.ndarray.BooleanNdArray;
import org.tensorflow.tools.ndarray.ByteNdArray;
import org.tensorflow.tools.ndarray.DoubleNdArray;
import org.tensorflow.tools.ndarray.FloatNdArray;
import org.tensorflow.tools.ndarray.IllegalRankException;
import org.tensorflow.tools.ndarray.IntNdArray;
import org.tensorflow.tools.ndarray.LongNdArray;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.NdArrays;
import org.tensorflow.tools.ndarray.ShortNdArray;
import org.tensorflow.tools.ndarray.index.Index;
import org.tensorflow.tools.ndarray.index.Indices;

/**
 * Helper class for instantiating NIO utility objects.
 */
public interface StaticApi {

  /**
   * Creates a buffer of objects of type {@code clazz} that can store up to {@code size} values
   *
   * @param clazz the type of object stored in this buffer
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  static <T> DataBuffer<T> bufferOfObjects(Class<T> clazz, long size) {
    return DataBuffers.ofObjects(clazz, size);
  }

  /**
   * Creates a buffer of bytes that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  static ByteDataBuffer bufferOfBytes(long size) {
    return DataBuffers.ofBytes(size);
  }

  /**
   * Creates a buffer of shorts that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  static ShortDataBuffer bufferOfShorts(long size) {
    return DataBuffers.ofShorts(size);
  }

  /**
   * Creates a buffer of ints that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  static IntDataBuffer bufferOfInts(long size) {
    return DataBuffers.ofInts(size);
  }

  /**
   * Creates a buffer of longs that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  static LongDataBuffer bufferOfLongs(long size) {
    return DataBuffers.ofLongs(size);
  }

  /**
   * Creates a buffer of floats that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  static FloatDataBuffer bufferOfFloats(long size) {
    return DataBuffers.ofFloats(size);
  }

  /**
   * Creates a buffer of doubles that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  static DoubleDataBuffer bufferOfDoubles(long size) {
    return DataBuffers.ofDoubles(size);
  }

  /**
   * Creates a buffer of booleans that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  static BooleanDataBuffer bufferOfBooleans(long size) {
    return DataBuffers.ofBooleans(size);
  }

  /**
   * Create a buffer from an array of floats into a data buffer.
   *
   * @param array array of floats
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static FloatDataBuffer bufferOfFloats(float[] array, boolean readOnly, boolean makeCopy) {
    return DataBuffers.from(array, readOnly, makeCopy);
  }

  /**
   * Create a buffer from an array of bytes into a data buffer.
   *
   * @param array array of bytes
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static ByteDataBuffer bufferOfBytes(byte[] array, boolean readOnly, boolean makeCopy) {
    return DataBuffers.from(array, readOnly, makeCopy);
  }

  /**
   * Create a buffer from an array of longs into a data buffer.
   *
   * @param array array of longs
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static LongDataBuffer bufferOfLongs(long[] array, boolean readOnly, boolean makeCopy) {
    return DataBuffers.from(array, readOnly, makeCopy);
  }

  /**
   * Create a buffer from an array of ints into a data buffer.
   *
   * @param array array of ints
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static IntDataBuffer bufferOfInts(int[] array, boolean readOnly, boolean makeCopy) {
    return DataBuffers.from(array, readOnly, makeCopy);
  }

  /**
   * Create a buffer from an array of shorts into a data buffer.
   *
   * @param array array of shorts
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static ShortDataBuffer bufferOfShorts(short[] array, boolean readOnly, boolean makeCopy) {
    return DataBuffers.from(array, readOnly, makeCopy);
  }

  /**
   * Create a buffer from an array of doubles into a data buffer.
   *
   * @param array array of doubles
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static DoubleDataBuffer bufferOfDoubles(double[] array, boolean readOnly, boolean makeCopy) {
    return DataBuffers.from(array, readOnly, makeCopy);
  }

  /**
   * Create a buffer from an array of booleans into a data buffer.
   *
   * @param array array of booleans
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static BooleanDataBuffer bufferOfBooleans(boolean[] array, boolean readOnly, boolean makeCopy) {
    return DataBuffers.from(array, readOnly, makeCopy);
  }

  /**
   * Create a buffer from an array of objects into a data buffer.
   *
   * @param array array of objects
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static <T> DataBuffer<T> bufferOfObjects(T[] array, boolean readOnly, boolean makeCopy) {
    return DataBuffers.from(array, readOnly, makeCopy);
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
  static <T> NdArray<T> ndArrayOfObjects(Class<T> clazz, Shape shape) {
    return NdArrays.ofObjects(clazz, shape);
  }

  /**
   * Creates a byte N-dimensional array of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new byte N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  static ByteNdArray ndArrayOfBytes(Shape shape) {
    return NdArrays.ofBytes(shape);
  }

  /**
   * Creates a short N-dimensional array of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new short N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  static ShortNdArray ndArrayOfShorts(Shape shape) {
    return NdArrays.ofShorts(shape);
  }

  /**
   * Creates an int N-dimensional array of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new int N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  static IntNdArray ndArrayOfInts(Shape shape) {
    return NdArrays.ofInts(shape);
  }

  /**
   * Creates a long N-dimensional array of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new long N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  static LongNdArray ndArrayOfLongs(Shape shape) {
    return NdArrays.ofLongs(shape);
  }

  /**
   * Creates a float N-dimensional array of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new float N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  static FloatNdArray ndArrayOfFloats(Shape shape) {
    return NdArrays.ofFloats(shape);
  }

  /**
   * Creates a double N-dimensional array of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new double N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  static DoubleNdArray ndArrayOfDoubles(Shape shape) {
    return NdArrays.ofDoubles(shape);
  }

  /**
   * Creates a boolean N-dimensional array of the given shape.
   *
   * <p>All values are initialized to zeros.
   *
   * @param shape shape of the array
   * @return new boolean N-dimensional array
   * @throws IllegalArgumentException if shape is null or has unknown dimensions
   */
  static BooleanNdArray ndArrayOfBooleans(Shape shape) {
    return NdArrays.ofBooleans(shape);
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
  static <T> NdArray<T> ndArrayOf(DataBuffer<T> buffer, Shape shape) {
    return NdArrays.wrap(buffer, shape);
  }

  /**
   * Wraps a byte buffer in an N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new byte N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  static ByteNdArray ndArrayOf(ByteDataBuffer buffer, Shape shape) {
    return NdArrays.wrap(buffer, shape);
  }

  /**
   * Wraps a short buffer in an N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new short N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  static ShortNdArray ndArrayOf(ShortDataBuffer buffer, Shape shape) {
    return NdArrays.wrap(buffer, shape);
  }

  /**
   * Wraps an int buffer in an N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new int N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  static IntNdArray ndArrayOf(IntDataBuffer buffer, Shape shape) {
    return NdArrays.wrap(buffer, shape);
  }

  /**
   * Wraps a long buffer in an N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new long N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  static LongNdArray ndArrayOf(LongDataBuffer buffer, Shape shape) {
    return NdArrays.wrap(buffer, shape);
  }

  /**
   * Wraps a float buffer in an N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new float N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  static FloatNdArray ndArrayOf(FloatDataBuffer buffer, Shape shape) {
    return NdArrays.wrap(buffer, shape);
  }

  /**
   * Wraps a double buffer in an N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new double N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  static DoubleNdArray ndArrayOf(DoubleDataBuffer buffer, Shape shape) {
    return NdArrays.wrap(buffer, shape);
  }

  /**
   * Wraps a boolean buffer in an N-dimensional array of a given shape.
   *
   * @param buffer buffer to wrap
   * @param shape shape of the array
   * @return new boolean N-dimensional array
   * @throws IllegalArgumentException if shape is null, has unknown dimensions or has size bigger
   *                                  in the buffer size
   */
  static BooleanNdArray ndArrayOf(BooleanDataBuffer buffer, Shape shape) {
    return NdArrays.wrap(buffer, shape);
  }

  /**
   * Creates scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new scalar
   */
  static <T> NdArray<T> scalarOfObject(T value) {
    return NdArrays.scalarOfObject(value);
  }

  /**
   * Creates byte scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new byte scalar
   */
  static ByteNdArray scalarOf(byte value) {
    return NdArrays.scalarOf(value);
  }

  /**
   * Creates short scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new short scalar
   */
  static ShortNdArray scalarOf(short value) {
    return NdArrays.scalarOf(value);
  }

  /**
   * Creates int scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new int scalar
   */
  static IntNdArray scalarOf(int value) {
    return NdArrays.scalarOf(value);
  }

  /**
   * Creates long scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new long scalar
   */
  static LongNdArray scalarOf(long value) {
    return NdArrays.scalarOf(value);
  }

  /**
   * Creates float scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new float scalar
   */
  static FloatNdArray scalarOf(float value) {
    return NdArrays.scalarOf(value);
  }

  /**
   * Creates double scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new double scalar
   */
  static DoubleNdArray scalarOf(double value) {
    return NdArrays.scalarOf(value);
  }

  /**
   * Creates boolean scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new boolean scalar
   */
  static BooleanNdArray scalarOf(boolean value) {
    return NdArrays.scalarOf(value);
  }

  /**
   * Creates a vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new vector
   * @throws IllegalArgumentException if values is null
   */
  @SafeVarargs
  static <T> NdArray<T> vectorOfObjects(T... values) {
    return NdArrays.vectorOfObjects(values);
  }

  /**
   * Creates a byte vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new byte vector
   * @throws IllegalArgumentException if values is null
   */
  static ByteNdArray vectorOf(byte... values) {
    return NdArrays.vectorOf(values);
  }

  /**
   * Creates a short vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new short vector
   * @throws IllegalArgumentException if values is null
   */
  static ShortNdArray vectorOf(short... values) {
    return NdArrays.vectorOf(values);
  }

  /**
   * Creates an int vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new int vector
   * @throws IllegalArgumentException if values is null
   */
  static IntNdArray vectorOf(int... values) {
    return NdArrays.vectorOf(values);
  }

  /**
   * Creates a long vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new long vector
   * @throws IllegalArgumentException if values is null
   */
  static LongNdArray vectorOf(long... values) {
    return NdArrays.vectorOf(values);
  }

  /**
   * Creates a float vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new float vector
   * @throws IllegalArgumentException if values is null
   */
  static FloatNdArray vectorOf(float... values) {
    return NdArrays.vectorOf(values);
  }

  /**
   * Creates a double vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new double vector
   * @throws IllegalArgumentException if values is null
   */
  static DoubleNdArray vectorOf(double... values) {
    return NdArrays.vectorOf(values);
  }

  /**
   * Creates a boolean vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new boolean vector
   * @throws IllegalArgumentException if values is null
   */
  static BooleanNdArray vectorOf(boolean... values) {
    return NdArrays.vectorOf(values);
  }

  /**
   * Create a Shape representing an N-dimensional value.
   *
   * @param dimensionSize size of each dimension in the shape, {@link Shape#UNKNOWN_SIZE} if unknown
   * @see Shape#of(long...)
   */
  static Shape shapeOf(long... dimensionSize) {
    return Shape.of(dimensionSize);
  }

  /**
   * A coordinate that selects a specific element on a given dimension.
   *
   * @param coord coordinate of the element on the indexed axis
   * @return index
   * @see Indices#at(long)
   */
  static Index at(long coord) {
    return Indices.at(coord);
  }

  /**
   * A coordinate that selects a specific element on a given dimension.
   *
   * @param coord scalar indicating the coordinate of the element on the indexed axis
   * @return index
   * @throws IllegalRankException if {@code coord} is not a scalar (rank 0)
   * @see Indices#at(NdArray)
   */
  static Index at(NdArray<? extends Number> coord) {
    return Indices.at(coord);
  }

  /**
   * An index that returns all elements of a dimension in the original order.
   *
   * @return index
   * @see Indices#all()
   */
  static Index all() {
    return Indices.all();
  }

  /**
   * An index that returns only specific elements on a given dimension.
   *
   * @param coords coordinate of the elements in the sequence
   * @return index
   * @see Indices#seq(long...)
   */
  static Index seq(long... coords) {
    return Indices.seq(coords);
  }

  /**
   * An index that returns only specific elements on a given dimension.
   *
   * @param coords vector of coordinates of the elements in the sequence
   * @return index
   * @throws IllegalRankException if {@code coord} is not a vector (rank 1)
   * @see Indices#seq(NdArray)
   */
  static Index seq(NdArray<? extends Number> coords) {
    return Indices.seq(coords);
  }

  /**
   * An index that returns only specific elements on a given dimension.
   *
   * @return index
   * @see Indices#even()
   */
  static Index even() {
    return Indices.even();
  }

  /**
   * An index that returns only elements found at an odd position in the
   * original dimension.
   *
   * @return index
   * @see Indices#odd()
   */
  static Index odd() {
    return Indices.odd();
  }

  /**
   * An index that skips a fixed amount of coordinates between each values returned.
   *
   * @param stepLength the number of elements between each steps
   * @return index
   * @see Indices#step(long)
   */
  static Index step(long stepLength) {
    return Indices.step(stepLength);
  }

  /**
   * An index that returns only elements on a given dimension starting at a
   * specific coordinate.
   *
   * @param start coordinate of the first element of the sequence
   * @return index
   * @see Indices#from(long)
   */
  static Index from(long start) {
    return Indices.from(start);
  }

  /**
   * An index that returns only elements on a given dimension up to a
   * specific coordinate.
   *
   * @param end coordinate of the last element of the sequence (exclusive)
   * @return index
   * @see Indices#to(long)
   */
  static Index to(long end) {
    return Indices.to(end);
  }

  /**
   * An index that returns only elements on a given dimension between two coordinates.
   *
   * @param start coordinate of the first element of the sequence
   * @param end coordinate of the last element of the sequence (exclusive)
   * @return index
   * @see Indices#range(long, long)
   */
  static Index range(long start, long end) {
    return Indices.range(start, end);
  }

  /**
   * An index that returns only elements on a given dimension between two coordinates.
   *
   * @return index
   * @see Indices#flip()
   */
  static Index flip() {
    return Indices.flip();
  }
}
