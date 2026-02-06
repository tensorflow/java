// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
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
package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.op.random.AllCandidateSampler;
import org.tensorflow.op.random.AnonymousRandomSeedGenerator;
import org.tensorflow.op.random.AnonymousSeedGenerator;
import org.tensorflow.op.random.DeleteRandomSeedGenerator;
import org.tensorflow.op.random.DeleteSeedGenerator;
import org.tensorflow.op.random.DummySeedGenerator;
import org.tensorflow.op.random.LogUniformCandidateSampler;
import org.tensorflow.op.random.Multinomial;
import org.tensorflow.op.random.NonDeterministicInts;
import org.tensorflow.op.random.ParameterizedTruncatedNormal;
import org.tensorflow.op.random.RandomGamma;
import org.tensorflow.op.random.RandomGammaGrad;
import org.tensorflow.op.random.RandomPoisson;
import org.tensorflow.op.random.RandomShuffle;
import org.tensorflow.op.random.RandomStandardNormal;
import org.tensorflow.op.random.RandomUniform;
import org.tensorflow.op.random.RandomUniformInt;
import org.tensorflow.op.random.RecordInput;
import org.tensorflow.op.random.RngReadAndSkip;
import org.tensorflow.op.random.RngSkip;
import org.tensorflow.op.random.StatefulRandomBinomial;
import org.tensorflow.op.random.StatefulStandardNormal;
import org.tensorflow.op.random.StatefulTruncatedNormal;
import org.tensorflow.op.random.StatefulUniform;
import org.tensorflow.op.random.StatefulUniformFullInt;
import org.tensorflow.op.random.StatefulUniformInt;
import org.tensorflow.op.random.StatelessMultinomial;
import org.tensorflow.op.random.StatelessParameterizedTruncatedNormal;
import org.tensorflow.op.random.StatelessRandomBinomial;
import org.tensorflow.op.random.StatelessRandomGamma;
import org.tensorflow.op.random.StatelessRandomGetAlg;
import org.tensorflow.op.random.StatelessRandomGetKeyCounter;
import org.tensorflow.op.random.StatelessRandomGetKeyCounterAlg;
import org.tensorflow.op.random.StatelessRandomNormal;
import org.tensorflow.op.random.StatelessRandomNormalV2;
import org.tensorflow.op.random.StatelessRandomPoisson;
import org.tensorflow.op.random.StatelessRandomUniform;
import org.tensorflow.op.random.StatelessRandomUniformFullInt;
import org.tensorflow.op.random.StatelessRandomUniformFullIntV2;
import org.tensorflow.op.random.StatelessRandomUniformInt;
import org.tensorflow.op.random.StatelessRandomUniformIntV2;
import org.tensorflow.op.random.StatelessRandomUniformV2;
import org.tensorflow.op.random.StatelessTruncatedNormal;
import org.tensorflow.op.random.StatelessTruncatedNormalV2;
import org.tensorflow.op.random.ThreadUnsafeUnigramCandidateSampler;
import org.tensorflow.op.random.TruncatedNormal;
import org.tensorflow.op.random.UniformCandidateSampler;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code random} operations as {@link Op Op}s
 *
 * @see Ops
 */
public final class RandomOps {
  public final RandomExperimentalOps experimental;

  private final Scope scope;

  private final Ops ops;

  RandomOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
    experimental = new RandomExperimentalOps(ops);
  }

  /**
   * Generates labels for candidate sampling with a learned unigram distribution.
   *  See explanations of candidate sampling and the data formats at
   *  go/candidate-sampling.
   *  <p>For each batch, this op picks a single set of sampled candidate labels.
   *  <p>The advantages of sampling candidates per-batch are simplicity and the
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
   */
  public AllCandidateSampler allCandidateSampler(Operand<TInt64> trueClasses, Long numTrue,
      Long numSampled, Boolean unique, AllCandidateSampler.Options... options) {
    return AllCandidateSampler.create(scope, trueClasses, numTrue, numSampled, unique, options);
  }

  /**
   * The AnonymousRandomSeedGenerator operation
   *
   * @param seed The seed value
   * @param seed2 The seed2 value
   * @return a new instance of AnonymousRandomSeedGenerator
   */
  public AnonymousRandomSeedGenerator anonymousRandomSeedGenerator(Operand<TInt64> seed,
      Operand<TInt64> seed2) {
    return AnonymousRandomSeedGenerator.create(scope, seed, seed2);
  }

  /**
   * The AnonymousSeedGenerator operation
   *
   * @param seed The seed value
   * @param seed2 The seed2 value
   * @param reshuffle The reshuffle value
   * @return a new instance of AnonymousSeedGenerator
   */
  public AnonymousSeedGenerator anonymousSeedGenerator(Operand<TInt64> seed, Operand<TInt64> seed2,
      Operand<TBool> reshuffle) {
    return AnonymousSeedGenerator.create(scope, seed, seed2, reshuffle);
  }

  /**
   * The DeleteRandomSeedGenerator operation
   *
   * @param handle The handle value
   * @param deleter The deleter value
   * @return a new instance of DeleteRandomSeedGenerator
   */
  public DeleteRandomSeedGenerator deleteRandomSeedGenerator(Operand<? extends TType> handle,
      Operand<? extends TType> deleter) {
    return DeleteRandomSeedGenerator.create(scope, handle, deleter);
  }

  /**
   * The DeleteSeedGenerator operation
   *
   * @param handle The handle value
   * @param deleter The deleter value
   * @return a new instance of DeleteSeedGenerator
   */
  public DeleteSeedGenerator deleteSeedGenerator(Operand<? extends TType> handle,
      Operand<? extends TType> deleter) {
    return DeleteSeedGenerator.create(scope, handle, deleter);
  }

  /**
   * The DummySeedGenerator operation
   *
   * @return a new instance of DummySeedGenerator
   */
  public DummySeedGenerator dummySeedGenerator() {
    return DummySeedGenerator.create(scope);
  }

  /**
   * Generates labels for candidate sampling with a log-uniform distribution.
   *  See explanations of candidate sampling and the data formats at
   *  go/candidate-sampling.
   *  <p>For each batch, this op picks a single set of sampled candidate labels.
   *  <p>The advantages of sampling candidates per-batch are simplicity and the
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
   */
  public LogUniformCandidateSampler logUniformCandidateSampler(Operand<TInt64> trueClasses,
      Long numTrue, Long numSampled, Boolean unique, Long rangeMax,
      LogUniformCandidateSampler.Options... options) {
    return LogUniformCandidateSampler.create(scope, trueClasses, numTrue, numSampled, unique, rangeMax, options);
  }

  /**
   * Draws samples from a multinomial distribution.
   *
   * @param logits 2-D Tensor with shape {@code [batch_size, num_classes]}.  Each slice {@code [i, :]}
   *  represents the unnormalized log probabilities for all classes.
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param options carries optional attribute values
   * @return a new instance of Multinomial, with default output types
   */
  public Multinomial<TInt64> multinomial(Operand<? extends TNumber> logits,
      Operand<TInt32> numSamples, Multinomial.Options... options) {
    return Multinomial.create(scope, logits, numSamples, options);
  }

  /**
   * Draws samples from a multinomial distribution.
   *
   * @param logits 2-D Tensor with shape {@code [batch_size, num_classes]}.  Each slice {@code [i, :]}
   *  represents the unnormalized log probabilities for all classes.
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param outputDtype The value of the outputDtype attribute
   * @param options carries optional attribute values
   * @param <U> data type for {@code Multinomial} output and operands
   * @return a new instance of Multinomial
   */
  public <U extends TNumber> Multinomial<U> multinomial(Operand<? extends TNumber> logits,
      Operand<TInt32> numSamples, Class<U> outputDtype, Multinomial.Options... options) {
    return Multinomial.create(scope, logits, numSamples, outputDtype, options);
  }

  /**
   * Non-deterministically generates some integers.
   *  This op may use some OS-provided source of non-determinism (e.g. an RNG), so each execution will give different results.
   *
   * @param shape The shape of the output tensor.
   * @return a new instance of NonDeterministicInts, with default output types
   */
  public NonDeterministicInts<TInt64> nonDeterministicInts(Operand<? extends TType> shape) {
    return NonDeterministicInts.create(scope, shape);
  }

  /**
   * Non-deterministically generates some integers.
   *  This op may use some OS-provided source of non-determinism (e.g. an RNG), so each execution will give different results.
   *
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param <U> data type for {@code NonDeterministicInts} output and operands
   * @return a new instance of NonDeterministicInts
   */
  public <U extends TType> NonDeterministicInts<U> nonDeterministicInts(
      Operand<? extends TType> shape, Class<U> dtype) {
    return NonDeterministicInts.create(scope, shape, dtype);
  }

  /**
   * Outputs random values from a normal distribution. The parameters may each be a
   *  scalar which applies to the entire output, or a vector of length shape[0] which
   *  stores the parameters for each batch.
   *
   * @param shape The shape of the output tensor. Batches are indexed by the 0th dimension.
   * @param means The mean parameter of each batch.
   * @param stdevs The standard deviation parameter of each batch. Must be greater than 0.
   * @param minvals The minimum cutoff. May be -infinity.
   * @param maxvals The maximum cutoff. May be +infinity, and must be more than the minval
   *  for each batch.
   * @param options carries optional attribute values
   * @param <U> data type for {@code ParameterizedTruncatedNormal} output and operands
   * @return a new instance of ParameterizedTruncatedNormal
   */
  public <U extends TNumber> ParameterizedTruncatedNormal<U> parameterizedTruncatedNormal(
      Operand<? extends TNumber> shape, Operand<U> means, Operand<U> stdevs, Operand<U> minvals,
      Operand<U> maxvals, ParameterizedTruncatedNormal.Options... options) {
    return ParameterizedTruncatedNormal.create(scope, shape, means, stdevs, minvals, maxvals, options);
  }

  /**
   * Outputs random values from the Gamma distribution(s) described by alpha.
   *  This op uses the algorithm by Marsaglia et al. to acquire samples via
   *  transformation-rejection from pairs of uniform and normal random variables.
   *  See http://dl.acm.org/citation.cfm?id=358414
   *
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   *  distribution described by the shape parameters given in alpha.
   * @param alpha A tensor in which each scalar is a &quot;shape&quot; parameter describing the
   *  associated gamma distribution.
   * @param options carries optional attribute values
   * @param <U> data type for {@code RandomGamma} output and operands
   * @return a new instance of RandomGamma
   */
  public <U extends TNumber> RandomGamma<U> randomGamma(Operand<? extends TNumber> shape,
      Operand<U> alpha, RandomGamma.Options... options) {
    return RandomGamma.create(scope, shape, alpha, options);
  }

  /**
   * Computes the derivative of a Gamma random sample w.r.t. {@code alpha}.
   *
   * @param alpha The alpha value
   * @param sample The sample value
   * @param <T> data type for {@code RandomGammaGrad} output and operands
   * @return a new instance of RandomGammaGrad
   */
  public <T extends TNumber> RandomGammaGrad<T> randomGammaGrad(Operand<T> alpha,
      Operand<T> sample) {
    return RandomGammaGrad.create(scope, alpha, sample);
  }

  /**
   * Outputs random values from the Poisson distribution(s) described by rate.
   *  This op uses two algorithms, depending on rate. If rate &gt;= 10, then
   *  the algorithm by Hormann is used to acquire samples via
   *  transformation-rejection.
   *  See http://www.sciencedirect.com/science/article/pii/0167668793909974.
   *  <p>Otherwise, Knuth's algorithm is used to acquire samples via multiplying uniform
   *  random variables.
   *  See Donald E. Knuth (1969). Seminumerical Algorithms. The Art of Computer
   *  Programming, Volume 2. Addison Wesley
   *
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   *  distribution described by the shape parameters given in rate.
   * @param rate A tensor in which each scalar is a &quot;rate&quot; parameter describing the
   *  associated poisson distribution.
   * @param options carries optional attribute values
   * @return a new instance of RandomPoisson, with default output types
   */
  public RandomPoisson<TInt64> randomPoisson(Operand<? extends TNumber> shape,
      Operand<? extends TNumber> rate, RandomPoisson.Options... options) {
    return RandomPoisson.create(scope, shape, rate, options);
  }

  /**
   * Outputs random values from the Poisson distribution(s) described by rate.
   *  This op uses two algorithms, depending on rate. If rate &gt;= 10, then
   *  the algorithm by Hormann is used to acquire samples via
   *  transformation-rejection.
   *  See http://www.sciencedirect.com/science/article/pii/0167668793909974.
   *  <p>Otherwise, Knuth's algorithm is used to acquire samples via multiplying uniform
   *  random variables.
   *  See Donald E. Knuth (1969). Seminumerical Algorithms. The Art of Computer
   *  Programming, Volume 2. Addison Wesley
   *
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   *  distribution described by the shape parameters given in rate.
   * @param rate A tensor in which each scalar is a &quot;rate&quot; parameter describing the
   *  associated poisson distribution.
   * @param dtype The value of the dtype attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code RandomPoissonV2} output and operands
   * @return a new instance of RandomPoisson
   */
  public <V extends TNumber> RandomPoisson<V> randomPoisson(Operand<? extends TNumber> shape,
      Operand<? extends TNumber> rate, Class<V> dtype, RandomPoisson.Options... options) {
    return RandomPoisson.create(scope, shape, rate, dtype, options);
  }

  /**
   * Randomly shuffles a tensor along its first dimension.
   *  The tensor is shuffled along dimension 0, such that each {@code value[j]} is mapped
   *  to one and only one {@code output[i]}. For example, a mapping that might occur for a
   *  3x2 tensor is:
   *  <pre>
   *  [[1, 2],       [[5, 6],
   *   [3, 4],  ==&gt;   [1, 2],
   *   [5, 6]]        [3, 4]]
   *  </pre>
   *
   * @param value The tensor to be shuffled.
   * @param options carries optional attribute values
   * @param <T> data type for {@code RandomShuffle} output and operands
   * @return a new instance of RandomShuffle
   */
  public <T extends TType> RandomShuffle<T> randomShuffle(Operand<T> value,
      RandomShuffle.Options... options) {
    return RandomShuffle.create(scope, value, options);
  }

  /**
   * Outputs random values from a normal distribution.
   *  The generated values will have mean 0 and standard deviation 1.
   *
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param options carries optional attribute values
   * @param <U> data type for {@code RandomStandardNormal} output and operands
   * @return a new instance of RandomStandardNormal
   */
  public <U extends TNumber> RandomStandardNormal<U> randomStandardNormal(
      Operand<? extends TNumber> shape, Class<U> dtype, RandomStandardNormal.Options... options) {
    return RandomStandardNormal.create(scope, shape, dtype, options);
  }

  /**
   * Outputs random values from a uniform distribution.
   *  The generated values follow a uniform distribution in the range {@code [0, 1)}. The
   *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
   *
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param options carries optional attribute values
   * @param <U> data type for {@code RandomUniform} output and operands
   * @return a new instance of RandomUniform
   */
  public <U extends TNumber> RandomUniform<U> randomUniform(Operand<? extends TNumber> shape,
      Class<U> dtype, RandomUniform.Options... options) {
    return RandomUniform.create(scope, shape, dtype, options);
  }

  /**
   * Outputs random integers from a uniform distribution.
   *  The generated values are uniform integers in the range {@code [minval, maxval)}.
   *  The lower bound {@code minval} is included in the range, while the upper bound
   *  {@code maxval} is excluded.
   *  <p>The random integers are slightly biased unless {@code maxval - minval} is an exact
   *  power of two.  The bias is small for values of {@code maxval - minval} significantly
   *  smaller than the range of the output (either {@code 2^32} or {@code 2^64}).
   *
   * @param shape The shape of the output tensor.
   * @param minval 0-D.  Inclusive lower bound on the generated integers.
   * @param maxval 0-D.  Exclusive upper bound on the generated integers.
   * @param options carries optional attribute values
   * @param <U> data type for {@code RandomUniformInt} output and operands
   * @return a new instance of RandomUniformInt
   */
  public <U extends TNumber> RandomUniformInt<U> randomUniformInt(Operand<? extends TNumber> shape,
      Operand<U> minval, Operand<U> maxval, RandomUniformInt.Options... options) {
    return RandomUniformInt.create(scope, shape, minval, maxval, options);
  }

  /**
   * Emits randomized records.
   *
   * @param filePattern Glob pattern for the data files.
   * @param options carries optional attribute values
   * @return a new instance of RecordInput
   */
  public RecordInput recordInput(String filePattern, RecordInput.Options... options) {
    return RecordInput.create(scope, filePattern, options);
  }

  /**
   * Advance the counter of a counter-based RNG.
   *  The state of the RNG after
   *  {@code rng_read_and_skip(n)} will be the same as that after {@code uniform([n])}
   *  (or any other distribution). The actual increment added to the
   *  counter is an unspecified implementation choice.
   *  <p>In the case that the input algorithm is RNG_ALG_AUTO_SELECT, the counter in the state needs to be of size int64[2], the current maximal counter size among algorithms. In this case, this op will manage the counter as if it is an 128-bit integer with layout [lower_64bits, higher_64bits]. If an algorithm needs less than 128 bits for the counter, it should use the left portion of the int64[2]. In this way, the int64[2] is compatible with all current RNG algorithms (Philox, ThreeFry and xla::RandomAlgorithm::RNG_DEFAULT). Downstream RNG ops can thus use this counter with any RNG algorithm.
   *
   * @param resource The handle of the resource variable that stores the state of the RNG. The state consists of the counter followed by the key.
   * @param alg The RNG algorithm.
   * @param delta The amount of advancement.
   * @return a new instance of RngReadAndSkip
   */
  public RngReadAndSkip rngReadAndSkip(Operand<? extends TType> resource, Operand<TInt32> alg,
      Operand<? extends TType> delta) {
    return RngReadAndSkip.create(scope, resource, alg, delta);
  }

  /**
   * Advance the counter of a counter-based RNG.
   *  The state of the RNG after
   *  {@code rng_skip(n)} will be the same as that after {@code stateful_uniform([n])}
   *  (or any other distribution). The actual increment added to the
   *  counter is an unspecified implementation detail.
   *
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param delta The amount of advancement.
   * @return a new instance of RngSkip
   */
  public RngSkip rngSkip(Operand<? extends TType> resource, Operand<TInt64> algorithm,
      Operand<TInt64> delta) {
    return RngSkip.create(scope, resource, algorithm, delta);
  }

  /**
   * The StatefulRandomBinomial operation
   *
   * @param resource The resource value
   * @param algorithm The algorithm value
   * @param shape The shape value
   * @param counts The counts value
   * @param probs The probs value
   * @param <U> data type for {@code StatefulRandomBinomial} output and operands
   * @return a new instance of StatefulRandomBinomial, with default output types
   */
  public <U extends TNumber> StatefulRandomBinomial<TInt64> statefulRandomBinomial(
      Operand<? extends TType> resource, Operand<TInt64> algorithm,
      Operand<? extends TNumber> shape, Operand<U> counts, Operand<U> probs) {
    return StatefulRandomBinomial.create(scope, resource, algorithm, shape, counts, probs);
  }

  /**
   * The StatefulRandomBinomial operation
   *
   * @param resource The resource value
   * @param algorithm The algorithm value
   * @param shape The shape value
   * @param counts The counts value
   * @param probs The probs value
   * @param dtype The value of the dtype attribute
   * @param <V> data type for {@code StatefulRandomBinomial} output and operands
   * @param <U> data type for {@code StatefulRandomBinomial} output and operands
   * @return a new instance of StatefulRandomBinomial
   */
  public <V extends TNumber, U extends TNumber> StatefulRandomBinomial<V> statefulRandomBinomial(
      Operand<? extends TType> resource, Operand<TInt64> algorithm,
      Operand<? extends TNumber> shape, Operand<U> counts, Operand<U> probs, Class<V> dtype) {
    return StatefulRandomBinomial.create(scope, resource, algorithm, shape, counts, probs, dtype);
  }

  /**
   * Outputs random values from a normal distribution.
   *  The generated values will have mean 0 and standard deviation 1.
   *
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @return a new instance of StatefulStandardNormal, with default output types
   */
  public StatefulStandardNormal<TFloat32> statefulStandardNormal(Operand<? extends TType> resource,
      Operand<TInt64> algorithm, Operand<? extends TType> shape) {
    return StatefulStandardNormal.create(scope, resource, algorithm, shape);
  }

  /**
   * Outputs random values from a normal distribution.
   *  The generated values will have mean 0 and standard deviation 1.
   *
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param <U> data type for {@code StatefulStandardNormalV2} output and operands
   * @return a new instance of StatefulStandardNormal
   */
  public <U extends TType> StatefulStandardNormal<U> statefulStandardNormal(
      Operand<? extends TType> resource, Operand<TInt64> algorithm, Operand<? extends TType> shape,
      Class<U> dtype) {
    return StatefulStandardNormal.create(scope, resource, algorithm, shape, dtype);
  }

  /**
   * Outputs random values from a truncated normal distribution.
   *  The generated values follow a normal distribution with mean 0 and standard
   *  deviation 1, except that values whose magnitude is more than 2 standard
   *  deviations from the mean are dropped and re-picked.
   *
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @return a new instance of StatefulTruncatedNormal, with default output types
   */
  public StatefulTruncatedNormal<TFloat32> statefulTruncatedNormal(
      Operand<? extends TType> resource, Operand<TInt64> algorithm,
      Operand<? extends TType> shape) {
    return StatefulTruncatedNormal.create(scope, resource, algorithm, shape);
  }

  /**
   * Outputs random values from a truncated normal distribution.
   *  The generated values follow a normal distribution with mean 0 and standard
   *  deviation 1, except that values whose magnitude is more than 2 standard
   *  deviations from the mean are dropped and re-picked.
   *
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param <U> data type for {@code StatefulTruncatedNormal} output and operands
   * @return a new instance of StatefulTruncatedNormal
   */
  public <U extends TType> StatefulTruncatedNormal<U> statefulTruncatedNormal(
      Operand<? extends TType> resource, Operand<TInt64> algorithm, Operand<? extends TType> shape,
      Class<U> dtype) {
    return StatefulTruncatedNormal.create(scope, resource, algorithm, shape, dtype);
  }

  /**
   * Outputs random values from a uniform distribution.
   *  The generated values follow a uniform distribution in the range {@code [0, 1)}. The
   *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
   *
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @return a new instance of StatefulUniform, with default output types
   */
  public StatefulUniform<TFloat32> statefulUniform(Operand<? extends TType> resource,
      Operand<TInt64> algorithm, Operand<? extends TType> shape) {
    return StatefulUniform.create(scope, resource, algorithm, shape);
  }

  /**
   * Outputs random values from a uniform distribution.
   *  The generated values follow a uniform distribution in the range {@code [0, 1)}. The
   *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
   *
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param <U> data type for {@code StatefulUniform} output and operands
   * @return a new instance of StatefulUniform
   */
  public <U extends TType> StatefulUniform<U> statefulUniform(Operand<? extends TType> resource,
      Operand<TInt64> algorithm, Operand<? extends TType> shape, Class<U> dtype) {
    return StatefulUniform.create(scope, resource, algorithm, shape, dtype);
  }

  /**
   * Outputs random integers from a uniform distribution.
   *  The generated values are uniform integers covering the whole range of {@code dtype}.
   *
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param <U> data type for {@code StatefulUniformFullInt} output and operands
   * @return a new instance of StatefulUniformFullInt
   */
  public <U extends TType> StatefulUniformFullInt<U> statefulUniformFullInt(
      Operand<? extends TType> resource, Operand<TInt64> algorithm, Operand<? extends TType> shape,
      Class<U> dtype) {
    return StatefulUniformFullInt.create(scope, resource, algorithm, shape, dtype);
  }

  /**
   * Outputs random integers from a uniform distribution.
   *  The generated values are uniform integers in the range {@code [minval, maxval)}.
   *  The lower bound {@code minval} is included in the range, while the upper bound
   *  {@code maxval} is excluded.
   *  <p>The random integers are slightly biased unless {@code maxval - minval} is an exact
   *  power of two.  The bias is small for values of {@code maxval - minval} significantly
   *  smaller than the range of the output (either {@code 2^32} or {@code 2^64}).
   *
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @param minval Minimum value (inclusive, scalar).
   * @param maxval Maximum value (exclusive, scalar).
   * @param <U> data type for {@code StatefulUniformInt} output and operands
   * @return a new instance of StatefulUniformInt
   */
  public <U extends TType> StatefulUniformInt<U> statefulUniformInt(
      Operand<? extends TType> resource, Operand<TInt64> algorithm, Operand<? extends TType> shape,
      Operand<U> minval, Operand<U> maxval) {
    return StatefulUniformInt.create(scope, resource, algorithm, shape, minval, maxval);
  }

  /**
   * Draws samples from a multinomial distribution.
   *
   * @param logits 2-D Tensor with shape {@code [batch_size, num_classes]}.  Each slice {@code [i, :]}
   *  represents the unnormalized log probabilities for all classes.
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessMultinomial, with default output types
   */
  public StatelessMultinomial<TInt64> statelessMultinomial(Operand<? extends TNumber> logits,
      Operand<TInt32> numSamples, Operand<? extends TNumber> seed) {
    return StatelessMultinomial.create(scope, logits, numSamples, seed);
  }

  /**
   * Draws samples from a multinomial distribution.
   *
   * @param logits 2-D Tensor with shape {@code [batch_size, num_classes]}.  Each slice {@code [i, :]}
   *  represents the unnormalized log probabilities for all classes.
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param seed 2 seeds (shape [2]).
   * @param outputDtype The value of the outputDtype attribute
   * @param <V> data type for {@code StatelessMultinomial} output and operands
   * @return a new instance of StatelessMultinomial
   */
  public <V extends TNumber> StatelessMultinomial<V> statelessMultinomial(
      Operand<? extends TNumber> logits, Operand<TInt32> numSamples,
      Operand<? extends TNumber> seed, Class<V> outputDtype) {
    return StatelessMultinomial.create(scope, logits, numSamples, seed, outputDtype);
  }

  /**
   * The StatelessParameterizedTruncatedNormal operation
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param means The mean parameter of each batch.
   * @param stddevs The standard deviation parameter of each batch. Must be greater than 0.
   * @param minvals The minimum cutoff. May be -infinity.
   * @param maxvals The maximum cutoff. May be +infinity, and must be more than the minval
   *  for each batch.
   * @param <V> data type for {@code StatelessParameterizedTruncatedNormal} output and operands
   * @return a new instance of StatelessParameterizedTruncatedNormal
   */
  public <V extends TNumber> StatelessParameterizedTruncatedNormal<V> statelessParameterizedTruncatedNormal(
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed, Operand<V> means,
      Operand<V> stddevs, Operand<V> minvals, Operand<V> maxvals) {
    return StatelessParameterizedTruncatedNormal.create(scope, shape, seed, means, stddevs, minvals, maxvals);
  }

  /**
   * Outputs deterministic pseudorandom random numbers from a binomial distribution.
   *  Outputs random values from a binomial distribution.
   *  <p>The outputs are a deterministic function of {@code shape}, {@code seed}, {@code counts}, and {@code probs}.
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param counts The counts of the binomial distribution. Must be broadcastable with {@code probs},
   *  and broadcastable with the rightmost dimensions of {@code shape}.
   * @param probs The probability of success for the binomial distribution. Must be broadcastable
   *  with {@code counts} and broadcastable with the rightmost dimensions of {@code shape}.
   * @param <V> data type for {@code StatelessRandomBinomial} output and operands
   * @return a new instance of StatelessRandomBinomial, with default output types
   */
  public <V extends TNumber> StatelessRandomBinomial<TInt64> statelessRandomBinomial(
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed, Operand<V> counts,
      Operand<V> probs) {
    return StatelessRandomBinomial.create(scope, shape, seed, counts, probs);
  }

  /**
   * Outputs deterministic pseudorandom random numbers from a binomial distribution.
   *  Outputs random values from a binomial distribution.
   *  <p>The outputs are a deterministic function of {@code shape}, {@code seed}, {@code counts}, and {@code probs}.
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param counts The counts of the binomial distribution. Must be broadcastable with {@code probs},
   *  and broadcastable with the rightmost dimensions of {@code shape}.
   * @param probs The probability of success for the binomial distribution. Must be broadcastable
   *  with {@code counts} and broadcastable with the rightmost dimensions of {@code shape}.
   * @param dtype The type of the output.
   * @param <W> data type for {@code StatelessRandomBinomial} output and operands
   * @param <V> data type for {@code StatelessRandomBinomial} output and operands
   * @return a new instance of StatelessRandomBinomial
   */
  public <W extends TNumber, V extends TNumber> StatelessRandomBinomial<W> statelessRandomBinomial(
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed, Operand<V> counts,
      Operand<V> probs, Class<W> dtype) {
    return StatelessRandomBinomial.create(scope, shape, seed, counts, probs, dtype);
  }

  /**
   * Outputs deterministic pseudorandom random numbers from a gamma distribution.
   *  Outputs random values from a gamma distribution.
   *  <p>The outputs are a deterministic function of the inputs.
   *
   * @param shape The shape of the output tensor.
   * @param key Key for the counter-based RNG algorithm (shape uint64[1]).
   * @param counter Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
   * @param alg The RNG algorithm (shape int32[]).
   * @param alpha The concentration of the gamma distribution. Shape must match the rightmost
   *  dimensions of {@code shape}.
   * @param <U> data type for {@code StatelessRandomGammaV3} output and operands
   * @return a new instance of StatelessRandomGamma
   */
  public <U extends TNumber> StatelessRandomGamma<U> statelessRandomGamma(
      Operand<? extends TNumber> shape, Operand<? extends TType> key,
      Operand<? extends TType> counter, Operand<TInt32> alg, Operand<U> alpha) {
    return StatelessRandomGamma.create(scope, shape, key, counter, alg, alpha);
  }

  /**
   * Picks the best counter-based RNG algorithm based on device.
   *  This op picks the best counter-based RNG algorithm based on device.
   *
   * @return a new instance of StatelessRandomGetAlg
   */
  public StatelessRandomGetAlg statelessRandomGetAlg() {
    return StatelessRandomGetAlg.create(scope);
  }

  /**
   * Scrambles seed into key and counter, using the best algorithm based on device.
   *  This op scrambles a shape-[2] seed into a key and a counter, both needed by counter-based RNG algorithms. The scrambing uses the best algorithm based on device. The scrambling is opaque but approximately satisfies the property that different seed results in different key/counter pair (which will in turn result in different random numbers).
   *
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessRandomGetKeyCounter
   */
  public StatelessRandomGetKeyCounter statelessRandomGetKeyCounter(
      Operand<? extends TNumber> seed) {
    return StatelessRandomGetKeyCounter.create(scope, seed);
  }

  /**
   * Picks the best algorithm based on device, and scrambles seed into key and counter.
   *  This op picks the best counter-based RNG algorithm based on device, and scrambles a shape-[2] seed into a key and a counter, both needed by the counter-based algorithm. The scrambling is opaque but approximately satisfies the property that different seed results in different key/counter pair (which will in turn result in different random numbers).
   *
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessRandomGetKeyCounterAlg
   */
  public StatelessRandomGetKeyCounterAlg statelessRandomGetKeyCounterAlg(
      Operand<? extends TNumber> seed) {
    return StatelessRandomGetKeyCounterAlg.create(scope, seed);
  }

  /**
   * Outputs deterministic pseudorandom values from a normal distribution.
   *  The generated values will have mean 0 and standard deviation 1.
   *  <p>The outputs are a deterministic function of {@code shape} and {@code seed}.
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessRandomNormal, with default output types
   */
  public StatelessRandomNormal<TFloat32> statelessRandomNormal(Operand<? extends TNumber> shape,
      Operand<? extends TNumber> seed) {
    return StatelessRandomNormal.create(scope, shape, seed);
  }

  /**
   * Outputs deterministic pseudorandom values from a normal distribution.
   *  The generated values will have mean 0 and standard deviation 1.
   *  <p>The outputs are a deterministic function of {@code shape} and {@code seed}.
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param dtype The type of the output.
   * @param <V> data type for {@code StatelessRandomNormal} output and operands
   * @return a new instance of StatelessRandomNormal
   */
  public <V extends TNumber> StatelessRandomNormal<V> statelessRandomNormal(
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed, Class<V> dtype) {
    return StatelessRandomNormal.create(scope, shape, seed, dtype);
  }

  /**
   * Outputs deterministic pseudorandom values from a normal distribution.
   *  The generated values will have mean 0 and standard deviation 1.
   *  <p>The outputs are a deterministic function of {@code shape}, {@code key}, {@code counter} and {@code alg}.
   *
   * @param shape The shape of the output tensor.
   * @param key Key for the counter-based RNG algorithm (shape uint64[1]).
   * @param counter Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
   * @param alg The RNG algorithm (shape int32[]).
   * @return a new instance of StatelessRandomNormalV2, with default output types
   */
  public StatelessRandomNormalV2<TFloat32> statelessRandomNormalV2(Operand<? extends TNumber> shape,
      Operand<? extends TType> key, Operand<? extends TType> counter, Operand<TInt32> alg) {
    return StatelessRandomNormalV2.create(scope, shape, key, counter, alg);
  }

  /**
   * Outputs deterministic pseudorandom values from a normal distribution.
   *  The generated values will have mean 0 and standard deviation 1.
   *  <p>The outputs are a deterministic function of {@code shape}, {@code key}, {@code counter} and {@code alg}.
   *
   * @param shape The shape of the output tensor.
   * @param key Key for the counter-based RNG algorithm (shape uint64[1]).
   * @param counter Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
   * @param alg The RNG algorithm (shape int32[]).
   * @param dtype The type of the output.
   * @param <U> data type for {@code StatelessRandomNormalV2} output and operands
   * @return a new instance of StatelessRandomNormalV2
   */
  public <U extends TNumber> StatelessRandomNormalV2<U> statelessRandomNormalV2(
      Operand<? extends TNumber> shape, Operand<? extends TType> key,
      Operand<? extends TType> counter, Operand<TInt32> alg, Class<U> dtype) {
    return StatelessRandomNormalV2.create(scope, shape, key, counter, alg, dtype);
  }

  /**
   * Outputs deterministic pseudorandom random numbers from a Poisson distribution.
   *  Outputs random values from a Poisson distribution.
   *  <p>The outputs are a deterministic function of {@code shape}, {@code seed}, and {@code lam}.
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param lam The rate of the Poisson distribution. Shape must match the rightmost dimensions
   *  of {@code shape}.
   * @param dtype The type of the output.
   * @param <W> data type for {@code StatelessRandomPoisson} output and operands
   * @return a new instance of StatelessRandomPoisson
   */
  public <W extends TNumber> StatelessRandomPoisson<W> statelessRandomPoisson(
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed,
      Operand<? extends TNumber> lam, Class<W> dtype) {
    return StatelessRandomPoisson.create(scope, shape, seed, lam, dtype);
  }

  /**
   * Outputs deterministic pseudorandom random values from a uniform distribution.
   *  The generated values follow a uniform distribution in the range {@code [0, 1)}. The
   *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
   *  <p>The outputs are a deterministic function of {@code shape} and {@code seed}.
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessRandomUniform, with default output types
   */
  public StatelessRandomUniform<TFloat32> statelessRandomUniform(Operand<? extends TNumber> shape,
      Operand<? extends TNumber> seed) {
    return StatelessRandomUniform.create(scope, shape, seed);
  }

  /**
   * Outputs deterministic pseudorandom random values from a uniform distribution.
   *  The generated values follow a uniform distribution in the range {@code [0, 1)}. The
   *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
   *  <p>The outputs are a deterministic function of {@code shape} and {@code seed}.
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param dtype The type of the output.
   * @param <V> data type for {@code StatelessRandomUniform} output and operands
   * @return a new instance of StatelessRandomUniform
   */
  public <V extends TNumber> StatelessRandomUniform<V> statelessRandomUniform(
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed, Class<V> dtype) {
    return StatelessRandomUniform.create(scope, shape, seed, dtype);
  }

  /**
   * Outputs deterministic pseudorandom random integers from a uniform distribution.
   *  The generated values are uniform integers covering the whole range of {@code dtype}.
   *  <p>The outputs are a deterministic function of {@code shape} and {@code seed}.
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param dtype The type of the output.
   * @param <V> data type for {@code StatelessRandomUniformFullInt} output and operands
   * @return a new instance of StatelessRandomUniformFullInt
   */
  public <V extends TNumber> StatelessRandomUniformFullInt<V> statelessRandomUniformFullInt(
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed, Class<V> dtype) {
    return StatelessRandomUniformFullInt.create(scope, shape, seed, dtype);
  }

  /**
   * Outputs deterministic pseudorandom random integers from a uniform distribution.
   *  The generated values are uniform integers covering the whole range of {@code dtype}.
   *  <p>The outputs are a deterministic function of {@code shape}, {@code key}, {@code counter} and {@code alg}.
   *
   * @param shape The shape of the output tensor.
   * @param key Key for the counter-based RNG algorithm (shape uint64[1]).
   * @param counter Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
   * @param alg The RNG algorithm (shape int32[]).
   * @param dtype The type of the output.
   * @param <U> data type for {@code StatelessRandomUniformFullIntV2} output and operands
   * @return a new instance of StatelessRandomUniformFullIntV2
   */
  public <U extends TNumber> StatelessRandomUniformFullIntV2<U> statelessRandomUniformFullIntV2(
      Operand<? extends TNumber> shape, Operand<? extends TType> key,
      Operand<? extends TType> counter, Operand<TInt32> alg, Class<U> dtype) {
    return StatelessRandomUniformFullIntV2.create(scope, shape, key, counter, alg, dtype);
  }

  /**
   * Outputs deterministic pseudorandom random integers from a uniform distribution.
   *  The generated values follow a uniform distribution in the range {@code [minval, maxval)}.
   *  <p>The outputs are a deterministic function of {@code shape}, {@code seed}, {@code minval}, and {@code maxval}.
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param minval Minimum value (inclusive, scalar).
   * @param maxval Maximum value (exclusive, scalar).
   * @param <V> data type for {@code StatelessRandomUniformInt} output and operands
   * @return a new instance of StatelessRandomUniformInt
   */
  public <V extends TNumber> StatelessRandomUniformInt<V> statelessRandomUniformInt(
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed, Operand<V> minval,
      Operand<V> maxval) {
    return StatelessRandomUniformInt.create(scope, shape, seed, minval, maxval);
  }

  /**
   * Outputs deterministic pseudorandom random integers from a uniform distribution.
   *  The generated values follow a uniform distribution in the range {@code [minval, maxval)}.
   *  <p>The outputs are a deterministic function of {@code shape}, {@code key}, {@code counter}, {@code alg}, {@code minval} and {@code maxval}.
   *
   * @param shape The shape of the output tensor.
   * @param key Key for the counter-based RNG algorithm (shape uint64[1]).
   * @param counter Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
   * @param alg The RNG algorithm (shape int32[]).
   * @param minval Minimum value (inclusive, scalar).
   * @param maxval Maximum value (exclusive, scalar).
   * @param <U> data type for {@code StatelessRandomUniformIntV2} output and operands
   * @return a new instance of StatelessRandomUniformIntV2
   */
  public <U extends TNumber> StatelessRandomUniformIntV2<U> statelessRandomUniformIntV2(
      Operand<? extends TNumber> shape, Operand<? extends TType> key,
      Operand<? extends TType> counter, Operand<TInt32> alg, Operand<U> minval, Operand<U> maxval) {
    return StatelessRandomUniformIntV2.create(scope, shape, key, counter, alg, minval, maxval);
  }

  /**
   * Outputs deterministic pseudorandom random values from a uniform distribution.
   *  The generated values follow a uniform distribution in the range {@code [0, 1)}. The
   *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
   *  <p>The outputs are a deterministic function of {@code shape}, {@code key}, {@code counter} and {@code alg}.
   *
   * @param shape The shape of the output tensor.
   * @param key Key for the counter-based RNG algorithm (shape uint64[1]).
   * @param counter Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
   * @param alg The RNG algorithm (shape int32[]).
   * @return a new instance of StatelessRandomUniformV2, with default output types
   */
  public StatelessRandomUniformV2<TFloat32> statelessRandomUniformV2(
      Operand<? extends TNumber> shape, Operand<? extends TType> key,
      Operand<? extends TType> counter, Operand<TInt32> alg) {
    return StatelessRandomUniformV2.create(scope, shape, key, counter, alg);
  }

  /**
   * Outputs deterministic pseudorandom random values from a uniform distribution.
   *  The generated values follow a uniform distribution in the range {@code [0, 1)}. The
   *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
   *  <p>The outputs are a deterministic function of {@code shape}, {@code key}, {@code counter} and {@code alg}.
   *
   * @param shape The shape of the output tensor.
   * @param key Key for the counter-based RNG algorithm (shape uint64[1]).
   * @param counter Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
   * @param alg The RNG algorithm (shape int32[]).
   * @param dtype The type of the output.
   * @param <U> data type for {@code StatelessRandomUniformV2} output and operands
   * @return a new instance of StatelessRandomUniformV2
   */
  public <U extends TNumber> StatelessRandomUniformV2<U> statelessRandomUniformV2(
      Operand<? extends TNumber> shape, Operand<? extends TType> key,
      Operand<? extends TType> counter, Operand<TInt32> alg, Class<U> dtype) {
    return StatelessRandomUniformV2.create(scope, shape, key, counter, alg, dtype);
  }

  /**
   * Outputs deterministic pseudorandom values from a truncated normal distribution.
   *  The generated values follow a normal distribution with mean 0 and standard
   *  deviation 1, except that values whose magnitude is more than 2 standard
   *  deviations from the mean are dropped and re-picked.
   *  <p>The outputs are a deterministic function of {@code shape} and {@code seed}.
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessTruncatedNormal, with default output types
   */
  public StatelessTruncatedNormal<TFloat32> statelessTruncatedNormal(
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed) {
    return StatelessTruncatedNormal.create(scope, shape, seed);
  }

  /**
   * Outputs deterministic pseudorandom values from a truncated normal distribution.
   *  The generated values follow a normal distribution with mean 0 and standard
   *  deviation 1, except that values whose magnitude is more than 2 standard
   *  deviations from the mean are dropped and re-picked.
   *  <p>The outputs are a deterministic function of {@code shape} and {@code seed}.
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param dtype The type of the output.
   * @param <V> data type for {@code StatelessTruncatedNormal} output and operands
   * @return a new instance of StatelessTruncatedNormal
   */
  public <V extends TNumber> StatelessTruncatedNormal<V> statelessTruncatedNormal(
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed, Class<V> dtype) {
    return StatelessTruncatedNormal.create(scope, shape, seed, dtype);
  }

  /**
   * Outputs deterministic pseudorandom values from a truncated normal distribution.
   *  The generated values follow a normal distribution with mean 0 and standard
   *  deviation 1, except that values whose magnitude is more than 2 standard
   *  deviations from the mean are dropped and re-picked.
   *  <p>The outputs are a deterministic function of {@code shape}, {@code key}, {@code counter} and {@code alg}.
   *
   * @param shape The shape of the output tensor.
   * @param key Key for the counter-based RNG algorithm (shape uint64[1]).
   * @param counter Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
   * @param alg The RNG algorithm (shape int32[]).
   * @return a new instance of StatelessTruncatedNormalV2, with default output types
   */
  public StatelessTruncatedNormalV2<TFloat32> statelessTruncatedNormalV2(
      Operand<? extends TNumber> shape, Operand<? extends TType> key,
      Operand<? extends TType> counter, Operand<TInt32> alg) {
    return StatelessTruncatedNormalV2.create(scope, shape, key, counter, alg);
  }

  /**
   * Outputs deterministic pseudorandom values from a truncated normal distribution.
   *  The generated values follow a normal distribution with mean 0 and standard
   *  deviation 1, except that values whose magnitude is more than 2 standard
   *  deviations from the mean are dropped and re-picked.
   *  <p>The outputs are a deterministic function of {@code shape}, {@code key}, {@code counter} and {@code alg}.
   *
   * @param shape The shape of the output tensor.
   * @param key Key for the counter-based RNG algorithm (shape uint64[1]).
   * @param counter Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
   * @param alg The RNG algorithm (shape int32[]).
   * @param dtype The type of the output.
   * @param <U> data type for {@code StatelessTruncatedNormalV2} output and operands
   * @return a new instance of StatelessTruncatedNormalV2
   */
  public <U extends TNumber> StatelessTruncatedNormalV2<U> statelessTruncatedNormalV2(
      Operand<? extends TNumber> shape, Operand<? extends TType> key,
      Operand<? extends TType> counter, Operand<TInt32> alg, Class<U> dtype) {
    return StatelessTruncatedNormalV2.create(scope, shape, key, counter, alg, dtype);
  }

  /**
   * Generates labels for candidate sampling with a learned unigram distribution.
   *  See explanations of candidate sampling and the data formats at
   *  go/candidate-sampling.
   *  <p>For each batch, this op picks a single set of sampled candidate labels.
   *  <p>The advantages of sampling candidates per-batch are simplicity and the
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
   * @return a new instance of ThreadUnsafeUnigramCandidateSampler
   */
  public ThreadUnsafeUnigramCandidateSampler threadUnsafeUnigramCandidateSampler(
      Operand<TInt64> trueClasses, Long numTrue, Long numSampled, Boolean unique, Long rangeMax,
      ThreadUnsafeUnigramCandidateSampler.Options... options) {
    return ThreadUnsafeUnigramCandidateSampler.create(scope, trueClasses, numTrue, numSampled, unique, rangeMax, options);
  }

  /**
   * Outputs random values from a truncated normal distribution.
   *  The generated values follow a normal distribution with mean 0 and standard
   *  deviation 1, except that values whose magnitude is more than 2 standard
   *  deviations from the mean are dropped and re-picked.
   *
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param options carries optional attribute values
   * @param <U> data type for {@code TruncatedNormal} output and operands
   * @return a new instance of TruncatedNormal
   */
  public <U extends TNumber> TruncatedNormal<U> truncatedNormal(Operand<? extends TNumber> shape,
      Class<U> dtype, TruncatedNormal.Options... options) {
    return TruncatedNormal.create(scope, shape, dtype, options);
  }

  /**
   * Generates labels for candidate sampling with a uniform distribution.
   *  See explanations of candidate sampling and the data formats at
   *  go/candidate-sampling.
   *  <p>For each batch, this op picks a single set of sampled candidate labels.
   *  <p>The advantages of sampling candidates per-batch are simplicity and the
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
   */
  public UniformCandidateSampler uniformCandidateSampler(Operand<TInt64> trueClasses, Long numTrue,
      Long numSampled, Boolean unique, Long rangeMax, UniformCandidateSampler.Options... options) {
    return UniformCandidateSampler.create(scope, trueClasses, numTrue, numSampled, unique, rangeMax, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
