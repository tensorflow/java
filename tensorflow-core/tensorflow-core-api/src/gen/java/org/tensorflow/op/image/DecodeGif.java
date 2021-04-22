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
 * Decode the frame(s) of a GIF-encoded image to a uint8 tensor.
 * GIF images with frame or transparency compression are not supported.
 * On Linux and MacOS systems, convert animated GIFs from compressed to
 * uncompressed by running:
 * <pre>
 * convert $src.gif -coalesce $dst.gif
 * </pre>
 * <p>This op also supports decoding JPEGs and PNGs, though it is cleaner to use
 * {@code tf.io.decode_image}.
 */
@Operator(
    group = "image"
)
public final class DecodeGif extends RawOp implements Operand<TUint8> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DecodeGif";

  private Output<TUint8> image;

  private DecodeGif(Operation operation) {
    super(operation);
    int outputIdx = 0;
    image = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DecodeGif operation.
   *
   * @param scope current scope
   * @param contents 0-D.  The GIF-encoded image.
   * @return a new instance of DecodeGif
   */
  @Endpoint(
      describeByClass = true
  )
  public static DecodeGif create(Scope scope, Operand<TString> contents) {
    OperationBuilder opBuilder = scope.env().opBuilder("DecodeGif", scope.makeOpName("DecodeGif"));
    opBuilder.addInput(contents.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new DecodeGif(opBuilder.build());
  }

  /**
   * Gets image.
   * 4-D with shape {@code [num_frames, height, width, 3]}. RGB channel order.
   * @return image.
   */
  public Output<TUint8> image() {
    return image;
  }

  @Override
  public Output<TUint8> asOutput() {
    return image;
  }
}
