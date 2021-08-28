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
 * Wraps the variadic XLA Reduce operator.
 * Semantics are documented at
 * https://www.tensorflow.org/performance/xla/operation_semantics#variadic_reduce.
 *
 * @param <T> data type for {@code output} output
 */
@Operator(
    group = "xla"
)
public final class XlaVariadicReduce<T extends TType> extends RawOp implements Iterable<Operand<T>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaVariadicReduce";

  private List<Output<T>> output;

  @SuppressWarnings("unchecked")
  private XlaVariadicReduce(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new XlaVariadicReduce operation.
   *
   * @param scope current scope
   * @param input the input tensor(s)
   * @param initValue scalar initial value(s) for the reduction
   * @param dimensionsToReduce dimension numbers over which to reduce
   * @param reducer a reducer function to apply
   * @param <T> data type for {@code XlaVariadicReduce} output and operands
   * @return a new instance of XlaVariadicReduce
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> XlaVariadicReduce<T> create(Scope scope,
      Iterable<Operand<T>> input, Iterable<Operand<T>> initValue, List<Long> dimensionsToReduce,
      ConcreteFunction reducer) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaVariadicReduce");
    opBuilder.addInputList(Operands.asOutputs(input));
    opBuilder.addInputList(Operands.asOutputs(initValue));
    long[] dimensionsToReduceArray = new long[dimensionsToReduce.size()];
    for (int i = 0 ; i < dimensionsToReduceArray.length ; i++) {
      dimensionsToReduceArray[i] = dimensionsToReduce.get(i);
    }
    opBuilder.setAttr("dimensions_to_reduce", dimensionsToReduceArray);
    opBuilder.setAttr("reducer", reducer);
    return new XlaVariadicReduce<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public List<Output<T>> output() {
    return output;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<T>> iterator() {
    return (Iterator) output.iterator();
  }
}
