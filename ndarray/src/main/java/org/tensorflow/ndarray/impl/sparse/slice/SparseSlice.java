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
package org.tensorflow.ndarray.impl.sparse.slice;

import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArraySequence;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.dimension.RelativeDimensionalSpace;
import org.tensorflow.ndarray.impl.sequence.SingleElementSequence;
import org.tensorflow.ndarray.impl.sequence.SlicingElementSequence;
import org.tensorflow.ndarray.impl.sparse.AbstractSparseNdArray;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;

/**
 * A sparse window is a view into an AbstractSparseNdArray. It is used internally by the slice
 * methods.
 *
 * @param <T> the type that the array contains
 * @param <U> the type of dense NdArray
 */
public abstract class SparseSlice<T, U extends NdArray<T>> extends AbstractSparseNdArray<T, U> {
  protected final AbstractSparseNdArray<T, U> source;
  protected final long sourcePosition;

  /**
   * Creates a SparseSlice
   *
   * @param source the source Sparse Array that this object slices.
   * @param sourcePosition the relative position into the source array
   * @param dimensions the dimensional space for the window
   */
  public SparseSlice(
      AbstractSparseNdArray<T, U> source, long sourcePosition, DimensionalSpace dimensions) {
    super(source.getDefaultValue(), dimensions);
    this.source = source;
    this.sourcePosition = sourcePosition;
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + source.hashCode();
    result = prime * result + (int) sourcePosition;
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof SparseSlice)) {
      return super.equals(obj);
    }
    SparseSlice<?, ?> other = (SparseSlice<?, ?>) obj;
    if (!source.equals(other.source)) {
      return false;
    }
    if (!shape().equals(other.shape())) {
      return false;
    }
    return sourcePosition == other.sourcePosition;
  }

  /** {@inheritDoc} */
  @Override
  public T getObject(long... coordinates) {
    long position = dimensions().positionOf(coordinates);
    long[] sourceCoordinates = toCoordinates(source.dimensions(), sourcePosition + position);
    return source.getObject(sourceCoordinates);
  }

  /** {@inheritDoc} */
  @Override
  public NdArray<T> get(long... coordinates) {
    long position = dimensions().positionOf(coordinates);
    long[] sourceCoordinates = toCoordinates(source.dimensions(), sourcePosition + position);
    return source.get(sourceCoordinates);
  }

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
   * Converts the sparse window to a dense NdArray
   *
   * @return the NdArray
   */
  public abstract U toDense();

  /** {@inheritDoc} */
  @Override
  public NdArray<T> write(DataBuffer<T> src) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public U createValues(Shape shape) {
    return source.createValues(shape);
  }
}
