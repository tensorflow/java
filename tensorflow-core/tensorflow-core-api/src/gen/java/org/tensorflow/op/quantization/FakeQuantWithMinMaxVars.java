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

/**
 * Fake-quantize the 'inputs' tensor of type float via global float scalars `min`
 * <p>
 * and `max` to 'outputs' tensor of same shape as `inputs`.
 * <p>
 * `[min; max]` define the clamping range for the `inputs` data.
 * `inputs` values are quantized into the quantization range (`[0; 2^num_bits - 1]`
 * when `narrow_range` is false and `[1; 2^num_bits - 1]` when it is true) and
 * then de-quantized and output as floats in `[min; max]` interval.
 * `num_bits` is the bitwidth of the quantization; between 2 and 16, inclusive.
 * <p>
 * Before quantization, `min` and `max` values are adjusted with the following
 * logic.
 * It is suggested to have `min <= 0 <= max`. If `0` is not in the range of values,
 * the behavior can be unexpected:
 * If `0 < min < max`: `min_adj = 0` and `max_adj = max - min`.
 * If `min < max < 0`: `min_adj = min - max` and `max_adj = 0`.
 * If `min <= 0 <= max`: `scale = (max - min) / (2^num_bits - 1) `,
 * `min_adj = scale * round(min / scale)` and `max_adj = max + min_adj - min`.
 * <p>
 * This operation has a gradient and thus allows for training `min` and `max`
 * values.
 */
@Operator(group = "quantization")
public final class FakeQuantWithMinMaxVars extends PrimitiveOp implements Operand<TFloat32> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.quantization.FakeQuantWithMinMaxVars}
   */
  public static class Options {
    
    /**
     * @param numBits 
     */
    public Options numBits(Long numBits) {
      this.numBits = numBits;
      return this;
    }
    
    /**
     * @param narrowRange 
     */
    public Options narrowRange(Boolean narrowRange) {
      this.narrowRange = narrowRange;
      return this;
    }
    
    private Long numBits;
    private Boolean narrowRange;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new FakeQuantWithMinMaxVars operation.
   * 
   * @param scope current scope
   * @param inputs 
   * @param min 
   * @param max 
   * @param options carries optional attributes values
   * @return a new instance of FakeQuantWithMinMaxVars
   */
  public static FakeQuantWithMinMaxVars create(Scope scope, Operand<TFloat32> inputs, Operand<TFloat32> min, Operand<TFloat32> max, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("FakeQuantWithMinMaxVars", scope.makeOpName("FakeQuantWithMinMaxVars"));
    opBuilder.addInput(inputs.asOutput());
    opBuilder.addInput(min.asOutput());
    opBuilder.addInput(max.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.numBits != null) {
          opBuilder.setAttr("num_bits", opts.numBits);
        }
        if (opts.narrowRange != null) {
          opBuilder.setAttr("narrow_range", opts.narrowRange);
        }
      }
    }
    return new FakeQuantWithMinMaxVars(opBuilder.build());
  }
  
  /**
   * @param numBits 
   */
  public static Options numBits(Long numBits) {
    return new Options().numBits(numBits);
  }
  
  /**
   * @param narrowRange 
   */
  public static Options narrowRange(Boolean narrowRange) {
    return new Options().narrowRange(narrowRange);
  }
  
  /**
   */
  public Output<TFloat32> outputs() {
    return outputs;
  }
  
  @Override
  public Output<TFloat32> asOutput() {
    return outputs;
  }
  
  private Output<TFloat32> outputs;
  
  private FakeQuantWithMinMaxVars(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputs = operation.output(outputIdx++);
  }
}
