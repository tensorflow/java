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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Adjust the hue of one or more images.
 * <p>
 * `images` is a tensor of at least 3 dimensions.  The last dimension is
 * interpreted as channels, and must be three.
 * <p>
 * The input image is considered in the RGB colorspace. Conceptually, the RGB
 * colors are first mapped into HSV. A delta is then applied all the hue values,
 * and then remapped back to RGB colorspace.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "image")
public final class AdjustHue<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new AdjustHue operation.
   * 
   * @param scope current scope
   * @param images Images to adjust.  At least 3-D.
   * @param delta A float delta to add to the hue.
   * @return a new instance of AdjustHue
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> AdjustHue<T> create(Scope scope, Operand<T> images, Operand<TFloat32> delta) {
    OperationBuilder opBuilder = scope.env().opBuilder("AdjustHue", scope.makeOpName("AdjustHue"));
    opBuilder.addInput(images.asOutput());
    opBuilder.addInput(delta.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new AdjustHue<T>(opBuilder.build());
  }
  
  /**
   * The hue-adjusted image or images.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "AdjustHue";
  
  private Output<T> output;
  
  private AdjustHue(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
