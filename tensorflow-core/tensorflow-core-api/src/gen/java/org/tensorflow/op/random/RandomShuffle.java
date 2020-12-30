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
import org.tensorflow.types.family.TType;

/**
 * Randomly shuffles a tensor along its first dimension.
 * <p>
 *   The tensor is shuffled along dimension 0, such that each `value[j]` is mapped
 *   to one and only one `output[i]`. For example, a mapping that might occur for a
 *   3x2 tensor is:
 * <pre>{@code
 * [[1, 2],       [[5, 6],
 *  [3, 4],  ==>   [1, 2],
 *  [5, 6]]        [3, 4]]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "random")
public final class RandomShuffle<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.random.RandomShuffle}
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
   * Factory method to create a class wrapping a new RandomShuffle operation.
   * 
   * @param scope current scope
   * @param value The tensor to be shuffled.
   * @param options carries optional attributes values
   * @return a new instance of RandomShuffle
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> RandomShuffle<T> create(Scope scope, Operand<T> value, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("RandomShuffle", scope.makeOpName("RandomShuffle"));
    opBuilder.addInput(value.asOutput());
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
    return new RandomShuffle<T>(opBuilder.build());
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
   * A tensor of same shape and type as `value`, shuffled along its first
   * dimension.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RandomShuffle";
  
  private Output<T> output;
  
  private RandomShuffle(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
