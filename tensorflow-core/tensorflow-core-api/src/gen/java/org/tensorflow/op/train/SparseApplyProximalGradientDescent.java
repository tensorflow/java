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

package org.tensorflow.op.train;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Sparse update '*var' as FOBOS algorithm with fixed learning rate.
 * <p>
 * That is for rows we have grad for, we update var as follows:
 * $$prox_v = var - alpha <i> grad$$
 * $$var = sign(prox_v)/(1+alpha</i>l2) <i> max{|prox_v|-alpha</i>l1,0}$$
 * 
 * @param <T> data type for {@code out()} output
 */
@Operator(group = "train")
public final class SparseApplyProximalGradientDescent<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.train.SparseApplyProximalGradientDescent}
   */
  public static class Options {
    
    /**
     * @param useLocking If True, the subtraction will be protected by a lock;
     * otherwise the behavior is undefined, but may exhibit less contention.
     */
    public Options useLocking(Boolean useLocking) {
      this.useLocking = useLocking;
      return this;
    }
    
    private Boolean useLocking;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SparseApplyProximalGradientDescent operation.
   * 
   * @param scope current scope
   * @param var Should be from a Variable().
   * @param alpha Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 regularization. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param options carries optional attributes values
   * @return a new instance of SparseApplyProximalGradientDescent
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> SparseApplyProximalGradientDescent<T> create(Scope scope, Operand<T> var, Operand<T> alpha, Operand<T> l1, Operand<T> l2, Operand<T> grad, Operand<U> indices, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseApplyProximalGradientDescent", scope.makeOpName("SparseApplyProximalGradientDescent"));
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(alpha.asOutput());
    opBuilder.addInput(l1.asOutput());
    opBuilder.addInput(l2.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new SparseApplyProximalGradientDescent<T>(opBuilder.build());
  }
  
  /**
   * @param useLocking If True, the subtraction will be protected by a lock;
   * otherwise the behavior is undefined, but may exhibit less contention.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }
  
  /**
   * Same as "var".
   */
  public Output<T> out() {
    return out;
  }
  
  @Override
  public Output<T> asOutput() {
    return out;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseApplyProximalGradientDescent";
  
  private Output<T> out;
  
  private SparseApplyProximalGradientDescent(Operation operation) {
    super(operation);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
  }
}
