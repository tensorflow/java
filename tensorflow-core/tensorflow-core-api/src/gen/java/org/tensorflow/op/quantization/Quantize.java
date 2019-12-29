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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * Quantize the 'input' tensor of type float to 'output' tensor of type 'T'.
 * <p>
 * [min_range, max_range] are scalar floats that specify the range for
 * the 'input' data. The 'mode' attribute controls exactly which calculations are
 * used to convert the float values to their quantized equivalents.  The
 * 'round_mode' attribute controls which rounding tie-breaking algorithm is used
 * when rounding float values to their quantized equivalents.
 * <p>
 * In 'MIN_COMBINED' mode, each value of the tensor will undergo the following:
 * <pre>{@code
 * out[i] = (in[i] - min_range) * range(T) / (max_range - min_range)
 * if T == qint8: out[i] -= (range(T) + 1) / 2.0
 * }</pre>
 * here `range(T) = numeric_limits<T>::max() - numeric_limits<T>::min()`
 * <p>
 * <i>MIN_COMBINED Mode Example</i>
 * <p>
 * Assume the input is type float and has a possible range of [0.0, 6.0] and the
 * output type is quint8 ([0, 255]). The min_range and max_range values should be
 * specified as 0.0 and 6.0. Quantizing from float to quint8 will multiply each
 * value of the input by 255/6 and cast to quint8.
 * <p>
 * If the output type was qint8 ([-128, 127]), the operation will additionally
 * subtract each value by 128 prior to casting, so that the range of values aligns
 * with the range of qint8.
 * <p>
 * If the mode is 'MIN_FIRST', then this approach is used:
 * <pre>{@code
 * num_discrete_values = 1 << (# of bits in T)
 * range_adjust = num_discrete_values / (num_discrete_values - 1)
 * range = (range_max - range_min) * range_adjust
 * range_scale = num_discrete_values / range
 * quantized = round(input * range_scale) - round(range_min * range_scale) +
 *   numeric_limits<T>::min()
 * quantized = max(quantized, numeric_limits<T>::min())
 * quantized = min(quantized, numeric_limits<T>::max())
 * }</pre>
 * The biggest difference between this and MIN_COMBINED is that the minimum range
 * is rounded first, before it's subtracted from the rounded value. With
 * MIN_COMBINED, a small bias is introduced where repeated iterations of quantizing
 * and dequantizing will introduce a larger and larger error.
 * <p>
 * <i>SCALED mode Example</i>
 * <p>
 * `SCALED` mode matches the quantization approach used in
 * `QuantizeAndDequantize{V2|V3}`.
 * <p>
 * If the mode is `SCALED`, we do not use the full range of the output type,
 * choosing to elide the lowest possible value for symmetry (e.g., output range is
 * -127 to 127, not -128 to 127 for signed 8 bit quantization), so that 0.0 maps to
 * 0.
 * <p>
 * We first find the range of values in our tensor. The
 * range we use is always centered on 0, so we find m such that
 * <pre>{@code
 *   m = max(abs(input_min), abs(input_max))
 * }</pre>
 * Our input tensor range is then `[-m, m]`.
 * <p>
 * Next, we choose our fixed-point quantization buckets, `[min_fixed, max_fixed]`.
 * If T is signed, this is
 * <pre>{@code
 *   num_bits = sizeof(T) * 8
 *   [min_fixed, max_fixed] =
 *       [-(1 << (num_bits - 1) - 1), (1 << (num_bits - 1)) - 1]
 * }</pre>
 * Otherwise, if T is unsigned, the fixed-point range is
 * <pre>{@code
 *   [min_fixed, max_fixed] = [0, (1 << num_bits) - 1]
 * }</pre>
 * From this we compute our scaling factor, s:
 * <pre>{@code
 *   s = (max_fixed - min_fixed) / (2 * m)
 * }</pre>
 * Now we can quantize the elements of our tensor:
 * <pre>{@code
 * result = round(input * s)
 * }</pre>
 * One thing to watch out for is that the operator may choose to adjust the
 * requested minimum and maximum values slightly during the quantization process,
 * so you should always use the output ports as the range for further calculations.
 * For example, if the requested minimum and maximum values are close to equal,
 * they will be separated by a small epsilon value to prevent ill-formed quantized
 * buffers from being created. Otherwise, you can end up with buffers where all the
 * quantized values map to the same float value, which causes problems for
 * operations that have to perform further calculations on them.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "quantization")
public final class Quantize<T extends TType> extends PrimitiveOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.quantization.Quantize}
   */
  public static class Options {
    
    /**
     * @param mode 
     */
    public Options mode(String mode) {
      this.mode = mode;
      return this;
    }
    
    /**
     * @param roundMode 
     */
    public Options roundMode(String roundMode) {
      this.roundMode = roundMode;
      return this;
    }
    
    private String mode;
    private String roundMode;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Quantize operation.
   * 
   * @param scope current scope
   * @param input 
   * @param minRange The minimum scalar value possibly produced for the input.
   * @param maxRange The maximum scalar value possibly produced for the input.
   * @param T 
   * @param options carries optional attributes values
   * @return a new instance of Quantize
   */
  public static <T extends TType> Quantize<T> create(Scope scope, Operand<TFloat32> input, Operand<TFloat32> minRange, Operand<TFloat32> maxRange, DataType<T> T, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizeV2", scope.makeOpName("Quantize"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(minRange.asOutput());
    opBuilder.addInput(maxRange.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    opBuilder.setAttr("T", T);
    if (options != null) {
      for (Options opts : options) {
        if (opts.mode != null) {
          opBuilder.setAttr("mode", opts.mode);
        }
        if (opts.roundMode != null) {
          opBuilder.setAttr("round_mode", opts.roundMode);
        }
      }
    }
    return new Quantize<T>(opBuilder.build());
  }
  
  /**
   * @param mode 
   */
  public static Options mode(String mode) {
    return new Options().mode(mode);
  }
  
  /**
   * @param roundMode 
   */
  public static Options roundMode(String roundMode) {
    return new Options().roundMode(roundMode);
  }
  
  /**
   * The quantized data produced from the float input.
   */
  public Output<T> output() {
    return output;
  }
  
  /**
   * The actual minimum scalar value used for the output.
   */
  public Output<TFloat32> outputMin() {
    return outputMin;
  }
  
  /**
   * The actual maximum scalar value used for the output.
   */
  public Output<TFloat32> outputMax() {
    return outputMax;
  }
  
  private Output<T> output;
  private Output<TFloat32> outputMin;
  private Output<TFloat32> outputMax;
  
  private Quantize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    outputMin = operation.output(outputIdx++);
    outputMax = operation.output(outputIdx++);
  }
}
