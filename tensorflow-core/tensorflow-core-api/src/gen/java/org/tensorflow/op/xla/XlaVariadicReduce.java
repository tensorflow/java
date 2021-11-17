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
 * Wraps the variadic XLA Reduce operator.
 * Semantics are documented at
 * https://www.tensorflow.org/performance/xla/operation_semantics#variadic_reduce.
 * <p>This is an expanded version of XlaVariadicReduce, with support for
 * operands of different dtypes, and improved shape inference.
 */
@OpMetadata(
    opType = XlaVariadicReduce.OP_NAME,
    inputsClass = XlaVariadicReduce.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaVariadicReduce extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaVariadicReduceV2";

  private List<Output<?>> outputs;

  @SuppressWarnings("unchecked")
  public XlaVariadicReduce(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputsLength = operation.outputListLength("outputs");
    outputs = Arrays.asList(operation.outputList(outputIdx, outputsLength));
    outputIdx += outputsLength;
  }

  /**
   * Factory method to create a class wrapping a new XlaVariadicReduceV2 operation.
   *
   * @param scope current scope
   * @param inputs the input tensor(s)
   * @param initValues scalar initial value(s) for the reduction
   * @param dimensionsToReduce dimension numbers over which to reduce
   * @param reducer a reducer function to apply
   * @return a new instance of XlaVariadicReduce
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaVariadicReduce create(Scope scope, Iterable<Operand<?>> inputs,
      Iterable<Operand<?>> initValues, List<Long> dimensionsToReduce, ConcreteFunction reducer) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaVariadicReduce");
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder.addInputList(Operands.asOutputs(initValues));
    long[] dimensionsToReduceArray = new long[dimensionsToReduce.size()];
    for (int i = 0 ; i < dimensionsToReduceArray.length ; i++) {
      dimensionsToReduceArray[i] = dimensionsToReduce.get(i);
    }
    opBuilder.setAttr("dimensions_to_reduce", dimensionsToReduceArray);
    opBuilder.setAttr("reducer", reducer);
    return new XlaVariadicReduce(opBuilder.build());
  }

  /**
   * Gets outputs.
   *
   * @return outputs.
   */
  public List<Output<?>> outputs() {
    return outputs;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) outputs.iterator();
  }

  @OpInputsMetadata(
      outputsClass = XlaVariadicReduce.class
  )
  public static class Inputs extends RawOpInputs<XlaVariadicReduce> {
    /**
     * the input tensor(s)
     */
    public final Iterable<Operand<?>> inputs;

    /**
     * scalar initial value(s) for the reduction
     */
    public final Iterable<Operand<?>> initValues;

    /**
     * The T attribute
     */
    public final DataType[] T;

    /**
     * dimension numbers over which to reduce
     */
    public final long[] dimensionsToReduce;

    public Inputs(GraphOperation op) {
      super(new XlaVariadicReduce(op), op, Arrays.asList("T", "dimensions_to_reduce"));
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      int initValuesLength = op.inputListLength("init_values");
      initValues = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, initValuesLength));
      inputIndex += initValuesLength;
      T = op.attributes().getAttrTypeList("T");
      dimensionsToReduce = op.attributes().getAttrIntList("dimensions_to_reduce");
    }
  }
}
