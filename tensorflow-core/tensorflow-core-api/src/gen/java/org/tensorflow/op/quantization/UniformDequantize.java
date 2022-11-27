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
 * Perform dequantization on the quantized Tensor {@code input}.
 * Given quantized {@code input} which was quantized using {@code scales} and {@code zero_points}, performs dequantization using the formula:
 * dequantized_data = (quantized_data - zero_point) * scale.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = UniformDequantize.OP_NAME,
    inputsClass = UniformDequantize.Inputs.class
)
public final class UniformDequantize<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UniformDequantize";

  private Output<U> output;

  public UniformDequantize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UniformDequantize operation.
   *
   * @param scope current scope
   * @param input Must be a Tensor of Tin.
   * @param scales The float value(s) used as scale(s) when quantizing original data that input represents.
   * Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (input.dim_size(quantization_axis),) (per-axis quantization).
   * @param zeroPoints The int32 value(s) used as zero_point(s) when quantizing original data that input represents.
   * Same shape condition as scales.
   * @param Tout The type of output Tensor. A tf.DType from: tf.qint8, tf.qint32
   * @param quantizationMinVal The quantization min value that was used when input was quantized.
   * The purpose of this attribute is typically (but not limited to) to indicate narrow range, where this is set to:
   * {@code (Tin lowest) + 1} if narrow range, and {@code (Tin lowest)} otherwise.
   * For example, if Tin is qint8, this is set to -127 if narrow range quantized or -128 if not.
   * @param quantizationMaxVal The quantization max value that was used when input was quantized.
   * The purpose of this attribute is typically (but not limited to) indicate narrow range, where this is set to:
   * {@code (Tout max)} for both narrow range and not narrow range.
   * For example, if Tin is qint8, this is set to 127.
   * @param options carries optional attribute values
   * @param <U> data type for {@code UniformDequantize} output and operands
   * @return a new instance of UniformDequantize
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> UniformDequantize<U> create(Scope scope,
      Operand<? extends TNumber> input, Operand<TFloat32> scales, Operand<TInt32> zeroPoints,
      Class<U> Tout, Long quantizationMinVal, Long quantizationMaxVal, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UniformDequantize");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(scales.asOutput());
    opBuilder.addInput(zeroPoints.asOutput());
    opBuilder.setAttr("Tout", Operands.toDataType(Tout));
    opBuilder.setAttr("quantization_min_val", quantizationMinVal);
    opBuilder.setAttr("quantization_max_val", quantizationMaxVal);
    if (options != null) {
      for (Options opts : options) {
        if (opts.quantizationAxis != null) {
          opBuilder.setAttr("quantization_axis", opts.quantizationAxis);
        }
      }
    }
    return new UniformDequantize<>(opBuilder.build());
  }

  /**
   * Sets the quantizationAxis option.
   *
   * @param quantizationAxis Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
   * If set to -1 (default), this indicates per-tensor quantization. Otherwise, it must be set within range [0, input.dims()).
   * @return this Options instance.
   */
  public static Options quantizationAxis(Long quantizationAxis) {
    return new Options().quantizationAxis(quantizationAxis);
  }

  /**
   * Gets output.
   * The output dequantized Tensor of Tout, whose shape is same as input.
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
   * Optional attributes for {@link org.tensorflow.op.quantization.UniformDequantize}
   */
  public static class Options {
    private Long quantizationAxis;

    private Options() {
    }

    /**
     * Sets the quantizationAxis option.
     *
     * @param quantizationAxis Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization. Otherwise, it must be set within range [0, input.dims()).
     * @return this Options instance.
     */
    public Options quantizationAxis(Long quantizationAxis) {
      this.quantizationAxis = quantizationAxis;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = UniformDequantize.class
  )
  public static class Inputs extends RawOpInputs<UniformDequantize<?>> {
    /**
     * Must be a Tensor of Tin.
     */
    public final Operand<? extends TNumber> input;

    /**
     * The float value(s) used as scale(s) when quantizing original data that input represents.
     * Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (input.dim_size(quantization_axis),) (per-axis quantization).
     */
    public final Operand<TFloat32> scales;

    /**
     * The int32 value(s) used as zero_point(s) when quantizing original data that input represents.
     * Same shape condition as scales.
     */
    public final Operand<TInt32> zeroPoints;

    /**
     * The type of input Tensor. A tf.DType from: tf.float32
     */
    public final DataType Tin;

    /**
     * The type of output Tensor. A tf.DType from: tf.qint8, tf.qint32
     */
    public final DataType Tout;

    /**
     * Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization. Otherwise, it must be set within range [0, input.dims()).
     */
    public final long quantizationAxis;

    /**
     * The quantization min value that was used when input was quantized.
     * The purpose of this attribute is typically (but not limited to) to indicate narrow range, where this is set to:
     * `(Tin lowest) + 1` if narrow range, and `(Tin lowest)` otherwise.
     * For example, if Tin is qint8, this is set to -127 if narrow range quantized or -128 if not.
     */
    public final long quantizationMinVal;

    /**
     * The quantization max value that was used when input was quantized.
     * The purpose of this attribute is typically (but not limited to) indicate narrow range, where this is set to:
     * `(Tout max)` for both narrow range and not narrow range.
     * For example, if Tin is qint8, this is set to 127.
     */
    public final long quantizationMaxVal;

    public Inputs(GraphOperation op) {
      super(new UniformDequantize<>(op), op, Arrays.asList("Tin", "Tout", "quantization_axis", "quantization_min_val", "quantization_max_val"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      scales = (Operand<TFloat32>) op.input(inputIndex++);
      zeroPoints = (Operand<TInt32>) op.input(inputIndex++);
      Tin = op.attributes().getAttrType("Tin");
      Tout = op.attributes().getAttrType("Tout");
      quantizationAxis = op.attributes().getAttrInt("quantization_axis");
      quantizationMinVal = op.attributes().getAttrInt("quantization_min_val");
      quantizationMaxVal = op.attributes().getAttrInt("quantization_max_val");
    }
  }
}
