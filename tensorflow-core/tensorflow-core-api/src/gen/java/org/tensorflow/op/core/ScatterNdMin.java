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

package org.tensorflow.op.core;

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
 * Computes element-wise minimum.
 * 
 * @param <T> data type for {@code outputRef()} output
 */
public final class ScatterNdMin<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.ScatterNdMin}
   */
  public static class Options {
    
    /**
     * @param useLocking An optional bool. Defaults to True. If True, the assignment will
     * be protected by a lock; otherwise the behavior is undefined,
     * but may exhibit less contention.
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
   * Factory method to create a class wrapping a new ScatterNdMin operation.
   * 
   * @param scope current scope
   * @param ref A mutable Tensor. Should be from a Variable node.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   * A tensor of indices into ref.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated values
   * to add to ref.
   * @param options carries optional attributes values
   * @return a new instance of ScatterNdMin
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> ScatterNdMin<T> create(Scope scope, Operand<T> ref, Operand<U> indices, Operand<T> updates, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ScatterNdMin", scope.makeOpName("ScatterNdMin"));
    opBuilder.addInput(ref.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new ScatterNdMin<T>(opBuilder.build());
  }
  
  /**
   * @param useLocking An optional bool. Defaults to True. If True, the assignment will
   * be protected by a lock; otherwise the behavior is undefined,
   * but may exhibit less contention.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }
  
  /**
   * Same as ref. Returned as a convenience for operations that want
   * to use the updated values after the update is done.
   */
  public Output<T> outputRef() {
    return outputRef;
  }
  
  @Override
  public Output<T> asOutput() {
    return outputRef;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ScatterNdMin";
  
  private Output<T> outputRef;
  
  private ScatterNdMin(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputRef = operation.output(outputIdx++);
  }
}
