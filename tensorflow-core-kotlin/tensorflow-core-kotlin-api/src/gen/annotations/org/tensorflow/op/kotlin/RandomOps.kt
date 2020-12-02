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
    vararg options: AllCandidateSampler.Options
  ): AllCandidateSampler = java.allCandidateSampler(trueClasses, numTrue, numSampled, unique,
      *options)

  public fun logUniformCandidateSampler(
    trueClasses: Operand<TInt64>,
    numTrue: Long,
    numSampled: Long,
    unique: Boolean,
    rangeMax: Long,
    vararg options: LogUniformCandidateSampler.Options
  ): LogUniformCandidateSampler = java.logUniformCandidateSampler(trueClasses, numTrue, numSampled,
      unique, rangeMax, *options)

  public fun <T : TNumber> multinomial(
    logits: Operand<T>,
    numSamples: Operand<TInt32>,
    vararg options: Multinomial.Options
  ): Multinomial<TInt64> = java.multinomial<T>(logits, numSamples, *options)

  public fun <U : TNumber, T : TNumber> multinomial(
    logits: Operand<T>,
    numSamples: Operand<TInt32>,
    outputDtype: DataType<U>,
    vararg options: Multinomial.Options
  ): Multinomial<U> = java.multinomial<U, T>(logits, numSamples, outputDtype, *options)

  public fun <U : TNumber, T : TNumber> parameterizedTruncatedNormal(
    shape: Operand<T>,
    means: Operand<U>,
    stdevs: Operand<U>,
    minvals: Operand<U>,
    maxvals: Operand<U>,
    vararg options: ParameterizedTruncatedNormal.Options
  ): ParameterizedTruncatedNormal<U> = java.parameterizedTruncatedNormal<U, T>(shape, means, stdevs,
      minvals, maxvals, *options)

  public fun <U : TNumber, T : TNumber> randomGamma(
    shape: Operand<T>,
    alpha: Operand<U>,
    vararg options: RandomGamma.Options
  ): RandomGamma<U> = java.randomGamma<U, T>(shape, alpha, *options)

  public fun <T : TNumber, U : TNumber> randomPoisson(
    shape: Operand<T>,
    rate: Operand<U>,
    vararg options: RandomPoisson.Options
  ): RandomPoisson<TInt64> = java.randomPoisson<T, U>(shape, rate, *options)

  public fun <V : TNumber, T : TNumber, U : TNumber> randomPoisson(
    shape: Operand<T>,
    rate: Operand<U>,
    dtype: DataType<V>,
    vararg options: RandomPoisson.Options
  ): RandomPoisson<V> = java.randomPoisson<V, T, U>(shape, rate, dtype, *options)

  public fun <T : TType> randomShuffle(value: Operand<T>, vararg options: RandomShuffle.Options):
      RandomShuffle<T> = java.randomShuffle<T>(value, *options)

  public fun <U : TNumber, T : TNumber> randomStandardNormal(
    shape: Operand<T>,
    dtype: DataType<U>,
    vararg options: RandomStandardNormal.Options
  ): RandomStandardNormal<U> = java.randomStandardNormal<U, T>(shape, dtype, *options)

  public fun <U : TNumber, T : TNumber> randomUniform(
    shape: Operand<T>,
    dtype: DataType<U>,
    vararg options: RandomUniform.Options
  ): RandomUniform<U> = java.randomUniform<U, T>(shape, dtype, *options)

  public fun <U : TNumber, T : TNumber> randomUniformInt(
    shape: Operand<T>,
    minval: Operand<U>,
    maxval: Operand<U>,
    vararg options: RandomUniformInt.Options
  ): RandomUniformInt<U> = java.randomUniformInt<U, T>(shape, minval, maxval, *options)

  public fun recordInput(filePattern: String, vararg options: RecordInput.Options): RecordInput =
      java.recordInput(filePattern, *options)

  public fun <T : TNumber, U : TNumber> statefulRandomBinomial(
    resource: Operand<*>,
    algorithm: Operand<TInt64>,
    shape: Operand<T>,
    counts: Operand<U>,
    probs: Operand<U>
  ): StatefulRandomBinomial<TInt64> = java.statefulRandomBinomial<T, U>(resource, algorithm, shape,
      counts, probs)

  public fun <V : TNumber, T : TNumber, U : TNumber> statefulRandomBinomial(
    resource: Operand<*>,
    algorithm: Operand<TInt64>,
    shape: Operand<T>,
    counts: Operand<U>,
    probs: Operand<U>,
    dtype: DataType<V>
  ): StatefulRandomBinomial<V> = java.statefulRandomBinomial<V, T, U>(resource, algorithm, shape,
      counts, probs, dtype)

  public fun <T : TType> statefulStandardNormal(
    resource: Operand<*>,
    algorithm: Operand<TInt64>,
    shape: Operand<T>
  ): StatefulStandardNormal<TFloat32> = java.statefulStandardNormal<T>(resource, algorithm, shape)

  public fun <U : TType, T : TType> statefulStandardNormal(
    resource: Operand<*>,
    algorithm: Operand<TInt64>,
    shape: Operand<T>,
    dtype: DataType<U>
  ): StatefulStandardNormal<U> = java.statefulStandardNormal<U, T>(resource, algorithm, shape,
      dtype)

  public fun <T : TNumber, U : TNumber> statelessMultinomial(
    logits: Operand<T>,
    numSamples: Operand<TInt32>,
    seed: Operand<U>
  ): StatelessMultinomial<TInt64> = java.statelessMultinomial<T, U>(logits, numSamples, seed)

  public fun <V : TNumber, T : TNumber, U : TNumber> statelessMultinomial(
    logits: Operand<T>,
    numSamples: Operand<TInt32>,
    seed: Operand<U>,
    outputDtype: DataType<V>
  ): StatelessMultinomial<V> = java.statelessMultinomial<V, T, U>(logits, numSamples, seed,
      outputDtype)

  public fun <T : TNumber, U : TNumber> statelessRandomNormal(shape: Operand<T>, seed: Operand<U>):
      StatelessRandomNormal<TFloat32> = java.statelessRandomNormal<T, U>(shape, seed)

  public fun <V : TNumber, T : TNumber, U : TNumber> statelessRandomNormal(
    shape: Operand<T>,
    seed: Operand<U>,
    dtype: DataType<V>
  ): StatelessRandomNormal<V> = java.statelessRandomNormal<V, T, U>(shape, seed, dtype)

  public fun <T : TNumber, U : TNumber> statelessRandomUniform(shape: Operand<T>, seed: Operand<U>):
      StatelessRandomUniform<TFloat32> = java.statelessRandomUniform<T, U>(shape, seed)

  public fun <V : TNumber, T : TNumber, U : TNumber> statelessRandomUniform(
    shape: Operand<T>,
    seed: Operand<U>,
    dtype: DataType<V>
  ): StatelessRandomUniform<V> = java.statelessRandomUniform<V, T, U>(shape, seed, dtype)

  public fun <T : TNumber, U : TNumber> statelessTruncatedNormal(shape: Operand<T>,
      seed: Operand<U>): StatelessTruncatedNormal<TFloat32> = java.statelessTruncatedNormal<T,
      U>(shape, seed)

  public fun <V : TNumber, T : TNumber, U : TNumber> statelessTruncatedNormal(
    shape: Operand<T>,
    seed: Operand<U>,
    dtype: DataType<V>
  ): StatelessTruncatedNormal<V> = java.statelessTruncatedNormal<V, T, U>(shape, seed, dtype)

  public fun <U : TNumber, T : TNumber> truncatedNormal(
    shape: Operand<T>,
    dtype: DataType<U>,
    vararg options: TruncatedNormal.Options
  ): TruncatedNormal<U> = java.truncatedNormal<U, T>(shape, dtype, *options)

  public fun uniformCandidateSampler(
    trueClasses: Operand<TInt64>,
    numTrue: Long,
    numSampled: Long,
    unique: Boolean,
    rangeMax: Long,
    vararg options: UniformCandidateSampler.Options
  ): UniformCandidateSampler = java.uniformCandidateSampler(trueClasses, numTrue, numSampled,
      unique, rangeMax, *options)
}
