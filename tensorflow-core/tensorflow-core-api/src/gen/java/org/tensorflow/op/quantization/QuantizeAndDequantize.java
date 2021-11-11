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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Quantizes then dequantizes a tensor.
 * This is almost identical to QuantizeAndDequantizeV2, except that num_bits is a
 * tensor, so its value can change during training.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = QuantizeAndDequantize.OP_NAME,
    inputsClass = QuantizeAndDequantize.Inputs.class
)
@Operator(
    group = "quantization"
)
public final class QuantizeAndDequantize<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizeAndDequantizeV3";

  private Output<T> output;

  public QuantizeAndDequantize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizeAndDequantizeV3 operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param inputMin The inputMin value
   * @param inputMax The inputMax value
   * @param numBits The numBits value
   * @param options carries optional attribute values
   * @param <T> data type for {@code QuantizeAndDequantizeV3} output and operands
   * @return a new instance of QuantizeAndDequantize
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> QuantizeAndDequantize<T> create(Scope scope, Operand<T> input,
      Operand<T> inputMin, Operand<T> inputMax, Operand<TInt32> numBits, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizeAndDequantize");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputMin.asOutput());
    opBuilder.addInput(inputMax.asOutput());
    opBuilder.addInput(numBits.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.signedInput != null) {
          opBuilder.setAttr("signed_input", opts.signedInput);
        }
        if (opts.rangeGiven != null) {
          opBuilder.setAttr("range_given", opts.rangeGiven);
        }
        if (opts.narrowRange != null) {
          opBuilder.setAttr("narrow_range", opts.narrowRange);
        }
        if (opts.axis != null) {
          opBuilder.setAttr("axis", opts.axis);
        }
      }
    }
    return new QuantizeAndDequantize<>(opBuilder.build());
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
   * Sets the rangeGiven option.
   *
   * @param rangeGiven the rangeGiven option
   * @return this Options instance.
   */
  public static Options rangeGiven(Boolean rangeGiven) {
    return new Options().rangeGiven(rangeGiven);
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
   * Optional attributes for {@link org.tensorflow.op.quantization.QuantizeAndDequantize}
   */
  public static class Options {
    private Boolean signedInput;

    private Boolean rangeGiven;

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

  @OpInputsMetadata(
      outputsClass = QuantizeAndDequantize.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<QuantizeAndDequantize<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The inputMin input
     */
    public final Operand<T> inputMin;

    /**
     * The inputMax input
     */
    public final Operand<T> inputMax;

    /**
     * The numBits input
     */
    public final Operand<TInt32> numBits;

    /**
     * The signedInput attribute
     */
    public final boolean signedInput;

    /**
     * The rangeGiven attribute
     */
    public final boolean rangeGiven;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The narrowRange attribute
     */
    public final boolean narrowRange;

    /**
     * The axis attribute
     */
    public final long axis;

    public Inputs(GraphOperation op) {
      super(new QuantizeAndDequantize<>(op), op, Arrays.asList("signed_input", "range_given", "T", "narrow_range", "axis"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      inputMin = (Operand<T>) op.input(inputIndex++);
      inputMax = (Operand<T>) op.input(inputIndex++);
      numBits = (Operand<TInt32>) op.input(inputIndex++);
      signedInput = op.attributes().getAttrBool("signed_input");
      rangeGiven = op.attributes().getAttrBool("range_given");
      T = op.attributes().getAttrType("T");
      narrowRange = op.attributes().getAttrBool("narrow_range");
      axis = op.attributes().getAttrInt("axis");
    }
  }
}
