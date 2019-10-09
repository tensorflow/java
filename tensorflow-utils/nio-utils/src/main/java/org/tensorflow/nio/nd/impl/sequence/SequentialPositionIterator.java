package org.tensorflow.nio.nd.impl.sequence;

import org.tensorflow.nio.nd.impl.dimension.DimensionalSpace;

class SequentialPositionIterator implements PositionIterator {

  @Override
  public boolean hasNext() {
    return index < end;
  }

  @Override
  public long nextLong() {
    return stride * index++;
  }

  SequentialPositionIterator(DimensionalSpace dimensions, int dimensionIdx) {
    long size = 1;
    for (int i = 0; i <= dimensionIdx; ++i) {
      size *= dimensions.get(i).numElements();
    }
    this.stride = dimensions.get(dimensionIdx).elementSize();
    this.end = size;
  }

  SequentialPositionIterator(long stride, long end) {
    this.stride = stride;
    this.end = end;
  }

  private final long stride;
  private final long end;
  private long index;
}
