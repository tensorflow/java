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
 * Batch normalization.
 * <p>
 * Note that the size of 4D Tensors are defined by either "NHWC" or "NCHW".
 * The size of 1D Tensors matches the dimension C of the 4D Tensors.
 * 
 * @param <T> data type for {@code y()} output
 * @param <U> data type for {@code batchMean()} output
 */
@Operator(group = "nn")
public final class FusedBatchNorm<T extends TNumber, U extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.FusedBatchNorm}
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
     * @param exponentialAvgFactor 
     */
    public Options exponentialAvgFactor(Float exponentialAvgFactor) {
      this.exponentialAvgFactor = exponentialAvgFactor;
      return this;
    }
    
    /**
     * @param dataFormat The data format for x and y. Either "NHWC" (default) or "NCHW".
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
    private Float exponentialAvgFactor;
    private String dataFormat;
    private Boolean isTraining;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new FusedBatchNorm operation.
   * 
   * @param scope current scope
   * @param x A 4D Tensor for input data.
   * @param scale A 1D Tensor for scaling factor, to scale the normalized x.
   * @param offset A 1D Tensor for offset, to shift to the normalized x.
   * @param mean A 1D Tensor for population mean. Used for inference only;
   * must be empty for training.
   * @param variance A 1D Tensor for population variance. Used for inference only;
   * must be empty for training.
   * @param options carries optional attributes values
   * @return a new instance of FusedBatchNorm
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber> FusedBatchNorm<T, U> create(Scope scope, Operand<T> x, Operand<U> scale, Operand<U> offset, Operand<U> mean, Operand<U> variance, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("FusedBatchNormV3", scope.makeOpName("FusedBatchNorm"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(scale.asOutput());
    opBuilder.addInput(offset.asOutput());
    opBuilder.addInput(mean.asOutput());
    opBuilder.addInput(variance.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.epsilon != null) {
          opBuilder.setAttr("epsilon", opts.epsilon);
        }
        if (opts.exponentialAvgFactor != null) {
          opBuilder.setAttr("exponential_avg_factor", opts.exponentialAvgFactor);
        }
        if (opts.dataFormat != null) {
          opBuilder.setAttr("data_format", opts.dataFormat);
        }
        if (opts.isTraining != null) {
          opBuilder.setAttr("is_training", opts.isTraining);
        }
      }
    }
    return new FusedBatchNorm<T, U>(opBuilder.build());
  }
  
  /**
   * @param epsilon A small float number added to the variance of x.
   */
  public static Options epsilon(Float epsilon) {
    return new Options().epsilon(epsilon);
  }
  
  /**
   * @param exponentialAvgFactor 
   */
  public static Options exponentialAvgFactor(Float exponentialAvgFactor) {
    return new Options().exponentialAvgFactor(exponentialAvgFactor);
  }
  
  /**
   * @param dataFormat The data format for x and y. Either "NHWC" (default) or "NCHW".
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
   * A 4D Tensor for output data.
   */
  public Output<T> y() {
    return y;
  }
  
  /**
   * A 1D Tensor for the computed batch mean, to be used by TensorFlow
   * to compute the running mean.
   */
  public Output<U> batchMean() {
    return batchMean;
  }
  
  /**
   * A 1D Tensor for the computed batch variance, to be used by
   * TensorFlow to compute the running variance.
   */
  public Output<U> batchVariance() {
    return batchVariance;
  }
  
  /**
   * A 1D Tensor for the computed batch mean, to be reused
   * in the gradient computation.
   */
  public Output<U> reserveSpace1() {
    return reserveSpace1;
  }
  
  /**
   * A 1D Tensor for the computed batch variance (inverted variance
   * in the cuDNN case), to be reused in the gradient computation.
   */
  public Output<U> reserveSpace2() {
    return reserveSpace2;
  }
  
  /**
   * A 1D Tensor for some intermediate results, to be reused in the gradient
   * computation for better efficiency.
   */
  public Output<U> reserveSpace3() {
    return reserveSpace3;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "FusedBatchNormV3";
  
  private Output<T> y;
  private Output<U> batchMean;
  private Output<U> batchVariance;
  private Output<U> reserveSpace1;
  private Output<U> reserveSpace2;
  private Output<U> reserveSpace3;
  
  private FusedBatchNorm(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
    batchMean = operation.output(outputIdx++);
    batchVariance = operation.output(outputIdx++);
    reserveSpace1 = operation.output(outputIdx++);
    reserveSpace2 = operation.output(outputIdx++);
    reserveSpace3 = operation.output(outputIdx++);
  }
}
