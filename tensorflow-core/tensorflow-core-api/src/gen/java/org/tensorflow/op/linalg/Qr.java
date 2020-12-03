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
 * Computes the QR decompositions of one or more matrices.
 * <p>
 * Computes the QR decomposition of each inner matrix in `tensor` such that
 * `tensor[..., :, :] = q[..., :, :] * r[..., :,:])`
 * <pre>{@code
 * # a is a tensor.
 * # q is a tensor of orthonormal matrices.
 * # r is a tensor of upper triangular matrices.
 * q, r = qr(a)
 * q_full, r_full = qr(a, full_matrices=True)
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code q()} output
 */
@Operator(group = "linalg")
public final class Qr<T extends TType> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.Qr}
   */
  public static class Options {
    
    /**
     * @param fullMatrices If true, compute full-sized `q` and `r`. If false
     * (the default), compute only the leading `P` columns of `q`.
     */
    public Options fullMatrices(Boolean fullMatrices) {
      this.fullMatrices = fullMatrices;
      return this;
    }
    
    private Boolean fullMatrices;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Qr operation.
   * 
   * @param scope current scope
   * @param input A tensor of shape `[..., M, N]` whose inner-most 2 dimensions
   * form matrices of size `[M, N]`. Let `P` be the minimum of `M` and `N`.
   * @param options carries optional attributes values
   * @return a new instance of Qr
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Qr<T> create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Qr", scope.makeOpName("Qr"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.fullMatrices != null) {
          opBuilder.setAttr("full_matrices", opts.fullMatrices);
        }
      }
    }
    return new Qr<T>(opBuilder.build());
  }
  
  /**
   * @param fullMatrices If true, compute full-sized `q` and `r`. If false
   * (the default), compute only the leading `P` columns of `q`.
   */
  public static Options fullMatrices(Boolean fullMatrices) {
    return new Options().fullMatrices(fullMatrices);
  }
  
  /**
   * Orthonormal basis for range of `a`. If `full_matrices` is `False` then
   * shape is `[..., M, P]`; if `full_matrices` is `True` then shape is
   * `[..., M, M]`.
   */
  public Output<T> q() {
    return q;
  }
  
  /**
   * Triangular factor. If `full_matrices` is `False` then shape is
   * `[..., P, N]`. If `full_matrices` is `True` then shape is `[..., M, N]`.
   */
  public Output<T> r() {
    return r;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Qr";
  
  private Output<T> q;
  private Output<T> r;
  
  private Qr(Operation operation) {
    super(operation);
    int outputIdx = 0;
    q = operation.output(outputIdx++);
    r = operation.output(outputIdx++);
  }
}
