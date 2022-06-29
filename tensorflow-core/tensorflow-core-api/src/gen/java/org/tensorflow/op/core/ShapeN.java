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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Returns shape of tensors.
 * This operation returns N 1-D integer tensors representing shape of {@code input[i]s}.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = ShapeN.OP_NAME,
    inputsClass = ShapeN.Inputs.class
)
@Operator
public final class ShapeN<U extends TNumber> extends RawOp implements Iterable<Operand<U>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ShapeN";

  private List<Output<U>> output;

  @SuppressWarnings("unchecked")
  public ShapeN(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList((Output<U>[]) operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new ShapeN operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param outType The value of the outType attribute
   * @param <U> data type for {@code ShapeN} output and operands
   * @return a new instance of ShapeN
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> ShapeN<U> create(Scope scope,
      Iterable<Operand<? extends TType>> input, Class<U> outType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ShapeN");
    opBuilder.addInputList(Operands.asOutputs(input));
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new ShapeN<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new ShapeN operation, with the default output types.
   *
   * @param scope current scope
   * @param input The input value
   * @return a new instance of ShapeN, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static ShapeN<TInt32> create(Scope scope, Iterable<Operand<? extends TType>> input) {
    return create(scope, input, TInt32.class);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public List<Output<U>> output() {
    return output;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<U>> iterator() {
    return (Iterator) output.iterator();
  }

  @OpInputsMetadata(
      outputsClass = ShapeN.class
  )
  public static class Inputs extends RawOpInputs<ShapeN<?>> {
    /**
     * The input input
     */
    public final Iterable<Operand<? extends TType>> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The outType attribute
     */
    public final DataType outType;

    public Inputs(GraphOperation op) {
      super(new ShapeN<>(op), op, Arrays.asList("T", "out_type"));
      int inputIndex = 0;
      int inputLength = op.inputListLength("input");
      input = Arrays.asList((Operand<? extends TType>[]) op.inputList(inputIndex, inputLength));
      inputIndex += inputLength;
      T = op.attributes().getAttrType("T");
      outType = op.attributes().getAttrType("out_type");
    }
  }
}
