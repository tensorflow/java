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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Computes the gradient of nearest neighbor interpolation.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class ResizeNearestNeighborGrad<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.ResizeNearestNeighborGrad}
   */
  public static class Options {
    
    /**
     * @param alignCorners If true, the centers of the 4 corner pixels of the input and grad tensors are
     * aligned. Defaults to false.
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
   * Factory method to create a class wrapping a new ResizeNearestNeighborGrad operation.
   * 
   * @param scope current scope
   * @param grads 4-D with shape `[batch, height, width, channels]`.
   * @param size = A 1-D int32 Tensor of 2 elements: `orig_height, orig_width`. The
   * original input size.
   * @param options carries optional attributes values
   * @return a new instance of ResizeNearestNeighborGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> ResizeNearestNeighborGrad<T> create(Scope scope, Operand<T> grads, Operand<TInt32> size, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ResizeNearestNeighborGrad", scope.makeOpName("ResizeNearestNeighborGrad"));
    opBuilder.addInput(grads.asOutput());
    opBuilder.addInput(size.asOutput());
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
    return new ResizeNearestNeighborGrad<T>(opBuilder.build());
  }
  
  /**
   * @param alignCorners If true, the centers of the 4 corner pixels of the input and grad tensors are
   * aligned. Defaults to false.
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
   * 4-D with shape `[batch, orig_height, orig_width, channels]`. Gradients
   * with respect to the input image.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ResizeNearestNeighborGrad";
  
  private Output<T> output;
  
  private ResizeNearestNeighborGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
