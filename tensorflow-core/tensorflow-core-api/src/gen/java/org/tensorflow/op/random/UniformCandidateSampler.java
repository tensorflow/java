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

package org.tensorflow.op.random;

import java.util.Arrays;
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
 * Generates labels for candidate sampling with a uniform distribution.
 * See explanations of candidate sampling and the data formats at
 * go/candidate-sampling.
 * <p>For each batch, this op picks a single set of sampled candidate labels.
 * <p>The advantages of sampling candidates per-batch are simplicity and the
 * possibility of efficient dense matrix multiplication. The disadvantage is that
 * the sampled candidates must be chosen independently of the context and of the
 * true labels.
 */
@OpMetadata(
    opType = UniformCandidateSampler.OP_NAME,
    inputsClass = UniformCandidateSampler.Inputs.class
)
@Operator(
    group = "random"
)
public final class UniformCandidateSampler extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UniformCandidateSampler";

  private Output<TInt64> sampledCandidates;

  private Output<TFloat32> trueExpectedCount;

  private Output<TFloat32> sampledExpectedCount;

  public UniformCandidateSampler(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sampledCandidates = operation.output(outputIdx++);
    trueExpectedCount = operation.output(outputIdx++);
    sampledExpectedCount = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UniformCandidateSampler operation.
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
   * @return a new instance of UniformCandidateSampler
   */
  @Endpoint(
      describeByClass = true
  )
  public static UniformCandidateSampler create(Scope scope, Operand<TInt64> trueClasses,
      Long numTrue, Long numSampled, Boolean unique, Long rangeMax, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UniformCandidateSampler");
    opBuilder.addInput(trueClasses.asOutput());
    opBuilder.setAttr("num_true", numTrue);
    opBuilder.setAttr("num_sampled", numSampled);
    opBuilder.setAttr("unique", unique);
    opBuilder.setAttr("range_max", rangeMax);
    if (options != null) {
      for (Options opts : options) {
        if (opts.seed != null) {
          opBuilder.setAttr("seed", opts.seed);
        }
        if (opts.seed2 != null) {
          opBuilder.setAttr("seed2", opts.seed2);
        }
      }
    }
    return new UniformCandidateSampler(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.random.UniformCandidateSampler}
   */
  public static class Options {
    private Long seed;

    private Long seed2;

    private Options() {
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
      outputsClass = UniformCandidateSampler.class
  )
  public static class Inputs extends RawOpInputs<UniformCandidateSampler> {
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
      super(new UniformCandidateSampler(op), op, Arrays.asList("num_true", "num_sampled", "unique", "range_max", "seed", "seed2"));
      int inputIndex = 0;
      trueClasses = (Operand<TInt64>) op.input(inputIndex++);
      numTrue = op.attributes().getAttrInt("num_true");
      numSampled = op.attributes().getAttrInt("num_sampled");
      unique = op.attributes().getAttrBool("unique");
      rangeMax = op.attributes().getAttrInt("range_max");
      seed = op.attributes().getAttrInt("seed");
      seed2 = op.attributes().getAttrInt("seed2");
    }
  }
}
