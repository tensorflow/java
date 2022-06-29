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

package org.tensorflow.op.quantization;

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
import org.tensorflow.types.TFloat32;

/**
 * Compute gradients for a FakeQuantWithMinMaxVarsPerChannel operation.
 */
@OpMetadata(
    opType = FakeQuantWithMinMaxVarsPerChannelGradient.OP_NAME,
    inputsClass = FakeQuantWithMinMaxVarsPerChannelGradient.Inputs.class
)
@Operator(
    group = "quantization"
)
public final class FakeQuantWithMinMaxVarsPerChannelGradient extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FakeQuantWithMinMaxVarsPerChannelGradient";

  private Output<TFloat32> backpropsWrtInput;

  private Output<TFloat32> backpropWrtMin;

  private Output<TFloat32> backpropWrtMax;

  public FakeQuantWithMinMaxVarsPerChannelGradient(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    backpropsWrtInput = operation.output(outputIdx++);
    backpropWrtMin = operation.output(outputIdx++);
    backpropWrtMax = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FakeQuantWithMinMaxVarsPerChannelGradient operation.
   *
   * @param scope current scope
   * @param gradients Backpropagated gradients above the FakeQuantWithMinMaxVars operation,
   * shape one of: {@code [d]}, {@code [b, d]},  {@code [b, h, w, d]}.
   * @param inputs Values passed as inputs to the FakeQuantWithMinMaxVars operation, shape
   * same as {@code gradients}.
   * min, max: Quantization interval, floats of shape {@code [d]}.
   * @param min The min value
   * @param max The max value
   * @param options carries optional attribute values
   * @return a new instance of FakeQuantWithMinMaxVarsPerChannelGradient
   */
  @Endpoint(
      describeByClass = true
  )
  public static FakeQuantWithMinMaxVarsPerChannelGradient create(Scope scope,
      Operand<TFloat32> gradients, Operand<TFloat32> inputs, Operand<TFloat32> min,
      Operand<TFloat32> max, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FakeQuantWithMinMaxVarsPerChannelGradient");
    opBuilder.addInput(gradients.asOutput());
    opBuilder.addInput(inputs.asOutput());
    opBuilder.addInput(min.asOutput());
    opBuilder.addInput(max.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.numBits != null) {
          opBuilder.setAttr("num_bits", opts.numBits);
        }
        if (opts.narrowRange != null) {
          opBuilder.setAttr("narrow_range", opts.narrowRange);
        }
      }
    }
    return new FakeQuantWithMinMaxVarsPerChannelGradient(opBuilder.build());
  }

  /**
   * Sets the numBits option.
   *
   * @param numBits The bitwidth of the quantization; between 2 and 16, inclusive.
   * @return this Options instance.
   */
  public static Options numBits(Long numBits) {
    return new Options().numBits(numBits);
  }

  /**
   * Sets the narrowRange option.
   *
   * @param narrowRange Whether to quantize into 2^num_bits - 1 distinct values.
   * @return this Options instance.
   */
  public static Options narrowRange(Boolean narrowRange) {
    return new Options().narrowRange(narrowRange);
  }

  /**
   * Gets backpropsWrtInput.
   * Backpropagated gradients w.r.t. inputs, shape same as
   * {@code inputs}:
   * {@code gradients * (inputs >= min && inputs <= max)}.
   * @return backpropsWrtInput.
   */
  public Output<TFloat32> backpropsWrtInput() {
    return backpropsWrtInput;
  }

  /**
   * Gets backpropWrtMin.
   * Backpropagated gradients w.r.t. min parameter, shape {@code [d]}:
   * {@code sum_per_d(gradients * (inputs < min))}.
   * @return backpropWrtMin.
   */
  public Output<TFloat32> backpropWrtMin() {
    return backpropWrtMin;
  }

  /**
   * Gets backpropWrtMax.
   * Backpropagated gradients w.r.t. max parameter, shape {@code [d]}:
   * {@code sum_per_d(gradients * (inputs > max))}.
   * @return backpropWrtMax.
   */
  public Output<TFloat32> backpropWrtMax() {
    return backpropWrtMax;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannelGradient}
   */
  public static class Options {
    private Long numBits;

    private Boolean narrowRange;

    private Options() {
    }

    /**
     * Sets the numBits option.
     *
     * @param numBits The bitwidth of the quantization; between 2 and 16, inclusive.
     * @return this Options instance.
     */
    public Options numBits(Long numBits) {
      this.numBits = numBits;
      return this;
    }

    /**
     * Sets the narrowRange option.
     *
     * @param narrowRange Whether to quantize into 2^num_bits - 1 distinct values.
     * @return this Options instance.
     */
    public Options narrowRange(Boolean narrowRange) {
      this.narrowRange = narrowRange;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = FakeQuantWithMinMaxVarsPerChannelGradient.class
  )
  public static class Inputs extends RawOpInputs<FakeQuantWithMinMaxVarsPerChannelGradient> {
    /**
     * Backpropagated gradients above the FakeQuantWithMinMaxVars operation,
     * shape one of: {@code [d]}, {@code [b, d]},  {@code [b, h, w, d]}.
     */
    public final Operand<TFloat32> gradients;

    /**
     * Values passed as inputs to the FakeQuantWithMinMaxVars operation, shape
     * same as {@code gradients}.
     * min, max: Quantization interval, floats of shape {@code [d]}.
     */
    public final Operand<TFloat32> inputs;

    /**
     * The min input
     */
    public final Operand<TFloat32> min;

    /**
     * The max input
     */
    public final Operand<TFloat32> max;

    /**
     * The bitwidth of the quantization; between 2 and 16, inclusive.
     */
    public final long numBits;

    /**
     * Whether to quantize into 2^num_bits - 1 distinct values.
     */
    public final boolean narrowRange;

    public Inputs(GraphOperation op) {
      super(new FakeQuantWithMinMaxVarsPerChannelGradient(op), op, Arrays.asList("num_bits", "narrow_range"));
      int inputIndex = 0;
      gradients = (Operand<TFloat32>) op.input(inputIndex++);
      inputs = (Operand<TFloat32>) op.input(inputIndex++);
      min = (Operand<TFloat32>) op.input(inputIndex++);
      max = (Operand<TFloat32>) op.input(inputIndex++);
      numBits = op.attributes().getAttrInt("num_bits");
      narrowRange = op.attributes().getAttrBool("narrow_range");
    }
  }
}
