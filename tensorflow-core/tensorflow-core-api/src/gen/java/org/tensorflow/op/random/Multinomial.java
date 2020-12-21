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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Draws samples from a multinomial distribution.
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator(group = "random")
public final class Multinomial<U extends TNumber> extends RawOp implements Operand<U> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.random.Multinomial}
   */
  public static class Options {
    
    /**
     * @param seed If either seed or seed2 is set to be non-zero, the internal random number
     * generator is seeded by the given seed.  Otherwise, a random seed is used.
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
   * Factory method to create a class wrapping a new Multinomial operation.
   * 
   * @param scope current scope
   * @param logits 2-D Tensor with shape `[batch_size, num_classes]`.  Each slice `[i, :]`
   * represents the unnormalized log probabilities for all classes.
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param outputDtype 
   * @param options carries optional attributes values
   * @return a new instance of Multinomial
   */
  @Endpoint(describeByClass = true)
  public static <U extends TNumber, T extends TNumber> Multinomial<U> create(Scope scope, Operand<T> logits, Operand<TInt32> numSamples, Class<U> outputDtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Multinomial", scope.makeOpName("Multinomial"));
    opBuilder.addInput(logits.asOutput());
    opBuilder.addInput(numSamples.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_dtype", Operands.toDataType(outputDtype));
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
    return new Multinomial<U>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new Multinomial operation using default output types.
   * 
   * @param scope current scope
   * @param logits 2-D Tensor with shape `[batch_size, num_classes]`.  Each slice `[i, :]`
   * represents the unnormalized log probabilities for all classes.
   * @param numSamples 0-D.  Number of independent samples to draw for each row slice.
   * @param options carries optional attributes values
   * @return a new instance of Multinomial
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> Multinomial<TInt64> create(Scope scope, Operand<T> logits, Operand<TInt32> numSamples, Options... options) {
    return create(scope, logits, numSamples, TInt64.class, options);
  }
  
  /**
   * @param seed If either seed or seed2 is set to be non-zero, the internal random number
   * generator is seeded by the given seed.  Otherwise, a random seed is used.
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
   * 2-D Tensor with shape `[batch_size, num_samples]`.  Each slice `[i, :]`
   * contains the drawn class labels with range `[0, num_classes)`.
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Multinomial";
  
  private Output<U> output;
  
  private Multinomial(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
