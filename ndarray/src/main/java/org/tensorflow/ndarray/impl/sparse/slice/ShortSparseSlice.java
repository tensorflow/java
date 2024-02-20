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
import org.tensorflow.ndarray.ShortNdArray;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.dimension.RelativeDimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.AbstractSparseNdArray;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class ShortSparseSlice extends SparseSlice<Short, ShortNdArray> implements ShortNdArray {

  /**
   * Creates a LongSparseSlice
   *
   * @param source the source Sparse Array that this object slices.
   * @param sourcePosition the relative source position into the source
   * @param dimensions the dimensional space for the window
   */
  public ShortSparseSlice(
      AbstractSparseNdArray<Short, ShortNdArray> source,
      long sourcePosition,
      DimensionalSpace dimensions) {
    super(source, sourcePosition, dimensions);
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray toDense() {
    ShortDataBuffer dataBuffer = DataBuffers.ofShorts(shape().size());
    copyTo(dataBuffer);
    return NdArrays.wrap(shape(), dataBuffer);
  }

  @Override
  public short getShort(long... coordinates) {
    return getObject(coordinates);
  }

  @Override
  public ShortNdArray setShort(short value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public ShortNdArray setObject(Short value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public ShortNdArray set(NdArray<Short> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray copyTo(DataBuffer<Short> dst) {
    // set the values in buf to the default, then overwrite with indices/values
    Short[] defaults = new Short[(int) shape().size()];
    Arrays.fill(defaults, getDefaultValue());
    dst.write(defaults);

    AtomicLong i = new AtomicLong();
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

  @Override
  public ShortNdArray copyTo(ShortDataBuffer dst) {
    return this.copyTo((DataBuffer<Short>) dst);
  }

  @Override
  public ShortNdArray copyFrom(DataBuffer<Short> src) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public ShortNdArray copyFrom(ShortDataBuffer src) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public ShortNdArray slice(Index... indices) {
    if (indices == null) {
      throw new IllegalArgumentException("Slicing requires at least one index");
    }
    RelativeDimensionalSpace sliceDimensions = dimensions().mapTo(indices);
    return slice(sliceDimensions.position(), sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public ShortNdArray slice(long position, DimensionalSpace sliceDimensions) {
    return new ShortSparseSlice(this.source, position + sourcePosition, sliceDimensions);
  }

  @Override
  public ShortNdArray get(long... coordinates) {
    return (ShortNdArray) super.get(coordinates);
  }

  @Override
  public ShortNdArray copyTo(NdArray<Short> dst) {
    return (ShortNdArray) super.copyTo(dst);
  }

  @Override
  public ShortNdArray createDefaultArray() {
    return source.getDefaultArray();
  }
}
