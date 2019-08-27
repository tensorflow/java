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

import org.tensorflow.nio.nd.NdArray;

class VectorValueIterator<T> implements ValueIterator<T> {

  @Override
  public boolean hasNext() {
    return currentIndex < array.size();
  }

  @Override
  public T next() {
    return array.get(currentIndex++);
  }    
  
  public void next(T value) {
    array.set(value, currentIndex++);
  }
  
  VectorValueIterator(NdArray<T> array) {
    if (array.shape().numDimensions() != 1) {
      throw new IllegalArgumentException();
    }
    this.array = array;
    currentIndex = 0L;
  }
  
  private final NdArray<T> array;

  private long currentIndex;
}
