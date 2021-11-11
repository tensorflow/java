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
import org.tensorflow.types.family.TType;

/**
 * Gradients for batch normalization.
 * This op is deprecated. See {@code tf.nn.batch_normalization}.
 *
 * @param <T> data type for {@code dx} output
 */
@OpMetadata(
    opType = BatchNormWithGlobalNormalizationGrad.OP_NAME,
    inputsClass = BatchNormWithGlobalNormalizationGrad.Inputs.class
)
@Operator(
    group = "nn"
)
public final class BatchNormWithGlobalNormalizationGrad<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BatchNormWithGlobalNormalizationGrad";

  private Output<T> dx;

  private Output<T> dm;

  private Output<T> dv;

  private Output<T> db;

  private Output<T> dg;

  public BatchNormWithGlobalNormalizationGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    dx = operation.output(outputIdx++);
    dm = operation.output(outputIdx++);
    dv = operation.output(outputIdx++);
    db = operation.output(outputIdx++);
    dg = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BatchNormWithGlobalNormalizationGrad operation.
   *
   * @param scope current scope
   * @param t A 4D input Tensor.
   * @param m A 1D mean Tensor with size matching the last dimension of t.
   * This is the first output from tf.nn.moments,
   * or a saved moving average thereof.
   * @param v A 1D variance Tensor with size matching the last dimension of t.
   * This is the second output from tf.nn.moments,
   * or a saved moving average thereof.
   * @param gamma A 1D gamma Tensor with size matching the last dimension of t.
   * If &quot;scale_after_normalization&quot; is true, this Tensor will be multiplied
   * with the normalized Tensor.
   * @param backprop 4D backprop Tensor.
   * @param varianceEpsilon A small float number to avoid dividing by 0.
   * @param scaleAfterNormalization A bool indicating whether the resulted tensor
   * needs to be multiplied with gamma.
   * @param <T> data type for {@code BatchNormWithGlobalNormalizationGrad} output and operands
   * @return a new instance of BatchNormWithGlobalNormalizationGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> BatchNormWithGlobalNormalizationGrad<T> create(Scope scope,
      Operand<T> t, Operand<T> m, Operand<T> v, Operand<T> gamma, Operand<T> backprop,
      Float varianceEpsilon, Boolean scaleAfterNormalization) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BatchNormWithGlobalNormalizationGrad");
    opBuilder.addInput(t.asOutput());
    opBuilder.addInput(m.asOutput());
    opBuilder.addInput(v.asOutput());
    opBuilder.addInput(gamma.asOutput());
    opBuilder.addInput(backprop.asOutput());
    opBuilder.setAttr("variance_epsilon", varianceEpsilon);
    opBuilder.setAttr("scale_after_normalization", scaleAfterNormalization);
    return new BatchNormWithGlobalNormalizationGrad<>(opBuilder.build());
  }

  /**
   * Gets dx.
   * 4D backprop tensor for input.
   * @return dx.
   */
  public Output<T> dx() {
    return dx;
  }

  /**
   * Gets dm.
   * 1D backprop tensor for mean.
   * @return dm.
   */
  public Output<T> dm() {
    return dm;
  }

  /**
   * Gets dv.
   * 1D backprop tensor for variance.
   * @return dv.
   */
  public Output<T> dv() {
    return dv;
  }

  /**
   * Gets db.
   * 1D backprop tensor for beta.
   * @return db.
   */
  public Output<T> db() {
    return db;
  }

  /**
   * Gets dg.
   * 1D backprop tensor for gamma.
   * @return dg.
   */
  public Output<T> dg() {
    return dg;
  }

  @OpInputsMetadata(
      outputsClass = BatchNormWithGlobalNormalizationGrad.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<BatchNormWithGlobalNormalizationGrad<T>> {
    /**
     * A 4D input Tensor.
     */
    public final Operand<T> t;

    /**
     * A 1D mean Tensor with size matching the last dimension of t.
     * This is the first output from tf.nn.moments,
     * or a saved moving average thereof.
     */
    public final Operand<T> m;

    /**
     * A 1D variance Tensor with size matching the last dimension of t.
     * This is the second output from tf.nn.moments,
     * or a saved moving average thereof.
     */
    public final Operand<T> v;

    /**
     * A 1D gamma Tensor with size matching the last dimension of t.
     * If &quot;scale_after_normalization&quot; is true, this Tensor will be multiplied
     * with the normalized Tensor.
     */
    public final Operand<T> gamma;

    /**
     * 4D backprop Tensor.
     */
    public final Operand<T> backprop;

    /**
     * The T attribute
     */
    public final DataType T;

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
      super(new BatchNormWithGlobalNormalizationGrad<>(op), op, Arrays.asList("T", "variance_epsilon", "scale_after_normalization"));
      int inputIndex = 0;
      t = (Operand<T>) op.input(inputIndex++);
      m = (Operand<T>) op.input(inputIndex++);
      v = (Operand<T>) op.input(inputIndex++);
      gamma = (Operand<T>) op.input(inputIndex++);
      backprop = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      varianceEpsilon = op.attributes().getAttrFloat("variance_epsilon");
      scaleAfterNormalization = op.attributes().getAttrBool("scale_after_normalization");
    }
  }
}
