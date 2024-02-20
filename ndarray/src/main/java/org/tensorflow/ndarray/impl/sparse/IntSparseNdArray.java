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

import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.slice.IntSparseSlice;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * sparse array for the int data type
 *
 * <p>A sparse array as two separate dense arrays: indices, values, and a shape that represents the
 * dense shape.
 *
 * <p><em>NOTE:</em> all Sparse Arrays are readonly for the {@link #set(NdArray, long...)} and
 * {@link #setObject(Integer, long...)} methods
 *
 * <pre>{@code
 * IntSparseNdArray st = new IntSparseNdArray(
 *      StdArrays.of(new long[][] {{0, 0}, {1, 2}}),
 *      NdArrays.vectorOf(1, 256),
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
public class IntSparseNdArray extends AbstractSparseNdArray<Integer, IntNdArray>
    implements IntNdArray {

  /**
   * Creates a IntSparseNdArray
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D IntNdArray of shape {@code [N]}, which supplies the values for each element
   *     in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter {@code
   *     values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  protected IntSparseNdArray(
      LongNdArray indices, IntNdArray values, int defaultValue, DimensionalSpace dimensions) {
    super(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a IntSparseNdArray with a default value of zero.
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D IntNdArray of shape {@code [N]}, which supplies the values for each element
   *     in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter {@code
   *     values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a value of
   *     {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  IntSparseNdArray(LongNdArray indices, IntNdArray values, DimensionalSpace dimensions) {
    this(indices, values, 0, dimensions);
  }

  /**
   * Creates a IntSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  IntSparseNdArray(IntDataBuffer dataBuffer, DimensionalSpace dimensions) {
    this(dataBuffer, 0, dimensions);
  }
  /**
   * Creates a IntSparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  IntSparseNdArray(IntDataBuffer dataBuffer, int defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
    // use write to set up the indices and values
    copyFrom(dataBuffer);
  }

  /**
   * Creates a zero-filled IntSparseNdArray
   *
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  IntSparseNdArray(DimensionalSpace dimensions) {
    this(0, dimensions);
  }

  /**
   * Creates a zero-filled IntSparseNdArray
   *
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  IntSparseNdArray(int defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
  }

  /**
   * Creates a new IntSparseNdArray
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
  public static IntSparseNdArray create(
      LongNdArray indices, IntNdArray values, DimensionalSpace dimensions) {
    return new IntSparseNdArray(indices, values, dimensions);
  }

  /**
   * Creates a new IntSparseNdArray
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
  public static IntSparseNdArray create(
      LongNdArray indices, IntNdArray values, int defaultValue, DimensionalSpace dimensions) {
    return new IntSparseNdArray(indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a new IntSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static IntSparseNdArray create(IntDataBuffer dataBuffer, DimensionalSpace dimensions) {
    return new IntSparseNdArray(dataBuffer, dimensions);
  }

  /**
   * Creates a new IntSparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static IntSparseNdArray create(
      IntDataBuffer dataBuffer, int defaultValue, DimensionalSpace dimensions) {
    return new IntSparseNdArray(dataBuffer, defaultValue, dimensions);
  }

  /**
   * Creates a new empty IntSparseNdArray from a data buffer
   *
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static IntSparseNdArray create(DimensionalSpace dimensions) {
    return new IntSparseNdArray(dimensions);
  }

  /**
   * Creates a new empty IntSparseNdArray from a data buffer
   *
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static IntSparseNdArray create(int defaultValue, DimensionalSpace dimensions) {
    return new IntSparseNdArray(defaultValue, dimensions);
  }

  /**
   * Creates a new empty IntSparseNdArray from a data buffer
   *
   * @param shape the shape of the debse array that this sparse array represents
   * @return the new Sparse Array
   */
  public static IntSparseNdArray create(Shape shape) {
    return new IntSparseNdArray(DimensionalSpace.create(shape));
  }
  /**
   * Creates a new empty IntSparseNdArray from a data buffer
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param shape the shape of the debse array that this sparse array represents
   * @return the new Sparse Array
   */
  public static IntSparseNdArray create(int defaultValue, Shape shape) {
    return new IntSparseNdArray(defaultValue, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new empty IntSparseNdArray from a int data buffer
   *
   * @param buffer the data buffer
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static IntSparseNdArray create(IntDataBuffer buffer, Shape shape) {
    return new IntSparseNdArray(buffer, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new empty IntSparseNdArray from a int data buffer
   *
   * @param buffer the data buffer
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static IntSparseNdArray create(IntDataBuffer buffer, int defaultValue, Shape shape) {
    return new IntSparseNdArray(buffer, defaultValue, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new IntSparseNdArray from a IntNdArray
   *
   * @param src the IntNdArray
   * @return the new Sparse Array
   */
  public static IntSparseNdArray create(IntNdArray src) {
    IntDataBuffer buffer = DataBuffers.ofInts(src.size());
    src.copyTo(buffer);
    return new IntSparseNdArray(buffer, DimensionalSpace.create(src.shape()));
  }

  /**
   * Creates a new IntSparseNdArray from a IntNdArray
   *
   * @param src the IntNdArray
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @return the new Sparse Array
   */
  public static IntSparseNdArray create(IntNdArray src, int defaultValue) {
    IntDataBuffer buffer = DataBuffers.ofInts(src.size());
    src.copyTo(buffer);
    return new IntSparseNdArray(buffer, defaultValue, DimensionalSpace.create(src.shape()));
  }

  /**
   * Creates a IntNdArray of the specified shape
   *
   * @param shape the shape of the dense array.
   * @return a IntNdArray of the specified shape
   */
  public IntNdArray createValues(Shape shape) {
    return NdArrays.ofInts(shape);
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray slice(long position, DimensionalSpace sliceDimensions) {
    return new IntSparseSlice(this, position, sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public int getInt(long... coordinates) {
    return getObject(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray setInt(int value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray copyTo(DataBuffer<Integer> dst) {
    return copyTo((IntDataBuffer) dst);
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray copyTo(IntDataBuffer dst) {
    // set the values in buf to the default, then overwrite with indices/values
    Integer[] defaults = new Integer[(int) shape().size()];
    Arrays.fill(defaults, getDefaultValue());
    dst.write(defaults);

    AtomicInteger i = new AtomicInteger();
    getIndices()
        .elements(0)
        .forEachIndexed(
            (idx, l) -> {
              long[] coordinates = getIndicesCoordinates(l);
              int value = getValues().getInt(i.getAndIncrement());
              dst.setObject(value, dimensions.positionOf(coordinates));
            });
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray copyFrom(IntDataBuffer src) {
    List<long[]> indices = new ArrayList<>();
    List<Integer> values = new ArrayList<>();

    for (long i = 0; i < src.size(); i++) {
      if (!src.getObject(i).equals(getDefaultValue())) {
        indices.add(toCoordinates(dimensions, i));
        values.add(src.getObject(i));
      }
    }
    long[][] indicesArray = new long[indices.size()][];
    int[] valuesArray = new int[values.size()];
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
  public IntNdArray copyFrom(DataBuffer<Integer> src) {
    return copyFrom((IntDataBuffer) src);
  }

  /**
   * Converts the sparse array to a dense array
   *
   * @return the dense array
   */
  public IntNdArray toDense() {
    IntDataBuffer dataBuffer = DataBuffers.ofInts(shape().size());
    copyTo(dataBuffer);
    return NdArrays.wrap(shape(), dataBuffer);
  }

  /**
   * Populates this sparse array from a dense array
   *
   * @param src the dense array
   * @return this sparse array
   */
  public IntNdArray fromDense(IntNdArray src) {
    IntDataBuffer buffer = DataBuffers.ofInts(src.size());
    src.copyTo(buffer);
    copyFrom(buffer);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray slice(Index... indices) {
    return (IntNdArray) super.slice(indices);
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray get(long... coordinates) {
    return (IntNdArray) super.get(coordinates);
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray setObject(Integer value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray set(NdArray<Integer> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray copyTo(NdArray<Integer> dst) {
    return (IntNdArray) super.copyTo(dst);
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray createDefaultArray() {
    return NdArrays.scalarOf(getDefaultValue());
  }
}
