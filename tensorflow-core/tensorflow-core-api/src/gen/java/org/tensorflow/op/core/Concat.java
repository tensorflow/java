/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Concatenates tensors along one dimension.
 */
@OpMetadata(
    opType = Concat.OP_NAME,
    inputsClass = Concat.Inputs.class
)
@Operator
public final class Concat<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ConcatV2";

  private Output<T> output;

  public Concat(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ConcatV2 operation.
   *
   * @param scope current scope
   * @param values List of {@code N} Tensors to concatenate. Their ranks and types must match,
   * and their sizes must match in all dimensions except {@code concat_dim}.
   * @param axis 0-D.  The dimension along which to concatenate.  Must be in the
   * range [-rank(values), rank(values)).
   * @param <T> data type for {@code ConcatV2} output and operands
   * @return a new instance of Concat
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Concat<T> create(Scope scope, Iterable<Operand<T>> values,
      Operand<? extends TNumber> axis) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Concat");
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder.addInput(axis.asOutput());
    return new Concat<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A {@code Tensor} with the concatenation of values stacked along the
   * {@code concat_dim} dimension.  This tensor's shape matches that of {@code values} except
   * in {@code concat_dim} where it has the sum of the sizes.
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
      outputsClass = Concat.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Concat<T>> {
    /**
     * List of {@code N} Tensors to concatenate. Their ranks and types must match,
     * and their sizes must match in all dimensions except {@code concat_dim}.
     */
    public final Iterable<Operand<T>> values;

    /**
     * 0-D.  The dimension along which to concatenate.  Must be in the
     * range [-rank(values), rank(values)).
     */
    public final Operand<? extends TNumber> axis;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tidx attribute
     */
    public final DataType Tidx;

    public Inputs(GraphOperation op) {
      super(new Concat<>(op), op, Arrays.asList("T", "Tidx"));
      int inputIndex = 0;
      int valuesLength = op.inputListLength("values");
      values = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, valuesLength));
      inputIndex += valuesLength;
      axis = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tidx = op.attributes().getAttrType("Tidx");
    }
  }
}
