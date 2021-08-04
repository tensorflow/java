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

import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.dimension.RelativeDimensionalSpace;
import org.tensorflow.ndarray.impl.sparse.AbstractSparseNdArray;
import org.tensorflow.ndarray.index.Index;

import java.nio.ReadOnlyBufferException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class DoubleSparseSlice extends SparseSlice<Double, DoubleNdArray> implements DoubleNdArray {

  /**
   * Creates a DoubleSparseSlice
   *
   * @param source the source Sparse Array that this object slices.
   * @param sourcePosition the relative source position into the source
   * @param dimensions the dimensional space for the window
   */
  public DoubleSparseSlice(
      AbstractSparseNdArray<Double, DoubleNdArray> source,
      long sourcePosition,
      DimensionalSpace dimensions) {
    super(source, sourcePosition, dimensions);
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray toDense() {
    DoubleDataBuffer dataBuffer = DataBuffers.ofDoubles(shape().size());
    read(dataBuffer);
    return NdArrays.wrap(shape(), dataBuffer);
  }

  @Override
  public double getDouble(long... coordinates) {
    return getObject(coordinates);
  }

  @Override
  public DoubleNdArray setDouble(double value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public DoubleNdArray setObject(Double value, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public DoubleNdArray set(NdArray<Double> src, long... coordinates) {
    throw new ReadOnlyBufferException();
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray read(DataBuffer<Double> dst) {
    // set the values in buf to the default, then overwrite with indices/values
    Double[] defaults = new Double[(int) shape().size()];
    Arrays.fill(defaults, getDefaultValue());
    dst.write(defaults);

    AtomicInteger i = new AtomicInteger();
    getIndices()
        .elements(0)
        .forEachIndexed(
            (idx, l) -> {
              long[] coordinates = getIndicesCoordinates(l);
              double value = getValues().getDouble(i.getAndIncrement());
              dst.setObject(value, dimensions.positionOf(coordinates));
            });
    return this;
  }

  @Override
  public DoubleNdArray read(DoubleDataBuffer dst) {
    return read((DataBuffer<Double>) dst);
  }

  @Override
  public DoubleNdArray write(DataBuffer<Double> src) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public DoubleNdArray write(DoubleDataBuffer src) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public DoubleNdArray slice(Index... indices) {
    if (indices == null) {
      throw new IllegalArgumentException("Slicing requires at least one index");
    }
    RelativeDimensionalSpace sliceDimensions = dimensions().mapTo(indices);
    return slice(sliceDimensions.position(), sliceDimensions);
  }

  /** {@inheritDoc} */
  @Override
  public DoubleNdArray slice(long position, DimensionalSpace sliceDimensions) {
    return new DoubleSparseSlice(this.source, position + sourcePosition, sliceDimensions);
  }

  @Override
  public DoubleNdArray get(long... coordinates) {
    return (DoubleNdArray) super.get(coordinates);
  }

  @Override
  public DoubleNdArray copyTo(NdArray<Double> dst) {
    return (DoubleNdArray) super.copyTo(dst);
  }

  @Override
  public DoubleNdArray createDefaultArray() {
    return source.getDefaultArray();
  }
}
