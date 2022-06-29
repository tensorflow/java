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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Perform a quantized matrix multiplication of  {@code a} by the matrix {@code b} with bias
 * add and relu fusion.
 * The inputs must be two-dimensional matrices and 1D bias vector. And the inner
 * dimension of {@code a} (after being transposed if {@code transpose_a} is non-zero) must
 * match the outer dimension of {@code b} (after being transposed if {@code transposed_b} is
 * non-zero). Then do broadcast add operation with bias values on the matrix
 * multiplication result. The bias size must match inner dimension of {@code b}. Then do
 * relu activation to get non-negative result.
 *
 * @param <V> data type for {@code out} output
 */
@OpMetadata(
    opType = QuantizedMatMulWithBiasAndRelu.OP_NAME,
    inputsClass = QuantizedMatMulWithBiasAndRelu.Inputs.class
)
public final class QuantizedMatMulWithBiasAndRelu<V extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedMatMulWithBiasAndRelu";

  private Output<V> out;

  private Output<TFloat32> minOut;

  private Output<TFloat32> maxOut;

  public QuantizedMatMulWithBiasAndRelu(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
    minOut = operation.output(outputIdx++);
    maxOut = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizedMatMulWithBiasAndRelu operation.
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
   * @param Toutput The value of the Toutput attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedMatMulWithBiasAndRelu} output and operands
   * @return a new instance of QuantizedMatMulWithBiasAndRelu
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> QuantizedMatMulWithBiasAndRelu<V> create(Scope scope,
      Operand<? extends TNumber> a, Operand<? extends TNumber> b, Operand<TFloat32> bias,
      Operand<TFloat32> minA, Operand<TFloat32> maxA, Operand<TFloat32> minB,
      Operand<TFloat32> maxB, Class<V> Toutput, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizedMatMulWithBiasAndRelu");
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder.addInput(bias.asOutput());
    opBuilder.addInput(minA.asOutput());
    opBuilder.addInput(maxA.asOutput());
    opBuilder.addInput(minB.asOutput());
    opBuilder.addInput(maxB.asOutput());
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
    return new QuantizedMatMulWithBiasAndRelu<>(opBuilder.build());
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
  public Output<V> out() {
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
   * Optional attributes for {@link org.tensorflow.op.linalg.QuantizedMatMulWithBiasAndRelu}
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

  @OpInputsMetadata(
      outputsClass = QuantizedMatMulWithBiasAndRelu.class
  )
  public static class Inputs extends RawOpInputs<QuantizedMatMulWithBiasAndRelu<?>> {
    /**
     * A matrix to be multiplied. Must be a two-dimensional tensor of type {@code quint8}.
     */
    public final Operand<? extends TNumber> a;

    /**
     * A matrix to be multiplied and must be a two-dimensional tensor of type {@code qint8}.
     */
    public final Operand<? extends TNumber> b;

    /**
     * A 1D bias tensor with size matching with inner dimension of {@code b} (after being
     * transposed if {@code transposed_b} is non-zero).
     */
    public final Operand<TFloat32> bias;

    /**
     * The float value that the lowest quantized {@code a} value represents.
     */
    public final Operand<TFloat32> minA;

    /**
     * The float value that the highest quantized {@code a} value represents.
     */
    public final Operand<TFloat32> maxA;

    /**
     * The float value that the lowest quantized {@code b} value represents.
     */
    public final Operand<TFloat32> minB;

    /**
     * The float value that the highest quantized {@code b} value represents.
     */
    public final Operand<TFloat32> maxB;

    /**
     * The T1 attribute
     */
    public final DataType T1;

    /**
     * The T2 attribute
     */
    public final DataType T2;

    /**
     * The Toutput attribute
     */
    public final DataType Toutput;

    /**
     * If true, `a` is transposed before multiplication.
     */
    public final boolean transposeA;

    /**
     * If true, `b` is transposed before multiplication.
     */
    public final boolean transposeB;

    /**
     * Input data quantization mode. Either MIN_FIRST(default) or SCALED.
     */
    public final String inputQuantMode;

    public Inputs(GraphOperation op) {
      super(new QuantizedMatMulWithBiasAndRelu<>(op), op, Arrays.asList("T1", "T2", "Toutput", "transpose_a", "transpose_b", "input_quant_mode"));
      int inputIndex = 0;
      a = (Operand<? extends TNumber>) op.input(inputIndex++);
      b = (Operand<? extends TNumber>) op.input(inputIndex++);
      bias = (Operand<TFloat32>) op.input(inputIndex++);
      minA = (Operand<TFloat32>) op.input(inputIndex++);
      maxA = (Operand<TFloat32>) op.input(inputIndex++);
      minB = (Operand<TFloat32>) op.input(inputIndex++);
      maxB = (Operand<TFloat32>) op.input(inputIndex++);
      T1 = op.attributes().getAttrType("T1");
      T2 = op.attributes().getAttrType("T2");
      Toutput = op.attributes().getAttrType("Toutput");
      transposeA = op.attributes().getAttrBool("transpose_a");
      transposeB = op.attributes().getAttrBool("transpose_b");
      inputQuantMode = op.attributes().getAttrString("input_quant_mode");
    }
  }
}
