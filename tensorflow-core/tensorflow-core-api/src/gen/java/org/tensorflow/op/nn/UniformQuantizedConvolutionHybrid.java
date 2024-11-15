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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Perform hybrid quantized convolution of float Tensor {@code lhs} and quantized Tensor {@code rhs}.
 * Given float {@code lhs} and quantized {@code rhs}, internally performs quantization on {@code lhs},
 * and then performs quantized convolution on quantized {@code lhs} and {@code rhs}.
 * <p>The internal quantization on {@code lhs} is a quantization to {@code Trhs}, dynamic range,
 * per-batch (per-axis along axis {@code dimension_numbers.input_batch_dimension}), asymmetric,
 * and not narrow range (the range is [Trhs_MIN, Trhs_MAX]).
 * <p>{@code lhs} and {@code rhs} must be Tensors of same rank, and meet following shape conditions.
 * <ul>
 * <li>lhs_feature % feature_group_count == 0</li>
 * <li>lhs_feature % rhs_input_feature == 0</li>
 * <li>lhs_feature / feature_group_count == rhs_input_feature</li>
 * <li>rhs_output_feature % feature_group_count == 0</li>
 * <li>lhs_batch % batch_group_count == 0</li>
 * <li>rhs_output_feature % batch_group_count == 0</li>
 * </ul>
 * <p>{@code rhs} must be quantized Tensor, where its data value is quantized using the formula:
 * quantized_data = clip(original_data / scale + zero_point, quantization_min_val, quantization_max_val).
 */
@OpMetadata(
    opType = UniformQuantizedConvolutionHybrid.OP_NAME,
    inputsClass = UniformQuantizedConvolutionHybrid.Inputs.class
)
@Operator(
    group = "nn"
)
public final class UniformQuantizedConvolutionHybrid<V extends TNumber> extends RawOp implements Operand<V> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UniformQuantizedConvolutionHybrid";

  private Output<V> output;

  public UniformQuantizedConvolutionHybrid(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UniformQuantizedConvolutionHybrid operation.
   *
   * @param scope current scope
   * @param lhs Must be a non-quantized Tensor of {@code Tlhs}, rank &gt;= 3.
   * @param rhs Must be a quantized Tensor of {@code Trhs}, same rank as {@code lhs}.
   * @param rhsScales The float value(s) used as scale factors when quantizing the original data that {@code rhs} represents.
   * Must be a scalar Tensor for per-tensor quantization,
   * or 1D Tensor of size {@code rhs.dim_size(kernel_output_feature_dimension)}, for per-channel quantization.
   * @param rhsZeroPoints The int32 value(s) used as zero_point when quantizing original data that {@code rhs} represents.
   * Same shape condition as {@code rhs_scales}.
   * @param Tout The type of output Tensor.
   * @param padding string from: {@code "SAME"}, {@code "VALID"}, or {@code "EXPLICIT"}, indicating the type of padding algorithm to use.
   * @param rhsQuantizationMinVal The min value of the quantized data stored in {@code rhs}.
   * For example, if {@code Trhs} is qint8, this must be set to -127 if narrow range quantized or -128 if not.
   * @param rhsQuantizationMaxVal The max value of the quantized data stored in {@code rhs}.
   * For example, if {@code Trhs} is qint8, this must be set to 127.
   * @param options carries optional attribute values
   * @param <V> data type for {@code UniformQuantizedConvolutionHybrid} output and operands
   * @return a new instance of UniformQuantizedConvolutionHybrid
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> UniformQuantizedConvolutionHybrid<V> create(Scope scope,
      Operand<? extends TNumber> lhs, Operand<? extends TNumber> rhs, Operand<TFloat32> rhsScales,
      Operand<TInt32> rhsZeroPoints, Class<V> Tout, String padding, Long rhsQuantizationMinVal,
      Long rhsQuantizationMaxVal, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UniformQuantizedConvolutionHybrid");
    opBuilder.addInput(lhs.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder.addInput(rhsScales.asOutput());
    opBuilder.addInput(rhsZeroPoints.asOutput());
    opBuilder.setAttr("Tout", Operands.toDataType(Tout));
    opBuilder.setAttr("padding", padding);
    opBuilder.setAttr("rhs_quantization_min_val", rhsQuantizationMinVal);
    opBuilder.setAttr("rhs_quantization_max_val", rhsQuantizationMaxVal);
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
        if (opts.rhsQuantizationAxis != null) {
          opBuilder.setAttr("rhs_quantization_axis", opts.rhsQuantizationAxis);
        }
      }
    }
    return new UniformQuantizedConvolutionHybrid<>(opBuilder.build());
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
   * @param explicitPadding If {@code padding} Attr is {@code "EXPLICIT"}, must be set as a list indicating
   * the explicit paddings at the start and end of each lhs spatial dimension.
   * Otherwise, this Attr is must be empty.
   * <p>(If used,) Must be a list of size 2 * (number of lhs spatial dimensions),
   * where (explicit_padding[2 * i], explicit_padding[2 * i + 1]) indicates
   * spatial_dimensions[i] (start_padding, end_padding).
   * @return this Options instance.
   */
  public static Options explicitPadding(List<Long> explicitPadding) {
    return new Options().explicitPadding(explicitPadding);
  }

  /**
   * Sets the explicitPadding option.
   *
   * @param explicitPadding If {@code padding} Attr is {@code "EXPLICIT"}, must be set as a list indicating
   * the explicit paddings at the start and end of each lhs spatial dimension.
   * Otherwise, this Attr is must be empty.
   * <p>(If used,) Must be a list of size 2 * (number of lhs spatial dimensions),
   * where (explicit_padding[2 * i], explicit_padding[2 * i + 1]) indicates
   * spatial_dimensions[i] (start_padding, end_padding).
   * @return this Options instance.
   */
  public static Options explicitPadding(Long... explicitPadding) {
    return new Options().explicitPadding(explicitPadding);
  }

  /**
   * Sets the lhsDilation option.
   *
   * @param lhsDilation The dilation factor to apply in each spatial dimension of {@code lhs}.
   * Must be an empty list (default) or a list of size (number of lhs spatial dimensions).
   * If empty list, the dilation for each lhs spatial dimension is set to 1.
   * @return this Options instance.
   */
  public static Options lhsDilation(List<Long> lhsDilation) {
    return new Options().lhsDilation(lhsDilation);
  }

  /**
   * Sets the lhsDilation option.
   *
   * @param lhsDilation The dilation factor to apply in each spatial dimension of {@code lhs}.
   * Must be an empty list (default) or a list of size (number of lhs spatial dimensions).
   * If empty list, the dilation for each lhs spatial dimension is set to 1.
   * @return this Options instance.
   */
  public static Options lhsDilation(Long... lhsDilation) {
    return new Options().lhsDilation(lhsDilation);
  }

  /**
   * Sets the rhsDilation option.
   *
   * @param rhsDilation The dilation factor to apply in each spatial dimension of {@code rhs}.
   * Must be an empty list (default) or a list of size (number of rhs spatial dimensions).
   * If empty list, the dilation for each rhs spatial dimension is set to 1.
   * @return this Options instance.
   */
  public static Options rhsDilation(List<Long> rhsDilation) {
    return new Options().rhsDilation(rhsDilation);
  }

  /**
   * Sets the rhsDilation option.
   *
   * @param rhsDilation The dilation factor to apply in each spatial dimension of {@code rhs}.
   * Must be an empty list (default) or a list of size (number of rhs spatial dimensions).
   * If empty list, the dilation for each rhs spatial dimension is set to 1.
   * @return this Options instance.
   */
  public static Options rhsDilation(Long... rhsDilation) {
    return new Options().rhsDilation(rhsDilation);
  }

  /**
   * Sets the batchGroupCount option.
   *
   * @param batchGroupCount The number of batch groups. Used for grouped filters.
   * Must be a divisor of output_feature.
   * @return this Options instance.
   */
  public static Options batchGroupCount(Long batchGroupCount) {
    return new Options().batchGroupCount(batchGroupCount);
  }

  /**
   * Sets the featureGroupCount option.
   *
   * @param featureGroupCount The number of feature groups. Used for grouped convolutions.
   * Must be a divisor of both lhs_feature and output_feature.
   * @return this Options instance.
   */
  public static Options featureGroupCount(Long featureGroupCount) {
    return new Options().featureGroupCount(featureGroupCount);
  }

  /**
   * Sets the dimensionNumbers option.
   *
   * @param dimensionNumbers Structure of dimension information for the convolution op.
   * Must be an empty string (default) or a serialized string of tensorflow.UniformQuantizedConvolutionDimensionNumbersAttr proto.
   * If empty string, the default is {@code ("NCHW", "OIHW", "NCHW")} (for a 2D convolution).
   * @return this Options instance.
   */
  public static Options dimensionNumbers(String dimensionNumbers) {
    return new Options().dimensionNumbers(dimensionNumbers);
  }

  /**
   * Sets the rhsQuantizationAxis option.
   *
   * @param rhsQuantizationAxis Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
   * If set to -1 (default), this indicates per-tensor quantization.
   * For the {@code rhs}, only per-tensor quantization
   * or per-channel quantization along kernel_output_feature_dimension is supported.
   * Thus, this attribute must be set to -1 or {@code dimension_numbers.kernel_output_feature_dimension}.
   * Other values will raise error at OpKernel construction.
   * @return this Options instance.
   */
  public static Options rhsQuantizationAxis(Long rhsQuantizationAxis) {
    return new Options().rhsQuantizationAxis(rhsQuantizationAxis);
  }

  /**
   * Gets output.
   * The output Tensor of {@code Tout}, same rank as {@code lhs} and {@code rhs}.
   * The output data is the non-quantized output data.
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
   * Optional attributes for {@link org.tensorflow.op.nn.UniformQuantizedConvolutionHybrid}
   */
  public static class Options {
    private List<Long> windowStrides;

    private List<Long> explicitPadding;

    private List<Long> lhsDilation;

    private List<Long> rhsDilation;

    private Long batchGroupCount;

    private Long featureGroupCount;

    private String dimensionNumbers;

    private Long rhsQuantizationAxis;

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
     * @param explicitPadding If {@code padding} Attr is {@code "EXPLICIT"}, must be set as a list indicating
     * the explicit paddings at the start and end of each lhs spatial dimension.
     * Otherwise, this Attr is must be empty.
     * <p>(If used,) Must be a list of size 2 * (number of lhs spatial dimensions),
     * where (explicit_padding[2 * i], explicit_padding[2 * i + 1]) indicates
     * spatial_dimensions[i] (start_padding, end_padding).
     * @return this Options instance.
     */
    public Options explicitPadding(List<Long> explicitPadding) {
      this.explicitPadding = explicitPadding;
      return this;
    }

    /**
     * Sets the explicitPadding option.
     *
     * @param explicitPadding If {@code padding} Attr is {@code "EXPLICIT"}, must be set as a list indicating
     * the explicit paddings at the start and end of each lhs spatial dimension.
     * Otherwise, this Attr is must be empty.
     * <p>(If used,) Must be a list of size 2 * (number of lhs spatial dimensions),
     * where (explicit_padding[2 * i], explicit_padding[2 * i + 1]) indicates
     * spatial_dimensions[i] (start_padding, end_padding).
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
     * Must be an empty list (default) or a list of size (number of lhs spatial dimensions).
     * If empty list, the dilation for each lhs spatial dimension is set to 1.
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
     * Must be an empty list (default) or a list of size (number of lhs spatial dimensions).
     * If empty list, the dilation for each lhs spatial dimension is set to 1.
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
     * Must be an empty list (default) or a list of size (number of rhs spatial dimensions).
     * If empty list, the dilation for each rhs spatial dimension is set to 1.
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
     * Must be an empty list (default) or a list of size (number of rhs spatial dimensions).
     * If empty list, the dilation for each rhs spatial dimension is set to 1.
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
     * Must be a divisor of output_feature.
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
     * Must be a divisor of both lhs_feature and output_feature.
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
     * Must be an empty string (default) or a serialized string of tensorflow.UniformQuantizedConvolutionDimensionNumbersAttr proto.
     * If empty string, the default is {@code ("NCHW", "OIHW", "NCHW")} (for a 2D convolution).
     * @return this Options instance.
     */
    public Options dimensionNumbers(String dimensionNumbers) {
      this.dimensionNumbers = dimensionNumbers;
      return this;
    }

    /**
     * Sets the rhsQuantizationAxis option.
     *
     * @param rhsQuantizationAxis Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization.
     * For the {@code rhs}, only per-tensor quantization
     * or per-channel quantization along kernel_output_feature_dimension is supported.
     * Thus, this attribute must be set to -1 or {@code dimension_numbers.kernel_output_feature_dimension}.
     * Other values will raise error at OpKernel construction.
     * @return this Options instance.
     */
    public Options rhsQuantizationAxis(Long rhsQuantizationAxis) {
      this.rhsQuantizationAxis = rhsQuantizationAxis;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = UniformQuantizedConvolutionHybrid.class
  )
  public static class Inputs extends RawOpInputs<UniformQuantizedConvolutionHybrid<?>> {
    /**
     * Must be a non-quantized Tensor of {@code Tlhs}, rank &gt;= 3.
     */
    public final Operand<? extends TNumber> lhs;

    /**
     * Must be a quantized Tensor of {@code Trhs}, same rank as {@code lhs}.
     */
    public final Operand<? extends TNumber> rhs;

    /**
     * The float value(s) used as scale factors when quantizing the original data that {@code rhs} represents.
     * Must be a scalar Tensor for per-tensor quantization,
     * or 1D Tensor of size {@code rhs.dim_size(kernel_output_feature_dimension)}, for per-channel quantization.
     */
    public final Operand<TFloat32> rhsScales;

    /**
     * The int32 value(s) used as zero_point when quantizing original data that {@code rhs} represents.
     * Same shape condition as {@code rhs_scales}.
     */
    public final Operand<TInt32> rhsZeroPoints;

    /**
     * The type of {@code lhs} input Tensor.
     */
    public final DataType Tlhs;

    /**
     * The type of {@code rhs} (quantized) input Tensor.
     */
    public final DataType Trhs;

    /**
     * The type of output Tensor.
     */
    public final DataType Tout;

    /**
     * The stride of the sliding window for each spatial dimension of {@code lhs}.
     * Must be an empty list (default) or a list of size (number of spatial dimensions).
     * If an empty list is provided, the stride for each spatial dimension is set to 1.
     */
    public final long[] windowStrides;

    /**
     * string from: {@code "SAME"}, {@code "VALID"}, or {@code "EXPLICIT"}, indicating the type of padding algorithm to use.
     */
    public final String padding;

    /**
     * If {@code padding} Attr is {@code "EXPLICIT"}, must be set as a list indicating
     * the explicit paddings at the start and end of each lhs spatial dimension.
     * Otherwise, this Attr is must be empty.
     * <p>(If used,) Must be a list of size 2 * (number of lhs spatial dimensions),
     * where (explicit_padding[2 * i], explicit_padding[2 * i + 1]) indicates
     * spatial_dimensions[i] (start_padding, end_padding).
     */
    public final long[] explicitPadding;

    /**
     * The dilation factor to apply in each spatial dimension of {@code lhs}.
     * Must be an empty list (default) or a list of size (number of lhs spatial dimensions).
     * If empty list, the dilation for each lhs spatial dimension is set to 1.
     */
    public final long[] lhsDilation;

    /**
     * The dilation factor to apply in each spatial dimension of {@code rhs}.
     * Must be an empty list (default) or a list of size (number of rhs spatial dimensions).
     * If empty list, the dilation for each rhs spatial dimension is set to 1.
     */
    public final long[] rhsDilation;

    /**
     * The number of batch groups. Used for grouped filters.
     * Must be a divisor of output_feature.
     */
    public final long batchGroupCount;

    /**
     * The number of feature groups. Used for grouped convolutions.
     * Must be a divisor of both lhs_feature and output_feature.
     */
    public final long featureGroupCount;

    /**
     * Structure of dimension information for the convolution op.
     * Must be an empty string (default) or a serialized string of tensorflow.UniformQuantizedConvolutionDimensionNumbersAttr proto.
     * If empty string, the default is {@code ("NCHW", "OIHW", "NCHW")} (for a 2D convolution).
     */
    public final String dimensionNumbers;

    /**
     * Indicates the dimension index of the tensor where per-axis quantization is applied for the slices along that dimension.
     * If set to -1 (default), this indicates per-tensor quantization.
     * For the {@code rhs}, only per-tensor quantization
     * or per-channel quantization along kernel_output_feature_dimension is supported.
     * Thus, this attribute must be set to -1 or {@code dimension_numbers.kernel_output_feature_dimension}.
     * Other values will raise error at OpKernel construction.
     */
    public final long rhsQuantizationAxis;

    /**
     * The min value of the quantized data stored in {@code rhs}.
     * For example, if {@code Trhs} is qint8, this must be set to -127 if narrow range quantized or -128 if not.
     */
    public final long rhsQuantizationMinVal;

    /**
     * The max value of the quantized data stored in {@code rhs}.
     * For example, if {@code Trhs} is qint8, this must be set to 127.
     */
    public final long rhsQuantizationMaxVal;

    public Inputs(GraphOperation op) {
      super(new UniformQuantizedConvolutionHybrid<>(op), op, Arrays.asList("Tlhs", "Trhs", "Tout", "window_strides", "padding", "explicit_padding", "lhs_dilation", "rhs_dilation", "batch_group_count", "feature_group_count", "dimension_numbers", "rhs_quantization_axis", "rhs_quantization_min_val", "rhs_quantization_max_val"));
      int inputIndex = 0;
      lhs = (Operand<? extends TNumber>) op.input(inputIndex++);
      rhs = (Operand<? extends TNumber>) op.input(inputIndex++);
      rhsScales = (Operand<TFloat32>) op.input(inputIndex++);
      rhsZeroPoints = (Operand<TInt32>) op.input(inputIndex++);
      Tlhs = op.attributes().getAttrType("Tlhs");
      Trhs = op.attributes().getAttrType("Trhs");
      Tout = op.attributes().getAttrType("Tout");
      windowStrides = op.attributes().getAttrIntList("window_strides");
      padding = op.attributes().getAttrString("padding");
      explicitPadding = op.attributes().getAttrIntList("explicit_padding");
      lhsDilation = op.attributes().getAttrIntList("lhs_dilation");
      rhsDilation = op.attributes().getAttrIntList("rhs_dilation");
      batchGroupCount = op.attributes().getAttrInt("batch_group_count");
      featureGroupCount = op.attributes().getAttrInt("feature_group_count");
      dimensionNumbers = op.attributes().getAttrString("dimension_numbers");
      rhsQuantizationAxis = op.attributes().getAttrInt("rhs_quantization_axis");
      rhsQuantizationMinVal = op.attributes().getAttrInt("rhs_quantization_min_val");
      rhsQuantizationMaxVal = op.attributes().getAttrInt("rhs_quantization_max_val");
    }
  }
}
