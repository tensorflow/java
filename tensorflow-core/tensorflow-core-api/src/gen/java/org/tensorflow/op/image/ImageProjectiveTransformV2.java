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
 * Applies the given transform to each of the images.
 * <p>
 * If one row of `transforms` is `[a0, a1, a2, b0, b1, b2, c0, c1]`, then it maps
 * the <i>output</i> point `(x, y)` to a transformed <i>input</i> point
 * `(x', y') = ((a0 x + a1 y + a2) / k, (b0 x + b1 y + b2) / k)`, where
 * `k = c0 x + c1 y + 1`. If the transformed point lays outside of the input
 * image, the output pixel is set to 0.
 * 
 * @param <T> data type for {@code transformedImages()} output
 */
public final class ImageProjectiveTransformV2<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.ImageProjectiveTransformV2}
   */
  public static class Options {
    
    /**
     * @param fillMode Fill mode, "REFLECT", "WRAP", or "CONSTANT".
     */
    public Options fillMode(String fillMode) {
      this.fillMode = fillMode;
      return this;
    }
    
    private String fillMode;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ImageProjectiveTransformV2 operation.
   * 
   * @param scope current scope
   * @param images 4-D with shape `[batch, height, width, channels]`.
   * @param transforms 2-D Tensor, `[batch, 8]` or `[1, 8]` matrix, where each row corresponds to a 3 x 3
   * projective transformation matrix, with the last entry assumed to be 1. If there
   * is one row, the same transformation will be applied to all images.
   * @param outputShape 1-D Tensor [new_height, new_width].
   * @param interpolation Interpolation method, "NEAREST" or "BILINEAR".
   * @param options carries optional attributes values
   * @return a new instance of ImageProjectiveTransformV2
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> ImageProjectiveTransformV2<T> create(Scope scope, Operand<T> images, Operand<TFloat32> transforms, Operand<TInt32> outputShape, String interpolation, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ImageProjectiveTransformV2", scope.makeOpName("ImageProjectiveTransformV2"));
    opBuilder.addInput(images.asOutput());
    opBuilder.addInput(transforms.asOutput());
    opBuilder.addInput(outputShape.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("interpolation", interpolation);
    if (options != null) {
      for (Options opts : options) {
        if (opts.fillMode != null) {
          opBuilder.setAttr("fill_mode", opts.fillMode);
        }
      }
    }
    return new ImageProjectiveTransformV2<T>(opBuilder.build());
  }
  
  /**
   * @param fillMode Fill mode, "REFLECT", "WRAP", or "CONSTANT".
   */
  public static Options fillMode(String fillMode) {
    return new Options().fillMode(fillMode);
  }
  
  /**
   * 4-D with shape
   * `[batch, new_height, new_width, channels]`.
   */
  public Output<T> transformedImages() {
    return transformedImages;
  }
  
  @Override
  public Output<T> asOutput() {
    return transformedImages;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ImageProjectiveTransformV2";
  
  private Output<T> transformedImages;
  
  private ImageProjectiveTransformV2(Operation operation) {
    super(operation);
    int outputIdx = 0;
    transformedImages = operation.output(outputIdx++);
  }
}
