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

package org.tensorflow.ndarray;

import java.util.function.BiConsumer;
import org.tensorflow.ndarray.buffer.DataBufferWindow;

/**
 * A sequence of elements of an N-dimensional array.
 *
 * <p>An {@code NdArraySequence} is used to traverse an {@code NdArray} in a given dimension
 * and visit each of its elements.  For example, given a {@code n x m} matrix on the {@code [x, y]} axes,
 * elements are iterated in the following order:
 * <pre>
 * x<sub>0</sub>y<sub>0</sub>, x<sub>0</sub>y<sub>1</sub>, ..., x<sub>0</sub>y<sub>m-1</sub>, x<sub>1</sub>y<sub>0</sub>, x<sub>1</sub>y<sub>1</sub>, ..., x<sub>n-1</sub>y<sub>m-1</sub>
 * </pre>
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

  /**
   * Returns each element as a new slice.
   *
   * <p>Unlike conventional Java collections, elements of a {@code NdArraySequence} are transient, i.e. new {@code NdArray}
   * instances are allocated for each iteration. To improve performance, the same instance can be recycled to view
   * all elements of this sequence, using a {@link DataBufferWindow}.
   *
   * <p>In some cases though, it might be preferable to disable such optimizations to ensure that each element returned is a
   * new slice of the original array. For example, if one or more elements visited must live beyond the scope of the sequence
   * iteration, {@code asSlices()} makes sure that all elements returned by the sequence are unique instances.
   *
   * <pre>{@code
   *     final List<IntNdArray> vectors = new ArrayList<>();
   *     IntNdArray matrix = NdArrays.ofInts(Shape.of(6, 6));
   *     ndArray.elements(0).forEach(e -> vectors::add);  // Not safe, as `e` might always be the same recycled instance
   *     ndArray.elements(0).asSlices().forEach(e -> vectors::add);  // Safe, each `e` is a distinct NdArray instance
   * }</pre>
   *
   * @return a sequence that returns each elements iterated as a new slice
   * @see DataBufferWindow
   */
  NdArraySequence<T> asSlices();
}
