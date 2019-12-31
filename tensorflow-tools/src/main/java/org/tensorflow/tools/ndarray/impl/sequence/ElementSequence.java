/*
 *  Copyright 2019 The TensorFlow Authors. All Rights Reserved.
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
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.NdArraySequence;
import org.tensorflow.tools.ndarray.impl.AbstractNdArray;
import org.tensorflow.tools.ndarray.impl.dimension.DimensionalSpace;

public class ElementSequence<T, U extends NdArray<T>> implements NdArraySequence<U> {

  public static <T, U extends NdArray<T>> NdArraySequence<U> create(AbstractNdArray<T, U> ndArray, int dimensionIdx) {
    if (ndArray.rank() == 0 && dimensionIdx < 0) {
      return new SingleElementSequence<>(ndArray);
    }
    return new ElementSequence<>(ndArray, dimensionIdx);
  }

  @Override
  public Iterator<U> iterator() {
    DimensionalSpace elementDimensions = ndArray.dimensions().from(dimensionIdx + 1);
    PositionIterator positionIterator = PositionIterator.create(ndArray.dimensions(), dimensionIdx);
    return new Iterator<U>() {

      @Override
      public boolean hasNext() {
        return positionIterator.hasNext();
      }

      @Override
      public U next() {
        return ndArray.slice(positionIterator.next(), elementDimensions);
      }
    };
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
