/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.nn;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;

/**
 * Generates labels for candidate sampling with a learned unigram distribution.
 * A unigram sampler could use a fixed unigram distribution read from a
 * file or passed in as an in-memory array instead of building up the distribution
 * from data on the fly. There is also an option to skew the distribution by
 * applying a distortion power to the weights.
 * <p>The vocabulary file should be in CSV-like format, with the last field
 * being the weight associated with the word.
 * <p>For each batch, this op picks a single set of sampled candidate labels.
 * <p>The advantages of sampling candidates per-batch are simplicity and the
 * possibility of efficient dense matrix multiplication. The disadvantage is that
 * the sampled candidates must be chosen independently of the context and of the
 * true labels.
 */
@OpMetadata(
    opType = FixedUnigramCandidateSampler.OP_NAME,
    inputsClass = FixedUnigramCandidateSampler.Inputs.class
)
@Operator(
    group = "nn"
)
public final class FixedUnigramCandidateSampler extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FixedUnigramCandidateSampler";

  private Output<TInt64> sampledCandidates;

  private Output<TFloat32> trueExpectedCount;

  private Output<TFloat32> sampledExpectedCount;

  public FixedUnigramCandidateSampler(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sampledCandidates = operation.output(outputIdx++);
    trueExpectedCount = operation.output(outputIdx++);
    sampledExpectedCount = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FixedUnigramCandidateSampler operation.
   *
   * @param scope current scope
   * @param trueClasses A batch_size * num_true matrix, in which each row contains the
   * IDs of the num_true target_classes in the corresponding original label.
   * @param numTrue Number of true labels per context.
   * @param numSampled Number of candidates to randomly sample.
   * @param unique If unique is true, we sample with rejection, so that all sampled
   * candidates in a batch are unique. This requires some approximation to
   * estimate the post-rejection sampling probabilities.
   * @param rangeMax The sampler will sample integers from the interval [0, range_max).
   * @param options carries optional attribute values
   * @return a new instance of FixedUnigramCandidateSampler
   */
  @Endpoint(
      describeByClass = true
  )
  public static FixedUnigramCandidateSampler create(Scope scope, Operand<TInt64> trueClasses,
      Long numTrue, Long numSampled, Boolean unique, Long rangeMax, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FixedUnigramCandidateSampler");
    opBuilder.addInput(trueClasses.asOutput());
    opBuilder.setAttr("num_true", numTrue);
    opBuilder.setAttr("num_sampled", numSampled);
    opBuilder.setAttr("unique", unique);
    opBuilder.setAttr("range_max", rangeMax);
    if (options != null) {
      for (Options opts : options) {
        if (opts.vocabFile != null) {
          opBuilder.setAttr("vocab_file", opts.vocabFile);
        }
        if (opts.distortion != null) {
          opBuilder.setAttr("distortion", opts.distortion);
        }
        if (opts.numReservedIds != null) {
          opBuilder.setAttr("num_reserved_ids", opts.numReservedIds);
        }
        if (opts.numShards != null) {
          opBuilder.setAttr("num_shards", opts.numShards);
        }
        if (opts.shard != null) {
          opBuilder.setAttr("shard", opts.shard);
        }
        if (opts.unigrams != null) {
          float[] unigramsArray = new float[opts.unigrams.size()];
          for (int i = 0 ; i < unigramsArray.length ; i++) {
            unigramsArray[i] = opts.unigrams.get(i);
          }
          opBuilder.setAttr("unigrams", unigramsArray);
        }
        if (opts.seed != null) {
          opBuilder.setAttr("seed", opts.seed);
        }
        if (opts.seed2 != null) {
          opBuilder.setAttr("seed2", opts.seed2);
        }
      }
    }
    return new FixedUnigramCandidateSampler(opBuilder.build());
  }

  /**
   * Sets the vocabFile option.
   *
   * @param vocabFile Each valid line in this file (which should have a CSV-like format)
   * corresponds to a valid word ID. IDs are in sequential order, starting from
   * num_reserved_ids. The last entry in each line is expected to be a value
   * corresponding to the count or relative probability. Exactly one of vocab_file
   * and unigrams needs to be passed to this op.
   * @return this Options instance.
   */
  public static Options vocabFile(String vocabFile) {
    return new Options().vocabFile(vocabFile);
  }

  /**
   * Sets the distortion option.
   *
   * @param distortion The distortion is used to skew the unigram probability distribution.
   * Each weight is first raised to the distortion's power before adding to the
   * internal unigram distribution. As a result, distortion = 1.0 gives regular
   * unigram sampling (as defined by the vocab file), and distortion = 0.0 gives
   * a uniform distribution.
   * @return this Options instance.
   */
  public static Options distortion(Float distortion) {
    return new Options().distortion(distortion);
  }

  /**
   * Sets the numReservedIds option.
   *
   * @param numReservedIds Optionally some reserved IDs can be added in the range [0,
   * ..., num_reserved_ids) by the users. One use case is that a special unknown
   * word token is used as ID 0. These IDs will have a sampling probability of 0.
   * @return this Options instance.
   */
  public static Options numReservedIds(Long numReservedIds) {
    return new Options().numReservedIds(numReservedIds);
  }

  /**
   * Sets the numShards option.
   *
   * @param numShards A sampler can be used to sample from a subset of the original range
   * in order to speed up the whole computation through parallelism. This parameter
   * (together with 'shard') indicates the number of partitions that are being
   * used in the overall computation.
   * @return this Options instance.
   */
  public static Options numShards(Long numShards) {
    return new Options().numShards(numShards);
  }

  /**
   * Sets the shard option.
   *
   * @param shard A sampler can be used to sample from a subset of the original range
   * in order to speed up the whole computation through parallelism. This parameter
   * (together with 'num_shards') indicates the particular partition number of a
   * sampler op, when partitioning is being used.
   * @return this Options instance.
   */
  public static Options shard(Long shard) {
    return new Options().shard(shard);
  }

  /**
   * Sets the unigrams option.
   *
   * @param unigrams A list of unigram counts or probabilities, one per ID in sequential
   * order. Exactly one of vocab_file and unigrams should be passed to this op.
   * @return this Options instance.
   */
  public static Options unigrams(List<Float> unigrams) {
    return new Options().unigrams(unigrams);
  }

  /**
   * Sets the unigrams option.
   *
   * @param unigrams A list of unigram counts or probabilities, one per ID in sequential
   * order. Exactly one of vocab_file and unigrams should be passed to this op.
   * @return this Options instance.
   */
  public static Options unigrams(Float... unigrams) {
    return new Options().unigrams(unigrams);
  }

  /**
   * Sets the seed option.
   *
   * @param seed If either seed or seed2 are set to be non-zero, the random number
   * generator is seeded by the given seed.  Otherwise, it is seeded by a
   * random seed.
   * @return this Options instance.
   */
  public static Options seed(Long seed) {
    return new Options().seed(seed);
  }

  /**
   * Sets the seed2 option.
   *
   * @param seed2 An second seed to avoid seed collision.
   * @return this Options instance.
   */
  public static Options seed2(Long seed2) {
    return new Options().seed2(seed2);
  }

  /**
   * Gets sampledCandidates.
   * A vector of length num_sampled, in which each element is
   * the ID of a sampled candidate.
   * @return sampledCandidates.
   */
  public Output<TInt64> sampledCandidates() {
    return sampledCandidates;
  }

  /**
   * Gets trueExpectedCount.
   * A batch_size * num_true matrix, representing
   * the number of times each candidate is expected to occur in a batch
   * of sampled candidates. If unique=true, then this is a probability.
   * @return trueExpectedCount.
   */
  public Output<TFloat32> trueExpectedCount() {
    return trueExpectedCount;
  }

  /**
   * Gets sampledExpectedCount.
   * A vector of length num_sampled, for each sampled
   * candidate representing the number of times the candidate is expected
   * to occur in a batch of sampled candidates.  If unique=true, then this is a
   * probability.
   * @return sampledExpectedCount.
   */
  public Output<TFloat32> sampledExpectedCount() {
    return sampledExpectedCount;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.FixedUnigramCandidateSampler}
   */
  public static class Options {
    private String vocabFile;

    private Float distortion;

    private Long numReservedIds;

    private Long numShards;

    private Long shard;

    private List<Float> unigrams;

    private Long seed;

    private Long seed2;

    private Options() {
    }

    /**
     * Sets the vocabFile option.
     *
     * @param vocabFile Each valid line in this file (which should have a CSV-like format)
     * corresponds to a valid word ID. IDs are in sequential order, starting from
     * num_reserved_ids. The last entry in each line is expected to be a value
     * corresponding to the count or relative probability. Exactly one of vocab_file
     * and unigrams needs to be passed to this op.
     * @return this Options instance.
     */
    public Options vocabFile(String vocabFile) {
      this.vocabFile = vocabFile;
      return this;
    }

    /**
     * Sets the distortion option.
     *
     * @param distortion The distortion is used to skew the unigram probability distribution.
     * Each weight is first raised to the distortion's power before adding to the
     * internal unigram distribution. As a result, distortion = 1.0 gives regular
     * unigram sampling (as defined by the vocab file), and distortion = 0.0 gives
     * a uniform distribution.
     * @return this Options instance.
     */
    public Options distortion(Float distortion) {
      this.distortion = distortion;
      return this;
    }

    /**
     * Sets the numReservedIds option.
     *
     * @param numReservedIds Optionally some reserved IDs can be added in the range [0,
     * ..., num_reserved_ids) by the users. One use case is that a special unknown
     * word token is used as ID 0. These IDs will have a sampling probability of 0.
     * @return this Options instance.
     */
    public Options numReservedIds(Long numReservedIds) {
      this.numReservedIds = numReservedIds;
      return this;
    }

    /**
     * Sets the numShards option.
     *
     * @param numShards A sampler can be used to sample from a subset of the original range
     * in order to speed up the whole computation through parallelism. This parameter
     * (together with 'shard') indicates the number of partitions that are being
     * used in the overall computation.
     * @return this Options instance.
     */
    public Options numShards(Long numShards) {
      this.numShards = numShards;
      return this;
    }

    /**
     * Sets the shard option.
     *
     * @param shard A sampler can be used to sample from a subset of the original range
     * in order to speed up the whole computation through parallelism. This parameter
     * (together with 'num_shards') indicates the particular partition number of a
     * sampler op, when partitioning is being used.
     * @return this Options instance.
     */
    public Options shard(Long shard) {
      this.shard = shard;
      return this;
    }

    /**
     * Sets the unigrams option.
     *
     * @param unigrams A list of unigram counts or probabilities, one per ID in sequential
     * order. Exactly one of vocab_file and unigrams should be passed to this op.
     * @return this Options instance.
     */
    public Options unigrams(List<Float> unigrams) {
      this.unigrams = unigrams;
      return this;
    }

    /**
     * Sets the unigrams option.
     *
     * @param unigrams A list of unigram counts or probabilities, one per ID in sequential
     * order. Exactly one of vocab_file and unigrams should be passed to this op.
     * @return this Options instance.
     */
    public Options unigrams(Float... unigrams) {
      this.unigrams = Arrays.asList(unigrams);
      return this;
    }

    /**
     * Sets the seed option.
     *
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     * generator is seeded by the given seed.  Otherwise, it is seeded by a
     * random seed.
     * @return this Options instance.
     */
    public Options seed(Long seed) {
      this.seed = seed;
      return this;
    }

    /**
     * Sets the seed2 option.
     *
     * @param seed2 An second seed to avoid seed collision.
     * @return this Options instance.
     */
    public Options seed2(Long seed2) {
      this.seed2 = seed2;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = FixedUnigramCandidateSampler.class
  )
  public static class Inputs extends RawOpInputs<FixedUnigramCandidateSampler> {
    /**
     * A batch_size * num_true matrix, in which each row contains the
     * IDs of the num_true target_classes in the corresponding original label.
     */
    public final Operand<TInt64> trueClasses;

    /**
     * Number of true labels per context.
     */
    public final long numTrue;

    /**
     * Number of candidates to randomly sample.
     */
    public final long numSampled;

    /**
     * If unique is true, we sample with rejection, so that all sampled
     * candidates in a batch are unique. This requires some approximation to
     * estimate the post-rejection sampling probabilities.
     */
    public final boolean unique;

    /**
     * The sampler will sample integers from the interval [0, range_max).
     */
    public final long rangeMax;

    /**
     * Each valid line in this file (which should have a CSV-like format)
     * corresponds to a valid word ID. IDs are in sequential order, starting from
     * num_reserved_ids. The last entry in each line is expected to be a value
     * corresponding to the count or relative probability. Exactly one of vocab_file
     * and unigrams needs to be passed to this op.
     */
    public final String vocabFile;

    /**
     * The distortion is used to skew the unigram probability distribution.
     * Each weight is first raised to the distortion's power before adding to the
     * internal unigram distribution. As a result, distortion = 1.0 gives regular
     * unigram sampling (as defined by the vocab file), and distortion = 0.0 gives
     * a uniform distribution.
     */
    public final float distortion;

    /**
     * Optionally some reserved IDs can be added in the range [0,
     * ..., num_reserved_ids) by the users. One use case is that a special unknown
     * word token is used as ID 0. These IDs will have a sampling probability of 0.
     */
    public final long numReservedIds;

    /**
     * A sampler can be used to sample from a subset of the original range
     * in order to speed up the whole computation through parallelism. This parameter
     * (together with 'shard') indicates the number of partitions that are being
     * used in the overall computation.
     */
    public final long numShards;

    /**
     * A sampler can be used to sample from a subset of the original range
     * in order to speed up the whole computation through parallelism. This parameter
     * (together with 'num_shards') indicates the particular partition number of a
     * sampler op, when partitioning is being used.
     */
    public final long shard;

    /**
     * A list of unigram counts or probabilities, one per ID in sequential
     * order. Exactly one of vocab_file and unigrams should be passed to this op.
     */
    public final float[] unigrams;

    /**
     * If either seed or seed2 are set to be non-zero, the random number
     * generator is seeded by the given seed.  Otherwise, it is seeded by a
     * random seed.
     */
    public final long seed;

    /**
     * An second seed to avoid seed collision.
     */
    public final long seed2;

    public Inputs(GraphOperation op) {
      super(new FixedUnigramCandidateSampler(op), op, Arrays.asList("num_true", "num_sampled", "unique", "range_max", "vocab_file", "distortion", "num_reserved_ids", "num_shards", "shard", "unigrams", "seed", "seed2"));
      int inputIndex = 0;
      trueClasses = (Operand<TInt64>) op.input(inputIndex++);
      numTrue = op.attributes().getAttrInt("num_true");
      numSampled = op.attributes().getAttrInt("num_sampled");
      unique = op.attributes().getAttrBool("unique");
      rangeMax = op.attributes().getAttrInt("range_max");
      vocabFile = op.attributes().getAttrString("vocab_file");
      distortion = op.attributes().getAttrFloat("distortion");
      numReservedIds = op.attributes().getAttrInt("num_reserved_ids");
      numShards = op.attributes().getAttrInt("num_shards");
      shard = op.attributes().getAttrInt("shard");
      unigrams = op.attributes().getAttrFloatList("unigrams");
      seed = op.attributes().getAttrInt("seed");
      seed2 = op.attributes().getAttrInt("seed2");
    }
  }
}
