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

public final class Iterators {
  
  public static <T> Iterator<NdArray<T>> elementsOf(NdArray<T> array) {
    if (array.shape().numDimensions() == 0) {
      throw new IllegalRankException("Cannot iterate elements of a scalar");
    }
    return new ElementIterator<>(array);
  }
  
  public static <T> ValueIterable<T> valuesOf(NdArray<T> array) {
    switch (array.shape().numDimensions()) {
    case 0:
      throw new IllegalRankException("Cannot iterate values of a scalar");
    case 1:
      return () -> new VectorValueIterator<>(array);
    default:
      return () -> new NdValueIterator<>(array);
    }
  }
}
