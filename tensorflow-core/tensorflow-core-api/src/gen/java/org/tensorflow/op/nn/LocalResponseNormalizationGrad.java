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

/**
 * Gradients for Local Response Normalization.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class LocalResponseNormalizationGrad<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.LocalResponseNormalizationGrad}
   */
  public static class Options {
    
    /**
     * @param depthRadius A depth radius.
     */
    public Options depthRadius(Long depthRadius) {
      this.depthRadius = depthRadius;
      return this;
    }
    
    /**
     * @param bias An offset (usually > 0 to avoid dividing by 0).
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
   * Factory method to create a class wrapping a new LocalResponseNormalizationGrad operation.
   * 
   * @param scope current scope
   * @param inputGrads 4-D with shape `[batch, height, width, channels]`.
   * @param inputImage 4-D with shape `[batch, height, width, channels]`.
   * @param outputImage 4-D with shape `[batch, height, width, channels]`.
   * @param options carries optional attributes values
   * @return a new instance of LocalResponseNormalizationGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> LocalResponseNormalizationGrad<T> create(Scope scope, Operand<T> inputGrads, Operand<T> inputImage, Operand<T> outputImage, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("LRNGrad", scope.makeOpName("LocalResponseNormalizationGrad"));
    opBuilder.addInput(inputGrads.asOutput());
    opBuilder.addInput(inputImage.asOutput());
    opBuilder.addInput(outputImage.asOutput());
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
    return new LocalResponseNormalizationGrad<T>(opBuilder.build());
  }
  
  /**
   * @param depthRadius A depth radius.
   */
  public static Options depthRadius(Long depthRadius) {
    return new Options().depthRadius(depthRadius);
  }
  
  /**
   * @param bias An offset (usually > 0 to avoid dividing by 0).
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
   * The gradients for LRN.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "LRNGrad";
  
  private Output<T> output;
  
  private LocalResponseNormalizationGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
