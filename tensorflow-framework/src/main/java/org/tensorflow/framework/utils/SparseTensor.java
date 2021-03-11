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
package org.tensorflow.framework.utils;

import org.tensorflow.Operand;
import org.tensorflow.Tensor;
import org.tensorflow.op.SparseOps;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * This is a helper class that represents a sparse tensor who's attributes may be passed to {@link
 * SparseOps} methods.
 *
 * <p>This class does <em>not inherit from {@link Tensor}</em>, but is merely a place to accumulate the
 * properties that are needed for the {@link SparseOps} methods.
 *
 * @param <T> the type of the SparseTensor's values.
 */
public class SparseTensor<T extends TType> {
    private final Operand<TInt64> indices;
    private final Operand<T> values;
    private final Operand<TInt64> denseShape;

    /**
     * Creates a SparseTensor
     *
     * @param indices A 2-D int64 tensor of shape `[N, ndims]`, which specifies the
     * indices of the elements in the sparse tensor that contain nonzero values
     * @param values A 1-D tensor of any type and shape `[N]`, which supplies the
     * values for each element in `indices`.
     * @param denseShape A 1-D int64 tensor of shape `[ndims]`, which specifies the
     * dense_shape of the sparse tensor
     * @throws IllegalArgumentException When building an eager SparseTensor if `dense_shape` is
     *   unknown or contains unknown elements (None or -1).
     */
    public SparseTensor (Operand<TInt64> indices, Operand<T> values, Operand<TInt64> denseShape) {
        this.indices = indices;
        this.values = values;
        this.denseShape = denseShape;
    }

    /**
     * Gets the indices for the Sparse Tensor
     * @return the indices
     */
    public Operand<TInt64> getIndices() {
        return indices;
    }

    /**
     * Get the values for the Sparse Tensor
     * @return the values
     */
    public Operand<T> getValues() {
        return values;
    }

    /**
     * Gets the dense shape for the Sparse Tensor
     *
     * @return the denseShape
     */
    public Operand<TInt64> getDenseShape() {
        return denseShape;
    }

}
