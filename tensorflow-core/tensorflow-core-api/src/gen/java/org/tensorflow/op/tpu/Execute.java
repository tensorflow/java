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

package org.tensorflow.op.tpu;

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
 * Op that loads and executes a TPU program on a TPU device.
 * For the internal use of the distributed TPU compiler.
 */
@OpMetadata(
    opType = Execute.OP_NAME,
    inputsClass = Execute.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class Execute extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUExecute";

  private List<Output<?>> results;

  @SuppressWarnings("unchecked")
  public Execute(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int resultsLength = operation.outputListLength("results");
    results = Arrays.asList(operation.outputList(outputIdx, resultsLength));
    outputIdx += resultsLength;
  }

  /**
   * Factory method to create a class wrapping a new TPUExecute operation.
   *
   * @param scope current scope
   * @param args The args value
   * @param key The key value
   * @param Tresults The value of the Tresults attribute
   * @return a new instance of Execute
   */
  @Endpoint(
      describeByClass = true
  )
  public static Execute create(Scope scope, Iterable<Operand<?>> args, Operand<TString> key,
      List<Class<? extends TType>> Tresults) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Execute");
    opBuilder.addInputList(Operands.asOutputs(args));
    opBuilder.addInput(key.asOutput());
    opBuilder.setAttr("Tresults", Operands.toDataTypes(Tresults));
    return new Execute(opBuilder.build());
  }

  /**
   * Gets results.
   *
   * @return results.
   */
  public List<Output<?>> results() {
    return results;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) results.iterator();
  }

  @OpInputsMetadata(
      outputsClass = Execute.class
  )
  public static class Inputs extends RawOpInputs<Execute> {
    /**
     * The args input
     */
    public final Iterable<Operand<?>> args;

    /**
     * The key input
     */
    public final Operand<TString> key;

    /**
     * The Targs attribute
     */
    public final DataType[] Targs;

    /**
     * The Tresults attribute
     */
    public final DataType[] Tresults;

    public Inputs(GraphOperation op) {
      super(new Execute(op), op, Arrays.asList("Targs", "Tresults"));
      int inputIndex = 0;
      int argsLength = op.inputListLength("args");
      args = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, argsLength));
      inputIndex += argsLength;
      key = (Operand<TString>) op.input(inputIndex++);
      Targs = op.attributes().getAttrTypeList("Targs");
      Tresults = op.attributes().getAttrTypeList("Tresults");
    }
  }
}
