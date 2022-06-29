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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Computes gradients for the scaled exponential linear (Selu) operation.
 *
 * @param <T> data type for {@code backprops} output
 */
@OpMetadata(
    opType = SeluGrad.OP_NAME,
    inputsClass = SeluGrad.Inputs.class
)
public final class SeluGrad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SeluGrad";

  private Output<T> backprops;

  public SeluGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    backprops = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SeluGrad operation.
   *
   * @param scope current scope
   * @param gradients The backpropagated gradients to the corresponding Selu operation.
   * @param outputs The outputs of the corresponding Selu operation.
   * @param <T> data type for {@code SeluGrad} output and operands
   * @return a new instance of SeluGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> SeluGrad<T> create(Scope scope, Operand<T> gradients,
      Operand<T> outputs) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SeluGrad");
    opBuilder.addInput(gradients.asOutput());
    opBuilder.addInput(outputs.asOutput());
    return new SeluGrad<>(opBuilder.build());
  }

  /**
   * Gets backprops.
   * The gradients: {@code gradients * (outputs + scale * alpha)}
   * if outputs &lt; 0, {@code scale * gradients} otherwise.
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
      outputsClass = SeluGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<SeluGrad<T>> {
    /**
     * The backpropagated gradients to the corresponding Selu operation.
     */
    public final Operand<T> gradients;

    /**
     * The outputs of the corresponding Selu operation.
     */
    public final Operand<T> outputs;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SeluGrad<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      gradients = (Operand<T>) op.input(inputIndex++);
      outputs = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
