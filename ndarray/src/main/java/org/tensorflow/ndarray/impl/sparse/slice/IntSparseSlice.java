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

import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.dimension.RelativeDimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.AbstractSparseNdArray;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class IntSparseSlice extends SparseSlice<Integer, IntNdArray> implements IntNdArray {

  /**
   * Creates a IntSparseSlice
   *
   * @param source the source Sparse Array that this object slices.
   * @param sourcePosition the relative source position into the source
   * @param dimensions the dimensional space for the window
   */
  public IntSparseSlice(
      AbstractSparseNdArray<Integer, IntNdArray> source,
      long sourcePosition,
      DimensionalSpace dimensions) {
    super(source, sourcePosition, dimensions);
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray toDense() {
    IntDataBuffer dataBuffer = DataBuffers.ofInts(shape().size());
    copyTo(dataBuffer);
    return NdArrays.wrap(shape(), dataBuffer);
  }

  @Override
  public int getInt(long... coordinates) {
    return getObject(coordinates);
  }

  @Override
  public IntNdArray setInt(int value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public IntNdArray setObject(Integer value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public IntNdArray set(NdArray<Integer> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray copyTo(DataBuffer<Integer> dst) {
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

  @Override
  public IntNdArray copyTo(IntDataBuffer dst) {
    return this.copyTo((DataBuffer<Integer>) dst);
  }

  @Override
  public IntNdArray copyFrom(DataBuffer<Integer> src) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public IntNdArray copyFrom(IntDataBuffer src) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public IntNdArray slice(Index... indices) {
    if (indices == null) {
      throw new IllegalArgumentException("Slicing requires at least one index");
    }
    RelativeDimensionalSpace sliceDimensions = dimensions().mapTo(indices);
    return slice(sliceDimensions.position(), sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public IntNdArray slice(long position, DimensionalSpace sliceDimensions) {
    return new IntSparseSlice(this.source, position + sourcePosition, sliceDimensions);
  }

  @Override
  public IntNdArray get(long... coordinates) {
    return (IntNdArray) super.get(coordinates);
  }

  @Override
  public IntNdArray copyTo(NdArray<Integer> dst) {
    return (IntNdArray) super.copyTo(dst);
  }

  @Override
  public IntNdArray createDefaultArray() {
    return source.getDefaultArray();
  }
}
