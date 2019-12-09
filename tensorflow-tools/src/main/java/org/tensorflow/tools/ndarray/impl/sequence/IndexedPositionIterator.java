package org.tensorflow.tools.ndarray.impl.sequence;

public interface IndexedPositionIterator extends PositionIterator {

  @FunctionalInterface
  interface CoordsLongConsumer {
    void consume(long[] coords, long position);
  }

  void forEachIndexed(CoordsLongConsumer consumer);
}
