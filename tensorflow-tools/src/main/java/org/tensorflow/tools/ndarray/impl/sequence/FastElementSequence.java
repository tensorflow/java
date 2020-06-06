/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */

package org.tensorflow.tools.ndarray.impl.sequence;

import java.util.Iterator;
import java.util.function.BiConsumer;

import org.tensorflow.tools.buffer.DataBufferWindow;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.NdArraySequence;
import org.tensorflow.tools.ndarray.impl.AbstractNdArray;

/**
 * A sequence recycling the same {@code NdArray} instance when iterating its elements
 *
 * @param <T> Type of the elements
 * @param <U> Type of the {@code NdArray} with this sequence
 */
public final class FastElementSequence<T, U extends NdArray<T>> implements NdArraySequence<U> {

  public FastElementSequence(AbstractNdArray<T, U> ndArray, int dimensionIdx, U element, DataBufferWindow<?> elementWindow) {
    this.ndArray = ndArray;
    this.dimensionIdx = dimensionIdx;
    this.element = element;
    this.elementWindow = elementWindow;
  }

  @Override
  public Iterator<U> iterator() {
    return new SequenceIterator();
  }

  @Override
  public void forEachIndexed(BiConsumer<long[], U> consumer) {
    PositionIterator.createIndexed(ndArray.dimensions(), dimensionIdx).forEachIndexed((long[] coords, long position) -> {
      elementWindow.slideTo(position);
      consumer.accept(coords, element);
    });
  }

  @Override
  public NdArraySequence<U> asSlices() {
    return new SlicingElementSequence<T, U>(ndArray, dimensionIdx);
  }

  private class SequenceIterator implements Iterator<U> {

      @Override
      public boolean hasNext() {
        return positionIterator.hasNext();
      }

      @Override
      public U next() {
        elementWindow.slideTo(positionIterator.nextLong());
        return element;
      }

      private final PositionIterator positionIterator = PositionIterator.create(ndArray.dimensions(), dimensionIdx);
  }

  private final AbstractNdArray<T, U> ndArray;
  private final int dimensionIdx;
  private final U element;
  private final DataBufferWindow<?> elementWindow;
}
