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

package org.tensorflow.op.xla;

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
import org.tensorflow.types.family.TType;

/**
 * XLA Launch Op. For use by the XLA JIT only.
 */
@OpMetadata(
    opType = XlaLaunch.OP_NAME,
    inputsClass = XlaLaunch.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaLaunch extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaLaunch";

  private List<Output<?>> results;

  @SuppressWarnings("unchecked")
  public XlaLaunch(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int resultsLength = operation.outputListLength("results");
    results = Arrays.asList(operation.outputList(outputIdx, resultsLength));
    outputIdx += resultsLength;
  }

  /**
   * Factory method to create a class wrapping a new XlaLaunch operation.
   *
   * @param scope current scope
   * @param constants The constants value
   * @param args The args value
   * @param resources The resources value
   * @param Tresults The value of the Tresults attribute
   * @param function The value of the function attribute
   * @return a new instance of XlaLaunch
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaLaunch create(Scope scope, Iterable<Operand<?>> constants,
      Iterable<Operand<?>> args, Iterable<Operand<? extends TType>> resources,
      List<Class<? extends TType>> Tresults, ConcreteFunction function) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaLaunch");
    opBuilder.addInputList(Operands.asOutputs(constants));
    opBuilder.addInputList(Operands.asOutputs(args));
    opBuilder.addInputList(Operands.asOutputs(resources));
    opBuilder.setAttr("Tresults", Operands.toDataTypes(Tresults));
    opBuilder.setAttr("function", function);
    return new XlaLaunch(opBuilder.build());
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
      outputsClass = XlaLaunch.class
  )
  public static class Inputs extends RawOpInputs<XlaLaunch> {
    /**
     * The constants input
     */
    public final Iterable<Operand<?>> constants;

    /**
     * The args input
     */
    public final Iterable<Operand<?>> args;

    /**
     * The resources input
     */
    public final Iterable<Operand<? extends TType>> resources;

    /**
     * The Tconstants attribute
     */
    public final DataType[] Tconstants;

    /**
     * The Targs attribute
     */
    public final DataType[] Targs;

    /**
     * The Tresults attribute
     */
    public final DataType[] Tresults;

    public Inputs(GraphOperation op) {
      super(new XlaLaunch(op), op, Arrays.asList("Tconstants", "Targs", "Tresults"));
      int inputIndex = 0;
      int constantsLength = op.inputListLength("constants");
      constants = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, constantsLength));
      inputIndex += constantsLength;
      int argsLength = op.inputListLength("args");
      args = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, argsLength));
      inputIndex += argsLength;
      int resourcesLength = op.inputListLength("resources");
      resources = Arrays.asList((Operand<? extends TType>[]) op.inputList(inputIndex, resourcesLength));
      inputIndex += resourcesLength;
      Tconstants = op.attributes().getAttrTypeList("Tconstants");
      Targs = op.attributes().getAttrTypeList("Targs");
      Tresults = op.attributes().getAttrTypeList("Tresults");
    }
  }
}
