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
 * Decode the first frame of a BMP-encoded image to a uint8 tensor.
 * <p>
 * The attr `channels` indicates the desired number of color channels for the
 * decoded image.
 * <p>
 * Accepted values are:
 * <ul>
 * <li>
 * 0: Use the number of channels in the BMP-encoded image.
 * </li>
 * <li>
 * 3: output an RGB image.
 * </li>
 * <li>
 * 4: output an RGBA image.
 */
@Operator(group = "image")
public final class DecodeBmp extends RawOp implements Operand<TUint8> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.DecodeBmp}
   */
  public static class Options {
    
    /**
     * @param channels 
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
   * Factory method to create a class wrapping a new DecodeBmp operation.
   * 
   * @param scope current scope
   * @param contents 0-D.  The BMP-encoded image.
   * @param options carries optional attributes values
   * @return a new instance of DecodeBmp
   */
  @Endpoint(describeByClass = true)
  public static DecodeBmp create(Scope scope, Operand<TString> contents, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DecodeBmp", scope.makeOpName("DecodeBmp"));
    opBuilder.addInput(contents.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.channels != null) {
          opBuilder.setAttr("channels", opts.channels);
        }
      }
    }
    return new DecodeBmp(opBuilder.build());
  }
  
  /**
   * @param channels 
   */
  public static Options channels(Long channels) {
    return new Options().channels(channels);
  }
  
  /**
   * 3-D with shape `[height, width, channels]`. RGB order
   */
  public Output<TUint8> image() {
    return image;
  }
  
  @Override
  public Output<TUint8> asOutput() {
    return image;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DecodeBmp";
  
  private Output<TUint8> image;
  
  private DecodeBmp(Operation operation) {
    super(operation);
    int outputIdx = 0;
    image = operation.output(outputIdx++);
  }
}
