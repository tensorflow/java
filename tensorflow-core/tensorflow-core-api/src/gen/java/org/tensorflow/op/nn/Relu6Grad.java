/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Computes rectified linear 6 gradients for a Relu6 operation.
 */
@OpMetadata(
    opType = Relu6Grad.OP_NAME,
    inputsClass = Relu6Grad.Inputs.class
)
@Operator(
    group = "nn"
)
public final class Relu6Grad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Relu6Grad";

  private Output<T> backprops;

  public Relu6Grad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    backprops = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Relu6Grad operation.
   *
   * @param scope current scope
   * @param gradients The backpropagated gradients to the corresponding Relu6 operation.
   * @param features The features passed as input to the corresponding Relu6 operation, or
   * its output; using either one produces the same result.
   * @param <T> data type for {@code Relu6Grad} output and operands
   * @return a new instance of Relu6Grad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Relu6Grad<T> create(Scope scope, Operand<T> gradients,
      Operand<T> features) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Relu6Grad");
    opBuilder.addInput(gradients.asOutput());
    opBuilder.addInput(features.asOutput());
    return new Relu6Grad<>(opBuilder.build());
  }

  /**
   * Gets backprops.
   * The gradients:
   * {@code gradients * (features > 0) * (features < 6)}.
   * @return backprops.
   */
  public Output<T> backprops() {
    return backprops;
  }

  @Override
  public Output<T> asOutput() {
    return backprops;
  }

  @OpInputsMetadata(
      outputsClass = Relu6Grad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<Relu6Grad<T>> {
    /**
     * The backpropagated gradients to the corresponding Relu6 operation.
     */
    public final Operand<T> gradients;

    /**
     * The features passed as input to the corresponding Relu6 operation, or
     * its output; using either one produces the same result.
     */
    public final Operand<T> features;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Relu6Grad<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      gradients = (Operand<T>) op.input(inputIndex++);
      features = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
