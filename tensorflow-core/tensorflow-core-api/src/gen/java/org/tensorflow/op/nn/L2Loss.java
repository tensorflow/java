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
 * L2 Loss.
 * Computes half the L2 norm of a tensor without the {@code sqrt}:
 * <pre>
 * output = sum(t ** 2) / 2
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = L2Loss.OP_NAME,
    inputsClass = L2Loss.Inputs.class
)
@Operator(
    group = "nn"
)
public final class L2Loss<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "L2Loss";

  private Output<T> output;

  public L2Loss(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new L2Loss operation.
   *
   * @param scope current scope
   * @param t Typically 2-D, but may have any dimensions.
   * @param <T> data type for {@code L2Loss} output and operands
   * @return a new instance of L2Loss
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> L2Loss<T> create(Scope scope, Operand<T> t) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "L2Loss");
    opBuilder.addInput(t.asOutput());
    return new L2Loss<>(opBuilder.build());
  }

  /**
   * Gets output.
   * 0-D.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = L2Loss.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<L2Loss<T>> {
    /**
     * Typically 2-D, but may have any dimensions.
     */
    public final Operand<T> t;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new L2Loss<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      t = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
