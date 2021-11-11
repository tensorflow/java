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
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;

/**
 * Decode a JPEG-encoded image to a uint8 tensor.
 * The attr {@code channels} indicates the desired number of color channels for the
 * decoded image.
 * <p>Accepted values are:
 * <ul>
 * <li>0: Use the number of channels in the JPEG-encoded image.</li>
 * <li>1: output a grayscale image.</li>
 * <li>3: output an RGB image.</li>
 * </ul>
 * <p>If needed, the JPEG-encoded image is transformed to match the requested number
 * of color channels.
 * <p>The attr {@code ratio} allows downscaling the image by an integer factor during
 * decoding.  Allowed values are: 1, 2, 4, and 8.  This is much faster than
 * downscaling the image later.
 * <p>This op also supports decoding PNGs and non-animated GIFs since the interface is
 * the same, though it is cleaner to use {@code tf.io.decode_image}.
 */
@OpMetadata(
    opType = DecodeJpeg.OP_NAME,
    inputsClass = DecodeJpeg.Inputs.class
)
@Operator(
    group = "image"
)
public final class DecodeJpeg extends RawOp implements Operand<TUint8> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DecodeJpeg";

  private Output<TUint8> image;

  public DecodeJpeg(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    image = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DecodeJpeg operation.
   *
   * @param scope current scope
   * @param contents 0-D.  The JPEG-encoded image.
   * @param options carries optional attribute values
   * @return a new instance of DecodeJpeg
   */
  @Endpoint(
      describeByClass = true
  )
  public static DecodeJpeg create(Scope scope, Operand<TString> contents, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DecodeJpeg");
    opBuilder.addInput(contents.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.channels != null) {
          opBuilder.setAttr("channels", opts.channels);
        }
        if (opts.ratio != null) {
          opBuilder.setAttr("ratio", opts.ratio);
        }
        if (opts.fancyUpscaling != null) {
          opBuilder.setAttr("fancy_upscaling", opts.fancyUpscaling);
        }
        if (opts.tryRecoverTruncated != null) {
          opBuilder.setAttr("try_recover_truncated", opts.tryRecoverTruncated);
        }
        if (opts.acceptableFraction != null) {
          opBuilder.setAttr("acceptable_fraction", opts.acceptableFraction);
        }
        if (opts.dctMethod != null) {
          opBuilder.setAttr("dct_method", opts.dctMethod);
        }
      }
    }
    return new DecodeJpeg(opBuilder.build());
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
   * Sets the ratio option.
   *
   * @param ratio Downscaling ratio.
   * @return this Options instance.
   */
  public static Options ratio(Long ratio) {
    return new Options().ratio(ratio);
  }

  /**
   * Sets the fancyUpscaling option.
   *
   * @param fancyUpscaling If true use a slower but nicer upscaling of the
   * chroma planes (yuv420/422 only).
   * @return this Options instance.
   */
  public static Options fancyUpscaling(Boolean fancyUpscaling) {
    return new Options().fancyUpscaling(fancyUpscaling);
  }

  /**
   * Sets the tryRecoverTruncated option.
   *
   * @param tryRecoverTruncated If true try to recover an image from truncated input.
   * @return this Options instance.
   */
  public static Options tryRecoverTruncated(Boolean tryRecoverTruncated) {
    return new Options().tryRecoverTruncated(tryRecoverTruncated);
  }

  /**
   * Sets the acceptableFraction option.
   *
   * @param acceptableFraction The minimum required fraction of lines before a truncated
   * input is accepted.
   * @return this Options instance.
   */
  public static Options acceptableFraction(Float acceptableFraction) {
    return new Options().acceptableFraction(acceptableFraction);
  }

  /**
   * Sets the dctMethod option.
   *
   * @param dctMethod string specifying a hint about the algorithm used for
   * decompression.  Defaults to &quot;&quot; which maps to a system-specific
   * default.  Currently valid values are [&quot;INTEGER_FAST&quot;,
   * &quot;INTEGER_ACCURATE&quot;].  The hint may be ignored (e.g., the internal
   * jpeg library changes to a version that does not have that specific
   * option.)
   * @return this Options instance.
   */
  public static Options dctMethod(String dctMethod) {
    return new Options().dctMethod(dctMethod);
  }

  /**
   * Gets image.
   * 3-D with shape {@code [height, width, channels]}..
   * @return image.
   */
  public Output<TUint8> image() {
    return image;
  }

  @Override
  public Output<TUint8> asOutput() {
    return image;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.DecodeJpeg}
   */
  public static class Options {
    private Long channels;

    private Long ratio;

    private Boolean fancyUpscaling;

    private Boolean tryRecoverTruncated;

    private Float acceptableFraction;

    private String dctMethod;

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
     * Sets the ratio option.
     *
     * @param ratio Downscaling ratio.
     * @return this Options instance.
     */
    public Options ratio(Long ratio) {
      this.ratio = ratio;
      return this;
    }

    /**
     * Sets the fancyUpscaling option.
     *
     * @param fancyUpscaling If true use a slower but nicer upscaling of the
     * chroma planes (yuv420/422 only).
     * @return this Options instance.
     */
    public Options fancyUpscaling(Boolean fancyUpscaling) {
      this.fancyUpscaling = fancyUpscaling;
      return this;
    }

    /**
     * Sets the tryRecoverTruncated option.
     *
     * @param tryRecoverTruncated If true try to recover an image from truncated input.
     * @return this Options instance.
     */
    public Options tryRecoverTruncated(Boolean tryRecoverTruncated) {
      this.tryRecoverTruncated = tryRecoverTruncated;
      return this;
    }

    /**
     * Sets the acceptableFraction option.
     *
     * @param acceptableFraction The minimum required fraction of lines before a truncated
     * input is accepted.
     * @return this Options instance.
     */
    public Options acceptableFraction(Float acceptableFraction) {
      this.acceptableFraction = acceptableFraction;
      return this;
    }

    /**
     * Sets the dctMethod option.
     *
     * @param dctMethod string specifying a hint about the algorithm used for
     * decompression.  Defaults to &quot;&quot; which maps to a system-specific
     * default.  Currently valid values are [&quot;INTEGER_FAST&quot;,
     * &quot;INTEGER_ACCURATE&quot;].  The hint may be ignored (e.g., the internal
     * jpeg library changes to a version that does not have that specific
     * option.)
     * @return this Options instance.
     */
    public Options dctMethod(String dctMethod) {
      this.dctMethod = dctMethod;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DecodeJpeg.class
  )
  public static class Inputs extends RawOpInputs<DecodeJpeg> {
    /**
     * 0-D.  The JPEG-encoded image.
     */
    public final Operand<TString> contents;

    /**
     * Number of color channels for the decoded image.
     */
    public final long channels;

    /**
     * Downscaling ratio.
     */
    public final long ratio;

    /**
     * If true use a slower but nicer upscaling of the
     * chroma planes (yuv420/422 only).
     */
    public final boolean fancyUpscaling;

    /**
     * If true try to recover an image from truncated input.
     */
    public final boolean tryRecoverTruncated;

    /**
     * The minimum required fraction of lines before a truncated
     * input is accepted.
     */
    public final float acceptableFraction;

    /**
     * string specifying a hint about the algorithm used for
     * decompression.  Defaults to "" which maps to a system-specific
     * default.  Currently valid values are ["INTEGER_FAST",
     * "INTEGER_ACCURATE"].  The hint may be ignored (e.g., the internal
     * jpeg library changes to a version that does not have that specific
     * option.)
     */
    public final String dctMethod;

    public Inputs(GraphOperation op) {
      super(new DecodeJpeg(op), op, Arrays.asList("channels", "ratio", "fancy_upscaling", "try_recover_truncated", "acceptable_fraction", "dct_method"));
      int inputIndex = 0;
      contents = (Operand<TString>) op.input(inputIndex++);
      channels = op.attributes().getAttrInt("channels");
      ratio = op.attributes().getAttrInt("ratio");
      fancyUpscaling = op.attributes().getAttrBool("fancy_upscaling");
      tryRecoverTruncated = op.attributes().getAttrBool("try_recover_truncated");
      acceptableFraction = op.attributes().getAttrFloat("acceptable_fraction");
      dctMethod = op.attributes().getAttrString("dct_method");
    }
  }
}
