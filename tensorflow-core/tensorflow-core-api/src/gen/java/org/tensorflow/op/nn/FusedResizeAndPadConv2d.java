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

package org.tensorflow.op.nn;

import java.util.Arrays;
import java.util.List;
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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Performs a resize and padding as a preprocess during a convolution.
 * It's often possible to do spatial transformations more efficiently as part of
 * the packing stage of a convolution, so this op allows for an optimized
 * implementation where these stages are fused together. This prevents the need to
 * write out the intermediate results as whole tensors, reducing memory pressure,
 * and we can get some latency gains by merging the transformation calculations.
 * The data_format attribute for Conv2D isn't supported by this op, and defaults to
 * 'NHWC' order.
 * Internally this op uses a single per-graph scratch buffer, which means that it
 * will block if multiple versions are being run in parallel. This is because this
 * operator is primarily an optimization to minimize memory usage.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = FusedResizeAndPadConv2d.OP_NAME,
    inputsClass = FusedResizeAndPadConv2d.Inputs.class
)
@Operator(
    group = "nn"
)
public final class FusedResizeAndPadConv2d<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FusedResizeAndPadConv2D";

  private Output<T> output;

  public FusedResizeAndPadConv2d(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FusedResizeAndPadConv2D operation.
   *
   * @param scope current scope
   * @param input 4-D with shape {@code [batch, in_height, in_width, in_channels]}.
   * @param sizeOutput A 1-D int32 Tensor of 2 elements: {@code new_height, new_width}.  The
   * new size for the images.
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   * rows must be the same as the rank of {@code input}.
   * @param filter 4-D with shape
   * {@code [filter_height, filter_width, in_channels, out_channels]}.
   * @param mode The value of the mode attribute
   * @param strides 1-D of length 4.  The stride of the sliding window for each dimension
   * of {@code input}. Must be in the same order as the dimension specified with format.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code FusedResizeAndPadConv2D} output and operands
   * @return a new instance of FusedResizeAndPadConv2d
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> FusedResizeAndPadConv2d<T> create(Scope scope, Operand<T> input,
      Operand<TInt32> sizeOutput, Operand<TInt32> paddings, Operand<T> filter, String mode,
      List<Long> strides, String padding, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FusedResizeAndPadConv2d");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    opBuilder.addInput(paddings.asOutput());
    opBuilder.addInput(filter.asOutput());
    opBuilder.setAttr("mode", mode);
    long[] stridesArray = new long[strides.size()];
    for (int i = 0 ; i < stridesArray.length ; i++) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.resizeAlignCorners != null) {
          opBuilder.setAttr("resize_align_corners", opts.resizeAlignCorners);
        }
      }
    }
    return new FusedResizeAndPadConv2d<>(opBuilder.build());
  }

  /**
   * Sets the resizeAlignCorners option.
   *
   * @param resizeAlignCorners If true, the centers of the 4 corner pixels of the input and output tensors are
   * aligned, preserving the values at the corner pixels. Defaults to false.
   * @return this Options instance.
   */
  public static Options resizeAlignCorners(Boolean resizeAlignCorners) {
    return new Options().resizeAlignCorners(resizeAlignCorners);
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
   * Optional attributes for {@link org.tensorflow.op.nn.FusedResizeAndPadConv2d}
   */
  public static class Options {
    private Boolean resizeAlignCorners;

    private Options() {
    }

    /**
     * Sets the resizeAlignCorners option.
     *
     * @param resizeAlignCorners If true, the centers of the 4 corner pixels of the input and output tensors are
     * aligned, preserving the values at the corner pixels. Defaults to false.
     * @return this Options instance.
     */
    public Options resizeAlignCorners(Boolean resizeAlignCorners) {
      this.resizeAlignCorners = resizeAlignCorners;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = FusedResizeAndPadConv2d.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<FusedResizeAndPadConv2d<T>> {
    /**
     * 4-D with shape {@code [batch, in_height, in_width, in_channels]}.
     */
    public final Operand<T> input;

    /**
     * A 1-D int32 Tensor of 2 elements: {@code new_height, new_width}.  The
     * new size for the images.
     */
    public final Operand<TInt32> sizeOutput;

    /**
     * A two-column matrix specifying the padding sizes. The number of
     * rows must be the same as the rank of {@code input}.
     */
    public final Operand<TInt32> paddings;

    /**
     * 4-D with shape
     * {@code [filter_height, filter_width, in_channels, out_channels]}.
     */
    public final Operand<T> filter;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * If true, the centers of the 4 corner pixels of the input and output tensors are
     * aligned, preserving the values at the corner pixels. Defaults to false.
     */
    public final boolean resizeAlignCorners;

    /**
     * The mode attribute
     */
    public final String mode;

    /**
     * 1-D of length 4.  The stride of the sliding window for each dimension
     * of `input`. Must be in the same order as the dimension specified with format.
     */
    public final long[] strides;

    /**
     * The type of padding algorithm to use.
     */
    public final String padding;

    public Inputs(GraphOperation op) {
      super(new FusedResizeAndPadConv2d<>(op), op, Arrays.asList("T", "resize_align_corners", "mode", "strides", "padding"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      sizeOutput = (Operand<TInt32>) op.input(inputIndex++);
      paddings = (Operand<TInt32>) op.input(inputIndex++);
      filter = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      resizeAlignCorners = op.attributes().getAttrBool("resize_align_corners");
      mode = op.attributes().getAttrString("mode");
      strides = op.attributes().getAttrIntList("strides");
      padding = op.attributes().getAttrString("padding");
    }
  }
}
