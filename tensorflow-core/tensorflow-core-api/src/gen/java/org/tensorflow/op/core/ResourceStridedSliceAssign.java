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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Assign {@code value} to the sliced l-value reference of {@code ref}.
 * The values of {@code value} are assigned to the positions in the variable
 * {@code ref} that are selected by the slice parameters. The slice parameters
 * {@code begin, }end{@code , }strides{@code , etc. work exactly as in }StridedSlice`.
 * <p>NOTE this op currently does not support broadcasting and so {@code value}'s
 * shape must be exactly the shape produced by the slice of {@code ref}.
 */
@Operator
public final class ResourceStridedSliceAssign extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceStridedSliceAssign";

  private ResourceStridedSliceAssign(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new ResourceStridedSliceAssign operation.
   *
   * @param scope current scope
   * @param ref the ref value
   * @param begin the begin value
   * @param end the end value
   * @param strides the strides value
   * @param value the value value
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceStridedSliceAssign} output and operands
   * @return a new instance of ResourceStridedSliceAssign
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> ResourceStridedSliceAssign create(Scope scope,
      Operand<? extends TType> ref, Operand<T> begin, Operand<T> end, Operand<T> strides,
      Operand<? extends TType> value, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ResourceStridedSliceAssign", scope.makeOpName("ResourceStridedSliceAssign"));
    opBuilder.addInput(ref.asOutput());
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
    return new ResourceStridedSliceAssign(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.core.ResourceStridedSliceAssign}
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
