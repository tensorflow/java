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
import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
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
 * Wraps the XLA Reduce operator, documented at
 * https://www.tensorflow.org/performance/xla/operation_semantics#reduce .
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Reduce.OP_NAME,
    inputsClass = Reduce.Inputs.class
)
@Operator(
    group = "xla"
)
public final class Reduce<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaReduce";

  private Output<T> output;

  public Reduce(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaReduce operation.
   *
   * @param scope current scope
   * @param input the input tensor
   * @param initValue a scalar representing the initial value for the reduction
   * @param dimensionsToReduce dimension numbers over which to reduce
   * @param reducer a reducer function to apply
   * @param <T> data type for {@code XlaReduce} output and operands
   * @return a new instance of Reduce
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Reduce<T> create(Scope scope, Operand<T> input,
      Operand<T> initValue, List<Long> dimensionsToReduce, ConcreteFunction reducer) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Reduce");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(initValue.asOutput());
    long[] dimensionsToReduceArray = new long[dimensionsToReduce.size()];
    for (int i = 0 ; i < dimensionsToReduceArray.length ; i++) {
      dimensionsToReduceArray[i] = dimensionsToReduce.get(i);
    }
    opBuilder.setAttr("dimensions_to_reduce", dimensionsToReduceArray);
    opBuilder.setAttr("reducer", reducer);
    return new Reduce<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = Reduce.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Reduce<T>> {
    /**
     * the input tensor
     */
    public final Operand<T> input;

    /**
     * a scalar representing the initial value for the reduction
     */
    public final Operand<T> initValue;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * dimension numbers over which to reduce
     */
    public final long[] dimensionsToReduce;

    public Inputs(GraphOperation op) {
      super(new Reduce<>(op), op, Arrays.asList("T", "dimensions_to_reduce"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      initValue = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      dimensionsToReduce = op.attributes().getAttrIntList("dimensions_to_reduce");
    }
  }
}
