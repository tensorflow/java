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
 * Inserts a dimension of 1 into a tensor's shape.
 * Given a tensor {@code input}, this operation inserts a dimension of 1 at the
 * dimension index {@code axis} of {@code input}'s shape. The dimension index {@code axis} starts at
 * zero; if you specify a negative number for {@code axis} it is counted backward from
 * the end.
 * <p>This operation is useful if you want to add a batch dimension to a single
 * element. For example, if you have a single image of shape {@code [height, width, channels]}, you can make it a batch of 1 image with {@code expand_dims(image, 0)},
 * which will make the shape {@code [1, height, width, channels]}.
 * <p>Other examples:
 * <pre>
 * # 't' is a tensor of shape [2]
 * shape(expand_dims(t, 0)) ==&gt; [1, 2]
 * shape(expand_dims(t, 1)) ==&gt; [2, 1]
 * shape(expand_dims(t, -1)) ==&gt; [2, 1]
 *
 * # 't2' is a tensor of shape [2, 3, 5]
 * shape(expand_dims(t2, 0)) ==&gt; [1, 2, 3, 5]
 * shape(expand_dims(t2, 2)) ==&gt; [2, 3, 1, 5]
 * shape(expand_dims(t2, 3)) ==&gt; [2, 3, 5, 1]
 * </pre>
 * <p>This operation requires that:
 * <p>{@code -1-input.dims() <= dim <= input.dims()}
 * <p>This operation is related to {@code squeeze()}, which removes dimensions of
 * size 1.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = ExpandDims.OP_NAME,
    inputsClass = ExpandDims.Inputs.class
)
@Operator
public final class ExpandDims<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ExpandDims";

  private Output<T> output;

  public ExpandDims(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ExpandDims operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param axis 0-D (scalar). Specifies the dimension index at which to
   * expand the shape of {@code input}. Must be in the range
   * {@code [-rank(input) - 1, rank(input)]}.
   * @param <T> data type for {@code ExpandDims} output and operands
   * @return a new instance of ExpandDims
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ExpandDims<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> axis) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ExpandDims");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(axis.asOutput());
    return new ExpandDims<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Contains the same data as {@code input}, but its shape has an additional
   * dimension of size 1 added.
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
      outputsClass = ExpandDims.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<ExpandDims<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * 0-D (scalar). Specifies the dimension index at which to
     * expand the shape of {@code input}. Must be in the range
     * {@code [-rank(input) - 1, rank(input)]}.
     */
    public final Operand<? extends TNumber> axis;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tdim attribute
     */
    public final DataType Tdim;

    public Inputs(GraphOperation op) {
      super(new ExpandDims<>(op), op, Arrays.asList("T", "Tdim"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      axis = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tdim = op.attributes().getAttrType("Tdim");
    }
  }
}
