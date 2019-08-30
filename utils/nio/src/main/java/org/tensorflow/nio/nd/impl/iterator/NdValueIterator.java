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

import org.tensorflow.nio.nd.NdArray;

class NdValueIterator<T> implements ValueIterator<T> {

  @Override
  public boolean hasNext() {
    return currentElementValueIterator.hasNext() || elementIterator.hasNext();
  }

  @Override
  public T next() {
    return currentElementValueIterator().next();
  }    
  
  @Override
  public void next(T value) {
    currentElementValueIterator().next(value);
  }
  
  NdValueIterator(NdArray<T> array) {
    elementIterator = array.childElements().iterator();
    currentElementValueIterator = elementIterator.next().values().iterator();
  }
  
  private ValueIterator<T> currentElementValueIterator() {
    if (!currentElementValueIterator.hasNext()) {
      currentElementValueIterator = elementIterator.next().values().iterator();
    }
    return currentElementValueIterator;
  }
  
  private Iterator<? extends NdArray<T>> elementIterator;

  private ValueIterator<T> currentElementValueIterator;
}
