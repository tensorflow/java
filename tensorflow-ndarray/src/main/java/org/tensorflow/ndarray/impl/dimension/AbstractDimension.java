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

abstract class AbstractDimension implements Dimension {

  /**
   * Dimensions are known to be equal if they have the same number of elements
   */
  @Override
  public int hashCode() {
    final int prime = 17;
    long numElements = numElements();
    return 31 * prime + (int)(numElements ^ (numElements >>> 32));
  }

  /**
   * Dimensions are known to be equal if they have the same number of elements
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof Dimension) {
      Dimension otherDimension = (Dimension) obj;
      return numElements() == otherDimension.numElements();
    }
    return false;
  }
}
