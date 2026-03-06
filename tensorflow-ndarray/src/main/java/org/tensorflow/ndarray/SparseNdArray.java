/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.ndarray;

/**
 * Interface for Sparse Arrays
 *
 * @param <T> the type that the array contains
 * @param <U> the type of dense NdArray
 */
public interface SparseNdArray<T, U extends NdArray<T>> extends NdArray<T> {
  /**
   * Gets the Indices
   *
   * <p>Indices are a A 2-D long array of shape {@code [N, ndims]}, that specifies the indices of
   * the elements in the sparse array that contain nonzero values (elements are zero-indexed).
   *
   * <p>For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   * coordinates {@code [1,3]} and {@code [2,4]} have nonzero values.
   *
   * @return the Indices
   */
  LongNdArray getIndices();

  /**
   * Gets the values.
   *
   * <p>Values are a 1-D array of any type and shape {@code [N]}, that supplies the values for each
   * element in indices.
   *
   * <p>For example, given {@code indices=[[1,3], [2,4]]}, and {@code values=[18, 3.6]} specifies
   * that element {@code [1,3]} of the sparse array has a value of {@code 18}, and element {@code
   * [2,4]} of the sparse array has a value of {@code 3.6}.
   *
   * @return the values
   */
  U getValues();
}
