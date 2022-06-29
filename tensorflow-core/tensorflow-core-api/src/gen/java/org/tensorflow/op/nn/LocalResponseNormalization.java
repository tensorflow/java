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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Local Response Normalization.
 * The 4-D {@code input} tensor is treated as a 3-D array of 1-D vectors (along the last
 * dimension), and each vector is normalized independently.  Within a given vector,
 * each component is divided by the weighted, squared sum of inputs within
 * {@code depth_radius}.  In detail,
 * <pre>
 * sqr_sum[a, b, c, d] =
 *     sum(input[a, b, c, d - depth_radius : d + depth_radius + 1] ** 2)
 * output = input / (bias + alpha * sqr_sum) ** beta
 * </pre>
 * <p>For details, see  <a href="http://papers.nips.cc/paper/4824-imagenet-classification-with-deep-convolutional-neural-networks">Krizhevsky et al., ImageNet classification with deep
 * convolutional neural networks (NIPS 2012)</a> .
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = LocalResponseNormalization.OP_NAME,
    inputsClass = LocalResponseNormalization.Inputs.class
)
@Operator(
    group = "nn"
)
public final class LocalResponseNormalization<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LRN";

  private Output<T> output;

  public LocalResponseNormalization(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LRN operation.
   *
   * @param scope current scope
   * @param input 4-D.
   * @param options carries optional attribute values
   * @param <T> data type for {@code LRN} output and operands
   * @return a new instance of LocalResponseNormalization
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> LocalResponseNormalization<T> create(Scope scope,
      Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LocalResponseNormalization");
    opBuilder.addInput(input.asOutput());
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
    return new LocalResponseNormalization<>(opBuilder.build());
  }

  /**
   * Sets the depthRadius option.
   *
   * @param depthRadius 0-D.  Half-width of the 1-D normalization window.
   * @return this Options instance.
   */
  public static Options depthRadius(Long depthRadius) {
    return new Options().depthRadius(depthRadius);
  }

  /**
   * Sets the bias option.
   *
   * @param bias An offset (usually positive to avoid dividing by 0).
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
   * Optional attributes for {@link org.tensorflow.op.nn.LocalResponseNormalization}
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
     * @param depthRadius 0-D.  Half-width of the 1-D normalization window.
     * @return this Options instance.
     */
    public Options depthRadius(Long depthRadius) {
      this.depthRadius = depthRadius;
      return this;
    }

    /**
     * Sets the bias option.
     *
     * @param bias An offset (usually positive to avoid dividing by 0).
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
      outputsClass = LocalResponseNormalization.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<LocalResponseNormalization<T>> {
    /**
     * 4-D.
     */
    public final Operand<T> input;

    /**
     * 0-D.  Half-width of the 1-D normalization window.
     */
    public final long depthRadius;

    /**
     * An offset (usually positive to avoid dividing by 0).
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
      super(new LocalResponseNormalization<>(op), op, Arrays.asList("depth_radius", "bias", "alpha", "beta", "T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      depthRadius = op.attributes().getAttrInt("depth_radius");
      bias = op.attributes().getAttrFloat("bias");
      alpha = op.attributes().getAttrFloat("alpha");
      beta = op.attributes().getAttrFloat("beta");
      T = op.attributes().getAttrType("T");
    }
  }
}
