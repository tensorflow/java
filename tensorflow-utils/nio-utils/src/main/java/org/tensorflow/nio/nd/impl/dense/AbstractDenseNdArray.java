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
package org.tensorflow.nio.nd.impl.dense;

import java.util.concurrent.atomic.AtomicLong;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.nd.IllegalRankException;
import org.tensorflow.nio.nd.NdArray;
import org.tensorflow.nio.nd.impl.AbstractNdArray;
import org.tensorflow.nio.nd.impl.dimension.DimensionalSpace;
import org.tensorflow.nio.nd.impl.dimension.RelativeDimensionalSpace;
import org.tensorflow.nio.nd.index.Index;

@SuppressWarnings("unchecked")
public abstract class AbstractDenseNdArray<T, U extends NdArray<T>> extends AbstractNdArray<T, U> {

  @Override
  public U slice(long position, DimensionalSpace dimensions) {
    return instantiate(buffer().offset(position), dimensions);
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
  public T getValue(long... coords) {
    return buffer().get(positionOf(coords, true));
  }

  @Override
  public U set(NdArray<T> src, long... coordinates) {
    src.copyTo((coordinates == null || coordinates.length == 0) ? this : get(coordinates));
    return (U)this;
  }

  @Override
  public U setValue(T value, long... coords) {
    buffer().set(value, positionOf(coords, true));
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

  protected AbstractDenseNdArray(DimensionalSpace dimensions) {
    super(dimensions);
  }

  abstract protected DataBuffer<T> buffer();

  abstract U instantiate(DataBuffer<T> buffer, DimensionalSpace dimensions);

  long positionOf(long[] coords, boolean isValue) {
    if (coords == null || coords.length == 0) {
      return 0;
    }
    if (coords.length > dimensions().numDimensions()) {
      throw new IndexOutOfBoundsException();
    }
    if (isValue && coords.length != dimensions().numDimensions()) {
      throw new IllegalRankException("Not a scalar value");
    }
    return dimensions().positionOf(coords);
  }

  @Override
  protected void slowCopyTo(NdArray<T> array) {
    if (array instanceof AbstractDenseNdArray) {
      AbstractDenseNdArray<T, U> dst = (AbstractDenseNdArray)array;
      AtomicLong off = new AtomicLong();
      scalars().forEach(s -> dst.buffer().set(s.getValue(), off.getAndIncrement()));
    } else {
      super.slowCopyTo(array);
    }
  }
}
