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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;

/**
 * Returns the gradient of {@code quantization.QuantizeAndDequantizeV4}.
 * This is almost identical to QuantizeAndDequantizeV2, except that it returns a
 * gradient of 1 for inputs that are within the quantization range, or 0 otherwise.
 *
 * @param <T> data type for {@code output} output
 */
@Operator(
    group = "quantization"
)
public final class QuantizeAndDequantizeV4<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizeAndDequantizeV4";

  private Output<T> output;

  private QuantizeAndDequantizeV4(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizeAndDequantizeV4 operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param inputMin the inputMin value
   * @param inputMax the inputMax value
   * @param options carries optional attribute values
   * @param <T> data type for {@code QuantizeAndDequantizeV4} output and operands
   * @return a new instance of QuantizeAndDequantizeV4
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> QuantizeAndDequantizeV4<T> create(Scope scope, Operand<T> input,
      Operand<T> inputMin, Operand<T> inputMax, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("QuantizeAndDequantizeV4"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputMin.asOutput());
    opBuilder.addInput(inputMax.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.signedInput != null) {
          opBuilder.setAttr("signed_input", opts.signedInput);
        }
        if (opts.numBits != null) {
          opBuilder.setAttr("num_bits", opts.numBits);
        }
        if (opts.rangeGiven != null) {
          opBuilder.setAttr("range_given", opts.rangeGiven);
        }
        if (opts.roundMode != null) {
          opBuilder.setAttr("round_mode", opts.roundMode);
        }
        if (opts.narrowRange != null) {
          opBuilder.setAttr("narrow_range", opts.narrowRange);
        }
        if (opts.axis != null) {
          opBuilder.setAttr("axis", opts.axis);
        }
      }
    }
    return new QuantizeAndDequantizeV4<>(opBuilder.build());
  }

  /**
   * Sets the signedInput option.
   *
   * @param signedInput the signedInput option
   * @return this Options instance.
   */
  public static Options signedInput(Boolean signedInput) {
    return new Options().signedInput(signedInput);
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
   * Sets the rangeGiven option.
   *
   * @param rangeGiven the rangeGiven option
   * @return this Options instance.
   */
  public static Options rangeGiven(Boolean rangeGiven) {
    return new Options().rangeGiven(rangeGiven);
  }

  /**
   * Sets the roundMode option.
   *
   * @param roundMode the roundMode option
   * @return this Options instance.
   */
  public static Options roundMode(String roundMode) {
    return new Options().roundMode(roundMode);
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
   * Sets the axis option.
   *
   * @param axis the axis option
   * @return this Options instance.
   */
  public static Options axis(Long axis) {
    return new Options().axis(axis);
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
   * Optional attributes for {@link org.tensorflow.op.quantization.QuantizeAndDequantizeV4}
   */
  public static class Options {
    private Boolean signedInput;

    private Long numBits;

    private Boolean rangeGiven;

    private String roundMode;

    private Boolean narrowRange;

    private Long axis;

    private Options() {
    }

    /**
     * Sets the signedInput option.
     *
     * @param signedInput the signedInput option
     * @return this Options instance.
     */
    public Options signedInput(Boolean signedInput) {
      this.signedInput = signedInput;
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
     * Sets the rangeGiven option.
     *
     * @param rangeGiven the rangeGiven option
     * @return this Options instance.
     */
    public Options rangeGiven(Boolean rangeGiven) {
      this.rangeGiven = rangeGiven;
      return this;
    }

    /**
     * Sets the roundMode option.
     *
     * @param roundMode the roundMode option
     * @return this Options instance.
     */
    public Options roundMode(String roundMode) {
      this.roundMode = roundMode;
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

    /**
     * Sets the axis option.
     *
     * @param axis the axis option
     * @return this Options instance.
     */
    public Options axis(Long axis) {
      this.axis = axis;
      return this;
    }
  }
}
