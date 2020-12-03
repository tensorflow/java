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
 * Update '*var' according to the Ftrl-proximal scheme.
 * <p>
 * grad_with_shrinkage = grad + 2 * l2_shrinkage * var
 * accum_new = accum + grad * grad
 * linear += grad_with_shrinkage -
 *     (accum_new^(-lr_power) - accum^(-lr_power)) / lr * var
 * quadratic = 1.0 / (accum_new^(lr_power) * lr) + 2 * l2
 * var = (sign(linear) * l1 - linear) / quadratic if |linear| > l1 else 0.0
 * accum = accum_new
 * 
 * @param <T> data type for {@code out()} output
 */
@Operator(group = "train")
public final class ApplyFtrl<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.train.ApplyFtrl}
   */
  public static class Options {
    
    /**
     * @param useLocking If `True`, updating of the var and accum tensors will be protected
     * by a lock; otherwise the behavior is undefined, but may exhibit less
     * contention.
     */
    public Options useLocking(Boolean useLocking) {
      this.useLocking = useLocking;
      return this;
    }
    
    /**
     * @param multiplyLinearByLr 
     */
    public Options multiplyLinearByLr(Boolean multiplyLinearByLr) {
      this.multiplyLinearByLr = multiplyLinearByLr;
      return this;
    }
    
    private Boolean useLocking;
    private Boolean multiplyLinearByLr;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ApplyFtrl operation.
   * 
   * @param scope current scope
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param linear Should be from a Variable().
   * @param grad The gradient.
   * @param lr Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 shrinkage regularization. Must be a scalar.
   * @param l2Shrinkage 
   * @param lrPower Scaling factor. Must be a scalar.
   * @param options carries optional attributes values
   * @return a new instance of ApplyFtrl
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> ApplyFtrl<T> create(Scope scope, Operand<T> var, Operand<T> accum, Operand<T> linear, Operand<T> grad, Operand<T> lr, Operand<T> l1, Operand<T> l2, Operand<T> l2Shrinkage, Operand<T> lrPower, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ApplyFtrlV2", scope.makeOpName("ApplyFtrl"));
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(accum.asOutput());
    opBuilder.addInput(linear.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(l1.asOutput());
    opBuilder.addInput(l2.asOutput());
    opBuilder.addInput(l2Shrinkage.asOutput());
    opBuilder.addInput(lrPower.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
        if (opts.multiplyLinearByLr != null) {
          opBuilder.setAttr("multiply_linear_by_lr", opts.multiplyLinearByLr);
        }
      }
    }
    return new ApplyFtrl<T>(opBuilder.build());
  }
  
  /**
   * @param useLocking If `True`, updating of the var and accum tensors will be protected
   * by a lock; otherwise the behavior is undefined, but may exhibit less
   * contention.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }
  
  /**
   * @param multiplyLinearByLr 
   */
  public static Options multiplyLinearByLr(Boolean multiplyLinearByLr) {
    return new Options().multiplyLinearByLr(multiplyLinearByLr);
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
  public static final String OP_NAME = "ApplyFtrlV2";
  
  private Output<T> out;
  
  private ApplyFtrl(Operation operation) {
    super(operation);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
  }
}
