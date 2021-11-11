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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Assign {@code value} to the sliced l-value reference of {@code input}.
 * The values of {@code value} are assigned to the positions in the tensor {@code input} that
 * are selected by the slice parameters. The slice parameters {@code begin} {@code end}
 * {@code strides} etc. work exactly as in {@code StridedSlice}.
 * <p>NOTE this op currently does not support broadcasting and so {@code value}'s shape
 * must be exactly the shape produced by the slice of {@code input}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = TensorStridedSliceUpdate.OP_NAME,
    inputsClass = TensorStridedSliceUpdate.Inputs.class
)
@Operator
public final class TensorStridedSliceUpdate<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorStridedSliceUpdate";

  private Output<T> output;

  public TensorStridedSliceUpdate(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorStridedSliceUpdate operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param begin The begin value
   * @param end The end value
   * @param strides The strides value
   * @param value The value value
   * @param options carries optional attribute values
   * @param <T> data type for {@code TensorStridedSliceUpdate} output and operands
   * @param <U> data type for {@code TensorStridedSliceUpdate} output and operands
   * @return a new instance of TensorStridedSliceUpdate
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TNumber> TensorStridedSliceUpdate<T> create(Scope scope,
      Operand<T> input, Operand<U> begin, Operand<U> end, Operand<U> strides, Operand<T> value,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorStridedSliceUpdate");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(begin.asOutput());
    opBuilder.addInput(end.asOutput());
    opBuilder.addInput(strides.asOutput());
    opBuilder.addInput(value.asOutput());
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
    return new TensorStridedSliceUpdate<>(opBuilder.build());
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
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.TensorStridedSliceUpdate}
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

  @OpInputsMetadata(
      outputsClass = TensorStridedSliceUpdate.class
  )
  public static class Inputs<T extends TType, U extends TNumber> extends RawOpInputs<TensorStridedSliceUpdate<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The begin input
     */
    public final Operand<U> begin;

    /**
     * The end input
     */
    public final Operand<U> end;

    /**
     * The strides input
     */
    public final Operand<U> strides;

    /**
     * The value input
     */
    public final Operand<T> value;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Index attribute
     */
    public final DataType Index;

    /**
     * The beginMask attribute
     */
    public final long beginMask;

    /**
     * The endMask attribute
     */
    public final long endMask;

    /**
     * The ellipsisMask attribute
     */
    public final long ellipsisMask;

    /**
     * The newAxisMask attribute
     */
    public final long newAxisMask;

    /**
     * The shrinkAxisMask attribute
     */
    public final long shrinkAxisMask;

    public Inputs(GraphOperation op) {
      super(new TensorStridedSliceUpdate<>(op), op, Arrays.asList("T", "Index", "begin_mask", "end_mask", "ellipsis_mask", "new_axis_mask", "shrink_axis_mask"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      begin = (Operand<U>) op.input(inputIndex++);
      end = (Operand<U>) op.input(inputIndex++);
      strides = (Operand<U>) op.input(inputIndex++);
      value = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Index = op.attributes().getAttrType("Index");
      beginMask = op.attributes().getAttrInt("begin_mask");
      endMask = op.attributes().getAttrInt("end_mask");
      ellipsisMask = op.attributes().getAttrInt("ellipsis_mask");
      newAxisMask = op.attributes().getAttrInt("new_axis_mask");
      shrinkAxisMask = op.attributes().getAttrInt("shrink_axis_mask");
    }
  }
}
