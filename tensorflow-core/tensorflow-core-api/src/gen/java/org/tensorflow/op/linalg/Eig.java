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
 * Computes the eigen decomposition of one or more square matrices.
 * <p>
 * Computes the eigenvalues and (optionally) right eigenvectors of each inner matrix in
 * `input` such that `input[..., :, :] = v[..., :, :] * diag(e[..., :])`. The eigenvalues
 * are sorted in non-decreasing order.
 * <pre>{@code
 * # a is a tensor.
 * # e is a tensor of eigenvalues.
 * # v is a tensor of eigenvectors.
 * e, v = eig(a)
 * e = eig(a, compute_v=False)
 * }</pre>
 * 
 * 
 * @param <U> data type for {@code e()} output
 */
@Operator(group = "linalg")
public final class Eig<U extends TType> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.Eig}
   */
  public static class Options {
    
    /**
     * @param computeV If `True` then eigenvectors will be computed and returned in `v`.
     * Otherwise, only the eigenvalues will be computed.
     */
    public Options computeV(Boolean computeV) {
      this.computeV = computeV;
      return this;
    }
    
    private Boolean computeV;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Eig operation.
   * 
   * @param scope current scope
   * @param input `Tensor` input of shape `[N, N]`.
   * @param Tout 
   * @param options carries optional attributes values
   * @return a new instance of Eig
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TType> Eig<U> create(Scope scope, Operand<T> input, Class<U> Tout, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Eig", scope.makeOpName("Eig"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("Tout", Operands.toDataType(Tout));
    if (options != null) {
      for (Options opts : options) {
        if (opts.computeV != null) {
          opBuilder.setAttr("compute_v", opts.computeV);
        }
      }
    }
    return new Eig<U>(opBuilder.build());
  }
  
  /**
   * @param computeV If `True` then eigenvectors will be computed and returned in `v`.
   * Otherwise, only the eigenvalues will be computed.
   */
  public static Options computeV(Boolean computeV) {
    return new Options().computeV(computeV);
  }
  
  /**
   * Eigenvalues. Shape is `[N]`.
   */
  public Output<U> e() {
    return e;
  }
  
  /**
   * Eigenvectors. Shape is `[N, N]`.
   */
  public Output<U> v() {
    return v;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Eig";
  
  private Output<U> e;
  private Output<U> v;
  
  private Eig(Operation operation) {
    super(operation);
    int outputIdx = 0;
    e = operation.output(outputIdx++);
    v = operation.output(outputIdx++);
  }
}
