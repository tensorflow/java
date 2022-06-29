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

package org.tensorflow.op.nn;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Gradients for Local Response Normalization.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = LocalResponseNormalizationGrad.OP_NAME,
    inputsClass = LocalResponseNormalizationGrad.Inputs.class
)
public final class LocalResponseNormalizationGrad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LRNGrad";

  private Output<T> output;

  public LocalResponseNormalizationGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LRNGrad operation.
   *
   * @param scope current scope
   * @param inputGrads 4-D with shape {@code [batch, height, width, channels]}.
   * @param inputImage 4-D with shape {@code [batch, height, width, channels]}.
   * @param outputImage 4-D with shape {@code [batch, height, width, channels]}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code LRNGrad} output and operands
   * @return a new instance of LocalResponseNormalizationGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> LocalResponseNormalizationGrad<T> create(Scope scope,
      Operand<T> inputGrads, Operand<T> inputImage, Operand<T> outputImage, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LocalResponseNormalizationGrad");
    opBuilder.addInput(inputGrads.asOutput());
    opBuilder.addInput(inputImage.asOutput());
    opBuilder.addInput(outputImage.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.depthRadius != null) {
          opBuilder.setAttr("depth_radius", opts.depthRadius);
        }
        if (opts.bias != null) {
          opBuilder.setAttr("bias", opts.bias);
        }
        if (opts.alpha != null) {
          opBuilder.setAttr("alpha", opts.alpha);
        }
        if (opts.beta != null) {
          opBuilder.setAttr("beta", opts.beta);
        }
      }
    }
    return new LocalResponseNormalizationGrad<>(opBuilder.build());
  }

  /**
   * Sets the depthRadius option.
   *
   * @param depthRadius A depth radius.
   * @return this Options instance.
   */
  public static Options depthRadius(Long depthRadius) {
    return new Options().depthRadius(depthRadius);
  }

  /**
   * Sets the bias option.
   *
   * @param bias An offset (usually &gt; 0 to avoid dividing by 0).
   * @return this Options instance.
   */
  public static Options bias(Float bias) {
    return new Options().bias(bias);
  }

  /**
   * Sets the alpha option.
   *
   * @param alpha A scale factor, usually positive.
   * @return this Options instance.
   */
  public static Options alpha(Float alpha) {
    return new Options().alpha(alpha);
  }

  /**
   * Sets the beta option.
   *
   * @param beta An exponent.
   * @return this Options instance.
   */
  public static Options beta(Float beta) {
    return new Options().beta(beta);
  }

  /**
   * Gets output.
   * The gradients for LRN.
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
   * Optional attributes for {@link org.tensorflow.op.nn.LocalResponseNormalizationGrad}
   */
  public static class Options {
    private Long depthRadius;

    private Float bias;

    private Float alpha;

    private Float beta;

    private Options() {
    }

    /**
     * Sets the depthRadius option.
     *
     * @param depthRadius A depth radius.
     * @return this Options instance.
     */
    public Options depthRadius(Long depthRadius) {
      this.depthRadius = depthRadius;
      return this;
    }

    /**
     * Sets the bias option.
     *
     * @param bias An offset (usually &gt; 0 to avoid dividing by 0).
     * @return this Options instance.
     */
    public Options bias(Float bias) {
      this.bias = bias;
      return this;
    }

    /**
     * Sets the alpha option.
     *
     * @param alpha A scale factor, usually positive.
     * @return this Options instance.
     */
    public Options alpha(Float alpha) {
      this.alpha = alpha;
      return this;
    }

    /**
     * Sets the beta option.
     *
     * @param beta An exponent.
     * @return this Options instance.
     */
    public Options beta(Float beta) {
      this.beta = beta;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = LocalResponseNormalizationGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<LocalResponseNormalizationGrad<T>> {
    /**
     * 4-D with shape {@code [batch, height, width, channels]}.
     */
    public final Operand<T> inputGrads;

    /**
     * 4-D with shape {@code [batch, height, width, channels]}.
     */
    public final Operand<T> inputImage;

    /**
     * 4-D with shape {@code [batch, height, width, channels]}.
     */
    public final Operand<T> outputImage;

    /**
     * A depth radius.
     */
    public final long depthRadius;

    /**
     * An offset (usually > 0 to avoid dividing by 0).
     */
    public final float bias;

    /**
     * A scale factor, usually positive.
     */
    public final float alpha;

    /**
     * An exponent.
     */
    public final float beta;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new LocalResponseNormalizationGrad<>(op), op, Arrays.asList("depth_radius", "bias", "alpha", "beta", "T"));
      int inputIndex = 0;
      inputGrads = (Operand<T>) op.input(inputIndex++);
      inputImage = (Operand<T>) op.input(inputIndex++);
      outputImage = (Operand<T>) op.input(inputIndex++);
      depthRadius = op.attributes().getAttrInt("depth_radius");
      bias = op.attributes().getAttrFloat("bias");
      alpha = op.attributes().getAttrFloat("alpha");
      beta = op.attributes().getAttrFloat("beta");
      T = op.attributes().getAttrType("T");
    }
  }
}
