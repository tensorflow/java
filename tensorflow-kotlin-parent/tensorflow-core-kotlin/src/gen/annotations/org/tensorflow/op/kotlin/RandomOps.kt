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
     *  See explanations of candidate sampling and the data formats at
     *  go/candidate-sampling.
     *
     * For each batch, this op picks a single set of sampled candidate labels.
     *
     * The advantages of sampling candidates per-batch are simplicity and the
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
     * @param options carries optional attribute values
     * @return a new instance of AllCandidateSampler
     * @see org.tensorflow.op.RandomOps.allCandidateSampler
     * @param seed Sets the seed option.
     *
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 An second seed to avoid seed collision.
     * @return this Options instance.
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
     *  See explanations of candidate sampling and the data formats at
     *  go/candidate-sampling.
     *
     * For each batch, this op picks a single set of sampled candidate labels.
     *
     * The advantages of sampling candidates per-batch are simplicity and the
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
     * @param rangeMax The sampler will sample integers from the interval [0, range_max).
     * @param options carries optional attribute values
     * @return a new instance of LogUniformCandidateSampler
     * @see org.tensorflow.op.RandomOps.logUniformCandidateSampler
     * @param seed Sets the seed option.
     *
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 An second seed to avoid seed collision.
     * @return this Options instance.
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
     * @param <U> data type for `output` output
     * @param logits 2-D Tensor with shape `&#91;batch_size, num_classes&#93;`.  Each slice `&#91;i,
     * :&#93;`
     *  represents the unnormalized log probabilities for all classes.
     * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
     * @param options carries optional attribute values
     * @return a new instance of Multinomial, with default output types
     * @see org.tensorflow.op.RandomOps.multinomial
     */
    public fun multinomial(
        logits: Operand<out TNumber>,
        numSamples: Operand<TInt32>,
        options: Array<Multinomial.Options>
    ): Multinomial<TInt64> = java.multinomial(
        logits,
        numSamples,
        options
    )

    /**
     * Draws samples from a multinomial distribution.
     *
     * @param <U> data type for `output` output
     * @param logits 2-D Tensor with shape `&#91;batch_size, num_classes&#93;`.  Each slice `&#91;i,
     * :&#93;`
     *  represents the unnormalized log probabilities for all classes.
     * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
     * @param outputDtype the value of the outputDtype property
     * @param options carries optional attribute values
     * @param <U> data type for `Multinomial` output and operands
     * @return a new instance of Multinomial
     * @see org.tensorflow.op.RandomOps.multinomial
     * @param seed Sets the seed option.
     *
     * @param seed If either seed or seed2 is set to be non-zero, the internal random number
     *  generator is seeded by the given seed.  Otherwise, a random seed is used.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    public fun <U : TNumber> multinomial(
        logits: Operand<out TNumber>,
        numSamples: Operand<TInt32>,
        outputDtype: Class<U>,
        seed: Long? = null,
        seed2: Long? = null
    ): Multinomial<U> = java.multinomial<U>(
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
     *  scalar which applies to the entire output, or a vector of length shape[0] which
     *  stores the parameters for each batch.
     *
     * @param <U> data type for `output` output
     * @param shape The shape of the output tensor. Batches are indexed by the 0th dimension.
     * @param means The mean parameter of each batch.
     * @param stdevs The standard deviation parameter of each batch. Must be greater than 0.
     * @param minvals The minimum cutoff. May be -infinity.
     * @param maxvals The maximum cutoff. May be +infinity, and must be more than the minval
     *  for each batch.
     * @param options carries optional attribute values
     * @param <U> data type for `ParameterizedTruncatedNormal` output and operands
     * @return a new instance of ParameterizedTruncatedNormal
     * @see org.tensorflow.op.RandomOps.parameterizedTruncatedNormal
     * @param seed Sets the seed option.
     *
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    public fun <U : TNumber> parameterizedTruncatedNormal(
        shape: Operand<out TNumber>,
        means: Operand<U>,
        stdevs: Operand<U>,
        minvals: Operand<U>,
        maxvals: Operand<U>,
        seed: Long? = null,
        seed2: Long? = null
    ): ParameterizedTruncatedNormal<U> = java.parameterizedTruncatedNormal<U>(
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
     *  This op uses the algorithm by Marsaglia et al. to acquire samples via
     *  transformation-rejection from pairs of uniform and normal random variables.
     *  See http://dl.acm.org/citation.cfm?id=358414
     *
     * @param <U> data type for `output` output
     * @param shape 1-D integer tensor. Shape of independent samples to draw from each
     *  distribution described by the shape parameters given in alpha.
     * @param alpha A tensor in which each scalar is a &quot;shape&quot; parameter describing the
     *  associated gamma distribution.
     * @param options carries optional attribute values
     * @param <U> data type for `RandomGamma` output and operands
     * @return a new instance of RandomGamma
     * @see org.tensorflow.op.RandomOps.randomGamma
     * @param seed Sets the seed option.
     *
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    public fun <U : TNumber> randomGamma(
        shape: Operand<out TNumber>,
        alpha: Operand<U>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomGamma<U> = java.randomGamma<U>(
        shape,
        alpha,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.random.RandomGamma.seed(it) },
            seed2?.let { org.tensorflow.op.random.RandomGamma.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Outputs random values from the Poisson distribution(s) described by rate.
     *  This op uses two algorithms, depending on rate. If rate >= 10, then
     *  the algorithm by Hormann is used to acquire samples via
     *  transformation-rejection.
     *  See http://www.sciencedirect.com/science/article/pii/0167668793909974.
     *
     * Otherwise, Knuth's algorithm is used to acquire samples via multiplying uniform
     *  random variables.
     *  See Donald E. Knuth (1969). Seminumerical Algorithms. The Art of Computer
     *  Programming, Volume 2. Addison Wesley
     *
     * @param <V> data type for `output` output
     * @param shape 1-D integer tensor. Shape of independent samples to draw from each
     *  distribution described by the shape parameters given in rate.
     * @param rate A tensor in which each scalar is a &quot;rate&quot; parameter describing the
     *  associated poisson distribution.
     * @param options carries optional attribute values
     * @return a new instance of RandomPoisson, with default output types
     * @see org.tensorflow.op.RandomOps.randomPoisson
     */
    public fun randomPoisson(
        shape: Operand<out TNumber>,
        rate: Operand<out TNumber>,
        options: Array<RandomPoisson.Options>
    ): RandomPoisson<TInt64> = java.randomPoisson(
        shape,
        rate,
        options
    )

    /**
     * Outputs random values from the Poisson distribution(s) described by rate.
     *  This op uses two algorithms, depending on rate. If rate >= 10, then
     *  the algorithm by Hormann is used to acquire samples via
     *  transformation-rejection.
     *  See http://www.sciencedirect.com/science/article/pii/0167668793909974.
     *
     * Otherwise, Knuth's algorithm is used to acquire samples via multiplying uniform
     *  random variables.
     *  See Donald E. Knuth (1969). Seminumerical Algorithms. The Art of Computer
     *  Programming, Volume 2. Addison Wesley
     *
     * @param <V> data type for `output` output
     * @param shape 1-D integer tensor. Shape of independent samples to draw from each
     *  distribution described by the shape parameters given in rate.
     * @param rate A tensor in which each scalar is a &quot;rate&quot; parameter describing the
     *  associated poisson distribution.
     * @param dtype the value of the dtype property
     * @param options carries optional attribute values
     * @param <V> data type for `RandomPoissonV2` output and operands
     * @return a new instance of RandomPoisson
     * @see org.tensorflow.op.RandomOps.randomPoisson
     * @param seed Sets the seed option.
     *
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    public fun <V : TNumber> randomPoisson(
        shape: Operand<out TNumber>,
        rate: Operand<out TNumber>,
        dtype: Class<V>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomPoisson<V> = java.randomPoisson<V>(
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
     *  The tensor is shuffled along dimension 0, such that each `value[j]` is mapped
     *  to one and only one `output[i]`. For example, a mapping that might occur for a
     *  3x2 tensor is:
     *  ```
     * [[1, 2],       [[5, 6],
     *   [3, 4],  ==>   [1, 2],
     *   [5, 6]]        [3, 4]]
     *
     * ```
     *
     * @param <T> data type for `output` output
     * @param value The tensor to be shuffled.
     * @param options carries optional attribute values
     * @param <T> data type for `RandomShuffle` output and operands
     * @return a new instance of RandomShuffle
     * @see org.tensorflow.op.RandomOps.randomShuffle
     * @param seed Sets the seed option.
     *
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
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
     *  The generated values will have mean 0 and standard deviation 1.
     *
     * @param <U> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param dtype The type of the output.
     * @param options carries optional attribute values
     * @param <U> data type for `RandomStandardNormal` output and operands
     * @return a new instance of RandomStandardNormal
     * @see org.tensorflow.op.RandomOps.randomStandardNormal
     * @param seed Sets the seed option.
     *
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    public fun <U : TNumber> randomStandardNormal(
        shape: Operand<out TNumber>,
        dtype: Class<U>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomStandardNormal<U> = java.randomStandardNormal<U>(
        shape,
        dtype,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.random.RandomStandardNormal.seed(it) },
            seed2?.let { org.tensorflow.op.random.RandomStandardNormal.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Outputs random values from a uniform distribution.
     *  The generated values follow a uniform distribution in the range `[0, 1)`. The
     *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
     *
     * @param <U> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param dtype The type of the output.
     * @param options carries optional attribute values
     * @param <U> data type for `RandomUniform` output and operands
     * @return a new instance of RandomUniform
     * @see org.tensorflow.op.RandomOps.randomUniform
     * @param seed Sets the seed option.
     *
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    public fun <U : TNumber> randomUniform(
        shape: Operand<out TNumber>,
        dtype: Class<U>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomUniform<U> = java.randomUniform<U>(
        shape,
        dtype,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.random.RandomUniform.seed(it) },
            seed2?.let { org.tensorflow.op.random.RandomUniform.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Outputs random integers from a uniform distribution.
     *  The generated values are uniform integers in the range `[minval, maxval)`.
     *  The lower bound `minval` is included in the range, while the upper bound
     *  `maxval` is excluded.
     *
     * The random integers are slightly biased unless `maxval - minval` is an exact
     *  power of two.  The bias is small for values of `maxval - minval` significantly
     *  smaller than the range of the output (either `2^32` or `2^64`).
     *
     * @param <U> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param minval 0-D.  Inclusive lower bound on the generated integers.
     * @param maxval 0-D.  Exclusive upper bound on the generated integers.
     * @param options carries optional attribute values
     * @param <U> data type for `RandomUniformInt` output and operands
     * @return a new instance of RandomUniformInt
     * @see org.tensorflow.op.RandomOps.randomUniformInt
     * @param seed Sets the seed option.
     *
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    public fun <U : TNumber> randomUniformInt(
        shape: Operand<out TNumber>,
        minval: Operand<U>,
        maxval: Operand<U>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomUniformInt<U> = java.randomUniformInt<U>(
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
     * @param options carries optional attribute values
     * @return a new instance of RecordInput
     * @see org.tensorflow.op.RandomOps.recordInput
     * @param fileRandomSeed Sets the fileRandomSeed option.
     *
     * @param fileRandomSeed Random seeds used to produce randomized records.
     * @return this Options instance.
     * @param fileShuffleShiftRatio Sets the fileShuffleShiftRatio option.
     *
     * @param fileShuffleShiftRatio Shifts the list of files after the list is randomly
     *  shuffled.
     * @return this Options instance.
     * @param fileBufferSize Sets the fileBufferSize option.
     *
     * @param fileBufferSize The randomization shuffling buffer.
     * @return this Options instance.
     * @param fileParallelism Sets the fileParallelism option.
     *
     * @param fileParallelism How many sstables are opened and concurrently iterated over.
     * @return this Options instance.
     * @param batchSize Sets the batchSize option.
     *
     * @param batchSize The batch size.
     * @return this Options instance.
     * @param compressionType Sets the compressionType option.
     *
     * @param compressionType The type of compression for the file. Currently ZLIB and
     *  GZIP are supported. Defaults to none.
     * @return this Options instance.
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
     * The StatefulRandomBinomial operation
     *
     * @param <V> data type for `output` output
     * @param resource the resource value
     * @param algorithm the algorithm value
     * @param shape the shape value
     * @param counts the counts value
     * @param probs the probs value
     * @param <U> data type for `StatefulRandomBinomial` output and operands
     * @return a new instance of StatefulRandomBinomial, with default output types
     * @see org.tensorflow.op.RandomOps.statefulRandomBinomial
     */
    public fun <U : TNumber> statefulRandomBinomial(
        resource: Operand<out TType>,
        algorithm: Operand<TInt64>,
        shape: Operand<out TNumber>,
        counts: Operand<U>,
        probs: Operand<U>
    ): StatefulRandomBinomial<TInt64> = java.statefulRandomBinomial<U>(
        resource,
        algorithm,
        shape,
        counts,
        probs
    )

    /**
     * The StatefulRandomBinomial operation
     *
     * @param <V> data type for `output` output
     * @param resource the resource value
     * @param algorithm the algorithm value
     * @param shape the shape value
     * @param counts the counts value
     * @param probs the probs value
     * @param dtype the value of the dtype property
     * @param <V> data type for `StatefulRandomBinomial` output and operands
     * @param <U> data type for `StatefulRandomBinomial` output and operands
     * @return a new instance of StatefulRandomBinomial
     * @see org.tensorflow.op.RandomOps.statefulRandomBinomial
     */
    public fun <V : TNumber, U : TNumber> statefulRandomBinomial(
        resource: Operand<out TType>,
        algorithm: Operand<TInt64>,
        shape: Operand<out TNumber>,
        counts: Operand<U>,
        probs: Operand<U>,
        dtype: Class<V>
    ): StatefulRandomBinomial<V> = java.statefulRandomBinomial<V, U>(
        resource,
        algorithm,
        shape,
        counts,
        probs,
        dtype
    )

    /**
     * Outputs random values from a normal distribution.
     *  The generated values will have mean 0 and standard deviation 1.
     *
     * @param <U> data type for `output` output
     * @param resource The handle of the resource variable that stores the state of the RNG.
     * @param algorithm The RNG algorithm.
     * @param shape The shape of the output tensor.
     * @return a new instance of StatefulStandardNormal, with default output types
     * @see org.tensorflow.op.RandomOps.statefulStandardNormal
     */
    public fun statefulStandardNormal(
        resource: Operand<out TType>,
        algorithm: Operand<TInt64>,
        shape: Operand<out TType>
    ): StatefulStandardNormal<TFloat32> = java.statefulStandardNormal(
        resource,
        algorithm,
        shape
    )

    /**
     * Outputs random values from a normal distribution.
     *  The generated values will have mean 0 and standard deviation 1.
     *
     * @param <U> data type for `output` output
     * @param resource The handle of the resource variable that stores the state of the RNG.
     * @param algorithm The RNG algorithm.
     * @param shape The shape of the output tensor.
     * @param dtype The type of the output.
     * @param <U> data type for `StatefulStandardNormalV2` output and operands
     * @return a new instance of StatefulStandardNormal
     * @see org.tensorflow.op.RandomOps.statefulStandardNormal
     */
    public fun <U : TType> statefulStandardNormal(
        resource: Operand<out TType>,
        algorithm: Operand<TInt64>,
        shape: Operand<out TType>,
        dtype: Class<U>
    ): StatefulStandardNormal<U> = java.statefulStandardNormal<U>(
        resource,
        algorithm,
        shape,
        dtype
    )

    /**
     * Draws samples from a multinomial distribution.
     *
     * @param <V> data type for `output` output
     * @param logits 2-D Tensor with shape `&#91;batch_size, num_classes&#93;`.  Each slice `&#91;i,
     * :&#93;`
     *  represents the unnormalized log probabilities for all classes.
     * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
     * @param seed 2 seeds (shape [2]).
     * @return a new instance of StatelessMultinomial, with default output types
     * @see org.tensorflow.op.RandomOps.statelessMultinomial
     */
    public fun statelessMultinomial(
        logits: Operand<out TNumber>,
        numSamples: Operand<TInt32>,
        seed: Operand<out TNumber>
    ): StatelessMultinomial<TInt64> = java.statelessMultinomial(
        logits,
        numSamples,
        seed
    )

    /**
     * Draws samples from a multinomial distribution.
     *
     * @param <V> data type for `output` output
     * @param logits 2-D Tensor with shape `&#91;batch_size, num_classes&#93;`.  Each slice `&#91;i,
     * :&#93;`
     *  represents the unnormalized log probabilities for all classes.
     * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
     * @param seed 2 seeds (shape [2]).
     * @param outputDtype the value of the outputDtype property
     * @param <V> data type for `StatelessMultinomial` output and operands
     * @return a new instance of StatelessMultinomial
     * @see org.tensorflow.op.RandomOps.statelessMultinomial
     */
    public fun <V : TNumber> statelessMultinomial(
        logits: Operand<out TNumber>,
        numSamples: Operand<TInt32>,
        seed: Operand<out TNumber>,
        outputDtype: Class<V>
    ): StatelessMultinomial<V> = java.statelessMultinomial<V>(
        logits,
        numSamples,
        seed,
        outputDtype
    )

    /**
     * Outputs deterministic pseudorandom values from a normal distribution.
     *  The generated values will have mean 0 and standard deviation 1.
     *
     * The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param <V> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape [2]).
     * @return a new instance of StatelessRandomNormal, with default output types
     * @see org.tensorflow.op.RandomOps.statelessRandomNormal
     */
    public fun statelessRandomNormal(shape: Operand<out TNumber>, seed: Operand<out TNumber>):
        StatelessRandomNormal<TFloat32> = java.statelessRandomNormal(
        shape,
        seed
    )

    /**
     * Outputs deterministic pseudorandom values from a normal distribution.
     *  The generated values will have mean 0 and standard deviation 1.
     *
     * The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param <V> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape [2]).
     * @param dtype The type of the output.
     * @param <V> data type for `StatelessRandomNormal` output and operands
     * @return a new instance of StatelessRandomNormal
     * @see org.tensorflow.op.RandomOps.statelessRandomNormal
     */
    public fun <V : TNumber> statelessRandomNormal(
        shape: Operand<out TNumber>,
        seed: Operand<out TNumber>,
        dtype: Class<V>
    ): StatelessRandomNormal<V> = java.statelessRandomNormal<V>(
        shape,
        seed,
        dtype
    )

    /**
     * Outputs deterministic pseudorandom random values from a uniform distribution.
     *  The generated values follow a uniform distribution in the range `[0, 1)`. The
     *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
     *
     * The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param <V> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape [2]).
     * @return a new instance of StatelessRandomUniform, with default output types
     * @see org.tensorflow.op.RandomOps.statelessRandomUniform
     */
    public fun statelessRandomUniform(shape: Operand<out TNumber>, seed: Operand<out TNumber>):
        StatelessRandomUniform<TFloat32> = java.statelessRandomUniform(
        shape,
        seed
    )

    /**
     * Outputs deterministic pseudorandom random values from a uniform distribution.
     *  The generated values follow a uniform distribution in the range `[0, 1)`. The
     *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
     *
     * The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param <V> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape [2]).
     * @param dtype The type of the output.
     * @param <V> data type for `StatelessRandomUniform` output and operands
     * @return a new instance of StatelessRandomUniform
     * @see org.tensorflow.op.RandomOps.statelessRandomUniform
     */
    public fun <V : TNumber> statelessRandomUniform(
        shape: Operand<out TNumber>,
        seed: Operand<out TNumber>,
        dtype: Class<V>
    ): StatelessRandomUniform<V> = java.statelessRandomUniform<V>(
        shape,
        seed,
        dtype
    )

    /**
     * Outputs deterministic pseudorandom values from a truncated normal distribution.
     *  The generated values follow a normal distribution with mean 0 and standard
     *  deviation 1, except that values whose magnitude is more than 2 standard
     *  deviations from the mean are dropped and re-picked.
     *
     * The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param <V> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape [2]).
     * @return a new instance of StatelessTruncatedNormal, with default output types
     * @see org.tensorflow.op.RandomOps.statelessTruncatedNormal
     */
    public fun statelessTruncatedNormal(shape: Operand<out TNumber>, seed: Operand<out TNumber>):
        StatelessTruncatedNormal<TFloat32> = java.statelessTruncatedNormal(
        shape,
        seed
    )

    /**
     * Outputs deterministic pseudorandom values from a truncated normal distribution.
     *  The generated values follow a normal distribution with mean 0 and standard
     *  deviation 1, except that values whose magnitude is more than 2 standard
     *  deviations from the mean are dropped and re-picked.
     *
     * The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param <V> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape [2]).
     * @param dtype The type of the output.
     * @param <V> data type for `StatelessTruncatedNormal` output and operands
     * @return a new instance of StatelessTruncatedNormal
     * @see org.tensorflow.op.RandomOps.statelessTruncatedNormal
     */
    public fun <V : TNumber> statelessTruncatedNormal(
        shape: Operand<out TNumber>,
        seed: Operand<out TNumber>,
        dtype: Class<V>
    ): StatelessTruncatedNormal<V> = java.statelessTruncatedNormal<V>(
        shape,
        seed,
        dtype
    )

    /**
     * Outputs random values from a truncated normal distribution.
     *  The generated values follow a normal distribution with mean 0 and standard
     *  deviation 1, except that values whose magnitude is more than 2 standard
     *  deviations from the mean are dropped and re-picked.
     *
     * @param <U> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param dtype The type of the output.
     * @param options carries optional attribute values
     * @param <U> data type for `TruncatedNormal` output and operands
     * @return a new instance of TruncatedNormal
     * @see org.tensorflow.op.RandomOps.truncatedNormal
     * @param seed Sets the seed option.
     *
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    public fun <U : TNumber> truncatedNormal(
        shape: Operand<out TNumber>,
        dtype: Class<U>,
        seed: Long? = null,
        seed2: Long? = null
    ): TruncatedNormal<U> = java.truncatedNormal<U>(
        shape,
        dtype,
        *listOfNotNull(
            seed?.let { org.tensorflow.op.random.TruncatedNormal.seed(it) },
            seed2?.let { org.tensorflow.op.random.TruncatedNormal.seed2(it) }
        ).toTypedArray()
    )

    /**
     * Generates labels for candidate sampling with a uniform distribution.
     *  See explanations of candidate sampling and the data formats at
     *  go/candidate-sampling.
     *
     * For each batch, this op picks a single set of sampled candidate labels.
     *
     * The advantages of sampling candidates per-batch are simplicity and the
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
     * @param rangeMax The sampler will sample integers from the interval [0, range_max).
     * @param options carries optional attribute values
     * @return a new instance of UniformCandidateSampler
     * @see org.tensorflow.op.RandomOps.uniformCandidateSampler
     * @param seed Sets the seed option.
     *
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 An second seed to avoid seed collision.
     * @return this Options instance.
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

    /**
     * Draws samples from a multinomial distribution.
     *
     * @param <U> data type for `output` output
     * @param logits 2-D Tensor with shape `&#91;batch_size, num_classes&#93;`.  Each slice `&#91;i,
     * :&#93;`
     *  represents the unnormalized log probabilities for all classes.
     * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
     * @param outputDtype the value of the outputDtype property
     * @param options carries optional attribute values
     * @param <U> data type for `Multinomial` output and operands
     * @return a new instance of Multinomial
     * @see org.tensorflow.op.RandomOps.multinomial
     * @param seed Sets the seed option.
     *
     * @param seed If either seed or seed2 is set to be non-zero, the internal random number
     *  generator is seeded by the given seed.  Otherwise, a random seed is used.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    @JvmName("multinomialReified")
    public inline fun <reified U : TNumber> multinomial(
        logits: Operand<out TNumber>,
        numSamples: Operand<TInt32>,
        seed: Long? = null,
        seed2: Long? = null
    ): Multinomial<U> = multinomial<U>(logits, numSamples, U::class.java, seed, seed2)

    /**
     * Outputs random values from the Poisson distribution(s) described by rate.
     *  This op uses two algorithms, depending on rate. If rate >= 10, then
     *  the algorithm by Hormann is used to acquire samples via
     *  transformation-rejection.
     *  See http://www.sciencedirect.com/science/article/pii/0167668793909974.
     *
     * Otherwise, Knuth's algorithm is used to acquire samples via multiplying uniform
     *  random variables.
     *  See Donald E. Knuth (1969). Seminumerical Algorithms. The Art of Computer
     *  Programming, Volume 2. Addison Wesley
     *
     * @param <V> data type for `output` output
     * @param shape 1-D integer tensor. Shape of independent samples to draw from each
     *  distribution described by the shape parameters given in rate.
     * @param rate A tensor in which each scalar is a &quot;rate&quot; parameter describing the
     *  associated poisson distribution.
     * @param dtype the value of the dtype property
     * @param options carries optional attribute values
     * @param <V> data type for `RandomPoissonV2` output and operands
     * @return a new instance of RandomPoisson
     * @see org.tensorflow.op.RandomOps.randomPoisson
     * @param seed Sets the seed option.
     *
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    @JvmName("randomPoissonReified")
    public inline fun <reified V : TNumber> randomPoisson(
        shape: Operand<out TNumber>,
        rate: Operand<out TNumber>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomPoisson<V> = randomPoisson<V>(shape, rate, V::class.java, seed, seed2)

    /**
     * Outputs random values from a normal distribution.
     *  The generated values will have mean 0 and standard deviation 1.
     *
     * @param <U> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param dtype The type of the output.
     * @param options carries optional attribute values
     * @param <U> data type for `RandomStandardNormal` output and operands
     * @return a new instance of RandomStandardNormal
     * @see org.tensorflow.op.RandomOps.randomStandardNormal
     * @param seed Sets the seed option.
     *
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    @JvmName("randomStandardNormalReified")
    public inline fun <reified U : TNumber> randomStandardNormal(
        shape: Operand<out TNumber>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomStandardNormal<U> = randomStandardNormal<U>(shape, U::class.java, seed, seed2)

    /**
     * Outputs random values from a uniform distribution.
     *  The generated values follow a uniform distribution in the range `[0, 1)`. The
     *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
     *
     * @param <U> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param dtype The type of the output.
     * @param options carries optional attribute values
     * @param <U> data type for `RandomUniform` output and operands
     * @return a new instance of RandomUniform
     * @see org.tensorflow.op.RandomOps.randomUniform
     * @param seed Sets the seed option.
     *
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    @JvmName("randomUniformReified")
    public inline fun <reified U : TNumber> randomUniform(
        shape: Operand<out TNumber>,
        seed: Long? = null,
        seed2: Long? = null
    ): RandomUniform<U> = randomUniform<U>(shape, U::class.java, seed, seed2)

    /**
     * The StatefulRandomBinomial operation
     *
     * @param <V> data type for `output` output
     * @param resource the resource value
     * @param algorithm the algorithm value
     * @param shape the shape value
     * @param counts the counts value
     * @param probs the probs value
     * @param dtype the value of the dtype property
     * @param <V> data type for `StatefulRandomBinomial` output and operands
     * @param <U> data type for `StatefulRandomBinomial` output and operands
     * @return a new instance of StatefulRandomBinomial
     * @see org.tensorflow.op.RandomOps.statefulRandomBinomial
     */
    @JvmName("statefulRandomBinomialReified")
    public inline fun <reified V : TNumber, U : TNumber> statefulRandomBinomialTyped(
        resource: Operand<out TType>,
        algorithm: Operand<TInt64>,
        shape: Operand<out TNumber>,
        counts: Operand<U>,
        probs: Operand<U>
    ): StatefulRandomBinomial<V> = statefulRandomBinomial<V, U>(
        resource, algorithm, shape, counts,
        probs, V::class.java
    )

    /**
     * Outputs random values from a normal distribution.
     *  The generated values will have mean 0 and standard deviation 1.
     *
     * @param <U> data type for `output` output
     * @param resource The handle of the resource variable that stores the state of the RNG.
     * @param algorithm The RNG algorithm.
     * @param shape The shape of the output tensor.
     * @param dtype The type of the output.
     * @param <U> data type for `StatefulStandardNormalV2` output and operands
     * @return a new instance of StatefulStandardNormal
     * @see org.tensorflow.op.RandomOps.statefulStandardNormal
     */
    @JvmName("statefulStandardNormalReified")
    public inline fun <reified U : TType> statefulStandardNormalTyped(
        resource: Operand<out TType>,
        algorithm: Operand<TInt64>,
        shape: Operand<out TType>
    ): StatefulStandardNormal<U> = statefulStandardNormal<U>(
        resource, algorithm, shape,
        U::class.java
    )

    /**
     * Draws samples from a multinomial distribution.
     *
     * @param <V> data type for `output` output
     * @param logits 2-D Tensor with shape `&#91;batch_size, num_classes&#93;`.  Each slice `&#91;i,
     * :&#93;`
     *  represents the unnormalized log probabilities for all classes.
     * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
     * @param seed 2 seeds (shape [2]).
     * @param outputDtype the value of the outputDtype property
     * @param <V> data type for `StatelessMultinomial` output and operands
     * @return a new instance of StatelessMultinomial
     * @see org.tensorflow.op.RandomOps.statelessMultinomial
     */
    @JvmName("statelessMultinomialReified")
    public inline fun <reified V : TNumber> statelessMultinomialTyped(
        logits: Operand<out TNumber>,
        numSamples: Operand<TInt32>,
        seed: Operand<out TNumber>
    ): StatelessMultinomial<V> = statelessMultinomial<V>(logits, numSamples, seed, V::class.java)

    /**
     * Outputs deterministic pseudorandom values from a normal distribution.
     *  The generated values will have mean 0 and standard deviation 1.
     *
     * The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param <V> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape [2]).
     * @param dtype The type of the output.
     * @param <V> data type for `StatelessRandomNormal` output and operands
     * @return a new instance of StatelessRandomNormal
     * @see org.tensorflow.op.RandomOps.statelessRandomNormal
     */
    @JvmName("statelessRandomNormalReified")
    public inline fun <reified V : TNumber> statelessRandomNormalTyped(
        shape: Operand<out TNumber>,
        seed: Operand<out TNumber>
    ): StatelessRandomNormal<V> = statelessRandomNormal<V>(
        shape,
        seed, V::class.java
    )

    /**
     * Outputs deterministic pseudorandom random values from a uniform distribution.
     *  The generated values follow a uniform distribution in the range `[0, 1)`. The
     *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
     *
     * The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param <V> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape [2]).
     * @param dtype The type of the output.
     * @param <V> data type for `StatelessRandomUniform` output and operands
     * @return a new instance of StatelessRandomUniform
     * @see org.tensorflow.op.RandomOps.statelessRandomUniform
     */
    @JvmName("statelessRandomUniformReified")
    public inline fun <reified V : TNumber> statelessRandomUniformTyped(
        shape: Operand<out TNumber>,
        seed: Operand<out TNumber>
    ): StatelessRandomUniform<V> =
        statelessRandomUniform<V>(shape, seed, V::class.java)

    /**
     * Outputs deterministic pseudorandom values from a truncated normal distribution.
     *  The generated values follow a normal distribution with mean 0 and standard
     *  deviation 1, except that values whose magnitude is more than 2 standard
     *  deviations from the mean are dropped and re-picked.
     *
     * The outputs are a deterministic function of `shape` and `seed`.
     *
     * @param <V> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param seed 2 seeds (shape [2]).
     * @param dtype The type of the output.
     * @param <V> data type for `StatelessTruncatedNormal` output and operands
     * @return a new instance of StatelessTruncatedNormal
     * @see org.tensorflow.op.RandomOps.statelessTruncatedNormal
     */
    @JvmName("statelessTruncatedNormalReified")
    public inline fun <reified V : TNumber> statelessTruncatedNormalTyped(
        shape: Operand<out
            TNumber>,
        seed: Operand<out TNumber>
    ): StatelessTruncatedNormal<V> =
        statelessTruncatedNormal<V>(shape, seed, V::class.java)

    /**
     * Outputs random values from a truncated normal distribution.
     *  The generated values follow a normal distribution with mean 0 and standard
     *  deviation 1, except that values whose magnitude is more than 2 standard
     *  deviations from the mean are dropped and re-picked.
     *
     * @param <U> data type for `output` output
     * @param shape The shape of the output tensor.
     * @param dtype The type of the output.
     * @param options carries optional attribute values
     * @param <U> data type for `TruncatedNormal` output and operands
     * @return a new instance of TruncatedNormal
     * @see org.tensorflow.op.RandomOps.truncatedNormal
     * @param seed Sets the seed option.
     *
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, it is seeded by a
     *  random seed.
     * @return this Options instance.
     * @param seed2 Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    @JvmName("truncatedNormalReified")
    public inline fun <reified U : TNumber> truncatedNormal(
        shape: Operand<out TNumber>,
        seed: Long? = null,
        seed2: Long? = null
    ): TruncatedNormal<U> = truncatedNormal<U>(shape, U::class.java, seed, seed2)
}
