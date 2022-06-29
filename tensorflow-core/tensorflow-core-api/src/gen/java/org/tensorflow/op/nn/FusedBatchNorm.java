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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Batch normalization.
 * Note that the size of 4D Tensors are defined by either &quot;NHWC&quot; or &quot;NCHW&quot;.
 * The size of 1D Tensors matches the dimension C of the 4D Tensors.
 *
 * @param <T> data type for {@code y} output
 *
 * @param <U> data type for {@code batch_mean} output
 */
@OpMetadata(
    opType = FusedBatchNorm.OP_NAME,
    inputsClass = FusedBatchNorm.Inputs.class
)
@Operator(
    group = "nn"
)
public final class FusedBatchNorm<T extends TNumber, U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FusedBatchNormV3";

  private Output<T> y;

  private Output<U> batchMean;

  private Output<U> batchVariance;

  private Output<U> reserveSpace1;

  private Output<U> reserveSpace2;

  private Output<U> reserveSpace3;

  public FusedBatchNorm(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
    batchMean = operation.output(outputIdx++);
    batchVariance = operation.output(outputIdx++);
    reserveSpace1 = operation.output(outputIdx++);
    reserveSpace2 = operation.output(outputIdx++);
    reserveSpace3 = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FusedBatchNormV3 operation.
   *
   * @param scope current scope
   * @param x A 4D Tensor for input data.
   * @param scale A 1D Tensor for scaling factor, to scale the normalized x.
   * @param offset A 1D Tensor for offset, to shift to the normalized x.
   * @param mean A 1D Tensor for population mean. Used for inference only;
   * must be empty for training.
   * @param variance A 1D Tensor for population variance. Used for inference only;
   * must be empty for training.
   * @param options carries optional attribute values
   * @param <T> data type for {@code FusedBatchNormV3} output and operands
   * @param <U> data type for {@code FusedBatchNormV3} output and operands
   * @return a new instance of FusedBatchNorm
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber, U extends TNumber> FusedBatchNorm<T, U> create(Scope scope,
      Operand<T> x, Operand<U> scale, Operand<U> offset, Operand<U> mean, Operand<U> variance,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FusedBatchNorm");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(scale.asOutput());
    opBuilder.addInput(offset.asOutput());
    opBuilder.addInput(mean.asOutput());
    opBuilder.addInput(variance.asOutput());
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
    return new FusedBatchNorm<>(opBuilder.build());
  }

  /**
   * Sets the epsilon option.
   *
   * @param epsilon A small float number added to the variance of x.
   * @return this Options instance.
   */
  public static Options epsilon(Float epsilon) {
    return new Options().epsilon(epsilon);
  }

  /**
   * Sets the exponentialAvgFactor option.
   *
   * @param exponentialAvgFactor the exponentialAvgFactor option
   * @return this Options instance.
   */
  public static Options exponentialAvgFactor(Float exponentialAvgFactor) {
    return new Options().exponentialAvgFactor(exponentialAvgFactor);
  }

  /**
   * Sets the dataFormat option.
   *
   * @param dataFormat The data format for x and y. Either &quot;NHWC&quot; (default) or &quot;NCHW&quot;.
   * @return this Options instance.
   */
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
  }

  /**
   * Sets the isTraining option.
   *
   * @param isTraining A bool value to indicate the operation is for training (default)
   * or inference.
   * @return this Options instance.
   */
  public static Options isTraining(Boolean isTraining) {
    return new Options().isTraining(isTraining);
  }

  /**
   * Gets y.
   * A 4D Tensor for output data.
   * @return y.
   */
  public Output<T> y() {
    return y;
  }

  /**
   * Gets batchMean.
   * A 1D Tensor for the computed batch mean, to be used by TensorFlow
   * to compute the running mean.
   * @return batchMean.
   */
  public Output<U> batchMean() {
    return batchMean;
  }

  /**
   * Gets batchVariance.
   * A 1D Tensor for the computed batch variance, to be used by
   * TensorFlow to compute the running variance.
   * @return batchVariance.
   */
  public Output<U> batchVariance() {
    return batchVariance;
  }

  /**
   * Gets reserveSpace1.
   * A 1D Tensor for the computed batch mean, to be reused
   * in the gradient computation.
   * @return reserveSpace1.
   */
  public Output<U> reserveSpace1() {
    return reserveSpace1;
  }

  /**
   * Gets reserveSpace2.
   * A 1D Tensor for the computed batch variance (inverted variance
   * in the cuDNN case), to be reused in the gradient computation.
   * @return reserveSpace2.
   */
  public Output<U> reserveSpace2() {
    return reserveSpace2;
  }

  /**
   * Gets reserveSpace3.
   * A 1D Tensor for some intermediate results, to be reused in the gradient
   * computation for better efficiency.
   * @return reserveSpace3.
   */
  public Output<U> reserveSpace3() {
    return reserveSpace3;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.FusedBatchNorm}
   */
  public static class Options {
    private Float epsilon;

    private Float exponentialAvgFactor;

    private String dataFormat;

    private Boolean isTraining;

    private Options() {
    }

    /**
     * Sets the epsilon option.
     *
     * @param epsilon A small float number added to the variance of x.
     * @return this Options instance.
     */
    public Options epsilon(Float epsilon) {
      this.epsilon = epsilon;
      return this;
    }

    /**
     * Sets the exponentialAvgFactor option.
     *
     * @param exponentialAvgFactor the exponentialAvgFactor option
     * @return this Options instance.
     */
    public Options exponentialAvgFactor(Float exponentialAvgFactor) {
      this.exponentialAvgFactor = exponentialAvgFactor;
      return this;
    }

    /**
     * Sets the dataFormat option.
     *
     * @param dataFormat The data format for x and y. Either &quot;NHWC&quot; (default) or &quot;NCHW&quot;.
     * @return this Options instance.
     */
    public Options dataFormat(String dataFormat) {
      this.dataFormat = dataFormat;
      return this;
    }

    /**
     * Sets the isTraining option.
     *
     * @param isTraining A bool value to indicate the operation is for training (default)
     * or inference.
     * @return this Options instance.
     */
    public Options isTraining(Boolean isTraining) {
      this.isTraining = isTraining;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = FusedBatchNorm.class
  )
  public static class Inputs<T extends TNumber, U extends TNumber> extends RawOpInputs<FusedBatchNorm<T, U>> {
    /**
     * A 4D Tensor for input data.
     */
    public final Operand<T> x;

    /**
     * A 1D Tensor for scaling factor, to scale the normalized x.
     */
    public final Operand<U> scale;

    /**
     * A 1D Tensor for offset, to shift to the normalized x.
     */
    public final Operand<U> offset;

    /**
     * A 1D Tensor for population mean. Used for inference only;
     * must be empty for training.
     */
    public final Operand<U> mean;

    /**
     * A 1D Tensor for population variance. Used for inference only;
     * must be empty for training.
     */
    public final Operand<U> variance;

    /**
     * The data type for the elements of input and output Tensors.
     */
    public final DataType T;

    /**
     * The data type for the scale, offset, mean, and variance.
     */
    public final DataType U;

    /**
     * A small float number added to the variance of x.
     */
    public final float epsilon;

    /**
     * The exponentialAvgFactor attribute
     */
    public final float exponentialAvgFactor;

    /**
     * The data format for x and y. Either "NHWC" (default) or "NCHW".
     */
    public final String dataFormat;

    /**
     * A bool value to indicate the operation is for training (default)
     * or inference.
     */
    public final boolean isTraining;

    public Inputs(GraphOperation op) {
      super(new FusedBatchNorm<>(op), op, Arrays.asList("T", "U", "epsilon", "exponential_avg_factor", "data_format", "is_training"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      scale = (Operand<U>) op.input(inputIndex++);
      offset = (Operand<U>) op.input(inputIndex++);
      mean = (Operand<U>) op.input(inputIndex++);
      variance = (Operand<U>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      U = op.attributes().getAttrType("U");
      epsilon = op.attributes().getAttrFloat("epsilon");
      exponentialAvgFactor = op.attributes().getAttrFloat("exponential_avg_factor");
      dataFormat = op.attributes().getAttrString("data_format");
      isTraining = op.attributes().getAttrBool("is_training");
    }
  }
}
