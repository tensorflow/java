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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;

/**
 * JPEG encode input image with provided compression quality.
 * <p>
 * `image` is a 3-D uint8 Tensor of shape `[height, width, channels]`.
 * `quality` is an int32 jpeg compression quality value between 0 and 100.
 * 
 */
@Operator(group = "image")
public final class EncodeJpegVariableQuality extends RawOp implements Operand<TString> {
  
  /**
   * Factory method to create a class wrapping a new EncodeJpegVariableQuality operation.
   * 
   * @param scope current scope
   * @param images Images to adjust.  At least 3-D.
   * @param quality An int quality to encode to.
   * @return a new instance of EncodeJpegVariableQuality
   */
  @Endpoint(describeByClass = true)
  public static EncodeJpegVariableQuality create(Scope scope, Operand<TUint8> images, Operand<TInt32> quality) {
    OperationBuilder opBuilder = scope.env().opBuilder("EncodeJpegVariableQuality", scope.makeOpName("EncodeJpegVariableQuality"));
    opBuilder.addInput(images.asOutput());
    opBuilder.addInput(quality.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new EncodeJpegVariableQuality(opBuilder.build());
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
  public static final String OP_NAME = "EncodeJpegVariableQuality";
  
  private Output<TString> contents;
  
  private EncodeJpegVariableQuality(Operation operation) {
    super(operation);
    int outputIdx = 0;
    contents = operation.output(outputIdx++);
  }
}
