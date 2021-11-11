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
 * JPEG-encode an image.
 * {@code image} is a 3-D uint8 Tensor of shape {@code [height, width, channels]}.
 * <p>The attr {@code format} can be used to override the color format of the encoded
 * output.  Values can be:
 * <ul>
 * <li>{@code ''}: Use a default format based on the number of channels in the image.</li>
 * <li>{@code grayscale}: Output a grayscale JPEG image.  The {@code channels} dimension
 * of {@code image} must be 1.</li>
 * <li>{@code rgb}: Output an RGB JPEG image. The {@code channels} dimension
 * of {@code image} must be 3.</li>
 * </ul>
 * <p>If {@code format} is not specified or is the empty string, a default format is picked
 * in function of the number of channels in {@code image}:
 * <ul>
 * <li>1: Output a grayscale image.</li>
 * <li>3: Output an RGB image.</li>
 * </ul>
 */
@OpMetadata(
    opType = EncodeJpeg.OP_NAME,
    inputsClass = EncodeJpeg.Inputs.class
)
@Operator(
    group = "image"
)
public final class EncodeJpeg extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "EncodeJpeg";

  private Output<TString> contents;

  public EncodeJpeg(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    contents = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new EncodeJpeg operation.
   *
   * @param scope current scope
   * @param image 3-D with shape {@code [height, width, channels]}.
   * @param options carries optional attribute values
   * @return a new instance of EncodeJpeg
   */
  @Endpoint(
      describeByClass = true
  )
  public static EncodeJpeg create(Scope scope, Operand<TUint8> image, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "EncodeJpeg");
    opBuilder.addInput(image.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.format != null) {
          opBuilder.setAttr("format", opts.format);
        }
        if (opts.quality != null) {
          opBuilder.setAttr("quality", opts.quality);
        }
        if (opts.progressive != null) {
          opBuilder.setAttr("progressive", opts.progressive);
        }
        if (opts.optimizeSize != null) {
          opBuilder.setAttr("optimize_size", opts.optimizeSize);
        }
        if (opts.chromaDownsampling != null) {
          opBuilder.setAttr("chroma_downsampling", opts.chromaDownsampling);
        }
        if (opts.densityUnit != null) {
          opBuilder.setAttr("density_unit", opts.densityUnit);
        }
        if (opts.xDensity != null) {
          opBuilder.setAttr("x_density", opts.xDensity);
        }
        if (opts.yDensity != null) {
          opBuilder.setAttr("y_density", opts.yDensity);
        }
        if (opts.xmpMetadata != null) {
          opBuilder.setAttr("xmp_metadata", opts.xmpMetadata);
        }
      }
    }
    return new EncodeJpeg(opBuilder.build());
  }

  /**
   * Sets the format option.
   *
   * @param format Per pixel image format.
   * @return this Options instance.
   */
  public static Options format(String format) {
    return new Options().format(format);
  }

  /**
   * Sets the quality option.
   *
   * @param quality Quality of the compression from 0 to 100 (higher is better and slower).
   * @return this Options instance.
   */
  public static Options quality(Long quality) {
    return new Options().quality(quality);
  }

  /**
   * Sets the progressive option.
   *
   * @param progressive If True, create a JPEG that loads progressively (coarse to fine).
   * @return this Options instance.
   */
  public static Options progressive(Boolean progressive) {
    return new Options().progressive(progressive);
  }

  /**
   * Sets the optimizeSize option.
   *
   * @param optimizeSize If True, spend CPU/RAM to reduce size with no quality change.
   * @return this Options instance.
   */
  public static Options optimizeSize(Boolean optimizeSize) {
    return new Options().optimizeSize(optimizeSize);
  }

  /**
   * Sets the chromaDownsampling option.
   *
   * @param chromaDownsampling See http://en.wikipedia.org/wiki/Chroma_subsampling.
   * @return this Options instance.
   */
  public static Options chromaDownsampling(Boolean chromaDownsampling) {
    return new Options().chromaDownsampling(chromaDownsampling);
  }

  /**
   * Sets the densityUnit option.
   *
   * @param densityUnit Unit used to specify {@code x_density} and {@code y_density}:
   * pixels per inch ({@code 'in'}) or centimeter ({@code 'cm'}).
   * @return this Options instance.
   */
  public static Options densityUnit(String densityUnit) {
    return new Options().densityUnit(densityUnit);
  }

  /**
   * Sets the xDensity option.
   *
   * @param xDensity Horizontal pixels per density unit.
   * @return this Options instance.
   */
  public static Options xDensity(Long xDensity) {
    return new Options().xDensity(xDensity);
  }

  /**
   * Sets the yDensity option.
   *
   * @param yDensity Vertical pixels per density unit.
   * @return this Options instance.
   */
  public static Options yDensity(Long yDensity) {
    return new Options().yDensity(yDensity);
  }

  /**
   * Sets the xmpMetadata option.
   *
   * @param xmpMetadata If not empty, embed this XMP metadata in the image header.
   * @return this Options instance.
   */
  public static Options xmpMetadata(String xmpMetadata) {
    return new Options().xmpMetadata(xmpMetadata);
  }

  /**
   * Gets contents.
   * 0-D. JPEG-encoded image.
   * @return contents.
   */
  public Output<TString> contents() {
    return contents;
  }

  @Override
  public Output<TString> asOutput() {
    return contents;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.EncodeJpeg}
   */
  public static class Options {
    private String format;

    private Long quality;

    private Boolean progressive;

    private Boolean optimizeSize;

    private Boolean chromaDownsampling;

    private String densityUnit;

    private Long xDensity;

    private Long yDensity;

    private String xmpMetadata;

    private Options() {
    }

    /**
     * Sets the format option.
     *
     * @param format Per pixel image format.
     * @return this Options instance.
     */
    public Options format(String format) {
      this.format = format;
      return this;
    }

    /**
     * Sets the quality option.
     *
     * @param quality Quality of the compression from 0 to 100 (higher is better and slower).
     * @return this Options instance.
     */
    public Options quality(Long quality) {
      this.quality = quality;
      return this;
    }

    /**
     * Sets the progressive option.
     *
     * @param progressive If True, create a JPEG that loads progressively (coarse to fine).
     * @return this Options instance.
     */
    public Options progressive(Boolean progressive) {
      this.progressive = progressive;
      return this;
    }

    /**
     * Sets the optimizeSize option.
     *
     * @param optimizeSize If True, spend CPU/RAM to reduce size with no quality change.
     * @return this Options instance.
     */
    public Options optimizeSize(Boolean optimizeSize) {
      this.optimizeSize = optimizeSize;
      return this;
    }

    /**
     * Sets the chromaDownsampling option.
     *
     * @param chromaDownsampling See http://en.wikipedia.org/wiki/Chroma_subsampling.
     * @return this Options instance.
     */
    public Options chromaDownsampling(Boolean chromaDownsampling) {
      this.chromaDownsampling = chromaDownsampling;
      return this;
    }

    /**
     * Sets the densityUnit option.
     *
     * @param densityUnit Unit used to specify {@code x_density} and {@code y_density}:
     * pixels per inch ({@code 'in'}) or centimeter ({@code 'cm'}).
     * @return this Options instance.
     */
    public Options densityUnit(String densityUnit) {
      this.densityUnit = densityUnit;
      return this;
    }

    /**
     * Sets the xDensity option.
     *
     * @param xDensity Horizontal pixels per density unit.
     * @return this Options instance.
     */
    public Options xDensity(Long xDensity) {
      this.xDensity = xDensity;
      return this;
    }

    /**
     * Sets the yDensity option.
     *
     * @param yDensity Vertical pixels per density unit.
     * @return this Options instance.
     */
    public Options yDensity(Long yDensity) {
      this.yDensity = yDensity;
      return this;
    }

    /**
     * Sets the xmpMetadata option.
     *
     * @param xmpMetadata If not empty, embed this XMP metadata in the image header.
     * @return this Options instance.
     */
    public Options xmpMetadata(String xmpMetadata) {
      this.xmpMetadata = xmpMetadata;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = EncodeJpeg.class
  )
  public static class Inputs extends RawOpInputs<EncodeJpeg> {
    /**
     * 3-D with shape {@code [height, width, channels]}.
     */
    public final Operand<TUint8> image;

    /**
     * Per pixel image format.
     */
    public final String format;

    /**
     * Quality of the compression from 0 to 100 (higher is better and slower).
     */
    public final long quality;

    /**
     * If True, create a JPEG that loads progressively (coarse to fine).
     */
    public final boolean progressive;

    /**
     * If True, spend CPU/RAM to reduce size with no quality change.
     */
    public final boolean optimizeSize;

    /**
     * See http://en.wikipedia.org/wiki/Chroma_subsampling.
     */
    public final boolean chromaDownsampling;

    /**
     * Unit used to specify `x_density` and `y_density`:
     * pixels per inch (`'in'`) or centimeter (`'cm'`).
     */
    public final String densityUnit;

    /**
     * Horizontal pixels per density unit.
     */
    public final long xDensity;

    /**
     * Vertical pixels per density unit.
     */
    public final long yDensity;

    /**
     * If not empty, embed this XMP metadata in the image header.
     */
    public final String xmpMetadata;

    public Inputs(GraphOperation op) {
      super(new EncodeJpeg(op), op, Arrays.asList("format", "quality", "progressive", "optimize_size", "chroma_downsampling", "density_unit", "x_density", "y_density", "xmp_metadata"));
      int inputIndex = 0;
      image = (Operand<TUint8>) op.input(inputIndex++);
      format = op.attributes().getAttrString("format");
      quality = op.attributes().getAttrInt("quality");
      progressive = op.attributes().getAttrBool("progressive");
      optimizeSize = op.attributes().getAttrBool("optimize_size");
      chromaDownsampling = op.attributes().getAttrBool("chroma_downsampling");
      densityUnit = op.attributes().getAttrString("density_unit");
      xDensity = op.attributes().getAttrInt("x_density");
      yDensity = op.attributes().getAttrInt("y_density");
      xmpMetadata = op.attributes().getAttrString("xmp_metadata");
    }
  }
}
