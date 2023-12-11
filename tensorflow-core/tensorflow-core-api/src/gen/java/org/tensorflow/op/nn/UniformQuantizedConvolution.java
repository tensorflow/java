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

package org.tensorflow.op.nn;

import java.util.Arrays;
import java.util.List;
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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Perform quantized convolution of quantized Tensor {@code lhs} and quantized Tensor {@code rhs}. to make quantized {@code output}.
 * Given quantized {@code lhs} and quantized {@code rhs}, performs quantized dot on {@code lhs} and {@code rhs} to make quantized {@code output}.
 * <p>{@code lhs} and {@code rhs} must be Tensors of same rank, and meet following shape conditions.
 * <ul>
 * <li>{@code lhs_feature} % {@code feature_group_count} == 0</li>
 * <li>{@code lhs_feature} % {@code rhs_input_feature} == 0</li>
 * <li>{@code lhs_feature} / {@code feature_group_count} == {@code rhs_input_feature}</li>
 * <li>{@code rhs_output_feature} % {@code feature_group_count} == 0</li>
 * <li>{@code lhs_batch} % {@code batch_group_count} == 0</li>
 * <li>{@code rhs_output_feature} % {@code batch_group_count} == 0</li>
 * </ul>
 * <p>{@code lhs} and {@code rhs} must be quantized Tensor, where data value is quantized using the formula:
 * <pre>
 * quantized_data = clip(original_data / scale + zero_point, quantization_min_val, quantization_max_val)
 * </pre>
 * <p>{@code output} is also quantized, using the same formula.
 * If {@code rhs} is per-tensor quantized, {@code output} must be also per-tensor quantized.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = UniformQuantizedConvolution.OP_NAME,
    inputsClass = UniformQuantizedConvolution.Inputs.class
)
public final class UniformQuantizedConvolution<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UniformQuantizedConvolution";

  private Output<U> output;

  public UniformQuantizedConvolution(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UniformQuantizedConvolution operation.
   *
   * @param scope current scope
   * @param lhs Must be a quantized tensor, rank &gt;= 3.
   * @param rhs Must be a quantized tensor, same rank as {@code lhs}.
   * @param lhsScales The float value(s) used as scale factors when quantizing the original data that {@code lhs} represents.
   * Must be a scalar {@code Tensor} ({@code lhs} supports only per-tensor quantization).
   * @param lhsZeroPoints The int32 value(s) used as zero points when quantizing original data that {@code lhs} represents.
   * Same shape condition as {@code lhs_scales}.
   * @param rhsScales The float value(s) used as scale factors when quantizing the original data that {@code rhs} represents.
   * Must be a scalar {@code Tensor} for per-tensor quantization,
   * or 1D {@code Tensor} of size {@code rhs.dim_size(kernel_output_feature_dimension)}, for per-channel quantization.
   * @param rhsZeroPoints The int32 value(s) used as zero points when quantizing original data that {@code rhs} represents.
   * Same shape condition as {@code rhs_scales}.
   * @param outputScales The float value(s) to use as scale factors when quantizing original data that {@code output} represents.
   * Must be a scalar {@code Tensor} for per-tensor quantization,
   * or 1D {@code Tensor} of size {@code rhs.dim_size(kernel_output_feature_dimension)}
   * <ul>
   * <li>which is equal to {@code output.dim_size(output_feature_dimension)},
   * for per-channel quantization.
   * If {@code rhs} is per-tensor quantized, output must be also per-tensor quantized.
   * This means that if {@code rhs_scales} and {@code rhs_zero_points} are scalar {@code Tensor}s, {@code output_scales} and {@code output_zero_points} must be scalar {@code Tensor}s as well.</li>
   * </ul>
   * @param outputZeroPoints The int32 value(s) used as zero points when quantizing original data that output represents.
   * Same shape condition as {@code output_scales}.
   * @param Tout The type of {@code output} {@code Tensor}.
   * @param padding string from: {@code "SAME"}, {@code "VALID"}, or {@code "EXPLICIT"}, indicating the type of padding algorithm to use.
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
   * @param <U> data type for {@code UniformQuantizedConvolution} output and operands
   * @param <T> data type for {@code UniformQuantizedConvolution} output and operands
   * @return a new instance of UniformQuantizedConvolution
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber, T extends TNumber> UniformQuantizedConvolution<U> create(
      Scope scope, Operand<T> lhs, Operand<T> rhs, Operand<TFloat32> lhsScales,
      Operand<TInt32> lhsZeroPoints, Operand<TFloat32> rhsScales, Operand<TInt32> rhsZeroPoints,
      Operand<TFloat32> outputScales, Operand<TInt32> outputZeroPoints, Class<U> Tout,
      String padding, Long lhsQuantizationMinVal, Long lhsQuantizationMaxVal,
      Long rhsQuantizationMinVal, Long rhsQuantizationMaxVal, Long outputQuantizationMinVal,
      Long outputQuantizationMaxVal, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UniformQuantizedConvolution");
    opBuilder.addInput(lhs.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder.addInput(lhsScales.asOutput());
    opBuilder.addInput(lhsZeroPoints.asOutput());
    opBuilder.addInput(rhsScales.asOutput());
    opBuilder.addInput(rhsZeroPoints.asOutput());
    opBuilder.addInput(outputScales.asOutput());
    opBuilder.addInput(outputZeroPoints.asOutput());
    opBuilder.setAttr("Tout", Operands.toDataType(Tout));
    opBuilder.setAttr("padding", padding);
    opBuilder.setAttr("lhs_quantization_min_val", lhsQuantizationMinVal);
    opBuilder.setAttr("lhs_quantization_max_val", lhsQuantizationMaxVal);
    opBuilder.setAttr("rhs_quantization_min_val", rhsQuantizationMinVal);
    opBuilder.setAttr("rhs_quantization_max_val", rhsQuantizationMaxVal);
    opBuilder.setAttr("output_quantization_min_val", outputQuantizationMinVal);
    opBuilder.setAttr("output_quantization_max_val", outputQuantizationMaxVal);
    if (options != null) {
      for (Options opts : options) {
        if (opts.windowStrides != null) {
          long[] windowStridesArray = new long[opts.windowStrides.size()];
          for (int i = 0 ; i < windowStridesArray.length ; i++) {
            windowStridesArray[i] = opts.windowStrides.get(i);
          }
          opBuilder.setAttr("window_strides", windowStridesArray);
        }
        if (opts.explicitPadding != null) {
          long[] explicitPaddingArray = new long[opts.explicitPadding.size()];
          for (int i = 0 ; i < explicitPaddingArray.length ; i++) {
            explicitPaddingArray[i] = opts.explicitPadding.get(i);
          }
          opBuilder.setAttr("explicit_padding", explicitPaddingArray);
        }
        if (opts.lhsDilation != null) {
          long[] lhsDilationArray = new long[opts.lhsDilation.size()];
          for (int i = 0 ; i < lhsDilationArray.length ; i++) {
            lhsDilationArray[i] = opts.lhsDilation.get(i);
          }
          opBuilder.setAttr("lhs_dilation", lhsDilationArray);
        }
        if (opts.rhsDilation != null) {
          long[] rhsDilationArray = new long[opts.rhsDilation.size()];
          for (int i = 0 ; i < rhsDilationArray.length ; i++) {
            rhsDilationArray[i] = opts.rhsDilation.get(i);
          }
          opBuilder.setAttr("rhs_dilation", rhsDilationArray);
        }
        if (opts.batchGroupCount != null) {
          opBuilder.setAttr("batch_group_count", opts.batchGroupCount);
        }
        if (opts.featureGroupCount != null) {
          opBuilder.setAttr("feature_group_count", opts.featureGroupCount);
        }
        if (opts.dimensionNumbers != null) {
          opBuilder.setAttr("dimension_numbers", opts.dimensionNumbers);
        }
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
    return new UniformQuantizedConvolution<>(opBuilder.build());
  }

  /**
   * Sets the windowStrides option.
   *
   * @param windowStrides The stride of the sliding window for each spatial dimension of {@code lhs}.
   * Must be an empty list (default) or a list of size (number of spatial dimensions).
   * If an empty list is provided, the stride for each spatial dimension is set to 1.
   * @return this Options instance.
   */
  public static Options windowStrides(List<Long> windowStrides) {
    return new Options().windowStrides(windowStrides);
  }

  /**
   * Sets the windowStrides option.
   *
   * @param windowStrides The stride of the sliding window for each spatial dimension of {@code lhs}.
   * Must be an empty list (default) or a list of size (number of spatial dimensions).
   * If an empty list is provided, the stride for each spatial dimension is set to 1.
   * @return this Options instance.
   */
  public static Options windowStrides(Long... windowStrides) {
    return new Options().windowStrides(windowStrides);
  }

  /**
   * Sets the explicitPadding option.
   *
   * @param explicitPadding If {@code padding} is {@code "EXPLICIT"}, must be set as a list indicating
   * the explicit paddings at the start and end of each {@code lhs} spatial dimension.
   * Otherwise, this must be empty.
   * <p>(If used,) Must be a list of size {@code 2 * (number of lhs spatial dimensions)},
   * where {@code (explicit_padding[2 * i], explicit_padding[2 * i + 1])} indicates
   * {@code (start_padding, end_padding)} of {@code spatial_dimensions[i]}.
   * @return this Options instance.
   */
  public static Options explicitPadding(List<Long> explicitPadding) {
    return new Options().explicitPadding(explicitPadding);
  }

  /**
   * Sets the explicitPadding option.
   *
   * @param explicitPadding If {@code padding} is {@code "EXPLICIT"}, must be set as a list indicating
   * the explicit paddings at the start and end of each {@code lhs} spatial dimension.
   * Otherwise, this must be empty.
   * <p>(If used,) Must be a list of size {@code 2 * (number of lhs spatial dimensions)},
   * where {@code (explicit_padding[2 * i], explicit_padding[2 * i + 1])} indicates
   * {@code (start_padding, end_padding)} of {@code spatial_dimensions[i]}.
   * @return this Options instance.
   */
  public static Options explicitPadding(Long... explicitPadding) {
    return new Options().explicitPadding(explicitPadding);
  }

  /**
   * Sets the lhsDilation option.
   *
   * @param lhsDilation The dilation factor to apply in each spatial dimension of {@code lhs}.
   * Must be an empty list (default) or a list of size (number of {@code lhs} spatial dimensions).
   * If empty list, the dilation for each {@code lhs} spatial dimension is set to 1.
   * @return this Options instance.
   */
  public static Options lhsDilation(List<Long> lhsDilation) {
    return new Options().lhsDilation(lhsDilation);
  }

  /**
   * Sets the lhsDilation option.
   *
   * @param lhsDilation The dilation factor to apply in each spatial dimension of {@code lhs}.
   * Must be an empty list (default) or a list of size (number of {@code lhs} spatial dimensions).
   * If empty list, the dilation for each {@code lhs} spatial dimension is set to 1.
   * @return this Options instance.
   */
  public static Options lhsDilation(Long... lhsDilation) {
    return new Options().lhsDilation(lhsDilation);
  }

  /**
   * Sets the rhsDilation option.
   *
   * @param rhsDilation The dilation factor to apply in each spatial dimension of {@code rhs}.
   * Must be an empty list (default) or a list of size (number of {@code rhs} spatial dimensions).
   * If empty list, the dilation for each {@code rhs} spatial dimension is set to 1.
   * @return this Options instance.
   */
  public static Options rhsDilation(List<Long> rhsDilation) {
    return new Options().rhsDilation(rhsDilation);
  }

  /**
   * Sets the rhsDilation option.
   *
   * @param rhsDilation The dilation factor to apply in each spatial dimension of {@code rhs}.
   * Must be an empty list (default) or a list of size (number of {@code rhs} spatial dimensions).
   * If empty list, the dilation for each {@code rhs} spatial dimension is set to 1.
   * @return this Options instance.
   */
  public static Options rhsDilation(Long... rhsDilation) {
    return new Options().rhsDilation(rhsDilation);
  }

  /**
   * Sets the batchGroupCount option.
   *
   * @param batchGroupCount The number of batch groups. Used for grouped filters.
   * Must be a divisor of {@code output_feature}.
   * @return this Options instance.
   */
  public static Options batchGroupCount(Long batchGroupCount) {
    return new Options().batchGroupCount(batchGroupCount);
  }

  /**
   * Sets the featureGroupCount option.
   *
   * @param featureGroupCount The number of feature groups. Used for grouped convolutions.
   * Must be a divisor of both {@code lhs_feature} and {@code output_feature}.
   * @return this Options instance.
   */
  public static Options featureGroupCount(Long featureGroupCount) {
    return new Options().featureGroupCount(featureGroupCount);
  }

  /**
   * Sets the dimensionNumbers option.
   *
   * @param dimensionNumbers Structure of dimension information for the convolution op.
   * Must be an empty string (default) or a serialized string of {@code tensorflow.UniformQuantizedConvolutionDimensionNumbersAttr} proto.
   * If empty string, the default is {@code ("NCHW", "OIHW", "NCHW")} (for a 2D convolution).
   * @return this Options instance.
   */
  public static Options dimensionNumbers(String dimensionNumbers) {
    return new Options().dimensionNumbers(dimensionNumbers);
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
   * The output quantized tensor of {@code Tout}, same rank as {@code lhs} and {@code rhs}.
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
   * Optional attributes for {@link org.tensorflow.op.nn.UniformQuantizedConvolution}
   */
  public static class Options {
    private List<Long> windowStrides;

    private List<Long> explicitPadding;

    private List<Long> lhsDilation;

    private List<Long> rhsDilation;

    private Long batchGroupCount;

    private Long featureGroupCount;

    private String dimensionNumbers;

    private Long lhsQuantizationAxis;

    private Long rhsQuantizationAxis;

    private Long outputQuantizationAxis;

    private Options() {
    }

    /**
     * Sets the windowStrides option.
     *
     * @param windowStrides The stride of the sliding window for each spatial dimension of {@code lhs}.
     * Must be an empty list (default) or a list of size (number of spatial dimensions).
     * If an empty list is provided, the stride for each spatial dimension is set to 1.
     * @return this Options instance.
     */
    public Options windowStrides(List<Long> windowStrides) {
      this.windowStrides = windowStrides;
      return this;
    }

    /**
     * Sets the windowStrides option.
     *
     * @param windowStrides The stride of the sliding window for each spatial dimension of {@code lhs}.
     * Must be an empty list (default) or a list of size (number of spatial dimensions).
     * If an empty list is provided, the stride for each spatial dimension is set to 1.
     * @return this Options instance.
     */
    public Options windowStrides(Long... windowStrides) {
      this.windowStrides = Arrays.asList(windowStrides);
      return this;
    }

    /**
     * Sets the explicitPadding option.
     *
     * @param explicitPadding If {@code padding} is {@code "EXPLICIT"}, must be set as a list indicating
     * the explicit paddings at the start and end of each {@code lhs} spatial dimension.
     * Otherwise, this must be empty.
     * <p>(If used,) Must be a list of size {@code 2 * (number of lhs spatial dimensions)},
     * where {@code (explicit_padding[2 * i], explicit_padding[2 * i + 1])} indicates
     * {@code (start_padding, end_padding)} of {@code spatial_dimensions[i]}.
     * @return this Options instance.
     */
    public Options explicitPadding(List<Long> explicitPadding) {
      this.explicitPadding = explicitPadding;
      return this;
    }

    /**
     * Sets the explicitPadding option.
     *
     * @param explicitPadding If {@code padding} is {@code "EXPLICIT"}, must be set as a list indicating
     * the explicit paddings at the start and end of each {@code lhs} spatial dimension.
     * Otherwise, this must be empty.
     * <p>(If used,) Must be a list of size {@code 2 * (number of lhs spatial dimensions)},
     * where {@code (explicit_padding[2 * i], explicit_padding[2 * i + 1])} indicates
     * {@code (start_padding, end_padding)} of {@code spatial_dimensions[i]}.
     * @return this Options instance.
     */
    public Options explicitPadding(Long... explicitPadding) {
      this.explicitPadding = Arrays.asList(explicitPadding);
      return this;
    }

    /**
     * Sets the lhsDilation option.
     *
     * @param lhsDilation The dilation factor to apply in each spatial dimension of {@code lhs}.
     * Must be an empty list (default) or a list of size (number of {@code lhs} spatial dimensions).
     * If empty list, the dilation for each {@code lhs} spatial dimension is set to 1.
     * @return this Options instance.
     */
    public Options lhsDilation(List<Long> lhsDilation) {
      this.lhsDilation = lhsDilation;
      return this;
    }

    /**
     * Sets the lhsDilation option.
     *
     * @param lhsDilation The dilation factor to apply in each spatial dimension of {@code lhs}.
     * Must be an empty list (default) or a list of size (number of {@code lhs} spatial dimensions).
     * If empty list, the dilation for each {@code lhs} spatial dimension is set to 1.
     * @return this Options instance.
     */
    public Options lhsDilation(Long... lhsDilation) {
      this.lhsDilation = Arrays.asList(lhsDilation);
      return this;
    }

    /**
     * Sets the rhsDilation option.
     *
     * @param rhsDilation The dilation factor to apply in each spatial dimension of {@code rhs}.
     * Must be an empty list (default) or a list of size (number of {@code rhs} spatial dimensions).
     * If empty list, the dilation for each {@code rhs} spatial dimension is set to 1.
     * @return this Options instance.
     */
    public Options rhsDilation(List<Long> rhsDilation) {
      this.rhsDilation = rhsDilation;
      return this;
    }

    /**
     * Sets the rhsDilation option.
     *
     * @param rhsDilation The dilation factor to apply in each spatial dimension of {@code rhs}.
     * Must be an empty list (default) or a list of size (number of {@code rhs} spatial dimensions).
     * If empty list, the dilation for each {@code rhs} spatial dimension is set to 1.
     * @return this Options instance.
     */
    public Options rhsDilation(Long... rhsDilation) {
      this.rhsDilation = Arrays.asList(rhsDilation);
      return this;
    }

    /**
     * Sets the batchGroupCount option.
     *
     * @param batchGroupCount The number of batch groups. Used for grouped filters.
     * Must be a divisor of {@code output_feature}.
     * @return this Options instance.
     */
    public Options batchGroupCount(Long batchGroupCount) {
      this.batchGroupCount = batchGroupCount;
      return this;
    }

    /**
     * Sets the featureGroupCount option.
     *
     * @param featureGroupCount The number of feature groups. Used for grouped convolutions.
     * Must be a divisor of both {@code lhs_feature} and {@code output_feature}.
     * @return this Options instance.
     */
    public Options featureGroupCount(Long featureGroupCount) {
      this.featureGroupCount = featureGroupCount;
      return this;
    }

    /**
     * Sets the dimensionNumbers option.
     *
     * @param dimensionNumbers Structure of dimension information for the convolution op.
     * Must be an empty string (default) or a serialized string of {@code tensorflow.UniformQuantizedConvolutionDimensionNumbersAttr} proto.
     * If empty string, the default is {@code ("NCHW", "OIHW", "NCHW")} (for a 2D convolution).
     * @return this Options instance.
     */
    public Options dimensionNumbers(String dimensionNumbers) {
      this.dimensionNumbers = dimensionNumbers;
      return this;
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
      outputsClass = UniformQuantizedConvolution.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<UniformQuantizedConvolution<?>> {
    /**
     * Must be a quantized tensor, rank &gt;= 3.
     */
    public final Operand<T> lhs;

    /**
     * Must be a quantized tensor, same rank as {@code lhs}.
     */
    public final Operand<T> rhs;

    /**
     * The float value(s) used as scale factors when quantizing the original data that {@code lhs} represents.
     * Must be a scalar {@code Tensor} ({@code lhs} supports only per-tensor quantization).
     */
    public final Operand<TFloat32> lhsScales;

    /**
     * The int32 value(s) used as zero points when quantizing original data that {@code lhs} represents.
     * Same shape condition as {@code lhs_scales}.
     */
    public final Operand<TInt32> lhsZeroPoints;

    /**
     * The float value(s) used as scale factors when quantizing the original data that {@code rhs} represents.
     * Must be a scalar {@code Tensor} for per-tensor quantization,
     * or 1D {@code Tensor} of size {@code rhs.dim_size(kernel_output_feature_dimension)}, for per-channel quantization.
     */
    public final Operand<TFloat32> rhsScales;

    /**
     * The int32 value(s) used as zero points when quantizing original data that {@code rhs} represents.
     * Same shape condition as {@code rhs_scales}.
     */
    public final Operand<TInt32> rhsZeroPoints;

    /**
     * The float value(s) to use as scale factors when quantizing original data that {@code output} represents.
     * Must be a scalar {@code Tensor} for per-tensor quantization,
     * or 1D {@code Tensor} of size {@code rhs.dim_size(kernel_output_feature_dimension)}
     * <ul>
     * <li>which is equal to {@code output.dim_size(output_feature_dimension)},
     * for per-channel quantization.
     * If {@code rhs} is per-tensor quantized, output must be also per-tensor quantized.
     * This means that if {@code rhs_scales} and {@code rhs_zero_points} are scalar {@code Tensor}s, {@code output_scales} and {@code output_zero_points} must be scalar {@code Tensor}s as well.</li>
     * </ul>
     */
    public final Operand<TFloat32> outputScales;

    /**
     * The int32 value(s) used as zero points when quantizing original data that output represents.
     * Same shape condition as {@code output_scales}.
     */
    public final Operand<TInt32> outputZeroPoints;

    /**
     * The type of `lhs` and `rhs` input `Tensor`.
     */
    public final DataType Tin;

    /**
     * The type of `output` `Tensor`.
     */
    public final DataType Tout;

    /**
     * The stride of the sliding window for each spatial dimension of `lhs`.
     * Must be an empty list (default) or a list of size (number of spatial dimensions).
     * If an empty list is provided, the stride for each spatial dimension is set to 1.
     */
    public final long[] windowStrides;

    /**
     * string from: `"SAME"`, `"VALID"`, or `"EXPLICIT"`, indicating the type of padding algorithm to use.
     */
    public final String padding;

    /**
     * If `padding` is `"EXPLICIT"`, must be set as a list indicating
     * the explicit paddings at the start and end of each `lhs` spatial dimension.
     * Otherwise, this must be empty.
     *
     * (If used,) Must be a list of size `2 * (number of lhs spatial dimensions)`,
     * where `(explicit_padding[2 * i], explicit_padding[2 * i + 1])` indicates
     * `(start_padding, end_padding)` of `spatial_dimensions[i]`.
     */
    public final long[] explicitPadding;

    /**
     * The dilation factor to apply in each spatial dimension of `lhs`.
     * Must be an empty list (default) or a list of size (number of `lhs` spatial dimensions).
     * If empty list, the dilation for each `lhs` spatial dimension is set to 1.
     */
    public final long[] lhsDilation;

    /**
     * The dilation factor to apply in each spatial dimension of `rhs`.
     * Must be an empty list (default) or a list of size (number of `rhs` spatial dimensions).
     * If empty list, the dilation for each `rhs` spatial dimension is set to 1.
     */
    public final long[] rhsDilation;

    /**
     * The number of batch groups. Used for grouped filters.
     * Must be a divisor of `output_feature`.
     */
    public final long batchGroupCount;

    /**
     * The number of feature groups. Used for grouped convolutions.
     * Must be a divisor of both `lhs_feature` and `output_feature`.
     */
    public final long featureGroupCount;

    /**
     * Structure of dimension information for the convolution op.
     * Must be an empty string (default) or a serialized string of `tensorflow.UniformQuantizedConvolutionDimensionNumbersAttr` proto.
     * If empty string, the default is `("NCHW", "OIHW", "NCHW")` (for a 2D convolution).
     */
    public final String dimensionNumbers;

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

    public Inputs(GraphOperation op) {
      super(new UniformQuantizedConvolution<>(op), op, Arrays.asList("Tin", "Tout", "window_strides", "padding", "explicit_padding", "lhs_dilation", "rhs_dilation", "batch_group_count", "feature_group_count", "dimension_numbers", "lhs_quantization_axis", "lhs_quantization_min_val", "lhs_quantization_max_val", "rhs_quantization_axis", "rhs_quantization_min_val", "rhs_quantization_max_val", "output_quantization_axis", "output_quantization_min_val", "output_quantization_max_val"));
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
      windowStrides = op.attributes().getAttrIntList("window_strides");
      padding = op.attributes().getAttrString("padding");
      explicitPadding = op.attributes().getAttrIntList("explicit_padding");
      lhsDilation = op.attributes().getAttrIntList("lhs_dilation");
      rhsDilation = op.attributes().getAttrIntList("rhs_dilation");
      batchGroupCount = op.attributes().getAttrInt("batch_group_count");
      featureGroupCount = op.attributes().getAttrInt("feature_group_count");
      dimensionNumbers = op.attributes().getAttrString("dimension_numbers");
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
