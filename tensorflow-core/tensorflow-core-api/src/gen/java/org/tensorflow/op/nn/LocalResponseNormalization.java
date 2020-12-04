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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Local Response Normalization.
 * <p>
 * The 4-D `input` tensor is treated as a 3-D array of 1-D vectors (along the last
 * dimension), and each vector is normalized independently.  Within a given vector,
 * each component is divided by the weighted, squared sum of inputs within
 * `depth_radius`.  In detail,
 * <p>
 *     sqr_sum[a, b, c, d] =
 *         sum(input[a, b, c, d - depth_radius : d + depth_radius + 1] ** 2)
 *     output = input / (bias + alpha * sqr_sum) ** beta
 * <p>
 * For details, see [Krizhevsky et al., ImageNet classification with deep
 * convolutional neural networks (NIPS 2012)](http://papers.nips.cc/paper/4824-imagenet-classification-with-deep-convolutional-neural-networks).
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "nn")
public final class LocalResponseNormalization<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.LocalResponseNormalization}
   */
  public static class Options {
    
    /**
     * @param depthRadius 0-D.  Half-width of the 1-D normalization window.
     */
    public Options depthRadius(Long depthRadius) {
      this.depthRadius = depthRadius;
      return this;
    }
    
    /**
     * @param bias An offset (usually positive to avoid dividing by 0).
     */
    public Options bias(Float bias) {
      this.bias = bias;
      return this;
    }
    
    /**
     * @param alpha A scale factor, usually positive.
     */
    public Options alpha(Float alpha) {
      this.alpha = alpha;
      return this;
    }
    
    /**
     * @param beta An exponent.
     */
    public Options beta(Float beta) {
      this.beta = beta;
      return this;
    }
    
    private Long depthRadius;
    private Float bias;
    private Float alpha;
    private Float beta;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new LocalResponseNormalization operation.
   * 
   * @param scope current scope
   * @param input 4-D.
   * @param options carries optional attributes values
   * @return a new instance of LocalResponseNormalization
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> LocalResponseNormalization<T> create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("LRN", scope.makeOpName("LocalResponseNormalization"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.depthRadius != null) {
          opBuilder.setAttr("depth_radius", opts.depthRadius);
        }
        if (opts.bias != null) {
          opBuilder.setAttr("bias", opts.bias);
        }
        if (opts.alpha != null) {
          opBuilder.setAttr("alpha", opts.alpha);
        }
        if (opts.beta != null) {
          opBuilder.setAttr("beta", opts.beta);
        }
      }
    }
    return new LocalResponseNormalization<T>(opBuilder.build());
  }
  
  /**
   * @param depthRadius 0-D.  Half-width of the 1-D normalization window.
   */
  public static Options depthRadius(Long depthRadius) {
    return new Options().depthRadius(depthRadius);
  }
  
  /**
   * @param bias An offset (usually positive to avoid dividing by 0).
   */
  public static Options bias(Float bias) {
    return new Options().bias(bias);
  }
  
  /**
   * @param alpha A scale factor, usually positive.
   */
  public static Options alpha(Float alpha) {
    return new Options().alpha(alpha);
  }
  
  /**
   * @param beta An exponent.
   */
  public static Options beta(Float beta) {
    return new Options().beta(beta);
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "LRN";
  
  private Output<T> output;
  
  private LocalResponseNormalization(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
