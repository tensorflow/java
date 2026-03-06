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

import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.slice.LongSparseSlice;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * sparse array for the long data type
 *
 * <p>A sparse array as two separate dense arrays: indices, values, and a shape that represents the
 * dense shape.
 *
 * <p><em>NOTE:</em> all Sparse Arrays are readonly for the {@link #set(NdArray, long...)} and
 * {@link #setObject(Long, long...)} methods
 *
 * <pre>{@code
 * LongSparseNdArray st = new LongSparseNdArray(
 *      StdArrays.of(new long[][] {{0, 0}, {1, 2}}),
 *      NdArrays.vectorOf(1L, 256L),
 *      Shape.of(3, 4));
 *
 * }</pre>
 *
 * <p>represents the dense array:
 *
 * <pre>{@code
 * [[1, 0, 0, 0]
 *  [0, 0, 256, 0]
 *  [0, 0, 0, 0]]
 *
 * }</pre>
 */
public class LongSparseNdArray extends AbstractSparseNdArray<Long, LongNdArray>
    implements LongNdArray {

  /**
   * Creates a LongSparseNdArray
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D LongNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter {@code
   *     values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  protected LongSparseNdArray(
      LongNdArray indices, LongNdArray values, long defaultValue, DimensionalSpace dimensions) {
    super(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a LongSparseNdArray with a default value of zero.
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D LongNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter {@code
   *     values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  LongSparseNdArray(LongNdArray indices, LongNdArray values, DimensionalSpace dimensions) {
    this(indices, values, 0L, dimensions);
  }

  /**
   * Creates a LongSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  LongSparseNdArray(LongDataBuffer dataBuffer, DimensionalSpace dimensions) {
    this(dataBuffer, 0L, dimensions);
  }

  /**
   * Creates a LongSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  LongSparseNdArray(LongDataBuffer dataBuffer, long defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
    // use write to set up the indices and values
    copyFrom(dataBuffer);
  }

  /**
   * Creates a zero-filled LongSparseNdArray
   *
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  LongSparseNdArray(DimensionalSpace dimensions) {
    this(0L, dimensions);
  }

  /**
   * Creates a zero-filled LongSparseNdArray
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  LongSparseNdArray(long defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
  }

  /**
   * Creates a new LongSparseNdArray
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
  public static LongSparseNdArray create(
      LongNdArray indices, LongNdArray values, DimensionalSpace dimensions) {
    return new LongSparseNdArray(indices, values, dimensions);
  }

  /**
   * Creates a new LongSparseNdArray
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
  public static LongSparseNdArray create(
      LongNdArray indices, LongNdArray values, long defaultValue, DimensionalSpace dimensions) {
    return new LongSparseNdArray(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a new LongSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static LongSparseNdArray create(LongDataBuffer dataBuffer, DimensionalSpace dimensions) {
    return new LongSparseNdArray(dataBuffer, dimensions);
  }

  /**
   * Creates a new LongSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static LongSparseNdArray create(
      LongDataBuffer dataBuffer, long defaultValue, DimensionalSpace dimensions) {
    return new LongSparseNdArray(dataBuffer, defaultValue, dimensions);
  }

  /**
   * Creates a new empty LongSparseNdArray from a data buffer
   *
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static LongSparseNdArray create(DimensionalSpace dimensions) {
    return new LongSparseNdArray(dimensions);
  }

  /**
   * Creates a new empty LongSparseNdArray from a data buffer
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static LongSparseNdArray create(long defaultValue, DimensionalSpace dimensions) {
    return new LongSparseNdArray(defaultValue, dimensions);
  }

  /**
   * Creates a new empty LongSparseNdArray from a long data buffer
   *
   * @param buffer the data buffer
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static LongSparseNdArray create(LongDataBuffer buffer, Shape shape) {
    return new LongSparseNdArray(buffer, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new empty LongSparseNdArray from a long data buffer
   *
   * @param buffer the data buffer
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static LongSparseNdArray create(LongDataBuffer buffer, long defaultValue, Shape shape) {
    return new LongSparseNdArray(buffer, defaultValue, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new LongSparseNdArray from a LongNdArray
   *
   * @param src the LongNdArray
   * @return the new Sparse Array
   */
  public static LongSparseNdArray create(LongNdArray src) {
    LongDataBuffer buffer = DataBuffers.ofLongs(src.size());
    src.copyTo(buffer);
    return new LongSparseNdArray(buffer, DimensionalSpace.create(src.shape()));
  }

  /**
   * Creates a new LongSparseNdArray from a LongNdArray
   *
   * @param src the LongNdArray
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @return the new Sparse Array
   */
  public static LongSparseNdArray create(LongNdArray src, long defaultValue) {
    LongDataBuffer buffer = DataBuffers.ofLongs(src.size());
    src.copyTo(buffer);
    return new LongSparseNdArray(buffer, defaultValue, DimensionalSpace.create(src.shape()));
  }

  /**
   * Creates a LongNdArray of the specified shape
   *
   * @param shape the shape of the dense array.
   * @return a LongNdArray of the specified shape
   */
  public LongNdArray createValues(Shape shape) {
    return NdArrays.ofLongs(shape);
  }

  /** {@inheritDoc} */
  @Override
  public LongNdArray slice(long position, DimensionalSpace sliceDimensions) {
    return new LongSparseSlice(this, position, sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public long getLong(long... coordinates) {
    return getObject(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public LongNdArray setLong(long value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public LongNdArray copyTo(DataBuffer<Long> dst) {
    return copyTo((LongDataBuffer) dst);
  }

  /** {@inheritDoc} */
  @Override
  public LongNdArray copyTo(LongDataBuffer dst) {
    // set the values in buf to the default, then overwrite with indices/values
    Long[] defaults = new Long[(int) shape().size()];
    Arrays.fill(defaults, getDefaultValue());
    dst.write(defaults);

    AtomicLong i = new AtomicLong();
    getIndices()
        .elements(0)
        .forEachIndexed(
            (idx, l) -> {
              long[] coordinates = getIndicesCoordinates(l);
              long value = getValues().getLong(i.getAndIncrement());
              dst.setObject(value, dimensions.positionOf(coordinates));
            });
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public LongNdArray copyFrom(LongDataBuffer src) {
    List<long[]> indices = new ArrayList<>();
    List<Long> values = new ArrayList<>();

    for (long i = 0; i < src.size(); i++) {
      if (!src.getObject(i).equals(getDefaultValue())) {
        indices.add(toCoordinates(dimensions, i));
        values.add(src.getObject(i));
      }
    }
    long[][] indicesArray = new long[indices.size()][];
    long[] valuesArray = new long[values.size()];
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
  public LongNdArray copyFrom(DataBuffer<Long> src) {
    return copyFrom((LongDataBuffer) src);
  }

  /**
   * Converts the sparse array to a dense array
   *
   * @return the dense array
   */
  public LongNdArray toDense() {
    LongDataBuffer dataBuffer = DataBuffers.ofLongs(shape().size());
    copyTo(dataBuffer);
    return NdArrays.wrap(shape(), dataBuffer);
  }

  /**
   * Populates this sparse array from a dense array
   *
   * @param src the dense array
   * @return this sparse array
   */
  public LongNdArray fromDense(LongNdArray src) {
    LongDataBuffer buffer = DataBuffers.ofLongs(src.size());
    src.copyTo(buffer);
    copyFrom(buffer);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public LongNdArray slice(Index... indices) {
    return (LongNdArray) super.slice(indices);
  }

  /** {@inheritDoc} */
  @Override
  public LongNdArray get(long... coordinates) {
    return (LongNdArray) super.get(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public LongNdArray setObject(Long value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public LongNdArray set(NdArray<Long> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public LongNdArray copyTo(NdArray<Long> dst) {
    return (LongNdArray) super.copyTo(dst);
  }

  /** {@inheritDoc} */
  @Override
  public LongNdArray createDefaultArray() {
    return NdArrays.scalarOf(getDefaultValue());
  }
}
