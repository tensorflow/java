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

package org.tensorflow.op.xla;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBfloat16;

/**
 * Takes the packed uint32 input and unpacks the input to uint8 to do
 * <p>
 * Dequantization on device.
 */
@Operator(group = "xla")
public final class Dequantize extends RawOp implements Operand<TBfloat16> {
  
  /**
   * Factory method to create a class wrapping a new Dequantize operation.
   * 
   * @param scope current scope
   * @param input Input tensors whose types is uint32, shape is [d0, ..., dn].
   * @param minRange The minimum scalar value possibly produced for the input.
   * @param maxRange The maximum scalar value possibly produced for the input.
   * @param mode String to determine the dequantize mode in {"MIN_COMBINED", "MIN_FIRST", "SCALED"}.
   * @param transposeOutput Boolean to determine if output is transposed. transpose_output
   * is faster when input is large and rank of input is higher than 1.
   * @return a new instance of Dequantize
   */
  @Endpoint(describeByClass = true)
  public static Dequantize create(Scope scope, Operand<?> input, Float minRange, Float maxRange, String mode, Boolean transposeOutput) {
    OperationBuilder opBuilder = scope.env().opBuilder("XlaDequantize", scope.makeOpName("Dequantize"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("min_range", minRange);
    opBuilder.setAttr("max_range", maxRange);
    opBuilder.setAttr("mode", mode);
    opBuilder.setAttr("transpose_output", transposeOutput);
    return new Dequantize(opBuilder.build());
  }
  
  /**
   * Output tensors whose types is bloat16. If transpose_output is true,
   * output shape is [dn * 4, dn-1, ..., d1, d0]. If transpose_output
   * is false, output shape is [d0,..., dn * 4].
   */
  public Output<TBfloat16> output() {
    return output;
  }
  
  @Override
  public Output<TBfloat16> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "XlaDequantize";
  
  private Output<TBfloat16> output;
  
  private Dequantize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
