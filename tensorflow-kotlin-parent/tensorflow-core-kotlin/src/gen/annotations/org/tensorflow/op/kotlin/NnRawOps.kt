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
import org.tensorflow.op.nn.raw.SoftmaxCrossEntropyWithLogits
import org.tensorflow.op.nn.raw.SparseSoftmaxCrossEntropyWithLogits
import org.tensorflow.types.family.TNumber

/**
 * An API for building `nn.raw` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class NnRawOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps,
) {
    public val java: org.tensorflow.op.NnRawOps = ops.java.nn.raw

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Computes softmax cross entropy cost and gradients to backpropagate.
     *
     *  Inputs are the logits, not probabilities.
     *
     * @param T data type for ` loss()` output
     * @param features batch_size x num_classes matrix
     * @param labels batch_size x num_classes matrix
     *  The caller must ensure that each batch of labels represents a valid
     *  probability distribution.
     * @return a new instance of SoftmaxCrossEntropyWithLogits
     * @see org.tensorflow.op.NnRawOps.softmaxCrossEntropyWithLogits
     */
    public fun <T : TNumber> softmaxCrossEntropyWithLogits(
        features: Operand<T>,
        labels: Operand<T>,
    ): SoftmaxCrossEntropyWithLogits<T> =
        java.softmaxCrossEntropyWithLogits<T>(
            features,
            labels
        )

    /**
     * Computes softmax cross entropy cost and gradients to backpropagate.
     *
     *  Unlike `SoftmaxCrossEntropyWithLogits`, this operation does not accept
     *  a matrix of label probabilities, but rather a single label per row
     *  of features.  This label is considered to have probability 1.0 for the
     *  given row.
     *
     *  Inputs are the logits, not probabilities.
     *
     * @param T data type for ` loss()` output
     * @param features batch_size x num_classes matrix
     * @param labels batch_size vector with values in &#91;0, num_classes).
     *  This is the label for the given minibatch entry.
     * @return a new instance of SparseSoftmaxCrossEntropyWithLogits
     * @see org.tensorflow.op.NnRawOps.sparseSoftmaxCrossEntropyWithLogits
     */
    public fun <T : TNumber> sparseSoftmaxCrossEntropyWithLogits(
        features: Operand<T>,
        labels: Operand<out TNumber>,
    ): SparseSoftmaxCrossEntropyWithLogits<T> =
        java.sparseSoftmaxCrossEntropyWithLogits<T>(
            features,
            labels
        )
}
