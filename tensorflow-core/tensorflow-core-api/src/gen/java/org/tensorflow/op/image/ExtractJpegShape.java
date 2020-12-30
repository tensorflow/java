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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * Extract the shape information of a JPEG-encoded image.
 * <p>
 * This op only parses the image header, so it is much faster than DecodeJpeg.
 * 
 * @param <T> data type for {@code imageShape()} output
 */
@Operator(group = "image")
public final class ExtractJpegShape<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new ExtractJpegShape operation.
   * 
   * @param scope current scope
   * @param contents 0-D. The JPEG-encoded image.
   * @param outputType (Optional) The output type of the operation (int32 or int64).
   * Defaults to int32.
   * @return a new instance of ExtractJpegShape
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> ExtractJpegShape<T> create(Scope scope, Operand<TString> contents, Class<T> outputType) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExtractJpegShape", scope.makeOpName("ExtractJpegShape"));
    opBuilder.addInput(contents.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("output_type", Operands.toDataType(outputType));
    return new ExtractJpegShape<T>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new ExtractJpegShape operation using default output types.
   * 
   * @param scope current scope
   * @param contents 0-D. The JPEG-encoded image.
   * @return a new instance of ExtractJpegShape
   */
  @Endpoint(describeByClass = true)
  public static ExtractJpegShape<TInt32> create(Scope scope, Operand<TString> contents) {
    return create(scope, contents, TInt32.class);
  }
  
  /**
   * 1-D. The image shape with format [height, width, channels].
   */
  public Output<T> imageShape() {
    return imageShape;
  }
  
  @Override
  public Output<T> asOutput() {
    return imageShape;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ExtractJpegShape";
  
  private Output<T> imageShape;
  
  private ExtractJpegShape(Operation operation) {
    super(operation);
    int outputIdx = 0;
    imageShape = operation.output(outputIdx++);
  }
}
