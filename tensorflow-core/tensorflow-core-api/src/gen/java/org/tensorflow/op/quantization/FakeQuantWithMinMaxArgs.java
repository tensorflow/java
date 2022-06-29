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
 * Fake-quantize the 'inputs' tensor, type float to 'outputs' tensor of same type.
 * Attributes
 * <ul>
 * <li>{@code [min; max]} define the clamping range for the {@code inputs} data.</li>
 * <li>{@code inputs} values are quantized into the quantization range (
 * {@code [0; 2^num_bits - 1]} when {@code narrow_range} is false and {@code [1; 2^num_bits - 1]}
 * when it is true) and then de-quantized and output as floats in {@code [min; max]}
 * interval.</li>
 * <li>{@code num_bits} is the bitwidth of the quantization; between 2 and 16, inclusive.</li>
 * </ul>
 * <p>Before quantization, {@code min} and {@code max} values are adjusted with the following
 * logic.
 * It is suggested to have {@code min <= 0 <= max}. If {@code 0} is not in the range of values,
 * the behavior can be unexpected:
 * <ul>
 * <li>If {@code 0 < min < max}: {@code min_adj = 0} and {@code max_adj = max - min}.</li>
 * <li>If {@code min < max < 0}: {@code min_adj = min - max} and {@code max_adj = 0}.</li>
 * <li>If {@code min <= 0 <= max}: {@code scale = (max - min) / (2^num_bits - 1) },
 * {@code min_adj = scale * round(min / scale)} and {@code max_adj = max + min_adj - min}.</li>
 * </ul>
 * <p>Quantization is called fake since the output is still in floating point.
 */
@OpMetadata(
    opType = FakeQuantWithMinMaxArgs.OP_NAME,
    inputsClass = FakeQuantWithMinMaxArgs.Inputs.class
)
@Operator(
    group = "quantization"
)
public final class FakeQuantWithMinMaxArgs extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FakeQuantWithMinMaxArgs";

  private Output<TFloat32> outputs;

  public FakeQuantWithMinMaxArgs(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputs = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new FakeQuantWithMinMaxArgs operation.
   *
   * @param scope current scope
   * @param inputs The inputs value
   * @param options carries optional attribute values
   * @return a new instance of FakeQuantWithMinMaxArgs
   */
  @Endpoint(
      describeByClass = true
  )
  public static FakeQuantWithMinMaxArgs create(Scope scope, Operand<TFloat32> inputs,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FakeQuantWithMinMaxArgs");
    opBuilder.addInput(inputs.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.min != null) {
          opBuilder.setAttr("min", opts.min);
        }
        if (opts.max != null) {
          opBuilder.setAttr("max", opts.max);
        }
        if (opts.numBits != null) {
          opBuilder.setAttr("num_bits", opts.numBits);
        }
        if (opts.narrowRange != null) {
          opBuilder.setAttr("narrow_range", opts.narrowRange);
        }
      }
    }
    return new FakeQuantWithMinMaxArgs(opBuilder.build());
  }

  /**
   * Sets the min option.
   *
   * @param min the min option
   * @return this Options instance.
   */
  public static Options min(Float min) {
    return new Options().min(min);
  }

  /**
   * Sets the max option.
   *
   * @param max the max option
   * @return this Options instance.
   */
  public static Options max(Float max) {
    return new Options().max(max);
  }

  /**
   * Sets the numBits option.
   *
   * @param numBits the numBits option
   * @return this Options instance.
   */
  public static Options numBits(Long numBits) {
    return new Options().numBits(numBits);
  }

  /**
   * Sets the narrowRange option.
   *
   * @param narrowRange the narrowRange option
   * @return this Options instance.
   */
  public static Options narrowRange(Boolean narrowRange) {
    return new Options().narrowRange(narrowRange);
  }

  /**
   * Gets outputs.
   *
   * @return outputs.
   */
  public Output<TFloat32> outputs() {
    return outputs;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return outputs;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.quantization.FakeQuantWithMinMaxArgs}
   */
  public static class Options {
    private Float min;

    private Float max;

    private Long numBits;

    private Boolean narrowRange;

    private Options() {
    }

    /**
     * Sets the min option.
     *
     * @param min the min option
     * @return this Options instance.
     */
    public Options min(Float min) {
      this.min = min;
      return this;
    }

    /**
     * Sets the max option.
     *
     * @param max the max option
     * @return this Options instance.
     */
    public Options max(Float max) {
      this.max = max;
      return this;
    }

    /**
     * Sets the numBits option.
     *
     * @param numBits the numBits option
     * @return this Options instance.
     */
    public Options numBits(Long numBits) {
      this.numBits = numBits;
      return this;
    }

    /**
     * Sets the narrowRange option.
     *
     * @param narrowRange the narrowRange option
     * @return this Options instance.
     */
    public Options narrowRange(Boolean narrowRange) {
      this.narrowRange = narrowRange;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = FakeQuantWithMinMaxArgs.class
  )
  public static class Inputs extends RawOpInputs<FakeQuantWithMinMaxArgs> {
    /**
     * The inputs input
     */
    public final Operand<TFloat32> inputs;

    /**
     * The min attribute
     */
    public final float min;

    /**
     * The max attribute
     */
    public final float max;

    /**
     * The numBits attribute
     */
    public final long numBits;

    /**
     * The narrowRange attribute
     */
    public final boolean narrowRange;

    public Inputs(GraphOperation op) {
      super(new FakeQuantWithMinMaxArgs(op), op, Arrays.asList("min", "max", "num_bits", "narrow_range"));
      int inputIndex = 0;
      inputs = (Operand<TFloat32>) op.input(inputIndex++);
      min = op.attributes().getAttrFloat("min");
      max = op.attributes().getAttrFloat("max");
      numBits = op.attributes().getAttrInt("num_bits");
      narrowRange = op.attributes().getAttrBool("narrow_range");
    }
  }
}
