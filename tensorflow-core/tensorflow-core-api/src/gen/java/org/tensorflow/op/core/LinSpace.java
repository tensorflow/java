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

package org.tensorflow.op.core;

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
 * Generates values in an interval.
 * <p>
 * A sequence of `num` evenly-spaced values are generated beginning at `start`.
 * If `num > 1`, the values in the sequence increase by `stop - start / num - 1`,
 * so that the last one is exactly `stop`.
 * <p>
 * For example:
 * <pre>{@code
 * tf.linspace(10.0, 12.0, 3, name="linspace") => [ 10.0  11.0  12.0]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
public final class LinSpace<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new LinSpace operation.
   * 
   * @param scope current scope
   * @param start 0-D tensor. First entry in the range.
   * @param stop 0-D tensor. Last entry in the range.
   * @param num 0-D tensor. Number of values to generate.
   * @return a new instance of LinSpace
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber> LinSpace<T> create(Scope scope, Operand<T> start, Operand<T> stop, Operand<U> num) {
    OperationBuilder opBuilder = scope.env().opBuilder("LinSpace", scope.makeOpName("LinSpace"));
    opBuilder.addInput(start.asOutput());
    opBuilder.addInput(stop.asOutput());
    opBuilder.addInput(num.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new LinSpace<T>(opBuilder.build());
  }
  
  /**
   * 1-D. The generated values.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "LinSpace";
  
  private Output<T> output;
  
  private LinSpace(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
