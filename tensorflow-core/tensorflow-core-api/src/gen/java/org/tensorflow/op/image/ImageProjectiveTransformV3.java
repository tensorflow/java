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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Applies the given transform to each of the images.
 * If one row of {@code transforms} is {@code [a0, a1, a2, b0, b1, b2, c0, c1]}, then it maps
 * the <em>output</em> point {@code (x, y)} to a transformed <em>input</em> point
 * {@code (x', y') = ((a0 x + a1 y + a2) / k, (b0 x + b1 y + b2) / k)}, where
 * {@code k = c0 x + c1 y + 1}. If the transformed point lays outside of the input
 * image, the output pixel is set to fill_value.
 *
 * @param <T> data type for {@code transformed_images} output
 */
@OpMetadata(
    opType = ImageProjectiveTransformV3.OP_NAME,
    inputsClass = ImageProjectiveTransformV3.Inputs.class
)
public final class ImageProjectiveTransformV3<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ImageProjectiveTransformV3";

  private Output<T> transformedImages;

  public ImageProjectiveTransformV3(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    transformedImages = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ImageProjectiveTransformV3 operation.
   *
   * @param scope current scope
   * @param images 4-D with shape {@code [batch, height, width, channels]}.
   * @param transforms 2-D Tensor, {@code [batch, 8]} or {@code [1, 8]} matrix, where each row corresponds to a 3 x 3
   * projective transformation matrix, with the last entry assumed to be 1. If there
   * is one row, the same transformation will be applied to all images.
   * @param outputShape 1-D Tensor [new_height, new_width].
   * @param fillValue float, the value to be filled when fill_mode is constant&quot;.
   * @param interpolation Interpolation method, &quot;NEAREST&quot; or &quot;BILINEAR&quot;.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ImageProjectiveTransformV3} output and operands
   * @return a new instance of ImageProjectiveTransformV3
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> ImageProjectiveTransformV3<T> create(Scope scope,
      Operand<T> images, Operand<TFloat32> transforms, Operand<TInt32> outputShape,
      Operand<TFloat32> fillValue, String interpolation, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ImageProjectiveTransformV3");
    opBuilder.addInput(images.asOutput());
    opBuilder.addInput(transforms.asOutput());
    opBuilder.addInput(outputShape.asOutput());
    opBuilder.addInput(fillValue.asOutput());
    opBuilder.setAttr("interpolation", interpolation);
    if (options != null) {
      for (Options opts : options) {
        if (opts.fillMode != null) {
          opBuilder.setAttr("fill_mode", opts.fillMode);
        }
      }
    }
    return new ImageProjectiveTransformV3<>(opBuilder.build());
  }

  /**
   * Sets the fillMode option.
   *
   * @param fillMode Fill mode, &quot;REFLECT&quot;, &quot;WRAP&quot;, &quot;CONSTANT&quot;, or &quot;NEAREST&quot;.
   * @return this Options instance.
   */
  public static Options fillMode(String fillMode) {
    return new Options().fillMode(fillMode);
  }

  /**
   * Gets transformedImages.
   * 4-D with shape
   * {@code [batch, new_height, new_width, channels]}.
   * @return transformedImages.
   */
  public Output<T> transformedImages() {
    return transformedImages;
  }

  @Override
  public Output<T> asOutput() {
    return transformedImages;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.ImageProjectiveTransformV3}
   */
  public static class Options {
    private String fillMode;

    private Options() {
    }

    /**
     * Sets the fillMode option.
     *
     * @param fillMode Fill mode, &quot;REFLECT&quot;, &quot;WRAP&quot;, &quot;CONSTANT&quot;, or &quot;NEAREST&quot;.
     * @return this Options instance.
     */
    public Options fillMode(String fillMode) {
      this.fillMode = fillMode;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ImageProjectiveTransformV3.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<ImageProjectiveTransformV3<T>> {
    /**
     * 4-D with shape {@code [batch, height, width, channels]}.
     */
    public final Operand<T> images;

    /**
     * 2-D Tensor, {@code [batch, 8]} or {@code [1, 8]} matrix, where each row corresponds to a 3 x 3
     * projective transformation matrix, with the last entry assumed to be 1. If there
     * is one row, the same transformation will be applied to all images.
     */
    public final Operand<TFloat32> transforms;

    /**
     * 1-D Tensor [new_height, new_width].
     */
    public final Operand<TInt32> outputShape;

    /**
     * float, the value to be filled when fill_mode is constant&quot;.
     */
    public final Operand<TFloat32> fillValue;

    /**
     * Input dtype.
     */
    public final DataType dtype;

    /**
     * Interpolation method, "NEAREST" or "BILINEAR".
     */
    public final String interpolation;

    /**
     * Fill mode, "REFLECT", "WRAP", "CONSTANT", or "NEAREST".
     */
    public final String fillMode;

    public Inputs(GraphOperation op) {
      super(new ImageProjectiveTransformV3<>(op), op, Arrays.asList("dtype", "interpolation", "fill_mode"));
      int inputIndex = 0;
      images = (Operand<T>) op.input(inputIndex++);
      transforms = (Operand<TFloat32>) op.input(inputIndex++);
      outputShape = (Operand<TInt32>) op.input(inputIndex++);
      fillValue = (Operand<TFloat32>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      interpolation = op.attributes().getAttrString("interpolation");
      fillMode = op.attributes().getAttrString("fill_mode");
    }
  }
}
