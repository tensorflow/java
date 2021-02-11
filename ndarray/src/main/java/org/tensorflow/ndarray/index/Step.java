/*
  Copyright 2020 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ==============================================================================
 */
package org.tensorflow.ndarray.index;

import java.util.StringJoiner;
import org.tensorflow.ndarray.impl.dimension.Dimension;

final class Step implements Index {

  Step(long stride) {
    this.stride = stride;

    if (stride == 0) {
      throw new IllegalArgumentException("Can not have a stride of 0");
    }
  }

  @Override
  public long numElements(Dimension dim) {
    long length = end(dim) - start(dim);

    return (length / stride) + (length % stride != 0 ? 1 : 0);
  }

  @Override
  public long mapCoordinate(long coordinate, Dimension dim) {
    return start(dim) + stride * coordinate;
  }

  @Override
  public boolean beginMask() {
    return true;
  }

  @Override
  public boolean endMask() {
    return true;
  }

  @Override
  public long stride() {
    return stride;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Step.class.getSimpleName() + "(", ")")
        .add("stride=" + stride)
        .toString();
  }

  private long start(Dimension dim) {
    if (stride > 0) {
      return 0;
    }

    return dim.numElements() - 1; // it's inclusive
  }

  private long end(Dimension dim) {
    if (stride > 0) {
      return dim.numElements();
    } else {
      return -1; // it's exclusive
    }
  }

  private final long stride;
}
