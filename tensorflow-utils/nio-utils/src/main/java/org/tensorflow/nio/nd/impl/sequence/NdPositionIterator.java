package org.tensorflow.nio.nd.impl.sequence;

import org.tensorflow.nio.nd.impl.dimension.DimensionalSpace;

class NdPositionIterator implements IndexedPositionIterator {

  @Override
  public boolean hasNext() {
    return coords != null;
  }

  @Override
  public long nextLong() {
    long position = dimensions.positionOf(coords);
    increment();
    return position;
  }

  @Override
  public void forEachIndexed(CoordsLongConsumer consumer) {
    while (hasNext()) {
      consumer.consume(coords, dimensions.positionOf(coords));
      increment();
    }
  }

  private void increment() {
    if (!increment(coords, dimensions)) {
      coords = null;
    }
  }

  static boolean increment(long[] coords, DimensionalSpace dimensions) {
    for (int i = coords.length - 1; i >= 0; --i) {
      if ((coords[i] = (coords[i] + 1) % dimensions.get(i).numElements()) > 0) {
        return true;
      }
    }
    return false;
  }

  NdPositionIterator(DimensionalSpace dimensions, int dimensionIdx) {
    this.dimensions = dimensions;
    this.coords = new long[dimensionIdx + 1];
  }

  private final DimensionalSpace dimensions;
  private long[] coords;
}
