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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Converts one or more images from RGB to HSV.
 * <p>
 * Outputs a tensor of the same shape as the `images` tensor, containing the HSV
 * value of the pixels. The output is only well defined if the value in `images`
 * are in `[0,1]`.
 * <p>
 * `output[..., 0]` contains hue, `output[..., 1]` contains saturation, and
 * `output[..., 2]` contains value. All HSV values are in `[0,1]`. A hue of 0
 * corresponds to pure red, hue 1/3 is pure green, and 2/3 is pure blue.
 * <p>
 * Usage Example:
 * <p>
 * >>> blue_image = tf.stack([
 * ...    tf.zeros([5,5]),
 * ...    tf.zeros([5,5]),
 * ...    tf.ones([5,5])],
 * ...    axis=-1)
 * >>> blue_hsv_image = tf.image.rgb_to_hsv(blue_image)
 * >>> blue_hsv_image[0,0].numpy()
 * array([0.6666667, 1. , 1. ], dtype=float32)
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "image")
public final class RgbToHsv<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new RgbToHsv operation.
   * 
   * @param scope current scope
   * @param images 1-D or higher rank. RGB data to convert. Last dimension must be size 3.
   * @return a new instance of RgbToHsv
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> RgbToHsv<T> create(Scope scope, Operand<T> images) {
    OperationBuilder opBuilder = scope.env().opBuilder("RGBToHSV", scope.makeOpName("RgbToHsv"));
    opBuilder.addInput(images.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new RgbToHsv<T>(opBuilder.build());
  }
  
  /**
   * `images` converted to HSV.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RGBToHSV";
  
  private Output<T> output;
  
  private RgbToHsv(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
