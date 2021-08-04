/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.ndarray.impl.sparse;

import org.tensorflow.ndarray.ByteNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.slice.ByteSparseSlice;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * sparse array for the byte data type
 *
 * <p>A sparse array as two separate dense arrays: indices, values, and a shape that represents the
 * dense shape.
 *
 * <p><em>NOTE:</em> all Sparse Arrays are readonly for the {@link #set(NdArray, long...)} and
 * {@link #setObject(Byte, long...)} methods
 *
 * <pre>{@code
 * ByteSparseNdArray st = new ByteSparseNdArray(
 *      StdArrays.of(new long[][] {{0, 0}, {1, 2}}),
 *      NdArrays.vectorOf((byte)1, (byte)255),
 *      Shape.of(3, 4));
 *
 * }</pre>
 *
 * <p>represents the dense array:
 *
 * <pre>{@code
 * [[(byte)1, (byte)0, (byte)0, (byte)0]
 *  [(byte)0, (byte)0, (byte)1, (byte)0]
 *  [(byte)0, (byte)0, (byte)0, (byte)0]]
 *
 * }</pre>
 */
public class ByteSparseNdArray extends AbstractSparseNdArray<Byte, ByteNdArray>
    implements ByteNdArray {

  /**
   * Creates a ByteSparseNdArray
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D NdArray of Byte type and shape {@code [N]}, which supplies the values for
   *     each element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter
   *     {@code values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a
   *     value of {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  ByteSparseNdArray(LongNdArray indices, ByteNdArray values, DimensionalSpace dimensions) {
    this(indices, values, (byte) 0, dimensions);
  }

  /**
   * Creates a ByteSparseNdArray with a default value of zero.
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D NdArray of Byte type and shape {@code [N]}, which supplies the values for
   *     each element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter
   *     {@code values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a
   *     value of {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  ByteSparseNdArray(
      LongNdArray indices, ByteNdArray values, byte defaultValue, DimensionalSpace dimensions) {
    super(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a ByteSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  ByteSparseNdArray(ByteDataBuffer dataBuffer, DimensionalSpace dimensions) {
    this(dataBuffer, (byte) 0, dimensions);
  }

  /**
   * Creates a ByteSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  ByteSparseNdArray(ByteDataBuffer dataBuffer, byte defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
    // use write to set up the indices and values
    write(dataBuffer);
  }

  /**
   * Creates a zero-filled ByteSparseNdArray
   *
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  ByteSparseNdArray(DimensionalSpace dimensions) {
    this((byte) 0, dimensions);
  }

  /**
   * Creates a zero-filled ByteSparseNdArray
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  ByteSparseNdArray(byte defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
  }

  /**
   * Creates a new ByteSparseNdArray
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D NdArray of any type and shape {@code [N]}, which supplies the values for
   *     each element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter
   *     {@code values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a
   *     value of {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param dimensions the dimensional space for the dense object represented by this sparse array.
   * @return the new Sparse Array
   */
  public static ByteSparseNdArray create(
      LongNdArray indices, ByteNdArray values, DimensionalSpace dimensions) {
    return new ByteSparseNdArray(indices, values, dimensions);
  }

  /**
   * Creates a new ByteSparseNdArray
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D NdArray of any type and shape {@code [N]}, which supplies the values for
   *     each element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter
   *     {@code values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a
   *     value of {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array.
   * @return the new Sparse Array
   */
  public static ByteSparseNdArray create(
      LongNdArray indices, ByteNdArray values, byte defaultValue, DimensionalSpace dimensions) {
    return new ByteSparseNdArray(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a new ByteSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static ByteSparseNdArray create(ByteDataBuffer dataBuffer, DimensionalSpace dimensions) {
    return new ByteSparseNdArray(dataBuffer, dimensions);
  }

  /**
   * Creates a new ByteSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static ByteSparseNdArray create(
      ByteDataBuffer dataBuffer, byte defaultValue, DimensionalSpace dimensions) {
    return new ByteSparseNdArray(dataBuffer, defaultValue, dimensions);
  }

  /**
   * Creates a new empty ByteSparseNdArray from a data buffer
   *
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static ByteSparseNdArray create(DimensionalSpace dimensions) {
    return new ByteSparseNdArray(dimensions);
  }

  /**
   * Creates a new empty ByteSparseNdArray from a data buffer
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static ByteSparseNdArray create(byte defaultValue, DimensionalSpace dimensions) {
    return new ByteSparseNdArray(defaultValue, dimensions);
  }

  /**
   * Creates a new empty ByteSparseNdArray from a float data buffer
   *
   * @param buffer the data buffer
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static ByteSparseNdArray create(ByteDataBuffer buffer, Shape shape) {
    return new ByteSparseNdArray(buffer, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new empty ByteSparseNdArray from a float data buffer
   *
   * @param buffer the data buffer
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static ByteSparseNdArray create(ByteDataBuffer buffer, byte defaultValue, Shape shape) {
    return new ByteSparseNdArray(buffer, defaultValue, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new ByteSparseNdArray from a ByteNdArray
   *
   * @param src the ByteNdArray
   * @return the new Sparse Array
   */
  public static ByteSparseNdArray create(ByteNdArray src) {
    ByteDataBuffer buffer = DataBuffers.ofBytes(src.size());
    src.read(buffer);
    return new ByteSparseNdArray(buffer, DimensionalSpace.create(src.shape()));
  }

  /**
   * Creates a new ByteSparseNdArray from a ByteNdArray
   *
   * @param src the ByteNdArray
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @return the new Sparse Array
   */
  public static ByteSparseNdArray create(ByteNdArray src, byte defaultValue) {
    ByteDataBuffer buffer = DataBuffers.ofBytes(src.size());
    src.read(buffer);
    return new ByteSparseNdArray(buffer, defaultValue, DimensionalSpace.create(src.shape()));
  }

  /**
   * Creates a ByteNdArray of the specified shape
   *
   * @param shape the shape of the dense array.
   * @return a ByteNdArray of the specified shape
   */
  public ByteNdArray createValues(Shape shape) {
    return NdArrays.ofBytes(shape);
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray slice(long position, DimensionalSpace sliceDimensions) {
    return new ByteSparseSlice(this, position, sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public byte getByte(long... coordinates) {
    return getObject(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray setByte(byte value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray read(DataBuffer<Byte> dst) {
    return read((ByteDataBuffer) dst);
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray read(ByteDataBuffer dst) {
    // set the values in buf to the default, then overwrite with indices/values
    Byte[] defaults = new Byte[(int) shape().size()];
    Arrays.fill(defaults, getDefaultValue());
    dst.write(defaults);

    AtomicInteger i = new AtomicInteger();
    getIndices()
        .elements(0)
        .forEachIndexed(
            (idx, l) -> {
              long[] coordinates = getIndicesCoordinates(l);
              byte value = getValues().getByte(i.getAndIncrement());
              dst.setObject(value, dimensions.positionOf(coordinates));
            });
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray write(ByteDataBuffer src) {
    List<long[]> indices = new ArrayList<>();
    List<Byte> values = new ArrayList<>();

    for (long i = 0; i < src.size(); i++) {
      if (!src.getObject(i).equals(getDefaultValue())) {
        indices.add(toCoordinates(dimensions, i));
        values.add(src.getObject(i));
      }
    }
    long[][] indicesArray = new long[indices.size()][];
    byte[] valuesArray = new byte[values.size()];
    for (int i = 0; i < indices.size(); i++) {
      indicesArray[i] = indices.get(i);
      valuesArray[i] = values.get(i);
    }

    setIndices(StdArrays.ndCopyOf(indicesArray));
    setValues(NdArrays.vectorOf(valuesArray));
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray write(DataBuffer<Byte> src) {
    return write((ByteDataBuffer) src);
  }

  /**
   * Converts the sparse array to a dense array
   *
   * @return the dense array
   */
  public ByteNdArray toDense() {
    ByteDataBuffer dataBuffer = DataBuffers.ofBytes(shape().size());
    read(dataBuffer);
    return NdArrays.wrap(shape(), dataBuffer);
  }

  /**
   * Populates this sparse array from a dense array
   *
   * @param src the dense array
   * @return this sparse array
   */
  public ByteNdArray fromDense(ByteNdArray src) {
    ByteDataBuffer buffer = DataBuffers.ofBytes(src.size());
    src.read(buffer);
    write(buffer);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray slice(Index... indices) {
    return (ByteNdArray) super.slice(indices);
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray get(long... coordinates) {
    return (ByteNdArray) super.get(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray setObject(Byte value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray set(NdArray<Byte> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray copyTo(NdArray<Byte> dst) {
    return (ByteNdArray) super.copyTo(dst);
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray createDefaultArray() {
    return NdArrays.scalarOf(getDefaultValue());
  }
}
