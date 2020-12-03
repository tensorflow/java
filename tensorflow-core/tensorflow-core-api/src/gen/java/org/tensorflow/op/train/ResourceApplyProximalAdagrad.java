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
 * Update '*var' and '*accum' according to FOBOS with Adagrad learning rate.
 * <p>
 * accum += grad <i> grad
 * prox_v = var - lr </i> grad <i> (1 / sqrt(accum))
 * var = sign(prox_v)/(1+lr</i>l2) <i> max{|prox_v|-lr</i>l1,0}
 */
@Operator(group = "train")
public final class ResourceApplyProximalAdagrad extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.train.ResourceApplyProximalAdagrad}
   */
  public static class Options {
    
    /**
     * @param useLocking If True, updating of the var and accum tensors will be protected by
     * a lock; otherwise the behavior is undefined, but may exhibit less contention.
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
   * Factory method to create a class wrapping a new ResourceApplyProximalAdagrad operation.
   * 
   * @param scope current scope
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 regularization. Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attributes values
   * @return a new instance of ResourceApplyProximalAdagrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> ResourceApplyProximalAdagrad create(Scope scope, Operand<?> var, Operand<?> accum, Operand<T> lr, Operand<T> l1, Operand<T> l2, Operand<T> grad, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ResourceApplyProximalAdagrad", scope.makeOpName("ResourceApplyProximalAdagrad"));
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(accum.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(l1.asOutput());
    opBuilder.addInput(l2.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new ResourceApplyProximalAdagrad(opBuilder.build());
  }
  
  /**
   * @param useLocking If True, updating of the var and accum tensors will be protected by
   * a lock; otherwise the behavior is undefined, but may exhibit less contention.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ResourceApplyProximalAdagrad";
  
  private ResourceApplyProximalAdagrad(Operation operation) {
    super(operation);
  }
}
