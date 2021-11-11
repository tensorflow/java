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
 * Batch normalization.
 * This op is deprecated. Prefer {@code tf.nn.batch_normalization}.
 *
 * @param <T> data type for {@code result} output
 */
@OpMetadata(
    opType = BatchNormWithGlobalNormalization.OP_NAME,
    inputsClass = BatchNormWithGlobalNormalization.Inputs.class
)
@Operator(
    group = "nn"
)
public final class BatchNormWithGlobalNormalization<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BatchNormWithGlobalNormalization";

  private Output<T> result;

  public BatchNormWithGlobalNormalization(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    result = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BatchNormWithGlobalNormalization operation.
   *
   * @param scope current scope
   * @param t A 4D input Tensor.
   * @param m A 1D mean Tensor with size matching the last dimension of t.
   * This is the first output from tf.nn.moments,
   * or a saved moving average thereof.
   * @param v A 1D variance Tensor with size matching the last dimension of t.
   * This is the second output from tf.nn.moments,
   * or a saved moving average thereof.
   * @param beta A 1D beta Tensor with size matching the last dimension of t.
   * An offset to be added to the normalized tensor.
   * @param gamma A 1D gamma Tensor with size matching the last dimension of t.
   * If &quot;scale_after_normalization&quot; is true, this tensor will be multiplied
   * with the normalized tensor.
   * @param varianceEpsilon A small float number to avoid dividing by 0.
   * @param scaleAfterNormalization A bool indicating whether the resulted tensor
   * needs to be multiplied with gamma.
   * @param <T> data type for {@code BatchNormWithGlobalNormalization} output and operands
   * @return a new instance of BatchNormWithGlobalNormalization
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> BatchNormWithGlobalNormalization<T> create(Scope scope,
      Operand<T> t, Operand<T> m, Operand<T> v, Operand<T> beta, Operand<T> gamma,
      Float varianceEpsilon, Boolean scaleAfterNormalization) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BatchNormWithGlobalNormalization");
    opBuilder.addInput(t.asOutput());
    opBuilder.addInput(m.asOutput());
    opBuilder.addInput(v.asOutput());
    opBuilder.addInput(beta.asOutput());
    opBuilder.addInput(gamma.asOutput());
    opBuilder.setAttr("variance_epsilon", varianceEpsilon);
    opBuilder.setAttr("scale_after_normalization", scaleAfterNormalization);
    return new BatchNormWithGlobalNormalization<>(opBuilder.build());
  }

  /**
   * Gets result.
   *
   * @return result.
   */
  public Output<T> result() {
    return result;
  }

  @Override
  public Output<T> asOutput() {
    return result;
  }

  @OpInputsMetadata(
      outputsClass = BatchNormWithGlobalNormalization.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<BatchNormWithGlobalNormalization<T>> {
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
     * A 1D beta Tensor with size matching the last dimension of t.
     * An offset to be added to the normalized tensor.
     */
    public final Operand<T> beta;

    /**
     * A 1D gamma Tensor with size matching the last dimension of t.
     * If &quot;scale_after_normalization&quot; is true, this tensor will be multiplied
     * with the normalized tensor.
     */
    public final Operand<T> gamma;

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
      super(new BatchNormWithGlobalNormalization<>(op), op, Arrays.asList("T", "variance_epsilon", "scale_after_normalization"));
      int inputIndex = 0;
      t = (Operand<T>) op.input(inputIndex++);
      m = (Operand<T>) op.input(inputIndex++);
      v = (Operand<T>) op.input(inputIndex++);
      beta = (Operand<T>) op.input(inputIndex++);
      gamma = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      varianceEpsilon = op.attributes().getAttrFloat("variance_epsilon");
      scaleAfterNormalization = op.attributes().getAttrBool("scale_after_normalization");
    }
  }
}
