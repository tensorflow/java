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

import java.util.NoSuchElementException;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;

class NdPositionIterator implements IndexedPositionIterator {

  @Override
  public boolean hasNext() {
    return coords != null;
  }

  @Override
  public long nextLong() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    long position = dimensions.positionOf(coords);
    increment();
    return position;
  }

  @Override
  public void forEachIndexed(CoordsLongConsumer consumer) {
    while (hasNext()) {
      consumer.consume(coords, dimensions.positionOf(coords));
      increment();
    }
  }

  private void increment() {
    if (!increment(coords, dimensions)) {
      coords = null;
    }
  }

  static boolean increment(long[] coords, DimensionalSpace dimensions) {
    for (int i = coords.length - 1; i >= 0; --i) {
      if ((coords[i] = (coords[i] + 1) % dimensions.get(i).numElements()) > 0) {
        return true;
      }
    }
    return false;
  }

  NdPositionIterator(DimensionalSpace dimensions, int dimensionIdx) {
    this.dimensions = dimensions;
    this.coords = new long[dimensionIdx + 1];
  }

  private final DimensionalSpace dimensions;
  private long[] coords;
}
