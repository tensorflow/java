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
import org.tensorflow.types.family.TType;

/**
 * Convert the quantized 'input' tensor into a lower-precision 'output', using the
 * <p>
 * actual distribution of the values to maximize the usage of the lower bit depth
 * and adjusting the output min and max ranges accordingly.
 * <p>
 * [input_min, input_max] are scalar floats that specify the range for the float
 * interpretation of the 'input' data. For example, if input_min is -1.0f and
 * input_max is 1.0f, and we are dealing with quint16 quantized data, then a 0
 * value in the 16-bit data should be interpreted as -1.0f, and a 65535 means 1.0f.
 * <p>
 * This operator tries to squeeze as much precision as possible into an output with
 * a lower bit depth by calculating the actual min and max values found in the
 * data. For example, maybe that quint16 input has no values lower than 16,384 and
 * none higher than 49,152. That means only half the range is actually needed, all
 * the float interpretations are between -0.5f and 0.5f, so if we want to compress
 * the data into a quint8 output, we can use that range rather than the theoretical
 * -1.0f to 1.0f that is suggested by the input min and max.
 * <p>
 * In practice, this is most useful for taking output from operations like
 * QuantizedMatMul that can produce higher bit-depth outputs than their inputs and
 * may have large potential output ranges, but in practice have a distribution of
 * input values that only uses a small fraction of the possible range. By feeding
 * that output into this operator, we can reduce it from 32 bits down to 8 with
 * minimal loss of accuracy.
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator(group = "quantization")
public final class QuantizeDownAndShrinkRange<U extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new QuantizeDownAndShrinkRange operation.
   * 
   * @param scope current scope
   * @param input 
   * @param inputMin The float value that the minimum quantized input value represents.
   * @param inputMax The float value that the maximum quantized input value represents.
   * @param outType The type of the output. Should be a lower bit depth than Tinput.
   * @return a new instance of QuantizeDownAndShrinkRange
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TType> QuantizeDownAndShrinkRange<U> create(Scope scope, Operand<T> input, Operand<TFloat32> inputMin, Operand<TFloat32> inputMax, Class<U> outType) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizeDownAndShrinkRange", scope.makeOpName("QuantizeDownAndShrinkRange"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputMin.asOutput());
    opBuilder.addInput(inputMax.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new QuantizeDownAndShrinkRange<U>(opBuilder.build());
  }
  
  /**
   */
  public Output<U> output() {
    return output;
  }
  
  /**
   * The float value that the minimum quantized output value represents.
   */
  public Output<TFloat32> outputMin() {
    return outputMin;
  }
  
  /**
   * The float value that the maximum quantized output value represents.
   */
  public Output<TFloat32> outputMax() {
    return outputMax;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "QuantizeDownAndShrinkRange";
  
  private Output<U> output;
  private Output<TFloat32> outputMin;
  private Output<TFloat32> outputMax;
  
  private QuantizeDownAndShrinkRange(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    outputMin = operation.output(outputIdx++);
    outputMax = operation.output(outputIdx++);
  }
}
