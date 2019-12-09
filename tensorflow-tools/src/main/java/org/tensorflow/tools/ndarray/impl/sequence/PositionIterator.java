package org.tensorflow.tools.ndarray.impl.sequence;

import java.util.PrimitiveIterator;
import org.tensorflow.tools.ndarray.impl.dimension.DimensionalSpace;

public interface PositionIterator extends PrimitiveIterator.OfLong {

  static PositionIterator create(DimensionalSpace dimensions, int dimensionIdx) {
    if (dimensions.isSegmented()) {
      return new NdPositionIterator(dimensions, dimensionIdx);
    }
    return new SequentialPositionIterator(dimensions, dimensionIdx);
  }

  static IndexedPositionIterator createIndexed(DimensionalSpace dimensions, int dimensionIdx) {
    if (dimensions.isSegmented()) {
      return new NdPositionIterator(dimensions, dimensionIdx);
    }
    return new IndexedSequentialPositionIterator(dimensions, dimensionIdx);
  }

  static PositionIterator sequence(long stride, long end) {
    return new SequentialPositionIterator(stride, end);
  }
}
