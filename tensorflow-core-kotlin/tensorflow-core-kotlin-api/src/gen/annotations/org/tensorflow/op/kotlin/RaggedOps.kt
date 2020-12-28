// Copyright 2020 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op.kotlin

import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.ragged.RaggedBincount
import org.tensorflow.types.TInt64
import org.tensorflow.types.family.TNumber

/**
 * An API for building `ragged` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class RaggedOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.RaggedOps = ops.java.ragged

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Counts the number of occurrences of each value in an integer array.
     *  
     *  Outputs a vector with length `size` and the same dtype as `weights`. If
     *  `weights` are empty, then index `i` stores the number of times the value `i` is
     *  counted in `arr`. If `weights` are non-empty, then index `i` stores the sum of
     *  the value in `weights` at each index where the corresponding value in `arr` is
     *  `i`.
     *  
     *  Values in `arr` outside of the range &#91;0, size) are ignored.
     * 
     * @param U data type for ` output()` output
     * @param splits 1D int64 `Tensor`.
     * @param values 2D int `Tensor`.
     * @param size non-negative int scalar `Tensor`.
     * @param weights is an int32, int64, float32, or float64 `Tensor` with the same
     *  shape as `input`, or a length-0 `Tensor`, in which case it acts as all weights
     *  equal to 1.
     * @param options carries optional attributes values
     * @return a new instance of RaggedBincount
     * @see org.tensorflow.op.RaggedOps.raggedBincount
     * @param binaryOutput bool; Whether the kernel should count the appearance or number of
     * occurrences.
     */
    public fun <U : TNumber, T : TNumber> raggedBincount(
        splits: Operand<TInt64>,
        values: Operand<T>,
        size: Operand<T>,
        weights: Operand<U>,
        binaryOutput: Boolean? = null
    ): RaggedBincount<U> = java.raggedBincount<U, T>(    
        splits,
        values,
        size,
        weights,
        *listOfNotNull(
            binaryOutput?.let{ org.tensorflow.op.ragged.RaggedBincount.binaryOutput(it) }
        ).toTypedArray()
        )
}
