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
import org.tensorflow.types.family.TNumber;

/**
 * Computes the gradient of morphological 2-D dilation with respect to the input.
 * 
 * @param <T> data type for {@code inBackprop()} output
 */
@Operator(group = "nn")
public final class Dilation2dBackpropInput<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Dilation2dBackpropInput operation.
   * 
   * @param scope current scope
   * @param input 4-D with shape `[batch, in_height, in_width, depth]`.
   * @param filter 3-D with shape `[filter_height, filter_width, depth]`.
   * @param outBackprop 4-D with shape `[batch, out_height, out_width, depth]`.
   * @param strides 1-D of length 4. The stride of the sliding window for each dimension of
   * the input tensor. Must be: `[1, stride_height, stride_width, 1]`.
   * @param rates 1-D of length 4. The input stride for atrous morphological dilation.
   * Must be: `[1, rate_height, rate_width, 1]`.
   * @param padding The type of padding algorithm to use.
   * @return a new instance of Dilation2dBackpropInput
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> Dilation2dBackpropInput<T> create(Scope scope, Operand<T> input, Operand<T> filter, Operand<T> outBackprop, List<Long> strides, List<Long> rates, String padding) {
    OperationBuilder opBuilder = scope.env().opBuilder("Dilation2DBackpropInput", scope.makeOpName("Dilation2dBackpropInput"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(filter.asOutput());
    opBuilder.addInput(outBackprop.asOutput());
    opBuilder = scope.apply(opBuilder);
    long[] stridesArray = new long[strides.size()];
    for (int i = 0; i < stridesArray.length; ++i) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    long[] ratesArray = new long[rates.size()];
    for (int i = 0; i < ratesArray.length; ++i) {
      ratesArray[i] = rates.get(i);
    }
    opBuilder.setAttr("rates", ratesArray);
    opBuilder.setAttr("padding", padding);
    return new Dilation2dBackpropInput<T>(opBuilder.build());
  }
  
  /**
   * 4-D with shape `[batch, in_height, in_width, depth]`.
   */
  public Output<T> inBackprop() {
    return inBackprop;
  }
  
  @Override
  public Output<T> asOutput() {
    return inBackprop;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Dilation2DBackpropInput";
  
  private Output<T> inBackprop;
  
  private Dilation2dBackpropInput(Operation operation) {
    super(operation);
    int outputIdx = 0;
    inBackprop = operation.output(outputIdx++);
  }
}
