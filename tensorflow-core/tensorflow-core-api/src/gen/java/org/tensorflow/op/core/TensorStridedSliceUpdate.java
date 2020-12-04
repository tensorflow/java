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
 * Assign `value` to the sliced l-value reference of `input`.
 * <p>
 * The values of `value` are assigned to the positions in the tensor `input` that
 * are selected by the slice parameters. The slice parameters `begin` `end`
 * `strides` etc. work exactly as in `StridedSlice`.
 * <p>
 * NOTE this op currently does not support broadcasting and so `value`'s shape
 * must be exactly the shape produced by the slice of `input`.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class TensorStridedSliceUpdate<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorStridedSliceUpdate}
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
   * Factory method to create a class wrapping a new TensorStridedSliceUpdate operation.
   * 
   * @param scope current scope
   * @param input 
   * @param begin 
   * @param end 
   * @param strides 
   * @param value 
   * @param options carries optional attributes values
   * @return a new instance of TensorStridedSliceUpdate
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> TensorStridedSliceUpdate<T> create(Scope scope, Operand<T> input, Operand<U> begin, Operand<U> end, Operand<U> strides, Operand<T> value, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorStridedSliceUpdate", scope.makeOpName("TensorStridedSliceUpdate"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(begin.asOutput());
    opBuilder.addInput(end.asOutput());
    opBuilder.addInput(strides.asOutput());
    opBuilder.addInput(value.asOutput());
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
    return new TensorStridedSliceUpdate<T>(opBuilder.build());
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
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorStridedSliceUpdate";
  
  private Output<T> output;
  
  private TensorStridedSliceUpdate(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
