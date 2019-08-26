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
package org.tensorflow.nio.nd.index;

import org.tensorflow.nio.nd.NdArray;
import org.tensorflow.nio.nd.NdArrays;
import org.tensorflow.nio.nd.Shape;

public final class Indices {
  
  public static Index at(long index) {
    return new At(index);
  }

  public static Index at(NdArray<? extends Number> index) {
    if (index.shape().numDimensions() > 0) {
      throw new IllegalArgumentException("Only scalars are accepted as a value index");
    }
    return new At(index.get().longValue());
  }
  
  public static Index all() {
    return All.INSTANCE;
  }
  
  public static Index seq(long... indices) {
    if (indices == null) {
      throw new IllegalArgumentException();
    }
    return new Sequence(NdArrays.wrap(indices, Shape.create(indices.length)));
  }
  
  public static Index elem(NdArray<? extends Number> indices) {
    if (indices.shape().numDimensions() != 1) {
      throw new IllegalArgumentException("Only vectors are accepted as an element index");
    }
    return new Sequence(indices);
  }
  
  public static Index even() {
    return Even.INSTANCE;
  }

  public static Index odd() {
    return Odd.INSTANCE;
  }
  
  public static Index step(long stepLength) {
    return new Step(stepLength);
  }
  
  public static Index from(long start) {
    return new From(start);
  }

  public static Index to(long end) {
    return new To(end);
  }
  
  public static Index range(long start, long end) {
    return new Range(start, end);
  }

  public static Index flip() {
    return Flip.INSTANCE;
  }
}
