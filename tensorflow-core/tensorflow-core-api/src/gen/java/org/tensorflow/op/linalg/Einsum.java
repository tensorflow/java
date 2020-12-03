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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Tensor contraction according to Einstein summation convention.
 * <p>
 * Implements generalized Tensor contraction and reduction. Each input Tensor must
 * have a corresponding input subscript appearing in the comma-separated left-hand
 * side of the equation. The right-hand side of the equation consists of the
 * output subscript. The input subscripts and the output subscript should consist
 * of zero or more named axis labels and at most one ellipsis (`...`).
 * <p>
 * The named axis labels may be any single character other than those having
 * special meaning, namely `,.->`. The behavior of this Op is undefined if it
 * receives an ill-formatted equation; since the validation is done at
 * graph-building time, we omit format validation checks at runtime.
 * <p>
 * Note: This Op is <i>not</i> intended to be called by the user; instead users should
 * call `tf.einsum` directly. It is a hidden Op used by `tf.einsum`.
 * <p>
 * Operations are applied to the input(s) according to the following rules:
 * <p>
 *  (a) Generalized Diagonals: For input dimensions corresponding to axis labels
 *      appearing more than once in the same input subscript, we take the
 *      generalized (`k`-dimensional) diagonal.
 *      For example, in the equation `iii->i` with input shape `[3, 3, 3]`, the
 *      generalized diagonal would consist of `3` elements at indices `(0, 0, 0)`,
 *      `(1, 1, 1)` and `(2, 2, 2)` to create a Tensor of shape `[3]`.
 * <p>
 *  (b) Reduction: Axes corresponding to labels appearing only in one input
 *      subscript but not in the output subscript are summed over prior to Tensor
 *      contraction.
 *      For example, in the equation `ab,bc->b`, the axis labels `a` and `c` are
 *      the reduction axis labels.
 * <p>
 *  (c) Batch Dimensions: Axes corresponding to labels appearing in each of the
 *      input subscripts and also in the output subscript make up the batch
 *      dimensions in Tensor contraction. Unnamed axis labels corresponding to
 *      ellipsis (`...`) also correspond to batch dimensions.
 *      For example, for the equation denoting batch matrix multiplication,
 *      `bij,bjk->bik`, the axis label `b` corresponds to a batch dimension.
 * <p>
 *  (d) Contraction: In case of binary einsum, axes corresponding to labels
 *      appearing in two different inputs (and not in the output) are contracted
 *      against each other.
 *      Considering the batch matrix multiplication equation again
 *      (`bij,bjk->bik`), the contracted axis label is `j`.
 * <p>
 *  (e) Expand Diagonal: If the output subscripts contain repeated (explicit) axis
 *      labels, the opposite operation of (a) is applied. For example, in the
 *      equation `i->iii`, and input shape `[3]`, the output of shape `[3, 3, 3]`
 *      are all zeros, except for the (generalized) diagonal which is populated
 *      with values from the input.
 *      Note: This operation is not supported by `np.einsum` or `tf.einsum`; it is
 *      provided to enable computing the symbolic gradient of `tf.einsum`.
 * <p>
 * The output subscripts must contain only labels appearing in at least one of the
 * input subscripts. Furthermore, all dimensions mapping to the same axis label
 * must be equal.
 * <p>
 * Any of the input and output subscripts may contain at most a single ellipsis
 * (`...`). These ellipsis are mapped against dimensions not corresponding to any
 * named axis label. If two inputs contain ellipsis, then they are broadcasted
 * according to standard NumPy broadcasting
 * [rules](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html).
 * <p>
 * The broadcasted dimensions are placed in the corresponding location of the
 * ellipsis in the output subscript. If the broadcasted dimensions are non-empty
 * and the output subscripts do not contain ellipsis, then an InvalidArgument error
 * is raised.
 * <p>
 * @compatibility(numpy)
 * Similar to [`numpy.einsum`](https://docs.scipy.org/doc/numpy/reference/generated/numpy.einsum.html).
 * <p>
 * Comparison with `numpy.einsum`:
 * <p>
 *  * This Op only supports unary and binary forms of `numpy.einsum`.
 *  * This Op does not support implicit form. (i.e. equations without `->`).
 *  * This Op also supports repeated indices in the output subscript, which is not
 *    supported by `numpy.einsum`.
 * @end_compatibility
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "linalg")
public final class Einsum<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Einsum operation.
   * 
   * @param scope current scope
   * @param inputs List of 1 or 2 Tensors.
   * @param equation String describing the Einstein Summation operation; in the format of np.einsum.
   * @return a new instance of Einsum
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Einsum<T> create(Scope scope, Iterable<Operand<T>> inputs, String equation) {
    OperationBuilder opBuilder = scope.env().opBuilder("Einsum", scope.makeOpName("Einsum"));
    opBuilder.addInputList(Operands.asOutputs(inputs));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("equation", equation);
    return new Einsum<T>(opBuilder.build());
  }
  
  /**
   * Output Tensor with shape depending upon `equation`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Einsum";
  
  private Output<T> output;
  
  private Einsum(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
