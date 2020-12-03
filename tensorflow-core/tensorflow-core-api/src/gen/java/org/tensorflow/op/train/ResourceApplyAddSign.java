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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Update '*var' according to the AddSign update.
 * <p>
 * m_t <- beta1 * m_{t-1} + (1 - beta1) * g
 * update <- (alpha + sign_decay * sign(g) *sign(m)) * g
 * variable <- variable - lr_t * update
 */
@Operator(group = "train")
public final class ResourceApplyAddSign extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.train.ResourceApplyAddSign}
   */
  public static class Options {
    
    /**
     * @param useLocking If `True`, updating of the var and m tensors is
     * protected by a lock; otherwise the behavior is undefined, but may exhibit less
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
   * Factory method to create a class wrapping a new ResourceApplyAddSign operation.
   * 
   * @param scope current scope
   * @param var Should be from a Variable().
   * @param m Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param alpha Must be a scalar.
   * @param signDecay Must be a scalar.
   * @param beta Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attributes values
   * @return a new instance of ResourceApplyAddSign
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> ResourceApplyAddSign create(Scope scope, Operand<?> var, Operand<?> m, Operand<T> lr, Operand<T> alpha, Operand<T> signDecay, Operand<T> beta, Operand<T> grad, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ResourceApplyAddSign", scope.makeOpName("ResourceApplyAddSign"));
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(m.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(alpha.asOutput());
    opBuilder.addInput(signDecay.asOutput());
    opBuilder.addInput(beta.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new ResourceApplyAddSign(opBuilder.build());
  }
  
  /**
   * @param useLocking If `True`, updating of the var and m tensors is
   * protected by a lock; otherwise the behavior is undefined, but may exhibit less
   * contention.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ResourceApplyAddSign";
  
  private ResourceApplyAddSign(Operation operation) {
    super(operation);
  }
}
