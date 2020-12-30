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
import org.tensorflow.types.family.TType;

/**
 * Resize quantized `images` to `size` using quantized bilinear interpolation.
 * <p>
 * Input images and output images must be quantized types.
 * 
 * @param <T> data type for {@code resizedImages()} output
 */
@Operator(group = "image")
public final class QuantizedResizeBilinear<T extends TType> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.QuantizedResizeBilinear}
   */
  public static class Options {
    
    /**
     * @param alignCorners If true, the centers of the 4 corner pixels of the input and output tensors are
     * aligned, preserving the values at the corner pixels. Defaults to false.
     */
    public Options alignCorners(Boolean alignCorners) {
      this.alignCorners = alignCorners;
      return this;
    }
    
    /**
     * @param halfPixelCenters 
     */
    public Options halfPixelCenters(Boolean halfPixelCenters) {
      this.halfPixelCenters = halfPixelCenters;
      return this;
    }
    
    private Boolean alignCorners;
    private Boolean halfPixelCenters;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new QuantizedResizeBilinear operation.
   * 
   * @param scope current scope
   * @param images 4-D with shape `[batch, height, width, channels]`.
   * @param size = A 1-D int32 Tensor of 2 elements: `new_height, new_width`.  The
   * new size for the images.
   * @param min 
   * @param max 
   * @param options carries optional attributes values
   * @return a new instance of QuantizedResizeBilinear
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> QuantizedResizeBilinear<T> create(Scope scope, Operand<T> images, Operand<TInt32> size, Operand<TFloat32> min, Operand<TFloat32> max, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizedResizeBilinear", scope.makeOpName("QuantizedResizeBilinear"));
    opBuilder.addInput(images.asOutput());
    opBuilder.addInput(size.asOutput());
    opBuilder.addInput(min.asOutput());
    opBuilder.addInput(max.asOutput());
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
    return new QuantizedResizeBilinear<T>(opBuilder.build());
  }
  
  /**
   * @param alignCorners If true, the centers of the 4 corner pixels of the input and output tensors are
   * aligned, preserving the values at the corner pixels. Defaults to false.
   */
  public static Options alignCorners(Boolean alignCorners) {
    return new Options().alignCorners(alignCorners);
  }
  
  /**
   * @param halfPixelCenters 
   */
  public static Options halfPixelCenters(Boolean halfPixelCenters) {
    return new Options().halfPixelCenters(halfPixelCenters);
  }
  
  /**
   * 4-D with shape
   * `[batch, new_height, new_width, channels]`.
   */
  public Output<T> resizedImages() {
    return resizedImages;
  }
  
  /**
   */
  public Output<TFloat32> outMin() {
    return outMin;
  }
  
  /**
   */
  public Output<TFloat32> outMax() {
    return outMax;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "QuantizedResizeBilinear";
  
  private Output<T> resizedImages;
  private Output<TFloat32> outMin;
  private Output<TFloat32> outMax;
  
  private QuantizedResizeBilinear(Operation operation) {
    super(operation);
    int outputIdx = 0;
    resizedImages = operation.output(outputIdx++);
    outMin = operation.output(outputIdx++);
    outMax = operation.output(outputIdx++);
  }
}
