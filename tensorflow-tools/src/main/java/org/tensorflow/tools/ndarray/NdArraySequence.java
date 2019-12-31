/*
 *  Copyright 2019 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */

package org.tensorflow.tools.ndarray;

import java.util.function.BiConsumer;

/**
 * Iterates through a sequence of elements of an N-dimensional array.
 *
 * @param <T> data type of the array being iterated
 */
public interface NdArraySequence<T extends NdArray<?>> extends Iterable<T> {

  /**
   * Visit each elements of this iteration and their respective coordinates.
   *
   * <p><i>Important: the consumer method should not keep a reference to the coordinates
   * as they might be mutable and reused during the iteration to improve performance.</i>
   *
   * @param consumer method to invoke for each elements
   */
  void forEachIndexed(BiConsumer<long[], T> consumer);
}
