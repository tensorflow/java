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
import java.util.Iterator;
import java.util.List;
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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Runs function {@code f} on a remote device indicated by {@code target}.
 */
@OpMetadata(
    opType = RemoteCall.OP_NAME,
    inputsClass = RemoteCall.Inputs.class
)
@Operator
public final class RemoteCall extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RemoteCall";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  public RemoteCall(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new RemoteCall operation.
   *
   * @param scope current scope
   * @param target A fully specified device name where we want to run the function.
   * @param args A list of arguments for the function.
   * @param Tout The type list for the return values.
   * @param f The function to run remotely.
   * @return a new instance of RemoteCall
   */
  @Endpoint(
      describeByClass = true
  )
  public static RemoteCall create(Scope scope, Operand<TString> target, Iterable<Operand<?>> args,
      List<Class<? extends TType>> Tout, ConcreteFunction f) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RemoteCall");
    opBuilder.addInput(target.asOutput());
    opBuilder.addInputList(Operands.asOutputs(args));
    opBuilder.setAttr("Tout", Operands.toDataTypes(Tout));
    opBuilder.setAttr("f", f);
    return new RemoteCall(opBuilder.build());
  }

  /**
   * Gets output.
   * A list of return values.
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
      outputsClass = RemoteCall.class
  )
  public static class Inputs extends RawOpInputs<RemoteCall> {
    /**
     * A fully specified device name where we want to run the function.
     */
    public final Operand<TString> target;

    /**
     * A list of arguments for the function.
     */
    public final Iterable<Operand<?>> args;

    /**
     * The type list for the arguments.
     */
    public final DataType[] Tin;

    /**
     * The type list for the return values.
     */
    public final DataType[] Tout;

    public Inputs(GraphOperation op) {
      super(new RemoteCall(op), op, Arrays.asList("Tin", "Tout"));
      int inputIndex = 0;
      target = (Operand<TString>) op.input(inputIndex++);
      int argsLength = op.inputListLength("args");
      args = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, argsLength));
      inputIndex += argsLength;
      Tin = op.attributes().getAttrTypeList("Tin");
      Tout = op.attributes().getAttrTypeList("Tout");
    }
  }
}
