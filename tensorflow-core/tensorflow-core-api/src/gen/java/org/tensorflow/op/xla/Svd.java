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
 * <p>
 * (Note: Only real inputs are supported).
 * <p>
 * Computes the eigenvalues and eigenvectors of the innermost M-by-N matrices in
 * tensor such that tensor[...,:,:] = u[..., :, :] * Diag(s[..., :]) * Transpose(v[...,:,:]).
 * 
 * @param <T> data type for {@code s()} output
 */
@Operator(group = "xla")
public final class Svd<T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new Svd operation.
   * 
   * @param scope current scope
   * @param a the input tensor.
   * @param maxIter maximum number of sweep update, i.e., the whole lower triangular
   * part or upper triangular part based on parameter lower. Heuristically, it has
   * been argued that approximately log(min (M, N)) sweeps are needed in practice
   * (Ref: Golub & van Loan "Matrix Computation").
   * @param epsilon the tolerance ratio.
   * @param precisionConfig a serialized xla::PrecisionConfig proto.
   * @return a new instance of Svd
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Svd<T> create(Scope scope, Operand<T> a, Long maxIter, Float epsilon, String precisionConfig) {
    OperationBuilder opBuilder = scope.env().opBuilder("XlaSvd", scope.makeOpName("Svd"));
    opBuilder.addInput(a.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("max_iter", maxIter);
    opBuilder.setAttr("epsilon", epsilon);
    opBuilder.setAttr("precision_config", precisionConfig);
    return new Svd<T>(opBuilder.build());
  }
  
  /**
   * Singular values. The values are sorted in reverse order of magnitude, so
   * s[..., 0] is the largest value, s[..., 1] is the second largest, etc.
   */
  public Output<T> s() {
    return s;
  }
  
  /**
   * Left singular vectors.
   */
  public Output<T> u() {
    return u;
  }
  
  /**
   * Right singular vectors.
   */
  public Output<T> v() {
    return v;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "XlaSvd";
  
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
