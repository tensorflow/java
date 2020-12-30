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

package org.tensorflow.op.summary;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.Tensor;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * Outputs a `Summary` protocol buffer with images.
 * <p>
 * The summary has up to `max_images` summary values containing images. The
 * images are built from `tensor` which must be 4-D with shape `[batch_size,
 * height, width, channels]` and where `channels` can be:
 * <ul>
 * <li>
 * 1: `tensor` is interpreted as Grayscale.
 * </li>
 * <li>
 * 3: `tensor` is interpreted as RGB.
 * </li>
 * <li>
 * 4: `tensor` is interpreted as RGBA.
 * </li>
 * </ul>
 * The images have the same number of channels as the input tensor. For float
 * input, the values are normalized one image at a time to fit in the range
 * `[0, 255]`.  `uint8` values are unchanged.  The op uses two different
 * normalization algorithms:
 * <ul>
 * <li>
 * If the input values are all positive, they are rescaled so the largest one
 *    is 255.
 * </li>
 * <li>
 * If any input value is negative, the values are shifted so input value 0.0
 *    is at 127.  They are then rescaled so that either the smallest value is 0,
 *    or the largest one is 255.
 * </li>
 * </ul>
 * The `tag` argument is a scalar `Tensor` of type `string`.  It is used to
 * build the `tag` of the summary values:
 * <ul>
 * <li>
 * If `max_images` is 1, the summary value tag is '<i>tag</i>/image'.
 * </li>
 * <li>
 * If `max_images` is greater than 1, the summary value tags are
 *    generated sequentially as '<i>tag</i>/image/0', '<i>tag</i>/image/1', etc.
 * </li>
 * </ul>
 * The `bad_color` argument is the color to use in the generated images for
 * non-finite input values.  It is a `uint8` 1-D tensor of length `channels`.
 * Each element must be in the range `[0, 255]` (It represents the value of a
 * pixel in the output image).  Non-finite values in the input tensor are
 * replaced by this tensor in the output image.  The default value is the color
 * red.
 */
@Operator(group = "summary")
public final class ImageSummary extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.summary.ImageSummary}
   */
  public static class Options {
    
    /**
     * @param maxImages Max number of batch elements to generate images for.
     */
    public Options maxImages(Long maxImages) {
      this.maxImages = maxImages;
      return this;
    }
    
    /**
     * @param badColor Color to use for pixels with non-finite values.
     */
    public Options badColor(Tensor badColor) {
      this.badColor = badColor;
      return this;
    }
    
    private Long maxImages;
    private Tensor badColor;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ImageSummary operation.
   * 
   * @param scope current scope
   * @param tag Scalar. Used to build the `tag` attribute of the summary values.
   * @param tensor 4-D of shape `[batch_size, height, width, channels]` where
   * `channels` is 1, 3, or 4.
   * @param options carries optional attributes values
   * @return a new instance of ImageSummary
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> ImageSummary create(Scope scope, Operand<TString> tag, Operand<T> tensor, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ImageSummary", scope.makeOpName("ImageSummary"));
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.maxImages != null) {
          opBuilder.setAttr("max_images", opts.maxImages);
        }
        if (opts.badColor != null) {
          opBuilder.setAttr("bad_color", opts.badColor);
        }
      }
    }
    return new ImageSummary(opBuilder.build());
  }
  
  /**
   * @param maxImages Max number of batch elements to generate images for.
   */
  public static Options maxImages(Long maxImages) {
    return new Options().maxImages(maxImages);
  }
  
  /**
   * @param badColor Color to use for pixels with non-finite values.
   */
  public static Options badColor(Tensor badColor) {
    return new Options().badColor(badColor);
  }
  
  /**
   * Scalar. Serialized `Summary` protocol buffer.
   */
  public Output<TString> summary() {
    return summary;
  }
  
  @Override
  public Output<TString> asOutput() {
    return summary;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ImageSummary";
  
  private Output<TString> summary;
  
  private ImageSummary(Operation operation) {
    super(operation);
    int outputIdx = 0;
    summary = operation.output(outputIdx++);
  }
}
