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
import org.tensorflow.ndarray.ShortNdArray;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.slice.ShortSparseSlice;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * sparse array for the short data type
 *
 * <p>A sparse array as two separate dense arrays: indices, values, and a shape that represents the
 * dense shape.
 *
 * <p><em>NOTE:</em> all Sparse Arrays are readonly for the {@link #set(NdArray, long...)} and
 * {@link #setObject(Short, long...)} methods
 *
 * <pre>{@code
 * ShortSparseNdArray st = new ShortSparseNdArray(
 *      StdArrays.of(new long[][] {{0, 0}, {1, 2}}),
 *      NdArrays.vectorOf((short)1, (short)256}),
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
public class ShortSparseNdArray extends AbstractSparseNdArray<Short, ShortNdArray>
    implements ShortNdArray {

  /**
   * Creates a ShortSparseNdArray
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D ShortNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter {@code
   *     values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  protected ShortSparseNdArray(
      LongNdArray indices, ShortNdArray values, short defaultValue, DimensionalSpace dimensions) {
    super(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a ShortSparseNdArray with a default value of zero.
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D ShortNdArray of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter {@code
   *     values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  ShortSparseNdArray(LongNdArray indices, ShortNdArray values, DimensionalSpace dimensions) {
    this(indices, values, (short) 0, dimensions);
  }

  /**
   * Creates a ShortSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  ShortSparseNdArray(ShortDataBuffer dataBuffer, DimensionalSpace dimensions) {
    this(dataBuffer, (short) 0, dimensions);
  }

  /**
   * Creates a ShortSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  ShortSparseNdArray(ShortDataBuffer dataBuffer, short defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
    // use write to set up the indices and values
    write(dataBuffer);
  }

  /**
   * Creates a zero-filled ShortSparseNdArray
   *
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  ShortSparseNdArray(DimensionalSpace dimensions) {
    this((short) 0, dimensions);
  }

  /**
   * Creates a zero-filled ShortSparseNdArray
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  ShortSparseNdArray(short defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
  }

  /**
   * Creates a new ShortSparseNdArray
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
  public static ShortSparseNdArray create(
      LongNdArray indices, ShortNdArray values, DimensionalSpace dimensions) {
    return new ShortSparseNdArray(indices, values, dimensions);
  }

  /**
   * Creates a new ShortSparseNdArray
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
  public static ShortSparseNdArray create(
      LongNdArray indices, ShortNdArray values, short defaultValue, DimensionalSpace dimensions) {
    return new ShortSparseNdArray(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a new ShortSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static ShortSparseNdArray create(ShortDataBuffer dataBuffer, DimensionalSpace dimensions) {
    return new ShortSparseNdArray(dataBuffer, dimensions);
  }

  /**
   * Creates a new ShortSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static ShortSparseNdArray create(
      ShortDataBuffer dataBuffer, short defaultValue, DimensionalSpace dimensions) {
    return new ShortSparseNdArray(dataBuffer, defaultValue, dimensions);
  }

  /**
   * Creates a new empty ShortSparseNdArray from a data buffer
   *
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static ShortSparseNdArray create(DimensionalSpace dimensions) {
    return new ShortSparseNdArray(dimensions);
  }

  /**
   * Creates a new empty ShortSparseNdArray from a data buffer
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static ShortSparseNdArray create(short defaultValue, DimensionalSpace dimensions) {
    return new ShortSparseNdArray(defaultValue, dimensions);
  }

  /**
   * Creates a new empty ShortSparseNdArray from a short data buffer
   *
   * @param buffer the data buffer
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static ShortSparseNdArray create(ShortDataBuffer buffer, Shape shape) {
    return new ShortSparseNdArray(buffer, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new empty ShortSparseNdArray from a short data buffer
   *
   * @param buffer the data buffer
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static ShortSparseNdArray create(ShortDataBuffer buffer, short defaultValue, Shape shape) {
    return new ShortSparseNdArray(buffer, defaultValue, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new ShortSparseNdArray from a ShortNdArray
   *
   * @param src the ShortNdArray
   * @return the new Sparse Array
   */
  public static ShortSparseNdArray create(ShortNdArray src) {
    ShortDataBuffer buffer = DataBuffers.ofShorts(src.size());
    src.read(buffer);
    return new ShortSparseNdArray(buffer, DimensionalSpace.create(src.shape()));
  }

  /**
   * Creates a new ShortSparseNdArray from a ShortNdArray
   *
   * @param src the ShortNdArray
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @return the new Sparse Array
   */
  public static ShortSparseNdArray create(ShortNdArray src, short defaultValue) {
    ShortDataBuffer buffer = DataBuffers.ofShorts(src.size());
    src.read(buffer);
    return new ShortSparseNdArray(buffer, defaultValue, DimensionalSpace.create(src.shape()));
  }

  /**
   * Creates a ShortNdArray of the specified shape
   *
   * @param shape the shape of the dense array.
   * @return a ShortNdArray of the specified shape
   */
  public ShortNdArray createValues(Shape shape) {
    return NdArrays.ofShorts(shape);
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray slice(long position, DimensionalSpace sliceDimensions) {
    return new ShortSparseSlice(this, position, sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public short getShort(long... coordinates) {
    return getObject(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray setShort(short value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray read(DataBuffer<Short> dst) {
    return read((ShortDataBuffer) dst);
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray read(ShortDataBuffer dst) {
    // set the values in buf to the default, then overwrite with indices/values
    Short[] defaults = new Short[(int) shape().size()];
    Arrays.fill(defaults, getDefaultValue());
    dst.write(defaults);

    AtomicInteger i = new AtomicInteger();
    getIndices()
        .elements(0)
        .forEachIndexed(
            (idx, l) -> {
              long[] coordinates = getIndicesCoordinates(l);
              short value = getValues().getShort(i.getAndIncrement());
              dst.setObject(value, dimensions.positionOf(coordinates));
            });
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray write(ShortDataBuffer src) {
    List<long[]> indices = new ArrayList<>();
    List<Short> values = new ArrayList<>();

    for (short i = 0; i < src.size(); i++) {
      if (!src.getObject(i).equals(getDefaultValue())) {
        indices.add(toCoordinates(dimensions, i));
        values.add(src.getObject(i));
      }
    }
    long[][] indicesArray = new long[indices.size()][];
    short[] valuesArray = new short[values.size()];
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
  public ShortNdArray write(DataBuffer<Short> src) {
    return write((ShortDataBuffer) src);
  }

  /**
   * Converts the sparse array to a dense array
   *
   * @return the dense array
   */
  public ShortNdArray toDense() {
    ShortDataBuffer dataBuffer = DataBuffers.ofShorts(shape().size());
    read(dataBuffer);
    return NdArrays.wrap(shape(), dataBuffer);
  }

  /**
   * Populates this sparse array from a dense array
   *
   * @param src the dense array
   * @return this sparse array
   */
  public ShortNdArray fromDense(ShortNdArray src) {
    ShortDataBuffer buffer = DataBuffers.ofShorts(src.size());
    src.read(buffer);
    write(buffer);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray slice(Index... indices) {
    return (ShortNdArray) super.slice(indices);
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray get(long... coordinates) {
    return (ShortNdArray) super.get(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray setObject(Short value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray set(NdArray<Short> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray copyTo(NdArray<Short> dst) {
    return (ShortNdArray) super.copyTo(dst);
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray createDefaultArray() {
    return NdArrays.scalarOf(getDefaultValue());
  }
}
