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
 * output = input; While (Cond(output)) { output = Body(output) }
 */
@Operator(
    group = "xla"
)
public final class While extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaWhile";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  private While(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new XlaWhile operation.
   *
   * @param scope current scope
   * @param input A list of input tensors whose types are T.
   * @param cond A function takes 'input' and returns a tensor.  If the tensor is
   * a scalar of non-boolean, the scalar is converted to a boolean
   * according to the following rule: if the scalar is a numerical
   * value, non-zero means True and zero means False; if the scalar is
   * a string, non-empty means True and empty means False. If the
   * tensor is not a scalar, non-emptiness means True and False
   * otherwise.
   * @param body A function that takes a list of tensors and returns another
   * list of tensors. Both lists have the same types as specified by T.
   * @return a new instance of While
   */
  @Endpoint(
      describeByClass = true,
      name = "whileOp"
  )
  public static While create(Scope scope, Iterable<Operand<?>> input, ConcreteFunction cond,
      ConcreteFunction body) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "While");
    opBuilder.addInputList(Operands.asOutputs(input));
    opBuilder.setAttr("cond", cond);
    opBuilder.setAttr("body", body);
    return new While(opBuilder.build());
  }

  /**
   * Gets output.
   * A list of output tensors whose types are T.
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
