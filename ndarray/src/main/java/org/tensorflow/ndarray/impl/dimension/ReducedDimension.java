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
package org.tensorflow.ndarray.impl.dimension;

final class ReducedDimension extends AbstractDimension {

  @Override
  public long numElements() {
    return originalDimension.numElements();
  }

  @Override
  public long positionOf(long coord) {
    return originalDimension.positionOf(coord) + offset;
  }

  @Override
  public boolean isSegmented() {
    return true;
  }

  @Override
  public long elementSize() {
    return elementSize;
  }

  @Override
  public long physicalSize() {
    // We simplify the computation by assuming that a reduced dimension takes the same amount of
    // memory than the original one
    return originalDimension.physicalSize();
  }

  @Override
  public String toString() {
    return String.valueOf(numElements());
  }

  ReducedDimension(Dimension originalDimension, long offset, long elementSize) {
    this.originalDimension = originalDimension;
    this.offset = offset;
    this.elementSize = elementSize;
  }

  private final Dimension originalDimension;
  private final long offset;
  private final long elementSize;
}
