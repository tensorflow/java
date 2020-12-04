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
 * Computes the gradient of the crop_and_resize op wrt the input boxes tensor.
 */
@Operator(group = "image")
public final class CropAndResizeGradBoxes extends RawOp implements Operand<TFloat32> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.CropAndResizeGradBoxes}
   */
  public static class Options {
    
    /**
     * @param method A string specifying the interpolation method. Only 'bilinear' is
     * supported for now.
     */
    public Options method(String method) {
      this.method = method;
      return this;
    }
    
    private String method;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new CropAndResizeGradBoxes operation.
   * 
   * @param scope current scope
   * @param grads A 4-D tensor of shape `[num_boxes, crop_height, crop_width, depth]`.
   * @param image A 4-D tensor of shape `[batch, image_height, image_width, depth]`.
   * Both `image_height` and `image_width` need to be positive.
   * @param boxes A 2-D tensor of shape `[num_boxes, 4]`. The `i`-th row of the tensor
   * specifies the coordinates of a box in the `box_ind[i]` image and is specified
   * in normalized coordinates `[y1, x1, y2, x2]`. A normalized coordinate value of
   * `y` is mapped to the image coordinate at `y * (image_height - 1)`, so as the
   * `[0, 1]` interval of normalized image height is mapped to
   * `[0, image_height - 1] in image height coordinates. We do allow y1 > y2, in
   * which case the sampled crop is an up-down flipped version of the original
   * image. The width dimension is treated similarly. Normalized coordinates
   * outside the `[0, 1]` range are allowed, in which case we use
   * `extrapolation_value` to extrapolate the input image values.
   * @param boxInd A 1-D tensor of shape `[num_boxes]` with int32 values in `[0, batch)`.
   * The value of `box_ind[i]` specifies the image that the `i`-th box refers to.
   * @param options carries optional attributes values
   * @return a new instance of CropAndResizeGradBoxes
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> CropAndResizeGradBoxes create(Scope scope, Operand<TFloat32> grads, Operand<T> image, Operand<TFloat32> boxes, Operand<TInt32> boxInd, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("CropAndResizeGradBoxes", scope.makeOpName("CropAndResizeGradBoxes"));
    opBuilder.addInput(grads.asOutput());
    opBuilder.addInput(image.asOutput());
    opBuilder.addInput(boxes.asOutput());
    opBuilder.addInput(boxInd.asOutput());
    opBuilder = scope.apply(opBuilder);
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
   * @param method A string specifying the interpolation method. Only 'bilinear' is
   * supported for now.
   */
  public static Options method(String method) {
    return new Options().method(method);
  }
  
  /**
   * A 2-D tensor of shape `[num_boxes, 4]`.
   */
  public Output<TFloat32> output() {
    return output;
  }
  
  @Override
  public Output<TFloat32> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "CropAndResizeGradBoxes";
  
  private Output<TFloat32> output;
  
  private CropAndResizeGradBoxes(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
