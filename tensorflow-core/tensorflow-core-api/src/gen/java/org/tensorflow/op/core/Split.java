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
 * Splits a tensor into {@code num_split} tensors along one dimension.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Split.OP_NAME,
    inputsClass = Split.Inputs.class
)
@Operator
public final class Split<T extends TType> extends RawOp implements Iterable<Operand<T>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Split";

  private List<Output<T>> output;

  @SuppressWarnings("unchecked")
  public Split(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList((Output<T>[]) operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new Split operation.
   *
   * @param scope current scope
   * @param axis 0-D.  The dimension along which to split.  Must be in the range
   * {@code [-rank(value), rank(value))}.
   * @param value The tensor to split.
   * @param numSplit The number of ways to split.  Must evenly divide
   * {@code value.shape[split_dim]}.
   * @param <T> data type for {@code Split} output and operands
   * @return a new instance of Split
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Split<T> create(Scope scope, Operand<TInt32> axis,
      Operand<T> value, Long numSplit) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Split");
    opBuilder.addInput(axis.asOutput());
    opBuilder.addInput(value.asOutput());
    opBuilder.setAttr("num_split", numSplit);
    return new Split<>(opBuilder.build());
  }

  /**
   * Gets output.
   * They are identically shaped tensors, whose shape matches that of {@code value}
   * except along {@code axis}, where their sizes are
   * {@code values.shape[split_dim] / num_split}.
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

  @OpInputsMetadata(
      outputsClass = Split.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Split<T>> {
    /**
     * 0-D.  The dimension along which to split.  Must be in the range
     * {@code [-rank(value), rank(value))}.
     */
    public final Operand<TInt32> axis;

    /**
     * The tensor to split.
     */
    public final Operand<T> value;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Split<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      axis = (Operand<TInt32>) op.input(inputIndex++);
      value = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
