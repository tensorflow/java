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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Perform a quantized matrix multiplication of  {@code a} by the matrix {@code b}.
 * The inputs must be two-dimensional matrices and the inner dimension of
 * {@code a} (after being transposed if {@code transpose_a} is non-zero) must match the
 * outer dimension of {@code b} (after being transposed if {@code transposed_b} is
 * non-zero).
 *
 * @param <V> data type for {@code out} output
 */
@OpMetadata(
    opType = QuantizedMatMul.OP_NAME,
    inputsClass = QuantizedMatMul.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class QuantizedMatMul<V extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedMatMul";

  private Output<V> out;

  private Output<TFloat32> minOut;

  private Output<TFloat32> maxOut;

  public QuantizedMatMul(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
    minOut = operation.output(outputIdx++);
    maxOut = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizedMatMul operation.
   *
   * @param scope current scope
   * @param a Must be a two-dimensional tensor.
   * @param b Must be a two-dimensional tensor.
   * @param minA The float value that the lowest quantized {@code a} value represents.
   * @param maxA The float value that the highest quantized {@code a} value represents.
   * @param minB The float value that the lowest quantized {@code b} value represents.
   * @param maxB The float value that the highest quantized {@code b} value represents.
   * @param Toutput The value of the Toutput attribute
   * @param Tactivation The type of output produced by activation function
   * following this operation.
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedMatMul} output and operands
   * @param <W> data type for {@code QuantizedMatMul} output and operands
   * @return a new instance of QuantizedMatMul
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber, W extends TNumber> QuantizedMatMul<V> create(Scope scope,
      Operand<? extends TNumber> a, Operand<? extends TNumber> b, Operand<TFloat32> minA,
      Operand<TFloat32> maxA, Operand<TFloat32> minB, Operand<TFloat32> maxB, Class<V> Toutput,
      Class<W> Tactivation, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizedMatMul");
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder.addInput(minA.asOutput());
    opBuilder.addInput(maxA.asOutput());
    opBuilder.addInput(minB.asOutput());
    opBuilder.addInput(maxB.asOutput());
    opBuilder.setAttr("Toutput", Operands.toDataType(Toutput));
    opBuilder.setAttr("Tactivation", Operands.toDataType(Tactivation));
    if (options != null) {
      for (Options opts : options) {
        if (opts.transposeA != null) {
          opBuilder.setAttr("transpose_a", opts.transposeA);
        }
        if (opts.transposeB != null) {
          opBuilder.setAttr("transpose_b", opts.transposeB);
        }
      }
    }
    return new QuantizedMatMul<>(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.linalg.QuantizedMatMul}
   */
  public static class Options {
    private Boolean transposeA;

    private Boolean transposeB;

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
  }

  @OpInputsMetadata(
      outputsClass = QuantizedMatMul.class
  )
  public static class Inputs extends RawOpInputs<QuantizedMatMul<?>> {
    /**
     * Must be a two-dimensional tensor.
     */
    public final Operand<? extends TNumber> a;

    /**
     * Must be a two-dimensional tensor.
     */
    public final Operand<? extends TNumber> b;

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
     * The type of output produced by activation function
     * following this operation.
     */
    public final DataType Tactivation;

    public Inputs(GraphOperation op) {
      super(new QuantizedMatMul<>(op), op, Arrays.asList("T1", "T2", "Toutput", "transpose_a", "transpose_b", "Tactivation"));
      int inputIndex = 0;
      a = (Operand<? extends TNumber>) op.input(inputIndex++);
      b = (Operand<? extends TNumber>) op.input(inputIndex++);
      minA = (Operand<TFloat32>) op.input(inputIndex++);
      maxA = (Operand<TFloat32>) op.input(inputIndex++);
      minB = (Operand<TFloat32>) op.input(inputIndex++);
      maxB = (Operand<TFloat32>) op.input(inputIndex++);
      T1 = op.attributes().getAttrType("T1");
      T2 = op.attributes().getAttrType("T2");
      Toutput = op.attributes().getAttrType("Toutput");
      transposeA = op.attributes().getAttrBool("transpose_a");
      transposeB = op.attributes().getAttrBool("transpose_b");
      Tactivation = op.attributes().getAttrType("Tactivation");
    }
  }
}
