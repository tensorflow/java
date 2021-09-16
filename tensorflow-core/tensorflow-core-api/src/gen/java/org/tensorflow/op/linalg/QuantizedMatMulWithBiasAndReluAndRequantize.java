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

package org.tensorflow.op.linalg;

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
 * Perform a quantized matrix multiplication of  {@code a} by the matrix {@code b} with bias
 * add and relu and requantize fusion.
 * The inputs must be two-dimensional matrices and 1D bias vector. And the inner
 * dimension of {@code a} (after being transposed if {@code transpose_a} is non-zero) must
 * match the outer dimension of {@code b} (after being transposed if {@code transposed_b} is
 * non-zero). Then do broadcast add operation with bias values on the matrix
 * multiplication result. The bias size must match inner dimension of {@code b}.  Then do
 * relu activation to get non-negative result. Then do requantize operation to get
 * final uint8 result.
 *
 * @param <W> data type for {@code out} output
 */
public final class QuantizedMatMulWithBiasAndReluAndRequantize<W extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedMatMulWithBiasAndReluAndRequantize";

  private Output<W> out;

  private Output<TFloat32> minOut;

  private Output<TFloat32> maxOut;

  private QuantizedMatMulWithBiasAndReluAndRequantize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
    minOut = operation.output(outputIdx++);
    maxOut = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizedMatMulWithBiasAndReluAndRequantize operation.
   *
   * @param scope current scope
   * @param a A matrix to be multiplied. Must be a two-dimensional tensor of type {@code quint8}.
   * @param b A matrix to be multiplied and must be a two-dimensional tensor of type {@code qint8}.
   * @param bias A 1D bias tensor with size matching with inner dimension of {@code b} (after being
   * transposed if {@code transposed_b} is non-zero).
   * @param minA The float value that the lowest quantized {@code a} value represents.
   * @param maxA The float value that the highest quantized {@code a} value represents.
   * @param minB The float value that the lowest quantized {@code b} value represents.
   * @param maxB The float value that the highest quantized {@code b} value represents.
   * @param minFreezedOutput The float value that the highest quantized output value after requantize.
   * @param maxFreezedOutput the maxFreezedOutput value
   * @param Toutput the value of the Toutput property
   * @param options carries optional attribute values
   * @param <W> data type for {@code QuantizedMatMulWithBiasAndReluAndRequantize} output and operands
   * @return a new instance of QuantizedMatMulWithBiasAndReluAndRequantize
   */
  @Endpoint(
      describeByClass = true
  )
  public static <W extends TNumber> QuantizedMatMulWithBiasAndReluAndRequantize<W> create(
      Scope scope, Operand<? extends TNumber> a, Operand<? extends TNumber> b,
      Operand<? extends TNumber> bias, Operand<TFloat32> minA, Operand<TFloat32> maxA,
      Operand<TFloat32> minB, Operand<TFloat32> maxB, Operand<TFloat32> minFreezedOutput,
      Operand<TFloat32> maxFreezedOutput, Class<W> Toutput, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizedMatMulWithBiasAndReluAndRequantize");
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
    return new QuantizedMatMulWithBiasAndReluAndRequantize<>(opBuilder.build());
  }

  /**
   * Sets the transposeA option.
   *
   * @param transposeA If true, {@code a} is transposed before multiplication.
   * @return this Options instance.
   */
  public static Options transposeA(Boolean transposeA) {
    return new Options().transposeA(transposeA);
  }

  /**
   * Sets the transposeB option.
   *
   * @param transposeB If true, {@code b} is transposed before multiplication.
   * @return this Options instance.
   */
  public static Options transposeB(Boolean transposeB) {
    return new Options().transposeB(transposeB);
  }

  /**
   * Sets the inputQuantMode option.
   *
   * @param inputQuantMode Input data quantization mode. Either MIN_FIRST(default) or SCALED.
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

  /**
   * Gets minOut.
   * The float value that the lowest quantized output value represents.
   * @return minOut.
   */
  public Output<TFloat32> minOut() {
    return minOut;
  }

  /**
   * Gets maxOut.
   * The float value that the highest quantized output value represents.
   * @return maxOut.
   */
  public Output<TFloat32> maxOut() {
    return maxOut;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.QuantizedMatMulWithBiasAndReluAndRequantize}
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
     * @param transposeA If true, {@code a} is transposed before multiplication.
     * @return this Options instance.
     */
    public Options transposeA(Boolean transposeA) {
      this.transposeA = transposeA;
      return this;
    }

    /**
     * Sets the transposeB option.
     *
     * @param transposeB If true, {@code b} is transposed before multiplication.
     * @return this Options instance.
     */
    public Options transposeB(Boolean transposeB) {
      this.transposeB = transposeB;
      return this;
    }

    /**
     * Sets the inputQuantMode option.
     *
     * @param inputQuantMode Input data quantization mode. Either MIN_FIRST(default) or SCALED.
     * @return this Options instance.
     */
    public Options inputQuantMode(String inputQuantMode) {
      this.inputQuantMode = inputQuantMode;
      return this;
    }
  }
}
