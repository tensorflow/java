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

/**
 * Compute the Hurwitz zeta function \\(\zeta(x, q)\\).
 * <p>
 * The Hurwitz zeta function is defined as:
 * <p>
 * \\(\zeta(x, q) = \sum_{n=0}^{\infty} (q + n)^{-x}\\)
 * 
 * @param <T> data type for {@code z()} output
 */
@Operator(group = "math")
public final class Zeta<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Zeta operation.
   * 
   * @param scope current scope
   * @param x 
   * @param q 
   * @return a new instance of Zeta
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> Zeta<T> create(Scope scope, Operand<T> x, Operand<T> q) {
    OperationBuilder opBuilder = scope.env().opBuilder("Zeta", scope.makeOpName("Zeta"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(q.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Zeta<T>(opBuilder.build());
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
  public static final String OP_NAME = "Zeta";
  
  private Output<T> z;
  
  private Zeta(Operation operation) {
    super(operation);
    int outputIdx = 0;
    z = operation.output(outputIdx++);
  }
}
