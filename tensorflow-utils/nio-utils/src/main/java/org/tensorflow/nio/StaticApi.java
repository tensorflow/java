package org.tensorflow.nio;

import org.tensorflow.nio.buffer.BooleanDataBuffer;
import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.DataBuffers;
import org.tensorflow.nio.buffer.DoubleDataBuffer;
import org.tensorflow.nio.buffer.FloatDataBuffer;
import org.tensorflow.nio.buffer.IntDataBuffer;
import org.tensorflow.nio.buffer.LongDataBuffer;
import org.tensorflow.nio.buffer.ShortDataBuffer;
import org.tensorflow.nio.nd.BooleanNdArray;
import org.tensorflow.nio.nd.ByteNdArray;
import org.tensorflow.nio.nd.DoubleNdArray;
import org.tensorflow.nio.nd.FloatNdArray;
import org.tensorflow.nio.nd.IntNdArray;
import org.tensorflow.nio.nd.LongNdArray;
import org.tensorflow.nio.nd.NdArray;
import org.tensorflow.nio.nd.NdArrays;
import org.tensorflow.nio.nd.Shape;
import org.tensorflow.nio.nd.ShortNdArray;
import org.tensorflow.nio.nd.index.Index;
import org.tensorflow.nio.nd.index.Indices;

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
  static <T> DataBuffer<T> bufferOf(Class<T> clazz, long size) {
    return DataBuffers.of(clazz, size);
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
   * Wraps an array of objects into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new buffer
   */
  static <T> DataBuffer<T> bufferOf(T[] array, boolean readOnly) {
    return DataBuffers.wrap(array, readOnly);
  }

  /**
   * Wraps an array of bytes into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new byte buffer
   */
  static ByteDataBuffer bufferOf(byte[] array, boolean readOnly) {
    return DataBuffers.wrap(array, readOnly);
  }

  /**
   * Wraps an array of shorts into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new short buffer
   */
  static ShortDataBuffer bufferOf(short[] array, boolean readOnly) {
    return DataBuffers.wrap(array, readOnly);
  }

  /**
   * Wraps an array of ints into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new int buffer
   */
  static IntDataBuffer bufferOf(int[] array, boolean readOnly) {
    return DataBuffers.wrap(array, readOnly);
  }

  /**
   * Wraps an array of longs into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a long buffer
   */
  static LongDataBuffer bufferOf(long[] array, boolean readOnly) {
    return DataBuffers.wrap(array, readOnly);
  }

  /**
   * Wraps an array of floats into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new float buffer
   */
  static FloatDataBuffer bufferOf(float[] array, boolean readOnly) {
    return DataBuffers.wrap(array, readOnly);
  }

  /**
   * Wraps an array of doubles into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new double buffer
   */
  static DoubleDataBuffer bufferOf(double[] array, boolean readOnly) {
    return DataBuffers.wrap(array, readOnly);
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
  static <T> NdArray<T> ndArrayOf(Class<T> clazz, Shape shape) {
    return NdArrays.of(clazz, shape);
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
  static <T> NdArray<T> scalarOf(T value) {
    return NdArrays.scalarOf(value);
  }

  /**
   * Creates byte scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new byte scalar
   */
  static ByteNdArray scalar(byte value) {
    return NdArrays.scalar(value);
  }

  /**
   * Creates short scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new short scalar
   */
  static ShortNdArray scalar(short value) {
    return NdArrays.scalar(value);
  }

  /**
   * Creates int scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new int scalar
   */
  static IntNdArray scalar(int value) {
    return NdArrays.scalar(value);
  }

  /**
   * Creates long scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new long scalar
   */
  static LongNdArray scalar(long value) {
    return NdArrays.scalar(value);
  }

  /**
   * Creates float scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new float scalar
   */
  static FloatNdArray scalar(float value) {
    return NdArrays.scalar(value);
  }

  /**
   * Creates double scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new double scalar
   */
  static DoubleNdArray scalar(double value) {
    return NdArrays.scalar(value);
  }

  /**
   * Creates boolean scalar (rank 0) initialized with the given value.
   *
   * @param value scalar value
   * @return new boolean scalar
   */
  static BooleanNdArray scalar(boolean value) {
    return NdArrays.scalar(value);
  }

  /**
   * Creates a vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new vector
   * @throws IllegalArgumentException if values is null
   */
  static <T> NdArray<T> vectorOf(T... values) {
    return NdArrays.vectorOf(values);
  }

  /**
   * Creates a byte vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new byte vector
   * @throws IllegalArgumentException if values is null
   */
  static ByteNdArray vector(byte... values) {
    return NdArrays.vector(values);
  }

  /**
   * Creates a short vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new short vector
   * @throws IllegalArgumentException if values is null
   */
  static ShortNdArray vector(short... values) {
    return NdArrays.vector(values);
  }

  /**
   * Creates an int vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new int vector
   * @throws IllegalArgumentException if values is null
   */
  static IntNdArray vector(int... values) {
    return NdArrays.vector(values);
  }

  /**
   * Creates a long vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new long vector
   * @throws IllegalArgumentException if values is null
   */
  static LongNdArray vector(long... values) {
    return NdArrays.vector(values);
  }

  /**
   * Creates a float vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new float vector
   * @throws IllegalArgumentException if values is null
   */
  static FloatNdArray vector(float... values) {
    return NdArrays.vector(values);
  }

  /**
   * Creates a double vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new double vector
   * @throws IllegalArgumentException if values is null
   */
  static DoubleNdArray vector(double... values) {
    return NdArrays.vector(values);
  }

  /**
   * Creates a boolean vector (rank 1) initialized with the given values.
   *
   * @param values vector values
   * @return new boolean vector
   * @throws IllegalArgumentException if values is null
   */
  static BooleanNdArray vector(boolean... values) {
    return NdArrays.vector(values);
  }

  /**
   * Create a Shape representing an N-dimensional value.
   *
   * @param dimensionSize size of each dimension in the shape, {@link Shape#UNKNOWN_SIZE} if unknown
   * @see Shape#make(long...)
   */
  static Shape shape(long... dimensionSize) {
    return Shape.make(dimensionSize);
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
   * @throws org.tensorflow.nio.nd.IllegalRankException if {@code coord} is not a scalar (rank 0)
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
   * @throws org.tensorflow.nio.nd.IllegalRankException if {@code coord} is not a vector (rank 1)
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
