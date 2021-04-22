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
 * Returns the gradient of {@code StridedSlice}.
 * Since {@code StridedSlice} cuts out pieces of its {@code input} which is size
 * {@code shape}, its gradient will have the same shape (which is passed here
 * as {@code shape}). The gradient will be zero in any element that the slice
 * does not select.
 * <p>Arguments are the same as StridedSliceGrad with the exception that
 * {@code dy} is the input gradient to be propagated and {@code shape} is the
 * shape of {@code StridedSlice}'s {@code input}.
 *
 * @param <U> data type for {@code output} output
 */
@Operator
public final class StridedSliceGrad<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StridedSliceGrad";

  private Output<U> output;

  private StridedSliceGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StridedSliceGrad operation.
   *
   * @param scope current scope
   * @param shape the shape value
   * @param begin the begin value
   * @param end the end value
   * @param strides the strides value
   * @param dy the dy value
   * @param options carries optional attribute values
   * @param <U> data type for {@code StridedSliceGrad} output and operands
   * @param <T> data type for {@code StridedSliceGrad} output and operands
   * @return a new instance of StridedSliceGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType, T extends TNumber> StridedSliceGrad<U> create(Scope scope,
      Operand<T> shape, Operand<T> begin, Operand<T> end, Operand<T> strides, Operand<U> dy,
      Options... options) {
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
    return new StridedSliceGrad<>(opBuilder.build());
  }

  /**
   * Sets the beginMask option.
   *
   * @param beginMask the beginMask option
   * @return this Options instance.
   */
  public static Options beginMask(Long beginMask) {
    return new Options().beginMask(beginMask);
  }

  /**
   * Sets the endMask option.
   *
   * @param endMask the endMask option
   * @return this Options instance.
   */
  public static Options endMask(Long endMask) {
    return new Options().endMask(endMask);
  }

  /**
   * Sets the ellipsisMask option.
   *
   * @param ellipsisMask the ellipsisMask option
   * @return this Options instance.
   */
  public static Options ellipsisMask(Long ellipsisMask) {
    return new Options().ellipsisMask(ellipsisMask);
  }

  /**
   * Sets the newAxisMask option.
   *
   * @param newAxisMask the newAxisMask option
   * @return this Options instance.
   */
  public static Options newAxisMask(Long newAxisMask) {
    return new Options().newAxisMask(newAxisMask);
  }

  /**
   * Sets the shrinkAxisMask option.
   *
   * @param shrinkAxisMask the shrinkAxisMask option
   * @return this Options instance.
   */
  public static Options shrinkAxisMask(Long shrinkAxisMask) {
    return new Options().shrinkAxisMask(shrinkAxisMask);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.StridedSliceGrad}
   */
  public static class Options {
    private Long beginMask;

    private Long endMask;

    private Long ellipsisMask;

    private Long newAxisMask;

    private Long shrinkAxisMask;

    private Options() {
    }

    /**
     * Sets the beginMask option.
     *
     * @param beginMask the beginMask option
     * @return this Options instance.
     */
    public Options beginMask(Long beginMask) {
      this.beginMask = beginMask;
      return this;
    }

    /**
     * Sets the endMask option.
     *
     * @param endMask the endMask option
     * @return this Options instance.
     */
    public Options endMask(Long endMask) {
      this.endMask = endMask;
      return this;
    }

    /**
     * Sets the ellipsisMask option.
     *
     * @param ellipsisMask the ellipsisMask option
     * @return this Options instance.
     */
    public Options ellipsisMask(Long ellipsisMask) {
      this.ellipsisMask = ellipsisMask;
      return this;
    }

    /**
     * Sets the newAxisMask option.
     *
     * @param newAxisMask the newAxisMask option
     * @return this Options instance.
     */
    public Options newAxisMask(Long newAxisMask) {
      this.newAxisMask = newAxisMask;
      return this;
    }

    /**
     * Sets the shrinkAxisMask option.
     *
     * @param shrinkAxisMask the shrinkAxisMask option
     * @return this Options instance.
     */
    public Options shrinkAxisMask(Long shrinkAxisMask) {
      this.shrinkAxisMask = shrinkAxisMask;
      return this;
    }
  }
}
