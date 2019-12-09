package org.tensorflow.tools.ndarray.impl.sequence;

import org.tensorflow.tools.ndarray.impl.dimension.DimensionalSpace;

class IndexedSequentialPositionIterator extends SequentialPositionIterator implements IndexedPositionIterator {

  @Override
  public void forEachIndexed(CoordsLongConsumer consumer) {
    while (hasNext()) {
      consumer.consume(coords, nextLong());
      NdPositionIterator.increment(coords, dimensions);
    }
  }

  IndexedSequentialPositionIterator(DimensionalSpace dimensions, int dimensionIdx) {
    super(dimensions, dimensionIdx);
    this.dimensions = dimensions;
    this.coords = new long[dimensionIdx + 1];
  }

  private final DimensionalSpace dimensions;
  private final long[] coords;
}
