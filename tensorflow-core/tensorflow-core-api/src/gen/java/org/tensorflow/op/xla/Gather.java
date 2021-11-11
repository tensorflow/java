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
 * Wraps the XLA Gather operator documented at
 * https://www.tensorflow.org/xla/operation_semantics#gather
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Gather.OP_NAME,
    inputsClass = Gather.Inputs.class
)
@Operator(
    group = "xla"
)
public final class Gather<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaGather";

  private Output<T> output;

  public Gather(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaGather operation.
   *
   * @param scope current scope
   * @param operand The array we're gathering from.
   * @param startIndices Array containing the starting indices of the slices we gather.
   * @param sliceSizes slice_sizes[i] is the bounds for the slice on dimension i.
   * @param dimensionNumbers A serialized xla::GatherDimensionNumbers proto.
   * @param indicesAreSorted Boolean indicating if the indices are sorted.
   * @param <T> data type for {@code XlaGather} output and operands
   * @param <U> data type for {@code XlaGather} output and operands
   * @return a new instance of Gather
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TNumber> Gather<T> create(Scope scope,
      Operand<T> operand, Operand<U> startIndices, Operand<U> sliceSizes, String dimensionNumbers,
      Boolean indicesAreSorted) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Gather");
    opBuilder.addInput(operand.asOutput());
    opBuilder.addInput(startIndices.asOutput());
    opBuilder.addInput(sliceSizes.asOutput());
    opBuilder.setAttr("dimension_numbers", dimensionNumbers);
    opBuilder.setAttr("indices_are_sorted", indicesAreSorted);
    return new Gather<>(opBuilder.build());
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
      outputsClass = Gather.class
  )
  public static class Inputs<T extends TType, U extends TNumber> extends RawOpInputs<Gather<T>> {
    /**
     * The array we're gathering from.
     */
    public final Operand<T> operand;

    /**
     * Array containing the starting indices of the slices we gather.
     */
    public final Operand<U> startIndices;

    /**
     * slice_sizes[i] is the bounds for the slice on dimension i.
     */
    public final Operand<U> sliceSizes;

    /**
     * A serialized xla::GatherDimensionNumbers proto.
     */
    public final String dimensionNumbers;

    /**
     * Boolean indicating if the indices are sorted.
     */
    public final boolean indicesAreSorted;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    public Inputs(GraphOperation op) {
      super(new Gather<>(op), op, Arrays.asList("dimension_numbers", "indices_are_sorted", "T", "Tindices"));
      int inputIndex = 0;
      operand = (Operand<T>) op.input(inputIndex++);
      startIndices = (Operand<U>) op.input(inputIndex++);
      sliceSizes = (Operand<U>) op.input(inputIndex++);
      dimensionNumbers = op.attributes().getAttrString("dimension_numbers");
      indicesAreSorted = op.attributes().getAttrBool("indices_are_sorted");
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
