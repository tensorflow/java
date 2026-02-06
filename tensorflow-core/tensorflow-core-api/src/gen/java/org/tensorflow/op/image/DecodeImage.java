/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TNumber;

/**
 * Function for decode_bmp, decode_gif, decode_jpeg, decode_webp, and decode_png.
 * Detects whether an image is a BMP, GIF, JPEG, WebP, or PNG, and performs the
 * appropriate operation to convert the input bytes string into a Tensor of type
 * dtype.
 * <p><em>NOTE</em>: decode_gif and decode_webp return a 4-D
 * array [num_frames, height, width, 3], as opposed to decode_bmp,
 * decode_jpeg, and decode_png, which always return 3-D arrays [height,
 * width, num_channels]. Make sure to take this into account when
 * constructing your graph if you are intermixing animated files with
 * BMP, JPEG, and/or PNG files. Alternately, set the expand_animations
 * argument of this function to False, in which case the op will return
 * 3-dimensional tensors and will truncate animations to the first frame.
 * <p><em>NOTE</em>: If the first frame of an animated GIF does not occupy the entire
 * canvas (maximum frame width x maximum frame height), then it fills the
 * unoccupied areas (in the first frame) with zeros (black). For frames after the
 * first frame that does not occupy the entire canvas, it uses the previous
 * frame to fill the unoccupied areas.
 */
@OpMetadata(
    opType = DecodeImage.OP_NAME,
    inputsClass = DecodeImage.Inputs.class
)
@Operator(
    group = "image"
)
public final class DecodeImage<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DecodeImage";

  private Output<T> image;

  public DecodeImage(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    image = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DecodeImage operation.
   *
   * @param scope current scope
   * @param contents 0-D. The encoded image bytes.
   * @param dtype The desired DType of the returned Tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code DecodeImage} output and operands
   * @return a new instance of DecodeImage
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> DecodeImage<T> create(Scope scope, Operand<TString> contents,
      Class<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DecodeImage");
    opBuilder.addInput(contents.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.channels != null) {
          opBuilder.setAttr("channels", opts.channels);
        }
        if (opts.expandAnimations != null) {
          opBuilder.setAttr("expand_animations", opts.expandAnimations);
        }
      }
    }
    return new DecodeImage<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new DecodeImage operation, with the default output types.
   *
   * @param scope current scope
   * @param contents 0-D. The encoded image bytes.
   * @param options carries optional attribute values
   * @return a new instance of DecodeImage, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static DecodeImage<TUint8> create(Scope scope, Operand<TString> contents,
      Options... options) {
    return create(scope, contents, TUint8.class, options);
  }

  /**
   * Sets the channels option.
   *
   * @param channels Number of color channels for the decoded image.
   * @return this Options instance.
   */
  public static Options channels(Long channels) {
    return new Options().channels(channels);
  }

  /**
   * Sets the expandAnimations option.
   *
   * @param expandAnimations Controls the output shape of the returned op. If True, the returned op
   * will produce a 3-D tensor for PNG, JPEG, and BMP files; and a 4-D
   * tensor for all GIFs and WebP images, whether animated or not. If,
   * False, the returned op will produce a 3-D tensor for all file types
   * and will truncate animated images to the first frame.
   * @return this Options instance.
   */
  public static Options expandAnimations(Boolean expandAnimations) {
    return new Options().expandAnimations(expandAnimations);
  }

  /**
   * Gets image.
   * 3-D with shape {@code [height, width, channels]} or 4-D with shape
   * {@code [frame, height, width, channels]}..
   * @return image.
   */
  public Output<T> image() {
    return image;
  }

  @Override
  public Output<T> asOutput() {
    return image;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.DecodeImage}
   */
  public static class Options {
    private Long channels;

    private Boolean expandAnimations;

    private Options() {
    }

    /**
     * Sets the channels option.
     *
     * @param channels Number of color channels for the decoded image.
     * @return this Options instance.
     */
    public Options channels(Long channels) {
      this.channels = channels;
      return this;
    }

    /**
     * Sets the expandAnimations option.
     *
     * @param expandAnimations Controls the output shape of the returned op. If True, the returned op
     * will produce a 3-D tensor for PNG, JPEG, and BMP files; and a 4-D
     * tensor for all GIFs and WebP images, whether animated or not. If,
     * False, the returned op will produce a 3-D tensor for all file types
     * and will truncate animated images to the first frame.
     * @return this Options instance.
     */
    public Options expandAnimations(Boolean expandAnimations) {
      this.expandAnimations = expandAnimations;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DecodeImage.class
  )
  public static class Inputs extends RawOpInputs<DecodeImage<?>> {
    /**
     * 0-D. The encoded image bytes.
     */
    public final Operand<TString> contents;

    /**
     * Number of color channels for the decoded image.
     */
    public final long channels;

    /**
     * The desired DType of the returned Tensor.
     */
    public final DataType dtype;

    /**
     * Controls the output shape of the returned op. If True, the returned op
     * will produce a 3-D tensor for PNG, JPEG, and BMP files; and a 4-D
     * tensor for all GIFs and WebP images, whether animated or not. If,
     * False, the returned op will produce a 3-D tensor for all file types
     * and will truncate animated images to the first frame.
     */
    public final boolean expandAnimations;

    public Inputs(GraphOperation op) {
      super(new DecodeImage<>(op), op, Arrays.asList("channels", "dtype", "expand_animations"));
      int inputIndex = 0;
      contents = (Operand<TString>) op.input(inputIndex++);
      channels = op.attributes().getAttrInt("channels");
      dtype = op.attributes().getAttrType("dtype");
      expandAnimations = op.attributes().getAttrBool("expand_animations");
    }
  }
}
