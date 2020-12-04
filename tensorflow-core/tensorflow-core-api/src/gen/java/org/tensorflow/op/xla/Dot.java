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
import org.tensorflow.types.family.TType;

/**
 * Wraps the XLA DotGeneral operator, documented at
 * <p>
 *  https://www.tensorflow.org/performance/xla/operation_semantics#dotgeneral
 * .
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "xla")
public final class Dot<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Dot operation.
   * 
   * @param scope current scope
   * @param lhs the LHS tensor
   * @param rhs the RHS tensor
   * @param dimensionNumbers a serialized xla::DotDimensionNumbers proto.
   * @param precisionConfig a serialized xla::PrecisionConfig proto.
   * @return a new instance of Dot
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Dot<T> create(Scope scope, Operand<T> lhs, Operand<T> rhs, String dimensionNumbers, String precisionConfig) {
    OperationBuilder opBuilder = scope.env().opBuilder("XlaDot", scope.makeOpName("Dot"));
    opBuilder.addInput(lhs.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dimension_numbers", dimensionNumbers);
    opBuilder.setAttr("precision_config", precisionConfig);
    return new Dot<T>(opBuilder.build());
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
  public static final String OP_NAME = "XlaDot";
  
  private Output<T> output;
  
  private Dot(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
