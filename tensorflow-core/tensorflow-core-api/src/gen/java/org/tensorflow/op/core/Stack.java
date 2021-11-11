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
 * Packs a list of {@code N} rank-{@code R} tensors into one rank-{@code (R+1)} tensor.
 * Packs the {@code N} tensors in {@code values} into a tensor with rank one higher than each
 * tensor in {@code values}, by packing them along the {@code axis} dimension.
 * Given a list of tensors of shape {@code (A, B, C)};
 * <p>if {@code axis == 0} then the {@code output} tensor will have the shape {@code (N, A, B, C)}.
 * if {@code axis == 1} then the {@code output} tensor will have the shape {@code (A, N, B, C)}.
 * Etc.
 * <p>For example:
 * <pre>
 * # 'x' is [1, 4]
 * # 'y' is [2, 5]
 * # 'z' is [3, 6]
 * pack([x, y, z]) =&gt; [[1, 4], [2, 5], [3, 6]]  # Pack along first dim.
 * pack([x, y, z], axis=1) =&gt; [[1, 2, 3], [4, 5, 6]]
 * </pre>
 * <p>This is the opposite of {@code unpack}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Stack.OP_NAME,
    inputsClass = Stack.Inputs.class
)
@Operator
public final class Stack<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Pack";

  private Output<T> output;

  public Stack(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Pack operation.
   *
   * @param scope current scope
   * @param values Must be of same shape and type.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Pack} output and operands
   * @return a new instance of Stack
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Stack<T> create(Scope scope, Iterable<Operand<T>> values,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Stack");
    opBuilder.addInputList(Operands.asOutputs(values));
    if (options != null) {
      for (Options opts : options) {
        if (opts.axis != null) {
          opBuilder.setAttr("axis", opts.axis);
        }
      }
    }
    return new Stack<>(opBuilder.build());
  }

  /**
   * Sets the axis option.
   *
   * @param axis Dimension along which to pack.  Negative values wrap around, so the
   * valid range is {@code [-(R+1), R+1)}.
   * @return this Options instance.
   */
  public static Options axis(Long axis) {
    return new Options().axis(axis);
  }

  /**
   * Gets output.
   * The packed tensor.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.Stack}
   */
  public static class Options {
    private Long axis;

    private Options() {
    }

    /**
     * Sets the axis option.
     *
     * @param axis Dimension along which to pack.  Negative values wrap around, so the
     * valid range is {@code [-(R+1), R+1)}.
     * @return this Options instance.
     */
    public Options axis(Long axis) {
      this.axis = axis;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Stack.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Stack<T>> {
    /**
     * Must be of same shape and type.
     */
    public final Iterable<Operand<T>> values;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Dimension along which to pack.  Negative values wrap around, so the
     * valid range is `[-(R+1), R+1)`.
     */
    public final long axis;

    public Inputs(GraphOperation op) {
      super(new Stack<>(op), op, Arrays.asList("T", "axis"));
      int inputIndex = 0;
      int valuesLength = op.inputListLength("values");
      values = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, valuesLength));
      inputIndex += valuesLength;
      T = op.attributes().getAttrType("T");
      axis = op.attributes().getAttrInt("axis");
    }
  }
}
