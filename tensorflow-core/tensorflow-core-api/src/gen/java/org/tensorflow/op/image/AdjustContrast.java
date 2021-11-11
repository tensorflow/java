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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Adjust the contrast of one or more images.
 * {@code images} is a tensor of at least 3 dimensions.  The last 3 dimensions are
 * interpreted as {@code [height, width, channels]}.  The other dimensions only
 * represent a collection of images, such as {@code [batch, height, width, channels].}
 * <p>Contrast is adjusted independently for each channel of each image.
 * <p>For each channel, the Op first computes the mean of the image pixels in the
 * channel and then adjusts each component of each pixel to
 * {@code (x - mean) * contrast_factor + mean}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = AdjustContrast.OP_NAME,
    inputsClass = AdjustContrast.Inputs.class
)
@Operator(
    group = "image"
)
public final class AdjustContrast<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AdjustContrastv2";

  private Output<T> output;

  public AdjustContrast(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AdjustContrastv2 operation.
   *
   * @param scope current scope
   * @param images Images to adjust.  At least 3-D.
   * @param contrastFactor A float multiplier for adjusting contrast.
   * @param <T> data type for {@code AdjustContrastv2} output and operands
   * @return a new instance of AdjustContrast
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> AdjustContrast<T> create(Scope scope, Operand<T> images,
      Operand<TFloat32> contrastFactor) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AdjustContrast");
    opBuilder.addInput(images.asOutput());
    opBuilder.addInput(contrastFactor.asOutput());
    return new AdjustContrast<>(opBuilder.build());
  }

  /**
   * Gets output.
   * The contrast-adjusted image or images.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = AdjustContrast.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<AdjustContrast<T>> {
    /**
     * Images to adjust.  At least 3-D.
     */
    public final Operand<T> images;

    /**
     * A float multiplier for adjusting contrast.
     */
    public final Operand<TFloat32> contrastFactor;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new AdjustContrast<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      images = (Operand<T>) op.input(inputIndex++);
      contrastFactor = (Operand<TFloat32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
