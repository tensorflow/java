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

package org.tensorflow.op.core;

import java.util.Arrays;
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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * The TensorListConcatLists operation
 */
@OpMetadata(
    opType = TensorListConcatLists.OP_NAME,
    inputsClass = TensorListConcatLists.Inputs.class
)
@Operator
public final class TensorListConcatLists extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorListConcatLists";

  private Output<? extends TType> output;

  @SuppressWarnings("unchecked")
  public TensorListConcatLists(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorListConcatLists operation.
   *
   * @param scope current scope
   * @param inputA The inputA value
   * @param inputB The inputB value
   * @param elementDtype The value of the elementDtype attribute
   * @param <T> data type for {@code TensorListConcatLists} output and operands
   * @return a new instance of TensorListConcatLists
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TensorListConcatLists create(Scope scope,
      Operand<? extends TType> inputA, Operand<? extends TType> inputB, Class<T> elementDtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorListConcatLists");
    opBuilder.addInput(inputA.asOutput());
    opBuilder.addInput(inputB.asOutput());
    opBuilder.setAttr("element_dtype", Operands.toDataType(elementDtype));
    return new TensorListConcatLists(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<? extends TType> output() {
    return output;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) output;
  }

  @OpInputsMetadata(
      outputsClass = TensorListConcatLists.class
  )
  public static class Inputs extends RawOpInputs<TensorListConcatLists> {
    /**
     * The inputA input
     */
    public final Operand<? extends TType> inputA;

    /**
     * The inputB input
     */
    public final Operand<? extends TType> inputB;

    /**
     * The elementDtype attribute
     */
    public final DataType elementDtype;

    public Inputs(GraphOperation op) {
      super(new TensorListConcatLists(op), op, Arrays.asList("element_dtype"));
      int inputIndex = 0;
      inputA = (Operand<? extends TType>) op.input(inputIndex++);
      inputB = (Operand<? extends TType>) op.input(inputIndex++);
      elementDtype = op.attributes().getAttrType("element_dtype");
    }
  }
}
