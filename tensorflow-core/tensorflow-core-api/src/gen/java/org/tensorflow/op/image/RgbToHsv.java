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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Converts one or more images from RGB to HSV.
 * Outputs a tensor of the same shape as the {@code images} tensor, containing the HSV
 * value of the pixels. The output is only well defined if the value in {@code images}
 * are in {@code [0,1]}.
 * <p>{@code output[..., 0]} contains hue, {@code output[..., 1]} contains saturation, and
 * {@code output[..., 2]} contains value. All HSV values are in {@code [0,1]}. A hue of 0
 * corresponds to pure red, hue 1/3 is pure green, and 2/3 is pure blue.
 * <p>Usage Example:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>blue_image = tf.stack([
 * ...    tf.zeros([5,5]),
 * ...    tf.zeros([5,5]),
 * ...    tf.ones([5,5])],
 * ...    axis=-1)
 * blue_hsv_image = tf.image.rgb_to_hsv(blue_image)
 * blue_hsv_image[0,0].numpy()
 * array([0.6666667, 1. , 1. ], dtype=float32)
 * </blockquote>
 * </blockquote>
 * </blockquote>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RgbToHsv.OP_NAME,
    inputsClass = RgbToHsv.Inputs.class
)
@Operator(
    group = "image"
)
public final class RgbToHsv<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RGBToHSV";

  private Output<T> output;

  public RgbToHsv(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RGBToHSV operation.
   *
   * @param scope current scope
   * @param images 1-D or higher rank. RGB data to convert. Last dimension must be size 3.
   * @param <T> data type for {@code RGBToHSV} output and operands
   * @return a new instance of RgbToHsv
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RgbToHsv<T> create(Scope scope, Operand<T> images) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RgbToHsv");
    opBuilder.addInput(images.asOutput());
    return new RgbToHsv<>(opBuilder.build());
  }

  /**
   * Gets output.
   * {@code images} converted to HSV.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = RgbToHsv.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<RgbToHsv<T>> {
    /**
     * 1-D or higher rank. RGB data to convert. Last dimension must be size 3.
     */
    public final Operand<T> images;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new RgbToHsv<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      images = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
