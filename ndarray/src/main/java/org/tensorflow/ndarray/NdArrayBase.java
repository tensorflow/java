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
package org.tensorflow.ndarray;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.index.Index;

public interface NdArrayBase {

  /**
   * @return the shape of this N-dimensional array
   */
  Shape shape();

  /**
   * @return the rank of this N-dimensional array
   */
  default int rank() {
    return shape().numDimensions();
  }

  /**
   * Computes and returns the total size of this N-dimensional array, in number of values.
   *
   * <p>For example, given a 3x3x2 matrix, the return value will be 18.
   * @return total size of this nd array
   */
  default long size() {
    return shape().size();
  }
}
