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
import org.tensorflow.types.family.TType;

/**
 * Batch normalization.
 * <p>
 * This op is deprecated. Prefer `tf.nn.batch_normalization`.
 * 
 * @param <T> data type for {@code result()} output
 */
@Operator(group = "nn")
public final class BatchNormWithGlobalNormalization<T extends TType> extends RawOp implements Operand<T> {
  
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
   * If "scale_after_normalization" is true, this tensor will be multiplied
   * with the normalized tensor.
   * @param varianceEpsilon A small float number to avoid dividing by 0.
   * @param scaleAfterNormalization A bool indicating whether the resulted tensor
   * needs to be multiplied with gamma.
   * @return a new instance of BatchNormWithGlobalNormalization
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> BatchNormWithGlobalNormalization<T> create(Scope scope, Operand<T> t, Operand<T> m, Operand<T> v, Operand<T> beta, Operand<T> gamma, Float varianceEpsilon, Boolean scaleAfterNormalization) {
    OperationBuilder opBuilder = scope.env().opBuilder("BatchNormWithGlobalNormalization", scope.makeOpName("BatchNormWithGlobalNormalization"));
    opBuilder.addInput(t.asOutput());
    opBuilder.addInput(m.asOutput());
    opBuilder.addInput(v.asOutput());
    opBuilder.addInput(beta.asOutput());
    opBuilder.addInput(gamma.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("variance_epsilon", varianceEpsilon);
    opBuilder.setAttr("scale_after_normalization", scaleAfterNormalization);
    return new BatchNormWithGlobalNormalization<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> result() {
    return result;
  }
  
  @Override
  public Output<T> asOutput() {
    return result;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BatchNormWithGlobalNormalization";
  
  private Output<T> result;
  
  private BatchNormWithGlobalNormalization(Operation operation) {
    super(operation);
    int outputIdx = 0;
    result = operation.output(outputIdx++);
  }
}
