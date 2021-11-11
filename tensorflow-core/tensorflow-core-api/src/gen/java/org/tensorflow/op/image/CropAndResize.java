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
 * Extracts crops from the input image tensor and resizes them.
 * Extracts crops from the input image tensor and resizes them using bilinear
 * sampling or nearest neighbor sampling (possibly with aspect ratio change) to a
 * common output size specified by {@code crop_size}. This is more general than the
 * {@code crop_to_bounding_box} op which extracts a fixed size slice from the input image
 * and does not allow resizing or aspect ratio change.
 * <p>Returns a tensor with {@code crops} from the input {@code image} at positions defined at the
 * bounding box locations in {@code boxes}. The cropped boxes are all resized (with
 * bilinear or nearest neighbor interpolation) to a fixed
 * {@code size = [crop_height, crop_width]}. The result is a 4-D tensor
 * {@code [num_boxes, crop_height, crop_width, depth]}. The resizing is corner aligned.
 * In particular, if {@code boxes = [[0, 0, 1, 1]]}, the method will give identical
 * results to using {@code tf.image.resize_bilinear()} or
 * {@code tf.image.resize_nearest_neighbor()}(depends on the {@code method} argument) with
 * {@code align_corners=True}.
 */
@OpMetadata(
    opType = CropAndResize.OP_NAME,
    inputsClass = CropAndResize.Inputs.class
)
@Operator(
    group = "image"
)
public final class CropAndResize extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CropAndResize";

  private Output<TFloat32> crops;

  public CropAndResize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    crops = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CropAndResize operation.
   *
   * @param scope current scope
   * @param image A 4-D tensor of shape {@code [batch, image_height, image_width, depth]}.
   * Both {@code image_height} and {@code image_width} need to be positive.
   * @param boxes A 2-D tensor of shape {@code [num_boxes, 4]}. The {@code i}-th row of the tensor
   * specifies the coordinates of a box in the {@code box_ind[i]} image and is specified
   * in normalized coordinates {@code [y1, x1, y2, x2]}. A normalized coordinate value of
   * {@code y} is mapped to the image coordinate at {@code y * (image_height - 1)}, so as the
   * {@code [0, 1]} interval of normalized image height is mapped to
   * {@code [0, image_height - 1]} in image height coordinates. We do allow {@code y1} &gt; {@code y2}, in
   * which case the sampled crop is an up-down flipped version of the original
   * image. The width dimension is treated similarly. Normalized coordinates
   * outside the {@code [0, 1]} range are allowed, in which case we use
   * {@code extrapolation_value} to extrapolate the input image values.
   * @param boxInd A 1-D tensor of shape {@code [num_boxes]} with int32 values in {@code [0, batch)}.
   * The value of {@code box_ind[i]} specifies the image that the {@code i}-th box refers to.
   * @param cropSize A 1-D tensor of 2 elements, {@code size = [crop_height, crop_width]}. All
   * cropped image patches are resized to this size. The aspect ratio of the image
   * content is not preserved. Both {@code crop_height} and {@code crop_width} need to be
   * positive.
   * @param options carries optional attribute values
   * @return a new instance of CropAndResize
   */
  @Endpoint(
      describeByClass = true
  )
  public static CropAndResize create(Scope scope, Operand<? extends TNumber> image,
      Operand<TFloat32> boxes, Operand<TInt32> boxInd, Operand<TInt32> cropSize,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CropAndResize");
    opBuilder.addInput(image.asOutput());
    opBuilder.addInput(boxes.asOutput());
    opBuilder.addInput(boxInd.asOutput());
    opBuilder.addInput(cropSize.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.method != null) {
          opBuilder.setAttr("method", opts.method);
        }
        if (opts.extrapolationValue != null) {
          opBuilder.setAttr("extrapolation_value", opts.extrapolationValue);
        }
      }
    }
    return new CropAndResize(opBuilder.build());
  }

  /**
   * Sets the method option.
   *
   * @param method A string specifying the sampling method for resizing. It can be either
   * {@code "bilinear"} or {@code "nearest"} and default to {@code "bilinear"}. Currently two sampling
   * methods are supported: Bilinear and Nearest Neighbor.
   * @return this Options instance.
   */
  public static Options method(String method) {
    return new Options().method(method);
  }

  /**
   * Sets the extrapolationValue option.
   *
   * @param extrapolationValue Value used for extrapolation, when applicable.
   * @return this Options instance.
   */
  public static Options extrapolationValue(Float extrapolationValue) {
    return new Options().extrapolationValue(extrapolationValue);
  }

  /**
   * Gets crops.
   * A 4-D tensor of shape {@code [num_boxes, crop_height, crop_width, depth]}.
   * @return crops.
   */
  public Output<TFloat32> crops() {
    return crops;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return crops;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.CropAndResize}
   */
  public static class Options {
    private String method;

    private Float extrapolationValue;

    private Options() {
    }

    /**
     * Sets the method option.
     *
     * @param method A string specifying the sampling method for resizing. It can be either
     * {@code "bilinear"} or {@code "nearest"} and default to {@code "bilinear"}. Currently two sampling
     * methods are supported: Bilinear and Nearest Neighbor.
     * @return this Options instance.
     */
    public Options method(String method) {
      this.method = method;
      return this;
    }

    /**
     * Sets the extrapolationValue option.
     *
     * @param extrapolationValue Value used for extrapolation, when applicable.
     * @return this Options instance.
     */
    public Options extrapolationValue(Float extrapolationValue) {
      this.extrapolationValue = extrapolationValue;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = CropAndResize.class
  )
  public static class Inputs extends RawOpInputs<CropAndResize> {
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
     * {@code [0, image_height - 1]} in image height coordinates. We do allow {@code y1} &gt; {@code y2}, in
     * which case the sampled crop is an up-down flipped version of the original
     * image. The width dimension is treated similarly. Normalized coordinates
     * outside the {@code [0, 1]} range are allowed, in which case we use
     * {@code extrapolation_value} to extrapolate the input image values.
     */
    public final Operand<TFloat32> boxes;

    /**
     * A 1-D tensor of shape {@code [num_boxes]} with int32 values in {@code [0, batch)}.
     * The value of {@code box_ind[i]} specifies the image that the {@code i}-th box refers to.
     */
    public final Operand<TInt32> boxInd;

    /**
     * A 1-D tensor of 2 elements, {@code size = [crop_height, crop_width]}. All
     * cropped image patches are resized to this size. The aspect ratio of the image
     * content is not preserved. Both {@code crop_height} and {@code crop_width} need to be
     * positive.
     */
    public final Operand<TInt32> cropSize;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * A string specifying the sampling method for resizing. It can be either
     * `"bilinear"` or `"nearest"` and default to `"bilinear"`. Currently two sampling
     * methods are supported: Bilinear and Nearest Neighbor.
     */
    public final String method;

    /**
     * Value used for extrapolation, when applicable.
     */
    public final float extrapolationValue;

    public Inputs(GraphOperation op) {
      super(new CropAndResize(op), op, Arrays.asList("T", "method", "extrapolation_value"));
      int inputIndex = 0;
      image = (Operand<? extends TNumber>) op.input(inputIndex++);
      boxes = (Operand<TFloat32>) op.input(inputIndex++);
      boxInd = (Operand<TInt32>) op.input(inputIndex++);
      cropSize = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      method = op.attributes().getAttrString("method");
      extrapolationValue = op.attributes().getAttrFloat("extrapolation_value");
    }
  }
}
