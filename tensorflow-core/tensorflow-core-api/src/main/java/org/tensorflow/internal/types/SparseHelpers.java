package org.tensorflow.internal.types;

import org.tensorflow.SparseTensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.types.TInt64;

abstract class SparseHelpers {

  static DimensionalSpace toDimensionalSpace(TInt64 denseShape) {
    return DimensionalSpace.create(Shape.of(StdArrays.array1dCopyOf(denseShape)));
  }

  static long numBytes(SparseTensor<?> t) {
    return t.indices().numBytes() + t.values().numBytes() + t.denseShape().numBytes();
  }
}
