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

package org.tensorflow.ndarray.impl.sequence;

import java.util.Iterator;
import java.util.function.BiConsumer;
import org.tensorflow.ndarray.IllegalRankException;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArraySequence;
import org.tensorflow.ndarray.impl.AbstractNdArray;

/**
 * A sequence of one single element
 *
 * @param <T> Type of the element
 * @param <U> Type of the {@code NdArray} with this sequence
 */
public final class SingleElementSequence<T, U extends NdArray<T>> implements NdArraySequence<U> {

  public SingleElementSequence(AbstractNdArray<T, U> ndArray) {
    this.ndArray = ndArray;
  }

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
  public NdArraySequence<U> asSlices() {
    return this;  // no need to slice, as there are only one element
  }

  @Override
  public void forEachIndexed(BiConsumer<long[], U> consumer) {
    throw new IllegalRankException("Single element has no coordinates to iterate on, use forEach()");
  }

  private final AbstractNdArray<T, U> ndArray;
}
