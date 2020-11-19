/*
 Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.ndarray;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.index.Index;

/**
 * Any data container with a given {@link Shape}.
 */
public interface Shaped {

  /**
   * @return the shape of this container
   */
  Shape shape();

  /**
   * @return the rank of this container
   */
  default int rank() {
    return shape().numDimensions();
  }

  /**
   * Computes and returns the total size of this container, in number of values.
   *
   * <p>For example, given a 3x3x2 matrix, the return value will be 18.
   *
   * @return number of values in this element
   */
  default long size() {
    return shape().size();
  }
}
