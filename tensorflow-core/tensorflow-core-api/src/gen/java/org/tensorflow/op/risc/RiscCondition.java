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

package org.tensorflow.op.risc;

import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;

/**
 * The RiscCondition operation
 *
 * @param <U> data type for {@code output} output
 */
public final class RiscCondition<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscCondition";

  private Output<U> output;

  private RiscCondition(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscCondition operation.
   *
   * @param scope current scope
   * @param pred the pred value
   * @param inputTrue the inputTrue value
   * @param inputFalse the inputFalse value
   * @param funcTrue the value of the funcTrue property
   * @param funcFalse the value of the funcFalse property
   * @param DstT the value of the DstT property
   * @param <U> data type for {@code RiscCondition} output and operands
   * @param <T> data type for {@code RiscCondition} output and operands
   * @return a new instance of RiscCondition
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber, T extends TNumber> RiscCondition<U> create(Scope scope,
      Operand<TBool> pred, Operand<T> inputTrue, Operand<T> inputFalse, ConcreteFunction funcTrue,
      ConcreteFunction funcFalse, Class<U> DstT) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscCondition");
    opBuilder.addInput(pred.asOutput());
    opBuilder.addInput(inputTrue.asOutput());
    opBuilder.addInput(inputFalse.asOutput());
    opBuilder.setAttr("func_true", funcTrue);
    opBuilder.setAttr("func_false", funcFalse);
    opBuilder.setAttr("DstT", Operands.toDataType(DstT));
    return new RiscCondition<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }
}
