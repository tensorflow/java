/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.core;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Perform quantized add of quantized Tensor {@code lhs} and quantized Tensor {@code rhs} to make quantized {@code output}.
 * Given quantized {@code lhs} and quantized {@code rhs}, performs quantized add on {@code lhs} and {@code rhs} to make quantized {@code output}.
 * <p>{@code UniformQuantizedAdd} follows Numpy broadcasting rules.
 * The two input array shapes are compared element-wise.
 * Starting with the trailing dimensions, the two dimensions either have to be equal or one of them needs to be 1.
 * <p>{@code lhs} and {@code rhs} must be quantized Tensor, where data value is quantized using the formula:
 * <pre>
 * quantized_data = clip(original_data / scale + zero_point, quantization_min_val, quantization_max_val)
 * </pre>
 * <p>{@code output} is also quantized, using the same formula.
 * <p>If {@code lhs} and {@code output} is both per-axis quantized, the quantization axis must match.
 * Also, if {@code rhs} and {@code output} is both per-axis quantized, the quantization axis must match.
 * <em>Match</em> means the axis must match when adding, regarding the broadcasting.
 * i.e. For both operands {@code lhs} and {@code rhs},
 * if {@code operand.quantization_axis} &gt;= 0 and {@code output.quantization_axis} &gt;= 0,
 * {@code operand.dims} - {@code operand.quantization_axis} must be equal to {@code output.dims} - {@code output.quantization_axis}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = UniformQuantizedAdd.OP_NAME,
    inputsClass = UniformQuantizedAdd.Inputs.class
)
public final class UniformQuantizedAdd<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UniformQuantizedAdd";

  private Output<T> output;

  public UniformQuantizedAdd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UniformQuantizedAdd operation.
   *
   * @param scope current scope
   * @param lhs Must be a quantized tensor.
   * @param rhs Must be a quantized tensor.
   * @param lhsScales The float value(s) used as scale factors when quantizing the original data that {@code lhs} represents.
   * @param lhsZeroPoints The int32 value(s) used as zero points when quantizing original data that {@code lhs} represents.
   * Must have same shape with {@code lhs_scales}.
   * @param rhsScales The float value(s) used as scale factors when quantizing the original data that {@code rhs} represents.
   * @param rhsZeroPoints The int32 value(s) used as zero points when quantizing original data that {@code rhs} represents.
   * Must have same shape with {@code rhs_scales}.
   * @param outputScales The float value(s) to use as scale factors when quantizing original data that {@code output} represents.
   * @param outputZeroPoints The int32 value(s) used as zero points when quantizing original data that output represents.
   * Must have same shape with {@code output_scales}.
   * @param lhsQuantizationMinVal The min value of the quantized data stored in {@code lhs}.
   * For example, if {@code Tin} is {@code qint8}, this must be set to -127 if narrow range quantized or -128 if not.
   * @param lhsQuantizationMaxVal The max value of the quantized data stored in {@code lhs}.
   * For example, if {@code Tin} is {@code qint8}, this must be set to 127.
   * @param rhsQuantizationMinVal The min value of the quantized data stored in {@code rhs}.
   * For example, if {@code Tin} is {@code qint8}, this must be set to -127 if narrow range quantized or -128 if not.
   * @param rhsQuantizationMaxVal The max value of the quantized data stored in {@code rhs}.
   * For example, if {@code Tin} is {@code qint8}, this must be set to 127.
   * @param outputQuantizationMinVal The min value of the quantized data stored in {@code output}.
   * For example, if  {@code Tout} is {@code qint8}, this must be set to -127 if narrow range quantized or -128 if not.
   * @param outputQuantizationMaxVal The max value of the quantized data stored in {@code output}.
   * For example, if {@code Tout} is {@code qint8}, this must be set to 127.
   * @param options carries optional attribute values
   * @param <T> data type for {@code UniformQuantizedAdd} output and operands
   * @return a new instance of UniformQuantizedAdd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> UniformQuantizedAdd<T> create(Scope scope, Operand<T> lhs,
      Operand<T> rhs, Operand<TFloat32> lhsScales, Operand<TInt32> lhsZeroPoints,
      Operand<TFloat32> rhsScales, Operand<TInt32> rhsZeroPoints, Operand<TFloat32> outputScales,
      Operand<TInt32> outputZeroPoints, Long lhsQuantizationMinVal, Long lhsQuantizationMaxVal,
      Long rhsQuantizationMinVal, Long rhsQuantizationMaxVal, Long outputQuantizationMinVal,
      Long outputQuantizationMaxVal, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UniformQuantizedAdd");
    opBuilder.addInput(lhs.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder.addInput(lhsScales.asOutput());
    opBuilder.addInput(lhsZeroPoints.asOutput());
    opBuilder.addInput(rhsScales.asOutput());
    opBuilder.addInput(rhsZeroPoints.asOutput());
    opBuilder.addInput(outputScales.asOutput());
    opBuilder.addInput(outputZeroPoints.asOutput());
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
    return new UniformQuantizedAdd<>(opBuilder.build());
  }

  /**
   * Sets the lhsQuantizationAxis option.
   *
   * @param lhsQuantizationAxis Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
   * If set to -1 (default), this indicates per-tensor quantization.
   * For the {@code lhs}, only per-tensor quantization is supported.
   * Thus, this must be set to -1.
   * Other values will raise error at OpKernel construction.
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
   * For the {@code rhs}, only per-tensor quantization
   * or per-channel quantization along {@code kernel_output_feature_dimension} is supported.
   * Thus, this must be set to -1 or {@code dimension_numbers.kernel_output_feature_dimension}.
   * Other values will raise error at OpKernel construction.
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
   * For the {@code output}, only per-tensor quantization or per-channel quantization along {@code output_feature_dimension} is supported.
   * Thus, this must be set to -1 or {@code dimension_numbers.output_feature_dimension}.
   * Other values will raise error at OpKernel construction.
   * @return this Options instance.
   */
  public static Options outputQuantizationAxis(Long outputQuantizationAxis) {
    return new Options().outputQuantizationAxis(outputQuantizationAxis);
  }

  /**
   * Gets output.
   * The output quantized tensor.
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
   * Optional attributes for {@link org.tensorflow.op.core.UniformQuantizedAdd}
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
     * For the {@code lhs}, only per-tensor quantization is supported.
     * Thus, this must be set to -1.
     * Other values will raise error at OpKernel construction.
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
     * For the {@code rhs}, only per-tensor quantization
     * or per-channel quantization along {@code kernel_output_feature_dimension} is supported.
     * Thus, this must be set to -1 or {@code dimension_numbers.kernel_output_feature_dimension}.
     * Other values will raise error at OpKernel construction.
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
     * For the {@code output}, only per-tensor quantization or per-channel quantization along {@code output_feature_dimension} is supported.
     * Thus, this must be set to -1 or {@code dimension_numbers.output_feature_dimension}.
     * Other values will raise error at OpKernel construction.
     * @return this Options instance.
     */
    public Options outputQuantizationAxis(Long outputQuantizationAxis) {
      this.outputQuantizationAxis = outputQuantizationAxis;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = UniformQuantizedAdd.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<UniformQuantizedAdd<T>> {
    /**
     * Must be a quantized tensor.
     */
    public final Operand<T> lhs;

    /**
     * Must be a quantized tensor.
     */
    public final Operand<T> rhs;

    /**
     * The float value(s) used as scale factors when quantizing the original data that {@code lhs} represents.
     */
    public final Operand<TFloat32> lhsScales;

    /**
     * The int32 value(s) used as zero points when quantizing original data that {@code lhs} represents.
     * Must have same shape with {@code lhs_scales}.
     */
    public final Operand<TInt32> lhsZeroPoints;

    /**
     * The float value(s) used as scale factors when quantizing the original data that {@code rhs} represents.
     */
    public final Operand<TFloat32> rhsScales;

    /**
     * The int32 value(s) used as zero points when quantizing original data that {@code rhs} represents.
     * Must have same shape with {@code rhs_scales}.
     */
    public final Operand<TInt32> rhsZeroPoints;

    /**
     * The float value(s) to use as scale factors when quantizing original data that {@code output} represents.
     */
    public final Operand<TFloat32> outputScales;

    /**
     * The int32 value(s) used as zero points when quantizing original data that output represents.
     * Must have same shape with {@code output_scales}.
     */
    public final Operand<TInt32> outputZeroPoints;

    /**
     * Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization.
     * For the `lhs`, only per-tensor quantization is supported.
     * Thus, this must be set to -1.
     * Other values will raise error at OpKernel construction.
     */
    public final long lhsQuantizationAxis;

    /**
     * The min value of the quantized data stored in `lhs`.
     * For example, if `Tin` is `qint8`, this must be set to -127 if narrow range quantized or -128 if not.
     */
    public final long lhsQuantizationMinVal;

    /**
     * The max value of the quantized data stored in `lhs`.
     * For example, if `Tin` is `qint8`, this must be set to 127.
     */
    public final long lhsQuantizationMaxVal;

    /**
     * Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization.
     * For the `rhs`, only per-tensor quantization
     * or per-channel quantization along `kernel_output_feature_dimension` is supported.
     * Thus, this must be set to -1 or `dimension_numbers.kernel_output_feature_dimension`.
     * Other values will raise error at OpKernel construction.
     */
    public final long rhsQuantizationAxis;

    /**
     * The min value of the quantized data stored in `rhs`.
     * For example, if `Tin` is `qint8`, this must be set to -127 if narrow range quantized or -128 if not.
     */
    public final long rhsQuantizationMinVal;

    /**
     * The max value of the quantized data stored in `rhs`.
     * For example, if `Tin` is `qint8`, this must be set to 127.
     */
    public final long rhsQuantizationMaxVal;

    /**
     * Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization.
     * For the `output`, only per-tensor quantization or per-channel quantization along `output_feature_dimension` is supported.
     * Thus, this must be set to -1 or `dimension_numbers.output_feature_dimension`.
     * Other values will raise error at OpKernel construction.
     */
    public final long outputQuantizationAxis;

    /**
     * The min value of the quantized data stored in `output`.
     * For example, if  `Tout` is `qint8`, this must be set to -127 if narrow range quantized or -128 if not.
     */
    public final long outputQuantizationMinVal;

    /**
     * The max value of the quantized data stored in `output`.
     * For example, if `Tout` is `qint8`, this must be set to 127.
     */
    public final long outputQuantizationMaxVal;

    /**
     * The type of `lhs`, `rhs`, and `output`.
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new UniformQuantizedAdd<>(op), op, Arrays.asList("lhs_quantization_axis", "lhs_quantization_min_val", "lhs_quantization_max_val", "rhs_quantization_axis", "rhs_quantization_min_val", "rhs_quantization_max_val", "output_quantization_axis", "output_quantization_min_val", "output_quantization_max_val", "T"));
      int inputIndex = 0;
      lhs = (Operand<T>) op.input(inputIndex++);
      rhs = (Operand<T>) op.input(inputIndex++);
      lhsScales = (Operand<TFloat32>) op.input(inputIndex++);
      lhsZeroPoints = (Operand<TInt32>) op.input(inputIndex++);
      rhsScales = (Operand<TFloat32>) op.input(inputIndex++);
      rhsZeroPoints = (Operand<TInt32>) op.input(inputIndex++);
      outputScales = (Operand<TFloat32>) op.input(inputIndex++);
      outputZeroPoints = (Operand<TInt32>) op.input(inputIndex++);
      lhsQuantizationAxis = op.attributes().getAttrInt("lhs_quantization_axis");
      lhsQuantizationMinVal = op.attributes().getAttrInt("lhs_quantization_min_val");
      lhsQuantizationMaxVal = op.attributes().getAttrInt("lhs_quantization_max_val");
      rhsQuantizationAxis = op.attributes().getAttrInt("rhs_quantization_axis");
      rhsQuantizationMinVal = op.attributes().getAttrInt("rhs_quantization_min_val");
      rhsQuantizationMaxVal = op.attributes().getAttrInt("rhs_quantization_max_val");
      outputQuantizationAxis = op.attributes().getAttrInt("output_quantization_axis");
      outputQuantizationMinVal = op.attributes().getAttrInt("output_quantization_min_val");
      outputQuantizationMaxVal = op.attributes().getAttrInt("output_quantization_max_val");
      T = op.attributes().getAttrType("T");
    }
  }
}
