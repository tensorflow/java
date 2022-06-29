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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.Tensor;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * Outputs a {@code Summary} protocol buffer with images.
 * The summary has up to {@code max_images} summary values containing images. The
 * images are built from {@code tensor} which must be 4-D with shape {@code [batch_size, height, width, channels]} and where {@code channels} can be:
 * <ul>
 * <li>1: {@code tensor} is interpreted as Grayscale.</li>
 * <li>3: {@code tensor} is interpreted as RGB.</li>
 * <li>4: {@code tensor} is interpreted as RGBA.</li>
 * </ul>
 * <p>The images have the same number of channels as the input tensor. For float
 * input, the values are normalized one image at a time to fit in the range
 * {@code [0, 255]}.  {@code uint8} values are unchanged.  The op uses two different
 * normalization algorithms:
 * <ul>
 * <li>
 * <p>If the input values are all positive, they are rescaled so the largest one
 * is 255.
 * </li>
 * <li>
 * <p>If any input value is negative, the values are shifted so input value 0.0
 * is at 127.  They are then rescaled so that either the smallest value is 0,
 * or the largest one is 255.
 * </li>
 * </ul>
 * <p>The {@code tag} argument is a scalar {@code Tensor} of type {@code string}.  It is used to
 * build the {@code tag} of the summary values:
 * <ul>
 * <li>If {@code max_images} is 1, the summary value tag is '<em>tag</em>/image'.</li>
 * <li>If {@code max_images} is greater than 1, the summary value tags are
 * generated sequentially as '<em>tag</em>/image/0', '<em>tag</em>/image/1', etc.</li>
 * </ul>
 * <p>The {@code bad_color} argument is the color to use in the generated images for
 * non-finite input values.  It is a {@code uint8} 1-D tensor of length {@code channels}.
 * Each element must be in the range {@code [0, 255]} (It represents the value of a
 * pixel in the output image).  Non-finite values in the input tensor are
 * replaced by this tensor in the output image.  The default value is the color
 * red.
 */
@OpMetadata(
    opType = ImageSummary.OP_NAME,
    inputsClass = ImageSummary.Inputs.class
)
@Operator(
    group = "summary"
)
public final class ImageSummary extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ImageSummary";

  private Output<TString> summary;

  public ImageSummary(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    summary = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ImageSummary operation.
   *
   * @param scope current scope
   * @param tag Scalar. Used to build the {@code tag} attribute of the summary values.
   * @param tensor 4-D of shape {@code [batch_size, height, width, channels]} where
   * {@code channels} is 1, 3, or 4.
   * @param options carries optional attribute values
   * @return a new instance of ImageSummary
   */
  @Endpoint(
      describeByClass = true
  )
  public static ImageSummary create(Scope scope, Operand<TString> tag,
      Operand<? extends TNumber> tensor, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ImageSummary");
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(tensor.asOutput());
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
   * Sets the maxImages option.
   *
   * @param maxImages Max number of batch elements to generate images for.
   * @return this Options instance.
   */
  public static Options maxImages(Long maxImages) {
    return new Options().maxImages(maxImages);
  }

  /**
   * Sets the badColor option.
   *
   * @param badColor Color to use for pixels with non-finite values.
   * @return this Options instance.
   */
  public static Options badColor(Tensor badColor) {
    return new Options().badColor(badColor);
  }

  /**
   * Gets summary.
   * Scalar. Serialized {@code Summary} protocol buffer.
   * @return summary.
   */
  public Output<TString> summary() {
    return summary;
  }

  @Override
  public Output<TString> asOutput() {
    return summary;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.summary.ImageSummary}
   */
  public static class Options {
    private Long maxImages;

    private Tensor badColor;

    private Options() {
    }

    /**
     * Sets the maxImages option.
     *
     * @param maxImages Max number of batch elements to generate images for.
     * @return this Options instance.
     */
    public Options maxImages(Long maxImages) {
      this.maxImages = maxImages;
      return this;
    }

    /**
     * Sets the badColor option.
     *
     * @param badColor Color to use for pixels with non-finite values.
     * @return this Options instance.
     */
    public Options badColor(Tensor badColor) {
      this.badColor = badColor;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ImageSummary.class
  )
  public static class Inputs extends RawOpInputs<ImageSummary> {
    /**
     * Scalar. Used to build the {@code tag} attribute of the summary values.
     */
    public final Operand<TString> tag;

    /**
     * 4-D of shape {@code [batch_size, height, width, channels]} where
     * {@code channels} is 1, 3, or 4.
     */
    public final Operand<? extends TNumber> tensor;

    /**
     * Max number of batch elements to generate images for.
     */
    public final long maxImages;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Color to use for pixels with non-finite values.
     */
    public final Tensor badColor;

    public Inputs(GraphOperation op) {
      super(new ImageSummary(op), op, Arrays.asList("max_images", "T", "bad_color"));
      int inputIndex = 0;
      tag = (Operand<TString>) op.input(inputIndex++);
      tensor = (Operand<? extends TNumber>) op.input(inputIndex++);
      maxImages = op.attributes().getAttrInt("max_images");
      T = op.attributes().getAttrType("T");
      badColor = op.attributes().getAttrTensor("bad_color");
    }
  }
}
