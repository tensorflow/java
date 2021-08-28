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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * The QuantizedMatMulWithBiasAndDequantize operation
 *
 * @param <W> data type for {@code out} output
 */
public final class QuantizedMatMulWithBiasAndDequantize<W extends TNumber> extends RawOp implements Operand<W> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedMatMulWithBiasAndDequantize";

  private Output<W> out;

  private QuantizedMatMulWithBiasAndDequantize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizedMatMulWithBiasAndDequantize operation.
   *
   * @param scope current scope
   * @param a the a value
   * @param b the b value
   * @param bias the bias value
   * @param minA the minA value
   * @param maxA the maxA value
   * @param minB the minB value
   * @param maxB the maxB value
   * @param minFreezedOutput the minFreezedOutput value
   * @param maxFreezedOutput the maxFreezedOutput value
   * @param Toutput the value of the Toutput property
   * @param options carries optional attribute values
   * @param <W> data type for {@code QuantizedMatMulWithBiasAndDequantize} output and operands
   * @return a new instance of QuantizedMatMulWithBiasAndDequantize
   */
  @Endpoint(
      describeByClass = true
  )
  public static <W extends TNumber> QuantizedMatMulWithBiasAndDequantize<W> create(Scope scope,
      Operand<? extends TNumber> a, Operand<? extends TNumber> b, Operand<? extends TNumber> bias,
      Operand<TFloat32> minA, Operand<TFloat32> maxA, Operand<TFloat32> minB,
      Operand<TFloat32> maxB, Operand<TFloat32> minFreezedOutput,
      Operand<TFloat32> maxFreezedOutput, Class<W> Toutput, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizedMatMulWithBiasAndDequantize");
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder.addInput(bias.asOutput());
    opBuilder.addInput(minA.asOutput());
    opBuilder.addInput(maxA.asOutput());
    opBuilder.addInput(minB.asOutput());
    opBuilder.addInput(maxB.asOutput());
    opBuilder.addInput(minFreezedOutput.asOutput());
    opBuilder.addInput(maxFreezedOutput.asOutput());
    opBuilder.setAttr("Toutput", Operands.toDataType(Toutput));
    if (options != null) {
      for (Options opts : options) {
        if (opts.transposeA != null) {
          opBuilder.setAttr("transpose_a", opts.transposeA);
        }
        if (opts.transposeB != null) {
          opBuilder.setAttr("transpose_b", opts.transposeB);
        }
        if (opts.inputQuantMode != null) {
          opBuilder.setAttr("input_quant_mode", opts.inputQuantMode);
        }
      }
    }
    return new QuantizedMatMulWithBiasAndDequantize<>(opBuilder.build());
  }

  /**
   * Sets the transposeA option.
   *
   * @param transposeA the transposeA option
   * @return this Options instance.
   */
  public static Options transposeA(Boolean transposeA) {
    return new Options().transposeA(transposeA);
  }

  /**
   * Sets the transposeB option.
   *
   * @param transposeB the transposeB option
   * @return this Options instance.
   */
  public static Options transposeB(Boolean transposeB) {
    return new Options().transposeB(transposeB);
  }

  /**
   * Sets the inputQuantMode option.
   *
   * @param inputQuantMode the inputQuantMode option
   * @return this Options instance.
   */
  public static Options inputQuantMode(String inputQuantMode) {
    return new Options().inputQuantMode(inputQuantMode);
  }

  /**
   * Gets out.
   *
   * @return out.
   */
  public Output<W> out() {
    return out;
  }

  @Override
  public Output<W> asOutput() {
    return out;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.quantization.QuantizedMatMulWithBiasAndDequantize}
   */
  public static class Options {
    private Boolean transposeA;

    private Boolean transposeB;

    private String inputQuantMode;

    private Options() {
    }

    /**
     * Sets the transposeA option.
     *
     * @param transposeA the transposeA option
     * @return this Options instance.
     */
    public Options transposeA(Boolean transposeA) {
      this.transposeA = transposeA;
      return this;
    }

    /**
     * Sets the transposeB option.
     *
     * @param transposeB the transposeB option
     * @return this Options instance.
     */
    public Options transposeB(Boolean transposeB) {
      this.transposeB = transposeB;
      return this;
    }

    /**
     * Sets the inputQuantMode option.
     *
     * @param inputQuantMode the inputQuantMode option
     * @return this Options instance.
     */
    public Options inputQuantMode(String inputQuantMode) {
      this.inputQuantMode = inputQuantMode;
      return this;
    }
  }
}
