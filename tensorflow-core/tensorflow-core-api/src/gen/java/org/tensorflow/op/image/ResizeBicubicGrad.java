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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the gradient of bicubic interpolation.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = ResizeBicubicGrad.OP_NAME,
    inputsClass = ResizeBicubicGrad.Inputs.class
)
public final class ResizeBicubicGrad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResizeBicubicGrad";

  private Output<T> output;

  public ResizeBicubicGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ResizeBicubicGrad operation.
   *
   * @param scope current scope
   * @param grads 4-D with shape {@code [batch, height, width, channels]}.
   * @param originalImage 4-D with shape {@code [batch, orig_height, orig_width, channels]},
   * The image tensor that was resized.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResizeBicubicGrad} output and operands
   * @return a new instance of ResizeBicubicGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> ResizeBicubicGrad<T> create(Scope scope,
      Operand<TFloat32> grads, Operand<T> originalImage, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResizeBicubicGrad");
    opBuilder.addInput(grads.asOutput());
    opBuilder.addInput(originalImage.asOutput());
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
    return new ResizeBicubicGrad<>(opBuilder.build());
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
   * 4-D with shape {@code [batch, orig_height, orig_width, channels]}.
   * Gradients with respect to the input image. Input image must have been
   * float or double.
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
   * Optional attributes for {@link org.tensorflow.op.image.ResizeBicubicGrad}
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
      outputsClass = ResizeBicubicGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<ResizeBicubicGrad<T>> {
    /**
     * 4-D with shape {@code [batch, height, width, channels]}.
     */
    public final Operand<TFloat32> grads;

    /**
     * 4-D with shape {@code [batch, orig_height, orig_width, channels]},
     * The image tensor that was resized.
     */
    public final Operand<T> originalImage;

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
      super(new ResizeBicubicGrad<>(op), op, Arrays.asList("T", "align_corners", "half_pixel_centers"));
      int inputIndex = 0;
      grads = (Operand<TFloat32>) op.input(inputIndex++);
      originalImage = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      alignCorners = op.attributes().getAttrBool("align_corners");
      halfPixelCenters = op.attributes().getAttrBool("half_pixel_centers");
    }
  }
}
