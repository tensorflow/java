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

package org.tensorflow.op.nn;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * Adds Tensor 'bias' to Tensor 'input' for Quantized types.
 * <p>
 * Broadcasts the values of bias on dimensions 0..N-2 of 'input'.
 * 
 * @param <V> data type for {@code output()} output
 */
@Operator(group = "nn")
public final class QuantizedBiasAdd<V extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new QuantizedBiasAdd operation.
   * 
   * @param scope current scope
   * @param input 
   * @param bias A 1D bias Tensor with size matching the last dimension of 'input'.
   * @param minInput The float value that the lowest quantized input value represents.
   * @param maxInput The float value that the highest quantized input value represents.
   * @param minBias The float value that the lowest quantized bias value represents.
   * @param maxBias The float value that the highest quantized bias value represents.
   * @param outType 
   * @return a new instance of QuantizedBiasAdd
   */
  @Endpoint(describeByClass = true)
  public static <V extends TType, T extends TType, U extends TType> QuantizedBiasAdd<V> create(Scope scope, Operand<T> input, Operand<U> bias, Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minBias, Operand<TFloat32> maxBias, Class<V> outType) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizedBiasAdd", scope.makeOpName("QuantizedBiasAdd"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(bias.asOutput());
    opBuilder.addInput(minInput.asOutput());
    opBuilder.addInput(maxInput.asOutput());
    opBuilder.addInput(minBias.asOutput());
    opBuilder.addInput(maxBias.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new QuantizedBiasAdd<V>(opBuilder.build());
  }
  
  /**
   */
  public Output<V> output() {
    return output;
  }
  
  /**
   * The float value that the lowest quantized output value represents.
   */
  public Output<TFloat32> minOut() {
    return minOut;
  }
  
  /**
   * The float value that the highest quantized output value represents.
   */
  public Output<TFloat32> maxOut() {
    return maxOut;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "QuantizedBiasAdd";
  
  private Output<V> output;
  private Output<TFloat32> minOut;
  private Output<TFloat32> maxOut;
  
  private QuantizedBiasAdd(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    minOut = operation.output(outputIdx++);
    maxOut = operation.output(outputIdx++);
  }
}
