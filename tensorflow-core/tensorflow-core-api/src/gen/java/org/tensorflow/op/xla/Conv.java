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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Wraps the XLA ConvGeneralDilated operator, documented at
 * <p>
 *  https://www.tensorflow.org/performance/xla/operation_semantics#conv_convolution
 * .
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "xla")
public final class Conv<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Conv operation.
   * 
   * @param scope current scope
   * @param lhs the input tensor
   * @param rhs the kernel tensor
   * @param windowStrides the inter-window strides
   * @param padding the padding to apply at the start and end of each input dimensions
   * @param lhsDilation dilation to apply between input elements
   * @param rhsDilation dilation to apply between kernel elements
   * @param featureGroupCount number of feature groups for grouped convolution.
   * @param dimensionNumbers a serialized xla::ConvolutionDimensionNumbers proto.
   * @param precisionConfig a serialized xla::PrecisionConfig proto.
   * @return a new instance of Conv
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> Conv<T> create(Scope scope, Operand<T> lhs, Operand<T> rhs, Operand<U> windowStrides, Operand<U> padding, Operand<U> lhsDilation, Operand<U> rhsDilation, Operand<U> featureGroupCount, String dimensionNumbers, String precisionConfig) {
    OperationBuilder opBuilder = scope.env().opBuilder("XlaConv", scope.makeOpName("Conv"));
    opBuilder.addInput(lhs.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder.addInput(windowStrides.asOutput());
    opBuilder.addInput(padding.asOutput());
    opBuilder.addInput(lhsDilation.asOutput());
    opBuilder.addInput(rhsDilation.asOutput());
    opBuilder.addInput(featureGroupCount.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dimension_numbers", dimensionNumbers);
    opBuilder.setAttr("precision_config", precisionConfig);
    return new Conv<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "XlaConv";
  
  private Output<T> output;
  
  private Conv(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
