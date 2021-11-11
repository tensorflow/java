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

package org.tensorflow.op.linalg;

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
 * Tensor contraction according to Einstein summation convention.
 * Implements generalized Tensor contraction and reduction. Each input Tensor must
 * have a corresponding input subscript appearing in the comma-separated left-hand
 * side of the equation. The right-hand side of the equation consists of the
 * output subscript. The input subscripts and the output subscript should consist
 * of zero or more named axis labels and at most one ellipsis ({@code ...}).
 * <p>The named axis labels may be any single character other than those having
 * special meaning, namely {@code ,.->}. The behavior of this Op is undefined if it
 * receives an ill-formatted equation; since the validation is done at
 * graph-building time, we omit format validation checks at runtime.
 * <p>Note: This Op is <em>not</em> intended to be called by the user; instead users should
 * call {@code tf.einsum} directly. It is a hidden Op used by {@code tf.einsum}.
 * <p>Operations are applied to the input(s) according to the following rules:
 * <p>(a) Generalized Diagonals: For input dimensions corresponding to axis labels
 * appearing more than once in the same input subscript, we take the
 * generalized ({@code k}-dimensional) diagonal.
 * For example, in the equation {@code iii->i} with input shape {@code [3, 3, 3]}, the
 * generalized diagonal would consist of {@code 3} elements at indices {@code (0, 0, 0)},
 * {@code (1, 1, 1)} and {@code (2, 2, 2)} to create a Tensor of shape {@code [3]}.
 * <p>(b) Reduction: Axes corresponding to labels appearing only in one input
 * subscript but not in the output subscript are summed over prior to Tensor
 * contraction.
 * For example, in the equation {@code ab,bc->b}, the axis labels {@code a} and {@code c} are
 * the reduction axis labels.
 * <p>(c) Batch Dimensions: Axes corresponding to labels appearing in each of the
 * input subscripts and also in the output subscript make up the batch
 * dimensions in Tensor contraction. Unnamed axis labels corresponding to
 * ellipsis ({@code ...}) also correspond to batch dimensions.
 * For example, for the equation denoting batch matrix multiplication,
 * {@code bij,bjk->bik}, the axis label {@code b} corresponds to a batch dimension.
 * <p>(d) Contraction: In case of binary einsum, axes corresponding to labels
 * appearing in two different inputs (and not in the output) are contracted
 * against each other.
 * Considering the batch matrix multiplication equation again
 * ({@code bij,bjk->bik}), the contracted axis label is {@code j}.
 * <p>(e) Expand Diagonal: If the output subscripts contain repeated (explicit) axis
 * labels, the opposite operation of (a) is applied. For example, in the
 * equation {@code i->iii}, and input shape {@code [3]}, the output of shape {@code [3, 3, 3]}
 * are all zeros, except for the (generalized) diagonal which is populated
 * with values from the input.
 * Note: This operation is not supported by {@code np.einsum} or {@code tf.einsum}; it is
 * provided to enable computing the symbolic gradient of {@code tf.einsum}.
 * <p>The output subscripts must contain only labels appearing in at least one of the
 * input subscripts. Furthermore, all dimensions mapping to the same axis label
 * must be equal.
 * <p>Any of the input and output subscripts may contain at most a single ellipsis
 * ({@code ...}). These ellipsis are mapped against dimensions not corresponding to any
 * named axis label. If two inputs contain ellipsis, then they are broadcasted
 * according to standard NumPy broadcasting
 *  <a href="http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html">rules</a> .
 * <p>The broadcasted dimensions are placed in the corresponding location of the
 * ellipsis in the output subscript. If the broadcasted dimensions are non-empty
 * and the output subscripts do not contain ellipsis, then an InvalidArgument error
 * is raised.
 * <p>{@literal @}compatibility(numpy)<br>
 * Similar to  <a href="https://docs.scipy.org/doc/numpy/reference/generated/numpy.einsum.html">{@code numpy.einsum}</a> .
 * <p>Comparison with {@code numpy.einsum}:
 * <ul>
 * <li>This Op only supports unary and binary forms of {@code numpy.einsum}.</li>
 * <li>This Op does not support implicit form. (i.e. equations without {@code ->}).</li>
 * <li>This Op also supports repeated indices in the output subscript, which is not
 * supported by {@code numpy.einsum}.
 * <br>{@literal @}end_compatibility</li>
 * </ul>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Einsum.OP_NAME,
    inputsClass = Einsum.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class Einsum<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Einsum";

  private Output<T> output;

  public Einsum(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Einsum operation.
   *
   * @param scope current scope
   * @param inputs List of 1 or 2 Tensors.
   * @param equation String describing the Einstein Summation operation; in the format of np.einsum.
   * @param <T> data type for {@code Einsum} output and operands
   * @return a new instance of Einsum
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Einsum<T> create(Scope scope, Iterable<Operand<T>> inputs,
      String equation) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Einsum");
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder.setAttr("equation", equation);
    return new Einsum<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Output Tensor with shape depending upon {@code equation}.
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
      outputsClass = Einsum.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Einsum<T>> {
    /**
     * List of 1 or 2 Tensors.
     */
    public final Iterable<Operand<T>> inputs;

    /**
     * String describing the Einstein Summation operation; in the format of np.einsum.
     */
    public final String equation;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Einsum<>(op), op, Arrays.asList("equation", "T"));
      int inputIndex = 0;
      int inputsLength = op.inputListLength("inputs");
      inputs = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, inputsLength));
      inputIndex += inputsLength;
      equation = op.attributes().getAttrString("equation");
      T = op.attributes().getAttrType("T");
    }
  }
}
