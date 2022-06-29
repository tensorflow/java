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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the gradient of the crop_and_resize op wrt the input boxes tensor.
 */
@OpMetadata(
    opType = CropAndResizeGradBoxes.OP_NAME,
    inputsClass = CropAndResizeGradBoxes.Inputs.class
)
@Operator(
    group = "image"
)
public final class CropAndResizeGradBoxes extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CropAndResizeGradBoxes";

  private Output<TFloat32> output;

  public CropAndResizeGradBoxes(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CropAndResizeGradBoxes operation.
   *
   * @param scope current scope
   * @param grads A 4-D tensor of shape {@code [num_boxes, crop_height, crop_width, depth]}.
   * @param image A 4-D tensor of shape {@code [batch, image_height, image_width, depth]}.
   * Both {@code image_height} and {@code image_width} need to be positive.
   * @param boxes A 2-D tensor of shape {@code [num_boxes, 4]}. The {@code i}-th row of the tensor
   * specifies the coordinates of a box in the {@code box_ind[i]} image and is specified
   * in normalized coordinates {@code [y1, x1, y2, x2]}. A normalized coordinate value of
   * {@code y} is mapped to the image coordinate at {@code y * (image_height - 1)}, so as the
   * {@code [0, 1]} interval of normalized image height is mapped to
   * {@code [0, image_height - 1] in image height coordinates. We do allow y1 > y2, in which case the sampled crop is an up-down flipped version of the original image. The width dimension is treated similarly. Normalized coordinates outside the }[0, 1]{@code range are allowed, in which case we use}extrapolation_value` to extrapolate the input image values.
   * @param boxInd A 1-D tensor of shape {@code [num_boxes]} with int32 values in {@code [0, batch)}.
   * The value of {@code box_ind[i]} specifies the image that the {@code i}-th box refers to.
   * @param options carries optional attribute values
   * @return a new instance of CropAndResizeGradBoxes
   */
  @Endpoint(
      describeByClass = true
  )
  public static CropAndResizeGradBoxes create(Scope scope, Operand<TFloat32> grads,
      Operand<? extends TNumber> image, Operand<TFloat32> boxes, Operand<TInt32> boxInd,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CropAndResizeGradBoxes");
    opBuilder.addInput(grads.asOutput());
    opBuilder.addInput(image.asOutput());
    opBuilder.addInput(boxes.asOutput());
    opBuilder.addInput(boxInd.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.method != null) {
          opBuilder.setAttr("method", opts.method);
        }
      }
    }
    return new CropAndResizeGradBoxes(opBuilder.build());
  }

  /**
   * Sets the method option.
   *
   * @param method A string specifying the interpolation method. Only 'bilinear' is
   * supported for now.
   * @return this Options instance.
   */
  public static Options method(String method) {
    return new Options().method(method);
  }

  /**
   * Gets output.
   * A 2-D tensor of shape {@code [num_boxes, 4]}.
   * @return output.
   */
  public Output<TFloat32> output() {
    return output;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.CropAndResizeGradBoxes}
   */
  public static class Options {
    private String method;

    private Options() {
    }

    /**
     * Sets the method option.
     *
     * @param method A string specifying the interpolation method. Only 'bilinear' is
     * supported for now.
     * @return this Options instance.
     */
    public Options method(String method) {
      this.method = method;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = CropAndResizeGradBoxes.class
  )
  public static class Inputs extends RawOpInputs<CropAndResizeGradBoxes> {
    /**
     * A 4-D tensor of shape {@code [num_boxes, crop_height, crop_width, depth]}.
     */
    public final Operand<TFloat32> grads;

    /**
     * A 4-D tensor of shape {@code [batch, image_height, image_width, depth]}.
     * Both {@code image_height} and {@code image_width} need to be positive.
     */
    public final Operand<? extends TNumber> image;

    /**
     * A 2-D tensor of shape {@code [num_boxes, 4]}. The {@code i}-th row of the tensor
     * specifies the coordinates of a box in the {@code box_ind[i]} image and is specified
     * in normalized coordinates {@code [y1, x1, y2, x2]}. A normalized coordinate value of
     * {@code y} is mapped to the image coordinate at {@code y * (image_height - 1)}, so as the
     * {@code [0, 1]} interval of normalized image height is mapped to
     * {@code [0, image_height - 1] in image height coordinates. We do allow y1 > y2, in which case the sampled crop is an up-down flipped version of the original image. The width dimension is treated similarly. Normalized coordinates outside the }[0, 1]{@code range are allowed, in which case we use}extrapolation_value` to extrapolate the input image values.
     */
    public final Operand<TFloat32> boxes;

    /**
     * A 1-D tensor of shape {@code [num_boxes]} with int32 values in {@code [0, batch)}.
     * The value of {@code box_ind[i]} specifies the image that the {@code i}-th box refers to.
     */
    public final Operand<TInt32> boxInd;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * A string specifying the interpolation method. Only 'bilinear' is
     * supported for now.
     */
    public final String method;

    public Inputs(GraphOperation op) {
      super(new CropAndResizeGradBoxes(op), op, Arrays.asList("T", "method"));
      int inputIndex = 0;
      grads = (Operand<TFloat32>) op.input(inputIndex++);
      image = (Operand<? extends TNumber>) op.input(inputIndex++);
      boxes = (Operand<TFloat32>) op.input(inputIndex++);
      boxInd = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      method = op.attributes().getAttrString("method");
    }
  }
}
