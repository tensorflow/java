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

/**
 * Extracts a glimpse from the input tensor.
 * <p>
 * Returns a set of windows called glimpses extracted at location
 * `offsets` from the input tensor. If the windows only partially
 * overlaps the inputs, the non overlapping areas will be filled with
 * random noise.
 * <p>
 * The result is a 4-D tensor of shape `[batch_size, glimpse_height,
 * glimpse_width, channels]`. The channels and batch dimensions are the
 * same as that of the input tensor. The height and width of the output
 * windows are specified in the `size` parameter.
 * <p>
 * The argument `normalized` and `centered` controls how the windows are built:
 * <ul>
 * <li>
 * If the coordinates are normalized but not centered, 0.0 and 1.0
 *   correspond to the minimum and maximum of each height and width
 *   dimension.
 * </li>
 * <li>
 * If the coordinates are both normalized and centered, they range from
 *   -1.0 to 1.0. The coordinates (-1.0, -1.0) correspond to the upper
 *   left corner, the lower right corner is located at (1.0, 1.0) and the
 *   center is at (0, 0).
 * </li>
 * <li>
 * If the coordinates are not normalized they are interpreted as
 *   numbers of pixels.
 */
public final class ExtractGlimpse extends RawOp implements Operand<TFloat32> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.ExtractGlimpse}
   */
  public static class Options {
    
    /**
     * @param centered indicates if the offset coordinates are centered relative to
     * the image, in which case the (0, 0) offset is relative to the center
     * of the input images. If false, the (0,0) offset corresponds to the
     * upper left corner of the input images.
     */
    public Options centered(Boolean centered) {
      this.centered = centered;
      return this;
    }
    
    /**
     * @param normalized indicates if the offset coordinates are normalized.
     */
    public Options normalized(Boolean normalized) {
      this.normalized = normalized;
      return this;
    }
    
    /**
     * @param uniformNoise indicates if the noise should be generated using a
     * uniform distribution or a Gaussian distribution.
     */
    public Options uniformNoise(Boolean uniformNoise) {
      this.uniformNoise = uniformNoise;
      return this;
    }
    
    /**
     * @param noise indicates if the noise should `uniform`, `gaussian`, or
     * `zero`. The default is `uniform` which means the the noise type
     * will be decided by `uniform_noise`.
     */
    public Options noise(String noise) {
      this.noise = noise;
      return this;
    }
    
    private Boolean centered;
    private Boolean normalized;
    private Boolean uniformNoise;
    private String noise;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ExtractGlimpse operation.
   * 
   * @param scope current scope
   * @param input A 4-D float tensor of shape `[batch_size, height, width, channels]`.
   * @param size A 1-D tensor of 2 elements containing the size of the glimpses
   * to extract.  The glimpse height must be specified first, following
   * by the glimpse width.
   * @param offsets A 2-D integer tensor of shape `[batch_size, 2]` containing
   * the y, x locations of the center of each window.
   * @param options carries optional attributes values
   * @return a new instance of ExtractGlimpse
   */
  @Endpoint(describeByClass = true)
  public static ExtractGlimpse create(Scope scope, Operand<TFloat32> input, Operand<TInt32> size, Operand<TFloat32> offsets, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExtractGlimpseV2", scope.makeOpName("ExtractGlimpse"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(size.asOutput());
    opBuilder.addInput(offsets.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.centered != null) {
          opBuilder.setAttr("centered", opts.centered);
        }
        if (opts.normalized != null) {
          opBuilder.setAttr("normalized", opts.normalized);
        }
        if (opts.uniformNoise != null) {
          opBuilder.setAttr("uniform_noise", opts.uniformNoise);
        }
        if (opts.noise != null) {
          opBuilder.setAttr("noise", opts.noise);
        }
      }
    }
    return new ExtractGlimpse(opBuilder.build());
  }
  
  /**
   * @param centered indicates if the offset coordinates are centered relative to
   * the image, in which case the (0, 0) offset is relative to the center
   * of the input images. If false, the (0,0) offset corresponds to the
   * upper left corner of the input images.
   */
  public static Options centered(Boolean centered) {
    return new Options().centered(centered);
  }
  
  /**
   * @param normalized indicates if the offset coordinates are normalized.
   */
  public static Options normalized(Boolean normalized) {
    return new Options().normalized(normalized);
  }
  
  /**
   * @param uniformNoise indicates if the noise should be generated using a
   * uniform distribution or a Gaussian distribution.
   */
  public static Options uniformNoise(Boolean uniformNoise) {
    return new Options().uniformNoise(uniformNoise);
  }
  
  /**
   * @param noise indicates if the noise should `uniform`, `gaussian`, or
   * `zero`. The default is `uniform` which means the the noise type
   * will be decided by `uniform_noise`.
   */
  public static Options noise(String noise) {
    return new Options().noise(noise);
  }
  
  /**
   * A tensor representing the glimpses `[batch_size,
   * glimpse_height, glimpse_width, channels]`.
   */
  public Output<TFloat32> glimpse() {
    return glimpse;
  }
  
  @Override
  public Output<TFloat32> asOutput() {
    return glimpse;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ExtractGlimpseV2";
  
  private Output<TFloat32> glimpse;
  
  private ExtractGlimpse(Operation operation) {
    super(operation);
    int outputIdx = 0;
    glimpse = operation.output(outputIdx++);
  }
}
