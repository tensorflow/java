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
 * Given quantized tensor {@code input}, requantize it with new quantization parameters.
 * Given quantized tensor {@code input}, which was quantized using {input_scales, input_zero_points, input_quantization_axis, input_quantization_min_val, input_quantization_max_val},
 * requantize it to a tensor, which is quantized using {output_scales, output_zero_points, output_quantization_axis, output_quantization_min_val, output_quantization_max_val}.
 * The requantization is done by using the formula:
 * output_quantized_data = clip(
 * (input_quantized_data - input_zero_point) * (input_scale / output_scale) + output_zero_point,
 * output_quantization_min_val,
 * output_quantization_max_val)
 * <p>Per-tensor and per-axis quantization supported cases are followings:
 * <ul>
 * <li>per-tensor -&gt; per-tensor</li>
 * <li>per-tensor -&gt; per-axis</li>
 * <li>per-axis -&gt; per-axis where input_quantization_axis equals output_quantization_axis.
 * i.e. At least one among input_quantization_axis and output_quantization_axis must be -1, or two must be equal.</li>
 * </ul>
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = UniformRequantize.OP_NAME,
    inputsClass = UniformRequantize.Inputs.class
)
public final class UniformRequantize<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UniformRequantize";

  private Output<U> output;

  public UniformRequantize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UniformRequantize operation.
   *
   * @param scope current scope
   * @param input Must be a Tensor of Tin.
   * @param inputScales The float value(s) used as scale(s) when quantizing original data that {@code input} represents.
   * Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (input.dim_size(quantization_axis),) (per-axis quantization).
   * @param inputZeroPoints The int32 value(s) used as zero_point(s) when quantizing original data that {@code input} represents.
   * Same shape condition as scales.
   * @param outputScales The float value(s) to use as new scale(s) to quantize original data that {@code input} represents.
   * Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (input.dim_size(quantization_axis),) (per-axis quantization).
   * @param outputZeroPoints The int32 value(s) to use as new zero_point(s) to quantize original data that {@code input} represents.
   * Same shape condition as scales.
   * @param Tout The type of output Tensor. A tf.DType from: tf.qint8, tf.qint32
   * @param inputQuantizationMinVal The quantization min value that was used when quantizing original data that {@code input} represents.
   * The purpose of this attribute is typically (but not limited to) to indicate narrow range, where this is set to:
   * {@code (Tin lowest) + 1} if narrow range, and {@code (Tin lowest)} otherwise.
   * For example, if Tin is qint8, this is set to -127 if narrow range quantized or -128 if not.
   * @param inputQuantizationMaxVal The quantization max value that was used when quantizing original data that {@code input} represents.
   * The purpose of this attribute is typically (but not limited to) indicate narrow range, where this is set to:
   * {@code (Tout max)} for both narrow range and not narrow range.
   * For example, if Tin is qint8, this is set to 127.
   * @param outputQuantizationMinVal The new quantization min value to quantize original data that {@code input} represents.
   * @param outputQuantizationMaxVal The new quantization max value to quantize original data that {@code input} represents.
   * @param options carries optional attribute values
   * @param <U> data type for {@code UniformRequantize} output and operands
   * @return a new instance of UniformRequantize
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> UniformRequantize<U> create(Scope scope,
      Operand<? extends TNumber> input, Operand<TFloat32> inputScales,
      Operand<TInt32> inputZeroPoints, Operand<TFloat32> outputScales,
      Operand<TInt32> outputZeroPoints, Class<U> Tout, Long inputQuantizationMinVal,
      Long inputQuantizationMaxVal, Long outputQuantizationMinVal, Long outputQuantizationMaxVal,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UniformRequantize");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputScales.asOutput());
    opBuilder.addInput(inputZeroPoints.asOutput());
    opBuilder.addInput(outputScales.asOutput());
    opBuilder.addInput(outputZeroPoints.asOutput());
    opBuilder.setAttr("Tout", Operands.toDataType(Tout));
    opBuilder.setAttr("input_quantization_min_val", inputQuantizationMinVal);
    opBuilder.setAttr("input_quantization_max_val", inputQuantizationMaxVal);
    opBuilder.setAttr("output_quantization_min_val", outputQuantizationMinVal);
    opBuilder.setAttr("output_quantization_max_val", outputQuantizationMaxVal);
    if (options != null) {
      for (Options opts : options) {
        if (opts.inputQuantizationAxis != null) {
          opBuilder.setAttr("input_quantization_axis", opts.inputQuantizationAxis);
        }
        if (opts.outputQuantizationAxis != null) {
          opBuilder.setAttr("output_quantization_axis", opts.outputQuantizationAxis);
        }
      }
    }
    return new UniformRequantize<>(opBuilder.build());
  }

  /**
   * Sets the inputQuantizationAxis option.
   *
   * @param inputQuantizationAxis The quantization axis that was used when quantizing original data that {@code input} represents.
   * Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
   * If set to -1 (default), this indicates per-tensor quantization. Otherwise, it must be set within range [0, input.dims()).
   * @return this Options instance.
   */
  public static Options inputQuantizationAxis(Long inputQuantizationAxis) {
    return new Options().inputQuantizationAxis(inputQuantizationAxis);
  }

  /**
   * Sets the outputQuantizationAxis option.
   *
   * @param outputQuantizationAxis The new quantization axis to use to quantize original data that {@code input} represents.
   * @return this Options instance.
   */
  public static Options outputQuantizationAxis(Long outputQuantizationAxis) {
    return new Options().outputQuantizationAxis(outputQuantizationAxis);
  }

  /**
   * Gets output.
   * The output quantized Tensor of Tout, whose shape is same as input.
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
   * Optional attributes for {@link org.tensorflow.op.core.UniformRequantize}
   */
  public static class Options {
    private Long inputQuantizationAxis;

    private Long outputQuantizationAxis;

    private Options() {
    }

    /**
     * Sets the inputQuantizationAxis option.
     *
     * @param inputQuantizationAxis The quantization axis that was used when quantizing original data that {@code input} represents.
     * Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization. Otherwise, it must be set within range [0, input.dims()).
     * @return this Options instance.
     */
    public Options inputQuantizationAxis(Long inputQuantizationAxis) {
      this.inputQuantizationAxis = inputQuantizationAxis;
      return this;
    }

    /**
     * Sets the outputQuantizationAxis option.
     *
     * @param outputQuantizationAxis The new quantization axis to use to quantize original data that {@code input} represents.
     * @return this Options instance.
     */
    public Options outputQuantizationAxis(Long outputQuantizationAxis) {
      this.outputQuantizationAxis = outputQuantizationAxis;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = UniformRequantize.class
  )
  public static class Inputs extends RawOpInputs<UniformRequantize<?>> {
    /**
     * Must be a Tensor of Tin.
     */
    public final Operand<? extends TNumber> input;

    /**
     * The float value(s) used as scale(s) when quantizing original data that {@code input} represents.
     * Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (input.dim_size(quantization_axis),) (per-axis quantization).
     */
    public final Operand<TFloat32> inputScales;

    /**
     * The int32 value(s) used as zero_point(s) when quantizing original data that {@code input} represents.
     * Same shape condition as scales.
     */
    public final Operand<TInt32> inputZeroPoints;

    /**
     * The float value(s) to use as new scale(s) to quantize original data that {@code input} represents.
     * Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (input.dim_size(quantization_axis),) (per-axis quantization).
     */
    public final Operand<TFloat32> outputScales;

    /**
     * The int32 value(s) to use as new zero_point(s) to quantize original data that {@code input} represents.
     * Same shape condition as scales.
     */
    public final Operand<TInt32> outputZeroPoints;

    /**
     * The type of input Tensor. A tf.DType from: tf.qint8, tf.qint32
     */
    public final DataType Tin;

    /**
     * The type of output Tensor. A tf.DType from: tf.qint8, tf.qint32
     */
    public final DataType Tout;

    /**
     * The quantization axis that was used when quantizing original data that `input` represents.
     * Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization. Otherwise, it must be set within range [0, input.dims()).
     */
    public final long inputQuantizationAxis;

    /**
     * The quantization min value that was used when quantizing original data that `input` represents.
     * The purpose of this attribute is typically (but not limited to) to indicate narrow range, where this is set to:
     * `(Tin lowest) + 1` if narrow range, and `(Tin lowest)` otherwise.
     * For example, if Tin is qint8, this is set to -127 if narrow range quantized or -128 if not.
     */
    public final long inputQuantizationMinVal;

    /**
     * The quantization max value that was used when quantizing original data that `input` represents.
     * The purpose of this attribute is typically (but not limited to) indicate narrow range, where this is set to:
     * `(Tout max)` for both narrow range and not narrow range.
     * For example, if Tin is qint8, this is set to 127.
     */
    public final long inputQuantizationMaxVal;

    /**
     * The new quantization axis to use to quantize original data that `input` represents.
     */
    public final long outputQuantizationAxis;

    /**
     * The new quantization min value to quantize original data that `input` represents.
     */
    public final long outputQuantizationMinVal;

    /**
     * The new quantization max value to quantize original data that `input` represents.
     */
    public final long outputQuantizationMaxVal;

    public Inputs(GraphOperation op) {
      super(new UniformRequantize<>(op), op, Arrays.asList("Tin", "Tout", "input_quantization_axis", "input_quantization_min_val", "input_quantization_max_val", "output_quantization_axis", "output_quantization_min_val", "output_quantization_max_val"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      inputScales = (Operand<TFloat32>) op.input(inputIndex++);
      inputZeroPoints = (Operand<TInt32>) op.input(inputIndex++);
      outputScales = (Operand<TFloat32>) op.input(inputIndex++);
      outputZeroPoints = (Operand<TInt32>) op.input(inputIndex++);
      Tin = op.attributes().getAttrType("Tin");
      Tout = op.attributes().getAttrType("Tout");
      inputQuantizationAxis = op.attributes().getAttrInt("input_quantization_axis");
      inputQuantizationMinVal = op.attributes().getAttrInt("input_quantization_min_val");
      inputQuantizationMaxVal = op.attributes().getAttrInt("input_quantization_max_val");
      outputQuantizationAxis = op.attributes().getAttrInt("output_quantization_axis");
      outputQuantizationMinVal = op.attributes().getAttrInt("output_quantization_min_val");
      outputQuantizationMaxVal = op.attributes().getAttrInt("output_quantization_max_val");
    }
  }
}
