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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Computes the singular value decompositions of one or more matrices.
 * <p>
 * Computes the SVD of each inner matrix in `input` such that
 * `input[..., :, :] = u[..., :, :] * diag(s[..., :, :]) * transpose(v[..., :, :])`
 * <pre>{@code
 * # a is a tensor containing a batch of matrices.
 * # s is a tensor of singular values for each matrix.
 * # u is the tensor containing the left singular vectors for each matrix.
 * # v is the tensor containing the right singular vectors for each matrix.
 * s, u, v = svd(a)
 * s, _, _ = svd(a, compute_uv=False)
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code s()} output
 */
@Operator(group = "linalg")
public final class Svd<T extends TType> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.Svd}
   */
  public static class Options {
    
    /**
     * @param computeUv If true, left and right singular vectors will be
     * computed and returned in `u` and `v`, respectively.
     * If false, `u` and `v` are not set and should never referenced.
     */
    public Options computeUv(Boolean computeUv) {
      this.computeUv = computeUv;
      return this;
    }
    
    /**
     * @param fullMatrices If true, compute full-sized `u` and `v`. If false
     * (the default), compute only the leading `P` singular vectors.
     * Ignored if `compute_uv` is `False`.
     */
    public Options fullMatrices(Boolean fullMatrices) {
      this.fullMatrices = fullMatrices;
      return this;
    }
    
    private Boolean computeUv;
    private Boolean fullMatrices;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Svd operation.
   * 
   * @param scope current scope
   * @param input A tensor of shape `[..., M, N]` whose inner-most 2 dimensions
   * form matrices of size `[M, N]`. Let `P` be the minimum of `M` and `N`.
   * @param options carries optional attributes values
   * @return a new instance of Svd
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Svd<T> create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Svd", scope.makeOpName("Svd"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.computeUv != null) {
          opBuilder.setAttr("compute_uv", opts.computeUv);
        }
        if (opts.fullMatrices != null) {
          opBuilder.setAttr("full_matrices", opts.fullMatrices);
        }
      }
    }
    return new Svd<T>(opBuilder.build());
  }
  
  /**
   * @param computeUv If true, left and right singular vectors will be
   * computed and returned in `u` and `v`, respectively.
   * If false, `u` and `v` are not set and should never referenced.
   */
  public static Options computeUv(Boolean computeUv) {
    return new Options().computeUv(computeUv);
  }
  
  /**
   * @param fullMatrices If true, compute full-sized `u` and `v`. If false
   * (the default), compute only the leading `P` singular vectors.
   * Ignored if `compute_uv` is `False`.
   */
  public static Options fullMatrices(Boolean fullMatrices) {
    return new Options().fullMatrices(fullMatrices);
  }
  
  /**
   * Singular values. Shape is `[..., P]`.
   */
  public Output<T> s() {
    return s;
  }
  
  /**
   * Left singular vectors. If `full_matrices` is `False` then shape is
   * `[..., M, P]`; if `full_matrices` is `True` then shape is
   * `[..., M, M]`. Undefined if `compute_uv` is `False`.
   */
  public Output<T> u() {
    return u;
  }
  
  /**
   * Left singular vectors. If `full_matrices` is `False` then shape is
   * `[..., N, P]`. If `full_matrices` is `True` then shape is `[..., N, N]`.
   * Undefined if `compute_uv` is false.
   */
  public Output<T> v() {
    return v;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Svd";
  
  private Output<T> s;
  private Output<T> u;
  private Output<T> v;
  
  private Svd(Operation operation) {
    super(operation);
    int outputIdx = 0;
    s = operation.output(outputIdx++);
    u = operation.output(outputIdx++);
    v = operation.output(outputIdx++);
  }
}
