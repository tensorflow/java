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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Perform clip by value on the quantized Tensor {@code operand}.
 * Given quantized {@code operand} which was quantized using {@code scales} and {@code zero_points}, performs clip by value using {@code min} and {@code max} values.
 * If quantization_axis is -1 (per-tensor quantized), the entire operand is clipped using scalar min, max.
 * Otherwise (per-channel quantized), the clipping is also done per-channel.
 */
@OpMetadata(
    opType = UniformQuantizedClipByValue.OP_NAME,
    inputsClass = UniformQuantizedClipByValue.Inputs.class
)
@Operator
public final class UniformQuantizedClipByValue<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UniformQuantizedClipByValue";

  private Output<T> output;

  public UniformQuantizedClipByValue(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UniformQuantizedClipByValue operation.
   *
   * @param scope current scope
   * @param operand Must be a Tensor of T.
   * @param min The min value(s) to clip operand. Must be a Tensor of T.
   * Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (operand.dim_size(quantization_axis),) (per-axis quantization).
   * @param max The min value(s) to clip operand. Must be a Tensor of T.
   * Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (operand.dim_size(quantization_axis),) (per-axis quantization).
   * @param scales The float value(s) used as scale(s) when quantizing {@code operand}, {@code min} and {@code max}.
   * Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (operand.dim_size(quantization_axis),) (per-axis quantization).
   * @param zeroPoints The int32 value(s) used as zero_point(s) when quantizing {@code operand}, {@code min} and {@code max}.
   * Same shape condition as scales.
   * @param quantizationMinVal The quantization min value that was used when operand was quantized.
   * @param quantizationMaxVal The quantization max value that was used when operand was quantized.
   * @param options carries optional attribute values
   * @param <T> data type for {@code UniformQuantizedClipByValue} output and operands
   * @return a new instance of UniformQuantizedClipByValue
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> UniformQuantizedClipByValue<T> create(Scope scope,
      Operand<T> operand, Operand<T> min, Operand<T> max, Operand<TFloat32> scales,
      Operand<TInt32> zeroPoints, Long quantizationMinVal, Long quantizationMaxVal,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UniformQuantizedClipByValue");
    opBuilder.addInput(operand.asOutput());
    opBuilder.addInput(min.asOutput());
    opBuilder.addInput(max.asOutput());
    opBuilder.addInput(scales.asOutput());
    opBuilder.addInput(zeroPoints.asOutput());
    opBuilder.setAttr("quantization_min_val", quantizationMinVal);
    opBuilder.setAttr("quantization_max_val", quantizationMaxVal);
    if (options != null) {
      for (Options opts : options) {
        if (opts.quantizationAxis != null) {
          opBuilder.setAttr("quantization_axis", opts.quantizationAxis);
        }
      }
    }
    return new UniformQuantizedClipByValue<>(opBuilder.build());
  }

  /**
   * Sets the quantizationAxis option.
   *
   * @param quantizationAxis Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
   * If set to -1 (default), this indicates per-tensor quantization. Otherwise, it must be set within range [0, operand.dims()).
   * @return this Options instance.
   */
  public static Options quantizationAxis(Long quantizationAxis) {
    return new Options().quantizationAxis(quantizationAxis);
  }

  /**
   * Gets output.
   * The output clipped Tensor of T, whose shape is same as operand.
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
   * Optional attributes for {@link org.tensorflow.op.core.UniformQuantizedClipByValue}
   */
  public static class Options {
    private Long quantizationAxis;

    private Options() {
    }

    /**
     * Sets the quantizationAxis option.
     *
     * @param quantizationAxis Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization. Otherwise, it must be set within range [0, operand.dims()).
     * @return this Options instance.
     */
    public Options quantizationAxis(Long quantizationAxis) {
      this.quantizationAxis = quantizationAxis;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = UniformQuantizedClipByValue.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<UniformQuantizedClipByValue<T>> {
    /**
     * Must be a Tensor of T.
     */
    public final Operand<T> operand;

    /**
     * The min value(s) to clip operand. Must be a Tensor of T.
     * Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (operand.dim_size(quantization_axis),) (per-axis quantization).
     */
    public final Operand<T> min;

    /**
     * The min value(s) to clip operand. Must be a Tensor of T.
     * Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (operand.dim_size(quantization_axis),) (per-axis quantization).
     */
    public final Operand<T> max;

    /**
     * The float value(s) used as scale(s) when quantizing {@code operand}, {@code min} and {@code max}.
     * Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (operand.dim_size(quantization_axis),) (per-axis quantization).
     */
    public final Operand<TFloat32> scales;

    /**
     * The int32 value(s) used as zero_point(s) when quantizing {@code operand}, {@code min} and {@code max}.
     * Same shape condition as scales.
     */
    public final Operand<TInt32> zeroPoints;

    /**
     * The type of operand, min, max, and output. A tf.DType from: tf.qint32
     */
    public final DataType T;

    /**
     * Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization. Otherwise, it must be set within range [0, operand.dims()).
     */
    public final long quantizationAxis;

    /**
     * The quantization min value that was used when operand was quantized.
     */
    public final long quantizationMinVal;

    /**
     * The quantization max value that was used when operand was quantized.
     */
    public final long quantizationMaxVal;

    public Inputs(GraphOperation op) {
      super(new UniformQuantizedClipByValue<>(op), op, Arrays.asList("T", "quantization_axis", "quantization_min_val", "quantization_max_val"));
      int inputIndex = 0;
      operand = (Operand<T>) op.input(inputIndex++);
      min = (Operand<T>) op.input(inputIndex++);
      max = (Operand<T>) op.input(inputIndex++);
      scales = (Operand<TFloat32>) op.input(inputIndex++);
      zeroPoints = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      quantizationAxis = op.attributes().getAttrInt("quantization_axis");
      quantizationMinVal = op.attributes().getAttrInt("quantization_min_val");
      quantizationMaxVal = op.attributes().getAttrInt("quantization_max_val");
    }
  }
}
