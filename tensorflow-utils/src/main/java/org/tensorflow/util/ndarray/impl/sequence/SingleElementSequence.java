package org.tensorflow.util.ndarray.impl.sequence;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.tensorflow.util.ndarray.IllegalRankException;
import org.tensorflow.util.ndarray.NdArray;
import org.tensorflow.util.ndarray.NdArraySequence;
import org.tensorflow.util.ndarray.impl.AbstractNdArray;

class SingleElementSequence<T, U extends NdArray<T>> implements NdArraySequence<U> {

  @Override
  @SuppressWarnings("unchecked")
  public void forEach(Consumer<U> consumer) {
    consumer.accept((U)ndArray);
  }

  @Override
  public void forEachIndexed(BiConsumer<long[], U> consumer) {
    throw new IllegalRankException("Single element has no coordinates to iterate on, use forEach()");
  }

  SingleElementSequence(AbstractNdArray<T, U> ndArray) {
    this.ndArray = ndArray;
  }

  private final AbstractNdArray<T, U> ndArray;
}
