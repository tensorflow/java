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
 * An API for building {@code nn.raw} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class NnRawOps(
	/**
	 * Get the parent {@link KotlinOps} object.
	 */
	public val ops: KotlinOps
) {
	public val java: org.tensorflow.op.NnRawOps = ops.java.nn.raw

	/**
	 * Returns the current {@link Scope scope} of this API
	 */
	public val scope: Scope = ops.scope

	public fun <T : TNumber> softmaxCrossEntropyWithLogits(features: Operand<T>, labels: Operand<T>):
			SoftmaxCrossEntropyWithLogits<T> = java.softmaxCrossEntropyWithLogits<T>(	
		features,
		labels
		)

	public fun <T : TNumber, U : TNumber> sparseSoftmaxCrossEntropyWithLogits(features: Operand<T>,
			labels: Operand<U>): SparseSoftmaxCrossEntropyWithLogits<T> =
			java.sparseSoftmaxCrossEntropyWithLogits<T, U>(	
		features,
		labels
		)
}
