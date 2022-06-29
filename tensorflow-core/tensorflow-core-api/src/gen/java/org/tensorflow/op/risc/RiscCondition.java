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

import java.util.Arrays;
import org.tensorflow.ConcreteFunction;
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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;

/**
 * The RiscCondition operation
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = RiscCondition.OP_NAME,
    inputsClass = RiscCondition.Inputs.class
)
public final class RiscCondition<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscCondition";

  private Output<U> output;

  public RiscCondition(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscCondition operation.
   *
   * @param scope current scope
   * @param pred The pred value
   * @param inputTrue The inputTrue value
   * @param inputFalse The inputFalse value
   * @param funcTrue The value of the funcTrue attribute
   * @param funcFalse The value of the funcFalse attribute
   * @param DstT The value of the DstT attribute
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

  @OpInputsMetadata(
      outputsClass = RiscCondition.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<RiscCondition<?>> {
    /**
     * The pred input
     */
    public final Operand<TBool> pred;

    /**
     * The inputTrue input
     */
    public final Operand<T> inputTrue;

    /**
     * The inputFalse input
     */
    public final Operand<T> inputFalse;

    /**
     * The SrcT attribute
     */
    public final DataType SrcT;

    /**
     * The DstT attribute
     */
    public final DataType DstT;

    public Inputs(GraphOperation op) {
      super(new RiscCondition<>(op), op, Arrays.asList("SrcT", "DstT"));
      int inputIndex = 0;
      pred = (Operand<TBool>) op.input(inputIndex++);
      inputTrue = (Operand<T>) op.input(inputIndex++);
      inputFalse = (Operand<T>) op.input(inputIndex++);
      SrcT = op.attributes().getAttrType("SrcT");
      DstT = op.attributes().getAttrType("DstT");
    }
  }
}
