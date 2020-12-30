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
 * Returns the gradient of `StridedSlice`.
 * <p>
 * Since `StridedSlice` cuts out pieces of its `input` which is size
 * `shape`, its gradient will have the same shape (which is passed here
 * as `shape`). The gradient will be zero in any element that the slice
 * does not select.
 * <p>
 * Arguments are the same as StridedSliceGrad with the exception that
 * `dy` is the input gradient to be propagated and `shape` is the
 * shape of `StridedSlice`'s `input`.
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator
public final class StridedSliceGrad<U extends TType> extends RawOp implements Operand<U> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.StridedSliceGrad}
   */
  public static class Options {
    
    /**
     * @param beginMask 
     */
    public Options beginMask(Long beginMask) {
      this.beginMask = beginMask;
      return this;
    }
    
    /**
     * @param endMask 
     */
    public Options endMask(Long endMask) {
      this.endMask = endMask;
      return this;
    }
    
    /**
     * @param ellipsisMask 
     */
    public Options ellipsisMask(Long ellipsisMask) {
      this.ellipsisMask = ellipsisMask;
      return this;
    }
    
    /**
     * @param newAxisMask 
     */
    public Options newAxisMask(Long newAxisMask) {
      this.newAxisMask = newAxisMask;
      return this;
    }
    
    /**
     * @param shrinkAxisMask 
     */
    public Options shrinkAxisMask(Long shrinkAxisMask) {
      this.shrinkAxisMask = shrinkAxisMask;
      return this;
    }
    
    private Long beginMask;
    private Long endMask;
    private Long ellipsisMask;
    private Long newAxisMask;
    private Long shrinkAxisMask;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new StridedSliceGrad operation.
   * 
   * @param scope current scope
   * @param shape 
   * @param begin 
   * @param end 
   * @param strides 
   * @param dy 
   * @param options carries optional attributes values
   * @return a new instance of StridedSliceGrad
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TNumber> StridedSliceGrad<U> create(Scope scope, Operand<T> shape, Operand<T> begin, Operand<T> end, Operand<T> strides, Operand<U> dy, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("StridedSliceGrad", scope.makeOpName("StridedSliceGrad"));
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(begin.asOutput());
    opBuilder.addInput(end.asOutput());
    opBuilder.addInput(strides.asOutput());
    opBuilder.addInput(dy.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.beginMask != null) {
          opBuilder.setAttr("begin_mask", opts.beginMask);
        }
        if (opts.endMask != null) {
          opBuilder.setAttr("end_mask", opts.endMask);
        }
        if (opts.ellipsisMask != null) {
          opBuilder.setAttr("ellipsis_mask", opts.ellipsisMask);
        }
        if (opts.newAxisMask != null) {
          opBuilder.setAttr("new_axis_mask", opts.newAxisMask);
        }
        if (opts.shrinkAxisMask != null) {
          opBuilder.setAttr("shrink_axis_mask", opts.shrinkAxisMask);
        }
      }
    }
    return new StridedSliceGrad<U>(opBuilder.build());
  }
  
  /**
   * @param beginMask 
   */
  public static Options beginMask(Long beginMask) {
    return new Options().beginMask(beginMask);
  }
  
  /**
   * @param endMask 
   */
  public static Options endMask(Long endMask) {
    return new Options().endMask(endMask);
  }
  
  /**
   * @param ellipsisMask 
   */
  public static Options ellipsisMask(Long ellipsisMask) {
    return new Options().ellipsisMask(ellipsisMask);
  }
  
  /**
   * @param newAxisMask 
   */
  public static Options newAxisMask(Long newAxisMask) {
    return new Options().newAxisMask(newAxisMask);
  }
  
  /**
   * @param shrinkAxisMask 
   */
  public static Options shrinkAxisMask(Long shrinkAxisMask) {
    return new Options().shrinkAxisMask(shrinkAxisMask);
  }
  
  /**
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StridedSliceGrad";
  
  private Output<U> output;
  
  private StridedSliceGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
