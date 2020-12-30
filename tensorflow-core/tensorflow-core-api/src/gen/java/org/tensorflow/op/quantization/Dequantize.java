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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Dequantize the 'input' tensor into a float or bfloat16 Tensor.
 * <p>
 * [min_range, max_range] are scalar floats that specify the range for
 * the output. The 'mode' attribute controls exactly which calculations are
 * used to convert the float values to their quantized equivalents.
 * <p>
 * In 'MIN_COMBINED' mode, each value of the tensor will undergo the following:
 * <pre>{@code
 * if T == qint8: in[i] += (range(T) + 1)/ 2.0
 * out[i] = min_range + (in[i]* (max_range - min_range) / range(T))
 * }</pre>
 * here `range(T) = numeric_limits<T>::max() - numeric_limits<T>::min()`
 * <p>
 * <i>MIN_COMBINED Mode Example</i>
 * <p>
 * If the input comes from a QuantizedRelu6, the output type is
 * quint8 (range of 0-255) but the possible range of QuantizedRelu6 is
 * 0-6.  The min_range and max_range values are therefore 0.0 and 6.0.
 * Dequantize on quint8 will take each value, cast to float, and multiply
 * by 6 / 255.
 * Note that if quantizedtype is qint8, the operation will additionally add
 * each value by 128 prior to casting.
 * <p>
 * If the mode is 'MIN_FIRST', then this approach is used:
 * <pre>{@code
 * num_discrete_values = 1 << (# of bits in T)
 * range_adjust = num_discrete_values / (num_discrete_values - 1)
 * range = (range_max - range_min) * range_adjust
 * range_scale = range / num_discrete_values
 * const double offset_input = static_cast<double>(input) - lowest_quantized;
 * result = range_min + ((input - numeric_limits<T>::min()) * range_scale)
 * }</pre>
 * If the mode is `SCALED`, dequantization is performed by multiplying each
 * input value by a scaling_factor. (Thus an input of 0 always maps to 0.0).
 * <p>
 * The scaling_factor is determined from `min_range`, `max_range`, and
 * `narrow_range` in a way that is compatible with `QuantizeAndDequantize{V2|V3}`
 * and `QuantizeV2`, using the following algorithm:
 * <pre>{@code
 *   const int min_expected_T = std::numeric_limits<T>::min() +
 *     (narrow_range ? 1 : 0);
 *   const int max_expected_T = std::numeric_limits<T>::max();
 *   const float max_expected_T = std::numeric_limits<float>::max();
 * 
 *   const float scale_factor =
 *     (std::numeric_limits<T>::min() == 0) ? (max_range / max_expected_T)
 *                                          : std::max(min_range / min_expected_T,
 *                                                     max_range / max_expected_T);
 * }</pre>
 * 
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator(group = "quantization")
public final class Dequantize<U extends TNumber> extends RawOp implements Operand<U> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.quantization.Dequantize}
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
     * @param narrowRange 
     */
    public Options narrowRange(Boolean narrowRange) {
      this.narrowRange = narrowRange;
      return this;
    }
    
    /**
     * @param axis 
     */
    public Options axis(Long axis) {
      this.axis = axis;
      return this;
    }
    
    private String mode;
    private Boolean narrowRange;
    private Long axis;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Dequantize operation.
   * 
   * @param scope current scope
   * @param input 
   * @param minRange The minimum scalar value possibly produced for the input.
   * @param maxRange The maximum scalar value possibly produced for the input.
   * @param dtype Type of the output tensor. Currently Dequantize supports float and bfloat16.
   * If 'dtype' is 'bfloat16', it only supports 'MIN_COMBINED' mode.
   * @param options carries optional attributes values
   * @return a new instance of Dequantize
   */
  @Endpoint(describeByClass = true)
  public static <U extends TNumber, T extends TType> Dequantize<U> create(Scope scope, Operand<T> input, Operand<TFloat32> minRange, Operand<TFloat32> maxRange, Class<U> dtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Dequantize", scope.makeOpName("Dequantize"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(minRange.asOutput());
    opBuilder.addInput(maxRange.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.mode != null) {
          opBuilder.setAttr("mode", opts.mode);
        }
        if (opts.narrowRange != null) {
          opBuilder.setAttr("narrow_range", opts.narrowRange);
        }
        if (opts.axis != null) {
          opBuilder.setAttr("axis", opts.axis);
        }
      }
    }
    return new Dequantize<U>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new Dequantize operation using default output types.
   * 
   * @param scope current scope
   * @param input 
   * @param minRange The minimum scalar value possibly produced for the input.
   * @param maxRange The maximum scalar value possibly produced for the input.
   * @param options carries optional attributes values
   * @return a new instance of Dequantize
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Dequantize<TFloat32> create(Scope scope, Operand<T> input, Operand<TFloat32> minRange, Operand<TFloat32> maxRange, Options... options) {
    return create(scope, input, minRange, maxRange, TFloat32.class, options);
  }
  
  /**
   * @param mode 
   */
  public static Options mode(String mode) {
    return new Options().mode(mode);
  }
  
  /**
   * @param narrowRange 
   */
  public static Options narrowRange(Boolean narrowRange) {
    return new Options().narrowRange(narrowRange);
  }
  
  /**
   * @param axis 
   */
  public static Options axis(Long axis) {
    return new Options().axis(axis);
  }
  
  /**
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Dequantize";
  
  private Output<U> output;
  
  private Dequantize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
