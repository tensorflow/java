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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * Produces the max pool of the input tensor for quantized types.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "nn")
public final class QuantizedMaxPool<T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new QuantizedMaxPool operation.
   * 
   * @param scope current scope
   * @param input The 4D (batch x rows x cols x depth) Tensor to MaxReduce over.
   * @param minInput The float value that the lowest quantized input value represents.
   * @param maxInput The float value that the highest quantized input value represents.
   * @param ksize The size of the window for each dimension of the input tensor.
   * The length must be 4 to match the number of dimensions of the input.
   * @param strides The stride of the sliding window for each dimension of the input
   * tensor. The length must be 4 to match the number of dimensions of the input.
   * @param padding The type of padding algorithm to use.
   * @return a new instance of QuantizedMaxPool
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> QuantizedMaxPool<T> create(Scope scope, Operand<T> input, Operand<TFloat32> minInput, Operand<TFloat32> maxInput, List<Long> ksize, List<Long> strides, String padding) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizedMaxPool", scope.makeOpName("QuantizedMaxPool"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(minInput.asOutput());
    opBuilder.addInput(maxInput.asOutput());
    opBuilder = scope.apply(opBuilder);
    long[] ksizeArray = new long[ksize.size()];
    for (int i = 0; i < ksizeArray.length; ++i) {
      ksizeArray[i] = ksize.get(i);
    }
    opBuilder.setAttr("ksize", ksizeArray);
    long[] stridesArray = new long[strides.size()];
    for (int i = 0; i < stridesArray.length; ++i) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    return new QuantizedMaxPool<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  /**
   * The float value that the lowest quantized output value represents.
   */
  public Output<TFloat32> minOutput() {
    return minOutput;
  }
  
  /**
   * The float value that the highest quantized output value represents.
   */
  public Output<TFloat32> maxOutput() {
    return maxOutput;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "QuantizedMaxPool";
  
  private Output<T> output;
  private Output<TFloat32> minOutput;
  private Output<TFloat32> maxOutput;
  
  private QuantizedMaxPool(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    minOutput = operation.output(outputIdx++);
    maxOutput = operation.output(outputIdx++);
  }
}
