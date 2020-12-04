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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Gradient for batch normalization.
 * <p>
 * Note that the size of 4D Tensors are defined by either "NHWC" or "NCHW".
 * The size of 1D Tensors matches the dimension C of the 4D Tensors.
 * 
 * @param <T> data type for {@code xBackprop()} output
 * @param <U> data type for {@code scaleBackprop()} output
 */
@Operator(group = "nn")
public final class FusedBatchNormGrad<T extends TNumber, U extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.FusedBatchNormGrad}
   */
  public static class Options {
    
    /**
     * @param epsilon A small float number added to the variance of x.
     */
    public Options epsilon(Float epsilon) {
      this.epsilon = epsilon;
      return this;
    }
    
    /**
     * @param dataFormat The data format for y_backprop, x, x_backprop.
     * Either "NHWC" (default) or "NCHW".
     */
    public Options dataFormat(String dataFormat) {
      this.dataFormat = dataFormat;
      return this;
    }
    
    /**
     * @param isTraining A bool value to indicate the operation is for training (default)
     * or inference.
     */
    public Options isTraining(Boolean isTraining) {
      this.isTraining = isTraining;
      return this;
    }
    
    private Float epsilon;
    private String dataFormat;
    private Boolean isTraining;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new FusedBatchNormGrad operation.
   * 
   * @param scope current scope
   * @param yBackprop A 4D Tensor for the gradient with respect to y.
   * @param x A 4D Tensor for input data.
   * @param scale A 1D Tensor for scaling factor, to scale the normalized x.
   * @param reserveSpace1 When is_training is True, a 1D Tensor for the computed batch
   * mean to be reused in gradient computation. When is_training is
   * False, a 1D Tensor for the population mean to be reused in both
   * 1st and 2nd order gradient computation.
   * @param reserveSpace2 When is_training is True, a 1D Tensor for the computed batch
   * variance (inverted variance in the cuDNN case) to be reused in
   * gradient computation. When is_training is False, a 1D Tensor
   * for the population variance to be reused in both 1st and 2nd
   * order gradient computation.
   * @param reserveSpace3 When is_training is True, a 1D Tensor for some intermediate results to be reused
   * in gradient computation. When is_training is False, a dummy empty Tensor will be
   * created.
   * @param options carries optional attributes values
   * @return a new instance of FusedBatchNormGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber> FusedBatchNormGrad<T, U> create(Scope scope, Operand<T> yBackprop, Operand<T> x, Operand<TFloat32> scale, Operand<U> reserveSpace1, Operand<U> reserveSpace2, Operand<U> reserveSpace3, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("FusedBatchNormGradV3", scope.makeOpName("FusedBatchNormGrad"));
    opBuilder.addInput(yBackprop.asOutput());
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(scale.asOutput());
    opBuilder.addInput(reserveSpace1.asOutput());
    opBuilder.addInput(reserveSpace2.asOutput());
    opBuilder.addInput(reserveSpace3.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.epsilon != null) {
          opBuilder.setAttr("epsilon", opts.epsilon);
        }
        if (opts.dataFormat != null) {
          opBuilder.setAttr("data_format", opts.dataFormat);
        }
        if (opts.isTraining != null) {
          opBuilder.setAttr("is_training", opts.isTraining);
        }
      }
    }
    return new FusedBatchNormGrad<T, U>(opBuilder.build());
  }
  
  /**
   * @param epsilon A small float number added to the variance of x.
   */
  public static Options epsilon(Float epsilon) {
    return new Options().epsilon(epsilon);
  }
  
  /**
   * @param dataFormat The data format for y_backprop, x, x_backprop.
   * Either "NHWC" (default) or "NCHW".
   */
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
  }
  
  /**
   * @param isTraining A bool value to indicate the operation is for training (default)
   * or inference.
   */
  public static Options isTraining(Boolean isTraining) {
    return new Options().isTraining(isTraining);
  }
  
  /**
   * A 4D Tensor for the gradient with respect to x.
   */
  public Output<T> xBackprop() {
    return xBackprop;
  }
  
  /**
   * A 1D Tensor for the gradient with respect to scale.
   */
  public Output<U> scaleBackprop() {
    return scaleBackprop;
  }
  
  /**
   * A 1D Tensor for the gradient with respect to offset.
   */
  public Output<U> offsetBackprop() {
    return offsetBackprop;
  }
  
  /**
   * Unused placeholder to match the mean input in FusedBatchNorm.
   */
  public Output<U> reserveSpace4() {
    return reserveSpace4;
  }
  
  /**
   * Unused placeholder to match the variance input
   * in FusedBatchNorm.
   */
  public Output<U> reserveSpace5() {
    return reserveSpace5;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "FusedBatchNormGradV3";
  
  private Output<T> xBackprop;
  private Output<U> scaleBackprop;
  private Output<U> offsetBackprop;
  private Output<U> reserveSpace4;
  private Output<U> reserveSpace5;
  
  private FusedBatchNormGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    xBackprop = operation.output(outputIdx++);
    scaleBackprop = operation.output(outputIdx++);
    offsetBackprop = operation.output(outputIdx++);
    reserveSpace4 = operation.output(outputIdx++);
    reserveSpace5 = operation.output(outputIdx++);
  }
}
