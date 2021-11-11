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

package org.tensorflow.op.image;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the gradient of nearest neighbor interpolation.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = ResizeNearestNeighborGrad.OP_NAME,
    inputsClass = ResizeNearestNeighborGrad.Inputs.class
)
public final class ResizeNearestNeighborGrad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResizeNearestNeighborGrad";

  private Output<T> output;

  public ResizeNearestNeighborGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ResizeNearestNeighborGrad operation.
   *
   * @param scope current scope
   * @param grads 4-D with shape {@code [batch, height, width, channels]}.
   * @param sizeOutput = A 1-D int32 Tensor of 2 elements: {@code orig_height, orig_width}. The
   * original input size.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResizeNearestNeighborGrad} output and operands
   * @return a new instance of ResizeNearestNeighborGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> ResizeNearestNeighborGrad<T> create(Scope scope,
      Operand<T> grads, Operand<TInt32> sizeOutput, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResizeNearestNeighborGrad");
    opBuilder.addInput(grads.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.alignCorners != null) {
          opBuilder.setAttr("align_corners", opts.alignCorners);
        }
        if (opts.halfPixelCenters != null) {
          opBuilder.setAttr("half_pixel_centers", opts.halfPixelCenters);
        }
      }
    }
    return new ResizeNearestNeighborGrad<>(opBuilder.build());
  }

  /**
   * Sets the alignCorners option.
   *
   * @param alignCorners If true, the centers of the 4 corner pixels of the input and grad tensors are
   * aligned. Defaults to false.
   * @return this Options instance.
   */
  public static Options alignCorners(Boolean alignCorners) {
    return new Options().alignCorners(alignCorners);
  }

  /**
   * Sets the halfPixelCenters option.
   *
   * @param halfPixelCenters the halfPixelCenters option
   * @return this Options instance.
   */
  public static Options halfPixelCenters(Boolean halfPixelCenters) {
    return new Options().halfPixelCenters(halfPixelCenters);
  }

  /**
   * Gets output.
   * 4-D with shape {@code [batch, orig_height, orig_width, channels]}. Gradients
   * with respect to the input image.
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
   * Optional attributes for {@link org.tensorflow.op.image.ResizeNearestNeighborGrad}
   */
  public static class Options {
    private Boolean alignCorners;

    private Boolean halfPixelCenters;

    private Options() {
    }

    /**
     * Sets the alignCorners option.
     *
     * @param alignCorners If true, the centers of the 4 corner pixels of the input and grad tensors are
     * aligned. Defaults to false.
     * @return this Options instance.
     */
    public Options alignCorners(Boolean alignCorners) {
      this.alignCorners = alignCorners;
      return this;
    }

    /**
     * Sets the halfPixelCenters option.
     *
     * @param halfPixelCenters the halfPixelCenters option
     * @return this Options instance.
     */
    public Options halfPixelCenters(Boolean halfPixelCenters) {
      this.halfPixelCenters = halfPixelCenters;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ResizeNearestNeighborGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<ResizeNearestNeighborGrad<T>> {
    /**
     * 4-D with shape {@code [batch, height, width, channels]}.
     */
    public final Operand<T> grads;

    /**
     * = A 1-D int32 Tensor of 2 elements: {@code orig_height, orig_width}. The
     * original input size.
     */
    public final Operand<TInt32> sizeOutput;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * If true, the centers of the 4 corner pixels of the input and grad tensors are
     * aligned. Defaults to false.
     */
    public final boolean alignCorners;

    /**
     * The halfPixelCenters attribute
     */
    public final boolean halfPixelCenters;

    public Inputs(GraphOperation op) {
      super(new ResizeNearestNeighborGrad<>(op), op, Arrays.asList("T", "align_corners", "half_pixel_centers"));
      int inputIndex = 0;
      grads = (Operand<T>) op.input(inputIndex++);
      sizeOutput = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      alignCorners = op.attributes().getAttrBool("align_corners");
      halfPixelCenters = op.attributes().getAttrBool("half_pixel_centers");
    }
  }
}
