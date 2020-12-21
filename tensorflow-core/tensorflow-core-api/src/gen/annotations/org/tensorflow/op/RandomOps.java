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
package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.op.random.AllCandidateSampler;
import org.tensorflow.op.random.LogUniformCandidateSampler;
import org.tensorflow.op.random.Multinomial;
import org.tensorflow.op.random.ParameterizedTruncatedNormal;
import org.tensorflow.op.random.RandomGamma;
import org.tensorflow.op.random.RandomPoisson;
import org.tensorflow.op.random.RandomShuffle;
import org.tensorflow.op.random.RandomStandardNormal;
import org.tensorflow.op.random.RandomUniform;
import org.tensorflow.op.random.RandomUniformInt;
import org.tensorflow.op.random.RecordInput;
import org.tensorflow.op.random.StatefulRandomBinomial;
import org.tensorflow.op.random.StatefulStandardNormal;
import org.tensorflow.op.random.StatelessMultinomial;
import org.tensorflow.op.random.StatelessRandomNormal;
import org.tensorflow.op.random.StatelessRandomUniform;
import org.tensorflow.op.random.StatelessTruncatedNormal;
import org.tensorflow.op.random.TruncatedNormal;
import org.tensorflow.op.random.UniformCandidateSampler;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code random} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class RandomOps {
  private final Scope scope;

  private final Ops ops;

  RandomOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Generates labels for candidate sampling with a learned unigram distribution.
   *  <p>
   *  See explanations of candidate sampling and the data formats at
   *  go/candidate-sampling.
   *  <p>
   *  For each batch, this op picks a single set of sampled candidate labels.
   *  <p>
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
   */
  public AllCandidateSampler allCandidateSampler(Operand<TInt64> trueClasses, Long numTrue,
      Long numSampled, Boolean unique, AllCandidateSampler.Options... options) {
    return AllCandidateSampler.create(scope, trueClasses, numTrue, numSampled, unique, options);
  }

  /**
   * Generates labels for candidate sampling with a log-uniform distribution.
   *  <p>
   *  See explanations of candidate sampling and the data formats at
   *  go/candidate-sampling.
   *  <p>
   *  For each batch, this op picks a single set of sampled candidate labels.
   *  <p>
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
   * @param rangeMax The sampler will sample integers from the interval [0, range_max).
   * @param options carries optional attributes values
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
   * @param <U> data type for {@code output()} output
   * @param logits 2-D Tensor with shape `[batch_size, num_classes]`.  Each slice `[i, :]`
   *  represents the unnormalized log probabilities for all classes.
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param options carries optional attributes values
   * @return a new instance of Multinomial
   */
  public <T extends TNumber> Multinomial<TInt64> multinomial(Operand<T> logits,
      Operand<TInt32> numSamples, Multinomial.Options... options) {
    return Multinomial.create(scope, logits, numSamples, options);
  }

  /**
   * Draws samples from a multinomial distribution.
   *
   * @param <U> data type for {@code output()} output
   * @param logits 2-D Tensor with shape `[batch_size, num_classes]`.  Each slice `[i, :]`
   *  represents the unnormalized log probabilities for all classes.
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param outputDtype
   * @param options carries optional attributes values
   * @return a new instance of Multinomial
   */
  public <U extends TNumber, T extends TNumber> Multinomial<U> multinomial(Operand<T> logits,
      Operand<TInt32> numSamples, Class<U> outputDtype, Multinomial.Options... options) {
    return Multinomial.create(scope, logits, numSamples, outputDtype, options);
  }

  /**
   * Outputs random values from a normal distribution. The parameters may each be a
   *  <p>
   *  scalar which applies to the entire output, or a vector of length shape[0] which
   *  stores the parameters for each batch.
   *
   * @param <U> data type for {@code output()} output
   * @param shape The shape of the output tensor. Batches are indexed by the 0th dimension.
   * @param means The mean parameter of each batch.
   * @param stdevs The standard deviation parameter of each batch. Must be greater than 0.
   * @param minvals The minimum cutoff. May be -infinity.
   * @param maxvals The maximum cutoff. May be +infinity, and must be more than the minval
   *  for each batch.
   * @param options carries optional attributes values
   * @return a new instance of ParameterizedTruncatedNormal
   */
  public <U extends TNumber, T extends TNumber> ParameterizedTruncatedNormal<U> parameterizedTruncatedNormal(
      Operand<T> shape, Operand<U> means, Operand<U> stdevs, Operand<U> minvals, Operand<U> maxvals,
      ParameterizedTruncatedNormal.Options... options) {
    return ParameterizedTruncatedNormal.create(scope, shape, means, stdevs, minvals, maxvals, options);
  }

  /**
   * Outputs random values from the Gamma distribution(s) described by alpha.
   *  <p>
   *  This op uses the algorithm by Marsaglia et al. to acquire samples via
   *  transformation-rejection from pairs of uniform and normal random variables.
   *  See http://dl.acm.org/citation.cfm?id=358414
   *
   * @param <U> data type for {@code output()} output
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   *  distribution described by the shape parameters given in alpha.
   * @param alpha A tensor in which each scalar is a "shape" parameter describing the
   *  associated gamma distribution.
   * @param options carries optional attributes values
   * @return a new instance of RandomGamma
   */
  public <U extends TNumber, T extends TNumber> RandomGamma<U> randomGamma(Operand<T> shape,
      Operand<U> alpha, RandomGamma.Options... options) {
    return RandomGamma.create(scope, shape, alpha, options);
  }

  /**
   * Outputs random values from the Poisson distribution(s) described by rate.
   *  <p>
   *  This op uses two algorithms, depending on rate. If rate >= 10, then
   *  the algorithm by Hormann is used to acquire samples via
   *  transformation-rejection.
   *  See http://www.sciencedirect.com/science/article/pii/0167668793909974.
   *  <p>
   *  Otherwise, Knuth's algorithm is used to acquire samples via multiplying uniform
   *  random variables.
   *  See Donald E. Knuth (1969). Seminumerical Algorithms. The Art of Computer
   *  Programming, Volume 2. Addison Wesley
   *
   * @param <V> data type for {@code output()} output
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   *  distribution described by the shape parameters given in rate.
   * @param rate A tensor in which each scalar is a "rate" parameter describing the
   *  associated poisson distribution.
   * @param options carries optional attributes values
   * @return a new instance of RandomPoisson
   */
  public <T extends TNumber, U extends TNumber> RandomPoisson<TInt64> randomPoisson(
      Operand<T> shape, Operand<U> rate, RandomPoisson.Options... options) {
    return RandomPoisson.create(scope, shape, rate, options);
  }

  /**
   * Outputs random values from the Poisson distribution(s) described by rate.
   *  <p>
   *  This op uses two algorithms, depending on rate. If rate >= 10, then
   *  the algorithm by Hormann is used to acquire samples via
   *  transformation-rejection.
   *  See http://www.sciencedirect.com/science/article/pii/0167668793909974.
   *  <p>
   *  Otherwise, Knuth's algorithm is used to acquire samples via multiplying uniform
   *  random variables.
   *  See Donald E. Knuth (1969). Seminumerical Algorithms. The Art of Computer
   *  Programming, Volume 2. Addison Wesley
   *
   * @param <V> data type for {@code output()} output
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   *  distribution described by the shape parameters given in rate.
   * @param rate A tensor in which each scalar is a "rate" parameter describing the
   *  associated poisson distribution.
   * @param dtype
   * @param options carries optional attributes values
   * @return a new instance of RandomPoisson
   */
  public <V extends TNumber, T extends TNumber, U extends TNumber> RandomPoisson<V> randomPoisson(
      Operand<T> shape, Operand<U> rate, Class<V> dtype, RandomPoisson.Options... options) {
    return RandomPoisson.create(scope, shape, rate, dtype, options);
  }

  /**
   * Randomly shuffles a tensor along its first dimension.
   *  <p>
   *    The tensor is shuffled along dimension 0, such that each `value[j]` is mapped
   *    to one and only one `output[i]`. For example, a mapping that might occur for a
   *    3x2 tensor is:
   *  <pre>{@code
   *  [[1, 2],       [[5, 6],
   *   [3, 4],  ==>   [1, 2],
   *   [5, 6]]        [3, 4]]
   *  }</pre>
   *
   * @param <T> data type for {@code output()} output
   * @param value The tensor to be shuffled.
   * @param options carries optional attributes values
   * @return a new instance of RandomShuffle
   */
  public <T extends TType> RandomShuffle<T> randomShuffle(Operand<T> value,
      RandomShuffle.Options... options) {
    return RandomShuffle.create(scope, value, options);
  }

  /**
   * Outputs random values from a normal distribution.
   *  <p>
   *  The generated values will have mean 0 and standard deviation 1.
   *
   * @param <U> data type for {@code output()} output
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param options carries optional attributes values
   * @return a new instance of RandomStandardNormal
   */
  public <U extends TNumber, T extends TNumber> RandomStandardNormal<U> randomStandardNormal(
      Operand<T> shape, Class<U> dtype, RandomStandardNormal.Options... options) {
    return RandomStandardNormal.create(scope, shape, dtype, options);
  }

  /**
   * Outputs random values from a uniform distribution.
   *  <p>
   *  The generated values follow a uniform distribution in the range `[0, 1)`. The
   *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
   *
   * @param <U> data type for {@code output()} output
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param options carries optional attributes values
   * @return a new instance of RandomUniform
   */
  public <U extends TNumber, T extends TNumber> RandomUniform<U> randomUniform(Operand<T> shape,
      Class<U> dtype, RandomUniform.Options... options) {
    return RandomUniform.create(scope, shape, dtype, options);
  }

  /**
   * Outputs random integers from a uniform distribution.
   *  <p>
   *  The generated values are uniform integers in the range `[minval, maxval)`.
   *  The lower bound `minval` is included in the range, while the upper bound
   *  `maxval` is excluded.
   *  <p>
   *  The random integers are slightly biased unless `maxval - minval` is an exact
   *  power of two.  The bias is small for values of `maxval - minval` significantly
   *  smaller than the range of the output (either `2^32` or `2^64`).
   *
   * @param <U> data type for {@code output()} output
   * @param shape The shape of the output tensor.
   * @param minval 0-D.  Inclusive lower bound on the generated integers.
   * @param maxval 0-D.  Exclusive upper bound on the generated integers.
   * @param options carries optional attributes values
   * @return a new instance of RandomUniformInt
   */
  public <U extends TNumber, T extends TNumber> RandomUniformInt<U> randomUniformInt(
      Operand<T> shape, Operand<U> minval, Operand<U> maxval, RandomUniformInt.Options... options) {
    return RandomUniformInt.create(scope, shape, minval, maxval, options);
  }

  /**
   * Emits randomized records.
   *
   * @param filePattern Glob pattern for the data files.
   * @param options carries optional attributes values
   * @return a new instance of RecordInput
   */
  public RecordInput recordInput(String filePattern, RecordInput.Options... options) {
    return RecordInput.create(scope, filePattern, options);
  }

  /**
   *
   * @param <V> data type for {@code output()} output
   * @param resource
   * @param algorithm
   * @param shape
   * @param counts
   * @param probs
   * @return a new instance of StatefulRandomBinomial
   */
  public <T extends TNumber, U extends TNumber> StatefulRandomBinomial<TInt64> statefulRandomBinomial(
      Operand<?> resource, Operand<TInt64> algorithm, Operand<T> shape, Operand<U> counts,
      Operand<U> probs) {
    return StatefulRandomBinomial.create(scope, resource, algorithm, shape, counts, probs);
  }

  /**
   *
   * @param <V> data type for {@code output()} output
   * @param resource
   * @param algorithm
   * @param shape
   * @param counts
   * @param probs
   * @param dtype
   * @return a new instance of StatefulRandomBinomial
   */
  public <V extends TNumber, T extends TNumber, U extends TNumber> StatefulRandomBinomial<V> statefulRandomBinomial(
      Operand<?> resource, Operand<TInt64> algorithm, Operand<T> shape, Operand<U> counts,
      Operand<U> probs, Class<V> dtype) {
    return StatefulRandomBinomial.create(scope, resource, algorithm, shape, counts, probs, dtype);
  }

  /**
   * Outputs random values from a normal distribution.
   *  <p>
   *  The generated values will have mean 0 and standard deviation 1.
   *
   * @param <U> data type for {@code output()} output
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @return a new instance of StatefulStandardNormal
   */
  public <T extends TType> StatefulStandardNormal<TFloat32> statefulStandardNormal(
      Operand<?> resource, Operand<TInt64> algorithm, Operand<T> shape) {
    return StatefulStandardNormal.create(scope, resource, algorithm, shape);
  }

  /**
   * Outputs random values from a normal distribution.
   *  <p>
   *  The generated values will have mean 0 and standard deviation 1.
   *
   * @param <U> data type for {@code output()} output
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @return a new instance of StatefulStandardNormal
   */
  public <U extends TType, T extends TType> StatefulStandardNormal<U> statefulStandardNormal(
      Operand<?> resource, Operand<TInt64> algorithm, Operand<T> shape, Class<U> dtype) {
    return StatefulStandardNormal.create(scope, resource, algorithm, shape, dtype);
  }

  /**
   * Draws samples from a multinomial distribution.
   *
   * @param <V> data type for {@code output()} output
   * @param logits 2-D Tensor with shape `[batch_size, num_classes]`.  Each slice `[i, :]`
   *  represents the unnormalized log probabilities for all classes.
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessMultinomial
   */
  public <T extends TNumber, U extends TNumber> StatelessMultinomial<TInt64> statelessMultinomial(
      Operand<T> logits, Operand<TInt32> numSamples, Operand<U> seed) {
    return StatelessMultinomial.create(scope, logits, numSamples, seed);
  }

  /**
   * Draws samples from a multinomial distribution.
   *
   * @param <V> data type for {@code output()} output
   * @param logits 2-D Tensor with shape `[batch_size, num_classes]`.  Each slice `[i, :]`
   *  represents the unnormalized log probabilities for all classes.
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param seed 2 seeds (shape [2]).
   * @param outputDtype
   * @return a new instance of StatelessMultinomial
   */
  public <V extends TNumber, T extends TNumber, U extends TNumber> StatelessMultinomial<V> statelessMultinomial(
      Operand<T> logits, Operand<TInt32> numSamples, Operand<U> seed, Class<V> outputDtype) {
    return StatelessMultinomial.create(scope, logits, numSamples, seed, outputDtype);
  }

  /**
   * Outputs deterministic pseudorandom values from a normal distribution.
   *  <p>
   *  The generated values will have mean 0 and standard deviation 1.
   *  <p>
   *  The outputs are a deterministic function of `shape` and `seed`.
   *
   * @param <V> data type for {@code output()} output
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessRandomNormal
   */
  public <T extends TNumber, U extends TNumber> StatelessRandomNormal<TFloat32> statelessRandomNormal(
      Operand<T> shape, Operand<U> seed) {
    return StatelessRandomNormal.create(scope, shape, seed);
  }

  /**
   * Outputs deterministic pseudorandom values from a normal distribution.
   *  <p>
   *  The generated values will have mean 0 and standard deviation 1.
   *  <p>
   *  The outputs are a deterministic function of `shape` and `seed`.
   *
   * @param <V> data type for {@code output()} output
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param dtype The type of the output.
   * @return a new instance of StatelessRandomNormal
   */
  public <V extends TNumber, T extends TNumber, U extends TNumber> StatelessRandomNormal<V> statelessRandomNormal(
      Operand<T> shape, Operand<U> seed, Class<V> dtype) {
    return StatelessRandomNormal.create(scope, shape, seed, dtype);
  }

  /**
   * Outputs deterministic pseudorandom random values from a uniform distribution.
   *  <p>
   *  The generated values follow a uniform distribution in the range `[0, 1)`. The
   *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
   *  <p>
   *  The outputs are a deterministic function of `shape` and `seed`.
   *
   * @param <V> data type for {@code output()} output
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessRandomUniform
   */
  public <T extends TNumber, U extends TNumber> StatelessRandomUniform<TFloat32> statelessRandomUniform(
      Operand<T> shape, Operand<U> seed) {
    return StatelessRandomUniform.create(scope, shape, seed);
  }

  /**
   * Outputs deterministic pseudorandom random values from a uniform distribution.
   *  <p>
   *  The generated values follow a uniform distribution in the range `[0, 1)`. The
   *  lower bound 0 is included in the range, while the upper bound 1 is excluded.
   *  <p>
   *  The outputs are a deterministic function of `shape` and `seed`.
   *
   * @param <V> data type for {@code output()} output
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param dtype The type of the output.
   * @return a new instance of StatelessRandomUniform
   */
  public <V extends TNumber, T extends TNumber, U extends TNumber> StatelessRandomUniform<V> statelessRandomUniform(
      Operand<T> shape, Operand<U> seed, Class<V> dtype) {
    return StatelessRandomUniform.create(scope, shape, seed, dtype);
  }

  /**
   * Outputs deterministic pseudorandom values from a truncated normal distribution.
   *  <p>
   *  The generated values follow a normal distribution with mean 0 and standard
   *  deviation 1, except that values whose magnitude is more than 2 standard
   *  deviations from the mean are dropped and re-picked.
   *  <p>
   *  The outputs are a deterministic function of `shape` and `seed`.
   *
   * @param <V> data type for {@code output()} output
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessTruncatedNormal
   */
  public <T extends TNumber, U extends TNumber> StatelessTruncatedNormal<TFloat32> statelessTruncatedNormal(
      Operand<T> shape, Operand<U> seed) {
    return StatelessTruncatedNormal.create(scope, shape, seed);
  }

  /**
   * Outputs deterministic pseudorandom values from a truncated normal distribution.
   *  <p>
   *  The generated values follow a normal distribution with mean 0 and standard
   *  deviation 1, except that values whose magnitude is more than 2 standard
   *  deviations from the mean are dropped and re-picked.
   *  <p>
   *  The outputs are a deterministic function of `shape` and `seed`.
   *
   * @param <V> data type for {@code output()} output
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param dtype The type of the output.
   * @return a new instance of StatelessTruncatedNormal
   */
  public <V extends TNumber, T extends TNumber, U extends TNumber> StatelessTruncatedNormal<V> statelessTruncatedNormal(
      Operand<T> shape, Operand<U> seed, Class<V> dtype) {
    return StatelessTruncatedNormal.create(scope, shape, seed, dtype);
  }

  /**
   * Outputs random values from a truncated normal distribution.
   *  <p>
   *  The generated values follow a normal distribution with mean 0 and standard
   *  deviation 1, except that values whose magnitude is more than 2 standard
   *  deviations from the mean are dropped and re-picked.
   *
   * @param <U> data type for {@code output()} output
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param options carries optional attributes values
   * @return a new instance of TruncatedNormal
   */
  public <U extends TNumber, T extends TNumber> TruncatedNormal<U> truncatedNormal(Operand<T> shape,
      Class<U> dtype, TruncatedNormal.Options... options) {
    return TruncatedNormal.create(scope, shape, dtype, options);
  }

  /**
   * Generates labels for candidate sampling with a uniform distribution.
   *  <p>
   *  See explanations of candidate sampling and the data formats at
   *  go/candidate-sampling.
   *  <p>
   *  For each batch, this op picks a single set of sampled candidate labels.
   *  <p>
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
   * @param rangeMax The sampler will sample integers from the interval [0, range_max).
   * @param options carries optional attributes values
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
