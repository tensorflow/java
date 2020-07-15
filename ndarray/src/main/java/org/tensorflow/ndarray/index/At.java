/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================
 */
package org.tensorflow.ndarray.index;

import org.tensorflow.ndarray.impl.dimension.Dimension;

final class At implements Index {

  @Override
  public long numElements(Dimension dim) {
    return 1;
  }

  @Override
  public long mapCoordinate(long coordinate, Dimension dim) {
    return dim.positionOf(coord); // TODO validate coordinate is 0?
  }

  @Override
  public Dimension apply(Dimension dim) {
    throw new IllegalStateException(); // FIXME?
  }

  @Override
  public boolean isPoint() {
    return true;
  }

  At(long coord) {
    this.coord = coord;
  }

  private final long coord;
}
