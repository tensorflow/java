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
import org.tensorflow.types.family.TType;

/**
 * Returns x / y element-wise for integer types.
 * <p>
 * Truncation designates that negative numbers will round fractional quantities
 * toward zero. I.e. -7 / 5 = -1. This matches C semantics but it is different
 * than Python semantics. See `FloorDiv` for a division function that matches
 * Python Semantics.
 * <p>
 * <i>NOTE</i>: `math.TruncateDiv` supports broadcasting. More about broadcasting
 * [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
 * 
 * @param <T> data type for {@code z()} output
 */
@Operator(group = "math")
public final class TruncateDiv<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new TruncateDiv operation.
   * 
   * @param scope current scope
   * @param x 
   * @param y 
   * @return a new instance of TruncateDiv
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TruncateDiv<T> create(Scope scope, Operand<T> x, Operand<T> y) {
    OperationBuilder opBuilder = scope.env().opBuilder("TruncateDiv", scope.makeOpName("TruncateDiv"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TruncateDiv<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> z() {
    return z;
  }
  
  @Override
  public Output<T> asOutput() {
    return z;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TruncateDiv";
  
  private Output<T> z;
  
  private TruncateDiv(Operation operation) {
    super(operation);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }
}
