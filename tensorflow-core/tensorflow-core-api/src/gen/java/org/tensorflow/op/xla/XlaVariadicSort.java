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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Wraps the XLA Sort operator, documented at
 * https://www.tensorflow.org/performance/xla/operation_semantics#sort
 * .
 * <p>Sorts one or more tensors, with support for custom comparator, dimension, and
 * is_stable attributes.
 */
@OpMetadata(
    opType = XlaVariadicSort.OP_NAME,
    inputsClass = XlaVariadicSort.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaVariadicSort extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaVariadicSort";

  private List<Output<?>> outputs;

  @SuppressWarnings("unchecked")
  public XlaVariadicSort(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputsLength = operation.outputListLength("outputs");
    outputs = Arrays.asList(operation.outputList(outputIdx, outputsLength));
    outputIdx += outputsLength;
  }

  /**
   * Factory method to create a class wrapping a new XlaVariadicSort operation.
   *
   * @param scope current scope
   * @param inputs A list of {@code Tensor} of identical shape but possibly different types.
   * @param dimension The dimension along which to sort. Must be a compile-time constant.
   * @param comparator A comparator function to apply to 2*N scalars and returning a
   * boolean. N is the number of sort inputs. If you want to sort in ascending
   * order then the comparator should perform a less-than comparison.
   * @param isStable Whether to use stable sort.
   * @return a new instance of XlaVariadicSort
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaVariadicSort create(Scope scope, Iterable<Operand<?>> inputs,
      Operand<TInt32> dimension, ConcreteFunction comparator, Boolean isStable) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaVariadicSort");
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder.addInput(dimension.asOutput());
    opBuilder.setAttr("comparator", comparator);
    opBuilder.setAttr("is_stable", isStable);
    return new XlaVariadicSort(opBuilder.build());
  }

  /**
   * Gets outputs.
   * A list of {@code Tensor} of same shape and types as the {@code input}.
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
      outputsClass = XlaVariadicSort.class
  )
  public static class Inputs extends RawOpInputs<XlaVariadicSort> {
    /**
     * A list of {@code Tensor} of identical shape but possibly different types.
     */
    public final Iterable<Operand<?>> inputs;

    /**
     * The dimension along which to sort. Must be a compile-time constant.
     */
    public final Operand<TInt32> dimension;

    /**
     * The T attribute
     */
    public final DataType[] T;

    /**
     * Whether to use stable sort.
     */
    public final boolean isStable;

    public Inputs(GraphOperation op) {
      super(new XlaVariadicSort(op), op, Arrays.asList("T", "is_stable"));
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      dimension = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrTypeList("T");
      isStable = op.attributes().getAttrBool("is_stable");
    }
  }
}
