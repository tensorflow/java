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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * PNG-encode an image.
 * <p>
 * `image` is a 3-D uint8 or uint16 Tensor of shape `[height, width, channels]`
 * where `channels` is:
 * <ul>
 * <li>
 * 1: for grayscale.
 * </li>
 * <li>
 * 2: for grayscale + alpha.
 * </li>
 * <li>
 * 3: for RGB.
 * </li>
 * <li>
 * 4: for RGBA.
 * </li>
 * </ul>
 * The ZLIB compression level, `compression`, can be -1 for the PNG-encoder
 * default or a value from 0 to 9.  9 is the highest compression level, generating
 * the smallest output, but is slower.
 */
@Operator(group = "image")
public final class EncodePng extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.EncodePng}
   */
  public static class Options {
    
    /**
     * @param compression Compression level.
     */
    public Options compression(Long compression) {
      this.compression = compression;
      return this;
    }
    
    private Long compression;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new EncodePng operation.
   * 
   * @param scope current scope
   * @param image 3-D with shape `[height, width, channels]`.
   * @param options carries optional attributes values
   * @return a new instance of EncodePng
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> EncodePng create(Scope scope, Operand<T> image, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("EncodePng", scope.makeOpName("EncodePng"));
    opBuilder.addInput(image.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.compression != null) {
          opBuilder.setAttr("compression", opts.compression);
        }
      }
    }
    return new EncodePng(opBuilder.build());
  }
  
  /**
   * @param compression Compression level.
   */
  public static Options compression(Long compression) {
    return new Options().compression(compression);
  }
  
  /**
   * 0-D. PNG-encoded image.
   */
  public Output<TString> contents() {
    return contents;
  }
  
  @Override
  public Output<TString> asOutput() {
    return contents;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "EncodePng";
  
  private Output<TString> contents;
  
  private EncodePng(Operation operation) {
    super(operation);
    int outputIdx = 0;
    contents = operation.output(outputIdx++);
  }
}
