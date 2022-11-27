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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Perform hybrid quantized dot of float Tensor {@code lhs} and quantized Tensor {@code rhs}.
 * Given float {@code lhs} and quantized {@code rhs}, internally performs quantization on {@code lhs}, and then performs quantized dot on quantized lhs and {@code rhs}.
 * The internal quantization on {@code lhs} is a quantization to qint8, dynamic range, per-batch (per-axis along axis 0), asymmetric, and not narrow range (the range is [-128, 127]).
 * {@code lhs} and {@code rhs} must be 2D Tensors and the lhs.dim_size(1) must match rhs.dim_size(0).
 * {@code rhs} must be quantized Tensor, where its data value is quantized using the formula:
 * quantized_data = clip(original_data / scale + zero_point, quantization_min_val, quantization_max_val).
 *
 * @param <V> data type for {@code output} output
 */
@OpMetadata(
    opType = UniformQuantizedDotHybrid.OP_NAME,
    inputsClass = UniformQuantizedDotHybrid.Inputs.class
)
public final class UniformQuantizedDotHybrid<V extends TNumber> extends RawOp implements Operand<V> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UniformQuantizedDotHybrid";

  private Output<V> output;

  public UniformQuantizedDotHybrid(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UniformQuantizedDotHybrid operation.
   *
   * @param scope current scope
   * @param lhs Must be a 2D Tensor of Tlhs.
   * @param rhs Must be a 2D Tensor of Trhs.
   * @param rhsScales The float value(s) used as scale when quantizing original data that rhs represents.
   * Must be a scalar Tensor (per-tensor quantization) or 1D Tensor of size (rhs.dim_size(1),) (per-channel quantization).
   * @param rhsZeroPoints The int32 value(s) used as zero_point when quantizing original data that rhs represents.
   * Same shape condition as rhs_scales.
   * @param Tout The type of output Tensor.
   * @param rhsQuantizationMinVal The min value of the quantized data stored in rhs.
   * For example, if Trhs is qint8, this must be set to -127 if narrow range quantized or -128 if not.
   * @param rhsQuantizationMaxVal The max value of the quantized data stored in rhs.
   * For example, if Trhs is qint8, this must be set to 127.
   * @param options carries optional attribute values
   * @param <V> data type for {@code UniformQuantizedDotHybrid} output and operands
   * @return a new instance of UniformQuantizedDotHybrid
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> UniformQuantizedDotHybrid<V> create(Scope scope,
      Operand<? extends TNumber> lhs, Operand<? extends TNumber> rhs, Operand<TFloat32> rhsScales,
      Operand<TInt32> rhsZeroPoints, Class<V> Tout, Long rhsQuantizationMinVal,
      Long rhsQuantizationMaxVal, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UniformQuantizedDotHybrid");
    opBuilder.addInput(lhs.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder.addInput(rhsScales.asOutput());
    opBuilder.addInput(rhsZeroPoints.asOutput());
    opBuilder.setAttr("Tout", Operands.toDataType(Tout));
    opBuilder.setAttr("rhs_quantization_min_val", rhsQuantizationMinVal);
    opBuilder.setAttr("rhs_quantization_max_val", rhsQuantizationMaxVal);
    if (options != null) {
      for (Options opts : options) {
        if (opts.rhsQuantizationAxis != null) {
          opBuilder.setAttr("rhs_quantization_axis", opts.rhsQuantizationAxis);
        }
      }
    }
    return new UniformQuantizedDotHybrid<>(opBuilder.build());
  }

  /**
   * Sets the rhsQuantizationAxis option.
   *
   * @param rhsQuantizationAxis Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
   * If set to -1 (default), this indicates per-tensor quantization.
   * For dot op rhs, only per-tensor quantization or per-channel quantization along dimension 1 is supported.
   * Thus, this attribute must be set to -1 or 1. Other values are rejected.
   * @return this Options instance.
   */
  public static Options rhsQuantizationAxis(Long rhsQuantizationAxis) {
    return new Options().rhsQuantizationAxis(rhsQuantizationAxis);
  }

  /**
   * Gets output.
   * The output 2D Tensor of Tout, whose shape is (lhs.dim_size(0), rhs.dim_size(1)).
   * The output data is the original output data itself (Not quantized).
   * @return output.
   */
  public Output<V> output() {
    return output;
  }

  @Override
  public Output<V> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.quantization.UniformQuantizedDotHybrid}
   */
  public static class Options {
    private Long rhsQuantizationAxis;

    private Options() {
    }

    /**
     * Sets the rhsQuantizationAxis option.
     *
     * @param rhsQuantizationAxis Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization.
     * For dot op rhs, only per-tensor quantization or per-channel quantization along dimension 1 is supported.
     * Thus, this attribute must be set to -1 or 1. Other values are rejected.
     * @return this Options instance.
     */
    public Options rhsQuantizationAxis(Long rhsQuantizationAxis) {
      this.rhsQuantizationAxis = rhsQuantizationAxis;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = UniformQuantizedDotHybrid.class
  )
  public static class Inputs extends RawOpInputs<UniformQuantizedDotHybrid<?>> {
    /**
     * Must be a 2D Tensor of Tlhs.
     */
    public final Operand<? extends TNumber> lhs;

    /**
     * Must be a 2D Tensor of Trhs.
     */
    public final Operand<? extends TNumber> rhs;

    /**
     * The float value(s) used as scale when quantizing original data that rhs represents.
     * Must be a scalar Tensor (per-tensor quantization) or 1D Tensor of size (rhs.dim_size(1),) (per-channel quantization).
     */
    public final Operand<TFloat32> rhsScales;

    /**
     * The int32 value(s) used as zero_point when quantizing original data that rhs represents.
     * Same shape condition as rhs_scales.
     */
    public final Operand<TInt32> rhsZeroPoints;

    /**
     * The type of lhs input Tensor.
     */
    public final DataType Tlhs;

    /**
     * The type of rhs (quantized) input Tensor.
     */
    public final DataType Trhs;

    /**
     * The type of output Tensor.
     */
    public final DataType Tout;

    /**
     * Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization.
     * For dot op rhs, only per-tensor quantization or per-channel quantization along dimension 1 is supported.
     * Thus, this attribute must be set to -1 or 1. Other values are rejected.
     */
    public final long rhsQuantizationAxis;

    /**
     * The min value of the quantized data stored in rhs.
     * For example, if Trhs is qint8, this must be set to -127 if narrow range quantized or -128 if not.
     */
    public final long rhsQuantizationMinVal;

    /**
     * The max value of the quantized data stored in rhs.
     * For example, if Trhs is qint8, this must be set to 127.
     */
    public final long rhsQuantizationMaxVal;

    public Inputs(GraphOperation op) {
      super(new UniformQuantizedDotHybrid<>(op), op, Arrays.asList("Tlhs", "Trhs", "Tout", "rhs_quantization_axis", "rhs_quantization_min_val", "rhs_quantization_max_val"));
      int inputIndex = 0;
      lhs = (Operand<? extends TNumber>) op.input(inputIndex++);
      rhs = (Operand<? extends TNumber>) op.input(inputIndex++);
      rhsScales = (Operand<TFloat32>) op.input(inputIndex++);
      rhsZeroPoints = (Operand<TInt32>) op.input(inputIndex++);
      Tlhs = op.attributes().getAttrType("Tlhs");
      Trhs = op.attributes().getAttrType("Trhs");
      Tout = op.attributes().getAttrType("Tout");
      rhsQuantizationAxis = op.attributes().getAttrInt("rhs_quantization_axis");
      rhsQuantizationMinVal = op.attributes().getAttrInt("rhs_quantization_min_val");
      rhsQuantizationMaxVal = op.attributes().getAttrInt("rhs_quantization_max_val");
    }
  }
}
