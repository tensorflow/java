package org.tensorflow.tools.ndarray.impl.sequence;

import java.util.Iterator;
import java.util.function.BiConsumer;
import org.tensorflow.tools.ndarray.IllegalRankException;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.NdArraySequence;
import org.tensorflow.tools.ndarray.impl.AbstractNdArray;

class SingleElementSequence<T, U extends NdArray<T>> implements NdArraySequence<U> {

  @Override
  public Iterator<U> iterator() {
    return new Iterator<U>() {

      @Override
      public boolean hasNext() {
        return element != null;
      }

      @Override
      public U next() {
        U ret = element;
        element = null;
        return ret;
      }

      @SuppressWarnings("unchecked")
      private U element = (U)ndArray;
    };
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
