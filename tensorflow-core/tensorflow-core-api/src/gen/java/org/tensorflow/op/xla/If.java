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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * output = cond ? then_branch(inputs) : else_branch(inputs).
 */
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
  private If(Operation operation) {
    super(operation);
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
   * @param Tout the value of the Tout property
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
}
