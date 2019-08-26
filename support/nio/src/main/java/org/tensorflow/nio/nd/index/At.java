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
package org.tensorflow.nio.nd.index;

import org.tensorflow.nio.nd.impl.dimension.Dimension;
import org.tensorflow.nio.nd.impl.dimension.Dimensions;

/**
 * An index that selects a specific element on a given dimension.
 *
 * <p>When this index is applied to a given dimension, the dimension is resolved as a
 * single element and therefore is excluded from the computation of the rank.
 *
 * <p>For example, given a 3D matrix on the axis [x, y, z], if
 * {@code matrix.slice(all(), at(0), at(0)}, then the rank of the returned slice is 1 and its
 * number of elements is {@code x.numElements()}
 */
class At implements Index {

  @Override
  public long numElements(Dimension dim) {
    return 1;
  }

  @Override
  public long mapCoordinate(long coordinate, Dimension dim) {
    return value;  // TODO Validate that coordinate is 0?
  }

  @Override
  public Dimension apply(Dimension dim) {
    return Dimensions.coord(value, dim);
  }

  At(long value) {
    this.value = value;
  }

  private long value;
}
