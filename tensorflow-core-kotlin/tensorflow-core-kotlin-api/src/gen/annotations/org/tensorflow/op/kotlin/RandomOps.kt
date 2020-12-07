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
 * An API for building `random` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class RandomOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.RandomOps = ops.java.random

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Generates labels for candidate sampling with a learned unigram distribution.
     *
     *  See explanations of candidate sampling and the data formats at
     *  go/candidate-sampling.
     *
     *  For each batch, this op picks a single set of sampled candidate labels.
     *
     *  The advantages of sampling candidates per-batch are simplicity and the
     *  possibility of efficient dense matrix multiplication. The disadvantage is that
     *  the sampled candidates must be chosen independently of the context and of the
     *  true labels.
     *
     * @param trueClasses A batch_size * num_true matrix, in which each row contains the
     *  IDs of the num_true target_classes in the corresponding original label.
     * @param numTrue Number of true labels per context.
     * @param numSampled Number of candidates to produce.
     * @param unique If unique is true, we sample with rejection, so that all sampled
     *  candidates in a batch are unique. This requires some approximation to
     *  estimate the post-rejection sampling probabilities.
     * @param options carries optional attributes values
     * @return a new instance of AllCandidateSampler
     * @see org.tensorflow.op.RandomOps.allCandidateSampler
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @param seed2 An second seed to avoid seed collision.
     */
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
            seed?.let { org.tensorflow.op.random.AllCandidateSampler.seed(it) },
            seed2?.let { org.tensorflow.op.random.AllCandidateSampler.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Generates labels for candidate sampling with a log-uniform distribution.
     *
     *  See explanations of candidate sampling and the data formats at
     *  go/candidate-sampling.
     *
     *  For each batch, this op picks a single set of sampled candidate labels.
     *
     *  The advantages of sampling candidates per-batch are simplicity and the
     *  possibility of efficient dense matrix multiplication. The disadvantage is that
     *  the sampled candidates must be chosen independently of the context and of the
     *  true labels.
     *
     * @param trueClasses A batch_size * num_true matrix, in which each row contains the
     *  IDs of the num_true target_classes in the corresponding original label.
     * @param numTrue Number of true labels per context.
     * @param numSampled Number of candidates to randomly sample.
     * @param unique If unique is true, we sample with rejection, so that all sampled
     *  candidates in a batch are unique. This requires some approximation to
     *  estimate the post-rejection sampling probabilities.
     * @param rangeMax The sampler will sample integers from the interval &#91;0, range_max).
     * @param options carries optional attributes values
     * @return a new instance of LogUniformCandidateSampler
     * @see org.tensorflow.op.RandomOps.logUniformCandidateSampler
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @param seed2 An second seed to avoid seed collision.
     */
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
            seed?.let { org.tensorflow.op.random.LogUniformCandidateSampler.seed(it) },
            seed2?.let { org.tensorflow.op.random.LogUniformCandidateSampler.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Draws samples from a multinomial distribution.
     *
     * @param U data type for ` output()` output
     * @param logits 2-D Tensor with shape `&#91;batch_size, num_classes]`.  Each slice `&#91;i,
     * :]`
     *  represents the unnormalized log probabilities for all classes.
     * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
     * @param options carries optional attributes values
     * @return a new instance of Multinomial
     * @see org.tensorflow.op.RandomOps.multinomial
     * @param seed If either seed or seed2 is set to be non-zero, the internal random number
     *  generator is seeded by the given seed.  Otherwise, a random seed is used.
     * @param seed2 A second seed to avoid seed collision.
     */
    public fun <T : TNumber> multinomial(
        logits: Operand<T>,
        numSamples: Operand<TInt32>,
        seed: Long? = null,
        seed2: Long? = null
    ): Multinomial<TInt64> = java.multinomial<T>(
        logits,
        numSamples,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.random.Multinomial.seed(it) },
            seed2?.let { org.tensorflow.op.random.Multinomial.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Draws samples from a multinomial distribution.
     *
     * @param U data type for ` output()` output
     * @param logits 2-D Tensor with shape `&#91;batch_size, num_classes]`.  Each slice `&#91;i,
     * :]`
     *  represents the unnormalized log probabilities for all classes.
     * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
     * @param outputDtype
     * @param options carries optional attributes values
     * @return a new instance of Multinomial
     * @see org.tensorflow.op.RandomOps.multinomial
     * @param seed If either seed or seed2 is set to be non-zero, the internal random number
     *  generator is seeded by the given seed.  Otherwise, a random seed is used.
     * @param seed2 A second seed to avoid seed collision.
     */
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
            seed?.let { org.tensorflow.op.random.Multinomial.seed(it) },
            seed2?.let { org.tensorflow.op.random.Multinomial.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Outputs random values from a normal distribution. The parameters may each be a
     *
     *  scalar which applies to the entire output, or a vector of length shape&#91;0] which
     *  stores the parameters for each batch.
     *
     * @param U data type for ` output()` output
     * @param shape The shape of the output tensor. Batches are indexed by the 0th dimension.
     * @param means The mean parameter of each batch.
     * @param stdevs The standard deviation parameter of each batch. Must be greater than 0.
     * @param minvals The minimum cutoff. May be -infinity.
     * @param maxvals The maximum cutoff. May be +infinity, and must be more than the minval
     *  for each batch.
     * @param options carries optional attributes values
     * @return a new instance of ParameterizedTruncatedNormal
     * @see org.tensorflow.op.RandomOps.parameterizedTruncatedNormal
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @param seed2 A second seed to avoid seed collision.
     */
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
            seed?.let { org.tensorflow.op.random.ParameterizedTruncatedNormal.seed(it) },
            seed2?.let { org.tensorflow.op.random.ParameterizedTruncatedNormal.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Outputs random values from the Gamma distribution(s) described by alpha.
     *
     *  This op uses the algorithm by Marsaglia et al. to acquire samples via
     *  transformation-rejection from pairs of uniform and normal random variables.
     *  See http://dl.acm.org/citation.cfm?id=358414
     *
     * @param U data type for ` output()` output
     * @param shape 1-D integer tensor. Shape of independent samples to draw from each
     *  distribution described by the shape parameters given in alpha.
     * @param alpha A tensor in which each scalar is a "shape" parameter describing the
     *  associated gamma distribution.
     * @param options carries optional attributes values
     * @return a new instance of RandomGamma
     * @see org.tensorflow.op.RandomOps.randomGamma
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @param seed2 A second seed to avoid seed collision.
     */
    public fun <U : TNumber, T : TNumber> randomGamma(
        shape: Operand<T>,
        alpha: Operand<U>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomGamma<U> = java.randomGamma<U, T>(
        shape,
        alpha,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.random.RandomGamma.seed(it) },
            seed2?.let { org.tensorflow.op.random.RandomGamma.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Outputs random values from the Poisson distribution(s) described by rate.
     *
     *  This op uses two algorithms, depending on rate. If rate >= 10, then
     *  the algorithm by Hormann is used to acquire samples via
     *  transformation-rejection.
     *  See http://www.sciencedirect.com/science/article/pii/0167668793909974.
     *
     *  Otherwise, Knuth's algorithm is used to acquire samples via multiplying uniform
     *  random variables.
     *  See Donald E. Knuth (1969). Seminumerical Algorithms. The Art of Computer
     *  Programming, Volume 2. Addison Wesley
     *
     * @param V data type for ` output()` output
     * @param shape 1-D integer tensor. Shape of independent samples to draw from each
     *  distribution described by the shape parameters given in rate.
     * @param rate A tensor in which each scalar is a "rate" parameter describing the
     *  associated poisson distribution.
     * @param options carries optional attributes values
     * @return a new instance of RandomPoisson
     * @see org.tensorflow.op.RandomOps.randomPoisson
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @param seed2 A second seed to avoid seed collision.
     */
    public fun <T : TNumber, U : TNumber> randomPoisson(
        shape: Operand<T>,
        rate: Operand<U>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomPoisson<TInt64> = java.randomPoisson<T, U>(
        shape,
        rate,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.random.RandomPoisson.seed(it) },
            seed2?.let { org.tensorflow.op.random.RandomPoisson.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Outputs random values from the Poisson distribution(s) described by rate.
     *
     *  This op uses two algorithms, depending on rate. If rate >= 10, then
     *  the algorithm by Hormann is used to acquire samples via
     *  transformation-rejection.
     *  See http://www.sciencedirect.com/science/article/pii/0167668793909974.
     *
     *  Otherwise, Knuth's algorithm is used to acquire samples via multiplying uniform
     *  random variables.
     *  See Donald E. Knuth (1969). Seminumerical Algorithms. The Art of Computer
     *  Programming, Volume 2. Addison Wesley
     *
     * @param V data type for ` output()` output
     * @param shape 1-D integer tensor. Shape of independent samples to draw from each
     *  distribution described by the shape parameters given in rate.
     * @param rate A tensor in which each scalar is a "rate" parameter describing the
     *  associated poisson distribution.
     * @param dtype
     * @param options carries optional attributes values
     * @return a new instance of RandomPoisson
     * @see org.tensorflow.op.RandomOps.randomPoisson
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @param seed2 A second seed to avoid seed collision.
     */
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
            seed?.let { org.tensorflow.op.random.RandomPoisson.seed(it) },
            seed2?.let { org.tensorflow.op.random.RandomPoisson.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Randomly shuffles a tensor along its first dimension.
     *
     *    The tensor is shuffled along dimension 0, such that each `value&#91;j]` is mapped
     *    to one and only one `output&#91;i]`. For example, a mapping that might occur for a
     *    3x2 tensor is:
     *  ```
     *  [[1, 2],       [[5, 6],
     *   [3, 4],  ==>   [1, 2],
     *   [5, 6]]        [3, 4]]
     *  ```
     *
     *
     * @param T data type for ` output()` output
     * @param value The tensor to be shuffled.
     * @param options carries optional attributes values
     * @return a new instance of RandomShuffle
     * @see org.tensorflow.op.RandomOps.randomShuffle
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @param seed2 A second seed to avoid seed collision.
     */
    public fun <T : TType> randomShuffle(
        value: Operand<T>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomShuffle<T> = java.randomShuffle<T>(
        value,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.random.RandomShuffle.seed(it) },
            seed2?.let { org.tensorflow.op.random.RandomShuffle.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Outputs random values from a normal distribution.
     *
     *  The generated values will have mean 0 and standard deviation 1.
     *
     * @param U data type for ` output()` output
     * @param shape The shape of the output tensor.
     * @param dtype The type of the output.
     * @param options carries optional attributes values
     * @return a new instance of RandomStandardNormal
     * @see org.tensorflow.op.RandomOps.randomStandardNormal
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @param seed2 A second seed to avoid seed collision.
     */
    public fun <U : TNumber, T : TNumber> randomStandardNormal(
        shape: Operand<T>,
        dtype: DataType<U>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomStandardNormal<U> = java.randomStandardNormal<U, T>(
        shape,
        dtype,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.random.RandomStandardNormal.seed(it) },
            seed2?.let { org.tensorflow.op.random.RandomStandardNormal.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Outputs random values from a uniform distribution.
     *
     *  The generated values follow a uniform distribution in the range `&#91;0, 1)`. The
     *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
     *
     * @param U data type for ` output()` output
     * @param shape The shape of the output tensor.
     * @param dtype The type of the output.
     * @param options carries optional attributes values
     * @return a new instance of RandomUniform
     * @see org.tensorflow.op.RandomOps.randomUniform
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @param seed2 A second seed to avoid seed collision.
     */
    public fun <U : TNumber, T : TNumber> randomUniform(
        shape: Operand<T>,
        dtype: DataType<U>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomUniform<U> = java.randomUniform<U, T>(
        shape,
        dtype,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.random.RandomUniform.seed(it) },
            seed2?.let { org.tensorflow.op.random.RandomUniform.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Outputs random integers from a uniform distribution.
     *
     *  The generated values are uniform integers in the range `&#91;minval, maxval)`.
     *  The lower bound `minval` is included in the range, while the upper bound
     *  `maxval` is excluded.
     *
     *  The random integers are slightly biased unless `maxval - minval` is an exact
     *  power of two.  The bias is small for values of `maxval - minval` significantly
     *  smaller than the range of the output (either `2^32` or `2^64`).
     *
     * @param U data type for ` output()` output
     * @param shape The shape of the output tensor.
     * @param minval 0-D.  Inclusive lower bound on the generated integers.
     * @param maxval 0-D.  Exclusive upper bound on the generated integers.
     * @param options carries optional attributes values
     * @return a new instance of RandomUniformInt
     * @see org.tensorflow.op.RandomOps.randomUniformInt
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @param seed2 A second seed to avoid seed collision.
     */
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
            seed?.let { org.tensorflow.op.random.RandomUniformInt.seed(it) },
            seed2?.let { org.tensorflow.op.random.RandomUniformInt.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Emits randomized records.
     *
     * @param filePattern Glob pattern for the data files.
     * @param options carries optional attributes values
     * @return a new instance of RecordInput
     * @see org.tensorflow.op.RandomOps.recordInput
     * @param fileRandomSeed Random seeds used to produce randomized records.
     * @param fileShuffleShiftRatio Shifts the list of files after the list is randomly
     *  shuffled.
     * @param fileBufferSize The randomization shuffling buffer.
     * @param fileParallelism How many sstables are opened and concurrently iterated over.
     * @param batchSize The batch size.
     * @param compressionType The type of compression for the file. Currently ZLIB and
     *  GZIP are supported. Defaults to none.
     */
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
            fileRandomSeed?.let { org.tensorflow.op.random.RecordInput.fileRandomSeed(it) },
            fileShuffleShiftRatio?.let { org.tensorflow.op.random.RecordInput.fileShuffleShiftRatio(it) },
            fileBufferSize?.let { org.tensorflow.op.random.RecordInput.fileBufferSize(it) },
            fileParallelism?.let { org.tensorflow.op.random.RecordInput.fileParallelism(it) },
            batchSize?.let { org.tensorflow.op.random.RecordInput.batchSize(it) },
            compressionType?.let { org.tensorflow.op.random.RecordInput.compressionType(it) }
        ).toTypedArray()
    )

    /**
     *
     * @param V data type for ` output()` output
     * @param resource
     * @param algorithm
     * @param shape
     * @param counts
     * @param probs
     * @return a new instance of StatefulRandomBinomial
     * @see org.tensorflow.op.RandomOps.statefulRandomBinomial
     */
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

    /**
     *
     * @param V data type for ` output()` output
     * @param resource
     * @param algorithm
     * @param shape
     * @param counts
     * @param probs
     * @param dtype
     * @return a new instance of StatefulRandomBinomial
     * @see org.tensorflow.op.RandomOps.statefulRandomBinomial
     */
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

    /**
     * Outputs random values from a normal distribution.
     *
     *  The generated values will have mean 0 and standard deviation 1.
     *
     * @param U data type for ` output()` output
     * @param resource The handle of the resource variable that stores the state of the RNG.
     * @param algorithm The RNG algorithm.
     * @param shape The shape of the output tensor.
     * @return a new instance of StatefulStandardNormal
     * @see org.tensorflow.op.RandomOps.statefulStandardNormal
     */
    public fun <T : TType> statefulStandardNormal(
        resource: Operand<*>,
        algorithm: Operand<TInt64>,
        shape: Operand<T>
    ): StatefulStandardNormal<TFloat32> = java.statefulStandardNormal<T>(
        resource,
        algorithm,
        shape
    )

    /**
     * Outputs random values from a normal distribution.
     *
     *  The generated values will have mean 0 and standard deviation 1.
     *
     * @param U data type for ` output()` output
     * @param resource The handle of the resource variable that stores the state of the RNG.
     * @param algorithm The RNG algorithm.
     * @param shape The shape of the output tensor.
     * @param dtype The type of the output.
     * @return a new instance of StatefulStandardNormal
     * @see org.tensorflow.op.RandomOps.statefulStandardNormal
     */
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

    /**
     * Draws samples from a multinomial distribution.
     *
     * @param V data type for ` output()` output
     * @param logits 2-D Tensor with shape `&#91;batch_size, num_classes]`.  Each slice `&#91;i,
     * :]`
     *  represents the unnormalized log probabilities for all classes.
     * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
     * @param seed 2 seeds (shape &#91;2]).
     * @return a new instance of StatelessMultinomial
     * @see org.tensorflow.op.RandomOps.statelessMultinomial
     */
    public fun <T : TNumber, U : TNumber> statelessMultinomial(
        logits: Operand<T>,
        numSamples: Operand<TInt32>,
        seed: Operand<U>
    ): StatelessMultinomial<TInt64> = java.statelessMultinomial<T, U>(
        logits,
        numSamples,
        seed
    )

    /**
     * Draws samples from a multinomial distribution.
     *
     * @param V data type for ` output()` output
     * @param logits 2-D Tensor with shape `&#91;batch_size, num_classes]`.  Each slice `&#91;i,
     * :]`
     *  represents the unnormalized log probabilities for all classes.
     * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
     * @param seed 2 seeds (shape &#91;2]).
     * @param outputDtype
     * @return a new instance of StatelessMultinomial
     * @see org.tensorflow.op.RandomOps.statelessMultinomial
     */
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

    /**
     * Outputs deterministic pseudorandom values from a normal distribution.
     *
     *  The generated values will have mean 0 and standard deviation 1.
     *
     *  The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param V data type for ` output()` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape &#91;2]).
     * @return a new instance of StatelessRandomNormal
     * @see org.tensorflow.op.RandomOps.statelessRandomNormal
     */
    public fun <T : TNumber, U : TNumber> statelessRandomNormal(
        shape: Operand<T>,
        seed: Operand<U>
    ): StatelessRandomNormal<TFloat32> = java.statelessRandomNormal<T, U>(
        shape,
        seed
    )

    /**
     * Outputs deterministic pseudorandom values from a normal distribution.
     *
     *  The generated values will have mean 0 and standard deviation 1.
     *
     *  The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param V data type for ` output()` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape &#91;2]).
     * @param dtype The type of the output.
     * @return a new instance of StatelessRandomNormal
     * @see org.tensorflow.op.RandomOps.statelessRandomNormal
     */
    public fun <V : TNumber, T : TNumber, U : TNumber> statelessRandomNormal(
        shape: Operand<T>,
        seed: Operand<U>,
        dtype: DataType<V>
    ): StatelessRandomNormal<V> = java.statelessRandomNormal<V, T, U>(
        shape,
        seed,
        dtype
    )

    /**
     * Outputs deterministic pseudorandom random values from a uniform distribution.
     *
     *  The generated values follow a uniform distribution in the range `&#91;0, 1)`. The
     *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
     *
     *  The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param V data type for ` output()` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape &#91;2]).
     * @return a new instance of StatelessRandomUniform
     * @see org.tensorflow.op.RandomOps.statelessRandomUniform
     */
    public fun <T : TNumber, U : TNumber> statelessRandomUniform(
        shape: Operand<T>,
        seed: Operand<U>
    ): StatelessRandomUniform<TFloat32> = java.statelessRandomUniform<T,
        U>(
        shape,
        seed
    )

    /**
     * Outputs deterministic pseudorandom random values from a uniform distribution.
     *
     *  The generated values follow a uniform distribution in the range `&#91;0, 1)`. The
     *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
     *
     *  The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param V data type for ` output()` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape &#91;2]).
     * @param dtype The type of the output.
     * @return a new instance of StatelessRandomUniform
     * @see org.tensorflow.op.RandomOps.statelessRandomUniform
     */
    public fun <V : TNumber, T : TNumber, U : TNumber> statelessRandomUniform(
        shape: Operand<T>,
        seed: Operand<U>,
        dtype: DataType<V>
    ): StatelessRandomUniform<V> = java.statelessRandomUniform<V, T, U>(
        shape,
        seed,
        dtype
    )

    /**
     * Outputs deterministic pseudorandom values from a truncated normal distribution.
     *
     *  The generated values follow a normal distribution with mean 0 and standard
     *  deviation 1, except that values whose magnitude is more than 2 standard
     *  deviations from the mean are dropped and re-picked.
     *
     *  The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param V data type for ` output()` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape &#91;2]).
     * @return a new instance of StatelessTruncatedNormal
     * @see org.tensorflow.op.RandomOps.statelessTruncatedNormal
     */
    public fun <T : TNumber, U : TNumber> statelessTruncatedNormal(
        shape: Operand<T>,
        seed: Operand<U>
    ): StatelessTruncatedNormal<TFloat32> = java.statelessTruncatedNormal<T,
        U>(
        shape,
        seed
    )

    /**
     * Outputs deterministic pseudorandom values from a truncated normal distribution.
     *
     *  The generated values follow a normal distribution with mean 0 and standard
     *  deviation 1, except that values whose magnitude is more than 2 standard
     *  deviations from the mean are dropped and re-picked.
     *
     *  The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param V data type for ` output()` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape &#91;2]).
     * @param dtype The type of the output.
     * @return a new instance of StatelessTruncatedNormal
     * @see org.tensorflow.op.RandomOps.statelessTruncatedNormal
     */
    public fun <V : TNumber, T : TNumber, U : TNumber> statelessTruncatedNormal(
        shape: Operand<T>,
        seed: Operand<U>,
        dtype: DataType<V>
    ): StatelessTruncatedNormal<V> = java.statelessTruncatedNormal<V, T, U>(
        shape,
        seed,
        dtype
    )

    /**
     * Outputs random values from a truncated normal distribution.
     *
     *  The generated values follow a normal distribution with mean 0 and standard
     *  deviation 1, except that values whose magnitude is more than 2 standard
     *  deviations from the mean are dropped and re-picked.
     *
     * @param U data type for ` output()` output
     * @param shape The shape of the output tensor.
     * @param dtype The type of the output.
     * @param options carries optional attributes values
     * @return a new instance of TruncatedNormal
     * @see org.tensorflow.op.RandomOps.truncatedNormal
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @param seed2 A second seed to avoid seed collision.
     */
    public fun <U : TNumber, T : TNumber> truncatedNormal(
        shape: Operand<T>,
        dtype: DataType<U>,
        seed: Long? = null,
        seed2: Long? = null
    ): TruncatedNormal<U> = java.truncatedNormal<U, T>(
        shape,
        dtype,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.random.TruncatedNormal.seed(it) },
            seed2?.let { org.tensorflow.op.random.TruncatedNormal.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Generates labels for candidate sampling with a uniform distribution.
     *
     *  See explanations of candidate sampling and the data formats at
     *  go/candidate-sampling.
     *
     *  For each batch, this op picks a single set of sampled candidate labels.
     *
     *  The advantages of sampling candidates per-batch are simplicity and the
     *  possibility of efficient dense matrix multiplication. The disadvantage is that
     *  the sampled candidates must be chosen independently of the context and of the
     *  true labels.
     *
     * @param trueClasses A batch_size * num_true matrix, in which each row contains the
     *  IDs of the num_true target_classes in the corresponding original label.
     * @param numTrue Number of true labels per context.
     * @param numSampled Number of candidates to randomly sample.
     * @param unique If unique is true, we sample with rejection, so that all sampled
     *  candidates in a batch are unique. This requires some approximation to
     *  estimate the post-rejection sampling probabilities.
     * @param rangeMax The sampler will sample integers from the interval &#91;0, range_max).
     * @param options carries optional attributes values
     * @return a new instance of UniformCandidateSampler
     * @see org.tensorflow.op.RandomOps.uniformCandidateSampler
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @param seed2 An second seed to avoid seed collision.
     */
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
            seed?.let { org.tensorflow.op.random.UniformCandidateSampler.seed(it) },
            seed2?.let { org.tensorflow.op.random.UniformCandidateSampler.seed2(it) }
        ).toTypedArray()
    )
}
