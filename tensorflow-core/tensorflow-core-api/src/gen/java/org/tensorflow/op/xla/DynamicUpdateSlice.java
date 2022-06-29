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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Wraps the XLA DynamicUpdateSlice operator, documented at
 * https://www.tensorflow.org/performance/xla/operation_semantics#dynamicupdateslice
 * .
 * <p>XlaDynamicUpdateSlice generates a result which is the value of the {@code input}
 * operand, with a slice update overwritten at {@code indices}. The shape of {@code update}
 * determines the shape of the sub-array of the result which is updated. The shape
 * of indices must be rank == 1, with dimension size equal to the rank of {@code input}.
 * <p>Handling of out-of-bounds slice indices is implementation-defined.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = DynamicUpdateSlice.OP_NAME,
    inputsClass = DynamicUpdateSlice.Inputs.class
)
@Operator(
    group = "xla"
)
public final class DynamicUpdateSlice<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaDynamicUpdateSlice";

  private Output<T> output;

  public DynamicUpdateSlice(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaDynamicUpdateSlice operation.
   *
   * @param scope current scope
   * @param input A {@code Tensor} of type T.
   * @param update A {@code Tensor} of type T. Same rank as {@code input}.
   * @param indices A vector of indices into {@code input}. Must have length equal to the rank of
   * {@code input}.
   * @param <T> data type for {@code XlaDynamicUpdateSlice} output and operands
   * @return a new instance of DynamicUpdateSlice
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> DynamicUpdateSlice<T> create(Scope scope, Operand<T> input,
      Operand<T> update, Operand<? extends TNumber> indices) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DynamicUpdateSlice");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(update.asOutput());
    opBuilder.addInput(indices.asOutput());
    return new DynamicUpdateSlice<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A {@code Tensor} of type T.
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
      outputsClass = DynamicUpdateSlice.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<DynamicUpdateSlice<T>> {
    /**
     * A {@code Tensor} of type T.
     */
    public final Operand<T> input;

    /**
     * A {@code Tensor} of type T. Same rank as {@code input}.
     */
    public final Operand<T> update;

    /**
     * A vector of indices into {@code input}. Must have length equal to the rank of
     * {@code input}.
     */
    public final Operand<? extends TNumber> indices;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    public Inputs(GraphOperation op) {
      super(new DynamicUpdateSlice<>(op), op, Arrays.asList("T", "Tindices"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      update = (Operand<T>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
