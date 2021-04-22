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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Resize {@code images} to {@code size} using bilinear interpolation.
 * Input images can be of different types but output images are always float.
 */
@Operator(
    group = "image"
)
public final class ResizeBilinear extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResizeBilinear";

  private Output<TFloat32> resizedImages;

  private ResizeBilinear(Operation operation) {
    super(operation);
    int outputIdx = 0;
    resizedImages = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ResizeBilinear operation.
   *
   * @param scope current scope
   * @param images 4-D with shape {@code [batch, height, width, channels]}.
   * @param sizeOutput = A 1-D int32 Tensor of 2 elements: {@code new_height, new_width}.  The
   * new size for the images.
   * @param options carries optional attribute values
   * @return a new instance of ResizeBilinear
   */
  @Endpoint(
      describeByClass = true
  )
  public static ResizeBilinear create(Scope scope, Operand<? extends TNumber> images,
      Operand<TInt32> sizeOutput, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ResizeBilinear", scope.makeOpName("ResizeBilinear"));
    opBuilder.addInput(images.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    opBuilder = scope.apply(opBuilder);
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
    return new ResizeBilinear(opBuilder.build());
  }

  /**
   * Sets the alignCorners option.
   *
   * @param alignCorners If true, the centers of the 4 corner pixels of the input and output tensors are
   * aligned, preserving the values at the corner pixels. Defaults to false.
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
   * Gets resizedImages.
   * 4-D with shape
   * {@code [batch, new_height, new_width, channels]}.
   * @return resizedImages.
   */
  public Output<TFloat32> resizedImages() {
    return resizedImages;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return resizedImages;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.ResizeBilinear}
   */
  public static class Options {
    private Boolean alignCorners;

    private Boolean halfPixelCenters;

    private Options() {
    }

    /**
     * Sets the alignCorners option.
     *
     * @param alignCorners If true, the centers of the 4 corner pixels of the input and output tensors are
     * aligned, preserving the values at the corner pixels. Defaults to false.
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
}
