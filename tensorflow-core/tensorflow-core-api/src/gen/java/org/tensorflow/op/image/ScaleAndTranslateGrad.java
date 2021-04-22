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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * The ScaleAndTranslateGrad operation
 *
 * @param <T> data type for {@code output} output
 */
public final class ScaleAndTranslateGrad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ScaleAndTranslateGrad";

  private Output<T> output;

  private ScaleAndTranslateGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ScaleAndTranslateGrad operation.
   *
   * @param scope current scope
   * @param grads the grads value
   * @param originalImage the originalImage value
   * @param scale the scale value
   * @param translation the translation value
   * @param options carries optional attribute values
   * @param <T> data type for {@code ScaleAndTranslateGrad} output and operands
   * @return a new instance of ScaleAndTranslateGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> ScaleAndTranslateGrad<T> create(Scope scope, Operand<T> grads,
      Operand<T> originalImage, Operand<TFloat32> scale, Operand<TFloat32> translation,
      Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ScaleAndTranslateGrad", scope.makeOpName("ScaleAndTranslateGrad"));
    opBuilder.addInput(grads.asOutput());
    opBuilder.addInput(originalImage.asOutput());
    opBuilder.addInput(scale.asOutput());
    opBuilder.addInput(translation.asOutput());
    opBuilder = scope.apply(opBuilder);
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
    return new ScaleAndTranslateGrad<>(opBuilder.build());
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
   * Gets output.
   *
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.ScaleAndTranslateGrad}
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
