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
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.dimension.RelativeDimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.SparseNdArray;
import org.tensorflow.ndarray.index.Index;

import java.lang.reflect.Array;
import java.nio.ReadOnlyBufferException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ObjectSparseSlice<T, U extends NdArray<T>> extends SparseSlice<T, U>
    implements NdArray<T> {

  /**
   * Creates a BooleanSparseSlice
   *
   * @param source the source Sparse Array that this object slices.
   * @param sourcePosition the relative source position into the source
   * @param dimensions the dimensional space for the window
   */
  public ObjectSparseSlice(
      SparseNdArray<T, U> source, long sourcePosition, DimensionalSpace dimensions) {
    super(source, sourcePosition, dimensions);
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public U toDense() {
    DataBuffer<T> dataBuffer = DataBuffers.ofObjects(getType(), shape().size());
    copyTo(dataBuffer);
    // unchecked NdArray<T> to U
    return (U) NdArrays.wrap(shape(), dataBuffer);
  }

  @Override
  public U setObject(T value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public U set(NdArray<T> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public U copyTo(DataBuffer<T> dst) {
    // unchecked Object to T[]
    T[] defaults = (T[]) Array.newInstance(getType(), (int) dst.size());
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
    // Unchecked cast ObjectSparseSlice<T,U> to U
    return (U) this;
  }

  @Override
  public U slice(Index... indices) {
    if (indices == null) {
      throw new IllegalArgumentException("Slicing requires at least one index");
    }
    RelativeDimensionalSpace sliceDimensions = dimensions().mapTo(indices);
    return slice(sliceDimensions.position(), sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public U slice(long position, DimensionalSpace sliceDimensions) {
    // unchecked ObjectSparseSlice<T,U> to U
    return (U)
        new ObjectSparseSlice<>(
            (SparseNdArray<T, U>) this.source, position + sourcePosition, sliceDimensions);
  }

  @Override
  public U createDefaultArray() {
    return source.getDefaultArray();
  }

  public Class<T> getType() {
    return ((SparseNdArray<T, U>) source).getType();
  }
}
