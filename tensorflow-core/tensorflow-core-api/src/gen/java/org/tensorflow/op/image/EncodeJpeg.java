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
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;

/**
 * JPEG-encode an image.
 * <p>
 * `image` is a 3-D uint8 Tensor of shape `[height, width, channels]`.
 * <p>
 * The attr `format` can be used to override the color format of the encoded
 * output.  Values can be:
 * <ul>
 * <li>
 * `''`: Use a default format based on the number of channels in the image.
 * </li>
 * <li>
 * `grayscale`: Output a grayscale JPEG image.  The `channels` dimension
 *     of `image` must be 1.
 * </li>
 * <li>
 * `rgb`: Output an RGB JPEG image. The `channels` dimension
 *     of `image` must be 3.
 * </li>
 * </ul>
 * If `format` is not specified or is the empty string, a default format is picked
 * in function of the number of channels in `image`:
 * <ul>
 * <li>
 * 1: Output a grayscale image.
 * </li>
 * <li>
 * 3: Output an RGB image.
 */
@Operator(group = "image")
public final class EncodeJpeg extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.EncodeJpeg}
   */
  public static class Options {
    
    /**
     * @param format Per pixel image format.
     */
    public Options format(String format) {
      this.format = format;
      return this;
    }
    
    /**
     * @param quality Quality of the compression from 0 to 100 (higher is better and slower).
     */
    public Options quality(Long quality) {
      this.quality = quality;
      return this;
    }
    
    /**
     * @param progressive If True, create a JPEG that loads progressively (coarse to fine).
     */
    public Options progressive(Boolean progressive) {
      this.progressive = progressive;
      return this;
    }
    
    /**
     * @param optimizeSize If True, spend CPU/RAM to reduce size with no quality change.
     */
    public Options optimizeSize(Boolean optimizeSize) {
      this.optimizeSize = optimizeSize;
      return this;
    }
    
    /**
     * @param chromaDownsampling See http://en.wikipedia.org/wiki/Chroma_subsampling.
     */
    public Options chromaDownsampling(Boolean chromaDownsampling) {
      this.chromaDownsampling = chromaDownsampling;
      return this;
    }
    
    /**
     * @param densityUnit Unit used to specify `x_density` and `y_density`:
     * pixels per inch (`'in'`) or centimeter (`'cm'`).
     */
    public Options densityUnit(String densityUnit) {
      this.densityUnit = densityUnit;
      return this;
    }
    
    /**
     * @param xDensity Horizontal pixels per density unit.
     */
    public Options xDensity(Long xDensity) {
      this.xDensity = xDensity;
      return this;
    }
    
    /**
     * @param yDensity Vertical pixels per density unit.
     */
    public Options yDensity(Long yDensity) {
      this.yDensity = yDensity;
      return this;
    }
    
    /**
     * @param xmpMetadata If not empty, embed this XMP metadata in the image header.
     */
    public Options xmpMetadata(String xmpMetadata) {
      this.xmpMetadata = xmpMetadata;
      return this;
    }
    
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
  }
  
  /**
   * Factory method to create a class wrapping a new EncodeJpeg operation.
   * 
   * @param scope current scope
   * @param image 3-D with shape `[height, width, channels]`.
   * @param options carries optional attributes values
   * @return a new instance of EncodeJpeg
   */
  @Endpoint(describeByClass = true)
  public static EncodeJpeg create(Scope scope, Operand<TUint8> image, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("EncodeJpeg", scope.makeOpName("EncodeJpeg"));
    opBuilder.addInput(image.asOutput());
    opBuilder = scope.apply(opBuilder);
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
   * @param format Per pixel image format.
   */
  public static Options format(String format) {
    return new Options().format(format);
  }
  
  /**
   * @param quality Quality of the compression from 0 to 100 (higher is better and slower).
   */
  public static Options quality(Long quality) {
    return new Options().quality(quality);
  }
  
  /**
   * @param progressive If True, create a JPEG that loads progressively (coarse to fine).
   */
  public static Options progressive(Boolean progressive) {
    return new Options().progressive(progressive);
  }
  
  /**
   * @param optimizeSize If True, spend CPU/RAM to reduce size with no quality change.
   */
  public static Options optimizeSize(Boolean optimizeSize) {
    return new Options().optimizeSize(optimizeSize);
  }
  
  /**
   * @param chromaDownsampling See http://en.wikipedia.org/wiki/Chroma_subsampling.
   */
  public static Options chromaDownsampling(Boolean chromaDownsampling) {
    return new Options().chromaDownsampling(chromaDownsampling);
  }
  
  /**
   * @param densityUnit Unit used to specify `x_density` and `y_density`:
   * pixels per inch (`'in'`) or centimeter (`'cm'`).
   */
  public static Options densityUnit(String densityUnit) {
    return new Options().densityUnit(densityUnit);
  }
  
  /**
   * @param xDensity Horizontal pixels per density unit.
   */
  public static Options xDensity(Long xDensity) {
    return new Options().xDensity(xDensity);
  }
  
  /**
   * @param yDensity Vertical pixels per density unit.
   */
  public static Options yDensity(Long yDensity) {
    return new Options().yDensity(yDensity);
  }
  
  /**
   * @param xmpMetadata If not empty, embed this XMP metadata in the image header.
   */
  public static Options xmpMetadata(String xmpMetadata) {
    return new Options().xmpMetadata(xmpMetadata);
  }
  
  /**
   * 0-D. JPEG-encoded image.
   */
  public Output<TString> contents() {
    return contents;
  }
  
  @Override
  public Output<TString> asOutput() {
    return contents;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "EncodeJpeg";
  
  private Output<TString> contents;
  
  private EncodeJpeg(Operation operation) {
    super(operation);
    int outputIdx = 0;
    contents = operation.output(outputIdx++);
  }
}
