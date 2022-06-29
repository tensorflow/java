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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;

/**
 * JPEG encode input image with provided compression quality.
 * {@code image} is a 3-D uint8 Tensor of shape {@code [height, width, channels]}.
 * {@code quality} is an int32 jpeg compression quality value between 0 and 100.
 */
@OpMetadata(
    opType = EncodeJpegVariableQuality.OP_NAME,
    inputsClass = EncodeJpegVariableQuality.Inputs.class
)
@Operator(
    group = "image"
)
public final class EncodeJpegVariableQuality extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "EncodeJpegVariableQuality";

  private Output<TString> contents;

  public EncodeJpegVariableQuality(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    contents = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new EncodeJpegVariableQuality operation.
   *
   * @param scope current scope
   * @param images Images to adjust.  At least 3-D.
   * @param quality An int quality to encode to.
   * @return a new instance of EncodeJpegVariableQuality
   */
  @Endpoint(
      describeByClass = true
  )
  public static EncodeJpegVariableQuality create(Scope scope, Operand<TUint8> images,
      Operand<TInt32> quality) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "EncodeJpegVariableQuality");
    opBuilder.addInput(images.asOutput());
    opBuilder.addInput(quality.asOutput());
    return new EncodeJpegVariableQuality(opBuilder.build());
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

  @OpInputsMetadata(
      outputsClass = EncodeJpegVariableQuality.class
  )
  public static class Inputs extends RawOpInputs<EncodeJpegVariableQuality> {
    /**
     * Images to adjust.  At least 3-D.
     */
    public final Operand<TUint8> images;

    /**
     * An int quality to encode to.
     */
    public final Operand<TInt32> quality;

    public Inputs(GraphOperation op) {
      super(new EncodeJpegVariableQuality(op), op, Arrays.asList());
      int inputIndex = 0;
      images = (Operand<TUint8>) op.input(inputIndex++);
      quality = (Operand<TInt32>) op.input(inputIndex++);
    }
  }
}
