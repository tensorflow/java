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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

/**
 * Computes the ids of the positions in sampled_candidates that match true_labels.
 * When doing log-odds NCE, the result of this op should be passed through a
 * SparseToDense op, then added to the logits of the sampled candidates. This has
 * the effect of 'removing' the sampled labels that match the true labels by
 * making the classifier sure that they are sampled labels.
 */
@OpMetadata(
    opType = ComputeAccidentalHits.OP_NAME,
    inputsClass = ComputeAccidentalHits.Inputs.class
)
@Operator(
    group = "nn"
)
public final class ComputeAccidentalHits extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ComputeAccidentalHits";

  private Output<TInt32> indices;

  private Output<TInt64> ids;

  private Output<TFloat32> weights;

  public ComputeAccidentalHits(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    indices = operation.output(outputIdx++);
    ids = operation.output(outputIdx++);
    weights = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ComputeAccidentalHits operation.
   *
   * @param scope current scope
   * @param trueClasses The true_classes output of UnpackSparseLabels.
   * @param sampledCandidates The sampled_candidates output of CandidateSampler.
   * @param numTrue Number of true labels per context.
   * @param options carries optional attribute values
   * @return a new instance of ComputeAccidentalHits
   */
  @Endpoint(
      describeByClass = true
  )
  public static ComputeAccidentalHits create(Scope scope, Operand<TInt64> trueClasses,
      Operand<TInt64> sampledCandidates, Long numTrue, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ComputeAccidentalHits");
    opBuilder.addInput(trueClasses.asOutput());
    opBuilder.addInput(sampledCandidates.asOutput());
    opBuilder.setAttr("num_true", numTrue);
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
    return new ComputeAccidentalHits(opBuilder.build());
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
   * Gets indices.
   * A vector of indices corresponding to rows of true_candidates.
   * @return indices.
   */
  public Output<TInt32> indices() {
    return indices;
  }

  /**
   * Gets ids.
   * A vector of IDs of positions in sampled_candidates that match a true_label
   * for the row with the corresponding index in indices.
   * @return ids.
   */
  public Output<TInt64> ids() {
    return ids;
  }

  /**
   * Gets weights.
   * A vector of the same length as indices and ids, in which each element
   * is -FLOAT_MAX.
   * @return weights.
   */
  public Output<TFloat32> weights() {
    return weights;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.ComputeAccidentalHits}
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
      outputsClass = ComputeAccidentalHits.class
  )
  public static class Inputs extends RawOpInputs<ComputeAccidentalHits> {
    /**
     * The true_classes output of UnpackSparseLabels.
     */
    public final Operand<TInt64> trueClasses;

    /**
     * The sampled_candidates output of CandidateSampler.
     */
    public final Operand<TInt64> sampledCandidates;

    /**
     * Number of true labels per context.
     */
    public final long numTrue;

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
      super(new ComputeAccidentalHits(op), op, Arrays.asList("num_true", "seed", "seed2"));
      int inputIndex = 0;
      trueClasses = (Operand<TInt64>) op.input(inputIndex++);
      sampledCandidates = (Operand<TInt64>) op.input(inputIndex++);
      numTrue = op.attributes().getAttrInt("num_true");
      seed = op.attributes().getAttrInt("seed");
      seed2 = op.attributes().getAttrInt("seed2");
    }
  }
}
