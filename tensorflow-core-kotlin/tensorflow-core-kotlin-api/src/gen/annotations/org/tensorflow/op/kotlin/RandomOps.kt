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

import org.tensorflow.DataType
import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.random.AllCandidateSampler
import org.tensorflow.op.random.LogUniformCandidateSampler
import org.tensorflow.op.random.Multinomial
import org.tensorflow.op.random.ParameterizedTruncatedNormal
import org.tensorflow.op.random.RandomGamma
import org.tensorflow.op.random.RandomPoisson
import org.tensorflow.op.random.RandomShuffle
import org.tensorflow.op.random.RandomStandardNormal
import org.tensorflow.op.random.RandomUniform
import org.tensorflow.op.random.RandomUniformInt
import org.tensorflow.op.random.RecordInput
import org.tensorflow.op.random.StatefulRandomBinomial
import org.tensorflow.op.random.StatefulStandardNormal
import org.tensorflow.op.random.StatelessMultinomial
import org.tensorflow.op.random.StatelessRandomNormal
import org.tensorflow.op.random.StatelessRandomUniform
import org.tensorflow.op.random.StatelessTruncatedNormal
import org.tensorflow.op.random.TruncatedNormal
import org.tensorflow.op.random.UniformCandidateSampler
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building {@code random} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class RandomOps(
	/**
	 * Get the parent {@link KotlinOps} object.
	 */
	public val ops: KotlinOps
) {
	public val java: org.tensorflow.op.RandomOps = ops.java.random

	/**
	 * Returns the current {@link Scope scope} of this API
	 */
	public val scope: Scope = ops.scope

	public fun allCandidateSampler(
		trueClasses: Operand<TInt64>,
		numTrue: Long,
		numSampled: Long,
		unique: Boolean,
		seed: Long? = null,
		seed2: Long? = null
	): AllCandidateSampler = java.allCandidateSampler(	
		trueClasses,
		numTrue,
		numSampled,
		unique,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.AllCandidateSampler.seed(it) },
			seed2?.let{ org.tensorflow.op.random.AllCandidateSampler.seed2(it) }
		).toTypedArray()
		)

	public fun logUniformCandidateSampler(
		trueClasses: Operand<TInt64>,
		numTrue: Long,
		numSampled: Long,
		unique: Boolean,
		rangeMax: Long,
		seed: Long? = null,
		seed2: Long? = null
	): LogUniformCandidateSampler = java.logUniformCandidateSampler(	
		trueClasses,
		numTrue,
		numSampled,
		unique,
		rangeMax,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.LogUniformCandidateSampler.seed(it) },
			seed2?.let{ org.tensorflow.op.random.LogUniformCandidateSampler.seed2(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> multinomial(
		logits: Operand<T>,
		numSamples: Operand<TInt32>,
		seed: Long? = null,
		seed2: Long? = null
	): Multinomial<TInt64> = java.multinomial<T>(	
		logits,
		numSamples,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.Multinomial.seed(it) },
			seed2?.let{ org.tensorflow.op.random.Multinomial.seed2(it) }
		).toTypedArray()
		)

	public fun <U : TNumber, T : TNumber> multinomial(
		logits: Operand<T>,
		numSamples: Operand<TInt32>,
		outputDtype: DataType<U>,
		seed: Long? = null,
		seed2: Long? = null
	): Multinomial<U> = java.multinomial<U, T>(	
		logits,
		numSamples,
		outputDtype,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.Multinomial.seed(it) },
			seed2?.let{ org.tensorflow.op.random.Multinomial.seed2(it) }
		).toTypedArray()
		)

	public fun <U : TNumber, T : TNumber> parameterizedTruncatedNormal(
		shape: Operand<T>,
		means: Operand<U>,
		stdevs: Operand<U>,
		minvals: Operand<U>,
		maxvals: Operand<U>,
		seed: Long? = null,
		seed2: Long? = null
	): ParameterizedTruncatedNormal<U> = java.parameterizedTruncatedNormal<U, T>(	
		shape,
		means,
		stdevs,
		minvals,
		maxvals,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.ParameterizedTruncatedNormal.seed(it) },
			seed2?.let{ org.tensorflow.op.random.ParameterizedTruncatedNormal.seed2(it) }
		).toTypedArray()
		)

	public fun <U : TNumber, T : TNumber> randomGamma(
		shape: Operand<T>,
		alpha: Operand<U>,
		seed: Long? = null,
		seed2: Long? = null
	): RandomGamma<U> = java.randomGamma<U, T>(	
		shape,
		alpha,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.RandomGamma.seed(it) },
			seed2?.let{ org.tensorflow.op.random.RandomGamma.seed2(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TNumber> randomPoisson(
		shape: Operand<T>,
		rate: Operand<U>,
		seed: Long? = null,
		seed2: Long? = null
	): RandomPoisson<TInt64> = java.randomPoisson<T, U>(	
		shape,
		rate,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.RandomPoisson.seed(it) },
			seed2?.let{ org.tensorflow.op.random.RandomPoisson.seed2(it) }
		).toTypedArray()
		)

	public fun <V : TNumber, T : TNumber, U : TNumber> randomPoisson(
		shape: Operand<T>,
		rate: Operand<U>,
		dtype: DataType<V>,
		seed: Long? = null,
		seed2: Long? = null
	): RandomPoisson<V> = java.randomPoisson<V, T, U>(	
		shape,
		rate,
		dtype,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.RandomPoisson.seed(it) },
			seed2?.let{ org.tensorflow.op.random.RandomPoisson.seed2(it) }
		).toTypedArray()
		)

	public fun <T : TType> randomShuffle(
		value: Operand<T>,
		seed: Long? = null,
		seed2: Long? = null
	): RandomShuffle<T> = java.randomShuffle<T>(	
		value,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.RandomShuffle.seed(it) },
			seed2?.let{ org.tensorflow.op.random.RandomShuffle.seed2(it) }
		).toTypedArray()
		)

	public fun <U : TNumber, T : TNumber> randomStandardNormal(
		shape: Operand<T>,
		dtype: DataType<U>,
		seed: Long? = null,
		seed2: Long? = null
	): RandomStandardNormal<U> = java.randomStandardNormal<U, T>(	
		shape,
		dtype,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.RandomStandardNormal.seed(it) },
			seed2?.let{ org.tensorflow.op.random.RandomStandardNormal.seed2(it) }
		).toTypedArray()
		)

	public fun <U : TNumber, T : TNumber> randomUniform(
		shape: Operand<T>,
		dtype: DataType<U>,
		seed: Long? = null,
		seed2: Long? = null
	): RandomUniform<U> = java.randomUniform<U, T>(	
		shape,
		dtype,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.RandomUniform.seed(it) },
			seed2?.let{ org.tensorflow.op.random.RandomUniform.seed2(it) }
		).toTypedArray()
		)

	public fun <U : TNumber, T : TNumber> randomUniformInt(
		shape: Operand<T>,
		minval: Operand<U>,
		maxval: Operand<U>,
		seed: Long? = null,
		seed2: Long? = null
	): RandomUniformInt<U> = java.randomUniformInt<U, T>(	
		shape,
		minval,
		maxval,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.RandomUniformInt.seed(it) },
			seed2?.let{ org.tensorflow.op.random.RandomUniformInt.seed2(it) }
		).toTypedArray()
		)

	public fun recordInput(
		filePattern: String,
		fileRandomSeed: Long? = null,
		fileShuffleShiftRatio: Float? = null,
		fileBufferSize: Long? = null,
		fileParallelism: Long? = null,
		batchSize: Long? = null,
		compressionType: String? = null
	): RecordInput = java.recordInput(	
		filePattern,
		*listOfNotNull(
			fileRandomSeed?.let{ org.tensorflow.op.random.RecordInput.fileRandomSeed(it) },
			fileShuffleShiftRatio?.let{ org.tensorflow.op.random.RecordInput.fileShuffleShiftRatio(it) },
			fileBufferSize?.let{ org.tensorflow.op.random.RecordInput.fileBufferSize(it) },
			fileParallelism?.let{ org.tensorflow.op.random.RecordInput.fileParallelism(it) },
			batchSize?.let{ org.tensorflow.op.random.RecordInput.batchSize(it) },
			compressionType?.let{ org.tensorflow.op.random.RecordInput.compressionType(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TNumber> statefulRandomBinomial(
		resource: Operand<*>,
		algorithm: Operand<TInt64>,
		shape: Operand<T>,
		counts: Operand<U>,
		probs: Operand<U>
	): StatefulRandomBinomial<TInt64> = java.statefulRandomBinomial<T, U>(	
		resource,
		algorithm,
		shape,
		counts,
		probs
		)

	public fun <V : TNumber, T : TNumber, U : TNumber> statefulRandomBinomial(
		resource: Operand<*>,
		algorithm: Operand<TInt64>,
		shape: Operand<T>,
		counts: Operand<U>,
		probs: Operand<U>,
		dtype: DataType<V>
	): StatefulRandomBinomial<V> = java.statefulRandomBinomial<V, T, U>(	
		resource,
		algorithm,
		shape,
		counts,
		probs,
		dtype
		)

	public fun <T : TType> statefulStandardNormal(
		resource: Operand<*>,
		algorithm: Operand<TInt64>,
		shape: Operand<T>
	): StatefulStandardNormal<TFloat32> = java.statefulStandardNormal<T>(	
		resource,
		algorithm,
		shape
		)

	public fun <U : TType, T : TType> statefulStandardNormal(
		resource: Operand<*>,
		algorithm: Operand<TInt64>,
		shape: Operand<T>,
		dtype: DataType<U>
	): StatefulStandardNormal<U> = java.statefulStandardNormal<U, T>(	
		resource,
		algorithm,
		shape,
		dtype
		)

	public fun <T : TNumber, U : TNumber> statelessMultinomial(
		logits: Operand<T>,
		numSamples: Operand<TInt32>,
		seed: Operand<U>
	): StatelessMultinomial<TInt64> = java.statelessMultinomial<T, U>(	
		logits,
		numSamples,
		seed
		)

	public fun <V : TNumber, T : TNumber, U : TNumber> statelessMultinomial(
		logits: Operand<T>,
		numSamples: Operand<TInt32>,
		seed: Operand<U>,
		outputDtype: DataType<V>
	): StatelessMultinomial<V> = java.statelessMultinomial<V, T, U>(	
		logits,
		numSamples,
		seed,
		outputDtype
		)

	public fun <T : TNumber, U : TNumber> statelessRandomNormal(shape: Operand<T>, seed: Operand<U>):
			StatelessRandomNormal<TFloat32> = java.statelessRandomNormal<T, U>(	
		shape,
		seed
		)

	public fun <V : TNumber, T : TNumber, U : TNumber> statelessRandomNormal(
		shape: Operand<T>,
		seed: Operand<U>,
		dtype: DataType<V>
	): StatelessRandomNormal<V> = java.statelessRandomNormal<V, T, U>(	
		shape,
		seed,
		dtype
		)

	public fun <T : TNumber, U : TNumber> statelessRandomUniform(shape: Operand<T>, seed: Operand<U>):
			StatelessRandomUniform<TFloat32> = java.statelessRandomUniform<T, U>(	
		shape,
		seed
		)

	public fun <V : TNumber, T : TNumber, U : TNumber> statelessRandomUniform(
		shape: Operand<T>,
		seed: Operand<U>,
		dtype: DataType<V>
	): StatelessRandomUniform<V> = java.statelessRandomUniform<V, T, U>(	
		shape,
		seed,
		dtype
		)

	public fun <T : TNumber, U : TNumber> statelessTruncatedNormal(shape: Operand<T>,
			seed: Operand<U>): StatelessTruncatedNormal<TFloat32> = java.statelessTruncatedNormal<T, U>(	
		shape,
		seed
		)

	public fun <V : TNumber, T : TNumber, U : TNumber> statelessTruncatedNormal(
		shape: Operand<T>,
		seed: Operand<U>,
		dtype: DataType<V>
	): StatelessTruncatedNormal<V> = java.statelessTruncatedNormal<V, T, U>(	
		shape,
		seed,
		dtype
		)

	public fun <U : TNumber, T : TNumber> truncatedNormal(
		shape: Operand<T>,
		dtype: DataType<U>,
		seed: Long? = null,
		seed2: Long? = null
	): TruncatedNormal<U> = java.truncatedNormal<U, T>(	
		shape,
		dtype,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.TruncatedNormal.seed(it) },
			seed2?.let{ org.tensorflow.op.random.TruncatedNormal.seed2(it) }
		).toTypedArray()
		)

	public fun uniformCandidateSampler(
		trueClasses: Operand<TInt64>,
		numTrue: Long,
		numSampled: Long,
		unique: Boolean,
		rangeMax: Long,
		seed: Long? = null,
		seed2: Long? = null
	): UniformCandidateSampler = java.uniformCandidateSampler(	
		trueClasses,
		numTrue,
		numSampled,
		unique,
		rangeMax,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.random.UniformCandidateSampler.seed(it) },
			seed2?.let{ org.tensorflow.op.random.UniformCandidateSampler.seed2(it) }
		).toTypedArray()
		)
}
