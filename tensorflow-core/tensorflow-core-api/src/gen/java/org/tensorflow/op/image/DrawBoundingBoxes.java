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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Draw bounding boxes on a batch of images.
 * Outputs a copy of {@code images} but draws on top of the pixels zero or more bounding
 * boxes specified by the locations in {@code boxes}. The coordinates of the each
 * bounding box in {@code boxes} are encoded as {@code [y_min, x_min, y_max, x_max]}. The
 * bounding box coordinates are floats in {@code [0.0, 1.0]} relative to the width and
 * height of the underlying image.
 * <p>For example, if an image is 100 x 200 pixels (height x width) and the bounding
 * box is {@code [0.1, 0.2, 0.5, 0.9]}, the upper-left and bottom-right coordinates of
 * the bounding box will be {@code (40, 10)} to {@code (100, 50)} (in (x,y) coordinates).
 * <p>Parts of the bounding box may fall outside the image.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = DrawBoundingBoxes.OP_NAME,
    inputsClass = DrawBoundingBoxes.Inputs.class
)
@Operator(
    group = "image"
)
public final class DrawBoundingBoxes<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DrawBoundingBoxesV2";

  private Output<T> output;

  public DrawBoundingBoxes(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DrawBoundingBoxesV2 operation.
   *
   * @param scope current scope
   * @param images 4-D with shape {@code [batch, height, width, depth]}. A batch of images.
   * @param boxes 3-D with shape {@code [batch, num_bounding_boxes, 4]} containing bounding
   * boxes.
   * @param colors 2-D. A list of RGBA colors to cycle through for the boxes.
   * @param <T> data type for {@code DrawBoundingBoxesV2} output and operands
   * @return a new instance of DrawBoundingBoxes
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> DrawBoundingBoxes<T> create(Scope scope, Operand<T> images,
      Operand<TFloat32> boxes, Operand<TFloat32> colors) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DrawBoundingBoxes");
    opBuilder.addInput(images.asOutput());
    opBuilder.addInput(boxes.asOutput());
    opBuilder.addInput(colors.asOutput());
    return new DrawBoundingBoxes<>(opBuilder.build());
  }

  /**
   * Gets output.
   * 4-D with the same shape as {@code images}. The batch of input images with
   * bounding boxes drawn on the images.
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
      outputsClass = DrawBoundingBoxes.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<DrawBoundingBoxes<T>> {
    /**
     * 4-D with shape {@code [batch, height, width, depth]}. A batch of images.
     */
    public final Operand<T> images;

    /**
     * 3-D with shape {@code [batch, num_bounding_boxes, 4]} containing bounding
     * boxes.
     */
    public final Operand<TFloat32> boxes;

    /**
     * 2-D. A list of RGBA colors to cycle through for the boxes.
     */
    public final Operand<TFloat32> colors;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new DrawBoundingBoxes<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      images = (Operand<T>) op.input(inputIndex++);
      boxes = (Operand<TFloat32>) op.input(inputIndex++);
      colors = (Operand<TFloat32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
