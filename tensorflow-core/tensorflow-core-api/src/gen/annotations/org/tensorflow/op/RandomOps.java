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
import org.tensorflow.op.random.StatelessMultinomial;
import org.tensorflow.op.random.StatelessRandomNormal;
import org.tensorflow.op.random.StatelessRandomUniform;
import org.tensorflow.op.random.StatelessTruncatedNormal;
import org.tensorflow.op.random.TruncatedNormal;
import org.tensorflow.op.random.UniformCandidateSampler;

/**
 * An API for building {@code random} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class RandomOps {
  private final Scope scope;

  RandomOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link RandomShuffle} operation
   *
   * @param value The tensor to be shuffled.
   * @param options carries optional attributes values
   * @return a new instance of RandomShuffle
   * @see org.tensorflow.op.random.RandomShuffle
   */
  public <T> RandomShuffle<T> randomShuffle(Operand<T> value, RandomShuffle.Options... options) {
    return RandomShuffle.create(scope, value, options);
  }

  /**
   * Builds an {@link RandomUniform} operation
   *
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param options carries optional attributes values
   * @return a new instance of RandomUniform
   * @see org.tensorflow.op.random.RandomUniform
   */
  public <U extends Number, T extends Number> RandomUniform<U> randomUniform(Operand<T> shape,
      Class<U> dtype, RandomUniform.Options... options) {
    return RandomUniform.create(scope, shape, dtype, options);
  }

  /**
   * Builds an {@link StatelessRandomNormal} operation
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessRandomNormal
   * @see org.tensorflow.op.random.StatelessRandomNormal
   */
  public <T extends Number, U extends Number> StatelessRandomNormal<Float> statelessRandomNormal(
      Operand<T> shape, Operand<U> seed) {
    return StatelessRandomNormal.create(scope, shape, seed);
  }

  /**
   * Builds an {@link StatelessTruncatedNormal} operation
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param dtype The type of the output.
   * @return a new instance of StatelessTruncatedNormal
   * @see org.tensorflow.op.random.StatelessTruncatedNormal
   */
  public <V extends Number, T extends Number, U extends Number> StatelessTruncatedNormal<V> statelessTruncatedNormal(
      Operand<T> shape, Operand<U> seed, Class<V> dtype) {
    return StatelessTruncatedNormal.create(scope, shape, seed, dtype);
  }

  /**
   * Builds an {@link RandomPoisson} operation
   *
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   * @param rate A tensor in which each scalar is a "rate" parameter describing the
   * @param options carries optional attributes values
   * @return a new instance of RandomPoisson
   * @see org.tensorflow.op.random.RandomPoisson
   */
  public <T extends Number, U extends Number> RandomPoisson<Long> randomPoisson(Operand<T> shape,
      Operand<U> rate, RandomPoisson.Options... options) {
    return RandomPoisson.create(scope, shape, rate, options);
  }

  /**
   * Builds an {@link RandomUniformInt} operation
   *
   * @param shape The shape of the output tensor.
   * @param minval 0-D.  Inclusive lower bound on the generated integers.
   * @param maxval 0-D.  Exclusive upper bound on the generated integers.
   * @param options carries optional attributes values
   * @return a new instance of RandomUniformInt
   * @see org.tensorflow.op.random.RandomUniformInt
   */
  public <U extends Number, T extends Number> RandomUniformInt<U> randomUniformInt(Operand<T> shape,
      Operand<U> minval, Operand<U> maxval, RandomUniformInt.Options... options) {
    return RandomUniformInt.create(scope, shape, minval, maxval, options);
  }

  /**
   * Builds an {@link StatelessMultinomial} operation
   *
   * @param logits 2-D Tensor with shape `[batch_size, num_classes]`.  Each slice `[i, :]`
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param seed 2 seeds (shape [2]).
   * @param outputDtype 
   * @return a new instance of StatelessMultinomial
   * @see org.tensorflow.op.random.StatelessMultinomial
   */
  public <V extends Number, T extends Number, U extends Number> StatelessMultinomial<V> statelessMultinomial(
      Operand<T> logits, Operand<Integer> numSamples, Operand<U> seed, Class<V> outputDtype) {
    return StatelessMultinomial.create(scope, logits, numSamples, seed, outputDtype);
  }

  /**
   * Builds an {@link RandomStandardNormal} operation
   *
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param options carries optional attributes values
   * @return a new instance of RandomStandardNormal
   * @see org.tensorflow.op.random.RandomStandardNormal
   */
  public <U extends Number, T extends Number> RandomStandardNormal<U> randomStandardNormal(
      Operand<T> shape, Class<U> dtype, RandomStandardNormal.Options... options) {
    return RandomStandardNormal.create(scope, shape, dtype, options);
  }

  /**
   * Builds an {@link LogUniformCandidateSampler} operation
   *
   * @param trueClasses A batch_size * num_true matrix, in which each row contains the
   * @param numTrue Number of true labels per context.
   * @param numSampled Number of candidates to randomly sample.
   * @param unique If unique is true, we sample with rejection, so that all sampled
   * @param rangeMax The sampler will sample integers from the interval [0, range_max).
   * @param options carries optional attributes values
   * @return a new instance of LogUniformCandidateSampler
   * @see org.tensorflow.op.random.LogUniformCandidateSampler
   */
  public LogUniformCandidateSampler logUniformCandidateSampler(Operand<Long> trueClasses,
      Long numTrue, Long numSampled, Boolean unique, Long rangeMax,
      LogUniformCandidateSampler.Options... options) {
    return LogUniformCandidateSampler.create(scope, trueClasses, numTrue, numSampled, unique, rangeMax, options);
  }

  /**
   * Builds an {@link RandomGamma} operation
   *
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   * @param alpha A tensor in which each scalar is a "shape" parameter describing the
   * @param options carries optional attributes values
   * @return a new instance of RandomGamma
   * @see org.tensorflow.op.random.RandomGamma
   */
  public <U extends Number, T extends Number> RandomGamma<U> randomGamma(Operand<T> shape,
      Operand<U> alpha, RandomGamma.Options... options) {
    return RandomGamma.create(scope, shape, alpha, options);
  }

  /**
   * Builds an {@link Multinomial} operation
   *
   * @param logits 2-D Tensor with shape `[batch_size, num_classes]`.  Each slice `[i, :]`
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param options carries optional attributes values
   * @return a new instance of Multinomial
   * @see org.tensorflow.op.random.Multinomial
   */
  public <T extends Number> Multinomial<Long> multinomial(Operand<T> logits,
      Operand<Integer> numSamples, Multinomial.Options... options) {
    return Multinomial.create(scope, logits, numSamples, options);
  }

  /**
   * Builds an {@link TruncatedNormal} operation
   *
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @param options carries optional attributes values
   * @return a new instance of TruncatedNormal
   * @see org.tensorflow.op.random.TruncatedNormal
   */
  public <U extends Number, T extends Number> TruncatedNormal<U> truncatedNormal(Operand<T> shape,
      Class<U> dtype, TruncatedNormal.Options... options) {
    return TruncatedNormal.create(scope, shape, dtype, options);
  }

  /**
   * Builds an {@link StatelessTruncatedNormal} operation
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessTruncatedNormal
   * @see org.tensorflow.op.random.StatelessTruncatedNormal
   */
  public <T extends Number, U extends Number> StatelessTruncatedNormal<Float> statelessTruncatedNormal(
      Operand<T> shape, Operand<U> seed) {
    return StatelessTruncatedNormal.create(scope, shape, seed);
  }

  /**
   * Builds an {@link StatelessRandomNormal} operation
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param dtype The type of the output.
   * @return a new instance of StatelessRandomNormal
   * @see org.tensorflow.op.random.StatelessRandomNormal
   */
  public <V extends Number, T extends Number, U extends Number> StatelessRandomNormal<V> statelessRandomNormal(
      Operand<T> shape, Operand<U> seed, Class<V> dtype) {
    return StatelessRandomNormal.create(scope, shape, seed, dtype);
  }

  /**
   * Builds an {@link AllCandidateSampler} operation
   *
   * @param trueClasses A batch_size * num_true matrix, in which each row contains the
   * @param numTrue Number of true labels per context.
   * @param numSampled Number of candidates to produce.
   * @param unique If unique is true, we sample with rejection, so that all sampled
   * @param options carries optional attributes values
   * @return a new instance of AllCandidateSampler
   * @see org.tensorflow.op.random.AllCandidateSampler
   */
  public AllCandidateSampler allCandidateSampler(Operand<Long> trueClasses, Long numTrue,
      Long numSampled, Boolean unique, AllCandidateSampler.Options... options) {
    return AllCandidateSampler.create(scope, trueClasses, numTrue, numSampled, unique, options);
  }

  /**
   * Builds an {@link StatelessMultinomial} operation
   *
   * @param logits 2-D Tensor with shape `[batch_size, num_classes]`.  Each slice `[i, :]`
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessMultinomial
   * @see org.tensorflow.op.random.StatelessMultinomial
   */
  public <T extends Number, U extends Number> StatelessMultinomial<Long> statelessMultinomial(
      Operand<T> logits, Operand<Integer> numSamples, Operand<U> seed) {
    return StatelessMultinomial.create(scope, logits, numSamples, seed);
  }

  /**
   * Builds an {@link Multinomial} operation
   *
   * @param logits 2-D Tensor with shape `[batch_size, num_classes]`.  Each slice `[i, :]`
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param outputDtype 
   * @param options carries optional attributes values
   * @return a new instance of Multinomial
   * @see org.tensorflow.op.random.Multinomial
   */
  public <U extends Number, T extends Number> Multinomial<U> multinomial(Operand<T> logits,
      Operand<Integer> numSamples, Class<U> outputDtype, Multinomial.Options... options) {
    return Multinomial.create(scope, logits, numSamples, outputDtype, options);
  }

  /**
   * Builds an {@link RandomPoisson} operation
   *
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   * @param rate A tensor in which each scalar is a "rate" parameter describing the
   * @param dtype 
   * @param options carries optional attributes values
   * @return a new instance of RandomPoisson
   * @see org.tensorflow.op.random.RandomPoisson
   */
  public <V extends Number, T extends Number, U extends Number> RandomPoisson<V> randomPoisson(
      Operand<T> shape, Operand<U> rate, Class<V> dtype, RandomPoisson.Options... options) {
    return RandomPoisson.create(scope, shape, rate, dtype, options);
  }

  /**
   * Builds an {@link ParameterizedTruncatedNormal} operation
   *
   * @param shape The shape of the output tensor. Batches are indexed by the 0th dimension.
   * @param means The mean parameter of each batch.
   * @param stdevs The standard deviation parameter of each batch. Must be greater than 0.
   * @param minvals The minimum cutoff. May be -infinity.
   * @param maxvals The maximum cutoff. May be +infinity, and must be more than the minval
   * @param options carries optional attributes values
   * @return a new instance of ParameterizedTruncatedNormal
   * @see org.tensorflow.op.random.ParameterizedTruncatedNormal
   */
  public <U extends Number, T extends Number> ParameterizedTruncatedNormal<U> parameterizedTruncatedNormal(
      Operand<T> shape, Operand<U> means, Operand<U> stdevs, Operand<U> minvals, Operand<U> maxvals,
      ParameterizedTruncatedNormal.Options... options) {
    return ParameterizedTruncatedNormal.create(scope, shape, means, stdevs, minvals, maxvals, options);
  }

  /**
   * Builds an {@link StatelessRandomUniform} operation
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param dtype The type of the output.
   * @return a new instance of StatelessRandomUniform
   * @see org.tensorflow.op.random.StatelessRandomUniform
   */
  public <V extends Number, T extends Number, U extends Number> StatelessRandomUniform<V> statelessRandomUniform(
      Operand<T> shape, Operand<U> seed, Class<V> dtype) {
    return StatelessRandomUniform.create(scope, shape, seed, dtype);
  }

  /**
   * Builds an {@link StatelessRandomUniform} operation
   *
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessRandomUniform
   * @see org.tensorflow.op.random.StatelessRandomUniform
   */
  public <T extends Number, U extends Number> StatelessRandomUniform<Float> statelessRandomUniform(
      Operand<T> shape, Operand<U> seed) {
    return StatelessRandomUniform.create(scope, shape, seed);
  }

  /**
   * Builds an {@link UniformCandidateSampler} operation
   *
   * @param trueClasses A batch_size * num_true matrix, in which each row contains the
   * @param numTrue Number of true labels per context.
   * @param numSampled Number of candidates to randomly sample.
   * @param unique If unique is true, we sample with rejection, so that all sampled
   * @param rangeMax The sampler will sample integers from the interval [0, range_max).
   * @param options carries optional attributes values
   * @return a new instance of UniformCandidateSampler
   * @see org.tensorflow.op.random.UniformCandidateSampler
   */
  public UniformCandidateSampler uniformCandidateSampler(Operand<Long> trueClasses, Long numTrue,
      Long numSampled, Boolean unique, Long rangeMax, UniformCandidateSampler.Options... options) {
    return UniformCandidateSampler.create(scope, trueClasses, numTrue, numSampled, unique, rangeMax, options);
  }

  /**
   * Builds an {@link RecordInput} operation
   *
   * @param filePattern Glob pattern for the data files.
   * @param options carries optional attributes values
   * @return a new instance of RecordInput
   * @see org.tensorflow.op.random.RecordInput
   */
  public RecordInput recordInput(String filePattern, RecordInput.Options... options) {
    return RecordInput.create(scope, filePattern, options);
  }
}
