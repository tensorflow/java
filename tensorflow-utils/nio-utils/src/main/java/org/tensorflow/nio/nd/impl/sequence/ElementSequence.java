package org.tensorflow.nio.nd.impl.sequence;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.tensorflow.nio.nd.NdArray;
import org.tensorflow.nio.nd.NdArraySequence;
import org.tensorflow.nio.nd.impl.AbstractNdArray;
import org.tensorflow.nio.nd.impl.dimension.DimensionalSpace;

public class ElementSequence<T, U extends NdArray<T>> implements NdArraySequence<U> {

  public static <T, U extends NdArray<T>> NdArraySequence<U> create(AbstractNdArray<T, U> ndArray, int dimensionIdx) {
    if (ndArray.rank() == 0 && dimensionIdx < 0) {
      return new SingleElementSequence<>(ndArray);
    }
    return new ElementSequence<>(ndArray, dimensionIdx);
  }

  @Override
  public void forEach(Consumer<U> consumer) {
    DimensionalSpace elementDimensions = ndArray.dimensions().from(dimensionIdx + 1);
    PositionIterator.create(ndArray.dimensions(), dimensionIdx).forEachRemaining((long position) ->
        consumer.accept(ndArray.slice(position, elementDimensions))
    );
  }

  @Override
  public void forEachIndexed(BiConsumer<long[], U> consumer) {
    DimensionalSpace elementDimensions = ndArray.dimensions().from(dimensionIdx + 1);
    PositionIterator.createIndexed(ndArray.dimensions(), dimensionIdx).forEachIndexed((long[] coords, long position) ->
        consumer.accept(coords, ndArray.slice(position, elementDimensions))
    );
  }

  private ElementSequence(AbstractNdArray<T, U> ndArray, int dimensionIdx) {
    this.ndArray = ndArray;
    this.dimensionIdx = dimensionIdx;
  }

  private final AbstractNdArray<T, U> ndArray;
  private final int dimensionIdx;
}
