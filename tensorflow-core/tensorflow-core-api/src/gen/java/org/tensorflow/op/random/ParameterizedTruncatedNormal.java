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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;

/**
 * Outputs random values from a normal distribution. The parameters may each be a
 * <p>
 * scalar which applies to the entire output, or a vector of length shape[0] which
 * stores the parameters for each batch.
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator(group = "random")
public final class ParameterizedTruncatedNormal<U extends TNumber> extends RawOp implements Operand<U> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.random.ParameterizedTruncatedNormal}
   */
  public static class Options {
    
    /**
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     * generator is seeded by the given seed.  Otherwise, it is seeded by a
     * random seed.
     */
    public Options seed(Long seed) {
      this.seed = seed;
      return this;
    }
    
    /**
     * @param seed2 A second seed to avoid seed collision.
     */
    public Options seed2(Long seed2) {
      this.seed2 = seed2;
      return this;
    }
    
    private Long seed;
    private Long seed2;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ParameterizedTruncatedNormal operation.
   * 
   * @param scope current scope
   * @param shape The shape of the output tensor. Batches are indexed by the 0th dimension.
   * @param means The mean parameter of each batch.
   * @param stdevs The standard deviation parameter of each batch. Must be greater than 0.
   * @param minvals The minimum cutoff. May be -infinity.
   * @param maxvals The maximum cutoff. May be +infinity, and must be more than the minval
   * for each batch.
   * @param options carries optional attributes values
   * @return a new instance of ParameterizedTruncatedNormal
   */
  @Endpoint(describeByClass = true)
  public static <U extends TNumber, T extends TNumber> ParameterizedTruncatedNormal<U> create(Scope scope, Operand<T> shape, Operand<U> means, Operand<U> stdevs, Operand<U> minvals, Operand<U> maxvals, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ParameterizedTruncatedNormal", scope.makeOpName("ParameterizedTruncatedNormal"));
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(means.asOutput());
    opBuilder.addInput(stdevs.asOutput());
    opBuilder.addInput(minvals.asOutput());
    opBuilder.addInput(maxvals.asOutput());
    opBuilder = scope.apply(opBuilder);
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
    return new ParameterizedTruncatedNormal<U>(opBuilder.build());
  }
  
  /**
   * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
   * generator is seeded by the given seed.  Otherwise, it is seeded by a
   * random seed.
   */
  public static Options seed(Long seed) {
    return new Options().seed(seed);
  }
  
  /**
   * @param seed2 A second seed to avoid seed collision.
   */
  public static Options seed2(Long seed2) {
    return new Options().seed2(seed2);
  }
  
  /**
   * A matrix of shape num_batches x samples_per_batch, filled with random
   * truncated normal values using the parameters for each row.
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ParameterizedTruncatedNormal";
  
  private Output<U> output;
  
  private ParameterizedTruncatedNormal(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
