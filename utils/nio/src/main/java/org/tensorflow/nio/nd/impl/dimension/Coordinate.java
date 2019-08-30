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
package org.tensorflow.nio.nd.impl.dimension;

final class Coordinate extends AbstractDimension {
  
  Coordinate(long index, AbstractDimension targetDimension) {
    this.index = index;
    this.stride = targetDimension.stride();
  }
  
  @Override
  public long numElements() {
    return 0;
  }
  
  @Override
  public long positionOf(long coord) {
    throw new IndexOutOfBoundsException();
  }

  @Override
  public long position() {
    return index * stride;
  }

  @Override
  public boolean isSegmented() {
    return true;  // a coordinate is a segment to a dimension
  }

  @Override
  long stride() {
    return stride;
  }

  @Override
  public String toString() {
    return "(" + index + ")";
  }
  
  private final long index;
  private final long stride;
}
