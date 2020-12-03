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

package org.tensorflow.op.math;

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
 * Returns the next representable value of `x1` in the direction of `x2`, element-wise.
 * <p>
 * This operation returns the same result as the C++ std::nextafter function.
 * <p>
 * It can also return a subnormal number.
 * <p>
 * @compatibility(cpp)
 * Equivalent to C++ std::nextafter function.
 * @end_compatibility
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "math")
public final class NextAfter<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new NextAfter operation.
   * 
   * @param scope current scope
   * @param x1 
   * @param x2 
   * @return a new instance of NextAfter
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> NextAfter<T> create(Scope scope, Operand<T> x1, Operand<T> x2) {
    OperationBuilder opBuilder = scope.env().opBuilder("NextAfter", scope.makeOpName("NextAfter"));
    opBuilder.addInput(x1.asOutput());
    opBuilder.addInput(x2.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new NextAfter<T>(opBuilder.build());
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
  public static final String OP_NAME = "NextAfter";
  
  private Output<T> output;
  
  private NextAfter(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
