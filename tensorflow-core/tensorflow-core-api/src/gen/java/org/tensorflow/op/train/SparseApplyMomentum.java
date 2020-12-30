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
 * Update relevant entries in '*var' and '*accum' according to the momentum scheme.
 * <p>
 * Set use_nesterov = True if you want to use Nesterov momentum.
 * <p>
 * That is for rows we have grad for, we update var and accum as follows:
 * <p>
 * $$accum = accum * momentum + grad$$
 * $$var -= lr * accum$$
 * 
 * @param <T> data type for {@code out()} output
 */
@Operator(group = "train")
public final class SparseApplyMomentum<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.train.SparseApplyMomentum}
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
     * @param useNesterov If `True`, the tensor passed to compute grad will be
     * var - lr * momentum * accum, so in the end, the var you get is actually
     * var - lr * momentum * accum.
     */
    public Options useNesterov(Boolean useNesterov) {
      this.useNesterov = useNesterov;
      return this;
    }
    
    private Boolean useLocking;
    private Boolean useNesterov;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SparseApplyMomentum operation.
   * 
   * @param scope current scope
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Learning rate. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param momentum Momentum. Must be a scalar.
   * @param options carries optional attributes values
   * @return a new instance of SparseApplyMomentum
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> SparseApplyMomentum<T> create(Scope scope, Operand<T> var, Operand<T> accum, Operand<T> lr, Operand<T> grad, Operand<U> indices, Operand<T> momentum, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseApplyMomentum", scope.makeOpName("SparseApplyMomentum"));
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(accum.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(momentum.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
        if (opts.useNesterov != null) {
          opBuilder.setAttr("use_nesterov", opts.useNesterov);
        }
      }
    }
    return new SparseApplyMomentum<T>(opBuilder.build());
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
   * @param useNesterov If `True`, the tensor passed to compute grad will be
   * var - lr * momentum * accum, so in the end, the var you get is actually
   * var - lr * momentum * accum.
   */
  public static Options useNesterov(Boolean useNesterov) {
    return new Options().useNesterov(useNesterov);
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
  public static final String OP_NAME = "SparseApplyMomentum";
  
  private Output<T> out;
  
  private SparseApplyMomentum(Operation operation) {
    super(operation);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
  }
}
