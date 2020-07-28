/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.keras.backend.tf;

import org.tensorflow.Operand;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Represents a sparse tensor.
 *
 * @param <T> the type of the SparseTensor
 */
public class SparseTensor<T extends TType> {

  private final Operand<TInt64> indices;
  private final Operand<T> values;
  private final Operand<TInt64> denseShape;

  /**
   * Create a SparseTensor
   *
   * @param indices A 2-D int64 tensor of shape `[N, ndims]`, which specifies the indices of the
   *     elements in the sparse tensor that contain nonzero values
   * @param values A 1-D tensor of any type and shape `[N]`, which supplies the values for each
   *     element in `indices`.
   * @param denseShape A 1-D int64 tensor of shape `[ndims]`, which specifies the dense_shape of the
   *     sparse tensor
   * @throws IllegalArgumentException When building an eager SparseTensor if `dense_shape` is
   *     unknown or contains unknown elements (None or -1).
   */
  public SparseTensor(Operand<TInt64> indices, Operand<T> values, Operand<TInt64> denseShape) {
    this.indices = indices;
    this.values = values;
    this.denseShape = denseShape;
  }

  /** @return the indices */
  public Operand<TInt64> getIndices() {
    return indices;
  }

  /** @return the values */
  public Operand<T> getValues() {
    return values;
  }

  /** @return the denseShape */
  public Operand<TInt64> getDenseShape() {
    return denseShape;
  }
}
