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

package org.tensorflow.op.random;

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
 * Computes the derivative of a Gamma random sample w.r.t. {@code alpha}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RandomGammaGrad.OP_NAME,
    inputsClass = RandomGammaGrad.Inputs.class
)
public final class RandomGammaGrad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RandomGammaGrad";

  private Output<T> output;

  public RandomGammaGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RandomGammaGrad operation.
   *
   * @param scope current scope
   * @param alpha The alpha value
   * @param sample The sample value
   * @param <T> data type for {@code RandomGammaGrad} output and operands
   * @return a new instance of RandomGammaGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RandomGammaGrad<T> create(Scope scope, Operand<T> alpha,
      Operand<T> sample) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RandomGammaGrad");
    opBuilder.addInput(alpha.asOutput());
    opBuilder.addInput(sample.asOutput());
    return new RandomGammaGrad<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
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
      outputsClass = RandomGammaGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<RandomGammaGrad<T>> {
    /**
     * The alpha input
     */
    public final Operand<T> alpha;

    /**
     * The sample input
     */
    public final Operand<T> sample;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new RandomGammaGrad<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      alpha = (Operand<T>) op.input(inputIndex++);
      sample = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
