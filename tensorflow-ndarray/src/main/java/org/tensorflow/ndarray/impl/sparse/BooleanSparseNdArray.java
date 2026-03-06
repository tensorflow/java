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

import org.tensorflow.ndarray.BooleanNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.slice.BooleanSparseSlice;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * sparse array for the boolean data type
 *
 * <p>A sparse array as two separate dense arrays: indices, values, and a shape that represents the
 * dense shape.
 *
 * <p><em>NOTE:</em> all Sparse Arrays are readonly for the {@link #set(NdArray, long...)} and
 * {@link #setObject(Boolean, long...)} methods
 *
 * <pre>{@code
 * FloatSparseNdArray st = new BooleanSparseNdArray(
 *      StdArrays.of(new long[][] {{0, 0}, {1, 2}}),
 *      NdArrays.vectorOf(true, true),
 *      Shape.of(3, 4));
 *
 * }</pre>
 *
 * <p>represents the dense array:
 *
 * <pre>{@code
 * [[true, false, false, false]
 *  [false, false, true, false]
 *  [false, false, false, false]]
 *
 * }</pre>
 */
public class BooleanSparseNdArray extends AbstractSparseNdArray<Boolean, BooleanNdArray>
    implements BooleanNdArray {

  /**
   * Creates a BooleanSparseNdArray
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D NdArray of Boolean type and shape {@code [N]}, which supplies the values
   *     for each element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the
   *     parameter {@code values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse
   *     NdArray has a value of {@code 18}, and element {@code [2,4]} of the NdArray has a value of
   *     {@code 3.6}.
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  protected BooleanSparseNdArray(
      LongNdArray indices,
      BooleanNdArray values,
      boolean defaultValue,
      DimensionalSpace dimensions) {
    super(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a BooleanSparseNdArray with a default value of false.
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D NdArray of Boolean type and shape {@code [N]}, which supplies the values
   *     for each element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the
   *     parameter {@code values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse
   *     NdArray has a value of {@code 18}, and element {@code [2,4]} of the NdArray has a value of
   *     {@code 3.6}.
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  BooleanSparseNdArray(LongNdArray indices, BooleanNdArray values, DimensionalSpace dimensions) {
    this(indices, values, false, dimensions);
  }

  /**
   * Creates a BooleanSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  BooleanSparseNdArray(BooleanDataBuffer dataBuffer, DimensionalSpace dimensions) {
    this(dataBuffer, false, dimensions);
  }

  /**
   * Creates a BooleanSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  BooleanSparseNdArray(
      BooleanDataBuffer dataBuffer, boolean defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
    // use write to set up the indices and values
    copyFrom(dataBuffer);
  }

  /**
   * Creates a zero-filled BooleanSparseNdArray
   *
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  BooleanSparseNdArray(DimensionalSpace dimensions) {
    this(false, dimensions);
  }

  /**
   * Creates a zero-filled BooleanSparseNdArray
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  BooleanSparseNdArray(boolean defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
  }

  /**
   * Creates a new BooleanSparseNdArray
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
  public static BooleanSparseNdArray create(
      LongNdArray indices, BooleanNdArray values, DimensionalSpace dimensions) {
    return new BooleanSparseNdArray(indices, values, dimensions);
  }

  /**
   * Creates a new BooleanSparseNdArray
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
  public static BooleanSparseNdArray create(
      LongNdArray indices,
      BooleanNdArray values,
      boolean defaultValue,
      DimensionalSpace dimensions) {
    return new BooleanSparseNdArray(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a new BooleanSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static BooleanSparseNdArray create(
      BooleanDataBuffer dataBuffer, DimensionalSpace dimensions) {
    return new BooleanSparseNdArray(dataBuffer, dimensions);
  }

  /**
   * Creates a new BooleanSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static BooleanSparseNdArray create(
      BooleanDataBuffer dataBuffer, boolean defaultValue, DimensionalSpace dimensions) {
    return new BooleanSparseNdArray(dataBuffer, defaultValue, dimensions);
  }

  /**
   * Creates a new empty BooleanSparseNdArray from a data buffer
   *
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static BooleanSparseNdArray create(DimensionalSpace dimensions) {
    return new BooleanSparseNdArray(dimensions);
  }

  /**
   * Creates a new empty BooleanSparseNdArray from a data buffer
   *
   * @param dimensions the dimensions array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @return the new Sparse Array
   */
  public static BooleanSparseNdArray create(boolean defaultValue, DimensionalSpace dimensions) {
    return new BooleanSparseNdArray(defaultValue, dimensions);
  }

  /**
   * Creates a new empty BooleanSparseNdArray from a float data buffer
   *
   * @param buffer the data buffer
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static BooleanSparseNdArray create(BooleanDataBuffer buffer, Shape shape) {
    return new BooleanSparseNdArray(buffer, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new empty BooleanSparseNdArray from a float data buffer
   *
   * @param buffer the data buffer
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static BooleanSparseNdArray create(
      BooleanDataBuffer buffer, boolean defaultValue, Shape shape) {
    return new BooleanSparseNdArray(buffer, defaultValue, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new BooleanSparseNdArray from a BooleanNdArray
   *
   * @param src the BooleanNdArray
   * @return the new Sparse Array
   */
  public static BooleanSparseNdArray create(BooleanNdArray src) {
    BooleanDataBuffer buffer = DataBuffers.ofBooleans(src.size());
    src.copyTo(buffer);
    return new BooleanSparseNdArray(buffer, DimensionalSpace.create(src.shape()));
  }
  /**
   * Creates a new BooleanSparseNdArray from a BooleanNdArray
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param src the BooleanNdArray
   * @return the new Sparse Array
   */
  public static BooleanSparseNdArray create(BooleanNdArray src, boolean defaultValue) {
    BooleanDataBuffer buffer = DataBuffers.ofBooleans(src.size());
    src.copyTo(buffer);
    return new BooleanSparseNdArray(buffer, defaultValue, DimensionalSpace.create(src.shape()));
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray createDefaultArray() {
    return NdArrays.scalarOf(getDefaultValue());
  }

  /**
   * Creates a BooleanNdArray of the specified shape
   *
   * @param shape the shape of the dense array.
   * @return a BooleanNdArray of the specified shape
   */
  public BooleanNdArray createValues(Shape shape) {
    return NdArrays.ofBooleans(shape);
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray slice(long position, DimensionalSpace sliceDimensions) {
    return new BooleanSparseSlice(this, position, sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public boolean getBoolean(long... coordinates) {
    return getObject(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray setBoolean(boolean value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray copyTo(DataBuffer<Boolean> dst) {
    return copyTo((BooleanDataBuffer) dst);
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray copyTo(BooleanDataBuffer dst) {
    // set the values in buf to the default, then overwrite with indices/values
    Boolean[] defaults = new Boolean[(int) shape().size()];
    Arrays.fill(defaults, getDefaultValue());
    dst.write(defaults);

    AtomicInteger i = new AtomicInteger();
    getIndices()
        .elements(0)
        .forEachIndexed(
            (idx, l) -> {
              long[] coordinates = getIndicesCoordinates(l);
              boolean value = getValues().getBoolean(i.getAndIncrement());
              dst.setObject(value, dimensions.positionOf(coordinates));
            });
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray copyFrom(BooleanDataBuffer src) {
    List<long[]> indices = new ArrayList<>();
    List<Boolean> values = new ArrayList<>();

    for (long i = 0; i < src.size(); i++) {
      if (!src.getObject(i).equals(getDefaultValue())) {
        indices.add(toCoordinates(dimensions, i));
        values.add(src.getObject(i));
      }
    }
    long[][] indicesArray = new long[indices.size()][];
    boolean[] valuesArray = new boolean[values.size()];
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
  public BooleanNdArray copyFrom(DataBuffer<Boolean> src) {
    return copyFrom((BooleanDataBuffer) src);
  }

  /**
   * Converts the sparse array to a dense array
   *
   * @return the dense array
   */
  public BooleanNdArray toDense() {
    BooleanDataBuffer dataBuffer = DataBuffers.ofBooleans(shape().size());
    copyTo(dataBuffer);
    return NdArrays.wrap(shape(), dataBuffer);
  }

  /**
   * Populates this sparse array from a dense array
   *
   * @param src the dense array
   * @return this sparse array
   */
  public BooleanNdArray fromDense(BooleanNdArray src) {
    BooleanDataBuffer buffer = DataBuffers.ofBooleans(src.size());
    src.copyTo(buffer);
    copyFrom(buffer);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray slice(Index... indices) {
    return (BooleanNdArray) super.slice(indices);
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray get(long... coordinates) {
    return (BooleanNdArray) super.get(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray setObject(Boolean value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray set(NdArray<Boolean> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray copyTo(NdArray<Boolean> dst) {
    return (BooleanNdArray) super.copyTo(dst);
  }
}
