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
package org.tensorflow.nio.nd.impl.iterator;

import java.util.Iterator;

import org.tensorflow.nio.nd.IllegalRankException;
import org.tensorflow.nio.nd.NdArray;

class ElementIterator<T> implements Iterator<NdArray<T>> {
  
  @Override
  public boolean hasNext() {
    return currentIdx < array.shape().numElements(0);
  }

  @Override
  public NdArray<T> next() {
    return array.at(currentIdx++);
  }

  ElementIterator(NdArray<T> array) {
    if (array.shape().numDimensions() == 0) {
      throw new IllegalRankException("Cannot iterate a scalar");
    }
    this.array = array;
    currentIdx = 0L;
  }
  
  private final NdArray<T> array;

  private long currentIdx;
}
