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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Gradient for batch normalization.
 * Note that the size of 4D Tensors are defined by either &quot;NHWC&quot; or &quot;NCHW&quot;.
 * The size of 1D Tensors matches the dimension C of the 4D Tensors.
 *
 * @param <T> data type for {@code x_backprop} output
 *
 * @param <U> data type for {@code scale_backprop} output
 */
@OpMetadata(
    opType = FusedBatchNormGrad.OP_NAME,
    inputsClass = FusedBatchNormGrad.Inputs.class
)
@Operator(
    group = "nn"
)
public final class FusedBatchNormGrad<T extends TNumber, U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FusedBatchNormGradV3";

  private Output<T> xBackprop;

  private Output<U> scaleBackprop;

  private Output<U> offsetBackprop;

  private Output<U> reserveSpace4;

  private Output<U> reserveSpace5;

  public FusedBatchNormGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    xBackprop = operation.output(outputIdx++);
    scaleBackprop = operation.output(outputIdx++);
    offsetBackprop = operation.output(outputIdx++);
    reserveSpace4 = operation.output(outputIdx++);
    reserveSpace5 = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FusedBatchNormGradV3 operation.
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
   * @param options carries optional attribute values
   * @param <T> data type for {@code FusedBatchNormGradV3} output and operands
   * @param <U> data type for {@code FusedBatchNormGradV3} output and operands
   * @return a new instance of FusedBatchNormGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber, U extends TNumber> FusedBatchNormGrad<T, U> create(Scope scope,
      Operand<T> yBackprop, Operand<T> x, Operand<TFloat32> scale, Operand<U> reserveSpace1,
      Operand<U> reserveSpace2, Operand<U> reserveSpace3, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FusedBatchNormGrad");
    opBuilder.addInput(yBackprop.asOutput());
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(scale.asOutput());
    opBuilder.addInput(reserveSpace1.asOutput());
    opBuilder.addInput(reserveSpace2.asOutput());
    opBuilder.addInput(reserveSpace3.asOutput());
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
    return new FusedBatchNormGrad<>(opBuilder.build());
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
   * Sets the dataFormat option.
   *
   * @param dataFormat The data format for y_backprop, x, x_backprop.
   * Either &quot;NHWC&quot; (default) or &quot;NCHW&quot;.
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
   * Gets xBackprop.
   * A 4D Tensor for the gradient with respect to x.
   * @return xBackprop.
   */
  public Output<T> xBackprop() {
    return xBackprop;
  }

  /**
   * Gets scaleBackprop.
   * A 1D Tensor for the gradient with respect to scale.
   * @return scaleBackprop.
   */
  public Output<U> scaleBackprop() {
    return scaleBackprop;
  }

  /**
   * Gets offsetBackprop.
   * A 1D Tensor for the gradient with respect to offset.
   * @return offsetBackprop.
   */
  public Output<U> offsetBackprop() {
    return offsetBackprop;
  }

  /**
   * Gets reserveSpace4.
   * Unused placeholder to match the mean input in FusedBatchNorm.
   * @return reserveSpace4.
   */
  public Output<U> reserveSpace4() {
    return reserveSpace4;
  }

  /**
   * Gets reserveSpace5.
   * Unused placeholder to match the variance input
   * in FusedBatchNorm.
   * @return reserveSpace5.
   */
  public Output<U> reserveSpace5() {
    return reserveSpace5;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.FusedBatchNormGrad}
   */
  public static class Options {
    private Float epsilon;

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
     * Sets the dataFormat option.
     *
     * @param dataFormat The data format for y_backprop, x, x_backprop.
     * Either &quot;NHWC&quot; (default) or &quot;NCHW&quot;.
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
      outputsClass = FusedBatchNormGrad.class
  )
  public static class Inputs<T extends TNumber, U extends TNumber> extends RawOpInputs<FusedBatchNormGrad<T, U>> {
    /**
     * A 4D Tensor for the gradient with respect to y.
     */
    public final Operand<T> yBackprop;

    /**
     * A 4D Tensor for input data.
     */
    public final Operand<T> x;

    /**
     * A 1D Tensor for scaling factor, to scale the normalized x.
     */
    public final Operand<TFloat32> scale;

    /**
     * When is_training is True, a 1D Tensor for the computed batch
     * mean to be reused in gradient computation. When is_training is
     * False, a 1D Tensor for the population mean to be reused in both
     * 1st and 2nd order gradient computation.
     */
    public final Operand<U> reserveSpace1;

    /**
     * When is_training is True, a 1D Tensor for the computed batch
     * variance (inverted variance in the cuDNN case) to be reused in
     * gradient computation. When is_training is False, a 1D Tensor
     * for the population variance to be reused in both 1st and 2nd
     * order gradient computation.
     */
    public final Operand<U> reserveSpace2;

    /**
     * When is_training is True, a 1D Tensor for some intermediate results to be reused
     * in gradient computation. When is_training is False, a dummy empty Tensor will be
     * created.
     */
    public final Operand<U> reserveSpace3;

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
     * The data format for y_backprop, x, x_backprop.
     * Either "NHWC" (default) or "NCHW".
     */
    public final String dataFormat;

    /**
     * A bool value to indicate the operation is for training (default)
     * or inference.
     */
    public final boolean isTraining;

    public Inputs(GraphOperation op) {
      super(new FusedBatchNormGrad<>(op), op, Arrays.asList("T", "U", "epsilon", "data_format", "is_training"));
      int inputIndex = 0;
      yBackprop = (Operand<T>) op.input(inputIndex++);
      x = (Operand<T>) op.input(inputIndex++);
      scale = (Operand<TFloat32>) op.input(inputIndex++);
      reserveSpace1 = (Operand<U>) op.input(inputIndex++);
      reserveSpace2 = (Operand<U>) op.input(inputIndex++);
      reserveSpace3 = (Operand<U>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      U = op.attributes().getAttrType("U");
      epsilon = op.attributes().getAttrFloat("epsilon");
      dataFormat = op.attributes().getAttrString("data_format");
      isTraining = op.attributes().getAttrBool("is_training");
    }
  }
}
