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
import org.tensorflow.types.family.TType;

/**
 * Extracts crops from the input image tensor and resizes them.
 * <p>
 * Extracts crops from the input image tensor and resizes them using bilinear
 * sampling or nearest neighbor sampling (possibly with aspect ratio change) to a
 * common output size specified by `crop_size`. This is more general than the
 * `crop_to_bounding_box` op which extracts a fixed size slice from the input image
 * and does not allow resizing or aspect ratio change.
 * <p>
 * Returns a tensor with `crops` from the input `image` at positions defined at the
 * bounding box locations in `boxes`. The cropped boxes are all resized (with
 * bilinear or nearest neighbor interpolation) to a fixed
 * `size = [crop_height, crop_width]`. The result is a 4-D tensor
 * `[num_boxes, crop_height, crop_width, depth]`. The resizing is corner aligned.
 * In particular, if `boxes = [[0, 0, 1, 1]]`, the method will give identical
 * results to using `tf.image.resize_bilinear()` or
 * `tf.image.resize_nearest_neighbor()`(depends on the `method` argument) with
 * `align_corners=True`.
 */
@Operator(group = "image")
public final class CropAndResize extends RawOp implements Operand<TFloat32> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.CropAndResize}
   */
  public static class Options {
    
    /**
     * @param method A string specifying the sampling method for resizing. It can be either
     * `"bilinear"` or `"nearest"` and default to `"bilinear"`. Currently two sampling
     * methods are supported: Bilinear and Nearest Neighbor.
     */
    public Options method(String method) {
      this.method = method;
      return this;
    }
    
    /**
     * @param extrapolationValue Value used for extrapolation, when applicable.
     */
    public Options extrapolationValue(Float extrapolationValue) {
      this.extrapolationValue = extrapolationValue;
      return this;
    }
    
    private String method;
    private Float extrapolationValue;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new CropAndResize operation.
   * 
   * @param scope current scope
   * @param image A 4-D tensor of shape `[batch, image_height, image_width, depth]`.
   * Both `image_height` and `image_width` need to be positive.
   * @param boxes A 2-D tensor of shape `[num_boxes, 4]`. The `i`-th row of the tensor
   * specifies the coordinates of a box in the `box_ind[i]` image and is specified
   * in normalized coordinates `[y1, x1, y2, x2]`. A normalized coordinate value of
   * `y` is mapped to the image coordinate at `y * (image_height - 1)`, so as the
   * `[0, 1]` interval of normalized image height is mapped to
   * `[0, image_height - 1]` in image height coordinates. We do allow `y1` > `y2`, in
   * which case the sampled crop is an up-down flipped version of the original
   * image. The width dimension is treated similarly. Normalized coordinates
   * outside the `[0, 1]` range are allowed, in which case we use
   * `extrapolation_value` to extrapolate the input image values.
   * @param boxInd A 1-D tensor of shape `[num_boxes]` with int32 values in `[0, batch)`.
   * The value of `box_ind[i]` specifies the image that the `i`-th box refers to.
   * @param cropSize A 1-D tensor of 2 elements, `size = [crop_height, crop_width]`. All
   * cropped image patches are resized to this size. The aspect ratio of the image
   * content is not preserved. Both `crop_height` and `crop_width` need to be
   * positive.
   * @param options carries optional attributes values
   * @return a new instance of CropAndResize
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> CropAndResize create(Scope scope, Operand<T> image, Operand<TFloat32> boxes, Operand<TInt32> boxInd, Operand<TInt32> cropSize, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("CropAndResize", scope.makeOpName("CropAndResize"));
    opBuilder.addInput(image.asOutput());
    opBuilder.addInput(boxes.asOutput());
    opBuilder.addInput(boxInd.asOutput());
    opBuilder.addInput(cropSize.asOutput());
    opBuilder = scope.apply(opBuilder);
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
   * @param method A string specifying the sampling method for resizing. It can be either
   * `"bilinear"` or `"nearest"` and default to `"bilinear"`. Currently two sampling
   * methods are supported: Bilinear and Nearest Neighbor.
   */
  public static Options method(String method) {
    return new Options().method(method);
  }
  
  /**
   * @param extrapolationValue Value used for extrapolation, when applicable.
   */
  public static Options extrapolationValue(Float extrapolationValue) {
    return new Options().extrapolationValue(extrapolationValue);
  }
  
  /**
   * A 4-D tensor of shape `[num_boxes, crop_height, crop_width, depth]`.
   */
  public Output<TFloat32> crops() {
    return crops;
  }
  
  @Override
  public Output<TFloat32> asOutput() {
    return crops;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "CropAndResize";
  
  private Output<TFloat32> crops;
  
  private CropAndResize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    crops = operation.output(outputIdx++);
  }
}
