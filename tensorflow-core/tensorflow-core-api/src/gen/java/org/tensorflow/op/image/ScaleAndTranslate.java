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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * The ScaleAndTranslate operation
 */
@OpMetadata(
    opType = ScaleAndTranslate.OP_NAME,
    inputsClass = ScaleAndTranslate.Inputs.class
)
@Operator(
    group = "image"
)
public final class ScaleAndTranslate extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ScaleAndTranslate";

  private Output<TFloat32> resizedImages;

  public ScaleAndTranslate(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    resizedImages = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ScaleAndTranslate operation.
   *
   * @param scope current scope
   * @param images The images value
   * @param sizeOutput The sizeOutput value
   * @param scale The scale value
   * @param translation The translation value
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

  @OpInputsMetadata(
      outputsClass = ScaleAndTranslate.class
  )
  public static class Inputs extends RawOpInputs<ScaleAndTranslate> {
    /**
     * The images input
     */
    public final Operand<? extends TNumber> images;

    /**
     * The sizeOutput input
     */
    public final Operand<TInt32> sizeOutput;

    /**
     * The scale input
     */
    public final Operand<TFloat32> scale;

    /**
     * The translation input
     */
    public final Operand<TFloat32> translation;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The kernelType attribute
     */
    public final String kernelType;

    /**
     * The antialias attribute
     */
    public final boolean antialias;

    public Inputs(GraphOperation op) {
      super(new ScaleAndTranslate(op), op, Arrays.asList("T", "kernel_type", "antialias"));
      int inputIndex = 0;
      images = (Operand<? extends TNumber>) op.input(inputIndex++);
      sizeOutput = (Operand<TInt32>) op.input(inputIndex++);
      scale = (Operand<TFloat32>) op.input(inputIndex++);
      translation = (Operand<TFloat32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      kernelType = op.attributes().getAttrString("kernel_type");
      antialias = op.attributes().getAttrBool("antialias");
    }
  }
}
