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
 * Performs fractional max pooling on the input.
 * <p>
 * Fractional max pooling is slightly different than regular max pooling.  In
 * regular max pooling, you downsize an input set by taking the maximum value of
 * smaller N x N subsections of the set (often 2x2), and try to reduce the set by
 * a factor of N, where N is an integer.  Fractional max pooling, as you might
 * expect from the word "fractional", means that the overall reduction ratio N
 * does not have to be an integer.
 * <p>
 * The sizes of the pooling regions are generated randomly but are fairly uniform.
 * For example, let's look at the height dimension, and the constraints on the
 * list of rows that will be pool boundaries.
 * <p>
 * First we define the following:
 * <p>
 * 1.  input_row_length : the number of rows from the input set
 * 2.  output_row_length : which will be smaller than the input
 * 3.  alpha = input_row_length / output_row_length : our reduction ratio
 * 4.  K = floor(alpha)
 * 5.  row_pooling_sequence : this is the result list of pool boundary rows
 * <p>
 * Then, row_pooling_sequence should satisfy:
 * <p>
 * 1.  a[0] = 0 : the first value of the sequence is 0
 * 2.  a[end] = input_row_length : the last value of the sequence is the size
 * 3.  K <= (a[i+1] - a[i]) <= K+1 : all intervals are K or K+1 size
 * 4.  length(row_pooling_sequence) = output_row_length+1
 * <p>
 * For more details on fractional max pooling, see this paper:
 * [Benjamin Graham, Fractional Max-Pooling](http://arxiv.org/abs/1412.6071)
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "nn")
public final class FractionalMaxPool<T extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.FractionalMaxPool}
   */
  public static class Options {
    
    /**
     * @param pseudoRandom When set to True, generates the pooling sequence in a
     * pseudorandom fashion, otherwise, in a random fashion. Check paper [Benjamin
     * Graham, Fractional Max-Pooling](http://arxiv.org/abs/1412.6071) for
     * difference between pseudorandom and random.
     */
    public Options pseudoRandom(Boolean pseudoRandom) {
      this.pseudoRandom = pseudoRandom;
      return this;
    }
    
    /**
     * @param overlapping When set to True, it means when pooling, the values at the boundary
     * of adjacent pooling cells are used by both cells. For example:
     * <p>
     * `index  0  1  2  3  4`
     * <p>
     * `value  20 5  16 3  7`
     * <p>
     * If the pooling sequence is [0, 2, 4], then 16, at index 2 will be used twice.
     * The result would be [20, 16] for fractional max pooling.
     */
    public Options overlapping(Boolean overlapping) {
      this.overlapping = overlapping;
      return this;
    }
    
    /**
     * @param deterministic When set to True, a fixed pooling region will be used when
     * iterating over a FractionalMaxPool node in the computation graph. Mainly used
     * in unit test to make FractionalMaxPool deterministic.
     */
    public Options deterministic(Boolean deterministic) {
      this.deterministic = deterministic;
      return this;
    }
    
    /**
     * @param seed If either seed or seed2 are set to be non-zero, the random number
     * generator is seeded by the given seed.  Otherwise, it is seeded by a
     * random seed.
     */
    public Options seed(Long seed) {
      this.seed = seed;
      return this;
    }
    
    /**
     * @param seed2 An second seed to avoid seed collision.
     */
    public Options seed2(Long seed2) {
      this.seed2 = seed2;
      return this;
    }
    
    private Boolean pseudoRandom;
    private Boolean overlapping;
    private Boolean deterministic;
    private Long seed;
    private Long seed2;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new FractionalMaxPool operation.
   * 
   * @param scope current scope
   * @param value 4-D with shape `[batch, height, width, channels]`.
   * @param poolingRatio Pooling ratio for each dimension of `value`, currently only
   * supports row and col dimension and should be >= 1.0. For example, a valid
   * pooling ratio looks like [1.0, 1.44, 1.73, 1.0]. The first and last elements
   * must be 1.0 because we don't allow pooling on batch and channels
   * dimensions. 1.44 and 1.73 are pooling ratio on height and width dimensions
   * respectively.
   * @param options carries optional attributes values
   * @return a new instance of FractionalMaxPool
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> FractionalMaxPool<T> create(Scope scope, Operand<T> value, List<Float> poolingRatio, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("FractionalMaxPool", scope.makeOpName("FractionalMaxPool"));
    opBuilder.addInput(value.asOutput());
    opBuilder = scope.apply(opBuilder);
    float[] poolingRatioArray = new float[poolingRatio.size()];
    for (int i = 0; i < poolingRatioArray.length; ++i) {
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
    return new FractionalMaxPool<T>(opBuilder.build());
  }
  
  /**
   * @param pseudoRandom When set to True, generates the pooling sequence in a
   * pseudorandom fashion, otherwise, in a random fashion. Check paper [Benjamin
   * Graham, Fractional Max-Pooling](http://arxiv.org/abs/1412.6071) for
   * difference between pseudorandom and random.
   */
  public static Options pseudoRandom(Boolean pseudoRandom) {
    return new Options().pseudoRandom(pseudoRandom);
  }
  
  /**
   * @param overlapping When set to True, it means when pooling, the values at the boundary
   * of adjacent pooling cells are used by both cells. For example:
   * <p>
   * `index  0  1  2  3  4`
   * <p>
   * `value  20 5  16 3  7`
   * <p>
   * If the pooling sequence is [0, 2, 4], then 16, at index 2 will be used twice.
   * The result would be [20, 16] for fractional max pooling.
   */
  public static Options overlapping(Boolean overlapping) {
    return new Options().overlapping(overlapping);
  }
  
  /**
   * @param deterministic When set to True, a fixed pooling region will be used when
   * iterating over a FractionalMaxPool node in the computation graph. Mainly used
   * in unit test to make FractionalMaxPool deterministic.
   */
  public static Options deterministic(Boolean deterministic) {
    return new Options().deterministic(deterministic);
  }
  
  /**
   * @param seed If either seed or seed2 are set to be non-zero, the random number
   * generator is seeded by the given seed.  Otherwise, it is seeded by a
   * random seed.
   */
  public static Options seed(Long seed) {
    return new Options().seed(seed);
  }
  
  /**
   * @param seed2 An second seed to avoid seed collision.
   */
  public static Options seed2(Long seed2) {
    return new Options().seed2(seed2);
  }
  
  /**
   * output tensor after fractional max pooling.
   */
  public Output<T> output() {
    return output;
  }
  
  /**
   * row pooling sequence, needed to calculate gradient.
   */
  public Output<TInt64> rowPoolingSequence() {
    return rowPoolingSequence;
  }
  
  /**
   * column pooling sequence, needed to calculate gradient.
   */
  public Output<TInt64> colPoolingSequence() {
    return colPoolingSequence;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "FractionalMaxPool";
  
  private Output<T> output;
  private Output<TInt64> rowPoolingSequence;
  private Output<TInt64> colPoolingSequence;
  
  private FractionalMaxPool(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    rowPoolingSequence = operation.output(outputIdx++);
    colPoolingSequence = operation.output(outputIdx++);
  }
}
