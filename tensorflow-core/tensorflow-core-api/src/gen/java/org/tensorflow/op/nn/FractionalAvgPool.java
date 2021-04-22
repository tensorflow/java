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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Performs fractional average pooling on the input.
 * Fractional average pooling is similar to Fractional max pooling in the pooling
 * region generation step. The only difference is that after pooling regions are
 * generated, a mean operation is performed instead of a max operation in each
 * pooling region.
 *
 * @param <T> data type for {@code output} output
 */
@Operator(
    group = "nn"
)
public final class FractionalAvgPool<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FractionalAvgPool";

  private Output<T> output;

  private Output<TInt64> rowPoolingSequence;

  private Output<TInt64> colPoolingSequence;

  private FractionalAvgPool(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    rowPoolingSequence = operation.output(outputIdx++);
    colPoolingSequence = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FractionalAvgPool operation.
   *
   * @param scope current scope
   * @param value 4-D with shape {@code [batch, height, width, channels]}.
   * @param poolingRatio Pooling ratio for each dimension of {@code value}, currently only
   * supports row and col dimension and should be &gt;= 1.0. For example, a valid
   * pooling ratio looks like [1.0, 1.44, 1.73, 1.0]. The first and last elements
   * must be 1.0 because we don't allow pooling on batch and channels
   * dimensions. 1.44 and 1.73 are pooling ratio on height and width dimensions
   * respectively.
   * @param options carries optional attribute values
   * @param <T> data type for {@code FractionalAvgPool} output and operands
   * @return a new instance of FractionalAvgPool
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> FractionalAvgPool<T> create(Scope scope, Operand<T> value,
      List<Float> poolingRatio, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("FractionalAvgPool", scope.makeOpName("FractionalAvgPool"));
    opBuilder.addInput(value.asOutput());
    opBuilder = scope.apply(opBuilder);
    float[] poolingRatioArray = new float[poolingRatio.size()];
    for (int i = 0 ; i < poolingRatioArray.length ; i++) {
      poolingRatioArray[i] = poolingRatio.get(i);
    }
    opBuilder.setAttr("pooling_ratio", poolingRatioArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.pseudoRandom != null) {
          opBuilder.setAttr("pseudo_random", opts.pseudoRandom);
        }
        if (opts.overlapping != null) {
          opBuilder.setAttr("overlapping", opts.overlapping);
        }
        if (opts.deterministic != null) {
          opBuilder.setAttr("deterministic", opts.deterministic);
        }
        if (opts.seed != null) {
          opBuilder.setAttr("seed", opts.seed);
        }
        if (opts.seed2 != null) {
          opBuilder.setAttr("seed2", opts.seed2);
        }
      }
    }
    return new FractionalAvgPool<>(opBuilder.build());
  }

  /**
   * Sets the pseudoRandom option.
   *
   * @param pseudoRandom When set to True, generates the pooling sequence in a
   * pseudorandom fashion, otherwise, in a random fashion. Check paper  <a href="http://arxiv.org/abs/1412.6071">Benjamin
   * Graham, Fractional Max-Pooling</a>  for
   * difference between pseudorandom and random.
   * @return this Options instance.
   */
  public static Options pseudoRandom(Boolean pseudoRandom) {
    return new Options().pseudoRandom(pseudoRandom);
  }

  /**
   * Sets the overlapping option.
   *
   * @param overlapping When set to True, it means when pooling, the values at the boundary
   * of adjacent pooling cells are used by both cells. For example:
   * <p>{@code index  0  1  2  3  4}
   * <p>{@code value  20 5  16 3  7}
   * <p>If the pooling sequence is [0, 2, 4], then 16, at index 2 will be used twice.
   * The result would be [41/3, 26/3] for fractional avg pooling.
   * @return this Options instance.
   */
  public static Options overlapping(Boolean overlapping) {
    return new Options().overlapping(overlapping);
  }

  /**
   * Sets the deterministic option.
   *
   * @param deterministic When set to True, a fixed pooling region will be used when
   * iterating over a FractionalAvgPool node in the computation graph. Mainly used
   * in unit test to make FractionalAvgPool deterministic.
   * @return this Options instance.
   */
  public static Options deterministic(Boolean deterministic) {
    return new Options().deterministic(deterministic);
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
   * Gets output.
   * output tensor after fractional avg pooling.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  /**
   * Gets rowPoolingSequence.
   * row pooling sequence, needed to calculate gradient.
   * @return rowPoolingSequence.
   */
  public Output<TInt64> rowPoolingSequence() {
    return rowPoolingSequence;
  }

  /**
   * Gets colPoolingSequence.
   * column pooling sequence, needed to calculate gradient.
   * @return colPoolingSequence.
   */
  public Output<TInt64> colPoolingSequence() {
    return colPoolingSequence;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.FractionalAvgPool}
   */
  public static class Options {
    private Boolean pseudoRandom;

    private Boolean overlapping;

    private Boolean deterministic;

    private Long seed;

    private Long seed2;

    private Options() {
    }

    /**
     * Sets the pseudoRandom option.
     *
     * @param pseudoRandom When set to True, generates the pooling sequence in a
     * pseudorandom fashion, otherwise, in a random fashion. Check paper  <a href="http://arxiv.org/abs/1412.6071">Benjamin
     * Graham, Fractional Max-Pooling</a>  for
     * difference between pseudorandom and random.
     * @return this Options instance.
     */
    public Options pseudoRandom(Boolean pseudoRandom) {
      this.pseudoRandom = pseudoRandom;
      return this;
    }

    /**
     * Sets the overlapping option.
     *
     * @param overlapping When set to True, it means when pooling, the values at the boundary
     * of adjacent pooling cells are used by both cells. For example:
     * <p>{@code index  0  1  2  3  4}
     * <p>{@code value  20 5  16 3  7}
     * <p>If the pooling sequence is [0, 2, 4], then 16, at index 2 will be used twice.
     * The result would be [41/3, 26/3] for fractional avg pooling.
     * @return this Options instance.
     */
    public Options overlapping(Boolean overlapping) {
      this.overlapping = overlapping;
      return this;
    }

    /**
     * Sets the deterministic option.
     *
     * @param deterministic When set to True, a fixed pooling region will be used when
     * iterating over a FractionalAvgPool node in the computation graph. Mainly used
     * in unit test to make FractionalAvgPool deterministic.
     * @return this Options instance.
     */
    public Options deterministic(Boolean deterministic) {
      this.deterministic = deterministic;
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
}
