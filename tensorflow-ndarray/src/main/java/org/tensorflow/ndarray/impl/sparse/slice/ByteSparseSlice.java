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

import org.tensorflow.ndarray.ByteNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.dimension.RelativeDimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.AbstractSparseNdArray;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;
import java.util.concurrent.atomic.AtomicInteger;

public class ByteSparseSlice extends SparseSlice<Byte, ByteNdArray> implements ByteNdArray {

  /**
   * Creates a ByteSparseSlice
   *
   * @param source the source Sparse Array that this object slices.
   * @param sourcePosition the relative source position into the source
   * @param dimensions the dimensional space for the window
   */
  public ByteSparseSlice(
      AbstractSparseNdArray<Byte, ByteNdArray> source,
      long sourcePosition,
      DimensionalSpace dimensions) {
    super(source, sourcePosition, dimensions);
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray toDense() {
    ByteDataBuffer dataBuffer = DataBuffers.ofBytes(shape().size());
    copyTo(dataBuffer);
    return NdArrays.wrap(shape(), dataBuffer);
  }

  @Override
  public byte getByte(long... coordinates) {
    return getObject(coordinates);
  }

  @Override
  public ByteNdArray setByte(byte value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public ByteNdArray setObject(Byte value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public ByteNdArray set(NdArray<Byte> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray copyTo(DataBuffer<Byte> dst) {
    // zero out buf.
    Byte[] defaults = new Byte[(int) shape().size()];
    dst.write(defaults);

    AtomicInteger i = new AtomicInteger();
    getIndices()
        .elements(0)
        .forEachIndexed(
            (idx, l) -> {
              long[] coordinates = getIndicesCoordinates(l);
              byte value = getValues().getByte(i.getAndIncrement());
              dst.setObject(value, dimensions.positionOf(coordinates));
            });
    return this;
  }

  @Override
  public ByteNdArray copyTo(ByteDataBuffer dst) {
    return this.copyTo((DataBuffer<Byte>) dst);
  }

  @Override
  public ByteNdArray copyFrom(DataBuffer<Byte> src) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public ByteNdArray copyFrom(ByteDataBuffer src) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public ByteNdArray slice(Index... indices) {
    if (indices == null) {
      throw new IllegalArgumentException("Slicing requires at least one index");
    }
    RelativeDimensionalSpace sliceDimensions = dimensions().mapTo(indices);
    return slice(sliceDimensions.position(), sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public ByteNdArray slice(long position, DimensionalSpace sliceDimensions) {
    return new ByteSparseSlice(this.source, position + sourcePosition, sliceDimensions);
  }

  @Override
  public ByteNdArray get(long... coordinates) {
    return (ByteNdArray) super.get(coordinates);
  }

  @Override
  public ByteNdArray copyTo(NdArray<Byte> dst) {
    return (ByteNdArray) super.copyTo(dst);
  }

  @Override
  public ByteNdArray createDefaultArray() {
    return source.getDefaultArray();
  }
}
