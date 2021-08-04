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

import org.tensorflow.ndarray.IllegalRankException;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArraySequence;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.SparseNdArray;
import org.tensorflow.ndarray.impl.AbstractNdArray;
import org.tensorflow.ndarray.impl.dense.AbstractDenseNdArray;
import org.tensorflow.ndarray.impl.dimension.Dimension;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.dimension.RelativeDimensionalSpace;
import org.tensorflow.ndarray.impl.sequence.SingleElementSequence;
import org.tensorflow.ndarray.impl.sequence.SlicingElementSequence;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

/**
 * Abstract base class for sparse array.
 *
 * <p>A sparse array as two separate dense arrays: indices, values, and a shape that represents the
 * dense shape.
 *
 * <p><em>NOTE:</em> all Sparse Arrays are readonly for the {@link #set(NdArray, long...)} and
 * {@link #setObject(Object, long...)} methods
 *
 * <pre>{@code
 * FloatSparseNdArray st = new FloatSparseNdArray(
 *      StdArrays.of(new long[][] {{0, 0}, {1, 2}}),
 *      NdArrays.vectorOf(1f, 2f),
 *      Shape.of(3, 4));
 *
 * }</pre>
 *
 * <p>represents the dense array:
 *
 * <pre>{@code
 * [[1, 0, 0, 0]
 *  [0, 0, 2, 0]
 *  [0, 0, 0, 0]]
 *
 * }</pre>
 *
 * @param <T> the type that the array contains
 * @param <U> the type of dense NdArray
 */
public abstract class AbstractSparseNdArray<T, U extends NdArray<T>> extends AbstractNdArray<T, U>
    implements SparseNdArray<T, U> {
  /**
   * A 2-D long array of shape {@code [N, ndims]}, that specifies the indices of the elements in the
   * sparse array that contain non-default values (elements are zero-indexed).
   *
   * <p>For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   * coordinates {@code [1,3]} and {@code [2,4]} have non-default values.
   */
  private LongNdArray indices;

  /**
   * A 1-D array of any type and shape {@code [N]}, that supplies the values for each element in
   * indices.
   *
   * <p>For example, given {@code indices=[[1,3], [2,4]]}, and {@code values=[18, 3.6]} specifies
   * that element {@code [1,3]} of the sparse array has a value of {@code 18}, and element {@code
   * [2,4]} of the sparse array has a value of {@code 3.6}.
   */
  private U values;

  /**
   * Scalar value to set for indices not specified in {@link #getIndices()} This will default to
   * zero, false, or the empty string depending on the data type of the values.
   */
  private T defaultValue;

  /**
   * Scalar NdArray to use for indices not specified in {@link #getIndices()} This will default to
   * zero, false, or the empty string depending on the data type of the values, otherwise it will
   * contain the defaultValue.
   */
  private U defaultArray;

  /**
   * Creates an abstract SparseNdArray
   *
   * @param indices A 2-D LongNdArray of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse array that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D NdArray of any type and shape {@code [N]}, which supplies the values for
   *     each element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter
   *     {@code values=[18, 3.6]} specifies that element {@code [1,3]} of the sparse NdArray has a
   *     value of {@code 18}, and element {@code [2,4]} of the NdArray has a value of {@code 3.6}.
   * @param defaultValue Scalar value to set for indices not specified in {@link #indices}
   * @param dimensions the dimensional space for the dense object represented by this sparse array.
   */
  protected AbstractSparseNdArray(
      LongNdArray indices, U values, T defaultValue, DimensionalSpace dimensions) {
    super(dimensions);
    this.indices = indices;
    this.values = values;
    setDefaultValue(defaultValue);

    // sanity checks on shapes, indices (shape = {@code [N, ndims]}, where N is the number of values
    // (shape = {@code [N]}}.
    if (this.indices.shape().get(0) != this.values.shape().get(0)) {
      throw new IllegalArgumentException(
          String.format(
              "The number of rows in indices (%d) does not  match the number of elements in values(%d).",
              this.indices.shape().get(0), this.values.shape().get(0)));
    }

    // sanity checks on shapes, indices (shape = {@code [N, ndims]}, where ndims = the number of
    // dimensions in the dense shape.
    if (this.indices.shape().get(1) != shape().numDimensions()) {
      throw new IllegalArgumentException(
          String.format(
              "The number of columns in indices (%d) does not  match the number of dimensions in shape (%d).",
              this.indices.shape().get(1), shape().get(0)));
    }
  }

  /**
   * Creates an abstract SparseNdArray
   *
   * @param defaultValue Scalar value to set for indices not specified in {@link #getIndices()}
   * @param dimensions the dimensional space for the dense object represented by this sparse array,
   */
  protected AbstractSparseNdArray(T defaultValue, DimensionalSpace dimensions) {
    super(dimensions);
    setDefaultValue(defaultValue);
  }

  /** {@inheritDoc} */
  @Override
  public NdArraySequence<U> elements(int dimensionIdx) {
    if (dimensionIdx >= shape().numDimensions()) {
      throw new IllegalArgumentException(
          "Cannot iterate elements in dimension '"
              + dimensionIdx
              + "' of array with shape "
              + shape());
    }
    if (rank() == 0 && dimensionIdx < 0) {
      return new SingleElementSequence<>(this);
    }
    DimensionalSpace elemDims = dimensions().from(dimensionIdx + 1);

    return new SlicingElementSequence<>(this, dimensionIdx, elemDims);
  }

  /**
   * Computes the coordinates based on a relative position to the beginning of the dimension space.
   *
   * @param dimensions the dimension space
   * @param position relative position to the beginning of the dimension space.
   * @return the coordinates
   */
  // TODO should have automatical access to the coordinates from which this position is coming from.
  //  But that will require some refactoring even at the dense level.
  protected long[] toCoordinates(DimensionalSpace dimensions, long position) {
    long[] result = new long[dimensions.numDimensions()];
    long p = position;

    for (int dim = 0; dim < dimensions.numDimensions(); dim++) {
      Dimension dimension = dimensions.get(dim);
      result[dim] = p / dimension.elementSize();
      p = p % dimension.elementSize();
    }
    return result;
  }

  /**
   * Converts the given set of indices coordinates to a long array of coordinates.
   *
   * <p>The shape of the NdArray is {@code [ndims]}
   *
   * @param l the LongNdArray containing the coordinates
   * @return the long array containing the coordinates.
   */
  protected long[] getIndicesCoordinates(LongNdArray l) {
    long[] results = new long[(int) l.size()];
    for (int i = 0; i < l.size(); i++) {
      results[i] = l.getLong(i);
    }
    return results;
  }

  /**
   * Converts this sparse array to a dense array.
   *
   * @return the dense array.
   */
  public abstract U toDense();

  /** {@inheritDoc} */
  @Override
  public NdArray<T> slice(Index... indices) {
    if (indices == null) {
      throw new IllegalArgumentException("Slicing requires at least one index");
    }
    RelativeDimensionalSpace sliceDimensions = dimensions().mapTo(indices);
    return slice(sliceDimensions.position(), sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public NdArray<T> get(long... coordinates) {
    return slice(positionOf(coordinates, false), dimensions().from(coordinates.length));
  }

  /** {@inheritDoc} */
  @Override
  public T getObject(long... coordinates) {
    if (coordinates.length != shape().numDimensions()) {
      throw new IllegalRankException(
          String.format(
              "Length of coordinates (%s)%s does not match the rank %d",
              coordinates.length, Arrays.toString(coordinates), shape().numDimensions()));
    }
    long index = locateIndex(coordinates);
    if (index >= 0) {
      return getValues().getObject(index);
    } else {
      return defaultValue;
    }
  }

  /** {@inheritDoc} */
  @Override
  public NdArray<T> setObject(T value, long... coords) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public NdArray<T> set(NdArray<T> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /**
   * Creates a dense array of the type that this sparse array represents.
   *
   * @param shape the shape of the dense array.
   * @return the dense of the type that this sparse array represents.
   */
  public abstract U createValues(Shape shape);

  /** {@inheritDoc} */
  @Override
  public NdArray<T> copyTo(NdArray<T> dst) {
    if (dst instanceof AbstractSparseNdArray) {
      AbstractSparseNdArray<T, U> sparse = (AbstractSparseNdArray<T, U>) dst;
      LongNdArray indicesCopy = NdArrays.ofLongs(indices.shape());
      this.indices.copyTo(indicesCopy);
      U valuesCopy = createValues(values.shape());
      this.values.copyTo(valuesCopy);
      sparse.setIndices(indicesCopy);
      sparse.setValues(valuesCopy);
    } else {
      U dense = toDense();
      dense.copyTo(dst);
    }
    return this;
  }

  /**
   * Computes the position within the dense array given by the coordinates
   *
   * @param coords the coordinates within the dense array
   * @param isValue indicator whether the coordinates represents a value or higher level dimension.
   * @return the position within the array
   */
  protected long positionOf(long[] coords, boolean isValue) {
    if (coords == null || coords.length == 0) {
      return 0;
    }
    Validator.coordinates(dimensions, coords, isValue);
    return dimensions.positionOf(coords);
  }

  /** {@inheritDoc} */
  @Override
  protected void slowCopyTo(NdArray<T> array) {
    if (array instanceof AbstractDenseNdArray) {
      AbstractDenseNdArray<T, U> dst = (AbstractDenseNdArray<T, U>) array;
      long offset = 0L;
      for (NdArray<T> s : scalars()) {
        dst.setObject(s.getObject(), offset++);
      }
    } else if (array instanceof AbstractSparseNdArray) {
      AbstractSparseNdArray<T, U> dst = (AbstractSparseNdArray<T, U>) array;
      indices.copyTo(dst.getIndices());
      values.copyTo(dst.values);
    } else {
      super.slowCopyTo(array);
    }
  }

  /**
   * Gets the Indices
   *
   * @return the Indices
   */
  public LongNdArray getIndices() {
    return indices;
  }

  /**
   * Sets the Indices
   *
   * @param indices the Indices
   */
  public void setIndices(LongNdArray indices) {
    this.indices = indices;
  }

  /**
   * Gets the values
   *
   * @return the values
   */
  public U getValues() {
    return values;
  }

  /**
   * Sets the values
   *
   * @param values the values
   */
  public void setValues(U values) {
    this.values = values;
  }

  /**
   * Gets the values index by coordinates
   *
   * @param coordinates the coordinates to locate
   * @return index of the coordinates, if the coordinates are contained in the {@code indices}
   *     array; otherwise, {@code (-(insertion point) - 1)}. The insertion point is defined as the
   *     point at which the {@code coordinates} would be inserted into the {@code indices} array:
   *     the index of the first element greater than the key, or {@code indices.shape().get(0)}; if
   *     all elements in the array are less than the specified key. Note that this guarantees that
   *     the return value will be {@code >= 0}, only if the coordinates are found.
   */
  protected long locateIndex(long[] coordinates) {
    long size = indices.shape().get(0);
    LongNdArray coordArray = NdArrays.vectorOf(coordinates);
    return binarySearch(size, coordArray);
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    if (dimensions().isSegmented()) {
      return slowHashCode();
    }
    final int prime = 31;
    int result = 1;
    result = prime * result + indices.hashCode();
    result = prime * result + values.hashCode();
    result = prime * result + shape().hashCode();
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof AbstractSparseNdArray)) {
      return super.equals(obj);
    }
    AbstractSparseNdArray<?, ?> other = (AbstractSparseNdArray<?, ?>) obj;
    if (!shape().equals(other.shape())) {
      return false;
    }
    if (!indices.equals(other.indices)) {
      return false;
    }
    return values.equals(other.values);
  }

  /**
   * Performs a binary search on the indices array to locate the index of the specified coordinates.
   * The indices array must be sorted by coordinates, row major.
   *
   * @param toIndex the index of the last element (exclusive) to be searched
   * @param coordinates the coordinates to locate
   * @return index of the coordinates, if the coordinates are contained in the {@code indices}
   *     array; otherwise, {@code (-(insertion point) - 1)}. The insertion point is defined as the
   *     point at which the {@code coordinates} would be inserted into the {@code indices} array:
   *     the index of the first element greater than the key, or {@code indices.shape().get(0)}; if
   *     all elements in the array are less than the specified key. Note that this guarantees that
   *     the return value will be @{code >= 0}, only if the coordinates are found.
   */
  private long binarySearch(long toIndex, LongNdArray coordinates) {

    long low = 0;
    long high = toIndex - 1;

    while (low <= high) {
      long mid = (low + high) >>> 1;
      LongNdArray comparable = indices.get(mid);
      int rc = compareCoordinates(comparable, coordinates);
      if (rc < 0) { // less than
        low = mid + 1;
      } else if (rc > 0) { // higher than
        high = mid - 1;
      } else { // match
        return mid;
      }
    }
    return -(low + 1); // no match
  }

  /**
   * Sorts the indices and values in ascending row-major coordinates.
   *
   * @return this instance
   */
  @SuppressWarnings("UnusedReturnValue")
  public AbstractSparseNdArray<T, U> sortIndicesAndValues() {

    // indices will contain the indexes into the indices and values ndArrays, resorted.
    List<Long> indexes = new ArrayList<>();
    //  create a range for the length of values
    LongStream.range(0, values.size()).forEach(indexes::add);

    // then sort this range based on ascending row-wise coordinates.
    indexes.sort((a, b) -> compareCoordinates(indices.get(a), indices.get(b)));

    LongNdArray newIndices = NdArrays.ofLongs(indices.shape());
    U newValues = createValues(values.shape());
    // used the sorted indexes to set up the sorted Indices and Values
    for (long i = 0; i < indexes.size(); i++) {
      long moveIndex = indexes.get((int) i);
      newIndices.set(indices.get(moveIndex), i);
      newValues.setObject(values.getObject(moveIndex), i);
    }
    indices = newIndices;
    values = newValues;
    return this;
  }

  /**
   * Compares its two arguments for row major coordinate order.
   *
   * @return a negative integer, zero, or a positive integer as the first argument is less than,
   *     equal to, or greater than the second.
   */
  private int compareCoordinates(LongNdArray a, LongNdArray b) {
    int rc = (int) (a.size() - b.size());
    if (rc != 0) {
      return rc;
    }

    for (long i = 0; i < a.size(); i++) {
      long l = a.getLong(i);
      rc = (int) (l - b.getLong(i));
      if (rc != 0) {
        return rc;
      }
    }
    return 0;
  }

  /**
   * Scalar value to set for indices not specified in {@link #indices}, defaults to zero, false, or
   * the empty String depending on the data type.
   */
  public T getDefaultValue() {
    return defaultValue;
  }

  /**
   * Sets the defaultValue
   *
   * @param defaultValue the default value
   */
  public void setDefaultValue(T defaultValue) {
    this.defaultValue = defaultValue;
    defaultArray = null;
  }

  /**
   * Creates the NdArray with the default value as a scalar
   *
   * @return the default NdArray of the default value as a scalar
   */
  public abstract U createDefaultArray();

  /**
   * Scalar NdArray to use for indices not specified in {@link #getIndices()} This will default to
   * zero, false, or the empty string depending on the data type of the values, otherwise it will
   * contain the {@link #defaultValue}.
   */
  public U getDefaultArray() {
    if (defaultArray == null) {
      defaultArray = createDefaultArray();
    }
    return defaultArray;
  }
}
