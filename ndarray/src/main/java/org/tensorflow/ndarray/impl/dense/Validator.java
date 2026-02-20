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
package org.tensorflow.ndarray.impl.dense;

import org.tensorflow.ndarray.IllegalRankException;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;

final class Validator extends org.tensorflow.ndarray.impl.Validator {

  static void coordinates(DimensionalSpace dimensions, long[] coords,
      boolean isValue) {
    if (coords.length > dimensions.numDimensions()) {
      throw new IndexOutOfBoundsException();
    }
    if (isValue && coords.length != dimensions.numDimensions()) {
      throw new IllegalRankException("Not a scalar value");
    }
  }

  static void denseShape(DataBuffer<?> buffer, Shape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    if (shape.hasUnknownDimension()) {
      throw new IllegalArgumentException("Dense arrays cannot have unknown dimension(s)");
    }
    if (buffer.size() < shape.size()) {
      throw new IllegalArgumentException("Buffer size is smaller than the shape size");
    };
  }

  private Validator() {}
}
