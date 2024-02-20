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

import org.tensorflow.ndarray.BooleanNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.dimension.RelativeDimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.AbstractSparseNdArray;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;
import java.util.concurrent.atomic.AtomicInteger;

public class BooleanSparseSlice extends SparseSlice<Boolean, BooleanNdArray>
    implements BooleanNdArray {

  /**
   * Creates a BooleanSparseSlice
   *
   * @param source the source Sparse Array that this object slices.
   * @param sourcePosition the relative source position into the source
   * @param dimensions the dimensional space for the window
   */
  public BooleanSparseSlice(
      AbstractSparseNdArray<Boolean, BooleanNdArray> source,
      long sourcePosition,
      DimensionalSpace dimensions) {
    super(source, sourcePosition, dimensions);
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray toDense() {
    BooleanDataBuffer dataBuffer = DataBuffers.ofBooleans(shape().size());
    copyTo(dataBuffer);
    return NdArrays.wrap(shape(), dataBuffer);
  }

  @Override
  public boolean getBoolean(long... coordinates) {
    return getObject(coordinates);
  }

  @Override
  public BooleanNdArray setBoolean(boolean value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public BooleanNdArray setObject(Boolean value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public BooleanNdArray set(NdArray<Boolean> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray copyTo(DataBuffer<Boolean> dst) {
    // zero out buf.
    Boolean[] defaults = new Boolean[(int) shape().size()];
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

  @Override
  public BooleanNdArray copyTo(BooleanDataBuffer dst) {
    return copyTo((DataBuffer<Boolean>) dst);
  }

  @Override
  public BooleanNdArray copyFrom(DataBuffer<Boolean> src) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public BooleanNdArray copyFrom(BooleanDataBuffer src) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public BooleanNdArray slice(Index... indices) {
    if (indices == null) {
      throw new IllegalArgumentException("Slicing requires at least one index");
    }
    RelativeDimensionalSpace sliceDimensions = dimensions().mapTo(indices);
    return slice(sliceDimensions.position(), sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public BooleanNdArray slice(long position, DimensionalSpace sliceDimensions) {
    return new BooleanSparseSlice(this.source, position + sourcePosition, sliceDimensions);
  }

  @Override
  public BooleanNdArray get(long... coordinates) {
    return (BooleanNdArray) super.get(coordinates);
  }

  @Override
  public BooleanNdArray copyTo(NdArray<Boolean> dst) {
    return (BooleanNdArray) super.copyTo(dst);
  }

  @Override
  public BooleanNdArray createDefaultArray() {
    return source.getDefaultArray();
  }
}
