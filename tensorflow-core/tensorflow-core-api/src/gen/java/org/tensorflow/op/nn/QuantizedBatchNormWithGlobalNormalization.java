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
import org.tensorflow.op.Operands;
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
 * Quantized Batch normalization.
 * This op is deprecated and will be removed in the future. Prefer
 * {@code tf.nn.batch_normalization}.
 *
 * @param <U> data type for {@code result} output
 */
@OpMetadata(
    opType = QuantizedBatchNormWithGlobalNormalization.OP_NAME,
    inputsClass = QuantizedBatchNormWithGlobalNormalization.Inputs.class
)
@Operator(
    group = "nn"
)
public final class QuantizedBatchNormWithGlobalNormalization<U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedBatchNormWithGlobalNormalization";

  private Output<U> result;

  private Output<TFloat32> resultMin;

  private Output<TFloat32> resultMax;

  public QuantizedBatchNormWithGlobalNormalization(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    result = operation.output(outputIdx++);
    resultMin = operation.output(outputIdx++);
    resultMax = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizedBatchNormWithGlobalNormalization operation.
   *
   * @param scope current scope
   * @param t A 4D input Tensor.
   * @param tMin The value represented by the lowest quantized input.
   * @param tMax The value represented by the highest quantized input.
   * @param m A 1D mean Tensor with size matching the last dimension of t.
   * This is the first output from tf.nn.moments,
   * or a saved moving average thereof.
   * @param mMin The value represented by the lowest quantized mean.
   * @param mMax The value represented by the highest quantized mean.
   * @param v A 1D variance Tensor with size matching the last dimension of t.
   * This is the second output from tf.nn.moments,
   * or a saved moving average thereof.
   * @param vMin The value represented by the lowest quantized variance.
   * @param vMax The value represented by the highest quantized variance.
   * @param beta A 1D beta Tensor with size matching the last dimension of t.
   * An offset to be added to the normalized tensor.
   * @param betaMin The value represented by the lowest quantized offset.
   * @param betaMax The value represented by the highest quantized offset.
   * @param gamma A 1D gamma Tensor with size matching the last dimension of t.
   * If &quot;scale_after_normalization&quot; is true, this tensor will be multiplied
   * with the normalized tensor.
   * @param gammaMin The value represented by the lowest quantized gamma.
   * @param gammaMax The value represented by the highest quantized gamma.
   * @param outType The value of the outType attribute
   * @param varianceEpsilon A small float number to avoid dividing by 0.
   * @param scaleAfterNormalization A bool indicating whether the resulted tensor
   * needs to be multiplied with gamma.
   * @param <U> data type for {@code QuantizedBatchNormWithGlobalNormalization} output and operands
   * @param <T> data type for {@code QuantizedBatchNormWithGlobalNormalization} output and operands
   * @return a new instance of QuantizedBatchNormWithGlobalNormalization
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber, T extends TNumber> QuantizedBatchNormWithGlobalNormalization<U> create(
      Scope scope, Operand<T> t, Operand<TFloat32> tMin, Operand<TFloat32> tMax, Operand<T> m,
      Operand<TFloat32> mMin, Operand<TFloat32> mMax, Operand<T> v, Operand<TFloat32> vMin,
      Operand<TFloat32> vMax, Operand<T> beta, Operand<TFloat32> betaMin, Operand<TFloat32> betaMax,
      Operand<T> gamma, Operand<TFloat32> gammaMin, Operand<TFloat32> gammaMax, Class<U> outType,
      Float varianceEpsilon, Boolean scaleAfterNormalization) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizedBatchNormWithGlobalNormalization");
    opBuilder.addInput(t.asOutput());
    opBuilder.addInput(tMin.asOutput());
    opBuilder.addInput(tMax.asOutput());
    opBuilder.addInput(m.asOutput());
    opBuilder.addInput(mMin.asOutput());
    opBuilder.addInput(mMax.asOutput());
    opBuilder.addInput(v.asOutput());
    opBuilder.addInput(vMin.asOutput());
    opBuilder.addInput(vMax.asOutput());
    opBuilder.addInput(beta.asOutput());
    opBuilder.addInput(betaMin.asOutput());
    opBuilder.addInput(betaMax.asOutput());
    opBuilder.addInput(gamma.asOutput());
    opBuilder.addInput(gammaMin.asOutput());
    opBuilder.addInput(gammaMax.asOutput());
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    opBuilder.setAttr("variance_epsilon", varianceEpsilon);
    opBuilder.setAttr("scale_after_normalization", scaleAfterNormalization);
    return new QuantizedBatchNormWithGlobalNormalization<>(opBuilder.build());
  }

  /**
   * Gets result.
   *
   * @return result.
   */
  public Output<U> result() {
    return result;
  }

  /**
   * Gets resultMin.
   *
   * @return resultMin.
   */
  public Output<TFloat32> resultMin() {
    return resultMin;
  }

  /**
   * Gets resultMax.
   *
   * @return resultMax.
   */
  public Output<TFloat32> resultMax() {
    return resultMax;
  }

  @OpInputsMetadata(
      outputsClass = QuantizedBatchNormWithGlobalNormalization.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<QuantizedBatchNormWithGlobalNormalization<?>> {
    /**
     * A 4D input Tensor.
     */
    public final Operand<T> t;

    /**
     * The value represented by the lowest quantized input.
     */
    public final Operand<TFloat32> tMin;

    /**
     * The value represented by the highest quantized input.
     */
    public final Operand<TFloat32> tMax;

    /**
     * A 1D mean Tensor with size matching the last dimension of t.
     * This is the first output from tf.nn.moments,
     * or a saved moving average thereof.
     */
    public final Operand<T> m;

    /**
     * The value represented by the lowest quantized mean.
     */
    public final Operand<TFloat32> mMin;

    /**
     * The value represented by the highest quantized mean.
     */
    public final Operand<TFloat32> mMax;

    /**
     * A 1D variance Tensor with size matching the last dimension of t.
     * This is the second output from tf.nn.moments,
     * or a saved moving average thereof.
     */
    public final Operand<T> v;

    /**
     * The value represented by the lowest quantized variance.
     */
    public final Operand<TFloat32> vMin;

    /**
     * The value represented by the highest quantized variance.
     */
    public final Operand<TFloat32> vMax;

    /**
     * A 1D beta Tensor with size matching the last dimension of t.
     * An offset to be added to the normalized tensor.
     */
    public final Operand<T> beta;

    /**
     * The value represented by the lowest quantized offset.
     */
    public final Operand<TFloat32> betaMin;

    /**
     * The value represented by the highest quantized offset.
     */
    public final Operand<TFloat32> betaMax;

    /**
     * A 1D gamma Tensor with size matching the last dimension of t.
     * If &quot;scale_after_normalization&quot; is true, this tensor will be multiplied
     * with the normalized tensor.
     */
    public final Operand<T> gamma;

    /**
     * The value represented by the lowest quantized gamma.
     */
    public final Operand<TFloat32> gammaMin;

    /**
     * The value represented by the highest quantized gamma.
     */
    public final Operand<TFloat32> gammaMax;

    /**
     * The Tinput attribute
     */
    public final DataType Tinput;

    /**
     * The outType attribute
     */
    public final DataType outType;

    /**
     * A small float number to avoid dividing by 0.
     */
    public final float varianceEpsilon;

    /**
     * A bool indicating whether the resulted tensor
     * needs to be multiplied with gamma.
     */
    public final boolean scaleAfterNormalization;

    public Inputs(GraphOperation op) {
      super(new QuantizedBatchNormWithGlobalNormalization<>(op), op, Arrays.asList("Tinput", "out_type", "variance_epsilon", "scale_after_normalization"));
      int inputIndex = 0;
      t = (Operand<T>) op.input(inputIndex++);
      tMin = (Operand<TFloat32>) op.input(inputIndex++);
      tMax = (Operand<TFloat32>) op.input(inputIndex++);
      m = (Operand<T>) op.input(inputIndex++);
      mMin = (Operand<TFloat32>) op.input(inputIndex++);
      mMax = (Operand<TFloat32>) op.input(inputIndex++);
      v = (Operand<T>) op.input(inputIndex++);
      vMin = (Operand<TFloat32>) op.input(inputIndex++);
      vMax = (Operand<TFloat32>) op.input(inputIndex++);
      beta = (Operand<T>) op.input(inputIndex++);
      betaMin = (Operand<TFloat32>) op.input(inputIndex++);
      betaMax = (Operand<TFloat32>) op.input(inputIndex++);
      gamma = (Operand<T>) op.input(inputIndex++);
      gammaMin = (Operand<TFloat32>) op.input(inputIndex++);
      gammaMax = (Operand<TFloat32>) op.input(inputIndex++);
      Tinput = op.attributes().getAttrType("Tinput");
      outType = op.attributes().getAttrType("out_type");
      varianceEpsilon = op.attributes().getAttrFloat("variance_epsilon");
      scaleAfterNormalization = op.attributes().getAttrBool("scale_after_normalization");
    }
  }
}
