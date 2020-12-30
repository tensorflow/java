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
import org.tensorflow.types.family.TType;

/**
 * Update '*var' according to the AdaMax algorithm.
 * <p>
 * m_t <- beta1 * m_{t-1} + (1 - beta1) * g
 * v_t <- max(beta2 * v_{t-1}, abs(g))
 * variable <- variable - learning_rate / (1 - beta1^t) * m_t / (v_t + epsilon)
 * 
 * @param <T> data type for {@code out()} output
 */
public final class ApplyAdaMax<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.train.ApplyAdaMax}
   */
  public static class Options {
    
    /**
     * @param useLocking If `True`, updating of the var, m, and v tensors will be protected
     * by a lock; otherwise the behavior is undefined, but may exhibit less
     * contention.
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
   * Factory method to create a class wrapping a new ApplyAdaMax operation.
   * 
   * @param scope current scope
   * @param var Should be from a Variable().
   * @param m Should be from a Variable().
   * @param v Should be from a Variable().
   * @param beta1Power Must be a scalar.
   * @param lr Scaling factor. Must be a scalar.
   * @param beta1 Momentum factor. Must be a scalar.
   * @param beta2 Momentum factor. Must be a scalar.
   * @param epsilon Ridge term. Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attributes values
   * @return a new instance of ApplyAdaMax
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> ApplyAdaMax<T> create(Scope scope, Operand<T> var, Operand<T> m, Operand<T> v, Operand<T> beta1Power, Operand<T> lr, Operand<T> beta1, Operand<T> beta2, Operand<T> epsilon, Operand<T> grad, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ApplyAdaMax", scope.makeOpName("ApplyAdaMax"));
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(m.asOutput());
    opBuilder.addInput(v.asOutput());
    opBuilder.addInput(beta1Power.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(beta1.asOutput());
    opBuilder.addInput(beta2.asOutput());
    opBuilder.addInput(epsilon.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new ApplyAdaMax<T>(opBuilder.build());
  }
  
  /**
   * @param useLocking If `True`, updating of the var, m, and v tensors will be protected
   * by a lock; otherwise the behavior is undefined, but may exhibit less
   * contention.
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
  public static final String OP_NAME = "ApplyAdaMax";
  
  private Output<T> out;
  
  private ApplyAdaMax(Operation operation) {
    super(operation);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
  }
}
