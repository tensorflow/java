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
 * Reshapes a tensor.
 * Given {@code tensor}, this operation returns a tensor that has the same values
 * as {@code tensor} with shape {@code shape}.
 * <p>If one component of 1-D tensor {@code shape} is the special value -1, the size of that
 * dimension is computed so that the total size remains constant.  In particular, a
 * {@code shape} of {@code [-1]} flattens into 1-D.  At most one component of {@code shape} may be
 * unknown.
 * <p>The {@code shape} must be 1-D and the operation returns a tensor with shape
 * {@code shape} filled with the values of {@code tensor}. In this case, the number of elements
 * implied by {@code shape} must be the same as the number of elements in {@code tensor}.
 * <p>It is an error if {@code shape} is not 1-D.
 * <p>For example:
 * <pre>
 * # tensor 't' is [1, 2, 3, 4, 5, 6, 7, 8, 9]
 * # tensor 't' has shape [9]
 * reshape(t, [3, 3]) ==&gt; [[1, 2, 3],
 *                         [4, 5, 6],
 *                         [7, 8, 9]]
 *
 * # tensor 't' is [[[1, 1], [2, 2]],
 * #                [[3, 3], [4, 4]]]
 * # tensor 't' has shape [2, 2, 2]
 * reshape(t, [2, 4]) ==&gt; [[1, 1, 2, 2],
 *                         [3, 3, 4, 4]]
 *
 * # tensor 't' is [[[1, 1, 1],
 * #                 [2, 2, 2]],
 * #                [[3, 3, 3],
 * #                 [4, 4, 4]],
 * #                [[5, 5, 5],
 * #                 [6, 6, 6]]]
 * # tensor 't' has shape [3, 2, 3]
 * # pass '[-1]' to flatten 't'
 * reshape(t, [-1]) ==&gt; [1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6]
 *
 * # -1 can also be used to infer the shape
 *
 * # -1 is inferred to be 9:
 * reshape(t, [2, -1]) ==&gt; [[1, 1, 1, 2, 2, 2, 3, 3, 3],
 *                          [4, 4, 4, 5, 5, 5, 6, 6, 6]]
 * # -1 is inferred to be 2:
 * reshape(t, [-1, 9]) ==&gt; [[1, 1, 1, 2, 2, 2, 3, 3, 3],
 *                          [4, 4, 4, 5, 5, 5, 6, 6, 6]]
 * # -1 is inferred to be 3:
 * reshape(t, [ 2, -1, 3]) ==&gt; [[[1, 1, 1],
 *                               [2, 2, 2],
 *                               [3, 3, 3]],
 *                              [[4, 4, 4],
 *                               [5, 5, 5],
 *                               [6, 6, 6]]]
 *
 * # tensor 't' is [7]
 * # shape `[]` reshapes to a scalar
 * reshape(t, []) ==&gt; 7
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Reshape.OP_NAME,
    inputsClass = Reshape.Inputs.class
)
@Operator
public final class Reshape<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Reshape";

  private Output<T> output;

  public Reshape(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Reshape operation.
   *
   * @param scope current scope
   * @param tensor The tensor value
   * @param shape Defines the shape of the output tensor.
   * @param <T> data type for {@code Reshape} output and operands
   * @return a new instance of Reshape
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Reshape<T> create(Scope scope, Operand<T> tensor,
      Operand<? extends TNumber> shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Reshape");
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(shape.asOutput());
    return new Reshape<>(opBuilder.build());
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
      outputsClass = Reshape.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Reshape<T>> {
    /**
     * The tensor input
     */
    public final Operand<T> tensor;

    /**
     * Defines the shape of the output tensor.
     */
    public final Operand<? extends TNumber> shape;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tshape attribute
     */
    public final DataType Tshape;

    public Inputs(GraphOperation op) {
      super(new Reshape<>(op), op, Arrays.asList("T", "Tshape"));
      int inputIndex = 0;
      tensor = (Operand<T>) op.input(inputIndex++);
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tshape = op.attributes().getAttrType("Tshape");
    }
  }
}
