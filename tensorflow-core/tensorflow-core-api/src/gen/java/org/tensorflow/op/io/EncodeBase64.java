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

package org.tensorflow.op.io;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Encode strings into web-safe base64 format.
 * <p>
 * Refer to the following article for more information on base64 format:
 * en.wikipedia.org/wiki/Base64. Base64 strings may have padding with '=' at the
 * end so that the encoded has length multiple of 4. See Padding section of the
 * link above.
 * <p>
 * Web-safe means that the encoder uses - and _ instead of + and /.
 */
@Operator(group = "io")
public final class EncodeBase64 extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.io.EncodeBase64}
   */
  public static class Options {
    
    /**
     * @param pad Bool whether padding is applied at the ends.
     */
    public Options pad(Boolean pad) {
      this.pad = pad;
      return this;
    }
    
    private Boolean pad;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new EncodeBase64 operation.
   * 
   * @param scope current scope
   * @param input Strings to be encoded.
   * @param options carries optional attributes values
   * @return a new instance of EncodeBase64
   */
  @Endpoint(describeByClass = true)
  public static EncodeBase64 create(Scope scope, Operand<TString> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("EncodeBase64", scope.makeOpName("EncodeBase64"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.pad != null) {
          opBuilder.setAttr("pad", opts.pad);
        }
      }
    }
    return new EncodeBase64(opBuilder.build());
  }
  
  /**
   * @param pad Bool whether padding is applied at the ends.
   */
  public static Options pad(Boolean pad) {
    return new Options().pad(pad);
  }
  
  /**
   * Input strings encoded in base64.
   */
  public Output<TString> output() {
    return output;
  }
  
  @Override
  public Output<TString> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "EncodeBase64";
  
  private Output<TString> output;
  
  private EncodeBase64(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
