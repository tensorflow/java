/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Perform quantized dot of quantized Tensor {@code lhs} and quantized Tensor {@code rhs} to make quantized {@code output}.
 * Given quantized {@code lhs} and quantized {@code rhs}, performs quantized dot on {@code lhs} and {@code rhs} to make quantized {@code output}.
 * {@code lhs} and {@code rhs} must be 2D Tensors and the lhs.dim_size(1) must match rhs.dim_size(0).
 * {@code lhs} and {@code rhs} must be quantized Tensor, where data value is quantized using the formula:
 * quantized_data = clip(original_data / scale + zero_point, quantization_min_val, quantization_max_val).
 * {@code output} is also quantized, using the same formula.
 * If {@code rhs} is per-tensor quantized, {@code output} must be also per-tensor quantized.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = UniformQuantizedDot.OP_NAME,
    inputsClass = UniformQuantizedDot.Inputs.class
)
@Operator(
    group = "quantization"
)
public final class UniformQuantizedDot<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UniformQuantizedDot";

  private Output<U> output;

  public UniformQuantizedDot(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UniformQuantizedDot operation.
   *
   * @param scope current scope
   * @param lhs Must be a 2D Tensor of Tin.
   * @param rhs Must be a 2D Tensor of Tin.
   * @param lhsScales The float value(s) used as scale when quantizing original data that lhs represents.
   * Must be a scalar Tensor (lhs supports only per-tensor quantization).
   * @param lhsZeroPoints The int32 value(s) used as zero_point when quantizing original data that lhs represents.
   * Same shape condition as lhs_scales.
   * @param rhsScales The float value(s) used as scale when quantizing original data that rhs represents.
   * Must be a scalar Tensor (per-tensor quantization) or 1D Tensor of size (rhs.dim_size(1),) (per-channel quantization).
   * @param rhsZeroPoints The int32 value(s) used as zero_point when quantizing original data that rhs represents.
   * Same shape condition as rhs_scales.
   * @param outputScales The float value(s) to use as scales when quantizing original data that output represents.
   * Must be a scalar Tensor (per-tensor quantization) or 1D Tensor of size (output.dim_size(1),) (per-channel quantization).
   * If rhs is per-tensor quantized, output must be also per-tensor quantized.
   * This means that if rhs_scales and rhs_zero_points are scalar Tensors, output_scales and output_zero_points must be scalar Tensors as well.
   * @param outputZeroPoints The int32 value(s) used as zero_point when quantizing original data that output represents.
   * Same shape condition as rhs_scales.
   * @param Tout The type of output Tensor.
   * @param lhsQuantizationMinVal The min value of the quantized data stored in lhs.
   * For example, if Tin is qint8, this must be set to -127 if narrow range quantized or -128 if not.
   * @param lhsQuantizationMaxVal The max value of the quantized data stored in rhs.
   * For example, if Tin is qint8, this must be set to 127.
   * @param rhsQuantizationMinVal The min value of the quantized data stored in rhs.
   * For example, if Trhs is qint8, this must be set to -127 if narrow range quantized or -128 if not.
   * @param rhsQuantizationMaxVal The max value of the quantized data stored in rhs.
   * For example, if Trhs is qint8, this must be set to 127.
   * @param outputQuantizationMinVal The min value of the quantized data stored in output.
   * For example, if Tout is qint8, this must be set to -127 if narrow range quantized or -128 if not.
   * @param outputQuantizationMaxVal The max value of the quantized data stored in output.
   * For example, if Tout is qint8, this must be set to 127.
   * @param options carries optional attribute values
   * @param <U> data type for {@code UniformQuantizedDot} output and operands
   * @param <T> data type for {@code UniformQuantizedDot} output and operands
   * @return a new instance of UniformQuantizedDot
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber, T extends TNumber> UniformQuantizedDot<U> create(Scope scope,
      Operand<T> lhs, Operand<T> rhs, Operand<TFloat32> lhsScales, Operand<TInt32> lhsZeroPoints,
      Operand<TFloat32> rhsScales, Operand<TInt32> rhsZeroPoints, Operand<TFloat32> outputScales,
      Operand<TInt32> outputZeroPoints, Class<U> Tout, Long lhsQuantizationMinVal,
      Long lhsQuantizationMaxVal, Long rhsQuantizationMinVal, Long rhsQuantizationMaxVal,
      Long outputQuantizationMinVal, Long outputQuantizationMaxVal, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UniformQuantizedDot");
    opBuilder.addInput(lhs.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder.addInput(lhsScales.asOutput());
    opBuilder.addInput(lhsZeroPoints.asOutput());
    opBuilder.addInput(rhsScales.asOutput());
    opBuilder.addInput(rhsZeroPoints.asOutput());
    opBuilder.addInput(outputScales.asOutput());
    opBuilder.addInput(outputZeroPoints.asOutput());
    opBuilder.setAttr("Tout", Operands.toDataType(Tout));
    opBuilder.setAttr("lhs_quantization_min_val", lhsQuantizationMinVal);
    opBuilder.setAttr("lhs_quantization_max_val", lhsQuantizationMaxVal);
    opBuilder.setAttr("rhs_quantization_min_val", rhsQuantizationMinVal);
    opBuilder.setAttr("rhs_quantization_max_val", rhsQuantizationMaxVal);
    opBuilder.setAttr("output_quantization_min_val", outputQuantizationMinVal);
    opBuilder.setAttr("output_quantization_max_val", outputQuantizationMaxVal);
    if (options != null) {
      for (Options opts : options) {
        if (opts.lhsQuantizationAxis != null) {
          opBuilder.setAttr("lhs_quantization_axis", opts.lhsQuantizationAxis);
        }
        if (opts.rhsQuantizationAxis != null) {
          opBuilder.setAttr("rhs_quantization_axis", opts.rhsQuantizationAxis);
        }
        if (opts.outputQuantizationAxis != null) {
          opBuilder.setAttr("output_quantization_axis", opts.outputQuantizationAxis);
        }
      }
    }
    return new UniformQuantizedDot<>(opBuilder.build());
  }

  /**
   * Sets the lhsQuantizationAxis option.
   *
   * @param lhsQuantizationAxis Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
   * If set to -1 (default), this indicates per-tensor quantization.
   * For dot op lhs, only per-tensor quantization is supported.
   * Thus, this attribute must be set to -1. Other values are rejected.
   * @return this Options instance.
   */
  public static Options lhsQuantizationAxis(Long lhsQuantizationAxis) {
    return new Options().lhsQuantizationAxis(lhsQuantizationAxis);
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
   * Sets the outputQuantizationAxis option.
   *
   * @param outputQuantizationAxis Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
   * If set to -1 (default), this indicates per-tensor quantization.
   * For dot op output, only per-tensor quantization or per-channel quantization along dimension 1 is supported.
   * Thus, this attribute must be set to -1 or 1. Other values are rejected.
   * @return this Options instance.
   */
  public static Options outputQuantizationAxis(Long outputQuantizationAxis) {
    return new Options().outputQuantizationAxis(outputQuantizationAxis);
  }

  /**
   * Gets output.
   * The output 2D Tensor of Tout, whose shape is (lhs.dim_size(0), rhs.dim_size(1)).
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.quantization.UniformQuantizedDot}
   */
  public static class Options {
    private Long lhsQuantizationAxis;

    private Long rhsQuantizationAxis;

    private Long outputQuantizationAxis;

    private Options() {
    }

    /**
     * Sets the lhsQuantizationAxis option.
     *
     * @param lhsQuantizationAxis Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization.
     * For dot op lhs, only per-tensor quantization is supported.
     * Thus, this attribute must be set to -1. Other values are rejected.
     * @return this Options instance.
     */
    public Options lhsQuantizationAxis(Long lhsQuantizationAxis) {
      this.lhsQuantizationAxis = lhsQuantizationAxis;
      return this;
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

    /**
     * Sets the outputQuantizationAxis option.
     *
     * @param outputQuantizationAxis Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization.
     * For dot op output, only per-tensor quantization or per-channel quantization along dimension 1 is supported.
     * Thus, this attribute must be set to -1 or 1. Other values are rejected.
     * @return this Options instance.
     */
    public Options outputQuantizationAxis(Long outputQuantizationAxis) {
      this.outputQuantizationAxis = outputQuantizationAxis;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = UniformQuantizedDot.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<UniformQuantizedDot<?>> {
    /**
     * Must be a 2D Tensor of Tin.
     */
    public final Operand<T> lhs;

    /**
     * Must be a 2D Tensor of Tin.
     */
    public final Operand<T> rhs;

    /**
     * The float value(s) used as scale when quantizing original data that lhs represents.
     * Must be a scalar Tensor (lhs supports only per-tensor quantization).
     */
    public final Operand<TFloat32> lhsScales;

    /**
     * The int32 value(s) used as zero_point when quantizing original data that lhs represents.
     * Same shape condition as lhs_scales.
     */
    public final Operand<TInt32> lhsZeroPoints;

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
     * The float value(s) to use as scales when quantizing original data that output represents.
     * Must be a scalar Tensor (per-tensor quantization) or 1D Tensor of size (output.dim_size(1),) (per-channel quantization).
     * If rhs is per-tensor quantized, output must be also per-tensor quantized.
     * This means that if rhs_scales and rhs_zero_points are scalar Tensors, output_scales and output_zero_points must be scalar Tensors as well.
     */
    public final Operand<TFloat32> outputScales;

    /**
     * The int32 value(s) used as zero_point when quantizing original data that output represents.
     * Same shape condition as rhs_scales.
     */
    public final Operand<TInt32> outputZeroPoints;

    /**
     * The type of lhs and rhs input Tensor.
     */
    public final DataType Tin;

    /**
     * The type of output Tensor.
     */
    public final DataType Tout;

    /**
     * Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization.
     * For dot op lhs, only per-tensor quantization is supported.
     * Thus, this attribute must be set to -1. Other values are rejected.
     */
    public final long lhsQuantizationAxis;

    /**
     * The min value of the quantized data stored in lhs.
     * For example, if Tin is qint8, this must be set to -127 if narrow range quantized or -128 if not.
     */
    public final long lhsQuantizationMinVal;

    /**
     * The max value of the quantized data stored in rhs.
     * For example, if Tin is qint8, this must be set to 127.
     */
    public final long lhsQuantizationMaxVal;

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

    /**
     * Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization.
     * For dot op output, only per-tensor quantization or per-channel quantization along dimension 1 is supported.
     * Thus, this attribute must be set to -1 or 1. Other values are rejected.
     */
    public final long outputQuantizationAxis;

    /**
     * The min value of the quantized data stored in output.
     * For example, if Tout is qint8, this must be set to -127 if narrow range quantized or -128 if not.
     */
    public final long outputQuantizationMinVal;

    /**
     * The max value of the quantized data stored in output.
     * For example, if Tout is qint8, this must be set to 127.
     */
    public final long outputQuantizationMaxVal;

    public Inputs(GraphOperation op) {
      super(new UniformQuantizedDot<>(op), op, Arrays.asList("Tin", "Tout", "lhs_quantization_axis", "lhs_quantization_min_val", "lhs_quantization_max_val", "rhs_quantization_axis", "rhs_quantization_min_val", "rhs_quantization_max_val", "output_quantization_axis", "output_quantization_min_val", "output_quantization_max_val"));
      int inputIndex = 0;
      lhs = (Operand<T>) op.input(inputIndex++);
      rhs = (Operand<T>) op.input(inputIndex++);
      lhsScales = (Operand<TFloat32>) op.input(inputIndex++);
      lhsZeroPoints = (Operand<TInt32>) op.input(inputIndex++);
      rhsScales = (Operand<TFloat32>) op.input(inputIndex++);
      rhsZeroPoints = (Operand<TInt32>) op.input(inputIndex++);
      outputScales = (Operand<TFloat32>) op.input(inputIndex++);
      outputZeroPoints = (Operand<TInt32>) op.input(inputIndex++);
      Tin = op.attributes().getAttrType("Tin");
      Tout = op.attributes().getAttrType("Tout");
      lhsQuantizationAxis = op.attributes().getAttrInt("lhs_quantization_axis");
      lhsQuantizationMinVal = op.attributes().getAttrInt("lhs_quantization_min_val");
      lhsQuantizationMaxVal = op.attributes().getAttrInt("lhs_quantization_max_val");
      rhsQuantizationAxis = op.attributes().getAttrInt("rhs_quantization_axis");
      rhsQuantizationMinVal = op.attributes().getAttrInt("rhs_quantization_min_val");
      rhsQuantizationMaxVal = op.attributes().getAttrInt("rhs_quantization_max_val");
      outputQuantizationAxis = op.attributes().getAttrInt("output_quantization_axis");
      outputQuantizationMinVal = op.attributes().getAttrInt("output_quantization_min_val");
      outputQuantizationMaxVal = op.attributes().getAttrInt("output_quantization_max_val");
    }
  }
}
