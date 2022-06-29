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
 * Reverses specific dimensions of a tensor.
 * Given a {@code tensor}, and a {@code int32} tensor {@code axis} representing the set of
 * dimensions of {@code tensor} to reverse. This operation reverses each dimension
 * {@code i} for which there exists {@code j} s.t. {@code axis[j] == i}.
 * <p>{@code tensor} can have up to 8 dimensions. The number of dimensions specified
 * in {@code axis} may be 0 or more entries. If an index is specified more than
 * once, a InvalidArgument error is raised.
 * <p>For example:
 * <pre>
 * # tensor 't' is [[[[ 0,  1,  2,  3],
 * #                  [ 4,  5,  6,  7],
 * #                  [ 8,  9, 10, 11]],
 * #                 [[12, 13, 14, 15],
 * #                  [16, 17, 18, 19],
 * #                  [20, 21, 22, 23]]]]
 * # tensor 't' shape is [1, 2, 3, 4]
 *
 * # 'dims' is [3] or 'dims' is [-1]
 * reverse(t, dims) ==&gt; [[[[ 3,  2,  1,  0],
 *                         [ 7,  6,  5,  4],
 *                         [ 11, 10, 9, 8]],
 *                        [[15, 14, 13, 12],
 *                         [19, 18, 17, 16],
 *                         [23, 22, 21, 20]]]]
 *
 * # 'dims' is '[1]' (or 'dims' is '[-3]')
 * reverse(t, dims) ==&gt; [[[[12, 13, 14, 15],
 *                         [16, 17, 18, 19],
 *                         [20, 21, 22, 23]
 *                        [[ 0,  1,  2,  3],
 *                         [ 4,  5,  6,  7],
 *                         [ 8,  9, 10, 11]]]]
 *
 * # 'dims' is '[2]' (or 'dims' is '[-2]')
 * reverse(t, dims) ==&gt; [[[[8, 9, 10, 11],
 *                         [4, 5, 6, 7],
 *                         [0, 1, 2, 3]]
 *                        [[20, 21, 22, 23],
 *                         [16, 17, 18, 19],
 *                         [12, 13, 14, 15]]]]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Reverse.OP_NAME,
    inputsClass = Reverse.Inputs.class
)
@Operator
public final class Reverse<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ReverseV2";

  private Output<T> output;

  public Reverse(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ReverseV2 operation.
   *
   * @param scope current scope
   * @param tensor Up to 8-D.
   * @param axis 1-D. The indices of the dimensions to reverse. Must be in the range
   * {@code [-rank(tensor), rank(tensor))}.
   * @param <T> data type for {@code ReverseV2} output and operands
   * @return a new instance of Reverse
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Reverse<T> create(Scope scope, Operand<T> tensor,
      Operand<? extends TNumber> axis) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Reverse");
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(axis.asOutput());
    return new Reverse<>(opBuilder.build());
  }

  /**
   * Gets output.
   * The same shape as {@code tensor}.
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
      outputsClass = Reverse.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Reverse<T>> {
    /**
     * Up to 8-D.
     */
    public final Operand<T> tensor;

    /**
     * 1-D. The indices of the dimensions to reverse. Must be in the range
     * {@code [-rank(tensor), rank(tensor))}.
     */
    public final Operand<? extends TNumber> axis;

    /**
     * The Tidx attribute
     */
    public final DataType Tidx;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Reverse<>(op), op, Arrays.asList("Tidx", "T"));
      int inputIndex = 0;
      tensor = (Operand<T>) op.input(inputIndex++);
      axis = (Operand<? extends TNumber>) op.input(inputIndex++);
      Tidx = op.attributes().getAttrType("Tidx");
      T = op.attributes().getAttrType("T");
    }
  }
}
