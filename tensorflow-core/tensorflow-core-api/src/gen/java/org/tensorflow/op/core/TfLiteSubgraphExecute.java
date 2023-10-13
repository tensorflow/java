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

package org.tensorflow.op.core;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The TfLiteSubgraphExecute operation
 */
@OpMetadata(
    opType = TfLiteSubgraphExecute.OP_NAME,
    inputsClass = TfLiteSubgraphExecute.Inputs.class
)
@Operator
public final class TfLiteSubgraphExecute extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TfLiteSubgraphExecute";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  public TfLiteSubgraphExecute(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new TfLiteSubgraphExecute operation.
   *
   * @param scope current scope
   * @param subgraphKey The subgraphKey value
   * @param args The args value
   * @param Tout The value of the Tout attribute
   * @return a new instance of TfLiteSubgraphExecute
   */
  @Endpoint(
      describeByClass = true
  )
  public static TfLiteSubgraphExecute create(Scope scope, Operand<TString> subgraphKey,
      Iterable<Operand<?>> args, List<Class<? extends TType>> Tout) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TfLiteSubgraphExecute");
    opBuilder.addInput(subgraphKey.asOutput());
    opBuilder.addInputList(Operands.asOutputs(args));
    opBuilder.setAttr("Tout", Operands.toDataTypes(Tout));
    return new TfLiteSubgraphExecute(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public List<Output<?>> output() {
    return output;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) output.iterator();
  }

  @OpInputsMetadata(
      outputsClass = TfLiteSubgraphExecute.class
  )
  public static class Inputs extends RawOpInputs<TfLiteSubgraphExecute> {
    /**
     * The subgraphKey input
     */
    public final Operand<TString> subgraphKey;

    /**
     * The args input
     */
    public final Iterable<Operand<?>> args;

    /**
     * The Tin attribute
     */
    public final DataType[] Tin;

    /**
     * The Tout attribute
     */
    public final DataType[] Tout;

    public Inputs(GraphOperation op) {
      super(new TfLiteSubgraphExecute(op), op, Arrays.asList("Tin", "Tout"));
      int inputIndex = 0;
      subgraphKey = (Operand<TString>) op.input(inputIndex++);
      int argsLength = op.inputListLength("args");
      args = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, argsLength));
      inputIndex += argsLength;
      Tin = op.attributes().getAttrTypeList("Tin");
      Tout = op.attributes().getAttrTypeList("Tout");
    }
  }
}
