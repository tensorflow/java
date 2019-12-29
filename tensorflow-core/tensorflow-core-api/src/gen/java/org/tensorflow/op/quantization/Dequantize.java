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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * Dequantize the 'input' tensor into a float Tensor.
 * <p>
 * [min_range, max_range] are scalar floats that specify the range for
 * the 'input' data. The 'mode' attribute controls exactly which calculations are
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
 *   s = (2 * m) / (max_fixed - min_fixed)
 * }</pre>
 * Now we can dequantize the elements of our tensor:
 * <pre>{@code
 * result = input * s
 * }</pre>
 * 
 */
@Operator(group = "quantization")
public final class Dequantize extends PrimitiveOp implements Operand<TFloat32> {
  
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
    
    private String mode;
    
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
   * @param options carries optional attributes values
   * @return a new instance of Dequantize
   */
  public static <T extends TType> Dequantize create(Scope scope, Operand<T> input, Operand<TFloat32> minRange, Operand<TFloat32> maxRange, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Dequantize", scope.makeOpName("Dequantize"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(minRange.asOutput());
    opBuilder.addInput(maxRange.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.mode != null) {
          opBuilder.setAttr("mode", opts.mode);
        }
      }
    }
    return new Dequantize(opBuilder.build());
  }
  
  /**
   * @param mode 
   */
  public static Options mode(String mode) {
    return new Options().mode(mode);
  }
  
  /**
   */
  public Output<TFloat32> output() {
    return output;
  }
  
  @Override
  public Output<TFloat32> asOutput() {
    return output;
  }
  
  private Output<TFloat32> output;
  
  private Dequantize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
