/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
 * Computes softsign: {@code features / (abs(features) + 1)}.
 *
 * @param <T> data type for {@code activations} output
 */
@OpMetadata(
    opType = Softsign.OP_NAME,
    inputsClass = Softsign.Inputs.class
)
@Operator(
    group = "nn"
)
public final class Softsign<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Softsign";

  private Output<T> activations;

  public Softsign(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    activations = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Softsign operation.
   *
   * @param scope current scope
   * @param features The features value
   * @param <T> data type for {@code Softsign} output and operands
   * @return a new instance of Softsign
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Softsign<T> create(Scope scope, Operand<T> features) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Softsign");
    opBuilder.addInput(features.asOutput());
    return new Softsign<>(opBuilder.build());
  }

  /**
   * Gets activations.
   *
   * @return activations.
   */
  public Output<T> activations() {
    return activations;
  }

  @Override
  public Output<T> asOutput() {
    return activations;
  }

  @OpInputsMetadata(
      outputsClass = Softsign.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<Softsign<T>> {
    /**
     * The features input
     */
    public final Operand<T> features;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Softsign<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      features = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
