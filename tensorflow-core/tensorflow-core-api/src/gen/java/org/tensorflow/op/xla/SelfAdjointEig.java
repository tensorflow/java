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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Computes the eigen decomposition of a batch of self-adjoint matrices
 * (Note: Only real inputs are supported).
 * <p>Computes the eigenvalues and eigenvectors of the innermost N-by-N matrices in
 * tensor such that tensor[...,:,:] * v[..., :,i] = e[..., i] * v[...,:,i], for
 * i=0...N-1.
 *
 * @param <T> data type for {@code w} output
 */
@Operator(
    group = "xla"
)
public final class SelfAdjointEig<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSelfAdjointEig";

  private Output<T> w;

  private Output<T> v;

  private SelfAdjointEig(Operation operation) {
    super(operation);
    int outputIdx = 0;
    w = operation.output(outputIdx++);
    v = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSelfAdjointEig operation.
   *
   * @param scope current scope
   * @param a the input tensor.
   * @param lower a boolean specifies whether the calculation is done with the lower
   * triangular part or the upper triangular part.
   * @param maxIter maximum number of sweep update, i.e., the whole lower triangular
   * part or upper triangular part based on parameter lower. Heuristically, it has
   * been argued that approximately logN sweeps are needed in practice (Ref: Golub &amp;
   * van Loan &quot;Matrix Computation&quot;).
   * @param epsilon the tolerance ratio.
   * @param <T> data type for {@code XlaSelfAdjointEig} output and operands
   * @return a new instance of SelfAdjointEig
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SelfAdjointEig<T> create(Scope scope, Operand<T> a, Boolean lower,
      Long maxIter, Float epsilon) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SelfAdjointEig");
    opBuilder.addInput(a.asOutput());
    opBuilder.setAttr("lower", lower);
    opBuilder.setAttr("max_iter", maxIter);
    opBuilder.setAttr("epsilon", epsilon);
    return new SelfAdjointEig<>(opBuilder.build());
  }

  /**
   * Gets w.
   * The eigenvalues in ascending order, each repeated according to its
   * multiplicity.
   * @return w.
   */
  public Output<T> w() {
    return w;
  }

  /**
   * Gets v.
   * The column v[..., :, i] is the normalized eigenvector corresponding to the
   * eigenvalue w[..., i].
   * @return v.
   */
  public Output<T> v() {
    return v;
  }
}
