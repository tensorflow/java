/* Copyright 2022 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/
package org.tensorflow.internal.types;

import org.tensorflow.SparseTensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.types.TInt64;

/** Internal helper class for sparse tensor mappers */
abstract class SparseHelpers {

  /**
   * Convert a 1-D dense tensor, where each scalar represents the size of a dimension, to a {@link
   * DimensionalSpace} instance as expected by the NdArray library.
   *
   * @param denseShape 1-D dense tensor holding the size of each dimensions
   * @return a {@link DimensionalSpace} with these dimensions
   */
  static DimensionalSpace toDimensionalSpace(TInt64 denseShape) {
    return DimensionalSpace.create(Shape.of(StdArrays.array1dCopyOf(denseShape)));
  }

  /**
   * Compute the total number of bytes required to store a sparse tensor by adding the size of each
   * of its dense sub-tensors.
   *
   * @param sparseTensor the sparse tensor
   * @return the total number of bytes
   */
  static long numBytes(SparseTensor<?> sparseTensor) {
    return sparseTensor.indices().numBytes()
        + sparseTensor.values().numBytes()
        + sparseTensor.denseShape().numBytes();
  }
}
