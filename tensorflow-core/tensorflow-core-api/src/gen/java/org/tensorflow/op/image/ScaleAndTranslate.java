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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * The ScaleAndTranslate operation
 */
@Operator(
    group = "image"
)
public final class ScaleAndTranslate extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ScaleAndTranslate";

  private Output<TFloat32> resizedImages;

  private ScaleAndTranslate(Operation operation) {
    super(operation);
    int outputIdx = 0;
    resizedImages = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ScaleAndTranslate operation.
   *
   * @param scope current scope
   * @param images the images value
   * @param sizeOutput the sizeOutput value
   * @param scale the scale value
   * @param translation the translation value
   * @param options carries optional attribute values
   * @return a new instance of ScaleAndTranslate
   */
  @Endpoint(
      describeByClass = true
  )
  public static ScaleAndTranslate create(Scope scope, Operand<? extends TNumber> images,
      Operand<TInt32> sizeOutput, Operand<TFloat32> scale, Operand<TFloat32> translation,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ScaleAndTranslate");
    opBuilder.addInput(images.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    opBuilder.addInput(scale.asOutput());
    opBuilder.addInput(translation.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.kernelType != null) {
          opBuilder.setAttr("kernel_type", opts.kernelType);
        }
        if (opts.antialias != null) {
          opBuilder.setAttr("antialias", opts.antialias);
        }
      }
    }
    return new ScaleAndTranslate(opBuilder.build());
  }

  /**
   * Sets the kernelType option.
   *
   * @param kernelType the kernelType option
   * @return this Options instance.
   */
  public static Options kernelType(String kernelType) {
    return new Options().kernelType(kernelType);
  }

  /**
   * Sets the antialias option.
   *
   * @param antialias the antialias option
   * @return this Options instance.
   */
  public static Options antialias(Boolean antialias) {
    return new Options().antialias(antialias);
  }

  /**
   * Gets resizedImages.
   *
   * @return resizedImages.
   */
  public Output<TFloat32> resizedImages() {
    return resizedImages;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return resizedImages;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.ScaleAndTranslate}
   */
  public static class Options {
    private String kernelType;

    private Boolean antialias;

    private Options() {
    }

    /**
     * Sets the kernelType option.
     *
     * @param kernelType the kernelType option
     * @return this Options instance.
     */
    public Options kernelType(String kernelType) {
      this.kernelType = kernelType;
      return this;
    }

    /**
     * Sets the antialias option.
     *
     * @param antialias the antialias option
     * @return this Options instance.
     */
    public Options antialias(Boolean antialias) {
      this.antialias = antialias;
      return this;
    }
  }
}
