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

import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;

class IndexedSequentialPositionIterator extends SequentialPositionIterator implements IndexedPositionIterator {

  @Override
  public void forEachIndexed(CoordsLongConsumer consumer) {
    while (hasNext()) {
      consumer.consume(coords, nextLong());
      incrementCoords();
    }
  }

  private void incrementCoords() {
    for (int i = coords.length - 1; i >= 0; --i) {
      if (coords[i] < shape[i] - 1) {
        coords[i] += 1L;
        return;
      }
      coords[i] = 0L;
    }
  }

  IndexedSequentialPositionIterator(DimensionalSpace dimensions, int dimensionIdx) {
    super(dimensions, dimensionIdx);
    this.shape = dimensions.shape().asArray();
    this.coords = new long[dimensionIdx + 1];
    //this.coordsIncrementor = new CoordinatesIncrementor(dimensions.shape().asArray(), dimensionIdx);
  }

  private final long[] shape;
  private final long[] coords;
}
