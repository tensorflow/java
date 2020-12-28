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

import org.tensorflow.ndarray.impl.dimension.Dimension;

final class Slice implements TensorIndex {

  private final Long start;
  private final Long end;
  private final long stride;

  private long start(Dimension dim){
    if(start == null){
      if(stride > 0){
        return 0;
      } else {
        return dim.numElements() - 1; // it's inclusive
      }
    } else if(start < 0){
      return dim.numElements() + start;
    } else {
      return start;
    }
  }

  private long end(Dimension dim){
    if(end == null){
      if(stride > 0){
        return dim.numElements();
      } else {
        return -1; // it's exclusive
      }
    } else if(end < 0){
      return dim.numElements() + end;
    } else {
      return end;
    }
  }

  Slice(Long start, Long end, long stride) {
    this.start = start;
    this.end = end;
    this.stride = stride;

    if(stride == 0){
      throw new IllegalArgumentException("Can not have a stride of 0");
    }
  }

  @Override
  public long numElements(Dimension dim) {
    long length = end(dim) - start(dim);
    if(stride < 0){
      length *= -1;
    }

    return (length / stride) + (length % stride != 0 ? 1 : 0);
  }

  @Override
  public long mapCoordinate(long coordinate, Dimension dim) {
    return start(dim) + stride * coordinate;
  }

  @Override
  public long begin() {
    return start == null ? 0 : start;
  }

  @Override
  public long end() {
    return end == null ? 0 : end;
  }

  @Override
  public long stride() {
    return stride;
  }

  @Override
  public boolean beginMask() {
    return start == null;
  }

  @Override
  public boolean endMask() {
    return end == null;
  }
}
