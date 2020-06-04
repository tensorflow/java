/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================
 */
package org.tensorflow.tools.ndarray.impl.dense;

import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.DataBufferWindow;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.NdArraySequence;
import org.tensorflow.tools.ndarray.impl.AbstractNdArray;
import org.tensorflow.tools.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.tools.ndarray.impl.dimension.RelativeDimensionalSpace;
import org.tensorflow.tools.ndarray.impl.sequence.SlicingElementSequence;
import org.tensorflow.tools.ndarray.impl.sequence.SingleElementSequence;
import org.tensorflow.tools.ndarray.impl.sequence.FastElementSequence;
import org.tensorflow.tools.ndarray.index.Index;

@SuppressWarnings("unchecked")
public abstract class AbstractDenseNdArray<T, U extends NdArray<T>> extends AbstractNdArray<T, U> {

  @Override
  public NdArraySequence<U> elements(int dimensionIdx) {
    if (dimensionIdx >= shape().numDimensions()) {
      throw new IllegalArgumentException("Cannot iterate elements in dimension '" + dimensionIdx +
          "' of array with shape " + shape());
    }
    if (rank() == 0 && dimensionIdx < 0) {
      return new SingleElementSequence<>(this);
    }
    DimensionalSpace elemDims = dimensions().from(dimensionIdx + 1);
    try {
      DataBufferWindow<? extends DataBuffer<T>> elemWindow = buffer().window(elemDims.physicalSize());
      U element = instantiate(elemWindow.buffer(), elemDims);
      return new FastElementSequence(this, dimensionIdx, element, elemWindow);
    } catch (UnsupportedOperationException e) {
      // If buffer windows are not supported, fallback to slicing (and slower) sequence
      return new SlicingElementSequence<>(this, dimensionIdx, elemDims);
    }
  }

  @Override
  public U slice(long position, DimensionalSpace sliceDimensions) {
    DataBuffer<T> sliceBuffer = buffer().slice(position, sliceDimensions.physicalSize());
    return instantiate(sliceBuffer, sliceDimensions);
  }

  @Override
  public U slice(Index... indices) {
    if (indices == null) {
      throw new IllegalArgumentException("Slicing requires at least one index");
    }
    RelativeDimensionalSpace sliceDimensions = dimensions().mapTo(indices);
    return slice(sliceDimensions.position(), sliceDimensions);
  }

  @Override
  public U get(long... coords) {
    return slice(positionOf(coords, false), dimensions().from(coords.length));
  }

  @Override
  public T getObject(long... coords) {
    return buffer().getObject(positionOf(coords, true));
  }

  @Override
  public U set(NdArray<T> src, long... coordinates) {
    src.copyTo((coordinates == null || coordinates.length == 0) ? this : get(coordinates));
    return (U)this;
  }

  @Override
  public U setObject(T value, long... coords) {
    buffer().setObject(value, positionOf(coords, true));
    return (U)this;
  }

  @Override
  public U read(DataBuffer<T> dst) {
    Validator.readToBufferArgs(this, dst);
    DataTransfer.execute(buffer(), dimensions(), dst, DataTransfer::ofValue);
    return (U)this;
  }

  @Override
  public U write(DataBuffer<T> src) {
    Validator.writeFromBufferArgs(this, src);
    DataTransfer.execute(src, buffer(), dimensions(), DataTransfer::ofValue);
    return (U)this;
  }

  @Override
  public int hashCode() {
    if (dimensions().isSegmented()) {
      return slowHashCode();
    }
    final int prime = 31;
    int result = 1;
    result = prime * result + buffer().hashCode();
    result = prime * result + shape().hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof AbstractDenseNdArray)) {
      return super.equals(obj);
    }
    AbstractDenseNdArray<?, ?> other = (AbstractDenseNdArray<?, ?>)obj;
    if (dimensions().isSegmented() || other.dimensions().isSegmented()) {
      return slowEquals(other);
    }
    if (!shape().equals(other.shape())) {
      return false;
    }
    return buffer().equals(other.buffer());
  }

  protected AbstractDenseNdArray(DimensionalSpace dimensions) {
    super(dimensions);
  }

  abstract protected DataBuffer<T> buffer();

  abstract U instantiate(DataBuffer<T> buffer, DimensionalSpace dimensions);

  long positionOf(long[] coords, boolean isValue) {
    if (coords == null || coords.length == 0) {
      return 0;
    }
    Validator.coordinates(dimensions, coords, isValue);
    return dimensions.positionOf(coords);
  }

  @Override
  protected void slowCopyTo(NdArray<T> array) {
    if (array instanceof AbstractDenseNdArray) {
      AbstractDenseNdArray<T, U> dst = (AbstractDenseNdArray)array;
      long offset = 0L;
      for (NdArray<T> s : scalars()) {
        dst.buffer().setObject(s.getObject(), offset++);
      }
    } else {
      super.slowCopyTo(array);
    }
  }
}
