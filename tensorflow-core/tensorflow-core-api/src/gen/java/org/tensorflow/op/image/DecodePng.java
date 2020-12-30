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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TNumber;

/**
 * Decode a PNG-encoded image to a uint8 or uint16 tensor.
 * <p>
 * The attr `channels` indicates the desired number of color channels for the
 * decoded image.
 * <p>
 * Accepted values are:
 * <ul>
 * <li>
 * 0: Use the number of channels in the PNG-encoded image.
 * </li>
 * <li>
 * 1: output a grayscale image.
 * </li>
 * <li>
 * 3: output an RGB image.
 * </li>
 * <li>
 * 4: output an RGBA image.
 * </li>
 * </ul>
 * If needed, the PNG-encoded image is transformed to match the requested number
 * of color channels.
 * <p>
 * This op also supports decoding JPEGs and non-animated GIFs since the interface
 * is the same, though it is cleaner to use `tf.io.decode_image`.
 * 
 * @param <T> data type for {@code image()} output
 */
@Operator(group = "image")
public final class DecodePng<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.DecodePng}
   */
  public static class Options {
    
    /**
     * @param channels Number of color channels for the decoded image.
     */
    public Options channels(Long channels) {
      this.channels = channels;
      return this;
    }
    
    private Long channels;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new DecodePng operation.
   * 
   * @param scope current scope
   * @param contents 0-D.  The PNG-encoded image.
   * @param dtype 
   * @param options carries optional attributes values
   * @return a new instance of DecodePng
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> DecodePng<T> create(Scope scope, Operand<TString> contents, Class<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DecodePng", scope.makeOpName("DecodePng"));
    opBuilder.addInput(contents.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.channels != null) {
          opBuilder.setAttr("channels", opts.channels);
        }
      }
    }
    return new DecodePng<T>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new DecodePng operation using default output types.
   * 
   * @param scope current scope
   * @param contents 0-D.  The PNG-encoded image.
   * @param options carries optional attributes values
   * @return a new instance of DecodePng
   */
  @Endpoint(describeByClass = true)
  public static DecodePng<TUint8> create(Scope scope, Operand<TString> contents, Options... options) {
    return create(scope, contents, TUint8.class, options);
  }
  
  /**
   * @param channels Number of color channels for the decoded image.
   */
  public static Options channels(Long channels) {
    return new Options().channels(channels);
  }
  
  /**
   * 3-D with shape `[height, width, channels]`.
   */
  public Output<T> image() {
    return image;
  }
  
  @Override
  public Output<T> asOutput() {
    return image;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DecodePng";
  
  private Output<T> image;
  
  private DecodePng(Operation operation) {
    super(operation);
    int outputIdx = 0;
    image = operation.output(outputIdx++);
  }
}
