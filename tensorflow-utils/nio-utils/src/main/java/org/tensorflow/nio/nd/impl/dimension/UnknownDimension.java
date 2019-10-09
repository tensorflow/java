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

import org.tensorflow.nio.nd.Shape;

final class UnknownDimension extends AbstractDimension {

  @Override
  public long numElements() {
    return Shape.UNKNOWN_SIZE;
  }
  
  @Override
  public long positionOf(long coord) {
    return coord;
  }
  
  @Override
  public boolean isSegmented() {
    return false;
  }

  @Override
  public long elementSize() {
    return 0L;
  }

  @Override
  public String toString() {
    return "Unknown";
  }

  @Override public int hashCode() {
    return System.identityHashCode(this); // All unknown dimensions are distinct
  }

  @Override
  public boolean equals(Object obj) {
    return false; // All unknown dimensions are distinct
  }
}
