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
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.slice.ObjectSparseSlice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * sparse array for the any data type
 *
 * <p>A sparse array has two separate dense arrays: indices, values, and a shape that represents the
 * dense shape.
 *
 * <p><em>NOTE:</em> all Sparse Arrays are readonly for the {@link #set(NdArray, long...)} and
 * {@link #setObject(Object, long...)} methods
 *
 * <pre>{@code
 * SparseNdArray<String> st = new SparseNdArray<>(
 *      StdArrays.of(new long[][] {{0, 0}, {1, 2}}),
 *      NdArrays.vectorOf("first", "second"),
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
public class SparseNdArray<T, U extends NdArray<T>> extends AbstractSparseNdArray<T, U>
    implements org.tensorflow.ndarray.SparseNdArray<T, U> {

  private final Class<T> type;

  /**
   * Creates a SparseNdArray
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
  protected SparseNdArray(
      Class<T> type, LongNdArray indices, U values, T defaultValue, DimensionalSpace dimensions) {
    super(indices, values, defaultValue, dimensions);
    this.type = type;
  }

  /**
   * Creates a SparseNdArray with a default value of null.
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
  SparseNdArray(Class<T> type, LongNdArray indices, U values, DimensionalSpace dimensions) {
    this(type, indices, values, null, dimensions);
  }

  /**
   * Creates a SparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  SparseNdArray(Class<T> type, DataBuffer<T> dataBuffer, DimensionalSpace dimensions) {
    this(type, dataBuffer, null, dimensions);
  }

  /**
   * Creates a SparseNdArray
   *
   * @param dataBuffer a dense dataBuffer used to create the spars array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  SparseNdArray(
      Class<T> type, DataBuffer<T> dataBuffer, T defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
    this.type = type;
    // use write to set up the indices and values
    copyFrom(dataBuffer);
  }

  /**
   * Creates a zero-filled SparseNdArray
   *
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  SparseNdArray(Class<T> type, DimensionalSpace dimensions) {
    this(type, (T) null, dimensions);
  }

  /**
   * Creates a zero-filled SparseNdArray
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  SparseNdArray(Class<T> type, T defaultValue, DimensionalSpace dimensions) {
    super(defaultValue, dimensions);
    this.type = type;
  }

  /**
   * Creates a new SparseNdArray
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
  public static <T, U extends NdArray<T>> SparseNdArray<T, U> create(
      Class<T> type, LongNdArray indices, U values, DimensionalSpace dimensions) {
    return new SparseNdArray<>(type, indices, values, dimensions);
  }

  /**
   * Creates a new SparseNdArray
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
  public static <T, U extends NdArray<T>> SparseNdArray<T, U> create(
      Class<T> type, LongNdArray indices, U values, T defaultValue, DimensionalSpace dimensions) {
    return new SparseNdArray<>(type, indices, values, defaultValue, dimensions);
  }

  /**
   * Creates a new SparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static <T, U extends NdArray<T>> SparseNdArray<T, U> create(
      Class<T> type, DataBuffer<T> dataBuffer, DimensionalSpace dimensions) {
    return new SparseNdArray<>(type, dataBuffer, dimensions);
  }

  /**
   * Creates a new SparseNdArray from a data buffer
   *
   * @param dataBuffer the databuffer containing the dense array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the sparse array
   * @return the new Sparse Array
   */
  public static <T, U extends NdArray<T>> SparseNdArray<T, U> create(
      Class<T> type, DataBuffer<T> dataBuffer, T defaultValue, DimensionalSpace dimensions) {
    return new SparseNdArray<>(type, dataBuffer, defaultValue, dimensions);
  }

  /**
   * Creates a new empty SparseNdArray from a data buffer
   *
   * @param dimensions the dimensions array
   * @return the new Sparse Array
   */
  public static <T, U extends NdArray<T>> SparseNdArray<T, U> create(
      Class<T> type, DimensionalSpace dimensions) {
    return new SparseNdArray<>(type, dimensions);
  }

  /**
   * Creates a new empty SparseNdArray from a data buffer
   *
   * @param dimensions the dimensions array
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @return the new Sparse Array
   */
  public static <T, U extends NdArray<T>> SparseNdArray<T, U> create(
      Class<T> type, T defaultValue, DimensionalSpace dimensions) {
    return new SparseNdArray<>(type, defaultValue, dimensions);
  }

  /**
   * Creates a new empty SparseNdArray from a float data buffer
   *
   * @param buffer the data buffer
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static <T, U extends NdArray<T>> SparseNdArray<T, U> create(
      Class<T> type, DataBuffer<T> buffer, Shape shape) {
    return new SparseNdArray<>(type, buffer, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new empty SparseNdArray from a float data buffer
   *
   * @param buffer the data buffer
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param shape the shape of the sparse array.
   * @return the new Sparse Array
   */
  public static <T, U extends NdArray<T>> SparseNdArray<T, U> create(
      Class<T> type, DataBuffer<T> buffer, T defaultValue, Shape shape) {
    return new SparseNdArray<>(type, buffer, defaultValue, DimensionalSpace.create(shape));
  }

  /**
   * Creates a new SparseNdArray from a NdArray
   *
   * @param src the NdArray
   * @return the new Sparse Array
   */
  public static <T, U extends NdArray<T>> SparseNdArray<T, U> create(Class<T> type, U src) {
    DataBuffer<T> buffer = DataBuffers.ofObjects(type, src.size());
    src.copyTo(buffer);
    return new SparseNdArray<>(type, buffer, DimensionalSpace.create(src.shape()));
  }
  /**
   * Creates a new SparseNdArray from a NdArray
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param src the NdArray
   * @return the new Sparse Array
   */
  public static <T, U extends NdArray<T>> SparseNdArray<T, U> create(
      Class<T> type, U src, T defaultValue) {
    DataBuffer<T> buffer = DataBuffers.ofObjects(type, src.size());
    src.copyTo(buffer);
    return new SparseNdArray<>(type, buffer, defaultValue, DimensionalSpace.create(src.shape()));
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public U createDefaultArray() {
    return getDefaultValue() == null
        ? (U) NdArrays.ofObjects(type, Shape.scalar())
        : (U) NdArrays.scalarOfObject(getDefaultValue());
  }

  /**
   * Creates a NdArray of the specified shape
   *
   * @param shape the shape of the dense array.
   * @return a NdArray of the specified shape
   */
  @SuppressWarnings("unchecked")
  public U createValues(Shape shape) {
    return (U) NdArrays.ofObjects(type, shape);
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public U slice(long position, DimensionalSpace sliceDimensions) {
    return (U) new ObjectSparseSlice<>(this, position, sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public NdArray<T> copyTo(DataBuffer<T> dst) {
    // set the values in buf to the default, then overwrite with indices/values
    @SuppressWarnings("unchecked")
    T[] defaults = (T[]) Array.newInstance(type, (int) dst.size());
    Arrays.fill(defaults, getDefaultValue());
    dst.write(defaults);

    AtomicInteger i = new AtomicInteger();
    getIndices()
        .elements(0)
        .forEachIndexed(
            (idx, l) -> {
              long[] coordinates = getIndicesCoordinates(l);
              T value = getValues().getObject(i.getAndIncrement());
              dst.setObject(value, dimensions.positionOf(coordinates));
            });
    return this;
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings({
    "unchecked",
  })
  public NdArray<T> copyFrom(DataBuffer<T> src) {
    List<long[]> indices = new ArrayList<>();
    List<T> values = new ArrayList<>();

    for (long i = 0; i < src.size(); i++) {
      if (!Objects.equals(src.getObject(i), getDefaultValue())) {
        indices.add(toCoordinates(dimensions, i));
        values.add(src.getObject(i));
      }
    }
    long[][] indicesArray = new long[indices.size()][];
    // unchecked cast, suppressed.
    T[] valuesArray = (T[]) Array.newInstance(type, values.size());
    for (int i = 0; i < indices.size(); i++) {
      indicesArray[i] = indices.get(i);
      valuesArray[i] = values.get(i);
    }

    setIndices(StdArrays.ndCopyOf(indicesArray));

    // unchecked cast, suppressed.
    setValues((U) NdArrays.vectorOfObjects(valuesArray));
    return this;
  }
  /**
   * Converts the sparse array to a dense array
   *
   * @return the dense array
   */
  @SuppressWarnings("unchecked")
  public U toDense() {
    DataBuffer<T> dataBuffer = DataBuffers.ofObjects(type, shape().size());
    copyTo(dataBuffer);
    // unchecked cast, suppressed.
    return (U) NdArrays.wrap(shape(), dataBuffer);
  }

  /**
   * Populates this sparse array from a dense array
   *
   * @param src the dense array
   * @return this sparse array
   */
  public NdArray<T> fromDense(NdArray<T> src) {
    DataBuffer<T> buffer = DataBuffers.ofObjects(type, src.size());
    src.copyTo(buffer);
    copyFrom(buffer);
    return this;
  }

  /**
   * Gets the class type for this sparse array
   *
   * @return the class type for this sparse array.
   */
  public Class<T> getType() {
    return type;
  }

  /**
   * A String showing the type, default value, number of elements and
   * the dense shape of this sparse ndarray.
   * @return A string containing the type, default value, number of elements and shape.
   */
  @Override
  public String toString() {
    long numElements = getValues() == null ? 0 : getValues().size();
    String strDefault;
    T defaultVal = getDefaultValue();
    if (defaultVal == null) {
      strDefault = "<null>";
    } else if (defaultVal instanceof Number) {
      strDefault = defaultVal.toString();
    } else {
      strDefault = "'" + defaultVal + "'";
    }
    return this.getClass().getSimpleName() + "(type="+type.getSimpleName()+", defaultValue=" + strDefault
            + ", numElements=" + numElements + ", shape=" + this.shape() + ")";
  }
}
