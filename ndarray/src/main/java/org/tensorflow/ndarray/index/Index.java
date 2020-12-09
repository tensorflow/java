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
package org.tensorflow.ndarray.index;

import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.impl.dimension.Dimension;

/**
 * An index used for slicing a view out of an N-dimensional array.
 *
 * <p>A slice, i.e. a reduced view, of an N-dimensional array is obtain by calling
 * {@link NdArray#slice(Index...)}, given a list of indices
 * that select which elements on a given dimension should be included/excluded
 * from that view.
 */
public interface Index {

  /**
   * Returns the number of elements that can be retrieved using this index on the
   * given dimension.
   *
   * <p>An index that maps one-by-one all elements of the dimensions will return a value
   * equal to {@code dim.numElements()}, while an index that only maps a subset of these
   * will return a smaller value.
   *
   * @param dim the indexed dimension
   * @return number of elements accessible
   */
  long numElements(Dimension dim);

  /**
   * Transforms an element coordinate to a new coordinate by applying this index to the
   * given dimension.
   *
   * <p>For example, if the coordinate is 0 and this index flips the {@code n} elements on this
   * dimension, then the returned value will be {@code n-1}.
   *
   * @param coordinate coordinate to transform
   * @param dim dimension the indexed dimension
   * @return transformed coordinate
   */
  long mapCoordinate(long coordinate, Dimension dim);

  /**
   * Applies this index to the given dimension.
   *
   * <p>When accessing the elements from the returned dimension, this index will automatically
   * apply and may transform the original position.
   *
   * @param dim dimension to apply this index to
   * @return an indexed dimension
   */
  default Dimension apply(Dimension dim) {
    return dim.withIndex(this);
  }

  /**
   * Returns true if this index is a single point, reducing the number of dimensions by one
   */
  default boolean isPoint() {
    return false;
  }

  /**
   * Returns true if this index is a new axis, adding a dimension of size 1
   */
  default boolean isNewAxis() {
    return false;
  }

  /**
   * Returns true if this index is an ellipsis, expanding to take as many dimensions as possible
   * (and applying all() to them)
   */
  default boolean isEllipsis() {
    return false;
  }
}
