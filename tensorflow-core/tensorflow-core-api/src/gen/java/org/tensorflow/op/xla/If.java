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
 * output = cond ? then_branch(inputs) : else_branch(inputs).
 */
@OpMetadata(
    opType = If.OP_NAME,
    inputsClass = If.Inputs.class
)
@Operator(
    group = "xla"
)
public final class If extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaIf";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  public If(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new XlaIf operation.
   *
   * @param scope current scope
   * @param cond A boolean scalar.
   * @param inputs A list of input tensors.
   * @param thenBranch A function takes 'inputs' and returns a list of tensors,
   * whose types are the same as what else_branch returns.
   * @param elseBranch A function takes 'inputs' and returns a list of tensors.
   * whose types are the same as what then_branch returns.
   * @param Tout The value of the Tout attribute
   * @return a new instance of If
   */
  @Endpoint(
      describeByClass = true,
      name = "ifOp"
  )
  public static If create(Scope scope, Operand<? extends TType> cond, Iterable<Operand<?>> inputs,
      ConcreteFunction thenBranch, ConcreteFunction elseBranch, List<Class<? extends TType>> Tout) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "If");
    opBuilder.addInput(cond.asOutput());
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder.setAttr("then_branch", thenBranch);
    opBuilder.setAttr("else_branch", elseBranch);
    opBuilder.setAttr("Tout", Operands.toDataTypes(Tout));
    return new If(opBuilder.build());
  }

  /**
   * Gets output.
   * A list of tensors returned by either then_branch(inputs) or
   * else_branch(inputs). The input shapes of the then_branch and
   * else_branch must match.
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
      outputsClass = If.class
  )
  public static class Inputs extends RawOpInputs<If> {
    /**
     * A boolean scalar.
     */
    public final Operand<? extends TType> cond;

    /**
     * A list of input tensors.
     */
    public final Iterable<Operand<?>> inputs;

    /**
     * The Tcond attribute
     */
    public final DataType Tcond;

    /**
     * The Tin attribute
     */
    public final DataType[] Tin;

    /**
     * The Tout attribute
     */
    public final DataType[] Tout;

    public Inputs(GraphOperation op) {
      super(new If(op), op, Arrays.asList("Tcond", "Tin", "Tout"));
      int inputIndex = 0;
      cond = (Operand<? extends TType>) op.input(inputIndex++);
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      Tcond = op.attributes().getAttrType("Tcond");
      Tin = op.attributes().getAttrTypeList("Tin");
      Tout = op.attributes().getAttrTypeList("Tout");
    }
  }
}
